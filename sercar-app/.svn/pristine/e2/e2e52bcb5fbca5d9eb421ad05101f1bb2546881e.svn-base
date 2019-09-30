package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Dispatching;
import com.qppi.crud.bean.DispatchingExample;
import com.qppi.crud.dao.DispatchingMapper;

@Service
public class DispatchingService {
	
	@Autowired
	private DispatchingMapper dispatchingMapper;

	public boolean add(Dispatching dispatching) {
		try{
			dispatchingMapper.insertSelective(dispatching);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Dispatching dispatching) {
		try{
			dispatchingMapper.updateByPrimaryKeySelective(dispatching);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(String dispatchingId) {
		try{
			dispatchingMapper.deleteByPrimaryKey(dispatchingId);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Dispatching> selectByOrderId(String orderId) {
		DispatchingExample example = new DispatchingExample();
		DispatchingExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<Dispatching> list = dispatchingMapper.selectByExample(example);
		return list;
	}

	public Dispatching getDispatching(String dispatchingId) {
		Dispatching dispatching = dispatchingMapper.selectByPrimaryKey(dispatchingId);
		return dispatching;
	}

	public List<Dispatching> selectByStatus(String status1, String status2) {
		DispatchingExample example = new DispatchingExample();
		DispatchingExample.Criteria criteria1 = example.createCriteria();
		criteria1.andStatusEqualTo(status1);
		
		DispatchingExample.Criteria criteria2 = example.createCriteria();
		criteria2.andStatusEqualTo(status2);
		
		example.or(criteria2);
		List<Dispatching> list = dispatchingMapper.selectByExample(example);
		return list;
	}

}
