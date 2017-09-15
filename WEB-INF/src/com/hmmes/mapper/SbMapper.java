package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface SbMapper<T> extends BaseMapper<T> {
	
	/**
	 *查询全部List
	 * @return
	 */
	public List<T> queryAllList();
	/**	
	 * 查询ID所有的
	 * @return
	 */
	public List<T> queryListById(Integer id);

    /**
	 * 根据DekSbmc（定额库设备名称）
	 * @return
	 */
	public List<T> queryListByDeksbmc(String deksbmc);
	/**
	 * 根据sbmc(设备名称)
	 * @return
	 */
	public T queryBySbmc(String sbmc);

}
