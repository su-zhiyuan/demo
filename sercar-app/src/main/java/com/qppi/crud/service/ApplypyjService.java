package com.qppi.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qppi.crud.bean.Applypyj;
import com.qppi.crud.bean.ApplypyjExample;
import com.qppi.crud.dao.ApplypyjMapper;

@Service
public class ApplypyjService {
	@Autowired
	private ApplypyjMapper applypyjMapper;
	
	/**
	 * 查询列表 
	 * @param applypyj
	 * @return
	 */
	public List<Applypyj> list(Applypyj applypyj, int page){
		ApplypyjExample example = new ApplypyjExample();
		example.setPageSize(10);
		example.setStartRow((page-1)*10);
		example.setOrderByClause("yl3 desc");
		ApplypyjExample.Criteria criteria = example.createCriteria();
		
		String companyId = applypyj.getYl10();
		if(null != companyId && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		String id = applypyj.getApplypyjId();
		if(null != id && !"".equals(id)){
			criteria.andApplypyjIdEqualTo(id);
		}
		
		String proposer = applypyj.getProposer();
		if(null != proposer && !"".equals(proposer)){
			criteria.andProposerEqualTo(proposer);
		}
		
		String department  = applypyj.getDepartment();
		if(null != department && !"".equals(department)){
			criteria.andDepartmentEqualTo(department);
		}
		
		String explains = applypyj.getExplains();
		if(null != explains && !"".equals(explains)){
			criteria.andExplainsEqualTo(explains);
		}
		
		String money = applypyj.getMoney();
		if(null != money && !"".equals(money)){
			criteria.andMoneyEqualTo(money);
		}
		String usedate = applypyj.getUsedate();
		if(null != usedate && !"".equals(usedate)){
			criteria.andUsedateEqualTo(usedate);
		}
		
		String returndate = applypyj.getReturndate();
		if(null != returndate && !"".equals(returndate)){
			criteria.andReturndateEqualTo(returndate);
		}
		
		String cashier = applypyj.getCashier();
		if(null != cashier && !"".equals(cashier)){
			criteria.andCashierEqualTo(cashier);
		}
		
		List<Applypyj> list = applypyjMapper.selectByExample(example);
		return list;
	}
	
	/**
	 * 获取对象
	 * @param applypyj
	 * @return
	 */
	public Applypyj get(Applypyj applypyj){
		return applypyjMapper.selectByPrimaryKey(applypyj.getApplypyjId());
	}
	
	/**
	 * 增加对象
	 * @param applypyj
	 * @return
	 */
	public boolean add(Applypyj applypyj){
		try{
			applypyjMapper.insertSelective(applypyj);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 更新对象
	 * @param applypyj
	 * @return
	 */
	public boolean update(Applypyj applypyj){
		try{
			applypyjMapper.updateByPrimaryKeySelective(applypyj);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除对象
	 * @param applypyj
	 * @return
	 */
	public boolean delete(Applypyj applypyj){
		try{
			applypyjMapper.deleteByPrimaryKey(applypyj.getApplypyjId());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Applypyj> select(Applypyj applypyj, int page) {
		ApplypyjExample example = new ApplypyjExample();
		if(page>0 && !"".equals(page)){
			example.setPageSize(10);
			example.setStartRow((page-1)*10);
		}
		example.setOrderByClause("yl3 desc");
		ApplypyjExample.Criteria criteria = example.createCriteria();
		
		String companyId = applypyj.getYl10();
		if(null != companyId && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		String username = applypyj.getYl4();
		if(null != username && !"".equals(username)){
			criteria.andYl4EqualTo(username);
		}
		String yl1 = applypyj.getYl1();
		if(null != yl1 && !"".equals(yl1)){
			criteria.andYl1EqualTo(yl1);
		}
		return applypyjMapper.selectByExample(example);
	}

	public List<Applypyj> selectDSP(Applypyj applypyj) {
		ApplypyjExample example = new ApplypyjExample();
		ApplypyjExample.Criteria criteria = example.createCriteria();
		String companyId = applypyj.getYl10();
		if(companyId != null && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		String status = applypyj.getYl1();
		if(status != null && !"".equals(status)){
			criteria.andYl1EqualTo(status);
		}
		List<Applypyj> list = applypyjMapper.selectByExample(example);
		return list;
	}
}
