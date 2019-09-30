package com.qppi.crud.dao;

import com.qppi.crud.bean.Reception;
import com.qppi.crud.bean.ReceptionExample;
import com.qppi.crud.bean.ReceptionWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReceptionMapper {
    long countByExample(ReceptionExample example);

    int deleteByExample(ReceptionExample example);

    int deleteByPrimaryKey(String receptionId);

    int insert(ReceptionWithBLOBs record);

    int insertSelective(ReceptionWithBLOBs record);

    List<ReceptionWithBLOBs> selectByExampleWithBLOBs(ReceptionExample example);

    List<Reception> selectByExample(ReceptionExample example);

    ReceptionWithBLOBs selectByPrimaryKey(String receptionId);

    int updateByExampleSelective(@Param("record") ReceptionWithBLOBs record, @Param("example") ReceptionExample example);

    int updateByExampleWithBLOBs(@Param("record") ReceptionWithBLOBs record, @Param("example") ReceptionExample example);

    int updateByExample(@Param("record") Reception record, @Param("example") ReceptionExample example);

    int updateByPrimaryKeySelective(ReceptionWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ReceptionWithBLOBs record);

    int updateByPrimaryKey(Reception record);
}