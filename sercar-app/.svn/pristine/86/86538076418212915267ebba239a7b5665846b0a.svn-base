package com.qppi.crud.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.ReservaTions;
import com.qppi.crud.dao.ReservaTionsMapper;

@Service
public class ReservaTionsService {
	@Autowired
	private ReservaTionsMapper reservaTionsMapper;
	
	//创建预约
	public boolean addYY(ReservaTions reservaTions) {
		try{
			reservaTionsMapper.addYY(reservaTions);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	//查询预约
	public List<ReservaTions> selectYY(int page,String companyId){
		int pagesize = 5;
		int startRow = (page-1)*pagesize;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pagesize);
		map.put("startRow", startRow);
		map.put("yl10", companyId);
		List<ReservaTions> list = reservaTionsMapper.selectYY(map);
		if(list.size()>0) {
			return list;
		}
		return null;
	}
	
	//根据ID查询预约
	public ReservaTions selectId(ReservaTions reservaTions){
		return reservaTionsMapper.selectId(reservaTions);	
	}
	//根据ID查询预约
	public boolean updateID(ReservaTions reservaTions){
		try {
			reservaTionsMapper.updateID(reservaTions);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
