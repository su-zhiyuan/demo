package com.fh.service.sercar.partoffer;

import java.util.List;
import com.fh.entity.Page;
import com.fh.util.PageData;

/** 
 * 说明： 配件报价单接口
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 * @version
 */
public interface PartOfferManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;

	/**
	 * 根据条件查询报价单
	 * @param page
	 * @return
	 * @throws Exceptionstatistics
	 */
	public List<PageData> findByCondition(Page page)throws Exception;
	
	/**
	 * 根据公司查出每个员工报价总额和定金总额
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> statistics(Page page)throws Exception;
	
	
}

