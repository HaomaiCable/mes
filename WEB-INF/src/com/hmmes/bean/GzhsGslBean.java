package com.hmmes.bean;


public class GzhsGslBean extends BaseBean {

		private Integer id;//   id主键	private String gd;//   工段	private String sbmc;//   机台名称	private Integer jtrs;//  机台人数，分配定额操作工人数	private Double gsl;//   工时工资率	private Integer state;//   状态0=可用 1=禁用

	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getGd() {		return this.gd;	}	public void setGd(String gd) {		this.gd = gd;	}	public String getSbmc() {		return this.sbmc;	}	public void setSbmc(String sbmc) {		this.sbmc = sbmc;	}	public Integer getJtrs() {	    return this.jtrs;	}	public void setJtrs(Integer jtrs) {	    this.jtrs=jtrs;	}	public Double getGsl() {	    return this.gsl;	}	public void setGsl(Double gsl) {	    this.gsl=gsl;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}
}
