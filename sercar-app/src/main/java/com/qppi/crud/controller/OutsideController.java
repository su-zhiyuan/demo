package com.qppi.crud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Dictionaries;
import com.qppi.crud.bean.Outside;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.OutsideService;
import com.qppi.crud.service.UserInfoService;
import com.qppi.crud.utils.GetRandem;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("outside")
public class OutsideController extends BaseController{
	@Autowired
	private OutsideService outsideService;
	@Autowired
	private DictionariesService dictionariesService;
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 获取列表
	 * @param outside
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Msg listOutside(Outside outside,int page) {
		return Msg.success().add("result", outsideService.list(outside,page));
	}
	
	/**
	 * 查询个人外出记录
	 */
	@RequestMapping("select")
	@ResponseBody
	public Msg select(int page,HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		return Msg.success().add("result", outsideService.select(page,username));
	}
	
	/**
	 * 获取对象
	 * @param outside
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public Msg getOutside(Outside outside){
		if(outside.getOutsideId() == null || outside.getOutsideId().equals("") ){
			return Msg.success().add("result", "outsideId不能为空");
		}else{			
			return Msg.success().add("result", outsideService.get(outside));
		}
	}
	
	/**
	 * 增加对象
	 * @param outside
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Msg addOutside(Outside outside, HttpServletRequest request) {
		
		String endtime = outside.getEndtime();
		String starttime = outside.getStarttime();
		endtime = endtime.replace("T", " ");
		starttime = starttime.replace("T", " ");
		outside.setEndtime(endtime);
		outside.setStarttime(starttime);
		
		outside.setOutsideId(GetRandem.getDateR());
		String time = MyTools.getTime();
		String[] split = time.split("-");
		outside.setYl5(split[1]);
		outside.setYl4(time);
		outside.setYl1("待审批");
		
		String bianma = "waichusp";
		String companyId = outside.getYl10();
		String contant = "有外出申请需要审批";
		List<String> quanxian = getQuanxian(bianma, companyId);
		for (String string : quanxian) {
			sendYuanGong(string,contant);
		}
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(outside).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(outsideService.add(outside)){
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "success");
		}
		status = "failure";
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.fail().add("result", "fail");
	}

	/**
	 * 更新对象
	 * @param outside
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Msg updateOutside(Outside outside,HttpServletRequest request){
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(outside).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String quanxian = "waichusp";
		Dictionaries d = dictionariesService.selectByBianma(quanxian);
		String username = getCurrentSysUser(request).getUsername();
		String companyId = outside.getYl10();
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
			
			if(outside.getOutsideId() == null || outside.getOutsideId().equals("") ){
				return Msg.success().add("result", "evectionId不能为空");
			}else{
				Outside eve = outsideService.get(outside);
				if(outside.getYl1().equals("已拒绝")){
					outside.setYl16(username);
					
					String cUsername = outsideService.get(outside).getYl3();
					String contant = "外出申请已拒绝";
					sendYuanGong(cUsername, contant);
					
					if(!outsideService.update(outside)){
						status = "failure";
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
						return Msg.fail().add("result", "更新失败");
					}
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.success().add("result", "更新成功");
				}else{
					
					String details = "";
					String oldBz = eve.getYl2();
					String newBz = outside.getYl2();
					if(newBz != null && !"".equals(newBz)){
						if (oldBz != null && !"".equals(oldBz)) {
							details = oldBz+ "," + outside.getYl15() + "--" + newBz ;
						}else{
							details = outside.getYl15() + "--" + newBz;
						}
					}else{
						details = oldBz;
					}
					outside.setYl2(details);
					
					if(eve != null){				
						String user = eve.getYl16();
						if(user != null && !"".equals(user)){
							user = eve.getYl16()+","+username;
						}else{
							user = username;
						}
						outside.setYl16(user);
					}
					String yl16 = outside.getYl16();
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
						outside.setYl15(yl15);
						outside.setYl16(userName);
						
						System.out.println(MySelfUtil.bijiao(urList,list));
						if(MySelfUtil.bijiao(urList,list)){
							outside.setYl1("已审批");
							if(!outsideService.update(outside)){
								status = "failure";
								MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
								return Msg.fail().add("result", "更新失败");
							}
							
							String cUsername = outsideService.get(outside).getYl3();
							String contant = "外出申请已审批";
							sendYuanGong(cUsername, contant);
							
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
							return Msg.success().add("result", "更新成功");
						}else {
							outside.setYl1("待审批");
							if(!outsideService.update(outside)){
								status = "failure";
								MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
								return Msg.fail().add("result", "更新失败");
							}
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
							return Msg.success().add("result", "更新成功");
						}
					}else {
						outside.setYl1("待审批");
						if(!outsideService.update(outside)){
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
			if(!outsideService.update(outside)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新失败");
			}
			String cUsername = outsideService.get(outside).getYl3();
			String contant = "外出申请已审批";
			sendYuanGong(cUsername, contant);
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "更新成功");
		}
	}
	
	/**
	 * 删除对象
	 * @param outside
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Msg deleteOutside(Outside outside,HttpServletRequest request){
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(outside).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(outside.getOutsideId() == null || outside.getOutsideId().equals("") ){
			return Msg.success().add("result", "outsideId不能为空");
		}else{
			if(outsideService.delite(outside)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.success().add("result", "success");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "fail");
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
}
