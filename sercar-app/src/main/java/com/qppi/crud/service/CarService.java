package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Car;
import com.qppi.crud.bean.CarExample;
import com.qppi.crud.dao.CarMapper;

@Service
public class CarService {

	@Autowired
	private CarMapper carMapper;

	public List<Car> select(String carchexi) {
		CarExample example = new CarExample();
		CarExample.Criteria criteria = example.createCriteria();
		if(carchexi != null && !"".equals(carchexi)){
			criteria.andPingpaiEqualTo(carchexi);
		}
		List<Car> list = carMapper.selectByExample(example);
		return list;
	}

	public boolean update(Car car) {
		try {
			carMapper.updateByPrimaryKeySelective(car);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Car> selectAll() {
		CarExample example = new CarExample();
		List<Car> list = carMapper.selectByExample(example);
		return list;
	}

}
