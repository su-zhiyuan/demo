package com.qppi.crud.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Deposit;
import com.qppi.crud.dao.DepositMapper;

@Service
public class DepositService {
	@Autowired
	private DepositMapper depositMapper;
	
	//创建订金
	public boolean addDJ(Deposit deposit) {
		try{
			depositMapper.addDJ(deposit);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//查询订金
	public List<Deposit> selectDJ(int page){
		Map<String, Object> map = new HashMap<String, Object>();
		int pageSize = 10;
		int startRow = (page-1)*10;
		map.put("pageSize", pageSize);
		map.put("startRow", startRow);
		List<Deposit> list = depositMapper.selectDJ(map);
		if(list.size()>0) {
			return list;
		}
		return null;
	}
	
	//根据ID查询订金
	public Deposit selectId(Deposit deposit){
		return depositMapper.selectId(deposit);	
	}
	//根据ID查询订金
	public boolean updateID(Deposit deposit){
		try {
			depositMapper.updateID(deposit);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
