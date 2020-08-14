package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.MessageTemplateEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTemplateMapper {

    int insert(MessageTemplateEntity entity);

    MessageTemplateEntity selectByCode(@Param("code") String code);
}
