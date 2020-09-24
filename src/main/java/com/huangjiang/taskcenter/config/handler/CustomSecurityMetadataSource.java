package com.huangjiang.taskcenter.config.handler;

import com.huangjiang.taskcenter.common.Constants;
import com.huangjiang.taskcenter.orm.entity.PermissionEntity;
import com.huangjiang.taskcenter.orm.entity.RoleEntity;
import com.huangjiang.taskcenter.service.passport.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static final List<String> whiteList = new ArrayList<>();

    @Autowired
    PermissionService permissionService;

    @PostConstruct
    private void init() {
        whiteList.add("/login");
        whiteList.add("/passport/**");
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        HttpServletRequest request = ((FilterInvocation) o).getRequest();
        for (String url: whiteList) {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
            if (matcher.matches(request)) {
                return Constants.PERMISSION_ALL;
            }
        }
        //查询具体某个接口的权限
        List<RoleEntity> roleEntities = permissionService.queryByPath(requestUrl);
        if (CollectionUtils.isEmpty(roleEntities)) {
            //请求路径没有配置权限，表明该请求接口可以任意访问
            return null;
        }
        String[] attributes = new String[roleEntities.size()];
        for (int i = 0; i < roleEntities.size(); i++) {
            attributes[i] = roleEntities.get(i).getCode();
        }
        return SecurityConfig.createList(attributes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
