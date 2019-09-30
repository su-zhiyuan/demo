package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.LuYin;
import com.qppi.crud.bean.LuYinExample;
import com.qppi.crud.dao.LuYinMapper;
import com.qppi.crud.utils.Msg;

@Service
public class LuyinService {
	@Autowired
	private LuYinMapper luYinMapper;

	public boolean addLuYin(LuYin luYin) {
		try {
			luYinMapper.insert(luYin);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<LuYin> listLuyin(String infoRelayId) {
		LuYinExample example = new LuYinExample();
		LuYinExample.Criteria criteria = example.createCriteria();
		criteria.andInforelayidEqualTo(infoRelayId);
		return luYinMapper.selectByExample(example);
	}

	public boolean update(LuYin luYin) {
		try {
			luYinMapper.updateByPrimaryKeySelective(luYin);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
}
