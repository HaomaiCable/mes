package com.hmmes.mapper;

import java.util.Map;

/**
 * SiteScore Mapper
 * @author Administrator
 *
 */
public interface SiteScoreMapper<T> extends BaseMapper<T> {
	
	public void saveScore(Map<String, Object> map);
	
}
