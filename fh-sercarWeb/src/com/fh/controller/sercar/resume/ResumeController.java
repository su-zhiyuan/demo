package com.fh.controller.sercar.resume;

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
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.service.sercar.resume.ResumeManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;

/** 
 * 说明：简历表
 * 创建人：FH Q313596790
 * 创建时间：2018-10-19
 */
@Controller
@RequestMapping(value="/resume")
public class ResumeController extends BaseController {
	
	String menuUrl = "resume/list.do"; //菜单地址(权限用)
	@Resource(name="resumeService")
	private ResumeManager resumeService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	
	/*static String URL = "http://192.168.0.105:8089/file-server/sercar-app";
	static String PATH = "E:/file-server/sercar-app";*/
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(Page page,HttpServletRequest request) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Resume");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pd2 = new PageData();
 		pd2.put("USERNAME", Jurisdiction.getUsername());
		page.setPd(pd2);
		List<PageData> list = userinfoService.list(page);
		PageData pageData = list.get(0);
		String string = pageData.getString("USERINFO_ID");
		
		String name = request.getParameter("NAME");
		String sex = request.getParameter("YL1");
		String birth = request.getParameter("BIRTH");
		String mingzu = request.getParameter("MINGZU");
		String zzmm = request.getParameter("ZHENZHIMIANMAO");
		String huji = request.getParameter("HUJI");
		String qq = request.getParameter("QQ");
		String phone = request.getParameter("PHONE");
		String email = request.getParameter("EMAIL");
		String sfz = request.getParameter("CARDNUMBER");
		String address = request.getParameter("ADDRESS");
		String xl = request.getParameter("XUELI");
		String zy = request.getParameter("ZHUANYE");
		String school = request.getParameter("SCHOOL");
		String zwpj = request.getParameter("ZIWOPINGJIA");
		String yl2 = request.getParameter("YL2");
		
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
		MultipartFile mFile = mRequest.getFile("PHOTO");
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
		
		 pd.put("NAME", name);
		 pd.put("YL1", sex);
		 pd.put("BIRTH", birth);
		 pd.put("MINGZU", mingzu);
		 pd.put("ZHENZHIMIANMAO", zzmm);
		 pd.put("HUJI", huji);
		 pd.put("QQ", qq);
		 pd.put("PHONE", phone);
		 pd.put("EMAIL", email);
		 pd.put("CARDNUMBER", sfz);
		 pd.put("ADDRESS", address);
		 pd.put("XUELI", xl);
		 pd.put("ZHUANYE", zy);
		 pd.put("SCHOOL", school);
		 pd.put("ZIWOPINGJIA", zwpj);
		 pd.put("YL2", yl2);
		
		
		pd.put("PHOTO", urlPath);
		pd.put("UID", string);
		pd.put("RESUME_ID", this.get32UUID());	//主键
		resumeService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Resume");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		resumeService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(HttpServletRequest request) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Resume");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		String id = request.getParameter("RESUME_ID");
		String name = request.getParameter("NAME");
		String sex = request.getParameter("YL1");
		String birth = request.getParameter("BIRTH");
		String mingzu = request.getParameter("MINGZU");
		String zzmm = request.getParameter("ZHENZHIMIANMAO");
		String huji = request.getParameter("HUJI");
		String qq = request.getParameter("QQ");
		String phone = request.getParameter("PHONE");
		String email = request.getParameter("EMAIL");
		String sfz = request.getParameter("CARDNUMBER");
		String address = request.getParameter("ADDRESS");
		String xl = request.getParameter("XUELI");
		String zy = request.getParameter("ZHUANYE");
		String school = request.getParameter("SCHOOL");
		String zwpj = request.getParameter("ZIWOPINGJIA");
		String uid = request.getParameter("UID");
		String yl2 = request.getParameter("YL2");
		String urlPath = request.getParameter("PHOTO");
		
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
		MultipartFile mFile = mRequest.getFile("PHOTO");
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
		 pd.put("RESUME_ID", id);
		 pd.put("NAME", name);
		 pd.put("YL1", sex);
		 pd.put("BIRTH", birth);
		 pd.put("MINGZU", mingzu);
		 pd.put("ZHENZHIMIANMAO", zzmm);
		 pd.put("HUJI", huji);
		 pd.put("QQ", qq);
		 pd.put("PHONE", phone);
		 pd.put("EMAIL", email);
		 pd.put("CARDNUMBER", sfz);
		 pd.put("ADDRESS", address);
		 pd.put("XUELI", xl);
		 pd.put("ZHUANYE", zy);
		 pd.put("SCHOOL", school);
		 pd.put("PHOTO", urlPath);
		 pd.put("ZIWOPINGJIA", zwpj);
		 pd.put("UID", uid);
		 pd.put("YL2", yl2);

		resumeService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Resume");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		String username = Jurisdiction.getUsername();
		List<PageData> varList = null;
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
			String uid = pageData.getString("USERINFO_ID");
			pd.put("UID", uid);
			page.setPd(pd);
			varList = resumeService.list(page);	//列出Resume列表
			mv.addObject("pd", pd);
		}else{
			pd2 = this.getPageData();
			String keywords = pd2.getString("keywords");				//关键词检索条件
			if(null != keywords && !"".equals(keywords)){
				pd2.put("keywords", keywords.trim());
			}
			page.setPd(pd2);
			varList = resumeService.list(page);	//列出Resume列表
			mv.addObject("pd", pd2);
		}
		
		mv.setViewName("sercar/resume/resume_list");
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
		mv.setViewName("sercar/resume/resume_edit");
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
		pd = resumeService.findById(pd);	//根据ID读取
		mv.setViewName("sercar/resume/resume_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Resume");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			resumeService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Resume到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("姓名");	//1
		titles.add("出生日期");	//2
		titles.add("民族");	//3
		titles.add("政治面貌");	//4
		titles.add("户籍");	//5
		titles.add("QQ");	//6
		titles.add("电话");	//7
		titles.add("邮箱");	//8
		titles.add("身份证号");	//9
		titles.add("家庭地址");	//10
		titles.add("学历");	//11
		titles.add("专业");	//12
		titles.add("毕业学校");	//13
		titles.add("自我评价");	//14
		titles.add("用户id");	//15
		titles.add("照片");	//16
		titles.add("预留");	//17
		titles.add("预留");	//18
		titles.add("预留");	//19
		titles.add("预留");	//20
		titles.add("预留");	//21
		titles.add("预留");	//22
		titles.add("预留");	//23
		titles.add("预留");	//24
		titles.add("预留");	//25
		titles.add("预留");	//26
		dataMap.put("titles", titles);
		List<PageData> varOList = resumeService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("NAME"));	    //1
			vpd.put("var2", varOList.get(i).getString("BIRTH"));	    //2
			vpd.put("var3", varOList.get(i).getString("MINGZU"));	    //3
			vpd.put("var4", varOList.get(i).getString("ZHENZHIMIANMAO"));	    //4
			vpd.put("var5", varOList.get(i).getString("HUJI"));	    //5
			vpd.put("var6", varOList.get(i).getString("QQ"));	    //6
			vpd.put("var7", varOList.get(i).getString("PHONE"));	    //7
			vpd.put("var8", varOList.get(i).getString("EMAIL"));	    //8
			vpd.put("var9", varOList.get(i).getString("CARDNUMBER"));	    //9
			vpd.put("var10", varOList.get(i).getString("ADDRESS"));	    //10
			vpd.put("var11", varOList.get(i).getString("XUELI"));	    //11
			vpd.put("var12", varOList.get(i).getString("ZHUANYE"));	    //12
			vpd.put("var13", varOList.get(i).getString("SCHOOL"));	    //13
			vpd.put("var14", varOList.get(i).getString("ZIWOPINGJIA"));	    //14
			vpd.put("var15", varOList.get(i).getString("UID"));	    //15
			vpd.put("var16", varOList.get(i).getString("PHOTO"));	    //16
			vpd.put("var17", varOList.get(i).getString("YL1"));	    //17
			vpd.put("var18", varOList.get(i).getString("YL2"));	    //18
			vpd.put("var19", varOList.get(i).getString("YL3"));	    //19
			vpd.put("var20", varOList.get(i).getString("YL4"));	    //20
			vpd.put("var21", varOList.get(i).getString("YL5"));	    //21
			vpd.put("var22", varOList.get(i).getString("YL6"));	    //22
			vpd.put("var23", varOList.get(i).getString("YL7"));	    //23
			vpd.put("var24", varOList.get(i).getString("YL8"));	    //24
			vpd.put("var25", varOList.get(i).getString("YL9"));	    //25
			vpd.put("var26", varOList.get(i).getString("YL10"));	    //26
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
