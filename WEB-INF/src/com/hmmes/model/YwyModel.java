package com.hmmes.model;

public class YwyModel extends BaseModel {
	

		private Integer id;//   id 主键	private String name;//   业务员姓名	private String department;//  所属部门	private Integer state;//   状态0=可用 1=禁用	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getDepartment() {	    return this.department;	}	public void setDepartment(String department) {	    this.department=department;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}
	
}
