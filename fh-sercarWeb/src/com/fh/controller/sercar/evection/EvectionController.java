package com.fh.controller.sercar.evection;

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
import com.fh.util.my.MyTools;
import com.fh.service.sercar.evection.EvectionManager;
import com.fh.service.sercar.userinfo.UserInfoManager;

/** 
 * 说明：出差
 * 创建人：FH Q313596790
 * 创建时间：2018-06-19
 */
@Controller
@RequestMapping(value="/evection")
public class EvectionController extends BaseController {
	
	String menuUrl = "evection/list.do"; //菜单地址(权限用)
	@Resource(name="evectionService")
	private EvectionManager evectionService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Evection");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("EVECTION_ID", this.get32UUID());	//主键
		evectionService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Evection");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		evectionService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Evection");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		evectionService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Evection");
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
					&& pd.getString("keywords")==null && pd.getString("YL1")==null){
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
			varList = evectionService.list(page);	//列出Evection列表
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
					&& pd2.getString("keywords")==null && pd2.getString("YL1")==null){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				pd2.put("lastStart", df.format(new Date())+" 00:00:00");
				pd2.put("lastEnd", df.format(new Date())+" 23:59:59");
			}
			page.setPd(pd2);
			varList = evectionService.list(page);	//列出Evection列表
			mv.addObject("pd", pd2);
		}
		
		mv.setViewName("sercar/evection/evection_list");
		mv.addObject("varList", varList);
		
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	//获取当前月份请假情况
		@RequestMapping(value="/tongji")
		public ModelAndView tongji(Page page) throws Exception{
			logBefore(logger, Jurisdiction.getUsername()+"列表Leave");
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
			pd.put("YL10", comid);
			pd.put("YL5", yue);
			pd.put("YL1", "已审批");
			page.setPd(pd);
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			List<PageData>	varList = evectionService.tongji(page);	//列出Leave列表
			for(int j=0;j<varList.size();j++){
				String sPeople = varList.get(j).getString("YL3");
				PageData pd2 = new PageData();
				pd2.put("USERNAME", sPeople);//根据用户名找出其姓名
				page.setPd(pd2);
				List<PageData> userinfo = userinfoService.list(page);
				PageData pageData = userinfo.get(0);
				String name = pageData.getString("NAME");
				
				Map<String, String> map = new HashMap<String,String>();
				PageData pd1 = new PageData();
				pd1.put("YL3", sPeople);
				pd1.put("YL5",yue);
				pd1.put("YL1", "已审批");
				page.setPd(pd1);
				int count = 0;
				List<PageData> evelist = evectionService.list(page);
				for(int i=0;i<evelist.size();i++){
					int k = Integer.parseInt(evelist.get(i).getString("days"));
					count+=k;
				}
				map.put("xingming", name);
				map.put("cishu", String.valueOf(evelist.size()));
				map.put("shijian", count+"");
				list.add(map);
			}
			mv.addObject("pd",pd);
			mv.addObject("varlist2", list);
			mv.setViewName("sercar/evection/evection_tongji");
			mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
			return mv;
		}
		
		//获取任意月份请假情况
		@RequestMapping(value="/yuefen")
		public ModelAndView yuefen(Page page) throws Exception{
			logBefore(logger, Jurisdiction.getUsername()+"列表Leave");
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();

			Page page1=new Page();
			PageData p = new PageData();
			p.put("USERNAME", Jurisdiction.getUsername());	
			page1.setPd(p);
			List<PageData> varList1 = userinfoService.list(page1);
			PageData pag = varList1.get(0);
			String comid = pag.getString("YL1");
			pd.put("YL10", comid);
			
			pd.put("YL1", "已审批");
			page.setPd(pd);
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			List<PageData>	varList = evectionService.tongji(page);	//列出Leave列表
			for(int j=0;j<varList.size();j++){
				String sPeople = varList.get(j).getString("YL3");
				PageData pd2 = new PageData();
				pd2.put("USERNAME", sPeople);//根据用户名找出其姓名
				page.setPd(pd2);
				List<PageData> userinfo = userinfoService.list(page);
				PageData pageData = userinfo.get(0);
				String name = pageData.getString("NAME");
				
				Map<String, String> map = new HashMap<String,String>();
				PageData pd1 = new PageData();
				pd1= this.getPageData();
				pd1.put("YL3", sPeople);
				pd1.put("YL1", "已审批");
				page.setPd(pd1);
				int count = 0;
				List<PageData> evelist = evectionService.list(page);
				for(int i=0;i<evelist.size();i++){
					int k = Integer.parseInt(evelist.get(i).getString("days"));
					count+=k;
				}
				map.put("xingming", name);
				map.put("cishu", String.valueOf(evelist.size()));
				map.put("shijian", count+"");
				list.add(map);
			}
			mv.addObject("pd",pd);
			mv.addObject("varlist2", list);
			mv.setViewName("sercar/evection/evection_tongji");
			mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
			return mv;
		}
		
		//获取个人当月所有情况
		@RequestMapping(value="/geren")
		public	ModelAndView geren(Page page) throws Exception{
			logBefore(logger, Jurisdiction.getUsername()+"列表Leave");
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			String name = pd.getString("NAME");
			String yue = pd.getString("YL5");
			pd.put("NAME", name);
			page.setPd(pd);
			List<PageData> list = userinfoService.list(page);
			String username = list.get(0).getString("USERNAME");
			PageData pd1 = new PageData();
			pd1.put("YL3", username);
			pd1.put("YL1", "已审批");
			pd1.put("YL5", yue);
			page.setPd(pd1);
			List<PageData> list2 = evectionService.list(page);
			mv.addObject("varList", list2);
			mv.setViewName("sercar/evection/evection_geren");
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
		mv.setViewName("sercar/evection/evection_edit");
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
		pd = evectionService.findById(pd);	//根据ID读取
		mv.setViewName("sercar/evection/evection_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Evection");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			evectionService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Evection到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("备注2");	//1
		titles.add("备注3");	//2
		titles.add("备注4");	//3
		titles.add("备注5");	//4
		titles.add("备注6");	//5
		titles.add("备注7");	//6
		titles.add("备注8");	//7
		titles.add("备注9");	//8
		titles.add("备注10");	//9
		titles.add("备注11");	//10
		titles.add("备注12");	//11
		titles.add("备注13");	//12
		titles.add("备注14");	//13
		titles.add("备注15");	//14
		titles.add("备注16");	//15
		titles.add("备注17");	//16
		titles.add("备注18");	//17
		titles.add("备注19");	//18
		titles.add("备注20");	//19
		dataMap.put("titles", titles);
		List<PageData> varOList = evectionService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("CAUSE"));	    //1
			vpd.put("var2", varOList.get(i).getString("JTGJ"));	    //2
			vpd.put("var3", varOList.get(i).getString("WAY"));	    //3
			vpd.put("var4", varOList.get(i).getString("CITYCF"));	    //4
			vpd.put("var5", varOList.get(i).getString("CITYDD"));	    //5
			vpd.put("var6", varOList.get(i).getString("STARTTIME"));	    //6
			vpd.put("var7", varOList.get(i).getString("ENDTIME"));	    //7
			vpd.put("var8", varOList.get(i).getString("DAYS"));	    //8
			vpd.put("var9", varOList.get(i).getString("TXR"));	    //9
			vpd.put("var10", varOList.get(i).getString("REMARK"));	    //10
			vpd.put("var11", varOList.get(i).getString("YL1"));	    //11
			vpd.put("var12", varOList.get(i).getString("YL2"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL3"));	    //13
			vpd.put("var14", varOList.get(i).getString("YL4"));	    //14
			vpd.put("var15", varOList.get(i).getString("YL5"));	    //15
			vpd.put("var16", varOList.get(i).getString("YL6"));	    //16
			vpd.put("var17", varOList.get(i).getString("YL7"));	    //17
			vpd.put("var18", varOList.get(i).getString("YL8"));	    //18
			vpd.put("var19", varOList.get(i).getString("YL9"));	    //19
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
