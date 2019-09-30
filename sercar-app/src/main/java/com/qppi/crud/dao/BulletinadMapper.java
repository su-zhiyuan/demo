package com.qppi.crud.dao;

import com.qppi.crud.bean.Bulletinad;
import com.qppi.crud.bean.BulletinadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BulletinadMapper {
    long countByExample(BulletinadExample example);

    int deleteByExample(BulletinadExample example);

    int deleteByPrimaryKey(String bulletinadId);

    int insert(Bulletinad record);

    int insertSelective(Bulletinad record);

    List<Bulletinad> selectByExample(BulletinadExample example);

    Bulletinad selectByPrimaryKey(String bulletinadId);

    int updateByExampleSelective(@Param("record") Bulletinad record, @Param("example") BulletinadExample example);

    int updateByExample(@Param("record") Bulletinad record, @Param("example") BulletinadExample example);

    int updateByPrimaryKeySelective(Bulletinad record);

    int updateByPrimaryKey(Bulletinad record);
}