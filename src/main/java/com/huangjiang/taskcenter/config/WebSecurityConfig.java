package com.huangjiang.taskcenter.config;

import com.huangjiang.taskcenter.config.handler.*;
import com.huangjiang.taskcenter.config.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //登录成功处理逻辑
    @Autowired
    CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    //登录失败处理逻辑
    @Autowired
    CustomAuthenticationFailureHandler authenticationFailureHandler;

    //权限拒绝处理逻辑
    @Autowired
    CustomAccessDeniedHandler accessDeniedHandler;

    //匿名用户访问无权限资源时的异常
    @Autowired
    CustomAuthenticationEntryPoint authenticationEntryPoint;

    //会话失效(账号被挤下线)处理逻辑
    @Autowired
    CustomSessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    //登出成功处理逻辑
    @Autowired
    CustomLogoutSuccessHandler logoutSuccessHandler;

    //访问决策管理器
    @Autowired
    CustomAccessDecisionManager accessDecisionManager;

    //实现权限拦截
    @Autowired
    CustomSecurityMetadataSource securityMetadataSource;

    @Autowired
    private CustomAbstractSecurityInterceptor securityInterceptor;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(accessDecisionManager); // 决策管理器
                        o.setSecurityMetadataSource(securityMetadataSource); // 安全元数据源
                        return o;
                    }
                })
                // 登出
                .and().logout()
                .permitAll() // 允许所有用户
                .logoutSuccessHandler(logoutSuccessHandler) // 登出成功处理逻辑
                .deleteCookies("JSESSIONID") // 登出之后删除cookie
                // 登入
                .and().formLogin()
                .permitAll() // 允许所有用户
                .successHandler(authenticationSuccessHandler) // 登录成功处理逻辑
                .failureHandler(authenticationFailureHandler) // 登录失败处理逻辑
                // 异常处理(权限拒绝、登录失效等)
                .and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler) // 权限拒绝处理逻辑
                .authenticationEntryPoint(authenticationEntryPoint) // 匿名用户访问无权限资源时的异常处理
                // 会话管理
                .and().sessionManagement()
                .maximumSessions(1) // 同一账号同时登录最大用户数
                .expiredSessionStrategy(sessionInformationExpiredStrategy); // 会话失效(账号被挤下线)处理逻辑
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }

}
