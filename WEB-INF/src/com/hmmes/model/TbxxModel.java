package com.hmmes.model;

import java.util.Date;

public class TbxxModel extends BaseModel {
	
    private Integer id;//   主键
	private Integer row;//    行号，招标物资明细行号
	private String ggbh;//   招标公告编号
	private Integer gysId;//    供应商ID
	private Integer state;//    状态，2=暂停，3=作废，1=正常
	private Integer deleted;//   是否删除,0=未删除，1=已删除

	private String wlfl;//   采购物资分类	
	private String wlmc;//   采购物流名称
	private String wlgg;//   物料规格
	private String wldw;//   计量单位
	private Double wlsl;//   需采购数量
	private String jsyq;//   采购技术要求
	private java.sql.Date jhrq_xq;//   需求的交货日期
	private Double tbsl;//   投标(中标)数量
	private Double dj;//   投标单价
	private Double je;//   金额
	private java.sql.Date jhrq;//   供应商承诺交货日期
	private String tbsm;//   投标说明性信息

	private String createBy;//   创建人	
	private java.sql.Timestamp createTime;//   创建时间
	private String updateBy;//   修改人	
	private java.sql.Timestamp updateTime;//   修改时间
	private Integer zb;//    中标标记，1==中标



	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRow() {
	    return this.row;
	}
	public void setRow(Integer row) {
	    this.row=row;
	}
	public String getGgbh() {
		return this.ggbh;
	}
	public void setGgbh(String ggbh) {
		this.ggbh = ggbh;
	}

	public Integer getGysId() {
	    return this.gysId;
	}
	public void setGysId(Integer gysId) {
	    this.gysId=gysId;
	}


	public Integer getState() {
	    return this.state;
	}
	public void setState(Integer state) {
	    this.state=state;
	}

	public Integer getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}


	public String getWlfl() {
	    return this.wlfl;
	}
	public void setWlfl(String wlfl) {
	    this.wlfl=wlfl;
	}
	public String getWlmc() {
	    return this.wlmc;
	}
	public void setWlmc(String wlmc) {
	    this.wlmc=wlmc;
	}
	public String getWlgg() {
	    return this.wlgg;
	}
	public void setWlgg(String wlgg) {
	    this.wlgg=wlgg;
	}
	public String getWldw() {
	    return this.wldw;
	}
	public void setWldw(String wldw) {
	    this.wldw=wldw;
	}
	public Double getWlsl() {
	    return this.wlsl;
	}
	public void setWlsl(Double wlsl) {
	    this.wlsl=wlsl;
	}
	public String getJsyq() {
	    return this.jsyq;
	}
	public void setJsyq(String jsyq) {
	    this.jsyq=jsyq;
	}
	public java.sql.Date getJhrq_xq() {
	    return this.jhrq_xq;
	}
	public void setJhrq_xq(java.sql.Date jhrq_xq) {
	    this.jhrq_xq=jhrq_xq;
	}
	public Double getTbsl() {
	    return this.tbsl;
	}
	public void setTbsl(Double tbsl) {
	    this.tbsl=tbsl;
	}
	public Double getDj() {
	    return this.dj;
	}
	public void setDj(Double dj) {
	    this.dj=dj;
	}
	public Double getJe() {
	    return this.je;
	}
	public void setJe(Double je) {
	    this.je=je;
	}
	public java.sql.Date getJhrq() {
	    return this.jhrq;
	}
	public void setJhrq(java.sql.Date jhrq) {
	    this.jhrq=jhrq;
	}
	public String getTbsm() {
	    return this.tbsm;
	}
	public void setTbsm(String tbsm) {
	    this.tbsm=tbsm;
	}
	public Integer getZb() {
		return this.zb;
	}
	public void setZb(Integer zb) {
		this.zb = zb;
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
	public String getUpdateBy() {
		return this.updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public java.sql.Timestamp getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}



	
}
