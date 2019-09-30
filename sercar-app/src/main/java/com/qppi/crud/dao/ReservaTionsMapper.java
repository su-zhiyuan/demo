package com.qppi.crud.dao;

import java.util.List;
import java.util.Map;

import com.qppi.crud.bean.ReservaTions;

public interface ReservaTionsMapper {
	//添加订金
	int addYY(ReservaTions reservaTions);
	//查询订金
	List<ReservaTions> selectYY(Map<String, Object> map);
	//根据ID查询订金
	ReservaTions selectId(ReservaTions reservaTions);
	//根据ID修改订金
	int updateID(ReservaTions reservaTions);
	
}
