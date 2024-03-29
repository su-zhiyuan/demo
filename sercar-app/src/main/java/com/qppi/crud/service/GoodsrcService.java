package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.GoodsRC;
import com.qppi.crud.bean.GoodsRCExample;
import com.qppi.crud.bean.Purchase;
import com.qppi.crud.dao.GoodsRCMapper;

@Service
public class GoodsrcService {

	@Autowired
	private GoodsRCMapper goodsRCMapper;

	public boolean addGood(GoodsRC good) {
		try {
			goodsRCMapper.insertSelective(good);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public List<GoodsRC> selectGoodsRC(GoodsRC goodsRC) {
		GoodsRCExample example = new GoodsRCExample();
		GoodsRCExample.Criteria criteria = example.createCriteria();
		
		String caigourcId = goodsRC.getCaigourcId();
		if(null != caigourcId && !"".equals(caigourcId)){
			criteria.andCaigourcIdEqualTo(caigourcId);
		}
		
		List<GoodsRC> list = goodsRCMapper.selectByExample(example);
		return list;
	}

	public boolean delGoods(Purchase pur) {
		GoodsRCExample example = new GoodsRCExample();
		GoodsRCExample.Criteria criteria = example.createCriteria();
		
		String purchaseId = pur.getPurchaseId();
		try {
			if(null != purchaseId && !"".equals(purchaseId)){
				criteria.andCaigourcIdEqualTo(purchaseId);
			}
			goodsRCMapper.deleteByExample(example);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateCaiGou(GoodsRC good) {
		try {	
			goodsRCMapper.updateByPrimaryKeySelective(good);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public List<GoodsRC> selectByCaigou(String caigourcId) {
		GoodsRCExample example = new GoodsRCExample();
		GoodsRCExample.Criteria criteria = example.createCriteria();
		criteria.andCaigourcIdEqualTo(caigourcId);
		return goodsRCMapper.selectByExample(example);
	}

	public boolean delete(GoodsRC goodsRC) {
		try {
			goodsRCMapper.deleteByPrimaryKey(goodsRC.getGoodsrcId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
