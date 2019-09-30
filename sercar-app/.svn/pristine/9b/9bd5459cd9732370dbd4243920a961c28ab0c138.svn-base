package com.qppi.crud.dao;

import com.qppi.crud.bean.Repoinfo;
import com.qppi.crud.bean.RepoinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepoinfoMapper {
    long countByExample(RepoinfoExample example);

    int deleteByExample(RepoinfoExample example);

    int deleteByPrimaryKey(String repoinfoId);

    int insert(Repoinfo record);

    int insertSelective(Repoinfo record);

    List<Repoinfo> selectByExample(RepoinfoExample example);

    Repoinfo selectByPrimaryKey(String repoinfoId);

    int updateByExampleSelective(@Param("record") Repoinfo record, @Param("example") RepoinfoExample example);

    int updateByExample(@Param("record") Repoinfo record, @Param("example") RepoinfoExample example);

    int updateByPrimaryKeySelective(Repoinfo record);

    int updateByPrimaryKey(Repoinfo record);
}