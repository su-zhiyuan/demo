package com.qppi.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Chatecord;
import com.qppi.crud.bean.Messlist;
import com.qppi.crud.bean.MesslistExample;
import com.qppi.crud.bean.UserInfo;
import com.qppi.crud.bean.UserInfoExample;
import com.qppi.crud.dao.MesslistMapper;
import com.qppi.crud.dao.UserInfoMapper;
import com.qppi.crud.utils.MyTools;

@Service
public class MesslistService {
	@Autowired
	private MesslistMapper messlistMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	//未读消息及消息内容显示
	public boolean record(Chatecord chatEcord){
		String send = chatEcord.getCreateBy();
		String rec = chatEcord.getFriends();
		try{
			MesslistExample example = new MesslistExample();
			MesslistExample.Criteria criteria = example.createCriteria();
			criteria.andSendbyEqualTo(send);
			criteria.andRecbyEqualTo(rec);
			List<Messlist> list = messlistMapper.selectByExample(example);
			if(list.size()>0){		//多条消息未读
				Messlist ml = list.get(0);
				ml.setUnreadcount(Integer.valueOf(ml.getUnreadcount())+1+"");
				ml.setEndmess(chatEcord.getChatContent());
				ml.setEndtime(MyTools.getTime());
				ml.setYl1(chatEcord.getNewsType());
				messlistMapper.updateByPrimaryKey(ml);
				return true;
			}else{					//单条消息未读
				Messlist ml = new Messlist();
				ml.setMlistId(MyTools.getDateR());
				ml.setSendby(send);
				ml.setRecby(rec);
				ml.setUnreadcount("1");
				ml.setEndmess(chatEcord.getChatContent());
				ml.setYl1(chatEcord.getNewsType());
				ml.setEndtime(MyTools.getTime());
				messlistMapper.insertSelective(ml);
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//查出所有发送和接收是我的记录
	public List<Messlist> listML(Messlist ml){
		MesslistExample example = new MesslistExample();
		MesslistExample.Criteria criteria = example.createCriteria();
		criteria.andSendbyEqualTo(ml.getSendby());							//发送是我的
		example.setOrderByClause("endtime DESC");
		List<Messlist> list1 = messlistMapper.selectByExample(example);
		
		//未读的list
		MesslistExample example2 = new MesslistExample();
		MesslistExample.Criteria criteria1 = example2.createCriteria();
		criteria1.andRecbyEqualTo(ml.getSendby());						//接收是我的
		example2.setOrderByClause("endtime DESC");
		List<Messlist> list2 = messlistMapper.selectByExample(example2);
		
		List<String> list = new ArrayList<String>();
		for (Messlist m : list1) {
			list.add(m.getRecby());
		}
		for (Messlist h : list2) {
			list.add(h.getSendby());
		}
		//去重
        List<String> listNew=new ArrayList<>();
        for (String str:list) {
            if(!listNew.contains(str)){
                listNew.add(str);
            }
        }
        
        List<Messlist> list5 = new ArrayList<Messlist>();
        
        for (String s : listNew) {
        	
        	UserInfoExample userexample = new UserInfoExample();
			UserInfoExample.Criteria usercriteria = userexample.createCriteria();
			usercriteria.andUsernameEqualTo(s);
			List<UserInfo> uList = userInfoMapper.selectByExampleWithBLOBs(userexample);
			if(uList.size()>0){
				UserInfo userInfo = uList.get(0);
				String name = userInfo.getName();
				String img = userInfo.getYl5();
				
	        	MesslistExample example1 = new MesslistExample();
	    		MesslistExample.Criteria criteria2 = example1.createCriteria();
	    		List<String> list3 = new ArrayList<String>();
	    		list3.add(s);
	    		list3.add(ml.getSendby());
	    		criteria2.andSendbyIn(list3);
	    		criteria2.andRecbyIn(list3);
	    		example1.setOrderByClause("endtime DESC");
	    		List<Messlist> list4 = messlistMapper.selectByExample(example1);
	    		if (list4!=null) {
					Messlist zx = list4.get(0); 		//最新消息
					zx.setName(name);
					zx.setYl2(img);
					if("图片".equals(zx.getYl1())){
						zx.setEndmess("[图片]");
					}
					list5.add(zx);
				}
			}
		}
		return list5;
		
	}
		
	public List<Messlist> test(){
		MesslistExample example = new MesslistExample();
		MesslistExample.Criteria criteria = example.createCriteria();
		criteria.andSendbyLike("%国%");
		return messlistMapper.selectByExample(example);
	}

}