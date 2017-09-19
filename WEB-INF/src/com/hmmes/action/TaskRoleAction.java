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


import com.hmmes.bean.TaskRole;
import com.hmmes.bean.SysUser;
import com.hmmes.model.TaskRoleModel;
import com.hmmes.service.TaskRoleService;
import com.hmmes.service.SysUserService;
import com.hmmes.utils.HtmlUtil;
//import com.hmmes.bean.BaseBean.DELETED;
import com.hmmes.bean.BaseBean.STATE;

 
@Controller
@RequestMapping("/taskRoleManage") 
public class TaskRoleAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(TaskRoleAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private TaskRoleService<TaskRole> taskRoleService; 

	@Autowired
	private SysUserService<SysUser> sysUserService; 


	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/taskRole") 
	public ModelAndView  list(TaskRoleModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("business/taskRoleManage",context); 
	}
	
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(TaskRoleModel model,HttpServletResponse response) throws Exception{
		List<TaskRole> dataList = taskRoleService.queryByList(model);
		for(TaskRole bean: dataList){
			String roleStrs = getRoleStrs(bean.getId());
			bean.setRoleStrs(roleStrs);
		}

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
	public void save(TaskRole bean,Integer[] roleIds,HttpServletResponse response) throws Exception{
		//Map<String,Object>  context = new HashMap<String,Object>();
		
		if(bean.getId() == null){

			taskRoleService.add(bean);
		}else{
			taskRoleService.update(bean);
		}
		SysUser userbean  = sysUserService.queryById(bean.getUserId());
		bean.setUserName(userbean.getNickName());
		taskRoleService.update(bean);
        taskRoleService.addTaskRoleRel(roleIds, bean.getId());
		sendSuccessMessage(response, "保存成功~");
	}
	 
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		//Map<String,Object>  context = new HashMap();
System.out.println("高绪山-id"+id);
		JSONObject context = new JSONObject();
		TaskRole bean  = taskRoleService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		List<TaskRole> users= taskRoleService.queryByGlzId(id);
		if(users != null && !users.isEmpty()){
			int[] roleIds = new int[users.size()];
			for(int i=0;i< users.size() ;i++){
				roleIds[i] =users.get(i)==null?0:users.get(i).getId();
System.out.println("高绪山-roleIds[i]"+roleIds[i]);
			}
			bean.setRoleIds(roleIds);
		}
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		taskRoleService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

	private String getRoleStrs(Integer roleId) throws Exception{
		List<TaskRole> users= taskRoleService.queryByGlzId(roleId);
		if(users == null || users.isEmpty()){
			return null;
		}
		StringBuffer str = new StringBuffer();
		int i=1;
		for(TaskRole user : users){
			str.append(user.getNickName());
			if(i < users.size()){
				str.append(",");
			}
			i++;
		}
//System.out.println("高绪山-SysUser"+str.toString());
        return str.toString();

   }

}
