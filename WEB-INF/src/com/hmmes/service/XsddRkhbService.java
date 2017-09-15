package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.XsddRkhbBean;
import com.hmmes.mapper.XsddRkhbMapper;

@Service("xsddRkhbService")
public class XsddRkhbService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(XsddRkhbService.class);


	
	@Autowired
    private XsddRkhbMapper<T> mapper;

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
	public List<T> queryListById(Integer xsddid){
		return mapper.queryListById(xsddid);
	}


	
	public XsddRkhbMapper<T> getMapper() {
		return mapper;
	}

}
