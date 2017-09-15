package com.hmmes.mapper;

import java.util.Map;


public interface SiteMainMapper<T> extends BaseMapper<T>{
	/**
	 * 管理站点类型
	 * @param siteTypeId
	 * @param siteId
	 */	
	public void addTypeRel(Map<String,Object> map);
	
	
	/**
	 * 删除关联的数据
	 * @param siteTypeId
	 * @param siteId
	 */	
	public void deleteTypeRel(Integer siteId);
}
