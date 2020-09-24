package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.PermissionEntity;
import com.huangjiang.taskcenter.orm.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {

    List<RoleEntity> queryRoleByPath(@Param("url") String url);

    List<RoleEntity> queryUserRole(@Param("userId") String userId);
}
