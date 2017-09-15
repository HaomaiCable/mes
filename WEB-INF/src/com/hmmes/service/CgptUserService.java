package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.CgptRole;
import com.hmmes.bean.CgptRoleRel;
import com.hmmes.bean.CgptRoleRel.RelType;
import com.hmmes.mapper.CgptUserMapper;
import com.hmmes.model.CgptUserModel;


@Service("cgptUserService")
public class CgptUserService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(CgptUserService.class);
	
	@Autowired
	private CgptRoleRelService<CgptRoleRel> cgptRoleRelService;

	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
		for(Object id :  ids){
			cgptRoleRelService.deleteByObjId((Integer)id, RelType.USER.key);
		}
	}
	/**
	 * 检查登录
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryLogin(String email,String pwd){
		CgptUserModel model = new CgptUserModel();
		model.setEmail(email);
		model.setPwd(pwd);
		return getMapper().queryLogin(model);
	}
	
	/**
	 * 查询邮箱总数，检查是否存在
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email){
		return getMapper().getUserCountByEmail(email);
	}
	
	/**
	 * 查询用户权限
	 * @param userId
	 * @return
	 */
	public List<CgptRoleRel> getUserRole(Integer userId){
		return cgptRoleRelService.queryByObjId(userId,RelType.USER.key);
	}
	
	/**
	 * 添加用户权限
	 * @param userId
	 * @param roleIds
	 * @throws Exception
	 */
	public void addUserRole(Integer userId,Integer[] roleIds) throws Exception{
		if(userId == null ||  roleIds == null || roleIds.length < 1 ){ 
			return;
		}
		//清除关联关系
		cgptRoleRelService.deleteByObjId(userId, RelType.USER.key);
		for(Integer roleId :roleIds ){ 
			CgptRoleRel rel = new CgptRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(userId);
			rel.setRelType(RelType.USER.key);
			cgptRoleRelService.add(rel);
		}
	}
	
	
	@Autowired
    private CgptUserMapper<T> mapper;

		
	public CgptUserMapper<T> getMapper() {
		return mapper;
	}

}
