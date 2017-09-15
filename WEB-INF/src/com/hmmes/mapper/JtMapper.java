package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface JtMapper<T> extends BaseMapper<T> {
	
	/**
	 *查询全部List
	 * @return
	 */
	public List<T> queryAllList();
	
	/**
	 * 根据jtmc(机台名称)
	 * @return
	 */
	public T queryByJtmc(String jtmc);


}
