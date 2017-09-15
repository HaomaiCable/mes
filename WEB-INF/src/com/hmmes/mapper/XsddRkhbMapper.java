package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenuBtn Mapper
 * @author Administrator
 *
 */
public interface XsddRkhbMapper<T> extends BaseMapper<T> {
	
	public List<T> queryListById(Integer ddid);
	public List<T> queryByAll();
}
