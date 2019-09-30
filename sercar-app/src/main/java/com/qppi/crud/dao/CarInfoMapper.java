package com.qppi.crud.dao;

import com.qppi.crud.bean.CarInfo;
import com.qppi.crud.bean.CarInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarInfoMapper {
    long countByExample(CarInfoExample example);

    int deleteByExample(CarInfoExample example);

    int deleteByPrimaryKey(String carinfoId);

    int insert(CarInfo record);

    int insertSelective(CarInfo record);

    List<CarInfo> selectByExample(CarInfoExample example);

    CarInfo selectByPrimaryKey(String carinfoId);

    int updateByExampleSelective(@Param("record") CarInfo record, @Param("example") CarInfoExample example);

    int updateByExample(@Param("record") CarInfo record, @Param("example") CarInfoExample example);

    int updateByPrimaryKeySelective(CarInfo record);

    int updateByPrimaryKey(CarInfo record);
}