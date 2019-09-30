package com.qppi.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.CompanyInfo;
import com.qppi.crud.dao.CompanyInfoMapper;

@Service
public class CompanyInfoService {
	
	@Autowired
	private CompanyInfoMapper companyInfoMapper;

	public CompanyInfo query(CompanyInfo ci) {
		try {
			ci = companyInfoMapper.selectByPrimaryKey(ci.getCompanyinfoId());
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ci;
	}

	public CompanyInfo selectById(String yl5) {
		CompanyInfo info = companyInfoMapper.selectByPrimaryKey(yl5);
		return info;
	}

	public boolean updateCompany(CompanyInfo company) {
		try {
			companyInfoMapper.updateByPrimaryKeySelective(company);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
