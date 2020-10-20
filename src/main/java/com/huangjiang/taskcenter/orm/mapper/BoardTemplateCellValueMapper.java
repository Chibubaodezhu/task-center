package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardTemplateCellValue;
import com.huangjiang.taskcenter.orm.entity.BoardTemplateCellValueExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardTemplateCellValueMapper {
    int countByExample(BoardTemplateCellValueExample example);

    int deleteByExample(BoardTemplateCellValueExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardTemplateCellValue record);

    int insertSelective(BoardTemplateCellValue record);

    List<BoardTemplateCellValue> selectByExample(BoardTemplateCellValueExample example);

    BoardTemplateCellValue selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardTemplateCellValue record, @Param("example") BoardTemplateCellValueExample example);

    int updateByExample(@Param("record") BoardTemplateCellValue record, @Param("example") BoardTemplateCellValueExample example);

    int updateByPrimaryKeySelective(BoardTemplateCellValue record);

    int updateByPrimaryKey(BoardTemplateCellValue record);
}
