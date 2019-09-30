package com.fh.controller.sercar.reception;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
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
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.util.my.MyTools;
import com.fh.service.sercar.car.CarManager;
import com.fh.service.sercar.carinfo.CarInfoManager;
import com.fh.service.sercar.contacts.ContactsManager;
import com.fh.service.sercar.order.OrderManager;
import com.fh.service.sercar.reception.ReceptionManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.service.system.user.UserManager;

/** 
 * 说明：接车单信息
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 */
@Controller
@RequestMapping(value="/reception")
public class ReceptionController extends BaseController {
	
	String menuUrl = "reception/list.do"; //菜单地址(权限用)
	@Resource(name="receptionService")
	private ReceptionManager receptionService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="carinfoService")
	private CarInfoManager carinfoService;
	@Resource(name="orderService")
	private OrderManager orderService;
	@Resource(name="contactsService")
	private ContactsManager contactsService;
	@Resource(name="carService")
	private CarManager carService;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		String username = Jurisdiction.getUsername();
		List<PageData>	varList =null;
		if(!"admin".equals(username)){
			pd = this.getPageData();
			pd.put("BIANMA", "jcdingdan");//获取数据字典的字节码
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
			
			if(pd.getString("lastStart")==null && pd.getString("lastEnd")==null && pd.getString("keywords")==null && pd.getString("name")==null && pd.getString("is_agree")==null){
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
			varList = receptionService.list(page);	//列出Reception列表
			mv.addObject("pd", pd);		
		}else{
			pd2 = this.getPageData();
			pd2.put("BIANMA", "jcdingdan");//获取数据字典的字节码
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
			
			if(pd2.getString("lastStart")==null && pd2.getString("lastEnd")==null && pd2.getString("keywords")==null && pd2.getString("name")==null && pd2.getString("is_agree")==null){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				pd2.put("lastStart", df.format(new Date())+" 00:00:00");
				pd2.put("lastEnd", df.format(new Date())+" 23:59:59");			
			}		
			page.setPd(pd2);
			varList = receptionService.list(page);	//列出Reception列表
			mv.addObject("pd", pd2);
		}
			
		mv.setViewName("sercar/reception/reception_list");
		mv.addObject("varList", varList);	
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去详情页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goDetails")
	public ModelAndView goDetails()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData reception = receptionService.findById(pd);
		String wg_photo = reception.getString("YL2");
		String[] photos = wg_photo.split("\\*\\*\\*");
		mv.addObject("reception", reception);
		mv.addObject("photo", photos);
		
		String orderId = reception.getString("ORDER_ID");
		pd.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd);	//根据ID读取
		mv.addObject("order", order);
		
		//查出订单的车辆信息
		String carId = order.getString("CAR_ID");
		PageData pd_car = new PageData();
		pd_car.put("CARINFO_ID", carId);
		PageData carInfo = carinfoService.findById(pd_car);
		mv.addObject("carInfo", carInfo);
		
		//查出联系人信息
		String contactId = order.getString("CONTACT_ID");
		PageData pd_contacts = new PageData();
		pd_contacts.put("CONTACTS_ID", contactId);
		PageData contact = contactsService.findById(pd_contacts);
		mv.addObject("contacts", contact);
		
		mv.setViewName("sercar/reception/reception_details");
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
		PageData reception = receptionService.findById(pd);	//根据ID读取
		
		String wg_photo = reception.getString("YL2");
		String[] photos = wg_photo.split("\\*\\*\\*");
		mv.addObject("reception", reception);
		ArrayList<String> photoList = new ArrayList<String>(Arrays.asList(photos));
		mv.addObject("photo", photoList);
		
		String orderId = reception.getString("ORDER_ID");
		pd.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd);	//根据ID读取
		mv.addObject("order", order);
		
		//查出订单的车辆信息
		String carId = order.getString("CAR_ID");
		PageData pd_car = new PageData();
		pd_car.put("CARINFO_ID", carId);
		PageData carInfo = carinfoService.findById(pd_car);
		mv.addObject("carInfo", carInfo);
		
		//查出联系人信息
		String contactId = order.getString("CONTACT_ID");
		PageData pd_contacts = new PageData();
		pd_contacts.put("CONTACTS_ID", contactId);
		PageData contact = contactsService.findById(pd_contacts);
		mv.addObject("contacts", contact);
		
		//获取全部车辆品牌
		List<PageData> listAll = carService.listAll(pd);
		List<String> list = new ArrayList<String>();
		for (PageData pageData : listAll) {
			String pingpai = pageData.getString("PINGPAI");
			list.add(pingpai);
		}
		List<String> removeDuplicate = Tools.removeDuplicate(list);
		mv.addObject("list",removeDuplicate);
		
		mv.setViewName("sercar/reception/reception_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	@RequestMapping(value="/edit")
	public ModelAndView Edit()throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Order");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		
		//获取图片的本地路径和服务器路径
		PageData dictionaries_pd = new PageData();
		dictionaries_pd.put("BIANMA", "fuwupath");
		PageData dictionariesURL = dictionariesService.findByBianma(dictionaries_pd);
		String URL =  dictionariesURL.getString("BZ");
		dictionaries_pd.put("BIANMA", "bendipath");
		PageData dictionariesBasePath = dictionariesService.findByBianma(dictionaries_pd);
		String basePath = dictionariesBasePath.getString("BZ");
		
		
		//获取当前登录人的公司id
		String user = Jurisdiction.getUsername();
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", user);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		String name = findByUsername.getString("NAME");
		
		//获取前台传递参数
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String receptionId = pd.getString("RECEPTION_ID");
		String CAR_NUM1 = pd.getString("CAR_NUM1");
		String CAR_NUM2 = pd.getString("CAR_NUM2");
		String CAR_NUM3 = pd.getString("CAR_NUM3");
		String brand = pd.getString("YL20");
		String chexi = pd.getString("YL19");
		String person = pd.getString("YL18");
		String phone = pd.getString("YL17");
		String photo_lc = pd.getString("lcimg");
		String photo_yl = pd.getString("ylimg");
		String reception_mileage = pd.getString("RECEPTION_MILEAGE");
		String RECEPTION_yl = pd.getString("RECEPTION_YL6");
		String agree = pd.getString("RECEPTION_AGREE");
		String RECEPTION_wg = pd.getString("YL1");
		String photo_wg = pd.getString("waiguantpList");
		
		PageData pd_reception = new PageData();
		pd_reception.put("RECEPTION_ID", receptionId);
		
		PageData reception = receptionService.findById(pd_reception);
		String orderId = reception.getString("ORDER_ID");
		
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		
		//油量
		reception.put("YL6", RECEPTION_yl);
		
		//油量图片保存
		if(photo_yl != null && !"".equals(photo_yl)){
			String r = MyTools.getDateR();
			String url = basePath + "/" + orderId + "/" + r;
			System.out.println(url);
			if (photo_yl.indexOf("data:image/") != -1) {
		        String type = "jpg";
		        url = url + "." + type;
		        try {
		        	photo_yl = photo_yl.substring(photo_yl.indexOf(",")+1);
					MyTools.GenerateImage(photo_yl,url);
					String ylPhoto = URL+"/"+orderId+"/"+r +"."+type;
					reception.put("YL7", ylPhoto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				reception.put("YL7", photo_yl);
			}
		}
		
		//里程数
		reception.put("MILEAGE", reception_mileage);
		
		//里程图片保存
		if(photo_lc != null && !"".equals(photo_lc)){
			String r = MyTools.getDateR();
			String url = basePath + "/" + orderId + "/" + r;
			System.out.println(url);
			if (photo_lc.indexOf("data:image/") != -1) {
		        String type = "jpg";
		        url = url + "." + type;
		        try {
		        	photo_lc = photo_lc.substring(photo_lc.indexOf(",")+1);
					MyTools.GenerateImage(photo_lc,url);
					String lcPhoto = URL+"/"+orderId+"/"+r +"."+type;
					reception.put("YL5", lcPhoto.getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				reception.put("YL5", photo_lc);
			}
		}
		
		//外观
		StringBuilder str = new StringBuilder();
		if(photo_wg != null){
			String[] photo_wg_List = photo_wg.split("@");
			if(photo_wg_List.length>0){
	        	for (int i = 0; i<photo_wg_List.length; i++) {
	    			String r = MyTools.getDateR();
	    			String url = basePath + "/" + orderId + "/" + r;
	    			System.out.println(url);
	    			if (photo_wg_List[i].indexOf("data:image/") != -1) {
				        String type = "jpg";
				        url = url + "." + type;
				        try {
				        	photo_wg_List[i] = photo_wg_List[i].substring(photo_wg_List[i].indexOf(",")+1);
							MyTools.GenerateImage(photo_wg_List[i],url);
							str.append(URL+"/"+orderId+"/"+r +"."+type).append("***");
							if(i == 1){
								String fengmian = URL+"/"+orderId+"/"+r +"."+type;
								order.put("YL1", fengmian) ;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
	    			}else {
	    				if(i == 1){
	    					order.put("YL1", photo_wg_List[1]) ;
						}
						str.append(photo_wg_List[i]).append("***");
					}
	    		}
	        }
		}
		
		reception.put("YL2", str.toString());
		
		//是否路测
		reception.put("IS_AGREE", agree);
		reception.put("STATUS", "待复核");
		reception.put("YL1", RECEPTION_wg);
		receptionService.edit(reception);
		
		order.put("STATUS", "接车单已提交待复核");
		order.put("CAR_NUM1", CAR_NUM1);
		order.put("CAR_NUM2", CAR_NUM2);
		order.put("CAR_NUM3", CAR_NUM3);
		order.put("YL17", phone);
		order.put("YL18", person);
		order.put("YL19", chexi);
		order.put("YL20", brand);
		orderService.edit(order);
		
		String carId = order.getString("CAR_ID");
		pd.put("CARINFO_ID", carId);
		PageData carInfo = carinfoService.findById(pd);
		carInfo.put("CAR_BRANK", brand);
		carInfo.put("YL1", chexi);
		carInfo.put("CAR_NUM1", CAR_NUM1);
		carInfo.put("CAR_NUM2", CAR_NUM2);
		carInfo.put("CAR_NUM3", CAR_NUM3);
		carinfoService.edit(carInfo);
		
		String contactId = order.getString("CONTACT_ID");
		pd.put("CONTACTS_ID", contactId);
		PageData contact = contactsService.findById(pd);
		contact.put("CONTACT", person);
		contact.put("CONTACT_TEL", phone);
		carinfoService.edit(contact);
		
		String bianma = "jiechefh";
		List<String> quanxian = getQuanxian(bianma, gsid);
		if(quanxian.size()>0){
			for (String username : quanxian) {
				sendYuanGong(username, "有接车单需要复核");
			}
		}
		
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Reception");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			receptionService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Reception到excel");
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
		titles.add("服务顾问");	//6
		titles.add("里程");	//7
		titles.add("状态");	//8
		titles.add("同意路试");	//9
		titles.add("授权凭证");	//10
		titles.add("备注");	//11
		titles.add("预留");	//12
		titles.add("预留");	//13
		dataMap.put("titles", titles);
		List<PageData> varOList = receptionService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("ORDER_ID"));	    //1
			vpd.put("var2", varOList.get(i).getString("CREATE_BY"));	    //2
			vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	    //3
			vpd.put("var4", varOList.get(i).getString("CHECKED_BY"));	    //4
			vpd.put("var5", varOList.get(i).getString("CHECKED_TIME"));	    //5
			vpd.put("var6", varOList.get(i).getString("SERVICE_ADVISOR"));	    //6
			vpd.put("var7", varOList.get(i).getString("MILEAGE"));	    //7
			vpd.put("var8", varOList.get(i).getString("STATUS"));	    //8
			vpd.put("var9", varOList.get(i).getString("IS_AGREE"));	    //9
			vpd.put("var10", varOList.get(i).getString("AUTHORIZATION_VOUCHER"));	    //10
			vpd.put("var11", varOList.get(i).getString("REMARK"));	    //11
			vpd.put("var12", varOList.get(i).getString("YL1"));	    //12
			vpd.put("var13", varOList.get(i).getString("YL2"));	    //13
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
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
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
