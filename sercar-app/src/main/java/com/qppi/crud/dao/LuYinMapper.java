package com.qppi.crud.dao;

import com.qppi.crud.bean.LuYin;
import com.qppi.crud.bean.LuYinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LuYinMapper {
    long countByExample(LuYinExample example);

    int deleteByExample(LuYinExample example);

    int deleteByPrimaryKey(String luyinId);

    int insert(LuYin record);

    int insertSelective(LuYin record);

    List<LuYin> selectByExample(LuYinExample example);

    LuYin selectByPrimaryKey(String luyinId);

    int updateByExampleSelective(@Param("record") LuYin record, @Param("example") LuYinExample example);

    int updateByExample(@Param("record") LuYin record, @Param("example") LuYinExample example);

    int updateByPrimaryKeySelective(LuYin record);

    int updateByPrimaryKey(LuYin record);
}