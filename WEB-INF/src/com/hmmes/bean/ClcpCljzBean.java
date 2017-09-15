package com.hmmes.bean;


public class ClcpCljzBean extends BaseBean {

		private Integer id;//   主键	private Integer cpid;//    产品信息库关联id clcp.id	private String clmc;//  材料名称	private Double cljz;//   材料净重

	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getCpid() {	    return this.cpid;	}	public void setCpid(Integer cpid) {	    this.cpid=cpid;	}	public String getClmc() {	    return this.clmc;	}	public void setClmc(String clmc) {	    this.clmc=clmc;	}	public Double  getCljz() {	    return this.cljz;	}	public void setCljz(Double cljz) {	    this.cljz=cljz;	}
}
