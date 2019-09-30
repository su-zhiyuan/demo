package com.fh.controller.sercar.inforelay;

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
import com.fh.util.my.MyTools;
import com.fh.service.sercar.inforelay.InfoRelayManager;
import com.fh.service.sercar.luyin.LuyinManager;
import com.fh.service.sercar.order.OrderManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;

/** 
 * 说明：信息反馈单
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 */
@Controller
@RequestMapping(value="/inforelay")
public class InfoRelayController extends BaseController {
	
	String menuUrl = "inforelay/list.do"; //菜单地址(权限用)
	@Resource(name="inforelayService")
	private InfoRelayManager inforelayService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="luyinService")
	private LuyinManager luyinService;
	@Resource(name="orderService")
	private OrderManager orderService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增InfoRelay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("INFORELAY_ID", this.get32UUID());	//主键
		pd.put("CREATE_BY", Jurisdiction.getUsername());//创建人
		pd.put("CREATE_TIME", DateUtil.getSdftime().format(new Date()));//创建时间
		inforelayService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除InfoRelay");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData inforelay = inforelayService.findById(pd);
		
		//删除对应录音文件
		String inforelayId = inforelay.getString("INFORELAY_ID");
		Page page = new Page();
		PageData pd_luyin = new PageData();
		pd_luyin.put("INFORELAYID", inforelayId);
		page.setPd(pd_luyin);
		List<PageData> luyinList = luyinService.findByCondition(page);
		if(luyinList.size()>0){
			for (PageData pageData : luyinList) {
				luyinService.delete(pageData);
			}
		}
		inforelayService.delete(pd);
		
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改InfoRelay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String id = pd.getString("INFORELAY_ID");
		String remark = pd.getString("REMARK");
		String contend = pd.getString("RELAY_CONTEND");
		
		PageData infor = inforelayService.findById(pd);
		infor.put("STATUS", "待复核");
		if(remark != null && !"".equals(remark)){
			infor.put("REMARK", remark);
		}
		if(contend != null && !"".equals(contend)){
			infor.put("RELAY_CONTEND", contend);
		}
		
		inforelayService.edit(infor);
		
		String bianma = "xinxifh";
		String gsid = infor.getString("YL10");
		List<String> quanxian = getQuanxian(bianma, gsid);
		if(quanxian.size()>0){
			for (String str : quanxian) {
				sendYuanGong(str, "有消息反馈单需要复核");
			}
		}
		
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
		logBefore(logger, Jurisdiction.getUsername()+"列表InfoRelay");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		String username = Jurisdiction.getUsername();
		List<PageData>	varList = new ArrayList<PageData>();
		
		Page page_inforelay = new Page();
		PageData pd_inforelay = new PageData();
		if(!"admin".equals(username)){
			pd = this.getPageData();		
			pd.put("BIANMA", "xxfkzhuangtai");//获取数据字典的字节码
			PageData findByBianma2 = dictionariesService.findByBianma(pd);
			if(findByBianma2 != null){
				String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
				List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				mv.addObject("list1", varList2);
			}	
			String keywords = pd.getString("keywords");	
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
			List<PageData> list = inforelayService.list(page);	//列出InfoRelay列表！
			
			for (PageData var : list) {
				String inforelayId = var.getString("INFORELAY_ID");
				pd_inforelay.put("INFORELAYID", inforelayId);
				page_inforelay.setPd(pd_inforelay);
				List<PageData> luyinList = luyinService.findByCondition(page_inforelay);
				var.put("luyinList", luyinList);
				varList.add(var);
			}
			
			mv.addObject("pd", pd);
		}else{
			pd2 = this.getPageData();		
			pd2.put("BIANMA", "xxfkzhuangtai");//获取数据字典的字节码
			PageData findByBianma2 = dictionariesService.findByBianma(pd2);
			if(findByBianma2 != null){
				String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
				List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				mv.addObject("list1", varList2);
			}
			String keywords = pd2.getString("keywords");	
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
			List<PageData> list = inforelayService.list(page);	//列出InfoRelay列表！
			
			for (PageData var : list) {
				String inforelayId = var.getString("INFORELAY_ID");
				pd_inforelay.put("INFORELAYID", inforelayId);
				page_inforelay.setPd(pd_inforelay);
				List<PageData> luyinList = luyinService.findByCondition(page_inforelay);
				var.put("luyinList", luyinList);
				varList.add(var);
			}
			mv.addObject("pd", pd2);
		}
		
		mv.setViewName("sercar/inforelay/inforelay_list");
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
		
		pd.put("BIANMA", "xxfkzhuangtai");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList2);
		}
		
		mv.setViewName("sercar/inforelay/inforelay_edit");
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
		pd = inforelayService.findById(pd);	//根据ID读取
		
		pd.put("BIANMA", "xxfkzhuangtai");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList2);
		}
		
		mv.setViewName("sercar/inforelay/inforelay_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	//去检测页面
	@RequestMapping(value="/goDetection")
	public ModelAndView goDetection()throws Exception{
		ModelAndView mv = this.getModelAndView();
		
		String username = Jurisdiction.getUsername();
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		String name = findByUsername.getString("NAME");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = inforelayService.findById(pd);
		
		mv.setViewName("sercar/inforelay/inforelay_detection");
		mv.addObject("pd", pd);
		mv.addObject("name", name);
		mv.addObject("gsid", gsid);
		return mv;
	}
	
	//保存诊断
	@RequestMapping(value="/submitInforelay")
	public ModelAndView submitInforelay() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = Jurisdiction.getUsername();
		String content = pd.getString("content");
		String name = pd.getString("name");
		
		PageData inforelay = inforelayService.findById(pd);
		String orderId = inforelay.getString("ORDER_ID");
		
		inforelay.put("DIAGNOSTIC_BY", username);
		inforelay.put("DIAGNOSTIC_TIME", MyTools.getTime());
		inforelay.put("DIAGNOSTIC_RESULT", content);
		inforelay.put("STATUS", "已诊断待客户确认");
		inforelay.put("YL12", name);
		
		inforelayService.edit(inforelay);
		
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		
		order.put("STATUS", "信息反馈单已诊断待客户确认");
		orderService.edit(order);
		
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除InfoRelay");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			inforelayService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	//客户确认userQueRen
	@RequestMapping(value="/userQueRen")
	@ResponseBody
	public String userQueRen() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData infor = inforelayService.findById(pd);
		infor.put("STATUS", "客户已确认");
		inforelayService.edit(infor);
		
		String orderId = infor.getString("ORDER_ID");
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		order.put("STATUS", "信息反馈单已确认待报价");
		orderService.edit(order);
		
		return "success";
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出InfoRelay到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("订单ID");	//1
		titles.add("创建人");	//2
		titles.add("创建时间");	//3
		titles.add("复核人");	//4
		titles.add("复核时间");	//5
		titles.add("反馈内容");	//6
		titles.add("反馈文件");	//7
		titles.add("诊断人");	//8
		titles.add("诊断时间");	//9
		titles.add("诊断结果");	//10
		titles.add("备注");	//11
		titles.add("状态");	//12
		titles.add("预留");	//13
		titles.add("预留");	//14
		dataMap.put("titles", titles);
		List<PageData> varOList = inforelayService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("ORDER_ID"));	    //1
			vpd.put("var2", varOList.get(i).getString("CREATE_BY"));	    //2
			vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	    //3
			vpd.put("var4", varOList.get(i).getString("CHECKED_BY"));	    //4
			vpd.put("var5", varOList.get(i).getString("CHECKED_TIME"));	    //5
			vpd.put("var6", varOList.get(i).getString("RELAY_CONTEND"));	    //6
			vpd.put("var7", varOList.get(i).getString("RELAY_FILE"));	    //7
			vpd.put("var8", varOList.get(i).getString("DIAGNOSTIC_BY"));	    //8
			vpd.put("var9", varOList.get(i).getString("DIAGNOSTIC_TIME"));	    //9
			vpd.put("var10", varOList.get(i).getString("DIAGNOSTIC_RESULT"));	    //10
			vpd.put("var11", varOList.get(i).getString("REMARK"));	    //11
			vpd.put("var12", varOList.get(i).getString("STATUS"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL1"));	    //13
			vpd.put("var14", varOList.get(i).getString("YL2"));	    //14
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	//发送消息到员工微信
	public void sendYuanGong(String username,String contant) {
		try {
			PageData pd_dic = new PageData();
			pd_dic.put("BIANMA", "wxpath");
			PageData path = dictionariesService.findByBianma(pd_dic);
			String baseUrl = path.getString("BZ");
			
			if(username != null){
				PageData pd_user = new PageData();
				pd_user.put("USERNAME", username);
				PageData userInfo = userinfoService.findByUsername(pd_user);
				
				String wxid = userInfo.getString("YL7");
				if("开".equals(userInfo.getString("YL8")) && wxid != null){
					String url = baseUrl+"/wx/sendFuhe?openid="+wxid+"&contant="+contant;
					String doHttpGet = Tools.doHttpGet(url);
					System.out.println(doHttpGet);
					System.err.println("发送完成");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取拥有审核权限的人
	public List<String> getQuanxian(String bianma, String companyId){
		List<String> list = new ArrayList<String>();
		try {
			PageData pd_dic = new PageData();
			pd_dic.put("BIANMA", bianma);
			PageData d = dictionariesService.findByBianma(pd_dic);
			String uString = "";
			if(d != null){
				String dicId = d.getString("DICTIONARIES_ID");
				List<Dictionaries> dSon = dictionariesService.listSubDictByParentId(dicId);
				if(dSon.size()>0){
					for (Dictionaries dic : dSon) {
						if(dic.getNAME_EN().equals(companyId)){
							uString = dic.getBZ();
							break;
						}
					}
				}
			}
			uString = uString.replace("，", ",");
			if(uString.contains(",")){
				String[] split = uString.split(",");
				for(int i = 0; i < split.length; i++){
					list.add(split[i]);
				}
				return list;
			}else{
				list.add(uString);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
