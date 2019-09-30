package com.qppi.crud.dao;

import com.qppi.crud.bean.InfoRelay;
import com.qppi.crud.bean.InfoRelayExample;
import com.qppi.crud.bean.InfoRelayWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InfoRelayMapper {
    long countByExample(InfoRelayExample example);

    int deleteByExample(InfoRelayExample example);

    int deleteByPrimaryKey(String inforelayId);

    int insert(InfoRelayWithBLOBs record);

    int insertSelective(InfoRelayWithBLOBs record);

    List<InfoRelayWithBLOBs> selectByExampleWithBLOBs(InfoRelayExample example);

    List<InfoRelay> selectByExample(InfoRelayExample example);

    InfoRelayWithBLOBs selectByPrimaryKey(String inforelayId);

    int updateByExampleSelective(@Param("record") InfoRelayWithBLOBs record, @Param("example") InfoRelayExample example);

    int updateByExampleWithBLOBs(@Param("record") InfoRelayWithBLOBs record, @Param("example") InfoRelayExample example);

    int updateByExample(@Param("record") InfoRelay record, @Param("example") InfoRelayExample example);

    int updateByPrimaryKeySelective(InfoRelayWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InfoRelayWithBLOBs record);

    int updateByPrimaryKey(InfoRelay record);
}