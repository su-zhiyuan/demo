package com.qppi.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qppi.crud.bean.Paymentrequest;
import com.qppi.crud.bean.PaymentrequestExample;
import com.qppi.crud.dao.PaymentrequestMapper;
@Service
public class PaymentrequestService {
	@Autowired
	private PaymentrequestMapper paymentrequestMapper;
	
	/**
	 * 查询列表 
	 * @param paymentrequest
	 * @return
	 */
	public List<Paymentrequest> list(Paymentrequest paymentrequest,int page){
		PaymentrequestExample example = new PaymentrequestExample();
		example.setOrderByClause("yl4 desc");
		example.setPageSize(10);
		example.setStartRow((page-1)*10);
		PaymentrequestExample.Criteria criteria = example.createCriteria();
		String id = paymentrequest.getPaymentrequestId();
		String companyId = paymentrequest.getYl10();
		criteria.andYl10EqualTo(companyId);
		if(null != id && !"".equals(id)){
			criteria.andPaymentrequestIdEqualTo(id);
		}
		String total = paymentrequest.getTotal();
		if(null != total && !"".equals(total)){
			criteria.andTotalEqualTo(total);
		}
		String mode = paymentrequest.getMode();
		if(null != mode && !"".equals(mode)){
			criteria.andModeEqualTo(mode);
		}
		String bank = paymentrequest.getBank();
		if(null != bank && !"".equals(bank)){
			criteria.andBankEqualTo(bank);
		}
		String account = paymentrequest.getAccount();
		if(null != account && !"".equals(account)){
			criteria.andAccountEqualTo(account);
		}
		String payer = paymentrequest.getPayer();
		if(null != payer && !"".equals(payer)){
			criteria.andPayerEqualTo(payer);
		}
		String explains = paymentrequest.getExplains();
		if(null != explains && !"".equals(explains)){
			criteria.andExplainsEqualTo(explains);
		}
		String paydate = paymentrequest.getPaydate();
		if(null != paydate && !"".equals(paydate)){
			criteria.andPaydateEqualTo(paydate);
		}
		String payobject = paymentrequest.getPayobject();
		if(null != payobject && !"".equals(payobject)){
			criteria.andPayobjectEqualTo(payobject);
		}
		
		return paymentrequestMapper.selectByExample(example);
	}
	
	/**
	 * 获取对象
	 * @param paymentrequest
	 * @return
	 */
	public Paymentrequest get(Paymentrequest paymentrequest){
		return paymentrequestMapper.selectByPrimaryKey(paymentrequest.getPaymentrequestId());
	}
	
	/**
	 * 增加对象
	 * @param paymentrequest
	 * @return
	 */
	public boolean add(Paymentrequest paymentrequest){
		try{
			paymentrequestMapper.insertSelective(paymentrequest);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 更新对象
	 * @param paymentrequest
	 * @return
	 */
	public boolean update(Paymentrequest paymentrequest){
		try{
			paymentrequestMapper.updateByPrimaryKeySelective(paymentrequest);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除对象
	 * @param paymentrequest
	 * @return
	 */
	public boolean delete(Paymentrequest paymentrequest){
		try{
			paymentrequestMapper.deleteByPrimaryKey(paymentrequest.getPaymentrequestId());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Paymentrequest> select(Paymentrequest paymentrequest, int page) {
		PaymentrequestExample example = new PaymentrequestExample();
		example.setOrderByClause("paydate desc");
		if(page >0 && !"".equals(page)){
			example.setPageSize(10);
			example.setStartRow((page-1)*10);
		}
		PaymentrequestExample.Criteria criteria = example.createCriteria();
		
		String username = paymentrequest.getYl3();
		if(username != null && !"".equals(username)){
			criteria.andYl3EqualTo(username);
		}
		String yl1 = paymentrequest.getYl1();
		if(null != yl1 && !"".equals(yl1)){
			criteria.andYl1EqualTo(yl1);
		}
		String companyId = paymentrequest.getYl10();
		if(null != companyId && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		
		return paymentrequestMapper.selectByExample(example);
	}

	public List<Paymentrequest> selectDSP(Paymentrequest paymentrequest) {
		PaymentrequestExample example = new PaymentrequestExample();
		PaymentrequestExample.Criteria criteria = example.createCriteria();
		String companyId = paymentrequest.getYl10();
		if(companyId != null && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		String status = paymentrequest.getYl1();
		if(status != null && !"".equals(status)){
			criteria.andYl1EqualTo(status);
		}
		List<Paymentrequest> list = paymentrequestMapper.selectByExample(example);
		return list;
	}
}
