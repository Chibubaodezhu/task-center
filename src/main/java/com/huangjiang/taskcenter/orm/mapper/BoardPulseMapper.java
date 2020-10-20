package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardPulse;
import com.huangjiang.taskcenter.orm.entity.BoardPulseExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardPulseMapper {
    int countByExample(BoardPulseExample example);

    int deleteByExample(BoardPulseExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardPulse record);

    int insertSelective(BoardPulse record);

    List<BoardPulse> selectByExample(BoardPulseExample example);

    BoardPulse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardPulse record, @Param("example") BoardPulseExample example);

    int updateByExample(@Param("record") BoardPulse record, @Param("example") BoardPulseExample example);

    int updateByPrimaryKeySelective(BoardPulse record);

    int updateByPrimaryKey(BoardPulse record);
}
