package com.hmmes.mapper;

import java.util.List;

/**
 * CgptMenuBtn Mapper
 * @author Administrator
 *
 */
public interface CgptMenuBtnMapper<T> extends BaseMapper<T> {
	
	public List<T> queryByMenuid(Integer menuid);
	
	public List<T> queryByMenuUrl(String url); 
	
	public void deleteByMenuid(Integer menuid);
	
	public List<T> getMenuBtnByUser(Integer userid); 
	
	
	
	public List<T> queryByAll();
}
