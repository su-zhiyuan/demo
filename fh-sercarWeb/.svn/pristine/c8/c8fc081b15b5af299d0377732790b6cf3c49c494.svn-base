package com.fh.controller.sercar.userinfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Dictionaries;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.service.sercar.companyinfo.CompanyInfoManager;
import com.fh.service.sercar.resume.ResumeManager;
import com.fh.service.sercar.resumeexperience.ResumeexperienceManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.service.system.user.UserManager;

/** 
 * 说明：用户信息
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 */
@Controller
@RequestMapping(value="/userinfo")
public class UserInfoController extends BaseController {
	
	String menuUrl = "userinfo/list.do"; //菜单地址(权限用)
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="companyinfoService")
	private CompanyInfoManager companyinfoService;
	@Resource(name="resumeService")
	private ResumeManager resumeService;
	@Resource(name="resumeexperienceService")
	private ResumeexperienceManager resumeexperienceService;
	@Autowired
	private CompanyInfoManager companyInfoService;
	
	/*static String URL = "http://192.168.0.101:8081/file-server/sercar-app/";
	static String PATH = "D:/file-server/sercar-app/";*/
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpServletRequest request) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("USERINFO_ID", this.get32UUID());	//主键
		userinfoService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	/*@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		userinfoService.delete(pd);
		out.write("success");
		out.close();
	}*/
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(HttpServletRequest request) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		
		String id = request.getParameter("USERINFO_ID");
		String username = request.getParameter("USERNAME");
		String roletype = request.getParameter("ROLETYPE");
		String status = request.getParameter("STATUS");
		String yl3 = request.getParameter("YL3");
		String remark = request.getParameter("REMARK");
		String yl1 = request.getParameter("YL1");
		String name = request.getParameter("NAME");
		String sex = request.getParameter("SEX");
		String yl2 = request.getParameter("YL2");
		String tel = request.getParameter("TEL");
		String yl6 = request.getParameter("YL6");
		String urlPath = request.getParameter("YL4");

		String URL ="";
		String PATH ="";		
		PageData pd1 = new PageData();
		pd1 = this.getPageData();		
		pd1.put("BIANMA", "path");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd1);
 		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			for (Dictionaries dictionaries : varList2) {
				String bz = dictionaries.getBZ();
				String bianma = dictionaries.getBIANMA();
				if(bianma.equals("bendipath")){
					PATH = bz;
				}else if(bianma.equals("fuwupath")){
					URL = bz;
				}
			}
		} 		
		
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		MultipartFile mFile = mRequest.getFile("YL4");		
		 try {
			 if(mFile.getSize()!=0){
				 urlPath="";  
				 byte[] bytes = mFile.getBytes();//获取字节数组    
				 String filePath = PATH + mFile.getOriginalFilename(); 
				 urlPath = URL +  mFile.getOriginalFilename();
				 File dest = new File(filePath);
				 // 检测是否存在目录
				 if (!dest.getParentFile().exists()) {
					 	dest.getParentFile().mkdirs();// 新建文件夹
				 }
				 FileOutputStream fos= new FileOutputStream(dest); //写出到文件            
				 fos.write(bytes);           
				 fos.flush();           
				 fos.close();           
			 } 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("USERINFO_ID", id);
		pd.put("USERNAME", username);
		pd.put("ROLETYPE", roletype);
		pd.put("STATUS", status);
		pd.put("YL3", yl3);
		pd.put("REMARK", remark);
		pd.put("YL1", yl1);
		pd.put("NAME", name);
		pd.put("SEX", sex);
		pd.put("YL2", yl2);
		pd.put("TEL", tel);
		pd.put("YL6", yl6);
		pd.put("YL4", urlPath);
		userinfoService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表UserInfo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		String username = Jurisdiction.getUsername();
		List<PageData> varList =null;
		if(!"admin".equals(username)){
			pd = this.getPageData();		
			pd.put("BIANMA", "jsleixin");//获取数据字典的字节码
			PageData findByBianma2 = dictionariesService.findByBianma(pd);
	 		if(findByBianma2 != null){
				String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
				List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				mv.addObject("list1", varList2);
			}
			
			pd.put("BIANMA", "yhzhuangtai");//获取数据字典的字节码
			PageData findByBianma = dictionariesService.findByBianma(pd);
			if(findByBianma != null){
				String parentId = findByBianma.getString("DICTIONARIES_ID");
				List<Dictionaries> varList3 = dictionariesService.listSubDictByParentId(parentId);//查询下级
				mv.addObject("list", varList3);
			}
			
			String keywords = pd.getString("keywords");				//关键词检索条件
			if(null != keywords && !"".equals(keywords)){
				pd.put("keywords", keywords.trim());
			}
			
			Page page1=new Page();
			PageData pd1 = new PageData();
			pd1.put("USERNAME", Jurisdiction.getUsername());	
			page1.setPd(pd1);
			List<PageData> varList1 = userinfoService.list(page1);
			if(varList1.size()>0){
				PageData pageData = varList1.get(0);
				String string2 = pageData.getString("YL1");
				pd.put("YL1", string2);
				page.setPd(pd);
				varList = userinfoService.list(page);
			}
			mv.addObject("pd", pd);
		}else{
			pd2 = this.getPageData();		
			pd2.put("BIANMA", "jsleixin");//获取数据字典的字节码
			PageData findByBianma2 = dictionariesService.findByBianma(pd2);
	 		if(findByBianma2 != null){
				String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
				List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				mv.addObject("list1", varList2);
			}
			
			pd2.put("BIANMA", "yhzhuangtai");//获取数据字典的字节码
			PageData findByBianma = dictionariesService.findByBianma(pd2);
			if(findByBianma != null){
				String parentId = findByBianma.getString("DICTIONARIES_ID");
				List<Dictionaries> varList3 = dictionariesService.listSubDictByParentId(parentId);//查询下级
				mv.addObject("list", varList3);
			}
			
			String keywords = pd2.getString("keywords");				//关键词检索条件
			if(null != keywords && !"".equals(keywords)){
				pd2.put("keywords", keywords.trim());
			}			
			page.setPd(pd2);
			varList = userinfoService.list(page);
			mv.addObject("pd", pd2);
		}
		
		
			//列出UserInfo列表
		mv.setViewName("sercar/userinfo/userinfo_list");
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
		pd.put("BIANMA", "jsleixin");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList2);
		}
		pd.put("BIANMA", "yhzhuangtai");//获取数据字典的字节码
		PageData findByBianma = dictionariesService.findByBianma(pd);
		if(findByBianma != null){
			String parentId = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> varList3 = dictionariesService.listSubDictByParentId(parentId);//查询下级
			mv.addObject("list", varList3);
		}
		
		Page page1=new Page();
		PageData pd1 = new PageData();
		pd1.put("USERNAME", Jurisdiction.getUsername());	
		page1.setPd(pd1);
		List<PageData> varList1 = userinfoService.list(page1);
		PageData pageData = varList1.get(0);
		String string = pageData.getString("YL1");
		System.err.println(string);
		
		pd1.put("BIANMA", "gsid");//获取数据字典的字节码
		PageData findByBianma3 = dictionariesService.findByBianma(pd1);
		System.err.println(findByBianma3);
		if(findByBianma3 != null){
			String parentId3 = findByBianma3.getString("DICTIONARIES_ID");
			List<Dictionaries> varList4 = dictionariesService.listSubDictByParentId(parentId3);//查询下级
			for(Dictionaries dd:varList4){
				String bianma = dd.getBIANMA();
				if(bianma.equals(string)){
					pd1.put("BIANMA",bianma);
					PageData findByBianma4 = dictionariesService.findByBianma(pd1);
					String parentId4 = findByBianma4.getString("DICTIONARIES_ID");
					List<Dictionaries> varList5 = dictionariesService.listSubDictByParentId(parentId4);//查询下级
					mv.addObject("list4", varList5);
				}
			}
			
		}
		
		mv.setViewName("sercar/userinfo/userinfo_edit");
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
		
		Page page1=new Page();
		PageData pd1 = new PageData();
		pd1.put("USERNAME", Jurisdiction.getUsername());	
		page1.setPd(pd1);
		List<PageData> varList1 = userinfoService.list(page1);
		PageData pageData = varList1.get(0);
		String comid = pageData.getString("YL1");
		System.err.println(comid);
		
		pd.put("BIANMA", "jsleixin");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			for(Dictionaries dd:varList2){
				String yw = dd.getNAME_EN();
				if(yw.equals(comid)){
					Page page2=new Page();
					PageData pd2 = new PageData();	
					pd2.put("NAME_EN",yw);
					pd2.put("PARENT_ID", parentId2);
					page2.setPd(pd2);
					List<PageData> list = dictionariesService.list(page2);
					String bianma = list.get(0).getString("BIANMA");
					pd1.put("BIANMA", bianma);
					PageData findByBianma4 = dictionariesService.findByBianma(pd1);
					if(findByBianma4 != null){
						String parentId4 = findByBianma4.getString("DICTIONARIES_ID");
						List<Dictionaries> varList5 = dictionariesService.listSubDictByParentId(parentId4);//查询下级
						mv.addObject("list1", varList5);
					}
					
				}
			}
		}
		
		pd.put("BIANMA", "yhzhuangtai");//获取数据字典的字节码
		PageData findByBianma = dictionariesService.findByBianma(pd);
		if(findByBianma != null){
			String parentId = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> varList3 = dictionariesService.listSubDictByParentId(parentId);//查询下级
			mv.addObject("list", varList3);
		}
		
		pd1.put("BIANMA", "gsid");//获取数据字典的字节码
		PageData findByBianma3 = dictionariesService.findByBianma(pd1);
		System.err.println(findByBianma3);
		if(findByBianma3 != null){
			String parentId3 = findByBianma3.getString("DICTIONARIES_ID");
			List<Dictionaries> varList4 = dictionariesService.listSubDictByParentId(parentId3);//查询下级
			for(Dictionaries dd:varList4){
				String yw = dd.getNAME_EN();
				if(yw.equals(comid)){
					Page page2=new Page();
					PageData pd2 = new PageData();	
					pd2.put("NAME_EN",yw);
					pd2.put("PARENT_ID", parentId3);
					page2.setPd(pd2);
					List<PageData> list = dictionariesService.list(page2);
					String bianma = list.get(0).getString("BIANMA");
					pd1.put("BIANMA", bianma);
					PageData findByBianma4 = dictionariesService.findByBianma(pd1);
					if(findByBianma4 != null){
						String parentId4 = findByBianma4.getString("DICTIONARIES_ID");
						List<Dictionaries> varList5 = dictionariesService.listSubDictByParentId(parentId4);//查询下级
						mv.addObject("list4", varList5);
					}
					
				}
			}
			
		}
		
		pd = userinfoService.findById(pd);	//根据ID读取
		List<PageData> companysList = companyInfoService.listAll(null);
		mv.setViewName("sercar/userinfo/userinfo_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.addObject("companysList", companysList);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	/*@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			userinfoService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	*/
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出UserInfo到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("用户名");	//1
		titles.add("角色类型");	//2
		titles.add("状态");	//3
		titles.add("备注");	//4
		titles.add("姓名");	//5
		titles.add("性别");	//6
		titles.add("身份证号码");	//7
		titles.add("电话");	//8
		titles.add("地址");	//9
		titles.add("留言");	//10
		titles.add("预留");	//11
		titles.add("预留");	//12
		titles.add("预言");	//13
		dataMap.put("titles", titles);
		List<PageData> varOList = userinfoService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("USERNAME"));	    //1
			vpd.put("var2", varOList.get(i).getString("ROLETYPE"));	    //2
			vpd.put("var3", varOList.get(i).getString("STATUS"));	    //3
			vpd.put("var4", varOList.get(i).getString("REMARK"));	    //4
			vpd.put("var5", varOList.get(i).getString("NAME"));	    //5
			vpd.put("var6", varOList.get(i).getString("SEX"));	    //6
			vpd.put("var7", varOList.get(i).getString("CARD_ID"));	    //7
			vpd.put("var8", varOList.get(i).getString("TEL"));	    //8
			vpd.put("var9", varOList.get(i).getString("ADDR"));	    //9
			vpd.put("var10", varOList.get(i).getString("YL1"));	    //10
			vpd.put("var11", varOList.get(i).getString("YL2"));	    //11
			vpd.put("var12", varOList.get(i).getString("YL3"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL4"));	    //13
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@RequestMapping(value="/gojianli")
	public ModelAndView gojianli(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表简历");	
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();		
		page.setPd(pd);
		List<PageData> varList =resumeService.list(page);
		if(varList.size()>0){
			PageData pageData = varList.get(0);
			String string = pageData.getString("RESUME_ID");
			pd.put("RESUMEID", string);
			page.setPd(pd);
			List<PageData> list = resumeexperienceService.list(page);
			if(list.size()>0){				
				mv.addObject("resumeexperList", list);
			}
			
			mv.addObject("pd", pageData);
			mv.setViewName("sercar/userinfo/jianli");
		}else{
			mv.setViewName("sercar/userinfo/no");
		}
		return mv;
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
