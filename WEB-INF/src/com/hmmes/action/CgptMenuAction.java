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

import com.hmmes.bean.CgptMenu;
import com.hmmes.bean.CgptMenuBtn;
import com.hmmes.bean.TreeNode;
import com.hmmes.bean.BaseBean.DELETED;
import com.hmmes.model.CgptMenuModel;
import com.hmmes.service.CgptMenuBtnService;
import com.hmmes.service.CgptMenuService;
import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.CgptTreeUtil;

import net.sf.json.JSONObject;

 
@Controller
@RequestMapping("/cgptMenu") 
public class CgptMenuAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(CgptMenuAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private CgptMenuService<CgptMenu> cgptMenuService; 
	
	@Autowired
	private CgptMenuBtnService<CgptMenuBtn> cgptMenuBtnService;
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/menu")
	public ModelAndView  menu(CgptMenuModel model,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		model.setDeleted(DELETED.NO.key);
		List<CgptMenu> dataList = cgptMenuService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("cgpt/cgptMenu",context); //view/cgpt/cgptMenu 
	}
	
	/**
	 * 顶级菜单 json 
	 * @param menuId 此菜单id不查询，可以为空
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/rootMenuJson") 
	public void  rootMenu(Integer menuId,HttpServletResponse response) throws Exception{
		List<CgptMenu> dataList = cgptMenuService.getRootMenu(menuId);
		if(dataList==null){
			dataList = new ArrayList<CgptMenu>();
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
	public void  dataList(CgptMenuModel model,HttpServletResponse response) throws Exception{
		List<CgptMenu> dataList = cgptMenuService.queryByList(model);
		List<CgptMenu> result = new ArrayList<CgptMenu>();	
		// 封装VO集合
		for (Object ele : dataList)
		{
			// 每个集合元素都是StockBean对象
			CgptMenu st = (CgptMenu)ele;
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
	public void save(CgptMenu bean,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(true){
			new Exception(" Test Error");
		}
		//设置菜单按钮数据
		List<CgptMenuBtn> btns=getReqBtns(request);
	    bean.setBtns(btns);  

		if(bean.getId() == null){
			bean.setDeleted(DELETED.NO.key);
			cgptMenuService.addBean(bean);
		}else{
			cgptMenuService.updateBean(bean);
		}
		sendSuccessMessage(response, "保存成功~");

	}
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		CgptMenu bean  = cgptMenuService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		List<CgptMenuBtn> btns = cgptMenuBtnService.queryByMenuid(id);
		bean.setBtns(btns);
		context.put(SUCCESS, true);
		context.put("data", bean);
		HtmlUtil.writerJson(response, context);
	}
	
	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		if(id != null && id.length > 0){
			cgptMenuService.delete(id);
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
		List<CgptMenu> rootMenus = cgptMenuService.getRootMenu(null);//根节点
		List<CgptMenu> childMenus = cgptMenuService.getChildMenu();//子节点
		List<CgptMenuBtn> childBtns = cgptMenuBtnService.queryByAll();
		CgptTreeUtil util = new CgptTreeUtil(rootMenus,childMenus,childBtns);
		return util.getTreeNode();
	}
	
	/**
	 * 获取请求的菜单按钮数据
	 * @param request
	 * @return
	 */
	public List<CgptMenuBtn> getReqBtns(HttpServletRequest request){
		List<CgptMenuBtn> btnList= new ArrayList<CgptMenuBtn>();
		String[] btnId  = request.getParameterValues("btnId");
		String[] btnName  = request.getParameterValues("btnName");
		String[] btnType  = request.getParameterValues("btnType");
		String[] actionUrls  = request.getParameterValues("actionUrls");
		String[] deleteFlag  = request.getParameterValues("deleteFlag");

		if ( btnId==null)
		{
             CgptMenuBtn btn = new CgptMenuBtn();
             btn.setBtnName("null");
			 btnList.add(btn);
			 return btnList;
		}		
//System.out.println("高绪山Action："+ btnId.length);
		for (int i = 0; i < btnId.length; i++) {
			if(StringUtils.isNotBlank(btnName[i]) && StringUtils.isNotBlank(btnType[i])){
				CgptMenuBtn btn = new CgptMenuBtn();
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
