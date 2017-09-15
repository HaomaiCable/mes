package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface YgMapper<T> extends BaseMapper<T> {
	
	/**
	 *查询全部的操作工
	 * @return
	 */
	public List<T> queryAllList();
	/**
	 * 根据机台ID的，查询包括员工
	 * @return
	 */
	public List<T> queryListById(Integer id);

}
