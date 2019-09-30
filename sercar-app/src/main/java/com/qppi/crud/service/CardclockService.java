package com.qppi.crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.qppi.crud.bean.Cardclock;
import com.qppi.crud.bean.CardclockExample;
import com.qppi.crud.dao.CardclockMapper;
import com.qppi.crud.utils.BaiduUtil;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MyTools;

@Service
public class CardclockService {
	@Autowired
	private CardclockMapper cardclockMapper;
	
	/**
	 * 查询列表 
	 * @param cardclock
	 * @return
	 */
	public List<Cardclock> list(Cardclock cardclock,String companyinfoId,String username){
		CardclockExample example = new CardclockExample();
//		example.setOrderByClause("updatetime DESC");
		CardclockExample.Criteria criteria = example.createCriteria();
		String id = cardclock.getCardclockId();
		if(null != id && !"".equals(id)){
			criteria.andCardclockIdEqualTo(id);
		}
		if(null != username && !"".equals(username)){
			criteria.andDowntimeEqualTo(username);
		}
		if(null != companyinfoId && !"".equals(companyinfoId)){
			criteria.andYl5EqualTo(companyinfoId);
		}
		String date = cardclock.getDate();
		if(null != date && !"".equals(date)){
			criteria.andDateEqualTo(date);
		}
		String updatetime = cardclock.getUpdatetime();
		if(null != updatetime && !"".equals(updatetime)){
			criteria.andUpdatetimeEqualTo(updatetime);
		}
		String downtime = cardclock.getDowntime();
		if(null != downtime && !"".equals(downtime)){
			criteria.andDowntimeEqualTo(downtime);
		}
		String upplace = cardclock.getUpplace();
		if(null != upplace && !"".equals(upplace)){
			criteria.andUpplaceEqualTo(upplace);
		}
		String downplace = cardclock.getDownplace();
		if(null != downplace && !"".equals(downplace)){
			criteria.andDownplaceEqualTo(downplace);
		}
		
		return cardclockMapper.selectByExample(example);
	}
	
	/**
	 * 获取对象
	 * @param cardclock
	 * @return
	 */
	public Cardclock get(Cardclock cardclock){
		return cardclockMapper.selectByPrimaryKey(cardclock.getCardclockId());
	}
	
	/**
	 * 增加对象
	 * @param cardclock
	 * @return
	 */
	public boolean add(Cardclock cardclock){
		try{
			String yl8 = cardclock.getYl8();
			String yl1 = cardclock.getYl1();
			String place = "";
			if(yl1.equals("1")){
				place = cardclock.getUpplace();
			}else{
				place = cardclock.getDownplace();
			}
			
			System.out.println(place);
			Map mapType = JSON.parseObject(place,Map.class);  
			String lng = mapType.get("longitude").toString();
			String lat = mapType.get("latitude").toString();
	        String address = BaiduUtil.getAddress(lng,lat);
	        System.err.println(address);
	        
	        if(yl1.equals("1")){
	        	if(yl8 != null && !"".equals(yl8)){
	        		cardclock.setYl3(yl8);
	        	}else{
	        		cardclock.setYl3(address);
	        	}
	        }else{
	        	if(yl8 != null && !"".equals(yl8)){
	        		cardclock.setYl4(yl8);
	        	}else{
	        		cardclock.setYl4(address);
	        	}
	        }
			cardclockMapper.insertSelective(cardclock);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 更新对象
	 * @param cardclock
	 * @return
	 */
	public boolean update(Cardclock cardclock){
		try{
			cardclockMapper.updateByPrimaryKeySelective(cardclock);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除对象
	 * @param cardclock
	 * @return
	 */
	public boolean delite(Cardclock cardclock){
		try{
			cardclockMapper.deleteByPrimaryKey(cardclock.getCardclockId());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Cardclock> selectByName(Cardclock cardclock, int page) {
		CardclockExample example = new CardclockExample();
		example.setOrderByClause("date desc");
		example.setPageSize(10);
		int startRow = 10*(page-1);
		example.setStartRow(startRow);
		CardclockExample.Criteria criteria = example.createCriteria();
		criteria.andDowntimeEqualTo(cardclock.getDowntime());
		List<Cardclock> list = cardclockMapper.selectByExample(example);
		return list;
	}

	public List<Cardclock> selectByCard(String yl1, String username) {
		String time = MyTools.dayTime();
		CardclockExample example = new CardclockExample();
		CardclockExample.Criteria criteria = example.createCriteria();
		criteria.andYl1EqualTo(yl1);
		criteria.andDowntimeEqualTo(username);
		criteria.andDateEqualTo(time);
		List<Cardclock> list = cardclockMapper.selectByExample(example);
		return list;
	}

	
	
	
}
