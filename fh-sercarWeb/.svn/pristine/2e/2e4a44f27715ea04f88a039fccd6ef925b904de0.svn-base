package com.fh.util.my;

import net.sf.json.JSONObject;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 扫描车牌
 * @author 97558
 *
 */
public class CarimgToTxt {

    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "31ZnMO63ODgUn5MeS7XbeG6y";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "E6oD8qEGPNGSXCyFZO0KBtj7zkvUIoa6";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.out.println("result:" + result);
            /*JSONObject jsonObject = new JSONObject(result);*/
            JSONObject jsonObject=JSONObject.fromObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }
    
    /**
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author: 
     * @CreateTime: 
     * @return
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
    
    public static String getImageStr(InputStream inputStream) {
        byte[] data = null;
        try {
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
    
    public static String searchCarNum(InputStream inputStream) throws IOException{
    	String access_token = getAuth();
    	String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate?access_token="+access_token;
    	String str64 = getImageStr(inputStream);
    	
    	Connection conn = Jsoup.connect(url);
    	conn.data("image",str64);
    	conn.data("multi_detect","false");
    	Document doc = conn.ignoreContentType(true).post();
    	
    	String body=doc.getElementsByTag("body").toString(); 
    	String bodyStr=body.substring(6,body.length()-7);
    	String words_result = null;
    	try{
    		words_result = JSONObject.fromObject(bodyStr).get("words_result").toString();
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    	String carNum=JSONObject.fromObject(words_result).get("number").toString();
    	return carNum;
    }
    
    public static void main(String[] args) throws IOException {
    	String access_token = getAuth();
    	String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/license_plate?access_token="+access_token;
    	String path = "C:/Users/97558/Desktop/1.png";
    	String str64 = getImageStr(path);
    	
    	Connection conn = Jsoup.connect(url);
    	conn.data("image",str64);
    	conn.data("multi_detect","false");
    	Document doc = conn.ignoreContentType(true).post();
    	System.out.println(doc);
	}

}