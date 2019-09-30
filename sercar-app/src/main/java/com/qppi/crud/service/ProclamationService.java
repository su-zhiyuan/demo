package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.MyCollection;
import com.qppi.crud.bean.Proclamation;
import com.qppi.crud.bean.ProclamationExample;
import com.qppi.crud.dao.MyCollectionMapper;
import com.qppi.crud.dao.ProclamationMapper;

@Service
public class ProclamationService {
	
	@Autowired
	private ProclamationMapper proclamationMapper;
	
	@Autowired
	private MyCollectionMapper myCollectionMapper;

	public List<Proclamation> listPs(Proclamation proclamation) {
		ProclamationExample example = new ProclamationExample();
		ProclamationExample.Criteria criteria = example.createCriteria();
		criteria.andYl1EqualTo(proclamation.getYl1());
		example.setOrderByClause("CREATE_TIME DESC");
		//公告列表
		List<Proclamation> pm = proclamationMapper.selectByExample(example);
		//我的收藏
		MyCollection mc = new MyCollection();
		mc.setUsername(proclamation.getCreateBy());
		List<MyCollection> mt= myCollectionMapper.selectByExample(mc);
		for(int i=0;i<mt.size();i++){
			for(int j=0;j<pm.size();j++){
				if(mt.get(i).getBulletinId().equals(pm.get(j).getProclamationId())){
					pm.get(j).setYl2("1");
				}
			}
		}
		return pm;		
	}

	public Proclamation getPs(Proclamation proclamation) {
		return proclamationMapper.selectByPrimaryKey(proclamation.getProclamationId());
	}

}
