package com.qppi.crud.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Car;
import com.qppi.crud.service.CarService;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;

@Controller
@RequestMapping("car")
public class CarController {

	@Autowired
	private CarService carService;
	
	//查看详细车型
	@RequestMapping("list")
	@ResponseBody
	public Msg list(String carchexi){
		List<Car> cars = carService.select(carchexi);
		Collections.sort(cars);
		return Msg.success().add("result", cars);
	}
	
	@RequestMapping("change")
	@ResponseBody
	public Msg change(){
		List<Car> cars = carService.selectAll();
		for (Car car : cars) {
			String hanyuPinyin = MySelfUtil.toHanyuPinyin(car.getChexi());
			car.setYl1(hanyuPinyin);
			carService.update(car);
		}
		return Msg.success().add("result", "true");
	}
}
