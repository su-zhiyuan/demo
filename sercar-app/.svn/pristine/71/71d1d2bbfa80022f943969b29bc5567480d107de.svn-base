package com.qppi.crud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Complaint;
import com.qppi.crud.service.ComplaintService;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.utils.GetRandem;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("complaint")
public class ComplaintController extends BaseController {
	@Autowired
	private ComplaintService ComplaintService;
	@Autowired
	private DictionariesService dictionariesService;
	
	/**
	 * 投诉建议查询
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("selectComplaint")
	@ResponseBody
	public Msg selectComplaint(Complaint complaint, HttpServletRequest request,int page){
		if(complaint.getCreaterBy() == null || complaint.getCreaterBy().equals("") ){
			return Msg.success().add("result","createrBy不能为空");
		}else{
			return Msg.success().add("result",ComplaintService.selectComplaint(complaint,page));
		}
	}
	
	/**
	 * 投诉建议详情查询
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("selectComplaintId")
	@ResponseBody
	public Msg selectComplaintId(Complaint complaint, HttpServletRequest request){
		if(complaint.getComplaintId() == null || complaint.getComplaintId().equals("") ){
			return Msg.success().add("result","complaintId不能为空");
		}else{			
			return Msg.success().add("result",ComplaintService.selectComplaintId(complaint));
		}
	}
	
	/**
	 * //投诉建议添加
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("insertComplaint")
	@ResponseBody
	public Msg insertComplaint(Complaint complaint, HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(complaint).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(complaint.getCreaterBy() == null || complaint.getCreaterBy().equals("")){
			return Msg.success().add("result","createrBy不能为空");
		}else{
			complaint.setComplaintId(GetRandem.getDateR());
			complaint.setComplaintTime(MyTools.getTime());
			complaint.setState("未解决");
			if(ComplaintService.insertComplaint(complaint)){
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.success().add("result", "success");
			}
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "fail");
		}
	}
	
	
}
