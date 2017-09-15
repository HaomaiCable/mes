package com.hmmes.bean;

import java.util.List;
import java.util.Date;


public class XsddBean extends BaseBean {
	
	private Integer id;//   主键	
	private java.sql.Date xdrq;//   计划下达日期
	private String jhbh;//   计划编号
	private String ywy;//   业务员	
	private Integer row;//    行号，订单明细行号
	private Integer state;//    状态，2=暂停，3=作废，1=正常
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

	private String deleteFlag; //删除标记，与数据库字段无关 1=删除,其他不删除

    private int subCountWg;//完工汇报记录数,与数据库字段无关
    private int subCountRk;//入库汇报记录数,与数据库字段无关
    private int subCountBg;//变更记录数,与数据库字段无关
    private int subCountJtjh;//机台计划记录数,与数据库字段无关

	//关联质量部完工汇报xsdd_wghb

    private Double sumWgsl;//合计完工数量,与数据库字段无关
    private String sumWgslds;//分段的完工数量,字符型，与数据库字段无关
    private String sumXpgg;//所使用的线盘汇总,字符型，与数据库字段无关
    private Double sumCmsl;//合计长米割去数量,与数据库字段无关

    //关联统计入库汇报xsdd_rkhb
    private java.sql.Date maxRkrq;//最后入库日期，与数据库字段无关
	private Double sumRksl;//合计入库数量,与数据库字段无关
    private String sumRkslds;//分段的入库明细,字符型，与数据库字段无关
    private Integer qbRk;//是否全部入库，1=计划已全部入库完毕,2==未完工,已加入到数据库(xsdd)中
    private long cqts;//超期天数,与数据库字段无关

	private java.sql.Date wgrq;//   完工日期，与数据库字段无关
	private Double wgsl;//   完工数量，与数据库字段无关
	private String wgslss;//   备注性说明，与数据库字段无关
	private String czg;//   完工机台主手，与数据库字段无关
	private String xpgg;//   线盘规格，与数据库字段无关
	private Double cmsl;//   长米割去数量，与数据库字段无关
	
	//质量部完工汇报
	private List<XsddWghbBean> wghbs;
	//统计入库汇报
	private List<XsddRkhbBean> rkhbs;
	//订单变更
	private List<XsddBgBean> bgs;


	//质量部	
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
	public Double getSumWgsl() {
	    return this.sumWgsl;
	}
	public void setSumWgsl(Double sumWgsl) {
	    this.sumWgsl=sumWgsl;
	}
	public String getSumWgslds() {
		return this.sumWgslds;
	}
	public void setSumWgslds(String sumWgslds) {
		this.sumWgslds = sumWgslds;
	}
	public void setSumXpgg(String sumXpgg) {
		this.sumXpgg = sumXpgg;
	}
	public String getSumXpgg() {
		return this.sumXpgg;
	}
	public Double getSumCmsl() {
	    return this.sumCmsl;
	}
	public void setSumCmsl(Double sumCmsl) {
	    this.sumCmsl=sumCmsl;
	}

	//统计
	public java.sql.Date getMaxRkrq() {
		return this.maxRkrq;
	}
	public void setMaxRkrq(java.sql.Date maxRkrq) {
		this.maxRkrq = maxRkrq;
	}
	public Double getSumRksl() {
	    return this.sumRksl;
	}
	public void setSumRksl(Double sumRksl) {
	    this.sumRksl=sumRksl;
	}
	public String getSumRkslds() {
		return this.sumRkslds;
	}
	public void setSumRkslds(String sumRkslds) {
		this.sumRkslds = sumRkslds;
	}
	public Integer getQbRk() {
		return this.qbRk;
	}
	public void setQbRk(Integer qbRk) {
		this.qbRk = qbRk;
	}
	public long getCqts() {
		return this.cqts;
	}
	public void setCqts(long cqts) {
		this.cqts = cqts;
	}

	public java.sql.Date getWgrq() {
	    return this.wgrq;
	}
	public void setWgrq(java.sql.Date wgrq) {
	    this.wgrq=wgrq;
	}
	public Double getWgsl() {
	    return this.wgsl;
	}
	public void setWgsl(Double wgsl) {
	    this.wgsl=wgsl;
	}
	public String getWgslss() {
	    return this.wgslss;
	}
	public void setWgslss(String wgslss) {
	    this.wgslss=wgslss;
	}

	public String getCzg() {
	    return this.czg;
	}
	public void setCzg(String czg) {
	    this.czg=czg;
	}
	public String getXpgg() {
	    return this.xpgg;
	}
	public void setXpgg(String xpgg) {
	    this.xpgg=xpgg;
	}
	public Double getCmsl() {
	    return this.cmsl;
	}
	public void setCmsl(Double cmsl) {
	    this.cmsl=cmsl;
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
	public int getSubCountWg() {
		return this.subCountWg;
	}
	public void setSubCountWg(int subCountWg) {
		this.subCountWg = subCountWg;
	}	
	public int getSubCountRk() {
		return this.subCountRk;
	}
	public void setSubCountRk(int subCountRk) {
		this.subCountRk = subCountRk;
	}
	public int getSubCountBg() {
		return this.subCountBg;
	}
	public void setSubCountBg(int subCountBg) {
		this.subCountBg = subCountBg;
	}
	public int getSubCountJtjh() {
		return this.subCountJtjh;
	}
	public void setSubCountJtjh(int subCountJtjh) {
		this.subCountJtjh = subCountJtjh;
	}

				
	public List<XsddWghbBean> getWghbs() {
		return this.wghbs;
	}
	public void setWghbs(List<XsddWghbBean> wghbs) {
		this.wghbs = wghbs;
	}	
	public List<XsddRkhbBean> getRkhbs() {
		return this.rkhbs;
	}
	public void setRkhbs(List<XsddRkhbBean> rkhbs) {
		this.rkhbs = rkhbs;
	}	
	public List<XsddBgBean> getBgs() {
		return this.bgs;
	}
	public void setBgs(List<XsddBgBean> bgs) {
		this.bgs = bgs;
	}	


}
