package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardTemplate;
import com.huangjiang.taskcenter.orm.entity.BoardTemplateExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardTemplateMapper {
    int countByExample(BoardTemplateExample example);

    int deleteByExample(BoardTemplateExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardTemplate record);

    int insertSelective(BoardTemplate record);

    List<BoardTemplate> selectByExample(BoardTemplateExample example);

    BoardTemplate selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardTemplate record, @Param("example") BoardTemplateExample example);

    int updateByExample(@Param("record") BoardTemplate record, @Param("example") BoardTemplateExample example);

    int updateByPrimaryKeySelective(BoardTemplate record);

    int updateByPrimaryKey(BoardTemplate record);
}
