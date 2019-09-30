package com.fh.controller.sercar.second;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.fh.service.sercar.repoinfo.RepoinfoManager;
import com.fh.service.sercar.repoinfo.impl.RepoinfoService;
import com.fh.service.sercar.settlement.SettlementManager;
import com.fh.service.sercar.userinfo.UserInfoManager;
import com.fh.service.system.dictionaries.DictionariesManager;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.util.Tools;

/**
 * 二期，汽车维修，统计模块
 * 主要统计：基盘客户数量，消费基盘数，消费基盘数新增，
 *          消费基盘流失，消费基盘返厂频次，客户流失率，一年客户忠诚数，结算客户数量
 *          结算产值，维修毛利率，配件客单，客单价，养护品客单价，忠诚客户占比
 * @author Word_6
 *
 */
@Controller
@RequestMapping(value="/second")
public class SecondController extends BaseController{
	
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
	@Resource(name="repoinfoService")
	private RepoinfoManager repoinfoService;
	

	//基盘客户数量统计 jipankehu
	@RequestMapping(value="/jipankehu")
	public ModelAndView JipanKehuList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		lastStart = getLeftTime(lastStart);
		settle_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		lastEnd = getRightTime(lastEnd);
		settle_pd.put("lastEnd", lastEnd);
		
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		PageData order_pd = new PageData();
		if(list.size()>0){
			for (PageData pageData : list) {
				String orderId = pageData.getString("ORDER_ID");
				String sum = pageData.getString("TOTAL");
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String str = order.getString("STATUS");
				if(str.contains("同意放行")){
					order.put("sum", sum);
					orderList.add(order);
				}
			}
		}
		
		mv.setViewName("sercar/second/jipankehu");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("count",orderList.size());
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//消费基盘数统计 
	@RequestMapping(value="/xiaofeijipan")
	public ModelAndView XiaofeiJipanList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		lastStart = getLeftTime(lastStart);
		settle_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		lastEnd = getRightTime(lastEnd);
		settle_pd.put("lastEnd", lastEnd);
		
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		PageData order_pd = new PageData();
		if(list.size()>0){
			for (PageData pageData : list) {
				String orderId = pageData.getString("ORDER_ID");
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String str = order.getString("STATUS");
				if(str.contains("同意放行")){
					if(sum != 0){
						order.put("sum", sum);
						orderList.add(order);
					}
				}
				
			}
		}
		
		mv.setViewName("sercar/second/xiaofeijipan");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("count",orderList.size());
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//消费基盘数新增 xzxiaofeijipan.do	
	@RequestMapping(value="/xzxiaofeijipan")
	public ModelAndView XzXiaofeiJipanList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		lastStart = getLeftTime(lastStart);
		settle_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		lastEnd = getRightTime(lastEnd);
		settle_pd.put("lastEnd", lastEnd);
		
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		PageData order_pd = new PageData();
		if(list.size()>0){
			for (PageData pageData : list) {
				boolean flage = false;
				String orderId = pageData.getString("ORDER_ID");
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String carId = order.getString("CAR_ID");
				
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				String newTime = pageData.getString("CREATE_TIME");
				String lastYear = getLastYear(newTime);
				if(lastYear != null){
					settle_pd.put("lastStart", lastYear);
					settle_pd.put("lastEnd", newTime);
					page.setPd(settle_pd);
					List<PageData> sList = settlementService.findByCondition(page);
					if(sList.size()>0){
						for (PageData settle : sList) {
							String oId = settle.getString("ORDER_ID");
							order_pd.put("ORDER_ID", oId);
							PageData o = orderService.findById(order_pd);
							String cId = o.getString("CAR_ID");
							if(cId.equals(carId)){
								flage = true;
							}
						}
					}
				}
				if(flage == false){
					String str = order.getString("STATUS");
					if(str.contains("同意放行")){
						if(sum != 0){
							order.put("sum", sum);
							orderList.add(order);
						}
					}
				}
			
			}
		}
		
		mv.setViewName("sercar/second/xzxiaofeijipan");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("count",orderList.size());
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//流失消费基盘数目
	@RequestMapping(value="/lsxiaofeijipan")
	public ModelAndView LsXiaofeiJipanList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		int month = 0;
		int year = 0;
		if(null != lastStart && !"".equals(lastStart)){
	        Calendar c = Calendar.getInstance();
	        c.setTime(format.parse(lastStart));
	        month = c.get(Calendar.MONTH) + 1;
	        year = c.get(Calendar.YEAR);
		}else{
			lastStart = format.format(new Date()); 
			Calendar cale = Calendar.getInstance();
			year = cale.get(Calendar.YEAR);
			month = cale.get(Calendar.MONTH) + 1;
		}
		
		
		String oldRightTime = "";
		int oldYear = year - 1;
		if(month<10){
			oldRightTime = oldYear + "-0" + month + "-01 00:00:00";
		}else{
			oldRightTime = year + month + "-01 00:00:00";
		}
		
		String oldLeftTime = "";
		int lastMonth = month - 1;
		if(lastMonth > 9 ){
			oldLeftTime = oldYear + lastMonth + "-01 00:00:00";
		}else if(lastMonth > 0 && lastMonth < 10){
			oldLeftTime = oldYear + "-0" + lastMonth + "-01 00:00:00";
		}else {
			int lastYear = oldYear - 1;
			oldLeftTime = lastYear + "-12-01 00:00:00";
		}
		
		settle_pd.put("lastStart", oldLeftTime);
		settle_pd.put("lastEnd", oldRightTime);
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		settle_pd.put("lastStart", oldRightTime);
		settle_pd.put("lastEnd", lastStart);
		page.setPd(settle_pd);
		List<PageData> nextList = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		PageData order_pd = new PageData();
		if(list.size()>0){
			for (PageData pageData : list) {
				boolean flag = false;
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				String orderId = pageData.getString("ORDER_ID");
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String carId = order.getString("CAR_ID");
				
				if(nextList.size()>0){
					for (PageData pdSett : nextList) {
						String oId = pdSett.getString("ORDER_ID");
						order_pd.put("ORDER_ID", oId);
						PageData o = orderService.findById(order_pd);
						String cId = o.getString("CAR_ID");
						if(cId.equals(carId)){
							flag = true;
						}
					}
				}
				if(flag == false){
					String str = order.getString("STATUS");
					if(str.contains("同意放行")){
						if(sum != 0){
							order.put("sum", sum);
							orderList.add(order);
						}
					}
				}
			}
		}
		
		mv.setViewName("sercar/second/lsxiaofeijipan");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("count",orderList.size());
		mv.addObject("lastTime",lastStart);
		mv.addObject("oldLeftTime",oldLeftTime);
		mv.addObject("oldRightTime",oldRightTime);
		return mv;
	}
	
	//消费基盘返厂频次	second/fancang.do
	@RequestMapping(value="/fancang")
	public ModelAndView FanCangList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		lastStart = getLeftTime(lastStart);
		settle_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		lastEnd = getRightTime(lastEnd);
		settle_pd.put("lastEnd", lastEnd);
		
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		List<String> carIdList = new ArrayList<String>();
		PageData order_pd = new PageData();
		if(list.size()>0){
			for (PageData pageData : list) {
				String orderId = pageData.getString("ORDER_ID");
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String str = order.getString("STATUS");
				if(str.contains("同意放行")){
					order.put("sum", sum);
					orderList.add(order);
					if(sum != 0){
						String carId = order.getString("CAR_ID");
						carIdList.add(carId);
					}
				}
			}
		}
		
		List<String> newCarIdList = Tools.removeDuplicate(carIdList);
		
		float rate = (float)list.size() / (float)newCarIdList.size();
		
		mv.setViewName("sercar/second/fancang");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("count",rate);
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//客户流失率second/kehuliushi.do
	@RequestMapping(value="/kehuliushi")
	public ModelAndView KehuLiushiList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		int month = 0;
		int year = 0;
		if(null != lastStart && !"".equals(lastStart)){
	        Calendar c = Calendar.getInstance();
	        c.setTime(format.parse(lastStart));
	        month = c.get(Calendar.MONTH) + 1;
	        year = c.get(Calendar.YEAR);
		}else{
			lastStart = format.format(new Date()); 
			Calendar cale = Calendar.getInstance();
			year = cale.get(Calendar.YEAR);
			month = cale.get(Calendar.MONTH) + 1;
		}
		
		String oldRightTime = "";
		int oldYear = year - 1;
		if(month<10){
			oldRightTime = oldYear + "-0" + month + "-01 00:00:00";
		}else{
			oldRightTime = year + month + "-01 00:00:00";
		}
		
		String oldLeftTime = "";
		int lastMonth = month - 1;
		if(lastMonth > 9 ){
			oldLeftTime = oldYear + lastMonth + "-01 00:00:00";
		}else if(lastMonth > 0 && lastMonth < 10){
			oldLeftTime = oldYear + "-0" + lastMonth + "-01 00:00:00";
		}else {
			int lastYear = oldYear - 1;
			oldLeftTime = lastYear + "-12-01 00:00:00";
		}
		
		settle_pd.put("lastStart", oldLeftTime);
		settle_pd.put("lastEnd", oldRightTime);
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		settle_pd.put("lastStart", oldRightTime);
		settle_pd.put("lastEnd", lastStart);
		page.setPd(settle_pd);
		List<PageData> nextList = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		PageData order_pd = new PageData();
		List<String> carIdList = new ArrayList<String>();
		List<String> cIdList = new ArrayList<String>();
		
		if(list.size()>0){
			for (PageData pageData : list) {
				boolean flag = false;
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				String orderId = pageData.getString("ORDER_ID");
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String str = order.getString("STATUS");
				String carId = "";
				if(str.contains("同意放行")){
					if(sum != 0){
						carId = order.getString("CAR_ID");
						carIdList.add(carId);
					}
				}
				
				if(nextList.size()>0){
					for (PageData pdSett : nextList) {
						String oId = pdSett.getString("ORDER_ID");
						order_pd.put("ORDER_ID", oId);
						PageData o = orderService.findById(order_pd);
						String cId = o.getString("CAR_ID");
						if(cId.equals(carId)){
							flag = true;
						}
					}
				}
				if(flag == false){
					cIdList.add(carId);
					if(str.contains("同意放行")){
						if(sum != 0){
							order.put("sum", sum);
							orderList.add(order);
						}
					}
				}
			}
		}
		
		List<String> newCarIdList = Tools.removeDuplicate(carIdList);
		List<String> newcIdList = Tools.removeDuplicate(cIdList);
		int size = newCarIdList.size();
		if(size > 0){
			float count = (float)newcIdList.size() / (float)size;
			mv.addObject("count",count);
		}else {
			mv.addObject("count","0");
		}
		
		mv.setViewName("sercar/second/kehuliushi");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("lastTime",lastStart);
		mv.addObject("oldLeftTime",oldLeftTime);
		mv.addObject("oldRightTime",oldRightTime);
		return mv;
	}
	
	//结算客户数量 	second/jiesuankehu.do
	@RequestMapping(value="/jiesuankehu")
	public ModelAndView JiesuanKehuList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		lastStart = getLeftTime(lastStart);
		settle_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		lastEnd = getRightTime(lastEnd);
		settle_pd.put("lastEnd", lastEnd);
		
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		List<String> contactIdList = new ArrayList<String>();
		PageData order_pd = new PageData();
		if(list.size()>0){
			for (PageData pageData : list) {
				String orderId = pageData.getString("ORDER_ID");
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String str = order.getString("STATUS");
				if(str.contains("同意放行")){
					order.put("sum", sum);
					String contactId = order.getString("CONTACT_ID");
					contactIdList.add(contactId);
					orderList.add(order);
				}
				
			}
		}
		List<String> newContactIdList = Tools.removeDuplicate(contactIdList);
		
		
		mv.setViewName("sercar/second/jiesuankehu");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("count",newContactIdList.size());
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//客户忠诚数 	second/kehuzhongcheng.do
	@RequestMapping(value="/kehuzhongcheng")
	public ModelAndView KehuZhongchengList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		lastStart = getLeftTime(lastStart);
		settle_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		lastEnd = getRightTime(lastEnd);
		settle_pd.put("lastEnd", lastEnd);
		
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		List<String> contactList = new ArrayList<String>();
		List<String> payList = new ArrayList<String>();
		PageData order_pd = new PageData();
		if(list.size()>0){
			for (PageData pageData : list) {
				String orderId = pageData.getString("ORDER_ID");
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String str = order.getString("STATUS");
				if(str.contains("同意放行")){
					order.put("sum", sum);
					String contactId = order.getString("CONTACT_ID");
					contactList.add(contactId);
					if(sum != 0){
						orderList.add(order);
						payList.add(contactId);
					}
				}
			}
		}
		List<String> newContactList = Tools.removeDuplicate(contactList);
		List<String> newPayIdList = Tools.removeDuplicate(payList);
		
		int size = newContactList.size();
		if(size>0){
			float count = (float)newPayIdList.size() / (float)size;
			mv.addObject("count",count);
		}else {
			mv.addObject("count","0");
		}
		
		mv.setViewName("sercar/second/kehuzhongcheng");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//结算产值 	second/jiesuanchanzhi.do
	@RequestMapping(value="/jiesuanchanzhi")
	public ModelAndView JiesuanChanzhiList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		lastStart = getLeftTime(lastStart);
		settle_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		lastEnd = getRightTime(lastEnd);
		settle_pd.put("lastEnd", lastEnd);
		
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		double count = 0;
		PageData order_pd = new PageData();
		if(list.size()>0){
			for (PageData pageData : list) {
				String orderId = pageData.getString("ORDER_ID");
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String str = order.getString("STATUS");
				if(str.contains("同意放行")){
					order.put("sum", sum);
					count = count + sum;
					orderList.add(order);
				}
			}
		}
		
		mv.setViewName("sercar/second/jiesuanchanzhi");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("count",count);
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//维修毛利率 	second/weixiumaoli.do
	@RequestMapping(value="/weixiumaoli")
	public ModelAndView WeixiuMaoliList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		lastStart = getLeftTime(lastStart);
		settle_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		lastEnd = getRightTime(lastEnd);
		settle_pd.put("lastEnd", lastEnd);
		
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		double count = 0;
		double chengben = 0;
		PageData order_pd = new PageData();
		Page pomx_page = new Page();
		PageData pomx_pd = new PageData();
		if(list.size()>0){
			for (PageData pageData : list) {
				String orderId = pageData.getString("ORDER_ID");
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String str = order.getString("STATUS");
				if(str.contains("同意放行")){
					order.put("sum", sum);
					count = count + sum;
					orderList.add(order);
					
					pomx_pd.put("YL2", orderId);
					pomx_page.setPd(pomx_pd);
					List<PageData> pomxList = pomxService.findByCondition(pomx_page);
					if(pomxList.size()>0){
						for (PageData pomx : pomxList) {
							double djprice = Double.parseDouble(pomx.getString("PRICE")) * Double.parseDouble(pomx.getString("PART_COUNT"));
							chengben = chengben + djprice;
						}
					}
				}
			}
		}
		
		if(count>0){
			double moli = ( count- chengben)/ count;
			mv.addObject("count",moli);
		}else {
			mv.addObject("count","0");
		}
		
		mv.setViewName("sercar/second/weixiumaoli");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//配件客单	second/peijiankedan.do
	@RequestMapping(value="/peijiankedan")
	public ModelAndView PeijianKedanList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		lastStart = getLeftTime(lastStart);
		settle_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		lastEnd = getRightTime(lastEnd);
		settle_pd.put("lastEnd", lastEnd);
		
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		double count = 0;
		double chengben = 0;
		PageData order_pd = new PageData();
		Page pomx_page = new Page();
		PageData pomx_pd = new PageData();
		if(list.size()>0){
			for (PageData pageData : list) {
				String orderId = pageData.getString("ORDER_ID");
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String str = order.getString("STATUS");
				if(str.contains("同意放行")){
					order.put("sum", sum);
					orderList.add(order);
					
					pomx_pd.put("YL2", orderId);
					pomx_page.setPd(pomx_pd);
					List<PageData> pomxList = pomxService.findByCondition(pomx_page);
					if(pomxList.size()>0){
						for (PageData pomx : pomxList) {
							double djprice = Double.parseDouble(pomx.getString("PRICE")) * Double.parseDouble(pomx.getString("PART_COUNT"));
							chengben = chengben + djprice;
						}
					}
				}
			}
		}
		
		int size = orderList.size();
		if(size>0){
			double moli = chengben / size;
			mv.addObject("count",moli);
		}else {
			mv.addObject("count","0");
		}
		
		mv.setViewName("sercar/second/peijiankedan");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//客单价  	second/kedanjia.do
	@RequestMapping(value="/kedanjia")
	public ModelAndView KedanjiaList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		lastStart = getLeftTime(lastStart);
		settle_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		lastEnd = getRightTime(lastEnd);
		settle_pd.put("lastEnd", lastEnd);
		
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		double count = 0;
		int number = 0;
		PageData order_pd = new PageData();
		if(list.size()>0){
			for (PageData pageData : list) {
				String orderId = pageData.getString("ORDER_ID");
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String str = order.getString("STATUS");
				if(str.contains("同意放行")){
					order.put("sum", sum);
					count = count + sum;
					number = number + 1;
					orderList.add(order);
				}
			}
		}
		
		if(number>0){
			double danjia = count / number;
			mv.addObject("count",danjia);
		}else {
			mv.addObject("count","0");
		}
		
		mv.setViewName("sercar/second/kedanjia");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//养护品客单价	second/yhpkedanjia.do
	@RequestMapping(value="/yhpkedanjia")
	public ModelAndView YhpKedanjiaList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		lastStart = getLeftTime(lastStart);
		settle_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		lastEnd = getRightTime(lastEnd);
		settle_pd.put("lastEnd", lastEnd);
		
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		PageData order_pd = new PageData();
		Page pomx_page = new Page();
		PageData pomx_pd = new PageData();
		PageData repo_pd = new PageData();
		double price = 0;
		
		if(list.size()>0){
			for (PageData pageData : list) {
				String orderId = pageData.getString("ORDER_ID");
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String str = order.getString("STATUS");
				if(str.contains("同意放行")){
					order.put("sum", sum);
					
					
					pomx_pd.put("YL2", orderId);
					pomx_page.setPd(pomx_pd);
					List<PageData> pomxList = pomxService.findByCondition(pomx_page);
					if(pomxList.size()>0){
						for (PageData pomx : pomxList) {
							String repoId = pomx.getString("YL9");
							if(repoId != null && !"".equals(repoId)){
								repo_pd.put("REPOINFO_ID", repoId);
								PageData repo = repoinfoService.findById(repo_pd);
								if("养护品".equals(repo.getString("TYPE"))){
									double djprice = Double.parseDouble(pomx.getString("PRICE")) * Double.parseDouble(pomx.getString("PART_COUNT"));
									price = price + djprice;
									orderList.add(order);
								}
							}
							
						}
					}
				}
			}
		}
		
		int size = orderList.size();
		if(size>0){
			double danjia = price / size;
			mv.addObject("count",danjia);
		}else {
			mv.addObject("count","0");
		}
		
		mv.setViewName("sercar/second/yhpkedanjia");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//忠诚客户占比	second/zhongchengkehu.do
	@RequestMapping(value="/zhongchengkehu")
	public ModelAndView ZhongchengKehuList(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Reception");
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		
		PageData userinfo_pd = new PageData();
		userinfo_pd.put("USERNAME", username);
		PageData findByUsername = userinfoService.findByUsername(userinfo_pd);
		String gsid = findByUsername.getString("YL1");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		PageData settle_pd = new PageData();
		settle_pd.put("YL10", gsid);
		
		String lastStart = pd.getString("lastStart");
		lastStart = getLeftTime(lastStart);
		settle_pd.put("lastStart", lastStart);
		
		String lastEnd = pd.getString("lastEnd");
		lastEnd = getRightTime(lastEnd);
		settle_pd.put("lastEnd", lastEnd);
		
		page.setPd(settle_pd);
		List<PageData> list = settlementService.findByCondition(page);
		
		List<PageData> orderList = new ArrayList<PageData>();
		PageData order_pd = new PageData();
		List<String> contactList = new ArrayList<>();
		
		if(list.size()>0){
			for (PageData pageData : list) {
				String orderId = pageData.getString("ORDER_ID");
				double sum = Double.parseDouble(pageData.getString("TOTAL"));
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String str = order.getString("STATUS");
				if(str.contains("同意放行")){
					order.put("sum", sum);
					orderList.add(order);
					String string = order.getString("CONTACT_ID");
					contactList.add(string);
				}
			}
		}
		
		Integer cf = getCfNumber(contactList);
		List<String> newContactIdList = Tools.removeDuplicate(contactList);
		
		int size = newContactIdList.size();
		if(size>0){
			double danjia = (double)cf / (double)size;
			mv.addObject("count",danjia);
		}else {
			mv.addObject("count","0");
		}
		
		mv.setViewName("sercar/second/zhongchengkehu");
		mv.addObject("varList", orderList);
		mv.addObject("pd",pd);
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	//根据时间获取到对应的一年前的时间
	public static String getLastYear (String time) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Calendar c = Calendar.getInstance();
	        c.setTime(format.parse(time));
	        c.add(Calendar.YEAR, -1);
	        Date y = c.getTime();
	        String year = format.format(y);
	        return year;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//获取查询中要大于的时间
	public String getLeftTime(String time){
		if(null != time && !"".equals(time)){
			if(!time.contains(" 00:00:00")){
				time = time + " 00:00:00";
			}
		}else{
			Calendar cale = Calendar.getInstance();
			int year = cale.get(Calendar.YEAR);
			int month = cale.get(Calendar.MONTH) + 1;
			if(month<10){
				time = year+"-0"+month+"-01 00:00:00";
			}else{
				time = year+"-"+month+"-01 00:00:00";
			}
		}
		return time;
	}
	
	//获取查询中要小于的时间
	public String getRightTime(String time){
		if(null != time && !"".equals(time)){
			if(!time.contains(" 23:59:59")){
				time = time +" 23:59:59";
			}
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String format = dateFormat.format(new Date());
			time = format + " 23:59:59";
		}
		return time;
	}
	
	//统计一个数组中个个值出现的次数 找出出现两次及以上值的个数
	public Integer getCfNumber(List<String> list){
		Map<String,Integer> map = new HashMap<>();
	    for(String str:list){
	    	Integer i = 1; 
	        if(map.get(str) != null){
	            i=map.get(str)+1;
	        }
	        map.put(str,i);
	    }
	    Integer number = 0;
	    for(String s:map.keySet()){
	        if(map.get(s) > 1){
	            number = number + 1;
	        }
	    }
	    return number;
	}
	
}
