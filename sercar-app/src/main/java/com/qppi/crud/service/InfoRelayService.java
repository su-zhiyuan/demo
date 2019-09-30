package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.InfoRelay;
import com.qppi.crud.bean.InfoRelayExample;
import com.qppi.crud.bean.InfoRelayWithBLOBs;
import com.qppi.crud.dao.InfoRelayMapper;

@Service
public class InfoRelayService {
	
	@Autowired
	private InfoRelayMapper infoRelayMapper;

	public boolean add(InfoRelayWithBLOBs infoRelay) {
		try{
			infoRelayMapper.insertSelective(infoRelay);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(InfoRelayWithBLOBs infoRelay) {
		try{
			infoRelayMapper.updateByPrimaryKeySelective(infoRelay);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(String inforelayId) {
		try{
			infoRelayMapper.deleteByPrimaryKey(inforelayId);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<InfoRelay> selectByOrderId(String orderId) {
		InfoRelayExample example = new InfoRelayExample();
		InfoRelayExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<InfoRelay> list = infoRelayMapper.selectByExample(example);
		return list;
	}

	public List<InfoRelay> selectByInfo(InfoRelay infoRelay, int page) {
		InfoRelayExample example = new InfoRelayExample();
		example.setOrderByClause("CREATE_TIME desc");
		if(page > 0 && !"".equals(page)){
			example.setPageSize(10);
			example.setStartRow((page-1)*10);
		}
		InfoRelayExample.Criteria criteria = example.createCriteria();
		String yl3 = infoRelay.getYl3();
		if(yl3 != null && !"".equals(yl3)){
			criteria.andYl3EqualTo(yl3);
		}
		String yl10 = infoRelay.getYl10();
		if(yl10 != null && !"".equals(yl10)){
			criteria.andYl10EqualTo(yl10);
		}
		String status = infoRelay.getStatus();
		if(status != null && !"".equals(status)){
			criteria.andStatusEqualTo(status);
		}
		List<InfoRelay> list = infoRelayMapper.selectByExample(example);
		return list;
	}

	public InfoRelayWithBLOBs getInfoRelay(String infoRelayid) {
		InfoRelayWithBLOBs relayWithBLOBs = infoRelayMapper.selectByPrimaryKey(infoRelayid);
		return relayWithBLOBs;
	}

}
