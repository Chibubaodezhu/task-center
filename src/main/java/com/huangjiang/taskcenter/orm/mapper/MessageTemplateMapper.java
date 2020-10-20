package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.MessageTemplateEntity;
import com.huangjiang.taskcenter.orm.entity.messageTemplate;
import com.huangjiang.taskcenter.orm.entity.messageTemplateExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageTemplateMapper {
    int countByExample(messageTemplateExample example);

    int deleteByExample(messageTemplateExample example);

    int deleteByPrimaryKey(String id);

    int insert(messageTemplate record);

    int insertSelective(messageTemplate record);

    MessageTemplateEntity selectByCode(@Param("code") String code);

    List<messageTemplate> selectByExample(messageTemplateExample example);

    messageTemplate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") messageTemplate record, @Param("example") messageTemplateExample example);

    int updateByExample(@Param("record") messageTemplate record, @Param("example") messageTemplateExample example);

    int updateByPrimaryKeySelective(messageTemplate record);

    int updateByPrimaryKey(messageTemplate record);
}
