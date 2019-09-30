package com.qppi.crud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Resume;
import com.qppi.crud.bean.Resumeexperience;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.service.ResumeService;
import com.qppi.crud.service.ResumeexperienceService;
import com.qppi.crud.service.UserInfoService;
import com.qppi.crud.utils.Msg;

@Controller
@RequestMapping("resume")
public class ResumeController extends BaseController{

	@Autowired
	private ResumeService resumeService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ResumeexperienceService resumeexperienceService;
	
	@RequestMapping("list")
	@ResponseBody
	public Msg list(HttpServletRequest request) {
		String username = getCurrentSysUser(request).getUsername();
		UserInfo userinfo = userInfoService.selectUserId(username);
		String userinfoId = userinfo.getUserinfoId();
		Resume r = resumeService.selectByUid(userinfoId);

		if(r != null){
			String resumeId = r.getResumeId();
			List<Resumeexperience> list = resumeexperienceService.selectByRid(resumeId);
			r.setResumeexperiences(list);
			return Msg.success().add("result", r);
		}
		return Msg.fail().add("result", "没有简历");
		
	}
}
