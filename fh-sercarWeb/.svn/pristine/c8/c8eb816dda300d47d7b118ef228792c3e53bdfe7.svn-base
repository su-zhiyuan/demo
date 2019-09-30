package com.fh.controller.sercar.repoinfo;

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
import com.fh.entity.system.Dictionaries;
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.service.sercar.repochange.RepoChangeManager;
import com.fh.service.sercar.repoinfo.RepoinfoManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.service.system.user.UserManager;

/** 
 * 说明：库存信息
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 */
@Controller
@RequestMapping(value="/repoinfo")
public class RepoinfoController extends BaseController {
	
	String menuUrl = "repoinfo/list.do"; //菜单地址(权限用)
	@Resource(name="repoinfoService")
	private RepoinfoManager repoinfoService;
	@Resource(name="repochangeService")
	private RepoChangeManager repochangeService;
	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增RepoInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		String user = Jurisdiction.getUsername();
		PageData pd2 = new PageData();
		pd2.put("USERNAME", user);
		PageData findByUsername = userService.findByUsername(pd2);
		String gsid = findByUsername.getString("BZ");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("REPOINFO_ID", this.get32UUID());	//主键
		pd.put("YL1", Jurisdiction.getUsername());//创建人
		pd.put("YL2", DateUtil.getSdftime().format(new Date()));//创建时间
		pd.put("YL3", "完成");
		pd.put("YL10", gsid);
		String number = pd.get("NUMBER").toString();
		pd.put("YL6", number);
		repoinfoService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除RepoInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		repoinfoService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改RepoInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String number = pd.get("NUMBER").toString();
		pd.put("YL6", number);
		repoinfoService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表RepoInfo");
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
			String name = pd.getString("name");
			if(null != name && !"".equals(name)){
				pd.put("name", name);
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
					&& pd.getString("keywords")==null){
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
			varList = repoinfoService.list(page);	//列出RepoInfo列表
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
					&& pd2.getString("keywords")==null){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				pd2.put("lastStart", df.format(new Date())+" 00:00:00");
				pd2.put("lastEnd", df.format(new Date())+" 23:59:59");				
			}

			page.setPd(pd2);
			varList = repoinfoService.list(page);	//列出RepoInfo列表
			mv.addObject("pd", pd2);
		}
		mv.setViewName("sercar/repoinfo/repoinfo_list");
		mv.addObject("varList", varList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		
		PageData dicPd = new PageData();
		dicPd.put("BIANMA", "repoinfotype");	
		PageData dicparent = dictionariesService.findByBianma(dicPd);
		if(dicparent != null){
			String parentId2 = dicparent.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList2);
		}
		
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
		String edite = "edite";
		pd.put("ifInfo", edite);
		mv.setViewName("sercar/repoinfo/repoinfo_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		
		PageData dicPd = new PageData();
		dicPd.put("BIANMA", "repoinfotype");	
		PageData dicparent = dictionariesService.findByBianma(dicPd);
		if(dicparent != null){
			String parentId2 = dicparent.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList2);
		}
		
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
		pd = repoinfoService.findById(pd);	//根据ID读取
		String edite = "edite";
		pd.put("ifInfo", edite);
		mv.setViewName("sercar/repoinfo/repoinfo_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		
		PageData dicPd = new PageData();
		dicPd.put("BIANMA", "repoinfotype");	
		PageData dicparent = dictionariesService.findByBianma(dicPd);
		if(dicparent != null){
			String parentId2 = dicparent.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList2);
		}
		
		return mv;
	}
	
	/**去入库页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goRepoIn")
	public ModelAndView goRepoIn()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = repoinfoService.findById(pd);	//根据ID读取
		pd.put("ifInfo", "repoIn");
		mv.setViewName("sercar/repoinfo/repoinfo_edit");
		mv.addObject("msg", "repoIn");
		mv.addObject("pd", pd);
		return mv;
	}
	
	
 	/**
 	 *入库
 	 * @return
 	 * @throws Exception
 	 */
	@RequestMapping(value="/repoIn")
	public ModelAndView UpdateIn()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String repid = pd.getString("REPOINFO_ID");
		String count = pd.getString("NUMBER");
		
		PageData repoinfo = repoinfoService.findById(pd);	//根据ID读取
		String rname = repoinfo.getString("NAME");
		String xinghao = repoinfo.getString("XINGHAO");
		String countIn = repoinfo.getString("NUMBER");
		String kyCount = repoinfo.getString("YL6");
	
	    int countNew = Integer.parseInt(countIn)+Integer.parseInt(count);
	    int keyiCOunt = Integer.parseInt(kyCount)+Integer.parseInt(count);
		repoinfo.put("NUMBER", countNew);
		repoinfo.put("YL6", keyiCOunt);
		repoinfoService.edit(repoinfo);
		
		//插入到库存异动记录表中
		PageData pd2 = new PageData();
		pd2 = this.getPageData();
		pd2.put("REPOCHANGE_ID", this.get32UUID());	//主键
		pd2.put("YL1", Jurisdiction.getUsername());//创建人
		pd2.put("YL2", DateUtil.getSdftime().format(new Date()));//创建时间
		pd2.put("TYPE", "入库");
		pd2.put("REPO_ID",repid);
		pd2.put("YL4",rname+"("+xinghao+")");
		pd2.put("COUNT", count);
		repochangeService.save(pd2);
		
		mv.setViewName("sercar/repoinfo/repoinfo_list");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 去出库页面
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value="/goRepoInChu")
	public ModelAndView goRepoInChu()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = repoinfoService.findById(pd);	//根据ID读取
		pd.put("ifInfo", "repoInChu");
		mv.setViewName("sercar/repoinfo/repoinfo_edit");
		mv.addObject("msg", "repoOut");
		mv.addObject("pd", pd);
		return mv;
	}
		
	/**
 	 *出库
 	 * @return
 	 * @throws Exception
 	 */
	@RequestMapping(value="/repoOut")
	public ModelAndView UpdateOut()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String repid = pd.getString("REPOINFO_ID");
		String outCount = pd.getString("NUMBER");
		String yongtu = pd.getString("YL6");
		String time = pd.getString("YL5");
		String cqType = pd.getString("YL9");
		String chepai = pd.getString("YL8");
		String getPerson = pd.getString("YL7");
		
		PageData repoinfo = repoinfoService.findById(pd);	//根据ID读取
		String rname = repoinfo.getString("NAME");
		String xinghao = repoinfo.getString("XINGHAO");
		
		String count = repoinfo.getString("NUMBER");
		String kyCount = repoinfo.getString("YL6");
		String sdCount = repoinfo.getString("YL7");
		
		int newCount = Integer.parseInt(count) - Integer.parseInt(outCount);
		repoinfo.put("NUMBER", newCount);
		if(cqType.equals("1")){
			int newKyCount = Integer.parseInt(kyCount) - Integer.parseInt(outCount);
			repoinfo.put("YL6", newKyCount);
		}else {
			int newSdCount = Integer.parseInt(sdCount) - Integer.parseInt(outCount);
			repoinfo.put("YL7", newSdCount);
		}
		repoinfoService.edit(repoinfo);
		
		//插入到库存异动记录表中
		PageData pd2 = new PageData();
		pd2 = this.getPageData();
		pd2.put("REPOCHANGE_ID", this.get32UUID());	//主键
		pd2.put("YL1", Jurisdiction.getUsername());//创建人
		pd2.put("YL2", DateUtil.getSdftime().format(new Date()));//创建时间
		pd2.put("TYPE", "出库");
		pd2.put("REPO_ID",repid);
		pd2.put("YL4",rname+"("+xinghao+")");
		pd2.put("COUNT", outCount);
		pd2.put("YL5", time);
		pd2.put("YL6", yongtu);
		pd2.put("YL7", getPerson);
		pd2.put("YL8", chepai);
		pd2.put("YL9", cqType);
		
		repochangeService.save(pd2);
		
		mv.setViewName("sercar/repoinfo/repoinfo_list");
		mv.setViewName("save_result");
		return mv;
	}
		
		
		
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除RepoInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			repoinfoService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Repoinfo到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("类别");	//1
		titles.add("编号");	//2
		titles.add("名称");	//3
		titles.add("规格型号");	//4
		titles.add("单位");	//5
		titles.add("数量");	//6
		titles.add("单价");	//7
		titles.add("存放地址");	//8
		titles.add("预留");	//9
		titles.add("预留");	//10
		titles.add("预留");	//11
		titles.add("预留");	//12
		titles.add("预留");	//13
		titles.add("预留");	//14
		titles.add("预留");	//15
		titles.add("预留");	//16
		titles.add("预留");	//17
		titles.add("预留");	//18
		dataMap.put("titles", titles);
		List<PageData> varOList = repoinfoService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TYPE"));	    //1
			vpd.put("var2", varOList.get(i).getString("BIANHAO"));	    //2
			vpd.put("var3", varOList.get(i).getString("NAME"));	    //3
			vpd.put("var4", varOList.get(i).getString("XINGHAO"));	    //4
			vpd.put("var5", varOList.get(i).getString("DANWEI"));	    //5
			vpd.put("var6", varOList.get(i).getString("NUMBER"));	    //6
			vpd.put("var7", varOList.get(i).getString("PRICE"));	    //7
			vpd.put("var8", varOList.get(i).getString("ADDRESS"));	    //8
			vpd.put("var9", varOList.get(i).getString("YL1"));	    //9
			vpd.put("var10", varOList.get(i).getString("YL2"));	    //10
			vpd.put("var11", varOList.get(i).getString("YL3"));	    //11
			vpd.put("var12", varOList.get(i).getString("YL4"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL5"));	    //13
			vpd.put("var14", varOList.get(i).getString("YL6"));	    //14
			vpd.put("var15", varOList.get(i).getString("YL7"));	    //15
			vpd.put("var16", varOList.get(i).getString("YL8"));	    //16
			vpd.put("var17", varOList.get(i).getString("YL9"));	    //17
			vpd.put("var18", varOList.get(i).getString("YL10"));	    //18
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
