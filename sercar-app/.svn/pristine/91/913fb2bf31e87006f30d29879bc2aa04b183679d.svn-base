package com.qppi.crud.dao;

import com.qppi.crud.bean.Dictionaries;
import com.qppi.crud.bean.DictionariesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DictionariesMapper {
    long countByExample(DictionariesExample example);

    int deleteByExample(DictionariesExample example);

    int deleteByPrimaryKey(String dictionariesId);

    int insert(Dictionaries record);

    int insertSelective(Dictionaries record);

    List<Dictionaries> selectByExample(DictionariesExample example);

    Dictionaries selectByPrimaryKey(String dictionariesId);

    int updateByExampleSelective(@Param("record") Dictionaries record, @Param("example") DictionariesExample example);

    int updateByExample(@Param("record") Dictionaries record, @Param("example") DictionariesExample example);

    int updateByPrimaryKeySelective(Dictionaries record);

    int updateByPrimaryKey(Dictionaries record);
}