package com.hmmes.mapper;

import java.util.List;

/**
 * Mapper
 * @author Administrator
 *
 */
public interface ZbwzMapper<T> extends BaseMapper<T> {
	
	/**
	 * 查所有
	 * @return
	 */
	public List<T> queryByAll();

	public List<T> queryByGysId(Integer gysId);
	/**
	 * 删除供应商数据
	 * @param siteTypeId
	 * @param siteId
	 */	
	public void deleteGysRel(Integer zbwzId);
	
}
