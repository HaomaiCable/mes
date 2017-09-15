package com.hmmes.mapper;

import java.util.List;

import com.hmmes.bean.SysRoleRel;

/**
 * SysRoleRel Mapper
 * @author Administrator
 *
 */
public interface SysRoleRelMapper<T> extends BaseMapper<T> {
	
	public void deleteByRoleId(java.util.Map<String, Object> param);
	
	public void deleteByObjId(java.util.Map<String, Object> param);
	
	
	public List<SysRoleRel> queryByRoleId(java.util.Map<String, Object> param);
	
	
	public List<SysRoleRel> queryByObjId(java.util.Map<String, Object> param);
	
	
}
