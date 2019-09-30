package com.qppi.crud.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;

public class VersionConfigUtil {
	
	private static Map<String,ResourceBundle> configMap = new HashMap<String, ResourceBundle>();
	
	private  static synchronized void loadResource(String propertyFileName){
		ResourceBundle resource = ResourceBundle.getBundle(propertyFileName);
		configMap.put(propertyFileName, resource);
	}
	
	/**
	 * 在指定文件中获取指定的配置属性
	 * @param key
	 * @param propertyFileName
	 * @return
	 */
	public static String getValue(String key,String propertyFileName) {
		ResourceBundle resource = configMap.get(propertyFileName);
		if(resource == null){
			loadResource(propertyFileName);
			resource = configMap.get(propertyFileName);
		}
		return resource.getString(key);
	}
	
	/**
	 * 在指定文件中获取所有属性集
	 * @param propertyFileName
	 * @return
	 */
	public static ResourceBundle getResources(String propertyFileName){
		ResourceBundle resource = configMap.get(propertyFileName);
		if(resource == null){
			loadResource(propertyFileName);
			resource = configMap.get(propertyFileName);
		}
		return resource;
	}
	
	public static void main(String[] args) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		ResourceBundle resourceBundle = VersionConfigUtil.getResources("dbconfig");
		if (resourceBundle != null) {
			for (String set : resourceBundle.keySet()) {
				String value = resourceBundle.getString(set);
				map.put(set, value);
			}
		}
		System.out.println(map.get("signName"));
	}
}
