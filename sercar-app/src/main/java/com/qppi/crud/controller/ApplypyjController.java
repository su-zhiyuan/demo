package com.qppi.crud.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Applypyj;
import com.qppi.crud.bean.Dictionaries;
import com.qppi.crud.bean.Order;
import com.qppi.crud.bean.Paymentrequest;
import com.qppi.crud.bean.Photo;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.service.ApplypyjService;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.UserInfoService;
import com.qppi.crud.utils.GetRandem;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("applypyj")
public class ApplypyjController extends BaseController{
	@Autowired
	private ApplypyjService applypyjService;
	@Autowired
	private DictionariesService dictionariesService;
	@Autowired
	private UserInfoService userInfoService;


	
	/**
	 * 获取列表
	 * @param applypyj
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Msg listApplypyj(Applypyj applypyj,int page) {
		List<Applypyj> list = applypyjService.list(applypyj,page);
		return Msg.success().add("result", list);
	}
	
	/**
	 * 获取个人备用金的信息
	 * @param applypyj
	 * @return
	 */
	@RequestMapping("select")
	@ResponseBody
	public Msg select(HttpServletRequest request,int page,Applypyj applypyj) {
		String username = getCurrentSysUser(request).getUsername();
		String yl10 = applypyj.getYl10();
		List<Applypyj> list = getStatus(username, yl10);
		applypyj.setYl4(username);
		return Msg.success().add("result", applypyjService.select(applypyj,page)).add("size", list.size());
	}
	
	/**
	 * 财务查看的待处理的报销
	 * @param applypyj
	 * @param request
	 * @return
	 */
	@RequestMapping("pendApplypyj")
	@ResponseBody
	public Msg pendApplypyj(Applypyj applypyj, HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		List<Applypyj> list = getStatus(username,applypyj.getYl10());
		Collections.sort(list, new Comparator<Applypyj>(){
            public int compare(Applypyj o1, Applypyj o2) {
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	int mark = 1;
          	   	try {
	          		Date date0 = sdf.parse(o1.getYl3());
	          		Date date1 = sdf.parse(o2.getYl3());
	          		if(date0.getTime() > date1.getTime()){
	          		    mark =  -1;
	          		}
	          		if(o1.getYl3().equals(o2.getYl3())){
	          		    mark =  0;
	          		}
          	   } catch (Exception e) {
          		   e.printStackTrace();
          	   }
          	   return mark;
            }
        }); 
		return Msg.success().add("result", list).add("size", list.size());
		
	}
	
	/**
	 * 财务确认
	 * @param request
	 * @param applypyj
	 * @param req
	 * @return
	 */
	@RequestMapping("affirmApplypyj")
	@ResponseBody
	public Msg affirmApplypyj(HttpServletRequest request,@RequestBody Applypyj applypyj) {
		
		String uname = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(applypyj).toString();
		String type1 = request.getMethod();
		String url1 = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		List<Photo> photos = applypyj.getPhotos();
		String basePath = dictionariesService.selectPATH();
		String id = applypyj.getApplypyjId();
		StringBuilder str = new StringBuilder();
		
		for (Photo photo : photos) {
			String r = MyTools.getDateR();
			String url = basePath + "/" + id + "/" + r;
			String tupian = photo.getTupian();
			System.out.println(url);
			if (tupian.indexOf("data:image/") != -1) {
			        int firstIndex = tupian.indexOf("data:image/") + 11;
			        int index1 = tupian.indexOf(";base64,");
			        String type = tupian.substring(firstIndex, index1);
			        url = url + "." + type;
			        try {
			        	tupian = tupian.substring(tupian.indexOf(",")+1);
						if(!MySelfUtil.GenerateImage(tupian,url)){
							return Msg.fail().add("result", "图片上传失败");
						}
						str.append(r +"."+type).append("+");
					} catch (Exception e) {
						e.printStackTrace();
						return Msg.fail().add("result", "图片上传失败");
					}
			}
		}
		
		applypyj.setYl9(str.toString());
		
		applypyj.setYl1("财务确认待申请人确认");
		String username = applypyjService.get(applypyj).getYl4();
		String contant = "备用金财务已确认";
		sendYuanGong(username, contant);
		
		if(!applypyjService.update(applypyj)){
			status = "failure";
			MySelfUtil.addRiZhi(uname, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
			return Msg.fail().add("result", "失败");
		}
		MySelfUtil.addRiZhi(uname, Xiangmuming, requestcontent, responsecontent, status, type1, url1);
		return Msg.success().add("result", "成功");
	}
	
	
	/**
	 * 获取对象
	 * @param applypyj
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public Msg get(Applypyj applypyj){
		String URL = dictionariesService.selectURL();
		if(applypyj.getApplypyjId() == null || applypyj.getApplypyjId().equals("")){
			return Msg.success().add("result", "applypyjId不能为空");
		}else{
			Applypyj pay = applypyjService.get(applypyj);
			
			String money = pay.getMoney();
			Map<String, String> map = MySelfUtil.number2CNMontrayUnit(money);
			pay.setMoneyMap(map);
		
			List<String> path = new ArrayList<String>();
			String yl9 = pay.getYl9();
			if(yl9 != null && !"".equals(yl9)){
				String id = applypyj.getApplypyjId();
				String[] split = yl9.split("\\+");
				for (String photo : split) {
					photo = URL+ "/" + id + "/" + photo;
					path.add(photo);
				}
				pay.setCwphotos(path);
			}
			return Msg.success().add("result", pay);
		}
	}
	
	/**
	 * 增加对象
	 * @param applypyj
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Msg add(Applypyj applypyj, HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		applypyj.setApplypyjId(GetRandem.getDateR());
		applypyj.setYl3(MyTools.getTime());
		applypyj.setYl1("待审批");
		applypyj.setYl4(username);
		try {
			double m = Double.parseDouble(applypyj.getMoney());
			DecimalFormat df = new DecimalFormat("0.00"); 
			String money = df.format(m);
			applypyj.setMoney(money);
			
		} catch (Exception e) {
			e.printStackTrace();
			return Msg.fail().add("result", "所填不是价格不是数字");
		}
		
		String bianma = "beiyongjinsp";
		String companyId = applypyj.getYl10();
		String contant = "有备用金申请需要审批";
		List<String> quanxian = getQuanxian(bianma, companyId);
		for (String string : quanxian) {
			sendYuanGong(string,contant);
		}
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(applypyj).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		if(!applypyjService.add(applypyj)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "fail");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "success");
	}

	/**
	 * 更新对象
	 * @param applypyj
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Msg update(Applypyj applypyj, HttpServletRequest request){
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(applypyj).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String quanxian = "beiyongjinsp";
		Dictionaries d = dictionariesService.selectByBianma(quanxian);
		String companyId = applypyj.getYl10();
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
			String[] uArry = uString.split(",");
			List<String> urList = new ArrayList<String>();
			for (String string : uArry) {
				urList.add(string);
			}
			
			if(applypyj.getApplypyjId() == null || applypyj.getApplypyjId().equals("") ){
				return Msg.success().add("result", "evectionId不能为空");
			}else{
				Applypyj eve = applypyjService.get(applypyj);
				if(applypyj.getYl1().equals("已拒绝")){
					applypyj.setYl16(username);
					
					String cUsername = applypyjService.get(applypyj).getYl4();
					String contant = "备用金申请已拒绝";
					sendYuanGong(cUsername, contant);
					
					if(!applypyjService.update(applypyj)){
						status = "failure";
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
						return Msg.fail().add("result", "更新失败");
					}
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.success().add("result", "更新成功");
				}else{
					
					String details = "";
					String oldBz = eve.getYl2();
					String newBz = applypyj.getYl2();
					if(newBz != null && !"".equals(newBz)){
						if (oldBz != null && !"".equals(oldBz)) {
							details = oldBz+ "," + applypyj.getYl15() + "--" + newBz ;
						}else{
							details = applypyj.getYl15() + "--" + newBz;
						}
					}else{
						details = oldBz;
					}
					applypyj.setYl2(details);
					
					if(eve != null){
						String user = eve.getYl16();
						if(user != null && !"".equals(user)){
							user = eve.getYl16()+","+username;
						}else{
							user = username;
						}
						applypyj.setYl16(user);
					}
					String yl16 = applypyj.getYl16();
					if(yl16.contains(",")){
						String[] aArr = yl16.split(",");
						List<String> arList = new ArrayList<String>();
						for (String string : aArr) {
							arList.add(string);
						}
						List<String> list = MySelfUtil.removeDuplicate(arList);
						
						String yl15 = "";
						String userName = "";
						for (String string : list) {
							UserInfo user = userInfoService.selectUserId(string);
							String n = user.getName();
							if(yl15.equals("")){
								yl15 = n;
							}else{
								yl15 = yl15 + "," + n;
							}
							if(userName.equals("")){
								userName = string;
							}else{
								userName = userName + "," + string;
							}
							
						}
						applypyj.setYl15(yl15);
						applypyj.setYl16(userName);
						
						System.out.println(MySelfUtil.bijiao(urList,list));
						if(MySelfUtil.bijiao(urList,list)){
							applypyj.setYl1("已审批待财务确认");
							if(!applypyjService.update(applypyj)){
								status = "failure";
								MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
								return Msg.fail().add("result", "更新失败");
							}
							
							String cUsername = applypyjService.get(applypyj).getYl4();
							String contant = "备用金申请已审批";
							sendYuanGong(cUsername, contant);
							
							String yl10 = applypyj.getYl10();
							String contants = "备用金申请需要确认";
							List<String> role = getRole(yl10,"4");
							for (String string : role) {
								UserInfo userInfo = new UserInfo();
								userInfo.setRoletype(string);
								List<UserInfo> selectUser = userInfoService.selectUser(userInfo);
								for (UserInfo uInfo : selectUser) {
									String uName = uInfo.getUsername();
									sendYuanGong(uName, contants);
								}
							}
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
							return Msg.success().add("result", "更新成功");
						}else {
							applypyj.setYl1("待审批");
							if(!applypyjService.update(applypyj)){
								status = "failure";
								MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
								return Msg.fail().add("result", "更新失败");
							}
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
							return Msg.success().add("result", "更新成功");
						}
					}else {
						applypyj.setYl1("待审批");
						if(!applypyjService.update(applypyj)){
							status = "failure";
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
							return Msg.fail().add("result", "更新失败");
						}
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
						return Msg.success().add("result", "更新成功");
					}
					
				}
			}
		}else{
			if(!applypyjService.update(applypyj)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新失败");
			}
			
			String cUsername = applypyjService.get(applypyj).getYl4();
			String contant = "备用金申请已审批";
			sendYuanGong(cUsername, contant);
			
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "更新成功");
		}
	}
	
	/**
	 * 删除对象
	 * @param applypyj
	 * @return
	 */
	@RequestMapping("delApplypyj")
	@ResponseBody
	public Msg delApplypyj(Applypyj applypyj,HttpServletRequest request){
		
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(applypyj).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "failure";
		
		if(applypyj.getApplypyjId() == null || applypyj.getApplypyjId().equals("")){
			return Msg.success().add("result", "applypyjId不能为空");
		}else{
			if(applypyjService.delete(applypyj)){
				status = "success";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.success().add("result", "success");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "fail");
		}
	}
	
	/**
	 * 申请人确认
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("querenApplypyj")
	@ResponseBody
	public Msg querenApplypyj(Applypyj applypyj,HttpServletRequest request){
		
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(applypyj).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		if(applypyj.getApplypyjId() == null || "".equals(applypyj.getApplypyjId()) ){
			return Msg.success().add("result", "paymentrequestId不能为空");
		}else{
			applypyj.setYl1("申请人已确认");
			if(!applypyjService.update(applypyj)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "fail");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "success");
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
	
	//根据用户名获取需要其处理状态获取到对应数据
	public List<Applypyj> getStatus(String username,String yl10){
		int page = 0;
		List<Applypyj> apList = new ArrayList<Applypyj>();
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
		List<String> dd = MySelfUtil.removeDuplicate(ddList);
		for (String status : dd) {
			Applypyj ap = new Applypyj();
			ap.setYl1(status);
			ap.setYl10(yl10);
			if(!quanxian.contains("4")){
				ap.setYl3(username);
			}
			
			if(!quanxian.contains("4")){
				ap.setYl3(username);
				List<Applypyj> list = applypyjService.select(ap, page);
				apList.addAll(list);
			}else{
				if("财务确认待申请人确认".equals(status)){
					ap.setYl3(username);
					List<Applypyj> list = applypyjService.select(ap, page);
					apList.addAll(list);
				}else {
					List<Applypyj> list = applypyjService.select(ap, page);
					apList.addAll(list);
				}
			}
			
		}
		return apList;
	}
	
	//根据公司id 找到app端权限对应的角色
	public List<String> getRole(String companyId,String roleEn){
		List<String> roleList = new ArrayList<String>();
		List<Dictionaries> role = dictionariesService.getRoleByCompanyId(companyId);
		if(role == null){
			return null;
		}
		for (Dictionaries dic : role) {
			String nameEn = dic.getNameEn();
			nameEn = nameEn.replace("，", ",");
			if(nameEn.contains(",")){
				String[] split = nameEn.split(",");
				for(int i=0; i<split.length; i++){
					if(split[i].equals(roleEn)){
						String name = dic.getName();
						roleList.add(name);
					}
				}
			}else {
				if(nameEn.equals(roleEn)){
					String name = dic.getName();
					roleList.add(name);
				}
			}
		}
		return roleList;
	}
}
