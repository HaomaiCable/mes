package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.mapper.CpgxmxMapper;


@Service("cpgxmxService")
public class CpgxmxService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(CpgxmxService.class);
	
	@Autowired
    private CpgxmxMapper<T> mapper;	

	public List<T> queryByAll(){
		return getMapper().queryByAll();
	}
	
	public List<T> queryListByCpId(Integer cpid){
		return getMapper().queryListByCpId(cpid);
	}

	public void deleteByCpId(Integer cpid){
		getMapper().deleteByCpId(cpid);
	}	

		
	public CpgxmxMapper<T> getMapper() {
		return mapper;
	}

}
