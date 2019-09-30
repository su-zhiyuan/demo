package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Resumeexperience;
import com.qppi.crud.bean.ResumeexperienceExample;
import com.qppi.crud.dao.ResumeexperienceMapper;

@Service
public class ResumeexperienceService {
	
	@Autowired
	private ResumeexperienceMapper resumeexperienceMapper;

	public List<Resumeexperience> selectByRid(String resumeId) {
		ResumeexperienceExample example = new ResumeexperienceExample();
		ResumeexperienceExample.Criteria criteria = example.createCriteria();
		criteria.andResumeidEqualTo(resumeId);
		List<Resumeexperience> list = resumeexperienceMapper.selectByExample(example);
		return list;
	}

}
