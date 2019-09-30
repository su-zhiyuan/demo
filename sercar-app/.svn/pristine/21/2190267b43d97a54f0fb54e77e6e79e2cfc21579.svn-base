package com.qppi.crud.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qppi.crud.bean.Applypyj;
import com.qppi.crud.bean.Baoxiao;
import com.qppi.crud.bean.Dictionaries;
import com.qppi.crud.bean.Photo;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.service.BaoxiaoService;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.UserInfoService;
import com.qppi.crud.utils.GetRandem;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("baoxiao")
public class BaoxiaoController extends BaseController{
	
	@Autowired
	private BaoxiaoService baoxiaoService;
	@Autowired
	private DictionariesService dictionariesService;
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * 获取列表
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Msg list(Baoxiao baoxiao, int page,HttpServletRequest request) {
		List<Baoxiao> list = baoxiaoService.list(baoxiao,page);
		return Msg.success().add("result", list);
	}
	
	
	
	/**
	 * 增加对象
	 * @param baoxiao
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("add")
	@ResponseBody
	public Msg addBaoxiao(@RequestBody Baoxiao baoxiao, HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		String id = GetRandem.getDateR();
		String time = MyTools.getTime();
		String[] split = time.split("-");
		baoxiao.setYl5(split[1]);
		baoxiao.setBaoxiaoId(id);
		baoxiao.setYl1("待审批");
		baoxiao.setYl3(username);
		
		try {
			double m = Double.parseDouble(baoxiao.getTotal());
			DecimalFormat df = new DecimalFormat("0.00"); 
			String money = df.format(m);
			baoxiao.setTotal(money);
		} catch (Exception e) {
			e.printStackTrace();
			return Msg.fail().add("result", "所填不是价格不是数字");
		}
	
		List<Photo> photos = baoxiao.getPhotos();
		String basePath = dictionariesService.selectPATH();
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
		
		baoxiao.setYl4(str.toString());
		
		String bianma = "baoxiaosp";
		String companyId = baoxiao.getYl10();
		String contant = "有报销申请需要审批";
		List<String> quanxian = getQuanxian(bianma, companyId);
		for (String string : quanxian) {
			sendYuanGong(string,contant);
		}
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(baoxiao).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		if(!baoxiaoService.add(baoxiao)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "fail");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "success");
	}

	/**
	 * 更新对象
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Msg updateBaoxiao(Baoxiao baoxiao,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(baoxiao).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String quanxian = "baoxiaosp";
		Dictionaries d = dictionariesService.selectByBianma(quanxian);
		String username = getCurrentSysUser(request).getUsername();
		String companyId = baoxiao.getYl10();
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
			
			if(baoxiao.getBaoxiaoId() == null || baoxiao.getBaoxiaoId().equals("") ){
				return Msg.success().add("result", "evectionId不能为空");
			}else{
				Baoxiao eve = baoxiaoService.get(baoxiao);
				if(baoxiao.getYl1().equals("已拒绝")){
					baoxiao.setYl16(username);
					
					String cUsername = baoxiaoService.get(baoxiao).getYl3();
					String contant = "报销申请已拒绝";
					sendYuanGong(cUsername, contant);
					
					if(!baoxiaoService.update(baoxiao)){
						status = "failure";
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
						return Msg.fail().add("result", "更新失败");
					}
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.success().add("result", "更新成功");
				}else{
					
					String details = "";
					String oldBz = eve.getYl6();
					String newBz = baoxiao.getYl6();
					if(newBz != null && !"".equals(newBz)){
						if (oldBz != null && !"".equals(oldBz)) {
							details = oldBz+ "," + baoxiao.getYl15() + "--" + newBz ;
						}else{
							details = baoxiao.getYl15() + "--" + newBz;
						}
					}else{
						details = oldBz;
					}
					baoxiao.setYl6(details);
					
					if(eve != null){
						String user = eve.getYl16();
						if(user != null && !"".equals(user)){
							user = eve.getYl16()+","+username;
						}else{
							user = username;
						}
						baoxiao.setYl16(user);
					}
					String yl16 = baoxiao.getYl16();
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
						baoxiao.setYl15(yl15);
						baoxiao.setYl16(userName);
						
						System.out.println(MySelfUtil.bijiao(urList,list));
						if(MySelfUtil.bijiao(urList,list)){
							baoxiao.setYl1("已审批待财务确认");
							if(!baoxiaoService.update(baoxiao)){
								status = "failure";
								MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
								return Msg.fail().add("result", "更新失败");
							}
							
							String cUsername = baoxiaoService.get(baoxiao).getYl3();
							String contant = "报销申请已审批";
							sendYuanGong(cUsername, contant);
							
							String yl10 = baoxiao.getYl10();
							String contants = "报销申请需要确认";
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
							baoxiao.setYl1("待审批");
							if(!baoxiaoService.update(baoxiao)){
								status = "failure";
								MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
								return Msg.fail().add("result", "更新失败");
							}
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
							return Msg.success().add("result", "更新成功");
						}
					}else {
						baoxiao.setYl1("待审批");
						if(!baoxiaoService.update(baoxiao)){
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
			if(!baoxiaoService.update(baoxiao)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新失败");
			}
			String cUsername = baoxiaoService.get(baoxiao).getYl3();
			String contant = "报销申请已审批";
			sendYuanGong(cUsername, contant);
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "更新成功");
		}
	}
	
	/**
	 * 删除对象
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("delBaoxiao")
	@ResponseBody
	public Msg delBaoxiao(Baoxiao baoxiao, HttpServletRequest request){
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(baoxiao).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		if(baoxiao.getBaoxiaoId() == null || baoxiao.getBaoxiaoId().equals("") ){
			return Msg.success().add("result", "baoxiaoId不能为空");
		}else{
			if(!baoxiaoService.delete(baoxiao)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "fail");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "success");

		}
	}
	
	/**
	 * 查询
	 */
	@RequestMapping("get")
	@ResponseBody
	public Msg get(Baoxiao baoxiao) {
		
		String URL = dictionariesService.selectURL();
		
		List<String> path = new ArrayList<String>();
		Baoxiao bx = baoxiaoService.get(baoxiao);
		String bxId = bx.getBaoxiaoId();
		String str = bx.getYl4();
		if(str != null && !"".equals(str)){
			String[] split = str.split("\\+");
			for (String photo : split) {
				photo = URL+ "/" + bxId + "/" + photo;
				path.add(photo);
			}
			bx.setBxphotos(path);
		}
		
		List<String> cwpath = new ArrayList<String>();
		String cwString = bx.getYl9();
		if(cwString != null && !"".equals(cwString)){
			String[] split = cwString.split("\\+");
			for (String photo : split) {
				photo = URL+ "/" + bxId + "/" + photo;
				cwpath.add(photo);
			}
			bx.setCwphotos(cwpath);
		}
		
		String total = bx.getTotal();
		Map<String, String> map = MySelfUtil.number2CNMontrayUnit(total);
		bx.setMoneyMap(map);
		
		return Msg.success().add("result", bx);
	}
	
	/**
	 * 获取个人的全部报销信息
	 * @param request
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping("select")
	@ResponseBody
	public Msg select(Baoxiao baoxiao ,HttpServletRequest request,int page) {
		String username = getCurrentSysUser(request).getUsername();
		String yl10 = baoxiao.getYl10();
		List<Baoxiao> list = getStatus(username, yl10);
		baoxiao.setYl3(username);
		return Msg.success().add("result", baoxiaoService.select(baoxiao,page)).add("size", list.size());
			
	}
	
	/**
	 * 报销的待处理
	 * @param baoxiao
	 * @param request
	 * @return
	 */
	@RequestMapping("pendBaoxiao")
	@ResponseBody
	public Msg pendBaoxiao(Baoxiao baoxiao, HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		List<Baoxiao> list = getStatus(username,baoxiao.getYl10());
		Collections.sort(list, new Comparator<Baoxiao>(){
            public int compare(Baoxiao o1, Baoxiao o2) {
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	int mark = 1;
          	   	try {
	          		Date date0 = sdf.parse(o1.getYl2());
	          		Date date1 = sdf.parse(o2.getYl2());
	          		if(date0.getTime() > date1.getTime()){
	          		    mark =  -1;
	          		}
	          		if(o1.getYl2().equals(o2.getYl2())){
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
	 * @param baoxiao
	 * @param req
	 * @return
	 */
	@RequestMapping("affirmBaoxiao")
	@ResponseBody
	public Msg affirmBaoxiao(HttpServletRequest request,@RequestBody Baoxiao baoxiao) {
		
		List<Photo> photos = baoxiao.getPhotos();
		String basePath = dictionariesService.selectPATH();
		String id = baoxiao.getBaoxiaoId();
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
		baoxiao.setYl9(str.toString());
		baoxiao.setYl1("财务确认待申请人确认");
		String username = baoxiaoService.get(baoxiao).getYl3();
		String contant = "报销财务确认";
		sendYuanGong(username, contant);
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(baoxiao).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		if(!baoxiaoService.update(baoxiao)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "成功");
	}
	
	/**
	 * 申请人确认
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("querenBaoxiao")
	@ResponseBody
	public Msg querenBaoxiao(Baoxiao baoxiao, HttpServletRequest request){
		
		if(baoxiao.getBaoxiaoId() == null || "".equals(baoxiao.getBaoxiaoId()) ){
			return Msg.success().add("result", "paymentrequestId不能为空");
		}else{
			baoxiao.setYl1("申请人已确认");
			
			String username = getCurrentSysUser(request).getUsername();
			String Xiangmuming = request.getContextPath();
			String requestcontent = request.getRequestURL().toString();
			String responsecontent = JSONArray.fromObject(baoxiao).toString();
			String type = request.getMethod();
			String url = dictionariesService.selectRiZhiURL();
			String status = "success";
			
			if(!baoxiaoService.update(baoxiao)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.success().add("result", "success");
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
	
	
	//根据用户名获取需要其处理状态获取到对应数据
	public List<Baoxiao> getStatus(String username,String yl10){
		int page = 0;
		List<Baoxiao> bxList = new ArrayList<Baoxiao>();
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
			Baoxiao bx = new Baoxiao();
			bx.setYl1(status);
			bx.setYl10(yl10);
			
			if(!quanxian.contains("4")){
				bx.setYl3(username);
				List<Baoxiao> list = baoxiaoService.select(bx, page);
				bxList.addAll(list);
			}else{
				if("财务确认待申请人确认".equals(status)){
					bx.setYl3(username);
					List<Baoxiao> list = baoxiaoService.select(bx, page);
					bxList.addAll(list);
				}else {
					List<Baoxiao> list = baoxiaoService.select(bx, page);
					bxList.addAll(list);
				}
			}
		}
		return bxList;
	}
	
	
}
