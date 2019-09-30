package com.qppi.crud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.GoodsRC;
import com.qppi.crud.bean.Purchase;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.GoodsrcService;
import com.qppi.crud.service.PurchaseService;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("purchase")
public class PurchaseController extends BaseController{

	@Autowired
	private PurchaseService purchaseService;
	@Autowired 
	private GoodsrcService goodsrcService;
	@Autowired
	private DictionariesService dictionariesService;
	
	//订单中添加采购
	@RequestMapping("addPurchase")
	@ResponseBody
	public Msg addPurchase(@RequestBody Purchase purchase,HttpServletRequest request){
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(purchase).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		String time = MyTools.getTime();
		String purchaseId = MyTools.getDateR();
		
		purchase.setPurchaseId(purchaseId);
		purchase.setCreateBy(username);
		purchase.setCreateTime(time);
		purchase.setStatus("待提交");
		
		List<GoodsRC> goods = purchase.getGoods();
		if(goods.size() == 0){
			return Msg.fail().add("result", "添加失败");
		}
		double allprice = 0;
		for (GoodsRC good : goods) {
			String goodsId = MyTools.getDateR();
			good.setGoodsrcId(goodsId);
			good.setCaigourcId(purchaseId);
			good.setCreacteTime(time);
			if(!goodsrcService.addGood(good)){
				return Msg.fail().add("result", "添加失败");
			}
			int count = Integer.parseInt(good.getNumber());
			double price = Double.parseDouble(good.getPrice());
			double sum = count * price;
			allprice = allprice + sum;
		}
		purchase.setYl1(allprice+"");
		if(!purchaseService.add(purchase)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "添加失败");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "添加成功");
	}
	
	//订单中提交采购单 
	@RequestMapping("subPurchase")
	@ResponseBody
	public Msg subPurchase(Purchase purchase,HttpServletRequest request){
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(purchase).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();

		String purchaseId = purchase.getPurchaseId();
		if(null != purchaseId && !"".equals(purchaseId)){
			purchase.setStatus("待复核");
			if(!purchaseService.update(purchase)){
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "提交失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "提交成功");
		}
		status = "failure";
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.fail().add("result", "提交失败");
	}
	
	//订单中审核采购单 
	@RequestMapping("checkPurchase")
	@ResponseBody
	public Msg checkPurchase(Purchase purchase,HttpServletRequest request){
		String username = getCurrentSysUser(request).getUsername();
		String time = MyTools.getTime();
		
		purchase.setCheckedBy(username);
		purchase.setCheckedTime(time);
		purchase.setStatus("已复核待确认");
		
		if(!purchaseService.update(purchase)){
			return Msg.fail().add("result", "提交失败");
		}
		return Msg.success().add("result", "提交成功");
	
	}
	
	//订单中拒绝采购单 
	@RequestMapping("nocheckPurchase")
	@ResponseBody
	public Msg nocheckPurchase(Purchase purchase, HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		String time = MyTools.getTime();

		purchase.setCheckedBy(username);
		purchase.setCheckedTime(time);
		purchase.setStatus("复核未通过");

		if (!purchaseService.update(purchase)) {
			return Msg.fail().add("result", "提交失败");
		}
		return Msg.success().add("result", "提交成功");
	}
	
	//订单中删除采购单
	@RequestMapping("delPurchase")
	@ResponseBody
	public Msg delPurchase(Purchase purchase) {
		String purchaseId = purchase.getPurchaseId();
		if(!purchaseService.delPurchase(purchaseId)){
			return Msg.fail().add("result", "删除失败");
		}
		Purchase pur = new Purchase();
		pur.setPurchaseId(purchaseId);
		if(!goodsrcService.delGoods(pur)){
			return Msg.fail().add("result", "删除失败");
		}
		return  Msg.success().add("result", "删除成功");
	}
	
	//采购单完成
	@RequestMapping("finashPurchase")
	@ResponseBody
	public Msg finashPurchase(Purchase purchase) {
		String time = MyTools.getTime();
		
		String purchaseId = purchase.getPurchaseId();
		Purchase pur = purchaseService.getPurchase(purchaseId);
		pur.setStatus("已完成");
		pur.setYl3(time);
		
		if (!purchaseService.update(pur)) {
			return Msg.fail().add("result", "失败");
		}
		
		return Msg.success().add("result", "已完成");
	}
	
}
