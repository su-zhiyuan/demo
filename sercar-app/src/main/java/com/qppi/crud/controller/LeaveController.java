package com.qppi.crud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Dictionaries;
import com.qppi.crud.bean.Leave;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.LeaveService;
import com.qppi.crud.service.UserInfoService;
import com.qppi.crud.utils.GetRandem;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("leave")
public class LeaveController extends BaseController{
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private DictionariesService dictionariesService;
	@Autowired
	private UserInfoService userInfoService;
//	@Autowired
//	private CacheManager cacheManager;

	
	/**
	 * 获取列表
	 * @param leave
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Msg listLeave(Leave leave,int page) {
		List<Leave> list = leaveService.list(leave,page);
		return Msg.success().add("result", list);
	}
	
	/**
	 * 查询个人请假
	 */
	@RequestMapping("select")
	@ResponseBody
	public Msg select(int page,HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		return Msg.success().add("result", leaveService.select(page,username));
	}
	
	/**
	 * 获取对象
	 * @param leave
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public Msg getLeave(Leave leave){
		if(leave.getLeaveId() == null || leave.getLeaveId().equals("") ){
			return Msg.success().add("result", "leaveId不能为空");
		}else{
			return Msg.success().add("result", leaveService.get(leave));
		}
	}
	
	/**
	 * 增加对象
	 * @param leave
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Msg addLeave(Leave leave, HttpServletRequest request) {
		
		String endtime = leave.getEndtime();
		String starttime = leave.getStarttime();
		endtime = endtime.replace("T", " ");
		starttime = starttime.replace("T", " ");
		leave.setEndtime(endtime);
		leave.setStarttime(starttime);
		
		leave.setLeaveId(GetRandem.getDateR());
		String time = MyTools.getTime();
		String[] split = time.split("-");
		leave.setYl5(split[1]);
		leave.setYl4(time);
		leave.setYl1("待审批");
		
		String bianma = "qingjiasp";
		String companyId = leave.getYl10();
		String contant = "有请假申请需要审批";
		List<String> quanxian = getQuanxian(bianma, companyId);
		for (String string : quanxian) {
			sendYuanGong(string,contant);
		}
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(leave).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(leaveService.add(leave)){
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "success");
		}
		status = "failure";
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.fail().add("result", "fail");
	}

	/**
	 * 更新对象
	 * @param leave
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Msg updateLeave(Leave leave,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(leave).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String quanxian = "qingjiasp";
		Dictionaries d = dictionariesService.selectByBianma(quanxian);
		String username = getCurrentSysUser(request).getUsername();
		String companyId = leave.getYl10();
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
			
			if(leave.getLeaveId() == null || leave.getLeaveId().equals("") ){
				return Msg.success().add("result", "evectionId不能为空");
			}else{
				Leave eve = leaveService.get(leave);
				if(leave.getYl1().equals("已拒绝")){
					leave.setYl16(username);
					
					String cUsername = leaveService.get(leave).getYl3();
					String contant = "请假申请已拒绝";
					sendYuanGong(cUsername, contant);
					
					if(!leaveService.update(leave)){
						status = "failure";
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
						return Msg.fail().add("result", "更新失败");
					}
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.success().add("result", "更新成功");
				}else{
					String details = "";
					String oldBz = eve.getYl2();
					String newBz = leave.getYl2();
					if(newBz != null && !"".equals(newBz)){
						if (oldBz != null && !"".equals(oldBz)) {
							details = oldBz+ "," + leave.getYl15() + "--" + newBz ;
						}else{
							details = leave.getYl15() + "--" + newBz;
						}
					}else{
						details = oldBz;
					}
					leave.setYl2(details);
					
					if(eve != null){
						String user = eve.getYl16();
						if(user != null && !"".equals(user)){
							user = eve.getYl16()+","+username;
						}else{
							user = username;
						}
						leave.setYl16(user);
					}
					String yl16 = leave.getYl16();
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
						leave.setYl15(yl15);
						leave.setYl16(userName);
						
						System.out.println(MySelfUtil.bijiao(urList,list));
						if(MySelfUtil.bijiao(urList,list)){
							leave.setYl1("已审批");
							if(!leaveService.update(leave)){
								status = "failure";
								MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
								return Msg.fail().add("result", "更新失败");
							}
							
							String cUsername = leaveService.get(leave).getYl3();
							String contant = "请假申请已审批";
							sendYuanGong(cUsername, contant);
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
							return Msg.success().add("result", "更新成功");
						}else {
							leave.setYl1("待审批");
							if(!leaveService.update(leave)){
								status = "failure";
								MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
								return Msg.fail().add("result", "更新失败");
							}
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
							return Msg.success().add("result", "更新成功");
						}
					}else {
						leave.setYl1("待审批");
						if(!leaveService.update(leave)){
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
			if(!leaveService.update(leave)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新失败");
			}
			
			String cUsername = leaveService.get(leave).getYl3();
			String contant = "请假申请已审批";
			sendYuanGong(cUsername, contant);
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "更新成功");
		}
	}
	
	/**
	 * 删除对象
	 * @param leave
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Msg deleteLeave(Leave leave, HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(leave).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(leave.getLeaveId() == null || leave.getLeaveId().equals("") ){
			return Msg.success().add("result", "leaveId不能为空");
		}else{
			if(leaveService.delite(leave)){
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.success().add("result", "success");
			}
			status = "failure";
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
