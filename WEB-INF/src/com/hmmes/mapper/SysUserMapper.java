package com.hmmes.mapper;
import java.util.List;
import com.hmmes.model.SysUserModel;

/**
 * SysUser Mapper
 * @author Administrator
 *
 */
public interface SysUserMapper<T> extends BaseMapper<T> {
	
	/**
	 * 检查登录
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryLogin(SysUserModel model);

	public T queryByName(SysUserModel model);	
	
	/**
	 * 查询邮箱总数，检查是否存在
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email);
	public List<T> queryAllList();
	public void deleteTaskRole(Integer Id);
	public void deleteTaskRoleRel(Integer Id);
}
