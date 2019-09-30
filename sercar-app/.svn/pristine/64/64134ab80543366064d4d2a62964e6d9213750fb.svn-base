package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Dsmx;
import com.qppi.crud.bean.DsmxExample;
import com.qppi.crud.bean.PartOffer;
import com.qppi.crud.bean.PartOfferExample;
import com.qppi.crud.bean.Pomx;
import com.qppi.crud.bean.PomxExample;
import com.qppi.crud.dao.DsmxMapper;
import com.qppi.crud.dao.PartOfferMapper;
import com.qppi.crud.dao.PomxMapper;

@Service
public class PartOfferService {
	
	@Autowired
	private PartOfferMapper partOfferMapper;
	@Autowired
	private PomxMapper pomxMapper;
	@Autowired
	private DsmxMapper dsmxMapper;
	
	

	public boolean add(PartOffer partOffer) {
		try{
			partOfferMapper.insertSelective(partOffer);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(PartOffer partOffer) {
		try{
			partOfferMapper.updateByPrimaryKeySelective(partOffer);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//工时列表
	public List<PartOffer> selectDsmx(PartOffer partOffer,int page){
		String companyId = partOffer.getYl10();
		PartOfferExample example = new PartOfferExample();
		if(page != -1 && !"".equals(page)){
			example.setPageSize(10);
			example.setStartRow((page-1)*10);
			example.setOrderByClause("CREATE_TIME desc");
		}
		PartOfferExample.Criteria criteria = example.createCriteria();
		criteria.andYl3EqualTo("1");
		if(companyId != null && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
//		String createBy = partOffer.getCreateBy();
//		if(createBy != null && !"".equals(createBy)){
//			criteria.andCreateByEqualTo(createBy);
//		}
		String status = partOffer.getStatus();
		if(status != null && !"".equals(status)){
			criteria.andStatusEqualTo(status);
		}
		List<PartOffer> list = partOfferMapper.selectByExample(example);
		return list;
	}
	
	//查询工时报价详情
	public List<Dsmx> dsmxDetails(Dsmx dsmx){
		List<Dsmx> listdsmx = dsmxMapper.dsmxDetails(dsmx);
		if(listdsmx.size()>0){
			return listdsmx;
		}
		return null;
	}
	
	//配件列表
	public List<PartOffer> selectPomx(PartOffer partOffer,int page){
		String companyId = partOffer.getYl10();
		PartOfferExample example = new PartOfferExample();
		if(page != -1 && !"".equals(page)){
			example.setPageSize(10);
			example.setStartRow((page-1)*10);
			example.setOrderByClause("CREATE_TIME desc");
		}
		PartOfferExample.Criteria criteria = example.createCriteria();
		criteria.andYl3EqualTo("2");
		if(companyId != null && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		String status = partOffer.getStatus();
		if(status != null && !"".equals(status)){
			criteria.andStatusEqualTo(status);
		}
		List<PartOffer> list = partOfferMapper.selectByExample(example);
		return list;
	}
	
	//查询配件报价详情
	public List<Pomx> pomxDetails(Pomx pomx){
		List<Pomx> listPomx = pomxMapper.pomxDetails(pomx);
		if(listPomx.size()>0){
			return listPomx;
		}
		return null;
	}
	//提交复核
	public boolean updatePartY(PartOffer partOffer) {
		try{
			partOfferMapper.updatePartY(partOffer);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	//确认复核
	public boolean updatePartN(PartOffer partOffer) {
		try{
			partOfferMapper.updatePartN(partOffer);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(String partofferId) {
		try{
			partOfferMapper.deleteByPrimaryKey(partofferId);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<PartOffer> selectByOrderId(String orderId) {
		PartOfferExample example = new PartOfferExample();
		PartOfferExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<PartOffer> list = partOfferMapper.selectByExample(example);
		return list;
	}

	public PartOffer getParOffer(PartOffer partOffer) {
		String partofferId = partOffer.getPartofferId();
		PartOffer offer = partOfferMapper.selectByPrimaryKey(partofferId);
		if(offer== null){
			return offer;
		}
		DsmxExample dsExample = new DsmxExample();
		DsmxExample.Criteria dsCriteria = dsExample.createCriteria();
		dsCriteria.andYl1EqualTo(partofferId);
		List<Dsmx> dsList = dsmxMapper.selectByExample(dsExample);
		offer.setDsmxs(dsList);
		
		PomxExample poExample = new PomxExample();
		PomxExample.Criteria poCriteria = poExample.createCriteria();
		poCriteria.andPartOfferIdEqualTo(partofferId);
		List<Pomx> poList = pomxMapper.selectByExample(poExample);
		offer.setPomxs(poList);
		return offer;
	}

	public List<PartOffer> selectByCar(String carnumber) {
		PartOfferExample example = new PartOfferExample();
		PartOfferExample.Criteria criteria = example.createCriteria();
		criteria.andYl9EqualTo(carnumber);
		return partOfferMapper.selectByExample(example);
	}

	public List<PartOffer> selectbyStatus(String status) {
		PartOfferExample example = new PartOfferExample();
		PartOfferExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(status);
		return partOfferMapper.selectByExample(example);
	}
}
