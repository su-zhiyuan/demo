package com.qppi.crud.dao;

import com.qppi.crud.bean.SName;
import com.qppi.crud.bean.SNameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SNameMapper {
    long countByExample(SNameExample example);

    int deleteByExample(SNameExample example);

    int deleteByPrimaryKey(String snameId);

    int insert(SName record);

    int insertSelective(SName record);

    List<SName> selectByExample(SNameExample example);

    SName selectByPrimaryKey(String snameId);

    int updateByExampleSelective(@Param("record") SName record, @Param("example") SNameExample example);

    int updateByExample(@Param("record") SName record, @Param("example") SNameExample example);

    int updateByPrimaryKeySelective(SName record);

    int updateByPrimaryKey(SName record);
}