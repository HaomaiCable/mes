package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.GysdaBean;
import com.hmmes.exception.ServiceException;
import com.hmmes.mapper.GysdaMapper;
import com.hmmes.model.GysdaModel;


@Service("gysdaService")
public class GysdaService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(GysdaService.class);
	
	@Autowired
    private GysdaMapper<T> mapper;


	/**
	 * 采购物资分类
	 * @param cgflId
	 * @param gysId
	 */	
	public void addCgflRel(Integer[] cgflIds,Integer gysId){
		//先清理掉关联关系
		getMapper().deleteCgflRel(gysId);
		if(cgflIds != null && cgflIds.length>0){
			for(Integer cgflId :cgflIds){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("cgflId", cgflId);
				map.put("gysId", gysId);
				getMapper().addCgflRel(map);
			}
		}
	}
	/**
	 * 招标物资明细
	 * @param zbwzId
	 * @param gysId
	 */	
	public void addZbwzRel(Integer[] zbwzIds,Integer gysId){
		//先清理掉关联关系
		getMapper().deleteZbwzRel(gysId);
		if(zbwzIds != null && zbwzIds.length>0 ){
			for(Integer zbwzId :zbwzIds){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("zbwzId", zbwzId);
				map.put("gysId", gysId);
				getMapper().addZbwzRel(map);
			}
		}
	}
	
	/**
	 * 管理供应商
	 * @param cgflId
	 * @param gysId
	 */	
	public List<Map<String,Object>> getCgflsByGysId(Integer gysId){
		return null;
	}
	/**
	 * 管理供应商
	 * @param zbwzId
	 * @param gysId
	 */	
	public List<Map<String,Object>> getZbwzsByGysId(Integer gysId){
		return null;
	}
	
	public void delete(Object[] ids) throws Exception{
		for(Object id : ids ){
			getMapper().deleteCgflRel((Integer)id);
			getMapper().deleteZbwzRel((Integer)id);
		}
		super.delete(ids);
	}

	
	public List<T> queryByAll() throws Exception{
		return getMapper().queryByAll();
	}
	
	public GysdaMapper<T> getMapper() {
		return mapper;
	}

}
