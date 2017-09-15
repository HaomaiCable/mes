package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.YgBean;
import com.hmmes.mapper.YgMapper;


@Service("YgService")
public class YgService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(YgService.class);
	
	
	/**
	 *查询全部有效的员工
	 * @return
	 */
	public List<T> queryAllList(){
		return getMapper().queryAllList();
	}

    /**
	 * 查询机台ID，所包括的员工
	 * @return
	 */
	public List<T> queryListById(Integer id){
		return mapper.queryListById(id);
	}	
	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
	}
	@Autowired
    private YgMapper<T> mapper;

		
	public YgMapper<T> getMapper() {
		return mapper;
	}

}
