package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardPulsePost;
import com.huangjiang.taskcenter.orm.entity.BoardPulsePostExample;
import com.huangjiang.taskcenter.orm.entity.BoardPulsePostWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardPulsePostMapper {
    int countByExample(BoardPulsePostExample example);

    int deleteByExample(BoardPulsePostExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardPulsePostWithBLOBs record);

    int insertSelective(BoardPulsePostWithBLOBs record);

    List<BoardPulsePostWithBLOBs> selectByExampleWithBLOBs(BoardPulsePostExample example);

    List<BoardPulsePost> selectByExample(BoardPulsePostExample example);

    BoardPulsePostWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardPulsePostWithBLOBs record, @Param("example") BoardPulsePostExample example);

    int updateByExampleWithBLOBs(@Param("record") BoardPulsePostWithBLOBs record, @Param("example") BoardPulsePostExample example);

    int updateByExample(@Param("record") BoardPulsePost record, @Param("example") BoardPulsePostExample example);

    int updateByPrimaryKeySelective(BoardPulsePostWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BoardPulsePostWithBLOBs record);

    int updateByPrimaryKey(BoardPulsePost record);
}
