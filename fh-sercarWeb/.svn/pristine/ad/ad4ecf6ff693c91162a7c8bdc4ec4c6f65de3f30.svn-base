package com.fh.controller.sercar.dingjin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Dictionaries;
import com.fh.service.sercar.order.OrderManager;
import com.fh.service.sercar.partoffer.PartOfferManager;
import com.fh.service.sercar.pomx.PomxManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.user.UserManager;
import com.fh.util.AppUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;

import oracle.net.aso.l;
import oracle.net.aso.s;

@Controller
@RequestMapping(value="/dingjin")
public class DingjinController extends BaseController{

	String menuUrl = "dingjin/list.do"; //菜单地址(权限用)
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="orderService")
	private OrderManager orderService;
	@Resource(name="pomxService")
	private PomxManager pomxService;
	@Resource(name="partofferService")
	private PartOfferManager partofferService;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		
		String username = Jurisdiction.getUsername();
		PageData pd = new PageData();
		pd.put("USERNAME", username);
		PageData user = userinfoService.findByUsername(pd);
		String gsId = user.getString("YL1");
		
		Page page_part = new Page();
		PageData pd_part = new PageData();
		pd_part.put("STATUS", "客户已确认");
		pd_part.put("YL10", gsId);
		page_part.setPd(pd_part);
		List<PageData> partofferList = partofferService.findByCondition(page_part);
		List<PageData> list = new ArrayList<PageData>();
		if(partofferList.size() > 0){
			for (PageData pageData : partofferList) {
				String str = pageData.getString("YL8");
				if(!"0".equals(str) && !"".equals(str) && str != null){
					list.add(pageData);
				}
			}
		}
		mv.setViewName("sercar/dingjin/dingjin_list");
		mv.addObject("list",list);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	//去订金详情页面收取订金
	@RequestMapping(value="/goDingjin")
	public ModelAndView goDingjin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd =this.getPageData();
		PageData partoffer = partofferService.findById(pd);
		
		String partofferId = partoffer.getString("PARTOFFER_ID");
		List<PageData> list = new ArrayList<PageData>();
		Page page_po = new Page();
		PageData pd_po = new PageData();
		pd_po.put("PART_OFFER_ID", partofferId);
		page_po.setPd(pd_po);
		List<PageData> pomxList = pomxService.findByCondition(page_po);
		if(pomxList.size() > 0){
			for (PageData pomx : pomxList) {
				list.add(pomx);
			}
		}
		
		String orderId = partoffer.getString("ORDER_ID");
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		
		mv.setViewName("sercar/dingjin/dingjin_edit");
		mv.addObject("list",list);
		mv.addObject("order",order);
		mv.addObject("part",partoffer);
		return mv;
	}
	
	
	//收取定金getDingjin
	@RequestMapping(value="/getDingjin")
	@ResponseBody
	public Object getDingjin() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Reception");
		PageData pd = new PageData();		
		pd = this.getPageData();
		
		PageData partoffer = partofferService.findById(pd);
		
		String orderId = partoffer.getString("ORDER_ID");
		
		PageData pd_order = new PageData();
		pd_order.put("ORDER_ID", orderId);
		PageData order = orderService.findById(pd_order);
		String yl9 = order.getString("YL9");
		if(yl9 == null || "".equals(yl9)){
			yl9 = "0";
		}
		
		String yl8 = partoffer.getString("YL8");
		if(yl8 == null || "".equals(yl8)){
			yl8 = "0";
		}
		
		double count = Double.parseDouble(yl9)+Double.parseDouble(yl8);
		order.put("YL9", count);
		orderService.edit(order);
		
		partoffer.put("STATUS", "已交付定金");
		partoffer.put("YL15", "已交付定金");
		partofferService.edit(partoffer);
		
		return "success";
	}
}
