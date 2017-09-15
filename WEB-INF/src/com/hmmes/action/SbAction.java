
package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


import net.sf.json.JSONObject;
import org.json.JSONArray;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hmmes.bean.SbBean;
import com.hmmes.bean.JtBean;
import com.hmmes.model.SbModel;
import com.hmmes.service.SbService;
import com.hmmes.service.JtService;
import com.hmmes.utils.HtmlUtil;

 
@Controller
@RequestMapping("/sbManage") 
public class SbAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(SbAction.class);
	
	// Servrice start
	@Autowired(required=false) 
	private SbService<SbBean> sbService; 
	@Autowired(required=false) 
	private JtService<JtBean> jtService; 
	

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/sb")
	public ModelAndView  list(SbModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		//List<SbBean> dataList = sbService.queryByList(model);
		//设置页面数据
		//context.put("dataList", dataList);
		return forword("business/sbManage",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(SbModel model,HttpServletResponse response) throws Exception{
		List<SbBean> dataList = sbService.queryByList(model);
		List<SbBean> result = new ArrayList<SbBean>();
		// 封装VO集合
		for (Object ele : dataList)
		{
			SbBean st = (SbBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";

        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	

	
	}

	@RequestMapping("/getSbList")  //设备combobox 选择数据
	public void  sbList(HttpServletResponse response) throws Exception{
		List<SbBean> dataList = sbService.queryAllList();
		List<SbBean> result = new ArrayList<SbBean>();
		// 封装VO集合
		for (Object ele : dataList)
		{
			// 每个集合元素都是StockBean对象
			SbBean st = (SbBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
//System.out.println("高绪山-XsddAction--dataListById-jsonStr"+jsonArr.toString());	
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonArr.toString());
	

	
	}	
	@RequestMapping("/getSbListForGd")  //设备combobox 选择数据
	public void  sbListForGd(Integer gdid,HttpServletResponse response) throws Exception{
		String gd="";
        switch (gdid)
        {
            case 1:
                gd="拔丝工段";
                break;
            case 2:
                gd="橡缆工段";
                break;
            case 3:
                gd="低压工段";
                break;
            case 4:
                gd="高压工段";
                break;
		    default:
				gd="";
			    break;
        }

		List<SbBean> dataList = sbService.queryAllList();
		List<SbBean> result = new ArrayList<SbBean>();
		for (Object ele : dataList)
		{
			SbBean sbbean = (SbBean)ele;
		    String jtmc="";
		    if (sbbean!=null )
		    {
			    jtmc=sbbean.getJt()==null?"":sbbean.getJt();
		    }
		    String gdmc="";
		    JtBean jtbean = jtService.queryByJtmc(jtmc);
		    if (jtbean!=null)
		    {
			    gdmc=jtbean.getGd()==null?"":jtbean.getGd();
		    }
		    if (gd.equals(gdmc.trim()))   //不是参数传递的工段,不显示

		   {
			    result.add(sbbean);
		   }
		}

		JSONArray jsonArr= new JSONArray(result);
//System.out.println("高绪山-XsddAction--dataListById-jsonStr"+jsonArr.toString());	
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
	public void save(SbBean bean,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			sbService.add(bean);
		}else{
			sbService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();

		SbBean bean  = sbService.queryById(id);
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
		sbService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	

}

