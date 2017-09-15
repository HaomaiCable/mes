package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.CgflBean;
import com.hmmes.exception.ServiceException;
import com.hmmes.mapper.CgflMapper;
import com.hmmes.model.CgflModel;


@Service("cgflService")
public class CgflService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(CgflService.class);
	
	@Autowired
    private CgflMapper<T> mapper;

	/**
	 * 添加
	 */
	public void add(T t) throws Exception{
		validation(t,0);
		getMapper().add(t);
	}
	
	
	/**
	 * 修改
	 */
	public void update(T t) throws Exception{
		validation(t,1);
		getMapper().update(t);
	}
	
	public void delete(Object[] ids) throws Exception{
		if(ids == null || ids.length < 1){
			return;
		}
		for(Object id : ids){
			getMapper().deleteGysRel((Integer)id);
		}
		super.delete(ids);
	}

	
	
	public List<T> queryByAll() throws Exception{
		return getMapper().queryByAll();
	}

	public List<T> queryByGysId(Integer gysId) throws Exception{
		return getMapper().queryByGysId(gysId);
	}	
	
	/**
	 * 验证是否重复
	 * @param t
	 * @param num
	 * @throws Exception
	 */
	private void validation(T t,int num) throws Exception{
		CgflBean bean = (CgflBean)t;
		//验证name是否重复
		CgflModel model = new CgflModel();
		model.setName(bean.getName());
		int count = getMapper().queryByCount(model);
		if(count > num ){
			 throw new ServiceException("采购物资分类不能重复！");
		}
		//验证code是否重复
		//model.setName(null);
		//model.setCode(bean.getCode());
		//count = getMapper().queryByCount(model);
		//if(count > num){
		//	 throw new ServiceException("code is can't be duplicate");
		//}
	}
	
	public CgflMapper<T> getMapper() {
		return mapper;
	}

}
