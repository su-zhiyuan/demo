package com.qppi.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Purchase;
import com.qppi.crud.dao.PurchaseMapper;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseMapper purchaseMapper;

	public boolean add(Purchase purchase) {
		try{
			purchaseMapper.insertSelective(purchase);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Purchase purchase) {
		try{
			purchaseMapper.updateByPrimaryKeySelective(purchase);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean delPurchase(String purchaseId) {
		try {
			purchaseMapper.deleteByPrimaryKey(purchaseId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Purchase getPurchase(String purchaseId) {
		Purchase purchase = purchaseMapper.selectByPrimaryKey(purchaseId);
		return purchase;
	}

}