package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Baoxiao;
import com.qppi.crud.bean.BaoxiaoExample;
import com.qppi.crud.bean.Order;
import com.qppi.crud.dao.BaoxiaoMapper;
import com.qppi.crud.utils.MyTools;

@Service
public class BaoxiaoService {
	@Autowired
	private BaoxiaoMapper baoxiaoMapper;
	
	/**
	 * 查询列表 
	 * @param baoxiao
	 * @return
	 */
	public List<Baoxiao> list(Baoxiao baoxiao,int page){
		BaoxiaoExample example = new BaoxiaoExample();
		example.setOrderByClause("yl2 desc");
		BaoxiaoExample.Criteria criteria = example.createCriteria();
		String id = baoxiao.getBaoxiaoId();
		String companyId = baoxiao.getYl10();
		criteria.andYl10EqualTo(companyId);
		if(null != id && !"".equals(id)){
			criteria.andBaoxiaoIdEqualTo(id);
		}
		String type = baoxiao.getType();
		if(null != type && !"".equals(type)){
			criteria.andTypeEqualTo(type);
		}
		String total = baoxiao.getTotal();
		if(null != total && !"".equals(total)){
			criteria.andTotalEqualTo(total);
		}
		String explains = baoxiao.getExplains();
		if(null != explains && !"".equals(explains)){
			criteria.andExplainsEqualTo(explains);
		}
		return baoxiaoMapper.selectByExample(example);
	}
	
	/**
	 * 获取对象
	 * @param baoxiao
	 * @return
	 */
	public Baoxiao get(Baoxiao baoxiao){
		return baoxiaoMapper.selectByPrimaryKey(baoxiao.getBaoxiaoId());
	}
	
	/**
	 * 增加对象
	 * @param baoxiao
	 * @return
	 */
	public boolean add(Baoxiao baoxiao){
		baoxiao.setYl2(MyTools.getTime());
		try{
			baoxiaoMapper.insertSelective(baoxiao);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 更新对象
	 * @param baoxiao
	 * @return
	 */
	public boolean update(Baoxiao baoxiao){
		try{
			baoxiaoMapper.updateByPrimaryKeySelective(baoxiao);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除对象
	 * @param baoxiao
	 * @return
	 */
	public boolean delete(Baoxiao baoxiao){
		try{
			baoxiaoMapper.deleteByPrimaryKey(baoxiao.getBaoxiaoId());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Baoxiao> select(Baoxiao baoxiao , int page) {
		BaoxiaoExample example = new BaoxiaoExample();
		example.setOrderByClause("yl2 desc");
		if(page > 0 && !"".equals(page)){
			example.setPageSize(10);
			example.setStartRow((page-1)*10);
		}
		BaoxiaoExample.Criteria criteria = example.createCriteria();
		
		String companyId = baoxiao.getYl10();
		if(null != companyId && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		String id = baoxiao.getBaoxiaoId();
		if(null != id && !"".equals(id)){
			criteria.andBaoxiaoIdEqualTo(id);
		}
		String total = baoxiao.getTotal();
		if(null != total && !"".equals(total)){
			criteria.andTotalEqualTo(total);
		}
		String username = baoxiao.getYl3();
		if(null != username && !"".equals(username)){
			criteria.andYl3EqualTo(username);
		}
		String yl1 = baoxiao.getYl1();
		if(null != yl1 && !"".equals(yl1)){
			criteria.andYl1EqualTo(yl1);
		}
		return baoxiaoMapper.selectByExample(example);
	}

	public List<Baoxiao> selectDSP(Baoxiao baoxiao) {
		BaoxiaoExample example = new BaoxiaoExample();
		BaoxiaoExample.Criteria criteria = example.createCriteria();
		String companyId = baoxiao.getYl10();
		if(companyId != null && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		String status = baoxiao.getYl1();
		if(status != null && !"".equals(status)){
			criteria.andYl1EqualTo(status);
		}
		List<Baoxiao> list = baoxiaoMapper.selectByExample(example);
		return list;
	}
}
