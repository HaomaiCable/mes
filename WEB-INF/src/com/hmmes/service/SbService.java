package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.SbBean;

import com.hmmes.mapper.SbMapper;

@Service("sbService")
public class SbService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SbService.class);

	
	@Autowired
    private SbMapper<T> mapper;




	/**
	 *查询全部有效List
	 * @return
	 */
	public List<T> queryAllList(){
		return getMapper().queryAllList();
	}

	/**
	 * 查询ID的所有
	 * @return
	 */
	public List<T> queryListById(Integer id){
		return mapper.queryListById(id);
	}

	/**
	 * 根据deksbmc(定额库设备名称)
	 * @return
	 */
	public List<T> queryListByDeksbmc(String deksbmc){
		return mapper.queryListByDeksbmc(deksbmc);
	}
	/**
	 * 根据sbmc(设备名称)
	 * @return
	 */
	public T queryBySbmc(String sbmc){
		return mapper.queryBySbmc(sbmc);
	}	
	
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}
	
	public SbMapper<T> getMapper() {
		return mapper;
	}

}
