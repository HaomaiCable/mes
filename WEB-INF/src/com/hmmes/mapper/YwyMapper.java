package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface YwyMapper<T> extends BaseMapper<T> {
	
	/**
	 *查询全部的业务员
	 * @return
	 */
	public List<T> queryAllList();
	

}
