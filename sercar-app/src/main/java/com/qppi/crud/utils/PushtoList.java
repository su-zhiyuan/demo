package com.qppi.crud.utils;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class PushtoList {

	static String appId = "gAMCDtTfmf74MO8YQ9pt87"; // 官网注册获取的appid
	static String appkey = "DXZ7r1z9ey6dyALu4Gf6B7"; // 官网注册获取的appkey
	static String master = "7MW7NhsYBX92GZFenH1ibA"; // 官网注册获取的master
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";
//	static String host = "http://sdk.open.baidu.com";

	/*public static void main(String[] args) {
		List<String> userList = new ArrayList<String>();
		userList.add("832980bb8811530ffbd717685dc565fa");
		pushMessage("好不好", "好呀好", "setTransmissionContent", userList);
	}*/

	// 安卓端推送 List<String> userList 用户手机中的个推唯一编码
	public static String pushMessage(String tittle, String content,/* String other,*/ List<String> userList) {
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appkey, master);

		NotificationTemplate tt = new NotificationTemplate();
		tt.setTitle(tittle);
		tt.setText(content);
		/*tt.setTransmissionContent(other);*/
		tt.setAppId(appId);
		tt.setAppkey(appkey);
		tt.setLogo("icon.png");
		tt.setLogoUrl("");
		tt.setIsRing(true);
		tt.setIsVibrate(true);
		tt.setIsClearable(true);
		tt.setTransmissionType(1);

		ListMessage message = new ListMessage();
		message.setData(tt);

		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(7*24 * 1000 * 3600);

		// 配置推送目标
		List<Target> targets = new ArrayList<Target>();

		for (int i = 0; i < userList.size(); i++) {
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(userList.get(i));
			targets.add(target);
		}

		// 获取taskID
		String taskId = push.getContentId(message);
		// 使用taskID对目标进行推送
		IPushResult ret = push.pushMessageToList(taskId, targets);
		// 打印服务器返回信息
		return ret.getResponse().toString();
	}

	/**
	 * 根据省份或者城市推送 安卓版本
	 * 
	 * @param tt
	 * @param city
	 */
	public static void pushMessage(String tittle, String content, String other, String city) {
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appkey, master);

		NotificationTemplate tt = new NotificationTemplate();
		tt.setTitle(tittle);
		tt.setText(content);
		tt.setTransmissionContent(other);
		tt.setAppId(appId);
		tt.setAppkey(appkey);
		tt.setLogo("icon.png");
		tt.setLogoUrl("");
		tt.setIsRing(true);
		tt.setIsVibrate(true);
		tt.setIsClearable(true);
		tt.setTransmissionType(1);

		AppMessage message = new AppMessage();

		message.setData(tt);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(7*24 * 1000 * 3600);
		// 设置推送目标条件过滤
		List<String> appIdList = new ArrayList<String>();
		List<String> provinceList = new ArrayList<String>();
		appIdList.add(appId);
		// 设置省份
		provinceList.add(city);
		message.setAppIdList(appIdList);
		message.setProvinceList(provinceList);
		IPushResult ret = push.pushMessageToApp(message);
		System.out.println(ret.getResponse().toString());
	}

	// IOS端推送 List<String> userList 用户手机中的个推唯一编码
	public static void apnpush(String tittle, String content, String other, List<String> userList) {
		IGtPush push = new IGtPush(host, appkey, master);

		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		template.setTransmissionContent("透传内容");
		template.setTransmissionType(2);
		APNPayload payload = new APNPayload();
		payload.setBadge(1);
		payload.setContentAvailable(1);
		payload.setSound("default");
		// payload.setCategory("$由客户端定义");
		payload.setAlertMsg(new APNPayload.SimpleAlertMsg(content));
		// 字典模式使用下者
		// payload.setAlertMsg(getDictionaryAlertMsg());
		template.setAPNInfo(payload);

		ListMessage message = new ListMessage();
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(7*24 * 1000 * 3600);
		String contentId = push.getAPNContentId(appId, message);
		System.out.println(contentId);
		List<String> dtl = new ArrayList<String>();
		dtl = userList;
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IPushResult ret = push.pushAPNMessageToList(appId, contentId, dtl);
		System.out.println(ret.getResponse());
	}

	// ios端，根据城市编码推送
	public static void apnpushCity(String tittle, String content, String other, String city) {
		IGtPush push = new IGtPush(host, appkey, master);

		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		template.setTransmissionContent("透传内容");
		template.setTransmissionType(2);
		APNPayload payload = new APNPayload();
		payload.setBadge(1);
		payload.setContentAvailable(1);
		payload.setSound("default");
		// payload.setCategory("$由客户端定义");
		payload.setAlertMsg(new APNPayload.SimpleAlertMsg(content));
		// 字典模式使用下者
		// payload.setAlertMsg(getDictionaryAlertMsg());
		template.setAPNInfo(payload);

		AppMessage message = new AppMessage();

		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(7*24 * 1000 * 3600);
		// 设置推送目标条件过滤
		List<String> appIdList = new ArrayList<String>();
		List<String> provinceList = new ArrayList<String>();
		appIdList.add(appId);
		// 设置省份
		provinceList.add(city);
		message.setAppIdList(appIdList);
		message.setProvinceList(provinceList);

		IPushResult ret = push.pushMessageToApp(message);
		System.out.println(ret.getResponse().toString());

	}
}