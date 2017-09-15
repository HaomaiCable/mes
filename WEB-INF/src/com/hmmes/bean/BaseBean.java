package com.hmmes.bean;

import java.sql.Timestamp;

import com.hmmes.bean.SiteScore.ScoreType;

/**
 * 
 * @author luozejun
 *
 */
public class BaseBean {
	
	 /**
	  * ×´Ì¬Ã¶¾Ù
	  * @author lu
	  *
	  */
	 public static enum STATE {
		 	ENABLE(0, "¿ÉÓÃ"), DISABLE(1,"½ûÓÃ");
			public int key;
			public String value;
			private STATE(int key, String value) {
				this.key = key;
				this.value = value;
			}
			public static STATE get(int key) {
				STATE[] values = STATE.values();
				for (STATE object : values) {
					if (object.key == key) {
						return object;
					}
				}
				return null;
			}
		}
	 	
	 	/**
	 	 * É¾³ýÃ¶¾Ù
	 	 * @author lu
	 	 *
	 	 */
	 	public static enum DELETED {
			NO(0, "Î´É¾³ý"), YES(1,"ÒÑÉ¾³ý");
			public int key;
			public String value;
			private DELETED(int key, String value) {
				this.key = key;
				this.value = value;
			}
			public static DELETED get(int key) {
				DELETED[] values = DELETED.values();
				for (DELETED object : values) {
					if (object.key == key) {
						return object;
					}
				}
				return null;
			}
		}
	
}
