package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.JhbhBean;
import com.hmmes.mapper.JhbhMapper;


@Service("JhbhService")
public class JhbhService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(JhbhService.class);
	
	/**
	 *根据year，month查询当前最大计划编号No
	 * @return
	 */
	public T queryNoByYearAndMonth(Integer nian,Integer yue,Integer flag){
		return getMapper().queryNoByYearAndMonth(nian,yue,flag);
	}

	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}
	

	@Autowired
    private JhbhMapper<T> mapper;

		
	public JhbhMapper<T> getMapper() {
		return mapper;
	}

}
