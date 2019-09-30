package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.MyCollection;
import com.qppi.crud.dao.MyCollectionMapper;

@Service
public class MyCollectionService {
	
	@Autowired
	private MyCollectionMapper myCollectionMapper;

	public List<MyCollection> listMC(MyCollection mc) {
		List<MyCollection> list = myCollectionMapper.selectByExample(mc);
		if(list.size()>0) {
			return list;
		}
		return null;
	}
	
	//点击收藏
	public boolean addMC(MyCollection mc) {
		try {
			myCollectionMapper.insert(mc);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//取消收藏
	public boolean delMC(MyCollection mc) {
		try {
			myCollectionMapper.delMC(mc);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//我的收藏
	public List<MyCollection> selectMC(MyCollection mc) {
		List<MyCollection> list = myCollectionMapper.selectMC(mc);
		if(list.size()>0) {
			return list;
		}
		return null;
	}

}
