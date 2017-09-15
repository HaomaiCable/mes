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


import com.hmmes.bean.GysdaBean;
import com.hmmes.bean.CgflBean;
import com.hmmes.bean.ZbwzBean;
import com.hmmes.model.GysdaModel;
import com.hmmes.service.GysdaService;
import com.hmmes.service.CgflService;
import com.hmmes.service.ZbwzService;
import com.hmmes.utils.HtmlUtil;
//import com.hmmes.bean.BaseBean.DELETED;
import com.hmmes.bean.BaseBean.STATE;

 
@Controller
@RequestMapping("/gysdaManage") 
public class GysdaAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(GysdaAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private GysdaService<GysdaBean> gysdaService; 

	@Autowired
	private CgflService<CgflBean> cgflService;	
	@Autowired
	private ZbwzService<ZbwzBean> zbwzService;	

	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/gysda") 
	public ModelAndView  list(GysdaModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("cgpt/gysdaManage",context); 
	}
	
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(GysdaModel model,HttpServletResponse response) throws Exception{
		List<GysdaBean> dataList = gysdaService.queryByList(model);
		for(GysdaBean bean: dataList){
			String cgfls = getCgflStr(bean.getId());
			bean.setCgfls(cgfls);
			//String zbwzs = getZbwzStr(bean.getId());
			//bean.setZbwzs(zbwzs);
		}

		//设置页面数据
		//Map<String,Object> jsonMap = new HashMap<String,Object>();
		JSONObject jsonMap = new JSONObject();	
		jsonMap.put("total",model.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}
	
	@RequestMapping("/gysListJson")  //供应商combobox 选择数据
	public void  gysListJson(HttpServletResponse response) throws Exception{
		List<GysdaBean> dataList = gysdaService.queryByAll();
		JSONArray jsonArr= new JSONArray(dataList);
		HtmlUtil.writerJson(response, jsonArr.toString());
		//HtmlUtil.writerJson(response, dataList);
	}
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(GysdaBean bean,Integer[] cgflIds,Integer[] zbwzIds,HttpServletResponse response) throws Exception{
		//Map<String,Object>  context = new HashMap<String,Object>();
		if(bean.getId() == null){
			gysdaService.add(bean);
		}else{
			gysdaService.update(bean);
		}
//System.out.println("高绪山--cgflIds"+cgflIds.toString());
//System.out.println("高绪山--zbwzIds"+zbwzIds.toString());
       //if (cgflIds!=null && cgflIds.length>0)
       //{
		   gysdaService.addCgflRel(cgflIds, bean.getId());
       //}
       //if (zbwzIds!=null && zbwzIds.length>0)
	   //{
		   gysdaService.addZbwzRel(zbwzIds, bean.getId());
	   //}
		sendSuccessMessage(response, "保存成功~");
	}
	 
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		//Map<String,Object>  context = new HashMap();
		JSONObject context = new JSONObject();
		GysdaBean bean  = gysdaService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		List<CgflBean> cgfls= cgflService.queryByGysId(bean.getId());
		if(cgfls != null && !cgfls.isEmpty()){
			int[] cgflIds = new int[cgfls.size()];
			for(int i=0;i< cgfls.size() ;i++){
				cgflIds[i] = cgfls.get(i).getId();
			}
			bean.setCgflIds(cgflIds);
		}
		List<ZbwzBean> zbwzs= zbwzService.queryByGysId(bean.getId());
		if(zbwzs != null && !zbwzs.isEmpty()){
			int[] zbwzIds = new int[zbwzs.size()];
			for(int i=0;i< zbwzs.size() ;i++){
				zbwzIds[i] = zbwzs.get(i).getId();
			}
			bean.setZbwzIds(zbwzIds);
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		gysdaService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

	private String getCgflStr(Integer gysId) throws Exception{
		List<CgflBean> cgfls= cgflService.queryByGysId(gysId);
		if(cgfls == null || cgfls.isEmpty()){
			return null;
		}
		StringBuffer str = new StringBuffer();
		int i=1;
		for(CgflBean cgfl : cgfls){
			str.append(cgfl.getName());
			if(i < cgfls.size()){
				str.append(",");
			}
			i++;
		}
		return str.toString();
	}

	private String getZbwzStr(Integer gysId) throws Exception{
		List<ZbwzBean> zbwzs= zbwzService.queryByGysId(gysId);
		if(zbwzs == null || zbwzs.isEmpty()){
			return null;
		}
		StringBuffer str = new StringBuffer();
		int i=1;
		for(ZbwzBean zbwz : zbwzs){
			str.append(zbwz.getName());
			if(i < zbwzs.size()){
				str.append(",");
			}
			i++;
		}
		return str.toString();
	}
}
