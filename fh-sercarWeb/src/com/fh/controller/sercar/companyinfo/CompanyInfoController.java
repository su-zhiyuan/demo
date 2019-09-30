package com.fh.controller.sercar.companyinfo;

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
import com.fh.util.my.MyTools;
import com.fh.service.sercar.companyinfo.CompanyInfoManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.service.system.user.UserManager;

/**
 * 说明：公司信息 创建人：FH Q313596790 创建时间：2018-04-08
 */
@Controller
@RequestMapping(value = "/companyinfo")
public class CompanyInfoController extends BaseController {

	String menuUrl = "companyinfo/list.do"; // 菜单地址(权限用)
	@Resource(name = "companyinfoService")
	private CompanyInfoManager companyinfoService;
	@Resource(name = "userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name = "dictionariesService")
	private DictionariesManager dictionariesService;

	/*
	 * static String URL = "http://192.168.0.105:8089/file-server/sercar-app/";
	 * static String PATH = "D:/file-server/sercar-app/";
	 */

	/**
	 * 保存
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "新增CompanyInfo");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return null;
		} // 校验权限

		String name = request.getParameter("COMPANY_NAME");
		String addr = request.getParameter("COMPANY_ADDR");
		String jj = request.getParameter("COMPANY_JJ");
		String yg = request.getParameter("YL1");
		String goTime = request.getParameter("yl4");
		String backTime = request.getParameter("yl5");
		String yl10 = request.getParameter("yl10");
		String yl11 = request.getParameter("yl11");
		String YL2 = goTime.replace(":", "");
		String YL3 = backTime.replace(":", "");

		String URL = "";
		String PATH = "";
		PageData pd1 = new PageData();
		pd1 = this.getPageData();
		pd1.put("BIANMA", "path");// 获取数据字典的字节码
		PageData findByBianma = dictionariesService.findByBianma(pd1);
		if (findByBianma != null) {
			String parentId = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService
					.listSubDictByParentId(parentId);// 查询下级
			for (Dictionaries dictionaries : varList2) {
				String bz = dictionaries.getBZ();
				String bianma = dictionaries.getBIANMA();
				if (bianma.equals("bendipath")) {
					PATH = bz;
				} else if (bianma.equals("fuwupath")) {
					URL = bz;
				}
			}
		}

		String urlPath = "";
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		MultipartFile mFile = mRequest.getFile("COMPANY_LOGO");
		try {
			if (mFile.getSize() != 0) {
				byte[] bytes = mFile.getBytes();// 获取字节数组
				String filePath = PATH + name + "/"
						+ mFile.getOriginalFilename();
				urlPath = URL + name + "/" + mFile.getOriginalFilename();
				File dest = new File(filePath);
				// 检测是否存在目录
				if (!dest.getParentFile().exists()) {
					dest.getParentFile().mkdirs();// 新建文件夹
				}
				FileOutputStream fos = new FileOutputStream(dest); // 写出到文件
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
		pd.put("COMPANY_NAME", name);
		pd.put("COMPANY_ADDR", addr);
		pd.put("COMPANY_JJ", jj);
		pd.put("COMPANY_LOGO", urlPath);
		pd.put("YL1", yg);
		pd.put("YL2", YL2);
		pd.put("YL3", YL3);
		pd.put("yl4", goTime);
		pd.put("yl5", backTime);
		pd.put("yl10", yl10);
		pd.put("yl11", yl11);
		pd.put("COMPANYINFO_ID", this.get32UUID()); // 主键
		companyinfoService.save(pd);

		// 数据字典部门设置
		PageData pd2 = new PageData();
		pd2.put("BIANMA", "gsid");
		PageData findByBianma1 = dictionariesService.findByBianma(pd2);
		String parentId1 = findByBianma1.getString("DICTIONARIES_ID");
		pd2.put("DICTIONARIES_ID", this.get32UUID());
		pd2.put("NAME", name);
		pd2.put("NAME_EN", pd.getString("COMPANYINFO_ID"));
		pd2.put("BIANMA", MyTools.getDateR());
		pd2.put("ORDER_BY", 1);
		pd2.put("PARENT_ID", parentId1);
		dictionariesService.save(pd2);

		// 数据字典角色类型设置
		PageData pd3 = new PageData();
		pd3.put("BIANMA", "jsleixin");
		PageData findByBianma2 = dictionariesService.findByBianma(pd3);
		String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
		pd3.put("DICTIONARIES_ID", this.get32UUID());
		pd3.put("NAME", name);
		pd3.put("NAME_EN", pd.getString("COMPANYINFO_ID"));
		pd3.put("BIANMA", MyTools.getDateR());
		pd3.put("ORDER_BY", 1);
		pd3.put("PARENT_ID", parentId2);
		dictionariesService.save(pd3);

		// 数据字典复核细分设置
		PageData pd4 = new PageData();
		pd4.put("BIANMA", "fuhexifen");
		PageData findByBianma3 = dictionariesService.findByBianma(pd4);
		String parentId3 = findByBianma3.getString("DICTIONARIES_ID");
		List<Dictionaries> varLi = dictionariesService.listSubDictByParentId(parentId3);// 查询下级
		for (Dictionaries d : varLi) {
			String dID = d.getDICTIONARIES_ID();
			pd4.put("DICTIONARIES_ID", this.get32UUID());
			pd4.put("NAME", name);
			pd4.put("NAME_EN", pd.getString("COMPANYINFO_ID"));
			pd4.put("BIANMA", MyTools.getDateR());
			pd4.put("ORDER_BY", 1);
			pd4.put("PARENT_ID", dID);
			dictionariesService.save(pd4);
		}

		// 数据字典审批细分设置
		PageData pd5 = new PageData();
		pd5.put("BIANMA", "shenpixifen");
		PageData findByBianma4 = dictionariesService.findByBianma(pd5);
		String parentId4 = findByBianma4.getString("DICTIONARIES_ID");
		List<Dictionaries> varLis = dictionariesService.listSubDictByParentId(parentId4);// 查询下级
		for (Dictionaries d : varLis) {
			String diD = d.getDICTIONARIES_ID();
			pd5.put("DICTIONARIES_ID", this.get32UUID());
			pd5.put("NAME", name);
			pd5.put("NAME_EN", pd.getString("COMPANYINFO_ID"));
			pd5.put("BIANMA", MyTools.getDateR());
			pd5.put("ORDER_BY", 1);
			pd5.put("PARENT_ID", diD);
			dictionariesService.save(pd5);
		}

		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 删除
	 * 
	 * @param out
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value="/delete") public void delete(PrintWriter out)
	 * throws Exception{ logBefore(logger,
	 * Jurisdiction.getUsername()+"删除CompanyInfo");
	 * if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
	 * PageData pd = new PageData(); pd = this.getPageData();
	 * companyinfoService.delete(pd); out.write("success"); out.close(); }
	 */

	/**
	 * 修改
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "修改CompanyInfo");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return null;
		} // 校验权限
		String id = request.getParameter("COMPANYINFO_ID");
		String name = request.getParameter("COMPANY_NAME");
		String addr = request.getParameter("COMPANY_ADDR");
		String jj = request.getParameter("COMPANY_JJ");
		String urlPath = request.getParameter("COMPANY_LOGO");
		String yg = request.getParameter("YL1");
		String goTime = request.getParameter("yl4");
		String backTime = request.getParameter("yl5");
		String yl10 = request.getParameter("yl10");
		String yl11 = request.getParameter("yl11");
		String YL2 = goTime.replace(":", "");
		String YL3 = backTime.replace(":", "");

		String URL = "";
		String PATH = "";
		PageData pd1 = new PageData();
		pd1 = this.getPageData();
		pd1.put("BIANMA", "path");// 获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd1);
		if (findByBianma2 != null) {
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService
					.listSubDictByParentId(parentId2);// 查询下级
			for (Dictionaries dictionaries : varList2) {
				String bz = dictionaries.getBZ();
				String bianma = dictionaries.getBIANMA();
				if (bianma.equals("bendipath")) {
					PATH = bz;
				} else if (bianma.equals("fuwupath")) {
					URL = bz;
				}
			}
		}

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		MultipartFile mFile = mRequest.getFile("COMPANY_LOGO");
		try {
			if (mFile.getSize() != 0) {
				urlPath = "";
				byte[] bytes = mFile.getBytes();// 获取字节数组
				String filePath = PATH + name + "/"
						+ mFile.getOriginalFilename();
				urlPath = URL + name + "/" + mFile.getOriginalFilename();
				File dest = new File(filePath);
				// 检测是否存在目录
				if (!dest.getParentFile().exists()) {
					dest.getParentFile().mkdirs();// 新建文件夹
				}
				FileOutputStream fos = new FileOutputStream(dest); // 写出到文件
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
		pd.put("COMPANYINFO_ID", id);
		pd.put("COMPANY_NAME", name);
		pd.put("COMPANY_ADDR", addr);
		pd.put("COMPANY_JJ", jj);
		pd.put("COMPANY_LOGO", urlPath);
		pd.put("YL1", yg);
		pd.put("YL2", YL2);
		pd.put("YL3", YL3);
		pd.put("yl4", goTime);
		pd.put("yl5", backTime);
		pd.put("yl10", yl10);
		pd.put("yl11", yl11);
		companyinfoService.edit(pd);
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 列表
	 * 
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "列表CompanyInfo");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		String username = Jurisdiction.getUsername();
		List<PageData> list = null;
		if (!"admin".equals(username)) {
			Page page1 = new Page();
			PageData pd1 = new PageData();
			pd1.put("USERNAME", username);
			page1.setPd(pd1);
			List<PageData> varList1 = userinfoService.list(page1);
			PageData pageData = varList1.get(0);
			String string2 = pageData.getString("YL1");
			pd2 = this.getPageData();
			String keywords = pd2.getString("keywords"); // 关键词检索条件
			if (null != keywords && !"".equals(keywords)) {
				pd2.put("keywords", keywords.trim());
			}
			pd2.put("COMPANYINFO_ID", string2);
			page.setPd(pd2);
			list = companyinfoService.list(page); // 列出CompanyInfo列表
			System.err.println(list);
			mv.addObject("pd", pd2);
		} else {
			pd = this.getPageData();
			String keywords = pd.getString("keywords"); // 关键词检索条件
			if (null != keywords && !"".equals(keywords)) {
				pd.put("keywords", keywords.trim());
			}
			page.setPd(pd);
			list = companyinfoService.list(page);
			mv.addObject("pd", pd);
		}

		mv.setViewName("sercar/companyinfo/companyinfo_list");
		mv.addObject("varList", list);
		mv.addObject("QX", Jurisdiction.getHC()); // 按钮权限
		return mv;
	}

	/**
	 * 去新增页面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("sercar/companyinfo/companyinfo_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 去修改页面
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = companyinfoService.findById(pd); // 根据ID读取
		mv.setViewName("sercar/companyinfo/companyinfo_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 批量删除
	 * 
	 * @param
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value="/deleteAll")
	 * 
	 * @ResponseBody public Object deleteAll() throws Exception{
	 * logBefore(logger, Jurisdiction.getUsername()+"批量删除CompanyInfo");
	 * if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
	 * PageData pd = new PageData(); Map<String,Object> map = new
	 * HashMap<String,Object>(); pd = this.getPageData(); List<PageData> pdList
	 * = new ArrayList<PageData>(); String DATA_IDS = pd.getString("DATA_IDS");
	 * if(null != DATA_IDS && !"".equals(DATA_IDS)){ String ArrayDATA_IDS[] =
	 * DATA_IDS.split(","); companyinfoService.deleteAll(ArrayDATA_IDS);
	 * pd.put("msg", "ok"); }else{ pd.put("msg", "no"); } pdList.add(pd);
	 * map.put("list", pdList); return AppUtil.returnObject(pd, map); }
	 */
	/**
	 * 导出到excel
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "导出CompanyInfo到excel");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
			return null;
		}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("公司名称"); // 1
		titles.add("公司地址"); // 2
		titles.add("公司LOGO"); // 3
		titles.add("公司简介"); // 4
		titles.add("预留"); // 5
		titles.add("预留"); // 6
		titles.add("预留"); // 7
		dataMap.put("titles", titles);
		List<PageData> varOList = companyinfoService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for (int i = 0; i < varOList.size(); i++) {
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("COMPANY_NAME")); // 1
			vpd.put("var2", varOList.get(i).getString("COMPANY_ADDR")); // 2
			vpd.put("var3", varOList.get(i).getString("COMPANY_LOGO")); // 3
			vpd.put("var4", varOList.get(i).getString("COMPANY_JJ")); // 4
			vpd.put("var5", varOList.get(i).getString("YL1")); // 5
			vpd.put("var6", varOList.get(i).getString("YL2")); // 6
			vpd.put("var7", varOList.get(i).getString("YL3")); // 7
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv, dataMap);
		return mv;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}
}
