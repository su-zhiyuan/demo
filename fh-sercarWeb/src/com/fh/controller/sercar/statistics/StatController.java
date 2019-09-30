package com.fh.controller.sercar.statistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Dictionaries;
import com.fh.service.sercar.caigourc.CaigourcManager;
import com.fh.service.sercar.dispatching.DispatchingManager;
import com.fh.service.sercar.dsmx.DsmxManager;
import com.fh.service.sercar.goods.GoodsManager;
import com.fh.service.sercar.inforelay.InfoRelayManager;
import com.fh.service.sercar.luyin.LuyinManager;
import com.fh.service.sercar.order.OrderManager;
import com.fh.service.sercar.partoffer.PartOfferManager;
import com.fh.service.sercar.pomx.PomxManager;
import com.fh.service.sercar.reception.ReceptionManager;
import com.fh.service.sercar.settlement.SettlementManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.util.Tools;

import oracle.net.aso.p;

@Controller
@RequestMapping(value="/statistics")
public class StatController extends BaseController{

	@Resource(name="orderService")
	private OrderManager orderService;
	@Resource(name="receptionService")
	private ReceptionManager receptionService;
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	@Resource(name="inforelayService")
	private InfoRelayManager inforelayService;
	@Resource(name="luyinService")
	private LuyinManager luyinService;
	@Resource(name="partofferService")
	private PartOfferManager partofferService;
	@Resource(name="dsmxService")
	private DsmxManager dsmxService;
	@Resource(name="pomxService")
	private PomxManager pomxService;
	@Resource(name="dispatchingService")
	private DispatchingManager dispatchingService;
	@Resource(name="settlementService")
	private SettlementManager settlementService;
	@Resource(name="caigourcService")
	private CaigourcManager caigourcService;
	@Resource(name="goodsService")
	private GoodsManager goodsService;
	
	/**接车单统计列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/reception")
	public ModelAndView ReceptionList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData reception_pd = new PageData();
		reception_pd.put("YL10", gsid);
		
		String keywords = pd.getString("keywords");
		if(null != keywords && !"".equals(keywords)){
			reception_pd.put("keywords", keywords.trim());
		}
		
		String lastStart = pd.getString("lastStart");
		if(null != lastStart && !"".equals(lastStart)){
			if(!lastStart.contains(" 00:00:00")){
				lastStart = lastStart + " 00:00:00";
			}
		}else{
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR);
			int month = cale.get(Calendar.MONTH) + 1;
			if(month<10){
				lastStart = year+"-0"+month+"-01 00:00:00";
			}else{
				lastStart = year+"-"+month+"-01 00:00:00";
			}
		}
		reception_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		if(null != lastEnd && !"".equals(lastEnd)){
			if(!lastEnd.contains(" 23:59:59")){
				lastEnd = lastEnd +" 23:59:59";
			}
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = dateFormat.format(new Date());
			lastEnd = format + " 23:59:59";
		}
		reception_pd.put("lastEnd", lastEnd);
		
		String status = pd.getString("status");
		if(null != status && !"".equals(status)){
			reception_pd.put("STATUS", status);
		}
		
		PageData pd_dic = new PageData();
		pd_dic.put("BIANMA", "jcdingdan");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd_dic);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("statusList", varList2);
		}
		
		page.setPd(reception_pd);
		List<PageData> list = receptionService.findByCondition(page);
		
		mv.setViewName("sercar/statistics/reception_stat");
		mv.addObject("varList", list);
		mv.addObject("pd",pd);
		mv.addObject("count",list.size());
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	/**接车单统计列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/inforelay")
	public ModelAndView InforelayList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData inforelay_pd = new PageData();
		inforelay_pd.put("YL10", gsid);
		
		String keywords = pd.getString("keywords");
		if(null != keywords && !"".equals(keywords)){
			inforelay_pd.put("keywords", keywords.trim());
		}
		
		String lastStart = pd.getString("lastStart");
		if(null != lastStart && !"".equals(lastStart)){
			if(!lastStart.contains(" 00:00:00")){
				lastStart = lastStart + " 00:00:00";
			}
		}else{
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR);
			int month = cale.get(Calendar.MONTH) + 1;
			if(month<10){
				lastStart = year+"-0"+month+"-01 00:00:00";
			}else{
				lastStart = year+"-"+month+"-01 00:00:00";
			}
		}
		inforelay_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		if(null != lastEnd && !"".equals(lastEnd)){
			if(!lastEnd.contains(" 23:59:59")){
				lastEnd = lastEnd +" 23:59:59";
			}
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = dateFormat.format(new Date());
			lastEnd = format + " 23:59:59";
		}
		inforelay_pd.put("lastEnd", lastEnd);
		
		String status = pd.getString("status");
		if(null != status && !"".equals(status)){
			inforelay_pd.put("STATUS", status);
		}
		
		PageData pd_dic = new PageData();
		pd_dic.put("BIANMA", "xxfkzhuangtai");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd_dic);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("statusList", varList2);
		}
		
		page.setPd(inforelay_pd);
		List<PageData> list = inforelayService.findByCondition(page);
		
		Page luyin_page = new Page();
		PageData luyin_pd = new PageData();
		for (PageData var : list) {
			String inforelayId = var.getString("INFORELAY_ID");
			luyin_pd.put("INFORELAYID", inforelayId);
			luyin_page.setPd(luyin_pd);
			List<PageData> luyinList = luyinService.findByCondition(luyin_page);
			var.put("luyinList", luyinList);
		}
		
		mv.setViewName("sercar/statistics/inforelay_stat");
		mv.addObject("varList", list);
		mv.addObject("pd",pd);
		mv.addObject("count",list.size());
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	/**报价单统计列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/partoffer")
	public ModelAndView PartofferList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData partoffer_pd = new PageData();
		partoffer_pd.put("YL10", gsid);
		
		String keywords = pd.getString("keywords");
		if(null != keywords && !"".equals(keywords)){
			partoffer_pd.put("keywords", keywords.trim());
		}
		
		String lastStart = pd.getString("lastStart");
		if(null != lastStart && !"".equals(lastStart)){
			if(!lastStart.contains(" 00:00:00")){
				lastStart = lastStart + " 00:00:00";
			}
		}else{
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR);
			int month = cale.get(Calendar.MONTH) + 1;
			if(month<10){
				lastStart = year+"-0"+month+"-01 00:00:00";
			}else{
				lastStart = year+"-"+month+"-01 00:00:00";
			}
		}
		partoffer_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		if(null != lastEnd && !"".equals(lastEnd)){
			if(!lastEnd.contains(" 23:59:59")){
				lastEnd = lastEnd +" 23:59:59";
			}
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = dateFormat.format(new Date());
			lastEnd = format + " 23:59:59";
		}
		partoffer_pd.put("lastEnd", lastEnd);
		
		String status = pd.getString("status");
		if(null != status && !"".equals(status)){
			partoffer_pd.put("STATUS", status);
		}
		
		
		page.setPd(partoffer_pd);
		List<PageData> list = partofferService.findByCondition(page);
		List<PageData> list2 = partofferService.statistics(page);
		
		
		double total = 0;
		double dingjin = 0;
		if(list.size()>0){
			for (PageData pageData : list) {
				total = total + Double.parseDouble(pageData.getString("TOTAL"));
				dingjin = dingjin + Double.parseDouble(pageData.getString("YL8"));
			}
		}
		
		mv.setViewName("sercar/statistics/partoffer_stat");
		mv.addObject("varList", list);
		mv.addObject("pd",pd);
		mv.addObject("count",list.size());
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		mv.addObject("total",total+"");
		mv.addObject("dingjin",dingjin+"");
		mv.addObject("varList2", list2);
		return mv;
	}
	
	/**
	 * 获取报价单明细
	 */
	@RequestMapping(value="/partdetails")
	public ModelAndView PartDetails(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("YL10", gsid);
		page.setPd(pd);
		List<PageData> list = partofferService.findByCondition(page);
		
		Page pomx_page = new Page();
		PageData pomx_pd = new PageData();
		Page dsmx_page = new Page();
		PageData dsmx_pd = new PageData();
		
		for (PageData pageData : list) {
			String type = pageData.getString("YL3");
			String partId = pageData.getString("PARTOFFER_ID");
			if("1".equals(type)){
				dsmx_pd.put("YL1", partId);
				dsmx_page.setPd(dsmx_pd);
				List<PageData> dsmxs = dsmxService.findByCondition(dsmx_page);
				pageData.put("dsmxList", dsmxs);
			}else{
				pomx_pd.put("PART_OFFER_ID",partId);
				pomx_page.setPd(pomx_pd);
				List<PageData> pomxs = pomxService.findByCondition(pomx_page);
				pageData.put("pomxList", pomxs);
			}
		}
		mv.setViewName("sercar/statistics/partoffer_detail");
		mv.addObject("varList",list);
		return mv;
	}
	
	/**派工单统计列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/dispatching")
	public ModelAndView DispatchingList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData dispatching_pd = new PageData();
		dispatching_pd.put("YL10", gsid);
		
		String keywords = pd.getString("keywords");
		if(null != keywords && !"".equals(keywords)){
			dispatching_pd.put("keywords", keywords.trim());
		}
		
		String lastStart = pd.getString("lastStart");
		if(null != lastStart && !"".equals(lastStart)){
			if(!lastStart.contains(" 00:00:00")){
				lastStart = lastStart + " 00:00:00";
			}
		}else{
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR);
			int month = cale.get(Calendar.MONTH) + 1;
			if(month<10){
				lastStart = year+"-0"+month+"-01 00:00:00";
			}else{
				lastStart = year+"-"+month+"-01 00:00:00";
			}
			
		}
		dispatching_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		if(null != lastEnd && !"".equals(lastEnd)){
			if(!lastEnd.contains(" 23:59:59")){
				lastEnd = lastEnd +" 23:59:59";
			}
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = dateFormat.format(new Date());
			lastEnd = format + " 23:59:59";
		}
		dispatching_pd.put("lastEnd", lastEnd);
		
		String status = pd.getString("status");
		if(null != status && !"".equals(status)){
			dispatching_pd.put("STATUS", status);
		}
		
		page.setPd(dispatching_pd);
		List<PageData> list = dispatchingService.findByCondition(page);
		
		double total = 0;
		if(list.size()>0){
			for (PageData pageData : list) {
				total = total + Double.parseDouble(pageData.getString("YL4"));
			}
		}
		
		List<PageData> list2 = dispatchingService.statistics(page);
		
		mv.setViewName("sercar/statistics/dispatching_stat");
		mv.addObject("varList", list);
		mv.addObject("pd",pd);
		mv.addObject("count",list.size());
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		mv.addObject("total",total+"");
		mv.addObject("varList2", list2);
		return mv;
	}
	
	/**
	 * 获取报价单明细
	 */
	@RequestMapping(value="/dispatchdetails")
	public ModelAndView DispatchDetails(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String lastStart = pd.getString("lastStart");
		String lastEnd = pd.getString("lastEnd");
		
		pd.put("YL10", gsid);
		page.setPd(pd);
		List<PageData> list = dispatchingService.statisticsbycreate(page);
		
		
		Page dispatching_page = new Page();
		PageData dispatching_pd = new PageData();
		Page dsmx_page = new Page();
		PageData dsmx_pd = new PageData();
		
		for (PageData pageData : list) {
			List<PageData> dsmxs= new ArrayList<PageData>();
			String js = pageData.getString("MAINTENANCE_TECHNICIAN");
			dispatching_pd.put("YL10", gsid);
			dispatching_pd.put("JISHI_NAME", js);
			dispatching_pd.put("lastStart", lastStart);
			dispatching_pd.put("lastEnd", lastEnd);
			
			dispatching_page.setPd(dispatching_pd);
			List<PageData> disList = dispatchingService.findByCondition(dispatching_page);
			for (PageData dis : disList) {
				String disId = dis.getString("DISPATCHING_ID");
				dsmx_pd.put("DISPATCHING_ID", disId);
				dsmx_page.setPd(dsmx_pd);
				List<PageData> ds = dsmxService.findByCondition(dsmx_page);
				if(ds.size()>0){
					for (PageData p : ds) {
						dsmxs.add(p);
					}
				}
			}
			pageData.put("dsmxList", dsmxs);
			pageData.put("DSMXNUM", dsmxs.size());
		}
		
		mv.setViewName("sercar/statistics/dispatching_detail");
		mv.addObject("varList",list);
		return mv;
	}
	
	/**结算单统计列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/settlement")
	public ModelAndView SettlementList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settlement_pd = new PageData();
		settlement_pd.put("YL10", gsid);
		
		String keywords = pd.getString("keywords");
		if(null != keywords && !"".equals(keywords)){
			settlement_pd.put("keywords", keywords.trim());
		}
		
		String lastStart = pd.getString("lastStart");
		if(null != lastStart && !"".equals(lastStart)){
			if(!lastStart.contains(" 00:00:00")){
				lastStart = lastStart + " 00:00:00";
			}
		}else{
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR);
			int month = cale.get(Calendar.MONTH) + 1;
			if(month<10){
				lastStart = year+"-0"+month+"-01 00:00:00";
			}else{
				lastStart = year+"-"+month+"-01 00:00:00";
			}
			
		}
		settlement_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		if(null != lastEnd && !"".equals(lastEnd)){
			if(!lastEnd.contains(" 23:59:59")){
				lastEnd = lastEnd +" 23:59:59";
			}
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = dateFormat.format(new Date());
			lastEnd = format + " 23:59:59";
		}
		settlement_pd.put("lastEnd", lastEnd);
		
		String status = pd.getString("status");
		if(null != status && !"".equals(status)){
			settlement_pd.put("STATUS", status);
		}
		
		page.setPd(settlement_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		double total = 0;
		if(list.size()>0){
			for (PageData pageData : list) {
				total = total + Double.parseDouble(pageData.getString("TOTAL"));
			}
		}
		
		mv.setViewName("sercar/statistics/settlement_stat");
		mv.addObject("varList", list);
		mv.addObject("pd",pd);
		mv.addObject("count",list.size());
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		mv.addObject("total",total+"");
		return mv;
	}
	
	@RequestMapping(value="/order")
	public ModelAndView OrderList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData order_pd = new PageData();
		order_pd.put("YL10", gsid);
		
		String keywords = pd.getString("keywords");
		if(null != keywords && !"".equals(keywords)){
			order_pd.put("keywords", keywords.trim());
		}
		
		String lastStart = pd.getString("lastStart");
		if(null != lastStart && !"".equals(lastStart)){
			if(!lastStart.contains(" 00:00:00")){
				lastStart = lastStart + " 00:00:00";
			}
		}else{
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR);
			int month = cale.get(Calendar.MONTH) + 1;
			if(month<10){
				lastStart = year+"-0"+month+"-01 00:00:00";
			}else{
				lastStart = year+"-"+month+"-01 00:00:00";
			}
		}
		order_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		if(null != lastEnd && !"".equals(lastEnd)){
			if(!lastEnd.contains(" 23:59:59")){
				lastEnd = lastEnd +" 23:59:59";
			}
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = dateFormat.format(new Date());
			lastEnd = format + " 23:59:59";
		}
		order_pd.put("lastEnd", lastEnd);
		
		String status = pd.getString("status");
		if(null != status && !"".equals(status)){
			order_pd.put("STATUS", status);
		}
		
		PageData pd_dic = new PageData();
		pd_dic.put("BIANMA", "dingdan");//获取数据字典的字节码
		PageData findByBianma2 = dictionariesService.findByBianma(pd_dic);
		if(findByBianma2 != null){
			String parentId2 = findByBianma2.getString("DICTIONARIES_ID");
			List<Dictionaries> varList2 = dictionariesService.listSubDictByParentId(parentId2);//查询下级
			mv.addObject("statusList", varList2);
		}
		
		page.setPd(order_pd);
		List<PageData> list = orderService.findByCondition(page);
		
		Page dsmx_page = new Page();
		PageData dsmx_pd = new PageData();
		Page partoffer_page = new Page();
		PageData partoffer_pd = new PageData();
		Page pomx_page = new Page();
		PageData pomx_pd = new PageData();
		
		for (PageData order : list) {
			String orderId = order.getString("ORDER_ID");
			
			partoffer_pd.put("ORDER_ID", orderId);
			partoffer_page.setPd(partoffer_pd);
			List<PageData> pList = partofferService.findByCondition(partoffer_page);
			double pomxzongji = 0;
			double dsmxzongji = 0;
			double partZongJi = 0;
			double zjMaoli = 0;
			if(pList.size()>0){
				for (PageData pageData : pList) {
					double parseDouble = Double.parseDouble(pageData.getString("TOTAL"));
					partZongJi = partZongJi + parseDouble;
					String partid = pageData.getString("PARTOFFER_ID");
					
					pomx_pd.put("PART_OFFER_ID", partid);
					pomx_page.setPd(pomx_pd);
					List<PageData> pomxList = pomxService.findByCondition(pomx_page);
					if(pomxList.size()>0){
						for (PageData p : pomxList) {
							double totals = Double.parseDouble(p.getString("TOTAL"));
							double price = Double.parseDouble(p.getString("PRICE"));
							double num = Double.parseDouble(p.getString("PART_COUNT"));
							zjMaoli = zjMaoli + totals - num * price;
							pomxzongji = pomxzongji + totals;
						}
					}
					
					dsmx_pd.put("YL1", partid);
					dsmx_page.setPd(dsmx_pd);
					List<PageData> dsmxList = dsmxService.findByCondition(dsmx_page);
					if(dsmxList.size()>0){
						for (PageData p : dsmxList) {
							double price = Double.parseDouble(p.getString("YL6"));
							dsmxzongji = dsmxzongji + price;
						}
					}
				}
			}
			order.put("zjMaoli", zjMaoli);
			order.put("pomxZongJi", pomxzongji);
			order.put("dsmxZongJi", dsmxzongji);
			order.put("partZongJi", partZongJi);
		}
		
		mv.setViewName("sercar/statistics/order_stat");
		mv.addObject("varList", list);
		mv.addObject("pd",pd);
		mv.addObject("count",list.size());
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//详细派工
	@RequestMapping(value="/getDsmx")
	public ModelAndView GetDsmx(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData> list = partofferService.findByCondition(page);
		
		Page dsmx_page = new Page();
		PageData dsmx_pd = new PageData();
		if(list.size()>0){
			for (PageData pageData : list) {
				String partId = pageData.getString("PARTOFFER_ID");
				dsmx_pd.put("YL1", partId);
				dsmx_page.setPd(dsmx_pd);
				List<PageData> dsmxs = dsmxService.findByCondition(dsmx_page);
				pageData.put("dsmxList", dsmxs);
			}
		}
		
		mv.setViewName("sercar/statistics/order_detail");
		mv.addObject("flag", "1");
		mv.addObject("varList", list);
		return mv;
	}
	
	//详细报价
	@RequestMapping(value="/getPomx")
	public ModelAndView GetPomx(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData> list = partofferService.findByCondition(page);
		if(list.size()>0){
			Page pomx_page = new Page();
			PageData pomx_pd = new PageData();
			
			for (PageData pageData : list) {
				String partId = pageData.getString("PARTOFFER_ID");
				pomx_pd.put("PART_OFFER_ID", partId);
				pomx_page.setPd(pomx_pd);
				List<PageData> pomxs = pomxService.findByCondition(pomx_page);
				if(pomxs.size()>0){
					for (PageData p : pomxs) {
						double totals = Double.parseDouble(p.getString("TOTAL"));
						double price = Double.parseDouble(p.getString("PRICE"));
						double num = Double.parseDouble(p.getString("PART_COUNT"));
						double maoli =  totals - num * price;
						p.put("ml", maoli);
					}
				}
				pageData.put("pomxList", pomxs);
			}
		}
		mv.setViewName("sercar/statistics/order_detail");
		mv.addObject("varList", list);
		mv.addObject("pd", pd);
		mv.addObject("flag", "2");
		return mv;
	}
	
	//采购单统计
	@RequestMapping(value="/caigou")
	public ModelAndView CaiGouList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData caigou_pd = new PageData();
		caigou_pd.put("YL10", gsid);
		
		String keywords = pd.getString("keywords");
		if(null != keywords && !"".equals(keywords)){
			caigou_pd.put("keywords", keywords.trim());
		}
		
		String lastStart = pd.getString("lastStart");
		if(null != lastStart && !"".equals(lastStart)){
			if(!lastStart.contains(" 00:00:00")){
				lastStart = lastStart + " 00:00:00";
			}
		}else{
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR);
			int month = cale.get(Calendar.MONTH) + 1;
			if(month<10){
				lastStart = year+"-0"+month+"-01 00:00:00";
			}else{
				lastStart = year+"-"+month+"-01 00:00:00";
			}
		}
		caigou_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		if(null != lastEnd && !"".equals(lastEnd)){
			if(!lastEnd.contains(" 23:59:59")){
				lastEnd = lastEnd +" 23:59:59";
			}
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = dateFormat.format(new Date());
			lastEnd = format + " 23:59:59";
		}
		caigou_pd.put("lastEnd", lastEnd);
		
		String status = pd.getString("status");
		if(null != status && !"".equals(status)){
			caigou_pd.put("STATUS", status);
		}
		
		
		page.setPd(caigou_pd);
		List<PageData> list = caigourcService.findByCondition(page);
		
		mv.setViewName("sercar/statistics/caigourc_stat");
		mv.addObject("varList", list);
		mv.addObject("pd",pd);
		mv.addObject("count",list.size());
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//获取采购单详细的商品信息
	@RequestMapping(value="/caigoudetails")
	public ModelAndView CaigouDetails(Page page) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		List<PageData> list = goodsService.findByCondition(page);
		
		mv.setViewName("sercar/statistics/caigourc_detail");
		mv.addObject("varList", list);
		return mv;
	}
	
}
