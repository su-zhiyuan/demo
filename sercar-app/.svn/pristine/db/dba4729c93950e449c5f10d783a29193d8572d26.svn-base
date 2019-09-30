package com.qppi.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Applyjb;
import com.qppi.crud.bean.Applypyj;
import com.qppi.crud.bean.Baoxiao;
import com.qppi.crud.bean.CaigouRC;
import com.qppi.crud.bean.Evection;
import com.qppi.crud.bean.Leave;
import com.qppi.crud.bean.Outside;
import com.qppi.crud.bean.Paymentrequest;
import com.qppi.crud.bean.YongGong;
import com.qppi.crud.service.ApplyjbService;
import com.qppi.crud.service.ApplypyjService;
import com.qppi.crud.service.BaoxiaoService;
import com.qppi.crud.service.CaiGourcService;
import com.qppi.crud.service.EvectionService;
import com.qppi.crud.service.LeaveService;
import com.qppi.crud.service.OutsideService;
import com.qppi.crud.service.PaymentrequestService;
import com.qppi.crud.service.YongGongService;
import com.qppi.crud.utils.Msg;

@Controller
@RequestMapping("rcgl")
public class RcglController extends BaseController{
	
	@Autowired
	private ApplyjbService applyjbService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private OutsideService outsideService;
	@Autowired
	private EvectionService evectionService;
	@Autowired
	private BaoxiaoService baoxiaoService;
	@Autowired
	private PaymentrequestService paymentrequestService;
	@Autowired
	private ApplypyjService applypyjService;
	@Autowired
	private CaiGourcService caiGourcService;
	@Autowired
	private YongGongService yongGongService;

	//所有待审批的数目
	@RequestMapping("allNum")
	@ResponseBody
	public Msg allNum(String yl10,HttpServletRequest request){
		Map<String, Integer> map = new HashMap<String, Integer>();
		String username = getCurrentSysUser(request).getUsername();
		
		
		//加班
		Integer jsNum = 0;
		Applyjb applyjb = new Applyjb();
		applyjb.setYl10(yl10);
		applyjb.setYl1("待审批");
		List<Applyjb> applyjbList = applyjbService.selectDSP(applyjb);
		for (Applyjb jb : applyjbList) {
			String yl16 = jb.getYl16();
			if(yl16 == null){
				jsNum = jsNum + 1;
			}else {
				String[] split = yl16.split(",");
				for (String str : split) {
					if(!str.equals(username)){
						jsNum = jsNum + 1;
					}
				}
			}
		}
		map.put("jiabandsp", jsNum);
		
		//请假
		Integer leNum = 0;
		Leave leave = new Leave();
		leave.setYl10(yl10);
		leave.setYl1("待审批");
		List<Leave> leaveList = leaveService.selectDSP(leave);
		for (Leave le : leaveList) {
			String yl16 = le.getYl16();
			if(yl16 == null){
				leNum = leNum + 1;
			}else {
				String[] split = yl16.split(",");
				for (String str : split) {
					if(!str.equals(username)){
						leNum = leNum + 1;
					}
				}
			}
		}
		map.put("qingjiadsp", leNum);
		
		//外出
		Integer outNum = 0;
		Outside outside = new Outside();
		outside.setYl10(yl10);
		outside.setYl1("待审批");
		List<Outside> outsideList = outsideService.selectDSP(outside);
		for (Outside out : outsideList) {
			String yl16 = out.getYl16();
			if(yl16 == null){
				outNum = outNum + 1;
			}else {
				String[] split = yl16.split(",");
				for (String str : split) {
					if(!str.equals(username)){
						outNum = outNum + 1;
					}
				}
			}
		}
		map.put("waichudsp", outNum);
		
		//出差
		Integer eveNum = 0;
		Evection evection = new Evection();
		evection.setYl10(yl10);
		evection.setYl1("待审批");
		List<Evection> evectionList = evectionService.selectDSP(evection);
		for (Evection eve : evectionList) {
			String yl16 = eve.getYl16();
			if(yl16 == null){
				eveNum = eveNum + 1;
			}else {
				String[] split = yl16.split(",");
				for (String str : split) {
					if(!str.equals(username)){
						eveNum = eveNum + 1;
					}
				}
			}
		}
		map.put("chuchaidsp", eveNum);
		
		//报销
		Integer bxNum = 0;
		Baoxiao baoxiao = new Baoxiao();
		baoxiao.setYl10(yl10);
		baoxiao.setYl1("待审批");
		List<Baoxiao> baoxiaoList = baoxiaoService.selectDSP(baoxiao);
		for (Baoxiao bx : baoxiaoList) {
			String yl16 = bx.getYl16();
			if(yl16 == null){
				bxNum = bxNum + 1;
			}else {
				String[] split = yl16.split(",");
				for (String str : split) {
					if(!str.equals(username)){
						bxNum = bxNum + 1;
					}
				}
			}
		}
		map.put("baoxiaodsp", bxNum);
		
		//付款
		Integer fkNum = 0;
		Paymentrequest paymentrequest = new Paymentrequest();
		paymentrequest.setYl10(yl10);
		paymentrequest.setYl1("待审批");
		List<Paymentrequest> payList = paymentrequestService.selectDSP(paymentrequest);
		for (Paymentrequest pay : payList) {
			String yl16 = pay.getYl16();
			if(yl16 == null){
				fkNum = fkNum + 1;
			}else {
				String[] split = yl16.split(",");
				for (String str : split) {
					if(!str.equals(username)){
						fkNum = fkNum + 1;
					}
				}
			}
		}
		map.put("fukuandsp", fkNum);
		
		//备用金
		Integer byjNum = 0;
		Applypyj applypyj = new Applypyj();
		applypyj.setYl10(yl10);
		applypyj.setYl1("待审批");
		List<Applypyj> pyjList = applypyjService.selectDSP(applypyj);
		for (Applypyj pyj : pyjList) {
			String yl16 = pyj.getYl16();
			if(yl16 == null){
				byjNum = byjNum + 1;
			}else {
				String[] split = yl16.split(",");
				for (String str : split) {
					if(!str.equals(username)){
						byjNum = byjNum + 1;
					}
				}
			}
		}
		map.put("beiyongjindsp", byjNum);
		
		//用工
		YongGong yongGong = new YongGong();
		yongGong.setYl10(yl10);
		yongGong.setYl1("待经理审批");
		List<YongGong> ygList = yongGongService.selectByStatus(yongGong);
		map.put("yonggongdsp", ygList.size());
		
		//采购
		Integer yspNum = 0;
		CaigouRC c1 = new CaigouRC();
		c1.setYl10(yl10);
		c1.setStatus("预审批");
		List<CaigouRC>  cg1List = caiGourcService.selectDSP(c1);
		for (CaigouRC rc : cg1List) {
			String shenhe = rc.getShenhe();
			if(shenhe == null){
				yspNum = yspNum + 1;
			}else {
				String[] split = shenhe.split(",");
				for (String str : split) {
					if(!str.equals(username)){
						yspNum = yspNum + 1;
					}
				}
			}
		}
		
		Integer dfsNum = 0;
		CaigouRC c2 = new CaigouRC();
		c2.setYl10(yl10);
		c2.setStatus("待复审");
		List<CaigouRC>  c1List = caiGourcService.selectDSP(c2);
		for (CaigouRC rc : c1List) {
			String shenhe = rc.getYl5();
			if(shenhe == null){
				dfsNum = dfsNum + 1;
			}else {
				String[] split = shenhe.split(",");
				for (String str : split) {
					if(!str.equals(username)){
						dfsNum = dfsNum + 1;
					}
				}
			}
		}
		map.put("caigoudsp", yspNum+dfsNum);
		return Msg.success().add("result", map);
		
	}
}
