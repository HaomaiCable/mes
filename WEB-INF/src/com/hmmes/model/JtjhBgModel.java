package com.hmmes.model;

import java.util.Date;

public class JtjhBgModel extends BaseModel {
	
	
	
private Integer id;//   主键
	
	private Integer jhid;//   机台计划ID，xsdd.id
	private String bh;//   机台计划变更单编号
	private Integer row;//    变更明细行号
	private String gd;//  变更设备所在工段
	private String sbmc;//  变更的设备名称
	private String jhbh;//   机台计划编号
	private Integer jhbhrow;//    机台计划明细行号
	private String field;//   变更项目
	private String oldContent;//  变更前内容
	private String newContent;//   变更后内容
	private String createBy;//   变更单创建人	
	private java.sql.Timestamp createTime;//  变更单创建时间
	private Integer accept;//    变更单工段确认，1=已确认
	private String acceptBy;//   工段确认人	
	private java.sql.Timestamp acceptTime;//   工段确认时间

    private Date frombgrq;//   查询用，变更单开始日期
    private Date tobgrq;//   查询用，变更单结束日期

	public Date getFrombgrq() {
	    return this.frombgrq;
	}
	public void setFrombgrq(Date frombgrq) {
	    this.frombgrq=frombgrq;
	}
	public Date getTobgrq() {
	    return this.tobgrq;
	}
	public void setTobgrq(Date tobgrq) {
	    this.tobgrq=tobgrq;
	}	

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getJhid() {
		return this.jhid;
	}
	public void setJhid(Integer jhid) {
		this.jhid = jhid;
	}

	public String getBh() {
		return this.bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public Integer getRow() {
		return this.row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public String getGd() {
		return this.gd;
	}
	public void setGd(String gd) {
		this.gd = gd;
	}
	public String getSbmc() {
		return this.sbmc;
	}
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}

	public String getJhbh() {
		return this.jhbh;
	}
	public void setJhbh(String jhbh) {
		this.jhbh = jhbh;
	}

	public Integer getJhbhrow() {
		return this.jhbhrow;
	}
	public void setJhbhrow(Integer jhbhrow) {
		this.jhbhrow = jhbhrow;
	}


	public String getField() {
		return this.field;
	}
	public void setField(String field) {
		this.field =field;
	}


	public String getOldContent() {
	    return this.oldContent;
	}
	public void setOldContent(String oldContent) {
	    this.oldContent=oldContent;
	}
	public String getNewContent() {
	    return this.newContent;
	}
	public void setNewContent(String newContent) {
	    this.newContent=newContent;
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
	public Integer getAccept() {
		return this.accept;
	}
	public void setAccept(Integer accept) {
		this.accept = accept;
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
