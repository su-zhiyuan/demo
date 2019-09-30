package com.fh.service.sercar.purchase.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.sercar.purchase.PurchaseManager;

/** 
 * 说明： 配件采购单
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 * @version
 */
@Service("purchaseService")
public class PurchaseService implements PurchaseManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("PurchaseMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("PurchaseMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("PurchaseMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("PurchaseMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("PurchaseMapper.listAll", pd);
	}
	
	/**
	 * 采购详情表
	 * @param pd
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> purchaseMx(String ORDER_ID) throws Exception{
		return (List<PageData>)dao.findForList("PomxMapper.purchaseMx", ORDER_ID);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("PurchaseMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("PurchaseMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

