package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardCollaborationBox;
import com.huangjiang.taskcenter.orm.entity.BoardCollaborationBoxExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCollaborationBoxMapper {
    int countByExample(BoardCollaborationBoxExample example);

    int deleteByExample(BoardCollaborationBoxExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardCollaborationBox record);

    int insertSelective(BoardCollaborationBox record);

    List<BoardCollaborationBox> selectByExampleWithBLOBs(BoardCollaborationBoxExample example);

    List<BoardCollaborationBox> selectByExample(BoardCollaborationBoxExample example);

    BoardCollaborationBox selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardCollaborationBox record, @Param("example") BoardCollaborationBoxExample example);

    int updateByExampleWithBLOBs(@Param("record") BoardCollaborationBox record, @Param("example") BoardCollaborationBoxExample example);

    int updateByExample(@Param("record") BoardCollaborationBox record, @Param("example") BoardCollaborationBoxExample example);

    int updateByPrimaryKeySelective(BoardCollaborationBox record);

    int updateByPrimaryKeyWithBLOBs(BoardCollaborationBox record);

    int updateByPrimaryKey(BoardCollaborationBox record);
}
