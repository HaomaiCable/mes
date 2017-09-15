package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.mapper.ClcpCljzMapper;


@Service("clcpCljzService")
public class ClcpCljzService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ClcpCljzService.class);
	
	@Autowired
    private ClcpCljzMapper<T> mapper;	

	public List<T> queryByAll(){
		return getMapper().queryByAll();
	}
	
	public List<T> queryListByCpId(Integer cpid){
		return getMapper().queryListByCpId(cpid);
	}
	public void deleteByCpId(Integer cpid){
		getMapper().deleteByCpId(cpid);
	}	
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}

		
	public ClcpCljzMapper<T> getMapper() {
		return mapper;
	}

}
