package com.huangjiang.taskcenter.config.service;

import com.huangjiang.taskcenter.common.Constants;
import com.huangjiang.taskcenter.orm.entity.RoleEntity;
import com.huangjiang.taskcenter.orm.entity.UserEntity;
import com.huangjiang.taskcenter.orm.mapper.PermissionMapper;
import com.huangjiang.taskcenter.service.passport.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PassportService passportService;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(s)) {
            throw new RuntimeException("用户名为空");
        }
        UserEntity userEntity = passportService.selectByEmail(s);
        if (userEntity == null) {
            throw new RuntimeException("用户不存在");
        }
        boolean status = false;
        if (Constants.USER_STATUS_NORMAL.equals(userEntity.getStatus())) {
            status = true;
        }
        List<RoleEntity> roleEntities = permissionMapper.queryUserRole(s);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleEntities)) {
            roleEntities.forEach(roleEntity -> authorities.add(new SimpleGrantedAuthority(roleEntity.getCode())));
        }
        return new User(s, userEntity.getPassword(), status, status, status, status, authorities);
    }
}
