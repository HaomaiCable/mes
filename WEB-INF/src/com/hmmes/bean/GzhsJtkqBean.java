package com.hmmes.bean;


public class GzhsJtkqBean extends BaseBean {

		private Integer id;//   id主键	private String gd;//   工段	private String sbmc;//   机台名称	private String ygxm;//   员工姓名	private Double dexs;//   分配工时工资系数 	private java.sql.Date kqrq;//   考勤日期	private Integer bc;//    班次，1==白班，2==夜班	private Double cqgs;//   出勤工时     private String lrBy;//   录入人		private java.sql.Timestamp lrTime;//   录入时间

	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getGd() {		return this.gd;	}	public void setGd(String gd) {		this.gd = gd;	}	public String getSbmc() {		return this.sbmc;	}	public void setSbmc(String sbmc) {		this.sbmc = sbmc;	}	public String getYgxm() {		return this.ygxm;	}	public void setYgxm(String ygxm) {		this.ygxm = ygxm;	}	public Double getDexs() {	    return this.dexs;	}	public void setDexs(Double dexs) {	    this.dexs=dexs;	}	public java.sql.Date getKqrq() {		return this.kqrq;	}	public void setKqrq(java.sql.Date kqrq) {		this.kqrq = kqrq;	}	public Integer getBc() {	    return this.bc;	}	public void setBc(Integer bc) {	    this.bc=bc;	}		public Double getCqgs() {	    return this.cqgs;	}	public void setCqgs(Double cqgs) {	    this.cqgs=cqgs;	}	public String getLrBy() {		return this.lrBy;	}	public void setLrBy(String lrBy) {		this.lrBy = lrBy;	}	public java.sql.Timestamp getLrTime() {		return this.lrTime;	}	public void setLrTime(java.sql.Timestamp lrTime) {		this.lrTime = lrTime;	}
}
