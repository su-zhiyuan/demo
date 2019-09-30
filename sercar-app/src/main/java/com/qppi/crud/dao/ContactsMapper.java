package com.qppi.crud.dao;

import com.qppi.crud.bean.Cardclock;
import com.qppi.crud.bean.Contacts;
import com.qppi.crud.bean.ContactsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContactsMapper {
    long countByExample(ContactsExample example);

    int deleteByExample(ContactsExample example);

    int deleteByPrimaryKey(String contactsId);

    int insert(Contacts record);

    int insertSelective(Contacts record);

    List<Contacts> selectByExampleWithBLOBs(ContactsExample example);
    
    //打卡统计
    List<Cardclock> statistics(Cardclock cardclock);

    List<Contacts> selectByExample(ContactsExample example);

    Contacts selectByPrimaryKey(String contactsId);

    int updateByExampleSelective(@Param("record") Contacts record, @Param("example") ContactsExample example);

    int updateByExampleWithBLOBs(@Param("record") Contacts record, @Param("example") ContactsExample example);

    int updateByExample(@Param("record") Contacts record, @Param("example") ContactsExample example);

    int updateByPrimaryKeySelective(Contacts record);

    int updateByPrimaryKeyWithBLOBs(Contacts record);

    int updateByPrimaryKey(Contacts record);
}