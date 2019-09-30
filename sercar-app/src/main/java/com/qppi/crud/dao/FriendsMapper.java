package com.qppi.crud.dao;

import com.qppi.crud.bean.Friends;
import com.qppi.crud.bean.FriendsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendsMapper {
    long countByExample(FriendsExample example);

    int deleteByExample(FriendsExample example);

    int deleteByPrimaryKey(String friendsId);

    int insert(Friends record);

    int insertSelective(Friends record);

    List<Friends> selectByExample(FriendsExample example);

    Friends selectByPrimaryKey(String friendsId);

    int updateByExampleSelective(@Param("record") Friends record, @Param("example") FriendsExample example);

    int updateByExample(@Param("record") Friends record, @Param("example") FriendsExample example);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);
}