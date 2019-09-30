package com.fh.controller.sercar.dispatching;

import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Dictionaries;
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.util.my.MyTools;
import com.fh.service.sercar.dispatching.DispatchingManager;
import com.fh.service.sercar.dsmx.DsmxManager;
import com.fh.service.sercar.order.OrderManager;
import com.fh.service.sercar.pomx.PomxManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;

/** 
 * 说明：派工单信息
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 */
@Controller
@RequestMapping(value="/dispatching")
public class DispatchingController extends BaseController {
	
	String menuUrl = "dispatching/list.do"; //菜单地址(权限用)
	@Resource(name="dispatchingService")
	private DispatchingManager dispatchingService;
	@Resource(name="dsmxService")
	private DsmxManager dsmxService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="orderService")
	private OrderManager orderService;
	@Resource(name="pomxService")
	private PomxManager pomxService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Dispatching");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("DISPATCHING_ID", this.get32UUID());	//主键
		pd.put("CREATE_BY", Jurisdiction.getUsername());//创建人
		pd.put("CREATE_TIME", DateUtil.getSdftime().format(new Date()));//创建时间
		dispatchingService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Dispatching");
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String dispartchId = pd.getString("DISPATCHING_ID");
		Page page = new Page();
		PageData pd_dsmx = new PageData();
		pd_dsmx.put("DISPATCHING_ID", dispartchId);
		page.setPd(pd_dsmx);
		List<PageData> dsmxs = dsmxService.findByCondition(page);
		if(dsmxs.size()>0){
			for (PageData pageData : dsmxs) {
				dsmxService.delete(pageData);
			}
		}
		dispatchingService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Dispatching");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		dispatchingService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Dispatching");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		String username = Jurisdiction.getUsername();
		
		PageData pd_userinfo = new PageData();
		pd_userinfo.put("USERNAME", username);
		PageData user = userinfoService.findByUsername(pd_userinfo);
		String roletype = user.getString("ROLETYPE");
		String gsId = user.getString("YL1");
		
		PageData pd_dic = new PageData();
		pd_dic.put("BIANMA", "jsleixin");
		PageData jsLeiXin = dictionariesService.findByBianma(pd_dic);
		String dicId = jsLeiXin.getString("DICTIONARIES_ID");
		List<Dictionaries> dictionaries = dictionariesService.listSubDictByParentId(dicId);
		String shuzi = null;
		for (Dictionaries dic : dictionaries) {
			String name_EN = dic.getNAME_EN();
			if(name_EN.equals(gsId)){
				String dictionaries_ID = dic.getDICTIONARIES_ID();
				List<Dictionaries> list = dictionariesService.listSubDictByParentId(dictionaries_ID);
				for (Dictionaries dic2 : list) {
					String name = dic2.getNAME();
					if(name.equals(roletype)){
						shuzi = dic2.getNAME_EN();
						break;
					}
				}
			}
		}
		
		List<PageData> varList = new ArrayList<PageData>();
		if("8".equals(shuzi)){
			pd = this.getPageData();		
			pd.put("BIANMA", "pgdzhuangtai");//获取数据字典的字节码
			PageData findByBianma2 = dictionariesService.findByBianma(pd);
			if(findByBianma2 != null){
				String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
				List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				mv.addObject("list1", varList2);
			}
			
			String keywords = pd.getString("keywords");				//关键词检索条件
			if(null != keywords && !"".equals(keywords)){
				pd.put("keywords", keywords.trim());
			}
			if(null != pd.getString("lastStart") && !"".equals(pd.getString("lastStart"))){
				String lastStart = pd.getString("lastStart")+" 00:00:00";
				pd.remove("lastStart");
				pd.put("lastStart", lastStart);
			}
			if(null != pd.getString("lastEnd") && !"".equals(pd.getString("lastEnd"))){
				String lastEnd = pd.getString("lastEnd")+" 23:59:59";
				pd.remove("lastEnd");
				pd.put("lastEnd", lastEnd);
			}
			if(pd.getString("lastStart")==null && pd.getString("lastEnd")==null && pd.getString("keywords")==null && pd.getString("name")==null){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				pd.put("lastStart", df.format(new Date())+" 00:00:00");
				pd.put("lastEnd", df.format(new Date())+" 23:59:59");				
			}
			Page page1=new Page();
			PageData pd1 = new PageData();
			pd1.put("USERNAME", username);	
			page1.setPd(pd1);
			List<PageData> varList1 = userinfoService.list(page1);
			PageData pageData = varList1.get(0);
			String comid = pageData.getString("YL1");
			pd.put("YL10", comid);
			pd.put("MAINTENANCE_TECHNICIAN", username);
			page.setPd(pd);
			varList = dispatchingService.list(page);	//列出Dispatching列表
			mv.addObject("pd", pd);
		}else{
			pd2 = this.getPageData();		
			pd2.put("BIANMA", "pgdzhuangtai");//获取数据字典的字节码
			PageData findByBianma2 = dictionariesService.findByBianma(pd2);
			if(findByBianma2 != null){
				String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
				List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				mv.addObject("list1", varList2);
			}
			
			String keywords = pd2.getString("keywords");				//关键词检索条件
			if(null != keywords && !"".equals(keywords)){
				pd2.put("keywords", keywords.trim());
			}
			if(null != pd2.getString("lastStart") && !"".equals(pd2.getString("lastStart"))){
				String lastStart = pd2.getString("lastStart")+" 00:00:00";
				pd2.remove("lastStart");
				pd2.put("lastStart", lastStart);
			}
			if(null != pd2.getString("lastEnd") && !"".equals(pd2.getString("lastEnd"))){
				String lastEnd = pd2.getString("lastEnd")+" 23:59:59";
				pd2.remove("lastEnd");
				pd2.put("lastEnd", lastEnd);
			}
			if(pd2.getString("lastStart")==null && pd2.getString("lastEnd")==null && pd2.getString("keywords")==null && pd2.getString("name")==null){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				pd2.put("lastStart", df.format(new Date())+" 00:00:00");
				pd2.put("lastEnd", df.format(new Date())+" 23:59:59");				
			}			
			
			page.setPd(pd2);
			varList = dispatchingService.list(page);	//列出Dispatching列表
			mv.addObject("pd", pd2);
		}
		
		if("7".equals(shuzi)){
			mv.addObject("flag", "fwgw");
		}else if("8".equals(shuzi) ){
			mv.addObject("flag", "js");
		}else {
			mv.addObject("flag", "ld");
		}
		
		
		mv.setViewName("sercar/dispatching/dispatching_list");
		mv.addObject("varList", varList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("BIANMA", "pgdzhuangtai");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList2);
		}
		
		mv.setViewName("sercar/dispatching/dispatching_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Page page = new Page();
		page.setPd(pd);
		List<PageData> dsmxs = dsmxService.findByCondition(page);
		mv.addObject("dsmxList", dsmxs);
		
		mv.setViewName("sercar/dispatching/dispatching_edit");
		mv.addObject("msg", "pomxAdd");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	
	
	/**
	 * 去新增派工明细页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getDispatchingDetails")
	public ModelAndView getDispatchingDetails()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		Page page = new Page();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData> dsmxs = dsmxService.findByCondition(page);
		mv.setViewName("sercar/dispatching/dispatching_details");
		mv.addObject("msg", "addDsmx");
		mv.addObject("dsmxList", dsmxs);
		return mv;
	}
	
	@RequestMapping(value="/delDispatching")
	@ResponseBody
	public String delDispatching() throws Exception{
		PageData pd = new PageData();		
		pd = this.getPageData();
		String id = pd.getString("id");
		
		PageData pd_dsmx = new PageData();
		pd_dsmx.put("DSMX_ID", id);
		PageData dsmx = dsmxService.findById(pd_dsmx);
		if(dsmx != null && !"".equals(dsmx)){
			dsmxService.delete(dsmx);
		}
		return "success";
	}
	
	//前往开工页面
	@RequestMapping(value="/goStartWork")
	@ResponseBody
	public  ModelAndView goStartWork() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = dispatchingService.findById(pd);
		mv.setViewName("sercar/dispatching/dispatching_startwork");
		mv.addObject("pd", pd);
		return mv;
	}
	
	//开工
	@RequestMapping(value="/StartWork")
	@ResponseBody
	public  ModelAndView StartWork() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String yjtime = pd.getString("project");
		pd = dispatchingService.findById(pd);
		
		pd.put("YL8", MyTools.getTime());
		pd.put("EXPECTED_COMPLETION_DATE", yjtime);
		pd.put("STATUS", "待完工");
		dispatchingService.edit(pd);
		
		String orderId = pd.getString("ORDER_ID");
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		order.put("STATUS", "技师已开工待反馈");
		orderService.edit(order);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	//前往项目完工页面goProjectWork
	@RequestMapping(value="/goProjectWork")
	@ResponseBody
	public  ModelAndView goProjectWork() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Page page = new Page();
		page.setPd(pd);
		List<PageData> dsmxList = dsmxService.findByCondition(page);
		mv.addObject("dsmxList", dsmxList);
		mv.setViewName("sercar/dispatching/dispatching_details");
		mv.addObject("msg", "finish");
		return mv;
	}
	
	//项目完工finishProject
	@RequestMapping(value="/finishProject")
	@ResponseBody
	public  ModelAndView finishProject() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData dsmx = dsmxService.findById(pd);
		dsmx.put("YL15", "待检测");
		dsmxService.edit(dsmx);
		
		String dispatchId = dsmx.getString("DISPATCHING_ID");
		Page page_dis = new Page();
		PageData pd_dis = new PageData();
		pd_dis.put("DISPATCHING_ID", dispatchId);
		page_dis.setPd(pd_dis);
		List<PageData> dsmxList = dsmxService.findByCondition(page_dis);
		int count = 0;
		for (PageData pageData : dsmxList) {
			String stust = pageData.getString("YL15");
			if(stust == "待检测"){
				count = count + 1;
			}
		}
		if(count == dsmxList.size()){
			PageData dispartch = dispatchingService.findById(pd_dis);
			dispartch.put("STATUS", "待检测");
			dispatchingService.edit(dispartch);
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	//派工完成paigongWancheng
	@RequestMapping(value="/paigongWancheng")
	@ResponseBody
	public String paigongWancheng() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData dispatch = dispatchingService.findById(pd);
		dispatch.put("STATUS", "待检测");
		dispatchingService.edit(dispatch);
		
		String orderId = dispatch.getString("ORDER_ID");
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		order.put("STATUS", "技师已完工待检查");
		orderService.edit(order);
		
		return "success";
	}
	
	//去项目检测页面goProjectDetection
	@RequestMapping(value="/goProjectDetection")
	@ResponseBody
	public  ModelAndView goProjectDetection() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Page page = new Page();
		page.setPd(pd);
		List<PageData> dsmxList = dsmxService.findByCondition(page);
		mv.addObject("dsmxList", dsmxList);
		mv.setViewName("sercar/dispatching/dispatching_details");
		mv.addObject("msg", "detection");
		return mv;
	}
	
	//检测项目
	@RequestMapping(value="/detectionProject")
	@ResponseBody
	public  ModelAndView detectionProject() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String content = pd.getString("content");
		
		PageData dsmx = dsmxService.findById(pd);
		dsmx.put("YL15", "已检查");
		dsmx.put("YL16", content);
		dsmxService.edit(dsmx);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	//去派工检测页面
	@RequestMapping(value="/goDetectionWork")
	@ResponseBody
	public  ModelAndView goDetectionWork() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData dispatch = dispatchingService.findById(pd);
		mv.addObject("dispatch", dispatch);
		mv.setViewName("sercar/dispatching/dispatching_paigong");
		mv.addObject("msg", "detection");
		return mv;
	}
	
	
	//检测派工
	@RequestMapping(value="/DetectionWork")
	@ResponseBody
	public  ModelAndView DetectionWork() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String remark = pd.getString("remark");
		
		PageData dispatch = dispatchingService.findById(pd);
		
		String orderId = dispatch.getString("ORDER_ID");
		String username = Jurisdiction.getUsername();
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String name = findByUsername.getString("NAME");
		
		dispatch.put("INSPECT", name);
		dispatch.put("INSPECT_REMARK", remark);
		dispatch.put("STATUS", "已检查");
		dispatchingService.edit(dispatch);
		
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		order.put("STATUS", "派工已检查待结算");
		orderService.edit(pd_order);
		
		String creater = order.getString("CREATE_BY");
		sendYuanGong(creater, "有订单待结算");
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	//去零件使用页面goUserLingJian
	@RequestMapping(value="/goUserLingJian")
	@ResponseBody
	public  ModelAndView goUserLingJian() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData dispartch = dispatchingService.findById(pd);
		String orderId = dispartch.getString("ORDER_ID");
		
		Page page_pomx = new Page();
		PageData pd_pomx = new PageData();
		pd_pomx.put("YL2", orderId);
		page_pomx.setPd(pd_pomx);
		List<PageData> pomxList = pomxService.findByCondition(page_pomx);
		
		mv.addObject("pomxList", pomxList);
		mv.setViewName("sercar/dispatching/dispatching_lingjian");
		
		return mv;
	}
	
	//使用零件
	@RequestMapping(value="/useLingJian")
	@ResponseBody
	public String useLingJian() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pomx = pomxService.findById(pd);
		pomx.put("YL15", "已使用");
		pomxService.edit(pomx);
		return "success";
	}
	
	
	//发送消息到员工微信
	public void sendYuanGong(String username,String contant) {
		try {
			PageData pd_dic = new PageData();
			pd_dic.put("BIANMA", "wxpath");
			PageData path = dictionariesService.findByBianma(pd_dic);
			String baseUrl = path.getString("BZ");
			
			if(username != null){
				PageData pd_user = new PageData();
				pd_user.put("USERNAME", username);
				PageData userInfo = userinfoService.findByUsername(pd_user);
				
				String wxid = userInfo.getString("YL7");
				if("开".equals(userInfo.getString("YL8")) && wxid != null){
					String url = baseUrl+"/wx/sendFuhe?openid="+wxid+"&contant="+contant;
					String doHttpGet = Tools.doHttpGet(url);
					System.out.println(doHttpGet);
					System.err.println("发送完成");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	
	/**去工作统计页面
	 * @param
	 * @throws Exception  
	 */
	@RequestMapping(value="/goWork")
	public ModelAndView goWork(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Calendar calendar=Calendar.getInstance();
		//获取年份
		String yearStr = calendar.get(Calendar.YEAR)+"";
		//获得当前时间的月份，月份从0开始所以结果要加1
		int month = calendar.get(Calendar.MONTH) + 1;
		String monthStr = month < 10 ? "0" + month : month + "";
		String startDate = yearStr+"-"+monthStr+"-01 00:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endDate = sdf.format(new Date());
		
		String username = Jurisdiction.getUsername();
		pd.put("USERNAME", username);
		page.setPd(pd);
		List<PageData> list = userinfoService.list(page);
		if(list.size()>0){
			String companyID = list.get(0).get("YL1").toString();
			String flag = "work";
			List<PageData> list2 = findTongji(flag, companyID,startDate,endDate);
			mv.addObject("list1", list2);
		}
		mv.setViewName("sercar/dispatching/dispatching_work");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**工作统计搜索
	 * @param
	 * @throws Exception  
	 */
	@RequestMapping(value="/findWork")
	public ModelAndView findWork(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String lastStart = "";
		String lastEnd = "";
		if(null != pd.getString("lastStart") && !"".equals(pd.getString("lastStart"))){
			lastStart = pd.getString("lastStart")+" 00:00:00";
			
		}
		if(null != pd.getString("lastEnd") && !"".equals(pd.getString("lastEnd"))){
			lastEnd = pd.getString("lastEnd")+" 23:59:59";
			
		}
		
		String username = Jurisdiction.getUsername();
		pd.put("USERNAME", username);
		page.setPd(pd);
		List<PageData> list = userinfoService.list(page);
		if(list.size()>0){
			String companyID = list.get(0).get("YL1").toString();
			String flag = "work";
			List<PageData> list2 = findTongji(flag, companyID,lastStart,lastEnd);
			mv.addObject("list1", list2);
		}
		mv.setViewName("sercar/dispatching/dispatching_work");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}
	
	public List<PageData> findTongji(String flag,String companyID,String startDate,String endDate) throws Exception{
		Page p1 = new Page();
		PageData pd1 = new PageData();
		pd1.put("lastStart", startDate);
		pd1.put("lastEnd", endDate);
		pd1.put("YL10", companyID);
		pd1.put("YL15", "已检查");
		p1.setPd(pd1);
		if(flag.equals("work")){
			List<PageData> list = dsmxService.listWork(p1);
			return list;
		}
		if(flag.equals("output")){
			List<PageData> list = dsmxService.listKind(p1);
			return list;
		}
		return null;
	}
	
	/**去工作统计页面
	 * @param
	 * @throws Exception  
	 */
	@RequestMapping(value="/goOutput")
	public ModelAndView goOutput(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Calendar calendar=Calendar.getInstance();
		//获取年份
		String yearStr = calendar.get(Calendar.YEAR)+"";
		//获得当前时间的月份，月份从0开始所以结果要加1
		int month = calendar.get(Calendar.MONTH) + 1;
		String monthStr = month < 10 ? "0" + month : month + "";
		String startDate = yearStr+"-"+monthStr+"-01 00:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endDate = sdf.format(new Date());

		String username = Jurisdiction.getUsername();
		pd.put("USERNAME", username);
		page.setPd(pd);
		List<PageData> list = userinfoService.list(page);
		if(list.size()>0){
			String companyID = list.get(0).get("YL1").toString();
			String flag = "output";
			List<PageData> list2 = findTongji(flag, companyID,startDate,endDate);
			mv.addObject("list1", list2);
		}
		mv.setViewName("sercar/dispatching/dispatching_output");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**工作统计搜索
	 * @param
	 * @throws Exception  
	 */
	@RequestMapping(value="/findOutput")
	public ModelAndView findOutput(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String lastStart = "";
		String lastEnd = "";
		if(null != pd.getString("lastStart") && !"".equals(pd.getString("lastStart"))){
			lastStart = pd.getString("lastStart")+" 00:00:00";
			
		}
		if(null != pd.getString("lastEnd") && !"".equals(pd.getString("lastEnd"))){
			lastEnd = pd.getString("lastEnd")+" 23:59:59";
			
		}
		
		String username = Jurisdiction.getUsername();
		pd.put("USERNAME", username);
		page.setPd(pd);
		List<PageData> list = userinfoService.list(page);
		if(list.size()>0){
			String companyID = list.get(0).get("YL1").toString();
			String flag = "output";
			List<PageData> list2 = findTongji(flag, companyID,lastStart,lastEnd);
			mv.addObject("list1", list2);
		}
		mv.setViewName("sercar/dispatching/dispatching_output");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
