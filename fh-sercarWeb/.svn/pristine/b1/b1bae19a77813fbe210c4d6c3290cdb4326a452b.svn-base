package com.fh.controller.sercar.car;

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
import com.fh.service.sercar.car.CarManager;

/** 
 * 说明：品牌信息
 * 创建人：FH Q313596790
 * 创建时间：2019-05-05
 */
@Controller
@RequestMapping(value="/car")
public class CarController extends BaseController {
	
	String menuUrl = "car/list.do"; //菜单地址(权限用)
	@Resource(name="carService")
	private CarManager carService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Car");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("CAR_ID", this.get32UUID());	//主键
		carService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除Car");
		PageData pd = new PageData();
		pd = this.getPageData();
		carService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Car");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		carService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Car");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		
		List<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		list.add("H");
		list.add("I");
		list.add("J");
		list.add("K");
		list.add("L");
		list.add("M");
		list.add("N");
		list.add("O");
		list.add("P");
		list.add("Q");
		list.add("R");
		list.add("S");
		list.add("T");
		list.add("U");
		list.add("V");
		list.add("W");
		list.add("X");
		list.add("Y");
		list.add("Z");
		
		page.setPd(pd);
		List<PageData>	varList = carService.list(page);	//列出Car列表
		mv.setViewName("sercar/car/car_list");
		mv.addObject("list", list);
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
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
		mv.setViewName("sercar/car/car_edit");
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
		pd = carService.findById(pd);	//根据ID读取
		mv.setViewName("sercar/car/car_edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Car");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			carService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Car到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("字母分类");	//1
		titles.add("汽车号");	//2
		titles.add("品牌");	//3
		titles.add("车类");	//4
		titles.add("车型");	//5
		titles.add("车系");	//6
		titles.add("拼音");	//7
		titles.add("预留");	//8
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
		titles.add("预留");	//19
		titles.add("预留");	//20
		titles.add("预留");	//21
		titles.add("预留");	//22
		titles.add("预留");	//23
		titles.add("预留");	//24
		titles.add("预留");	//25
		titles.add("预留");	//26
		dataMap.put("titles", titles);
		List<PageData> varOList = carService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TYPE"));	    //1
			vpd.put("var2", varOList.get(i).getString("NUMBER"));	    //2
			vpd.put("var3", varOList.get(i).getString("PINGPAI"));	    //3
			vpd.put("var4", varOList.get(i).getString("CHELEI"));	    //4
			vpd.put("var5", varOList.get(i).getString("CHEXING"));	    //5
			vpd.put("var6", varOList.get(i).getString("CHEXI"));	    //6
			vpd.put("var7", varOList.get(i).getString("YL1"));	    //7
			vpd.put("var8", varOList.get(i).getString("YL2"));	    //8
			vpd.put("var9", varOList.get(i).getString("YL3"));	    //9
			vpd.put("var10", varOList.get(i).getString("YL4"));	    //10
			vpd.put("var11", varOList.get(i).getString("YL5"));	    //11
			vpd.put("var12", varOList.get(i).getString("YL6"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL7"));	    //13
			vpd.put("var14", varOList.get(i).getString("YL8"));	    //14
			vpd.put("var15", varOList.get(i).getString("YL9"));	    //15
			vpd.put("var16", varOList.get(i).getString("YL10"));	    //16
			vpd.put("var17", varOList.get(i).getString("YL11"));	    //17
			vpd.put("var18", varOList.get(i).getString("YL12"));	    //18
			vpd.put("var19", varOList.get(i).getString("YL13"));	    //19
			vpd.put("var20", varOList.get(i).getString("YL14"));	    //20
			vpd.put("var21", varOList.get(i).getString("YL15"));	    //21
			vpd.put("var22", varOList.get(i).getString("YL16"));	    //22
			vpd.put("var23", varOList.get(i).getString("YL17"));	    //23
			vpd.put("var24", varOList.get(i).getString("YL18"));	    //24
			vpd.put("var25", varOList.get(i).getString("YL19"));	    //25
			vpd.put("var26", varOList.get(i).getString("YL20"));	    //26
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
