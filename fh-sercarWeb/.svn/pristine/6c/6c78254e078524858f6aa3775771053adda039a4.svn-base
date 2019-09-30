package com.fh.controller.sercar.chanliang;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.sercar.dispatching.DispatchingManager;
import com.fh.service.sercar.dsmx.DsmxManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;

/** 
 * 说明：产量统计
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 */
@Controller
@RequestMapping(value="/chanliang")
public class ChanliangController extends BaseController {
	
	String menuUrl = "chanliang/list.do"; //菜单地址(权限用)
	@Resource(name="dispatchingService")
	private DispatchingManager dispatchingService;
	@Resource(name="dsmxService")
	private DsmxManager dsmxService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	

	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Calendar calendar=Calendar.getInstance();
		//获取年份
		String yearStr = calendar.get(Calendar.YEAR)+"";
		//获得当前时间的月份，月份从0开始所以结果要加1
		int month = calendar.get(Calendar.MONTH) + 1;
		String monthStr = month < 10 ? "0" + month : month + "";
		
		String startDate ="";
		String endDate = "";
		
		if(null != pd.getString("lastStart") && !"".equals(pd.getString("lastStart"))){
			startDate = pd.getString("lastStart")+" 00:00:00";
		}else {
			startDate = yearStr+"-"+monthStr+"-01 00:00:00";
		}
		if(null != pd.getString("lastEnd") && !"".equals(pd.getString("lastEnd"))){
			endDate = pd.getString("lastEnd")+" 23:59:59";
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			endDate = sdf.format(new Date());
		}

		pd.put("lastStart", startDate);
		pd.put("lastEnd", endDate);
		pd.put("YL15", "已检查");
		
		PageData pd1 = new PageData();
		Page page1 = new Page();
		String username = Jurisdiction.getUsername();
		pd1.put("USERNAME", username);
		page1.setPd(pd1);
		List<PageData> list = userinfoService.list(page1);
		
		if(list.size()>0){
			String companyID = list.get(0).get("YL1").toString();
			pd.put("YL10", companyID);
			page.setPd(pd);
			List<PageData> list1 = dsmxService.listKind(page);
			mv.addObject("list1", list1);
		}
		mv.setViewName("sercar/chanliang/chanliang_list");
		mv.addObject("pd", pd);
		return mv;
	}
	

	@RequestMapping(value="/gochanlaing")
	public ModelAndView gochanlaing(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Calendar calendar=Calendar.getInstance();
		//获取年份
		String yearStr = calendar.get(Calendar.YEAR)+"";
		//获得当前时间的月份，月份从0开始所以结果要加1
		int month = calendar.get(Calendar.MONTH) + 1;
		String monthStr = month < 10 ? "0" + month : month + "";
		
		String startDate ="";
		String endDate = "";
		
		if(null != pd.getString("lastStart") && !"".equals(pd.getString("lastStart"))){
			startDate = pd.getString("lastStart")+" 00:00:00";
		}else {
			startDate = yearStr+"-"+monthStr+"-01 00:00:00";
		}
		if(null != pd.getString("lastEnd") && !"".equals(pd.getString("lastEnd"))){
			endDate = pd.getString("lastEnd")+" 23:59:59";
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			endDate = sdf.format(new Date());
		}
		
		pd.put("lastStart", startDate);
		pd.put("lastEnd", endDate);
		pd.put("YL15", "已检查");
		
		PageData pd1 = new PageData();
		Page page1 = new Page();
		String username = Jurisdiction.getUsername();
		pd1.put("USERNAME", username);
		page1.setPd(pd1);
		List<PageData> list = userinfoService.list(page1);
		if(list.size()>0){
			String companyID = list.get(0).get("YL1").toString();
			pd.put("YL10", companyID);
			page.setPd(pd);
			List<PageData> listByType = dsmxService.listByType(page);
			mv.addObject("list", listByType);
		}
		mv.setViewName("sercar/chanliang/chanliang_details");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Dispatching到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("订单ID");	//1
		titles.add("创建人");	//2
		titles.add("创建时间");	//3
		titles.add("复核人");	//4
		titles.add("复核时间");	//5
		titles.add("维修技师");	//6
		titles.add("完工时间");	//7
		titles.add("预交车日");	//8
		titles.add("总检查员");	//9
		titles.add("总检查备注");	//10
		titles.add("备注");	//11
		titles.add("状态");	//12
		titles.add("预留");	//13
		titles.add("预留");	//14
		dataMap.put("titles", titles);
		List<PageData> varOList = dispatchingService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("ORDER_ID"));	    //1
			vpd.put("var2", varOList.get(i).getString("CREATE_BY"));	    //2
			vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	    //3
			vpd.put("var4", varOList.get(i).getString("CHECKED_BY"));	    //4
			vpd.put("var5", varOList.get(i).getString("CEHCKED_TIME"));	    //5
			vpd.put("var6", varOList.get(i).getString("MAINTENANCE_TECHNICIAN"));	    //6
			vpd.put("var7", varOList.get(i).getString("COMPLETION_TIME"));	    //7
			vpd.put("var8", varOList.get(i).getString("EXPECTED_COMPLETION_DATE"));	    //8
			vpd.put("var9", varOList.get(i).getString("INSPECT"));	    //9
			vpd.put("var10", varOList.get(i).getString("INSPECT_REMARK"));	    //10
			vpd.put("var11", varOList.get(i).getString("REMARK"));	    //11
			vpd.put("var12", varOList.get(i).getString("STATUS"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL1"));	    //13
			vpd.put("var14", varOList.get(i).getString("YL2"));	    //14
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
