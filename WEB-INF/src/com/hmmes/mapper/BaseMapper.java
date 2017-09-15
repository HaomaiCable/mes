package com.hmmes.mapper;

import java.util.List;

import com.hmmes.model.BaseModel;

public interface BaseMapper<T> {

	
	public void add(T t);
	
	public void update(T t);
	
	public void updateBySelective(T t); 	
	
	public void delete(Object id);

	public int queryByCount(BaseModel model);
	public int queryByCountDist(BaseModel model);
	public int queryByCountBcp(BaseModel model);
	
	public List<T> queryByList(BaseModel model);

	public List<T> queryByListNoPage(BaseModel model);

    public List<T> queryByListDist(BaseModel model);
    public List<T> queryByListBcp(BaseModel model);
	public List<T> queryByListGd(BaseModel model);
	public List<T> queryByListHs(BaseModel model);

	
	public T queryById(Object id);

}
