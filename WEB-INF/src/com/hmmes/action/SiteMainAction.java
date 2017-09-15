package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hmmes.bean.SiteMain;
import com.hmmes.bean.SiteType;
import com.hmmes.bean.BaseBean.DELETED;
import com.hmmes.model.SiteMainModel;
import com.hmmes.service.SiteMainService;
import com.hmmes.service.SiteTypeService;
import com.hmmes.utils.HtmlUtil;

@Controller
@RequestMapping("/siteMain")
public class SiteMainAction extends BaseAction{
	
	@Autowired
	private SiteMainService<SiteMain> siteMainService;
	
	@Autowired
	private SiteTypeService<SiteType> siteTypeService;
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list") 
	public ModelAndView  list(SiteMainModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("siteMain/siteMain",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  datalist(SiteMainModel model,HttpServletResponse response) throws Exception{
		model.setDeleted(0);
		List<SiteMain> dataList = siteMainService.queryByList(model);
		for(SiteMain bean: dataList){
			String types = getTypeStr(bean.getId());
			bean.setTypes(types);
		}

		JSONObject jsonMap = new JSONObject();	
		//Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("total",model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
//System.out.println("高绪山：SiteMain"+jsonMap.toString());
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
	public void save(SiteMain bean,Integer[] typeIds,HttpServletResponse response) throws Exception{
		//Map<String,Object>  context = new HashMap<String,Object>();
		JSONObject context = new JSONObject();	
		bean.setDeleted(DELETED.NO.key);
		if(bean.getId() == null){
			siteMainService.add(bean);
		}else{
			siteMainService.update(bean);
		}
		siteMainService.addTypeRel(typeIds, bean.getId());
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		//Map<String,Object>  context = new HashMap();
		JSONObject context = new JSONObject();	
		SiteMain bean  = siteMainService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		List<SiteType> types= siteTypeService.queryBySiteId(bean.getId());
		if(types != null && !types.isEmpty()){
			int[] typeIds = new int[types.size()];
			for(int i=0;i< types.size() ;i++){
				typeIds[i] = types.get(i).getId();
			}
			bean.setTypeIds(typeIds);
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		siteMainService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	
	private String getTypeStr(Integer siteId) throws Exception{
		List<SiteType> types= siteTypeService.queryBySiteId(siteId);
		if(types == null || types.isEmpty()){
			return null;
		}
		StringBuffer str = new StringBuffer();
		int i=1;
		for(SiteType type : types){
			str.append(type.getName());
			if(i < types.size()){
				str.append(",");
			}
			i++;
		}
		return str.toString();
		
	}
	
	
}
