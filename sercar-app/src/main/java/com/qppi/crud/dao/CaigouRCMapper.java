package com.qppi.crud.dao;

import com.qppi.crud.bean.CaigouRC;
import com.qppi.crud.bean.CaigouRCExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaigouRCMapper {
    long countByExample(CaigouRCExample example);

    int deleteByExample(CaigouRCExample example);

    int deleteByPrimaryKey(String caigourcId);

    int insert(CaigouRC record);

    int insertSelective(CaigouRC record);

    List<CaigouRC> selectByExample(CaigouRCExample example);

    CaigouRC selectByPrimaryKey(String caigourcId);

    int updateByExampleSelective(@Param("record") CaigouRC record, @Param("example") CaigouRCExample example);

    int updateByExample(@Param("record") CaigouRC record, @Param("example") CaigouRCExample example);

    int updateByPrimaryKeySelective(CaigouRC record);

    int updateByPrimaryKey(CaigouRC record);
}