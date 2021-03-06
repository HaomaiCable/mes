package com.hmmes.model;

import java.sql.Timestamp;

/**
 * 
 * @author luozejun
 *
 */
public class SiteMainModel extends BaseModel{

	private int id;// ����
	private String name;// NULL��վ���
	private String domain;//���� ����http://
	private String  link;//��ַ���� ��http://
	private Integer state;//״̬ 0���� 1���� 2�����
	private Timestamp createTime;//����ʱ��
	private Timestamp updateTime;//�޸�ʱ��
	private Integer rank;//NULL����
	private String pic;//ͼƬURL
	private Integer deleted;//ɾ��״̬ 0=δɾ�� 1=ɾ��
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	
}
