package com.hmmes.bean;

import com.hmmes.bean.BaseBean.DELETED;


public class SysRoleRel extends BaseBean {
	
	    private Integer objId; // �������� type=0����sys_menu.id, type=1����sys_user.id
	  	private Integer relType; // �������� 0=�˵�,1=�û�
	
	 
	/**
 	 * ö��
 	 * @author lu
 	 *
 	 */
 	public static enum RelType {
		MENU(0, "�˵�"), USER(1,"�û�"),BTN(2,"��ť");
		public int key;
		public String value;
		private RelType(int key, String value) {
			this.key = key;
			this.value = value;
		}
		public static RelType get(int key) {
			RelType[] values = RelType.values();
			for (RelType object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}
	
	
	    public void setRoleId(Integer roleId) {
		public Integer getObjId() {
		public void setObjId(Integer objId) {
		public Integer getRelType() {
		public void setRelType(Integer relType) {
}