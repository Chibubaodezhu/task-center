package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.PermissionEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {

    List<PermissionEntity> selectByPath(@Param("url") String url);
}
