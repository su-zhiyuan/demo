package com.fh.controller.sercar.dsmx;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.fh.service.sercar.dsmx.DsmxManager;
import com.fh.service.sercar.userinfo.UserInfoManager;

/** 
 * 说明：派工明细
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 */
@Controller
@RequestMapping(value="/dsmx")
public class DsmxController extends BaseController {
	
	String menuUrl = "dsmx/list.do"; //菜单地址(权限用)
	@Resource(name="dsmxService")
	private DsmxManager dsmxService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Dsmx");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("DSMX_ID", this.get32UUID());	//主键
		dsmxService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Dsmx");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		dsmxService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Dsmx");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		dsmxService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Dsmx");
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
			Page page1=new Page();
			PageData pd1 = new PageData();
			pd1.put("USERNAME", username);	
			page1.setPd(pd1);
			List<PageData> varList1 = userinfoService.list(page1);
			PageData pageData = varList1.get(0);
			String comid = pageData.getString("YL1");
			pd.put("YL10", comid);
			page.setPd(pd);
			varList = dsmxService.list(page);	//列出Dsmx列表
			mv.addObject("pd", pd);
		}else{
			pd2= this.getPageData();
			String keywords = pd2.getString("keywords");				//关键词检索条件
			if(null != keywords && !"".equals(keywords)){
				pd2.put("keywords", keywords.trim());
			}
			page.setPd(pd2);
			varList = dsmxService.list(page);	//列出Dsmx列表
			mv.addObject("pd", pd2);
		}

		mv.setViewName("sercar/dsmx/dsmx_list");
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
		mv.setViewName("sercar/dsmx/dsmx_edit");
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
		PageData findById = dsmxService.findById(pd);	//根据ID读取
		mv.setViewName("sercar/dsmx/dsmx_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", findById);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Dsmx");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			dsmxService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Dsmx到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("派工单ID");	//1
		titles.add("结算单ID");	//2
		titles.add("作业名称");	//3
		titles.add("检查员");	//4
		titles.add("工时");	//5
		titles.add("金额");	//6
		titles.add("应收");	//7
		titles.add("实收");	//8
		titles.add("折扣率");	//9
		titles.add("备注");	//10
		titles.add("预留");	//11
		titles.add("预留");	//12
		dataMap.put("titles", titles);
		List<PageData> varOList = dsmxService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("DISPATCHING_ID"));	    //1
			vpd.put("var2", varOList.get(i).getString("SETTLEMENT_ID"));	    //2
			vpd.put("var3", varOList.get(i).getString("WORK_NAME"));	    //3
			vpd.put("var4", varOList.get(i).getString("INSPECT"));	    //4
			vpd.put("var5", varOList.get(i).getString("WORK_HOURS"));	    //5
			vpd.put("var6", varOList.get(i).getString("TOTAL"));	    //6
			vpd.put("var7", varOList.get(i).getString("RECEIVABLE"));	    //7
			vpd.put("var8", varOList.get(i).getString("RECEIPTS"));	    //8
			vpd.put("var9", varOList.get(i).getString("DISCOUNT_RATE"));	    //9
			vpd.put("var10", varOList.get(i).getString("REMARK"));	    //10
			vpd.put("var11", varOList.get(i).getString("YL1"));	    //11
			vpd.put("var12", varOList.get(i).getString("YL2"));	    //12
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
