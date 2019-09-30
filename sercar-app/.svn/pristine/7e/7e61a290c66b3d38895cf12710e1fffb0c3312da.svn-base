package com.qppi.crud.dao;

import com.qppi.crud.bean.Revisit;
import com.qppi.crud.bean.RevisitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RevisitMapper {
    long countByExample(RevisitExample example);

    int deleteByExample(RevisitExample example);

    int deleteByPrimaryKey(String revisitId);

    int insert(Revisit record);

    int insertSelective(Revisit record);

    List<Revisit> selectByExample(RevisitExample example);

    Revisit selectByPrimaryKey(String revisitId);

    int updateByExampleSelective(@Param("record") Revisit record, @Param("example") RevisitExample example);

    int updateByExample(@Param("record") Revisit record, @Param("example") RevisitExample example);

    int updateByPrimaryKeySelective(Revisit record);

    int updateByPrimaryKey(Revisit record);
}