package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.GzhsJtkqBean;
import com.hmmes.mapper.GzhsJtkqMapper;


@Service("GzhsJtkqService")
public class GzhsJtkqService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(GzhsJtkqService.class);
	
	
	/**
	 *查询全部有效的
	 * @return
	 */
	public List<T> queryAllList(){
		return getMapper().queryAllList();
	}

 
    /**
	 * 查询sbmc,kqrq,bc
	 * @return
	 */
	public T queryBySbmcAndKqrqAndBc(String sbmc,java.sql.Date kqrq,Integer bc){
		return mapper.queryBySbmcAndKqrqAndBc(sbmc,kqrq,bc);
	}
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}
	@Autowired
    private GzhsJtkqMapper<T> mapper;

		
	public GzhsJtkqMapper<T> getMapper() {
		return mapper;
	}

}
