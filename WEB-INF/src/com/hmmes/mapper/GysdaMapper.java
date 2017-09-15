package com.hmmes.mapper;

import java.util.List;
import java.util.Map;

/**
 * Mapper
 * @author Administrator
 *
 */
public interface GysdaMapper<T> extends BaseMapper<T> {
	
	/**
	 * 查所有
	 * @return
	 */
	public List<T> queryByAll();

    /**
	 * 管理采购物资分类
	 * @param cgflId
	 * @param gysId
	 */	
	public void addCgflRel(Map<String,Object> map);

    /**
	 * 管理招标物资
	 * @param zbwzId
	 * @param gysId
	 */	
	public void addZbwzRel(Map<String,Object> map);	
	
	/**
	 * 删除关联的数据
	 * @param cgflId
	 * @param gysId
	 */	
	public void deleteCgflRel(Integer gysId);
	public void deleteZbwzRel(Integer gysId);
	
}
