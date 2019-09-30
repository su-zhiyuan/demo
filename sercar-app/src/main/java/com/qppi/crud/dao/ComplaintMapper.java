package com.qppi.crud.dao;

import java.util.List;

import com.qppi.crud.bean.Complaint;


public interface ComplaintMapper {
	
	//投诉建议查询
	List<Complaint> selectComplaint(Complaint complaint);
	
	//投诉建议查询
	List<Complaint> selectComplaintId(Complaint complaint);
		
	//投诉建议添加
	int insertComplaint(Complaint complaint);

	//根据微信查询投诉建议
	List<Complaint> selectComplaintWx(Complaint complaint);
	
}
