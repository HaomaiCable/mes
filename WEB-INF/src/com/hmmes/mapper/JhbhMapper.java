package com.hmmes.mapper;

import java.util.List;

/**
 * JhbhManage Mapper
 * @author Administrator
 *
 */
public interface JhbhMapper<T> extends BaseMapper<T> {
	
	/**
	 *根据year，month查询当前最大计划编号No
	 * @return
	 */
	public T queryNoByYearAndMonth(Integer nian, Integer yue, Integer flag);
	

}
