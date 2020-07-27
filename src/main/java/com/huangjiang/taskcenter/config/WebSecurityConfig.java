package com.huangjiang.taskcenter.config;

import com.huangjiang.taskcenter.config.filter.ResfulUsernamePasswordAuthenticationFilter;
import com.huangjiang.taskcenter.config.handler.AuthenticationFailureHandlerImpl;
import com.huangjiang.taskcenter.config.handler.AuthenticationSuccessHandlerImpl;
import com.huangjiang.taskcenter.config.handler.LogoutSuccessHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Order(Integer.MIN_VALUE + 2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandlerImpl();
    }

    @Bean
    public AuthenticationFailureHandlerImpl authenticationFailureHandler() {
        return new AuthenticationFailureHandlerImpl();
    }

    /**
     * 使用用户名、密码认证的拦截器
     *
     * @return
     * @throws Exception
     */
    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter filter = new ResfulUsernamePasswordAuthenticationFilter();
        filter.setUsernameParameter("username");
        filter.setPasswordParameter("password");
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());
        RequestMatcher matcher = new AntPathRequestMatcher("/login", "POST");
        filter.setRequiresAuthenticationRequestMatcher(matcher);
        return filter;
    }

    /**
     * spring security安全拦截配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("SESSION")
                .logoutSuccessHandler(new LogoutSuccessHandlerImpl())
                .and()
                .addFilterBefore(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .requestCache().requestCache(new NullRequestCache())
                .and()
                .csrf().disable()
                .cors().disable();
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 请求cookie处理
     *
     * @return
     */
    @Bean
    public DefaultCookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookiePath("/");
        serializer.setSameSite(null);
        return serializer;
    }
}
