package com.fh.controller.sercar.settlement;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils.Null;
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
import com.fh.service.sercar.dsmx.DsmxManager;
import com.fh.service.sercar.order.OrderManager;
import com.fh.service.sercar.pomx.PomxManager;
import com.fh.service.sercar.settlement.SettlementManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;

/** 
 * 说明：结算单
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 */
@Controller
@RequestMapping(value="/settlement")
public class SettlementController extends BaseController {
	
	String menuUrl = "settlement/list.do"; //菜单地址(权限用)
	@Resource(name="settlementService")
	private SettlementManager settlementService;
	@Resource(name="dsmxService")
	private DsmxManager dsmxService;
	@Resource(name="pomxService")
	private PomxManager pomxService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="orderService")
	private OrderManager orderService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Settlement");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SETTLEMENT_ID", this.get32UUID());	//主键
		pd.put("CREATE_BY", Jurisdiction.getUsername());//创建人
		pd.put("CREATE_TIME", DateUtil.getSdftime().format(new Date()));//创建时间
		settlementService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Settlement");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData settle = settlementService.findById(pd);
		
		String orderId = settle.getString("ORDER_ID");
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		order.put("STATUS", "派工已检查待结算");
		orderService.edit(order);
		
		settlementService.delete(settle);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Settlement");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		settlementService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Settlement");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		String username = Jurisdiction.getUsername();
		List<PageData>	varList =null;
		if(!"admin".equals(username)){
			pd = this.getPageData();
			pd.put("BIANMA", "jsdzhuangtai");//获取数据字典的字节码
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
			page.setPd(pd);
			varList = settlementService.list(page);	//列出Settlement列表
			mv.addObject("pd", pd);
		}else{
			pd2 = this.getPageData();
			pd2.put("BIANMA", "jsdzhuangtai");//获取数据字典的字节码
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
			varList = settlementService.list(page);	//列出Settlement列表
			mv.addObject("pd", pd2);
		}
		
		mv.setViewName("sercar/settlement/settlement_list");
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
		pd.put("BIANMA", "jsdzhuangtai");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList2);
		}
		mv.setViewName("sercar/settlement/settlement_edit");
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
		pd.put("BIANMA", "jsdzhuangtai");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList2);
		}
		pd = settlementService.findById(pd);	//根据ID读取
		mv.setViewName("sercar/settlement/settlement_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	//结算明细页面
	@RequestMapping(value="/goSettlementMx")
	public ModelAndView goSettlementMx()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData sett = settlementService.findById(pd);
		
		String dsmxStr = sett.getString("YL3");
		String pomxStr = sett.getString("YL4");
		
		List<PageData> dsmxList = new ArrayList<PageData>();
		if(dsmxStr != null && !"".equals(dsmxStr)){
			PageData pd_dsmx = new PageData();
			if(dsmxStr.contains("-")){
				String[] split = dsmxStr.split("-");
				for (String dsmxId : split) {
					if(dsmxId != null && !"".equals(dsmxId)){
						pd_dsmx.put("DSMX_ID", dsmxId);
						PageData dsmx = dsmxService.findById(pd_dsmx);
						dsmxList.add(dsmx);
					}
				}
			}else{
				pd_dsmx.put("DSMX_ID", dsmxStr);
				PageData dsmx = dsmxService.findById(pd_dsmx);
				dsmxList.add(dsmx);
			}
		}
		mv.addObject("dsmxList", dsmxList);
		
		List<PageData> pomxList = new ArrayList<PageData>();
		if(pomxStr != null && !"".equals(pomxStr)){
			PageData pd_pomx = new PageData();
			if(pomxStr.contains("-")){
				String[] split = pomxStr.split("-");
				for (String pomxId : split) {
					if(pomxId != null && !"".equals(pomxId)){
						pd_pomx.put("POMX_ID", pomxId);
						PageData pomx = pomxService.findById(pd_pomx);
						pomxList.add(pomx);
					}
				}
			}else{
				pd_pomx.put("POMX_ID", pomxStr);
				PageData pomx = pomxService.findById(pd_pomx);
				pomxList.add(pomx);
			}
		}
		mv.addObject("pomxList", pomxList);
		
		mv.setViewName("sercar/settlement/settlement_mx");
		mv.addObject("pd", sett);
		mv.addObject("msg", "settlementMxEdit");
		return mv;
	}
	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Settlement");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			settlementService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Settlement到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("订单ID");	//1
		titles.add("创建人");	//2
		titles.add("创建时间");	//3
		titles.add("合计");	//4
		titles.add("结算备注");	//5
		titles.add("备注");	//6
		titles.add("结算时间");	//7
		titles.add("状态");	//8
		titles.add("预留");	//9
		titles.add("预留");	//10
		dataMap.put("titles", titles);
		List<PageData> varOList = settlementService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("ORDER_ID"));	    //1
			vpd.put("var2", varOList.get(i).getString("CREATE_BY"));	    //2
			vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	    //3
			vpd.put("var4", varOList.get(i).getString("TOTAL"));	    //4
			vpd.put("var5", varOList.get(i).getString("SREMARK"));	    //5
			vpd.put("var6", varOList.get(i).getString("REMARK"));	    //6
			vpd.put("var7", varOList.get(i).getString("S_TIME"));	    //7
			vpd.put("var8", varOList.get(i).getString("STATUS"));	    //8
			vpd.put("var9", varOList.get(i).getString("YL1"));	    //9
			vpd.put("var10", varOList.get(i).getString("YL2"));	    //10
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	//结算中服务顾问确认客户付款paySettlement
	@RequestMapping(value="/paySettlement")
	@ResponseBody
	public String paySettlement() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData order = orderService.findById(pd);
		
		String orderId = order.getString("ORDER_ID");
		Page page_sett = new Page();
		PageData pd_sett = new PageData();
		pd_sett.put("ORDER_ID", orderId);
		page_sett.setPd(pd_sett);
		List<PageData> list = settlementService.findByCondition(page_sett);
		if(list.size()>0){
			PageData settle = list.get(0);
			settle.put("STATUS", "待确认");
			settlementService.edit(settle);
			order.put("STATUS", "客户已付款待财务确认");
			orderService.edit(order);
		}
		
		return "success";
	}
	
	
	//结算确认列表
	@RequestMapping(value="/affirm")
	public ModelAndView affirm(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Settlement");
		ModelAndView mv = this.getModelAndView();
		
		String username = Jurisdiction.getUsername();
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd.put("YL10", gsid);
		page.setPd(pd);
		List<PageData> settlementList = settlementService.findByCondition(page);
		
		mv.setViewName("sercar/settlement/settlement_affirm");
		mv.addObject("varList",settlementList);	//按钮权限
		return mv;
	}
	
	//结算中服务顾问确认客户付款paySettlement
	@RequestMapping(value="/affirmSettlement")
	@ResponseBody
	public String affirmSettlement() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData settle = settlementService.findById(pd);
		settle.put("STATUS", "财务已确认");
		settlementService.edit(settle);
		
		String orderId = settle.getString("ORDER_ID");
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		order.put("STATUS", "财务已确认");
		orderService.edit(order);
		
		return "success";
	}
	
	//付款确认列表settlement/pay.do	
	@RequestMapping(value="/pay")
	public ModelAndView pay(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Settlement");
		ModelAndView mv = this.getModelAndView();
		
		String username = Jurisdiction.getUsername();
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd.put("YL10", gsid);
		pd.put("STATUS", "同意放行---未付款");
		page.setPd(pd);
		List<PageData> orderlist = orderService.findByCondition(page);
		
		List<PageData> list = new ArrayList<PageData>();
		Page page_sett = new Page();
		PageData pd_sett = new PageData();
		
		for (PageData pageData : orderlist) {
			String orderId = pageData.getString("ORDER_ID");
			pd_sett.put("ORDER_ID", orderId);
			page_sett.setPd(pd_sett);
			List<PageData> settList = settlementService.findByCondition(page_sett);
			if(settList.size()>0){
				PageData sett = settList.get(0);
				pageData.put("settle", sett);
				list.add(pageData);
			}
		}
		
		mv.setViewName("sercar/settlement/settlement_pay");
		mv.addObject("varList",list);	//按钮权限
		return mv;
	}
	
	//确认付款 
	@RequestMapping(value="/affirmPay")
	@ResponseBody
	public String affirmPay() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData order = orderService.findById(pd);
		
		order.put("STATUS", "同意放行---已付款");
		orderService.edit(order);
		
		return "success";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
