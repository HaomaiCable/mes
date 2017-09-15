package com.hmmes.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.json.JSONArray;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hmmes.bean.SysMenu;
import com.hmmes.bean.SysMenuBtn;
import com.hmmes.bean.TreeNode;
import com.hmmes.bean.BaseBean.DELETED;
import com.hmmes.model.SysMenuModel;
import com.hmmes.service.SysMenuBtnService;
import com.hmmes.service.SysMenuService;
import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.TreeUtil;

import net.sf.json.JSONObject;

 
@Controller
@RequestMapping("/sysMenu") 
public class SysMenuAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(SysMenuAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SysMenuService<SysMenu> sysMenuService; 
	
	@Autowired
	private SysMenuBtnService<SysMenuBtn> sysMenuBtnService;
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/menu")
	public ModelAndView  menu(SysMenuModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		model.setDeleted(DELETED.NO.key);
		List<SysMenu> dataList = sysMenuService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("sys/sysMenu",context); //view/sys/sysMenu 
	}
	
	/**
	 * 顶级菜单 json 
	 * @param menuId 此菜单id不查询，可以为空
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/rootMenuJson") 
	public void  rootMenu(Integer menuId,HttpServletResponse response) throws Exception{
		List<SysMenu> dataList = sysMenuService.getRootMenu(menuId);
		if(dataList==null){
			dataList = new ArrayList<SysMenu>();
		}
		HtmlUtil.writerJson(response, dataList);
	}
	
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList")   //DataGrid url
	public void  dataList(SysMenuModel model,HttpServletResponse response) throws Exception{
		List<SysMenu> dataList = sysMenuService.queryByList(model);
		List<SysMenu> result = new ArrayList<SysMenu>();	
				// 封装VO集合
		for (Object ele : dataList)
		{
			// 每个集合元素都是StockBean对象
			SysMenu st = (SysMenu)ele;
			result.add(st);
		}
		//设置页面数据

		//JSONObject jsonMap = new JSONObject();
		//jsonMap.put("total",model.getPager().getRowCount());
		//jsonMap.put("rows", dataList);
		//HtmlUtil.writerJson(response, jsonMap);
		
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";

        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//设置页面数据
	}
	

	
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void save(SysMenu bean,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(true){
			new Exception(" Test Error");
		}
		//设置菜单按钮数据
		List<SysMenuBtn> btns=getReqBtns(request);
	    bean.setBtns(btns);  

		if(bean.getId() == null){
			bean.setDeleted(DELETED.NO.key);
			sysMenuService.addBean(bean);
		}else{
			sysMenuService.updateBean(bean);
		}
		sendSuccessMessage(response, "保存成功~");

	}
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		SysMenu bean  = sysMenuService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		List<SysMenuBtn> btns = sysMenuBtnService.queryByMenuid(id);
		bean.setBtns(btns);
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		if(id != null && id.length > 0){
			sysMenuService.delete(id);
			sendSuccessMessage(response, "删除成功");
		}else{
			sendFailureMessage(response, "未选中记录");
		}
	}
	
	
	@RequestMapping("/getMenuTree")
	public void getMenuTree(Integer id,HttpServletResponse response) throws Exception{
		List<TreeNode> menuTree = treeMenu();
		List<TreeNode> result = new ArrayList<TreeNode>();
		// 封装VO集合
		for (Object ele : menuTree)
		{
			// 每个集合元素都是TreeNoden对象
			TreeNode st = (TreeNode)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
//System.out.println("高绪山menutree"+jsonArr.toString());
		HtmlUtil.writerJson(response, jsonArr);

		//HtmlUtil.writerJson(response, menuTree);
	}
	
	/**
	 * 构建树形菜单
	 * @return
	 */
	public List<TreeNode> treeMenu(){
		List<SysMenu> rootMenus = sysMenuService.getRootMenu(null);//根节点
		List<SysMenu> childMenus = sysMenuService.getChildMenu();//子节点
		List<SysMenuBtn> childBtns = sysMenuBtnService.queryByAll();
		TreeUtil util = new TreeUtil(rootMenus,childMenus,childBtns);
		return util.getTreeNode();
	}
	
	/**
	 * 获取请求的菜单按钮数据
	 * @param request
	 * @return
	 */
	public List<SysMenuBtn> getReqBtns(HttpServletRequest request){
		List<SysMenuBtn> btnList= new ArrayList<SysMenuBtn>();
		String[] btnId  = request.getParameterValues("btnId");
		String[] btnName  = request.getParameterValues("btnName");
		String[] btnType  = request.getParameterValues("btnType");
		String[] actionUrls  = request.getParameterValues("actionUrls");
		String[] deleteFlag  = request.getParameterValues("deleteFlag");

		if ( btnId==null)
		{
             SysMenuBtn btn = new SysMenuBtn();
             btn.setBtnName("null");
			 btnList.add(btn);
			 return btnList;
		}		
//System.out.println("高绪山Action："+ btnId.length);
		for (int i = 0; i < btnId.length; i++) {
			if(StringUtils.isNotBlank(btnName[i]) && StringUtils.isNotBlank(btnType[i])){
				SysMenuBtn btn = new SysMenuBtn();
				if(StringUtils.isNotBlank(btnId[i]) && NumberUtils.isNumber(btnId[i])){
					btn.setId(NumberUtils.toInt(btnId[i]));
				}
				btn.setBtnName(btnName[i]);
				btn.setBtnType(btnType[i]);
				btn.setActionUrls(actionUrls[i]);
				btn.setDeleteFlag(deleteFlag[i]);
				btnList.add(btn);
			}
		}
		return btnList;
	}
}
