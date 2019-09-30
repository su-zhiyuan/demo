package com.qppi.crud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Order;
import com.qppi.crud.bean.Rescue;
import com.qppi.crud.service.OrderService;
import com.qppi.crud.service.RescueService;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MyTools;

@Controller
@RequestMapping("rescue")
public class RescueController extends BaseController {
	@Autowired
	private OrderService orderService;		//订单
	
	@Autowired
	private RescueService rescueService;	//救援
	
	/**
	 * 新建救援和订单
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("insertRescue")
	@ResponseBody
	public Msg insertRescue(Rescue rescue, HttpServletRequest request) {
		String vehicle = request.getParameter("vehicle");
		Order order = new Order();
		String username = getCurrentSysUser(request).getUsername();
		order.setOrderId(MyTools.getDateR());
		order.setCreateBy(username);
		order.setCreateTime(MyTools.getTime());
		order.setInTime(MyTools.getTime());
		order.setStatus("接车中");
		order.setServiceConsultant(username);
		order.setYl2(vehicle);
		if(!orderService.add(order)){
			return Msg.fail2().add("result","新增订单失败");
		}
		rescue.setRescueId(MyTools.getDateR());
		rescue.setOrderId(order.getOrderId());
		rescue.setCreationTime(MyTools.getTime());
		if(rescueService.insertRescue(rescue)){
			//根据ID去查询返回值
			return Msg.success().add("result",rescueService.selectRescueId(rescue));
		}
		return Msg.fail().add("result", "fail");
	}
	
	/**
	 * 查询救援
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("selectRescue")
	@ResponseBody
	public Msg selectRescue(Rescue rescue, HttpServletRequest request,int page) {
		List<Rescue> selectRescue = rescueService.selectRescue(page);
		return Msg.success().add("result",selectRescue);
	}
	
	/**
	 * 根据ID查询救援
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("selectRescueId")
	@ResponseBody
	public Msg selectRescueId(Rescue rescue, HttpServletRequest request) {
		if(rescue.getOrderId() == null || rescue.getOrderId().equals("")){
			return Msg.success().add("result","orderId不能为空");
		}else{			
			return Msg.success().add("result",rescueService.selectRescueId(rescue));
		}
	}
	
	/**
	 * 根据ID修改救援
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("updateRescueId")
	@ResponseBody
	public Msg updateRescueId(Rescue rescue, HttpServletRequest request) {
		if(rescue.getRescueId() == null || rescue.getRescueId().equals("")){
			return Msg.success().add("result","rescueId不能为空");
		}else{			
			if(!rescueService.updateRescueId(rescue)) {
				return Msg.fail().add("result", "修改失败");
			}
			return Msg.success().add("result", "修改成功");
		}
	}
}
