package com.qppi.crud.dao;

import com.qppi.crud.bean.Messlist;
import com.qppi.crud.bean.MesslistExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MesslistMapper {
    long countByExample(MesslistExample example);

    int deleteByExample(MesslistExample example);

    int deleteByPrimaryKey(String mlistId);

    int insert(Messlist record);

    int insertSelective(Messlist record);

    List<Messlist> selectByExample(MesslistExample example);

    Messlist selectByPrimaryKey(String mlistId);

    int updateByExampleSelective(@Param("record") Messlist record, @Param("example") MesslistExample example);

    int updateByExample(@Param("record") Messlist record, @Param("example") MesslistExample example);

    int updateByPrimaryKeySelective(Messlist record);

    int updateByPrimaryKey(Messlist record);
}