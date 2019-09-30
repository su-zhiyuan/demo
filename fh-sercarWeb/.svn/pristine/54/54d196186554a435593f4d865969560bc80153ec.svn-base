package com.fh.controller.sercar.outside;

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
import com.fh.service.sercar.outside.OutsideManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.user.UserManager;
import com.fh.util.AppUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;

/** 
 * 说明：外出
 * 创建人：FH Q313596790
 * 创建时间：2018-06-19
 */
@Controller
@RequestMapping(value="/outside")
public class OutsideController extends BaseController {
	
	String menuUrl = "outside/list.do"; //菜单地址(权限用)
	@Resource(name="outsideService")
	private OutsideManager outsideService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="userService")
	private UserManager userService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Outside");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		String user = Jurisdiction.getUsername();
		PageData pd2 = new PageData();
		pd2.put("USERNAME", user);
		PageData findByUsername = userService.findByUsername(pd2);
		String gsid = findByUsername.getString("BZ");
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("OUTSIDE_ID", this.get32UUID());	//主键
		pd.put("YL10", gsid);
		outsideService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Outside");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		outsideService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Outside");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		outsideService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Outside");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = Jurisdiction.getUsername();
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
		List<PageData>	varList = outsideService.list(page);	//列出Outside列表
		mv.setViewName("sercar/outside/outside_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
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
		mv.setViewName("sercar/outside/outside_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	//获取当前月份请假情况
		@RequestMapping(value="/tongji")
		public ModelAndView tongji(Page page) throws Exception{
			logBefore(logger, Jurisdiction.getUsername()+"列表Leave");
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();

			Calendar calendar=Calendar.getInstance();
			//获得当前时间的月份，月份从0开始所以结果要加1
			int month=calendar.get(Calendar.MONTH)+1;
			String yue="";
			if(month<10){
				yue="0"+month;
			}else{
				yue=month+"";
			}
			//获取公司id
			String username = Jurisdiction.getUsername();
			Page page3=new Page();
			PageData pd3 = new PageData();
			pd3.put("USERNAME", username);	
			page3.setPd(pd3);
			List<PageData> varList1 = userinfoService.list(page3);
			PageData pd4 = varList1.get(0);
			String comid = pd4.getString("YL1");
			pd.put("YL10", comid);
			pd.put("YL5", yue);
			pd.put("YL1", "已审批");
			page.setPd(pd);
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			List<PageData>	varList = outsideService.tongji(page);	//列出Leave列表
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
				pd1.put("YL10", comid);
				page.setPd(pd1);
				List<PageData> outlist = outsideService.list(page);
				int outcount = 0;
				for (PageData pageData2 : outlist) {
					String waichushijian = pageData2.getString("yl6");
					int shijian = Integer.parseInt(waichushijian);
					outcount+=shijian;
				}
				map.put("xingming", name);
				map.put("cishu", outlist.size()+"");
				map.put("shijian", String.valueOf(outcount));
				list.add(map);
			}
			mv.addObject("varlist2", list);
			mv.addObject("pd",pd);
			mv.setViewName("sercar/outside/outside_tongji");
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
			
			//获取公司id
			String username = Jurisdiction.getUsername();
			Page page3=new Page();
			PageData pd3 = new PageData();
			pd3.put("USERNAME", username);	
			page3.setPd(pd3);
			List<PageData> varList1 = userinfoService.list(page3);
			PageData pd4 = varList1.get(0);
			String comid = pd4.getString("YL1");
			pd.put("YL10", comid);
			pd.put("YL1", "已审批");
			page.setPd(pd);
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			List<PageData>	varList = outsideService.tongji(page);	//列出Leave列表
			for(int j=0;j<varList.size();j++){
				String sPeople = varList.get(j).getString("YL3");
				PageData pd2 = new PageData();
				pd2 = this.getPageData();
				pd2.put("USERNAME", sPeople);//根据用户名找出其姓名
				page.setPd(pd2);
				List<PageData> userinfo = userinfoService.list(page);
				PageData pageData = userinfo.get(0);
				String name = pageData.getString("NAME");
				
				Map<String, String> map = new HashMap<String,String>();
				PageData pd1 = new PageData();
				pd1 = this.getPageData();
				pd1.put("YL3", sPeople);
				pd1.put("YL1", "已审批");
				page.setPd(pd1);
				List<PageData> outlist = outsideService.list(page);
				int outcount = 0;
				for (PageData pageData2 : outlist) {
					String waichushijian = pageData2.getString("yl6");
					int shijian = Integer.parseInt(waichushijian);
					outcount+=shijian;
				}
				map.put("xingming", name);
				map.put("cishu", outlist.size()+"");
				map.put("shijian", String.valueOf(outcount));
				list.add(map);
			}
			mv.addObject("pd",pd);
			mv.addObject("varlist2", list);
			mv.setViewName("sercar/outside/outside_tongji");
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
			List<PageData> list2 = outsideService.list(page);
			mv.addObject("varList", list2);
			mv.setViewName("sercar/outside/outside_geren");
			mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
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
		pd = outsideService.findById(pd);	//根据ID读取
		mv.setViewName("sercar/outside/outside_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Outside");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			outsideService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Outside到excel");
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
		dataMap.put("titles", titles);
		List<PageData> varOList = outsideService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("STARTTIME"));	    //1
			vpd.put("var2", varOList.get(i).getString("ENDTIME"));	    //2
			vpd.put("var3", varOList.get(i).getString("CAUSE"));	    //3
			vpd.put("var4", varOList.get(i).getString("REMARK"));	    //4
			vpd.put("var5", varOList.get(i).getString("YL1"));	    //5
			vpd.put("var6", varOList.get(i).getString("YL2"));	    //6
			vpd.put("var7", varOList.get(i).getString("YL3"));	    //7
			vpd.put("var8", varOList.get(i).getString("YL4"));	    //8
			vpd.put("var9", varOList.get(i).getString("YL5"));	    //9
			vpd.put("var10", varOList.get(i).getString("YL6"));	    //10
			vpd.put("var11", varOList.get(i).getString("YL7"));	    //11
			vpd.put("var12", varOList.get(i).getString("YL8"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL9"));	    //13
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
