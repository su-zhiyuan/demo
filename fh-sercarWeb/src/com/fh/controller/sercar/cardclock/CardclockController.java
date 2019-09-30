package com.fh.controller.sercar.cardclock;

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
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.service.sercar.cardclock.CardclockManager;
import com.fh.service.sercar.userinfo.UserInfoManager;

/** 
 * 说明：考勤打卡
 * 创建人：FH Q313596790
 * 创建时间：2018-09-06
 */
@Controller
@RequestMapping(value="/cardclock")
public class CardclockController extends BaseController {
	
	String menuUrl = "cardclock/list.do"; //菜单地址(权限用)
	@Resource(name="cardclockService")
	private CardclockManager cardclockService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Cardclock");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("CARDCLOCK_ID", this.get32UUID());	//主键
		cardclockService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Cardclock");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		cardclockService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Cardclock");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		cardclockService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Cardclock");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		String username = Jurisdiction.getUsername();
		List<PageData>	varList =null;
		if(!"admin".equals(username)){
			pd = this.getPageData();
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
			if(pd.getString("lastStart")==null && pd.getString("lastEnd")==null 
					&& pd.getString("keywords")==null && pd.getString("YL2")==null){
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
			pd.put("YL5", comid);
			page.setPd(pd);
			varList = cardclockService.list(page);	//列出Cardclock列表
			mv.addObject("pd", pd);
		}else{
			pd2 = this.getPageData();
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
			if(pd2.getString("lastStart")==null && pd2.getString("lastEnd")==null 
					&& pd2.getString("keywords")==null && pd2.getString("YL2")==null){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				pd2.put("lastStart", df.format(new Date())+" 00:00:00");
				pd2.put("lastEnd", df.format(new Date())+" 23:59:59");
			}
			page.setPd(pd2);
			varList = cardclockService.list(page);	//列出Cardclock列表
			mv.addObject("pd", pd2);
		}
		
		mv.setViewName("sercar/cardclock/cardclock_list");
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
		mv.setViewName("sercar/cardclock/cardclock_edit");
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
		pd = cardclockService.findById(pd);	//根据ID读取
		mv.setViewName("sercar/cardclock/cardclock_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Cardclock");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			cardclockService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Cardclock到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("日期");	//1
		titles.add("打卡时间");	//2
		titles.add("用户名");	//3
		titles.add("上班打卡定位");	//4
		titles.add("下班打卡定位");	//5
		titles.add("备注");	//6
		titles.add("打卡状态");	//7
		titles.add("状态");	//8
		titles.add("备注9");	//9
		titles.add("备注10");	//10
		titles.add("备注11");	//11
		titles.add("备注12");	//12
		titles.add("备注13");	//13
		titles.add("备注14");	//14
		titles.add("备注15");	//15
		dataMap.put("titles", titles);
		List<PageData> varOList = cardclockService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("DATE"));	    //1
			vpd.put("var2", varOList.get(i).getString("UPDATETIME"));	    //2
			vpd.put("var3", varOList.get(i).getString("DOWNTIME"));	    //3
			vpd.put("var4", varOList.get(i).getString("UPPLACE"));	    //4
			vpd.put("var5", varOList.get(i).getString("DOWNPLACE"));	    //5
			vpd.put("var6", varOList.get(i).getString("REMARK"));	    //6
			vpd.put("var7", varOList.get(i).getString("YL1"));	    //7
			vpd.put("var8", varOList.get(i).getString("YL2"));	    //8
			vpd.put("var9", varOList.get(i).getString("YL3"));	    //9
			vpd.put("var10", varOList.get(i).getString("YL4"));	    //10
			vpd.put("var11", varOList.get(i).getString("YL5"));	    //11
			vpd.put("var12", varOList.get(i).getString("YL6"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL7"));	    //13
			vpd.put("var14", varOList.get(i).getString("YL8"));	    //14
			vpd.put("var15", varOList.get(i).getString("YL9"));	    //15
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	/**去统计页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/gotongji")
	public ModelAndView gotongji(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Calendar calendar=Calendar.getInstance();
		//获得当前时间的月份，月份从0开始所以结果要加1
		int month=calendar.get(Calendar.MONTH)+1;
		String yue="";
		if(month<10){
			yue="0"+month;
		}else{
			yue=month+"";
		}
		Page page1=new Page();
		PageData p = new PageData();
		p.put("USERNAME", Jurisdiction.getUsername());	
		page1.setPd(p);
		List<PageData> varList1 = userinfoService.list(page1);
		PageData pag = varList1.get(0);
		String comid = pag.getString("YL1");
		pd.put("YL5", comid);
		pd.put("YL6", yue);
		page.setPd(pd);
		List<PageData>	dkpeoplelist = cardclockService.dakapeople(page);		
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for(int j=0;j<dkpeoplelist.size();j++){
			String sPeople = dkpeoplelist.get(j).getString("DOWNTIME");
			
			Page page2=new Page();
			PageData pd3 = new PageData();
			pd3.put("DOWNTIME", sPeople);
			pd3.put("YL6", yue);
			pd3.put("YL7", "异常打卡");
			page2.setPd(pd3);
			List<PageData> yichangtj = cardclockService.yichangtj(page2);
			String yccont = yichangtj.get(0).get("yc").toString();
			
			PageData pd2 = new PageData();
			pd2 = this.getPageData();
			pd2.put("USERNAME", sPeople);//根据用户名找出其姓名
			page.setPd(pd2);
			List<PageData> userinfo = userinfoService.list(page);
			PageData pageData = userinfo.get(0);
			String string = pageData.getString("NAME");
			
			Map<String, String> map = new HashMap<String,String>();
			PageData pd1 = new PageData();
			pd1 = this.getPageData();
			pd1.put("DOWNTIME", sPeople);
			pd1.put("YL6",yue);
			page.setPd(pd1);
			List<PageData>	dklist = cardclockService.listdaka(page);		
			PageData data = dklist.get(0);
			String chuqincount = data.get("statistics").toString();
			PageData data1 = dklist.get(1);
			String chidaocount = data1.get("statistics").toString();
			PageData data2 = dklist.get(2);
			String zaotuicount = data2.get("statistics").toString();
			map.put("chuqin", chuqincount);
			map.put("chidao", chidaocount);
			map.put("zaotui", zaotuicount);
			map.put("name", string);
			map.put("yichang", yccont);
			list.add(map);
		}
		mv.addObject("dktongjilist", list);
		mv.addObject("pd", pd);
		mv.setViewName("sercar/cardclock/dakatongji");
		return mv;
	}
	
	@RequestMapping(value="/yuetongji")
	public ModelAndView yuetongji(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String yue = pd.getString("YL6");
		
		Page page1=new Page();
		PageData p = new PageData();
		p.put("USERNAME", Jurisdiction.getUsername());	
		page1.setPd(p);
		List<PageData> varList1 = userinfoService.list(page1);
		PageData pag = varList1.get(0);
		String comid = pag.getString("YL1");
		pd.put("YL5", comid);
		
		page.setPd(pd);
		List<PageData>	dkpeoplelist = cardclockService.dakapeople(page);
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for(int j=0;j<dkpeoplelist.size();j++){
			String sPeople = dkpeoplelist.get(j).getString("DOWNTIME");
			
			Page page2=new Page();
			PageData pd3 = new PageData();
			pd3.put("DOWNTIME", sPeople);
			pd3.put("YL6", yue);
			pd3.put("YL7", "异常打卡");
			page2.setPd(pd3);
			List<PageData> yichangtj = cardclockService.yichangtj(page2);
			String yccont = yichangtj.get(0).get("yc").toString();
			
			PageData pd2 = new PageData();
			pd2 = this.getPageData();
			pd2.put("USERNAME", sPeople);//根据用户名找出其姓名
			page.setPd(pd2);
			List<PageData> userinfo = userinfoService.list(page);
			PageData pageData = userinfo.get(0);
			String string = pageData.getString("NAME");
			
			Map<String, String> map = new HashMap<String,String>();
			PageData pd1 = new PageData();
			pd1 = this.getPageData();
			pd1.put("DOWNTIME", sPeople);
			page.setPd(pd1);
			List<PageData>	dklist = cardclockService.listdaka(page);		
			PageData data = dklist.get(0);
			String chuqincount = data.get("statistics").toString();
			PageData data1 = dklist.get(1);
			String chidaocount = data1.get("statistics").toString();
			PageData data2 = dklist.get(2);
			String zaotuicount = data2.get("statistics").toString();
			map.put("chuqin", chuqincount);
			map.put("chidao", chidaocount);
			map.put("zaotui", zaotuicount);
			map.put("name", string);
			map.put("yichang", yccont);
			list.add(map);
		}
		mv.addObject("dktongjilist", list);
		mv.addObject("pd", pd);
		mv.setViewName("sercar/cardclock/dakatongji");
		return mv;
	}
	
	//查看此人当月打卡详情
			@RequestMapping(value="/detaillist")
			public ModelAndView detaillist(Page page) throws Exception{
				ModelAndView mv = this.getModelAndView();
				PageData pd = new PageData();
				pd = this.getPageData();
				String yue = pd.getString("YL6");	
				String name = pd.getString("Name");
				pd.put("NAME",name);
				page.setPd(pd);
				List<PageData> list = userinfoService.list(page);
				String username = list.get(0).getString("USERNAME");
				PageData pd1 = new PageData();
				pd1.put("YL6",yue);
				pd1.put("DOWNTIME", username);
				page.setPd(pd1);
				List<PageData>	varList = cardclockService.list(page);
				mv.setViewName("sercar/cardclock/detail");
				mv.addObject("varList", varList);
				return mv;
			}
		

	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
