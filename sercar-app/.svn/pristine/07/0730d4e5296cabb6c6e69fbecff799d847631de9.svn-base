package com.qppi.crud.dao;

import com.qppi.crud.bean.Applyjb;
import com.qppi.crud.bean.ApplyjbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplyjbMapper {
    long countByExample(ApplyjbExample example);

    int deleteByExample(ApplyjbExample example);

    int deleteByPrimaryKey(String applyjbId);

    int insert(Applyjb record);

    int insertSelective(Applyjb record);

    List<Applyjb> selectByExample(ApplyjbExample example);

    Applyjb selectByPrimaryKey(String applyjbId);

    int updateByExampleSelective(@Param("record") Applyjb record, @Param("example") ApplyjbExample example);

    int updateByExample(@Param("record") Applyjb record, @Param("example") ApplyjbExample example);

    int updateByPrimaryKeySelective(Applyjb record);

    int updateByPrimaryKey(Applyjb record);
}