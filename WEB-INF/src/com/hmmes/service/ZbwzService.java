package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.ZbwzBean;
import com.hmmes.exception.ServiceException;
import com.hmmes.mapper.ZbwzMapper;
import com.hmmes.model.ZbwzModel;


@Service("zbwzService")
public class ZbwzService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(ZbwzService.class);
	
	@Autowired
    private ZbwzMapper<T> mapper;

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
		ZbwzBean bean = (ZbwzBean)t;
		//验证name是否重复
		ZbwzModel model = new ZbwzModel();
		model.setName(bean.getName());
		int count = getMapper().queryByCount(model);
		if(count > num ){
			 throw new ServiceException("招标物资不能重复！");
		}
		//验证code是否重复
		//model.setName(null);
		//model.setCode(bean.getCode());
		//count = getMapper().queryByCount(model);
		//if(count > num){
		//	 throw new ServiceException("code is can't be duplicate");
		//}
	}
	
	public ZbwzMapper<T> getMapper() {
		return mapper;
	}

}
