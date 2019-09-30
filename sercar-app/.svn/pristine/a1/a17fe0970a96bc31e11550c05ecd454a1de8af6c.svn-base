package com.qppi.crud.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Baoxiao;
import com.qppi.crud.bean.CaigouRC;
import com.qppi.crud.bean.Dictionaries;
import com.qppi.crud.bean.GoodsRC;
import com.qppi.crud.bean.Photo;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.service.BaoxiaoService;
import com.qppi.crud.service.CaiGourcService;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.GoodsrcService;
import com.qppi.crud.service.UserInfoService;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("caigou")
public class CaiGourcController extends BaseController{

	@Autowired
	private CaiGourcService caiGourcService;
	@Autowired
	private GoodsrcService goodsrcService;
	@Autowired
	private DictionariesService dictionariesService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private BaoxiaoService baoxiaoService;
	
	//新增采购单
	@RequestMapping("addCaiGou")
	@ResponseBody
	public Msg addCaiGou(@RequestBody CaigouRC caigouRC,HttpServletRequest request){
		String username = getCurrentSysUser(request).getUsername();
		String time = MyTools.getTime();
		String caigouId = MyTools.getDateR();
		String basePath = dictionariesService.selectPATH();

		caigouRC.setCaigourcId(caigouId);
		caigouRC.setCreateBy(username);
		caigouRC.setCreateTime(time);
		caigouRC.setStatus("预审批");
		
		List<GoodsRC> goodsRCs = caigouRC.getGoodsRCs();
		double allprice = 0;
		//添加商品信息入商品表
		if(goodsRCs.size() == 0){
			return Msg.fail().add("result", "添加失败");
		}
		for (GoodsRC good : goodsRCs) {
			String goodsId = MyTools.getDateR();
			good.setGoodsrcId(goodsId);
			good.setCaigourcId(caigouId);
			good.setCreacteTime(time);
			good.setYl6("待审批");
			
			try {
				double m = Double.parseDouble(good.getPrice());
				DecimalFormat df = new DecimalFormat("0.00"); 
				String money = df.format(m);
				good.setPrice(money);
			} catch (Exception e) {
				e.printStackTrace();
				return Msg.fail().add("result", "所填不是价格不是数字");
			}
			
			List<Photo> photos = good.getPhotos();
			StringBuilder str = new StringBuilder();
			for (Photo photo : photos) {
				String r = MyTools.getDateR();
				String url = basePath + "/" + caigouId + "/" + r;
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
			good.setYl9(str.toString());
			if(!goodsrcService.addGood(good)){
				return Msg.fail().add("result", "添加失败");
			}
			int count = Integer.parseInt(good.getNumber());
			String p = good.getPrice();
			if(p == null || "".equals(p)){
				p = "0";
			}
			double price = Double.parseDouble(p);
			double sum = count * price;
			allprice = allprice + sum;
		}
		caigouRC.setZongji(allprice+"");
		
		String bianma = "caigousp";
		String companyId = caigouRC.getYl10();
		String contant = "有采购申请需要审批";
		List<String> quanxian = getQuanxian(bianma, companyId);
		for (String string : quanxian) {
			sendYuanGong(string,contant);
		}
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(caigouRC).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		if(!caiGourcService.addCaiGou(caigouRC)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "添加失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "添加成功");
	}
	
	//列表采购单
	@RequestMapping("listCaiGou")
	@ResponseBody
	public Msg listCaiGou(CaigouRC caigouRC,HttpServletRequest request,int page){
		String username = getCurrentSysUser(request).getUsername();
		caigouRC.setCreateBy(username);
		List<CaigouRC> list = getStatus(username,caigouRC.getYl10());
		List<CaigouRC> caiList = caiGourcService.listCaiGou(caigouRC,page);
		return Msg.success().add("result", caiList).add("size", list.size());
	}
	
	//详情采购单
	@RequestMapping("getCaiGou")
	@ResponseBody
	public Msg getCaiGou(CaigouRC caigouRC){
		String URL = dictionariesService.selectURL();
		
		String caigourcId = caigouRC.getCaigourcId();
		CaigouRC cg = caiGourcService.getCaiGou(caigourcId);
		GoodsRC goodsRC = new GoodsRC();
		goodsRC.setCaigourcId(caigourcId);
		List<GoodsRC> goodsRCs = goodsrcService.selectGoodsRC(goodsRC);
		for (GoodsRC goods : goodsRCs) {
			List<String> path = new ArrayList<String>();
			String str = goods.getYl9();
			if(str != null && !"".equals(str)){
				String[] split = str.split("\\+");
				for (String photo : split) {
					photo = URL+ "/" + caigourcId + "/" + photo;
					path.add(photo);
				}
				goods.setCgphotos(path);
			}
		}
		cg.setGoodsRCs(goodsRCs);
		return Msg.success().add("result", cg);
	}
	
	//采购单审核列表
	@RequestMapping("listShenhe")
	@ResponseBody
	public Msg listShenhe(CaigouRC caigouRC,HttpServletRequest request,int page){
		List<CaigouRC> list = caiGourcService.listCaiGou(caigouRC,page);
		return Msg.success().add("result", list);
	}
	
	//采购单初审核
	@RequestMapping("checkCaiGou")
	@ResponseBody
	public Msg checkCaiGou(CaigouRC caigouRC, HttpServletRequest request){
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(caigouRC).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String quanxian = "caigousp";
		Dictionaries d = dictionariesService.selectByBianma(quanxian);
		String username = getCurrentSysUser(request).getUsername();
		String companyId = caigouRC.getYl10();
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
			
			if(caigouRC.getCaigourcId() == null || caigouRC.getCaigourcId().equals("") ){
				return Msg.success().add("result", "evectionId不能为空");
			}else{
				CaigouRC caiGou = caiGourcService.getCaiGou(caigouRC.getCaigourcId());
				
				String details = "";
				String oldBz = caiGou.getYl3();
				String newBz = caigouRC.getYl3();
				if(newBz != null && !"".equals(newBz)){
					if (oldBz != null && !"".equals(oldBz)) {
						details = oldBz+ "," + caigouRC.getYl12() + "--" + newBz ;
					}else{
						details = caigouRC.getYl12() + "--" + newBz;
					}
				}else{
					details = oldBz;
				}
				caigouRC.setYl3(details);
				
				
				String shenhe = caiGou.getShenhe();
				if(shenhe != null && !"".equals(shenhe)){
					shenhe = shenhe + "," + username;
				}else{
					shenhe = username;
				}
				caigouRC.setShenhe(shenhe);
			}
			
			String sh = caigouRC.getShenhe();
			
			if(sh.contains(",")){
				String[] split = sh.split(",");
				List<String> arList = new ArrayList<String>();
				for (String string : split) {
					arList.add(string);
				}
				List<String> list = MySelfUtil.removeDuplicate(arList);
				
				String yl12 = "";
				String shenhe = "";
				for (String string : list) {
					
					UserInfo user = userInfoService.selectUserId(string);
					String n = user.getName();
					if(yl12.equals("")){
						yl12 = n;
					}else{
						yl12 = yl12 + "," + n;
					}
					if(shenhe.equals("")){
						shenhe = string;
					}else{
						shenhe = shenhe + "," + string;
					}
					
				}
				caigouRC.setYl12(yl12);
				caigouRC.setShenhe(shenhe);
				
				System.out.println(MySelfUtil.bijiao(list, urList));
				if(MySelfUtil.bijiao(urList,list)){
					caigouRC.setStatus("待询价");
					if(!caiGourcService.editCaiGou(caigouRC)){
						status = "failure";
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
						return Msg.fail().add("result", "更新失败");
					}
					String caigourcId = caigouRC.getCaigourcId();
					GoodsRC goodsRC = new GoodsRC();
					goodsRC.setCaigourcId(caigourcId);
					List<GoodsRC> rc = goodsrcService.selectGoodsRC(goodsRC);
					for (GoodsRC g : rc) {
						g.setYl6("待询价");
						goodsrcService.updateCaiGou(g);
					}
					
					String cUsername = caiGourcService.getCaiGou(caigouRC.getCaigourcId()).getCreateBy();
					String contant = "采购单已经待询价";
					sendYuanGong(cUsername, contant);
					
					UserInfo uInfo = new UserInfo();
					uInfo.setYl10(companyId);
					List<UserInfo> uList = userInfoService.selectUser(uInfo);
					for (UserInfo u : uList) {
						String roletype = u.getRoletype();
						String qx = dictionariesService.selectQuanXian(roletype);
						qx = qx.replace("，", ",");
						if(qx.contains(",")){
							String[] split2 = qx.split(",");
							for(int i=0; i<split2.length; i++){
								if(split2[i].equals("10")){
									sendYuanGong(u.getUsername(),contant);
								}
							}
						}else{
							if("10".equals(qx)){
								sendYuanGong(u.getUsername(),contant);
							}
						}
					}
					
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.success().add("result", "更新成功");
				}else {
					caigouRC.setStatus("预审批");
					if(!caiGourcService.editCaiGou(caigouRC)){
						status = "failure";
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
						return Msg.fail().add("result", "更新失败");
					}
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.success().add("result", "更新成功");
				}
				
			}else {
				caigouRC.setStatus("预审批");
				caigouRC.setShenhe(sh);
				if(!caiGourcService.editCaiGou(caigouRC)){
					status = "failure";
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.fail().add("result", "更新失败");
				}
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.success().add("result", "更新成功");
			}
			
		}else{
			String time = MyTools.getTime();
			caigouRC.setStatus("待询价");
			caigouRC.setShenhe(username);
			caigouRC.setShTime(time);
			
			String cUsername = caiGourcService.getCaiGou(caigouRC.getCaigourcId()).getCreateBy();
			String contant = "采购单已经待询价";
			sendYuanGong(cUsername, contant);
			
			UserInfo uInfo = new UserInfo();
			uInfo.setYl10(companyId);
			List<UserInfo> uList = userInfoService.selectUser(uInfo);
			for (UserInfo u : uList) {
				String roletype = u.getRoletype();
				String qx = dictionariesService.selectQuanXian(roletype);
				qx.replace("，", ",");
				if(qx.contains(",")){
					String[] split2 = qx.split(",");
					for(int i=0; i<split2.length; i++){
						if(split2[i].equals("10")){
							sendYuanGong(u.getUsername(),contant);
						}
					}
				}else{
					if("10".equals(qx)){
						sendYuanGong(u.getUsername(),contant);
					}
				}
			}
			
			if(!caiGourcService.editCaiGou(caigouRC)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "更新失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "更新成功");
		}
	}
	
	//采购单询价审核
	@RequestMapping("subCaiGou")
	@ResponseBody
	public Msg subCaiGou(GoodsRC goodsRC, HttpServletRequest request ){
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(goodsRC).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		if(goodsRC.getCaigourcId() == null || goodsRC.getCaigourcId().equals("") ){
			return Msg.success().add("result", "evectionId不能为空");
		}else{
			goodsRC.setYl4(username);
			goodsRC.setYl6("已询价");
			boolean b = goodsrcService.updateCaiGou(goodsRC);
			
			if(b == true){
				List<GoodsRC> goodList = goodsrcService.selectGoodsRC(goodsRC);
				for (GoodsRC good : goodList) {
					if(!"已询价".equals(good.getYl6())){
						return Msg.success().add("result", "已询价部分商品");
					}
				}
				CaigouRC caiGou = caiGourcService.getCaiGou(goodsRC.getCaigourcId());
				caiGou.setStatus("待复审");
				double allprice = 0;
				for (GoodsRC good : goodList) {
					int count = Integer.parseInt(good.getNumber());
					double price = Double.parseDouble(good.getYl3());
					double sum = count * price;
					allprice = allprice + sum;
				}
				caiGou.setYl7(allprice+"");
				if(!caiGourcService.editCaiGou(caiGou)){
					status = "failure";
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.fail().add("result", "提交失败");
				}
				
				String bianma = "caigousp";
				String companyId = goodsRC.getYl10();
				String contant = "有采购申请需待复审";
				List<String> quanxian = getQuanxian(bianma, companyId);
				for (String string : quanxian) {
					sendYuanGong(string,contant);
				}
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.success().add("result", "提交成功");
			}
			
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "提交失败");
		}
	}
	
	//采购单复审
	@RequestMapping("twocheckCaiGou")
	@ResponseBody
	public Msg twocheckCaiGou(CaigouRC caigouRC, HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(caigouRC).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String quanxian = "caigousp";
		Dictionaries d = dictionariesService.selectByBianma(quanxian);
		String username = getCurrentSysUser(request).getUsername();
		String companyId = caigouRC.getYl10();
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
			
			if(caigouRC.getCaigourcId() == null || caigouRC.getCaigourcId().equals("") ){
				return Msg.success().add("result", "evectionId不能为空");
			}else{
				CaigouRC caiGou = caiGourcService.getCaiGou(caigouRC.getCaigourcId());
				
				String details = "";
				String oldBz = caiGou.getYl6();
				String newBz = caigouRC.getYl6();
				if(newBz != null && !"".equals(newBz)){
					if (oldBz != null && !"".equals(oldBz)) {
						details = oldBz+ "," + caigouRC.getYl13() + "--" + newBz ;
					}else{
						details = caigouRC.getYl13() + "--" + newBz;
					}
				}else{
					details = oldBz;
				}
				caigouRC.setYl6(details);
				
				String shenhe = caiGou.getYl5();
				if(shenhe != null && !"".equals(shenhe)){
					shenhe = shenhe + "," + username;
				}else{
					shenhe = username;
				}
				caigouRC.setYl5(shenhe);
			}
			
			String sh = caigouRC.getYl5();
			
			if(sh.contains(",")){
				String[] split = sh.split(",");
				List<String> arList = new ArrayList<String>();
				for (String string : split) {
					arList.add(string);
				}
				List<String> list = MySelfUtil.removeDuplicate(arList);
				
				String yl13 = "";
				String yl5 = "";
				for (String string : list) {
					
					UserInfo user = userInfoService.selectUserId(string);
					String n = user.getName();
					if(yl13.equals("")){
						yl13 = n;
					}else{
						yl13 = yl13 + "," + n;
					}
					if(yl5.equals("")){
						yl5 = string;
					}else{
						yl5 = yl5 + "," + string;
					}
					
				}
				caigouRC.setYl13(yl13);
				caigouRC.setYl5(yl5);
				
				System.out.println(MySelfUtil.bijiao(list, urList));
				if(MySelfUtil.bijiao(urList,list)){
					caigouRC.setStatus("已复审待完成");
					if(!caiGourcService.editCaiGou(caigouRC)){
						status = "failure";
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
						return Msg.fail().add("result", "更新失败");
					}
					
					String contant = "有采购单已复审待完成";
					UserInfo uInfo = new UserInfo();
					uInfo.setYl10(companyId);
					List<UserInfo> uList = userInfoService.selectUser(uInfo);
					for (UserInfo u : uList) {
						String roletype = u.getRoletype();
						String qx = dictionariesService.selectQuanXian(roletype);
						qx.replace("，", ",");
						if(qx.contains(",")){
							String[] split2 = qx.split(",");
							for(int i=0; i<split2.length; i++){
								if(split2[i].equals("10")){
									sendYuanGong(u.getUsername(),contant);
								}
							}
						}else{
							if("10".equals(qx)){
								sendYuanGong(u.getUsername(),contant);
							}
						}
					}
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.success().add("result", "更新成功");
				}else {
					caigouRC.setStatus("待复审");
					if(!caiGourcService.editCaiGou(caigouRC)){
						status = "failure";
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
						return Msg.fail().add("result", "更新失败");
					}
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.success().add("result", "更新成功");
				}
				
			}else {
				caigouRC.setStatus("待复审");
				caigouRC.setShenhe(sh);
				if(!caiGourcService.editCaiGou(caigouRC)){
					status = "failure";
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.fail().add("result", "审核失败");
				}
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.success().add("result", "审核成功");
			}
			
		}else{
			String time = MyTools.getTime();
			caigouRC.setStatus("已复审待完成");
			caigouRC.setShenhe(username);
			caigouRC.setShTime(time);
			
			String contant = "有采购单已复审待完成";
			UserInfo uInfo = new UserInfo();
			uInfo.setYl10(companyId);
			List<UserInfo> uList = userInfoService.selectUser(uInfo);
			for (UserInfo u : uList) {
				String roletype = u.getRoletype();
				String qx = dictionariesService.selectQuanXian(roletype);
				qx.replace("，", ",");
				if(qx.contains(",")){
					String[] split2 = qx.split(",");
					for(int i=0; i<split2.length; i++){
						if(split2[i].equals("10")){
							sendYuanGong(u.getUsername(),contant);
						}
					}
				}else{
					if("10".equals(qx)){
						sendYuanGong(u.getUsername(),contant);
					}
				}
			}
			
			if(!caiGourcService.editCaiGou(caigouRC)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "审核失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "审核成功");
		}
	}
	
	//采购单拒绝申请
	@RequestMapping("nocheckCaiGou")
	@ResponseBody
	public Msg nocheckCaiGou(CaigouRC caigouRC, HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(caigouRC).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String username = getCurrentSysUser(request).getUsername();
		String time = MyTools.getTime();
	
		caigouRC.setStatus("已拒绝");
		caigouRC.setShenhe(username);
		caigouRC.setShTime(time);
		
		CaigouRC caiGou = caiGourcService.getCaiGou(caigouRC.getCaigourcId());
		String createBy = caiGou.getCreateBy();
		String contant = "采购单审批被拒绝";
		sendYuanGong(createBy, contant);
		
		if(!caiGourcService.editCaiGou(caigouRC)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "审核失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "审核成功");
	}
	
	//采购单完成
	@RequestMapping("finashCaiGou")
	@ResponseBody
	public Msg finashCaiGou(CaigouRC caigouRC, HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(caigouRC).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		String time = MyTools.getTime();
		
		String caigourcId = caigouRC.getCaigourcId();
		CaigouRC cg = caiGourcService.getCaiGou(caigourcId);
		cg.setStatus("待入库");
		cg.setFinishTime(time);
		if(!caiGourcService.editCaiGou(cg)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "完成失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "已完成");
	}
	
	/**
	 * 删除对象
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("delCaigou")
	@ResponseBody
	public Msg delCaigou(CaigouRC caigouRC, HttpServletRequest request){
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(caigouRC).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		if(caigouRC.getCaigourcId() == null || "".equals(caigouRC.getCaigourcId()) ){
			return Msg.success().add("result", "baoxiaoId不能为空");
		}else{
			if(!caiGourcService.delete(caigouRC)){
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
	
	//采购人待处理
	@RequestMapping("pendCaiGou")
	@ResponseBody
	public Msg pendCaiGou(CaigouRC caigouRC, HttpServletRequest request){
		String username = getCurrentSysUser(request).getUsername();
		List<CaigouRC> list = getStatus(username,caigouRC.getYl10());
		Collections.sort(list, new Comparator<CaigouRC>(){
            public int compare(CaigouRC o1, CaigouRC o2) {
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
	
	//仓库确定入库
	@RequestMapping("confirmRuku")
	@ResponseBody
	public Msg confirmRuku(CaigouRC caigouRC){
		String caigourcId = caigouRC.getCaigourcId();
		CaigouRC caiGou = caiGourcService.getCaiGou(caigourcId);
		
		Calendar cale = null;  
        cale = Calendar.getInstance();  
        int month = cale.get(Calendar.MONTH) + 1;  
		Baoxiao baoxiao = new Baoxiao();
		baoxiao.setType("采购经费");
		baoxiao.setTotal(caiGou.getYl7());
		String str = caiGou.getCreateTime()+"---"+ caiGou.getYl11() +"创建的采购单号为："+ caigourcId +"的报销单";
		baoxiao.setExplains(str);
		baoxiao.setYl1("待审批");
		baoxiao.setYl2(MyTools.getTime());
		baoxiao.setYl3(caiGou.getCreateBy());
		baoxiao.setYl5(month+"");
		baoxiao.setBaoxiaoId(MyTools.getDateR());
		baoxiao.setYl10(caiGou.getYl10());
		baoxiao.setYl11(caiGou.getYl11());
		
		if(!baoxiaoService.add(baoxiao)){
			return Msg.fail().add("result", "失败");
		}
		
		caiGou.setStatus("已入库");
		if(!caiGourcService.editCaiGou(caiGou)){
			return Msg.fail().add("result", "失败");
		}
		return Msg.success().add("result", "成功");
	}
	
	
	//根据用户名获取需要其处理状态获取到对应数据
	public List<CaigouRC> getStatus(String username,String yl10){
		int page = 0;
		List<CaigouRC> crList = new ArrayList<CaigouRC>();
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
			CaigouRC cr = new CaigouRC();
			cr.setStatus(status);
			cr.setYl10(yl10);
			if(!quanxian.contains("10")){
				cr.setCreateBy(username);
			}
			List<CaigouRC> list = caiGourcService.listCaiGou(cr, page);
			crList.addAll(list);
		}
		return crList;
	}
	
}
