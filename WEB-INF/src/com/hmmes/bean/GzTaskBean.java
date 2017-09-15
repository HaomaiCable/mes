package com.hmmes.bean;

import java.util.List;

public class GzTaskBean extends BaseBean {
	
	private Integer id;//   主键	
	private java.sql.Date rq;//   工作任务下达日期
	private String tcr;//   工作任务提出人
	private Integer state;//    状态，2=暂停，3=作废，1=正常
	private String ly;//   工作任务来源
	private String cyry;//   工作任务参与人员
	private String rwName;//  工作任务名称
	private String rwContent;//   工作任务详细描述
	private String rwResult;//   工作任务结果
	private java.sql.Date wcrq_yq;//   工作任务要求完成日期
	private java.sql.Date wcrq; //工作任务实际完成日期
	private String wcResult;//   完成结果
	private String createBy; //   创建人	
	private java.sql.Timestamp createTime; //   创建时间
	private Integer report;//    汇报，2==未汇报，1==已汇报
	private java.sql.Timestamp reportTime;//   汇报 时间
	private Integer accept;//    确认，2==未确认，1==已确认
	private String acceptBy;//   确认人	
	private java.sql.Timestamp acceptTime;//   确认时间	

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public java.sql.Date getRq() {
	    return this.rq;
	}
	public void setRq(java.sql.Date rq) {
	    this.rq=rq;
	}
	public Integer getState() {
	    return this.state;
	}
	public void setState(Integer state) {
	    this.state=state;
	}


	public String getTcr() {
		return this.tcr;
	}
	public void setTcr(String tcr) {
		this.tcr = tcr;
	}

	public String getLy() {
		return this.ly;
	}
	public void setLy(String ly) {
		this.ly = ly;
	}
	public String getCyry() {
		return this.cyry;
	}
	public void setCyry(String cyry) {
		this.cyry = cyry;
	}
	public String getRwName() {
		return this.rwName;
	}
	public void setRwName(String rwName) {
		this.rwName = rwName;
	}
	public String getRwContent() {
		return this.rwContent;
	}
	public void setRwContent(String rwContent) {
		this.rwContent = rwContent;
	}
	public String getRwResult() {
		return this.rwResult;
	}
	public void setRwResult(String rwResult) {
		this.rwResult = rwResult;
	}

	public java.sql.Date getWcrq_yq() {
	    return this.wcrq_yq;
	}
	public void setWcrq_yq(java.sql.Date wcrq_yq) {
	    this.wcrq_yq=wcrq_yq;
	}
	public java.sql.Date getWcrq() {
	    return this.wcrq;
	}
	public void setWcrq(java.sql.Date wcrq) {
	    this.wcrq=wcrq;
	}
	public String getWcResult() {
		return this.wcResult;
	}
	public void setWcResult(String wcResult) {
		this.wcResult = wcResult;
	}

	public String getCreateBy() {
		return this.createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public java.sql.Timestamp getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getReport() {
	    return this.report;
	}
	public void setReport(Integer report) {
	    this.report=report;
	}


	public java.sql.Timestamp getReportTime() {
		return this.reportTime;
	}
	public void setReportTime(java.sql.Timestamp reportTime) {
		this.reportTime = reportTime;
	}
	public Integer getAccept() {
	    return this.accept;
	}
	public void setAccept(Integer accept) {
	    this.accept=accept;
	}

	public String getAcceptBy() {
		return this.acceptBy;
	}
	public void setAcceptBy(String acceptBy) {
		this.acceptBy = acceptBy;
	}
	public java.sql.Timestamp getAcceptTime() {
		return this.acceptTime;
	}
	public void setAcceptTime(java.sql.Timestamp acceptTime) {
		this.acceptTime = acceptTime;
	}


}
