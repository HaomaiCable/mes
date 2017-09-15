package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.CgptMenu;
import com.hmmes.bean.CgptMenuBtn;
import com.hmmes.bean.CgptRoleRel;
import com.hmmes.bean.CgptRoleRel.RelType;
import com.hmmes.mapper.CgptMenuMapper;

/**
 * 
 * <br>
 * <b>功能：</b>CgptMenuService<br>
 * <b>作者：</b>罗泽军<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 * <b>版权所有：<b>版权所有(C) 2011，WWW.VOWO.COM<br>
 */
@Service("cgptMenuService")
public class CgptMenuService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(CgptMenuService.class);


	@Autowired
	private CgptRoleRelService<CgptRoleRel> cgptRoleRelService;
	
	@Autowired
	private CgptMenuBtnService<CgptMenuBtn> cgptMenuBtnService;
	
	@Autowired
    private CgptMenuMapper<T> mapper;
	
	/**
	 * 保存菜单btn
	 * @param btns
	 * @throws Exception 
	 */
	public void saveBtns(Integer menuid,List<CgptMenuBtn> btns) throws Exception{
//System.out.println("高绪山server："+ btns.get(0).getBtnName());
		if(btns == null || btns.isEmpty() ||  "null".equals(btns.get(0).getBtnName())){
			return;
		}
		for (CgptMenuBtn btn : btns) {
			if(btn.getId()!= null && "1".equals(btn.getDeleteFlag())){
				cgptMenuBtnService.delete(btn.getId());
				continue;
			}
			btn.setMenuid(menuid);
			if(btn.getId() == null){
				cgptMenuBtnService.add(btn);
			}else{
				cgptMenuBtnService.update(btn);
			}
		}
		
	}
	

	public void addBean(CgptMenu menu) throws Exception {
		super.add((T)menu);

		saveBtns(menu.getId(),menu.getBtns());		}


	public void updateBean(CgptMenu menu) throws Exception {
		super.update((T)menu);
		saveBtns(menu.getId(),menu.getBtns());	

	}


	/**
	 * 查询所有系统菜单列表
	 * @return
	 */
	public List<T> queryByAll(){
		return mapper.queryByAll();
	}
	
	/**
	 * 获取顶级菜单
	 * @return
	 */
	public List<T> getRootMenu(Integer menuId){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("menuId", menuId);
		return mapper.getRootMenu(map);
	}
	
	/**
	 * 获取子菜单
	 * @return
	 */
	public List<T> getChildMenu(){
		return mapper.getChildMenu();
	}
	
	/**
	 * 根据用户id查询父菜单
	 * @param roleId
	 * @return
	 */
	public List<T> getRootMenuByUser(Integer userId){
		return getMapper().getRootMenuByUser(userId);
	}
	
	
	/**
	 * 根据用户id查询子菜单
	 * @param roleId
	 * @return
	 */
	public List<T> getChildMenuByUser(Integer userId){
		return getMapper().getChildMenuByUser(userId);
	}
	
	
	/**
	 * 根据权限id查询菜单
	 * @param roleId
	 * @return
	 */
	public List<T> getMenuByRoleId(Integer roleId){
		return getMapper().getMenuByRoleId(roleId);
	}
	
	
	
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
		//删除关联关系
		for(Object id : ids){
			cgptRoleRelService.deleteByObjId((Integer)id, RelType.MENU.key);
			cgptMenuBtnService.deleteByMenuid((Integer)id);
		}
	}

	
	
	
	public CgptMenuMapper<T> getMapper() {
		return mapper;
	}

}
