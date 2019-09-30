package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Order;
import com.qppi.crud.bean.Settlement;
import com.qppi.crud.bean.SettlementExample;
import com.qppi.crud.dao.OrderMapper;
import com.qppi.crud.dao.SettlementMapper;

@Service
public class SettlementService {
	
	@Autowired
	private SettlementMapper settlementMapper;
	@Autowired
	private OrderMapper orderMapper;

	public boolean add(Settlement settlement) {
		try{
			settlementMapper.insertSelective(settlement);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Settlement settlement) {
		try{
			settlementMapper.updateByPrimaryKeySelective(settlement);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(String settlementId,String orderId) {
		try{
			settlementMapper.deleteByPrimaryKey(settlementId);
			Order order = new Order();
			order.setOrderId(orderId);
			order.setStatus("配件已报价待结算");
			orderMapper.updateByPrimaryKeySelective(order);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public Settlement selectByOrderId(String orderId) {
		SettlementExample example = new SettlementExample();
		SettlementExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<Settlement> list = settlementMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public Settlement getSettlement(Settlement settlement) {
		Settlement st = settlementMapper.selectByPrimaryKey(settlement.getSettlementId());
		return st;
	}

}
