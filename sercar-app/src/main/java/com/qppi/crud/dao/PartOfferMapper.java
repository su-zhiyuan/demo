package com.qppi.crud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qppi.crud.bean.PartOffer;
import com.qppi.crud.bean.PartOfferExample;

public interface PartOfferMapper {
    long countByExample(PartOfferExample example);

    int deleteByExample(PartOfferExample example);

    int deleteByPrimaryKey(String partofferId);

    int insert(PartOffer record);

    int insertSelective(PartOffer record);

    List<PartOffer> selectByExample(PartOfferExample example);

    PartOffer selectByPrimaryKey(String partofferId);

    int updateByExampleSelective(@Param("record") PartOffer record, @Param("example") PartOfferExample example);

    int updateByExample(@Param("record") PartOffer record, @Param("example") PartOfferExample example);

    int updateByPrimaryKeySelective(PartOffer record);

    int updateByPrimaryKey(PartOffer record);
    //救援查询工时
    List<PartOffer> selectOrderId(PartOffer partOffer);
    
    //工时单独查询列表
    PartOffer selectDsmx(PartOffer partOffer);
    
    //提交复合
    int updatePartY(PartOffer partOffer);
    //确认复合
    int updatePartN(PartOffer partOffer);
}