package com.qppi.crud.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.qppi.crud.bean.Dispatching;
import com.qppi.crud.bean.Order;
import com.qppi.crud.bean.PartOffer;
import com.qppi.crud.bean.Revisit;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.DispatchingService;
import com.qppi.crud.service.OrderService;
import com.qppi.crud.service.PartOfferService;
import com.qppi.crud.service.RevisitService;
import com.qppi.crud.service.UserInfoService;
import com.qppi.crud.utils.MySelfUtil;

@Controller
public class TimerController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private DictionariesService dictionariesService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private DispatchingService dispatchingService;
	@Autowired
	private PartOfferService partOfferService;
	@Autowired
	private RevisitService revisitService;
	

	//回访提醒
	@Scheduled(cron = "0 0 9 * * ?")
	public void huifang(){
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String newTime = sdf.format(new Date());
		List<Revisit> list = revisitService.selectAllVisit();
		for (Revisit revisit : list) {
			String outTime = revisit.getCreateTime();
			if(outTime != null && !"".equals(outTime)){
				try {
					Date outDate = sdf.parse(outTime);
					Date newDate = sdf.parse(newTime);
					
					long oTime = outDate.getTime();
					long nTime = newDate.getTime();
					
					Long ms = nTime-oTime;
					Long dd = 86400000L;
					Long day = ms / dd;
					
					if(day>=3 && day<=5){
						revisit.setYl4("待回访");
						revisitService.update(revisit);
						String contant = "有订单要回访";
						sendYuanGong(revisit.getCreater(), contant);
					}
					if(day>5){
						String contant = "您有订单未及时回访，请尽快回访";
						sendYuanGong(revisit.getCreater(), contant);
					}
					System.out.println(day);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}     
			}
		}
	}
	
	//派工提醒
	@Scheduled(cron = "0 0 9 * * ?")
	public void kaigong(){
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String newTime = sdf.format(new Date());
		
		List<Dispatching> list = dispatchingService.selectByStatus("待开工","待完工");
		if(list.size()>0){
			for (Dispatching dis : list) {
				String spell = dis.getYl5();
				if(spell != null && !"".equals(spell)){
					String[] times = spell.split(" --- ");
					if("待开工".equals(dis.getStatus())){
						try {
							Date outDate = sdf.parse(times[0]);
							Date newDate = sdf.parse(newTime);
							
							long oTime = outDate.getTime();
							long nTime = newDate.getTime();
							
							Long ms = nTime-oTime;
							Long dd = 86400000L;
							Long day = ms / dd;
							
							if(day>=1 && day<=2){
								String jsUserName = dis.getMaintenanceTechnician();
								String contant = "有派工单将要开工，注意开工时间";
								sendYuanGong(jsUserName , contant);
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					if("待完工".equals(dis.getStatus())){
						try {
							Date outDate = sdf.parse(times[1]);
							Date newDate = sdf.parse(newTime);
							
							long oTime = outDate.getTime();
							long nTime = newDate.getTime();
							
							Long ms = oTime - nTime;
							Long dd = 86400000L;
							Long day = ms / dd;
							
							if(day>=1 && day<=3){
								String jsUserName = dis.getMaintenanceTechnician();
								String contant = "有派工单将要完工，注意工作进度";
								sendYuanGong(jsUserName , contant);
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
		}
	}
	
	//定时取消报价单
	@Scheduled(cron = "0 0 9 * * ?")
	public void quxiaobaojia(){
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = sdf.format(new Date());
		
		List<PartOffer> list = partOfferService.selectbyStatus("待确认");
		if(list.size()>0){
			for (PartOffer part : list) {
				String checkedTime = part.getCheckedTime();
				
				try {
					Date checkedDate = sdf.parse(checkedTime);
					Date newDate = sdf.parse(nowTime);
					
					long cTime = checkedDate.getTime();
					long nTime = newDate.getTime();
					
					Long ms = nTime - cTime;
					Long dd = 86400000L;
					Long day = ms / dd;
					
					if(day>7){
						part.setStatus("客户未同意");
						partOfferService.update(part);
						String jsUserName = part.getCreateBy();
						String contant = "有报价单超时，客户已拒绝，请尽快确认报价单";
						sendYuanGong(jsUserName , contant);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//发送消息到员工微信
	public void sendYuanGong(String username,String contant){
		String baseUrl = dictionariesService.selectWxURL();
		if(username != null){
			UserInfo userInfo = userInfoService.selectUserId(username);
			String wxid = userInfo.getYl7();
			if("是".equals(userInfo.getYl8()) && wxid != null){
				String url = baseUrl+"/wx/sendFuhe?openid="+wxid+"&contant="+contant;
				String doHttpGet = MySelfUtil.doHttpGet(url);
				System.out.println(doHttpGet);
				System.err.println("发送完成");
			}
		}
	}
	
}
