package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardTemplateColumn;
import com.huangjiang.taskcenter.orm.entity.BoardTemplateColumnExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardTemplateColumnMapper {
    int countByExample(BoardTemplateColumnExample example);

    int deleteByExample(BoardTemplateColumnExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardTemplateColumn record);

    int insertSelective(BoardTemplateColumn record);

    List<BoardTemplateColumn> selectByExample(BoardTemplateColumnExample example);

    BoardTemplateColumn selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardTemplateColumn record, @Param("example") BoardTemplateColumnExample example);

    int updateByExample(@Param("record") BoardTemplateColumn record, @Param("example") BoardTemplateColumnExample example);

    int updateByPrimaryKeySelective(BoardTemplateColumn record);

    int updateByPrimaryKey(BoardTemplateColumn record);
}
