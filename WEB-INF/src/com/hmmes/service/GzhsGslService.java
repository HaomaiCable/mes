package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.GzhsGslBean;
import com.hmmes.mapper.GzhsGslMapper;


@Service("GzhsGslService")
public class GzhsGslService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(GzhsGslService.class);
	
	
	/**
	 *查询全部有效的
	 * @return
	 */
	public List<T> queryAllList(){
		return getMapper().queryAllList();
	}

 
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}
	@Autowired
    private GzhsGslMapper<T> mapper;

		
	public GzhsGslMapper<T> getMapper() {
		return mapper;
	}

}
