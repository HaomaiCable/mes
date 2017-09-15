
package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
//import java.util.Date;

import net.sf.json.JSONObject;
import org.json.JSONArray;
//import net.sf.json.JsonConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hmmes.bean.YwyBean;
import com.hmmes.model.YwyModel;
import com.hmmes.service.YwyService;
import com.hmmes.utils.HtmlUtil;
//import com.hmmes.utils.json.JsonDateValueProcessor;
 
@Controller
@RequestMapping("/ywyManage") 
public class YwyAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(YwyAction.class);
	
	// Servrice start
	@Autowired(required=false) 
	private YwyService<YwyBean> YwyService; 
	

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/ywy")
	public ModelAndView  list(YwyModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		//List<YwyBean> dataList = YwyService.queryByList(model);
		//设置页面数据
		//context.put("dataList", dataList);
		return forword("business/ywyManage",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(YwyModel model,HttpServletResponse response) throws Exception{
		List<YwyBean> dataList = YwyService.queryByList(model);
		List<YwyBean> result = new ArrayList<YwyBean>();
		// 封装VO集合
		for (Object ele : dataList)
		{
			// 每个集合元素都是StockBean对象
			YwyBean st = (YwyBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
//System.out.println("高绪山-role-Array"+jsonArr.toString());

		//JSONObject jsonMap = new JSONObject();
		//Map<String,Object> jsonMap = new HashMap<String,Object>();
		//设置页面数据
		//jsonMap.put("total",model.getPager().getRowCount());
		//jsonMap.put("rows", result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山-role-Json"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonMap);

	
	}
	@RequestMapping("/getYwyList")  //业务员combobox 选择数据
	public void  ywyList(YwyModel model,HttpServletResponse response) throws Exception{
		List<YwyBean> dataList = YwyService.queryAllList();
		List<YwyBean> result = new ArrayList<YwyBean>();
		// 封装VO集合
		for (Object ele : dataList)
		{
			// 每个集合元素都是StockBean对象
			YwyBean st = (YwyBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
//System.out.println("高绪山-业务员-List"+jsonArr.toString());
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonArr.toString());
	

	
	}
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(YwyBean bean,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			YwyService.add(bean);
		}else{
			YwyService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object> ();
		YwyBean bean  = YwyService.queryById(id);
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
		YwyService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	

}

