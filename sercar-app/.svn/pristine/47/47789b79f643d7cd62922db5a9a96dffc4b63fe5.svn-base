package com.qppi.crud.dao;

import com.qppi.crud.bean.ExpenditureStatement;
import com.qppi.crud.bean.ExpenditureStatementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpenditureStatementMapper {
    long countByExample(ExpenditureStatementExample example);

    int deleteByExample(ExpenditureStatementExample example);

    int deleteByPrimaryKey(String expenditurestatementId);

    int insert(ExpenditureStatement record);

    int insertSelective(ExpenditureStatement record);

    List<ExpenditureStatement> selectByExample(ExpenditureStatementExample example);

    ExpenditureStatement selectByPrimaryKey(String expenditurestatementId);

    int updateByExampleSelective(@Param("record") ExpenditureStatement record, @Param("example") ExpenditureStatementExample example);

    int updateByExample(@Param("record") ExpenditureStatement record, @Param("example") ExpenditureStatementExample example);

    int updateByPrimaryKeySelective(ExpenditureStatement record);

    int updateByPrimaryKey(ExpenditureStatement record);
}