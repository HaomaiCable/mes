package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenuBtn Mapper
 * @author Administrator
 *
 */
public interface ClcpCljzMapper<T> extends BaseMapper<T> {
	
	public List<T> queryListByCpId(Integer cpid);
	
	public void deleteByCpId(Integer cpid);
	

	public List<T> queryByAll();
}
