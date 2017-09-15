package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.mapper.XsddsMapper;


@Service("xsddsService")
public class XsddsService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(XsddsService.class);
	
	public List<T> queryByAll(){
		return getMapper().queryByAll();
	}
	
	public List<T> queryByBtid(Integer btid){
		return getMapper().queryByBtid(btid);
	}
	public void deleteByBtid(Integer btid){
		getMapper().deleteByBtid(btid);
	}	
	

	@Autowired
    private XsddsMapper<T> mapper;

		
	public XsddsMapper<T> getMapper() {
		return mapper;
	}

}
