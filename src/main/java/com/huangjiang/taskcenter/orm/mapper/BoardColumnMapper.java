package com.huangjiang.taskcenter.orm.mapper;

import com.huangjiang.taskcenter.orm.entity.BoardColumn;
import com.huangjiang.taskcenter.orm.entity.BoardColumnExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardColumnMapper {
    int countByExample(BoardColumnExample example);

    int deleteByExample(BoardColumnExample example);

    int deleteByPrimaryKey(String id);

    int insert(BoardColumn record);

    int insertSelective(BoardColumn record);

    List<BoardColumn> selectByExample(BoardColumnExample example);

    BoardColumn selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BoardColumn record, @Param("example") BoardColumnExample example);

    int updateByExample(@Param("record") BoardColumn record, @Param("example") BoardColumnExample example);

    int updateByPrimaryKeySelective(BoardColumn record);

    int updateByPrimaryKey(BoardColumn record);
}
