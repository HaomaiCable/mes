package com.hmmes.test;

import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hmmes.bean.SiteColumn;
import com.hmmes.bean.SiteMain;
import com.hmmes.bean.SiteType;
import com.hmmes.bean.SysMenu;
import com.hmmes.model.SiteMainModel;
import com.hmmes.service.SiteColumnService;
import com.hmmes.service.SiteMainService;
import com.hmmes.service.SiteTypeService;
import com.hmmes.service.SysMenuService;

public class SpringTest {
	static ApplicationContext context = new ClassPathXmlApplicationContext(
			new String[]{"spring-*.xml"}); 
	
	SiteMainService<SiteMain> siteMainService = (SiteMainService)context.getBean("siteMainService");
//	UserMainService<UserMain> userMainService = (UserMainService)context.getBean("userMainService");
	SiteTypeService<SiteType> siteTypeService = (SiteTypeService)context.getBean("siteTypeService");
	SiteColumnService<SiteColumn> siteColumnService = (SiteColumnService)context.getBean("siteColumnService");
	private SysMenuService<SysMenu> sysMenuService = (SysMenuService)context.getBean("sysMenuService");
	
	
	public static void main(String[] args) throws Exception {
		SpringTest test = new SpringTest();
//		test.updateSite();
//		test.addColumn();
		test.testList();
	}

	public void testList(){
		List<SysMenu> rootMenus = null;
		List<SysMenu> childMenus = null;
		rootMenus = sysMenuService.getRootMenu(null);// 查询所有根节点
		childMenus = sysMenuService.getChildMenu();//查询所有子节点
		List<SysMenu> newMenus = ListUtils.sum(rootMenus, childMenus);
		System.out.println("rootMenus:"+rootMenus.size() +" childMenus:"+childMenus.size()+" newMenus:"+newMenus.size());
		for(SysMenu menu: newMenus){
			System.out.println(menu.getUrl());
		}
	}
	
	public void addColumn() throws Exception{
		SiteColumn bean = new SiteColumn();
		bean.setSiteId(3);
		bean.setName("MOKO!美空  展示");
		bean.setLink("http://www.moko.cc/moko/post/1.html");
		bean.setPic("http://img1.moko.cc/users/0/9/2805/post/a2/img1_cover_6971997.jpg");
		siteColumnService.add(bean);
		
		
		bean = new SiteColumn();
		bean.setSiteId(3);
		bean.setName("MOKO!美空  模特儿");
		bean.setLink("http://www.moko.cc/channels/post/23/1.html");
		bean.setPic("http://img1.moko.cc/users/14/4322/1296697/post/31/img1_mokoshow_6973444.jpg");
		siteColumnService.add(bean);
	}
	
	public void addTypeRel() throws Exception{
		siteMainService.addTypeRel(new Integer[]{1,2,3}, 3);
		siteMainService.addTypeRel(new Integer[]{1,3}, 2);
		siteMainService.addTypeRel(new Integer[]{1,2}, 1);
		siteMainService.delete(new Object[]{3});
	}
	
	
	
	
	public void addType() throws Exception{
		SiteType bean = new SiteType();
		bean.setName("美女论坛");
		bean.setCode("xgmn");
		bean.setRank(0);
		bean.setState(0);
		bean.setDescr("超级性感美女");
		siteTypeService.add(bean);
	}
	
	
	public void updateType() throws Exception{
		SiteType bean = new SiteType();
		bean.setId(2);
		bean.setName("美女论坛");
		bean.setCode("xgmn1");
		bean.setRank(0);
		bean.setState(0);
		bean.setDescr("超级性感美女");
		siteTypeService.update(bean);
	}
	
	public void delType() throws Exception{
		//siteTypeService.delete();
	}
	
//	
//	public void addUser() throws Exception{
//		UserMain bean = new UserMain();
//		bean.setEmail("35263107@qq.com");
//		bean.setNickName("swing");
//		bean.setState(0);
//		bean.setPassWord("123456");
//		bean.setUserIcon("1.jpg");
//		userMainService.add(bean);
//	}
	
//	public void updateUser() throws Exception{
//		UserMain bean = new UserMain();
//		bean.setId(2);
//		bean.setEmail("yykk520@qq.com");
//		bean.setNickName("yykk");
//		bean.setState(0);
//		bean.setPassWord("123456");
//		bean.setUserIcon("2.jpg");
//		userMainService.update(bean);
//	}
//	
//	
//	public void delUser() throws Exception{
//		userMainService.delete(4);
//	}
//	
//	
//	public void add() throws Exception{
//		com.hmmes.bean.SiteMain a;
//		SiteMain bean = new SiteMain();
//		bean.setName("美图阁");
//		bean.setDomain("www.520mm.cc");
//		bean.setLink("http://www.520mm.cc");
//		bean.setDeleted(0);
//		bean.setState(1);
//		bean.setPic("http://image.mn606.com/images/2012/12/12/150_150/2012121220405491043.jpg");
//		siteMainService.add(bean);
//		System.out.println("id"+bean.getId());
//	}
//	
//	
	public void updateSite() throws Exception{
		SiteMain bean = new SiteMain();
		 bean.setId(3);
		bean.setName("美空网");
		bean.setDomain("www.moko.cc");
		bean.setLink("http://www.moko.cc");
		bean.setDeleted(0);
		bean.setState(1);
		bean.setPic("http://image.mn606.com/images/2012/12/12/150_150/2012121220405491043.jpg");
		siteMainService.update(bean);
		System.out.println("id"+bean.getId());
	}
	
	
	
	public void queryByList() throws Exception{
		SiteMainModel model = new SiteMainModel();
		model.getPager().setPageSize(2);
		List<SiteMain> list = siteMainService.queryByList(model);
		System.out.println(list.size());
		System.out.println(model.getPager().getRowCount());
	}
}
