package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardTemplatePulse;
import com.huangjiang.taskcenter.orm.entity.BoardTemplatePulseExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardTemplatePulseMapper {
    int countByExample(BoardTemplatePulseExample example);

    int deleteByExample(BoardTemplatePulseExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardTemplatePulse record);

    int insertSelective(BoardTemplatePulse record);

    List<BoardTemplatePulse> selectByExample(BoardTemplatePulseExample example);

    BoardTemplatePulse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardTemplatePulse record, @Param("example") BoardTemplatePulseExample example);

    int updateByExample(@Param("record") BoardTemplatePulse record, @Param("example") BoardTemplatePulseExample example);

    int updateByPrimaryKeySelective(BoardTemplatePulse record);

    int updateByPrimaryKey(BoardTemplatePulse record);
}
