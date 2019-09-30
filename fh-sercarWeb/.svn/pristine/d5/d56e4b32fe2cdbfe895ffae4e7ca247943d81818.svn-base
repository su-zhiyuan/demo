package com.fh.controller.sercar.advertising;

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
import com.fh.util.DateUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.service.sercar.advertising.AdvertisingManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.service.system.user.UserManager;

/** 
 * 说明：广告
 * 创建人：FH Q313596790
 * 创建时间：2018-04-18
 */
@Controller
@RequestMapping(value="/advertising")
public class AdvertisingController extends BaseController {
	
	String menuUrl = "advertising/list.do"; //菜单地址(权限用)
	@Resource(name="advertisingService")
	private AdvertisingManager advertisingService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;;
	
	
/*	
	*//**列表(用于弹窗)
	 * @param page
	 * @return
	 * @throws Exception 
	 *//*
	@RequestMapping(value="/listfortc")
	public ModelAndView listfortc(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String KEYW = pd.getString("keyword");	//检索条件
		if(null != KEYW && !"".equals(KEYW)){
			pd.put("KEYW", KEYW.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = advertisingService.list(page);	//列出Pictures列表
		mv.setViewName("sercar/advertising/advertising_list_tc");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
*/
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(Page page,HttpServletRequest request) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Advertising");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		String user = Jurisdiction.getUsername();
		PageData pd2 = new PageData();
		pd2.put("USERNAME", user);
		PageData findByUsername = userService.findByUsername(pd2);
		String gsid = findByUsername.getString("BZ");
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String advname = request.getParameter("ADVERTISING_NAME");
		String advlink = request.getParameter("ADVERTISING_LINK");
		String type = request.getParameter("TYPE");
		String status = request.getParameter("STATUS");
		String advcontent = request.getParameter("YL1");
		String remark = request.getParameter("REMARK");
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
		
		String urlPath="";
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		MultipartFile mFile = mRequest.getFile("PICTURE_PATH");
		 try {
			 if(mFile.getSize()!=0){  
				 byte[] bytes = mFile.getBytes();//获取字节数组            
				 String filePath = PATH + mFile.getOriginalFilename(); 
				 urlPath = URL + mFile.getOriginalFilename();
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
		pd.put("ADVERTISING_NAME", advname);
		pd.put("ADVERTISING_LINK", advlink);
		pd.put("TYPE", type);
		pd.put("STATUS", status);
		pd.put("YL1", advcontent);
		pd.put("REMARK", remark);
		pd.put("PICTURE_PATH", urlPath);
		pd.put("ADVERTISING_ID", this.get32UUID());	//主键
		pd.put("CREATE_BY", Jurisdiction.getUsername());//创建人
		pd.put("CREATE_TIME", DateUtil.getSdftime().format(new Date()));//创建时间
		pd.put("YL10",gsid);
		advertisingService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Advertising");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		advertisingService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(HttpServletRequest request) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Advertising");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		String id = request.getParameter("ADVERTISING_ID");
		String advname = request.getParameter("ADVERTISING_NAME");
		String advlink = request.getParameter("ADVERTISING_LINK");
		String type = request.getParameter("TYPE");
		String status = request.getParameter("STATUS");
		String advcontent = request.getParameter("YL1");
		String remark = request.getParameter("REMARK");
		String urlPath = request.getParameter("PICTURE_PATH");
		String yl10 = request.getParameter("YL10");
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
		MultipartFile mFile = mRequest.getFile("PICTURE_PATH");
		 try {
			 if(mFile.getSize()!=0){
				 urlPath="";  
				 byte[] bytes = mFile.getBytes();//获取字节数组            
				 String filePath = PATH + mFile.getOriginalFilename(); 
				 urlPath = URL + mFile.getOriginalFilename();
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
		pd.put("ADVERTISING_ID", id);
		pd.put("ADVERTISING_NAME", advname);
		pd.put("ADVERTISING_LINK", advlink);
		pd.put("TYPE", type);
		pd.put("STATUS", status);
		pd.put("YL1", advcontent);
		pd.put("REMARK", remark);
		pd.put("PICTURE_PATH", urlPath);
		pd.put("YL10", yl10);
		advertisingService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Advertising");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		String username = Jurisdiction.getUsername();
		List<PageData>	varList =null;
		if(!"admin".equals(username)){
			pd = this.getPageData();		
			pd.put("BIANMA", "ggzhuangtai");//获取数据字典的字节码
			PageData findByBianma2 = dictionariesService.findByBianma(pd);
			if(findByBianma2 != null){
				String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
				List<Dictionaries> varList1 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				mv.addObject("list1", varList1);
			}
			pd.put("BIANMA", "ggleixing");//获取数据字典的字节码
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
					&& pd.getString("keywords")==null && pd.getString("name")==null && pd.getString("STATUS")==null){
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
			varList = advertisingService.list(page);	//列出Advertising列表
			mv.addObject("pd", pd);
		}else{
			pd2 = this.getPageData();		
			pd2.put("BIANMA", "ggzhuangtai");//获取数据字典的字节码
			PageData findByBianma2 = dictionariesService.findByBianma(pd2);
			if(findByBianma2 != null){
				String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
				List<Dictionaries> varList1 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				mv.addObject("list1", varList1);
			}

			pd2.put("BIANMA", "ggleixing");//获取数据字典的字节码
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
					&& pd2.getString("keywords")==null && pd2.getString("name")==null && pd2.getString("STATUS")==null){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				pd2.put("lastStart", df.format(new Date())+" 00:00:00");
				pd2.put("lastEnd", df.format(new Date())+" 23:59:59");			
			}

			page.setPd(pd2);
			varList = advertisingService.list(page);	//列出Advertising列表
			mv.addObject("pd", pd2);
		}
		
		mv.setViewName("sercar/advertising/advertising_list");
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

		pd.put("BIANMA", "ggzhuangtai");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList1 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList1);
		}

		pd.put("BIANMA", "ggleixing");//获取数据字典的字节码
		PageData findByBianma = dictionariesService.findByBianma(pd);
		if(findByBianma != null){
			String parentId = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId);//查询下级
			mv.addObject("list2", varList2);
		}
		
		mv.setViewName("sercar/advertising/advertising_edit");
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

		pd.put("BIANMA", "ggzhuangtai");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList1 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList1);
		}

		pd.put("BIANMA", "ggleixing");//获取数据字典的字节码
		PageData findByBianma = dictionariesService.findByBianma(pd);
		if(findByBianma != null){
			String parentId = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId);//查询下级
			mv.addObject("list2", varList2);
		}
		
		pd = advertisingService.findById(pd);	//根据ID读取
		mv.setViewName("sercar/advertising/advertising_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Advertising");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			advertisingService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Advertising到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("广告名称");	//1
		titles.add("图片路径");	//2
		titles.add("广告链接");	//3
		titles.add("类型");	//4
		titles.add("状态");	//5
		titles.add("创建人");	//6
		titles.add("创建时间");	//7
		titles.add("预留1");	//8
		titles.add("预留2");	//9
		titles.add("预留3");	//10
		titles.add("备注");	//11
		dataMap.put("titles", titles);
		List<PageData> varOList = advertisingService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("ADVERTISING_NAME"));	    //1
			vpd.put("var2", varOList.get(i).getString("PICTURE_PATH"));	    //2
			vpd.put("var3", varOList.get(i).getString("ADVERTISING_LINK"));	    //3
			vpd.put("var4", varOList.get(i).getString("TYPE"));	    //4
			vpd.put("var5", varOList.get(i).getString("STATUS"));	    //5
			vpd.put("var6", varOList.get(i).getString("CREATE_BY"));	    //6
			vpd.put("var7", varOList.get(i).getString("CREATE_TIME"));	    //7
			vpd.put("var8", varOList.get(i).getString("YL1"));	    //8
			vpd.put("var9", varOList.get(i).getString("YL2"));	    //9
			vpd.put("var10", varOList.get(i).getString("YL3"));	    //10
			vpd.put("var11", varOList.get(i).getString("REMARK"));	    //11
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
