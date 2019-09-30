package com.fh.service.sercar.cardclock;

import java.util.List;
import com.fh.entity.Page;
import com.fh.util.PageData;

/** 
 * 说明： 考勤打卡接口
 * 创建人：FH Q313596790
 * 创建时间：2018-09-06
 * @version
 */
public interface CardclockManager{

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
	
	
	/**查出每月所有打卡人
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> dakapeople(Page page)throws Exception;
	
	
	/**每月所有人的打卡统计列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> listdaka(Page page)throws Exception;
	
	/**每月所有人的打卡异常统计
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> yichangtj(Page page)throws Exception;
	
	
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
	
}

