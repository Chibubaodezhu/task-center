package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardCellValue;
import com.huangjiang.taskcenter.orm.entity.BoardCellValueExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCellValueMapper {
    int countByExample(BoardCellValueExample example);

    int deleteByExample(BoardCellValueExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardCellValue record);

    int insertSelective(BoardCellValue record);

    List<BoardCellValue> selectByExample(BoardCellValueExample example);

    BoardCellValue selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardCellValue record, @Param("example") BoardCellValueExample example);

    int updateByExample(@Param("record") BoardCellValue record, @Param("example") BoardCellValueExample example);

    int updateByPrimaryKeySelective(BoardCellValue record);

    int updateByPrimaryKey(BoardCellValue record);
}
