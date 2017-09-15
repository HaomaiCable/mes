package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.JtjhWghbBean;
import com.hmmes.mapper.JtjhWghbMapper;

@Service("jtjhWghbService")
public class JtjhWghbService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(JtjhWghbService.class);


	
	@Autowired
    private JtjhWghbMapper<T> mapper;

	/**
	 * 查询所有
	 * @return
	 */
	public List<T> queryByAll(){
		return mapper.queryByAll();
	}
	
	/**
	 * 通过xsdd.id查询
	 * @return
	 */
	public List<T> queryListByJhId(Integer jhid){
		return mapper.queryListByJhId(jhid);
	}
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}

	
	public JtjhWghbMapper<T> getMapper() {
		return mapper;
	}

}
