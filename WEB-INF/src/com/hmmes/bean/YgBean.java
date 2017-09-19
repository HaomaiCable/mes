package com.hmmes.bean;


public class YgBean extends BaseBean {

		private Integer id;//   id主键	private String xm;//   姓名	private Integer jtid;//  关联机台Id,jt.id	private String jtmc;//   所属机台名称	private Double gexs;//   分配定额系数	private Integer state;//   状态0=可用 1=禁用

	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getXm() {	    return this.xm;	}	public void setXm(String xm) {	    this.xm=xm;	}	public Integer getJtid() {	    return this.jtid;	}	public void setJtid(Integer jtid) {	    this.jtid=jtid;	}	public String getJtmc() {	    return this.jtmc;	}	public void setJtmc(String jtmc) {	    this.jtmc=jtmc;	}	public Double getDexs() {	    return this.gexs;	}	public void setDexs(Double gexs) {	    this.gexs=gexs;	}	public Integer getState() {	    return this.state;	}	public void setState(Integer state) {	    this.state=state;	}
}
