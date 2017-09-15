package com.hmmes.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.DateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;
//import org.springframework.web.bind.ServletRequestDataBinder.InitBinder;

import com.hmmes.edit.MyEditor;
import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.URLUtils;

public class BaseAction{
	
	public final static String SUCCESS ="success";  
	
	public final static String MSG ="msg";  
	
	
	public final static String DATA ="data";  
	
	public final static String LOGOUT_FLAG = "logoutFlag";  
	
	
   @InitBinder  
   protected void initBinder(WebDataBinder binder) {  
		 //binder.registerCustomEditor(java.sql.Date.class, new CustomDateEditor(
         //       new SimpleDateFormat("yyyy-MM-dd"), true));  
		 //binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(
         //       new SimpleDateFormat("yyyy-MM-dd"), true));  
		 //binder.registerCustomEditor(Date.class, new CustomDateEditor(
         //       new SimpleDateFormat("yyyy-MM-dd"), true));  
		 //binder.registerCustomEditor(java.sql.Timestamp.class, new CustomDateEditor(
         //    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));  
		  binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), true));  
	      binder.registerCustomEditor(int.class,new MyEditor()); 


    /**@InitBinder  
    protected void initBinder(HttpServletRequest request,  
          ServletRequestDataBinder binder) throws Exception {   
         DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");  
         CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);  
         binder.registerCustomEditor(Date.class, dateEditor);
         binder.registerCustomEditor(java.sql.Timestamp.class, new CustomDateEditor(
             new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));  		 
         binder.registerCustomEditor(int.class,new MyEditor()); 
         super.initBinder(request, binder);   
        */
   }  
	 
	 /**
	  * 获取IP地址
	  * @param request
	  * @return
	  */
	 public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	 
	 /**
	  * 所有ActionMap 统一从这里获取
	  * @return
	  */
	public Map<String,Object> getRootMap(){
		Map<String,Object> rootMap = new HashMap<String, Object>();
		//添加url到 Map中
//System.out.println("高绪山getRootMap=("+URLUtils.getUrlMap()+")");
		rootMap.putAll(URLUtils.getUrlMap());
		return rootMap;
	}
	
	public ModelAndView forword(String viewName,Map context){
		return new ModelAndView(viewName,context); 
	}
	
	public ModelAndView error(String errMsg){
		return new ModelAndView("error"); 
	}
	
	/**
	 *
	 * 提示成功信息
	 *
	 * @param message
	 *
	 */
	public void sendSuccessMessage(HttpServletResponse response,  String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, message);
//System.out.println("高绪山sendSuccessMessage:"+result.toString());
		HtmlUtil.writerJson(response, result);
	}

	/**
	 *
	 * 提示失败信息
	 *
	 * @param message
	 *
	 */
	public void sendFailureMessage(HttpServletResponse response,String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, false);
		result.put(MSG, message);
//System.out.println("高绪山sendFailureMessage:"+result.toString());
		HtmlUtil.writerJson(response, result);
	}
}
