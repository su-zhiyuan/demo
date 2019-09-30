package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Bulletinad;
import com.qppi.crud.bean.BulletinadExample;
import com.qppi.crud.dao.BulletinadMapper;

@Service
public class BulletinadService {
	
	@Autowired
	private BulletinadMapper bulletinadMapper;

	public List<Bulletinad> listBA(Bulletinad ba) {
		BulletinadExample example = new BulletinadExample();
		BulletinadExample.Criteria criteria = example.createCriteria();
		String type = ba.getType();
		if(null != type && !"".equals(type)) {
			criteria.andTypeEqualTo(type);
		}
		String yl1 = ba.getYl1();
		if(null != yl1 && !"".equals(yl1)) {
			criteria.andYl1EqualTo(yl1);
		}
		List<Bulletinad> list = bulletinadMapper.selectByExample(example);
		if(list.size()>0) {
			return list;
		}
		return null;
	}

}
