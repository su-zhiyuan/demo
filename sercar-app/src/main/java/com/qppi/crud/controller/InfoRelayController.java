package com.qppi.crud.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qppi.crud.bean.CaigouRC;
import com.qppi.crud.bean.Dictionaries;
import com.qppi.crud.bean.InfoRelay;
import com.qppi.crud.bean.InfoRelayWithBLOBs;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.InfoRelayService;
import com.qppi.crud.service.UserInfoService;
import com.qppi.crud.utils.FileSave;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("inforelay")
public class InfoRelayController extends BaseController{

	@Autowired
	private InfoRelayService infoRelayService;
	@Autowired
	private DictionariesService dictionariesService;
	@Autowired
	private UserInfoService userInfoService;
	
	//新增独立的信息反馈单
	@RequestMapping("addInfoRelay")
	@ResponseBody
	public Msg addInfoRelay(@RequestParam(value = "relayFile111", required = true) MultipartFile relayFile111,InfoRelayWithBLOBs infoRelay,HttpServletRequest request){
		String URL = dictionariesService.selectURL();
		String PATH = dictionariesService.selectPATH();
	    try {
	    	InputStream inputStream = relayFile111.getInputStream();
			String infoPath = "file/"+infoRelay.getOrderId()+"/";
			String mileageName = MyTools.getDateR()+".amr";
			
			if(!FileSave.saveFile(inputStream, PATH+infoPath, mileageName)){
				return Msg.fail().add("result", "保存文件失败");
			}
			infoRelay.setRelayFile(URL+infoPath+mileageName);
			infoRelay.setInforelayId(MyTools.getDateR());
			
			infoRelay.setCreateBy(getCurrentSysUser(request).getUsername());
			infoRelay.setCreateTime(MyTools.getTime());
			infoRelay.setStatus("待提交");
			infoRelay.setYl3("1");
			
			String Xiangmuming = request.getContextPath();
			String requestcontent = request.getRequestURL().toString();
			String responsecontent = JSONArray.fromObject(infoRelay).toString();
			String type = request.getMethod();
			String url = dictionariesService.selectRiZhiURL();
			String status = "success";
			String username = getCurrentSysUser(request).getUsername();
			
			if(!infoRelayService.add(infoRelay)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "新增信息反馈单失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", infoRelay);
		} catch (Exception e) {
			e.printStackTrace();
			return Msg.fail().add("result", "新增信息反馈单失败");
		}
		
	}
	
	//无录音文件上传反馈单
	@RequestMapping("addInfo")
	@ResponseBody
	public Msg addInfo(InfoRelayWithBLOBs infoRelay,HttpServletRequest request){
		infoRelay.setInforelayId(MyTools.getDateR());
		infoRelay.setCreateBy(getCurrentSysUser(request).getUsername());
		infoRelay.setCreateTime(MyTools.getTime());
		infoRelay.setStatus("待提交");
		infoRelay.setYl3("1");
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(infoRelay).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(!infoRelayService.add(infoRelay)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "新增信息反馈单失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", infoRelay);
	}

	//输入流转字节
	public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024*4];
        int n=0;
        while ( (n=in.read(buffer)) !=-1) {
            out.write(buffer,0,n);
        }
        return out.toByteArray();
    }
	
	//个人信息反馈单列表
	@RequestMapping("listInfoRelay")
	@ResponseBody
	public Msg ListInfoRelay(InfoRelay infoRelay,HttpServletRequest request, int page, String type){
		String username = getCurrentSysUser(request).getUsername();
		infoRelay.setYl3("1");
		infoRelay.setCreateBy(username);
		List<InfoRelay> list = getStatus(infoRelay, username);
		List<InfoRelay> info = infoRelayService.selectByInfo(infoRelay,page);
		return Msg.success().add("result", info).add("size", list.size());
	}
	
	//财务查看的待处理的报销
	@RequestMapping("pendInfoRelay")
	@ResponseBody
	public Msg pendInfoRelay(InfoRelay infoRelay,HttpServletRequest request){
		String username = getCurrentSysUser(request).getUsername();
		infoRelay.setYl3("1");
		infoRelay.setCreateBy(username);
		List<InfoRelay> list = getStatus(infoRelay, username);
		Collections.sort(list, new Comparator<InfoRelay>(){
            public int compare(InfoRelay o1, InfoRelay o2) {
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
		return Msg.success().add("result", list).add("size", list.size());
	}
	
	
	//详情
	@RequestMapping("getInfoRelay")
	@ResponseBody
	public Msg getInfoRelay(InfoRelayWithBLOBs infoRelay,HttpServletRequest request){
		InfoRelayWithBLOBs info = infoRelayService.getInfoRelay(infoRelay.getInforelayId());
		return Msg.success().add("result", info);
	}
	
	//提交信息反馈单
	@RequestMapping("subInfoRelay")
	@ResponseBody
	public Msg subInfoRelay(InfoRelayWithBLOBs infoRelay,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(infoRelay).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		try {
			infoRelay.setStatus("待复核");
			if(!infoRelayService.update(infoRelay)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "提交失败");
			}
			//发送消息到微信
			String bianma = "xinxifh";
			String companyId = infoRelay.getYl10();
			String contant = "有消息反馈单需要复核";
			List<String> quanxian = getQuanxian(bianma, companyId);
			if(quanxian == null || "".equals(quanxian)){
				return Msg.fail2().add("result", "没有复核人");
			}
			for (String string : quanxian) {
				sendYuanGong(string,contant);
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "提交成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Msg.fail2().add("result", "更新失败");
		}
	}
		
	//同意复核信息反馈单
	@RequestMapping("agreeInfoRelay")
	@ResponseBody
	public Msg agreeInfoRelay(InfoRelayWithBLOBs infoRelay,HttpServletRequest request){
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(infoRelay).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		try {
			String time = MyTools.getTime();
			infoRelay.setStatus("已复核");
			infoRelay.setCheckedBy(username);
			infoRelay.setCheckedTime(time);
			if(!infoRelayService.update(infoRelay)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "提交失败");
			}
			
			//发送消息到微信
			InfoRelayWithBLOBs relay = infoRelayService.getInfoRelay(infoRelay.getInforelayId());
			String createBy = relay.getCreateBy();
			String contant = "有消息反馈单已复核";
			sendYuanGong(createBy,contant);
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "提交成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Msg.fail2().add("result", "更新失败");
		}
	}
	
	//拒绝复核信息反馈单
	@RequestMapping("disagreeInfoRelay")
	@ResponseBody
	public Msg disagreeInfoRelay(InfoRelayWithBLOBs infoRelay,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(infoRelay).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		try {
			String time = MyTools.getTime();
			infoRelay.setStatus("复核未通过");
			infoRelay.setCheckedBy(username);
			infoRelay.setCheckedTime(time);
			if(!infoRelayService.update(infoRelay)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "提交失败");
			}
			//发送消息到微信
			InfoRelayWithBLOBs relay = infoRelayService.getInfoRelay(infoRelay.getInforelayId());
			String createBy = relay.getCreateBy();
			String contant = "有消息反馈单已拒绝";
			sendYuanGong(createBy,contant);
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "提交成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Msg.fail2().add("result", "更新失败");
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
	public List<InfoRelay> getStatus(InfoRelay infoRelay,String username){
		int page = 0;
		List<InfoRelay> infoList = new ArrayList<InfoRelay>();
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
			InfoRelay info = new InfoRelay();
			info.setStatus(status);
			info.setYl3("1");
			info.setCreateBy(infoRelay.getCreateBy());
			info.setYl10(infoRelay.getYl10());
			List<InfoRelay> list = infoRelayService.selectByInfo(info, page);
			infoList.addAll(list);
		}
		return infoList;
	}
}
