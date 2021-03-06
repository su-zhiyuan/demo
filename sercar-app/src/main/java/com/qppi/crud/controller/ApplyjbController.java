package com.qppi.crud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Applyjb;
import com.qppi.crud.bean.Dictionaries;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.service.ApplyjbService;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.UserInfoService;
import com.qppi.crud.utils.GetRandem;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("applyjb")
public class ApplyjbController extends BaseController {

	@Autowired
	private ApplyjbService applyjbService;
	@Autowired
	private DictionariesService dictionariesService;
	@Autowired
	private UserInfoService userInfoService;

	
	/**
	 * 获取列表
	 * @param applyjb
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Msg listApplyjb(Applyjb applyjb,int page) {
		return Msg.success().add("result", applyjbService.list(applyjb,page));
	}
	
	/**
	 * 获取个人加班数据
	 */
	@RequestMapping("select")
	@ResponseBody
	public Msg select(HttpServletRequest request, int page) {
		String username = getCurrentSysUser(request).getUsername();
		return Msg.success().add("result", applyjbService.select(username, page));
	}
	
	/**
	 * 获取对象
	 * @param applyjb
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public Msg getApplyjb(Applyjb applyjb){
		if(applyjb.getApplyjbId() == null || applyjb.getApplyjbId().equals("") ){
			return Msg.success().add("result", "applyjbId不能为空");
		}
		return Msg.success().add("result", applyjbService.get(applyjb));
	}
	
	/**
	 * 增加对象
	 * @param applyjb
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Msg addApplyjb(Applyjb applyjb, HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		String time = MyTools.getTime();
		String[] split = time.split("-");
		
		String endtime = applyjb.getEndtime();
		String starttime = applyjb.getStarttime();
		endtime = endtime.replace("T", " ");
		starttime = starttime.replace("T", " ");
		applyjb.setEndtime(endtime);
		applyjb.setStarttime(starttime);
		
		applyjb.setYl5(split[1]);
		applyjb.setCreateby(username);
		applyjb.setApplyjbId(GetRandem.getDateR());
		applyjb.setYl1("待审批");
		applyjb.setCreatetime(time);
		//发推送
		String bianma = "jiabansp";
		String companyId = applyjb.getYl10();
		String contant = "有加班申请需要审批";
		List<String> quanxian = getQuanxian(bianma, companyId);
		for (String string : quanxian) {
			sendYuanGong(string,contant);
		}
		
		//写日志
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(applyjb).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		if(!applyjbService.add(applyjb)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "fail");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "success");
	}

	/**
	 * 更新对象
	 * @param applyjb
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Msg updateApplyjb(Applyjb applyjb,HttpServletRequest request){
		//添加日志操作
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(applyjb).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String quanxian = "jiabansp";
		Dictionaries d = dictionariesService.selectByBianma(quanxian);
		String username = getCurrentSysUser(request).getUsername();
		String companyId = applyjb.getYl10();
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
			
			if(applyjb.getApplyjbId() == null || applyjb.getApplyjbId().equals("") ){
				return Msg.success().add("result", "evectionId不能为空");
			}else{
				Applyjb eve = applyjbService.get(applyjb);
				if(applyjb.getYl1().equals("已拒绝")){
					applyjb.setYl16(username);
					
					String createby = applyjbService.get(applyjb).getCreateby();
					String contant = "加班申请已拒绝";
					sendYuanGong(createby, contant);
					
					if(!applyjbService.update(applyjb)){
						status = "failure";
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
						return Msg.fail().add("result", "更新失败");
					}
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.success().add("result", "更新成功");
				}else{
					String details = "";
					String oldBz = eve.getYl2();
					String newBz = applyjb.getYl2();
					if(newBz != null && !"".equals(newBz)){
						if (oldBz != null && !"".equals(oldBz)) {
							details = oldBz+ "," + applyjb.getYl15() + "--" + newBz ;
						}else{
							details = applyjb.getYl15() + "--" + newBz;
						}
					}else{
						details = oldBz;
					}
					applyjb.setYl2(details);
					
					if(eve != null){
						String user = eve.getYl16();
						if(user != null && !"".equals(user)){
							user = eve.getYl16()+","+username;
						}else{
							user = username;
						}
						applyjb.setYl16(user);
					}
					
					String yl16 = applyjb.getYl16();
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
						applyjb.setYl15(yl15);
						applyjb.setYl16(userName);
						
						System.out.println(MySelfUtil.bijiao(urList,list));
						if(MySelfUtil.bijiao(urList,list)){
							applyjb.setYl1("已审批");
							if(!applyjbService.update(applyjb)){
								status = "failure";
								MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
								return Msg.fail().add("result", "更新失败");
							}
							
							String createby = applyjbService.get(applyjb).getCreateby();
							String contant = "加班申请已审批";
							sendYuanGong(createby, contant);
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
							return Msg.success().add("result", "更新成功");
						}else {
							applyjb.setYl1("待审批");
							if(!applyjbService.update(applyjb)){
								status = "failure";
								MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
								return Msg.fail().add("result", "更新失败");
							}
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
							return Msg.success().add("result", "更新成功");
						}
					}else {
						applyjb.setYl1("待审批");
						if(!applyjbService.update(applyjb)){
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
			if(!applyjbService.update(applyjb)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新失败");
			}
			
			String createby = applyjbService.get(applyjb).getCreateby();
			String contant = "加班申请已审批";
			sendYuanGong(createby, contant);
			
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "更新成功");
		}
	}
	
	/**
	 * 删除对象
	 * @param applyjb
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Msg deleteApplyjb(Applyjb applyjb,HttpServletRequest request){
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(applyjb).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		if(applyjb.getApplyjbId() == null || applyjb.getApplyjbId().equals("") ){
			return Msg.success().add("result", "applyjbId不能为空");
		}else{
			if(!applyjbService.delite(applyjb)){
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
}
