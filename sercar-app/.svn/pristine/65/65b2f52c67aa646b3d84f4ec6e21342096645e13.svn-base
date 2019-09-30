package com.qppi.crud.dao;

import com.qppi.crud.bean.Paymentrequest;
import com.qppi.crud.bean.PaymentrequestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaymentrequestMapper {
    long countByExample(PaymentrequestExample example);

    int deleteByExample(PaymentrequestExample example);

    int deleteByPrimaryKey(String paymentrequestId);

    int insert(Paymentrequest record);

    int insertSelective(Paymentrequest record);

    List<Paymentrequest> selectByExample(PaymentrequestExample example);

    Paymentrequest selectByPrimaryKey(String paymentrequestId);

    int updateByExampleSelective(@Param("record") Paymentrequest record, @Param("example") PaymentrequestExample example);

    int updateByExample(@Param("record") Paymentrequest record, @Param("example") PaymentrequestExample example);

    int updateByPrimaryKeySelective(Paymentrequest record);

    int updateByPrimaryKey(Paymentrequest record);
}