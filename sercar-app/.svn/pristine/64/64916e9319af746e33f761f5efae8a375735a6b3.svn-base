package com.qppi.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.qppi.crud.bean.CompanyInfo;
import com.qppi.crud.service.CompanyInfoService;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.utils.BaiduUtil;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("company")
public class CompanyInfoController extends BaseController{
	
	@Autowired
	private CompanyInfoService companyInfoService;
	@Autowired
	private DictionariesService dictionariesService;
	
	/*
	 * 设置打卡中心点
	 */
	@RequestMapping("addAddress")
	@ResponseBody
	public Msg AddAddress(CompanyInfo companyInfo, HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(companyInfo).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
	    String lng = companyInfo.getYl6();
		String lat= companyInfo.getYl7();
		String address = BaiduUtil.getAddress(lng,lat);
		companyInfo.setYl8(address);
		if(companyInfoService.updateCompany(companyInfo)){
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", companyInfo);
		}else {
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "设置失败");
		}
	}
	
	//获得当前地地点
	@SuppressWarnings("rawtypes")
	@RequestMapping("getAddress")
	@ResponseBody
	public Msg add(String place){
		System.out.println(place);
		Map mapType = JSON.parseObject(place,Map.class);  
		String lng = mapType.get("longitude").toString();
		String lat = mapType.get("latitude").toString();
		String address = BaiduUtil.getAddress(lng,lat);
		if(address != null && !"".equals(address)){
			return Msg.success().add("result", address);
		}
		return Msg.fail().add("result", address);
//		for (Object obj : mapType.keySet()){  
//		     Object object = mapType.get(obj);
//		     String str = object.toString();
//		     list.add(str);
//		 }  
//		if(list.size()>0){
//			String lng = list.get(0);
//			String lat= list.get(1);
//			String address = BaiduUtil.getAddress(lng,lat);
//			return Msg.success().add("result", address);
//		}else {
//			return Msg.fail().add("result", "定位失败");
//		}
	}
}
