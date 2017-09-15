
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

import com.hmmes.bean.YgBean;
import com.hmmes.model.YgModel;
import com.hmmes.service.YgService;
import com.hmmes.utils.HtmlUtil;
//import com.hmmes.utils.json.JsonDateValueProcessor;
 
@Controller
@RequestMapping("/ygManage") 
public class YgAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(YgAction.class);
	
	// Servrice start
	@Autowired(required=false) 
	private YgService<YgBean> YgService; 
	

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/yg")
	public ModelAndView  list(YgModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		//List<YgBean> dataList = YgService.queryByList(model);
		//设置页面数据
		//context.put("dataList", dataList);
		return forword("business/ygManage",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(YgModel model,HttpServletResponse response) throws Exception{
		List<YgBean> dataList = YgService.queryByList(model);
		List<YgBean> result = new ArrayList<YgBean>();
		// 封装VO集合
		for (Object ele : dataList)
		{
			// 每个集合元素都是StockBean对象
			YgBean st = (YgBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	

	
	}
	@RequestMapping("/getYgList")  //员工combobox 选择数据
	public void  ygList(YgModel model,HttpServletResponse response) throws Exception{
		List<YgBean> dataList = YgService.queryAllList();
		List<YgBean> result = new ArrayList<YgBean>();
		// 封装VO集合
		for (Object ele : dataList)
		{
			// 每个集合元素都是StockBean对象
			YgBean st = (YgBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
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
	public void save(YgBean bean,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			YgService.add(bean);
		}else{
			YgService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();

		YgBean bean  = YgService.queryById(id);
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
		YgService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	

}

