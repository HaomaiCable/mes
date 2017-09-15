
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

import com.hmmes.bean.SysMenu;
import com.hmmes.bean.SysRole;
import com.hmmes.bean.SysRoleRel;
import com.hmmes.bean.SysRoleRel.RelType;
import com.hmmes.model.SysRoleModel;
import com.hmmes.service.SysMenuService;
import com.hmmes.service.SysRoleRelService;
import com.hmmes.service.SysRoleService;
import com.hmmes.utils.HtmlUtil;
//import com.hmmes.utils.json.JsonDateValueProcessor;
 
@Controller
@RequestMapping("/sysRole") 
public class SysRoleAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(SysRoleAction.class);
	
	// Servrice start
	@Autowired(required=false) 
	private SysRoleService<SysRole> sysRoleService; 
	
	// Servrice start
	@Autowired(required=false) 
	private SysMenuService<SysMenu> sysMenuService; 
	@Autowired(required=false) 
	private SysRoleRelService<SysRoleRel> sysRoleRelService;
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/role")
	public ModelAndView  list(SysRoleModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("sys/sysRole",context); 
	}
	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(SysRoleModel model,HttpServletResponse response) throws Exception{
		List<SysRole> dataList = sysRoleService.queryByList(model);
		List<SysRole> result = new ArrayList<SysRole>();
		// 封装VO集合
		for (Object ele : dataList)
		{
			// 每个集合元素都是StockBean对象
			SysRole st = (SysRole)ele;
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
	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(SysRole bean,Integer[] menuIds,Integer[] btnIds,HttpServletResponse response) throws Exception{
		if(bean.getId() == null){
			sysRoleService.add(bean,menuIds,btnIds);
		}else{
			sysRoleService.update(bean,menuIds,btnIds);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object> ();
		SysRole bean  = sysRoleService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		//获取权限关联的菜单
		Integer[] menuIds = null;
		List<SysMenu> menuList =  sysMenuService.getMenuByRoleId(id);
		if(menuList != null){
			menuIds = new Integer[menuList.size()];
			int i = 0;
			for(SysMenu item : menuList){
				menuIds[i] = item.getId();
				i++;
			}
		}
		//获取权限下关联的按钮
		Integer[] btnIds = null;
		List<SysRoleRel>  btnList =sysRoleRelService.queryByRoleId(id, RelType.BTN.key);
		if(btnList != null){
			btnIds = new Integer[btnList.size()];
			int i = 0;
			for(SysRoleRel item : btnList){
				btnIds[i] = item.getObjId();
				i++;
			}
		}

		//将对象转成Map
		//JSONObject data = new JSONObject();
		Map<String,Object> data = BeanUtils.describe(bean);
		data.put("menuIds", menuIds);
		data.put("btnIds", btnIds);
		//context.put("id", bean.getId());
		//context.put("roleName", bean.getRoleName());
		//context.put("state", bean.getState());
		context.put("data", data);
		context.put(SUCCESS, true);
		//JSONObject jo = JSONObject.fromObject(context);
		//JSONArray jsonArr=new JSONArray.fromObject(context);
System.out.println("高绪山-Tree"+context.toString());		
		HtmlUtil.writerJson(response, context);

	

		/**	Map<String,Object> data = BeanUtils.describe(bean);
		data.put("menuIds", menuIds);
		data.put("btnIds", btnIds);
		context.put(SUCCESS, true);
		context.put("data", data);
		HtmlUtil.writerJson(response, context);*/
	}
	
	
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		sysRoleService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
	
	
	
	@RequestMapping("/loadRoleList")
	public void loadRoleList(HttpServletResponse response) throws Exception{
	    List<SysRole>  dataList = sysRoleService.queryAllList(); 
		//JSONObject jsonMap = new JSONObject();
		List<SysRole> result = new ArrayList<SysRole>();
		// 封装VO集合
		for (Object ele : dataList)
		{
			// 每个集合元素都是StockBean对象
			SysRole st = (SysRole)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);

		HtmlUtil.writerJson(response, jsonArr.toString());
	}



}

