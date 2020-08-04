package com.huangjiang.taskcenter.service.passport;

import com.huangjiang.taskcenter.common.Constants;
import com.huangjiang.taskcenter.model.param.UserParam;
import com.huangjiang.taskcenter.orm.entity.UserEntity;
import com.huangjiang.taskcenter.orm.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class PassportService {

    @Autowired
    private UserMapper userMapper;

    public Boolean register(UserParam param) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String email = param.getEmail();
        if (StringUtils.isEmpty(email)) {
            throw new Exception("邮箱不能为空!");
        }
        int exist = userMapper.exist(email);
        if (exist == 0) {
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(param, userEntity);
            userEntity.setPassword(passwordEncoder.encode(param.getPassword()));
            String id = UUID.randomUUID().toString();
            userEntity.setId(id);
            userEntity.setStatus(Constants.USER_STATUS_NORMAL);
            return userMapper.insert(userEntity) > 0;
        } else {
            return false;
        }
    }

    public UserEntity selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }
}
