package com.hmmes.mapper;

import java.util.List;

import com.hmmes.bean.CgptRoleRel;

/**
 * CgptRoleRel Mapper
 * @author Administrator
 *
 */
public interface CgptRoleRelMapper<T> extends BaseMapper<T> {
	
	public void deleteByRoleId(java.util.Map<String, Object> param);
	
	public void deleteByObjId(java.util.Map<String, Object> param);
	
	
	public List<CgptRoleRel> queryByRoleId(java.util.Map<String, Object> param);
	
	
	public List<CgptRoleRel> queryByObjId(java.util.Map<String, Object> param);
	
	
}
