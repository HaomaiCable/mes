package com.hmmes.model;

import java.util.Date;

public class XsddBgModel extends BaseModel {
	
	
    private Integer id;//   主键
	private Integer ddid;//   销售订单ID，xsdd.id
	private String bh;//   销售订单变更单编号
	private Integer row;//    变更明细行号
	private String jhbh;//   销售订单编号
	private Integer jhbhrow;//    销售订单明细行号
	private String field;//   变更项目
	private String oldContent;//  变更前内容
	private String newContent;//   变更后内容
	private String createBy;//   变更单创建人	
	private java.sql.Timestamp createTime;//  变更单创建时间
	private Integer checked;//    变更单审核，1=已审核
	private String checkBy;//   审核人	
	private java.sql.Timestamp checkTime;//   审核时间
	private Integer accept;//    变更单生产确认，1=已确认
	private String acceptBy;//   生产确认人	
	private java.sql.Timestamp acceptTime;//   生产确认时间

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
	public Integer getDdid() {
		return this.ddid;
	}
	public void setDdid(Integer ddid) {
		this.ddid = ddid;
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
	public Integer getChecked() {
		return this.checked;
	}
	public void setChecked(Integer checked) {
		this.checked = checked;
	}
	public String getCheckBy() {
		return this.checkBy;
	}
	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}
	public java.sql.Timestamp getCheckTime() {
		return this.checkTime;
	}
	public void setCheckTime(java.sql.Timestamp checkTime) {
		this.checkTime = checkTime;
	}

	
}
