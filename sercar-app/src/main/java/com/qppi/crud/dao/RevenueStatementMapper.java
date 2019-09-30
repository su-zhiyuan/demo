package com.qppi.crud.dao;

import com.qppi.crud.bean.RevenueStatement;
import com.qppi.crud.bean.RevenueStatementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RevenueStatementMapper {
    long countByExample(RevenueStatementExample example);

    int deleteByExample(RevenueStatementExample example);

    int deleteByPrimaryKey(String revenuestatementId);

    int insert(RevenueStatement record);

    int insertSelective(RevenueStatement record);

    List<RevenueStatement> selectByExample(RevenueStatementExample example);

    RevenueStatement selectByPrimaryKey(String revenuestatementId);

    int updateByExampleSelective(@Param("record") RevenueStatement record, @Param("example") RevenueStatementExample example);

    int updateByExample(@Param("record") RevenueStatement record, @Param("example") RevenueStatementExample example);

    int updateByPrimaryKeySelective(RevenueStatement record);

    int updateByPrimaryKey(RevenueStatement record);
}