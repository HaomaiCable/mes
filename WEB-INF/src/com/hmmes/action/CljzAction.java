
package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections; 
//import java.util.Date;

import net.sf.json.JSONObject;
//import net.sf.json.JSON;
import org.json.JSONArray;
import com.alibaba.fastjson.JSON;  
//import com.alibaba.fastjson.JSONObject;  
import com.alibaba.fastjson.annotation.JSONField;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hmmes.bean.JsonBean;
import com.hmmes.bean.ClcpBean;
import com.hmmes.bean.ClcpCljzBean;
import com.hmmes.model.ClcpModel;
import com.hmmes.model.ClcpCljzModel;
import com.hmmes.service.ClcpService;
import com.hmmes.service.ClcpCljzService;
import com.hmmes.utils.HtmlUtil;

 
@Controller
@RequestMapping("/cljzManage") 
public class CljzAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(CljzAction.class);
	
	// Servrice start
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private ClcpService<ClcpBean> clcpService; 
	
	@Autowired
	private ClcpCljzService<ClcpCljzBean> clcpCljzService;
	

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/cljz")
	public ModelAndView  list(ClcpModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("business/cljzManage",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(ClcpModel model,HttpServletResponse response) throws Exception{
		List<ClcpBean> dataList = clcpService.queryByList(model);
		List<ClcpBean> result = new ArrayList<ClcpBean>();
		// 封装VO集合
		for (Object ele : dataList)
		{
			ClcpBean st = (ClcpBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山-role-Json"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonMap);
	}

    @RequestMapping("/dataListForCpId")   //根据产品ID，查询材料净重显示DataGrid 
	public void  dataListForCpId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<ClcpCljzBean> dataList = clcpCljzService.queryListByCpId(id);
		List<ClcpCljzBean> result = new ArrayList<ClcpCljzBean>();

		for (Object ele : dataList)
		{
			ClcpCljzBean st = (ClcpCljzBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山：XsddAction--dataListchangeForDdid"+jsonStr);
        context.put("cljzmx",jsonStr);
		context.put(SUCCESS, true);	
	    HtmlUtil.writerJson(response, context);
   
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
	public void save(ClcpBean bean,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			clcpService.add(bean);
		}else{
			clcpService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}

	//保存材料净重的修改
    @RequestMapping("/saveCljz")
	public void  saveCljz(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	     request.setCharacterEncoding("UTF-8");  
        //获取编辑数据 这里获取到的是json字符串  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 String cpid  = request.getParameter("cpid");
//System.out.println("高绪山-getCljzForCpId--cpid"+cpid);
//System.out.println("高绪山：XsddAction--savewghb:ddid="+ddid);
//System.out.println("高绪山：XsddAction--savewghb:insert"+inserted);
//System.out.println("高绪山：XsddAction--savewghb:delete"+deleted);
//System.out.println("高绪山：XsddAction--savewghb:update"+updated);


		 
         Integer row=0;
         if(deleted != null){  
             //把json字符串转换成对象  

             List<ClcpCljzBean> listDeleted = new ArrayList<ClcpCljzBean>();	
             listDeleted = JSON.parseArray(deleted, ClcpCljzBean.class);  
             //TODO 下面就可以根据转换后的对象进行相应的操作了 
			 Integer [] ids=new Integer[listDeleted.size()];

             row=0;
		     for (Object ele : listDeleted )
		     {
				 ClcpCljzBean bean = (ClcpCljzBean)ele;	  

                  Integer id1=bean.getId();
                  ids[row]=id1;
				  row++;
			 } 
			 clcpCljzService.delete(ids);
         }  
 
         if(inserted != null){  
             //把json字符串转换成对象  
             List<ClcpCljzBean> listInserted = new ArrayList<ClcpCljzBean>();	
	
			 listInserted = JSON.parseArray(inserted, ClcpCljzBean.class);  
	
			 for (Object ele : listInserted )
		     {
				 ClcpCljzBean bean = (ClcpCljzBean)ele;	
				 bean.setCpid(Integer.valueOf(cpid));
				 clcpCljzService.add(bean);
		     }
         }  

         if(updated != null){  
            //把json字符串转换成对象  
		    List<ClcpCljzBean> listUpdated = new ArrayList<ClcpCljzBean>();	
            listUpdated = JSON.parseArray(updated, ClcpCljzBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 ClcpCljzBean bean = (ClcpCljzBean)ele;	
 				 clcpCljzService.update(bean);
		     }
          }
	    sendSuccessMessage(response, "保存成功~");
		return ;
	}	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object> ();
		ClcpBean bean  = clcpService.queryById(id);
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
		clcpService.deleteBean(id);
		sendSuccessMessage(response, "删除成功");
	}

    //材料净重DataGrid
	@RequestMapping("/getCljzForCpId")
	public void getCljzForCpId(Integer id,HttpServletResponse response) throws Exception{

		JSONObject context = new JSONObject();
		ClcpBean bean  = clcpService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		List<ClcpCljzBean> dataList = clcpCljzService.queryListByCpId(id);
		List<ClcpCljzBean> result = new ArrayList<ClcpCljzBean>();	
		for (Object ele : dataList)
		{
			ClcpCljzBean elebean = (ClcpCljzBean)ele;
			result.add(elebean);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山-XsddAction--getWghb-jsonArr"+jsonStr);
		context.put("json",jsonStr);

		context.put(SUCCESS, true);	
		
//System.out.println("高绪山-XsddAction---getWghb-context"+context.toString());
		HtmlUtil.writerJson(response, context);

	}
    //不重复的型号
	@RequestMapping("/getUniXh")
	public void getUniXh(HttpServletResponse response) throws Exception{
	
		List<ClcpBean> dataList = clcpService.queryByAll();
	
	    List<String> xh1 = new ArrayList();
		for (Object ele : dataList)
		{
			ClcpBean st = (ClcpBean)ele;
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
	
		List<ClcpBean> dataList = clcpService.queryByAll();
	
	    List<String> gg1= new ArrayList();
		for (Object ele : dataList)
		{
			ClcpBean st = (ClcpBean)ele;
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
	
		List<ClcpBean> dataList = clcpService.queryByAll();
	
	    List<String> dy1= new ArrayList();
		for (Object ele : dataList)
		{
			ClcpBean st = (ClcpBean)ele;
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
   //不重复的材料名称
	@RequestMapping("/getUniCl")
	public void getUniCl(HttpServletResponse response) throws Exception{
	
		List<ClcpCljzBean> dataList = clcpCljzService.queryByAll();
	
	    List<String> cl1= new ArrayList();
		for (Object ele : dataList)
		{
			ClcpCljzBean st = (ClcpCljzBean)ele;
			cl1.add(st.getClmc());
		}
		

		//删除重复的
		List<String> unicl1=new ArrayList();
		unicl1=removeDuplicate(cl1);
		Collections.sort(unicl1);
		List<JsonBean> unicl = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<unicl1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(unicl1.get(i));
            id++;
			unicl.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(unicl);
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

