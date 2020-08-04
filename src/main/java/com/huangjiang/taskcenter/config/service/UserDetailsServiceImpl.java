package com.huangjiang.taskcenter.config.service;

import com.huangjiang.taskcenter.common.Constants;
import com.huangjiang.taskcenter.orm.entity.UserEntity;
import com.huangjiang.taskcenter.service.passport.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PassportService passportService;

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
        return new User(s, userEntity.getPassword(), status, status, status, status, new ArrayList<>());
    }
}
