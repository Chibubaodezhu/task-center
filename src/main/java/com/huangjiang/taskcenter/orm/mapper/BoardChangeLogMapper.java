package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardChangeLog;
import com.huangjiang.taskcenter.orm.entity.BoardChangeLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardChangeLogMapper {
    int countByExample(BoardChangeLogExample example);

    int deleteByExample(BoardChangeLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardChangeLog record);

    int insertSelective(BoardChangeLog record);

    List<BoardChangeLog> selectByExample(BoardChangeLogExample example);

    BoardChangeLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardChangeLog record, @Param("example") BoardChangeLogExample example);

    int updateByExample(@Param("record") BoardChangeLog record, @Param("example") BoardChangeLogExample example);

    int updateByPrimaryKeySelective(BoardChangeLog record);

    int updateByPrimaryKey(BoardChangeLog record);
}
