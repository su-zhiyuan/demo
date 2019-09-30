package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.bean.UserInfoExample;
import com.qppi.crud.dao.UserInfoMapper;

@Service
public class UserInfoService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	public UserInfo getUserInfo(UserInfo userInfo){
		UserInfoExample example = new UserInfoExample();
		UserInfoExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userInfo.getUsername());
		List<UserInfo> list = userInfoMapper.selectByExampleWithBLOBs(example);
		if(list.size() == 1){
			return list.get(0);
		}
		return null;
	}
	
	public boolean addUserInfo(UserInfo userInfo){
		try{
			userInfoMapper.insertSelective(userInfo);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateUserInfo(UserInfo userInfo){
		try{
			userInfoMapper.updateByPrimaryKeySelective(userInfo);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public UserInfo queryUserInfo(UserInfo userInfo) {
		return userInfoMapper.selectByPrimaryKey(userInfo.getUserinfoId());
	}

	public List<UserInfo> listUserInfo(UserInfo userInfo,String username) {
		UserInfoExample example = new UserInfoExample();
		UserInfoExample.Criteria criteria = example.createCriteria();
		
		String yl1 = userInfo.getYl1();
		if(null != yl1 && !"".equals(yl1)){
			criteria.andYl1EqualTo(yl1);
		}
		String status = "在职";
		criteria.andStatusEqualTo(status);
		String name = userInfo.getName();
		if(null != name && !"".equals(name)){
			criteria.andNameLike("%"+name+"%");
		}
		criteria.andUsernameNotEqualTo(username);
		return userInfoMapper.selectByExampleWithBLOBs(example);
	}
	
	//根据公司ID查询公司所有的人
	public List<UserInfo> selectUserInfo(UserInfo userInfo) {
		String yl1 = userInfo.getYl1();
		UserInfoExample example = new UserInfoExample();
		UserInfoExample.Criteria criteria = example.createCriteria();
		if(yl1 != null || !"".equals(yl1)){
			criteria.andYl1EqualTo(yl1);
		}
		List<UserInfo> listUserInfo = userInfoMapper.selectByExample(example);
		return listUserInfo;
	}

	//根据公司角色类型判断
	public List<UserInfo> selectUser(UserInfo userInfo) {
		
		UserInfoExample example = new UserInfoExample();
		UserInfoExample.Criteria criteria = example.createCriteria();
		String roletype = userInfo.getRoletype();
		if(null != roletype && !"".equals(roletype)){
			criteria.andRoletypeEqualTo(roletype);
		}
		String username = userInfo.getUsername();
		if(null != username && !"".equals(username)){
			criteria.andUsernameEqualTo(username);
		}
		String yl10 = userInfo.getYl10();
		if(null != yl10 && !"".equals(yl10)){
			criteria.andYl10EqualTo(yl10);
		}
		String yl1 = userInfo.getYl1();
		if(null != yl1 && !"".equals(yl1)){
			criteria.andYl1EqualTo(yl1);
		}
		List<UserInfo> selectByExample = userInfoMapper.selectByExampleWithBLOBs(example);
		return selectByExample;
	}

	
	public UserInfo selectUserId(String username) {
		UserInfoExample example = new UserInfoExample();
		UserInfoExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<UserInfo> list = userInfoMapper.selectByExample(example);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	public UserInfo selectUserInfoByWx(String wxid) {
		UserInfoExample example = new UserInfoExample();
		UserInfoExample.Criteria criteria = example.createCriteria();
		criteria.andYl7EqualTo(wxid);
		List<UserInfo> list = userInfoMapper.selectByExample(example);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public List<UserInfo> selectRoleType(UserInfo userInfo) {
		UserInfoExample example = new UserInfoExample();
		UserInfoExample.Criteria criteria = example.createCriteria();
		String roletype = userInfo.getRoletype();
		if(null != roletype && !"".equals(roletype)){
			criteria.andRoletypeLike("%"+roletype+"%");
		}
		String yl1 = userInfo.getYl1();
		if(null != yl1 && !"".equals(yl1)){
			criteria.andYl1EqualTo(yl1);
		}
		List<UserInfo> selectByExample = userInfoMapper.selectByExampleWithBLOBs(example);
		return selectByExample;
	}

	public UserInfo getUserInfoById(String userinfoId) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByPrimaryKey(userinfoId);
	}

}
