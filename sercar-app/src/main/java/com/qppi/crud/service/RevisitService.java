package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Revisit;
import com.qppi.crud.bean.RevisitExample;
import com.qppi.crud.dao.RevisitMapper;

@Service
public class RevisitService {
	@Autowired
	private RevisitMapper revisitMapper;

	public boolean add(Revisit revisit) {
		try {
			revisitMapper.insert(revisit);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Revisit> selectAllVisit() {
		RevisitExample example = new RevisitExample();
		List<Revisit> list = revisitMapper.selectByExample(example);
		return list;
	}

	public List<Revisit> getList(Revisit revisit) {
		RevisitExample example = new RevisitExample();
		RevisitExample.Criteria criteria = example.createCriteria();
		String username = revisit.getCreater();
		if(username != null && !"".equals(username)){
			criteria.andCreaterEqualTo(username);
		}
		String yl10 = revisit.getYl10();
		if(yl10 != null && !"".equals(yl10)){
			criteria.andYl10EqualTo(yl10);
		}
		String yl4 = revisit.getYl4();
		if(yl4 != null && !"".equals(yl4)){
			criteria.andYl4EqualTo(yl4);
		}
		return revisitMapper.selectByExample(example);
	}

	public boolean update(Revisit revisit) {
		try {
			revisitMapper.updateByPrimaryKeySelective(revisit);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public Revisit getRevisit(String revisitId) {
		Revisit revisit = revisitMapper.selectByPrimaryKey(revisitId);
		return revisit;
	}
}
