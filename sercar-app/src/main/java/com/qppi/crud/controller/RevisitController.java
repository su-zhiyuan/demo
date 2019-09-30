package com.qppi.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Revisit;
import com.qppi.crud.service.RevisitService;
import com.qppi.crud.utils.Msg;

@Controller
@RequestMapping("revisit")
public class RevisitController {
	
	@Autowired
	private RevisitService revisitService;

	//列表
	@RequestMapping("list")
	@ResponseBody
	public Msg List(String username, String yl10) {
		Revisit revisit = new Revisit();
		revisit.setCreater(username);
		revisit.setYl10(yl10);
		List<Revisit> listAll =  revisitService.getList(revisit);
		revisit.setYl4("待回访");
		List<Revisit> list = revisitService.getList(revisit);
		return Msg.success().add("result", listAll).add("size", list.size());
	}
	
	//待处理
	@RequestMapping("pendlist")
	@ResponseBody
	public Msg pendList(String username, String yl10) {
		Revisit revisit = new Revisit();
		revisit.setCreater(username);
		revisit.setYl10(yl10);
		revisit.setYl4("待回访");
		List<Revisit> list = revisitService.getList(revisit);
		return Msg.success().add("result", list);
	}
	
	//确认回访
	@RequestMapping("affirmvisit")
	@ResponseBody
	public Msg affirmVisit(String revisitId) {
		Revisit revisit = revisitService.getRevisit(revisitId);
		revisit.setYl4("已回访");
		if(!revisitService.update(revisit)){
			return Msg.fail().add("result", "失败");
		}
		return Msg.success().add("result", "成功");
	}
	
}
