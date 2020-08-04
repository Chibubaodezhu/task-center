package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int insert(UserEntity userEntity);

    int delete(String id);

    int update(UserEntity userEntity);

    int exist(@Param("email") String email);

    UserEntity selectByEmail(@Param("email")String email);
}
