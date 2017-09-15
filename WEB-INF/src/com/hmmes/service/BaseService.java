package com.hmmes.service;

import java.util.List;

import com.hmmes.mapper.BaseMapper;
import com.hmmes.model.BaseModel;

public abstract class BaseService<T>{
	
	public abstract BaseMapper<T> getMapper();

	
	public void add(T t)  throws Exception{
		getMapper().add(t);
	}
	
	public void update(T t)  throws Exception{
		getMapper().update(t);
	}
	
	
	public void updateBySelective(T t){
		getMapper().updateBySelective(t);
	}

	public void delete(Object... ids) throws Exception{
		if(ids == null || ids.length < 1){
			return;
		}
		for(Object id : ids ){
			getMapper().delete(id);
		}
	}
	
	public int queryByCount(BaseModel model)throws Exception{
		return getMapper().queryByCount(model);
	}

	public int queryByCountDist(BaseModel model)throws Exception{
		return getMapper().queryByCountDist(model);
	}
	public int queryByCountBcp(BaseModel model)throws Exception{
		return getMapper().queryByCountBcp(model);
	}
		
	public List<T> queryByListDist(BaseModel model) throws Exception{
		Integer rowCount = queryByCountDist(model);
		model.getPager().setRowCount(rowCount);
		return getMapper().queryByListDist(model);
	}

	public List<T> queryByList(BaseModel model) throws Exception{
		Integer rowCount = queryByCount(model);
//System.out.println("¸ßÐ÷É½£ºrowCount"+rowCount);
		model.getPager().setRowCount(rowCount);
		return getMapper().queryByList(model);
	}
	public List<T> queryByListBcp(BaseModel model) throws Exception{
		Integer rowCount = queryByCountBcp(model);
		model.getPager().setRowCount(rowCount);
		return getMapper().queryByListBcp(model);
	}
	public List<T> queryByListGd(BaseModel model) throws Exception{
		Integer rowCount = queryByCount(model);
		model.getPager().setRowCount(rowCount);
		return getMapper().queryByListGd(model);
	}
	public List<T> queryByListHs(BaseModel model) throws Exception{
		Integer rowCount = queryByCount(model);
		model.getPager().setRowCount(rowCount);
		return getMapper().queryByListHs(model);
	}
    public List<T> queryByListNoPage(BaseModel model) throws Exception{
		Integer rowCount = queryByCount(model);
		model.getPager().setRowCount(rowCount);
		return getMapper().queryByListNoPage(model);
	}

	public T queryById(Object id) throws Exception{
		return getMapper().queryById(id);
	}
}
