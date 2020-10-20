package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardGroup;
import com.huangjiang.taskcenter.orm.entity.BoardGroupExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardGroupMapper {
    int countByExample(BoardGroupExample example);

    int deleteByExample(BoardGroupExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardGroup record);

    int insertSelective(BoardGroup record);

    List<BoardGroup> selectByExample(BoardGroupExample example);

    BoardGroup selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardGroup record, @Param("example") BoardGroupExample example);

    int updateByExample(@Param("record") BoardGroup record, @Param("example") BoardGroupExample example);

    int updateByPrimaryKeySelective(BoardGroup record);

    int updateByPrimaryKey(BoardGroup record);
}
