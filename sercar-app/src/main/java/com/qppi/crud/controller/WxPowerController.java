package com.qppi.crud.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.CaigouRC;
import com.qppi.crud.bean.Complaint;
import com.qppi.crud.bean.Contacts;
import com.qppi.crud.bean.GoodsRC;
import com.qppi.crud.bean.InfoRelay;
import com.qppi.crud.bean.LuYin;
import com.qppi.crud.bean.Order;
import com.qppi.crud.bean.PartOffer;
import com.qppi.crud.bean.Pomx;
import com.qppi.crud.bean.ReceptionWithBLOBs;
import com.qppi.crud.bean.Repoinfo;
import com.qppi.crud.bean.SysUser;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.service.CaiGourcService;
import com.qppi.crud.service.ComplaintService;
import com.qppi.crud.service.ContactsService;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.GoodsrcService;
import com.qppi.crud.service.InfoRelayService;
import com.qppi.crud.service.LuyinService;
import com.qppi.crud.service.OrderService;
import com.qppi.crud.service.PartOfferService;
import com.qppi.crud.service.PomxService;
import com.qppi.crud.service.ReceptionService;
import com.qppi.crud.service.RepoinfoService;
import com.qppi.crud.service.SysUserService;
import com.qppi.crud.service.UserInfoService;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("wx")
public class WxPowerController {
	
	@Autowired
	private ContactsService contactsService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PartOfferService partOfferService;
	@Autowired
	private DictionariesService dictionariesService;
	@Autowired
	private ReceptionService receptionService;
	@Autowired
	private ComplaintService complaintService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private InfoRelayService infoRelayService;
	@Autowired
	private PomxService pomxService;
	@Autowired
	private RepoinfoService repoinfoService;
	@Autowired   
	private GoodsrcService goodsrcService;
	@Autowired   
	private CaiGourcService caigourcService;
	@Autowired
	private LuyinService luyinService;

	//绑定客户
	@RequestMapping("update")
	@ResponseBody
	public Msg bangding(String code,String username,String phone,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url1 = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		
		String baseUrl = dictionariesService.selectWxURL();
		String url = baseUrl+"/wx/getAT?code=" + code;
		String result = MySelfUtil.doHttpGet(url);
		System.out.println(result);
		if(result != null){
			JSONObject json = JSONObject.fromObject(result);
			JSONObject json1 = json.getJSONObject("extend");
			JSONObject json2 = json1.getJSONObject("result");
			System.out.println(json2);
			String wxid ;
			try{
				wxid = json2.getString("openid");
			}catch(Exception e){
				e.printStackTrace();
				
				String responsecontent = "微信号获取失败";
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
				return Msg.fail().add("result", "微信号获取失败");
			}
//			System.out.println(wxid);
			if(wxid != null){
				Contacts user = contactsService.selectByTel(phone);
				if(user != null){
					if(user.getYl1() == null || "".equals(user.getYl1())){
						user.setYl1(wxid);
						if(contactsService.updateContact(user)){
							String responsecontent = "绑定成功";
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
							return Msg.success().add("result", "绑定成功");
						}else{
							String responsecontent = "绑定失败";
							status = "failure";
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
							return Msg.fail().add("result", "绑定失败");
						}
					}else{
						String responsecontent = "用户已绑定";
						status = "failure";
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
						return Msg.fail().add("result", "用户已绑定");
					}
				}else {
					String responsecontent = "用户不存在";
					status = "failure";
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
					return Msg.fail().add("result", "用户不存在");
				}
			}else {
				String responsecontent = "微信号不正确";
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
				return Msg.fail().add("result", "微信号不正确");
			}
		}else {
			String responsecontent = "绑定失败";
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
			return Msg.fail().add("result", "绑定失败");
		}
	}
	
	//绑定员工
	@RequestMapping("updateYuanGong")
	@ResponseBody
	public Msg updateYuanGong(String code,String username,String password,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url1 = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String baseUrl = dictionariesService.selectWxURL();
		
		String url = baseUrl+"/wx/getAT?code=" + code;
		String result = MySelfUtil.doHttpGet(url);
		System.out.println(result);
		if(result != null){
			JSONObject json = JSONObject.fromObject(result);
			JSONObject json1 = json.getJSONObject("extend");
			JSONObject json2 = json1.getJSONObject("result");
			System.out.println(json2);
			String wxid ;
			try{
				wxid = json2.getString("openid");
			}catch(Exception e){
				e.printStackTrace();
				String responsecontent = "微信号获取失败";
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
				return Msg.fail().add("result", "微信号获取失败");
			}
			if(wxid != null){
				SysUser sysUser = new SysUser();
				sysUser.setPassword(password);
				sysUser.setUsername(username);
				sysUser.setPassword(new SimpleHash("SHA-1", sysUser.getUsername(), sysUser.getPassword()).toString());	//密码加密
				sysUser = sysUserService.getSysUser(sysUser);
				if(sysUser == null){
					
					String responsecontent = "用户名或密码不对";
					status = "failure";
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
					return Msg.fail().add("result", "用户名或密码不对");
				}
				UserInfo userInfo = new UserInfo();
				userInfo.setUsername(sysUser.getUsername());
				userInfo = userInfoService.getUserInfo(userInfo);
				if(userInfo == null){
					String responsecontent = "获取用户信息失败";
					status = "failure";
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
					return Msg.fail2().add("result", "获取用户信息失败");
				}
				if(userInfo.getYl7() != null && !"".equals(userInfo.getYl7())){
					String responsecontent = "已绑定微信";
					status = "failure";
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
					return Msg.fail().add("result", "已绑定微信");
				}
				userInfo.setYl7(wxid);
				userInfo.setYl8("开");
				if(!userInfoService.updateUserInfo(userInfo)){
					String responsecontent = "更新失败";
					status = "failure";
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
					return Msg.fail2().add("result", "更新失败");
				}
				String responsecontent = JSONArray.fromObject(userInfo).toString();
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
				return Msg.success().add("result", "绑定成功");
			}else {
				String responsecontent = "微信号不正确";
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
				return Msg.fail().add("result", "微信号不正确");
			}
		}else {
			String responsecontent = "绑定失败";
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url1);
			return Msg.fail().add("result", "绑定失败");
		}
	}
	
	//保存签名
	@RequestMapping("upload")
	@ResponseBody
	public Msg qianming(String imgStr,String orderid,HttpServletRequest request){
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url1 = dictionariesService.selectRiZhiURL();
		String status = "failure";
		
		imgStr = imgStr.replaceAll(" ", "+");
		Order order = orderService.selectById(orderid);
		ReceptionWithBLOBs reception = receptionService.selectByOrderId(orderid);
		reception.setAuthorizationVoucher(imgStr.getBytes());
		reception.setStatus("客户已确认");
		//发送消息到微信
		String createUsername = order.getCreateBy();
		String contant = "有接车单客户已确认";
		sendYuanGong(createUsername,contant);
		
		if(receptionService.updateR(reception)){
			order.setStatus("接车单已确认待信息反馈");
			if(orderService.update(order)){
				String responsecontent = JSONArray.fromObject(order).toString();;
				status = "success";
				MySelfUtil.addRiZhi(createUsername, Xiangmuming, requestcontent, responsecontent, status, type, url1);
				return Msg.success().add("result", "保存成功");
			}
		}
		String responsecontent = "保存失败";
		status = "failure";
		MySelfUtil.addRiZhi(createUsername, Xiangmuming, requestcontent, responsecontent, status, type, url1);
		return Msg.fail().add("result", "保存失败");
	}
	
	
	//不同意授权
	@RequestMapping("noupload")
	@ResponseBody
	public Msg noQianming(String orderid,HttpServletRequest request){
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url1 = dictionariesService.selectRiZhiURL();
		String status = "failure";
		
		Order order = orderService.selectById(orderid);
		ReceptionWithBLOBs reception = receptionService.selectByOrderId(orderid);
		reception.setStatus("待提交");
		
		//发送消息到微信
		String createUsername = order.getCreateBy();
		String contant = "有接车单客户已取消";
		sendYuanGong(createUsername,contant);
		
		if(receptionService.updateR(reception)){
			order.setStatus("已创建接车单待提交复核");
			if(orderService.update(order)){
				String responsecontent = JSONArray.fromObject(order).toString();;
				status = "success";
				MySelfUtil.addRiZhi(createUsername, Xiangmuming, requestcontent, responsecontent, status, type, url1);
				return Msg.success().add("result", "取消成功");
			}
		}
		String responsecontent = JSONArray.fromObject(order).toString();;
		MySelfUtil.addRiZhi(createUsername, Xiangmuming, requestcontent, responsecontent, status, type, url1);
		return Msg.fail().add("result", "取消失败");
	}
	
	//微信端调用
	@RequestMapping("orderWx")
	@ResponseBody
	public Msg orderWx(String code){
		String baseUrl = dictionariesService.selectWxURL();
		String url = baseUrl+"/wx/getAT?code=" + code;
		String result = MySelfUtil.doHttpGet(url);
		System.out.println(result);
		if(result != null){
			JSONObject json = JSONObject.fromObject(result);
			JSONObject json1 = json.getJSONObject("extend");
			JSONObject json2 = json1.getJSONObject("result");
			System.out.println(json2);
			String wxid ;
			try{
				wxid = json2.getString("openid");
				Contacts contacts = contactsService.selectByWX(wxid);
				if(contacts != null){
					List<Order> list = orderService.selectByContast(contacts.getContactsId());
					return Msg.success().add("result", list);
				}else{
					return Msg.fail().add("result", "获取订单失败");
				}
			}catch(Exception e){
				e.printStackTrace();
				return Msg.fail().add("result", "微信号获取失败");
			}
		}
		return null;
	}
	
	//订单详情
	@RequestMapping("orderlist")
	@ResponseBody
	public Msg orderList(String orderId){
		Order o = new Order();
		o.setOrderId(orderId);
		Order queryOrder = orderService.queryOrder(o);
		return Msg.success().add("result", queryOrder);
	}
	
	/**
	 * 报价单确认
	 * @param infoRelay
	 * @param partOffer
	 * @param request
	 * @return
	 */
	@RequestMapping("memCon")
	@ResponseBody
	public Msg memCon(InfoRelay infoRelay,PartOffer partOffer,HttpServletRequest request,String imgStr){
		imgStr = imgStr.replaceAll(" ", "+");
		String basePath = dictionariesService.selectPATH();
		String URL = dictionariesService.selectURL();
		String id = partOffer.getPartofferId();
		String r = MyTools.getDateR();
		String url = basePath + "/" + id + "/" + r + ".png";
		
		if (imgStr.indexOf("data:image/") != -1) {
	        try {
	        	imgStr = imgStr.substring(imgStr.indexOf(",")+1);
				if(!MySelfUtil.GenerateImage(imgStr,url)){
					return Msg.fail().add("result", "图片上传失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return Msg.fail().add("result", "图片上传失败");
			}
		}
		String lpath = URL + "/" + id + "/" + r+ ".png" ;
		
		boolean flag = true;
		partOffer.setYl6(lpath);
		partOffer.setYl7(url);
		partOffer.setStatus("客户已确认");
		if(!partOfferService.update(partOffer)){
			return Msg.fail2().add("result", "更新配件报价单失败");
		}
		
		List<PartOffer> list = partOfferService.selectByOrderId(partOffer.getOrderId());
		for (PartOffer p : list) {
			if(!"客户已确认".equals(p.getStatus())){
				flag = false;
				break;
			}
		}
		
		Order order = orderService.selectById(partOffer.getOrderId());
		if(flag == true){
			order.setStatus("配件已报价待派工");
		}
		
		String createUsername = order.getCreateBy();
		String contant = "有报价单单客户已确认";
		sendYuanGong(createUsername,contant);
		
		if(!orderService.update(order)){
			return Msg.fail2().add("result", "更新订单失败");
		}
		createCaigou(partOffer);
		return Msg.success().add("result", "更新成功");
	}
	
	//不同意报价
	@RequestMapping("nomemCon")
	@ResponseBody
	public Msg nomemCon(InfoRelay infoRelay,PartOffer partOffer,HttpServletRequest request){
		partOffer.setStatus("客户未同意");
		if(!partOfferService.update(partOffer)){
			return Msg.fail2().add("result", "更新配件报价单失败");
		}
		Order order = orderService.selectById(partOffer.getOrderId());
		String createUsername = order.getCreateBy();
		String contant = "有报价单单客户未同意";
		sendYuanGong(createUsername,contant);
		order.setStatus("报价单客户未同意");
		if(!orderService.update(order)){
			return Msg.fail2().add("result", "更新订单失败");
		}
		return Msg.success().add("result", "更新成功");
	}
	
	//投诉列表
	@RequestMapping("listAdvice")
	@ResponseBody
	public Msg listAdvice(String code,HttpServletRequest request,String openId){
		String baseUrl = dictionariesService.selectWxURL();
		if(openId == null){
			String url = baseUrl+"/wx/getAT?code=" + code;
			String result = MySelfUtil.doHttpGet(url);
			System.out.println(result);
			if(result != null){
				JSONObject json = JSONObject.fromObject(result);
				JSONObject json1 = json.getJSONObject("extend");
				JSONObject json2 = json1.getJSONObject("result");
				System.out.println(json2);
				String wxid ;
				try{
					wxid = json2.getString("openid");
				}catch(Exception e){
					e.printStackTrace();
					return Msg.fail().add("result", "微信号获取失败");
				}
				List<Complaint> list = complaintService.selectByWx(wxid);
				return Msg.success().add("result", list).add("openId", wxid);
			}
			return Msg.fail().add("result", "获取失败");
		}else{
			List<Complaint> list = complaintService.selectByWx(openId);
			return Msg.success().add("result", list).add("openId", openId);
		}
		
	}

	//添加投诉意见
	@RequestMapping("addAdvice")
	@ResponseBody
	public Msg addAdvice(String openId,HttpServletRequest request,Complaint complaint){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type = request.getMethod();
		String url1 = dictionariesService.selectRiZhiURL();
		String status = "failure";
		String responsecontent = JSONArray.fromObject(complaint).toString();

		complaint.setComplaintId(MyTools.getDateR());
		complaint.setComplaintTime(MyTools.getTime());
		complaint.setState("未解决");
		complaint.setYl1(openId);
		if(!complaintService.insertComplaint(complaint)){
			status = "success";
			MySelfUtil.addRiZhi(openId, Xiangmuming, requestcontent, responsecontent, status, type, url1);
			return Msg.fail().add("result", "添加失败");
		}
		return Msg.success().add("result", "添加成功");
	}

	//获取详细投诉意见
	@RequestMapping("getAdvice")
	@ResponseBody
	public Msg getAdvice(String code,HttpServletRequest request,Complaint complaint) {
		List<Complaint> list = complaintService.selectComplaintId(complaint);
		if(list.size()>0){
			return Msg.success().add("result", list.get(0));
		}
		return Msg.fail().add("result", "获取失败");
	}
	
	
	//解除绑定
	@RequestMapping("jiechubangding")
	@ResponseBody
	public Msg jiechubangding(String code,String username,String password){
		String baseUrl = dictionariesService.selectWxURL();
		String url = baseUrl+"/wx/getAT?code=" + code;

		try {
			String result = MySelfUtil.doHttpGet(url);
			if(result != null){
				JSONObject json = JSONObject.fromObject(result);
				JSONObject json1 = json.getJSONObject("extend");
				JSONObject json2 = json1.getJSONObject("result");
				System.out.println(json2);
				String wxid = json2.getString("openid");
				UserInfo userInfo = userInfoService.selectUserInfoByWx(wxid);
				if(userInfo != null && !"".equals(userInfo)){
					userInfo.setYl7("");
					userInfoService.updateUserInfo(userInfo);
				}
				Contacts contacts = contactsService.selectByWX(wxid);
				if(contacts != null && !"".equals(contacts)){
					contacts.setYl1("");
					contactsService.updateContact(contacts);
				}
				return Msg.success().add("result", "解绑成功");				
			}else {
				return Msg.fail().add("result", "解绑失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Msg.fail().add("result", "解绑失败");
		}
	}
	
	//发送消息给客户微信
	@RequestMapping("lucechange")
	@ResponseBody
	public Msg lucechange(String orderId){
		String contant = "订单需要路测"+orderId;
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
		return Msg.success().add("result", "已发送消息");
	}
	
	//路测同意
	@RequestMapping("luceagress")
	@ResponseBody
	public Msg luceagress(ReceptionWithBLOBs reception){
		String orderId = reception.getOrderId();
		ReceptionWithBLOBs re = receptionService.selectByOrderId(orderId);
		re.setIsAgree("是");
		if(!receptionService.updateR(re)){
			return Msg.fail().add("result", "更新失败");
		}
		return Msg.success().add("result", "更新成功");
	}
	
	//一键授权
	@RequestMapping("allagree")
	@ResponseBody
	public Msg allAgree(String orderId,String imgStr){
		
		imgStr = imgStr.replaceAll(" ", "+");
		String basePath = dictionariesService.selectPATH();
		String URL = dictionariesService.selectURL();
		String r = MyTools.getDateR();
		String url = basePath + "/" + orderId + "/" + r + ".png";
		
		if (imgStr.indexOf("data:image/") != -1) {
	        try {
	        	imgStr = imgStr.substring(imgStr.indexOf(",")+1);
				if(!MySelfUtil.GenerateImage(imgStr,url)){
					return Msg.fail().add("result", "图片上传失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return Msg.fail().add("result", "图片上传失败");
			}
		}
		String lpath = URL + "/" + orderId + "/" + r+ ".png" ;
		
		List<PartOffer> list = partOfferService.selectByOrderId(orderId);
		Order order = orderService.selectById(orderId);
		order.setStatus("客户已确认");
		if(list.size()>0){
			for (PartOffer part : list) {
				String status = part.getStatus();
				if("待确认".equals(status)){
					part.setStatus("客户已确认");
					part.setYl6(lpath);
					createCaigou(part);
					if(!partOfferService.update(part)){
						return Msg.fail().add("result", "授权失败");
					}
				}
			}
		}
		if(!orderService.update(order)){
			return Msg.fail().add("result", "授权失败");
		}
		return Msg.success().add("result", "授权成功");
	}
	
	//一键取消授权
	@RequestMapping("qxallagree")
	@ResponseBody
	public Msg qxAllAgree(String orderId){
		Order order = orderService.selectById(orderId);
		order.setStatus("客户已取消授权");
		
		List<PartOffer> list = partOfferService.selectByOrderId(orderId);
		if(list.size()>0){
			for (PartOffer part : list) {
				part.setStatus("客户已取消授权");
				String partofferId = part.getPartofferId();
				shanchuCaigou(partofferId);
				if(!partOfferService.update(part)){
					return Msg.fail().add("result", "授权失败");
				}
			}
		}
		if(!orderService.update(order)){
			return Msg.fail().add("result", "取消失败");
		}
		return Msg.success().add("result", "取消成功");
	}
	
	//取消授权 客户已取消授权
	@RequestMapping("qxagree")
	@ResponseBody
	public Msg qxAgree(String orderId,String partofferId){
		Order order = orderService.selectById(orderId);
		order.setStatus("客户已取消授权");
		
		PartOffer partOffer = new PartOffer();
		partOffer.setPartofferId(partofferId);
		PartOffer parOffer = partOfferService.getParOffer(partOffer);
		
		shanchuCaigou(partofferId);
		
		if(parOffer != null && !"".equals(parOffer)){
			parOffer.setStatus("客户已取消授权");
			if(!partOfferService.update(parOffer)){
				return Msg.fail().add("result", "取消失败");
			}
		}else{
			return Msg.fail().add("result", "取消失败");
		}
		if(!orderService.update(order)){
			return Msg.fail().add("result", "取消失败");
		}
		return Msg.success().add("result", "取消成功");
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
		String URL = dictionariesService.selectURL();
		String PATH = dictionariesService.selectPATH();
		
		List<LuYin> list = luyinService.listLuyin(infoRelayId);
		if(list.size()>0){
			for (LuYin luYin : list) {
				if(luYin.getYl2() == null){
					String content = luYin.getContent();
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
					luyinService.update(luYin);
				}
			}
		}
		return Msg.success().add("result", list);
	}
	
	
	//创建采购单，在客户同意报价单是时候创建期货的采购单
	public void createCaigou(PartOffer partOffer) {
		
		PartOffer parOffer = partOfferService.getParOffer(partOffer);
		String partofferId = parOffer.getPartofferId();
		String createBy = parOffer.getCreateBy();
		String fuheName = parOffer.getYl11();
		String createName = parOffer.getYl12();
		String fuheBy = parOffer.getCheckedBy();
		List<Date> dateList = new ArrayList<Date>();
		
		List<Pomx> poList = pomxService.selectByPartOfferId(partofferId);
		boolean flag1 = true;
		if(poList.size()>0){
			String caigouId = MyTools.getDateR();
			int zongji = 0;
			for (Pomx p : poList) {
				String huoqi = p.getYl6();
				String yl9 = p.getYl9();
				int nkCount =Integer.parseInt(p.getPartCount()); ;
				int danjia = Integer.parseInt(p.getPrice());
				Repoinfo repoinfo = repoinfoService.selectGet(yl9);
				int kyCount = 0;
				String yl6 = repoinfo.getYl6();
				if(!"null".equals(yl6) && yl6 != null){
					kyCount = Integer.parseInt(yl6);
				}
				
				int sdCount = 0;
				String yl7 = repoinfo.getYl7();
				if(!"null".equals(yl7) && yl7 != null){
					sdCount = Integer.parseInt(yl7);
				}
				
				if("现货".equals(huoqi)){
					int ky = kyCount - nkCount;
					int sd = sdCount + nkCount;
					repoinfo.setYl6(ky+"");
					repoinfo.setYl7(sd+"");
					repoinfoService.update(repoinfo);
				}
				if("期货".equals(huoqi)){
					flag1 = false;
					int cg = nkCount - kyCount;
					int sd = sdCount + kyCount;
					repoinfo.setYl6("0");
					repoinfo.setYl7(sd+"");
					repoinfoService.update(repoinfo);
					
					String goodsId = MyTools.getDateR();
					GoodsRC goodsRC = new GoodsRC();
					goodsRC.setCaigourcId(caigouId);
					goodsRC.setGoodsrcId(goodsId);
					goodsRC.setPrice(p.getPrice());
					goodsRC.setName(p.getPartName());
					goodsRC.setNumber(cg + "");
					goodsRC.setCreacteTime(MyTools.getTime());
					goodsRC.setReason("报价单（" + partofferId + ")需要采购的商品");
					goodsRC.setYl4(createBy);
					goodsRC.setYl5(createName);
					goodsRC.setYl6("已询价");
					goodsRC.setYl10(parOffer.getYl10());
					goodsrcService.addGood(goodsRC);
					
					int time = Integer.parseInt(p.getYl7());
					Date date = MySelfUtil.getBeforeOrAfterDate(new Date(), time+1);
					dateList.add(date);
					zongji = zongji + danjia*cg;
					
				}
			}
			if(flag1 == false){
				String dayTime = MyTools.dayTime();
				CaigouRC caigouRC = new CaigouRC();
				caigouRC.setCaigourcId(caigouId);
				caigouRC.setCreateBy(createBy);
				caigouRC.setCreateTime(dayTime);
				caigouRC.setShenhe(fuheBy);
				caigouRC.setShTime(dayTime);
				caigouRC.setStatus("已复审待完成");
				caigouRC.setZongji(zongji+"");
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String predictTime = "";
				if(dateList.size()>0){
					Date maxDate = dateList.get(0);
					for (Date date : dateList) {
						if(date.getTime() > maxDate.getTime()){
							maxDate = date;
						}
					}
					predictTime = sdf.format(maxDate);
				}
				caigouRC.setYl1(predictTime);
				caigouRC.setYl2(parOffer.getPartofferId());
				caigouRC.setYl3("报价单（" + partofferId + ")需要采购的商品");
				caigouRC.setYl4("汽车配件采购");
				caigouRC.setYl5(fuheBy);
				caigouRC.setYl6("报价单（" + partofferId + ")客户已经同意");
				caigouRC.setYl7(zongji+"");
				caigouRC.setYl10(parOffer.getYl10());
				caigouRC.setYl11(createName);
				caigouRC.setYl12(fuheName);
				caigouRC.setYl13(fuheName);
				caigourcService.addCaiGou(caigouRC);
			}
		}
	}
	
	//客户同意授权，然后取消授权的时候，删除掉期货生成的采购单和现货解除对应的锁死的部分
	public void shanchuCaigou(String partofferId) {
		List<Pomx> poList = pomxService.selectByPartOfferId(partofferId);
		if(poList.size()>0){
			for (Pomx p : poList) {
				String huoqi = p.getYl6();
				String yl9 = p.getYl9();
				Repoinfo repoinfo = repoinfoService.selectGet(yl9);
				int nkCount =Integer.parseInt(p.getPartCount()); 
				int kyCount = 0;
				String yl6 = repoinfo.getYl6();
				if(!"null".equals(yl6) && yl6 != null){
					kyCount = Integer.parseInt(yl6);
				}
				int sdCount = 0;
				String yl7 = repoinfo.getYl7();
				if(!"null".equals(yl7) && yl7 != null){
					sdCount = Integer.parseInt(yl7);
				}
				
				if("现货".equals(huoqi)){
					int ky = kyCount + nkCount;
					int sd = sdCount - nkCount;
					repoinfo.setYl6(ky+"");
					repoinfo.setYl7(sd+"");
					repoinfoService.update(repoinfo);
					
				}else{
					List<CaigouRC> list = caigourcService.selectByPart(partofferId);
					if(list.size()>0){
						for (CaigouRC caigouRC : list) {
							List<GoodsRC> goodsRCs = goodsrcService.selectByCaigou(caigouRC.getCaigourcId());
							if(goodsRCs.size()>0){
								for (GoodsRC goodsRC : goodsRCs) {
									goodsrcService.delete(goodsRC);
								}
							}
							caigourcService.delete(caigouRC);
						}
					}
				}
			}
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
	
}
