package com.qppi.crud.dao;

import com.qppi.crud.bean.MyCollection;
import com.qppi.crud.bean.MyCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyCollectionMapper {
    long countByExample(MyCollectionExample example);

    int deleteByExample(MyCollectionExample example);

    int deleteByPrimaryKey(String mycollectionId);

    //点击收藏
    int insert(MyCollection record);
    
    //取消收藏
    int delMC(MyCollection mc);
    
    int insertSelective(MyCollection record);

    List<MyCollection> selectByExample(MyCollection mc);
    //我的收藏
    List<MyCollection> selectMC(MyCollection mc);
    
    MyCollection selectByPrimaryKey(String mycollectionId);

    int updateByExampleSelective(@Param("record") MyCollection record, @Param("example") MyCollectionExample example);

    int updateByExample(@Param("record") MyCollection record, @Param("example") MyCollectionExample example);

    int updateByPrimaryKeySelective(MyCollection record);

    int updateByPrimaryKey(MyCollection record);
}