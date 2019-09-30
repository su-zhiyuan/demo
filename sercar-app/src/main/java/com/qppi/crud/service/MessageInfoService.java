package com.qppi.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.MessageInfo;
import com.qppi.crud.bean.MessageInfoExample;
import com.qppi.crud.dao.MessageInfoMapper;

@Service
public class MessageInfoService {
	
	@Autowired
	private MessageInfoMapper messageInfoMapper;
	
	public List<MessageInfo> listMessage(MessageInfo messageInfo){
		MessageInfoExample example = new MessageInfoExample();
		MessageInfoExample.Criteria criteria = example.createCriteria();
		criteria.andReceUserEqualTo(messageInfo.getReceUser());
		return messageInfoMapper.selectByExample(example);
	}

}
