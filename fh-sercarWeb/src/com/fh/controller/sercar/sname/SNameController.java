package com.fh.controller.sercar.sname;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.DailyTimeIntervalScheduleBuilder;
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
import com.fh.service.sercar.companyinfo.impl.CompanyInfoService;
import com.fh.service.sercar.sname.SNameManager;
import com.fh.service.sercar.stype.STypeManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.user.UserManager;

/** 
 * 说明：项目名称
 * 创建人：FH Q313596790
 * 创建时间：2018-05-07
 */
@Controller
@RequestMapping(value="/sname")
public class SNameController extends BaseController {
	
	String menuUrl = "sname/list.do"; //菜单地址(权限用)
	@Resource(name="snameService")
	private SNameManager snameService;
	@Resource(name="companyinfoService")
	private CompanyInfoService companyInfoService;
	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="stypeService")
	private STypeManager stypeService;
	
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增SName");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		String user = Jurisdiction.getUsername();
		PageData pd2 = new PageData();
		pd2.put("USERNAME", user);
		PageData findByUsername = userService.findByUsername(pd2);
		String gsid = findByUsername.getString("BZ");
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SNAME_ID", this.get32UUID());	//主键
		pd.put("YL10", gsid);
		snameService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除SName");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		snameService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改SName");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		snameService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表SName");
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
			Page page1=new Page();
			PageData pd1 = new PageData();
			pd1.put("USERNAME", username);	
			page1.setPd(pd1);
			List<PageData> varList1 = userinfoService.list(page1);
			PageData pageData = varList1.get(0);
			String comid = pageData.getString("YL1");
			pd.put("YL10", comid);
			page.setPd(pd);
			varList = snameService.list(page);	//列出SName列表
			mv.addObject("pd", pd);
		}else{
			pd2 = this.getPageData();
			String keywords = pd2.getString("keywords");				//关键词检索条件
			if(null != keywords && !"".equals(keywords)){	
				pd2.put("keywords", keywords.trim());
			}
			page.setPd(pd2);
			varList = snameService.list(page);	//列出SName列表
			mv.addObject("pd", pd2);
		}
		
		mv.setViewName("sercar/sname/sname_list");
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
		
		PageData pd_user = new PageData();
		String username = Jurisdiction.getUsername();
		pd_user.put("USERNAME", username);
		PageData user = userinfoService.findByUsername(pd_user);
		String gsId = user.getString("YL1");
		
		Page page = new Page();
		PageData pd = new PageData();
		pd.put("COMPANY_ID", gsId);
		page.setPd(pd);
		List<PageData> list = stypeService.findByCondition(page);
		
		mv.setViewName("sercar/sname/sname_edit");
		mv.addObject("msg", "save");
		mv.addObject("list", list);
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
		pd = snameService.findById(pd);	//根据ID读取
		
		String gsId = pd.getString("YL10");
		Page page = new Page();
		PageData pd1 = new PageData();
		pd1.put("COMPANY_ID", gsId);
		page.setPd(pd1);
		List<PageData> list = stypeService.findByCondition(page);
		
		mv.setViewName("sercar/sname/sname_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.addObject("list", list);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除SName");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			snameService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出SName到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("上级ID");	//1
		titles.add("项目名称");	//2
		titles.add("备注");	//3
		titles.add("预留");	//4
		titles.add("预留");	//5
		titles.add("预留");	//6
		dataMap.put("titles", titles);
		List<PageData> varOList = snameService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("PID"));	    //1
			vpd.put("var2", varOList.get(i).getString("S_NAME"));	    //2
			vpd.put("var3", varOList.get(i).getString("REMARK"));	    //3
			vpd.put("var4", varOList.get(i).getString("YL1"));	    //4
			vpd.put("var5", varOList.get(i).getString("YL2"));	    //5
			vpd.put("var6", varOList.get(i).getString("YL3"));	    //6
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
