package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Outside;
import com.qppi.crud.bean.OutsideExample;
import com.qppi.crud.dao.OutsideMapper;

@Service
public class OutsideService {
	@Autowired
	private OutsideMapper outsideMapper;
	
	/**
	 * 查询列表 
	 * @param outside
	 * @return
	 */
	public List<Outside> list(Outside outside,int page){
		OutsideExample example = new OutsideExample();
		example.setOrderByClause("yl4 desc");
		example.setPageSize(10);
		example.setStartRow((page-1)*10);
		OutsideExample.Criteria criteria = example.createCriteria();
		String id = outside.getOutsideId();
		String companyId = outside.getYl10();
		criteria.andYl10EqualTo(companyId);
		if(null != id && !"".equals(id)){
			criteria.andOutsideIdEqualTo(id);
		}
		String starttime = outside.getStarttime();
		if(null != starttime && !"".equals(starttime)){
			criteria.andStarttimeEqualTo(starttime);
		}
		String endtime = outside.getEndtime();
		if(null != endtime && !"".equals(endtime)){
			criteria.andEndtimeEqualTo(endtime);
		}
		String cause = outside.getCause();
		if(null != cause && !"".equals(cause)){
			criteria.andCauseEqualTo(cause);
		}
		
		return outsideMapper.selectByExample(example);
	}
	
	/**
	 * 获取对象
	 * @param outside
	 * @return
	 */
	public Outside get(Outside outside){
		return outsideMapper.selectByPrimaryKey(outside.getOutsideId());
	}
	
	/**
	 * 增加对象
	 * @param outside
	 * @return
	 */
	public boolean add(Outside outside){
		try{
			outsideMapper.insertSelective(outside);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 更新对象
	 * @param outside
	 * @return
	 */
	public boolean update(Outside outside){
		try{
			outsideMapper.updateByPrimaryKeySelective(outside);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除对象
	 * @param outside
	 * @return
	 */
	public boolean delite(Outside outside){
		try{
			outsideMapper.deleteByPrimaryKey(outside.getOutsideId());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 查询个人外出
	 */
	public List<Outside> select(int page, String username) {
		OutsideExample example = new OutsideExample();
		example.setOrderByClause("yl4 desc");
		example.setPageSize(10);
		example.setStartRow((page-1)*10);
		OutsideExample.Criteria criteria = example.createCriteria();
		criteria.andYl3EqualTo(username);
		return outsideMapper.selectByExample(example);
	}

	public List<Outside> selectDSP(Outside outside) {
		OutsideExample example = new OutsideExample();
		OutsideExample.Criteria criteria = example.createCriteria();
		String companyId = outside.getYl10();
		if(companyId != null && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		String status = outside.getYl1();
		if(status != null && !"".equals(status)){
			criteria.andYl1EqualTo(status);
		}
		List<Outside> list = outsideMapper.selectByExample(example);
		return list;
	}
	
}
