package com.hmmes.bean;


public class JhbhBean extends BaseBean {
	
		private Integer id;//   id主键	private Integer nian;//   年	private Integer yue;//   月	private Integer jhno;//   4位顺序号	private Integer flag;//   标记1==销售订单，2==销售订单变更单,3==生产半成品计划编号,4==机台计划变更单
	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getNian() {	    return this.nian;	}	public void setNian(Integer nian) {	    this.nian=nian;	}	public Integer getYue() {	    return this.yue;	}	public void setYue(Integer yue) {	    this.yue=yue;	}	public Integer getJhno() {	    return this.jhno;	}	public void setJhno(Integer jhno) {	    this.jhno=jhno;	}	public Integer getFlag() {	    return this.flag;	}	public void setFlag(Integer flag) {	    this.flag=flag;	}
}
