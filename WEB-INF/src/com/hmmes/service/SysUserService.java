package com.hmmes.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmmes.bean.SysRole;
import com.hmmes.bean.SysUser;
import com.hmmes.bean.SysRoleRel;
import com.hmmes.bean.SysRoleRel.RelType;
import com.hmmes.mapper.SysUserMapper;
import com.hmmes.model.SysUserModel;
import com.hmmes.service.SysUserService;


@Service("sysUserService")
public class SysUserService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SysUserService.class);
	
	@Autowired
	private SysRoleRelService<SysRoleRel> sysRoleRelService;
	private SysUserService<SysUser> sysUserService;

	@Override
	public void delete(Object[] ids) throws Exception {
		super.delete(ids);
		for(Object id :  ids){
			sysRoleRelService.deleteByObjId((Integer)id, RelType.USER.key);
			getMapper().deleteTaskRole((Integer)id);
			getMapper().deleteTaskRoleRel((Integer)id);
		}
	}
	/**
	 * 检查登录
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryLogin(String email,String pwd){
		SysUserModel model = new SysUserModel();
		model.setEmail(email);
		model.setPwd(pwd);
		return getMapper().queryLogin(model);
	}
	/**
	 * 根据用户名查询
	 * @param nickname
	 * @param pwd
	 * @return
	 */
	public T queryByName(String nickname){
		SysUserModel model = new SysUserModel();
		model.setNickName(nickname);
		return getMapper().queryByName(model);
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
	public List<SysRoleRel> getUserRole(Integer userId){
		return sysRoleRelService.queryByObjId(userId,RelType.USER.key);
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
		sysRoleRelService.deleteByObjId(userId, RelType.USER.key);
		for(Integer roleId :roleIds ){ 
			SysRoleRel rel = new SysRoleRel();
			rel.setRoleId(roleId);
			rel.setObjId(userId);
			rel.setRelType(RelType.USER.key);
			sysRoleRelService.add(rel);
		}
	}
	public List<T> queryAllList(){
		return getMapper().queryAllList();
	}	
	
	@Autowired
    private SysUserMapper<T> mapper;

		
	public SysUserMapper<T> getMapper() {
		return mapper;
	}

}
