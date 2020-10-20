package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.boardDefinition;
import com.huangjiang.taskcenter.orm.entity.boardDefinitionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface boardDefinitionMapper {
    int countByExample(boardDefinitionExample example);

    int deleteByExample(boardDefinitionExample example);

    int deleteByPrimaryKey(String id);

    int insert(boardDefinition record);

    int insertSelective(boardDefinition record);

    List<boardDefinition> selectByExample(boardDefinitionExample example);

    boardDefinition selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") boardDefinition record, @Param("example") boardDefinitionExample example);

    int updateByExample(@Param("record") boardDefinition record, @Param("example") boardDefinitionExample example);

    int updateByPrimaryKeySelective(boardDefinition record);

    int updateByPrimaryKey(boardDefinition record);
}
