package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenu Mapper
 * @author Administrator
 *
 */
public interface XsddBgMapper<T> extends BaseMapper<T> {
	
	/**
	 * 查询所有列表
	 * @return
	 */
	public List<T> queryByAll();

	/**
	 * 查询ID的列表
	 * @return
	 */
	public List<T> queryListById(Integer ddid);
		
	/**
	 * @param bh 
	 * @return
	 */
	public List<T> queryListByBh(String bh);	
	
	
}
