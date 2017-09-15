package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.CgptRole;
import com.hmmes.bean.CgptRoleRel;
import com.hmmes.bean.CgptRoleRel.RelType;
import com.hmmes.mapper.CgptRoleMapper;

/**
 * 
 * <br>
 * <b>功能：</b>CgptRoleService<br>
 * <b>作者：</b>罗泽军<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 * <b>版权所有：<b>版权所有(C) 2011，WWW.VOWO.COM<br>
 */
@Service("cgptRoleService")
public class CgptRoleService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(CgptRoleService.class);
	
	@Autowired
	private CgptRoleRelService<CgptRoleRel> cgptRoleRelService;
	
	/**
	 * 添加角色&菜单关系
	 */
	public void addRoleMenuRel(Integer roleId,Integer[] menuIds) throws Exception{
		if(roleId == null ||  menuIds == null || menuIds.length < 1 ){ 
			return;
		}
		for(Integer menuid :menuIds ){ 
			CgptRoleRel rel = new CgptRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(menuid);
			rel.setRelType(RelType.MENU.key);
			cgptRoleRelService.add(rel);
		}
	}
		
	/**
	 * 添加角色&按钮关系
	 */
	public void addRoleBtnRel(Integer roleId,Integer[] btnIds) throws Exception{
		if(roleId == null ||  btnIds == null || btnIds.length < 1 ){ 
			return;
		}
		for(Integer btnid : btnIds ){ 
			CgptRoleRel rel = new CgptRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(btnid);
			rel.setRelType(RelType.BTN.key);
			cgptRoleRelService.add(rel);
		}
	}
		
	
	/**
	 * 添加
	 * @param role
	 * @param menuIds
	 * @throws Exception
	 */
	public void add(CgptRole role,Integer[] menuIds,Integer[] btnIds) throws Exception {
		super.add((T)role);
//System.out.println("高绪山role:add"+role.getId());
		addRoleMenuRel(role.getId(),menuIds);
		addRoleBtnRel(role.getId(),btnIds);
	}

	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
		for(Object id : ids){
			//清除关联关系
			cgptRoleRelService.deleteByRoleId((Integer)id);
		}
	}

	/**
	 * 修改
	 * @param role
	 * @param menuIds
	 * @throws Exception
	 */
	public void update(CgptRole role,Integer[] menuIds,Integer[] btnIds) throws Exception {
		super.update((T)role);
		//清除关联关系
		cgptRoleRelService.deleteByRoleId(role.getId(),RelType.MENU.key);
		cgptRoleRelService.deleteByRoleId(role.getId(),RelType.BTN.key);
		addRoleMenuRel(role.getId(),menuIds);
		addRoleBtnRel(role.getId(),btnIds);
	}

	
	/**
	 *查询全部有效的权限
	 * @return
	 */
	public List<T> queryAllList(){
		return getMapper().queryAllList();
	}

	

	/**
	 *查询全部有效的权限
	 * @return
	 */
	public List<T> queryByUserid(Integer userid){
		return getMapper().queryByUserid(userid);
	}

	@Autowired
    private CgptRoleMapper<T> mapper;

		
	public CgptRoleMapper<T> getMapper() {
		return mapper;
	}

}
