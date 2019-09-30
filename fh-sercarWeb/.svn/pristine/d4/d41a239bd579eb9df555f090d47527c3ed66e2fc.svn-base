package com.fh.controller.sercar.baoxiao;

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
import javax.servlet.http.HttpServletRequest;

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
import com.fh.service.sercar.baoxiao.BaoxiaoManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;

/** 
 * 说明：报销
 * 创建人：FH Q313596790
 * 创建时间：2018-09-06
 */
@Controller
@RequestMapping(value="/baoxiao")
public class BaoxiaoController extends BaseController {
	
	String menuUrl = "baoxiao/list.do"; //菜单地址(权限用)
	@Resource(name="baoxiaoService")
	private BaoxiaoManager baoxiaoService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Baoxiao");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("BAOXIAO_ID", this.get32UUID());	//主键
		baoxiaoService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Baoxiao");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		baoxiaoService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Baoxiao");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		baoxiaoService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Baoxiao");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		String username = Jurisdiction.getUsername();
		List<PageData>	varList =null;
		List<String> imglist=null;
		if(!"admin".equals(username)){
			pd = this.getPageData();		
			pd.put("BIANMA", "bxiaozhuangtai");//获取数据字典的字节码
			PageData findByBianma2 = dictionariesService.findByBianma(pd);
			if(findByBianma2 != null){
				String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
				List<Dictionaries> varList1 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				mv.addObject("list1", varList1);
			}

			pd.put("BIANMA", "baoxiaoleixing");//获取数据字典的字节码
			PageData findByBianma = dictionariesService.findByBianma(pd);
			if(findByBianma != null){
				String parentId = findByBianma.getString("DICTIONARIES_ID");
				List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId);//查询下级
				mv.addObject("list2", varList2);
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
			if(pd.getString("lastStart")==null && pd.getString("lastEnd")==null 
					&& pd.getString("keywords")==null && pd.getString("TYPE")==null  && pd.getString("name")==null){
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
			varList = baoxiaoService.list(page);	//列出Baoxiao列表
			
			String URL ="";	
			PageData pd3 = new PageData();
			pd3 = this.getPageData();		
			pd3.put("BIANMA", "path");//获取数据字典的字节码
			PageData findByBianma3 = dictionariesService.findByBianma(pd3);
	 		if(findByBianma3 != null){
				String parentId2 = findByBianma3.getString("DICTIONARIES_ID");
				List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				for (Dictionaries dictionaries : varList2) {
					String bz = dictionaries.getBZ();
					String bianma = dictionaries.getBIANMA();
					if(bianma.equals("fuwupath")){
						URL = bz;
					}
				}
			} 		
	 		String urlPath="";
			
			if(varList.size()>0){
				for(PageData pt:varList){
					imglist=new ArrayList<String>();
					String pzimg = pt.getString("yl4");
					if(pzimg !=null && !"".equals(pzimg)){
						String bxid=pt.getString("BAOXIAO_ID");
						System.err.println(pzimg+"------------------------");
						String[] img = pzimg.split("\\+");
						for(int i=0;i<img.length;i++){
							urlPath = URL + bxid+ "/" +  img[i];
							imglist.add(urlPath);
						}
						pt.put("yl4", imglist);
					}
				}
			}
			mv.addObject("pd", pd);
		}else{
			pd2 = this.getPageData();		
			pd2.put("BIANMA", "bxiaozhuangtai");//获取数据字典的字节码
			PageData findByBianma2 = dictionariesService.findByBianma(pd2);
			if(findByBianma2 != null){
				String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
				List<Dictionaries> varList1 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				mv.addObject("list1", varList1);
			}

			pd2.put("BIANMA", "baoxiaoleixing");//获取数据字典的字节码
			PageData findByBianma = dictionariesService.findByBianma(pd2);
			if(findByBianma != null){
				String parentId = findByBianma.getString("DICTIONARIES_ID");
				List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId);//查询下级
				mv.addObject("list2", varList2);
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
			if(pd2.getString("lastStart")==null && pd2.getString("lastEnd")==null 
					&& pd2.getString("keywords")==null && pd2.getString("TYPE")==null  && pd2.getString("name")==null){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				pd2.put("lastStart", df.format(new Date())+" 00:00:00");
				pd2.put("lastEnd", df.format(new Date())+" 23:59:59");
			}
			page.setPd(pd2);
			varList = baoxiaoService.list(page);	//列出Baoxiao列表
			
			String URL ="";	
			PageData pd3 = new PageData();
			pd3 = this.getPageData();		
			pd3.put("BIANMA", "path");//获取数据字典的字节码
			PageData findByBianma3 = dictionariesService.findByBianma(pd3);
	 		if(findByBianma3 != null){
				String parentId2 = findByBianma3.getString("DICTIONARIES_ID");
				List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				for (Dictionaries dictionaries : varList2) {
					String bz = dictionaries.getBZ();
					String bianma = dictionaries.getBIANMA();
					if(bianma.equals("fuwupath")){
						URL = bz;
					}
				}
			} 		
	 		String urlPath="";
			
			if(varList.size()>0){
				for(PageData pt:varList){
					imglist=new ArrayList<String>();
					String pzimg = pt.getString("yl4");
					if(pzimg !=null && !"".equals(pzimg)){
						String bxid=pt.getString("BAOXIAO_ID");
						System.err.println(pzimg+"------------------------");
						String[] img = pzimg.split("\\+");
						for(int i=0;i<img.length;i++){
							urlPath = URL + bxid+ "/" +  img[i];
							imglist.add(urlPath);
						}
						pt.put("yl4", imglist);
					}
				}
			}
			mv.addObject("pd", pd2);
		}
		
		mv.setViewName("sercar/baoxiao/baoxiao_list");
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

		pd.put("BIANMA", "bxiaozhuangtai");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList1 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList1);
		}

		pd.put("BIANMA", "baoxiaoleixing");//获取数据字典的字节码
		PageData findByBianma = dictionariesService.findByBianma(pd);
		if(findByBianma != null){
			String parentId = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId);//查询下级
			mv.addObject("list2", varList2);
		}
		mv.setViewName("sercar/baoxiao/baoxiao_edit");
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
		pd = baoxiaoService.findById(pd);	//根据ID读取

		pd.put("BIANMA", "bxiaozhuangtai");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList1 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList1);
		}

		pd.put("BIANMA", "baoxiaoleixing");//获取数据字典的字节码
		PageData findByBianma = dictionariesService.findByBianma(pd);
		if(findByBianma != null){
			String parentId = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId);//查询下级
			mv.addObject("list2", varList2);
		}
		mv.setViewName("sercar/baoxiao/baoxiao_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Baoxiao");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			baoxiaoService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Baoxiao到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("类型");	//1
		titles.add("金额");	//2
		titles.add("费用明细");	//3
		titles.add("备注");	//4
		titles.add("状态");	//5
		titles.add("时间");	//6
		titles.add("创建人");	//7
		titles.add("备注8");	//8
		titles.add("备注9");	//9
		titles.add("备注10");	//10
		titles.add("备注11");	//11
		titles.add("备注12");	//12
		titles.add("备注13");	//13
		dataMap.put("titles", titles);
		List<PageData> varOList = baoxiaoService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TYPE"));	    //1
			vpd.put("var2", varOList.get(i).getString("TOTAL"));	    //2
			vpd.put("var3", varOList.get(i).getString("EXPLAINS"));	    //3
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
		pd.put("YL10", comid);
		pd.put("YL5", yue);
		pd.put("YL1", "已审批");
		page.setPd(pd);
		List<PageData>	dkpeoplelist = baoxiaoService.listpeople(page);
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for(int j=0;j<dkpeoplelist.size();j++){
			String sPeople = dkpeoplelist.get(j).getString("YL3");
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
			pd1.put("YL3", sPeople);
			pd1.put("YL5",yue);
			pd1.put("YL1", "已审批");
			page.setPd(pd1);
			int baoxiaomoney=0;
			List<PageData>	dklist = baoxiaoService.list(page);
			int baoxiaocount = dklist.size();
			for(int i=0;i<dklist.size();i++){
				String string2 = dklist.get(i).getString("total");
				baoxiaomoney=baoxiaomoney+Integer.parseInt(string2);
			}
			map.put("baoxiao", baoxiaocount+"");
			map.put("baoxiaomoney", baoxiaomoney+"");
			map.put("name", string);
			list.add(map);
		}
		mv.addObject("dktongjilist", list);
		mv.addObject("pd", pd);
		mv.setViewName("sercar/baoxiao/tongji");
		return mv;
	}
	
	@RequestMapping(value="/yuetongji")
	public ModelAndView yuetongji(Page page) throws Exception{
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
		List<PageData>	dkpeoplelist = baoxiaoService.listpeople(page);
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		for(int j=0;j<dkpeoplelist.size();j++){
			String sPeople = dkpeoplelist.get(j).getString("YL3");
			
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
			pd1.put("YL3", sPeople);
			pd1.put("YL1", "已审批");
			page.setPd(pd1);
			int baoxiaomoney=0;
			List<PageData>	dklist = baoxiaoService.list(page);
			int baoxiaocount = dklist.size();
			for(int i=0;i<dklist.size();i++){
				String string2 = dklist.get(i).getString("total");
				baoxiaomoney=baoxiaomoney+Integer.parseInt(string2);
			}
			map.put("baoxiao", baoxiaocount+"");
			map.put("baoxiaomoney", baoxiaomoney+"");
			map.put("name", string);
			list.add(map);
		}
		
		mv.addObject("dktongjilist", list);
		mv.addObject("pd", pd);
		mv.setViewName("sercar/baoxiao/tongji");
		return mv;
	}
	
	//查看此人当月报销详情
		@RequestMapping(value="/detaillist")
		public ModelAndView detaillist(Page page) throws Exception{
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			String yue = pd.getString("YL5");	
			String name = pd.getString("Name");
			pd.put("NAME",name);
			page.setPd(pd);
			List<PageData> list = userinfoService.list(page);
			String username = list.get(0).getString("USERNAME");
			PageData pd1 = new PageData();
			pd1.put("YL5",yue);
			pd1.put("YL3", username);
			pd1.put("YL1", "已审批");
			page.setPd(pd1);
			List<PageData>	varList = baoxiaoService.list(page);
			mv.setViewName("sercar/baoxiao/detail");
			mv.addObject("varList", varList);
			return mv;
		}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
