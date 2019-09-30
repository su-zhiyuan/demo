package com.qppi.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qppi.crud.bean.Evection;
import com.qppi.crud.bean.EvectionExample;
import com.qppi.crud.dao.EvectionMapper;

@Service
public class EvectionService {
	@Autowired
	private EvectionMapper evectionMapper;
	
	/**
	 * 查询列表 
	 * @param evection
	 * @return
	 */
	public List<Evection> list(Evection evection,int page){
		EvectionExample example = new EvectionExample();
		example.setOrderByClause("yl4 desc");
		example.setPageSize(10);
		example.setStartRow((page-1)*10);
		EvectionExample.Criteria criteria = example.createCriteria();
		String id = evection.getEvectionId();
		String companyId = evection.getYl10();
		criteria.andYl10EqualTo(companyId);
		if(null != id && !"".equals(id)){
			criteria.andEvectionIdEqualTo(id);
		}
		String cause = evection.getCause();
		if(null != cause && !"".equals(cause)){
			criteria.andCauseEqualTo(cause);
		}
		String jtgj = evection.getJtgj();
		if(null != jtgj && !"".equals(jtgj)){
			criteria.andJtgjEqualTo(jtgj);
		}
		String way = evection.getWay();
		if(null != way && !"".equals(way)){
			criteria.andWayEqualTo(way);
		}
		String citycf = evection.getCitycf();
		if(null != citycf && !"".equals(citycf)){
			criteria.andCitycfEqualTo(citycf);
		}
		String citydd = evection.getCitydd();
		if(null != citydd && !"".equals(citydd)){
			criteria.andCityddEqualTo(citydd);
		}
		String starttime = evection.getStarttime();
		if(null != starttime && !"".equals(starttime)){
			criteria.andStarttimeEqualTo(starttime);
		}
		String endtime = evection.getEndtime();
		if(null != endtime && !"".equals(endtime)){
			criteria.andEndtimeEqualTo(endtime);
		}
		String days = evection.getDays();
		if(null != days && !"".equals(days)){
			criteria.andDaysEqualTo(days);
		}
		String txr = evection.getTxr();
		if(null != txr && !"".equals(txr)){
			criteria.andTxrEqualTo(txr);
		}
		
		return evectionMapper.selectByExample(example);
	}
	
	/**
	 * 获取对象
	 * @param evection
	 * @return
	 */
	public Evection get(Evection evection){
		return evectionMapper.selectByPrimaryKey(evection.getEvectionId());
	}
	
	/**
	 * 增加对象
	 * @param evection
	 * @return
	 */
	public boolean add(Evection evection){
		try{
			evectionMapper.insertSelective(evection);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 更新对象
	 * @param evection
	 * @return
	 */
	public boolean update(Evection evection){
		try{
			evectionMapper.updateByPrimaryKeySelective(evection);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除对象
	 * @param evection
	 * @return
	 */
	public boolean delite(Evection evection){
		try{
			evectionMapper.deleteByPrimaryKey(evection.getEvectionId());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Evection> select(int page, String username) {
		EvectionExample example = new EvectionExample();
		example.setOrderByClause("yl4 desc");
		example.setPageSize(10);
		example.setStartRow((page-1)*10);
		EvectionExample.Criteria criteria = example.createCriteria();
		if(username != null && !"".equals(username)){
			criteria.andYl3EqualTo(username);
		}
		return evectionMapper.selectByExample(example);
	}

	public Evection selectById(String evectionId) {
		Evection evection = evectionMapper.selectByPrimaryKey(evectionId);
		return evection;
	}

	public List<Evection> selectDSP(Evection evection) {
		EvectionExample example = new EvectionExample();
		EvectionExample.Criteria criteria = example.createCriteria();
		String companyId = evection.getYl10();
		if(companyId != null && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		String status = evection.getYl1();
		if(status != null && !"".equals(status)){
			criteria.andYl1EqualTo(status);
		}
		List<Evection> list = evectionMapper.selectByExample(example);
		return list;
	}
}
