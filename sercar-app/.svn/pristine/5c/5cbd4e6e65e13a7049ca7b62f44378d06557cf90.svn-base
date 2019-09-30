package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Resume;
import com.qppi.crud.bean.ResumeExample;
import com.qppi.crud.dao.ResumeMapper;

@Service
public class ResumeService {
	
	@Autowired
	ResumeMapper resumeMapper;

	public Resume selectByUid(String userinfoId) {
		// TODO Auto-generated method stub
		ResumeExample example = new ResumeExample();
		ResumeExample.Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(userinfoId);
		List<Resume> list = resumeMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
