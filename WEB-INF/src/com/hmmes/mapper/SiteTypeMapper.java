package com.hmmes.mapper;

import java.util.List;

/**
 * SiteType Mapper
 * @author Administrator
 *
 */
public interface SiteTypeMapper<T> extends BaseMapper<T> {
	
	/**
	 * 删除管理站点数据
	 * @param siteTypeId
	 * @param siteId
	 */	
	public void deleteSiteRel(Integer siteTypeId);
	
	
	/**
	 * 查所有站点类型
	 * @return
	 */
	public List<T> queryByAll();
	
	
	public List<T> queryBySiteId(Integer siteId);
	
}
