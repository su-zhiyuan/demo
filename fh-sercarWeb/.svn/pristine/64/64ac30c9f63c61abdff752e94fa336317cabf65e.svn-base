package com.fh.controller.sercar.dayin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Dictionaries;
import com.fh.service.sercar.companyinfo.CompanyInfoManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.service.system.user.UserManager;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;

import oracle.net.aso.l;

@Controller
@RequestMapping(value="/dayin")
public class DayinController  extends BaseController{
	
	String menuUrl = "dayin/list.do"; //菜单地址(权限用)
	@Resource(name = "companyinfoService")
	private CompanyInfoManager companyinfoService;
	@Resource(name = "userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name = "dictionariesService")
	private DictionariesManager dictionariesService;

	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername() + "列表CompanyInfo");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		
		String urlPath = "";
		String basePath = "";
		PageData pd1 = new PageData();
		pd1 = this.getPageData();
		pd1.put("BIANMA", "path");// 获取数据字典的字节码
		PageData findByBianma = dictionariesService.findByBianma(pd1);
		if (findByBianma != null) {
			String parentId = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId);// 查询下级
			for (Dictionaries dictionaries : varList2) {
				String bz = dictionaries.getBZ();
				String bianma = dictionaries.getBIANMA();
				if (bianma.equals("bendipath")) {
					basePath = bz;
				} else if (bianma.equals("fuwupath")) {
					urlPath = bz;
				}
			}
		}
		
		String path = basePath + "/print/";
		String url = urlPath + "/print/";
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();

		File file = new File(path);
		
		if (file.isDirectory()) {
			File templist[] = file.listFiles();
			if (templist.length > 0) {
				for (int i = 0; i < templist.length; i++) {
					Map<String, String> map = new HashMap<String, String>();
					String FileName = templist[i].getName();
					map.put("filename", FileName);
					list.add(map);
				}
			}
		}
		mv.setViewName("sercar/dayin/dayin_list");
		mv.addObject("varList", list);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
		
	}
	
	@RequestMapping(value="/get")
	public ModelAndView get(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername() + "列表CompanyInfo");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		String filename = pd.getString("filename"); // 关键词检索条件
		
		String urlPath = "";
		String basePath = "";
		PageData pd1 = new PageData();
		pd1 = this.getPageData();
		pd1.put("BIANMA", "path");// 获取数据字典的字节码
		PageData findByBianma = dictionariesService.findByBianma(pd1);
		if (findByBianma != null) {
			String parentId = findByBianma.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId);// 查询下级
			for (Dictionaries dictionaries : varList2) {
				String bz = dictionaries.getBZ();
				String bianma = dictionaries.getBIANMA();
				if (bianma.equals("bendipath")) {
					basePath = bz;
				} else if (bianma.equals("fuwupath")) {
					urlPath = bz;
				}
			}
		}
		
		String path = basePath + "/print/" + filename;
		String url = urlPath + "/print/" + filename;
		
		List<String> list = new ArrayList<String>();

		File file = new File(path);
		if (file.isDirectory()) {
			File templist[] = file.listFiles();
			if (templist.length > 0) {
				for (int i = 0; i < templist.length; i++) {
					String FileName = templist[i].getName();
					String URL = url + "/" + FileName;
					list.add(URL);
				}
			}
		}
		
		mv.setViewName("sercar/dayin/dayin_get");
		mv.addObject("varList", list);
		return mv;
	}
	
	

}
