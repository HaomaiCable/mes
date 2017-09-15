package com.hmmes.mapper;

import java.util.List;

/**
 *  Mapper
 * @author Administrator
 *
 */
public interface ZbggMapper<T> extends BaseMapper<T> {
	
	/**
	 * 查询所有销售订单列表
	 * @return
	 */
	public List<T> queryByAll();	



	/**
	 * 查询ID的所有销售订单表头
	 * @return
	 */

	public List<T> queryListById(Integer zbggid);
		
	/**
	 * @param jhbh 
	 * @return
	 */
	public List<T> queryListByGgbh(String ggbh);	
	
	
}
