package com.qppi.crud.dao;

import com.qppi.crud.bean.Pomx;
import com.qppi.crud.bean.PomxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PomxMapper {
    long countByExample(PomxExample example);

    int deleteByExample(PomxExample example);

    int deleteByPrimaryKey(String pomxId);

    int insert(Pomx record);

    int insertSelective(Pomx record);

    List<Pomx> selectByExample(PomxExample example);

    Pomx selectByPrimaryKey(String pomxId);

    int updateByExampleSelective(@Param("record") Pomx record, @Param("example") PomxExample example);

    int updateByExample(@Param("record") Pomx record, @Param("example") PomxExample example);

    int updateByPrimaryKeySelective(Pomx record);

    int updateByPrimaryKey(Pomx record);
    
    List<Pomx> selectPomxId(Pomx pomx);
       
    //配件列表
    List<Pomx> selectPomx(Pomx pomx);
    //查询配件详情
    List<Pomx> pomxDetails(Pomx pomx);
}