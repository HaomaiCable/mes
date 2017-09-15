package com.hmmes.bean;


public class TaskRole extends BaseBean {
	
		private Integer id;//   id主键    private Integer userId;//   计划管理者ID主键	private String userName;//   用户登录名	private Integer state;//   状态 0=可用,1=禁用
	
	private String roleStrs;//用户权限, 按","区分,与数据库无关	private int[] roleIds = {}; //采购物资分类的id,与数据库无关 	private String nickName;//用户名	,与数据库无关	private Integer viewId ;//计划被管理用户ID	,与数据库无关
		public String getRoleStrs() {
		return this.roleStrs;
	}
	public void setRoleStrs(String roleStrs) {
		this.roleStrs = roleStrs;
	}	public int[] getRoleIds() {		return roleIds;	}	public void setRoleIds(int[] roleIds) {		this.roleIds = roleIds;	}	public String getNickName() {		return this.nickName;	}	public void setNickName(String nickName) {		this.nickName = nickName;	}	public Integer getViewId() {	    return this.viewId;	}	public void setViewId(Integer userId) {	    this.viewId=viewId;	}
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getUserId() {	    return this.userId;	}	public void setUserId(Integer userId) {	    this.userId=userId;	}	public String getUserName() {	    return this.userName;	}	public void setUserName(String userName) {	    this.userName=userName;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}
}
