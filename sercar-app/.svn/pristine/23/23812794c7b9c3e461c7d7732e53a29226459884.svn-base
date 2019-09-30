package com.qppi.crud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Memo;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.MemoService;
import com.qppi.crud.utils.GetRandem;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("memo")
public class MemoController extends BaseController{
	@Autowired
	private MemoService memoService;
	@Autowired
	private DictionariesService dictionariesService;
	
	/**
	 * 获取列表
	 * @param memo
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public Msg listMemo(Memo memo,HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		return Msg.success().add("result", memoService.list(memo,username));
	}
	
	/**
	 * 获取对象
	 * @param memo
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public Msg getMemo(Memo memo){
		if(memo.getMemoId() == null || memo.getMemoId().equals("") ){
			return Msg.success().add("result", "memoId不能为空");
		}else{
			return Msg.success().add("result", memoService.get(memo));
		}
	}
	
	/**
	 * 增加对象
	 * @param memo
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Msg addMemo(Memo memo, HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(memo).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		
		memo.setMemoId(GetRandem.getDateR());
		memo.setYl1(username);
		memo.setCreatetime(MyTools.getTime());
		if(memoService.add(memo)){
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.success().add("result", "success");
		}
		status = "failure";
		MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
		return Msg.fail().add("result", "fail");
	}

	/**
	 * 更新对象
	 * @param memo
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Msg updateMemo(Memo memo,HttpServletRequest request){
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(memo).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(memo.getMemoId() == null || memo.getMemoId().equals("") ){
			return Msg.success().add("result", "memoId不能为空");
		}else{
			if(memoService.update(memo)){
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
	 * @param memo
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Msg deleteMemo(Memo memo,HttpServletRequest request){
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(memo).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		if(memo.getMemoId() == null || memo.getMemoId().equals("") ){
			return Msg.success().add("result", "memoId不能为空");
		}else{
			if(memoService.delite(memo)){
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.success().add("result", "success");
			}
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "fail");
		}
	}
}
