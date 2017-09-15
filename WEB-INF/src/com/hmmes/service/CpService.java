package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.CpBean;
import com.hmmes.bean.CpgxmxBean;

import com.hmmes.mapper.CpMapper;

@Service("cpService")
public class CpService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(CpService.class);

	
	@Autowired
    private CpMapper<T> mapper;
	@Autowired
	private CpgxmxService<CpgxmxBean> cpgxmxService;



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
	public List<T> queryListById(Integer id){
		return mapper.queryListById(id);
	}

	

	public void deleteBean(Object[] ids) throws Exception {

		super.delete(ids);
		//删除关联关系
		for(Object cpid : ids){
			cpgxmxService.deleteByCpId((Integer)cpid);
		}
	}

	
	/**
	 *根据xh，gg查询产品
	 * @return
	 */
	public T queryByXhAndGg(String xh, String gg){
		return getMapper().queryByXhAndGg(xh,gg);
	}	


	public CpMapper<T> getMapper() {
		return mapper;
	}

}
