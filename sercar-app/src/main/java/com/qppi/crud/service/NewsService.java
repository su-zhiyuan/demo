package com.qppi.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qppi.crud.bean.Chatecord;
import com.qppi.crud.bean.ChatecordExample;
import com.qppi.crud.bean.ChatecordExample.Criteria;
import com.qppi.crud.bean.Friends;
import com.qppi.crud.bean.FriendsExample;
import com.qppi.crud.bean.Messlist;
import com.qppi.crud.bean.MesslistExample;
import com.qppi.crud.dao.ChatecordMapper;
import com.qppi.crud.dao.FriendsMapper;
import com.qppi.crud.dao.MesslistMapper;



@Service
public class NewsService {

	@Autowired
	private MesslistMapper messlistMapper;
	@Autowired
	private FriendsMapper friendsMapper;
	@Autowired
	private ChatecordMapper chatecordMapper;
	
	
	//根据用户查询好友
	public List<Friends> selectFriends(Friends friends){
		int unread = 0;
		String createBy = friends.getCreateBy();
		FriendsExample example = new FriendsExample();
		com.qppi.crud.bean.FriendsExample.Criteria criteria = example.createCriteria();
		criteria.andCreateByEqualTo(createBy);
		List<Friends> list = friendsMapper.selectByExample(example);
		
		Chatecord chatEcord = new Chatecord();
		chatEcord.setCreateBy(createBy);
		ChatecordExample example2 = new ChatecordExample();
		Criteria criteria2 = example2.createCriteria();
		criteria2.andCreateByEqualTo(createBy);
		List<Chatecord> list2 = chatecordMapper.selectByExample(example2);
		for(int i=0;i<list.size();i++){
			for(int j=0;j<list2.size();j++){
				if(list.get(i).getFriends().equals(list2.get(j).getFriends())){
					if(list2.get(j).getState().equals("1")){
						unread = unread +1;
						String uread = String.valueOf(unread);
						list.get(i).setUnread(uread);
						
					}
					list.get(i).setCreateTime(list2.get(j).getCreateTime());
					list.get(i).setYl1(list2.get(j).getChatContent());
				}
			}
		}
		return list;
	}

	
	
	//查询单个好友聊天显示
	public List<Chatecord> chatEcord(Chatecord chatEcord,int page){
		String createBy = chatEcord.getCreateBy();
		String friends = chatEcord.getFriends();
		//接收人和发送人
		List<String> list = new ArrayList<String>();
		list.add(createBy);
		list.add(friends);
		
		//设置未读
		MesslistExample example = new MesslistExample();
		MesslistExample.Criteria criteria = example.createCriteria();
		criteria.andSendbyEqualTo(chatEcord.getFriends());
		criteria.andRecbyEqualTo(chatEcord.getCreateBy());
		Messlist ml = new Messlist();
		ml.setUnreadcount("0");
		messlistMapper.updateByExampleSelective(ml, example);
		
		//查询到所有的信息记录
		ChatecordExample example2 = new ChatecordExample();
		Criteria criteria2 = example2.createCriteria();
		criteria2.andCreateByIn(list);
		criteria2.andFriendsIn(list);
		example2.setPageSize(10);
		example2.setStartRow((page-1)*10);

		example2.setOrderByClause("CREATE_TIME 	DESC");
		List<Chatecord> list2 = chatecordMapper.selectByExample(example2);
		ArrayList<Chatecord> arrayList = new ArrayList<Chatecord>();
		if(list2.size()>0) {
			for(int i=0;i<list2.size();i++){
				Chatecord CH = list2.get(list2.size()-i-1);
				arrayList.add(CH);
			}
		}
		return arrayList;
	}
	
	//查询消息列表是否存在
	public List<Friends> selectNews(Friends friends){
		String createBy = friends.getCreateBy();
		String friends2 = friends.getFriends();
		FriendsExample example = new FriendsExample();
		FriendsExample.Criteria criteria = example.createCriteria();
		criteria.andCreateByEqualTo(createBy);
		criteria.andCreateByEqualTo(friends2);
		List<Friends> list = friendsMapper.selectByExample(example);
		if(list.size()>0) {
			return list;
		}
		return null;	
	}
		
	//添加好友聊天消息(主表)
	public boolean insertChatEcord(Chatecord chatEcord){
		try{
			chatecordMapper.insertSelective(chatEcord);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
