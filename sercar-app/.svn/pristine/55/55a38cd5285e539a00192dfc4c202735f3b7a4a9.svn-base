package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.SName;
import com.qppi.crud.bean.SNameExample;
import com.qppi.crud.bean.SType;
import com.qppi.crud.bean.STypeExample;
import com.qppi.crud.dao.SNameMapper;
import com.qppi.crud.dao.STypeMapper;

@Service
public class SService {
	
	@Autowired
	private STypeMapper stypeMapper;
	@Autowired
	private SNameMapper snameMapper;

	public List<SType> listST(String companyId) {
		STypeExample example = new STypeExample();
		STypeExample.Criteria criteria = example.createCriteria();
		if(null != companyId && !"".equals(companyId)){
			criteria.andCompanyIdEqualTo(companyId);
		}
		List<SType> list = stypeMapper.selectByExample(example);
		return list;
	}

	public List<SName> listSN(SName sname, String key) {
		SNameExample example = new SNameExample();
		SNameExample.Criteria criteria = example.createCriteria();
		String pid = sname.getPid();
		if(pid != null && !"".equals(pid)){
			criteria.andPidEqualTo(pid);
		}
		if(key != null && !"".equals(key)){
			criteria.andSNameLike("%"+key+"%");
		}
		return snameMapper.selectByExample(example);
	}

}
