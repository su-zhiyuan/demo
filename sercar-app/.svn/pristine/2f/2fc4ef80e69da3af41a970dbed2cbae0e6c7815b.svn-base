package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.RepoChange;
import com.qppi.crud.bean.RepoChangeExample;
import com.qppi.crud.bean.Repoinfo;
import com.qppi.crud.dao.RepoChangeMapper;

@Service
public class RepoChangeService {

	@Autowired
	private RepoChangeMapper repoChangeMapper;
	
	public boolean insertSelective(RepoChange rc) {
		try {
			repoChangeMapper.insertSelective(rc);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<RepoChange> selectList(String keyword, int page, RepoChange repoChange) {

		RepoChangeExample example = new RepoChangeExample();
		example.setOrderByClause("YL2 desc");
		example.setPageSize(10);
		example.setStartRow((page-1)*10);
		RepoChangeExample.Criteria criteria = example.createCriteria();
		String companyId = repoChange.getYl10();
		if(null != companyId && !"".equals(companyId)) {
			criteria.andYl10EqualTo(companyId);
		}
		String type = repoChange.getType();
		if(null != type && !"".equals(type)) {
			criteria.andTypeEqualTo(type);
		}
		if(keyword != null && !"".equals(keyword)){
			criteria.andYl4Like("%" + keyword +"%");
		}
		List<RepoChange> list = repoChangeMapper.selectByExample(example);
		return list;
	}

	public RepoChange getRepo(String repochangeId) {
		RepoChange repoChange = repoChangeMapper.selectByPrimaryKey(repochangeId);
		return repoChange;
	}

	public boolean update(RepoChange repoChange) {
		try {
			repoChangeMapper.updateByPrimaryKeySelective(repoChange);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		
	}
}
