package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.YwyBean;
import com.hmmes.mapper.YwyMapper;


@Service("YwyService")
public class YwyService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(YwyService.class);
	
	
	/**
	 *查询全部有效的业务员
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
    private YwyMapper<T> mapper;

		
	public YwyMapper<T> getMapper() {
		return mapper;
	}

}
