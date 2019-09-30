package com.qppi.crud.dao;

import com.qppi.crud.bean.Dispatching;
import com.qppi.crud.bean.DispatchingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DispatchingMapper {
    long countByExample(DispatchingExample example);

    int deleteByExample(DispatchingExample example);

    int deleteByPrimaryKey(String dispatchingId);

    int insert(Dispatching record);

    int insertSelective(Dispatching record);

    List<Dispatching> selectByExample(DispatchingExample example);

    Dispatching selectByPrimaryKey(String dispatchingId);

    int updateByExampleSelective(@Param("record") Dispatching record, @Param("example") DispatchingExample example);

    int updateByExample(@Param("record") Dispatching record, @Param("example") DispatchingExample example);

    int updateByPrimaryKeySelective(Dispatching record);

    int updateByPrimaryKey(Dispatching record);
}