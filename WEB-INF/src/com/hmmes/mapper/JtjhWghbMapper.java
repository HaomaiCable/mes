package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenuBtn Mapper
 * @author Administrator
 *
 */
public interface JtjhWghbMapper<T> extends BaseMapper<T> {
	
	public List<T> queryListByJhId(Integer jhid);
	public List<T> queryByAll();
}
