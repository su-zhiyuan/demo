package com.qppi.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Applyjb;
import com.qppi.crud.bean.Leave;
import com.qppi.crud.bean.LeaveExample;
import com.qppi.crud.dao.LeaveMapper;

@Service
public class LeaveService {
	@Autowired
	private LeaveMapper leaveMapper;
	
	/**
	 * 查询列表 
	 * @param leave
	 * @return
	 */
	public List<Leave> list(Leave leave,int page){
		LeaveExample example = new LeaveExample();
		example.setOrderByClause("yl4 desc");
		example.setPageSize(10);
		example.setStartRow((page-1)*10);
		LeaveExample.Criteria criteria = example.createCriteria();
		String id = leave.getLeaveId();
		String companyId = leave.getYl10();
		criteria.andYl10EqualTo(companyId);
		if(null != id && !"".equals(id)){
			criteria.andLeaveIdEqualTo(id);
		}
		String type = leave.getType();
		if(null != type && !"".equals(type)){
			criteria.andTypeEqualTo(type);
		}
		String starttime = leave.getStarttime();
		if(null != starttime && !"".equals(starttime)){
			criteria.andStarttimeEqualTo(starttime);
		}
		String endtime = leave.getEndtime();
		if(null != endtime && !"".equals(endtime)){
			criteria.andEndtimeEqualTo(endtime);
		}
		String cause = leave.getCause();
		if(null != cause && !"".equals(cause)){
			criteria.andCauseEqualTo(cause);
		}
		List<Leave> list = leaveMapper.selectByExample(example);
		return list;
	}
	
	/**
	 * 获取对象
	 * @param leave
	 * @return
	 */
	public Leave get(Leave leave){
		return leaveMapper.selectByPrimaryKey(leave.getLeaveId());
	}
	
	/**
	 * 增加对象
	 * @param leave
	 * @return
	 */
	public boolean add(Leave leave){
		try{
			leaveMapper.insertSelective(leave);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 更新对象
	 * @param leave
	 * @return
	 */
	public boolean update(Leave leave){
		try{
			leaveMapper.updateByPrimaryKeySelective(leave);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除对象
	 * @param leave
	 * @return
	 */
	public boolean delite(Leave leave){
		try{
			leaveMapper.deleteByPrimaryKey(leave.getLeaveId());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 查询个人请假
	 */
	public List<Leave> select(int page, String username) {
		LeaveExample example = new LeaveExample();
		example.setOrderByClause("yl4 desc");
		example.setPageSize(10);
		example.setStartRow((page-1)*10);
		LeaveExample.Criteria criteria = example.createCriteria();
		criteria.andYl3EqualTo(username);
		return leaveMapper.selectByExample(example);
	}

	public List<Leave> selectDSP(Leave leave) {
		LeaveExample example = new LeaveExample();
		LeaveExample.Criteria criteria = example.createCriteria();
		String companyId = leave.getYl10();
		if(companyId != null && !"".equals(companyId)){
			criteria.andYl10EqualTo(companyId);
		}
		String status = leave.getYl1();
		if(status != null && !"".equals(status)){
			criteria.andYl1EqualTo(status);
		}
		List<Leave> list = leaveMapper.selectByExample(example);
		return list;
	}
	
	
	
}
