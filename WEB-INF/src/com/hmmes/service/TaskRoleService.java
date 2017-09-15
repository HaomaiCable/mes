package com.hmmes.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hmmes.mapper.TaskRoleMapper;
import com.hmmes.model.TaskRoleModel;


@Service("taskRoleService")
public class TaskRoleService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(TaskRoleService.class);

	/**
	 * 计划查看
	 * @param cgflId
	 * @param gysId
	 */	
	public void addTaskRoleRel(Integer[] roleIds,Integer roleId){
		//先清理掉关联关系
		getMapper().deleteTaskRoleRel(roleId);
		if(roleIds != null && roleIds.length>0){
			for(Integer viewroleId :roleIds){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("viewId", viewroleId);
				map.put("roleId", roleId);
				getMapper().addTaskRoleRel(map);
			}
		}
	}

	
	/**
	 * 管理计划账户
	 * @param roleId
	 * @param 
	 */	
	public List<Map<String,Object>> getusersByroleId(Integer roleId){
		return null;
	}

	
	public void delete(Object[] ids) throws Exception{
		for(Object id : ids ){
			getMapper().deleteTaskRoleRel((Integer)id);
		}
		super.delete(ids);
	}

	
	public List<T> queryByAll() throws Exception{
		return getMapper().queryByAll();
	}

   public List<T> queryByGlzId(Integer roleId) throws Exception{
		return getMapper().queryByGlzId(roleId);
	}	
   public T queryByUserId(Integer userId) throws Exception{
		return getMapper().queryByUserId(userId);
	}	
	@Autowired
    private TaskRoleMapper<T> mapper;

		
	public TaskRoleMapper<T> getMapper() {
		return mapper;
	}

}
