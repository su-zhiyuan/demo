package com.qppi.crud.dao;

import com.qppi.crud.bean.Proclamation;
import com.qppi.crud.bean.ProclamationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProclamationMapper {
    long countByExample(ProclamationExample example);

    int deleteByExample(ProclamationExample example);

    int deleteByPrimaryKey(String proclamationId);

    int insert(Proclamation record);

    int insertSelective(Proclamation record);

    List<Proclamation> selectByExample(ProclamationExample example);

    Proclamation selectByPrimaryKey(String proclamationId);

    int updateByExampleSelective(@Param("record") Proclamation record, @Param("example") ProclamationExample example);

    int updateByExample(@Param("record") Proclamation record, @Param("example") ProclamationExample example);

    int updateByPrimaryKeySelective(Proclamation record);

    int updateByPrimaryKey(Proclamation record);
}