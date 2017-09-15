package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.GzTaskBean;

import com.hmmes.mapper.GzTaskMapper;

@Service("gzTaskService")
public class GzTaskService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(GzTaskService.class);

	
	@Autowired
    private GzTaskMapper<T> mapper;



	/**
	 * 查询所有
	 * @return
	 */
	public List<T> queryByAll(){
		return mapper.queryByAll();
	}

	/**
	 * 查询ID的所有
	 * @return
	 */
	public List<T> queryListById(Integer id){
		return mapper.queryListById(id);
	}

		
	public GzTaskMapper<T> getMapper() {
		return mapper;
	}

}
