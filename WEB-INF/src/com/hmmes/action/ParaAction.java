
package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections; 
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

import com.hmmes.bean.JsonBean;
import com.hmmes.bean.ParaBean;
import com.hmmes.model.ParaModel;
import com.hmmes.service.ParaService;
import com.hmmes.utils.HtmlUtil;

 
@Controller
@RequestMapping("/paraManage") 
public class ParaAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(ParaAction.class);
	
	// Servrice start
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private ParaService<ParaBean> paraService;
	

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/para")
	public ModelAndView  list(ParaModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("business/paraManage",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(ParaModel model,HttpServletResponse response) throws Exception{
		List<ParaBean> dataList = paraService.queryByList(model);
		List<ParaBean> result = new ArrayList<ParaBean>();
		for (Object ele : dataList)
		{
			ParaBean st = (ParaBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山-role-Json"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonMap);
	
	}

	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(ParaBean bean,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			paraService.add(bean);
		}else{
			paraService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object> ();
		ParaBean bean  = paraService.queryById(id);
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
		paraService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

 

}

