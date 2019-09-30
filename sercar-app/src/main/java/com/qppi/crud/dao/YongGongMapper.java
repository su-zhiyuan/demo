package com.qppi.crud.dao;

import com.qppi.crud.bean.YongGong;
import com.qppi.crud.bean.YongGongExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YongGongMapper {
    long countByExample(YongGongExample example);

    int deleteByExample(YongGongExample example);

    int deleteByPrimaryKey(String yonggongId);

    int insert(YongGong record);

    int insertSelective(YongGong record);

    List<YongGong> selectByExample(YongGongExample example);

    YongGong selectByPrimaryKey(String yonggongId);

    int updateByExampleSelective(@Param("record") YongGong record, @Param("example") YongGongExample example);

    int updateByExample(@Param("record") YongGong record, @Param("example") YongGongExample example);

    int updateByPrimaryKeySelective(YongGong record);

    int updateByPrimaryKey(YongGong record);
}