package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.ReceptionExample;
import com.qppi.crud.bean.ReceptionWithBLOBs;
import com.qppi.crud.dao.ReceptionMapper;

@Service
public class ReceptionService {
	
	@Autowired
	private ReceptionMapper receptionMapper;

	public boolean add(ReceptionWithBLOBs reception) {
		try{
			receptionMapper.insertSelective(reception);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateR(ReceptionWithBLOBs reception) {
		try{
			receptionMapper.updateByPrimaryKeySelective(reception);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public ReceptionWithBLOBs selectByOrderId(String orderId) {
		ReceptionExample example = new ReceptionExample();
		ReceptionExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<ReceptionWithBLOBs> list = receptionMapper.selectByExampleWithBLOBs(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public ReceptionWithBLOBs getReception(String id) {
		ReceptionWithBLOBs reception = receptionMapper.selectByPrimaryKey(id);
		return reception;
	}

	public ReceptionWithBLOBs selectByReceptionId(String receptionId) {
		ReceptionWithBLOBs selectByPrimaryKey = receptionMapper.selectByPrimaryKey(receptionId);
		return selectByPrimaryKey;
	}

}
