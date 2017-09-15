package com.hmmes.model;

public class SiteScoreModel extends BaseModel {
	

		private Integer siteId;//   站点id 管理site_main.id	private Integer viewNum;//   浏览次数	private Integer likeNum;//   喜欢数量	private Integer shareNum;//   分享次数	private Integer clickNum;//   点击次数	private Integer collectNum;//   收藏总数	private Integer commentNum;//   评论总数	public Integer getSiteId() {	    return this.siteId;	}	public void setSiteId(Integer siteId) {	    this.siteId=siteId;	}	public Integer getViewNum() {	    return this.viewNum;	}	public void setViewNum(Integer viewNum) {	    this.viewNum=viewNum;	}	public Integer getLikeNum() {	    return this.likeNum;	}	public void setLikeNum(Integer likeNum) {	    this.likeNum=likeNum;	}	public Integer getShareNum() {	    return this.shareNum;	}	public void setShareNum(Integer shareNum) {	    this.shareNum=shareNum;	}	public Integer getClickNum() {	    return this.clickNum;	}	public void setClickNum(Integer clickNum) {	    this.clickNum=clickNum;	}	public Integer getCollectNum() {	    return this.collectNum;	}	public void setCollectNum(Integer collectNum) {	    this.collectNum=collectNum;	}	public Integer getCommentNum() {	    return this.commentNum;	}	public void setCommentNum(Integer commentNum) {	    this.commentNum=commentNum;	}
	
}
