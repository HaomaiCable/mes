package com.hmmes.bean;


public class SiteScore extends BaseBean {
	public SiteScore(){
		
	}
	
	public SiteScore(Integer siteId, Integer viewNum, Integer likeNum,
			Integer shareNum, Integer clickNum, Integer collectNum,
			Integer commentNum) {
		super();
		this.siteId = siteId;
		this.viewNum = viewNum;
		this.likeNum = likeNum;
		this.shareNum = shareNum;
		this.clickNum = clickNum;
		this.collectNum = collectNum;
		this.commentNum = commentNum;
	}
		private Integer siteId;//   站点id 管理site_main.id	private Integer viewNum;//   浏览次数	private Integer likeNum;//   喜欢数量	private Integer shareNum;//   分享次数	private Integer clickNum;//   点击次数	private Integer collectNum;//   收藏总数	private Integer commentNum;//   评论总数
	
	
	 public static enum ScoreType {
		VIEW(1, "viewNum"), LIKE(2, "likeNum"), SHARE(1, "shareNum"), CLICK(2,
				"clickNum"), COLLECT(1, "collectNum"), COMMENT(2, "commentNum");
		public int key;
		public String value;

		private ScoreType(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public static ScoreType get(int key) {
			ScoreType[] values = ScoreType.values();
			for (ScoreType object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}
	
		public Integer getSiteId() {	    return this.siteId;	}	public void setSiteId(Integer siteId) {	    this.siteId=siteId;	}	public Integer getViewNum() {	    return this.viewNum;	}	public void setViewNum(Integer viewNum) {	    this.viewNum=viewNum;	}	public Integer getLikeNum() {	    return this.likeNum;	}	public void setLikeNum(Integer likeNum) {	    this.likeNum=likeNum;	}	public Integer getShareNum() {	    return this.shareNum;	}	public void setShareNum(Integer shareNum) {	    this.shareNum=shareNum;	}	public Integer getClickNum() {	    return this.clickNum;	}	public void setClickNum(Integer clickNum) {	    this.clickNum=clickNum;	}	public Integer getCollectNum() {	    return this.collectNum;	}	public void setCollectNum(Integer collectNum) {	    this.collectNum=collectNum;	}	public Integer getCommentNum() {	    return this.commentNum;	}	public void setCommentNum(Integer commentNum) {	    this.commentNum=commentNum;	}
	
}
