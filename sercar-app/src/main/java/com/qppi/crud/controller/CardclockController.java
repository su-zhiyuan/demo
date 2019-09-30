package com.qppi.crud.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Cardclock;
import com.qppi.crud.bean.CompanyInfo;
import com.qppi.crud.service.CardclockService;
import com.qppi.crud.service.CompanyInfoService;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.utils.GetRandem;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("cardclock")
public class CardclockController extends BaseController {
	@Autowired
	private CardclockService cardclockService;
	@Autowired
	private CompanyInfoService companyInfoService;
	@Autowired
	private DictionariesService dictionariesService;
	
	/**
	 * 获取列表
	 * @param cardclock
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Msg listCardclock(Cardclock cardclock,HttpServletRequest request,String companyinfoId) {
		String username = getCurrentSysUser(request).getUsername();
		return Msg.success().add("result", cardclockService.list(cardclock,companyinfoId,username));
	}
	
	/**
	 * 获取对象
	 * @param cardclock
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public Msg getCardclock(Cardclock cardclock){
		if(cardclock.getCardclockId() == null || cardclock.getCardclockId().equals("") ){
			return Msg.success().add("result", "cardclockId不能为空");
		}else{			
			return Msg.success().add("result", cardclockService.get(cardclock));
		}
	}
	
	/**
	 * 打卡
	 * @param cardclock
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Msg addCardclock(Cardclock cardclock, HttpServletRequest request) {
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(cardclock).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		String username = getCurrentSysUser(request).getUsername();
		String time = MyTools.dayTime();
		String[] split = time.split("-");
		cardclock.setYl6(split[1]);
		cardclock.setCardclockId(GetRandem.getDateR());
		cardclock.setDate(time);
		cardclock.setUpdatetime(MyTools.getTime());
		cardclock.setDowntime(getCurrentSysUser(request).getUsername());
		
		Calendar c = Calendar.getInstance();
		String hour = c.get(Calendar.HOUR_OF_DAY)+"";
		String minute = c.get(Calendar.MINUTE)+"";
		int dkTime = Integer.parseInt(hour+minute);
		
		CompanyInfo companyInfo = companyInfoService.selectById(cardclock.getYl5());
		
		if(companyInfo == null){
			return Msg.fail().add("result", companyInfo);
		}
		
		
		//上班
		if(cardclock.getYl1().equals("1")){
			int sbTime = Integer.parseInt(companyInfo.getYl2()) ;
			if(sbTime < dkTime){
				cardclock.setYl2("迟到");
			}else if(sbTime > dkTime){
				cardclock.setYl2("正常");
			}
			
		}
		
		//下班
		if(cardclock.getYl1().equals("2")){
			int spTime = Integer.parseInt(companyInfo.getYl3());
			if(spTime < dkTime){
				cardclock.setYl2("正常");
			}else if(spTime > dkTime){
				cardclock.setYl2("早退");
			}
		}
		
		if(cardclock.getYl7().equals("正常打卡")){
			cardclock.setYl8(companyInfo.getCompanyName());
		}
		
		List<Cardclock> list = cardclockService.selectByCard(cardclock.getYl1(), username);
		if(list.size()>0){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "fail"); 
		}
		
		if(cardclockService.add(cardclock)){
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "success");
		}
		status = "failure";
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.fail().add("result", "fail");
	}
	
	/**
	 * 更新对象
	 * @param cardclock
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Msg updateCardclock(Cardclock cardclock, HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(cardclock).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(cardclock.getCardclockId() == null || cardclock.getCardclockId().equals("") ){
			return Msg.success().add("result", "cardclockId不能为空");
		}else{
			if(cardclockService.update(cardclock)){
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.success().add("result", "success");
			}
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "fail");
		}
	}
	
	/**
	 * 删除对象
	 * @param cardclock
	 * @return
	 */ 
	@RequestMapping("delete")
	@ResponseBody
	public Msg deleteCardclock(Cardclock cardclock,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(cardclock).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(cardclock.getCardclockId() == null || cardclock.getCardclockId().equals("") ){
			return Msg.success().add("result", "cardclockId不能为空");
		}else{
			if(cardclockService.delite(cardclock)){
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.success().add("result", "success");
			}
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "fail");
		}
	}
	
	/**
	 * 根据用户名查打卡记录
	 */
	@RequestMapping("select")
	@ResponseBody
	public Msg select(Cardclock cardclock,HttpServletRequest request,int page){
		String username = getCurrentSysUser(request).getUsername();
		cardclock.setDowntime(username);
		List<Cardclock> list = cardclockService.selectByName(cardclock,page);
		return Msg.success().add("result", list);
	}
}
