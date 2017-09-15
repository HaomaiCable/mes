package com.hmmes.bean;

import java.util.List;


public class CgptMenu extends BaseBean {
	
	private Integer id;//   主键	
	private String name;//   菜单名称
	private String url;//   系统url	
	private Integer parentId;//   父id 关联sys_menu.id	
	private Integer deleted;//   是否删除,0=未删除，1=已删除	
	private java.sql.Timestamp createTime;//   创建时间
	private java.sql.Timestamp updateTime;//   修改时间	
	private Integer rank;//   排序
	private String actions; //注册Action 按钮|分隔

	private int subCount;//子菜单总数
	
	//菜单按钮
	private List<CgptMenuBtn> btns;
	
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getParentId() {
		return this.parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	public java.sql.Timestamp getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getRank() {
		return this.rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public List<CgptMenuBtn> getBtns() {
		return this.btns;
	}
	public void setBtns(List<CgptMenuBtn> btns) {
		this.btns = btns;
	}
	public String getActions() {
		return this.actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
	public int getSubCount() {
		return this.subCount;
	}
	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}	
}
