package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardSubscribers;
import com.huangjiang.taskcenter.orm.entity.BoardSubscribersExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardSubscribersMapper {
    int countByExample(BoardSubscribersExample example);

    int deleteByExample(BoardSubscribersExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardSubscribers record);

    int insertSelective(BoardSubscribers record);

    List<BoardSubscribers> selectByExample(BoardSubscribersExample example);

    BoardSubscribers selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardSubscribers record, @Param("example") BoardSubscribersExample example);

    int updateByExample(@Param("record") BoardSubscribers record, @Param("example") BoardSubscribersExample example);

    int updateByPrimaryKeySelective(BoardSubscribers record);

    int updateByPrimaryKey(BoardSubscribers record);
}
