package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenu Mapper
 * @author Administrator
 *
 */
public interface GzTaskMapper<T> extends BaseMapper<T> {
	
	/**
	 * 查询列表
	 * @return
	 */
	public List<T> queryByAll();

	/**
	 * 查询ID的所有
	 * @return
	 */
	public List<T> queryListById(Integer id);
	
}
