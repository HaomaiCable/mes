package com.hmmes.mapper;

import java.util.List;

/**
 * JhbhManage Mapper
 * @author Administrator
 *
 */
public interface ParaMapper<T> extends BaseMapper<T> {
	
	/**
	 *根据Flag，查询参数
	 * @return
	 */
	public List<T> queryByFlag(String flag);
	

}
