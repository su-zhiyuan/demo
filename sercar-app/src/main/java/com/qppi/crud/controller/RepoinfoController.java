package com.qppi.crud.controller;
/**
 * 仓库信息,出入库信息
 */
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.RepoChange;
import com.qppi.crud.bean.Repoinfo;
import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.service.RepoChangeService;
import com.qppi.crud.service.RepoinfoService;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;
import com.qppi.crud.utils.MyTools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("repoinfo")
public class RepoinfoController extends BaseController{

	@Autowired
	private RepoinfoService repoinfoService;
	@Autowired
	private RepoChangeService repoChangeService;
	@Autowired
	private DictionariesService dictionariesService;
	
	//列表
	@RequestMapping("list")
	@ResponseBody
	public Msg RepoinfoList(String keyword, Integer page, Repoinfo repoinfo){
		if(page == null){
			return Msg.fail().add("result", "失败");
		}
		List<Repoinfo> list = repoinfoService.selectList(keyword,page,repoinfo);
		return Msg.success().add("result", list);
	}
	
	//查看详情
	@RequestMapping("get")
	@ResponseBody
	public Msg GetRepoinfo(String repoinfoid){
		Repoinfo repoinfo = repoinfoService.selectGet(repoinfoid);
		return  Msg.success().add("result", repoinfo);
	}
	
	//入库
	@RequestMapping("repoIn")
	@ResponseBody
	public Msg repoIn(Repoinfo repoInfo,HttpServletRequest request,String count){
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(repoInfo).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		String repoId = repoInfo.getRepoinfoId();
		Repoinfo repo = repoinfoService.selectGet(repoId);
		if(repo != null){
			String number = repo.getNumber();
			int sum = Integer.parseInt(number) + Integer.parseInt(count);
			int ky = Integer.parseInt(repo.getYl6()) + Integer.parseInt(count);
			repo.setNumber(sum+"");
			repo.setYl6(ky+"");
			if(repoinfoService.update(repo)){
				RepoChange repoChange = new RepoChange();
				repoChange.setRepochangeId(MyTools.getDateR());
				repoChange.setType("入库");
				repoChange.setRepoId(repo.getRepoinfoId());
				repoChange.setCount(count);
				repoChange.setYl1(getCurrentSysUser(request).getUsername());
				repoChange.setYl2(MyTools.getTime());
				repoChange.setYl3("完成");
				repoChange.setYl4(repo.getName());
				repoChange.setYl10(repoInfo.getYl10());
				if(repoChangeService.insertSelective(repoChange)){
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.success().add("result", "入库成功");
				}else {
					status = "failure";
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.fail().add("result", "入库失败");
				}
			}else {
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "入库失败");
			}
		}else{
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "库存信息不存在");
		}
	}
	
	//出库
	@RequestMapping("repoOut")
	@ResponseBody
	public Msg OutRepoinfi(Repoinfo repoInfo,HttpServletRequest request,String count,String time, String cktype, String ckused, String ckname, String yl8){
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String responsecontent = JSONArray.fromObject(repoInfo).toString();
		String type = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		
		String repoid = repoInfo.getRepoinfoId();
		Repoinfo repo = repoinfoService.selectGet(repoid);
		if(repo != null){
			int number = Integer.parseInt(repo.getNumber());
			int cou = Integer.parseInt(count);
			if(number >= cou){
				int sum = number - cou;
				repo.setNumber(sum+"");
				if("1".equals(cktype)){
					//日常出库
					String yl6 = repo.getYl6();
					if(yl6 == null || "".equals(yl6)){
						yl6 = "0";
					}
					int ky = Integer.parseInt(yl6) - cou;
					repo.setYl6(ky+"");
				}else{
					String yl7 = repo.getYl7();
					if(yl7 == null || "".equals(yl7)){
						yl7 = "0";
					}
					int ky = Integer.parseInt(yl7) - cou;
					repo.setYl7(ky+"");
				}
				if(repoinfoService.update(repo)){
					RepoChange repoChange = new RepoChange();
					repoChange.setRepochangeId(MyTools.getDateR());
					repoChange.setType("出库");
					repoChange.setRepoId(repo.getRepoinfoId());
					repoChange.setCount(count);
					repoChange.setYl1(getCurrentSysUser(request).getUsername());
					repoChange.setYl2(MyTools.getTime());
					repoChange.setYl3("完成");
					repoChange.setYl4(repo.getName());
					repoChange.setYl10(repoInfo.getYl10());
					repoChange.setYl5(time);
					repoChange.setYl6(ckused);
					repoChange.setYl7(ckname);
					repoChange.setYl8(yl8);
					repoChange.setYl9(cktype);
					if(repoChangeService.insertSelective(repoChange)){
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
						return Msg.success().add("result", "出库成功");
					}else {
						status = "failure";
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
						return Msg.fail().add("result", "出库失败");
					}
				}else{
					status = "failure";
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
					return Msg.fail().add("result", "出库失败");
				}
			}else {
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
				return Msg.fail().add("result", "出库数目不合法");
			}
		}else{
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type, url);
			return Msg.fail().add("result", "库存信息不存在");
		}
	}
	
	//库存变更列表
	@RequestMapping("changelist")
	@ResponseBody
	public Msg ChangeList(String keyword, int page, RepoChange repoChange){
		List<RepoChange> list = repoChangeService.selectList(keyword,page,repoChange);
		if(list.size()>0){
			for (RepoChange repo : list) {
				String repoId = repo.getRepoId();
				Repoinfo repoingo = repoinfoService.selectGet(repoId);
				repoChange.setRepoinfo(repoingo);
			}
			return Msg.success().add("result", list);
		}else {
			return Msg.fail().add("result", list);
		}
		
	}
	
	//废除出入库
	@RequestMapping("change")
	@ResponseBody
	public Msg change(RepoChange rc, HttpServletRequest request){
		
		String Xiangmuming = request.getContextPath();
		String requestcontent = request.getRequestURL().toString();
		String type1 = request.getMethod();
		String url = dictionariesService.selectRiZhiURL();
		String status = "success";
		String username = getCurrentSysUser(request).getUsername();
		
		
		String repochangeId = rc.getRepochangeId();
		RepoChange repoChange = repoChangeService.getRepo(repochangeId);
		if(repoChange != null){
			String repoId = repoChange.getRepoId();
			Repoinfo repo = repoinfoService.selectGet(repoId);
			if(repo != null){
				String type = repoChange.getType();
				if("入库".equals(type)){
					int number = Integer.parseInt(repo.getNumber());
					int count = Integer.parseInt(repoChange.getCount());
					if(number >= count){
						repo.setNumber((number-count)+"");
						repoChange.setYl3("作废");
						try {
							repoinfoService.update(repo);
							repoChangeService.update(repoChange);
							
							String responsecontent = JSONArray.fromObject(repoChange).toString();
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url);
							return Msg.success().add("result", "移除成功");
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							String responsecontent = JSONArray.fromObject(repoChange).toString();
							status = "failure";
							MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url);
							return Msg.fail().add("result", "移除失败");
						}
					}else{
						String responsecontent = JSONArray.fromObject(repoChange).toString();
						status = "failure";
						MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url);
						return Msg.fail().add("result", "移除失败");
					}
				}else if("出库".equals(type)){
					int number = Integer.parseInt(repo.getNumber());
					int count = Integer.parseInt(repoChange.getCount());
					repo.setNumber((number+count)+"");
					repoChange.setYl3("作废");
					repoinfoService.update(repo);
					repoChangeService.update(repoChange);
					
					String responsecontent = JSONArray.fromObject(repoChange).toString();
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url);
					return Msg.success().add("result", "移除成功");
				}else {
					String responsecontent = JSONArray.fromObject(repoChange).toString();
					status = "failure";
					MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url);
					return Msg.fail().add("result", "已经移除");
				}
			}else {
				String responsecontent = JSONArray.fromObject(repoChange).toString();
				status = "failure";
				MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url);
				return Msg.fail().add("result", "库存信息不存在");
			}
		}else {
			String responsecontent = JSONArray.fromObject(repoChange).toString();
			status = "failure";
			MySelfUtil.addRiZhi(username, Xiangmuming, requestcontent, responsecontent, status, type1, url);
			return Msg.fail().add("result", "出入库信息不存在");
		}
	}
	
	//出入库的记录的详情
	@RequestMapping("getchange")
	@ResponseBody
	public Msg GetChange(RepoChange rc){
		String repochangeId = rc.getRepochangeId();
		RepoChange repoChange = repoChangeService.getRepo(repochangeId);
		if(repoChange != null){
			String repoId = repoChange.getRepoId();
			Repoinfo repo = repoinfoService.selectGet(repoId);
			repoChange.setRepoinfo(repo);
			return Msg.success().add("result", repoChange);
		}else{
			return Msg.fail().add("result", "出入库信息不存在");
		}
	}
	
	//查看仓库的零件名称
	@RequestMapping("listLJ")
	@ResponseBody
	public Msg listLJ(String key,String yl10){
		List<Repoinfo> selectLj = repoinfoService.selectLj(key, yl10);
		return Msg.success().add("result", selectLj);
	}
}
