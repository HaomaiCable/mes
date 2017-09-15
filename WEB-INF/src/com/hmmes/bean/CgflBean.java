package com.hmmes.bean;


public class CgflBean extends BaseBean {
	
		private Integer id;//   id 主键	private String name;//   采购分类名称	private String descr;//   描述	private Integer state;//   状态 0=可用,1=禁用	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getDescr() {	    return this.descr;	}	public void setDescr(String descr) {	    this.descr=descr;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}	
}
