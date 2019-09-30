package com.qppi.crud.dao;

import com.qppi.crud.bean.GoodsRC;
import com.qppi.crud.bean.GoodsRCExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsRCMapper {
    long countByExample(GoodsRCExample example);

    int deleteByExample(GoodsRCExample example);

    int deleteByPrimaryKey(String goodsrcId);

    int insert(GoodsRC record);

    int insertSelective(GoodsRC record);

    List<GoodsRC> selectByExample(GoodsRCExample example);

    GoodsRC selectByPrimaryKey(String goodsrcId);

    int updateByExampleSelective(@Param("record") GoodsRC record, @Param("example") GoodsRCExample example);

    int updateByExample(@Param("record") GoodsRC record, @Param("example") GoodsRCExample example);

    int updateByPrimaryKeySelective(GoodsRC record);

    int updateByPrimaryKey(GoodsRC record);
}