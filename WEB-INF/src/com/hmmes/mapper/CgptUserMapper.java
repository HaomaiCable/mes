package com.hmmes.mapper;

import com.hmmes.model.CgptUserModel;

/**
 * CgptUser Mapper
 * @author Administrator
 *
 */
public interface CgptUserMapper<T> extends BaseMapper<T> {
	
	/**
	 * 检查登录
	 * @param email
	 * @param pwd
	 * @return
	 */
	public T queryLogin(CgptUserModel model);
	
	
	/**
	 * 查询邮箱总数，检查是否存在
	 * @param email
	 * @return
	 */
	public int getUserCountByEmail(String email);
}
