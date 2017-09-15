package com.hmmes.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class XsddModel extends BaseModel {
	
    private Integer id;//   主键
	private java.sql.Date xdrq;//   计划下达日期
	private String jhbh;//   计划编号
	private String ywy;//   业务员	
	private Integer row;//    行号，订单明细行号
	private Integer state;//    状态，1=暂停，2=作废，0=正常
	private String xh;//   产品型号
	private String gg;//   产品规格
	private String dy;//   电压等级
	private String gy;//   工艺类型
	private String dw;//   计量单位
	private Double sl;//   计划数量
	private java.sql.Date jhrq_kh;//   客户要求的交货日期
	private java.sql.Date jhrq;//   生产管理部确认的计划交货日期
	private String jsyq;//   技术要求
	private String ph;//   批号
	private String ywyy;//   延误原因
	private String ywjt;//   延误机台
	private java.sql.Date ecjhq;//   二次交货期
    private Integer xdjt;//    是否分解下达机台，1=已分解下达，0=未分解下达
	private String createBy;//   创建人	
	private java.sql.Timestamp createTime;//   创建时间
	private String updateBy;//   修改人	
	private java.sql.Timestamp updateTime;//   修改时间
	private Integer deleted;//   是否删除,0=未删除，1=已删除
    private java.sql.Date maxWgrq;//最后完工日期，
    private String wgzs;//最后完工的完工主手,字符型
	private Integer qbRk;//是否全部入库，1=计划已全部入库完毕,,2==未完工,已加入到数据库(xsdd)中

    private Date fromxdrq;//   查询用，销售订单开始日期
    private Date toxdrq;//   查询用，销售订单结束日期
    private Date wgrq;//   查询用，最后的完工日期
    private Date fromjhrq;//   查询用，交货日期开始日期
    private Date tojhrq;//   查询用，交货日期结束日期
    private Date frommaxWgrq_Search;//   查询用，最后完工日期开始日期
    private Date tomaxWgrq_Search;//   查询用，最后完工日期结束日期

	public Date getFromxdrq() {
	    return this.fromxdrq;
	}
	public void setFromxdrq(Date fromxdrq) {
	    this.fromxdrq=fromxdrq;
	}
	public Date getToxdrq() {
	    return this.toxdrq;
	}
	public void setToxdrq(Date toxdrq) {
	    this.toxdrq=toxdrq;
	}	
	public Date getWgrq() {
	    return this.wgrq;
	}
    public void setWgrq(Date wgrq) {
	    this.wgrq=wgrq;
	}
	public Date getFromjhrq() {
	    return this.fromjhrq;
	}
	public void setFromjhrq(Date fromjhrq) {
	    this.fromjhrq=fromjhrq;
	}
	public Date getTojhrq() {
	    return this.tojhrq;
	}
	public void setTojhrq(Date tojhrq) {
	    this.tojhrq=tojhrq;
	}	
	public Date getFrommaxWgrq_Search() {
	    return this.frommaxWgrq_Search;
	}
	public void setFrommaxWgrq_Search(Date frommaxWgrq_Search) {
	    this.frommaxWgrq_Search=frommaxWgrq_Search;
	}	
	public Date getTomaxWgrq_Search() {
	    return this.tomaxWgrq_Search;
	}
	public void setTomaxWgrq_Search(Date tomaxWgrq_Search) {
	    this.tomaxWgrq_Search=tomaxWgrq_Search;
	}	
	public java.sql.Date getMaxWgrq() {
		return this.maxWgrq;
	}
	public void setMaxWgrq(java.sql.Date maxWgrq) {
		this.maxWgrq = maxWgrq;
	}
	public String getWgzs() {
		return this.wgzs;
	}
	public void setWgzs(String wgzs) {
		this.wgzs = wgzs;
	}
	public Integer getQbRk() {
		return this.qbRk;
	}
	public void setQbRk(Integer qbRk) {
		this.qbRk = qbRk;
	}	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public java.sql.Date getXdrq() {
	    return this.xdrq;
	}
	public void setXdrq(java.sql.Date xdrq) {
	    this.xdrq=xdrq;
	}
	public String getJhbh() {
		return this.jhbh;
	}
	public void setJhbh(String jhbh) {
		this.jhbh = jhbh;
	}
	public String getYwy() {
		return this.ywy;
	}
	public void setYwy(String ywy) {
		this.ywy = ywy;
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
	public String getXh() {
	    return this.xh;
	}
	public void setXh(String xh) {
	    this.xh=xh;
	}
	public String getGg() {
	    return this.gg;
	}
	public void setGg(String gg) {
	    this.gg=gg;
	}
	public String getDy() {
	    return this.dy;
	}
	public void setDy(String dy) {
	    this.dy=dy;
	}
	public String getGy() {
	    return this.gy;
	}
	public void setGy(String gy) {
	    this.gy=gy;
	}
	public String getDw() {
	    return this.dw;
	}
	public void setDw(String dw) {
	    this.dw=dw;
	}
	public Double getSl() {
	    return this.sl;
	}
	public void setSl(Double sl) {
	    this.sl=sl;
	}
	public java.sql.Date getJhrq_kh() {
	    return this.jhrq_kh;
	}
	public void setJhrq_kh(java.sql.Date jhrq_kh) {
	    this.jhrq_kh=jhrq_kh;
	}
	public java.sql.Date getJhrq() {
	    return this.jhrq;
	}
	public void setJhrq(java.sql.Date jhrq) {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		//java.sql.Date jhrq1=null; 
   		//try {
  	    //     jhrq1=new java.sql.Date(sdf.parse(sdf.format(jhrq)).getTime());
        //} catch (ParseException e) {
        //     e.printStackTrace();
        //}
		this.jhrq=jhrq;
	}
	public String getJsyq() {
	    return this.jsyq;
	}
	public void setJsyq(String jsyq) {
	    this.jsyq=jsyq;
	}
	public String getPh() {
	    return this.ph;
	}
	public void setPh(String ph) {
	    this.ph=ph;
	}
	public void setYwyy(String ywyy) {
	    this.ywyy=ywyy;
	}
	public String getYwyy() {
	    return this.ywyy;
	}
	public void setYwjt(String ywjt) {
	    this.ywjt=ywjt;
	}
	public String getYwjt() {
	    return this.ywjt;
	}
	public java.sql.Date getEcjhq() {
	    return this.ecjhq;
	}
	public void setEcjhq(java.sql.Date ecjhq) {
	    this.ecjhq=ecjhq;
	}
	public Integer getXdjt() {
	    return this.xdjt;
	}
	public void setXdjt(Integer xdjt) {
	    this.xdjt=xdjt;
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
	public Integer getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
}
