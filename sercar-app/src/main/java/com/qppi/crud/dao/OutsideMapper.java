package com.qppi.crud.dao;

import com.qppi.crud.bean.Outside;
import com.qppi.crud.bean.OutsideExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OutsideMapper {
    long countByExample(OutsideExample example);

    int deleteByExample(OutsideExample example);

    int deleteByPrimaryKey(String outsideId);

    int insert(Outside record);

    int insertSelective(Outside record);

    List<Outside> selectByExample(OutsideExample example);

    Outside selectByPrimaryKey(String outsideId);

    int updateByExampleSelective(@Param("record") Outside record, @Param("example") OutsideExample example);

    int updateByExample(@Param("record") Outside record, @Param("example") OutsideExample example);

    int updateByPrimaryKeySelective(Outside record);

    int updateByPrimaryKey(Outside record);
}