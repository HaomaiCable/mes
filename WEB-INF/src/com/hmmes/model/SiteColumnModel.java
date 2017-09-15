package com.hmmes.model;

public class SiteColumnModel extends BaseModel {
	

		private Integer id;//   主键id	private Integer siteId;//   站点id 管理site_main.id	private String name;//   栏目名称	private String link;//   连接地址	private String pic;//   图片地址	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public Integer getSiteId() {	    return this.siteId;	}	public void setSiteId(Integer siteId) {	    this.siteId=siteId;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getLink() {	    return this.link;	}	public void setLink(String link) {	    this.link=link;	}	public String getPic() {	    return this.pic;	}	public void setPic(String pic) {	    this.pic=pic;	}
	
}
