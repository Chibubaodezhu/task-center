package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardColumnDefinition;
import com.huangjiang.taskcenter.orm.entity.BoardColumnDefinitionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardColumnDefinitionMapper {
    int countByExample(BoardColumnDefinitionExample example);

    int deleteByExample(BoardColumnDefinitionExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardColumnDefinition record);

    int insertSelective(BoardColumnDefinition record);

    List<BoardColumnDefinition> selectByExample(BoardColumnDefinitionExample example);

    BoardColumnDefinition selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardColumnDefinition record, @Param("example") BoardColumnDefinitionExample example);

    int updateByExample(@Param("record") BoardColumnDefinition record, @Param("example") BoardColumnDefinitionExample example);

    int updateByPrimaryKeySelective(BoardColumnDefinition record);

    int updateByPrimaryKey(BoardColumnDefinition record);
}
