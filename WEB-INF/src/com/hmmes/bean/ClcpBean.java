package com.hmmes.bean;
import java.util.List;

public class ClcpBean extends BaseBean {

		private Integer id;//   id主键	private String cpxh;//   产品型号	private String cpgg;//   产品规格	private String cpdy;//   电压等级    private int subCountJz;//关联材料净重,与数据库字段无关   	//关联材料净重明细clcp_cljz	private List<ClcpCljzBean> cljzs;	public List<ClcpCljzBean> getCljzs() {		return this.cljzs;	}	public void setCljzs(List<ClcpCljzBean> cljzs) {		this.cljzs = cljzs;	}		public int getSubCountJz() {		return this.subCountJz;	}	public void setSubCountJz(int subCountJz) {		this.subCountJz = subCountJz;	}	

	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getCpxh() {	    return this.cpxh;	}	public void setCpxh(String cpxh) {	    this.cpxh=cpxh;	}	public String getCpgg() {	    return this.cpgg;	}	public void setCpgg(String cpgg) {	    this.cpgg=cpgg;	}	public String getCpdy() {	    return this.cpdy;	}	public void setCpdy(String cpdy) {	    this.cpdy=cpdy;	}
}
