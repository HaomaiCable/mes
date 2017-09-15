package com.hmmes.model;

import java.util.Date;


public class ZbggModel extends BaseModel {
	
	
private Integer id;//   主键
	
	private java.sql.Date fbrq;//   招标公告发布日期
	private String ggbh;//   招标公告编号
	private Integer row;//    行号，招标物资明细行号
	private Integer state;//    状态，2=暂停，3=作废，1=正常
    private Integer kb;//    是否开标，1=已开标，0=未开标
	private Integer deleted;//   是否删除,0=未删除，1=已删除
	private java.sql.Timestamp yxrq;//   招标公告的有效日期
	private String wlfl;//   采购物资分类	
	private String wlmc;//   采购物流名称
	private String wlgg;//   物料规格
	private String wldw;//   计量单位
	private Double wlsl;//   需采购数量
	private String jsyq;//   采购技术要求
	private java.sql.Date jhrq_xq;//   需求的交货日期

	private String createBy;//   创建人	
	private java.sql.Timestamp createTime;//   创建时间
	private String updateBy;//   修改人	
	private java.sql.Timestamp updateTime;//   修改时间
	private Integer qd;//    确定供应商，1==已确定
	private String qdBy;//   中标确定人	
	private java.sql.Timestamp qdTime;//   中标确定时间
	private Integer sp;//    审批确定的供应商，1==已审批
	private String spBy;//   审批人	
	private java.sql.Timestamp spTime;//   审批时间

    private Date fromfbrq;//   查询用，招标公告发布开始日期
    private Date tofbrq;//   查询用，招标公告发布结束日期
    private Date fromyxrq;//   查询用，招标公告有效时间的开始日期
    private Date toyxrq;//   查询用，招标公告有效时间的结束日期


	public Date getFromfbrq() {
	    return this.fromfbrq;
	}
	public void setFromfbrq(Date fromfbrq) {
	    this.fromfbrq=fromfbrq;
	}
	public Date getTofbrq() {
	    return this.tofbrq;
	}
	public void setTofbrq(Date tofbrq) {
	    this.tofbrq=tofbrq;
	}	

	public Date getFromyxrq() {
	    return this.fromyxrq;
	}
	public void setFromyxrq(Date fromyxrq) {
	    this.fromyxrq=fromyxrq;
	}
	public Date getToyxrq() {
	    return this.toyxrq;
	}
	public void setToyxrq(Date toyxrq) {
	    this.toyxrq=toyxrq;
	}	
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public java.sql.Date getFbrq() {
	    return this.fbrq;
	}
	public void setFbrq(java.sql.Date fbrq) {
	    this.fbrq=fbrq;
	}
	public String getGgbh() {
		return this.ggbh;
	}
	public void setGgbh(String ggbh) {
		this.ggbh = ggbh;
	}

	public Integer getRow() {
	    return this.row;
	}
	public void setRow(Integer row) {
	    this.row=row;
	}
	public Integer getState() {
	    return this.state;
	}
	public void setState(Integer state) {
	    this.state=state;
	}
	public Integer getKb() {
	    return this.kb;
	}
	public void setKb(Integer kb) {
	    this.kb=kb;
	}
	public Integer getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public java.sql.Timestamp getYxrq() {
		return this.yxrq;
	}
	public void setYxrq(java.sql.Timestamp yxrq) {
		this.yxrq = yxrq;
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
	public Integer getQd() {
	    return this.qd;
	}
	public void setQd(Integer qd) {
	    this.qd=qd;
	}
	public String getQdBy() {
		return this.qdBy;
	}
	public void setQdBy(String qdBy) {
		this.qdBy = qdBy;
	}

	public java.sql.Timestamp getQdTime() {
		return this.qdTime;
	}
	public void setQdTime(java.sql.Timestamp qdTime) {
		this.qdTime = qdTime;
	}
	public Integer getSp() {
	    return this.sp;
	}
	public void setSp(Integer sp) {
	    this.sp=sp;
	}
	public String getSpBy() {
		return this.spBy;
	}
	public void setSpBy(String spBy) {
		this.spBy = spBy;
	}

	public java.sql.Timestamp getSpTime() {
		return this.spTime;
	}
	public void setSpTime(java.sql.Timestamp spTime) {
		this.spTime = spTime;
	}	
}
