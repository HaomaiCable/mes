
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
import com.hmmes.bean.CpBean;
import com.hmmes.bean.CpgxmxBean;
import com.hmmes.model.CpModel;
import com.hmmes.model.CpgxmxModel;
import com.hmmes.service.CpService;
import com.hmmes.service.CpgxmxService;
import com.hmmes.utils.HtmlUtil;

 
@Controller
@RequestMapping("/cpgxmxManage") 
public class CpgxmxAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(CpgxmxAction.class);
	
	// Servrice start
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private CpService<CpBean> cpService; 
	
	@Autowired
	private CpgxmxService<CpgxmxBean> cpgxmxService;
	

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/cpgxmx")
	public ModelAndView  list(CpModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("business/cpgxmxManage",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(CpModel model,HttpServletResponse response) throws Exception{
		List<CpBean> dataList = cpService.queryByList(model);
		List<CpBean> result = new ArrayList<CpBean>();
		// 封装VO集合
		for (Object ele : dataList)
		{
			CpBean st = (CpBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山-role-Json"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonMap);

	
	}
	@RequestMapping("/dataListForCpId")   //根据产品ID，查询产品工序明细显示DataGrid 
	public void  dataListForCpId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<CpgxmxBean> dataList = cpgxmxService.queryListByCpId(id);
		List<CpgxmxBean> result = new ArrayList<CpgxmxBean>();

		for (Object ele : dataList)
		{
			CpgxmxBean st = (CpgxmxBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山：XsddAction--dataListchangeForDdid"+jsonStr);
        context.put("gxmx",jsonStr);
		context.put(SUCCESS, true);	
	    HtmlUtil.writerJson(response, context);
        //response.setCharacterEncoding("UTF-8");
		//response.getWriter().print(jsonStr);
	
		return ;
	}
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(CpBean bean,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			cpService.add(bean);
		}else{
			cpService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object> ();
		CpBean bean  = cpService.queryById(id);
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
		cpService.deleteBean(id);
		sendSuccessMessage(response, "删除成功");
	}

    //不重复的型号
	@RequestMapping("/getUniXh")
	public void getUniXh(HttpServletResponse response) throws Exception{
	
		List<CpBean> dataList = cpService.queryByAll();
	
	    List<String> xh1 = new ArrayList();
		for (Object ele : dataList)
		{
			CpBean st = (CpBean)ele;
			xh1.add(st.getCpxh());
		}
		

		//删除重复的
		List<String> unixh1=new ArrayList();
		unixh1=removeDuplicate(xh1);
		Collections.sort(unixh1);
		List<JsonBean> unixh = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<unixh1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(unixh1.get(i));
            id++;
			unixh.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(unixh);
		String jsonStr=jsonArr.toString();
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	}	
    //不重复的规格
	@RequestMapping("/getUniGg")
	public void getUniGg(HttpServletResponse response) throws Exception{
	
		List<CpBean> dataList = cpService.queryByAll();
	
	    List<String> gg1= new ArrayList();
		for (Object ele : dataList)
		{
			CpBean st = (CpBean)ele;
			gg1.add(st.getCpgg());
		}
		

		//删除重复的
		List<String> unigg1=new ArrayList();
		unigg1=removeDuplicate(gg1);
		Collections.sort(unigg1);
		List<JsonBean> unigg = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<unigg1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(unigg1.get(i));
            id++;
			unigg.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(unigg);
		String jsonStr=jsonArr.toString();
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	}	
    //不重复的电压
	@RequestMapping("/getUniDy")
	public void getUniDy(HttpServletResponse response) throws Exception{
	
		List<CpBean> dataList = cpService.queryByAll();
	
	    List<String> dy1= new ArrayList();
		for (Object ele : dataList)
		{
			CpBean st = (CpBean)ele;
			dy1.add(st.getCpdy());
		}
		

		//删除重复的
		List<String> unidy1=new ArrayList();
		unidy1=removeDuplicate(dy1);
		Collections.sort(unidy1);
		List<JsonBean> unidy = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<unidy1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(unidy1.get(i));
            id++;
			unidy.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(unidy);
		String jsonStr=jsonArr.toString();
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	}
   //不重复的类别
	@RequestMapping("/getUniLb")
	public void getUniLb(HttpServletResponse response) throws Exception{
	
		List<CpBean> dataList = cpService.queryByAll();
	
	    List<String> lb1= new ArrayList();
		for (Object ele : dataList)
		{
			CpBean st = (CpBean)ele;
			lb1.add(st.getCplb());
		}
		

		//删除重复的
		List<String> unilb1=new ArrayList();
		unilb1=removeDuplicate(lb1);
		Collections.sort(unilb1);
		List<JsonBean> unilb = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<unilb1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(unilb1.get(i));
            id++;
			unilb.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(unilb);
		String jsonStr=jsonArr.toString();
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	}
    /**
	 * 返回不重复的List元素
	 * @param List 
	 * @return 不重复的List
	 */
	public List<String> removeDuplicate(List<String> list)
	{
       for ( int i = 0 ; i < list.size() - 1 ; i ++ ) 
		   {
               for ( int j = list.size() - 1 ; j > i; j -- )
				   {
                         if (list.get(j).equals(list.get(i))) 

						  { 
						  
						      list.remove(j);
                           }
                    }
           }

		   return list;
    }	


}

