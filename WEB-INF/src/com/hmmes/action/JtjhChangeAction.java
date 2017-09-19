package com.hmmes.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Collections; 

import net.sf.json.JSONObject;
//import net.sf.json.JSON;
import org.json.JSONArray;
import com.alibaba.fastjson.JSON;  
//import com.alibaba.fastjson.JSONObject;  
import com.alibaba.fastjson.annotation.JSONField;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.context.*;
import org.springframework.web.context.support.*;

import com.hmmes.bean.JsonBean;
import com.hmmes.bean.JtjhBean;

import com.hmmes.bean.JhbhBean;
import com.hmmes.bean.JtjhBgBean;
import com.hmmes.bean.JtjhWghbBean;

import com.hmmes.bean.BaseBean.DELETED;

import com.hmmes.model.JtjhModel;
import com.hmmes.model.JtjhBgModel;

import com.hmmes.service.JhbhService;
import com.hmmes.service.JtjhBgService;
import com.hmmes.service.JtjhService;
import com.hmmes.service.JtjhWghbService;

import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.XsddTree;
import com.hmmes.utils.SessionUtils;
import com.hmmes.utils.DateUtil;
import com.hmmes.utils.StringUtil;
import com.hmmes.utils.excelutils.ExcelHelper;
import com.hmmes.utils.excelutils.JxlExcelHelper;
import com.hmmes.utils.ListUtils;

import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  

import java.util.*;
import java.io.*;


 
@Controller
@RequestMapping("/jtjhChangeManage") 
public class JtjhChangeAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(XsddAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private JtjhService<JtjhBean> jtjhService; 
	
	@Autowired
	private JhbhService<JhbhBean> jhbhService;
	@Autowired
	private JtjhWghbService<JtjhWghbBean> jtjhWghbService;

	@Autowired
	private  JtjhBgService<JtjhBgBean> jtjhBgService;	
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 */
							
	//机台计划变更
	@RequestMapping("/jtjhchange")
	public ModelAndView  jtjhchange(JtjhModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//销售订单结束时间，当天
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//日期减1年
         // rightNow.add(Calendar.MONTH,3);//日期加3个月
         rightNow.add(Calendar.DAY_OF_YEAR,-2);//日期-2天
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//销售订单开始时间
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    //model.setDeleted(DELETED.NO.key);
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);

	    return forword("business/jtjhChange",context); 
	
		
	}
	//机台计划批量变更,对于电压、工艺、批号、技术要求、状态等对所有机台一样的，循环进行替换
	@RequestMapping("/jtjhchange_pl")
	public ModelAndView  jtjhchange_pl(JtjhModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//销售订单结束时间，当天
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//日期减1年
         // rightNow.add(Calendar.MONTH,3);//日期加3个月
         rightNow.add(Calendar.DAY_OF_YEAR,-2);//日期-2天
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//销售订单开始时间
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    //model.setDeleted(DELETED.NO.key);
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);

	    return forword("business/jtjhChange_pl",context); 
	
		
	}
	//机台计划变更确认
	@RequestMapping("/jtjhChangeAccept")
	public ModelAndView  jtjhChangeAccept(JtjhBgModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//销售订单结束时间，当天
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//日期减1年
         // rightNow.add(Calendar.MONTH,3);//日期加3个月
         rightNow.add(Calendar.DAY_OF_YEAR,-2);//日期-2天
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//销售订单开始时间
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		Map<String,Object>  context = getRootMap();
	    //model.setDeleted(DELETED.NO.key);
		List<JtjhBgBean> dataList = jtjhBgService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);

	    return forword("business/jtjhChangeAccept",context); 
	
	}

	@RequestMapping("/jtjhChangeBrow")  //机台计划变更单浏览
	public ModelAndView jtjhChangeBrow(JtjhBgModel model,HttpServletRequest request) throws Exception{
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String todate=sdf.format(new Date()) ;//销售订单结束时间，当天
		 String syear=DateUtil.getNowYear();
		 String smonth=DateUtil.getNowMonth();
		 Date fd=sdf.parse(syear+"-"+smonth+"-"+"1");
		 Calendar rightNow = Calendar.getInstance();
         rightNow.setTime(fd);
         // righNow.add(Calendar.YEAR,-1);//日期减1年
         rightNow.add(Calendar.MONTH,-1);//日期-1个月,两个月计划
         //rightNow.add(Calendar.DAY_OF_YEAR,-2);//日期-2天
         Date dt1=rightNow.getTime();
		 String fromdate=sdf.format(dt1);//销售订单开始时间
         request.setAttribute("fromdate",fromdate);
         request.setAttribute("todate",todate);

		
		Map<String,Object>  context = getRootMap();
		//model.setDeleted(DELETED.NO.key);
		List<JtjhBgBean> dataList = jtjhBgService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("business/jtjhChangeBrow",context); 
	}	
	
     /**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList")  //显示全部机台计划列表
	public void  dataList(JtjhModel model,HttpServletResponse response) throws Exception{
		List<JtjhBean> dataList = jtjhService.queryByList(model);
		List<JtjhBean> result = new ArrayList<JtjhBean>();
		// 封装VO集合
		for (Object ele : dataList)
		{
			JtjhBean st = (JtjhBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonMap);
	}	


	@RequestMapping("/dataListChange")   //机台计划变更单DataGrid 
	public void  dataListChange(JtjhBgModel model,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<JtjhBgBean> dataList = jtjhBgService.queryByList(model);
		List<JtjhBgBean> result = new ArrayList<JtjhBgBean>();

		for (Object ele : dataList)
		{
			JtjhBgBean st = (JtjhBgBean)ele;
			if ("状态".equals(st.getField()))
			{
                 if ("1".equals(st.getOldContent()))
                 {
					 st.setOldContent_dc("正常");
                 }	
				 else if ("2".equals(st.getOldContent()))
				 {
					 st.setOldContent_dc("暂停");
				 }
				 else if ("3".equals(st.getOldContent()))
				 {
					 st.setOldContent_dc("作废");
				 }
				 else {
				 }

                 if ("1".equals(st.getNewContent()))
                 {
					 st.setNewContent_dc("正常");
                 }	
				 else if ("2".equals(st.getNewContent()))
				 {
					 st.setNewContent_dc("暂停");
				 }
				 else if ("3".equals(st.getNewContent()))
				 {
					 st.setNewContent_dc("作废");
				 }
				 else{
				 }
			}
			else {
				st.setOldContent_dc(st.getOldContent());
				st.setNewContent_dc(st.getNewContent());
			}
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山：XsddAction--dataListchange"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}

	@RequestMapping("/dataListChangeForDdId")   //根据机台计划ID，查询机台计划变更单显示DataGrid 
	public void  dataListChangeForDdid(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<JtjhBgBean> dataList = jtjhBgService.queryListById(id);
		List<JtjhBgBean> result = new ArrayList<JtjhBgBean>();

		for (Object ele : dataList)
		{
			JtjhBgBean st = (JtjhBgBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山：XsddAction--dataListchangeForDdid"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}

    //导出Excel,变更明细
	@RequestMapping("/exportExcelBgd")
	public void  exportExcelBgd(HttpServletRequest request,HttpServletResponse response,HttpSession session)  
		throws Exception{
	    //request.setCharacterEncoding("UTF-8");  
	   	JSONObject context = new JSONObject();
        //获取编辑数据 这里获取到的是json字符串  

        String selected = request.getParameter("selected"); 
//System.out.println("高绪山：exportExcle="+selected);
		 //String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
	     String sdate=sdf.format(new Date());	
		 if ( selected!=null )
		 {
		     List<JtjhBgBean> listSelected = new ArrayList<JtjhBgBean>();	
             listSelected = JSON.parseArray(selected, JtjhBgBean.class);  
             String[] titles = new String[]{"变更日期", "变更单编号", "行号", "机台", "机台计划编号", "行号", "变更项目", "原内容", "变更后内容", "变更人"
			 , "确认人"};
             String[] fieldNames = new String[]{"createTime", "bh", "row", "sbmc", "jhbh", "jhbhrow", "field", "oldContent_dc", "newContent_dc", "createBy",
                "acceptBy"};
             try {
				String excelname="机台计划变更明细导出_("+sdate+")";
				//String  serverFilePath=session.getServletContext().getRealPath("/uploadfiles/")+excelname+".xls";
				//serverFilePath=request.getRealPath("/")+ "\\uploadfiles\\" +excelname+".xls"; //getServletContext().getRealPath("/")
//System.out.println("高绪山old："+request.getRealPath("/")+ "\\uploadfiles\\" +excelname+".xls");
				String path = session.getServletContext().getRealPath("/uploadfiles/");
//System.out.println("高绪山new："+path+File.separator+excelname+".xls");
                File file1 = new File(path+File.separator+excelname+".xls");
                ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
                //eh1.writeExcel(JtjhBean.class, listSelected);
                eh1.writeExcel(JtjhBgBean.class, listSelected, fieldNames, titles);
                //List<Employee> list1 = eh1.readExcel(Employee.class, fieldNames,
                //    true);
                //System.out.println("-----------------JXL2003.xls-----------------");
                //for (Employee user : list1) {
                //    System.out.println(user);
                //}
	            context.put("fileName", excelname+".xls");
		        context.put(SUCCESS, true);	
				HtmlUtil.writerJson(response, context);
  
             } catch (Exception e) {
				    sendFailureMessage(response,"导出失败！");
                    e.printStackTrace();

             } 
			 //ExportExcel excel=new ExportExcel();  
             //String[] Title={"ID","下达日期","计划编号","业务员","行号","订单状态","产品型号","产品规格","电压等级","工艺"
			 //,"单位","计划数量","客户要求交货期","计划交货期","技术要求","批号","延误原因","延误机台","二次交货期"
			 //,"是否下达机台","订单员","","","","","完工日期","完工主手","","Wg"
			 //,"Rk","Bg","Jtjh","合计完工数量","完工段数明细","使用木轮明细","合计长米数量","","合计入库数量"
			 //,"入库段数明细","全部入库","超期天数(-9999为未到期)"};  

		     
            // String rfp=excel.exportExcel(request,excelname+".xls",excelname,Title, result,41);   		 
	        // if (rfp.equals("系统提示：Excel文件导出成功！"))
	        // { 
             //   //request.setAttribute("fileName",excelname+".xls");
			//	context.put("fileName", excelname+".xls");
		     //   context.put(SUCCESS, true);	
			//	HtmlUtil.writerJson(response, context);
    			//sendSuccessMessage(response, rfp+",文件名为:"+excelname+".xls");
	         //}
			 //else{
			 //     sendFailureMessage(response,rfp);
			 //}
			
		     return ;
         }
		 sendFailureMessage(response, "导出失败，请重试！");
	}
    //导出Excel,变更后计划明细
	@RequestMapping("/exportExcelBgjh")
	public void  exportExcelBgjh(HttpServletRequest request,HttpServletResponse response,HttpSession session)  
		throws Exception{
	    //request.setCharacterEncoding("UTF-8");  
	   	JSONObject context = new JSONObject();
        //获取编辑数据 这里获取到的是json字符串  

         String selected = request.getParameter("selected"); 
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
	     String sdate=sdf.format(new Date());	
		 if ( selected!=null )
		 {
		     List<JtjhBgBean> listSelected = new ArrayList<JtjhBgBean>();	
             listSelected = JSON.parseArray(selected, JtjhBgBean.class);  
             String [] sortNameArr = {"jhbh","jhbhrow"};
			 ListUtils.sort(listSelected, true, "jhbh","jhbhrow"); 
			 String tmpjhbh="";
			 Integer tmpjhbhrow=0;
			 List<JtjhBean> result = new ArrayList<JtjhBean>();
			 for (JtjhBgBean bgBean : listSelected )
			 {

				 if (!(tmpjhbh.equals(bgBean.getJhbh()) && tmpjhbhrow==bgBean.getJhbhrow()))
				 {
                     JtjhBean jhBean = jtjhService.queryByJhbhAndRow(bgBean.getJhbh(),bgBean.getJhbhrow());
					 if (jhBean!=null)
					 {
						 jhBean.setCreateTime(bgBean.getCreateTime());  //变更日期
						 //借用gd 存放state的中文说明
						 if (jhBean.getState()==1)
                         {
					         jhBean.setGd("");
                         }	
				         else if (jhBean.getState()==2)
                         {
					         jhBean.setGd("暂停");
                         }	
				         else if (jhBean.getState()==3)
                         {
					         jhBean.setGd("作废");
                         }	
				         else {
				         }
						 result.add(jhBean);

					}
				 }
				 tmpjhbh=bgBean.getJhbh();
			     tmpjhbhrow =bgBean.getJhbhrow();

			 }
             String[] titles = new String[]{"变更日期", "机台", "计划号", "型号", "规格", "电压等级", "单位"
			 , "数量", "工序", "完成日期", "技术要求", "批号", "工艺","状态"};
             String[] fieldNames = new String[]{"createTime", "sbmc", "jhbh", "gxxh", "gxgg", "gxdy", "gxdw",
                "jhsl_xs", "gxlb", "jhrq", "gxjsyq", "gxph", "gxgy","gd"};
             try {
				String excelname="变更后的机台计划单导出_("+sdate+")";
				String path = session.getServletContext().getRealPath("/uploadfiles/");

                File file1 = new File(path+File.separator+excelname+".xls");
                ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
                eh1.writeExcel(JtjhBean.class, result, fieldNames, titles);
                //List<Employee> list1 = eh1.readExcel(Employee.class, fieldNames,
                //    true);
                //System.out.println("-----------------JXL2003.xls-----------------");
                //for (Employee user : list1) {
                //    System.out.println(user);
                //}
	            context.put("fileName", excelname+".xls");
		        context.put(SUCCESS, true);	
				HtmlUtil.writerJson(response, context);
  
             } catch (Exception e) {
				    sendFailureMessage(response,"导出失败！");
                    e.printStackTrace();

             } 
			
		     return ;
         }
		 sendFailureMessage(response, "导出失败，请重试！");
	}
  
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */

	
	@RequestMapping("/save")  //机台计划变更保存
	public void save(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 request.setCharacterEncoding("UTF-8");  
         //获取编辑数据  
		 String id  = request.getParameter("id");
		 String jhbh  = request.getParameter("jhbh");
		 String row  = request.getParameter("row");
		 String sbmc  = request.getParameter("sbmc");
		 String sbmcnew  = request.getParameter("sbmcnew");
		 String iszl  = request.getParameter("iszl");
		 String iszlnew  = request.getParameter("iszlnew");
		 String gxxh  = request.getParameter("gxxh");
		 String gxxhnew  = request.getParameter("gxxhnew");
		 String gxgg  = request.getParameter("gxgg");
		 String gxggnew  = request.getParameter("gxggnew");
		 String gxdy  = request.getParameter("gxdy");
		 String gxdynew  = request.getParameter("gxdynew");
		 String gxgy  = request.getParameter("gxgy");
		 String gxgynew  = request.getParameter("gxgynew");
		 String gxdw  = request.getParameter("gxdw");
		 String gxdwnew  = request.getParameter("gxdwnew");
		 String gxlb  = request.getParameter("gxlb");
		 String gxlbnew  = request.getParameter("gxlbnew");
		 String jhsl_xs  = request.getParameter("jhsl_xs");
		 String jhsl_xsnew  = request.getParameter("jhsl_xsnew");
		 String jhsl_o  = request.getParameter("jhsl_o");
		 String jhsl_onew  = request.getParameter("jhsl_onew");
		 String jhrq  = request.getParameter("jhrq");
		 String jhrqnew  = request.getParameter("jhrqnew");
		 String gxjsyq  = request.getParameter("gxjsyq");
		 String gxjsyqnew  = request.getParameter("gxjsyqnew");
		 String gxph  = request.getParameter("gxph");
		 String gxphnew  = request.getParameter("gxphnew");
		 String state  = request.getParameter("state");
		 String statenew  = request.getParameter("statenew");

		 JtjhBean ddbean  = jtjhService.queryById(Integer.valueOf(id));
	     if(ddbean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		 }
		 String bh=null;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	     String username = SessionUtils.getUser(request).getNickName();
		 if (bh==null || "".equals(bh))
		 {
             String syear=DateUtil.getNowYear();
			 String smonth=DateUtil.getNowMonth();
			 Integer iyear=Integer.valueOf(syear);
             Integer imonth=Integer.valueOf(smonth);
			 JhbhBean jhbhbean  = jhbhService.queryNoByYearAndMonth(iyear, imonth,4);  //4--机台计划变更单编号
			 //@Param("uuid")String uuid, @Param("portletid")String portletid
  		     if(jhbhbean  == null){
				  JhbhBean newbean=new JhbhBean();
                  newbean.setNian(iyear);
				  newbean.setYue(imonth);
				  newbean.setFlag(4);//=4
				  newbean.setJhno(1);
				  jhbhService.add(newbean);
				  String sjhno=StringUtil.fillZero("1", 3);
				  bh=syear+smonth+sjhno;
		    }
			else 
			{
				 Integer newno=jhbhbean.getJhno()+1;
				 jhbhbean.setJhno(newno);
				 jhbhService.update(jhbhbean);
                 String sjhno=StringUtil.fillZero(newno+"", 3);
				 bh=syear+smonth+sjhno;
			}
	     }		 
		 int changeFlag=0;
		 int bgrow=1;
         if (!(sbmc.equals(sbmcnew)) &&   StringUtils.isNotBlank(sbmcnew) )
         {
			 ddbean.setSbmc(sbmcnew);
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("设备名称");
			 bgbean.setOldContent(sbmc);
			 bgbean.setNewContent(sbmcnew);
		     jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(iszl.equals(iszlnew)) &&   StringUtils.isNotBlank(iszlnew) )
         {
			 ddbean.setIszl(iszlnew);
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("主零");
			 bgbean.setOldContent(iszl);
			 bgbean.setNewContent(iszlnew);
		     jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(gxxh.equals(gxxhnew)) &&   StringUtils.isNotBlank(gxxhnew) )
         {
			 ddbean.setGxxh(gxxhnew);
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("型号");
			 bgbean.setOldContent(gxxh);
			 bgbean.setNewContent(gxxhnew);
		     jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(gxgg.equals(gxggnew)) &&    StringUtils.isNotBlank(gxggnew) )
         {
			 ddbean.setGxgg(gxggnew);
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("规格");
			 bgbean.setOldContent(gxgg);
			 bgbean.setNewContent(gxggnew);
		     jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(gxdy.equals(gxdynew)) &&  StringUtils.isNotBlank(gxdynew) )
         {
			 ddbean.setGxdy(gxdynew);
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("电压");
			 bgbean.setOldContent(gxdy);
			 bgbean.setNewContent(gxdynew);
			 jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(gxgy.equals(gxgynew)) &&  StringUtils.isNotBlank(gxgynew) )
         {
			 ddbean.setGxgy(gxgynew);
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("工艺");
			 bgbean.setOldContent(gxgy);
			 bgbean.setNewContent(gxgynew);
			 jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(gxdw.equals(gxdwnew)) &&  StringUtils.isNotBlank(gxdwnew) )
         {
			 ddbean.setGxdw(gxdwnew);
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("单位");
			 bgbean.setOldContent(gxdw);
			 bgbean.setNewContent(gxdwnew);
		     jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(gxlb.equals(gxlbnew)) &&  StringUtils.isNotBlank(gxlbnew) )
         {
			 ddbean.setGxlb(gxlbnew);
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("类别");
			 bgbean.setOldContent(gxlb);
			 bgbean.setNewContent(gxlbnew);
		     jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(jhsl_xs.equals(jhsl_xsnew)) &&  StringUtils.isNotBlank(jhsl_xsnew) )
         {
			 ddbean.setJhsl_xs(jhsl_xsnew);
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("产品数量");
			 bgbean.setOldContent(jhsl_xs);
			 bgbean.setNewContent(jhsl_xsnew);
		     jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(jhsl_o.equals(jhsl_onew)) &&  StringUtils.isNotBlank(jhsl_onew) )
         {
			 ddbean.setJhsl_o(Double.valueOf(jhsl_onew));
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("机台数量");
			 bgbean.setOldContent(jhsl_o);
			 bgbean.setNewContent(jhsl_onew);
		     jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(jhrq.equals(jhrqnew))  &&  StringUtils.isNotBlank(jhrqnew) )
         {
             Date ddate=null;
	         //sdate=sdf.format(new Date());	
	         try {
	              ddate = sdf.parse(jhrqnew);
                 } catch (ParseException e) {
                 e.printStackTrace();
             }
			 ddbean.setJhrq(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setField("完工日期");
			 bgbean.setRow(bgrow);
			 bgbean.setOldContent(jhrq);
			 bgbean.setNewContent(jhrqnew);
		     jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(gxjsyq.equals(gxjsyqnew)) &&  StringUtils.isNotBlank(gxjsyqnew))
         {
			 ddbean.setGxjsyq(gxjsyqnew);
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setField("技术要求");
			 bgbean.setRow(bgrow);
			 bgbean.setOldContent(gxjsyq);
			 bgbean.setNewContent(gxjsyqnew);
		     jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(gxph.equals(gxphnew)) &&  StringUtils.isNotBlank(gxphnew))
         {
			 ddbean.setGxph(gxphnew);
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setField("批号");
			 bgbean.setRow(bgrow);
			 bgbean.setOldContent(gxph);
			 bgbean.setNewContent(gxphnew);
		     jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(state.equals(statenew))  &&  StringUtils.isNotBlank(statenew) )
         {
			 ddbean.setState(Integer.valueOf(statenew));
		     JtjhBgBean bgbean=new JtjhBgBean();
			 bgbean.setSbmc(ddbean.getSbmc());
			 bgbean.setGd(ddbean.getGd());
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setJhid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("状态");
			 bgbean.setOldContent(state);
			 bgbean.setNewContent(statenew);
		     jtjhBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
		 if (changeFlag==1)
		 {
			  jtjhService.update(ddbean);
		      sendSuccessMessage(response, "机台计划变更成功~");
		 }
		 else {
	   
		    sendFailureMessage(response, "未进行机台计划变更！");
		 }
	}	
    //机台计划批量变更保存，对于电压、工艺、批号、技术要求、状态等对所有机台一样的，循环进行变更
	@RequestMapping("/savePlBg")  
	public void savePlBg(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 request.setCharacterEncoding("UTF-8");  
         //获取编辑数据  
		 String id  = request.getParameter("id");
		 String jhbh  = request.getParameter("jhbh");
		 String row  = request.getParameter("row");
		 String sbmc  = request.getParameter("sbmc");
		 String sbmcnew  = request.getParameter("sbmcnew");
		 String iszl  = request.getParameter("iszl");
		 String iszlnew  = request.getParameter("iszlnew");
		 String gxxh  = request.getParameter("gxxh");
		 String gxxhnew  = request.getParameter("gxxhnew");
		 String gxgg  = request.getParameter("gxgg");
		 String gxggnew  = request.getParameter("gxggnew");
		 String gxdy  = request.getParameter("gxdy");
		 String gxdynew  = request.getParameter("gxdynew");
		 String gxgy  = request.getParameter("gxgy");
		 String gxgynew  = request.getParameter("gxgynew");
		 String gxdw  = request.getParameter("gxdw");
		 String gxdwnew  = request.getParameter("gxdwnew");
		 String gxlb  = request.getParameter("gxlb");
		 String gxlbnew  = request.getParameter("gxlbnew");
		 String jhsl_xs  = request.getParameter("jhsl_xs");
		 String jhsl_xsnew  = request.getParameter("jhsl_xsnew");
		 String jhsl_o  = request.getParameter("jhsl_o");
		 String jhsl_onew  = request.getParameter("jhsl_onew");
		 String jhrq  = request.getParameter("jhrq");
		 String jhrqnew  = request.getParameter("jhrqnew");
		 String gxjsyq  = request.getParameter("gxjsyq");
		 String gxjsyqnew  = request.getParameter("gxjsyqnew");
		 String gxph  = request.getParameter("gxph");
		 String gxphnew  = request.getParameter("gxphnew");
		 String state  = request.getParameter("state");
		 String statenew  = request.getParameter("statenew");
         List<JtjhBean> dataListJtjhPl = jtjhService.queryListByJhbh(jhbh);

		 for (JtjhBean ddbean : dataListJtjhPl)
		 {
             Integer bgId=ddbean.getId();
			 Integer bgbhRow=ddbean.getRow();
			 String bgGd=ddbean.getGd();
			 String bgSbmc=ddbean.getSbmc();
//System.out.println("高绪山-bgbhRow"+ bgbhRow);
		     String bh=null;
		     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	         String username = SessionUtils.getUser(request).getNickName();

             String syear=DateUtil.getNowYear();
			 String smonth=DateUtil.getNowMonth();
			 Integer iyear=Integer.valueOf(syear);
             Integer imonth=Integer.valueOf(smonth);
			 JhbhBean jhbhbean  = jhbhService.queryNoByYearAndMonth(iyear, imonth,4);  //4--机台计划变更单编号
			 //@Param("uuid")String uuid, @Param("portletid")String portletid
  		     if(jhbhbean  == null){
				  JhbhBean newbean=new JhbhBean();
                  newbean.setNian(iyear);
				  newbean.setYue(imonth);
				  newbean.setFlag(4);//=4
				  newbean.setJhno(1);
				  jhbhService.add(newbean);
				  String sjhno=StringUtil.fillZero("1", 3);
				  bh=syear+smonth+sjhno;
		     }
			 else 
			 {
				 Integer newno=jhbhbean.getJhno()+1;
				 jhbhbean.setJhno(newno);
				 jhbhService.update(jhbhbean);
                 String sjhno=StringUtil.fillZero(newno+"", 3);
				 bh=syear+smonth+sjhno;
			 }
		     int changeFlag=0;
		     int bgrow=1;
			 /**
             if (!(sbmc.equals(sbmcnew)) &&   StringUtils.isNotBlank(sbmcnew) )
             {
			     ddbean.setSbmc(sbmcnew);
		         JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
			     bgbean.setAccept(0);
			     bgbean.setBh(bh);
			     bgbean.setCreateBy(username);
			     bgbean.setJhid(bgId);
			     bgbean.setJhbh(jhbh);
			     bgbean.setJhbhrow(bgbhRow);
			     bgbean.setRow(bgrow);
			     bgbean.setField("设备名称");
			     bgbean.setOldContent(sbmc);
			     bgbean.setNewContent(sbmcnew);
		         jtjhBgService.add(bgbean);
			     bgrow++;
			     changeFlag=1;
             }
			 */
			 /**
             if (!(iszl.equals(iszlnew)) &&   StringUtils.isNotBlank(iszlnew) )
             {
			     ddbean.setIszl(iszlnew);
		         JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
			     bgbean.setAccept(0);
			     bgbean.setBh(bh);
			     bgbean.setCreateBy(username);
			     bgbean.setJhid(bgId);
			     bgbean.setJhbh(jhbh);
			     bgbean.setJhbhrow(bgbhRow);
			     bgbean.setRow(bgrow);
			     bgbean.setField("主零");
			     bgbean.setOldContent(iszl);
			     bgbean.setNewContent(iszlnew);
		         jtjhBgService.add(bgbean);
			     bgrow++;
			     changeFlag=1;
             }
			 */
			 /**
             if (!(gxxh.equals(gxxhnew)) &&   StringUtils.isNotBlank(gxxhnew) )
             {
			     ddbean.setGxxh(gxxhnew);
		         JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
			     bgbean.setAccept(0);
				 bgbean.setBh(bh);
				 bgbean.setCreateBy(username);
				 bgbean.setJhid(bgId);
				 bgbean.setJhbh(jhbh);
				 bgbean.setJhbhrow(bgbhRow);
				 bgbean.setRow(bgrow);
				 bgbean.setField("型号");
				 bgbean.setOldContent(gxxh);
				 bgbean.setNewContent(gxxhnew);
				 jtjhBgService.add(bgbean);
				 bgrow++;
				 changeFlag=1;
			 }
			 */
			 /**
			 if (!(gxgg.equals(gxggnew)) &&    StringUtils.isNotBlank(gxggnew) )
			 {
				 ddbean.setGxgg(gxggnew);
				 JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
				 bgbean.setAccept(0);
				 bgbean.setBh(bh);
				 bgbean.setCreateBy(username);
				 bgbean.setJhid(bgId);
				 bgbean.setJhbh(jhbh);
				 bgbean.setJhbhrow(bgbhRow);
				 bgbean.setRow(bgrow);
				 bgbean.setField("规格");
				 bgbean.setOldContent(gxgg);
				 bgbean.setNewContent(gxggnew);
				 jtjhBgService.add(bgbean);
				 bgrow++;
				 changeFlag=1;
			 }
			 */
			
			 if (!(gxdy.equals(gxdynew)) &&  StringUtils.isNotBlank(gxdynew) )
			 {
				 ddbean.setGxdy(gxdynew);
				 JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
				 bgbean.setAccept(0);
				 bgbean.setBh(bh);
				 bgbean.setCreateBy(username);
				 bgbean.setJhid(bgId);
				 bgbean.setJhbh(jhbh);
				 bgbean.setJhbhrow(bgbhRow);
				 bgbean.setRow(bgrow);
				 bgbean.setField("电压");
				 bgbean.setOldContent(gxdy);
				 bgbean.setNewContent(gxdynew);
				 jtjhBgService.add(bgbean);
				 bgrow++;
				 changeFlag=1;
			 }
			 if (!(gxgy.equals(gxgynew)) &&  StringUtils.isNotBlank(gxgynew) )
			 {
				 ddbean.setGxgy(gxgynew);
				 JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
				 bgbean.setAccept(0);
				 bgbean.setBh(bh);
				 bgbean.setCreateBy(username);
				 bgbean.setJhid(bgId);
				 bgbean.setJhbh(jhbh);
				 bgbean.setJhbhrow(bgbhRow);
				 bgbean.setRow(bgrow);
				 bgbean.setField("工艺");
				 bgbean.setOldContent(gxgy);
				 bgbean.setNewContent(gxgynew);
				 jtjhBgService.add(bgbean);
				 bgrow++;
				 changeFlag=1;
			 }
			 /**
			 if (!(gxdw.equals(gxdwnew)) &&  StringUtils.isNotBlank(gxdwnew) )
			 {
				 ddbean.setGxdw(gxdwnew);
				 JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
				 bgbean.setAccept(0);
				 bgbean.setBh(bh);
				 bgbean.setCreateBy(username);
				 bgbean.setJhid(bgId);
				 bgbean.setJhbh(jhbh);
				 bgbean.setJhbhrow(bgbhRow);
				 bgbean.setRow(bgrow);
				 bgbean.setField("单位");
				 bgbean.setOldContent(gxdw);
				 bgbean.setNewContent(gxdwnew);
				 jtjhBgService.add(bgbean);
				 bgrow++;
				 changeFlag=1;
			 }
			 */
			 /**
			 if (!(gxlb.equals(gxlbnew)) &&  StringUtils.isNotBlank(gxlbnew) )
			 {
				 ddbean.setGxlb(gxlbnew);
				 JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
				 bgbean.setAccept(0);
				 bgbean.setBh(bh);
				 bgbean.setCreateBy(username);
				 bgbean.setJhid(bgId);
				 bgbean.setJhbh(jhbh);
				 bgbean.setJhbhrow(bgbhRow);
				 bgbean.setRow(bgrow);
				 bgbean.setField("类别");
				 bgbean.setOldContent(gxlb);
				 bgbean.setNewContent(gxlbnew);
				 jtjhBgService.add(bgbean);
				 bgrow++;
				 changeFlag=1;
			 }
			 */
			 /**
			 if (!(jhsl_xs.equals(jhsl_xsnew)) &&  StringUtils.isNotBlank(jhsl_xsnew) )
			 {
				 ddbean.setJhsl_xs(jhsl_xsnew);
				 JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
				 bgbean.setAccept(0);
				 bgbean.setBh(bh);
				 bgbean.setCreateBy(username);
				 bgbean.setJhid(bgId);
				 bgbean.setJhbh(jhbh);
				 bgbean.setJhbhrow(bgbhRow);
				 bgbean.setRow(bgrow);
				 bgbean.setField("产品数量");
				 bgbean.setOldContent(jhsl_xs);
				 bgbean.setNewContent(jhsl_xsnew);
				 jtjhBgService.add(bgbean);
				 bgrow++;
				 changeFlag=1;
			 }
			 */
			 /**
			 if (!(jhsl_o.equals(jhsl_onew)) &&  StringUtils.isNotBlank(jhsl_onew) )
			 {
				 ddbean.setJhsl_o(Double.valueOf(jhsl_onew));
				 JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
				 bgbean.setAccept(0);
				 bgbean.setBh(bh);
				 bgbean.setCreateBy(username);
				 bgbean.setJhid(bgId);
				 bgbean.setJhbh(jhbh);
				 bgbean.setJhbhrow(bgbhRow);
				 bgbean.setRow(bgrow);
				 bgbean.setField("机台数量");
				 bgbean.setOldContent(jhsl_o);
				 bgbean.setNewContent(jhsl_onew);
				 jtjhBgService.add(bgbean);
				 bgrow++;
				 changeFlag=1;
			 }
			 */
			 /**
			 if (!(jhrq.equals(jhrqnew))  &&  StringUtils.isNotBlank(jhrqnew) )
			 {
				 Date ddate=null;
				 //sdate=sdf.format(new Date());	
				 try {
					  ddate = sdf.parse(jhrqnew);
					 } catch (ParseException e) {
					 e.printStackTrace();
				 }
				 ddbean.setJhrq(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date
				 JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
				 bgbean.setAccept(0);
				 bgbean.setBh(bh);
				 bgbean.setCreateBy(username);
				 bgbean.setJhid(bgId);
				 bgbean.setJhbh(jhbh);
				 bgbean.setJhbhrow(bgbhRow);
				 bgbean.setField("完工日期");
				 bgbean.setRow(bgrow);
				 bgbean.setOldContent(jhrq);
				 bgbean.setNewContent(jhrqnew);
				 jtjhBgService.add(bgbean);
				 bgrow++;
				 changeFlag=1;
			 }
			 */
			 if (!(gxjsyq.equals(gxjsyqnew)) &&  StringUtils.isNotBlank(gxjsyqnew))
			 {
				 ddbean.setGxjsyq(gxjsyqnew);
				 JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
				 bgbean.setAccept(0);
				 bgbean.setBh(bh);
				 bgbean.setCreateBy(username);
				 bgbean.setJhid(bgId);
				 bgbean.setJhbh(jhbh);
				 bgbean.setJhbhrow(bgbhRow);
				 bgbean.setField("技术要求");
				 bgbean.setRow(bgrow);
				 bgbean.setOldContent(gxjsyq);
				 bgbean.setNewContent(gxjsyqnew);
				 jtjhBgService.add(bgbean);
				 bgrow++;
				 changeFlag=1;
			 }
			 if (!(gxph.equals(gxphnew)) &&  StringUtils.isNotBlank(gxphnew))
			 {
				 ddbean.setGxph(gxphnew);
				 JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
				 bgbean.setAccept(0);
				 bgbean.setBh(bh);
				 bgbean.setCreateBy(username);
				 bgbean.setJhid(bgId);
				 bgbean.setJhbh(jhbh);
				 bgbean.setJhbhrow(bgbhRow);
				 bgbean.setField("批号");
				 bgbean.setRow(bgrow);
				 bgbean.setOldContent(gxph);
				 bgbean.setNewContent(gxphnew);
				 jtjhBgService.add(bgbean);
				 bgrow++;
				 changeFlag=1;
			 }
			 if (!(state.equals(statenew))  &&  StringUtils.isNotBlank(statenew) )
			 {
				 ddbean.setState(Integer.valueOf(statenew));
				 JtjhBgBean bgbean=new JtjhBgBean();
				 bgbean.setGd(bgGd);
			     bgbean.setSbmc(bgSbmc);
				 bgbean.setAccept(0);
				 bgbean.setBh(bh);
				 bgbean.setCreateBy(username);
				 bgbean.setJhid(bgId);
				 bgbean.setJhbh(jhbh);
				 bgbean.setJhbhrow(bgbhRow);
				 bgbean.setRow(bgrow);
				 bgbean.setField("状态");
				 bgbean.setOldContent(state);
				 bgbean.setNewContent(statenew);
				 jtjhBgService.add(bgbean);
				 bgrow++;
				 changeFlag=1;
			 }
			 if (changeFlag==1)
			 {
				  jtjhService.update(ddbean);
				  sendSuccessMessage(response, "机台计划变更成功~");
				  
			 }
			 else {
		   
				sendFailureMessage(response, "未进行机台计划变更！");
			 }
		 }
	}	

	@RequestMapping("/saveDdbgAccept")  //机台计划变更确认保存
	public void saveDdbgAccept(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 request.setCharacterEncoding("UTF-8");  
         //获取编辑数据  
		 String bh  = request.getParameter("bh");
	     String username = SessionUtils.getUser(request).getNickName();
		 List<JtjhBgBean> bgdataList  = jtjhBgService.queryListByBh(bh);
	     if(bgdataList.size()  == 0){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		 }
		 for (Object ele:bgdataList )
		 {
             JtjhBgBean bgbean = (JtjhBgBean)ele;
			 bgbean.setAcceptBy(username);
             bgbean.setAccept(1);
	         jtjhBgService.update(bgbean);
		 }

         sendSuccessMessage(response, "变更单【确认】成功~");
	}	


    //机台计划变更
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		 JtjhBean bean  = jtjhService.queryById(id);
	     if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
        List<JtjhWghbBean> wghbDataList=jtjhWghbService.queryListByJhId(bean.getId());
		if(wghbDataList ==null ||  wghbDataList.size()==-1){
			sendFailureMessage(response, "该半成品计划尚未进行完工汇报,不需要进行变更!");
			return;
		}
	
		List<JtjhBean> dataList = jtjhService.queryListById(id);

		List<JtjhBean> result = new ArrayList<JtjhBean>();	

	    for (Object ele : dataList)
	    {
		    JtjhBean elebean = (JtjhBean)ele;
		    result.add(elebean);
	    }
		JSONArray jsonArr= new JSONArray(result);

	    String username = SessionUtils.getUser(request).getNickName();
		String lrr=bean.getCreateBy();
		//if (username.equals(lrr))
		//{
           	//Map<String,Object>  datanew = new HashMap<String,Object> ();
			JSONObject datanew= new JSONObject();
			datanew.put("sbmcnew","");
			datanew.put("iszlnew","");
		    datanew.put("gxxhnew","");
		    datanew.put("gxggnew","");
		    datanew.put("gxdynew","");
            datanew.put("gxgynew","");
		    datanew.put("gxdwnew","");
		    datanew.put("gxlbnew","");
		    datanew.put("jhsl_xsnew","");
		    datanew.put("jhsl_onew","");				
            datanew.put("jhrqnew","");
		    datanew.put("gxjsyqnew","");
			datanew.put("gxphnew","");
			datanew.put("statenew","");
			context.put("data1",datanew);

            String jsonStr1=jsonArr.toString().replace("[","").replace("]","");
			jsonStr1=jsonStr1.substring(0,jsonStr1.length()-1);


            String jsonStr2=datanew.toString();
			jsonStr2=jsonStr2.substring(1,jsonStr2.length());

            context.put("data", jsonStr1+","+jsonStr2);

			context.put(SUCCESS, true);	

//System.out.println("高绪山-xsdd-Getddbg"+context.toString());
	        HtmlUtil.writerJson(response, context);
			return ;
		//}
		//else 
		//{
		//    sendFailureMessage(response, "该机台计划单为("+bean.getCreateBy()+")创建，不能进行变更!");
		//    return;
		//}
	}

   //机台计划变更确认
	@RequestMapping("/getDdbgAccept")
	public void getDdbgAccept(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		JSONObject datanew= new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		 JtjhBgBean bean  = jtjhBgService.queryById(id);
	     if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}	
        int bgjhid=bean.getJhid();	
		String bgbh=bean.getBh();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		List<JtjhBean> dddataList = jtjhService.queryListById(bgjhid);

		List<JtjhBean> result = new ArrayList<JtjhBean>();	

	    for (Object ele : dddataList)
	    {
		    JtjhBean elebean = (JtjhBean)ele;
		    result.add(elebean);
	    }

	    //该编号的所有变更内容
		List<JtjhBgBean> bgdataList = jtjhBgService.queryListByBh(bgbh);
		    datanew.put("sbmcnew","");
			datanew.put("iszlnew","");
		  	datanew.put("gxxhnew","");
			datanew.put("gxggnew","");
			datanew.put("gxdynew","");
			datanew.put("gxgynew","");
			datanew.put("gxdwnew","");
			datanew.put("gxlbnew","");
			datanew.put("jhsl_xsnew","");
			datanew.put("jhsl_onew","");
			datanew.put("jhrqnew","");
			datanew.put("gxjsyqnew","");
			datanew.put("gxphnew","");
			datanew.put("statenew","");
        for (Object ele : bgdataList)
	    {

			JtjhBgBean bgbean = (JtjhBgBean)ele;
			if ("设备名称".equals(bgbean.getField()))
			{
				dddataList.get(0).setSbmc(bgbean.getOldContent());
				String sbmcnew=bgbean.getNewContent();
	            datanew.put("sbmcnew",sbmcnew);
				continue;
		    }
			if ("主零".equals(bgbean.getField()))
			{
				dddataList.get(0).setIszl(bgbean.getOldContent());
				String iszlnew=bgbean.getNewContent();
	            datanew.put("iszlnew",iszlnew);
				continue;
		    }
			if ("型号".equals(bgbean.getField()))
			{
				dddataList.get(0).setGxxh(bgbean.getOldContent());
				String gxxhnew=bgbean.getNewContent();
	            datanew.put("gxxhnew",gxxhnew);
				continue;
		    }

			if ("规格".equals(bgbean.getField()))
			{
				dddataList.get(0).setGxgg(bgbean.getOldContent());
				String gxggnew=bgbean.getNewContent();
				datanew.put("gxggnew",gxggnew);
				continue;
		    }	
			
			if ("电压".equals(bgbean.getField()))
			{
				dddataList.get(0).setGxdy(bgbean.getOldContent());
				String gxdynew=bgbean.getNewContent();
		        datanew.put("gxdynew",gxdynew);
				continue;
		    }	

		    if ("工艺".equals(bgbean.getField()))
			{
				dddataList.get(0).setGxgy(bgbean.getOldContent());
				String gxgynew=bgbean.getNewContent();
				datanew.put("gxgynew",gxgynew);
				continue;
		    }	
		
		    if ("单位".equals(bgbean.getField()))
			{
				dddataList.get(0).setGxdw(bgbean.getOldContent());
				String gxdwnew=bgbean.getNewContent();
                datanew.put("gxdwnew",gxdwnew);
				continue;
		    }	
		    if ("类别".equals(bgbean.getField()))
			{
				dddataList.get(0).setGxlb(bgbean.getOldContent());
				String gxlbnew=bgbean.getNewContent();
                datanew.put("gxlbnew",gxlbnew);
				continue;
		    }	

		    if ("产品数量".equals(bgbean.getField()))
			{
				dddataList.get(0).setJhsl_xs(bgbean.getOldContent());
				String jhsl_xsnew=bgbean.getNewContent();
				datanew.put("jhsl_xsnew",jhsl_xsnew);	
				continue;
		    }	
		    if ("机台数量".equals(bgbean.getField()))
			{
				dddataList.get(0).setJhsl_o(Double.valueOf(bgbean.getOldContent()));
				String jhsl_onew=bgbean.getNewContent();
				datanew.put("jhsl_onew",jhsl_onew);	
				continue;
		    }	


		    if ("完工日期".equals(bgbean.getField()))
			{
			    Date ddate=null;

	            try {
					if (StringUtils.isNotBlank(bgbean.getOldContent()))
					{
	                    ddate = sdf.parse(bgbean.getOldContent());
					}
                 } catch (ParseException e) {
                    e.printStackTrace();
                }
				if (ddate!=null)
				{
	
				    dddataList.get(0).setJhrq(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date
				}
				else{
					dddataList.get(0).setJhrq(null);
				}
				String jhrqnew=bgbean.getNewContent();
				datanew.put("jhrqnew",jhrqnew);
				continue;
		    }

		    if ("技术要求".equals(bgbean.getField()))
			{
				dddataList.get(0).setGxjsyq(bgbean.getOldContent());
				String gxjsyqnew=bgbean.getNewContent();
				datanew.put("gxjsyqnew",gxjsyqnew);
				continue;
		    }	
		    if ("批号".equals(bgbean.getField()))
			{
				dddataList.get(0).setGxph(bgbean.getOldContent());
				String gxphnew=bgbean.getNewContent();
				datanew.put("gxphnew",gxphnew);
				continue;
		    }	


		    if ("状态".equals(bgbean.getField()))
			{
				dddataList.get(0).setState(Integer.valueOf(bgbean.getOldContent()));
				String statenew=bgbean.getNewContent();
				datanew.put("statenew",statenew);
				continue;
		    }	


	    }
		datanew.put("bh",bgbh);
		datanew.put("acceptBy",bgdataList.get(0).getAcceptBy());
		datanew.put("acceptTime",bgdataList.get(0).getAcceptTime()!=null?(sdf.format(bgdataList.get(0).getAcceptTime())):"");

		context.put("data1",datanew);
		JSONArray jsonArr= new JSONArray(result);

        String jsonStr1=jsonArr.toString().replace("[","").replace("]","");
    	jsonStr1=jsonStr1.substring(0,jsonStr1.length()-1);

        String jsonStr2=datanew.toString();
		jsonStr2=jsonStr2.substring(1,jsonStr2.length());

        context.put("data", jsonStr1+","+jsonStr2);
		context.put(SUCCESS, true);	
//System.out.println("高绪山-xsdd-GetddbgAccept"+context.toString());

        HtmlUtil.writerJson(response, context);
		return ;
	
	}
		
		
  
  
    //不重复的产品型号
	@RequestMapping("/getUniXh")
	public void getUniXh(HttpServletResponse response) throws Exception{
	
		List<JtjhBean> dataList = jtjhService.queryByAll();
	
	    List<String> xh1 = new ArrayList();
		for (Object ele : dataList)
		{
			JtjhBean st = (JtjhBean)ele;
			xh1.add(st.getGxxh());
		}
		

		//删除重复的
		List<String> unixh1=new ArrayList();
		unixh1=removeDuplicate(xh1);
		Collections.sort(unixh1);
		List<JsonBean> unixh = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<unixh1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(unixh1.get(i));
            id++;
			unixh.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(unixh);
		String jsonStr=jsonArr.toString();
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	}

    //不重复的产品规格
	@RequestMapping("/getUniGg")
	public void getUniGg(HttpServletResponse response) throws Exception{
	
		List<JtjhBean> dataList = jtjhService.queryByAll();

		List<String> gg1 = new ArrayList();

		for (Object ele : dataList)
		{
			JtjhBean st = (JtjhBean)ele;
			gg1.add(st.getGxgg());
		}
		

		//删除重复的
	
		List<String> unigg1=new ArrayList();
		unigg1=removeDuplicate(gg1);
		Collections.sort(unigg1);

		List<JsonBean> unigg = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<unigg1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(unigg1.get(i));
            id++;
			unigg.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(unigg);
		String jsonStr=jsonArr.toString();
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	}

 
	 /**
	 * 返回不重复的List元素
	 * @param List 
	 * @return 不重复的List
	 */
	public List<String> removeDuplicate(List<String> list)
	{
       for ( int i = 0 ; i < list.size() - 1 ; i ++ ) 
	   {
            for ( int j = list.size() - 1 ; j > i; j -- )
			{
                if (list.get(j).equals(list.get(i))) 
				{ 
				    list.remove(j);
                 }
            }
       }
	   return list;
    }	
}
