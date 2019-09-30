package com.qppi.crud.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;

public class FieldErrorUtil {
	
	public static Map<String,Object> error(List<FieldError> fieldErrors){
		Map<String, Object> m = new HashMap<String,Object>();
		for(FieldError fe : fieldErrors){
			m.put(fe.getField(), fe.getDefaultMessage());
		}
		return m;
	}
	
}