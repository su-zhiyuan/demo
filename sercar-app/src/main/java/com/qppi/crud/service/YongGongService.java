package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.YongGong;
import com.qppi.crud.bean.YongGongExample;
import com.qppi.crud.dao.YongGongMapper;

@Service
public class YongGongService {

	@Autowired
	private YongGongMapper yongGongMapper;

	public List<YongGong> listYonggong(YongGong yongGong,int page) {
		YongGongExample example = new YongGongExample();
		example.setPageSize(10);
		example.setStartRow((page-1)*10);
		example.setOrderByClause("CREATE_TIME desc");
		YongGongExample.Criteria criteria = example.createCriteria();
		String createBy = yongGong.getCreateBy();
		if(createBy != null && !"".equals(createBy)){
			criteria.andCreateByEqualTo(createBy);
		}
		String companyId = yongGong.getYl10();
		if(companyId != null && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		List<YongGong> list = yongGongMapper.selectByExample(example);
		return list;
	}

	public boolean addYonggong(YongGong yongGong) {
		try {
			yongGongMapper.insertSelective(yongGong);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public YongGong getYonggong(YongGong yongGong) {
		YongGong key = yongGongMapper.selectByPrimaryKey(yongGong.getYonggongId());
		return key;
	}

	public List<YongGong> selectByStatus(YongGong yongGong) {
		YongGongExample example = new YongGongExample();
		YongGongExample.Criteria criteria = example.createCriteria();
		String status = yongGong.getYl1();
		if(status != null && !"".equals(status)){
			criteria.andYl1EqualTo(status);
		}
		String zpBumen = yongGong.getZpBumen();
		if(zpBumen != null && !"".equals(zpBumen)){
			criteria.andZpBumenEqualTo(zpBumen);
		}
		String companyId = yongGong.getYl10();
		if(companyId != null && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		List<YongGong> list = yongGongMapper.selectByExample(example);
		return list;
	}

	public boolean update(YongGong yongGong) {
		try {
			yongGongMapper.updateByPrimaryKeySelective(yongGong);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
