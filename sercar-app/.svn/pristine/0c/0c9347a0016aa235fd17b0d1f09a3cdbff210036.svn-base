package com.qppi.crud.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import net.sf.json.JSONObject;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MySelfUtil {
	
	//汉语中数字大写
	private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

	//汉语中货币单位大写，这样的设计类似于占位符
	private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰" };
	
	//特殊字符：负
	private static final String CN_NEGATIVE = "负";
	
	//金额的精度，默认值为2
	private static final int MONEY_PRECISION = 2;
	
	//特殊字符：零元整
	private static final String CN_ZEOR_FULL = "零元";

	//访问接口
	public static String doHttpGet(String url) {
		// 需要访问的接口路径
		//String url = "http://127.0.0.1:8085/sercar-app/wx/getOrderId?code=" + code;
		// 配置请求信息（请求时间）
		RequestConfig rc = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
		// 获取使用DefaultHttpClient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 返回结果
		String result = null;
		try {
			if (url != null) {
				// 创建HttpGet对象，将URL通过构造方法传入HttpGet对象
				HttpGet httpget = new HttpGet(url);
				// 将配置好请求信息附加到http请求中
				httpget.setConfig(rc);
				// 执行DefaultHttpClient对象的execute方法发送GET请求，通过CloseableHttpResponse接口的实例，可以获取服务器返回的信息
				CloseableHttpResponse response = httpclient.execute(httpget);
				try {
					// 得到返回对象
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						// 获取返回结果
						result = EntityUtils.toString(entity);
					}
				} finally {
					// 关闭到客户端的连接
					response.close();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭http请求
				httpclient.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	// 图片转化成base64字符串
	public static String GetImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}
	  
	
	// base64字符串转化成图片
	public static boolean GenerateImage(String imgStr, String imgFilePath) throws Exception { 
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
 
		// Base64解码,对字节数组字符串进行Base64解码并生成图片
		byte[] b = decoder.decodeBuffer(imgStr);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}
		// 生成jpeg图片
		// String imgFilePath = "c://temp_kjbl_001_ab_010.jpg";//新生成的图片
		 File dest = new File(imgFilePath);
		 if (!dest.getParentFile().exists()) {
			 dest.getParentFile().mkdirs();// 新建文件夹
		 }
		OutputStream out = new FileOutputStream(imgFilePath);
		out.write(b);
		out.flush();
		out.close();
		return true;
	}
	
	//读取文件中的内容
	public static String getProperties(String filePath,String key){
		 Properties prop = new Properties();
         String value = null;
         try {
             // 通过输入缓冲流进行读取配置文件
             InputStream InputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
             // 加载输入流
        	 
             prop.load(InputStream);
             // 根据关键字获取value值
             value = prop.getProperty(key);
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
         return value;
	}
	
	//比较两集合的相同
	public static boolean bijiao(List<String> a, List<String> b){
		if (a.size() != b.size()){
			return false;
		}

		for (Object object : a) {
			if (!b.contains(object)){
				return false;
			}
		}
		return true;
	}
	
	//集合去重
	public static List<String> removeDuplicate(List<String> list)  {       
		for(int i = 0; i < list.size()-1; i++){       
			for(int j = list.size()-1; j > i; j--){       
				if(list.get(j).equals(list.get(i))){       
					list.remove(j);       
	            }        
			}        
		}        
	  	return list;       
	}
	
	//把输入的金额转换为汉语中人民币的大写
	public static Map<String, String> number2CNMontrayUnit(String money) {
		Map<String, String> map = new HashMap<String, String>();
		BigDecimal numberOfMoney = new BigDecimal(money);
		StringBuffer sb = new StringBuffer();
		
		// 返回-1：表示该数小于0 0：表示该数等于0 1：表示该数大于0
		int signum = numberOfMoney.signum();
		// 零元的情况
		if (signum == 0) {
			map.put("fen", "/");
			map.put("jiao", "/");
			map.put("yuan", "零");
			map.put("shi", "/");
			map.put("bai", "/");
			map.put("qian", "/");
			map.put("wan", "/");
			map.put("shiwan", "/");
			return map;
		}
		
		// 这里会进行金额的四舍五入
		long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0, 4).abs().longValue();
		// 得到小数点后两位值
		
		// 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
		long scale = number % 100;
		long fen = scale % 10; 
		long jiao = scale / 10;
		String fenDw = CN_UPPER_MONETRAY_UNIT[0];
		String jiaoDw = CN_UPPER_MONETRAY_UNIT[1];
		if(fen == 0 && jiao ==0){
			map.put(toHanyuPinyin(fenDw), "/");
			map.put(toHanyuPinyin(jiaoDw), "/");
		}else if(fen == 0 && jiao !=0){
			map.put(toHanyuPinyin(fenDw), "/");
			map.put(toHanyuPinyin(jiaoDw), CN_UPPER_NUMBER[(int) jiao]);
		}else{
			map.put(toHanyuPinyin(fenDw), CN_UPPER_NUMBER[(int) fen]);
			map.put(toHanyuPinyin(jiaoDw), CN_UPPER_NUMBER[(int) jiao]);
		}
		
		int flag = 0;
		long num = 0;
		
		for(int i=8 ; i>1; i--){
			num = (long) (number / Math.pow(10, i));
			if(flag == 0){
				if(num != 0){
					flag = 1;
					if(i != 8 && i != 7){
						String dw = CN_UPPER_MONETRAY_UNIT[i];
						map.put(toHanyuPinyin(dw), CN_UPPER_NUMBER[(int) num]);
					}else{
						String dw = CN_UPPER_MONETRAY_UNIT[i] + "万";
						map.put(toHanyuPinyin(dw), CN_UPPER_NUMBER[(int) num]);
					}
				}else {
					if(i != 8 && i != 7){
						String dw = CN_UPPER_MONETRAY_UNIT[i];
						map.put(toHanyuPinyin(dw), "/");
					}else{
						String dw = CN_UPPER_MONETRAY_UNIT[i] + "万";
						map.put(toHanyuPinyin(dw), "/");
					}
				}
			}else{
				if(i != 8 && i != 7){
					String dw = CN_UPPER_MONETRAY_UNIT[i];
					map.put(toHanyuPinyin(dw), CN_UPPER_NUMBER[(int) num]);
				}else{
					String dw = CN_UPPER_MONETRAY_UNIT[i] + "万";
					map.put(toHanyuPinyin(dw), CN_UPPER_NUMBER[(int) num]);
				}
			}
			
			number = (long) (number % Math.pow(10, i));
		}
		
		return map;
	}

	
	public static String toHanyuPinyin(String ChineseLanguage) {
		char[] cl_chars = ChineseLanguage.trim().toCharArray();
		String hanyupinyin = "";
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		try {
			for (int i = 0; i < cl_chars.length; i++) {
				if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")) {//
					// 如果字符是中文,则将中文转为汉语拼音
					hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
				} else {// 如果字符不是中文,则不转换
					hanyupinyin += cl_chars[i];
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			System.out.println("字符不能转成汉语拼音");
		}
		return hanyupinyin;
	}
	//添加日志信息
	public static void addRiZhi(String createBy,String Xiangmuming,String requestcontent, String responsecontent, String status, String type, String url){
		Map<String, String> parems = new HashMap<String, String>();
		parems.put("createBy", createBy);
		parems.put("Xiangmuming", Xiangmuming);
		parems.put("requestcontent", requestcontent);
		parems.put("responsecontent", responsecontent);
		parems.put("status", status);
		parems.put("type", type);
		//新增日志
		Connection con = Jsoup.connect(url);
		con.data(parems);
		try{
			Document doc = con.ignoreContentType(true).post();
			JSONObject jsons = JSONObject.fromObject(doc.getElementsByTag("body").get(0).text());
			int code = (int) jsons.get("code");
			if(code==100){
				System.out.println("添加成功");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("添加失败");
		}
	}
	
	//版本更新
	public static String updateVersion(String versionnum,String url,String Xiangmuming){
		Connection con = Jsoup.connect(url);						//版本更新路径
		con.data("versionNum", versionnum);							//版本号
		con.data("yl1",Xiangmuming);									//项目名称	
		try {
			Document doc = con.ignoreContentType(true).post();
			JSONObject json = JSONObject.fromObject(doc.getElementsByTag("body").get(0).text());
			JSONObject jsonObject = json.getJSONObject("extend");
			JSONObject obj = jsonObject.getJSONObject("result");
			if(obj.isNullObject()){
				return null;
			}else{
				return obj.toString();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("失败");
			return null;
		}
	}
	
	public static Date getBeforeOrAfterDate(Date date, int num) {
        Calendar calendar = Calendar.getInstance();//获取日历
        calendar.setTime(date);//当date的值是当前时间，则可以不用写这段代码。
        calendar.add(Calendar.DATE, num);
        Date d = calendar.getTime();//把日历转换为Date
        return d;
    }
	
	
	//判断一个数是否是double类型
	public static boolean isDouble(String str){
		try{
			if(str != null && !"".equals(str)){
				Double.parseDouble(str);
		      	return true;
			}
		}catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	//utf-8转为汉字
	public static String convert(String src) {
		boolean contains = src.contains("%");
		if(contains){
			src = src.replaceAll("%", "");
			byte[] bytes = new byte[src.length() / 2];
			for (int i = 0; i < bytes.length; i++){
				bytes[i] = (byte) Integer.parseInt(src.substring(2 * i, 2 * i + 2),16);
			}
			try {
				return new String(bytes, "utf-8");
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}else{
			return src;
		}
	}
	
	//amr格式转 mp3格式
	public static void changeToMp3(String sourcePath, String targetPath) {  
        File source = new File(sourcePath);
        File target = new File(targetPath);  
        AudioAttributes audio = new AudioAttributes();  
        Encoder encoder = new Encoder();  
  
        audio.setCodec("libmp3lame");  
        EncodingAttributes attrs = new EncodingAttributes();  
        attrs.setFormat("mp3");  
        attrs.setAudioAttributes(audio);  
  
        try {  
            encoder.encode(source, target, attrs);  
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        } catch (InputFormatException e) {  
            e.printStackTrace();  
        } catch (EncoderException e) {  
            e.printStackTrace();  
        }  
	}
}
