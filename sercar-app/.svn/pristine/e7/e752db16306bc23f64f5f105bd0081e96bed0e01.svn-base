package com.qppi.crud.dao;

import java.util.List;

import com.qppi.crud.bean.Artificial;
import com.qppi.crud.bean.Parity;
import com.qppi.crud.bean.Record;


public interface ArtificialMapper {
	
	//智能查询历史记录
	List<Record> selectRecord(Record record);
	//查询智能回复
	Artificial selectArtificial(Artificial artificial);
	//添加智能回复
	int insertRecord(Record record);
	//添加人工回复
	int insertParity(Parity parity);
	//查询人工回复记录
	List<Parity> selectParity(Parity parity);
}
