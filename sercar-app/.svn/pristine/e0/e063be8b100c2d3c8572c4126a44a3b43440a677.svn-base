package com.qppi.crud.dao;

import com.qppi.crud.bean.Cardclock;
import com.qppi.crud.bean.CardclockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardclockMapper {
    long countByExample(CardclockExample example);

    int deleteByExample(CardclockExample example);

    int deleteByPrimaryKey(String cardclockId);

    int insert(Cardclock record);

    int insertSelective(Cardclock record);

    List<Cardclock> selectByExample(CardclockExample example);

    Cardclock selectByPrimaryKey(String cardclockId);

    int updateByExampleSelective(@Param("record") Cardclock record, @Param("example") CardclockExample example);

    int updateByExample(@Param("record") Cardclock record, @Param("example") CardclockExample example);

    int updateByPrimaryKeySelective(Cardclock record);

    int updateByPrimaryKey(Cardclock record);
}