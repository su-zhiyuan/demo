package com.qppi.crud.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qppi.crud.bean.SysUser;
import com.qppi.crud.controller.BaseController;
import com.qppi.crud.utils.Msg;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
//import net.sf.json.JSONObject;
import net.sf.json.JSONObject;

public class LoginFilter implements HandlerInterceptor {

	@Autowired
	private CacheManager cacheManager;
	
	/**
	 * 该方法将在请求处理之前进行调用，只有该方法返回true，才会继续执行后续的Interceptor和Controller，
	 * 当返回值为true 时就会继续调用下一个Interceptor的preHandle方法，
	 * 如果已经是最后一个Interceptor的时候就会是调用当前请求的Controller方法； 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String token = request.getParameter("token");
		System.err.println(token);
		Cache cache = cacheManager.getCache("data-cache");
		Element e = cache.get(token);
		
		if(e == null){
			BaseController.outJson(JSONObject.fromObject(Msg.fail3().add("result", "当前没有登录，请登录!")).toString(), response);
			return false;
		}else{
			
			 SysUser user = (SysUser) e.getObjectValue();
			  String userId = user.getUserId();
			  Element eq = cache.get(userId);
			  String str = eq.getValue().toString();
			  if(token.equals(str)){
				  System.out.println("success");
				  return true;
			  }else{
				  BaseController.outJson(JSONObject.fromObject(Msg.fail3().add("result", "重复登录")).toString(), response);
				 System.out.println("faluse");
				  return false;
			  }
		}
		
		
//		if(e==null){
//			BaseController.outJson(JSONObject.fromObject(Msg.fail().add("result", "当前没有登录，请登录!")).toString(), response);
//			return false;
//		}else{
//			if(e.getObjectValue()!=null){
//				SysUser sysUser=(SysUser) e.getObjectValue();
//				if(sysUser==null){
//					BaseController.outJson(JSONObject.fromObject(Msg.fail().add("result", "session过期，请重新登录!")).toString(), response);
//					return false;
//				}
				
//			}
//			return false;
//		}
	}

	/**
	 * 该方法将在请求处理之后，DispatcherServlet进行视图返回渲染之前进行调用，
	 * 可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行，
	 * 该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。用于进行资源清理。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}