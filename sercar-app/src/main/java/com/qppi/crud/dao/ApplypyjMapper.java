package com.qppi.crud.dao;

import com.qppi.crud.bean.Applypyj;
import com.qppi.crud.bean.ApplypyjExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplypyjMapper {
    long countByExample(ApplypyjExample example);

    int deleteByExample(ApplypyjExample example);

    int deleteByPrimaryKey(String applypyjId);

    int insert(Applypyj record);

    int insertSelective(Applypyj record);

    List<Applypyj> selectByExample(ApplypyjExample example);

    Applypyj selectByPrimaryKey(String applypyjId);

    int updateByExampleSelective(@Param("record") Applypyj record, @Param("example") ApplypyjExample example);

    int updateByExample(@Param("record") Applypyj record, @Param("example") ApplypyjExample example);

    int updateByPrimaryKeySelective(Applypyj record);

    int updateByPrimaryKey(Applypyj record);
}