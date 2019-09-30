package com.fh.controller.sercar.carinfo;

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
import com.fh.service.sercar.carinfo.CarInfoManager;
import com.fh.service.sercar.contacts.ContactsManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.service.system.user.UserManager;

/** 
 * 说明：车辆信息
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 */
@Controller
@RequestMapping(value="/carinfo")
public class CarInfoController extends BaseController {
	
	String menuUrl = "carinfo/list.do"; //菜单地址(权限用)
	@Resource(name="carinfoService")
	private CarInfoManager carinfoService;
	@Resource(name="contactsService")
	private ContactsManager contactsService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增CarInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		String user = Jurisdiction.getUsername();
		PageData pd2 = new PageData();
		pd2.put("USERNAME", user);
		PageData findByUsername = userService.findByUsername(pd2);
		String gsid = findByUsername.getString("BZ");
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("CARINFO_ID", this.get32UUID());	//主键
		pd.put("CREATE_BY", Jurisdiction.getUsername());//创建人
		pd.put("CREATE_TIME", DateUtil.getSdftime().format(new Date()));//创建时间
		pd.put("YL10", gsid);
		carinfoService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除CarInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		carinfoService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改CarInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		carinfoService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表CarInfo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		String username = Jurisdiction.getUsername();
		List<PageData>	varList =null;
		if(!"admin".equals(username)){
			pd = this.getPageData();	
			pd.put("BIANMA", "qichexilie");//获取数据字典的字节码
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
			varList = carinfoService.list(page);	//列出CarInfo列表
			mv.addObject("pd", pd);
		}else{
			pd2 = this.getPageData();	
			pd2.put("BIANMA", "qichexilie");//获取数据字典的字节码
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
			varList = carinfoService.list(page);	//列出CarInfo列表
			mv.addObject("pd", pd2);
		}
		
		mv.setViewName("sercar/carinfo/carinfo_list");
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

		pd.put("BIANMA", "qichexilie");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList2);
		}
		
		mv.setViewName("sercar/carinfo/carinfo_edit");
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
		pd = carinfoService.findById(pd);	//根据ID读取

		pd.put("BIANMA", "qichexilie");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList2);
		}
		
		mv.setViewName("sercar/carinfo/carinfo_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 去添加联系人页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goAddLxr")
	public ModelAndView goAddLxr()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = carinfoService.findById(pd);
		String carinfo_id = pd.getString("CARINFO_ID");//获取汽车ID
		pd.put("CAR_ID", carinfo_id);
		mv.setViewName("sercar/carinfo/contacts_add");
		mv.addObject("msg", "AddLxr");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 新增联系人
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/AddLxr")
	public ModelAndView AddLxr() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Contacts");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("CONTACTS_ID", this.get32UUID());	//主键
		contactsService.save(pd);
		mv.addObject("msg","success");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除CarInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			carinfoService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出CarInfo到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("创建人");	//1
		titles.add("创建时间");	//2
		titles.add("车牌号");	//3
		titles.add("车架号");	//4
		titles.add("发动机号");	//5
		titles.add("汽车品牌");	//6
		titles.add("汽车系列");	//7
		titles.add("汽车年款");	//8
		titles.add("外车颜色");	//9
		titles.add("内车颜色");	//10
		titles.add("所属用户，可多个追加");	//11
		titles.add("备注");	//12
		titles.add("预留");	//13
		titles.add("预留");	//14
		titles.add("预留");	//15
		titles.add("预留");	//16
		dataMap.put("titles", titles);
		List<PageData> varOList = carinfoService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("CREATE_BY"));	    //1
			vpd.put("var2", varOList.get(i).getString("CREATE_TIME"));	    //2
			vpd.put("var3", varOList.get(i).getString("CAR_NUM1"));	    //3
			vpd.put("var4", varOList.get(i).getString("CAR_NUM2"));	    //4
			vpd.put("var5", varOList.get(i).getString("CAR_NUM3"));	    //5
			vpd.put("var6", varOList.get(i).getString("CAR_BRANK"));	    //6
			vpd.put("var7", varOList.get(i).getString("CAR_XL"));	    //7
			vpd.put("var8", varOList.get(i).getString("CAR_YEAR"));	    //8
			vpd.put("var9", varOList.get(i).getString("COLOR_OUT"));	    //9
			vpd.put("var10", varOList.get(i).getString("COLOR_IN"));	    //10
			vpd.put("var11", varOList.get(i).getString("USERNAME"));	    //11
			vpd.put("var12", varOList.get(i).getString("REMARK"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL1"));	    //13
			vpd.put("var14", varOList.get(i).getString("YL2"));	    //14
			vpd.put("var15", varOList.get(i).getString("YL3"));	    //15
			vpd.put("var16", varOList.get(i).getString("YL4"));	    //16
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
