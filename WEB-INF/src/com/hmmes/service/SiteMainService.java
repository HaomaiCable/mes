package com.hmmes.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.mapper.SiteMainMapper;

@Service("siteMainService")
public class SiteMainService<T>  extends BaseService<T>{

	@Autowired
	private SiteMainMapper<T> siteMainMapper;
	
	
	/**
	 * 管理站点类型
	 * @param siteTypeId
	 * @param siteId
	 */	
	public void addTypeRel(Integer[] typeIds,Integer siteId){
		//先清理掉关联关系
		getMapper().deleteTypeRel(siteId);
		if(typeIds != null){
			for(Integer typeId :typeIds){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("siteTypeId", typeId);
				map.put("siteId", siteId);
				getMapper().addTypeRel(map);
			}
		}
	}
	
	/**
	 * 管理站点类型
	 * @param siteTypeId
	 * @param siteId
	 */	
	public List<Map<String,Object>> getTypesBySiteId(Integer siteId){
		return null;
	}
	
	public void delete(Object[] ids) throws Exception{
		for(Object id : ids ){
			getMapper().deleteTypeRel((Integer)id);
		}
		super.delete(ids);
	}
	
	@Override
	public SiteMainMapper<T> getMapper() {
		return siteMainMapper;
	}
	
	
}
