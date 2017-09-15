package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.mapper.SiteColumnMapper;

/**
 * 
 * <br>
 * <b>功能：</b>SiteColumnService<br>
 * <b>作者：</b>罗泽军<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 * <b>版权所有：<b>版权所有(C) 2011，WWW.VOWO.COM<br>
 */
@Service("siteColumnService")
public class SiteColumnService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SiteColumnService.class);
	
	/**
	 * 根据站点id查询栏目
	 * @param siteId
	 */
	public List<T> queryBySiteId(Integer siteId){
		return getMapper().queryBySiteId(siteId);
	}
	

	@Autowired
    private SiteColumnMapper<T> mapper;

		
	public SiteColumnMapper<T> getMapper() {
		return mapper;
	}

}
