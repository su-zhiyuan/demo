package com.fh.service.sercar.cardclock.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.sercar.cardclock.CardclockManager;

/** 
 * 说明： 考勤打卡
 * 创建人：FH Q313596790
 * 创建时间：2018-09-06
 * @version
 */
@Service("cardclockService")
public class CardclockService implements CardclockManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("CardclockMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("CardclockMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("CardclockMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CardclockMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CardclockMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CardclockMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CardclockMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/**查出每月所有打卡人
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> dakapeople(Page page) throws Exception {
		return (List<PageData>)dao.findForList("CardclockMapper.dakapeople", page);
	}
	
	/**每月所有人的打卡统计列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listdaka(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CardclockMapper.dakatongji", page);
	}
	
	
	/**每月所有人的打卡异常统计
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> yichangtj(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CardclockMapper.yichangtongji", page);
	}
}

