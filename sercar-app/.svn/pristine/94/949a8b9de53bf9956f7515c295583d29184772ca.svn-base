package com.qppi.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qppi.crud.bean.Contacts;
import com.qppi.crud.service.ContactsService;
import com.qppi.crud.utils.Msg;

@Controller
@RequestMapping("contacts")
public class ContactsController {

	@Autowired
	private ContactsService contactsService;
	
	@RequestMapping("select")
	@ResponseBody
	public Msg Select(Contacts contacts){
		Contacts user = contactsService.selectByTel(contacts.getContactTel());
		if(user == null){
			return Msg.fail().add("result", "不存在联系人");
		}
		return Msg.success().add("result", user);
	}
}
