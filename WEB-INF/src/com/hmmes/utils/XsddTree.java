package com.hmmes.utils;

import java.util.ArrayList;
import java.util.List;

import com.hmmes.bean.XsddBean;
import com.hmmes.bean.XsddsBean;
import com.hmmes.bean.TreeNode;

public class XsddTree {
	private final static String ROOT_ID = "root_";
	
	private final static String CHILD_ID = "child_";
	
	
	List<XsddBean> rootItems;
	List<XsddsBean> childItems;
	//List<XsddBeanBtn> childBtns;
	
	public XsddTree(List<XsddBean> rootItems,List<XsddsBean> childItems){
		this.rootItems = rootItems;
		this.childItems = childItems;
	}  
	
	//public TreeUtil(List<XsddBean> rootItems,List<XsddBean> childItems,List<XsddBeanBtn> childBtns){
	//	this.rootItems = rootItems;
	//	this.childItems = childItems;
	//	this.childBtns = childBtns;
	//}  
	
	public List<TreeNode> getTreeNode(){
		return getRootNodes();
	}
	
	/**
	 * 
	 * @param root
	 * @return
	 */
	private TreeNode RootToNode(XsddBean xsdd){
		if(xsdd == null){
			return null;
		}
		TreeNode node = new TreeNode();
		node.setId(ROOT_ID+xsdd.getId());
		node.setDataId(xsdd.getId());
		node.setText(xsdd.getJhbh());
		//node.setUrl(xsdd.getUrl());
		//node.setParentId(0);
		node.getAttributes().put("type", "0");
		node.getAttributes().put("id", xsdd.getId());
		return node;
	}
	
	
	/**
	 * 
	 * @param root
	 * @return
	 */
	
	private TreeNode ChildToNode(XsddsBean xsdds){
		if(xsdds == null){
			return null;
		}
		TreeNode node = new TreeNode();
		node.setId(CHILD_ID+xsdds.getId());
		node.setDataId(xsdds.getId());
		node.setText(xsdds.getXh()+","+xsdds.getGg());
		node.setParentId(xsdds.getBtid());
		node.getAttributes().put("type", "1");
		node.getAttributes().put("id", xsdds.getId());
		return node;
	}
  
	private List<TreeNode> getRootNodes(){
		List<TreeNode> rootNodes = new ArrayList<TreeNode>();
		for(XsddBean xsdd : rootItems){
			TreeNode node = RootToNode(xsdd);
			if(node != null){
				addChlidNodes(node);
				rootNodes.add(node);
			}
		}
		return rootNodes;
	}
	
	/**
	 * 
	 * @param xsdd
	 * @return
	 */
	private void addChlidNodes(TreeNode rootNode){
		List<TreeNode> childNodes = new ArrayList<TreeNode>();  
		for(XsddsBean xsdds : childItems){
			if(rootNode.getDataId().equals(xsdds.getBtid())){
				TreeNode node = ChildToNode(xsdds);
				//if(childBtns != null && !childBtns.isEmpty()){
				//	addChlidBtn(node);
				//}
				childNodes.add(node);
			}
		}
		rootNode.setChildren(childNodes);
	}
	
	
	/**
	 * …Ë÷√≤Àµ•button
	 * @param xsdd
	 * @return
	 */
	 /**
	private void addChlidBtn(TreeNode treeNode){
		List<TreeNode> childNodes = new ArrayList<TreeNode>(); 
		for(XsddBeanBtn btn : childBtns){
			if(treeNode.getDataId().equals(btn.getRootid())){
				TreeNode node = BtnToNode(btn);
				childNodes.add(node);
			}
		}
		treeNode.setChildren(childNodes);
	}
	*/
	
	
}
