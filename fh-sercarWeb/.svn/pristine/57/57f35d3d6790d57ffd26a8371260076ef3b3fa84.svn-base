package com.fh.service.sercar.dsmx.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.sercar.dsmx.DsmxManager;

/** 
 * 说明： 派工明细
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 * @version
 */
@Service("dsmxService")
public class DsmxService implements DsmxManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("DsmxMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("DsmxMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("DsmxMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("DsmxMapper.datalistPage", page);
	}
	
	/**查询产量
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listKind(Page page)throws Exception{
		return (List<PageData>)dao.findForList("DsmxMapper.statistics", page);
	}
	
	/**查询工作量
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listWork(Page page)throws Exception{
		return (List<PageData>)dao.findForList("DsmxMapper.workjob", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("DsmxMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DsmxMapper.findById", pd);
	}
	
	/**结算单明细修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit_mx(PageData pd)throws Exception{
		dao.update("DsmxMapper.edit_mx", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("DsmxMapper.deleteAll", ArrayDATA_IDS);
	}

	/**
	 * 根据类型查出指定类型
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listByType(Page page) throws Exception {
		return (List<PageData>)dao.findForList("DsmxMapper.listbytype", page);
	}

	/**
	 * 根据人找出工作量
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listByPersion(Page page) throws Exception {
		return (List<PageData>)dao.findForList("DsmxMapper.listbyperson", page);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> findByCondition(Page page) throws Exception {
		return (List<PageData>)dao.findForList("DsmxMapper.findByCondition", page);
	}
	
}

