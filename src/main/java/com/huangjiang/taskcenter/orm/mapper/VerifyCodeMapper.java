package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.VerifyCodeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyCodeMapper {

    int insert(VerifyCodeEntity entity);
}
