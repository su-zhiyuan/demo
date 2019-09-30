package com.qppi.crud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Order;
import com.qppi.crud.bean.ReservaTions;
import com.qppi.crud.service.OrderService;
import com.qppi.crud.service.ReservaTionsService;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MyTools;

@Controller
@RequestMapping("reservations")
public class ReservaTionsController extends BaseController {
	@Autowired
	private OrderService orderService;		//订单
	
	@Autowired
	private ReservaTionsService reservaTionsService;	//预约
	
	/**
	 * 新建预约和订单
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("addYY")
	@ResponseBody
	public Msg addYY(ReservaTions reservaTions, HttpServletRequest request) {
		reservaTions.setReservationsId(MyTools.getDateR());
		reservaTions.setCreationTime(MyTools.getTime());
		if(reservaTionsService.addYY(reservaTions)){
			return Msg.success().add("result", "success");
		}
		return Msg.fail().add("result", "fail");
	}
	
	/**
	 * 查询预约
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("selectYY")
	@ResponseBody
	public Msg selectYY(ReservaTions reservaTions, HttpServletRequest request,int page) {
		return Msg.success().add("result",reservaTionsService.selectYY(page,reservaTions.getYl10()));
	}
	
	/**
	 * 根据ID查询预约
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("selectId")
	@ResponseBody
	public Msg selectId(ReservaTions reservaTions, HttpServletRequest request) {
		if(reservaTions.getReservationsId() == null || reservaTions.getReservationsId().equals("")){
			return Msg.success().add("result","reservationsId不能为空");
		}else{			
			List<String> list = new ArrayList<String>();
			ReservaTions reserva = reservaTionsService.selectId(reservaTions);
			String yl2 = reserva.getYl2();
			list.add(yl2.substring(0, 1));
			list.add(yl2.substring(1, 2));
			list.add(yl2.substring(2));
			reserva.setChepai(list);
			return Msg.success().add("result",reserva);
		}
	}
	
	/**
	 * 根据ID修改预约
	 * @param baoxiao
	 * @return
	 */
	@RequestMapping("updateID")
	@ResponseBody
	public Msg updateID(ReservaTions reservaTions, HttpServletRequest request) {
		if(reservaTions.getReservationsId() == null || reservaTions.getReservationsId().equals("")){
			return Msg.success().add("result","reservationsId不能为空");
		}else{
			if(!reservaTionsService.updateID(reservaTions)) {
				return Msg.fail().add("result", "修改失败");
			}
			return Msg.success().add("result", "修改成功");
		}
	}
}
