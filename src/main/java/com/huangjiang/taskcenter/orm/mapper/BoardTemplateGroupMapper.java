package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardTemplateGroup;
import com.huangjiang.taskcenter.orm.entity.BoardTemplateGroupExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardTemplateGroupMapper {
    int countByExample(BoardTemplateGroupExample example);

    int deleteByExample(BoardTemplateGroupExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardTemplateGroup record);

    int insertSelective(BoardTemplateGroup record);

    List<BoardTemplateGroup> selectByExample(BoardTemplateGroupExample example);

    BoardTemplateGroup selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardTemplateGroup record, @Param("example") BoardTemplateGroupExample example);

    int updateByExample(@Param("record") BoardTemplateGroup record, @Param("example") BoardTemplateGroupExample example);

    int updateByPrimaryKeySelective(BoardTemplateGroup record);

    int updateByPrimaryKey(BoardTemplateGroup record);
}
