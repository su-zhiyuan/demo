package com.qppi.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qppi.crud.bean.Applyjb;
import com.qppi.crud.bean.ApplyjbExample;
import com.qppi.crud.dao.ApplyjbMapper;

@Service
public class ApplyjbService {
	@Autowired
	private ApplyjbMapper applyjbMapper;
	
	/**
	 * 查询列表 
	 * @param applyjb
	 * @return
	 */
	public List<Applyjb> list(Applyjb applyjb,int page){
		ApplyjbExample example = new ApplyjbExample();
		example.setOrderByClause("createtime desc");
		example.setPageSize(10);
		example.setStartRow((page-1)*10);
		ApplyjbExample.Criteria criteria = example.createCriteria();
		String id = applyjb.getApplyjbId();
		String companyId = applyjb.getYl10();
		criteria.andYl10EqualTo(companyId);
		if(null != id && !"".equals(id)){
			criteria.andApplyjbIdEqualTo(id);
		}
		String createby = applyjb.getCreateby();
		if(null != createby && !"".equals(createby)){
			criteria.andCreatebyEqualTo(createby);
		}
		String createtime = applyjb.getCreatetime();
		if(null != createtime && !"".equals(createtime)){
			criteria.andCreatetimeEqualTo(createtime);
		}
		String starttime = applyjb.getStarttime();
		if(null != starttime && !"".equals(starttime)){
			criteria.andStarttimeEqualTo(starttime);
		}
		String endtime = applyjb.getEndtime();
		if(null != endtime && !"".equals(endtime)){
			criteria.andEndtimeEqualTo(endtime);
		}
		String cause = applyjb.getCause();
		if(null != cause && !"".equals(cause)){
			criteria.andCauseEqualTo(cause);
		}
		return applyjbMapper.selectByExample(example);
	}
	
	/**
	 * 获取对象
	 * @param applyjb
	 * @return
	 */
	public Applyjb get(Applyjb applyjb){
		return applyjbMapper.selectByPrimaryKey(applyjb.getApplyjbId());
	}
	
	/**
	 * 增加对象
	 * @param applyjb
	 * @return
	 */
	public boolean add(Applyjb applyjb){
		try{
			applyjbMapper.insertSelective(applyjb);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 更新对象
	 * @param applyjb
	 * @return
	 */
	public boolean update(Applyjb applyjb){
		try{
			applyjbMapper.updateByPrimaryKeySelective(applyjb);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除对象
	 * @param applyjb
	 * @return
	 */
	public boolean delite(Applyjb applyjb){
		try{
			applyjbMapper.deleteByPrimaryKey(applyjb.getApplyjbId());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Applyjb> select(String username, int page) {
		ApplyjbExample example = new ApplyjbExample();
		example.setOrderByClause("createtime desc");
		example.setPageSize(10);
		example.setStartRow((page-1)*10);
		ApplyjbExample.Criteria criteria = example.createCriteria();
		if(username != null && !"".equals(username)){
			criteria.andYl3EqualTo(username);
		}
		return applyjbMapper.selectByExample(example);
	}

	public List<Applyjb> selectDSP(Applyjb applyjb) {
		ApplyjbExample example = new ApplyjbExample();
		ApplyjbExample.Criteria criteria = example.createCriteria();
		String companyId = applyjb.getYl10();
		if(companyId != null && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		String status = applyjb.getYl1();
		if(status != null && !"".equals(status)){
			criteria.andYl1EqualTo(status);
		}
		List<Applyjb> list = applyjbMapper.selectByExample(example);
		return list;
	}
}
