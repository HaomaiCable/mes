package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.JtjhBgBean;
import com.hmmes.mapper.JtjhBgMapper;

@Service("jtjhBgService")
public class JtjhBgService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(JtjhBgService.class);

	@Autowired
    private JtjhBgMapper<T> mapper;
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<T> queryByAll(){
		return mapper.queryByAll();
	}
	
	/**
	 * 查询ID的
	 * @return
	 */
	public List<T> queryListById(Integer jhid){
		return mapper.queryListById(jhid);
	}
	/**
	 * 根据bh的
	 * @return
	 */
	public List<T> queryListByBh(String bh){
		return mapper.queryListByBh(bh);
	}	
	
	
	public JtjhBgMapper<T> getMapper() {
		return mapper;
	}

}
