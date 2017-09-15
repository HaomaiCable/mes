package com.hmmes.mapper;

import java.util.List;

/**
 * SysMenu Mapper
 * @author Administrator
 *
 */
public interface JtjhMapper<T> extends BaseMapper<T> {
	
	/**
	 * 查询列表
	 * @return
	 */
	public List<T> queryByAll();

	/**
	 * 查询ID的所有
	 * @return
	 */
	public List<T> queryListById(Integer id);
	/**
	 * 根据ddid查询
	 * @return
	 */
	public List<T> queryListByDdId(Integer ddid);
		
	/**
	 * @param jhbh 
	 * @return
	 */
	public List<T> queryListByJhbh(String jhbh);	
	/**
	 * 根据sbmcdek(定额库设备名称)查询
	 * @return
	 */
	public List<T> queryListBySbmcdek(String sbmcdek);
	/**
	 * 根据sbmc(下达计划的设备名称)查询
	 * @return
	 */
	public List<T> queryListBySbmc(String sbmc);	
	public T queryByJhbhAndRow(String jhbh,Integer row);
	
}
