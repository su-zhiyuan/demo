package com.qppi.crud.dao;

import com.qppi.crud.bean.Evection;
import com.qppi.crud.bean.EvectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EvectionMapper {
    long countByExample(EvectionExample example);

    int deleteByExample(EvectionExample example);

    int deleteByPrimaryKey(String evectionId);

    int insert(Evection record);

    int insertSelective(Evection record);

    List<Evection> selectByExample(EvectionExample example);

    Evection selectByPrimaryKey(String evectionId);

    int updateByExampleSelective(@Param("record") Evection record, @Param("example") EvectionExample example);

    int updateByExample(@Param("record") Evection record, @Param("example") EvectionExample example);

    int updateByPrimaryKeySelective(Evection record);

    int updateByPrimaryKey(Evection record);
}