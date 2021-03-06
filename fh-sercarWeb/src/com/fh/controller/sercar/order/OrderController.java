package com.fh.controller.sercar.order;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Dictionaries;
import com.fh.service.sercar.car.CarManager;
import com.fh.service.sercar.carinfo.CarInfoManager;
import com.fh.service.sercar.contacts.ContactsManager;
import com.fh.service.sercar.dispatching.DispatchingManager;
import com.fh.service.sercar.dsmx.DsmxManager;
import com.fh.service.sercar.inforelay.InfoRelayManager;
import com.fh.service.sercar.luyin.LuyinManager;
import com.fh.service.sercar.order.OrderManager;
import com.fh.service.sercar.partoffer.PartOfferManager;
import com.fh.service.sercar.pomx.PomxManager;
import com.fh.service.sercar.purchase.PurchaseManager;
import com.fh.service.sercar.reception.ReceptionManager;
import com.fh.service.sercar.repoinfo.RepoinfoManager;
import com.fh.service.sercar.settlement.SettlementManager;
import com.fh.service.sercar.sname.SNameManager;
import com.fh.service.sercar.stype.STypeManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.service.system.user.UserManager;
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.my.MyTools;

import net.sf.json.JSONArray;

/** 
 * 说明：订单信息
 * 创建人：FH Q313596790
 * 创建时间：2018-04-07
 */
@Controller
@RequestMapping(value="/order")
public class OrderController extends BaseController {
	
	String menuUrl = "order/list.do"; //菜单地址(权限用)
	@Resource(name="orderService")
	private OrderManager orderService;
	@Resource(name="receptionService")
	private ReceptionManager receptionService;
	@Resource(name="inforelayService")
	private InfoRelayManager inforelayService;
	@Resource(name="dispatchingService")
	private DispatchingManager dispatchingService;
	@Resource(name="partofferService")
	private PartOfferManager partofferService;
	@Resource(name="purchaseService")
	private PurchaseManager purchaseService;
	@Resource(name="settlementService")
	private SettlementManager settlementService;
	@Resource(name="stypeService")
	private STypeManager stypeService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="carService")
	private CarManager carService;
	@Resource(name="carinfoService")
	private CarInfoManager carinfoService;
	@Resource(name="contactsService")
	private ContactsManager contactsService;
	@Resource(name="luyinService")
	private LuyinManager luyinService;
	@Resource(name="dsmxService")
	private DsmxManager dsmxService;
	@Resource(name="pomxService")
	private PomxManager pomxService;
	@Resource(name="repoinfoService")
	private RepoinfoManager repoinfoService;
	@Resource(name="snameService")
	private SNameManager snameService;
	
	
	/**
	 * 
	 */
	//获取车牌对应的信息
	@RequestMapping(value="/getCarInfo")
	@ResponseBody
	public PageData getCarInfo()throws Exception{
		Page page = new Page();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData> list = carinfoService.listByNum(page);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据姓名获取电话
	 */
	@RequestMapping(value="/getContants")
	@ResponseBody
	public PageData getContants() {
		try {
			Page page = new Page();
			PageData pd = new PageData();
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData> list = contactsService.list(page);
			if(list.size() == 1){
				return list.get(0);
			}
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(@RequestBody Reception data) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Order");
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
		
		String CAR_NUM1 = data.getCAR_NUM1();
		String CAR_NUM2 = data.getCAR_NUM2();
		String CAR_NUM3 = data.getCAR_NUM3();
		String brand = data.getYL20();
		String chexi = data.getYL19();
		String person = data.getYL18();
		String phone = data.getYL17();
		
		String photo_lc = data.getLcimg();
		String photo_yl = data.getYlimg();
		String reception_mileage = data.getRECEPTION_MILEAGE();
		String RECEPTION_yl = data.getRECEPTION_YL6();
		String agree = data.getRECEPTION_AGREE();
		String RECEPTION_wg = data.getYL1();
		String photo_wg = data.getWaiguantpList();
		String wc = data.getWc();
		
		
		//订单表的pd
		PageData order_pd = new PageData();
		
		//车辆信息id
		PageData carInfo = new PageData();
		Page carInfo_page = new Page();
		PageData carInfo_pd = new PageData();
		if(CAR_NUM1 != null && !"".equals(CAR_NUM1)){
			carInfo_pd.put("CAR_NUM1", CAR_NUM1);
			carInfo_page.setPd(carInfo_pd);
			List<PageData> list = carinfoService.listByNum(carInfo_page);
			if(list.size()>0){
				carInfo = list.get(0);
			}
		}
		
		if(carInfo == null || "".equals(carInfo)){
			String carId = MyTools.getDateR();
			carInfo.put("CARINFO_ID", carId);
			carInfo.put("CAR_NUM1", CAR_NUM1);
			carInfo.put("CAR_NUM2", CAR_NUM2);
			carInfo.put("CAR_NUM3", CAR_NUM3);
			carInfo.put("YL11", name);
			carInfo.put("CAR_BRANK", brand);
			carInfo.put("YL1", chexi);
			carInfo.put("CREATE_BY", user);
			carInfo.put("CREATE_TIME", DateUtil.getSdftime().format(new Date()));
			carinfoService.save(carInfo);
			order_pd.put("CAR_ID", carId);
		}else {
			carInfo.put("CAR_NUM1", CAR_NUM1);
			carInfo.put("CAR_NUM2", CAR_NUM2);
			carInfo.put("CAR_NUM3", CAR_NUM3);
			carInfo.put("YL11", name);
			carInfo.put("CAR_BRANK", brand);
			carInfo.put("YL1", chexi);
			carinfoService.edit(carInfo);
			order_pd.put("CAR_ID", carInfo.getString("CARINFO_ID"));
		}
		
		String orderId = MyTools.getDateR();
		order_pd.put("ORDER_ID", orderId);	//主键
		
		//人物信息id
		Page contact_page = new Page();
		PageData contact_pd = new PageData();
		contact_pd.put("CONTACT", person);
		contact_pd.put("CONTACT_TEL", phone);
		contact_pd.put("YL10", gsid);
		contact_page.setPd(contact_pd);
		List<PageData> contactsList = contactsService.findByPerson(contact_page);
		if(contactsList.size() == 0){
			String contactId = MyTools.getDateR();
			contact_pd.put("CONTACTS_ID", contactId);
			contact_pd.put("YL3", orderId);
			contact_pd.put("YL4", wc);
			contactsService.save(contact_pd);
			order_pd.put("CONTACT_ID", contactId);
		}else {
			order_pd.put("CONTACT_ID", contactsList.get(0).getString("CONTACTS_ID"));
		}
		
		//订单信息
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
								order_pd.put("YL1", fengmian) ;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
	    			}
	    		}
	        }
		}
		
		order_pd.put("CREATE_BY", user);//创建人
		order_pd.put("CREATE_TIME", DateUtil.getSdftime().format(new Date()));//创建时间
		order_pd.put("STATUS", "已创建接车单待提交复核");
		order_pd.put("CAR_NUM1", CAR_NUM1);
		order_pd.put("CAR_NUM2", CAR_NUM2);
		order_pd.put("CAR_NUM3", CAR_NUM3);
		order_pd.put("SERVICE_CONSULTANT", user);
		order_pd.put("IN_TIME", DateUtil.getSdftime().format(new Date()));
		order_pd.put("YL2", "1");
		order_pd.put("YL10", gsid);
		order_pd.put("YL11", name);	//主键
		order_pd.put("YL17", phone);
		order_pd.put("YL18", person);
		order_pd.put("YL19", chexi);
		order_pd.put("YL20", brand);
		orderService.save(order_pd);
		
		PageData reception_pd = new PageData();
		
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
					reception_pd.put("YL5", lcPhoto.getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
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
					reception_pd.put("YL7", ylPhoto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		//外观图片保存
		reception_pd.put("YL2", str.toString());
		
		String receptionId = MyTools.getDateR();
		
		reception_pd.put("RECEPTION_ID", receptionId);
		reception_pd.put("ORDER_ID", orderId);
		reception_pd.put("CREATE_BY", user);
		reception_pd.put("CREATE_TIME", DateUtil.getSdftime().format(new Date()));
		reception_pd.put("MILEAGE", reception_mileage);
		reception_pd.put("SERVICE_ADVISOR", name);
		reception_pd.put("STATUS", "待提交");
		reception_pd.put("IS_AGREE", agree);
		reception_pd.put("YL1", RECEPTION_wg);
		reception_pd.put("YL6", RECEPTION_yl);
		reception_pd.put("YL10", gsid);
		reception_pd.put("YL12", name);
		receptionService.save(reception_pd);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	//接车单提交复核
	@RequestMapping(value="/subreception")
	public void subReception(PrintWriter out) throws Exception{
		
		String user = Jurisdiction.getUsername();
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", user);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData reception = receptionService.findById(pd);
		reception.put("STATUS", "待复核");
		receptionService.edit(reception);
		
		PageData order_pd = new PageData();
		String orderId = reception.getString("ORDER_ID");
		order_pd.put("ORDER_ID", orderId);
		PageData order = orderService.findById(order_pd);
		order.put("STATUS", "接车单已提交待复核");
		orderService.edit(order);
		
		String bianma = "jiechefh";
		List<String> quanxian = getQuanxian(bianma, gsid);
		if(quanxian.size()>0){
			for (String username : quanxian) {
				sendYuanGong(username, "有接车单需要复核");
			}
		}
		
		out.write("success");
		out.close();
	}
	
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Order");
		PageData pd = new PageData();
		pd = this.getPageData();
		orderService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Order");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		orderService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Order");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd2 = new PageData();
		String username = Jurisdiction.getUsername();
		List<PageData>	varList = new ArrayList<PageData>();
		if(!"admin".equals(username)){
			pd = this.getPageData();
			pd.put("BIANMA", "dingdan");//获取数据字典的字节码
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
			
			if(pd.getString("lastStart")==null && pd.getString("lastEnd")==null && pd.getString("keywords")==null && pd.getString("name")==null && pd.getString("status")==null){
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
			pd.put("COMPANY_ID", comid);
			List<PageData> listAll = stypeService.listAll(pd);
			mv.addObject("list2",listAll);
			page.setPd(pd);
			List<PageData> list = orderService.list(page);	//列出Order列表
			
			Page page_sett = new Page();
			PageData pd_sett = new PageData();
			for (PageData order : list) {
				String orderId = order.getString("ORDER_ID");
				pd_sett.put("ORDER_ID", orderId);
				page_sett.setPd(pd_sett);
				List<PageData> settList = settlementService.findByCondition(page_sett);
				if(settList.size()>0){
					PageData sett = settList.get(0);
					order.put("sett", sett);
					varList.add(order);
				}else{
					varList.add(order);
				}
			}
			mv.addObject("pd", pd);
		}else{
			pd2 = this.getPageData();
			pd2.put("BIANMA", "dingdan");//获取数据字典的字节码
			PageData findByBianma2 = dictionariesService.findByBianma(pd2);
			if(findByBianma2 != null){
				String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
				List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
				mv.addObject("list1", varList2);
			}
			
			List<PageData> listAll = stypeService.listAll(pd2);
			mv.addObject("list2",listAll);   
			
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
			
			if(pd2.getString("lastStart")==null && pd2.getString("lastEnd")==null && pd2.getString("keywords")==null && pd2.getString("name")==null && pd2.getString("status")==null){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				pd2.put("lastStart", df.format(new Date())+" 00:00:00");
				pd2.put("lastEnd", df.format(new Date())+" 23:59:59");				
			}			
			page.setPd(pd2);
			List<PageData> list = orderService.list(page);	//列出Order列表
			Page page_sett = new Page();
			PageData pd_sett = new PageData();
			for (PageData order : list) {
				String orderId = order.getString("ORDER_ID");
				pd_sett.put("ORDER_ID", orderId);
				page_sett.setPd(pd_sett);
				List<PageData> settList = settlementService.findByCondition(page_sett);
				if(settList.size()>0){
					PageData sett = settList.get(0);
					order.put("sett", sett);
					varList.add(order);
				}else{
					varList.add(order);
				}
			}
			mv.addObject("pd", pd2);
		}
		
		
		mv.setViewName("sercar/order/order_list");
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
		List<PageData> listAll = carService.listAll(pd);
		List<String> list = new ArrayList<String>();
		for (PageData pageData : listAll) {
			String pingpai = pageData.getString("PINGPAI");
			list.add(pingpai);
		}
		List<String> removeDuplicate = Tools.removeDuplicate(list);
		mv.addObject("list",removeDuplicate);
		mv.setViewName("sercar/order/order_add");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	//获取品牌对应车系
	@RequestMapping(value="/getCheXi")
	@ResponseBody
	public List<String> getCheXi(String brand)throws Exception{
		Page page = new Page();
		PageData pd = new PageData();
		pd.put("keywords", brand);
		page.setPd(pd);
		List<PageData> list = carService.list(page);
		List<String> cxList = new ArrayList<String>();
		for (PageData pageData : list) {
			String chexi = pageData.getString("CHEXI");
			cxList.add(chexi);
		}
		return cxList;
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
		pd = orderService.findById(pd);	//根据ID读取
		pd.put("COMPANY_ID", pd.getString("YL10"));
		List<PageData> listAll = stypeService.listAll(pd);
		mv.addObject("list2",listAll);
		
		pd.put("BIANMA", "dingdan");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("list1", varList2);
		}
		
		mv.setViewName("sercar/order/order_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 查看订单的详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getOrder")
	public ModelAndView getOrder()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData order = orderService.findById(pd);	//根据ID读取
		String orderId = order.getString("ORDER_ID");
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
		
		//接车信息单
		Page page_reception = new Page();
		PageData pd_reception = new PageData();
		pd_reception.put("ORDER_ID", orderId);
		page_reception.setPd(pd_reception);
		List<PageData> list_reception = receptionService.findByCondition(page_reception);
		if(list_reception.size()>0){
			PageData reception = list_reception.get(0);
			String wg_photo = reception.getString("YL2");
			String[] photos = wg_photo.split("\\*\\*\\*");
			mv.addObject("reception", reception);
			mv.addObject("photo", photos);
		}
		
		//查出信息反馈
		Page page_luyin = new Page();
		PageData pd_luyin = new PageData();
		List<PageData> inforelays = new ArrayList<PageData>();
		
		Page page_inforelay = new Page();
		PageData pd_inforelay = new PageData();
		pd_inforelay.put("ORDER_ID", orderId);
		page_inforelay.setPd(pd_inforelay);
		List<PageData> list_inforelay = inforelayService.findByCondition(page_inforelay);
		for (PageData infore : list_inforelay) {
			List<String> luyin = new ArrayList<String>();
			String inforelayId = infore.getString("INFORELAY_ID");
			pd_luyin.put("INFORELAYID", inforelayId);
			page_luyin.setPd(pd_luyin);
			List<PageData> list_luyin = luyinService.findByCondition(page_luyin);
			for (PageData ly : list_luyin) {
				String lypath = ly.getString("YL2");
				luyin.add(lypath);
			}
			infore.put("luyin", luyin);
			inforelays.add(infore);
		}
		mv.addObject("list_inforelay", inforelays);
		
		//查出报价单
		Page page_pomx = new Page();
		PageData pd_pomx = new PageData();
		
		Page page_partdsmx = new Page();
		PageData pd_partdsmx = new PageData();
		
		Page page_partoffer = new Page();
		PageData pd_partoffer = new PageData();
		pd_partoffer.put("ORDER_ID", orderId);
		page_partoffer.setPd(pd_partoffer);
		List<PageData> list_partoffer = partofferService.findByCondition(page_partoffer);
		
		for (PageData partoffer : list_partoffer) {
			String partofferId = partoffer.getString("PARTOFFER_ID");
			pd_pomx.put("PART_OFFER_ID", partofferId);
			page_pomx.setPd(pd_pomx);
			List<PageData> pomxs = pomxService.findByCondition(page_pomx);
			if(pomxs.size()>0){
				partoffer.put("list_pomx", pomxs);
			}
			pd_partdsmx.put("YL1", partofferId);
			page_partdsmx.setPd(pd_partdsmx);
			List<PageData> dsmxs = dsmxService.findByCondition(page_partdsmx);
			if(dsmxs.size() > 0 ){
				partoffer.put("list_partdsmx", dsmxs);
			}
		}
		mv.addObject("list_partoffer", list_partoffer);
		
		//查出派工单
		Page page_dispatching = new Page();
		PageData pd_dispatching = new PageData();
		pd_dispatching.put("ORDER_ID", orderId);
		page_dispatching.setPd(pd_dispatching);
		List<PageData> list_dispatching = dispatchingService.findByCondition(page_dispatching);
		mv.addObject("list_dispatching", list_dispatching);
		
		//查出详细的派工单
		List<PageData> list_dsmx = new ArrayList<PageData>();
		Page page_dsmx = new Page();
		PageData pd_dsmx = new PageData();
		for (PageData dispatching : list_dispatching) {
			String dispatchingId = dispatching.getString("DISPATCHING_ID");
			pd_dsmx.put("DISPATCHING_ID", dispatchingId);
			page_dsmx.setPd(pd_dsmx);
			List<PageData> dsmxs = dsmxService.findByCondition(page_dsmx);
			for (PageData pageData : dsmxs) {
				list_dsmx.add(pageData);
			}
		}
		mv.addObject("list_dsmx", list_dsmx);
		
		//查出结算单
		Page page_settlement = new Page();
		PageData pd_settlement = new PageData();
		pd_settlement.put("ORDER_ID", orderId);
		page_settlement.setPd(pd_settlement);
		List<PageData> list_settlement = settlementService.findByCondition(page_settlement);
		if(list_settlement.size()>0){
			PageData settlement = list_settlement.get(0);
			mv.addObject("settlement", settlement);
			
			//配件
			List<PageData> list_settPomx = new ArrayList<PageData>();
			PageData pd_settPomx = new PageData();
			String pomxIdStr = settlement.getString("YL4");
			if(pomxIdStr.contains("-")){
				String[] split = pomxIdStr.split("-");
				for (String pomxId : split) {
					if(pomxId != null && !"".equals(pomxId)){
						pd_settPomx.put("POMX_ID", pomxId);
						PageData pomx = pomxService.findById(pd_settPomx);
						list_settPomx.add(pomx);
					}
				}
			}else{
				pd_settPomx.put("POMX_ID", pomxIdStr);
				PageData pomx = pomxService.findById(pd_settPomx);
				list_settPomx.add(pomx);
			}
			mv.addObject("list_settPomx",list_settPomx);
			
			//派工
			List<PageData> list_settDsmx = new ArrayList<PageData>();
			PageData pd_settDsmx = new PageData();
			String dsmxIdStr = settlement.getString("YL3");
			if(dsmxIdStr.contains("-")){
				String[] split = dsmxIdStr.split("-");
				for (String dsmxId : split) {
					if(dsmxId != null && !"".equals(dsmxId)){
						pd_settDsmx.put("DSMX_ID", dsmxId);
						PageData dsmx = dsmxService.findById(pd_settDsmx);
						list_settDsmx.add(dsmx);
					}
				}
			}else{
				pd_settPomx.put("DSMX_ID", dsmxIdStr);
				PageData dsmx = dsmxService.findById(pd_settDsmx);
				list_settDsmx.add(dsmx);
			}
			mv.addObject("list_settDsmx",list_settDsmx);
		}
		
		mv.setViewName("sercar/order/order_details");
		return mv;
	}
	
	/**
	 * 新增接车单明细
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value="/addReception")
//	public ModelAndView addReception() throws Exception{
//		String user = Jurisdiction.getUsername();
//		logBefore(logger, user+"新增addReception");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
//		ModelAndView mv = this.getModelAndView();
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		pd.put("RECEPTION_ID", this.get32UUID());	//主键
//		pd.put("CREATE_BY", user);
//		pd.put("CREATE_TIME", new Date());
//		receptionService.save(pd);
//		mv.addObject("msg","success");
//		mv.setViewName("save_result");
//		return mv;
//	}
	
	/**
	 * 新增信息反馈单明细页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goAddInforelay")
	public ModelAndView goAddInforelay()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = orderService.findById(pd);	//根据ID读取
		mv.setViewName("sercar/order/Inforelay_add");
		mv.addObject("msg", "addInforelay");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 新增信息反馈单明细
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addInforelay")
	public ModelAndView addInforelay() throws Exception{
		String user = Jurisdiction.getUsername();
		logBefore(logger, user+"新增addPartoffer");
		ModelAndView mv = this.getModelAndView();
		
		//获取当前登录人的公司id
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", user);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		String name = findByUsername.getString("NAME");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData order = orderService.findById(pd);
		
		pd.put("INFORELAY_ID", MyTools.getDateR());	//主键
		pd.put("CREATE_BY", user);
		pd.put("CREATE_TIME", new Date());
		pd.put("STATUS", "待提交");
		pd.put("YL10", gsid);
		pd.put("YL13", name);
		inforelayService.save(pd);
		
		order.put("STATUS", "信息反馈单待提交复核");
		orderService.edit(order);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 信息反馈单提交复核 
	 */
	@RequestMapping(value="/subinforelay")
	public void subInforelay(PrintWriter out) throws Exception{
		
		String user = Jurisdiction.getUsername();
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", user);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData inforelay = inforelayService.findById(pd);
		inforelay.put("STATUS", "待复核");
		inforelayService.edit(inforelay);
		
		PageData order_pd = new PageData();
		String orderId = inforelay.getString("ORDER_ID");
		order_pd.put("ORDER_ID", orderId);
		PageData order = orderService.findById(order_pd);
		order.put("STATUS", "信息反馈单已提交待复核");
		orderService.edit(order);
		
		String bianma = "xinxifh";
		List<String> quanxian = getQuanxian(bianma, gsid);
		if(quanxian.size()>0){
			for (String str : quanxian) {
				sendYuanGong(str, "有消息反馈单需要复核");
			}
		}
		
		out.write("success");
		out.close();
	}
	
	
	/**
	 * 新增配件报价单明细页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goAddPartofferPomx")
	public ModelAndView goAddPartoffer()throws Exception{
		ModelAndView mv = this.getModelAndView();
		String user = Jurisdiction.getUsername();
		//获取当前登录人的公司id
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", user);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String name = findByUsername.getString("NAME");
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = orderService.findById(pd);	//根据ID读取
		mv.setViewName("sercar/order/partoffer_addPomx");
		mv.addObject("msg", "addPartofferPomx");
		mv.addObject("YL12",name);
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 根据关键字查信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getRepoInfo")
	@ResponseBody
	public List<PageData> getRepoInfo() throws Exception{
		
		PageData pd = new PageData();
		pd = this.getPageData();
		Page page = new Page();
		page.setPd(pd);
		List<PageData> list = repoinfoService.findByCondition(page);
		return list;
	}
	
	/**
	 * 保存新建的配件报价单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/savePartofferPomx")
	@ResponseBody
	public ModelAndView savePartofferPomx() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Order");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String username = Jurisdiction.getUsername();
		String orderId = pd.getString("orderId");
		String baojiaoPerson = pd.getString("baojiaoPerson");
		String gongsiId = pd.getString("gongsiId");
		String remark = pd.getString("remark");
		
		String pomxString = pd.getString("pomxs");
		JSONArray array = JSONArray.fromObject(pomxString);
		
		String partofferId = MyTools.getDateR();
		double price = 0;
		double price_dj = 0;
		
		PageData pd_pomx = new PageData();
		for (int i=0; i< array.size(); i++) {
			JSONObject jsonObject = JSONObject.parseObject(array.getString(i));
			Map map = (Map)jsonObject;
			String partName = map.get("partName").toString();
			String partCount = map.get("partCount").toString();
			String partNum = map.get("partNum").toString();
			String dijiaprice = map.get("dijiaprice").toString();
			String huoqitype = map.get("huoqitype").toString();
			String huiqitime = map.get("huiqitime").toString();
			String chushouprice = map.get("chushouprice").toString();
			String dingjinprice = map.get("dingjinprice").toString();
			
			Page page_repoinfo = new Page();
			PageData pd_repoinfo = new PageData();
			pd_repoinfo.put("NAME", partName);
			pd_repoinfo.put("BIANHAO", partNum);
			page_repoinfo.setPd(pd_repoinfo);
			List<PageData> list = repoinfoService.findByCondition(page_repoinfo);
			if(list.size() == 0){
				String repoinfoId = MyTools.getDateR();
				pd_pomx.put("YL9", repoinfoId);
				
				pd_repoinfo.put("REPOINFO_ID", repoinfoId);
				pd_repoinfo.put("TYPE", "零配件");
				pd_repoinfo.put("NUMBER", "0");
				pd_repoinfo.put("PRICE", dijiaprice);
				repoinfoService.save(pd_repoinfo);
			}else {
				String repoinfoId = list.get(0).getString("REPOINFO_ID");
				pd_pomx.put("YL9", repoinfoId);
			}
			
			double price_pomx = Double.parseDouble(chushouprice) * Double.parseDouble(partCount);
			price = price + price_pomx;
			if(dingjinprice == null || "".equals(dingjinprice)){
				dingjinprice = "0";
			}
			price_dj = price_dj + Double.parseDouble(dingjinprice);
			
			pd_pomx.put("POMX_ID", MyTools.getDateR());
			pd_pomx.put("PART_OFFER_ID", partofferId);
			pd_pomx.put("PART_NAME", partName);
			pd_pomx.put("PART_COUNT", partCount);
			pd_pomx.put("PART_NUM", partNum);
			pd_pomx.put("PRICE", dijiaprice);
			pd_pomx.put("TOTAL", price_pomx);
			pd_pomx.put("YL2", orderId);
			pd_pomx.put("YL6", huoqitype);
			pd_pomx.put("YL15", "待使用");
			if(huiqitime != null && !"".equals(huiqitime)){
				pd_pomx.put("YL7", huiqitime);
			}else {
				pd_pomx.put("YL7", "0");
			}
			pd_pomx.put("YL8", chushouprice);
			pd_pomx.put("YL10", gongsiId);
			pd_pomx.put("YL12", baojiaoPerson);
			pd_pomx.put("YL16", dingjinprice);
			pomxService.save(pd_pomx);
			
		}
		
		PageData pd_partoffer = new PageData();
		pd_partoffer.put("PARTOFFER_ID", partofferId);
		pd_partoffer.put("ORDER_ID", orderId);
		pd_partoffer.put("STATUS", "待提交");
		pd_partoffer.put("CREATE_BY", username);
		pd_partoffer.put("CREATE_TIME", DateUtil.getSdftime().format(new Date()));
		pd_partoffer.put("OFFER_BY", username);
		pd_partoffer.put("TOTAL", price);
		pd_partoffer.put("YL3", "2");
		pd_partoffer.put("YL8", price_dj);
		pd_partoffer.put("YL10", gongsiId);
		pd_partoffer.put("YL12", baojiaoPerson);
		pd_partoffer.put("REMARK", remark);
		partofferService.save(pd_partoffer);
		
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		String yl8 = order.getString("YL8");
		if(yl8 == null || "".equals(yl8)){
			yl8 = "0";
		}
		double zj = Double.parseDouble(yl8) + price_dj;
		order.put("YL8", zj);
		order.put("STATUS", "已创建报价单待提交复核");
		order.put("YL2", "1");
		orderService.edit(order);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 去工时报价页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goAddPartofferDsmx")
	public ModelAndView goAddPartofferDsmx()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = orderService.findById(pd);	//根据ID读取
		
		String gsId = pd.getString("YL10");
		
		String user = Jurisdiction.getUsername();
		//获取当前登录人的公司id
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", user);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String name = findByUsername.getString("NAME");
		
		Page page_stype = new Page();
		PageData pd_stype = new PageData();
		pd_stype.put("COMPANY_ID", gsId);
		page_stype.setPd(pd_stype);
		List<PageData> list = stypeService.findByCondition(page_stype);
		
		mv.setViewName("sercar/order/partoffer_addDsmx");
		mv.addObject("msg", "addPartofferDsmx");
		mv.addObject("name", name);
		mv.addObject("list", list);
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 根据类型找出项目名称
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getSName")
	@ResponseBody
	public List<PageData> getSName() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String sname = pd.getString("keywords");
		String gsId = pd.getString("YL10");
		String stypeId = pd.getString("type");
		
		Page page_sname = new Page();
		PageData pd_sname = new PageData();
		pd_sname.put("PID", stypeId);
		pd_sname.put("YL10", gsId);
		pd_sname.put("S_NAME", sname);
		page_sname.setPd(pd_sname);
		List<PageData> list = snameService.findByCondition(page_sname);
		return list;
	}
	
	/**
	 * 新增工时报价
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/savePartofferDsmx")
	public ModelAndView savePartofferDsmx() throws Exception{
		String user = Jurisdiction.getUsername();
		logBefore(logger, user+"新增addPurchase");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String username = Jurisdiction.getUsername();
		String orderId = pd.getString("orderId");
		String baojiaoPerson = pd.getString("baojiaoPerson");
		String gongsiId = pd.getString("gongsiId");
		String typeid = pd.getString("type");
		String remark = pd.getString("remark");
		
		PageData pd_stype = new PageData();
		pd_stype.put("STYPE_ID", typeid);
		PageData stype = stypeService.findById(pd_stype);
		String stype_name = stype.getString("TYPE_NAME");
		
		String dsmxsString = pd.getString("dsmxs");
		JSONArray array = JSONArray.fromObject(dsmxsString);
		
		String partofferId = MyTools.getDateR();
		double price = 0;
		
		for (int i=0; i< array.size(); i++) {
			JSONObject jsonObject = JSONObject.parseObject(array.getString(i));
			Map map = (Map)jsonObject;
			String dsmx_name = map.get("partName").toString();
			String dsmx_price = map.get("DSMX_PRICE").toString();
			String dsmx_id = map.get("DSMX_ID").toString();
			
			price = price + Double.parseDouble(dsmx_price);
			
			if(dsmx_id == null || "".equals(dsmx_id)){
				String id = MyTools.getDateR();
				PageData pd_sname = new PageData();
				pd_sname.put("SNAME_ID", id);
				pd_sname.put("PID", typeid);
				pd_sname.put("S_NAME", dsmx_name);
				pd_sname.put("YL1", dsmx_price);
				pd_sname.put("YL10", gongsiId);
				snameService.save(pd_sname);
			}
			
			PageData pd_dsmx = new PageData();
			pd_dsmx.put("DSMX_ID", MyTools.getDateR());
			pd_dsmx.put("WORK_NAME", dsmx_name);
			pd_dsmx.put("YL1", partofferId);
			pd_dsmx.put("YL2", orderId);
			pd_dsmx.put("YL3", dsmx_price);
			pd_dsmx.put("YL4", DateUtil.getSdftime().format(new Date()));
			pd_dsmx.put("YL6", dsmx_price);
			pd_dsmx.put("YL7", stype_name);
			pd_dsmx.put("YL10", gongsiId);
			pd_dsmx.put("YL15", "待完成");
			dsmxService.save(pd_dsmx);
		}
		
		PageData pd_partoffer = new PageData();
		pd_partoffer.put("PARTOFFER_ID", partofferId);
		pd_partoffer.put("ORDER_ID", orderId);
		pd_partoffer.put("STATUS", "待提交");
		pd_partoffer.put("CREATE_BY", username);
		pd_partoffer.put("CREATE_TIME", DateUtil.getSdftime().format(new Date()));
		pd_partoffer.put("OFFER_BY", username);
		pd_partoffer.put("TOTAL", price);
		pd_partoffer.put("YL3", "1");
		pd_partoffer.put("YL8", "0");
		pd_partoffer.put("YL10", gongsiId);
		pd_partoffer.put("YL12", baojiaoPerson);
		pd_partoffer.put("REMARK", remark);
		partofferService.save(pd_partoffer);
		
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		order.put("STATUS", "已创建报价单待提交复核");
		orderService.edit(order);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 新增派工单明细页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goAddDispatch")
	public ModelAndView goAddDispatch()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = orderService.findById(pd);	//根据ID读取
		
		String gsId = pd.getString("YL10");
		Page page_stype = new Page();
		PageData pd_stype = new PageData();
		pd_stype.put("COMPANY_ID", gsId);
		page_stype.setPd(pd_stype);
		List<PageData> list = stypeService.findByCondition(page_stype);
		
		
		mv.setViewName("sercar/order/dispatch_add");
		mv.addObject("list", list);
		mv.addObject("msg", "addDispatch");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 新增派工单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveDispatching")
	public ModelAndView saveDispatching() throws Exception{
		String username = Jurisdiction.getUsername();
		logBefore(logger, username+"新增addPurchase");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String orderId = pd.getString("orderId");
		String gongsiId = pd.getString("gongsiId");
		String typeid = pd.getString("type");
		String jishi = pd.getString("jishi");
		String workstart = pd.getString("workstart");
		String workend = pd.getString("workend");
		String remark = pd.getString("remark");
		
		//获取类型
		PageData pd_stype = new PageData();
		pd_stype.put("STYPE_ID", typeid);
		PageData stype = stypeService.findById(pd_stype);
		String stype_name = stype.getString("TYPE_NAME");
		
		//获取技师信息
		PageData pd_userinfo = new PageData();
		pd_userinfo.put("USERNAME", jishi);
		PageData data_jishi = userinfoService.findByUsername(pd_userinfo);
		String jsName = data_jishi.getString("NAME");
		
		//获取创建人信息
		pd_userinfo.put("USERNAME", username);
		PageData data_user = userinfoService.findByUsername(pd_userinfo);
		String uName = data_user.getString("NAME");
		
		String dsmxsString = pd.getString("dsmxs");
		JSONArray array = JSONArray.fromObject(dsmxsString);
		
		String dispatchId = MyTools.getDateR();
		double price = 0;
		
		for (int i=0; i< array.size(); i++) {
			JSONObject jsonObject = JSONObject.parseObject(array.getString(i));
			Map map = (Map)jsonObject;
			String dsmx_name = map.get("partName").toString();
			String dsmx_price = map.get("DSMX_PRICE").toString();
			String dsmx_id = map.get("DSMX_ID").toString();
			
			price = price + Double.parseDouble(dsmx_price);
			
			if(dsmx_id == null || "".equals(dsmx_id)){
				String id = MyTools.getDateR();
				PageData pd_sname = new PageData();
				pd_sname.put("SNAME_ID", id);
				pd_sname.put("PID", typeid);
				pd_sname.put("S_NAME", dsmx_name);
				pd_sname.put("YL1", dsmx_price);
				pd_sname.put("YL10", gongsiId);
				snameService.save(pd_sname);
			}
			
			PageData pd_dsmx = new PageData();
			pd_dsmx.put("DSMX_ID", MyTools.getDateR());
			pd_dsmx.put("WORK_NAME", dsmx_name);
			pd_dsmx.put("DISPATCHING_ID", dispatchId);
			pd_dsmx.put("YL2", orderId);
			pd_dsmx.put("YL3", dsmx_price);
			pd_dsmx.put("YL4", DateUtil.getSdftime().format(new Date()));
			pd_dsmx.put("YL5", username);
			pd_dsmx.put("YL6", dsmx_price);
			pd_dsmx.put("YL7", stype_name);
			pd_dsmx.put("YL10", gongsiId);
			pd_dsmx.put("YL13", jsName);
			pd_dsmx.put("YL15", "待完成");
			dsmxService.save(pd_dsmx);
		}
		
		PageData pd_dispatch = new PageData();
		pd_dispatch.put("DISPATCHING_ID", dispatchId);
		pd_dispatch.put("ORDER_ID", orderId);
		pd_dispatch.put("CREATE_BY", username);
		pd_dispatch.put("CREATE_TIME", DateUtil.getSdftime().format(new Date()));
		pd_dispatch.put("MAINTENANCE_TECHNICIAN", jishi);
		pd_dispatch.put("STATUS", "待提交");
		pd_dispatch.put("YL2", jsName);
		pd_dispatch.put("YL3", typeid);
		pd_dispatch.put("YL4", price);
		pd_dispatch.put("YL5", workstart +" --- "+workend);
		pd_dispatch.put("YL7", stype_name);
		pd_dispatch.put("YL10", gongsiId);
		pd_dispatch.put("YL12", uName);
		pd_dispatch.put("REMARK", remark);
		dispatchingService.save(pd_dispatch);
		
		PageData order_pd = new PageData();
		order_pd.put("ORDER_ID", orderId);
		PageData order = orderService.findById(order_pd);
		order.put("STATUS", "已创建派工单待提交复核");
		orderService.edit(order);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 提交派工单 subdispatching
	 */
	@RequestMapping(value="/subdispatching")
	public void subDispatching(PrintWriter out) throws Exception{
		
		String user = Jurisdiction.getUsername();
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", user);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData dispatching = dispatchingService.findById(pd);
		dispatching.put("STATUS", "待复核");
		dispatchingService.edit(dispatching);
		
		PageData order_pd = new PageData();
		String orderId = dispatching.getString("ORDER_ID");
		order_pd.put("ORDER_ID", orderId);
		PageData order = orderService.findById(order_pd);
		order.put("STATUS", "派工单已提交待复核");
		orderService.edit(order);
		
		String bianma = "paigongfh";
		List<String> quanxian = getQuanxian(bianma, gsid);
		if(quanxian.size()>0){
			for (String str : quanxian) {
				sendYuanGong(str, "有派工单需要复核");
			}
		}

		out.write("success");
		out.close();
	}
	
	/**
	 * 新增结算单明细页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goAddSettlement")
	public ModelAndView goAddSettlement()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = orderService.findById(pd);	//根据ID读取
		String orderid = pd.getString("ORDER_ID");
		
		//获取当前登录人的姓名
		String username = Jurisdiction.getUsername();
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String name = findByUsername.getString("NAME");
		
		//获取报价单
		Page page_partoffer = new Page();
		PageData pd_partoffer = new PageData();
		
		pd_partoffer.put("ORDER_ID", orderid);
		page_partoffer.setPd(pd_partoffer);
		List<PageData> list_partoffer = partofferService.findByCondition(page_partoffer);
		
		Page page_pomx = new Page();
		PageData pd_pomx = new PageData();
		Page page_dsmx = new Page();
		PageData pd_dsmx = new PageData();
		List<PageData> list_pomx = new ArrayList<PageData>();
		List<PageData> list_dsmx = new ArrayList<PageData>();
		for (PageData partoffer : list_partoffer) {
			String partId = partoffer.getString("PARTOFFER_ID");
			pd_pomx.put("PART_OFFER_ID", partId);
			page_pomx.setPd(pd_pomx);
			List<PageData> listpo = pomxService.findByCondition(page_pomx);
			list_pomx.addAll(listpo);
			
			pd_dsmx.put("YL1", partId);
			page_dsmx.setPd(pd_dsmx);
			List<PageData> listds = dsmxService.findByCondition(page_dsmx);
			list_dsmx.addAll(listds);
		}
		
		mv.setViewName("sercar/order/settlement_add");
		mv.addObject("name", name);
		mv.addObject("msg", "addSettlement");
		mv.addObject("pomxlist", list_pomx);
		mv.addObject("dsmxlist", list_dsmx);
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 获取到对应的维修技师
	 */
	@RequestMapping(value="/getJiShi")
	@ResponseBody
	public List<PageData> getJiShi()throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String roleType = pd.getString("roleType");
		String gsId = pd.getString("gsId");
		
		Page page_userinfo = new Page();
		PageData pd_userinfo = new PageData();
		pd_userinfo.put("ROLETYPE", roleType);
		pd_userinfo.put("YL1", gsId);
		page_userinfo.setPd(pd_userinfo);
		List<PageData> list = userinfoService.findByCondition(page_userinfo);
		return list;
	}
	
	/**
	 * 新增结算单明细
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addSettlement")
	public ModelAndView addSettlement() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增addSettlement");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String orderId = pd.getString("orderId");
		String gongsiId = pd.getString("gongsiId");
		String name = pd.getString("name");
		
		String dsmxsString = pd.getString("dsmxs");
		JSONArray array = JSONArray.fromObject(dsmxsString);
		
		PageData pd_dsmx = new PageData();
		String yl3 = "";
		double price_dsmx = 0;
		
		for (Object obj : array) {
			String dsmxId = obj.toString();
			yl3 = yl3 + dsmxId + "-";
			pd_dsmx.put("DSMX_ID", dsmxId);
			PageData dsmx = dsmxService.findById(pd_dsmx);
			String money = dsmx.getString("YL6");
			price_dsmx = Double.parseDouble(money) + price_dsmx;
		}
		
		String pomxsString = pd.getString("pomxs");
		JSONArray arrayp = JSONArray.fromObject(pomxsString);
		
		PageData pd_pomx = new PageData();
		String yl4 = "";
		double price_pomx = 0;
		
		for (Object obj : arrayp) {
			String pomxId = obj.toString();
			yl4 = yl4 + pomxId + "-";
			pd_pomx.put("POMX_ID", pomxId);
			PageData pomx = pomxService.findById(pd_pomx);
			String money = pomx.getString("TOTAL");
			price_pomx = price_pomx + Double.parseDouble(money);
		}
		
		double price_dispatch = 0;
		PageData pd_dispatch = new PageData();
		Page page_dispatch = new Page();
		pd_dispatch.put("ORDER_ID", orderId);
		page_dispatch.setPd(pd_dispatch);
		List<PageData> dispatchs = dispatchingService.findByCondition(page_dispatch);
		for (PageData pageData : dispatchs) {
			String money = pageData.getString("YL4");
			price_dispatch = price_dispatch + Double.parseDouble(money);			
		}
		
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		String dj_shiji = order.getString("YL9");
		String dj_butui = order.getString("YL12");
		String dj_tuihuan = order.getString("YL13");
		if(dj_shiji == null || "".equals(dj_shiji)){
			dj_shiji = "0";
		}
		if(dj_butui == null || "".equals(dj_butui)){
			dj_butui = "0";
		}
		if(dj_tuihuan == null || "".equals(dj_tuihuan)){
			dj_tuihuan = "0";
		}
		
		double price_sett = price_dsmx + price_pomx;
		double price = price_sett + Double.parseDouble(dj_butui) - Double.parseDouble(dj_shiji);
		
		
		PageData pd_sett = new PageData();
		pd_sett.put("SETTLEMENT_ID", MyTools.getDateR());
		pd_sett.put("ORDER_ID", orderId);
		pd_sett.put("CREATE_BY", Jurisdiction.getUsername());
		pd_sett.put("CREATE_TIME", MyTools.getTime());
		pd_sett.put("TOTAL", price);
		pd_sett.put("STATUS", "待提交");
		pd_sett.put("YL2", price_dispatch + price_pomx);
		pd_sett.put("YL3", yl3);
		pd_sett.put("YL4", yl4);
		pd_sett.put("YL5", "100%");
		pd_sett.put("YL6", "100%");
		pd_sett.put("YL9", dj_shiji);
		pd_sett.put("YL10", gongsiId);
		pd_sett.put("YL12", name);
		pd_sett.put("YL13", dj_tuihuan);
		settlementService.save(pd_sett);
		
		order.put("STATUS", "已创建结算单待提交复核");
		orderService.edit(order);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	/**
	 * 提交结算单 subsettlement
	 */
	@RequestMapping(value="/subsettlement")
	public void subSettlement(PrintWriter out) throws Exception{
		
		String user = Jurisdiction.getUsername();
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", user);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData settlement = settlementService.findById(pd);
		settlement.put("STATUS", "待复核");
		settlementService.edit(settlement);
		
		PageData order_pd = new PageData();
		String orderId = settlement.getString("ORDER_ID");
		order_pd.put("ORDER_ID", orderId);
		PageData order = orderService.findById(order_pd);
		order.put("STATUS", "结算单已提交待复核");
		orderService.edit(order);
		
		String bianma = "jiesuanfh";
		List<String> quanxian = getQuanxian(bianma, gsid);
		if(quanxian.size()>0){
			for (String str : quanxian) {
				sendYuanGong(str, "有结算单需要复核");
			}
		}

		out.write("success");
		out.close();
	}
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Order");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			orderService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Order到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("创建人");	//1
		titles.add("创建时间");	//2
		titles.add("状态");	//3
		titles.add("车辆ID");	//4
		titles.add("车牌号");	//5
		titles.add("车架号");	//6
		titles.add("发动机号");	//7
		titles.add("服务类型");	//8
		titles.add("联系人ID");	//9
		titles.add("服务顾问");	//10
		titles.add("总检查员");	//11
		titles.add("配件合计");	//12
		titles.add("工时合计");	//13
		titles.add("合计");	//14
		titles.add("入厂时间");	//15
		titles.add("出厂时间");	//16
		titles.add("备注");	//17
		titles.add("预留");	//18
		titles.add("预留");	//19
		titles.add("预留");	//20
		titles.add("预留");	//21
		dataMap.put("titles", titles);
		List<PageData> varOList = orderService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("CREATE_BY"));	    //1
			vpd.put("var2", varOList.get(i).getString("CREATE_TIME"));	    //2
			vpd.put("var3", varOList.get(i).getString("STATUS"));	    //3
			vpd.put("var4", varOList.get(i).getString("CAR_ID"));	    //4
			vpd.put("var5", varOList.get(i).getString("CAR_NUM1"));	    //5
			vpd.put("var6", varOList.get(i).getString("CAR_NUM2"));	    //6
			vpd.put("var7", varOList.get(i).getString("CAR_NUM3"));	    //7
			vpd.put("var8", varOList.get(i).getString("SERVICE_TYPE"));	    //8
			vpd.put("var9", varOList.get(i).getString("CONTACT_ID"));	    //9
			vpd.put("var10", varOList.get(i).getString("SERVICE_CONSULTANT"));	    //10
			vpd.put("var11", varOList.get(i).getString("INSPECTOR"));	    //11
			vpd.put("var12", varOList.get(i).getString("PART_TOTAL"));	    //12
			vpd.put("var13", varOList.get(i).getString("HOURS_TOTAL"));	    //13
			vpd.put("var14", varOList.get(i).getString("TOTAL"));	    //14
			vpd.put("var15", varOList.get(i).getString("IN_TIME"));	    //15
			vpd.put("var16", varOList.get(i).getString("OUT_TIME"));	    //16
			vpd.put("var17", varOList.get(i).getString("REMARK"));	    //17
			vpd.put("var18", varOList.get(i).getString("YL1"));	    //18
			vpd.put("var19", varOList.get(i).getString("YL2"));	    //19
			vpd.put("var20", varOList.get(i).getString("YL3"));	    //20
			vpd.put("var21", varOList.get(i).getString("YL4"));	    //21
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	//申请交车applyGiveCar
	@RequestMapping(value="/applyGiveCar")
	@ResponseBody
	public String applyGiveCar() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData order = orderService.findById(pd);
		order.put("STATUS", "申请交车中");
		orderService.edit(order);
		return "success";
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
				if(wxid != null && !"".equals(wxid)){
					if("开".equals(userInfo.getString("YL8")) && wxid != null){
						String url = baseUrl+"/wx/sendFuhe?openid="+wxid+"&contant="+contant;
						String doHttpGet = Tools.doHttpGet(url);
						System.out.println(doHttpGet);
						System.err.println("发送完成");
					}
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
