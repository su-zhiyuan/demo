package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.CarInfo;
import com.qppi.crud.bean.CarInfoExample;
import com.qppi.crud.dao.CarInfoMapper;
import com.qppi.crud.utils.MyTools;

@Service
public class CarInfoService {
	
	@Autowired
	private CarInfoMapper carInfoMapper;

	public CarInfo getCarInfo(CarInfo carInfo) {
		String num1 = carInfo.getCarNum1();
		String num2 = carInfo.getCarNum2();
		String num3 = carInfo.getCarNum3();
		CarInfoExample example = new CarInfoExample();
		CarInfoExample.Criteria criteria = example.createCriteria();
		if(!"".equals(num1) && null != num1){
			criteria.andCarNum1EqualTo(num1);
		}
		if(!"".equals(num2) && null != num2){
			criteria.andCarNum2EqualTo(num2);
		}
		if(!"".equals(num3) && null != num3){
			criteria.andCarNum3EqualTo(num3);
		}
		List<CarInfo> list = carInfoMapper.selectByExample(example);
		if(list.size() == 1){
			return list.get(0);
		}
		return null;
	}

	public boolean add(CarInfo carInfo) {
		try{
			carInfoMapper.insertSelective(carInfo);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateC(CarInfo carinfo) {
		try{
			String carnum = carinfo.getCarNum1();
			if("无法获取".equals(carnum)){
				carinfo.setCarNum1(carnum+MyTools.getDateR());
			}
			carInfoMapper.updateByPrimaryKeySelective(carinfo);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public CarInfo selectById(String carId) {
		CarInfo carInfo = carInfoMapper.selectByPrimaryKey(carId);
		return carInfo;
	}
	
	
	
}