package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Artificial;
import com.qppi.crud.bean.Parity;
import com.qppi.crud.bean.Record;
import com.qppi.crud.dao.ArtificialMapper;
import com.qppi.crud.utils.GetRandem;


@Service
public class ArtificialService {
	@Autowired
	private ArtificialMapper artificialMapper;
	
	//智能查询历史记录
	public List<Record> selectRecord(Record record){
		List<Record> list = artificialMapper.selectRecord(record);
		if(list.size()>0) {
			return list;
		}
		return list;	
	}
	
	//查询智能回复
	public Artificial selectArtificial(Record record){
		Artificial artificial = new Artificial();
		artificial.setReply(record.getRecordContent());
		Artificial af = artificialMapper.selectArtificial(artificial);
		if(af != null){
			record.setRecordId(GetRandem.getDateR());
			record.setSendContent(af.getReplyContent());
			record.setState("1");
			artificialMapper.insertRecord(record);
			return af;
		} 
		return af;
	}
	
	//添加人工回复
	public boolean insertParity(Parity parity){
		try{
			artificialMapper.insertParity(parity);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//查询人工回复记录 
	public List<Parity> selectParity(Parity parity){
		List<Parity> list = artificialMapper.selectParity(parity);
		if(list.size()>0) {
			return list;
		}
		return list;	
	}
}
