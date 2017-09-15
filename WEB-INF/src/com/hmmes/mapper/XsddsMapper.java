package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenuBtn Mapper
 * @author Administrator
 *
 */
public interface XsddsMapper<T> extends BaseMapper<T> {
	
	public List<T> queryByBtid(Integer btid);
	public void deleteByBtid(Integer btid);
	

	public List<T> queryByAll();
}
