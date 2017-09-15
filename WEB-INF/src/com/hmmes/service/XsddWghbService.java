package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.XsddWghbBean;
import com.hmmes.mapper.XsddWghbMapper;

@Service("xsddWghbService")
public class XsddWghbService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(XsddWghbService.class);


	
	@Autowired
    private XsddWghbMapper<T> mapper;

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
	public List<T> queryListById(Integer ddid){
		return mapper.queryListById(ddid);
	}

	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}
	
	public XsddWghbMapper<T> getMapper() {
		return mapper;
	}

}
