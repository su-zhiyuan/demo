package com.qppi.crud.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MyTools;
import com.qppi.crud.utils.VersionConfigUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Controller
@RequestMapping("duanxin")
public class DuanxinController {
	
	@Autowired
	private CacheManager cacheManager;
	
	//短信API产品名称
    static final String product="Dysmsapi";
    //短信API产品域名
    static final String domain="dysmsapi.aliyuncs.com";
	
	//发送短信验证码
	@ResponseBody
	@RequestMapping("send")
	public SendSmsResponse send(String telphone){
		return sendSms(telphone);
	}
	
	//验证短信验证码
	@ResponseBody
	@RequestMapping("yanzheng")
	public Msg yanzheng(String code,String telphone){
		Element e = null;
		try {
			Cache cache= cacheManager.getCache("data-cache");
			e =cache.get(telphone);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(!e.getObjectValue().equals(code)){
				return Msg.fail().add("result", "验证失败");
		}else {
			return Msg.success().add("result", "验证成功");
		}
	}
   
	public SendSmsResponse sendSms(String telphone){
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		ResourceBundle resourceBundle = VersionConfigUtil.getResources("dbconfig");
		if (resourceBundle != null) {
			for (String set : resourceBundle.keySet()) {
				String value = resourceBundle.getString(set);
				map.put(set, value);
			}
		}
		
		//密匙
	    final String accessKeyId = (String) map.get("accessKeyId");
	    final String accessKeySecret = (String) map.get("accessKeySecret");
        //设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient
        IClientProfile profile=DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou",product, domain);
		} catch (ClientException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        IAcsClient acsClient=new DefaultAcsClient(profile);
        
        //组装请求对象
        SendSmsRequest request=new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //待发送的手机号
        request.setPhoneNumbers(telphone);
        //短信签名
//        request.setSignName("闹闹科技");
        request.setSignName((String)map.get("signName"));
        System.out.println((String)map.get("signName"));
        //短信模板ID
//        request.setTemplateCode("SMS_149095202");
        request.setTemplateCode((String)map.get("templateCode"));
        System.out.println((String)map.get("templateCode"));
        //验证码
        String codetemp = MyTools.getR();
        System.out.println("code:"+codetemp);
        /*
         * 可选:模板中的变量替换JSON串,
         * 如模板内容为"亲爱的${name},您的验证码为${code}"时,
         * 此处的值为{"name":"Tom","code":"1454"}
         *   \  反斜杠为转义字符，使得输出双引号
         */
        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\""+codetemp+"\"}");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("1454");
        SendSmsResponse response = null;
		try {
			response = acsClient.getAcsResponse(request);
		} catch (ServerException e1) {
			e1.printStackTrace();
		} catch (ClientException e1) {
			e1.printStackTrace();
		}
        Cache cache = cacheManager.getCache("data-cache");
        if(response.getCode() != null && response.getCode().equals("OK")) {
            //请求成功
    		Element e = new Element(telphone, codetemp);
    		cache.put(e);
            System.out.println("发送成功！");
        }else {
            System.out.println("发送失败！");
        }
        return response;
    }
   
}
	

