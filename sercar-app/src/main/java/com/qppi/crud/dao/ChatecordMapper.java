package com.qppi.crud.dao;

import com.qppi.crud.bean.Chatecord;
import com.qppi.crud.bean.ChatecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatecordMapper {
    long countByExample(ChatecordExample example);

    int deleteByExample(ChatecordExample example);

    int deleteByPrimaryKey(String chatrecordId);

    int insert(Chatecord record);

    int insertSelective(Chatecord record);

    List<Chatecord> selectByExample(ChatecordExample example);

    Chatecord selectByPrimaryKey(String chatrecordId);

    int updateByExampleSelective(@Param("record") Chatecord record, @Param("example") ChatecordExample example);

    int updateByExample(@Param("record") Chatecord record, @Param("example") ChatecordExample example);

    int updateByPrimaryKeySelective(Chatecord record);

    int updateByPrimaryKey(Chatecord record);
}