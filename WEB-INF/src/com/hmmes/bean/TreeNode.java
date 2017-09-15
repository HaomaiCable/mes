package com.hmmes.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode {

	private String id;
	
	private Integer dataId;
	
	private String text;
	
	private String url;
	
	private String state;//value closed,open
	
	private boolean checked; //
	
	private Integer parentId;
	
	private Map<String,Object> attributes = new HashMap<String, Object>();
	
	private List<TreeNode>  children;
	
	

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public  Integer getParentId() {
		return this.parentId;
	}

	public void setParentId( Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getDataId() {
		return this.dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<TreeNode> getChildren() {
		return this.children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	
}
