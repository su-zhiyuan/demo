package com.fh.controller.sercar.recheck;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.sercar.contacts.ContactsManager;
import com.fh.service.sercar.dispatching.DispatchingManager;
import com.fh.service.sercar.dsmx.DsmxManager;
import com.fh.service.sercar.inforelay.InfoRelayManager;
import com.fh.service.sercar.luyin.LuyinManager;
import com.fh.service.sercar.order.OrderManager;
import com.fh.service.sercar.partoffer.PartOfferManager;
import com.fh.service.sercar.pomx.PomxManager;
import com.fh.service.sercar.reception.ReceptionManager;
import com.fh.service.sercar.settlement.SettlementManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.util.DateUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.my.MyTools;

@Controller
@RequestMapping(value="/recheck")
public class RecheckController extends BaseController{
	
	//String menuUrl = "recheck/list.do"; //菜单地址(权限用)
	@Resource(name="orderService")
	private OrderManager orderService;
	@Resource(name="dispatchingService")
	private DispatchingManager dispatchingService;
	@Resource(name="partofferService")
	private PartOfferManager partofferService;
	@Resource(name="settlementService")
	private SettlementManager settlementService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="receptionService")
	private ReceptionManager receptionService;
	@Resource(name="inforelayService")
	private InfoRelayManager inforelayService;
	@Resource(name="luyinService")
	private LuyinManager luyinService;
	@Resource(name="dsmxService")
	private DsmxManager dsmxService;
	@Resource(name="pomxService")
	private PomxManager pomxService;
	@Resource(name="contactsService")
	private ContactsManager contactsService;

	
	@RequestMapping(value="/reception")
	public ModelAndView reception(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Order");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		if(gsid != null && !"".equals(gsid)){
			//接车单
			List<PageData> list_reception = new ArrayList<PageData>();
			Page page_reception = new Page();
			PageData pd_reception = new PageData();
			pd_reception.put("YL10", gsid);
			page_reception.setPd(pd_reception);
			List<PageData> receptions = receptionService.findByCondition(page_reception);
			for (PageData pageData : receptions) {
				String status = pageData.getString("STATUS");
				if("待复核".equals(status)){
					String wg_photo = pageData.getString("YL2");
					String[] photos = wg_photo.split("\\*\\*\\*");
					List<String> photoList = new ArrayList<String>();
					for (String photo : photos) {
						photoList.add(photo);
					}
					pageData.put("photo", photoList);
					list_reception.add(pageData);
				}
			}
			mv.addObject("list_reception",list_reception);
		}
		mv.setViewName("sercar/recheck/reception_list");
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	//前往接车单复核页面
	@RequestMapping(value="/goReception")
	public ModelAndView goReception() throws Exception{
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = receptionService.findById(pd);
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		String name = findByUsername.getString("NAME");
		
		String status = pd.getString("STATUS");
		if ("已复核待客户确认".equals(status)) {
			mv.addObject("msg","success");
			mv.setViewName("save_result");
			return mv;
		}
		
		mv.setViewName("sercar/recheck/reception_check");
		mv.addObject("pd", pd);
		mv.addObject("name", name);
		mv.addObject("gsid", gsid);
		mv.addObject("username", username);
		
		return mv;
	}
	
	//同意复核
	@RequestMapping(value="/agreeReception")
	public ModelAndView agreeReception() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = pd.getString("username");
		String gongsiId = pd.getString("gongsiId");
		String content = pd.getString("content");
		String name = pd.getString("name");
		
		pd = receptionService.findById(pd);
		String createName = pd.getString("CREATE_BY");
	
		pd.put("CHECKED_BY", username);
		pd.put("CHECKED_TIME", DateUtil.getSdftime().format(new Date()));
		pd.put("STATUS", "已复核待客户确认");
		pd.put("YL3", content);
		pd.put("YL10", gongsiId);
		pd.put("YL11", name);
		
		receptionService.edit(pd);
		
		PageData order = orderService.findById(pd);
		String contactId = order.getString("CONTACT_ID");
		
		order.put("STATUS", "接车单已复核待客户确认");
		orderService.edit(order);
		
		PageData contact_pd = new PageData();
		contact_pd.put("CONTACTS_ID", contactId);
		PageData contact = contactsService.findById(contact_pd);
		String wxid = contact.getString("YL1");
		String conStr = "您有一个订单需要授权";
		
		send(wxid, conStr);
		sendYuanGong(createName, "接车单已复核待客户确认");

		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	//不同意复核
	@RequestMapping(value="/disagreeReception")
	public ModelAndView disagreeReception() throws Exception{
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = pd.getString("username");
		String gongsiId = pd.getString("gongsiId");
		String content = pd.getString("content");
		String name = pd.getString("name");
		
		pd = receptionService.findById(pd);
		String createName = pd.getString("CREATE_BY");
		
		pd.put("CHECKED_BY", username);
		pd.put("CHECKED_TIME", DateUtil.getSdftime().format(new Date()));
		pd.put("STATUS", "复核未通过");
		pd.put("YL3", content);
		pd.put("YL10", gongsiId);
		pd.put("YL11", name);
		
		receptionService.edit(pd);
		
		PageData order = orderService.findById(pd);
		order.put("STATUS", "已创建接车单待提交复核");
		orderService.edit(order);
		
		sendYuanGong(createName, "有接车单复核不通过");
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	//信息反馈单列表
	@RequestMapping(value="/inforelay")
	public ModelAndView inforelay(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Order");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		if(gsid != null && !"".equals(gsid)){
			List<PageData> list_inforelay = new ArrayList<PageData>();
			Page page_inforelay = new Page();
			PageData pd_inforelay = new PageData();
			pd_inforelay.put("YL10", gsid);
			page_inforelay.setPd(pd_inforelay);
			List<PageData> inforelays = inforelayService.findByCondition(page_inforelay);
			for (PageData pageData : inforelays) {
				String status = pageData.getString("STATUS");
				if("待复核".equals(status)){
					String inforelayId = pageData.getString("INFORELAY_ID");
					pd_inforelay.put("INFORELAYID", inforelayId);
					page_inforelay.setPd(pd_inforelay);
					List<PageData> luyinList = luyinService.findByCondition(page_inforelay);
					pageData.put("luyinList", luyinList);
					list_inforelay.add(pageData);
				}
			}
			mv.addObject("list_inforelay",list_inforelay);
		}
		mv.setViewName("sercar/recheck/inforelay_list");
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	//前往信息反馈单复核页面
	@RequestMapping(value="/goInforelay")
	public ModelAndView goInforelay() throws Exception{
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		String name = findByUsername.getString("NAME");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = inforelayService.findById(pd);
		
		String status = pd.getString("STATUS");
		if ("待检测".equals(status)) {
			mv.addObject("msg","success");
			mv.setViewName("save_result");
			return mv;
		}
		
		mv.setViewName("sercar/recheck/inforelay_check");
		mv.addObject("pd", pd);
		mv.addObject("name", name);
		mv.addObject("gsid", gsid);
		mv.addObject("username", username);
		return mv;
	}
	
	//同意复核
	@RequestMapping(value="/agreeInforelay")
	public ModelAndView agreeInforelay() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = pd.getString("username");
		String gongsiId = pd.getString("gongsiId");
		String content = pd.getString("content");
		String name = pd.getString("name");
		
		pd = inforelayService.findById(pd);
		String createName = pd.getString("CREATE_BY");
		
		pd.put("CHECKED_BY", username);
		pd.put("CHECKED_TIME", DateUtil.getSdftime().format(new Date()));
		pd.put("STATUS", "待检测");
		pd.put("YL2", content);
		pd.put("YL10", gongsiId);
		pd.put("YL11", name);
		
		inforelayService.edit(pd);
		
		PageData order = orderService.findById(pd);
		order.put("STATUS", "信息反馈单已复核待诊断");
		orderService.edit(order);

		sendYuanGong(createName, "信息反馈单已复核待诊断");

		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	//不同意
	@RequestMapping(value="/disagreeInforelay")
	public ModelAndView disagreeInforelay() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = pd.getString("username");
		String gongsiId = pd.getString("gongsiId");
		String content = pd.getString("content");
		String name = pd.getString("name");
		
		pd = inforelayService.findById(pd);
		String createName = pd.getString("CREATE_BY");
		
		pd.put("CHECKED_BY", username);
		pd.put("CHECKED_TIME", DateUtil.getSdftime().format(new Date()));
		pd.put("STATUS", "复核未通过");
		pd.put("YL2", content);
		pd.put("YL10", gongsiId);
		pd.put("YL11", name);
		
		inforelayService.edit(pd);
		
		PageData order = orderService.findById(pd);
		order.put("STATUS", "信息反馈单待提交复核");
		orderService.edit(order);
		
		sendYuanGong(createName, "信息反馈单复核未通过");

		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	//报价单列表
	@RequestMapping(value="/partoffer")
	public ModelAndView partoffer(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Order");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		if(gsid != null && !"".equals(gsid)){
			List<PageData> list_partoffer = new ArrayList<PageData>();
			Page page_partoffer = new Page();
			PageData pd_partoffer = new PageData();
			pd_partoffer.put("YL10", gsid);
			page_partoffer.setPd(pd_partoffer);
			List<PageData> partoffers = partofferService.findByCondition(page_partoffer);
			for (PageData pageData : partoffers) {
				String status = pageData.getString("STATUS");
				if("待复核".equals(status)){
					list_partoffer.add(pageData);
				}
			}
			mv.addObject("list_partoffer",list_partoffer);
		}
		mv.setViewName("sercar/recheck/partoffer_list");
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	//去报价详情页面
	@RequestMapping(value="/goPartofferDetails")
	public ModelAndView goPartofferDetails(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String partofferId = pd.getString("PARTOFFER_ID");
		pd.put("PART_OFFER_ID", partofferId);
		page.setPd(pd);
		List<PageData> pomxs = pomxService.findByCondition(page);
		
		pd.put("YL1", partofferId);
		page.setPd(pd);
		List<PageData> dsmxs = dsmxService.findByCondition(page);
		
		mv.addObject("pomxs",pomxs);	//按钮权限
		mv.addObject("dsmxs",dsmxs);	//按钮权限
		
		mv.setViewName("sercar/recheck/partoffer_details");
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	//去报价复核页面
	@RequestMapping(value="/goPartoffer")
	public ModelAndView goPartoffer(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		String name = findByUsername.getString("NAME");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = partofferService.findById(pd);
		
		String status = pd.getString("STATUS");
		if ("待确认".equals(status)) {
			mv.addObject("msg","success");
			mv.setViewName("save_result");
			return mv;
		}
		
		
		mv.setViewName("sercar/recheck/partoffer_check");
		mv.addObject("pd", pd);
		mv.addObject("name", name);
		mv.addObject("gsid", gsid);
		mv.addObject("username", username);
		return mv;
	}
	
	//同意复核
	@RequestMapping(value="/agreePartoffer")
	public ModelAndView agreePartoffer() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = pd.getString("username");
		String gongsiId = pd.getString("gongsiId");
		String content = pd.getString("content");
		String name = pd.getString("name");
		
		pd = partofferService.findById(pd);
		String createName = pd.getString("CREATE_BY");
		
		pd.put("CHECKED_BY", username);
		pd.put("CHECKED_TIME", DateUtil.getSdftime().format(new Date()));
		pd.put("STATUS", "待确认");
		pd.put("YL1", content);
		pd.put("YL10", gongsiId);
		pd.put("YL11", name);
		
		partofferService.edit(pd);
		
		PageData order = orderService.findById(pd);
		order.put("STATUS", "报价单已复核待客户确认");
		orderService.edit(order);

		sendYuanGong(createName, "报价单已复核待客户确认");
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
		
	//不同意
	@RequestMapping(value="/disagreePartoffer")
	public ModelAndView disagreePartoffer() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = pd.getString("username");
		String gongsiId = pd.getString("gongsiId");
		String content = pd.getString("content");
		String name = pd.getString("name");
		
		pd = partofferService.findById(pd);
		String createName = pd.getString("CREATE_BY");
		
		pd.put("CHECKED_BY", username);
		pd.put("CHECKED_TIME", DateUtil.getSdftime().format(new Date()));
		pd.put("STATUS", "复核未通过");
		pd.put("YL2", content);
		pd.put("YL10", gongsiId);
		pd.put("YL11", name);
		
		partofferService.edit(pd);
		
		PageData order = orderService.findById(pd);
		order.put("STATUS", "报价单待提交复核");
		orderService.edit(order);

		sendYuanGong(createName, "报价单复核未通过");
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	//派工单列表
	@RequestMapping(value="/dispatching")
	public ModelAndView dispatching(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Order");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		if(gsid != null && !"".equals(gsid)){
			List<PageData> list_dispatching = new ArrayList<PageData>();
			Page page_dispatching = new Page();
			PageData pd_dispatching = new PageData();
			pd_dispatching.put("YL10", gsid);
			page_dispatching.setPd(pd_dispatching);
			List<PageData> dispatchings = dispatchingService.findByCondition(page_dispatching);
			for (PageData pageData : dispatchings) {
				String status = pageData.getString("STATUS");
				if("待复核".equals(status)){
					list_dispatching.add(pageData);
				}
			}
			mv.addObject("list_dispatching",list_dispatching);
		}
		mv.setViewName("sercar/recheck/dispatching_list");
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	//去派工详情页面
	@RequestMapping(value="/getDispatchingDetails")
	public ModelAndView getDispatchingDetails(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String dispatchingId = pd.getString("DISPATCHING_ID");
		pd.put("DISPATCHING_ID", dispatchingId);
		page.setPd(pd);
		List<PageData> dsmxs = dsmxService.findByCondition(page);
		mv.addObject("dsmxs", dsmxs);
		
		mv.setViewName("sercar/recheck/dispatching_details");
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	//去派工复核页面
	@RequestMapping(value="/goDispatching")
	public ModelAndView goDispatching(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		String name = findByUsername.getString("NAME");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = dispatchingService.findById(pd);
		
		String status = pd.getString("STATUS");
		if ("待开工".equals(status)) {
			mv.addObject("msg","success");
			mv.setViewName("save_result");
			return mv;
		}
		
		mv.setViewName("sercar/recheck/dispatching_check");
		mv.addObject("pd", pd);
		mv.addObject("name", name);
		mv.addObject("gsid", gsid);
		mv.addObject("username", username);
		return mv;
	}
	
	//同意复核
	@RequestMapping(value="/agreeDispatching")
	public ModelAndView agreeDispatching() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = pd.getString("username");
		String gongsiId = pd.getString("gongsiId");
		String content = pd.getString("content");
		String name = pd.getString("name");
		
		pd = dispatchingService.findById(pd);
		String createName = pd.getString("CREATE_BY");
		String jishiName = pd.getString("MAINTENANCE_TECHNICIAN");
		
		pd.put("CHECKED_BY", username);
		pd.put("CEHCKED_TIME", MyTools.getTime());
		pd.put("STATUS", "待开工");
		pd.put("YL1", content);
		pd.put("YL10", gongsiId);
		pd.put("YL11", name);
		
		dispatchingService.edit(pd);
		
		PageData order = orderService.findById(pd);
		order.put("STATUS", "派工单已复核待技师开工");
		orderService.edit(order);
		
		sendYuanGong(createName, "派工单已复核待技师开工");
		sendYuanGong(jishiName, "派工单已复核待技师开工");

		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
		
	//不同意
	@RequestMapping(value="/disagreeDispatching")
	public ModelAndView disagreeDispatching() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = pd.getString("username");
		String gongsiId = pd.getString("gongsiId");
		String content = pd.getString("content");
		String name = pd.getString("name");
		
		pd = dispatchingService.findById(pd);
		String createName = pd.getString("CREATE_BY");
		
		pd.put("CHECKED_BY", username);
		pd.put("CEHCKED_TIME", MyTools.getTime());
		pd.put("STATUS", "复核未通过");
		pd.put("YL2", content);
		pd.put("YL10", gongsiId);
		pd.put("YL11", name);
		
		dispatchingService.edit(pd);
		
		PageData order = orderService.findById(pd);
		order.put("STATUS", "派工单待提交复核");
		orderService.edit(order);
		
		sendYuanGong(createName, "派工单复核未通过");

		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	//报价单列表
	@RequestMapping(value="/settlement")
	public ModelAndView settlement(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Order");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		if(gsid != null && !"".equals(gsid)){
			List<PageData> list_settlement = new ArrayList<PageData>();
			Page page_settlement = new Page();
			PageData pd_settlement = new PageData();
			pd_settlement.put("YL10", gsid);
			page_settlement.setPd(pd_settlement);
			List<PageData> settlements = settlementService.findByCondition(page_settlement);
			for (PageData pageData : settlements) {
				String status = pageData.getString("STATUS");
				if("待复核".equals(status)){
					list_settlement.add(pageData);
				}
			}
			mv.addObject("list_settlement",list_settlement);
		}
		mv.setViewName("sercar/recheck/settlement_list");
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	//去具体账单情页面
	@RequestMapping(value="/goSettlementDetails")
	public ModelAndView goSettlementDetails(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData settlement = settlementService.findById(pd);
		
		String dsmxIdStr = settlement.getString("YL3");
		List<PageData> dsmxs = new ArrayList<PageData>();
		PageData pd_dsmx = new PageData();
		if(dsmxIdStr.contains("-")){
			String[] split = dsmxIdStr.split("-");
			for (String dsmxId : split) {
				pd_dsmx.put("DSMX_ID", dsmxId);
				PageData dsmx = dsmxService.findById(pd);
				dsmxs.add(dsmx);
			}
		}else{
			pd_dsmx.put("DSMX_ID", dsmxIdStr);
			PageData dsmx = dsmxService.findById(pd);
			dsmxs.add(dsmx);
		}
		
		String pomxIdStr = settlement.getString("YL4");
		List<PageData> pomxs = new ArrayList<PageData>();
		PageData pd_pomx = new PageData();
		if(pomxIdStr.contains("-")){
			String[] split = pomxIdStr.split("-");
			for (String pomxId : split) {
				pd_pomx.put("POMX_ID", pomxId);
				PageData pomx = pomxService.findById(pd);
				pomxs.add(pomx);
			}
		}else{
			pd_pomx.put("POMX_ID", pomxIdStr);
			PageData pomx = pomxService.findById(pd);
			pomxs.add(pomx);
		}
		mv.addObject("dsmxs",dsmxs);
		mv.addObject("pomxs",pomxs);
		mv.setViewName("sercar/recheck/settlement_details");
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	//去结算复核页面
	@RequestMapping(value="/goSettlement")
	public ModelAndView goSettlement(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		String name = findByUsername.getString("NAME");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = settlementService.findById(pd);
		
		String status = pd.getString("STATUS");
		if ("待付款".equals(status)) {
			mv.addObject("msg","success");
			mv.setViewName("save_result");
			return mv;
		}
		
		mv.setViewName("sercar/recheck/settlement_check");
		mv.addObject("pd", pd);
		mv.addObject("name", name);
		mv.addObject("gsid", gsid);
		mv.addObject("username", username);
		return mv;
	}
	
	//同意复核
	@RequestMapping(value="/agreeSettlement")
	public ModelAndView agreeSettlement() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = pd.getString("username");
		String gongsiId = pd.getString("gongsiId");
		String content = pd.getString("content");
		String name = pd.getString("name");
		
		pd = settlementService.findById(pd);
		String createName = pd.getString("CREATE_BY");
		
		pd.put("YL19", username);
		pd.put("YL20", DateUtil.getSdftime().format(new Date()));
		pd.put("STATUS", "待付款");
		pd.put("YL1", content);
		pd.put("YL10", gongsiId);
		pd.put("YL11", name);
		
		settlementService.edit(pd);
		
		PageData order = orderService.findById(pd);
		order.put("STATUS", "结算单已复核待客户付款");
		orderService.edit(order);
		
		sendYuanGong(createName, "结算单已复核待客户付款");

		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
		
	//不同意
	@RequestMapping(value="/disagreeSettlement")
	public ModelAndView disagreeSettlement() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = pd.getString("username");
		String gongsiId = pd.getString("gongsiId");
		String content = pd.getString("content");
		String name = pd.getString("name");
		
		pd = settlementService.findById(pd);
		String createName = pd.getString("CREATE_BY");
		
		pd.put("YL19", username);
		pd.put("YL20", DateUtil.getSdftime().format(new Date()));
		pd.put("STATUS", "复核未通过");
		pd.put("YL1", content);
		pd.put("YL10", gongsiId);
		pd.put("YL11", name);
		
		settlementService.edit(pd);
		
		PageData order = orderService.findById(pd);
		order.put("STATUS", "结算单未提交复核");
		orderService.edit(order);
		
		sendYuanGong(createName, "结算单复核未通过");

		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	//发送消息到员工微信
	public void sendYuanGong(String username,String contant) {
		try {
			PageData pd_dic = new PageData();
			pd_dic.put("BIANMA", "wxpath");
			PageData path = dictionariesService.findByBianma(pd_dic);
			String baseUrl = path.getString("BZ");
			
			if(username != null){
				PageData pd_user = new PageData();
				pd_user.put("USERNAME", username);
				PageData userInfo = userinfoService.findByUsername(pd_user);
				
				String wxid = userInfo.getString("YL7");
				if(wxid != null && !"".equals(wxid)){
					if("开".equals(userInfo.getString("YL8")) && wxid != null){
						String url = baseUrl+"/wx/sendFuhe?openid="+wxid+"&contant="+contant;
						String doHttpGet = Tools.doHttpGet(url);
						System.out.println(doHttpGet);
						System.err.println("发送完成");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//发送消息到客户微信
	public void send(String wxid,String contant){
		try {
			PageData pd_dic = new PageData();
			pd_dic.put("BIANMA", "wxpath");
			PageData path = dictionariesService.findByBianma(pd_dic);
			String baseUrl = path.getString("BZ");
				
			if(wxid != null && !"".equals(wxid)){
				String url = baseUrl+"/wx/sendFuhe?openid="+wxid+"&contant="+contant;
				String doHttpGet = Tools.doHttpGet(url);
				System.out.println(doHttpGet);
				System.err.println("发送完成");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
