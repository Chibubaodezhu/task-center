package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardCellValueText;
import com.huangjiang.taskcenter.orm.entity.BoardCellValueTextExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCellValueTextMapper {
    int countByExample(BoardCellValueTextExample example);

    int deleteByExample(BoardCellValueTextExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardCellValueText record);

    int insertSelective(BoardCellValueText record);

    List<BoardCellValueText> selectByExample(BoardCellValueTextExample example);

    BoardCellValueText selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardCellValueText record, @Param("example") BoardCellValueTextExample example);

    int updateByExample(@Param("record") BoardCellValueText record, @Param("example") BoardCellValueTextExample example);

    int updateByPrimaryKeySelective(BoardCellValueText record);

    int updateByPrimaryKey(BoardCellValueText record);
}
