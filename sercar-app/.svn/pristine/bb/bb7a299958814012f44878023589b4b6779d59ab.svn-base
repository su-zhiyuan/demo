package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.SysUser;
import com.qppi.crud.bean.SysUserExample;
import com.qppi.crud.dao.SysUserMapper;

@Service
public class SysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;
	
	public SysUser getSysUser(SysUser sysUser){
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(sysUser.getUsername());
		criteria.andPasswordEqualTo(sysUser.getPassword());
		List<SysUser> list = sysUserMapper.selectByExample(example);
		if(list.size() == 1){
			return list.get(0);
		}
		return null;
	}
	
	public boolean addUser(SysUser sysUser){
		try{
			sysUserMapper.insertSelective(sysUser);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}