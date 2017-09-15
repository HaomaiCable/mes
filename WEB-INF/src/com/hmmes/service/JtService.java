package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.JtBean;
import com.hmmes.mapper.JtMapper;


@Service("JtService")
public class JtService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(JtService.class);
	
	
	/**
	 *查询全部有效的机台
	 * @return
	 */
	public List<T> queryAllList(){
		return getMapper().queryAllList();
	}

	/**
	 * 根据jtmc(机台名称)
	 * @return
	 */
	public T queryByJtmc(String jtmc){
		return mapper.queryByJtmc(jtmc);
	}			
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}

	@Autowired
    private JtMapper<T> mapper;

		
	public JtMapper<T> getMapper() {
		return mapper;
	}

}
