package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.hmmes.bean.SiteMain;
import com.hmmes.bean.SiteType;
import com.hmmes.model.SiteMainModel;
import com.hmmes.model.SiteTypeModel;
import com.hmmes.service.SiteTypeService;
import com.hmmes.utils.HtmlUtil;
import com.hmmes.bean.SiteType;
import com.hmmes.bean.BaseBean.DELETED;
import com.hmmes.bean.BaseBean.STATE;
import com.hmmes.model.SiteTypeModel;
 
@Controller
@RequestMapping("/siteType") 
public class SiteTypeAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(SiteTypeAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SiteTypeService<SiteType> siteTypeService; 
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(SiteTypeModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("siteMain/siteType",context); 
	}
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/typeListJson") 
	public void  typeListJson(HttpServletResponse response) throws Exception{
		List<SiteType> dataList = siteTypeService.queryByAll();
		JSONArray jsonArr= new JSONArray(dataList);
		HtmlUtil.writerJson(response, jsonArr.toString());
		//HtmlUtil.writerJson(response, dataList);
	}
	
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(SiteTypeModel model,HttpServletResponse response) throws Exception{
		List<SiteType> dataList = siteTypeService.queryByList(model);
		//设置页面数据
		//Map<String,Object> jsonMap = new HashMap<String,Object>();
		JSONObject jsonMap = new JSONObject();	
		jsonMap.put("total",model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	

	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(SiteType bean,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap<String,Object>();
		if(bean.getId() == null){
			siteTypeService.add(bean);
		}else{
			siteTypeService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = new HashMap();
		SiteType bean  = siteTypeService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		siteTypeService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	
}
