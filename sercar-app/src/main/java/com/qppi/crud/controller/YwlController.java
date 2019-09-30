package com.qppi.crud.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.ReverbType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.qppi.crud.bean.CarInfo;
import com.qppi.crud.bean.Contacts;
import com.qppi.crud.bean.Dictionaries;
import com.qppi.crud.bean.Dispatching;
import com.qppi.crud.bean.Dsmx;
import com.qppi.crud.bean.InfoRelay;
import com.qppi.crud.bean.InfoRelayWithBLOBs;
import com.qppi.crud.bean.LuYin;
import com.qppi.crud.bean.Order;
import com.qppi.crud.bean.PartOffer;
import com.qppi.crud.bean.Photo;
import com.qppi.crud.bean.Pomx;
import com.qppi.crud.bean.ReceptionWithBLOBs;
import com.qppi.crud.bean.Repoinfo;
import com.qppi.crud.bean.RevenueStatement;
import com.qppi.crud.bean.Revisit;
import com.qppi.crud.bean.SName;
import com.qppi.crud.bean.Settlement;
import com.qppi.crud.bean.SysUser;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.service.CarInfoService;
import com.qppi.crud.service.ContactsService;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.DispatchingService;
import com.qppi.crud.service.DsmxService;
import com.qppi.crud.service.InfoRelayService;
import com.qppi.crud.service.LuyinService;
import com.qppi.crud.service.OrderService;
import com.qppi.crud.service.PartOfferMXService;
import com.qppi.crud.service.PartOfferService;
import com.qppi.crud.service.PomxService;
import com.qppi.crud.service.ReceptionService;
import com.qppi.crud.service.RepoinfoService;
import com.qppi.crud.service.RevenueStatementService;
import com.qppi.crud.service.RevisitService;
import com.qppi.crud.service.SettlementService;
import com.qppi.crud.service.UserInfoService;
import com.qppi.crud.utils.CarimgToTxt;
import com.qppi.crud.utils.FileSave;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.statistics.beans.DoubleBeanProxy;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("ywl")
public class YwlController extends BaseController {
		
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ReceptionService receptionService;
	@Autowired
	private InfoRelayService infoRelayService;
	@Autowired
	private DispatchingService dispatchingService;
	@Autowired
	private DsmxService dsmxService;
	@Autowired
	private PartOfferService partOfferService;
	@Autowired
	private PartOfferMXService partOfferMXService;
	@Autowired
	private SettlementService settlementService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private DictionariesService dictionariesService;
	@Autowired
	private ContactsService contactsService;
	@Autowired
	private PomxService pomxService;
	@Autowired
	private RevenueStatementService revenueStatementService;
	@Autowired
	private RepoinfoService repoinfoService;
	@Autowired
	private LuyinService luyinService;
	@Autowired
	private RevisitService revisitService;

	
	public static String dateStr = "";	//日期字符串
	public static int numberCount = 0; 	//数量
	
	/**
	 * 扫描车牌，查询车牌号，判断是否有信息
	 * 有则返回汽车信息
	 * 没有则返回识别后的车牌号
	 * @ 
	 */
	@RequestMapping("getCarInfo")
	@ResponseBody
	public Msg getCarInfo(@RequestParam(value = "carImg", required = true) MultipartFile carImg,HttpServletRequest request) {
		try {
			InputStream inputStream = carImg.getInputStream();
			System.out.println();
			String carNum1 = CarimgToTxt.searchCarNum(inputStream);
			if(carNum1 == null){
				return Msg.fail().add("result", "无法获取");
			}
			CarInfo carInfo = new CarInfo();
			System.out.println("车牌号："+carNum1);
			carInfo.setCarNum1(carNum1);
			carInfo = carInfoService.getCarInfo(carInfo);
			if(carInfo == null){
				return Msg.success2().add("result", carNum1);
			}
			return Msg.success().add("result",carInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Msg.fail().add("result", "无法获取");
		}
		
	}
	
	/**
	 * 输入车牌号或车架号或发动机号获取车辆信息
	 * 有则返回汽车信息,和报价单消息
	 * 没有则返回null
	 */
	@RequestMapping("getCarByNums")
	@ResponseBody
	
	public Msg getCarByNums(CarInfo carInfo,HttpServletRequest request) {
		String parameter = request.getParameter("carNum1");
		carInfo.setCarNum1(parameter);
		carInfo = carInfoService.getCarInfo(carInfo);
		if(carInfo == null){
			return Msg.success2().add("result", null);
		}
		Order order = new Order();
		order.setStatus("订单已取消");
		if(carInfo.getCarNum1() != null && !"".equals(carInfo.getCarNum1())){
			order.setCarNum1(carInfo.getCarNum1());
		}
		if(carInfo.getCarNum2() != null && !"".equals(carInfo.getCarNum2())){
			order.setCarNum2(carInfo.getCarNum2());
		}
		if(carInfo.getCarNum3() != null && !"".equals(carInfo.getCarNum3())){
			order.setCarNum3(carInfo.getCarNum3());
		}
		List<Order> list = orderService.selectByCePai(order);
		List<Order> qxlist = new ArrayList<Order>();
		for (Order o : list) {
			Order xqorder = orderService.queryOrder(o);
			qxlist.add(xqorder);
		}
		return Msg.success().add("result",carInfo).add("order", qxlist);
	}
	
	/**
	 * 新增订单
	 * 如果车辆信息不存在，则先新增车辆信息
	 */
	@RequestMapping("addOrder")
	@ResponseBody
	public Msg addOrder(CarInfo carInfo,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String user = getCurrentSysUser(request).getUsername();
		String code = request.getParameter("code");
		String vehicle = request.getParameter("vehicle");
		//车辆信息不存在
		Order order = new Order();
		String carId = MyTools.getDateR();
		if("100".equals(code)){
			String carpai = carInfo.getCarNum1();
			if("无法获取".equals(carpai)){
				carInfo.setCarNum1("无法获取"+MyTools.getDateR());
			}
			carInfo.setCarinfoId(carId);
			carInfo.setCreateBy(getCurrentSysUser(request).getUsername());
			carInfo.setCreateTime(MyTools.getTime());
			order.setCarId(carId);
			if(!carInfoService.add(carInfo)){
				return Msg.fail().add("result", "新增车辆失败");
			}
		}else{
			carInfoService.updateC(carInfo);
			order.setCarId(carInfo.getCarinfoId());
		}
		
		String time = MyTools.getTime();
		
		order.setOrderId(MyTools.getDateR());
		order.setCreateBy(user);
		order.setCreateTime(time);
		order.setInTime(time);
		order.setStatus("接车中");
		order.setCarNum1(carInfo.getCarNum1());
		order.setCarNum2(carInfo.getCarNum2());
		order.setCarNum3(carInfo.getCarNum3());
		order.setServiceConsultant(user);
		order.setCarInfo(carInfo);
		order.setYl2(vehicle);
		order.setYl10(carInfo.getYl10());
		order.setYl11(carInfo.getYl11());
		order.setYl20(carInfo.getCarBrank());
		order.setYl19(carInfo.getYl1());
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.add(order)){
			status = "failure";
			MySelfUtil.addRiZhi(user, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result","新增订单失败");
		}
		MySelfUtil.addRiZhi(user, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", order);
	}
	
	/**
	 * 创建接车单
	 * 更新订单信息
	 */
	@RequestMapping("addReception")
	@ResponseBody
	public Msg addReception(@RequestBody ReceptionWithBLOBs reception,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type1 = request.getMethod();
		String url1 = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		String URL = dictionariesService.selectURL();
		String num = MyTools.getDateR();
		
		String licheng = reception.getLicheng();
       
		String basePath = dictionariesService.selectPATH();
		StringBuilder str = new StringBuilder();
        List<Photo> photos = reception.getWaiguanList();
        String orderimg = "";
        if(photos.size()>0){
        	for (int i = 0; i<photos.size(); i++) {
    			String r = MyTools.getDateR();
    			String url = basePath + "/" + num + "/" + r;
    			String tupian = photos.get(i).getTupian();
    			System.out.println(url);
    			if (tupian.indexOf("data:image/") != -1) {
    			        String type = "jpg";
    			        url = url + "." + type;
    			        try {
    			        	tupian = tupian.substring(tupian.indexOf(",")+1);
    						if(!MySelfUtil.GenerateImage(tupian,url)){
    							return Msg.fail().add("result", "图片上传失败");
    						}
    						str.append(URL+"/"+num+"/"+r +"."+type).append("***");
    						if(i == 0){
    							orderimg = URL+"/"+num+"/"+r +"."+type;
    						}
    					} catch (Exception e) {
    						e.printStackTrace();
    						return Msg.fail().add("result", "图片上传失败");
    					}
    			}
    		}
        }
        
        //添加油量图片
        String oilPhoto = reception.getYl7();
        if(oilPhoto != null && !"".equals(oilPhoto)){
        	StringBuilder s = new StringBuilder();
        	String r = MyTools.getDateR();
			String url = basePath + "/" + num + "/" + r;
			if (oilPhoto.indexOf("data:image/") != -1) {
				String type = "jpg";
			    url = url + "." + type;
		        try {
		        	oilPhoto = oilPhoto.substring(oilPhoto.indexOf(",")+1);
					if(!MySelfUtil.GenerateImage(oilPhoto,url)){
						return Msg.fail().add("result", "图片上传失败");
					}
					s.append(URL+"/"+num+"/"+r +"."+type);
				} catch (Exception e) {
					e.printStackTrace();
					return Msg.fail().add("result", "图片上传失败");
				}
	        	reception.setYl7(s.toString());
			}
        }
        
        if(licheng != null){
        	StringBuilder s = new StringBuilder();
        	String r = MyTools.getDateR();
			String url = basePath + "/" + num + "/" + r;
        	String type = "jpg";
		    url = url + "." + type;
	        try {
	        	licheng = licheng.substring(licheng.indexOf(",")+1);
				if(!MySelfUtil.GenerateImage(licheng,url)){
					return Msg.fail().add("result", "图片上传失败");
				}
				s.append(URL+"/"+num+"/"+r +"."+type);
			} catch (Exception e) {
				e.printStackTrace();
				return Msg.fail().add("result", "图片上传失败");
			}
        	reception.setMileageImg(s.toString().getBytes());
        	reception.setYl5(s.toString());
		}
		reception.setYl2(str.toString());
		reception.setReceptionId(num);
		reception.setCreateBy(username);
		reception.setCreateTime(MyTools.getTime());
		reception.setStatus("待提交");
		reception.setYl4(request.getParameter("contactId"));
		if(!receptionService.add(reception)){
			return Msg.fail().add("result", "新增接车单失败");
		}
		
		Order order = orderService.selectById(reception.getOrderId());
		order.setYl1(orderimg);
		order.setStatus("已创建接车单待提交复核");
		order.setContactId(request.getParameter("contactId"));
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
		return Msg.success().add("result", reception);
	}
	
	/**
	 * 提交接车复核(gai)
	 * 更新接车单状态
	 */
	@RequestMapping("submitr")
	@ResponseBody
	public Msg submitr(ReceptionWithBLOBs reception,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Order order = orderService.selectById(reception.getOrderId());
		if(!"已创建接车单待提交复核".equals(order.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		ReceptionWithBLOBs receptionWithBLOBs = receptionService.selectByReceptionId(reception.getReceptionId());
		receptionWithBLOBs.setStatus("待复核");
		if(!receptionService.updateR(receptionWithBLOBs)){
			return Msg.fail().add("result", "提交失败");
		}
		order.setStatus("接车单已提交待复核");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)) {
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail2().add("result", "更新失败");
		}
		String bianma = "jiechefh";
		String companyId = reception.getYl10();
		String contant = "有接车单需要复核";
		List<String> quanxian = getQuanxian(bianma, companyId);
		if(quanxian == null || "".equals(quanxian)){
			return Msg.fail().add("result", "没有复核人");
		}
		for (String string : quanxian) {
			sendYuanGong(string,contant);
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}

	/**
	 * 接车复核
	 * 更新接车单状态
	 */
	@RequestMapping("checkr")
	@ResponseBody
	public Msg checkr(ReceptionWithBLOBs reception,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Order order = orderService.selectById(reception.getOrderId());
		if(!"接车单已提交待复核".equals(order.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		ReceptionWithBLOBs receptionWithBLOBs = receptionService.selectByReceptionId(reception.getReceptionId());
		receptionWithBLOBs.setYl3(reception.getYl3());
		receptionWithBLOBs.setYl11(reception.getYl11());
		receptionWithBLOBs.setStatus("已复核待客户确认");
		receptionWithBLOBs.setCheckedBy(getCurrentSysUser(request).getUsername());
		receptionWithBLOBs.setCheckedTime(MyTools.getTime());
		if(!receptionService.updateR(receptionWithBLOBs)){
			return Msg.fail().add("result", "复核失败");
		}
		order.setStatus("接车单已复核待客户确认");
		String responsecontent = JSONArray.fromObject(reception).toString();
		if(!orderService.update(order)) {
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新失败");
		}
		
		//推送给客户
		String conStr = "您有一个订单需要授权";
		send(reception.getOrderId(),conStr);
		
		//推送给服务顾问
		String createBy = order.getCreateBy();
		String contant = "有接车单待客户确认";
		sendYuanGong(createBy, contant);

		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}
	
	/**
	 * 接车取消复核
	 * 更新接车单状态
	 */
	@RequestMapping("nocheckr")
	@ResponseBody
	public Msg nocheckr(ReceptionWithBLOBs reception,HttpServletRequest request) {	
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String username = getCurrentSysUser(request).getUsername();
		
		Order order = orderService.selectById(reception.getOrderId());
		if(!"接车单已提交待复核".equals(order.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		ReceptionWithBLOBs receptionWithBLOBs = receptionService.selectByReceptionId(reception.getReceptionId());
		receptionWithBLOBs.setYl3(reception.getYl3());
		receptionWithBLOBs.setYl11(reception.getYl11());
		receptionWithBLOBs.setStatus("复核未通过");
		receptionWithBLOBs.setCheckedBy(getCurrentSysUser(request).getUsername());
		receptionWithBLOBs.setCheckedTime(MyTools.getTime());
		if(!receptionService.updateR(receptionWithBLOBs)){
			return Msg.fail().add("result", "复核失败");
		}
		
		//发送消息到微信
		String createUsername = order.getCreateBy();
		String contant = "有接车单复核不通过";
		sendYuanGong(createUsername, contant);
		
		order.setStatus("已创建接车单待提交复核");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)) {
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新失败");
		}

		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}
	
	
	/**
	 * 创建信息反馈单
	 * @param request
	 * @param infoRelay
	 * @return
	 */
	@RequestMapping("addInfo")
	@ResponseBody
	public Msg addInfo(HttpServletRequest request,InfoRelayWithBLOBs infoRelay) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		
		String infoRelayId = MyTools.getDateR();
		String URL = dictionariesService.selectURL();
		String PATH = dictionariesService.selectPATH();
	    //创建一个通用的多部分解析器
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	    //判断 request 是否有文件上传,即多部分请求  
	    if (multipartResolver.isMultipart(request)){  
	    	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  
	        Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
	        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
	        	// 获取单个文件
	            MultipartFile mf = entity.getValue(); // 获得原始文件名
	            try {
	            	InputStream inputStream = mf.getInputStream();
	    			String infoPath = "/file/"+infoRelay.getOrderId()+"/";
	    			String mileageName = MyTools.getDateR()+".amr";
	    			
	    			if(!FileSave.saveFile(inputStream, PATH+infoPath, mileageName)){
	    				return Msg.fail().add("result", "保存文件失败");
	    			}
	    			String content = URL+infoPath+mileageName;
	    			LuYin luYin = new LuYin();
	    			luYin.setLuyinId(MyTools.getDateR());
	    			luYin.setContent(content);
	    			
	    			String path1 = content.replace(URL, PATH);
					System.out.println(path1);
					String path2 = path1.replace(".amr", ".mp3");
					System.out.println(path2);
					try {
						MySelfUtil.changeToMp3(path1, path2);
					} catch (Exception e) {
						e.printStackTrace();
					}
					String newpath = content.replace(".amr", ".mp3");
					luYin.setYl2(newpath);
					
	    			luYin.setCreater(username);
	    			luYin.setCreatetime(MyTools.getTime());
	    			luYin.setInforelayid(infoRelayId);
	    			luYin.setYl1(infoRelay.getYl13());
	    			luYin.setYl10(infoRelay.getYl10());
	    			if(!luyinService.addLuYin(luYin)){
	    				String responsecontent = JSONArray.fromObject(luYin).toString();
	    				status = "failure";
	    				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
	    				return Msg.fail().add("result", "更新失败");
	    			}
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }  
	        
			infoRelay.setInforelayId(infoRelayId);
			infoRelay.setCreateBy(getCurrentSysUser(request).getUsername());
			infoRelay.setCreateTime(MyTools.getTime());
			infoRelay.setStatus("待提交");
			infoRelay.setYl3("0");
			if(!infoRelayService.add(infoRelay)){
				return Msg.fail().add("result", "新增信息反馈单失败");
			}
			Order order = orderService.selectById(infoRelay.getOrderId());
			order.setStatus("信息反馈单待提交复核");
			String responsecontent = JSONArray.fromObject(order).toString();
			if(!orderService.update(order)) {
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", infoRelay);
	    }
	    return Msg.success().add("result", infoRelay);
	}
	
	/**
	 * 添加录音
	 */
	@RequestMapping("addLuyin")
	@ResponseBody
	public Msg addLuyin(@RequestParam(value = "relayFile111", required = true) MultipartFile relayFile111,InfoRelayWithBLOBs infoRelay,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		String URL = dictionariesService.selectURL();
		String PATH = dictionariesService.selectPATH();
	    try {
	    	InputStream inputStream = relayFile111.getInputStream();
			String infoPath = "/file/"+infoRelay.getOrderId()+"/";
			String mileageName = MyTools.getDateR()+".amr";
			
			if(!FileSave.saveFile(inputStream, PATH+infoPath, mileageName)){
				return Msg.fail().add("result", "保存文件失败");
			}

			String content = URL+infoPath+mileageName;
			LuYin luYin = new LuYin();
			luYin.setLuyinId(MyTools.getDateR());
			luYin.setContent(content);
			
			String path1 = content.replace(URL, PATH);
			System.out.println(path1);
			String path2 = path1.replace(".amr", ".mp3");
			System.out.println(path2);
			try {
				MySelfUtil.changeToMp3(path1, path2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String newpath = content.replace(".amr", ".mp3");
			luYin.setYl2(newpath);
			
			luYin.setCreater(username);
			luYin.setCreatetime(MyTools.getTime());
			luYin.setInforelayid(infoRelay.getInforelayId());
			luYin.setYl1(infoRelay.getYl13());
			luYin.setYl10(infoRelay.getYl10());
			String responsecontent = JSONArray.fromObject(luYin).toString();
			if(!luyinService.addLuYin(luYin)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新失败");
			}
			status = "success";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Msg.fail().add("result", "新增信息反馈单失败");
		}
	}
	
	/**
	 * 查看录音列表
	 * @param infoRelay
	 * @param request
	 * @return
	 */
	@RequestMapping("listLuyin")
	@ResponseBody
	public Msg listLuyin(String infoRelayId) {
		List<LuYin> list = luyinService.listLuyin(infoRelayId);
		return Msg.success().add("result", list);
	}
	
	
	//无录音文件上传反馈单
	@RequestMapping("addInfoR")
	@ResponseBody
	public Msg addInfoR(InfoRelayWithBLOBs infoRelay,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		
		infoRelay.setInforelayId(MyTools.getDateR());
		infoRelay.setCreateBy(getCurrentSysUser(request).getUsername());
		infoRelay.setCreateTime(MyTools.getTime());
		infoRelay.setStatus("待提交");
		infoRelay.setYl3("0");
		if(!infoRelayService.add(infoRelay)){
			return Msg.fail().add("result", "新增信息反馈单失败");
		}
		
		Order order = orderService.selectById(infoRelay.getOrderId());
		order.setStatus("信息反馈单待提交复核");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)) {
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", infoRelay);
	}


	public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024*4];
        int n=0;
        while ( (n=in.read(buffer)) !=-1) {
            out.write(buffer,0,n);
        }
        return out.toByteArray();
    }
	
	/**
	 * 提交信息反馈复核
	 * 更新信息反馈单状态
	 */
	@RequestMapping("submiti")
	@ResponseBody
	public Msg submiti(InfoRelayWithBLOBs infoRelay,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		
		InfoRelayWithBLOBs i = infoRelayService.getInfoRelay(infoRelay.getInforelayId());
		if(!"待提交".equals(i.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		infoRelay.setStatus("待复核");
		if(!infoRelayService.update(infoRelay)){
			return Msg.fail().add("result", "提交失败");
		}
		Order order = orderService.selectById(infoRelay.getOrderId());
		order.setStatus("信息反馈单已提交待复核");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)) {
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新失败");
		}
		
		//发送消息到微信
		String bianma = "xinxifh";
		String companyId = infoRelay.getYl10();
		String contant = "有消息反馈单需要复核";
		List<String> quanxian = getQuanxian(bianma, companyId);
		if(quanxian == null || "".equals(quanxian)){
			return Msg.fail().add("result", "没有复核人");
		}
		for (String string : quanxian) {
			sendYuanGong(string,contant);
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
		
	}
	
	/**
	 * 信息反馈复核
	 * 更新信息反馈单状态
	 */
	@RequestMapping("checki")
	@ResponseBody
	public Msg checki(InfoRelayWithBLOBs infoRelay,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		InfoRelayWithBLOBs i = infoRelayService.getInfoRelay(infoRelay.getInforelayId());
		if(!"待复核".equals(i.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		infoRelay.setCheckedBy(getCurrentSysUser(request).getUsername());
		infoRelay.setCheckedTime(MyTools.getTime());
		infoRelay.setStatus("待检测");
		if(!infoRelayService.update(infoRelay)){
			return Msg.fail().add("result", "提交失败");
		}
		Order order = orderService.selectById(infoRelay.getOrderId());
		order.setStatus("信息反馈单已复核待诊断");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)) {
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新失败");
		}
		
//		维修技师推送
		List<String> list = getRoleType(infoRelay.getYl10(),"8");
		String contant = "有信息反馈单需要诊断";
		if(list.size()>0){
			for (String roleType : list) {
				UserInfo user = new UserInfo();
				user.setRoletype(roleType);
				List<UserInfo> userList = userInfoService.selectUser(user);
				for (UserInfo userInfo : userList) {
					sendYuanGong(userInfo.getUsername(), contant);
				}
			}
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");

	}
	
	/**
	 * 信息反馈取消复核
	 * 更新信息反馈单状态
	 */
	@RequestMapping("nochecki")
	@ResponseBody
	public Msg nochecki(InfoRelayWithBLOBs infoRelay,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		InfoRelayWithBLOBs i = infoRelayService.getInfoRelay(infoRelay.getInforelayId());
		if(!"待复核".equals(i.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		infoRelay.setCheckedBy(getCurrentSysUser(request).getUsername());
		infoRelay.setCheckedTime(MyTools.getTime());
		infoRelay.setStatus("复核未通过");
		if(!infoRelayService.update(infoRelay)){
			return Msg.fail().add("result", "提交失败");
		}
		Order order = orderService.selectById(infoRelay.getOrderId());
		order.setStatus("信息反馈单待提交复核");
		String createUsername = order.getCreateBy();
		String contant = "有信息反馈单复核未通过";
		sendYuanGong(createUsername, contant);
		
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)) {
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}
	
	/**
	 * 信息反馈诊断
	 * 更新信息反馈单状态
	 */
	@RequestMapping("coni")
	@ResponseBody
	public Msg coni(InfoRelayWithBLOBs infoRelay,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(infoRelay.getOrderId() == null || infoRelay.getOrderId().equals("")){
			return Msg.success().add("result", "orderId不能为空");
		}else{
			
			InfoRelayWithBLOBs i = infoRelayService.getInfoRelay(infoRelay.getInforelayId());
			if(!"待检测".equals(i.getStatus())){
				return Msg.fail2().add("result", "订单状态不正确");
			}
			
			infoRelay.setStatus("已诊断待客户确认");
			infoRelay.setDiagnosticBy(getCurrentSysUser(request).getUsername());
			infoRelay.setDiagnosticTime(MyTools.getTime());
			if(!infoRelayService.update(infoRelay)){
				return Msg.fail().add("result", "提交失败");
			}
			
			Order order = orderService.selectById(infoRelay.getOrderId());
			order.setStatus("信息反馈单已诊断待客户确认");
			String createUsename = order.getCreateBy();
			String contant = "有信息反馈单被诊断";
			sendYuanGong(createUsename, contant);
			
			String responsecontent = JSONArray.fromObject(order).toString();
			if(!orderService.update(order)) {
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "提交成功");
		}
	}
	
	//信息反馈单确认
	@RequestMapping("xinxifankui")
	@ResponseBody
	public Msg xinxifankui(String orderId,String inforelayId) {
		Order order = orderService.selectById(orderId);
		if(!"信息反馈单已诊断待客户确认".equals(order.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		boolean flag = true;
		InfoRelayWithBLOBs infoRelay = infoRelayService.getInfoRelay(inforelayId);
		infoRelay.setStatus("客户已确认");
		if(!infoRelayService.update(infoRelay)){
			return Msg.fail().add("result", "更新失败");
		}
		
		List<InfoRelay> list = infoRelayService.selectByOrderId(orderId);
		for (InfoRelay info : list) {
			if(!info.getStatus().equals("客户已确认")){
				flag = false;
				break;
			}
		}
		if(flag == true){
			order.setStatus("信息反馈单已确认待报价");
		}
		if(!orderService.update(order)){
			return Msg.fail().add("result", "更新失败");
		}
		return Msg.success().add("result", "更新成功");
		
	}
	
	//信息反馈单拒绝
	@RequestMapping("noxinxifankui")
	@ResponseBody
	public Msg noxinxifankui(String orderId,String inforelayId,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Order order = orderService.selectById(orderId);
		InfoRelayWithBLOBs infoRelay = infoRelayService.getInfoRelay(inforelayId);
		infoRelay.setStatus("已拒绝");
		order.setStatus("信息反馈单已拒绝待交车");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!infoRelayService.update(infoRelay)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新失败");
		}
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "更新成功");
	}
	
	/**
	 * 创建配件报价单
	 * 更新订单状态
	 */
	@RequestMapping("partOffer")
	@ResponseBody
	public Msg partOffer(@RequestBody PartOffer partOffer,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		partOffer.setYl3("2");
		String vehicle = request.getParameter("vehicle");
		String id = MyTools.getDateR();
		Double total = 0.00;
		double zjdj = 0;
		for(Pomx pomx:partOffer.getPomxs()){
			if("sky".equals(pomx.getYl9())){
				String dateR = MyTools.getDateR();
				pomx.setYl9(dateR);
				Repoinfo p = new Repoinfo();
				p.setRepoinfoId(dateR);
				p.setName(pomx.getPartName());
				p.setPrice(pomx.getPrice());
				p.setNumber("0");
				repoinfoService.insetRepoinfo(p);
			}else{
				if("现货".equals(pomx.getYl6())){
					String count = pomx.getPartCount();
					Repoinfo repoinfo = repoinfoService.selectGet(pomx.getYl9());
					String yl6 = repoinfo.getYl6();
					if(Integer.parseInt(yl6) < Integer.parseInt(count)){
						return Msg.fail().add("result", "仓库中可用零件不足");
					}
				}
			}
			String dingjin = pomx.getYl16();
			if(dingjin == null || "".equals(dingjin)){
				dingjin = "0"; 
				pomx.setYl16("0");
			}
			if(!MySelfUtil.isDouble(dingjin)){
				return Msg.fail().add("result", "输入订金不对");
			}
			zjdj = zjdj + Double.parseDouble(dingjin);
			
			String price = pomx.getYl8();
			if(price == null || "".equals(price)){
				price = "0.00";
			}
			
			String partCount = pomx.getPartCount();
			Double mxTotal = 0.00;
			if(price!=null && !"".equals(price) && partCount!=null && !"".equals(partCount)) {
				try {
					Double p = Double.parseDouble(price);
					int pc = Integer.parseInt(partCount);
					mxTotal = p*pc;
					total += mxTotal;
				}catch (Exception e) {
					e.printStackTrace();
					return Msg.fail().add("result", "参数错误");
				}
			}else {
				return Msg.fail().add("result", "参数错误");
			}
			pomx.setPomxId(MyTools.getDateR());
			pomx.setYl2(partOffer.getOrderId());
			pomx.setPartOfferId(id);
			pomx.setTotal(mxTotal+"");
			pomx.setYl15("待使用");
			if(!partOfferMXService.addP(pomx)){
				return Msg.fail().add("result", "新增配件报价明细失败");
			}
		}
		
		partOffer.setPartofferId(id);
		partOffer.setTotal(total+"");
		partOffer.setCreateBy(getCurrentSysUser(request).getUsername());
		partOffer.setCreateTime(MyTools.getTime());
		partOffer.setStatus("待提交");
		partOffer.setOfferBy(username);
		partOffer.setYl8(zjdj+"");
		if(!partOfferService.add(partOffer)){
			return Msg.fail().add("result", "创建配件报价单失败");
		}
		
		Order order = orderService.selectById(partOffer.getOrderId());
		order.setStatus("已创建报价单待提交复核");
		String yl8 = order.getYl8();
		if(!MySelfUtil.isDouble(yl8)){
			yl8 = "0";
		}
		double orderdingjin = Double.parseDouble(yl8) + zjdj;
		order.setYl2(vehicle);
		order.setYl8(orderdingjin+"");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", partOffer);
	}
	
	/**
	 * 创建工时报价单
	 */
	@RequestMapping("hoursOffer")
	@ResponseBody
	public Msg hoursOffer(@RequestBody PartOffer partOffer,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		partOffer.setYl3("1");
		partOffer.setOfferBy(username);
		String xmlxId = partOffer.getYl4();
		String vehicle = request.getParameter("vehicle");
		String id = MyTools.getDateR();
		Double total = 0.00;
		for(Dsmx dsmx:partOffer.getDsmxs()){
			dsmx.setDsmxId(MyTools.getDateR());
			dsmx.setYl2(partOffer.getOrderId());
			dsmx.setYl1(id);
			dsmx.setYl4(MyTools.getTime());
			dsmx.setYl5(username);
			String yl6 = dsmx.getYl6();
			dsmx.setYl15("待完成");
			total = total +Double.parseDouble(yl6);
			if(!partOfferMXService.addD(dsmx)){
				return Msg.fail().add("result", "新增工时报价明细失败");
			}
			
			String workName = dsmx.getWorkName();
			List<SName> list = dsmxService.selectXmName(workName,xmlxId);
			if(list.size() == 0){
				SName sName  = new SName();
				sName.setPid(xmlxId);
				sName.setsName(workName);
				sName.setSnameId(MyTools.getDateR());
				sName.setYl1(yl6);
				dsmxService.insertXmName(sName);
			}
			
		}
		
		partOffer.setPartofferId(id);
		partOffer.setTotal(total+"");
		partOffer.setCreateBy(username);
		partOffer.setCreateTime(MyTools.getTime());
		partOffer.setStatus("待提交");
		partOffer.setOfferBy(username);
		if(!partOfferService.add(partOffer)){
			return Msg.fail().add("result", "创建工时报价单失败");
		}
		
		Order order = orderService.selectById(partOffer.getOrderId());
		order.setStatus("已创建报价单待提交复核");
		order.setYl2(vehicle);
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", partOffer);
	}
	
	/**
	 * 配件报价提交
	 * 更新报价单状态
	 */
	@RequestMapping("submitp")
	@ResponseBody
	public Msg submitp(PartOffer partOffer,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		PartOffer parOffer = partOfferService.getParOffer(partOffer);
		String s = parOffer.getStatus();
		if("待提交".equals(s) || "客户已取消授权".equals(s) || "客户未同意".equals(s) || "复核未通过".equals(s)){
			partOffer.setStatus("待复核");
			if(!partOfferService.update(partOffer)){
				return Msg.fail().add("result", "复核失败");
			}
			Order order = orderService.selectById(partOffer.getOrderId());
			order.setStatus("报价单已提交待复核");
			String responsecontent = JSONArray.fromObject(order).toString();
			if(!orderService.update(order)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新订单失败");
			}
			
			String bianma = "peijianfh";
			String companyId = partOffer.getYl10();
			String contant = "有报价单需要复核";
			List<String> quanxian = getQuanxian(bianma, companyId);
			if(quanxian == null || "".equals(quanxian)){
				return Msg.fail().add("result", "没有复核人");
			}
			for (String string : quanxian) {
				sendYuanGong(string,contant);
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "提交成功");
		}
		return Msg.fail2().add("result", "订单状态不正确");
		
	}
	
	/**
	 * 工时报价提交
	 * 更新报价单状态
	 */
	@RequestMapping("submitg")
	@ResponseBody
	public Msg submitg(PartOffer partOffer,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		PartOffer parOffer = partOfferService.getParOffer(partOffer);
		String s = parOffer.getStatus();
		if("待提交".equals(s) || "客户已取消授权".equals(s) || "客户未同意".equals(s) || "复核未通过".equals(s)){
			Order order = orderService.selectById(partOffer.getOrderId());
			partOffer.setStatus("待复核");
			if(!partOfferService.update(partOffer)){
				return Msg.fail().add("result", "复核失败");
			}
			
			String bianma1 = "gongshifh";
			String companyId1 = partOffer.getYl10();
			String contant1 = "有报价单需要复核";
			List<String> quanxian1 = getQuanxian(bianma1, companyId1);
			if(quanxian1 == null || "".equals(quanxian1)){
				return Msg.fail().add("result", "没有复核人");
			}
			for (String string : quanxian1) {
				sendYuanGong(string,contant1);
			}
			
			order.setStatus("报价单已提交待复核");
			String responsecontent = JSONArray.fromObject(order).toString();
			if(!orderService.update(order)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新订单失败");
			}
			
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "提交成功");
		}
		return Msg.fail2().add("result", "订单状态不正确");
		
	}
	
	
	/**
	 * 报价复核
	 * 更新报价单状态
	 */
	@RequestMapping("checkp")
	@ResponseBody
	public Msg checkp(PartOffer partOffer,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		PartOffer parOffer = partOfferService.getParOffer(partOffer);
		if(!"待复核".equals(parOffer.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		partOffer.setStatus("待确认");
		partOffer.setCheckedBy(getCurrentSysUser(request).getUsername());
		partOffer.setCheckedTime(MyTools.getTime());
		if(!partOfferService.update(partOffer)){
			return Msg.fail().add("result", "复核失败");
		}
		
		Order order = orderService.selectById(partOffer.getOrderId());
		order.setStatus("报价单已复核待客户确认");
		String createUsername = order.getCreateBy();
		String contant = "有报价单待客户确认";
		sendYuanGong(createUsername, contant);
		
		String conStr = "您有报价单需要确认";
		send(partOffer.getOrderId(),conStr);
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
		
	}
	
	/**
	 * 报价取消复核
	 * 更新报价单状态
	 */
	@RequestMapping("nocheckp")
	@ResponseBody
	public Msg nocheckp(PartOffer partOffer,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		PartOffer parOffer = partOfferService.getParOffer(partOffer);
		if(!"待复核".equals(parOffer.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		partOffer.setStatus("复核未通过");
		partOffer.setCheckedBy(getCurrentSysUser(request).getUsername());
		partOffer.setCheckedTime(MyTools.getTime());
		if(!partOfferService.update(partOffer)){
			return Msg.fail().add("result", "复核失败");
		}

		Order order = orderService.selectById(partOffer.getOrderId());
		order.setStatus("报价单待提交复核");
		String createUsername = order.getCreateBy();
		String contant = "有报价单复核失败";
		sendYuanGong(createUsername, contant);
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}
	
	/**
	 * 客户确认
	 * 更新信息反馈单状态
	 * 更新配件报价单状态
	 */
	@RequestMapping("memCon")
	@ResponseBody
	public Msg memCon(InfoRelay infoRelay,PartOffer partOffer,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		partOffer.setStatus("客户已确认");
		if(!partOfferService.update(partOffer)){
			return Msg.fail().add("result", "更新配件报价单失败");
		}
		
		Order order = orderService.selectById(partOffer.getOrderId());
		order.setStatus("报价单已确认待派工");
		String createUsername = order.getCreateBy();
		String contant = "有配件报价单需要结算";
		sendYuanGong(createUsername, contant);
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}
	
	//取消工时报价
	@RequestMapping("qxHoursOffer")
	@ResponseBody
	public Msg qxHoursOffer(String dsmxId, String offerId){
		Dsmx dsmx = new Dsmx();
		dsmx.setDsmxId(dsmxId);
		Dsmx ds = dsmxService.getDsmx(dsmx);
		PartOffer partOffer = new PartOffer();
		partOffer.setPartofferId(offerId);
		PartOffer parOffer = partOfferService.getParOffer(partOffer);
		
		double allMoney = 0;
		if(MySelfUtil.isDouble(parOffer.getTotal())){
			allMoney = Double.parseDouble(parOffer.getTotal());
		}
		double oneMoney = 0;
		if(ds.getYl3() != null && !("").equals(ds.getYl3())){
			if(MySelfUtil.isDouble(ds.getYl3())){
				oneMoney = Double.parseDouble(ds.getYl3());
			}
		}else{
			if(MySelfUtil.isDouble(ds.getYl6())){
				oneMoney = Double.parseDouble(ds.getYl6());
			}
		}
		
		double newMoney = allMoney - oneMoney;
		parOffer.setTotal(newMoney+"");
		
		if(!partOfferService.update(parOffer)){
			return Msg.fail().add("result", "取消失败");
		}
		if(!dsmxService.delete(dsmxId)){
			return Msg.fail().add("result", "取消失败");
		}
		
		List<Dsmx> list = dsmxService.selectByPoId(offerId);
		if(list.size() == 0){
			if(!partOfferService.delete(offerId)){
				return Msg.fail().add("result", "取消失败");
			}
		}
		
		return Msg.success().add("result", "取消成功");
	}
	
	//取消配件报价   是  --- 退定金
	@RequestMapping("qxPomxOffer")
	@ResponseBody
	public Msg qxPomxOffer(String pomxId, String offerId,String str){
		Pomx pomx = new Pomx();
		pomx.setPomxId(pomxId);;
		Pomx po = pomxService.getPomx(pomx);
		PartOffer partOffer = new PartOffer();
		partOffer.setPartofferId(offerId);
		
		PartOffer parOffer = partOfferService.getParOffer(partOffer);
		Order order = orderService.selectById(parOffer.getOrderId());
		
		double allMoney = 0;
		if(MySelfUtil.isDouble(parOffer.getTotal())){
			allMoney = Double.parseDouble(parOffer.getTotal());
		}
		double oneMoney = 0;
		if(MySelfUtil.isDouble(po.getTotal())){
			oneMoney = Double.parseDouble(po.getTotal());
		}
		
		double newMoney = allMoney - oneMoney;
		parOffer.setTotal(newMoney+"");
		
		if("已交付定金".equals(parOffer.getYl15())){
			if(MySelfUtil.isDouble(po.getYl16())){
				double dingjin = Double.parseDouble(po.getYl16());
				if("是".equals(str)){
					String yl13 = order.getYl13();
					if(yl13 == null || "".equals(yl13) ){
						yl13 = "0";
					}
					double tuihuan = Double.parseDouble(yl13) + dingjin;
					order.setYl13(tuihuan+"");
					double pdingjin = Double.parseDouble(parOffer.getYl8()) - dingjin;
					parOffer.setYl8(pdingjin+"");
					double odingjin = Double.parseDouble(order.getYl8()) - dingjin;
					order.setYl8(odingjin+"");
					/*double sjdingjin = Double.parseDouble(order.getYl9()) - dingjin;
					order.setYl9(sjdingjin+"");*/
				}else {
					String yl12 = order.getYl12();
					if(yl12 == null || "".equals(yl12)){
						yl12 = "0";
					}
					double btdingjin = Double.parseDouble(yl12) + dingjin;
					order.setYl12(btdingjin+"");
				}
				if(!orderService.update(order)){
					return Msg.fail().add("result", "失败");
				}
			}
		}else{
			if(MySelfUtil.isDouble(po.getYl16())){
				double dingjin = Double.parseDouble(po.getYl16());
				double pdingjin = Double.parseDouble(parOffer.getYl8()) - dingjin;
				parOffer.setYl8(pdingjin+"");
				double odingjin = Double.parseDouble(order.getYl8()) - dingjin;
				order.setYl8(odingjin+"");
				
				if(!orderService.update(order)){
					return Msg.fail().add("result", "失败");
				}
			}
		}
		
		if(!partOfferService.update(parOffer)){
			return Msg.fail().add("result", "取消失败");
		}
		if(!pomxService.deleteById(pomxId)){
			return Msg.fail().add("result", "取消失败");
		}
		List<Pomx> list = pomxService.selectByPartOfferId(offerId);
		if(list.size() == 0){
			if(!partOfferService.delete(offerId)){
				return Msg.fail().add("result", "取消失败");
			}
		}
		return Msg.success().add("result", "取消成功");
	}
	
	//确认收取订金
	@RequestMapping("isGetDingjin")
	@ResponseBody
	public Msg isGetDingjin(String partofferId,String orderId){
		
		PartOffer partOffer = new PartOffer();
		partOffer.setPartofferId(partofferId);
		PartOffer parOffer = partOfferService.getParOffer(partOffer);
		
		
		Order order = orderService.selectById(orderId);
		double count = 0;
		String yl9 = order.getYl9();
		if("".equals(parOffer.getYl15()) || parOffer.getYl15() == null){
			if(yl9 == null || "".equals(yl9)){
				yl9 = "0";
			}
			count = Double.parseDouble(yl9)+Double.parseDouble(parOffer.getYl8());
		}else{
			count = Double.parseDouble(yl9);
		}
		
		parOffer.setStatus("已交付定金");
		parOffer.setYl15("已交付定金");
		if(!partOfferService.update(parOffer)){
			return Msg.fail().add("result", "失败");
		}
		order.setYl9(count+"");
		if(!orderService.update(order)){
			return Msg.fail().add("result", "失败"); 
		}
		return Msg.success().add("result", "成功");
	}
	
	/**
	 * 创建派工单
	 * 更新订单状态
	 */
	@RequestMapping("addDispat")
	@ResponseBody
	public Msg addDispat(@RequestBody Dispatching dispatching,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String id = MyTools.getDateR();
		dispatching.setDispatchingId(id);
		dispatching.setCreateBy(getCurrentSysUser(request).getUsername());
		dispatching.setCreateTime(MyTools.getTime());
		dispatching.setStatus("待提交");
		String username = dispatching.getMaintenanceTechnician();
		UserInfo user = userInfoService.selectUserId(username);
		dispatching.setYl2(user.getName());
		
		String xmlxId = dispatching.getYl3();
		Integer count = 0;
		for(int i = 0;i<dispatching.getDsmxs().size();i++){
			Dsmx dsmx = dispatching.getDsmxs().get(i);
			String money = dsmx.getYl3();
			count = count +Integer.parseInt(money);
			dsmx.setYl4(MyTools.getTime());
			dsmx.setYl5(username);
			dsmx.setDsmxId(MyTools.getDateR());
			dsmx.setYl2(dispatching.getOrderId());
			dsmx.setYl10(dispatching.getYl10());
			dsmx.setYl13(user.getName());
			dsmx.setDispatchingId(id);
			dsmx.setYl15("待完成");
			String workName = dsmx.getWorkName();
			List<SName> list = dsmxService.selectXmName(workName,xmlxId);
			if(list.size() == 0){
				SName sName  = new SName();
				sName.setPid(xmlxId);
				sName.setsName(workName);
				sName.setSnameId(MyTools.getDateR());
				sName.setYl1(money);
				dsmxService.insertXmName(sName);
			}
			if(!dsmxService.add(dsmx)){
				return Msg.fail().add("result", "新增派工单明细失败");
			}
		}
		
		dispatching.setYl4(count.toString());
		if(!dispatchingService.add(dispatching)){
			return Msg.fail().add("result", "新增派工单失败");
		}
		
		
		Order order = orderService.selectById(dispatching.getOrderId());
		order.setStatus("已创建派工单待提交复核");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", dispatching);
	}
	
	/**
	 * 提交派工单
	 * 更新派工单状态
	 */
	@RequestMapping("submitd")
	@ResponseBody
	public Msg submitd(Dispatching dispatching,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(dispatching.getOrderId() == null || dispatching.getOrderId().equals("")){
			return Msg.success().add("result", "orderId不能为空");
		}else{
			
			Dispatching dis = dispatchingService.getDispatching(dispatching.getDispatchingId());
			if(!"待提交".equals(dis.getStatus())){
				return Msg.fail2().add("result", "订单状态不正确");
			}
			
			dispatching.setStatus("待复核");
			if(!dispatchingService.update(dispatching)){
				return Msg.fail().add("result", "提交失败");
			}
			
			String bianma = "paigongfh";
			String companyId = dispatching.getYl10();
			String contant = "有派工单需要复核";
			List<String> quanxian = getQuanxian(bianma, companyId);
			if(quanxian == null || "".equals(quanxian)){
				return Msg.fail().add("result", "没有复核人");
			}
			for (String string : quanxian) {
				sendYuanGong(string,contant);
			}
			
			Order order = orderService.selectById(dispatching.getOrderId());
			order.setStatus("派工单已提交待复核");
			String responsecontent = JSONArray.fromObject(order).toString();
			if(!orderService.update(order)) {
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "提交成功");
		}
	}
	
	/**
	 * 派工复核
	 * 更新派工单状态
	 */
	@RequestMapping("checkd")
	@ResponseBody
	public Msg checkd(Dispatching dispatching,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Order order = orderService.selectById(dispatching.getOrderId());
		Dispatching dis = dispatchingService.getDispatching(dispatching.getDispatchingId());
		if(!"待复核".equals(dis.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		dispatching.setStatus("待开工");
		dispatching.setYl8(MyTools.getTime());
		dispatching.setCheckedBy(getCurrentSysUser(request).getUsername());
		dispatching.setCehckedTime(MyTools.getTime());
		if(!dispatchingService.update(dispatching)){
			return Msg.fail().add("result", "复核失败");
		}
		
		Dispatching dispat = dispatchingService.getDispatching(dispatching.getDispatchingId());
		String wxjsUsername = dispat.getMaintenanceTechnician();
		String contant = "有派工单需要开工";
		sendYuanGong(wxjsUsername, contant);
		
		order.setStatus("派工单已复核待技师开工");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)) {
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}
	

	/**
	 * 派工取消复核
	 * 更新派工单状态
	 */
	@RequestMapping("nocheckd")
	@ResponseBody
	public Msg nocheckd(Dispatching dispatching,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Dispatching dis = dispatchingService.getDispatching(dispatching.getDispatchingId());
		if(!"待复核".equals(dis.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		dispatching.setStatus("复核未通过");
		dispatching.setCheckedBy(getCurrentSysUser(request).getUsername());
		dispatching.setCehckedTime(MyTools.getTime());
		if(!dispatchingService.update(dispatching)){
			return Msg.fail().add("result", "复核失败");
		}
		
		Order order = orderService.selectById(dispatching.getOrderId());
		String createUsename = order.getCreateBy();
		String contant = "有派工单没有通过复核";
		sendYuanGong(createUsename, contant);
		
		order.setStatus("派工单待提交复核");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)) {
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}
	
	/**
	 * 确认开工
	 * 更新派工单状态
	 */
	@RequestMapping("conDispat")
	@ResponseBody
	public Msg conDispat(Dispatching dispatching,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Order order = orderService.selectById(dispatching.getOrderId());
		Dispatching dis = dispatchingService.getDispatching(dispatching.getDispatchingId());
		if("待开工".equals(dis.getStatus()) || "已停工".equals(dis.getStatus())){
			dispatching.setStatus("待完工");
			if(!dispatchingService.update(dispatching)){
				return Msg.fail().add("result", "更新派工单状态失败");
			}
			order.setStatus("技师已开工待反馈");
			String responsecontent = JSONArray.fromObject(order).toString();
			if(!orderService.update(order)) {
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "更新成功");
		}
		return Msg.fail2().add("result", "订单状态不正确");
	}
	
	//每一个派工完成
	@RequestMapping("paigongfinish")
	@ResponseBody
	public Msg paigongfinish(Dsmx dsmx,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String responsecontent = JSONArray.fromObject(dsmx).toString();
		String username = getCurrentSysUser(request).getUsername();
		
		dsmx.setYl15("待检测");
		if(!dsmxService.update(dsmx)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "更新成功");
	}
	
	
	//检查每一项派工
	@RequestMapping("jcPaiGong")
	@ResponseBody
	public Msg jcPaiGong(String dsmxid, String yl16){
		Dsmx dsmx = dsmxService.selectByDsmxId(dsmxid);
		dsmx.setYl15("已检查");
		dsmx.setYl16(yl16);
		if(!dsmxService.update(dsmx)){
			return Msg.fail().add("result", "检查失败");
		}
		return Msg.success().add("result", "检查成功");
	}
	
	/**
	 * 技师完工
	 * 更新派工单
	 * 更新订单状态
	 */
	@RequestMapping("comWork")
	@ResponseBody
	public Msg comWork(@RequestBody Dispatching dispatching,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Order order = orderService.selectById(dispatching.getOrderId());
		Dispatching dis = dispatchingService.getDispatching(dispatching.getDispatchingId());
		if(!"待完工".equals(dis.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		dispatching.setStatus("待检查");
		dispatching.setCompletionTime(MyTools.getTime());
		if(!dispatchingService.update(dispatching)){
			return Msg.fail().add("result", "更新派工单失败");
		}
		
		String companyId = dispatching.getYl10();
		List<String> roleType = getRoleType(companyId, "8");
		String contant = "有工程待检测";
		for (String str : roleType) {
			UserInfo u = new UserInfo();
			u.setYl1(companyId);
			u.setRoletype(str);
			List<UserInfo> list = userInfoService.selectRoleType(u);
			for (UserInfo userInfo : list) {
				String uname = userInfo.getUsername();
				sendYuanGong(uname, contant);
			}
		}
		
		order.setStatus("技师已完工待检查");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", dispatching);
	}
	
	/**
	 * 完工检查
	 * 更新派工单
	 * 更新订单状态
	 */
	@RequestMapping("disCheck")
	@ResponseBody
	public Msg disCheck(Dispatching dispatching,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Order order = orderService.selectById(dispatching.getOrderId());
		Dispatching dis = dispatchingService.getDispatching(dispatching.getDispatchingId());
		if(!"待检查".equals(dis.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		SysUser user = getCurrentSysUser(request);
		dispatching.setStatus("已检查");
		dispatching.setCheckedBy(user.getUsername());
		if(!dispatchingService.update(dispatching)){
			return Msg.fail().add("result", "更新派工单失败");
		}
		
		//给订单的服务顾问发微信消息
		order.setStatus("派工已检查待结算");
		String createUsename = order.getCreateBy();
		String contant = "有订单待结算";
		sendYuanGong(createUsename, contant);
		
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}
	
	/**
	 * 创建结算单
	 * 更新订单状态
	 */
	@RequestMapping("addSett")
	@ResponseBody
	public Msg addSett(@RequestBody Settlement settlement,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Date date = new Date();
		DateFormat format=new SimpleDateFormat("yyyyMMdd");
		String s = format.format(date);
		
		if(dateStr == ""||numberCount == 0){
			dateStr = s;
			numberCount = 1;
		}else {
			if(s.equals(dateStr)){
				numberCount+=1;
			}else {
				dateStr = s;
				numberCount = 1;
			}
		}
		
		String string = String.valueOf(numberCount);
		String  number = StringUtils.leftPad(string, 5, "0");
		String zimu = MyTools.getRandom();
		String sett_id = "JS-"+ dateStr + zimu + number;

//		String sett_id = MyTools.getDateR();
		settlement.setSettlementId(sett_id);
		settlement.setCreateBy(getCurrentSysUser(request).getUsername());
		settlement.setCreateTime(MyTools.getTime());
		settlement.setStatus("待提交");
		
		String orderId = settlement.getOrderId();
		Order order = orderService.selectById(orderId);
		order.setStatus("已创建结算单待提交复核");
		Double count = 0.00;
		
		List<Dispatching> list = dispatchingService.selectByOrderId(orderId);
		for (Dispatching dis : list) {
			List<Dsmx> dsmxs = dsmxService.listByDid(dis);
			for (Dsmx dsmx : dsmxs) {
				double dsmxprice = Double.parseDouble(dsmx.getYl3());
				count = count + dsmxprice;
			}
		}
		
		List<Pomx> pomxs = pomxService.selectByOrderId(orderId);
		for (Pomx pomx : pomxs) {
			double pomxprice = Double.parseDouble(pomx.getTotal());
			count = count + pomxprice;
		}
		String yl9 = order.getYl9();
		double payMoney = 0;
		if(yl9 != null && !"".equals(yl9)){
			payMoney = Double.parseDouble(yl9);
		}
		
		/*String yl12 = order.getYl12();
		double getMoney = 0;
		if(yl12 != null && !"".equals(yl12)){
			getMoney = Double.parseDouble(yl12);
		}*/
		
		String yl13 = order.getYl13();
		if(yl13 == null || "".equals(yl13)){
			yl13 = "0";
		}
		order.setYl13(yl13);
		
		String total = settlement.getTotal();
		if(MySelfUtil.isDouble(total)){
			double t = Double.parseDouble(total) - payMoney;
			settlement.setTotal(t+"");
		}
		DecimalFormat df = new DecimalFormat("0.00"); 
		String money = df.format(count);
		settlement.setYl2(money);
		
		if(!settlementService.add(settlement)){
			numberCount-=1;
			return Msg.fail().add("result", "新增结算单失败");	
		}
		for(PartOffer po : settlement.getPos()){
			po.setSettId(sett_id);
			if(!partOfferService.update(po)){
				return Msg.fail().add("result", "更新报价单失败");
			}
		}
		
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", settlement);
	}
	
	/**
	 * 结算单提交
	 * 更新结算单状态
	 */
	@RequestMapping("submitS")
	@ResponseBody
	public Msg submitS(Settlement settlement,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Settlement st = settlementService.getSettlement(settlement);
		if(!"待提交".equals(st.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		settlement.setStatus("待复核");
		if(!settlementService.update(settlement)){
			return Msg.fail().add("result", "更新结算单失败");
		}
		
		String bianma = "jiesuanfh";
		String companyId = settlement.getYl10();
		String contant = "有结算单需要复核";
		List<String> quanxian = getQuanxian(bianma, companyId);
		if(quanxian == null || "".equals(quanxian)){
			return Msg.fail().add("result", "没有复核人");
		}
		for (String string : quanxian) {
			sendYuanGong(string,contant);
		}
		
		Order order = orderService.selectById(settlement.getOrderId());
		order.setStatus("结算单已提交待复核");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}
	
	/**
	 * 结算单复核
	 * 更新结算单状态
	 */
	@RequestMapping("checkS")
	@ResponseBody
	public Msg checkS(Settlement settlement,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Order order = orderService.selectById(settlement.getOrderId());
		Settlement st = settlementService.getSettlement(settlement);
		if(!"待复核".equals(st.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		settlement.setStatus("待付款");
		settlement.setYl3(username);
		settlement.setYl4(MyTools.getTime());
		if(!settlementService.update(settlement)){
			return Msg.fail().add("result", "更新结算单失败");
		}
		
		String createUsername = order.getCreateBy();
		String contant = "有结算单带客户付款";
		sendYuanGong(createUsername, contant);
		
		order.setStatus("结算单已复核待客户付款");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}
	
	/**
	 * 结算单取消复核
	 * 更新结算单状态
	 */
	@RequestMapping("nocheckS")
	@ResponseBody
	public Msg nocheckS(Settlement settlement,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Order order = orderService.selectById(settlement.getOrderId());
		Settlement st = settlementService.getSettlement(settlement);
		if(!"待复核".equals(st.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		settlement.setStatus("复核未通过");
		settlement.setYl3(username);
		settlement.setYl4(MyTools.getTime());
		if(!settlementService.update(settlement)){
			return Msg.fail().add("result", "更新结算单失败");
		}
		
		String createUsername = order.getCreateBy();
		String contant = "有结算单未通过复核";
		sendYuanGong(createUsername, contant);
		
		order.setStatus("结算单未提交复核");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}
	
	/**
	 * 客户支付
	 * 更新结算单状态
	 */
	@RequestMapping("pay")
	@ResponseBody
	public Msg pay(Settlement settlement,HttpServletRequest request,String state) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Order order = orderService.selectById(settlement.getOrderId());
		Settlement st = settlementService.getSettlement(settlement);
		if(!"待付款".equals(st.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		settlement.setStatus("待确认");
		if(!settlementService.update(settlement)){
			return Msg.fail().add("result", "更新结算单失败");
		}
		
		RevenueStatement revenueStatement = new RevenueStatement();
		revenueStatement.setRevenuestatementId(MyTools.getDateR());
		revenueStatement.setOrderId(st.getOrderId());
		revenueStatement.setSettId(st.getSettlementId());
		revenueStatement.setCreateBy(username);
		revenueStatement.setCreateTime(MyTools.getTime());
		revenueStatement.setTotal(st.getTotal());
		revenueStatement.setStatus(state);
		revenueStatement.setYl1("订单支付");
		
		if(!revenueStatementService.add(revenueStatement)){
			return Msg.fail().add("result", "更新订单失败");
		}
		
		order.setStatus("客户已付款待财务确认");
		List<String> list = getRoleType(settlement.getYl10(),"4");
		String contant = "有财务单需要确认";
		for (String roleType : list) {
			UserInfo user = new UserInfo();
			user.setRoletype(roleType);
			List<UserInfo> userList = userInfoService.selectUser(user);
			for (UserInfo userInfo : userList) {
				sendYuanGong(userInfo.getUsername(), contant);
			}
		}
		
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
	}
	
	/**
	 * 财务确认
	 * 更新结算单状态
	 * 更新订单状态
	 */
	@RequestMapping("payCon")
	@ResponseBody
	public Msg payCon(Settlement settlement,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		Order order = orderService.selectById(settlement.getOrderId());
		Settlement st = settlementService.getSettlement(settlement);
		if(!"待确认".equals(st.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		settlement.setStatus("财务已确认");
		if(!settlementService.update(settlement)){
			return Msg.fail().add("result", "更新结算单失败");
		}
		
		order.setStatus("财务已确认");
		String responsecontent = JSONArray.fromObject(order).toString();
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新订单失败");
		}
		
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "更新成功");
	}
	
	/**
	 * 取消订单
	 * 更新订单状态
	 */
	@RequestMapping("orderCancel")
	@ResponseBody
	public Msg orderCancel(Order order,HttpServletRequest request) {
		order.setStatus("已取消");
		if(!orderService.update(order)){
			return Msg.fail().add("result", "更新订单单失败");
		}
		return Msg.success().add("result", "更新成功");
	}
	
	/**
	 * 查询工时报价列表
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("selectDsmx")
	@ResponseBody
	public Msg selectDsmx(PartOffer partOffer, HttpServletRequest request,int page) {	
		String username = getCurrentSysUser(request).getUsername();
		partOffer.setCreateBy(username);
		partOffer.setYl3("1");
		List<PartOffer> list = getStatus(username, partOffer);
		List<PartOffer> selectDsmx = partOfferService.selectDsmx(partOffer,page);
		return Msg.success().add("result",selectDsmx).add("size", list.size());
	}
	
	/**
	 * 待完成工时报价列表
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("pendDsmx")
	@ResponseBody
	public Msg pendDsmx(PartOffer partOffer, HttpServletRequest request) {	
		String username = getCurrentSysUser(request).getUsername();
		partOffer.setCreateBy(username);
		partOffer.setYl3("1");
		List<PartOffer> list = getStatus(username, partOffer);
		
		Collections.sort(list, new Comparator<PartOffer>(){
            public int compare(PartOffer o1, PartOffer o2) {
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	int mark = 1;
          	   	try {
	          		Date date0 = sdf.parse(o1.getCreateTime());
	          		Date date1 = sdf.parse(o2.getCreateTime());
	          		if(date0.getTime() > date1.getTime()){
	          		    mark =  -1;
	          		}
	          		if(o1.getCreateTime().equals(o2.getCreateTime())){
	          		    mark =  0;
	          		}
          	   } catch (Exception e) {
          		   e.printStackTrace();
          	   }
          	   return mark;
            }
        }); 
		return Msg.success().add("result",list).add("size", list.size());
	}
	
	/**
	 * 查询工时报价详情
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("dsmxDetails")
	@ResponseBody
	public Msg dsmxDetails(PartOffer partOffer, HttpServletRequest request) {
		if(partOffer.getPartofferId() == null || "".equals(partOffer.getPartofferId()) ){
			return Msg.success().add("result", "id不能为空");
		}
		PartOffer po = partOfferService.getParOffer(partOffer);
		return Msg.success().add("result",po);
	}
	
	/**
	 * 查询配件报价列表
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("selectPomx")
	@ResponseBody
	public Msg selectPomx(PartOffer partOffer, HttpServletRequest request,int page, String type) {
		
		String username = getCurrentSysUser(request).getUsername();
		partOffer.setYl3("2");
		partOffer.setCreateBy(username);
		List<PartOffer> list = getStatus(username, partOffer);
		List<PartOffer> selectPomx = partOfferService.selectPomx(partOffer,page);
		return Msg.success().add("result",selectPomx).add("size", list.size());

	}
	
	/**
	 * 待完成配件报价列表
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("pendPomx")
	@ResponseBody
	public Msg pendPomx(PartOffer partOffer, HttpServletRequest request) {	
		String username = getCurrentSysUser(request).getUsername();
		partOffer.setYl3("2");
		partOffer.setCreateBy(username);
		List<PartOffer> list = getStatus(username, partOffer);
		Collections.sort(list, new Comparator<PartOffer>(){
            public int compare(PartOffer o1, PartOffer o2) {
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	int mark = 1;
          	   	try {
	          		Date date0 = sdf.parse(o1.getCreateTime());
	          		Date date1 = sdf.parse(o2.getCreateTime());
	          		if(date0.getTime() > date1.getTime()){
	          		    mark =  -1;
	          		}
	          		if(o1.getCreateTime().equals(o2.getCreateTime())){
	          		    mark =  0;
	          		}
          	   } catch (Exception e) {
          		   e.printStackTrace();
          	   }
          	   return mark;
            }
        }); 
		return Msg.success().add("result",list).add("size", list.size());
	}
	
	/**
	 * 查询配件报价详情
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("pomxDetails")
	@ResponseBody
	public Msg pomxDetails(PartOffer partOffer, HttpServletRequest request) {
		if(partOffer.getPartofferId() == null || "".equals(partOffer.getPartofferId()) ){
			return Msg.success().add("result", "id不能为空");
		}
		PartOffer po = partOfferService.getParOffer(partOffer);
		return Msg.success().add("result",po);
	}

	/**
	 * 单独的配件报价提交复核
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("updatePartY")
	@ResponseBody
	public Msg updatePartY(PartOffer partOffer, HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String responsecontent = JSONArray.fromObject(partOffer).toString();
		String username = getCurrentSysUser(request).getUsername();
		
		PartOffer p = partOfferService.getParOffer(partOffer);
		if(!"待提交".equals(p.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		partOffer.setStatus("待复核");
		
		String bianma1 = "peijianfh";
		String companyId1 = partOffer.getYl10();
		String contant1 = "有报价单需要复核";
		List<String> quanxian1 = getQuanxian(bianma1, companyId1);
		if(quanxian1 == null || "".equals(quanxian1)){
			return Msg.fail().add("result", "没有复核人");
		}
		for (String string : quanxian1) {
			sendYuanGong(string,contant1);
		}
	
		if(!partOfferService.update(partOffer)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
		
	}
	
	
	/**
	 * 单独的工时报价提交复核
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("updatePartG")
	@ResponseBody
	public Msg updatePartG(PartOffer partOffer, HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String responsecontent = JSONArray.fromObject(partOffer).toString();
		String username = getCurrentSysUser(request).getUsername();
		
		PartOffer p = partOfferService.getParOffer(partOffer);
		if(!"待提交".equals(p.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		partOffer.setStatus("待复核");

		String bianma = "gongshifh";
		String companyId = partOffer.getYl10();
		String contant = "有报价单需要复核";
		List<String> quanxian = getQuanxian(bianma, companyId);
		if(quanxian == null || "".equals(quanxian)){
			return Msg.fail().add("result", "没有复核人");
		}
		for (String string : quanxian) {
			sendYuanGong(string,contant);
		}
		if(!partOfferService.update(partOffer)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "提交成功");
		
	}
	
	/**
	 * 确认复核
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("updatePartN")
	@ResponseBody
	public Msg updatePartN(PartOffer partOffer, HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String responsecontent = JSONArray.fromObject(partOffer).toString();
		String username = getCurrentSysUser(request).getUsername();
		
		PartOffer p = partOfferService.getParOffer(partOffer);
		if(!"待复核".equals(p.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		partOffer.setStatus("已复核");
		partOffer.setCheckedBy((getCurrentSysUser(request).getUsername()));
		partOffer.setCheckedTime(MyTools.getTime());

		String contant  = "有订单已复核";
		List<String> list = getRoleType(partOffer.getYl10(),"7");
		if(list.size()>0){
			for (String roleType : list) {
				UserInfo userInfo = new UserInfo();
				userInfo.setRoletype(roleType);
				List<UserInfo> selectUser = userInfoService.selectUser(userInfo);
				for (UserInfo user : selectUser) {
					sendYuanGong(user.getUsername(), contant);
				}
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result",partOfferService.updatePartN(partOffer));
		}
		status = "failure";
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.fail().add("result", "更新失败");
	}
	
	@RequestMapping("noupdatePartN")
	@ResponseBody
	public Msg noupdatePartN(PartOffer partOffer, HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String responsecontent = JSONArray.fromObject(partOffer).toString();
		String username = getCurrentSysUser(request).getUsername();
		
		PartOffer p = partOfferService.getParOffer(partOffer);
		if(!"待复核".equals(p.getStatus())){
			return Msg.fail2().add("result", "订单状态不正确");
		}
		
		partOffer.setStatus("复核未通过");
		partOffer.setCheckedBy((getCurrentSysUser(request).getUsername()));
		partOffer.setCheckedTime(MyTools.getTime());
		
		String contant  = "有订单复核未通过";
		List<String> list = getRoleType(partOffer.getYl10(),"7");
		if(list.size()>0){
			for (String roleType : list) {
				UserInfo userInfo = new UserInfo();
				userInfo.setRoletype(roleType);
				List<UserInfo> selectUser = userInfoService.selectUser(userInfo);
				for (UserInfo user : selectUser) {
					sendYuanGong(user.getUsername(), contant);
				}
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result",partOfferService.updatePartN(partOffer));
		}
		status = "failure";
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.fail().add("result", "更新失败");
	
	}
	
	/**
	 * 单独新增工时报价
	 * 
	 */
	@RequestMapping("insertDsmx")
	@ResponseBody
	public Msg insertDsmx(@RequestBody PartOffer partOffer, HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		partOffer.setYl3("1");
		partOffer.setOfferBy(username);
		String xmlxId = partOffer.getYl4();
		String id = MyTools.getDateR();
		Double total = 0.00;
		for(Dsmx dsmx:partOffer.getDsmxs()){
			dsmx.setDsmxId(MyTools.getDateR());
			dsmx.setYl2(partOffer.getOrderId());
			dsmx.setYl1(id);
			dsmx.setYl4(MyTools.getTime());
			dsmx.setYl5(username);
			String yl3 = dsmx.getYl3();
			dsmx.setYl6(yl3);
			dsmx.setYl15("待完成");
			total = total +Double.parseDouble(yl3);
			if(!partOfferMXService.addD(dsmx)){
				return Msg.fail().add("result", "新增工时报价明细失败");
			}
			
			String workName = dsmx.getWorkName();
			List<SName> list = dsmxService.selectXmName(workName,xmlxId);
			if(list.size() == 0){
				SName sName  = new SName();
				sName.setPid(xmlxId);
				sName.setsName(workName);
				sName.setSnameId(MyTools.getDateR());
				sName.setYl1(yl3);
				dsmxService.insertXmName(sName);
			}
			
		}
		
		partOffer.setPartofferId(id);
		partOffer.setTotal(total+"");
		partOffer.setCreateBy(username);
		partOffer.setCreateTime(MyTools.getTime());
		partOffer.setStatus("待提交");
		if(!partOfferService.add(partOffer)){
			return Msg.fail().add("result", "创建工时报价单失败");
		}
		return Msg.success().add("result", partOffer);
	}
	
	/**
	 * 单独新增配件报价
	 * 
	 */
	@RequestMapping("insertPomx")
	@ResponseBody
	public Msg insertPomx(@RequestBody PartOffer partOffer,HttpServletRequest request){
		String username = getCurrentSysUser(request).getUsername();
		partOffer.setOfferBy(username);
		partOffer.setYl3("2");
		String id = MyTools.getDateR();
		double total = 0.00;
		for(Pomx pomx:partOffer.getPomxs()){
			
			if("sky".equals(pomx.getYl9())){
				String dateR = MyTools.getDateR();
				pomx.setYl9(dateR);
				Repoinfo p = new Repoinfo();
				p.setRepoinfoId(dateR);
				p.setName(pomx.getPartName());
				p.setPrice(pomx.getPrice());
				p.setNumber("0");
				repoinfoService.insetRepoinfo(p);
			}
			
			String price = pomx.getYl8();
			String partCount = pomx.getPartCount();
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(partCount);
			if( !isNum.matches() ){
				return Msg.success().add("result", "输入数量有误！");
			}
			if(price == null || price.equals("")){
				price = "0.00";
			}
		
			Double mxTotal = 0.00;
			if(price!=null && !"".equals(price) && partCount!=null && !"".equals(partCount)) {
				try {
					Double p = Double.parseDouble(price);
					int pc = Integer.parseInt(partCount);
					mxTotal = p*pc;
					total += mxTotal;
				}catch (Exception e) {
					e.printStackTrace();
					return Msg.fail().add("result", "参数错误");
				}
			}else {
				return Msg.fail().add("result", "参数错误");
			}
			pomx.setPomxId(MyTools.getDateR());
			pomx.setYl2(partOffer.getOrderId());
			pomx.setPartOfferId(id);
			pomx.setTotal(mxTotal+"");
			pomx.setYl10(partOffer.getYl10());
			if(!partOfferMXService.addP(pomx)){
				return Msg.fail().add("result", "新增配件报价明细失败");
			}
		}
	
		partOffer.setPartofferId(id);
		partOffer.setTotal(total+"");
		partOffer.setCreateBy(getCurrentSysUser(request).getUsername());
		partOffer.setCreateTime(MyTools.getTime());
		partOffer.setStatus("待提交");
		if(!partOfferService.add(partOffer)){
			return Msg.fail().add("result", "创建配件报价单失败");
		}
		return Msg.success().add("result", partOffer);
	}
	
	
	/**
	 * 更新车辆信息
	 */
	@RequestMapping("updateC")
	@ResponseBody
	public Msg updateC(CarInfo carinfo, HttpServletRequest request, String orderId){
		String requestcontent = request.getRequestURL().toString();
		String type1 = request.getMethod();
		String url1 = dictionariesService.selectRiZhiURL();
		String status = "success";
		String responsecontent = JSONArray.fromObject(carinfo).toString();
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();

		Order order = orderService.selectById(orderId);
		if("接车单已确认待信息反馈".equals(order.getStatus())){
			return  Msg.fail2().add("result", "订单状态不正确");
		}
		
		if(carInfoService.updateC(carinfo)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
			return Msg.success().add("result", "成功");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
		return Msg.fail().add("result", "失败");
	}
	
	/**
	 * 更新接车信息
	 * @throws IOException 
	 */
	@RequestMapping("updateR")
	@ResponseBody
	public Msg updateR(@RequestBody ReceptionWithBLOBs reception,HttpServletRequest request){
		String requestcontent = request.getRequestURL().toString();
		String type1 = request.getMethod();
		String url1 = dictionariesService.selectRiZhiURL();
		String status = "success";
		String responsecontent = JSONArray.fromObject(reception).toString();
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		
		String URL = dictionariesService.selectURL();
		String basePath = dictionariesService.selectPATH();
		StringBuilder strBul= new StringBuilder();
		
		ReceptionWithBLOBs re = receptionService.selectByOrderId(reception.getOrderId());
		if("客户已确认".equals(re.getStatus())){
			return  Msg.fail2().add("result", "订单状态不正确");
		}
		String num = re.getReceptionId();
		
		String licheng = reception.getLicheng();
		if(licheng != null && !licheng.contains(".jpg") && !"".equals(licheng)){
        	StringBuilder s = new StringBuilder();
        	String r = MyTools.getDateR();
			String url = basePath + "/" + num + "/" + r;
        	String type = "jpg";
		    url = url + "." + type;
	        try {
	        	licheng = licheng.substring(licheng.indexOf(",")+1);
				if(!MySelfUtil.GenerateImage(licheng,url)){
					return Msg.fail().add("result", "图片上传失败");
				}
				s.append(URL+"/"+num+"/"+r +"."+type);
			} catch (Exception e) {
				e.printStackTrace();
				return Msg.fail().add("result", "图片上传失败");
			}
        	reception.setMileageImg(s.toString().getBytes());
		}else {
			reception.setMileageImg(licheng.toString().getBytes());
		}
		
		String oilPhoto = reception.getYl7();
		if(oilPhoto != null && !oilPhoto.contains(".jpg") && !"".equals(oilPhoto)){
        	StringBuilder s = new StringBuilder();
        	String r = MyTools.getDateR();
			String url = basePath + "/" + num + "/" + r;
        	String type = "jpg";
		    url = url + "." + type;
	        try {
	        	oilPhoto = oilPhoto.substring(oilPhoto.indexOf(",")+1);
				if(!MySelfUtil.GenerateImage(oilPhoto,url)){
					return Msg.fail().add("result", "图片上传失败");
				}
				s.append(URL+"/"+num+"/"+r +"."+type);
			} catch (Exception e) {
				e.printStackTrace();
				return Msg.fail().add("result", "图片上传失败");
			}
        	reception.setYl7(s.toString());
		}else {
			reception.setYl7(oilPhoto);
		}
		
		List<Photo> photos = reception.getWaiguanList();
		if(photos.size()>0){
			 for (int i = 0; i<photos.size(); i++) {
		        	String tp = photos.get(i).getTupian();
		        	if(tp.contains(".jpg")){
		        		 StringBuffer sb=new StringBuffer(photos.get(i).getTupian());
		        		 strBul.append(sb).append("***");
		        	}else{
		        		String r = MyTools.getDateR();
						String url = basePath + "/" + num + "/" + r;
						String tupian = photos.get(i).getTupian();
						if (tupian.indexOf("data:image/") != -1) {
					        url = url + ".jpg";
					        try {
					        	tupian = tupian.substring(tupian.indexOf(",")+1);
								if(!MySelfUtil.GenerateImage(tupian,url)){
									return Msg.fail().add("result", "图片上传失败");
								}
								strBul.append(URL+"/"+num+"/"+r +".jpg").append("***");
							} catch (Exception e) {
								e.printStackTrace();
								return Msg.fail().add("result", "图片上传失败");
							}
						}
		        	}
				}
		}
       
        reception.setYl2(strBul.toString());
		reception.setStatus("待提交");
		reception.setYl4(request.getParameter("contactId"));
		if(!receptionService.updateR(reception)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
			return Msg.fail().add("result", "失败");
		}
		
		Order order = orderService.selectById(reception.getOrderId());
		order.setStatus("已创建接车单待提交复核");
		order.setContactId(request.getParameter("contactId"));
		if(!orderService.update(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
			return Msg.fail2().add("result", "更新订单失败");
		}
		
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
		return Msg.success().add("result", reception);
		
	}
	
	/**
	 * 删除信息反馈记录
	 */
	@RequestMapping("deleteI")
	@ResponseBody
	public Msg deleteI(InfoRelay infoRelay,String orderId,HttpServletRequest request){
		String requestcontent = request.getRequestURL().toString();
		String type1 = request.getMethod();
		String url1 = dictionariesService.selectRiZhiURL();
		String status = "success";
		String responsecontent = JSONArray.fromObject(infoRelay).toString();
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		if(infoRelayService.delete(infoRelay.getInforelayId())){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
			return Msg.success().add("result", "成功");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
		return Msg.fail().add("result", "失败");
	}
	
	/**
	 * 删除派工记录
	 */
	@RequestMapping("deleteD")
	@ResponseBody
	public Msg deleteD(Dispatching dispatching,String orderId,HttpServletRequest request){
		String requestcontent = request.getRequestURL().toString();
		String type1 = request.getMethod();
		String url1 = dictionariesService.selectRiZhiURL();
		String status = "success";
		String responsecontent = JSONArray.fromObject(dispatching).toString();
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		
		if(!dispatchingService.delete(dispatching.getDispatchingId())){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
			return Msg.fail().add("result", "失败");
		}
		
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
		return Msg.success().add("result", "成功");
		
	}
	
	/**
	 * 删除配件报价记录
	 */
	@RequestMapping("deleteP")
	@ResponseBody
	public Msg deleteP(PartOffer partOffer,String str){
		String orderId = partOffer.getOrderId();
		String partofferId = partOffer.getPartofferId();
		
		PartOffer parOffer = partOfferService.getParOffer(partOffer);
		Order order = orderService.selectById(orderId);
		String o = order.getYl8();
		String yl9 = order.getYl9();
		String yl12 = order.getYl12();
		String p = parOffer.getYl8();
		if(p == null || "".equals(p)){
			p="0";
		}
		String yl13 = order.getYl13();
		
		if("已交付定金".equals(parOffer.getYl15())){
			if("是".equals(str)){
				if(yl13 == null || "".equals(yl13) ){
					yl13 = "0";
				}
				double tuihuan = Double.parseDouble(yl13) + Double.parseDouble(p);
				double count = Double.parseDouble(o) - Double.parseDouble(p);
				/*double sjcount = Double.parseDouble(yl9) - Double.parseDouble(p);*/
				order.setYl8(count+"");
				/*order.setYl9(sjcount+"");*/
				order.setYl13(tuihuan+"");
				
				if(!orderService.update(order)){
					return Msg.fail().add("result", "失败");
				}
			}else{
				if(yl12 == null || "".equals(yl12)){
					yl12 = "0";
				}
				double btcount = Double.parseDouble(yl12) + Double.parseDouble(p);
				order.setYl12(btcount+"");
				if(!orderService.update(order)){
					return Msg.fail().add("result", "失败");
				}
			}
		}else{
			double count = Double.parseDouble(o) - Double.parseDouble(p);
			order.setYl8(count+"");
			if(!orderService.update(order)){
				return Msg.fail().add("result", "失败");
			}
		}
		
		List<Pomx> pomxs = pomxService.selectByPartOfferId(partofferId);
		if(pomxs.size()>0){
			for (Pomx pomx : pomxs) {
				if(!pomxService.deleteById(pomx.getPomxId())){
					return Msg.fail().add("result", "失败");
				}
			}
		}
		if(partOfferService.delete(partofferId)){
			return Msg.success().add("result", "成功");
		}
		return Msg.fail().add("result", "失败");
	}
	
	/**
	 * 删除结算单
	 */
	@RequestMapping("deleteS")
	@ResponseBody
	public Msg deleteS(Settlement settlement){
		if(settlementService.delete(settlement.getSettlementId(),settlement.getOrderId())){
			return Msg.success().add("result", "成功");
		}
		return Msg.fail().add("result", "失败");
	}
	
	// 缓存cid
	@SuppressWarnings("rawtypes")
	@RequestMapping("savecid")
	@ResponseBody
	public Msg save(HttpServletRequest request, String cid, String userid) {
		Map mapTypes = JSON.parseObject(cid);  
		String cId = "";
		for (Object obj : mapTypes.keySet()){ 
			String str = obj.toString();
			if(str.equals("clientid")){
				cId= mapTypes.get(obj).toString();
			}
	    }  
		Cache cache = cacheManager.getCache("data-cache");
		Element e = new Element(userid, cId);
		cache.put(e);
		return Msg.success().add("result", userid);
	}
	
	//获取对应权限的角色(根据权限对应的数字)
	public List<String> getRoleType(String companyId, String roleEn){
		List<String> roleList = new ArrayList<String>();
		UserInfo userInfo = new UserInfo();
		userInfo.setYl1(companyId);
		List<UserInfo> userList = userInfoService.selectUserInfo(userInfo);
		for (UserInfo user : userList) {
			String roletype = user.getRoletype();
			String nameEn = dictionariesService.selectQuanXian(roletype);
			if(nameEn != null){
				nameEn = nameEn.replace("，", ",");
				if(nameEn.contains(",")){
					String[] split = nameEn.split(",");
					for(int i=0; i<split.length; i++){
						if(roleEn.equals(split[i])){
							roleList.add(roletype);
						}
					}
				}else{
					if(roleEn.equals(nameEn)){
						roleList.add(roletype);
					}
				}
			}
			
		}
		List<String> removeDuplicate = MySelfUtil.removeDuplicate(roleList);
		return removeDuplicate;
	}
	
	//发送消息到客户微信
	public void send(String orderId,String contant){
		String baseUrl = dictionariesService.selectWxURL();
		Order o = orderService.selectById(orderId);
		String contactId = o.getContactId();
		if(contactId != null){
			Contacts contacts = contactsService.selectById(contactId);
			String wxid = contacts.getYl1();
			String url = baseUrl+"/wx/sendMsg?openid="+wxid+"&orderid="+orderId+"&contant="+contant;
			String doHttpGet = MySelfUtil.doHttpGet(url);
			System.out.println(doHttpGet);
			System.err.println("发送完成");
		}
	}
	
	//获取拥有审核权限的人
	public List<String> getQuanxian(String bianma, String companyId){
		List<String> list = new ArrayList<String>();
		Dictionaries d = dictionariesService.selectByBianma(bianma);
		String uString = "";
		if(d != null){
			List<Dictionaries> dSon = dictionariesService.selectSon(d.getDictionariesId());
			if(dSon.size()>0){
				for (Dictionaries dic : dSon) {
					if(dic.getNameEn().equals(companyId)){
						uString = dic.getBz();
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
	}
	
	//发送消息到员工微信
	public void sendYuanGong(String username,String contant){
		String baseUrl = dictionariesService.selectWxURL();
		if(username != null){
			UserInfo userInfo = userInfoService.selectUserId(username);
			String wxid = userInfo.getYl7();
			if("开".equals(userInfo.getYl8()) && wxid != null){
				String url = baseUrl+"/wx/sendFuhe?openid="+wxid+"&contant="+contant;
				String doHttpGet = MySelfUtil.doHttpGet(url);
				System.out.println(doHttpGet);
				System.err.println("发送完成");
			}
		}
	}
	
	//删除接车单
	@RequestMapping("delReception")
	@ResponseBody
	public Msg delReception(Order order,HttpServletRequest request){
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String responsecontent = JSONArray.fromObject(order).toString();
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		
		if(!orderService.deleteReception(order)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "成功");
	}
	
	//查看待处理的订单
	@RequestMapping("pendOrder")
	@ResponseBody
	public Msg pendOrder(HttpServletRequest request,String yl10){
	
		List<Order> orderList = new ArrayList<Order>();
		String username = getCurrentSysUser(request).getUsername();
		UserInfo user = userInfoService.selectUserId(username);
		String roletype = user.getRoletype();
		String quanxian = dictionariesService.selectQuanXian(roletype);
		//获取角色具有的权限
		List<String> qxList = new ArrayList<String>();
		quanxian = quanxian.replace("，", ",");
		if(quanxian.contains(",")){
			String[] split = quanxian.split(",");
			for(int i=0; i<split.length; i++){
				qxList.add(split[i]);
			}
		}else{
			qxList.add(quanxian);
		}
		
		//对应订单状态
		List<String> ddList = new ArrayList<String>();
		List<Dictionaries> dicList = dictionariesService.selectAllRoot();
		for (String qx : qxList) {
			for (Dictionaries dic : dicList) {
				if(dic.getNameEn().equals(qx)){
					String bz = dic.getBz();
					if(bz.contains("-----")){
						String[] split = bz.split("-----");
						String status = split[1];
						status = status.replace("，", ",");
						if(status.contains(",")){
							String[] s = status.split(",");
							for(int i=0; i<s.length; i++){
								ddList.add(s[i]);
							}
						}else{
							ddList.add(status);
						}
					}
					break;
				}
			}
		}
		
		for (String status : ddList) {
			Order order = new Order();
			order.setStatus(status);
			order.setYl10(yl10);
			List<Order> list = orderService.selectByStatus(order);
			orderList.addAll(list);
		}
		
		Collections.sort(orderList, new Comparator<Order>(){
            public int compare(Order o1, Order o2) {
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	int mark = 1;
          	   	try {
	          		Date date0 = sdf.parse(o1.getCreateTime());
	          		Date date1 = sdf.parse(o2.getCreateTime());
	          		if(date0.getTime() > date1.getTime()){
	          		    mark =  -1;
	          		}
	          		if(o1.getCreateTime().equals(o2.getCreateTime())){
	          		    mark =  0;
	          		}
          	   } catch (Exception e) {
          		   e.printStackTrace();
          	   }
          	   return mark;
            }
        }); 
		return Msg.success().add("result", orderList);
	}
	
	//获取到指定车辆全部联系人
	@RequestMapping("getContants")
	@ResponseBody
	public Msg getContants(HttpServletRequest request,Order order){
		List<Order> oList = orderService.selectByCePai(order);
		List<String> cIdList = new ArrayList<String>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (Order o : oList) {
			String contactId = o.getContactId();
			if(contactId != null){
				cIdList.add(contactId);
			}
		}
		if(cIdList.size()>1){
			MySelfUtil.removeDuplicate(cIdList);
		}
		if(cIdList.size()==0){
			return Msg.success().add("result", list);
		}
		for (String contactId : cIdList) {
			Map<String, String> map = new HashMap<String, String>();
			order.setContactId(contactId);
			List<Order> cList = orderService.selectByCePai(order);
			Contacts contacts = contactsService.selectById(contactId);
			map.put("contact", contacts.getContact());
			map.put("contactTel", contacts.getContactTel());
			map.put("contactSex", contacts.getYl4());
			map.put("size", cList.size()+"");
			list.add(map);
		}
		

		for(int i =0;i < list.size() - 1;i++){
            for(int j = 0;j <  list.size() - 1-i;j++) {
            	Integer l1 = Integer.parseInt(list.get(j).get("size"));
            	Integer l2 = Integer.parseInt(list.get(j+1).get("size"));
                if(l1 < l2) {
                    Map<String, String> temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1 , temp);
                }
            }
		}
		return Msg.success().add("result", list);
	}
	
	//零件完成
	@RequestMapping("lingjianfinish")
	@ResponseBody
	public Msg lingjianfinish(Pomx pomx,HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String responsecontent = JSONArray.fromObject(pomx).toString();
		String username = getCurrentSysUser(request).getUsername();
		
		pomx.setYl15("已使用");
		if(!pomxService.update(pomx)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "更新失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "更新成功");
	}
	
	
	
	//取消订单
	@RequestMapping("qxorder")
	@ResponseBody
	public Msg qxorder(String orderid,String status){
		Order order = new Order();
		order.setOrderId(orderid);
		order.setYl7(status);
		order.setStatus("订单已取消");
		if(!orderService.update(order)){
			return Msg.fail().add("result", "取消失败");
		}
		return Msg.success().add("result", "取消成功");
	}
	
	//恢复订单
	@RequestMapping("hforder")
	@ResponseBody
	public Msg hforder(String orderid){
		Order order = orderService.selectById(orderid);
		String yl7 = order.getYl7();
		if(yl7 == null){
			return Msg.fail().add("result", "恢复失败");
		}
		order.setStatus(yl7);
		if(!orderService.update(order)){
			return Msg.fail().add("result", "恢复失败");
		}
		return Msg.success().add("result", "恢复成功");
	}
	
	/**
	 * 接车时找出过去取消的报价单列表
	 * @param carnumber
	 * @return 报价单
	 */
	@RequestMapping("choosepartoffer")
	@ResponseBody
	public Msg choosepartoffer(String carnumber){
		List<PartOffer> list = partOfferService.selectByCar(carnumber);
		return Msg.success().add("result", list);
	}
	
	/**
	 * 接车时确定过去取消的报价单
	 * @param orderid
	 * @param json
	 * @return
	 */
	@RequestMapping("confirmpartoffer")
	@ResponseBody
	public Msg confirmpartoffer(String orderid,String json){
		String[] split = json.split("---");
		List<String> paroffers= new ArrayList<>(Arrays.asList(split));
		Order order = orderService.selectById(orderid);
		
		double yl8 = Double.parseDouble(order.getYl8());
		
		if(paroffers.size()>0){
			for (String OfferID : paroffers) {
				PartOffer offer = new PartOffer();
				offer.setPartofferId(OfferID);
				PartOffer partOffer = partOfferService.getParOffer(offer);
				partOffer.setOrderId(orderid);
				
				List<Dsmx> dsmxs = partOffer.getDsmxs();
				if(dsmxs.size()>0){
					for (Dsmx dsmx : dsmxs) {
						dsmx.setYl2(orderid);
						if(!dsmxService.update(dsmx)){
							return Msg.fail().add("result", "失败");
						}
					}
				}
				double djcount = 0;
				List<Pomx> pomxs = partOffer.getPomxs();
				if(pomxs.size()>0){
					for (Pomx pomx : pomxs) {
						pomx.setYl2(orderid);
						pomx.setYl15("待使用");
						
						String dj = pomx.getYl16();
						if(dj == null || "".equals(dj)){
							dj = "0";
						}
						djcount = djcount + Double.parseDouble(dj);
						if(!pomxService.update(pomx)){
							return Msg.fail().add("result", "失败");
						}
					}
				}
				partOffer.setYl8(djcount+"");
				if(!partOfferService.update(partOffer)){
					return Msg.fail().add("result", "失败");
				}
				yl8 = yl8 + djcount;
			}
		}
		
		order.setYl8(yl8+"");
		if(!orderService.update(order)){
			return Msg.fail().add("result", "失败");
		}
		return Msg.success().add("result", "成功");
	}
	
	//是否关闭推送
	@RequestMapping("isCloseTuiSong")
	@ResponseBody
	public Msg isCloseTuiSong(String userinfoId,String yl8) {
		if(userinfoId == null || "".equals(userinfoId)){
			return Msg.fail().add("result", "失败");
		}
		UserInfo info = userInfoService.getUserInfoById(userinfoId);
		if(info != null && !"".equals(info)){
			info.setYl8(yl8);
			if(!userInfoService.updateUserInfo(info)){
				return Msg.fail().add("result", "失败");
			}
			return Msg.success().add("result", "成功");
		}
		return Msg.fail().add("result", "失败");
	}
	
	//车辆放行
	@RequestMapping("submitCar")
	@ResponseBody
	public Msg submitCar(String orderId){
		Order order = orderService.selectById(orderId);
		order.setStatus("申请交车中");
		if(!orderService.update(order)){
			return Msg.fail().add("result", "失败"); 
		}
		if(order.getYl10() != null){
			ensureSend(order.getYl10());
		}
		return Msg.success().add("result", "成功");
	}
	
	//不同意放行
	@RequestMapping("refuseCar")
	@ResponseBody
	public Msg refuseCar(String orderId){
		Order order = orderService.selectById(orderId);
		order.setStatus("拒接交车");
		if(!orderService.update(order)){
			return Msg.fail().add("result", "失败"); 
		}
		return Msg.success().add("result", "成功");
	}
	
	//同意放行---已付款
	@RequestMapping("agreeCar")
	@ResponseBody
	public Msg agreeCar(Order order){
		String orderId = order.getOrderId();
		if(orderId != null && !"".equals(orderId) ){
			Order order2 = orderService.selectById(orderId);
			order2.setStatus("同意放行---已付款");
			if(!orderService.update(order2)){
				return Msg.fail().add("result", "失败"); 
			}
			
			Revisit revisit = new Revisit();
			revisit.setRevisitId(MyTools.getDateR());
			revisit.setOrderId(order2.getOrderId());
			revisit.setCreater(order2.getCreateBy());
			revisit.setCreateTime(MyTools.getTime());
			revisit.setClient(order2.getYl18());
			revisit.setClientPhone(order2.getYl17());
			revisit.setYl1(order2.getCarNum1());
			revisit.setYl2(order2.getYl19());
			revisit.setYl3(order2.getYl20());
			revisit.setYl4("时间未到");
			revisit.setYl5(order2.getYl11());
			revisit.setYl6(order2.getYl1());
			revisit.setYl10(order2.getYl10());
			if(!revisitService.add(revisit)){
				return Msg.fail().add("result", "失败");
			}
		}
		return Msg.fail().add("result", "失败"); 
	}
	
	//同意放行---未付款
	@RequestMapping("nopayCar")
	@ResponseBody
	public Msg nopayCar(Order order){
		String orderId = order.getOrderId();
		String yl15 = order.getYl15();
		UserInfo userInfo = userInfoService.selectUserId(yl15);
		if(userInfo == null || "".equals(userInfo)){
			return Msg.fail4().add("result", "输入担保人账号不正确"); 
		}
		if(orderId != null && !"".equals(orderId) ){
			order.setStatus("待担保人确认");
			if(!orderService.update(order)){
				return Msg.fail().add("result", "失败"); 
			}
		}
		return Msg.success().add("result", "成功"); 
	}
	
	//担保信息列表
	@RequestMapping("ensureList")
	@ResponseBody
	public Msg ensureList(UserInfo userInfo){
		List<Order> list = orderService.selectByPerson(userInfo);
		List<Order> olist = new ArrayList<Order>();
		int size = 0;
		if(list.size()>0){
			for (Order order : list) {
				Order queryOrder = orderService.queryOrder(order);
				if("待担保人确认".equals(order.getStatus())){
					size = size + 1;
				}
				olist.add(queryOrder);
			}
		}
		return Msg.success().add("result", olist).add("size", size);
	}
	
	//同意担保
	@RequestMapping("ensureAgree")
	@ResponseBody
	public Msg ensureAgree(Order order){
		Order order2 = orderService.selectById(order.getOrderId());
		order2.setStatus("同意放行---未付款");
		if(!orderService.update(order2)){
			return Msg.fail().add("result", "失败"); 
		}
		if(order2.getYl10() != null){
			ensureSend(order2.getYl10());
		}
		Revisit revisit = new Revisit();
		revisit.setRevisitId(MyTools.getDateR());
		revisit.setOrderId(order2.getOrderId());
		revisit.setCreater(order2.getCreateBy());
		revisit.setCreateTime(MyTools.getTime());
		revisit.setClient(order2.getYl18());
		revisit.setClientPhone(order2.getYl17());
		revisit.setYl1(order2.getCarNum1());
		revisit.setYl2(order2.getYl19());
		revisit.setYl3(order2.getYl20());
		revisit.setYl4("时间未到");
		revisit.setYl5(order2.getYl11());
		revisit.setYl6(order2.getYl1());
		revisit.setYl10(order2.getYl10());
		if(!revisitService.add(revisit)){
			return Msg.fail().add("result", "失败");
		}
		return Msg.success().add("result", "成功");
	}
	
	//不同意担保
	@RequestMapping("ensureRefuse")
	@ResponseBody
	public Msg ensureRefuse(Order order){
		order.setStatus("申请交车中");
		if(!orderService.update(order)){
			return Msg.fail().add("result", "失败"); 
		}
		if(order.getYl10() != null){
			ensureSend(order.getYl10());
		}
		return Msg.success().add("result", "成功");
	}
	
	//担保人待处理
	@RequestMapping("ensurePend")
	@ResponseBody
	public Msg ensurePend(UserInfo userInfo){
		List<Order> list = orderService.selectByPerson(userInfo);
		List<Order> olist = new ArrayList<Order>();
		if(list.size()>0){
			for (Order order : list) {
				if("待担保人确认".equals(order.getStatus())){
					Order queryOrder = orderService.queryOrder(order);
					olist.add(queryOrder);
				}
			}
		}
		return Msg.success().add("result", olist).add("size", olist.size());
	}
	
	//停工修改状态
	@RequestMapping("workstop")
	@ResponseBody
	public Msg workStop(String dispatchingId){
		Dispatching dispatching = dispatchingService.getDispatching(dispatchingId);
		dispatching.setStatus("已停工");
		if(!dispatchingService.update(dispatching)){
			return  Msg.fail().add("result", "停工失败");
		}
		
		String username = dispatching.getCheckedBy();
		sendYuanGong(username, "有派工单停工，需要重新派工");
		
		return Msg.success().add("result", "停工成功");
	}
	
	//修改技师--确定更换的员工
	@RequestMapping("workconfirm")
	@ResponseBody
	public Msg workConfirm(Dispatching dispatching){
		dispatching.setStatus("待开工");
		if(!dispatchingService.update(dispatching)){
			return  Msg.fail().add("result", "停工失败");
		}
		String username = dispatching.getMaintenanceTechnician();
		sendYuanGong(username, "有派工单要派工");
		return Msg.success().add("result", "停工成功");
	}
	
	//获取公司全部人员的信息
	@RequestMapping("getUserInfo")
	@ResponseBody
	public Msg getUserInfo(String yl10){
		UserInfo userInfo = new UserInfo();
		userInfo.setYl1(yl10);
		List<UserInfo> list = userInfoService.selectUser(userInfo);
		return Msg.success().add("result", list);
	}
	
	//担保时候推送消息
	public void ensureSend(String companyId){
		UserInfo userInfo = new UserInfo();
		userInfo.setYl1(companyId);
		List<UserInfo> userList = userInfoService.selectUserInfo(userInfo);
		for (UserInfo uInfo : userList) {
			String roletype = uInfo.getRoletype();
			List<Dictionaries> roleList = dictionariesService.getRoleByCompanyId(companyId);
			for (Dictionaries dic : roleList) {
				String nameEn = dic.getNameEn();
				String name = dic.getName();
				if(nameEn.contains("4")){
					sendYuanGong(uInfo.getUsername(), "有要交车");
				}
			}
		}
	}
	
	//未付款放行后 ， 结账
	@RequestMapping("getmoney")
	@ResponseBody
	public Msg getMoney(String orderId){
		Order order = orderService.selectById(orderId);
		order.setStatus("同意放行---已付款");
		if(!orderService.update(order)){
			return  Msg.fail().add("result", "失败");
		}
		return Msg.success().add("result", "成功");
	}
	
	
	//根据用户名获取需要其处理状态获取到对应数据
	public List<PartOffer> getStatus(String username,PartOffer partOffer){
		Integer page = -1;
		String leixing = partOffer.getYl3();
		List<PartOffer> partList = new ArrayList<PartOffer>();
		UserInfo user = userInfoService.selectUserId(username);
		String roletype = user.getRoletype();
		String quanxian = dictionariesService.selectQuanXian(roletype);
		//获取角色具有的权限
		List<String> qxList = new ArrayList<String>();
		quanxian = quanxian.replace("，", ",");
		if(quanxian.contains(",")){
			String[] split = quanxian.split(",");
			for(int i=0; i<split.length; i++){
				qxList.add(split[i]);
			}
		}else{
			qxList.add(quanxian);
		}
		
		//对应订单状态
		List<String> ddList = new ArrayList<String>();
		List<Dictionaries> dicList = dictionariesService.selectAllRoot();
		for (String qx : qxList) {
			for (Dictionaries dic : dicList) {
				if(dic.getNameEn().equals(qx)){
					String bz = dic.getBz();
					if(bz.contains("-----")){
						String[] split = bz.split("-----");
						String status = split[1];
						status = status.replace("，", ",");
						if(status.contains(",")){
							String[] s = status.split(",");
							for(int i=0; i<s.length; i++){
								ddList.add(s[i]);
							}
						}else{
							ddList.add(status);
						}
					}
					break;
				}
			}
		}
		
		if(leixing.equals("1")){
			for (String status : ddList) {
				PartOffer part = new PartOffer();
				part.setStatus(status);
				part.setYl3(leixing);
				part.setCreateBy(partOffer.getCreateBy());
				part.setYl10(partOffer.getYl10());
				List<PartOffer> list = partOfferService.selectDsmx(part,page);
				partList.addAll(list);
			}
		}
		if(leixing.equals("2")){
			for (String status : ddList) {
				PartOffer part = new PartOffer();
				part.setYl3(leixing);
				part.setStatus(status);
				part.setCreateBy(partOffer.getCreateBy());
				part.setYl10(partOffer.getYl10());
				List<PartOffer> list = partOfferService.selectPomx(part, page);
				partList.addAll(list);
			}
		}
		return partList;
	}
}