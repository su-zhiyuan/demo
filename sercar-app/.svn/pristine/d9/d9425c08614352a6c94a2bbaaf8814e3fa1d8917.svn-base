package com.qppi.crud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Chatecord;
import com.qppi.crud.bean.Friends;
import com.qppi.crud.bean.Messlist;
import com.qppi.crud.service.MesslistService;
import com.qppi.crud.service.NewsService;
import com.qppi.crud.utils.GetRandem;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MyTools;
import com.vdurmont.emoji.EmojiParser;


@Controller
@RequestMapping("news")
public class NewsController extends BaseController {
	@Autowired
	private NewsService newsService;	//消息
	@Autowired
	private MesslistService messlistService;
	
	
	/**
	 * 查出所有发送和接收是我的记录
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("selectFriends")
	@ResponseBody
	public Msg selectFriends(Messlist ml, HttpServletRequest request){
		return Msg.success().add("result", messlistService.listML(ml));
	}
	
	/**
	 * 查询单个好友聊天记录
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("chatEcord")
	@ResponseBody
	public Msg chatEcord(Chatecord chatEcord, HttpServletRequest request,int page){
		if(chatEcord.getCreateBy() == null || chatEcord.getCreateBy().equals("")){
			return Msg.success().add("result","createBy不能为空");
		}else{	
			List<Chatecord> list = newsService.chatEcord(chatEcord,page);
			if(list.size() == 0){
				return Msg.success().add("result", "没有更多数据");
			}
			for (Chatecord chat : list) {
				String string = EmojiParser.parseToUnicode(chat.getChatContent());
				chat.setChatContent(string);
			}
			return Msg.success().add("result", list);
		}
	}
	
	/**
	 * 查询消息列表是否存在
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("selectNews")
	@ResponseBody
	public Msg selectNews(Friends friends, HttpServletRequest request){
		if(friends.getCreateBy() == null || friends.getCreateBy().equals("") ){
			return Msg.success().add("result","createBy不能为空");
		}else{			
			List<Friends> list  = newsService.selectNews(friends);
			return Msg.success().add("result",list);
		}
	}
	
	/**
	 * 添加聊天消息
	 * @param jiuyuan
	 * @return
	 */
	@RequestMapping("insertChatEcord")
	@ResponseBody
	public Msg insertChatEcord(Chatecord chatEcord, HttpServletRequest request){
		if(chatEcord.getCreateBy() == null || chatEcord.getCreateBy().equals("")){
			return Msg.success().add("result","createBy不能为空");
		}else{
			String string = EmojiParser.parseToAliases(chatEcord.getChatContent());
			chatEcord.setChatContent(string);
			chatEcord.setChatrecordId(GetRandem.getDateR());
			chatEcord.setCreateTime(MyTools.getTime());
			if(newsService.insertChatEcord(chatEcord)){
				if(messlistService.record(chatEcord)){
					return Msg.success().add("result", "success"); 
				}
			}
			return Msg.fail().add("result", "fail");
		}
	}
	
	
	@RequestMapping("test")
	@ResponseBody
	public Msg test(){
		return Msg.success().add("result", messlistService.test());
	}
	
	public static void main(String[] args) {
		String currdate = "2018-08-16";
		String currdate1 = "2018-08-15";
		System.out.println(currdate.compareTo(currdate1));
	}
}
