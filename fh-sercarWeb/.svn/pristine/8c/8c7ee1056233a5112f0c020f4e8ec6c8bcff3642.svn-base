package com.fh.controller.sercar.settlement;

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
import com.fh.service.sercar.carinfo.CarInfoManager;
import com.fh.service.sercar.contacts.ContactsManager;
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

@Controller
@RequestMapping(value="/settlestat")
public class SettleStatController extends BaseController{

	@Resource(name="settlementService")
	private SettlementManager settlementService;
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
	@Resource(name="caigourcService")
	private CaigourcManager caigourcService;
	@Resource(name="goodsService")
	private GoodsManager goodsService;
	@Resource(name="carinfoService")
	private CarInfoManager carinfoService;
	@Resource(name="contactsService")
	private ContactsManager contactsService;
	
	@RequestMapping(value="/jipannumber")
	public ModelAndView JipanNumber(Page page) throws Exception{
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
		
		String keywords = pd.getString("keywords");
		if(null != keywords && !"".equals(keywords)){
			settle_pd.put("keywords", keywords.trim());
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
		settle_pd.put("lastStart", lastStart);
		
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
		settle_pd.put("lastEnd", lastEnd);
		
		String status = pd.getString("status");
		if(null != status && !"".equals(status)){
			settle_pd.put("STATUS", status);
		}
		
		settle_pd.put("StatStatus", "同意放行");
		
		page.setPd(settle_pd);
		List<PageData> settleList = settlementService.findByCondition(page);
		int size = settleList.size();
		mv.addObject("jpkh",size);
		
		List<PageData> newList = new ArrayList<PageData>();
		if(size > 0){
			for (PageData settle : settleList) {
				String money = settle.getString("TOTAL");
				if(money != null && !"".equals(money) && !"0".equals(money)){
					newList.add(settle);
				}
			}
		}
		mv.addObject("xfjp",newList.size());
		
		lastEnd = lastStart;
		lastStart = Tools.getOldDate(lastStart);
		settle_pd.put("lastStart", lastStart);
		settle_pd.put("lastEnd", lastEnd);
		page.setPd(settle_pd);
		List<PageData> oldSettleList = settlementService.findByCondition(page);
		List<PageData> oldList = new ArrayList<PageData>();
		if(oldSettleList.size() > 0){
			for (PageData settle : oldSettleList) {
				String money = settle.getString("TOTAL");
				if(money != null && !"".equals(money) && !"0".equals(money)){
					oldList.add(settle);
				}
			}
		}
		
		PageData order_pd = new PageData();
		List<String> oldCarList = new ArrayList<String>();
		if(oldList.size()>0){
			for (PageData settle : oldList) {
				String orderId = settle.getString("ORDER_ID");
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String carId = order.getString("CAR_ID");
				oldCarList.add(carId);
			}
		}
		
		List<PageData> carInfoList = new ArrayList<PageData>();
		if(newList.size()>0){
			PageData car_pd = new PageData();
			for (PageData settle : newList) {
				String orderId = settle.getString("ORDER_ID");
				order_pd.put("ORDER_ID", orderId);
				PageData order = orderService.findById(order_pd);
				String carId = order.getString("CAR_ID");
				if(!oldCarList.contains(carId)){
					car_pd.put("CARINFO_ID", carId);
					PageData carInfo = carinfoService.findById(car_pd);
					carInfoList.add(carInfo);
				}
			}
		}
		
        mv.addObject("carInfoList",carInfoList);
        mv.addObject("carInfoSize",carInfoList.size());
		mv.setViewName("sercar/settlestat/settlement_stat");
		mv.addObject("varList", settleList);
		mv.addObject("pd",pd);
		mv.addObject("lastTime",lastStart);
		mv.addObject("endTime",lastEnd);
		return mv;
	}
	
	
	
	
}
