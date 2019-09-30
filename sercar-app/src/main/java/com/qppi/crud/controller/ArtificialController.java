package com.qppi.crud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Parity;
import com.qppi.crud.bean.Record;
import com.qppi.crud.service.ArtificialService;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.utils.GetRandem;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("Artificial")
public class ArtificialController extends BaseController {
	@Autowired
	private ArtificialService artificialService;	//救援
	@Autowired
	private DictionariesService dictionariesService;
	
	
	/**
	 * 智能查询历史记录
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("selectRecord")
	@ResponseBody
	public Msg selectRecord(Record record, HttpServletRequest request){
		if(record.getSender() == null || record.getSender().equals("") ){
			return Msg.success().add("result","sender不能为空");
		}else{
			return Msg.success().add("result",artificialService.selectRecord(record));
		}
	}
	
	/**
	 * 查询智能回复
	 * @param artificial
	 * @param request
	 * @return
	 */
	@RequestMapping("selectArtificial")
	@ResponseBody
	public Msg selectArtificial(Record record, HttpServletRequest request){
		if(record.getRecordContent() == null || record.getRecordContent().equals("") ){
			return Msg.success().add("result","recordContent不能为空");
		}else{	
			return Msg.success().add("result",artificialService.selectArtificial(record));
		}
	}
	
	/**
	 * 人工回复信息
	 * @param artificial
	 * @param request
	 * @return
	 */
	@RequestMapping("insertParity")
	@ResponseBody
	public Msg insertParity(Parity parity, HttpServletRequest request){
		parity.setArtificialId(GetRandem.getDateR());
		
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(parity).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		if(!artificialService.insertParity(parity)){
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "fail");
		}
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.success().add("result", "success");
	}
	
	/**
	 * 查询人工回复记录 
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("selectParity")
	@ResponseBody
	public Msg selectParity(Parity parity, HttpServletRequest request){
		if(parity.getSender() == null || parity.getSender().equals("") && parity.getRecipient() == null || parity.getRecipient().equals("")){
			return Msg.success().add("result", "sender,recipient不能为空");
		}else{
			return Msg.success().add("result",artificialService.selectParity(parity));
		}
	}
}
