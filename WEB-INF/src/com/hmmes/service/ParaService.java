package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.ParaBean;
import com.hmmes.mapper.ParaMapper;


@Service("paraService")
public class ParaService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ParaService.class);
	
	/**
	 *根据year，month查询当前最大计划编号No
	 * @return
	 */
	public List<T> queryByFlag(String flag){
		return getMapper().queryByFlag(flag);
	}

	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}

	@Autowired
    private ParaMapper<T> mapper;

		
	public ParaMapper<T> getMapper() {
		return mapper;
	}

}
