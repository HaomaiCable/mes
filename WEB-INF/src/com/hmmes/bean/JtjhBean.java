package com.hmmes.bean;

import java.util.List;
import java.sql.Timestamp;
import java.sql.Date;

public class JtjhBean extends BaseBean {
	
	private Integer id;//   主键	
	private Integer row;//    机台计划，工序顺序号，排序用
	private Integer ddid;//    销售订单关联ID xsdd.id
	private java.sql.Date xdrq;//   机台计划下达日期
	private String jhbh;//   机台计划编号
	private Integer state;//    状态，2=暂停，3=作废，1=正常
	private String sbmc;//   工序的设备名称
	private String sbmcdek;//   工时定额库查找的设备名称
	private String iszl;//   主=主线，零=零线
	private String gxxh;//   工序的产品型号
	private String gxxh_o;//   保留工序明细库的产品型号，便于查找、计算工时
	private String gxgg;//   工序的产品规格
	private String gxgg_o;//   保留工序明细库的产品规格，便于查找、计算工时
	private String gxdy;//   工序的电压等级
	private String gxdy_o;//   保留工序明细库的电压等级，便于以后计算工时
	private String gxgy;//   工序的工艺
	private String gxdw;//   工序的计量单位，同销售订单计量单位，xsdd.dw
	private String gxjsyq;//   技术要求，同销售订单技术要求,xsdd.jsyq
	private String gxph;//   批号，同销售订单技术要求,xsdd.ph
	private String gxlb;//   类别，计算工时用
	private Double jhsl;//   工序的计划数量
	private Double jhsl_o;//   保留乘以芯数的计划数量，便于查找、计算工时，控制完工汇报数量
	private String  jhsl_xs;//   字符串型，用于机台计划显示和打印
	private String  wgflag;//   字符串型，完工标记，分为：完工，未完工
	private java.sql.Date jhrq;//   工序完工日期
	private String createBy;//   创建人	
	private java.sql.Timestamp createTime;//   创建时间
	private String updateBy;//   修改人	
	private java.sql.Timestamp updateTime;//   修改时间
	private Integer deleted;//   是否删除,0=未删除，1=已删除
    private String gd;//工段名称
	private Integer dtts;//    交货期增加天数

	//完工汇报
	private List<JtjhWghbBean> wghbs;
	//计划变更
	private List<JtjhBgBean> bgs;

    private java.sql.Date maxWgrq;//最后完工日期，与数据库字段无关 
    private Double sumWgsl;//合计完工数量,与数据库字段无关
    private Double wwgsl;//尚未完工数量,与数据库字段无关，=jhsl-sumWgsl
    private String sumWgslds;//分段的完工数量,字符型，与数据库字段无关
	private Integer qbWg;//是否全部完成，1=计划已全部完成,与数据库字段无关
	private long cqts;//超期天数,与数据库字段无关
	
	//批量完工汇报用,与jtjh数据库无关
	private java.sql.Date wgrq;//   完工日期,与数据库字段无关
	private Integer bc;//   班次，1=白班，2==夜班。与数据库字段无关
	private Double wgsl;//   完工数量,与数据库字段无关
	private String wgsm;//   完工数量文字性，字符型,与数据库字段无关
	private Integer wg;//   全部完工，1=全部完工，当计划完工数量因为有库存等导致数量小于计划数量时，填写1,,与数据库字段无关


	private int subCountWg;//完工汇报记录数,与数据库字段无关
    private int subCountBg;//变更记录数,与数据库字段无关

/**
    public JtjhBean() {
 
    }
 
    public JtjhBean(Integer id, Integer row, Integer ddid, Date xdrq,String jhbh,Integer state,String sbmc,String sbmcdek
		,String iszl,String gxxh,String gxxh_o,String gxgg,	String gxgg_o,String gxdy,String gxdy_o,String gxgy,String gxdw,String gxjsyq
		,String gxph,String gxlb,Double jhsl,Double jhsl_o,String  jhsl_xs,String  wgflag,java.sql.Date jhrq,String createBy
		,java.sql.Timestamp createTime,String updateBy,java.sql.Timestamp updateTime,Integer deleted,String gd,Integer dtts
		,java.sql.Date maxWgrq,Double sumWgsl,Double wwgsl,String sumWgslds,Integer qbWg,long cqts) {
        super();
        this.id = id;
        this.row = row;
        this.ddid = ddid;
        this.xdrq = xdrq;
        this.jhbh = jhbh;
        this.state = state;
        this.sbmc = sbmc;
        this.sbmcdek = sbmcdek;
        this.iszl = iszl;
        this.gxxh = gxxh;
        this.gxxh_o = gxxh_o;
        this.gxgg = gxgg;
        this.gxgg_o = gxgg_o;
        this.gxdy = gxdy;
        this.gxdy_o = gxdy_o;
        this.gxgy = gxgy;
        this.gxdw = gxdw;
        this.gxjsyq = gxjsyq;
        this.gxph = gxph;
        this.gxlb = gxlb;
        this.jhsl = jhsl;
        this.jhsl_o = jhsl_o;
        this.jhsl_xs = jhsl_xs;
        this.wgflag = wgflag;
        this.jhrq = jhrq;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.gd = gd;
        this.dtts = dtts;
        this.maxWgrq = maxWgrq;
        this.sumWgsl = sumWgsl;
        this.wwgsl = wwgsl;
        this.sumWgslds = sumWgslds;
        this.qbWg = qbWg;
        this.cqts = cqts;

    }
*/
	public List<JtjhWghbBean> getWghbs() {
		return this.wghbs;
	}
	public void setWghbs(List<JtjhWghbBean> wghbs) {
		this.wghbs = wghbs;
	}
	public List<JtjhBgBean> getBgs() {
		return this.bgs;
	}
	public void setBgs(List<JtjhBgBean> bgs) {
		this.bgs = bgs;
	}	

	public java.sql.Date getMaxWgrq() {
		return this.maxWgrq;
	}
	public void setMaxWgrq(java.sql.Date maxWgrq) {
		this.maxWgrq = maxWgrq;
	}
	public Double getSumWgsl() {
	    return this.sumWgsl;
	}
	public void setSumWgsl(Double sumWgsl) {
	    this.sumWgsl=sumWgsl;
	}
	public Double getWwgsl() {
	    return this.wwgsl;
	}
	public void setWwgsl(Double wwgsl) {
	    this.wwgsl=wwgsl;
	}
	public String getSumWgslds() {
		return this.sumWgslds;
	}
	public void setSumWgslds(String sumWgslds) {
		this.sumWgslds = sumWgslds;
	}

	public Integer getQbWg() {
		return this.qbWg;
	}
	public void setQbWg(Integer qbWg) {
		this.qbWg = qbWg;
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
	public Integer getBc() {
	    return this.bc;
	}
	public void setBc(Integer bc) {
	    this.bc=bc;
	}	
	public Double getWgsl() {
	    return this.wgsl;
	}
	public void setWgsl(Double wgsl) {
	    this.wgsl=wgsl;
	}
	public String getWgsm() {
	    return this.wgsm;
	}
	public void setWgsm(String wgsm) {
	    this.wgsm=wgsm;
	}

	public Integer getWg() {
	    return this.wg;
	}
	public void setWg(Integer wg) {
	    this.wg=wg;
	}	

	public int getSubCountWg() {
		return this.subCountWg;
	}
	public void setSubCountWg(int subCountWg) {
		this.subCountWg = subCountWg;
	}	
	
	public int getSubCountBg() {
		return this.subCountBg;
	}
	public void setSubCountBg(int subCountBg) {
		this.subCountBg = subCountBg;
	}



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
	public Integer getDdid() {
	    return this.ddid;
	}
	public void setDdid(Integer ddid) {
	    this.ddid=ddid;
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
	public Integer getState() {
	    return this.state;
	}
	public void setState(Integer state) {
	    this.state=state;
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
	public String getSbmcdek() {
		return this.sbmcdek;
	}
	public void setSbmcdek(String sbmcdek) {
		this.sbmcdek = sbmcdek;
	}
	public String getIszl() {
	    return this.iszl;
	}
	public void setIszl(String iszl) {
	    this.iszl=iszl;
	}
	public String getGxxh() {
	    return this.gxxh;
	}
	public void setGxxh(String gxxh) {
	    this.gxxh=gxxh;
	}
	public String getGxxh_o() {
	    return this.gxxh_o;
	}
	public void setGxxh_o(String gxxh_o) {
	    this.gxxh_o=gxxh_o;
	}
	public String getGxgg() {
	    return this.gxgg;
	}
	public void setGxgg(String gxgg) {
	    this.gxgg=gxgg;
	}
	public String getGxgg_o() {
	    return this.gxgg_o;
	}
	public void setGxgg_o(String gxgg_o) {
	    this.gxgg_o=gxgg_o;
	}
	public String getGxdy() {
	    return this.gxdy;
	}
	public void setGxdy(String gxdy) {
	    this.gxdy=gxdy;
	}
	public String getGxdy_o() {
	    return this.gxdy_o;
	}
	public void setGxdy_o(String gxdy_o) {
	    this.gxdy_o=gxdy_o;
	}
	public String getGxgy() {
	    return this.gxgy;
	}
	public void setGxgy(String gxgy) {
	    this.gxgy=gxgy;
	}
	public String getGxdw() {
	    return this.gxdw;
	}
	public void setGxdw(String gxdw) {
	    this.gxdw=gxdw;
	}
	public String getGxjsyq() {
	    return this.gxjsyq;
	}
	public void setGxjsyq(String gxjsyq) {
	    this.gxjsyq=gxjsyq;
	}
	public String getGxph() {
	    return this.gxph;
	}
	public void setGxph(String gxph) {
	    this.gxph=gxph;
	}
	public String getGxlb() {
	    return this.gxlb;
	}
	public void setGxlb(String gxlb) {
	    this.gxlb=gxlb;
	}

	public Double getJhsl() {
	    return this.jhsl;
	}
	public void setJhsl(Double jhsl) {
	    this.jhsl=jhsl;
	}
	public Double getJhsl_o() {
	    return this.jhsl_o;
	}
	public void setJhsl_o(Double jhsl_o) {
	    this.jhsl_o=jhsl_o;
	}
	public String getJhsl_xs() {
	    return this.jhsl_xs;
	}
	public void setJhsl_xs(String jhsl_xs) {
	    this.jhsl_xs=jhsl_xs;
	}
	public String getWgflag() {
	    return this.wgflag;
	}
	public void setWgflag(String wgflag) {
	    this.wgflag=wgflag;
	}
	public java.sql.Date getJhrq() {
	    return this.jhrq;
	}
	public void setJhrq(java.sql.Date jhrq) {
	    this.jhrq=jhrq;
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
	public Integer getDtts() {
		return this.dtts;
	}
	public void setDtts(Integer dtts) {
		this.dtts = dtts;
	}


}
