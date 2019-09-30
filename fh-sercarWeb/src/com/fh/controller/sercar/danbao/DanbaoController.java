package com.fh.controller.sercar.danbao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.sercar.order.OrderManager;
import com.fh.service.sercar.settlement.SettlementManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.service.system.user.UserManager;
import com.fh.util.DateUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.util.Tools;

@Controller
@RequestMapping(value="/danbao")
public class DanbaoController extends BaseController{
	
	String menuUrl = "danbao/list.do"; //菜单地址(权限用)
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="orderService")
	private OrderManager orderService;
	@Resource(name="settlementService")
	private SettlementManager settlementService;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Order");
		ModelAndView mv = this.getModelAndView();
		
		String username = Jurisdiction.getUsername();
		PageData pd = new PageData();
		pd.put("USERNAME", username);
		PageData user = userinfoService.findByUsername(pd);
		String gsId = user.getString("YL1");
		
		PageData pd_order = new PageData();
		pd_order.put("YL15", username);
		pd_order.put("YL10", gsId);
		page.setPd(pd_order);
		List<PageData> orderList = orderService.findByCondition(page);
		List<PageData> list = new ArrayList<PageData>();
		
		Page page_sett = new Page();
		PageData pd_sett = new PageData();
		for (PageData pageData : orderList) {
			String orderId = pageData.getString("ORDER_ID");
			pd_sett.put("ORDER_ID", orderId);
			page_sett.setPd(pd_sett);
			List<PageData> settlist = settlementService.findByCondition(page_sett);
			if(settlist.size()>0){
				pageData.put("settle", settlist.get(0));
			}
			list.add(pageData);
				
		}
		
		mv.setViewName("sercar/danbao/danbao_list");
		mv.addObject("list",list);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	//同意担保
	@RequestMapping(value="/agreeDanBao")
	public ModelAndView agreeDanBao() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData order = orderService.findById(pd);
		
		order.put("STATUS", "同意放行---未付款");
		orderService.edit(order);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	// 不同意担保
	@RequestMapping(value="/disagreeDanBao")
	public ModelAndView disagreeDanBao() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData order = orderService.findById(pd);
		
		order.put("STATUS", "申请交车中");
		orderService.edit(order);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
}
