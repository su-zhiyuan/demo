package com.qppi.crud.dao;

import com.qppi.crud.bean.SType;
import com.qppi.crud.bean.STypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface STypeMapper {
    long countByExample(STypeExample example);

    int deleteByExample(STypeExample example);

    int deleteByPrimaryKey(String stypeId);

    int insert(SType record);

    int insertSelective(SType record);

    List<SType> selectByExample(STypeExample example);

    SType selectByPrimaryKey(String stypeId);

    int updateByExampleSelective(@Param("record") SType record, @Param("example") STypeExample example);

    int updateByExample(@Param("record") SType record, @Param("example") STypeExample example);

    int updateByPrimaryKeySelective(SType record);

    int updateByPrimaryKey(SType record);
}