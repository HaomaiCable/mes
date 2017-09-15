package com.hmmes.mapper;

import java.util.List;

/**
 * YywManage Mapper
 * @author Administrator
 *
 */
public interface GsdeMapper<T> extends BaseMapper<T> {
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<T> queryByAll();

	/**	
	 * 查询ID所有的
	 * @return
	 */
	public List<T> queryListById(Integer id);

    /**
	 * 根据DekSbmc（定额库设备名称）,gxxh,gxgg,gxdy,xglb
	 * @return
	 */
	T queryListByDeksbmcEtc(String deksbmc,String gxxh,String gxgg,String gxdy,String gxlb);

}
