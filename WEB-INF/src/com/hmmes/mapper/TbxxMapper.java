package com.hmmes.mapper;

import java.util.List;
import com.hmmes.model.TbxxModel;

/**
 *  Mapper
 * @author Administrator
 *
 */
public interface TbxxMapper<T> extends BaseMapper<T> {
	
	/**
	 * 查询所有销售订单列表
	 * @return
	 */
	public List<T> queryByAll();	



	/**
	 * 查询ID的所有销售订单表头
	 * @return
	 */

	public List<T> queryListById(Integer zbggid);
		
	/**
	 * @param jhbh 
	 * @return
	 */
	public List<T> queryListByGgbh(String ggbh);

	public List<T> queryListByGgbhAndGysId(TbxxModel model);	
	
	
}
