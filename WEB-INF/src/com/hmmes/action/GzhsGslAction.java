
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

import com.hmmes.bean.GzhsGslBean;
import com.hmmes.model.GzhsGslModel;
import com.hmmes.service.GzhsGslService;
import com.hmmes.utils.HtmlUtil;
//import com.hmmes.utils.json.JsonDateValueProcessor;
 
@Controller
@RequestMapping("/gzhsGslManage") 
public class GzhsGslAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(GzhsGslAction.class);
	
	// Servrice start
	@Autowired(required=false) 
	private GzhsGslService<GzhsGslBean> gzhsGslService; 
	

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/gzhsGsl")
	public ModelAndView  list(GzhsGslModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		//List<GzhsGslBean> dataList = gzhsGslService.queryByList(model);
		//设置页面数据
		//context.put("dataList", dataList);
		return forword("business/gzhsGslManage",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(GzhsGslModel model,HttpServletResponse response) throws Exception{
		List<GzhsGslBean> dataList = gzhsGslService.queryByList(model);
		List<GzhsGslBean> result = new ArrayList<GzhsGslBean>();
		for (Object ele : dataList)
		{
			GzhsGslBean st = (GzhsGslBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	

	
	}
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(GzhsGslBean bean,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			gzhsGslService.add(bean);
		}else{
			gzhsGslService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();

		GzhsGslBean bean  = gzhsGslService.queryById(id);
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
		gzhsGslService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	

}

