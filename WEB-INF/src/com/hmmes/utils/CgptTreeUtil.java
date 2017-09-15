package com.hmmes.utils;

import java.util.ArrayList;
import java.util.List;

import com.hmmes.bean.CgptMenu;
import com.hmmes.bean.CgptMenuBtn;
import com.hmmes.bean.TreeNode;

public class CgptTreeUtil {
	private final static String MENU_ID = "menu_";
	
	private final static String BTN_ID = "btn_";
	
	
	List<CgptMenu> rootMenus;
	List<CgptMenu> childMenus;
	List<CgptMenuBtn> childBtns;
	
	public CgptTreeUtil(List<CgptMenu> rootMenus,List<CgptMenu> childMenus){
		this.rootMenus = rootMenus;
		this.childMenus = childMenus;
	}  
	
	public CgptTreeUtil(List<CgptMenu> rootMenus,List<CgptMenu> childMenus,List<CgptMenuBtn> childBtns){
		this.rootMenus = rootMenus;
		this.childMenus = childMenus;
		this.childBtns = childBtns;
	}  
	
	public List<TreeNode> getTreeNode(){
		return getRootNodes();
	}
	
	/**
	 * 
	 * @param menu
	 * @return
	 */
	private TreeNode MenuToNode(CgptMenu menu){
		if(menu == null){
			return null;
		}
		TreeNode node = new TreeNode();
		node.setId(MENU_ID+menu.getId());
		node.setDataId(menu.getId());
		node.setText(menu.getName());
		node.setUrl(menu.getUrl());
		node.setParentId(menu.getParentId());
		node.getAttributes().put("type", "0");
		node.getAttributes().put("id", menu.getId());
		return node;
	}
	
	
	/**
	 * 
	 * @param menu
	 * @return
	 */
	private TreeNode BtnToNode(CgptMenuBtn btn){
		if(btn == null){
			return null;
		}
		TreeNode node = new TreeNode();
		node.setId(BTN_ID+btn.getId());
		node.setDataId(btn.getId());
		node.setText(btn.getBtnName());
		node.setParentId(btn.getMenuid());
		node.getAttributes().put("type", "1");
		node.getAttributes().put("id", btn.getId());
		return node;
	}

	private List<TreeNode> getRootNodes(){
		List<TreeNode> rootNodes = new ArrayList<TreeNode>();
		for(CgptMenu menu : rootMenus){
			TreeNode node = MenuToNode(menu);
			if(node != null){
				addChlidNodes(node);
				rootNodes.add(node);
			}
		}
		return rootNodes;
	}
	
	/**
	 * 
	 * @param menu
	 * @return
	 */
	private void addChlidNodes(TreeNode rootNode){
		List<TreeNode> childNodes = new ArrayList<TreeNode>();  
		for(CgptMenu menu : childMenus){
			if(rootNode.getDataId().equals(menu.getParentId())){
				TreeNode node = MenuToNode(menu);
				if(childBtns != null && !childBtns.isEmpty()){
					addChlidBtn(node);
				}
				childNodes.add(node);
			}
		}
		rootNode.setChildren(childNodes);
	}
	
	
	/**
	 * …Ë÷√≤Àµ•button
	 * @param menu
	 * @return
	 */
	private void addChlidBtn(TreeNode treeNode){
		List<TreeNode> childNodes = new ArrayList<TreeNode>(); 
		for(CgptMenuBtn btn : childBtns){
			if(treeNode.getDataId().equals(btn.getMenuid())){
				TreeNode node = BtnToNode(btn);
				childNodes.add(node);
			}
		}
		treeNode.setChildren(childNodes);
	}
	
	
	
}
