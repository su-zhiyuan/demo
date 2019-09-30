package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Cardclock;
import com.qppi.crud.bean.Contacts;
import com.qppi.crud.bean.ContactsExample;
import com.qppi.crud.bean.Order;
import com.qppi.crud.bean.OrderExample;
import com.qppi.crud.dao.ContactsMapper;
import com.qppi.crud.dao.OrderMapper;

@Service
public class ContactsService {

	@Autowired
	private ContactsMapper contactsMapper;
	@Autowired
	private OrderMapper orderMapper;
	
	
	public boolean updateContact(Contacts contacts) {
		try {
			contactsMapper.updateByPrimaryKeySelective(contacts);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delContact(Contacts contacts) {
		try {
			contactsMapper.deleteByPrimaryKey(contacts.getContactsId());
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addContact(Contacts contacts) {
		try {
			contactsMapper.insertSelective(contacts);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Contacts listContact(Contacts contacts) {
		OrderExample example = new OrderExample();
		OrderExample.Criteria criteria = example.createCriteria();
		String orderid = contacts.getYl3();
		if(orderid != null && !"".equals(orderid)){
			criteria.andOrderIdEqualTo(orderid);
			Order order = orderMapper.selectByPrimaryKey(orderid);
			if(order == null){
				return null;
			}else{
				String contactId = order.getContactId();
				Contacts con = contactsMapper.selectByPrimaryKey(contactId);
				return con;
			}
		}
		return null;
		
	}
	
	
	//打卡统计
	public List<Cardclock> statistics(Cardclock cardclock) {
		List<Cardclock> list = contactsMapper.statistics(cardclock);
		if(list.size()>0) {
			return list;
		}
		return null;
	}

	public Contacts selectByWX(String wxid) {
		ContactsExample example = new ContactsExample();
		ContactsExample.Criteria criteria = example.createCriteria();
		criteria.andYl1EqualTo(wxid);
		List<Contacts> list = contactsMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public Contacts selectById(String contactId) {
		return contactsMapper.selectByPrimaryKey(contactId);
	}

	public Contacts selectByTel(String contactTel) {
		ContactsExample example = new ContactsExample();
		ContactsExample.Criteria criteria = example.createCriteria();
		criteria.andContactTelEqualTo(contactTel);
		List<Contacts> list = contactsMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public List<Contacts> selectByOrder(String orderId) {
		ContactsExample example = new ContactsExample();
		ContactsExample.Criteria criteria = example.createCriteria();
		criteria.andYl3EqualTo(orderId);
		List<Contacts> list = contactsMapper.selectByExample(example);
		return list;
	}
	
}
