package com.hmmes.utils;

//import java.io.File;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 出版页面的工具类
 * @author Administrator
 *
 */
public class PublishPageUtil {
	
	static ApplicationContext context = new ClassPathXmlApplicationContext(
			new String[]{"com/wei/ssi/conf/spring/*.xml"}); 
	
	private final static String  FILE_DIR_PATH = PathUtil.getRootPath();//获取项目部署根目录  如：F:\openwork\mn606\WebRoot

	public static void main(String[] args) {
//		createIndex();
	}
}
