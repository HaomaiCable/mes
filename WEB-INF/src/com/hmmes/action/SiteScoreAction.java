package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.hmmes.bean.SiteScore;
import com.hmmes.model.SiteScoreModel;
import com.hmmes.service.SiteScoreService;
import com.hmmes.bean.SiteScore;
import com.hmmes.model.SiteScoreModel;
 
@Controller
@RequestMapping("/sys/siteScore") 
public class SiteScoreAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(SiteScoreAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SiteScoreService<SiteScore> siteScoreService; 
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(SiteScoreModel model,HttpServletRequest request) throws Exception{
		//Map<String,Object>  context = getRootMap();
		JSONObject context = new JSONObject();	
		List<SiteScore> dataList = siteScoreService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("siteScore/list",context); 
	}
	

}
