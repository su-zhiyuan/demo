package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Complaint;
import com.qppi.crud.dao.ComplaintMapper;


@Service
public class ComplaintService {
	@Autowired
	private ComplaintMapper complaintMapper;
	
	//投诉建议查询
	public List<Complaint> selectComplaint(Complaint complaint,int page){
		complaint.setPageSize(10);
		complaint.setStartRow((page-1)*10);
		List<Complaint> list = complaintMapper.selectComplaint(complaint);
		if(list.size()>0) {
			return list;
		}
		return null;
	}
	
	//投诉建议详情查询
	public List<Complaint> selectComplaintId(Complaint complaint){
		List<Complaint> list = complaintMapper.selectComplaintId(complaint);
		if(list.size()>0) {
			return list;
		}
		return null;
	}
	
	//投诉建议添加
	public boolean insertComplaint(Complaint complaint){
		try{
			complaintMapper.insertComplaint(complaint);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Complaint> selectByWx(String wxid) {
		Complaint complaint = new Complaint();
		complaint.setYl1(wxid);
		List<Complaint> list = complaintMapper.selectComplaintWx(complaint);
		return list;
	}
	
}
