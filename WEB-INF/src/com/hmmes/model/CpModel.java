package com.hmmes.model;

public class CpModel extends BaseModel {
	
	
    private Integer id;//   主键
	private String cplb;//   产品类别
	private String cpxh;//   产品型号
	private String cpgg;//   产品规格
	private String cpdy;//   电压等级
	private String cpdw;//   计量单位
	private String xsjg;//   线芯结构
    private Integer zxs;//   主线芯芯数
	private Double zzl;//   主线芯单位长度重量
    private Integer lxs;//   零线芯芯数
	private Double lzl;//   零线芯单位长度重量
	private Double cpgs;//   产品终端工时

    public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getCplb() {
		return this.cplb;
	}
	public void setCplb(String cplb) {
		this.cplb = cplb;
	}

	public String getCpxh() {
	    return this.cpxh;
	}
	public void setCpxh(String cpxh) {
	    this.cpxh=cpxh;
	}
	public String getCpgg() {
	    return this.cpgg;
	}
	public void setCpgg(String cpgg) {
	    this.cpgg=cpgg;
	}
	public String getCpdy() {
	    return this.cpdy;
	}
	public void setCpdy(String cpdy) {
	    this.cpdy=cpdy;
	}

	public String getCpdw() {
	    return this.cpdw;
	}
	public void setCpdw(String cpdw) {
	    this.cpdw=cpdw;
	}
	public String getXsjg() {
	    return this.xsjg;
	}
	public void setXsjg(String xsjg) {
	    this.xsjg=xsjg;
	}
	public Integer getZxs() {
		return this.zxs;
	}
	public void setZxs(Integer zxs) {
		this.zxs = zxs;
	}
	public Double getZzl() {
	    return this.zzl;
	}
	public void setZzl(Double zzl) {
	    this.zzl=zzl;
	}

	public Integer getLxs() {
		return this.lxs;
	}
	public void setLxs(Integer lxs) {
		this.lxs = lxs;
	}
	public Double getLzl() {
	    return this.lzl;
	}
	public void setLzl(Double lzl) {
	    this.lzl=lzl;
	}
	public Double getCpgs() {
	    return this.cpgs;
	}
	public void setCpgs(Double cpgs) {
	    this.cpgs=cpgs;
	}

	
}
