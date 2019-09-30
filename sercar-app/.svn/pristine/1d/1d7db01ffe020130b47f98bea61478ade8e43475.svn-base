package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Dsmx;
import com.qppi.crud.bean.Pomx;
import com.qppi.crud.bean.PomxExample;
import com.qppi.crud.dao.DsmxMapper;
import com.qppi.crud.dao.PomxMapper;

@Service
public class PartOfferMXService {

	@Autowired
	private PomxMapper pomxMapper;
	@Autowired
	private DsmxMapper dsmxMapper;
	
	public boolean addP(Pomx pomx) {
		try{
			pomxMapper.insertSelective(pomx);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean addD(Dsmx dsmx) {
		try{
			dsmxMapper.insertSelective(dsmx);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Pomx pomx) {
		try{
			pomxMapper.updateByPrimaryKeySelective(pomx);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Pomx> listByOrderId(Pomx pomx) {
		try{
			PomxExample example = new PomxExample();
			PomxExample.Criteria criteria = example.createCriteria();
			criteria.andYl2EqualTo(pomx.getYl2());
			return pomxMapper.selectByExample(example);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean delete(String partOfferId) {
		try{
			pomxMapper.deleteByPrimaryKey(partOfferId);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
