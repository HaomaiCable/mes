package com.hmmes.bean;



public class SbBean extends BaseBean {
	
	private Integer id;//   主键	
	private String sbmc;//  设备名称，下达机台计划显示
	private String deksbmc;//  产品工时定额(产品工序数据库)中的设备名称，查询计算用
	private Double sbsl;//   设备台数
	private Double bc;//   该设备的工作班次，例如，每天1班，每天两班
	private Double cqgs;//   每班出勤工时
	private Double yyfh;//   尚未完工负荷（单位：工时）
    private Double bpjhfh;//   本次计划需要负荷（单位：工时）
    private Double tzxs;//   负荷调整系数，实际负荷=(yyfh+bpjhfh)*tzxx,负荷天数=实际负荷/(sbsl*bc*cqgs)
	private String jt;//   所属机台班组
	private Integer state;//  设备状态， 0=可用，1=不可用

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getSbmc() {
		return this.sbmc;
	}
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}

	public String getDeksbmc() {
	    return this.deksbmc;
	}
	public void setDeksbmc(String deksbmc) {
	    this.deksbmc=deksbmc;
	}

	public Double getSbsl() {
	    return this.sbsl;
	}
	public void setSbsl(Double sbsl) {
	    this.sbsl=sbsl;
	}
	public Double getBc() {
	    return this.bc;
	}
	public void setBc(Double bc) {
	    this.bc=bc;
	}

	public Double getCqgs() {
	    return this.cqgs;
	}
	public void setCqgs(Double cqgs) {
	    this.cqgs=cqgs;
	}
	public Double getYyfh() {
	    return this.yyfh;
	}
	public void setYyfh(Double yyfh) {
	    this.yyfh=yyfh;
	}
	public Double getBpjhfh() {
	    return this.bpjhfh;
	}
	public void setBpjhfh(Double bpjhfh) {
	    this.bpjhfh=bpjhfh;
	}
	public Double getTzxs() {
	    return this.tzxs;
	}
	public void setTzxs(Double tzxs) {
	    this.tzxs=tzxs;
	}
	public String getJt() {
	    return this.jt;
	}
	public void setJt(String jt) {
	    this.jt=jt;
	}
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

}
