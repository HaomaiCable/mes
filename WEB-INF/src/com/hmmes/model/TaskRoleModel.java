package com.hmmes.model;

public class TaskRoleModel extends BaseModel {
		private Integer id;//   id主键    private Integer userId;//   计划管理者ID主键	private String userName;//   用户登录名	private Integer state;//   状态 0=可用,1=禁用	private String nickName;//用户名	,与数据库无关	public String getNickName() {		return this.nickName;	}	public void setNickName(String nickName) {		this.nickName = nickName;	}	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getUserId() {	    return this.userId;	}	public void setUserId(Integer userId) {	    this.userId=userId;	}	public String getUserName() {	    return this.userName;	}	public void setUserName(String userName) {	    this.userName=userName;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}
}
