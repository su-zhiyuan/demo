package com.qppi.crud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Deposit;
import com.qppi.crud.bean.Order;
import com.qppi.crud.service.DepositService;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.OrderService;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("deposit")
public class DepositController extends BaseController {
	@Autowired
	private OrderService orderService;		//订单
	@Autowired
	private DepositService depositService;		//订单
	@Autowired
	private DictionariesService dictionariesService;
	
	/**
	 * 新建订金和订单
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("addDJ")
	@ResponseBody
	public Msg addDJ(Deposit deposit, HttpServletRequest request) {
		Order order = new Order();
		String username = getCurrentSysUser(request).getUsername();
		order.setOrderId(MyTools.getDateR());
		order.setCreateBy(username);
		order.setCreateTime(MyTools.getTime());
		order.setInTime(MyTools.getTime());
		order.setStatus("接车中");
		order.setServiceConsultant(username);
		if(!orderService.add(order)){
			return Msg.fail2().add("result","新增订单失败");
		}
		deposit.setDepositId(MyTools.getDateR());
		deposit.setOrderId(order.getOrderId());
		deposit.setPaymentTime(MyTools.getTime());
		deposit.setCreationTime(MyTools.dayTime());
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(deposit).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		if(depositService.addDJ(deposit)){
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "success");
		}
		status = "failure";
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.fail().add("result", "fail");
	}
	
	/**
	 * 查询订金
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("selectDJ")
	@ResponseBody
	public Msg selectDJ(Deposit deposit, HttpServletRequest request,int page) {
		return Msg.success().add("result",depositService.selectDJ(page));
	}
	
	/**
	 * 根据ID查询订金
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("selectId")
	@ResponseBody
	public Msg selectId(Deposit deposit, HttpServletRequest request) {
		if(deposit.getDepositId() == null || deposit.getDepositId().equals("") ){
			return Msg.success().add("result","depositId不可为空");
		}else{
			return Msg.success().add("result",depositService.selectId(deposit));
		}
	}
	
	/**
	 * 根据ID修改订金
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("updateID")
	@ResponseBody
	public Msg updateID(Deposit deposit, HttpServletRequest request) {
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(deposit).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(deposit.getDepositId() == null || deposit.getDepositId().equals("") ){
			return Msg.success().add("result","depositId不可为空");
		}else{
			
			if(!depositService.updateID(deposit)) {
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "修改失败");
			}
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "修改成功");
		}
	}
}
