package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.VerifyCodeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyCodeMapper {

    int insert(VerifyCodeEntity entity);

    VerifyCodeEntity selectByMail(@Param("email") String email);

    int updateStatus(@Param("id") String id);
}
