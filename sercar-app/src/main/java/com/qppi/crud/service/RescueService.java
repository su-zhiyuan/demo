package com.qppi.crud.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Dsmx;
import com.qppi.crud.bean.PartOffer;
import com.qppi.crud.bean.Pomx;
import com.qppi.crud.bean.Rescue;
import com.qppi.crud.dao.DsmxMapper;
import com.qppi.crud.dao.PartOfferMapper;
import com.qppi.crud.dao.PomxMapper;
import com.qppi.crud.dao.RescueMapper;

@Service
public class RescueService {

	@Autowired
	private RescueMapper rescueMapper;
	@Autowired
	private PartOfferMapper partOfferMapper;
	@Autowired
	private PomxMapper pomxMapper;
	@Autowired
	private DsmxMapper dsmxMapper;
	
	//创建救援
	public boolean insertRescue(Rescue rescue) {
		try{
			rescueMapper.insertRescue(rescue);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	//查询救援
	public List<Rescue> selectRescue(int page){
		int pagesize = 5;
		int startRow = (page-1)*pagesize;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pagesize);
		map.put("startRow", startRow);
		List<Rescue> list = rescueMapper.selectRescue(map);
		if(list.size()>0) {
			return list;
		}
		return null;
	}
	
	//根据ID查询救援
	public Rescue selectRescueId(Rescue rescue){
		rescue = rescueMapper.selectRescueId(rescue);	
		PartOffer partOffer = new PartOffer();
		partOffer.setOrderId(rescue.getOrderId());
		List<PartOffer> listPart = partOfferMapper.selectOrderId(partOffer);
		for(int i=0; i<listPart.size(); i++){
			String partofferId = listPart.get(i).getPartofferId();
			//工时
			Dsmx dsmx = new Dsmx();
			dsmx.setYl1(partofferId);
			List<Dsmx> listDsmx = dsmxMapper.selectDsmxId(dsmx);
			listPart.get(i).setDsmxs(listDsmx);
			//配件
			Pomx pomx = new Pomx();
			pomx.setPartOfferId(partofferId);
			List<Pomx> listPomx = pomxMapper.selectPomxId(pomx);
			listPart.get(i).setPomxs(listPomx);
		}
		rescue.setPartOffers(listPart);
		return rescue;
	}
	//根据ID修改救援
	public boolean updateRescueId(Rescue rescue){
		try {
			rescueMapper.updateRescueId(rescue);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
