package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.SysRoleRel;
import com.hmmes.mapper.SysRoleRelMapper;

/**
 * 
 * <br>
 * <b>功能：</b>SysRoleRelService<br>
 * <b>作者：</b>罗泽军<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 * <b>版权所有：<b>版权所有(C) 2011，WWW.VOWO.COM<br>
 */
@Service("sysRoleRelService")
public class SysRoleRelService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SysRoleRelService.class);
	
	
	public List<SysRoleRel> queryByRoleId(Integer roleId,Integer relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		param.put("relType", relType);
		return getMapper().queryByRoleId(param);
	}
	
	
	public List<SysRoleRel> queryByObjId(Integer objId,Integer relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objId", objId);
		param.put("relType", relType);
		return getMapper().queryByObjId(param);
	}
	
	/**
	 * 根据关联对象id,关联类型删除 
	 * @param objId
	 * @param relType
	 */
	public void deleteByObjId(Integer objId,Integer relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objId", objId);
		param.put("relType", relType);
		getMapper().deleteByObjId(param);
	}
	
	/**
	 * 根据角色id删除 
	 * @param roleId
	 */
	public void deleteByRoleId(Integer roleId){
		deleteByRoleId(roleId,null);
	}
	
	/**
	 *  根据角色id,关联类型删除 
	 * @param roleId
	 * @param relType
	 */
	public void deleteByRoleId(Integer roleId,Integer relType){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		param.put("relType", relType);
		getMapper().deleteByRoleId(param);
	}
	
	
	

	@Autowired
    private SysRoleRelMapper<T> mapper;

		
	public SysRoleRelMapper<T> getMapper() {
		return mapper;
	}

}
