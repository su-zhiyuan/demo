package com.qppi.crud.dao;

import com.qppi.crud.bean.Settlement;
import com.qppi.crud.bean.SettlementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SettlementMapper {
    long countByExample(SettlementExample example);

    int deleteByExample(SettlementExample example);

    int deleteByPrimaryKey(String settlementId);

    int insert(Settlement record);

    int insertSelective(Settlement record);

    List<Settlement> selectByExample(SettlementExample example);

    Settlement selectByPrimaryKey(String settlementId);

    int updateByExampleSelective(@Param("record") Settlement record, @Param("example") SettlementExample example);

    int updateByExample(@Param("record") Settlement record, @Param("example") SettlementExample example);

    int updateByPrimaryKeySelective(Settlement record);

    int updateByPrimaryKey(Settlement record);
}