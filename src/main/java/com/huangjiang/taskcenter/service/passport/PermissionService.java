package com.huangjiang.taskcenter.service.passport;

import com.huangjiang.taskcenter.orm.entity.PermissionEntity;
import com.huangjiang.taskcenter.orm.entity.RoleEntity;
import com.huangjiang.taskcenter.orm.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<RoleEntity> queryByPath(String requestUrl) {
        return permissionMapper.queryRoleByPath(requestUrl);
    }
}
