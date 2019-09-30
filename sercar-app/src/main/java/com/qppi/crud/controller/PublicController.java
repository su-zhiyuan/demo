package com.qppi.crud.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Cardclock;
import com.qppi.crud.bean.CompanyInfo;
import com.qppi.crud.bean.Contacts;
import com.qppi.crud.bean.Dictionaries;
import com.qppi.crud.bean.MessageInfo;
import com.qppi.crud.bean.MyCollection;
import com.qppi.crud.bean.Order;
import com.qppi.crud.bean.Proclamation;
import com.qppi.crud.bean.SysUser;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.service.AdvertisingService;
import com.qppi.crud.service.CompanyInfoService;
import com.qppi.crud.service.ContactsService;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.MessageInfoService;
import com.qppi.crud.service.MyCollectionService;
import com.qppi.crud.service.OrderService;
import com.qppi.crud.service.ProclamationService;
import com.qppi.crud.service.SysUserService;
import com.qppi.crud.service.UserInfoService;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("public")
public class PublicController extends BaseController {
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ContactsService contactsService;
	@Autowired
	private CompanyInfoService companyInfoService;
	@Autowired
	private ProclamationService proclamationService;
	@Autowired
	private MyCollectionService myCollectionService;
	@Autowired
	private AdvertisingService advertisingService;
	@Autowired
	private MessageInfoService messageInfoService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private DictionariesService dictionariesService;
	
	/**
	 * 用户登录
	 * 生成token，用户信息存入cache
	 * 返回token,用户信息
	 */
	@RequestMapping("login")
	@ResponseBody
	public Msg login(SysUser sysUser, HttpServletRequest request,String versionNum){
		String Xiangmuming = request.getContextPath();
		String replace = Xiangmuming.replace("/", "");
		String verUrl = "http://119.97.180.51:81/tongyongInf/version/list?";
		
//		String verUrl = "http://192.168.0.205:8080/tongyongInf/version/list?";
//		String version = MySelfUtil.updateVersion(versionNum, verUrl, replace);
		
		if(sysUser.getUsername() == null || sysUser.getPassword() == null){
			return Msg.fail().add("result", "用户名或密码不对");
		}
		sysUser.setPassword(new SimpleHash("SHA-1", sysUser.getUsername(), sysUser.getPassword()).toString());	//密码加密
		sysUser = sysUserService.getSysUser(sysUser);
		if(sysUser == null){
			return Msg.fail().add("result", "用户名或密码不对");
		}
		if(sysUser.getStatus().equals("停用")){
			return Msg.fail().add("result", "账号已停用");
		}
		String token = MyTools.getDateR();
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(sysUser.getUsername());
		userInfo = userInfoService.getUserInfo(userInfo);
		if(userInfo == null){
			return Msg.fail2().add("result", "获取用户信息失败");
		}
		
		//获取到角色权限
		String companyId = userInfo.getYl1();
		Map<String, String> map = new HashMap<String, String>();
		
		String roletype = userInfo.getRoletype();
		String YW = dictionariesService.selectYingWen(roletype,companyId);
		YW = YW.replace("，", ",");
		String[] split = YW.split(",");
		
		List<Dictionaries> roots = dictionariesService.selectAllRoot();
		for(int i=0; i<roots.size(); i++){
			boolean flag = false;
			String nameEn = roots.get(i).getNameEn();
			for(int j=0; j<split.length; j++){
				String str = split[j];
				if(str.equals(nameEn)){
					String bianma = roots.get(i).getBianma();
					map.put(bianma, "是");
					flag = true;
					break;
				}
			}
			if(flag == false){
				String bianma = roots.get(i).getBianma();
				map.put(bianma, "否");
			}
		}
		userInfo.setRoot(map);
		
		CompanyInfo companyInfo = new CompanyInfo();
		companyInfo.setCompanyinfoId(userInfo.getYl1());
		companyInfo = companyInfoService.query(companyInfo);
		if(companyInfo == null){
			return Msg.fail2().add("result", "获取公司信息失败");
		}
				
		String username = userInfo.getUsername();
		String companyinfoId = companyInfo.getCompanyinfoId();
		
		//复核权限
		Map<String, String> fhMap = new HashMap<String, String>();
		String fhXiangxi = "fuhexifen";
		Map<String, Dictionaries> fuHe = dictionariesService.selectAllXiangxi(companyinfoId,fhXiangxi);
		if(fuHe != null && !"".equals(fuHe)){
			for(Map.Entry<String, Dictionaries> entry : fuHe.entrySet()){
				Dictionaries dic = entry.getValue();
				String bz = dic.getBz();
				bz = bz.replace("，", ",");
				if(bz.contains(",")){
					String[] spName = bz.split(",");
					for (String string : spName) {
						if(string.equals(username)){
							fhMap.put(entry.getKey(), "是");
						}
					}
				}else{
					if(bz.equals(username)){
						fhMap.put(entry.getKey(), "是");
					}
				}
			}
		}
		userInfo.setReview(fhMap);
		
		//审批权限
		Map<String, String> spMap = new HashMap<String, String>();
		Map<String, String> spNameMap = new HashMap<String, String>();
		String spXiangxi = "shenpixifen";
		Map<String, Dictionaries> shenPi = dictionariesService.selectAllXiangxi(companyinfoId,spXiangxi);
		if(shenPi != null && !"".equals(shenPi)){
			for(Map.Entry<String, Dictionaries> entry : shenPi.entrySet()){
				Dictionaries dic = entry.getValue();
				String bz = dic.getBz();
				StringBuffer str = new StringBuffer();
				bz = bz.replace("，", ",");
				if(bz.contains(",")){
					String[] spName = bz.split(",");
					for (int i = 0; i<spName.length; i++) {
						String string = spName[i];
						UserInfo user = userInfoService.selectUserId(string);
						String name = user.getName();
						if(i == spName.length-1){
							str.append(name);
						}else{
							str.append(name).append(",");
						}
						if(string.equals(username)){
							spMap.put(entry.getKey(), "是");
						}
					}
				}else{
					UserInfo user = userInfoService.selectUserId(bz);
					String name = user.getName();
					str.append(name);
					if(bz.equals(username)){
						spMap.put(entry.getKey(), "是");
					}
				}
				spNameMap.put(entry.getKey(), str.toString());
			}
		}
		userInfo.setExamination(spMap);
		userInfo.setSpName(spNameMap);
		
		sysUser.setUserInfo(userInfo);
		
		String companyLogo = companyInfo.getCompanyLogo();
		String selectPATH = dictionariesService.selectPATH();
		String selectURL = dictionariesService.selectURL();
		String re = companyLogo.replace(selectURL, selectPATH);
		
		String getImageStr = MySelfUtil.GetImageStr(re);
		getImageStr.replaceAll("[+]", "%2B");
		String imgStr= "data:image/png;base64,"+getImageStr;
		companyInfo.setCompanyLogo(imgStr);
		
		sysUser.setCompanyInfo(companyInfo);
		Cache cache = cacheManager.getCache("data-cache");
		Element e = new Element(token, sysUser);
		cache.put(e);
		
		Element eq = new Element(sysUser.getUserId(), token);
		cache.put(eq);
		
//		sysUser.setVersion(version);
		return Msg.success().add("token", token).add("result", sysUser);
	}
	
	/**
	 * 新增用户
	 * 同时新增用户信息
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public Msg addUser(SysUser sysUser){
		System.err.println(sysUser.getUsername());
		System.err.println(sysUser.getPassword());
		sysUser.setPassword(new SimpleHash("SHA-1", sysUser.getUsername(), sysUser.getPassword()).toString());	//密码加密
		sysUser.setUserId(MyTools.getDateR());
		if(!sysUserService.addUser(sysUser)){
			return Msg.fail().add("result", "新增用户失败");
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setUserinfoId(MyTools.getDateR());
		userInfo.setUsername(sysUser.getUsername());
		if(!userInfoService.addUserInfo(userInfo)){
			return Msg.fail().add("result", "新增用户信息失败");
		}
		return Msg.success().add("result", "新增用户成功");
	}
	
	/**
	 * 列表用户
	 */
	@RequestMapping("listUserInfo")
	@ResponseBody
	public Msg listUserInfo(UserInfo userInfo,HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		return Msg.success().add("result", userInfoService.listUserInfo(userInfo,username));
	}
	
	/**
	 * 查询用户信息
	 */
	@RequestMapping("queryUserInfo")
	@ResponseBody
	public Msg queryUserInfo(UserInfo userInfo){
		return Msg.success().add("result", userInfoService.queryUserInfo(userInfo));
	}
	
	/**
	 * 更新用户信息
	 * 不允许修改用户角色ID
	 */
	@RequestMapping("updateUserInfo")
	@ResponseBody
	public Msg updateUserInfo(@RequestBody UserInfo userInfo,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(userInfo).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		
		UserInfo ui = userInfoService.selectUserId(username);
		userInfo.setUserinfoId(ui.getUserinfoId());
		if(userInfo.getUserinfoId() == null || userInfo.getUserinfoId().equals("")){
			return Msg.success().add("result", "userInfoId不能为空");
		}else{			
			if(!userInfoService.updateUserInfo(userInfo)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail2().add("result", "更新用户信息失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "更新用户信息成功");
		}
	}
	
	/**
	 * 新增联系人
	 */
	@RequestMapping("addContact")
	@ResponseBody
	public Msg addContact(Contacts contacts,HttpServletRequest request){
		if(contacts.getYl3() == null){
			return Msg.fail().add("result", "失败");
		}
		String orderid = contacts.getYl3();
		Order order = orderService.selectById(orderid);
		order.setYl18(contacts.getContact());
		order.setYl17(contacts.getContactTel());
		if(order != null){
			Contacts user = contactsService.selectByTel(contacts.getContactTel());
			if(user == null){
				String contactId = MyTools.getDateR();
				contacts.setContactsId(contactId);
				order.setContactId(contactId);
				if(contactsService.addContact(contacts)) {
					if(orderService.update(order)){
						return Msg.success().add("result", "添加联系人成功");
					}else {
						return Msg.fail().add("result", "添加联系人失败");
					}
				}else {
					return Msg.fail().add("result", "添加联系人失败");
				}
			}else{
				contacts.setContactsId(user.getContactsId());
				if(!contactsService.updateContact(contacts)){
					return Msg.fail().add("result", "添加失败");
				}
				String contactsId = user.getContactsId();
				order.setContactId(contactsId);
				if(orderService.update(order)){
					return Msg.success().add("result", "添加联系人成功");
				}else {
					return Msg.fail().add("result", "添加联系人失败");
				}
			}
		}
		return Msg.fail().add("result", "订单不存在");
	}
	
	/**
	 * 更新联系人
	 */
	@RequestMapping("updateContact")
	@ResponseBody
	public Msg updateContact(Contacts contacts, HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(contacts).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(contacts.getContactsId() == null || contacts.getContactsId().equals("")){
			return Msg.success().add("result", "contactsId不能为空");
		}else{			
			if(!contactsService.updateContact(contacts)) {
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail2().add("result", "更新联系人失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "更新联系人成功");
		}
	}
	
	/**
	 * 删除联系人
	 */
	@RequestMapping("delContact")
	@ResponseBody
	public Msg delContact(Contacts contacts,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(contacts).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(contacts.getContactsId() == null || contacts.getContactsId().equals("")){
			return Msg.success().add("result", "contactsId不能为空");
		}else{
			if(!contactsService.delContact(contacts)) {
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail2().add("result", "删除联系人失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "删除联系人成功");
		}
	}
	
	/**
	 * 列表联系人
	 */
	@RequestMapping("listContact")
	@ResponseBody
	public Msg listContact(Contacts contacts,HttpServletRequest request){
		return Msg.success().add("result", contactsService.listContact(contacts));
	}
	
	/**
	 * 查看公司信息
	 */
	@RequestMapping("queryCompany")
	@ResponseBody
	public Msg queryCompany(CompanyInfo ci) {
		if(ci.getCompanyinfoId() == null || ci.getCompanyinfoId().equals("")){
			return Msg.success().add("result", "companyinfoId不能为空");
		}else{			
			return Msg.success().add("result", companyInfoService.query(ci));
		}
	}
	
	/**
	 * 列表公告
	 */
	@RequestMapping("listPs")
	@ResponseBody
	public Msg listPs(Proclamation proclamation,HttpServletRequest request){
		return Msg.success().add("result", proclamationService.listPs(proclamation));
	}
	
	/**
	 * 获取公告
	 */
	@RequestMapping("getPs")
	@ResponseBody
	public Msg getPs(Proclamation proclamation,HttpServletRequest request) {
		if(proclamation.getProclamationId() == null || proclamation.getProclamationId().equals("")){
			return Msg.success().add("result", "proclamationId不能为空");
		}else{			
			return Msg.success().add("result", proclamationService.getPs(proclamation));
		}
	}
	
	/**
	 * 列表广告
	 */
	@RequestMapping("listAs")
	@ResponseBody
	public Msg listAs(HttpServletRequest request) {
		return Msg.success().add("result", advertisingService.listAs());
	}
	
	/**
	 * 列表消息
	 */
	@RequestMapping("listMessage")
	@ResponseBody
	public Msg listMessage(MessageInfo messageInfo) {
		return Msg.success().add("result", messageInfoService.listMessage(messageInfo));
	}
	
	/**
	 * 列表我的收藏
	 */
	@RequestMapping("listMC")
	@ResponseBody
	public Msg listMC(MyCollection mc) {
		if(mc.getUsername() == null || mc.getUsername().equals("")){
			return Msg.success().add("result", "username不能为空");
		}else{			
			return Msg.success().add("result", myCollectionService.listMC(mc));
		}
	}
	
	/**
	 * 点击收藏
	 */
	@RequestMapping("addMC")
	@ResponseBody
	public Msg addMC(MyCollection mc,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(mc).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(mc.getBulletinId() == null || mc.getBulletinId().equals("")){
			return Msg.success().add("result", "bulletinId不能为空");
		}else{	
			mc.setMycollectionId(MyTools.getDateR());
			if(!myCollectionService.addMC(mc)) {
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "收藏失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "收藏成功");
		}
	}
	
	/**
	 * 取消收藏
	 */
	@RequestMapping("delMC")
	@ResponseBody
	public Msg delMC(MyCollection mc,HttpServletRequest request) {
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(mc).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(mc.getBulletinId() == null || mc.getBulletinId().equals("")){
			return Msg.success().add("result", "bulletinId不能为空");
		}else{
			if(!myCollectionService.delMC(mc)) {
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "取消收藏失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "取消收藏成功");
		}
	}
	
	/**
	 * 打卡统计
	 */
	@RequestMapping("statistics")
	@ResponseBody
	public Msg statistics(Cardclock cardclock,HttpServletRequest request){
		if(cardclock.getDowntime() == null || cardclock.getDowntime().equals("")){
			return Msg.success().add("result", "downtime不能为空");
		}else{			
			return Msg.success().add("result", contactsService.statistics(cardclock));
		}
	}
	
	/**
	 * 打卡统计
	 */
	@RequestMapping("selectUserInfo")
	@ResponseBody
	public Msg selectUserInfo(UserInfo userInfo,HttpServletRequest request,String type){
		if(userInfo.getYl1() == null || userInfo.getYl1().equals("")){
			return Msg.success().add("result", "yl1不能为空");
		}else{			
			if(type == null || "".equals(type)){
				return Msg.success().add("result", userInfoService.selectUserInfo(userInfo));
			}else{
				List<UserInfo> list = listUserInfo(type, userInfo);
				return Msg.success().add("result", list);
			}
		}
	}
	
	//根据公司和权限，获取全部有该权限的人
	public List<UserInfo> listUserInfo(String role, UserInfo userInfo){
		List<UserInfo> list = new ArrayList<UserInfo>();
		List<UserInfo> userList = userInfoService.selectUserInfo(userInfo);
		for (UserInfo user : userList) {
			String roletype = user.getRoletype();
			String qx = dictionariesService.selectQuanXian(roletype);
			if(qx != null){
				qx = qx.replace("，", ",");
				if(qx.contains(",")){
					String[] split = qx.split(",");
					for(int i=0; i<split.length; i++){
						if(role.equals(split[i])){
							list.add(user);
						}
					}
				}else{
					if(role.equals(qx)){
						list.add(user);
					}
				}
			}
		}
		return list;
	}
	
}