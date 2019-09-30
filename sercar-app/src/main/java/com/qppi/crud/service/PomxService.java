package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Pomx;
import com.qppi.crud.bean.PomxExample;
import com.qppi.crud.dao.PomxMapper;

@Service
public class PomxService {

	@Autowired
	private PomxMapper pomxMapper;

	public Pomx getPomx(Pomx pomx) {
		String pomxId = pomx.getPomxId();
		if(pomxId != null && !"".equals(pomxId)){
			Pomx po = pomxMapper.selectByPrimaryKey(pomxId);
			return po;
		}
		return null;
	}

	public boolean update(Pomx pomx) {
		try {
			pomxMapper.updateByPrimaryKeySelective(pomx);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Pomx> selectByOrderId(String orderId) {
		PomxExample example = new PomxExample();
		PomxExample.Criteria criteria = example.createCriteria();
		criteria.andYl2EqualTo(orderId);
		List<Pomx> list = pomxMapper.selectByExample(example);
		return list;
	}

	public List<Pomx> selectByPartOfferId(String partofferId) {
		PomxExample example = new PomxExample();
		PomxExample.Criteria criteria = example.createCriteria();
		criteria.andPartOfferIdEqualTo(partofferId);
		List<Pomx> list = pomxMapper.selectByExample(example);
		return list;
	}

	public boolean deleteById(String pomxId) {
		try {
			pomxMapper.deleteByPrimaryKey(pomxId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

}
