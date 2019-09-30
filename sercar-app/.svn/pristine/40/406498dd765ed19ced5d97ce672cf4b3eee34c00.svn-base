package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Advertising;
import com.qppi.crud.dao.AdvertisingMapper;

@Service
public class AdvertisingService {

	@Autowired
	private AdvertisingMapper advertisingMapper;
	
	public List<Advertising> listAs() {
		return advertisingMapper.selectByExample(null);
	}

}