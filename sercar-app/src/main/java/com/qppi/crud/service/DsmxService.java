package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Dispatching;
import com.qppi.crud.bean.Dsmx;
import com.qppi.crud.bean.DsmxExample;
import com.qppi.crud.bean.SName;
import com.qppi.crud.bean.SNameExample;
import com.qppi.crud.dao.DsmxMapper;
import com.qppi.crud.dao.SNameMapper;

@Service
public class DsmxService {
	
	@Autowired
	private DsmxMapper dsmxMapper;
	@Autowired
	private SNameMapper sNameMapper;

	public boolean add(Dsmx dsmx) {
		try{
			dsmxMapper.insertSelective(dsmx);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Dsmx dsmx) {
		try{
			dsmxMapper.updateByPrimaryKeySelective(dsmx);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Dsmx> listByDid(Dispatching dis) {
		try{
			DsmxExample example = new DsmxExample();
			DsmxExample.Criteria criteria = example.createCriteria();
			criteria.andDispatchingIdEqualTo(dis.getDispatchingId());
			return dsmxMapper.selectByExample(example);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Dsmx> listByOrderid(Dsmx dsmx) {
		try{
			DsmxExample example = new DsmxExample();
			DsmxExample.Criteria criteria = example.createCriteria();
			criteria.andYl2EqualTo(dsmx.getYl2());
			return dsmxMapper.selectByExample(example);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean delete(String dsmxId) {
		try{
			dsmxMapper.deleteByPrimaryKey(dsmxId);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Dsmx> selectByPoId(String partofferId) {
		DsmxExample example = new DsmxExample();
		DsmxExample.Criteria criteria = example.createCriteria();
		criteria.andYl1EqualTo(partofferId);
		List<Dsmx> list = dsmxMapper.selectByExample(example);
		return list;
	}

	public Dsmx getDsmx(Dsmx dsmx) {
		String dsmxId = dsmx.getDsmxId();
		if(dsmxId != null && !"".equals(dsmxId)){
			Dsmx ds = dsmxMapper.selectByPrimaryKey(dsmxId);
			return ds;
		}
		return null;
	}

	public List<SName> selectXmName(String name, String xmlxId) {
		SNameExample example = new SNameExample();
		SNameExample.Criteria criteria = example.createCriteria();
		if(name != null && !"".equals(name)){
			criteria.andSNameEqualTo(name);
		}
		if(xmlxId != null && !"".equals(xmlxId)){
			criteria.andPidEqualTo(xmlxId);
		}
		List<SName> list = sNameMapper.selectByExample(example);
		return list;
	}

	public boolean insertXmName(SName sName) {
		try {
			sNameMapper.insert(sName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Dsmx selectByDsmxId(String dsmxid) {
		Dsmx dsmx = dsmxMapper.selectByPrimaryKey(dsmxid);
		return dsmx;
	}

}
