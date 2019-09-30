package com.qppi.crud.dao;

import com.qppi.crud.bean.Dsmx;
import com.qppi.crud.bean.DsmxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DsmxMapper {
    long countByExample(DsmxExample example);

    int deleteByExample(DsmxExample example);

    int deleteByPrimaryKey(String dsmxId);

    int insert(Dsmx record);

    int insertSelective(Dsmx record);

    List<Dsmx> selectByExample(DsmxExample example);

    Dsmx selectByPrimaryKey(String dsmxId);

    int updateByExampleSelective(@Param("record") Dsmx record, @Param("example") DsmxExample example);

    int updateByExample(@Param("record") Dsmx record, @Param("example") DsmxExample example);

    int updateByPrimaryKeySelective(Dsmx record);

    int updateByPrimaryKey(Dsmx record);
    
    List<Dsmx> selectDsmxId(Dsmx dsmx);
    
    
    //工时列表
    List<Dsmx> selectDsmx(Dsmx dsmx);
    //查询工时详情
    List<Dsmx> dsmxDetails(Dsmx dsmx);
}