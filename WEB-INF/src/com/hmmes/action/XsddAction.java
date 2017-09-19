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
import javax.servlet.http.HttpSession;


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
import com.hmmes.bean.XsddBean;
import com.hmmes.bean.XsddsBean;
import com.hmmes.bean.JhbhBean;
import com.hmmes.bean.XsddWghbBean;
import com.hmmes.bean.XsddRkhbBean;
import com.hmmes.bean.XsddBgBean;
import com.hmmes.bean.JtjhBean;
import com.hmmes.bean.JtjhWghbBean;
import com.hmmes.bean.TreeNode;
import com.hmmes.bean.BaseBean.DELETED;

import com.hmmes.model.XsddModel;
import com.hmmes.model.XsddBgModel;

import com.hmmes.service.JhbhService;
import com.hmmes.service.XsddService;
import com.hmmes.service.XsddsService;
import com.hmmes.service.XsddWghbService;
import com.hmmes.service.XsddRkhbService;
import com.hmmes.service.XsddBgService;
import com.hmmes.service.JtjhService;
import com.hmmes.service.JtjhWghbService;
import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.XsddTree;
import com.hmmes.utils.SessionUtils;
import com.hmmes.utils.DateUtil;
import com.hmmes.utils.StringUtil;
import com.hmmes.utils.ExportExcel;
import com.hmmes.utils.excelutils.ExcelHelper;
import com.hmmes.utils.excelutils.JxlExcelHelper;
import com.hmmes.utils.ListUtils;

import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;  
import java.util.*;
import java.io.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

 
@Controller
@RequestMapping("/xsddManage") 
public class XsddAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(XsddAction.class);
	
	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private XsddService<XsddBean> xsddService; 
	
	@Autowired
	private JhbhService<JhbhBean> jhbhService;

	@Autowired
	private  XsddsService<XsddsBean> xsddsService;

	@Autowired
	private  XsddWghbService<XsddWghbBean> xsddWghbService;	

	@Autowired
	private  XsddRkhbService<XsddRkhbBean> xsddRkhbService;	

	@Autowired
	private  XsddBgService<XsddBgBean> xsddBgService;	
	@Autowired
	private JtjhService<JtjhBean> jtjhService;	
	@Autowired
	private JtjhWghbService<JtjhWghbBean> jtjhWghbService;		
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 */
							
	@RequestMapping("/xsdd")
	public ModelAndView  xsdd(XsddModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
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

	
	   /**

		String strtmp="$("+'"'+"#searchForm input:input[name='jhbh']"+'"'+").val('"+"2017070154"+"');";
System.out.println("高绪山：Xsdd--out "+strtmp);
        out.write("<script type='text/javascript'>");
        //out.write("alert('文件导入成功！');");	
	    out.write("$("+'"'+"#searchForm input:input[name='jhbh']"+'"'+").val('"+"2017"+"');");
	    //out.write("<script type='text/javascript'>"
		//					+ "parent.callback('文件导入成功！');"
		//					+ "</script>");

		//$("#searchForm input:input[name='jhbh']").val('2017');
		out.write("var param =$('#searchForm').serializeObject();");
		out.write("$('#data-list').datagrid('reload',param);");
        out.write("</script>");
		*/
      	//response.setContentType("text/html;charset=gbk");
		//// 获取输出流
		//PrintWriter out = response.getWriter();
		//out.write("<script type='text/javascript'>"
		//				+ "parent.initdate(fromdate,todate);"
		//				+ "</script>");


		Map<String,Object>  context = getRootMap();
	    model.setDeleted(DELETED.NO.key);
		List<XsddBean> dataList = xsddService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);

	    return forword("business/xsddManage",context); 
	
		
	}
	//销售订单变更
	@RequestMapping("/xsddchange")
	public ModelAndView  xsddchange(XsddModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
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
		List<XsddBean> dataList = xsddService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);

	    return forword("business/xsddChange",context); 
	
		
	}
	//销售订单变更审核
	@RequestMapping("/xsddChangeCheck")
	public ModelAndView  xsddChangeCheck(XsddBgModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
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
		List<XsddBgBean> dataList = xsddBgService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);

	    return forword("business/xsddChangeCheck",context); 
	
	}

	//销售订单变更确认
	@RequestMapping("/xsddChangeAccept")
	public ModelAndView  xsddChangeAccept(XsddBgModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
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
		List<XsddBgBean> dataList = xsddBgService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);

	    return forword("business/xsddChangeAccept",context); 
	
	}
	@RequestMapping("/xsddChangeBrow")  //销售订单变更单浏览
	public ModelAndView xsddChangeBrow(XsddBgModel model,HttpServletRequest request) throws Exception{
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
		List<XsddBgBean> dataList = xsddBgService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("business/xsddChangeBrow",context); 
	}	
	
	/**
	 * 交货期答复
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/jhqdf")
	public ModelAndView  jhqdf(XsddModel model,HttpServletRequest request) throws Exception{
		
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
		model.setDeleted(DELETED.NO.key);
		List<XsddBean> dataList = xsddService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("business/jhqConfirm",context); 
	}	

    /**
	 * 延误计划汇报
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/ecjhq")
	public ModelAndView  ecjhq(XsddModel model,HttpServletRequest request) throws Exception{

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
		model.setDeleted(DELETED.NO.key);
		List<XsddBean> dataList = xsddService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("business/ecjhqInput",context); 
	}	
  /**
	 * 销售订单浏览答复
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/xsddbrow")
	public ModelAndView  xsddbrow(XsddModel model,HttpServletRequest request) throws Exception{
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
		model.setDeleted(DELETED.NO.key);
		List<XsddBean> dataList = xsddService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("business/xsddBrow",context); 
	}	
    //机台计划看板
	@RequestMapping("/jtjhwatch")
	public ModelAndView  jtjhwatch(XsddModel model,HttpServletRequest request) throws Exception{
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
		model.setDeleted(DELETED.NO.key);
		List<XsddBean> dataList = xsddService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("business/jtjhWatch",context); 
	}		
		
	/**
	 * 销售订单完工汇报
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/xsddwghb")
	public ModelAndView xsddwghb(XsddModel model,HttpServletRequest request) throws Exception{
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
		model.setDeleted(DELETED.NO.key);
		List<XsddBean> dataList = xsddService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("business/xsddWgReport",context); 
	}

	//电线保管批量完工汇报
	@RequestMapping("/xsddwghb_pl")
	public ModelAndView xsddwghb_pl(XsddModel model,HttpServletRequest request) throws Exception{
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
		model.setDeleted(DELETED.NO.key);
		List<XsddBean> dataList = xsddService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("business/xsddWgReport_pl",context); 
	}
	/**
	 * 销售订单入库汇报
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/xsddrkhb")
	public ModelAndView xsddrkhb(XsddModel model,HttpServletRequest request) throws Exception{

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
		model.setDeleted(DELETED.NO.key);
		List<XsddBean> dataList = xsddService.queryByList(model);
		//设置页面数据
		context.put("dataList", dataList);
		return forword("business/xsddRkReport",context); 
	}		
	
     /**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList")   //DataGrid 对于销售订单下达、交货期答复、二次交货期答复，不需要链接完工汇报(Wghb)和入库汇报(Rkhb)
	public void  dataList(XsddModel model,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<XsddBean> dataList = xsddService.queryByList(model);
		List<XsddBean> result = new ArrayList<XsddBean>();

		for (Object ele : dataList)
		{
			XsddBean st = (XsddBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山：XsddAction--dataList"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}
	
	
	/**
	 * json 列表页面
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataListLinkNoPage")   //不分页的，用于销售订单浏览
	public void  dataListLinkNoPage(XsddModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<XsddBean> dataList = xsddService.queryByList(model);
		//List<XsddBean> dataList = xsddService.queryByListNoPage(model);
        //List<XsddBean> dataList = xsddService.queryByAll();
		List<XsddBean> result = new ArrayList<XsddBean>();	
		int totaljh=0;
		int noontimejh=0;
		DecimalFormat  df2   = new DecimalFormat("######0.00");
		// 封装VO集合
		for (Object ele : dataList)
		{
			XsddBean st = (XsddBean)ele;

			Double ddsl=st.getSl()==null?0:st.getSl();
			DecimalFormat  df0   = new DecimalFormat("######0");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	        int ddid=st.getId();
			List<XsddWghbBean> wghbList = xsddWghbService.queryListById(ddid);
            Integer qbrk=2;//0
		    String sdate=sdf.format(new Date());
		    Date ud= sdf.parse(sdate);
		    java.sql.Date sd = new java.sql.Date(ud.getTime());   
		    long cqts=-9999;

			if (wghbList!=null && wghbList.size()>0 )
			{

                 if ((wghbList.get(0).getWgrq())!=null)
                 {
		              st.setMaxWgrq(wghbList.get(0).getWgrq());
	     	     }
				 Double sumwgsl=0.0;
				 Double sumcmsl=0.0;
				 Double sumrksl=0.0;
				 

				 StringBuffer sumwgslds = new StringBuffer();
				 StringBuffer sumwgslss = new StringBuffer();
				 StringBuffer sumxpgg = new StringBuffer();
				 StringBuffer sumrkslds = new StringBuffer();
		      	 for(int i=0;i<wghbList.size();i++){
			         XsddWghbBean wghb = wghbList.get(i);
			         sumwgslds.append(wghb.getWgsl()!=null?df0.format(wghb.getWgsl())+"":"");
			         sumxpgg.append(wghb.getXpgg()+"");
					 sumwgslss.append(wghb.getWgslss()+"");
					 sumwgsl=sumwgsl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
                     sumcmsl=sumcmsl+(wghb.getCmsl()==null?0.0:wghb.getCmsl());
					 if (wghb.getRk()!=null && wghb.getRk()==1)
					 {
					     sumrkslds.append(wghb.getWgsl()!=null?df0.format(wghb.getWgsl())+"":"");
					     sumrksl=sumrksl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
					 }
			         if((i+1) < wghbList.size()){
				         sumwgslds.append("、");
						 sumwgslss.append("、");
						 sumxpgg.append("、");
						 if (wghb.getRk()!=null && wghb.getRk()==1 ){
						     sumrkslds.append("、");
						 }
			         }
		         }
				 if (sumrksl>=ddsl )
				 {
					 qbrk=1;
				 }
				 else{
				     if (ddsl!=0)
				     {
					     if ((ddsl-sumrksl)/ddsl<=0.1)  //小于10%视为入库
					     {
						     qbrk=1;
					     }
				     }
				 }					 
		
				 if (st.getJhrq()!=null && !"".equals(st.getJhrq()))
				 {
		
				     if (qbrk==1)
				     {
                         if (st.getMaxWgrq()==null || "".equals(st.getMaxWgrq()))
                         {
                             cqts=(sd.getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
                         }
						 else{
						     cqts=(st.getMaxWgrq().getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
                         }				     }
				     else{
					     if ((sd.getTime()-st.getJhrq().getTime())<=0)
						 {
							  cqts=-9999;
						 }
						 else{
						     cqts=(sd.getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
						 }
					 }

				 }
				 else{
					 cqts=-9999;
				 }

				 st.setSumWgsl(sumwgsl);
				 st.setSumCmsl(sumcmsl);
				 st.setSumWgslds("【"+sumwgslds.toString()+"】");
				 st.setSumWgslss("【"+sumwgslss.toString()+"】");
				 st.setSumXpgg("【"+sumxpgg.toString()+"】");
				 st.setSumRksl(sumrksl);
				 st.setQbRk(qbrk);
				 st.setSumRkslds("【"+sumrkslds.toString()+"】");
				 if (st.getState()==1)
				 { 
					st.setCqts(cqts);
				 }
				 //xsddService.updateBean(st);//
			}
			else{
				 if (st.getJhrq()!=null && !"".equals(st.getJhrq())){
				     if ((sd.getTime()-st.getJhrq().getTime())<=0)
				     {
					     cqts=-9999;
				     }
				     else{
				         cqts=(sd.getTime()-st.getJhrq().getTime())/ 1000 / 60 / 60 / 24;
				     }
				 }
				 else{
					 cqts=-9999;
				 }
			     if (st.getState()==1)
				 { 
				     st.setCqts(cqts);
				 }
				 //st.setQbRk(qbrk);//
				 //xsddService.updateBean(st);//
			}
			/**
			List<JtjhBean> jtjhList = jtjhService.queryListByDdId(ddid);
			if (jtjhList!=null && jtjhList.size()>0 )
			{

				 if ((rkhbList.get(0).getRkrq())!=null)
                 {
              
				     st.setMaxRkrq(rkhbList.get(0).getRkrq());
				 }
				 Double sumrksl=0.0;
				 Integer qbrk=0;
				 StringBuffer sumrkslds = new StringBuffer();
		      	 for(int i=0;i<rkhbList.size();i++){
			         XsddRkhbBean rkhb = rkhbList.get(i);
			         sumrkslds.append(df0.format(rkhb.getRksl())+"");
					 sumrksl=sumrksl+(rkhb.getRksl()==null?0.0:rkhb.getRksl());
					 if (rkhb.getRk()!=null)
					 {
					
					     if (rkhb.getRk()==1)
					     {
						     qbrk=1;
					     } 
					 }

			         if((i+1) < rkhbList.size()){
				         sumrkslds.append("、");

			         }
		         }
				 st.setSumRksl(sumrksl);
				 st.setQbRk(qbrk);
				 st.setSumRkslds("【"+sumrkslds.toString()+"】");

			}
			*/
			if (st.getState()==1)
			{
				 totaljh++;
			}
		   
			if (cqts > 0 && st.getState()==1 )
			{
  			    noontimejh++;
			}
			result.add(st);
		}
		if (totaljh!=0)
		{
		
		    XsddBean st = new XsddBean();
            st.setJhbh("入库及时率=");
		    st.setXh((totaljh-noontimejh)+"/"+totaljh+"="+df2.format(100*(totaljh-noontimejh)/totaljh)+"%");
            result.add(st);
        }
		//设置页面数据
		//JSONObject context = new JSONObject();

		//JSONObject jsonMap = new JSONObject();
		//jsonMap.put("total",model.getPager().getRowCount());
		//jsonMap.put("rows", result);
//System.out.println("高绪山-xsdd-Json"+jsonMap.toString());
		//HtmlUtil.writerJson(response, jsonMap);
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
        //request.setAttribute("data",jsonArr.toString());
		//context.put("data", jsonArr.toString());
//System.out.println("高绪山：XsddAction--dataList"+jsonStr);	
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
		//HtmlUtil.writerJson(response, jsonStr); 
		//HtmlUtil.writerJson(response, context); 

	}
	
	@RequestMapping("/dataListLink")   //DataGrid url 对于销售订单、质量部完工汇报、统计入库汇报，链接完工汇报(Wghb)和入库汇报(Rkhb)数据库，添加信息
	public void  dataListLink(XsddModel model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<XsddBean> dataList = xsddService.queryByList(model);
		List<XsddBean> result = new ArrayList<XsddBean>();	
				// 封装VO集合
		for (Object ele : dataList)
		{
			XsddBean st = (XsddBean)ele;
			Double ddsl=st.getSl()==null?0:st.getSl();
			DecimalFormat  df0   = new DecimalFormat("######0");
			int ddid=st.getId();
			List<XsddWghbBean> wghbList = xsddWghbService.queryListById(ddid);

			if (wghbList!=null && wghbList.size()>0 )
			{

                 //if ((wghbList.get(0).getWgrq())!=null)
                 //{
		         //     st.setMaxWgrq(wghbList.get(0).getWgrq());
                 //     st.setWgzs(wghbList.get(0).getCzg());
	     	     //}
				 Double sumwgsl=0.0;
				 Double sumcmsl=0.0;
				 Double sumrksl=0.0;
				 Integer qbrk=0;

				 StringBuffer sumwgslds = new StringBuffer();
				 StringBuffer sumwgslss = new StringBuffer();
				 StringBuffer sumxpgg = new StringBuffer();
				 StringBuffer sumrkslds = new StringBuffer();
		      	 for(int i=0;i<wghbList.size();i++){
			         XsddWghbBean wghb = wghbList.get(i);
			         sumwgslds.append(wghb.getWgsl()!=null?df0.format(wghb.getWgsl())+"":"");
			         sumxpgg.append(wghb.getXpgg()+"");
					 sumwgslss.append(wghb.getWgslss()+"");
					 sumwgsl=sumwgsl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
                     sumcmsl=sumcmsl+(wghb.getCmsl()==null?0.0:wghb.getCmsl());
					 if (wghb.getRk()!=null && wghb.getRk()==1)
					 {
					     sumrkslds.append(wghb.getWgsl()!=null?df0.format(wghb.getWgsl())+"":"");
					     sumrksl=sumrksl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
					 }
			         if((i+1) < wghbList.size()){
				         sumwgslds.append("、");
						 sumwgslss.append("、");
						 sumxpgg.append("、");
						 if (wghb.getRk()!=null && wghb.getRk()==1 ){
						     sumrkslds.append("、");
						 }
			         }
		         }
				 if (sumrksl>=ddsl )
				 {
					 qbrk=1;
				 }
				 st.setSumWgsl(sumwgsl);
				 st.setSumCmsl(sumcmsl);
				 st.setSumWgslds("【"+sumwgslds.toString()+"】");
				 st.setSumWgslss("【"+sumwgslss.toString()+"】");
				 st.setSumXpgg("【"+sumxpgg.toString()+"】");
				 st.setSumRksl(sumrksl);
				 st.setQbRk(qbrk);
				 st.setSumRkslds("【"+sumrkslds.toString()+"】");
			}
			/**
			List<XsddRkhbBean> rkhbList = xsddRkhbService.queryListById(ddid);
			if (rkhbList!=null && rkhbList.size()>0 )
			{

				 if ((rkhbList.get(0).getRkrq())!=null)
                 {
              
				     st.setMaxRkrq(rkhbList.get(0).getRkrq());
				 }
				 Double sumrksl=0.0;
				 Integer qbrk=0;
				 StringBuffer sumrkslds = new StringBuffer();
		      	 for(int i=0;i<rkhbList.size();i++){
			         XsddRkhbBean rkhb = rkhbList.get(i);
			         sumrkslds.append(df0.format(rkhb.getRksl())+"");
					 sumrksl=sumrksl+(rkhb.getRksl()==null?0.0:rkhb.getRksl());
					 if (rkhb.getRk()!=null)
					 {
					
					     if (rkhb.getRk()==1)
					     {
						     qbrk=1;
					     } 
					 }

			         if((i+1) < rkhbList.size()){
				         sumrkslds.append("、");

			         }
		         }
				 st.setSumRksl(sumrksl);
				 st.setQbRk(qbrk);
				 st.setSumRkslds("【"+sumrkslds.toString()+"】");

			}
			*/
		    
			result.add(st);
		}
		//设置页面数据
		//JSONObject jsonMap = new JSONObject();
		//jsonMap.put("total",model.getPager().getRowCount());
		//jsonMap.put("rows", result);
//System.out.println("高绪山-xsdd-Json"+jsonMap.toString());
		//HtmlUtil.writerJson(response, jsonMap);
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山：XsddAction--dataList"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	}

	@RequestMapping("/dataListChange")   //销售变更单DataGrid 
	public void  dataListChange(XsddBgModel model,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<XsddBgBean> dataList = xsddBgService.queryByList(model);
		List<XsddBgBean> result = new ArrayList<XsddBgBean>();

		for (Object ele : dataList)
		{
			XsddBgBean st = (XsddBgBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山：XsddAction--dataListchange"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}

	@RequestMapping("/dataListChangeForDdid")   //根据销售订单ID，查询销售变更单显示DataGrid 
	public void  dataListChangeForDdid(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<XsddBgBean> dataList = xsddBgService.queryListById(id);
		List<XsddBgBean> result = new ArrayList<XsddBgBean>();

		for (Object ele : dataList)
		{
			XsddBgBean st = (XsddBgBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山：XsddAction--dataListchangeForDdid"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}
    //根据销售订单ID，查询完工汇报单显示DataGrid 
	@RequestMapping("/dataListWghbForDdid")   //根据销售订单ID，查询完工汇报单显示DataGrid 
	public void  dataListWghbForDdid(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<XsddWghbBean> dataList = xsddWghbService.queryListById(id);
		List<XsddWghbBean> result = new ArrayList<XsddWghbBean>();

		for (Object ele : dataList)
		{
			XsddWghbBean st = (XsddWghbBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山：XsddAction--dataListWghbForDdid"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}

    //根据销售订单ID，查询入库汇报单显示DataGrid 
	@RequestMapping("/dataListRkhbForDdid")   //根据销售订单ID，查询入库汇报单显示DataGrid 
	public void  dataListRkhbForDdid(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<XsddWghbBean> dataList = xsddWghbService.queryListById(id);
		List<XsddWghbBean> result = new ArrayList<XsddWghbBean>();

		for (Object ele : dataList)
		{
			XsddWghbBean st = (XsddWghbBean)ele;
	
			result.add(st);
		}

	    JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山：XsddAction--dataListRkhbForDdid"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);
	
		return ;
	}

   //根据销售订单ID，查询机台计划显示DataGrid 
	@RequestMapping("/dataListJtjhForDdId")   //根据销售订单ID，查询入库汇报单显示DataGrid 
	public void  dataListJtjhForDdId(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();	
		List<JtjhBean> dataList = jtjhService.queryListByDdId(id);
		List<JtjhBean> result = new ArrayList<JtjhBean>();

	    for (Object ele : dataList)
		{
	            JtjhBean st = (JtjhBean)ele;
				Double jhsl=st.getJhsl();
			    DecimalFormat  df0   = new DecimalFormat("######0");
			    Integer  jhid=st.getId();
			    List<JtjhWghbBean> wghbList = jtjhWghbService.queryListByJhId(jhid);

			    if (wghbList!=null && wghbList.size()>0 )
			    {

                     if ((wghbList.get(0).getWgrq())!=null)
                     {
		                  st.setMaxWgrq(wghbList.get(0).getWgrq());
	     	         }
				     Double sumwgsl=0.0;
				     Integer qbwg=0;

				     StringBuffer sumwgslds = new StringBuffer();

		      	     for(int i=0;i<wghbList.size();i++){
			             JtjhWghbBean wghb = wghbList.get(i);
			             sumwgslds.append(wghb.getWgsl()!=null?df0.format(wghb.getWgsl())+"":"");
					     sumwgsl=sumwgsl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
					     if (wghb.getWg()!=null)
					     {
					
					         if (wghb.getWg()==1)
					         {
						         qbwg=1;
					         } 
					     }
			
	    	            if((i+1) < wghbList.size()){
				             sumwgslds.append("、");
				        }
		            }
				    if (sumwgsl>=jhsl )
				    {
					    qbwg=1;
				    }

				    st.setSumWgsl(sumwgsl);
				    st.setSumWgslds("【"+sumwgslds.toString()+"】");
				    st.setQbWg(qbwg);

			    }
    
			    result.add(st);
		}

		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山：XsddAction--dataList"+jsonStr);
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);	
	}
	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public void  save(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	    request.setCharacterEncoding("UTF-8");  
        //获取编辑数据 这里获取到的是json字符串  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 String jhbh  = request.getParameter("jhbh");
		 String ywy  = request.getParameter("ywy");
		 String xdrq  = request.getParameter("xdrq");
		 String jhrq_kh  = request.getParameter("jhrq_kh");
//System.out.println("高绪山：XsddAction--add:xdrq="+xdrq);
//System.out.println("高绪山：XsddAction--add:ywy="+ywy);
//System.out.println("高绪山：XsddAction--add:jhbh="+jhbh);
//System.out.println("高绪山：XsddAction--add:jhrq_kh="+jhrq_kh);
//System.out.println("高绪山：XsddAction--add:insert"+inserted);
//System.out.println("高绪山：XsddAction--add:delete"+deleted);
//System.out.println("高绪山：XsddAction--add:update"+updated);

		 String username = SessionUtils.getUser(request).getNickName();
		 if (jhbh==null || "".equals(jhbh))
		 {
             String syear=DateUtil.getNowYear();
			 String smonth=DateUtil.getNowMonth();
			 Integer iyear=Integer.valueOf(syear);
             Integer imonth=Integer.valueOf(smonth);
			 JhbhBean bean  = jhbhService.queryNoByYearAndMonth(iyear, imonth,1);  //1--销售订单编号
			 //@Param("uuid")String uuid, @Param("portletid")String portletid
  		     if(bean  == null){
				  JhbhBean newbean=new JhbhBean();
                  newbean.setNian(iyear);
				  newbean.setYue(imonth);
				  newbean.setFlag(1);
				  newbean.setJhno(1);
				  jhbhService.add(newbean);
				  String sjhno=StringUtil.fillZero("1", 4);
				  jhbh=syear+smonth+sjhno;
		    }
			else 
			{
				 Integer newno=bean.getJhno()+1;
				 bean.setJhno(newno);
				 jhbhService.update(bean);
                 String sjhno=StringUtil.fillZero(newno+"", 4);
				 jhbh=syear+smonth+sjhno;
			}
	     }
         Integer row=0;
		 Integer sortFlag=0;//是否重新替换row
         if(deleted != null){  
             //把json字符串转换成对象  
             sortFlag=1;
             List<XsddBean> listDeleted = new ArrayList<XsddBean>();	
             listDeleted = JSON.parseArray(deleted, XsddBean.class);  
             //TODO 下面就可以根据转换后的对象进行相应的操作了 
			 Integer [] ids=new Integer[listDeleted.size()];

             row=0;
		     for (Object ele : listDeleted )
		     {
				 XsddBean bean = (XsddBean)ele;	  

                  Integer ddid=bean.getId();
                  ids[row]=ddid;
				  row++;
			 } 
			 xsddService.deleteBean(ids);
         }  
 
         if(inserted != null){  
			 sortFlag=1;
             //把json字符串转换成对象  
             List<XsddBean> listInserted = new ArrayList<XsddBean>();	
	
			 listInserted = JSON.parseArray(inserted, XsddBean.class);  
			 for (Object ele : listInserted )
		     {
			     XsddBean bean = (XsddBean)ele;	
				 //bean.setId(null);
		         bean.setJhbh(jhbh);
				 bean.setRow(row);
                 //bean.setYwy(ywy);
				 bean.setDeleted(DELETED.NO.key);
				 bean.setState(1);
			     bean.setXdjt(0);
			     bean.setCreateBy(username);
				 xsddService.addBean(bean);
				 //row++;

		     }
         }  

         if(updated != null){  
            //把json字符串转换成对象  
			sortFlag=1;
		    List<XsddBean> listUpdated = new ArrayList<XsddBean>();	
            listUpdated = JSON.parseArray(updated, XsddBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 XsddBean bean = (XsddBean)ele;	
                 bean.setUpdateBy(username);				 
 				 xsddService.updateBean(bean);
		     }
         }
  		 if (sortFlag==1)
		 {
  		     row=1;
		     List<XsddBean> dataList = new ArrayList<XsddBean>();	

		     dataList = xsddService.queryListByJhbh(jhbh);
		     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		     String sdate;
			 Date dxdrq=null;

		 	 if (xdrq==null || xdrq.trim().length()<1 )
			 {
		         sdate=sdf.format(new Date());	

			 }
			 else 
			 { 
			 	 sdate=xdrq;
			 }
			 try {
 			        
       		     dxdrq = sdf.parse(sdate);
       
              } catch (ParseException e) {
                 e.printStackTrace();
              }

			 Date djhrq_kh=null;
	         int flag=0; //替换标记,明细中的客户要求日期为空，用表头的客户要求日期替换
             if  ( jhrq_kh!=null && jhrq_kh.trim().length()>=1 )
             {
                 
   		         try {
     		          djhrq_kh = sdf.parse(jhrq_kh);
                 } catch (ParseException e) {
                      e.printStackTrace();
                 }
				 flag=1;
			 }
	
	         for (Object ele : dataList )
		     {
			     XsddBean bean = (XsddBean)ele;	    
                 bean.setXdrq(new java.sql.Date(dxdrq.getTime()));  //java.util.Date -->java.sql.Date 

		
				 if ((bean.getJhrq_kh()==null) && (flag==1) )
				 { 
					 bean.setJhrq_kh(new java.sql.Date(djhrq_kh.getTime()));   
				 }
	             bean.setRow(row);
			     bean.setJhbh(jhbh);
				 if (bean.getYwy()==null || "".equals(bean.getYwy().trim()))
				 {
					 bean.setYwy(ywy);
				 }
			     
				 if (bean.getPh()==null || "".equals(bean.getPh())){
					 String phprefix="JH";
					 if ("库存".equals(bean.getYwy())) {
					
						 phprefix="KC";
					 }

					 if ("KC".equals(phprefix) && ((bean.getJsyq()).indexOf("米/盘")!=-1 || 
						 (bean.getJsyq()).indexOf("M/盘")!=-1 || (bean.getJsyq()).indexOf("m/盘")!=-1) )
					 { 
                         bean.setPh("KC");						
					 }
					 else
					 {
                         bean.setPh(phprefix+bean.getJhbh()+("JH".equals(phprefix)?bean.getYwy():"")+"-"+StringUtil.fillZero(bean.getRow()+"", 3));
					 }
				 }

				 xsddService.updateBean(bean);
				 row++;
		    } 
		}
	    sendSuccessMessage(response, "保存成功~");
		return ;
	}

	@RequestMapping("/saveJhq")  //生产管理部填写交货日期保存
	public void saveJhq(XsddBean beanback,HttpServletRequest request,HttpServletResponse response) throws Exception{
  	    request.setCharacterEncoding("UTF-8");  
        //获取编辑数据 这里获取到的是json字符串  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
	
//System.out.println("高绪山：XsddAction--add:insert"+inserted);
//System.out.println("高绪山：XsddAction--add:delete"+deleted);
//System.out.println("高绪山：XsddAction--add:update"+updated);


         if(updated != null){  
            //把json字符串转换成对象  
		    List<XsddBean> listUpdated = new ArrayList<XsddBean>();	
            listUpdated = JSON.parseArray(updated, XsddBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 XsddBean bean = (XsddBean)ele;	
 				 xsddService.updateBean(bean);
		     }    
			 sendSuccessMessage(response, "保存成功~");
          }
  		  return ;
	}

	@RequestMapping("/saveEcjhq")  //生产管理部二次交货期保存
	public void saveEcjhq(XsddBean beanback,HttpServletRequest request,HttpServletResponse response) throws Exception{
  	    request.setCharacterEncoding("UTF-8");  
        //获取编辑数据 这里获取到的是json字符串  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
	
//System.out.println("高绪山：XsddAction--Ecjhq:insert"+inserted);
//System.out.println("高绪山：XsddAction--Ecjhq:delete"+deleted);
//System.out.println("高绪山：XsddAction--Ecjhq:update"+updated);


         if(updated != null){  
            //把json字符串转换成对象  
		    List<XsddBean> listUpdated = new ArrayList<XsddBean>();	
            listUpdated = JSON.parseArray(updated, XsddBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 XsddBean bean = (XsddBean)ele;	
 				 xsddService.updateBean(bean);
		     }
			 sendSuccessMessage(response, "保存成功~");
          }
    	  return ;
	}
	
    //保存质量部完工汇报
	@RequestMapping("/saveWghb")
	public void  saveWghb(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	    request.setCharacterEncoding("UTF-8");  
        //获取编辑数据 这里获取到的是json字符串  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 String ddid  = request.getParameter("ddid");

//System.out.println("高绪山：XsddAction--savewghb:ddid="+ddid);
//System.out.println("高绪山：XsddAction--savewghb:insert"+inserted);
//System.out.println("高绪山：XsddAction--savewghb:delete"+deleted);
//System.out.println("高绪山：XsddAction--savewghb:update"+updated);

		 String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String sdate;
		 Date ddate=null;
         sdate=sdf.format(new Date());	
		 try {
    	     ddate = sdf.parse(sdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }
		 
         Integer row=0;
         if(deleted != null){  
             //把json字符串转换成对象  

             List<XsddWghbBean> listDeleted = new ArrayList<XsddWghbBean>();	
             listDeleted = JSON.parseArray(deleted, XsddWghbBean.class);  
             //TODO 下面就可以根据转换后的对象进行相应的操作了 
			 Integer [] ids=new Integer[listDeleted.size()];

             row=0;
		     for (Object ele : listDeleted )
		     {
				 XsddWghbBean bean = (XsddWghbBean)ele;	  

                  Integer id1=bean.getId();
                  ids[row]=id1;
				  row++;
			 } 
			 xsddWghbService.delete(ids);
         }  
 
         if(inserted != null){  
             //把json字符串转换成对象  
             List<XsddWghbBean> listInserted = new ArrayList<XsddWghbBean>();	
	
			 listInserted = JSON.parseArray(inserted, XsddWghbBean.class);  
	
			 for (Object ele : listInserted )
		     {
				 XsddWghbBean bean = (XsddWghbBean)ele;	
				 bean.setRk(0);
			     bean.setLrBy(username);
                 bean.setLrTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
				 bean.setDdid(Integer.valueOf(ddid));
				 xsddWghbService.add(bean);
		     }
         }  

         if(updated != null){  
            //把json字符串转换成对象  
		    List<XsddWghbBean> listUpdated = new ArrayList<XsddWghbBean>();	
            listUpdated = JSON.parseArray(updated, XsddWghbBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 XsddWghbBean bean = (XsddWghbBean)ele;	
                 bean.setLrBy(username);	
                 bean.setLrTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
 				 xsddWghbService.update(bean);
		     }
		 }
        java.sql.Date wgrq =null ;
		String wgzs="";
        List<XsddWghbBean> updateDataList =xsddWghbService.queryListById(Integer.valueOf(ddid));
		if (updateDataList!=null && updateDataList.size()>0 )
		{
			wgrq=updateDataList.get(0).getWgrq();
			wgzs=updateDataList.get(0).getCzg();

		}

		XsddBean xsddbean=xsddService.queryById(Integer.valueOf(ddid));

		if (xsddbean!=null )
		{
		
		    xsddbean.setMaxWgrq(wgrq);
		    xsddbean.setWgzs(wgzs);
            xsddService.updateBean(xsddbean);
		}
	    sendSuccessMessage(response, "保存成功~");
		return ;
	}

   //保存电线批量完工汇报
	@RequestMapping("/saveWghbpl")
	public void  saveWghbpl(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	    request.setCharacterEncoding("UTF-8");  
        //获取编辑数据 这里获取到的是json字符串  
         //String deleted = request.getParameter("deleted");  
         //String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 //String ddid  = request.getParameter("ddid");

//System.out.println("高绪山：XsddAction--savewghb:ddid="+ddid);
//System.out.println("高绪山：XsddAction--savewghb:insert"+inserted);
//System.out.println("高绪山：XsddAction--savewghb:delete"+deleted);
//System.out.println("高绪山：XsddAction--savewghb:update"+updated);

		 String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String sdate;
		 Date ddate=null;
         sdate=sdf.format(new Date());	
		 try {
    	     ddate = sdf.parse(sdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }
		 
         Integer row=0;
    

         if(updated != null){  
        //把json字符串转换成对象  
		    List<XsddBean> listUpdated = new ArrayList<XsddBean>();	
            listUpdated = JSON.parseArray(updated, XsddBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 XsddBean ddbean = (XsddBean)ele;	

				 Integer ddid=ddbean.getId();
                 //List<JtjhWghbBean> wghbList = jtjhWghbService.queryListByJhId(jhid);
//System.out.println("高绪山：XsddAction--savewghb:update"+jhbean.getWgrq()+jhbean.getWgsl());
			     if (ddbean.getWgrq()!=null && ddbean.getWgsl()!=null)
			     {
					 XsddWghbBean wgbean=new XsddWghbBean();
					 wgbean.setDdid(ddid);
					 wgbean.setWgrq(ddbean.getWgrq());
					 wgbean.setWgsl(ddbean.getWgsl());
					 wgbean.setWgslss(ddbean.getWgslss());
					 wgbean.setCzg(ddbean.getCzg());
					 wgbean.setXpgg(ddbean.getXpgg());
					 wgbean.setCmsl(ddbean.getCmsl());

                     wgbean.setLrBy(username);	
                     wgbean.setLrTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
 				     xsddWghbService.add(wgbean);
                     //替换xsdd中
					 java.sql.Date wgrq =null ;
		             String wgzs="";
                     List<XsddWghbBean> updateDataList =xsddWghbService.queryListById(ddid);
		             if (updateDataList!=null && updateDataList.size()>0 )
		             {
			             wgrq=updateDataList.get(0).getWgrq();
			             wgzs=updateDataList.get(0).getCzg();

		             }

		             XsddBean xsddbean=xsddService.queryById(Integer.valueOf(ddid));

		             if (xsddbean!=null )
		             {
		
		                 xsddbean.setMaxWgrq(wgrq);
		                 xsddbean.setWgzs(wgzs);
                         xsddService.updateBean(xsddbean);
		             }
				 }
		     }
             sendSuccessMessage(response, "保存成功~");
		     return ;
		 }

	}

    //保存统计入库汇报
	@RequestMapping("/saveRkhb")
	public void  saveRkhb(HttpServletRequest request,HttpServletResponse response)  throws Exception{
	    request.setCharacterEncoding("UTF-8");  
        //获取编辑数据 这里获取到的是json字符串  
         String deleted = request.getParameter("deleted");  
         String inserted = request.getParameter("inserted");  
         String updated = request.getParameter("updated");  
		 String ddid  = request.getParameter("ddid");

//System.out.println("高绪山：XsddAction--saverkhb:ddid="+ddid);
//System.out.println("高绪山：XsddAction--saverkhb:insert"+inserted);
//System.out.println("高绪山：XsddAction--saverkhb:delete"+deleted);
//System.out.println("高绪山：XsddAction--saverkhb:update"+updated);

		 String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String sdate;
		 Date ddate=null;
         sdate=sdf.format(new Date());	
		 try {
    	     ddate = sdf.parse(sdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }

         if(updated != null){  
            //把json字符串转换成对象  
		    List<XsddWghbBean> listUpdated = new ArrayList<XsddWghbBean>();	
            listUpdated = JSON.parseArray(updated, XsddWghbBean.class);  
 	        for (Object ele : listUpdated )
		    {
				 XsddWghbBean bean = (XsddWghbBean)ele;	
                 bean.setRklrBy(username);	
                 bean.setRklrTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
 				 xsddWghbService.update(bean);
		     }
          }
		  Double ddsl=0.0;
		  XsddBean xsddbean=xsddService.queryById(Integer.valueOf(ddid));

		  if (xsddbean!=null )
		  {
		      ddsl=xsddbean.getSl()==null?0:xsddbean.getSl();
		  }

		  List<XsddWghbBean> wghbList = xsddWghbService.queryListById(Integer.valueOf(ddid));
          Integer qbrk=2;

		  if (wghbList!=null && wghbList.size()>0 )
		  {
				 Double sumrksl=0.0;
		      	 for (int i=0;i<wghbList.size();i++){
			         XsddWghbBean wghb = wghbList.get(i);
					 if (wghb.getRk()!=null && wghb.getRk()==1)
					 {
					     sumrksl=sumrksl+(wghb.getWgsl()==null?0.0:wghb.getWgsl());
					 }
		         }
				 if (sumrksl>=ddsl )
				 {
					 qbrk=1;
				 }
				 else{
				     if (ddsl!=0)
				     {
					     if ((ddsl-sumrksl)/ddsl<=0.1)  //小于10%视为入库
					     {
						     qbrk=1;
					     }
				     }
				 }					 

		  }
	
		  xsddbean.setQbRk(qbrk);
		  xsddService.updateBean(xsddbean);
	
	      sendSuccessMessage(response, "保存成功~");
		  return ;
	}
     //导出Excel,xsdd_bg
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
		     List<XsddBgBean> listSelected = new ArrayList<XsddBgBean>();	
             listSelected = JSON.parseArray(selected, XsddBgBean.class);  
			 for (XsddBgBean st: listSelected  )
			 {
			
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
		    }
            String [] sortNameArr = {"bh","row"};
			ListUtils.sort(listSelected, true, "bh","row"); 

            String[] titles = new String[]{"变更日期", "变更单编号", "行号", "销售订单编号", "行号", "变更项目", "原内容", "变更后内容", "变更人"
			 , "审核人", "确认人"};
            String[] fieldNames = new String[]{"createTime", "bh", "row", "jhbh", "jhbhrow", "field", "oldContent_dc", "newContent_dc", "createBy",
                "checkBy", "acceptBy"};
            try {
				String excelname="销售订单变更单导出_("+sdate+")";
				//String  serverFilePath=session.getServletContext().getRealPath("/uploadfiles/")+excelname+".xls";
				//serverFilePath=request.getRealPath("/")+ "\\uploadfiles\\" +excelname+".xls"; //getServletContext().getRealPath("/")
//System.out.println("高绪山old："+request.getRealPath("/")+ "\\uploadfiles\\" +excelname+".xls");
				String path = session.getServletContext().getRealPath("/uploadfiles/");
//System.out.println("高绪山new："+path+File.separator+excelname+".xls");
                File file1 = new File(path+File.separator+excelname+".xls");
                ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
                //eh1.writeExcel(JtjhBean.class, listSelected);
                eh1.writeExcel(XsddBgBean.class, listSelected, fieldNames, titles);
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
     //导出Excel,xsdd--Ecjhq
	@RequestMapping("/exportExcelEcjhq")
	public void  exportExcelEcjhq(HttpServletRequest request,HttpServletResponse response,HttpSession session)  
		throws Exception{

	   	JSONObject context = new JSONObject();
        //获取编辑数据 这里获取到的是json字符串  

        String selected = request.getParameter("selected"); 

         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
	     String sdate=sdf.format(new Date());	
		 if ( selected!=null )
		 {
		     List<XsddBean> listSelected = new ArrayList<XsddBean>();	
             listSelected = JSON.parseArray(selected, XsddBean.class);  
			 List<XsddBean> result = new ArrayList<XsddBean>();
			 for ( XsddBean ddbean :listSelected) 
			 {
				 //订单状态汉字说明
				 //XsddBean ddbean =listSelected.get(i);
				 if (ddbean.getState()!=null)
				 {
					 if (ddbean.getState()==1)
				     {
					     ddbean.setCstate("");
				     }
				     else if (ddbean.getState()==2)
				     {
					      ddbean.setCstate("暂停");
				     }
				     else if (ddbean.getState()==3)
				     {
					      ddbean.setCstate("作废");
				     }
				     else {
					      ddbean.setCstate("");
				     }
				 }
				 else{
					 ddbean.setCstate("");
				 }
	 		    //借用uodateBy 表示超期天数
                if (ddbean.getState()!=null && ddbean.getState()==2)
			    {
				     ddbean.setCcqts("暂停");
			    }
			    else if (ddbean.getState()!=null && ddbean.getState()==3)
			    {
			         ddbean.setCcqts("作废");
			    }
			    else{
				     if (ddbean.getCqts()==-9999)
				     {
					     ddbean.setCcqts("未到期");
				     }
				     else if (ddbean.getCqts()<0)
				     {
					      ddbean.setCcqts("提前"+ddbean.getCqts()*(-1)+"天");
				     }
				     else if (ddbean.getCqts()>0)
				     {
					     ddbean.setCcqts("超期"+ddbean.getCqts()+"天");
				     }
				     else if (ddbean.getCqts()==0)
				     {
					     ddbean.setCcqts("按期完成");
				     }
				     else{
					      ddbean.setCcqts("");
				     }
			    }
				result.add(ddbean);
			 }
             String [] sortNameArr = {"jhbh","row"};
			 ListUtils.sort(result, true, "jhbh","row"); 

             String[] titles = new String[]{"超期状态","超期天数", "下达日期", "计划编号",  "行号", "型号", "规格", "电压等级", "工艺"
			 , "单位", "数量", "技术要求", "状态", "交货日期", "二次交货期", "延误原因", "延误机台"};
             String[] fieldNames = new String[]{"ccqts","cqts", "xdrq", "jhbh", "row", "xh", "gg", "dy", "gy", "dw",
                "sl", "jsyq", "cstate", "jhrq", "ecjhq", "ywyy", "ywjt"};
             try {
				String excelname="二次交货期确认导出_("+sdate+")";

				String path = session.getServletContext().getRealPath("/uploadfiles/");

                File file1 = new File(path+File.separator+excelname+".xls");
                ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
                eh1.writeExcel(XsddBean.class, result, fieldNames, titles);

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

    //导出Excel  xsdd 
	@RequestMapping("/exportExcel")
	public void  exportExcel(HttpServletRequest request,HttpServletResponse response,HttpSession session)  
		throws Exception{
	   	JSONObject context = new JSONObject();
        //获取编辑数据 这里获取到的是json字符串  

         String selected = request.getParameter("selected"); 
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
	     String sdate=sdf.format(new Date());	
		 if ( selected!=null )
		 {
		     List<XsddBean> listSelected = new ArrayList<XsddBean>();	
             listSelected = JSON.parseArray(selected, XsddBean.class);  
			 List<XsddBean> result = new ArrayList<XsddBean>();
			 for ( XsddBean ddbean :listSelected) 
			 {
				 //订单状态汉字说明
				 //XsddBean ddbean =listSelected.get(i);
				 if (ddbean.getState()!=null)
				 {
					 if (ddbean.getState()==1)
				     {
					     ddbean.setCstate("");
				     }
				     else if (ddbean.getState()==2)
				     {
					      ddbean.setCstate("暂停");
				     }
				     else if (ddbean.getState()==3)
				     {
					      ddbean.setCstate("作废");
				     }
				     else {
					      ddbean.setCstate("");
				     }
				 }
				 else{
					 ddbean.setCstate("");
				 }
				 //全部完工
				 if (ddbean.getQbRk()!=null)
				 {
				
				     if (ddbean.getQbRk()==1)
				     {
					     ddbean.setCqbrk("全部完工");
				     }
				     else{
					      ddbean.setCqbrk("");
				     }
				 }
                else {
					ddbean.setCqbrk("");
				}
				 //借用uodateBy 表示超期天数
                if (ddbean.getState()!=null && ddbean.getState()==2)
			    {
				     ddbean.setCcqts("暂停");
			    }
			    else if (ddbean.getState()!=null && ddbean.getState()==3)
			    {
			         ddbean.setCcqts("作废");
			    }
			    else{
				     if (ddbean.getCqts()==-9999)
				     {
					     ddbean.setCcqts("未到期");
				     }
				     else if (ddbean.getCqts()<0)
				     {
					      ddbean.setCcqts("提前"+ddbean.getCqts()*(-1)+"天");
				     }
				     else if (ddbean.getCqts()>0)
				     {
					     ddbean.setCcqts("超期"+ddbean.getCqts()+"天");
				     }
				     else if (ddbean.getCqts()==0)
				     {
					     ddbean.setCcqts("按期完成");
				     }
				     else{
					      ddbean.setCcqts("");
				     }
			    }
				result.add(ddbean);
			 }

             String [] sortNameArr = {"jhbh","row"};
			 ListUtils.sort(result, true, "jhbh","row"); 
             /**
			 // 按userId升序、username降序、birthDate升序排序  
             String [] sortNameArr = {"userId","username","birthDate"};  
             boolean [] isAscArr = {true,false,true};  
             ListUtils.sort(list,sortNameArr,isAscArr);  
             System.out.println("\n--------按userId升序、username降序、birthDate升序排序（如果userId相同，则按照username降序,如果username相同，则按照birthDate升序）------------------");  
             testObj.printfUserInfoList(list);  
          
             // 按userId、username、birthDate都升序排序  
             ListUtils.sort(list, true, "userId", "username","birthDate");  
             System.out.println("\n--------按userId、username、birthDate排序（如果userId相同，则按照username升序,如果username相同，则按照birthDate升序）------------------");  
             testObj.printfUserInfoList(list);  
             */
             String[] titles = new String[]{"状态","下达日期","业务员", "计划编号", "行号", "产品型号","产品规格","电压等级","工艺"
			 ,"单位","计划数量","客户要求交货期","计划交货期","技术要求","批号","订单员","延误原因","延误机台","二次交货期","完工日期","完工主手"
			 ,"合计完工数量","完工段数明细","实数段数明细","使用木轮明细","合计长米数量","合计入库数量" ,"入库段数明细", "全部完工", "超期状态", "超期天数"};
             String[] fieldNames = new String[]{"cstate","xdrq", "ywy", "jhbh", "row", "xh", "gg", "dy", "gy", "dw",
                "sl", "jhrq_kh", "jhrq", "jsyq", "ph","createBy", "ywyy", "ywjt", "ecjhq", "maxWgrq", "wgzs"
			 ,"sumWgsl", "sumWgslds", "sumWgslss","sumXpgg","sumCmsl", "sumRksl", "sumRkslds", "cqbrk","ccqts","cqts"};

             try {
				String excelname="销售订单明细表_"+sdate+")";

				String path = session.getServletContext().getRealPath("/uploadfiles/");

                File file1 = new File(path+File.separator+excelname+".xls");
                ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
                //eh1.writeExcel(JtjhBean.class, listSelected);
                eh1.writeExcel(XsddBean.class, result, fieldNames, titles);
	            context.put("fileName", excelname+".xls");
		        context.put(SUCCESS, true);	
				HtmlUtil.writerJson(response, context);
  
             } catch (Exception e) {
				    sendFailureMessage(response,"导出失败！");
                    e.printStackTrace();

             } 
		     return;
         }
		 sendFailureMessage(response, "导出失败，请重试！");
	}
	
/**
	    //request.setCharacterEncoding("UTF-8");  
	   	JSONObject context = new JSONObject();
        //获取编辑数据 这里获取到的是json字符串  

         String selected = request.getParameter("selected"); 
		 String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
	     String sdate=sdf.format(new Date());	
		 if ( selected!=null )
		 {
         
		     List<XsddBean> listSelected = new ArrayList<XsddBean>();	
		     List<Object> result=new ArrayList();		     
             listSelected = JSON.parseArray(selected, XsddBean.class);  
  
             String [] sortNameArr = {"jhbh","row"};
			 ListUtils.sort(listSelected, true, "jhbh","row"); 

 	         for (Object ele : listSelected )
			 //for (int i=0; i<listSelected.size() ;i++ )
			 {
	            
				 XsddBean bean  =  (XsddBean)ele;
				 result.add(bean);
            }

			 ExportExcel excel=new ExportExcel();  


		     String excelname="销售订单明细表_("+sdate+")";
             String rfp=excel.exportExcel(session,excelname+".xls",excelname,Title, result,41);   		 
	         if (rfp.equals("系统提示：Excel文件导出成功！"))
	         { 
                //request.setAttribute("fileName",excelname+".xls");
				context.put("fileName", excelname+".xls");
		        context.put(SUCCESS, true);	
				HtmlUtil.writerJson(response, context);
    			//sendSuccessMessage(response, rfp+",文件名为:"+excelname+".xls");
	         }
			 else{
			      sendFailureMessage(response,rfp);
			 }
			
		     return ;
         }
		 sendFailureMessage(response, "导出失败，请重试！");
	}
	*/

    @RequestMapping("/proUpload")
    public void proUpload (HttpServletRequest request ,
		HttpServletResponse response,HttpSession session) throws IOException,Exception{
 
		Iterator iter = null;
		String title = null;
		response.setContentType("text/html;charset=gbk");
		// 获取输出流
		PrintWriter out = response.getWriter();
		try
		{
			// 使用Uploader处理上传
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = upload.parseRequest(request);
			iter = items.iterator();
			// 遍历每个表单控件对应的内容
			while (iter.hasNext())
			{
				FileItem item = (FileItem)iter.next();
				// 如果该项是普通表单域
				if (item.isFormField())
				{
					String name = item.getFieldName();
					if (name.equals("title"))
					{
						title = item.getString("gbk");
					}
				}
				// 如果是需要上传的文件
				else
				{
					String user = (String)request.getSession()
						.getAttribute("curUser");
					String serverFileName = null;
					// 返回文件名
					String fileName = item.getName();
					if (fileName==null || "".equals(fileName))
					{
	                       out.write("<script type='text/javascript'>"
							+ "parent.callback('没有选择导入文件，请重新选择！');"
							+ "</script>");
							return ;
					}
					// 取得文件后缀
					String suffix = fileName.substring(
						fileName.lastIndexOf("."));
					// 返回文件类型
					String contentType = item.getContentType();
					// 只允许上传xls(x)
					//if (contentType.equals("image/pjpeg")
					//	|| contentType.equals("image/gif")
					//	|| contentType.equals("image/jpeg")
					//	|| contentType.equals("image/png"))
					//{
					    InputStream input = item.getInputStream();
						serverFileName =  UUID.randomUUID().toString();
				        String path = session.getServletContext().getRealPath("/uploadfiles/");
  						String serverFilePath=path+File.separator+ serverFileName + suffix;
						//serverFilePath=request.getRealPath("/")+ "\\uploadfiles\\" + serverFileName + suffix; //getServletContext().getRealPath("/")
						FileOutputStream output = new FileOutputStream(serverFilePath);
						byte[] buffer = new byte[1024];
						int len = 0;
						while((len = input.read(buffer)) > 0 )
						{
							output.write(buffer , 0 , len);
						}
						input.close();
						output.close();
						String resultCheck= importExcelCheck(serverFilePath);
						if ("检查正确!".equals(resultCheck))
						{
						
	         			    importExcel(serverFilePath,request);

						    //as.addPhoto(user , title , serverFileName + suffix);
							out.write("<script type='text/javascript'>"
							+ "parent.callback('文件导入成功！');"
							+ "</script>");
						}
						else{
							out.write("<script type='text/javascript'>"
							+ "parent.callback('"+resultCheck+"');"
							+ "</script>");
						}
					
					//}
					//else
					//{
					//	out.write("<script type='text/javascript'>"
					//		+ "parent.callback('本系统只允许上传"
					//		+ "JPG、GIF、PNG图片文件，请重试！')</script>");
					//}
				}
			}
		}
		catch (FileUploadException fue)
		{
			fue.printStackTrace();
			out.write("<script type='text/javascript'>"
				+ "parent.callback('处理上传文件出现错误，请重试！');"
				+ "</script>");
		 	 //sendFailureMessage(response, "上传文件失败，请重试！");
	         return ;

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
 }  
 
    //导入的Excel文件检查
    public String  importExcelCheck(String FileName) throws Exception{
         String result="检查正确!";

	     //创建输入流  
         InputStream stream = new FileInputStream(FileName);  
         Workbook  rwb = Workbook.getWorkbook(stream);  
		 try{
             //获取文件的指定工作表 默认的第一个  
             Sheet sheet = rwb.getSheet(0);    
             //行数(表头的目录不需要，从1开始)  
             for(int i=2; i<sheet.getRows(); i++){  
               
				Cell cell = null;     
				cell=sheet.getCell(0,i);
				String c1=cell.getContents();
                cell=sheet.getCell(1,i);
                String c2=cell.getContents();
                if ("".equals(c1.trim()) && "".equals(c1.trim())) //第1、2列为空，结束
                {
					//result="导入文件为空，请重新选择文件导入!";
					break;

                }

				//列数  
		        for(int j=0; j<sheet.getColumns(); j++){  
                    //获取第i行，第j列的值  
                    cell = sheet.getCell(j,i);   
                    String str = cell.getContents();  

			        switch(j) 
                    { 
                       case 0: 
						   if (str==null || "".equals(str))
						   {
						       result="产品型号不能为空，请重新选择文件导入!";

						   }
				  	       //bean.setXh(str);
                           break; 
                       case 1: 
				  	       //bean.setGg(str);
                           break; 
                       case 2: 
				  	       //bean.setDy(str);
                           break; 
                       case 3: 
				  	       //bean.setDw(str);
                           break; 
                       case 4: 
						   if (str==null || "".equals(str) || !(NumberUtils.isNumber(str)) )
						   {
				  	           result="数量为空或者不是有效的数值，请重新选择文件导入!";

						   }
                           break; 
                       case 5: 
					       if (str==null || "".equals(str) || str.length()< 8 )
					       {
						       result="客户要求交货日期为空或者不是有效的格式(日期格式：YYYY-MM-DD)，请重新选择文件导入!";

					       }
                           break; 
                       case 6: 
					       break;
				       case 7: 
                           break; 
			           case 8: 
						   if (str==null || "".equals(str))
						   {
						       result="业务员不能为空，请重新选择文件导入!";

						   }
                           break; 
			           case 9: 
                           //bean.setPh(str);
                           break; 
			           case 10: 
                           //bean.setGy(str);
                           break; 
				       default: 
                           break; 
                  } 
             }  

         }  
	 }
	 finally{
		 if (rwb!=null){
			 rwb.close();
			 stream.close();
		 }
	 }

	 return result ;

 }  
    //@RequestMapping("/importExcel1")  
    //根据上传Excel文件生成销售订单
    public void  importExcel(String FileName,HttpServletRequest request) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        String str = null; 
		String jhbh=null;
	    String username = SessionUtils.getUser(request).getNickName();
		 if (jhbh==null || "".equals(jhbh))
		 {
             String syear=DateUtil.getNowYear();
			 String smonth=DateUtil.getNowMonth();
			 Integer iyear=Integer.valueOf(syear);
             Integer imonth=Integer.valueOf(smonth);
			 JhbhBean jhbhbean  = jhbhService.queryNoByYearAndMonth(iyear, imonth,1);  //1--销售订单编号
			 //@Param("uuid")String uuid, @Param("portletid")String portletid
  		     if(jhbhbean  == null){
				  JhbhBean newbean=new JhbhBean();
                  newbean.setNian(iyear);
				  newbean.setYue(imonth);
				  newbean.setFlag(1);
				  newbean.setJhno(1);
				  jhbhService.add(newbean);
				  String sjhno=StringUtil.fillZero("1", 4);
				  jhbh=syear+smonth+sjhno;
		    }
			else 
			{
				 Integer newno=jhbhbean.getJhno()+1;
				 jhbhbean.setJhno(newno);
				 jhbhService.update(jhbhbean);
                 String sjhno=StringUtil.fillZero(newno+"", 4);
				 jhbh=syear+smonth+sjhno;
			}
	     }
		 String sdate;
		 Date dxdrq=null;
         sdate=sdf.format(new Date());	
		 try {
    	     dxdrq = sdf.parse(sdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }
		 int row=1;
		 List<XsddBean> list = new ArrayList<XsddBean>();
	     //创建输入流  
         InputStream stream = new FileInputStream(FileName);  
         Workbook  rwb = Workbook.getWorkbook(stream);  
		 try{
             //获取文件的指定工作表 默认的第一个  
             Sheet sheet = rwb.getSheet(0);    
             //行数(表头的目录不需要，从1开始)  
             for(int i=2; i<sheet.getRows(); i++){  
               
				Cell cell = null;     
				cell=sheet.getCell(0,i);
				String c1=cell.getContents();
                cell=sheet.getCell(1,i);
                String c2=cell.getContents();
                if ("".equals(c1.trim()) && "".equals(c1.trim())) //第1、2列为空，结束
                {
					break;
                }

		        XsddBean bean =new XsddBean();
			    bean.setXdrq(new java.sql.Date(dxdrq.getTime()));  //java.util.Date -->java.sql.Date 
			    bean.setJhbh(jhbh);
		        bean.setDeleted(DELETED.NO.key);
		        bean.setRow(row);
 		        bean.setState(1);
			    bean.setXdjt(0);
			    bean.setCreateBy(username);

				//列数  
		        for(int j=0; j<sheet.getColumns(); j++){  
                    //获取第i行，第j列的值  
                    cell = sheet.getCell(j,i);   
                    str = cell.getContents();  

			        switch(j) 
                    { 
                       case 0: 
				  	       bean.setXh(str);
                           break; 
                       case 1: 
				  	       bean.setGg(str);
                           break; 
                       case 2: 
				  	       bean.setDy(str);
                           break; 
                       case 3: 
				  	       bean.setDw(str);
                           break; 
                       case 4: 
						   if (str!=null && !("".equals(str)) && NumberUtils.isNumber(str) )
						   {
				  	           bean.setSl(Double.valueOf(str));
						    }
                           break; 
                       case 5: 
					       if (str!=null && !("".equals(str)) && str.length()>=8 )
					       {
					           //Date ddate = DateUtil.parse(str);
				  	           str=str.replace("/","-");
						       Date ddate=null;
	
	
						       try {
		                           ddate = sdf.parse(str);
                               } catch (ParseException e) {
                                   //e.printStackTrace();
                               }

		 			          bean.setJhrq_kh(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date 
					       }
                           break; 
                       case 6: 
					       break;
				       case 7: 
                           bean.setJsyq(str);
                           break; 
			           case 8: 
                           bean.setYwy(str);
                           break; 
			           case 9: 
                           bean.setPh(str);
                           break; 
			           case 10: 
                           bean.setGy(str);
                           break; 
				       default: 
                           break; 
                  } 
             }  
				 if (bean.getPh()==null || "".equals(bean.getPh())){
					 String phprefix="JH";
					 if ("库存".equals(bean.getYwy())) {
					
						 phprefix="KC";
					 }

					 if ("KC".equals(phprefix) && ((bean.getJsyq()).indexOf("米/盘")!=-1 || 
						 (bean.getJsyq()).indexOf("M/盘")!=-1 || (bean.getJsyq()).indexOf("m/盘")!=-1) )
					 { 
                         bean.setPh("KC");						
					 }
					 else
					 {
                         bean.setPh(phprefix+bean.getJhbh()+("JH".equals(phprefix)?bean.getYwy():"")+"-"+StringUtil.fillZero(bean.getRow()+"", 3));
					 }
				 }
		     xsddService.addBean(bean);
		     row++;
         }  
	 }
	 finally{
		 if (rwb!=null){
			 rwb.close();
			 stream.close();
		 }
	 }

 	 //sendSuccessMessage(response, "导入成功~");
	 //return ;

 }  

	
	@RequestMapping("/saveDdbg")  //订单变更保存
	public void saveDdbg(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 request.setCharacterEncoding("UTF-8");  
         //获取编辑数据  
		 String id  = request.getParameter("id");
		 String jhbh  = request.getParameter("jhbh");
		 String row  = request.getParameter("row");
		 String ywy  = request.getParameter("ywy");
		 String ywynew  = request.getParameter("ywynew");
		 String ph  = request.getParameter("ph");
		 String phnew  = request.getParameter("phnew");

		 String xh  = request.getParameter("xh");
		 String xhnew  = request.getParameter("xhnew");
		 String gg  = request.getParameter("gg");
		 String ggnew  = request.getParameter("ggnew");
		 String dy  = request.getParameter("dy");
		 String dynew  = request.getParameter("dynew");
		 String gy  = request.getParameter("gy");
		 String gynew  = request.getParameter("gynew");
		 String dw  = request.getParameter("dw");
		 String dwnew  = request.getParameter("dwnew");
		 String sl  = request.getParameter("sl");
		 String slnew  = request.getParameter("slnew");
		 String jhrq  = request.getParameter("jhrq");
		 String jhrqnew  = request.getParameter("jhrqnew");
		 String jsyq  = request.getParameter("jsyq");
		 String jsyqnew  = request.getParameter("jsyqnew");
		 String state  = request.getParameter("state");
		 String statenew  = request.getParameter("statenew");

		 XsddBean ddbean  = xsddService.queryById(Integer.valueOf(id));
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
			 JhbhBean jhbhbean  = jhbhService.queryNoByYearAndMonth(iyear, imonth,2);  //2--变更单编号
			 //@Param("uuid")String uuid, @Param("portletid")String portletid
  		     if(jhbhbean  == null){
				  JhbhBean newbean=new JhbhBean();
                  newbean.setNian(iyear);
				  newbean.setYue(imonth);
				  newbean.setFlag(2);
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
         if (!(ywy.equals(ywynew)) &&   StringUtils.isNotBlank(ywynew) )
         {
			 ddbean.setYwy(ywynew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("业务员");
			 bgbean.setOldContent(ywy);
			 bgbean.setNewContent(ywynew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(ph.equals(phnew)) &&   StringUtils.isNotBlank(phnew) )
         {
			 ddbean.setPh(phnew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("批号");
			 bgbean.setOldContent(ph);
			 bgbean.setNewContent(phnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }

         if (!(xh.equals(xhnew)) &&   StringUtils.isNotBlank(xhnew) )
         {
			 ddbean.setXh(xhnew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("产品型号");
			 bgbean.setOldContent(xh);
			 bgbean.setNewContent(xhnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(gg.equals(ggnew)) && StringUtils.isNotBlank(ggnew) )
         {
			 ddbean.setGg(ggnew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("产品规格");
			 bgbean.setOldContent(gg);
			 bgbean.setNewContent(ggnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(dy.equals(dynew)) &&  StringUtils.isNotBlank(dynew) )
         {
			 ddbean.setDy(dynew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("电压");
			 bgbean.setOldContent(dy);
			 bgbean.setNewContent(dynew);
			 xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(gy.equals(gynew)) &&  StringUtils.isNotBlank(gynew) )
         {
			 ddbean.setGy(gynew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("工艺");
			 bgbean.setOldContent(gy);
			 bgbean.setNewContent(gynew);
			 xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(dw.equals(dwnew)) &&  StringUtils.isNotBlank(dwnew) )
         {
			 ddbean.setDw(dwnew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("单位");
			 bgbean.setOldContent(dw);
			 bgbean.setNewContent(dwnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(sl.equals(slnew)) &&  StringUtils.isNotBlank(slnew) )
         {
			 ddbean.setSl(Double.valueOf(slnew));
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("数量");
			 bgbean.setOldContent(sl);
			 bgbean.setNewContent(slnew);
		     xsddBgService.add(bgbean);
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
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setField("交货日期");
			 bgbean.setRow(bgrow);
			 bgbean.setOldContent(jhrq);
			 bgbean.setNewContent(jhrqnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(jsyq.equals(jsyqnew)) &&  StringUtils.isNotBlank(jsyqnew))
         {
			 ddbean.setJsyq(jsyqnew);
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setField("技术要求");
			 bgbean.setRow(bgrow);
			 bgbean.setOldContent(jsyq);
			 bgbean.setNewContent(jsyqnew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
         if (!(state.equals(statenew))  &&  StringUtils.isNotBlank(statenew) )
         {
			 ddbean.setState(Integer.valueOf(statenew));
		     XsddBgBean bgbean=new XsddBgBean();
			 bgbean.setAccept(0);
			 bgbean.setBh(bh);
			 bgbean.setCreateBy(username);
			 bgbean.setDdid(Integer.valueOf(id));
			 bgbean.setJhbh(jhbh);
			 bgbean.setJhbhrow(Integer.valueOf(row));
			 bgbean.setRow(bgrow);
			 bgbean.setField("状态");
			 bgbean.setOldContent(state);
			 bgbean.setNewContent(statenew);
		     xsddBgService.add(bgbean);
			 bgrow++;
			 changeFlag=1;
         }
		 if (changeFlag==1)
		 {
			  xsddService.updateBean(ddbean);
		      sendSuccessMessage(response, "销售订单变更成功~");
		 }
		 else {
	   
		    sendFailureMessage(response, "未进行销售订单变更！");
		 }
	}	

	@RequestMapping("/saveDdbgCheck")  //订单变更审核保存
	public void saveDdbgCheck(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 request.setCharacterEncoding("UTF-8");  
         //获取编辑数据  
		 String bh  = request.getParameter("bh");
	     String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String sdate;
		 Date ddate=null;
         sdate=sdf.format(new Date());	
		 try {
    	     ddate = sdf.parse(sdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }
		 List<XsddBgBean> bgdataList  = xsddBgService.queryListByBh(bh);
	     if(bgdataList.size()  == 0){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		 }
		 for (Object ele:bgdataList )
		 {
             XsddBgBean bgbean = (XsddBgBean)ele;
			 bgbean.setCheckBy(username);
             bgbean.setChecked(1);
             bgbean.setCheckTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
	         xsddBgService.update(bgbean);
		 }

         sendSuccessMessage(response, "变更单【审核】成功~");
	}	



	@RequestMapping("/saveDdbgAccept")  //订单变更确认保存
	public void saveDdbgAccept(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 request.setCharacterEncoding("UTF-8");  
         //获取编辑数据  
		 String bh  = request.getParameter("bh");
	     String username = SessionUtils.getUser(request).getNickName();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String sdate;
		 Date ddate=null;
         sdate=sdf.format(new Date());	
		 try {
    	     ddate = sdf.parse(sdate);
         } catch (ParseException e) {
             e.printStackTrace();
         }

		 List<XsddBgBean> bgdataList  = xsddBgService.queryListByBh(bh);
	     if(bgdataList.size()  == 0){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		 }
		 for (Object ele:bgdataList )
		 {
             XsddBgBean bgbean = (XsddBgBean)ele;
			 bgbean.setAcceptBy(username);
             bgbean.setAccept(1);
             bgbean.setAcceptTime(new java.sql.Timestamp(ddate.getTime()));  //java.util.Date -->java.sql.Date
	         xsddBgService.update(bgbean);
		 }

         sendSuccessMessage(response, "变更单【确认】成功~");
	}	

	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		XsddBean bean  = xsddService.queryById(id);
		String jhbh= bean.getJhbh()	;
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
	    if(bean.getXdjt()  == 1 || bean.getJhrq()!=null){
			sendFailureMessage(response, "该订单已确认交货期或者分解下达机台,不能修改!");
			return;
		}
	    String username = SessionUtils.getUser(request).getNickName();
		String lrr=bean.getCreateBy();
		if (username.equals(lrr))
		{
	
//System.out.println("高绪山-XsddAction--dataListById-jhbh"+jhbh);	
		    List<XsddBean> dataList = xsddService.queryListByJhbh(jhbh);

		    List<XsddBean> result = new ArrayList<XsddBean>();	

		    for (Object ele : dataList)
		    {
  			    XsddBean elebean = (XsddBean)ele;
			    result.add(elebean);
		    }
		    JSONArray jsonArr= new JSONArray(result);
		    String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山-XsddAction--dataListById-jsonStr"+jsonArr.toString());
		//context.put("total",result.size());
		//context.put("rows", jsonArr.toString());
		    context.put("json",jsonStr);
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		    String sdate=df.format(new Date());

		    Map<String,Object>  data = new HashMap<String,Object> ();
		    if (result==null|| result.size()<1 ){	
	            data.put("jhbh", "");
		        data.put("xdrq", sdate);
			    data.put("ywy","");
			    data.put("jhrq_kh","");
		    }
		    else{
	            data.put("jhbh", result.get(0).getJhbh());
		        data.put("xdrq", result.get(0).getXdrq()!=null?(df.format(result.get(0).getXdrq())):"");
			    data.put("ywy",result.get(0).getYwy());	
			    data.put("jhrq_kh",result.get(0).getJhrq_kh()!=null?(df.format(result.get(0).getJhrq_kh())):"");
		    }
            context.put("data", data);
		    context.put(SUCCESS, true);	
		
//System.out.println("高绪山-XsddAction--dataListById-context"+context.toString());
		    HtmlUtil.writerJson(response, context);
//		//List<XsddsBean> xsdds = xsddsService.queryByBtid(id);
//		//bean.setXsddmxs(xsdds);
//		context.put(SUCCESS, true);
//		context.put("data", bean);
//System.out.println("高绪山-xsdd-GetId"+context.toString());
//		HtmlUtil.writerJson(response, context);
		}
		else 
		{
		    sendFailureMessage(response, "该订单为("+bean.getCreateBy()+")创建，不能修改!");
		    return;
		}
	}
    //订单变更
	@RequestMapping("/getDdbg")
	public void getDDbg(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		 XsddBean bean  = xsddService.queryById(id);
	     if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
	    //if(bean.getXdjt()  == 0 ){
		//	sendFailureMessage(response, "该订单尚未分解下达机台,不需要变更!");
		//	return;
		//}	
	    if( bean.getJhrq()==null){
			sendFailureMessage(response, "该订单尚未确定交货期,不需要变更!");
			return;
		}		 		
		List<XsddBean> dataList = xsddService.queryListById(id);

		List<XsddBean> result = new ArrayList<XsddBean>();	

	    for (Object ele : dataList)
	    {
		    XsddBean elebean = (XsddBean)ele;
		    result.add(elebean);
	    }
		JSONArray jsonArr= new JSONArray(result);

	    String username = SessionUtils.getUser(request).getNickName();
		String lrr=bean.getCreateBy();
		if (username.equals(lrr))
		{
           	//Map<String,Object>  datanew = new HashMap<String,Object> ();
			JSONObject datanew= new JSONObject();
			datanew.put("ywynew","");
			datanew.put("phnew","");
		    datanew.put("xhnew","");
		    datanew.put("ggnew","");
		    datanew.put("dynew","");
            datanew.put("gynew","");
		    datanew.put("dwnew","");
		    datanew.put("slnew","");	
            datanew.put("jhrqnew","");
		    datanew.put("jsyqnew","");
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
		}
		else 
		{
		    sendFailureMessage(response, "该订单为("+bean.getCreateBy()+")创建，不能进行变更!");
		    return;
		}
	}

   //订单变更审核
	@RequestMapping("/getDdbgCheck")
	public void getDdbgCheck(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		JSONObject datanew= new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		 XsddBgBean bean  = xsddBgService.queryById(id);
	     if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}	
        int bgddid=bean.getDdid();	
		String bgbh=bean.getBh();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		List<XsddBean> dddataList = xsddService.queryListById(bgddid);

		List<XsddBean> result = new ArrayList<XsddBean>();	

	    for (Object ele : dddataList)
	    {
		    XsddBean elebean = (XsddBean)ele;
		    result.add(elebean);
	    }

	    //该编号的所有变更内容
		List<XsddBgBean> bgdataList = xsddBgService.queryListByBh(bgbh);
		    datanew.put("ywynew","");
            datanew.put("phnew","");
		  	datanew.put("xhnew","");
			datanew.put("ggnew","");
			datanew.put("dynew","");
			datanew.put("gynew","");
			datanew.put("dwnew","");
			datanew.put("slnew","");
			datanew.put("jhrqnew","");
			datanew.put("jsyqnew","");
			datanew.put("statenew","");
        for (Object ele : bgdataList)
	    {

			XsddBgBean bgbean = (XsddBgBean)ele;
			if ("业务员".equals(bgbean.getField()))
			{
				dddataList.get(0).setYwy(bgbean.getOldContent());
				String ywynew=bgbean.getNewContent();
	            datanew.put("ywynew",ywynew);
				continue;
		    }
			if ("批号".equals(bgbean.getField()))
			{
				dddataList.get(0).setPh(bgbean.getOldContent());
				String phnew=bgbean.getNewContent();
	            datanew.put("phnew",phnew);
				continue;
		    }


			if ("产品型号".equals(bgbean.getField()))
			{
				dddataList.get(0).setXh(bgbean.getOldContent());
				String xhnew=bgbean.getNewContent();
	            datanew.put("xhnew",xhnew);
				continue;
		    }

			if ("产品规格".equals(bgbean.getField()))
			{
				dddataList.get(0).setGg(bgbean.getOldContent());
				String ggnew=bgbean.getNewContent();
				datanew.put("ggnew",ggnew);
				continue;
		    }	
			
			if ("电压".equals(bgbean.getField()))
			{
				dddataList.get(0).setDy(bgbean.getOldContent());
				String dynew=bgbean.getNewContent();
		        datanew.put("dynew",dynew);
				continue;
		    }	

		    if ("工艺".equals(bgbean.getField()))
			{
				dddataList.get(0).setGy(bgbean.getOldContent());
				String gynew=bgbean.getNewContent();
				datanew.put("gynew",gynew);
				continue;
		    }	
		
		    if ("单位".equals(bgbean.getField()))
			{
				dddataList.get(0).setDw(bgbean.getOldContent());
				String dwnew=bgbean.getNewContent();
                datanew.put("dwnew",dwnew);
				continue;
		    }	

		    if ("数量".equals(bgbean.getField()))
			{
				if (bgbean.getOldContent()!=null &&  !"".equals(bgbean.getOldContent()))
				{
				    dddataList.get(0).setSl(Double.valueOf(bgbean.getOldContent()));
				}
				else{
                   dddataList.get(0).setSl(null);
				}
				String slnew=bgbean.getNewContent();
				datanew.put("slnew",slnew);	
				continue;
		    }	

		    if ("交货日期".equals(bgbean.getField()))
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
				dddataList.get(0).setJsyq(bgbean.getOldContent());
				String jsyqnew=bgbean.getNewContent();
				datanew.put("jsyqnew",jsyqnew);
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
		datanew.put("checkBy",bgdataList.get(0).getCheckBy());
		datanew.put("checkTime",bgdataList.get(0).getCheckTime()!=null?(sdf.format(bgdataList.get(0).getCheckTime())):"");

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

   //订单变更确认
	@RequestMapping("/getDdbgAccept")
	public void getDdbgAccept(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		JSONObject datanew= new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		 XsddBgBean bean  = xsddBgService.queryById(id);
	     if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}	
		if (bean.getChecked()==null || bean.getChecked()!=1)
		{
			sendFailureMessage(response, "该变更单尚未进行审核，请审核后再进行确认!");
			return;

		}
        int bgddid=bean.getDdid();	
		String bgbh=bean.getBh();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		List<XsddBean> dddataList = xsddService.queryListById(bgddid);

		List<XsddBean> result = new ArrayList<XsddBean>();	

	    for (Object ele : dddataList)
	    {
		    XsddBean elebean = (XsddBean)ele;
		    result.add(elebean);
	    }

	    //该编号的所有变更内容
		List<XsddBgBean> bgdataList = xsddBgService.queryListByBh(bgbh);
		    datanew.put("ywynew","");
            datanew.put("phnew","");
		  	datanew.put("xhnew","");
			datanew.put("ggnew","");
			datanew.put("dynew","");
			datanew.put("gynew","");
			datanew.put("dwnew","");
			datanew.put("slnew","");
			datanew.put("jhrqnew","");
			datanew.put("jsyqnew","");
			datanew.put("statenew","");
        for (Object ele : bgdataList)
	    {

			XsddBgBean bgbean = (XsddBgBean)ele;
			if ("业务员".equals(bgbean.getField()))
			{
				dddataList.get(0).setYwy(bgbean.getOldContent());
				String ywynew=bgbean.getNewContent();
	            datanew.put("ywynew",ywynew);
				continue;
		    }
			if ("批号".equals(bgbean.getField()))
			{
				dddataList.get(0).setPh(bgbean.getOldContent());
				String phnew=bgbean.getNewContent();
	            datanew.put("phnew",phnew);
				continue;
		    }

			if ("产品型号".equals(bgbean.getField()))
			{
				dddataList.get(0).setXh(bgbean.getOldContent());
				String xhnew=bgbean.getNewContent();
	            datanew.put("xhnew",xhnew);
				continue;
		    }

			if ("产品规格".equals(bgbean.getField()))
			{
				dddataList.get(0).setGg(bgbean.getOldContent());
				String ggnew=bgbean.getNewContent();
				datanew.put("ggnew",ggnew);
				continue;
		    }	
			
			if ("电压".equals(bgbean.getField()))
			{
				dddataList.get(0).setDy(bgbean.getOldContent());
				String dynew=bgbean.getNewContent();
		        datanew.put("dynew",dynew);
				continue;
		    }	

		    if ("工艺".equals(bgbean.getField()))
			{
				dddataList.get(0).setGy(bgbean.getOldContent());
				String gynew=bgbean.getNewContent();
				datanew.put("gynew",gynew);
				continue;
		    }	
		
		    if ("单位".equals(bgbean.getField()))
			{
				dddataList.get(0).setDw(bgbean.getOldContent());
				String dwnew=bgbean.getNewContent();
                datanew.put("dwnew",dwnew);
				continue;
		    }	

		    if ("数量".equals(bgbean.getField()))
			{
				if (bgbean.getOldContent()!=null &&  !"".equals(bgbean.getOldContent()))
				{
				    dddataList.get(0).setSl(Double.valueOf(bgbean.getOldContent()));
				}
				else{
                   dddataList.get(0).setSl(null);
				}				String slnew=bgbean.getNewContent();
				datanew.put("slnew",slnew);	
				continue;
		    }	

		    if ("交货日期".equals(bgbean.getField()))
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
				dddataList.get(0).setJsyq(bgbean.getOldContent());
				String jsyqnew=bgbean.getNewContent();
				datanew.put("jsyqnew",jsyqnew);
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
		datanew.put("checkBy",bgdataList.get(0).getCheckBy());
		datanew.put("checkTime",bgdataList.get(0).getCheckTime()!=null?(sdf.format(bgdataList.get(0).getCheckTime())):"");

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
   //订单变更查询
	@RequestMapping("/getDdbgBrow")
	public void getDdbgBrow(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		JSONObject datanew= new JSONObject();
		//Map<String,Object>  context = new HashMap<String,Object>();
		 XsddBgBean bean  = xsddBgService.queryById(id);
	     if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}	
        int bgddid=bean.getDdid();	
		String bgbh=bean.getBh();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		List<XsddBean> dddataList = xsddService.queryListById(bgddid);

		List<XsddBean> result = new ArrayList<XsddBean>();	

	    for (Object ele : dddataList)
	    {
		    XsddBean elebean = (XsddBean)ele;
		    result.add(elebean);
	    }

	    //该编号的所有变更内容
		List<XsddBgBean> bgdataList = xsddBgService.queryListByBh(bgbh);
		    datanew.put("ywynew","");
            datanew.put("phnew","");
		  	datanew.put("xhnew","");
			datanew.put("ggnew","");
			datanew.put("dynew","");
			datanew.put("gynew","");
			datanew.put("dwnew","");
			datanew.put("slnew","");
			datanew.put("jhrqnew","");
			datanew.put("jsyqnew","");
			datanew.put("statenew","");

        for (Object ele : bgdataList)
	    {

			XsddBgBean bgbean = (XsddBgBean)ele;
			if ("业务员".equals(bgbean.getField()))
			{
				dddataList.get(0).setYwy(bgbean.getOldContent());
				String ywynew=bgbean.getNewContent();
	            datanew.put("ywynew",ywynew);
				continue;
		    }
			if ("批号".equals(bgbean.getField()))
			{
				dddataList.get(0).setPh(bgbean.getOldContent());
				String phnew=bgbean.getNewContent();
	            datanew.put("phnew",phnew);
				continue;
		    }

			if ("产品型号".equals(bgbean.getField()))
			{
				dddataList.get(0).setXh(bgbean.getOldContent());
				String xhnew=bgbean.getNewContent();
	            datanew.put("xhnew",xhnew);
				continue;
		    }

			if ("产品规格".equals(bgbean.getField()))
			{
				dddataList.get(0).setGg(bgbean.getOldContent());
				String ggnew=bgbean.getNewContent();
				datanew.put("ggnew",ggnew);
				continue;
		    }	
			
			if ("电压".equals(bgbean.getField()))
			{
				dddataList.get(0).setDy(bgbean.getOldContent());
				String dynew=bgbean.getNewContent();
		        datanew.put("dynew",dynew);
				continue;
		    }	

		    if ("工艺".equals(bgbean.getField()))
			{
				dddataList.get(0).setGy(bgbean.getOldContent());
				String gynew=bgbean.getNewContent();
				datanew.put("gynew",gynew);
				continue;
		    }	
		
		    if ("单位".equals(bgbean.getField()))
			{
				dddataList.get(0).setDw(bgbean.getOldContent());
				String dwnew=bgbean.getNewContent();
                datanew.put("dwnew",dwnew);
				continue;
		    }	

		    if ("数量".equals(bgbean.getField()))
			{
				if (bgbean.getOldContent()!=null &&  !"".equals(bgbean.getOldContent()))
				{
				    dddataList.get(0).setSl(Double.valueOf(bgbean.getOldContent()));
				}
				else{
                   dddataList.get(0).setSl(null);
				}				String slnew=bgbean.getNewContent();
				datanew.put("slnew",slnew);	
				continue;
		    }	

		    if ("交货日期".equals(bgbean.getField()))
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
				dddataList.get(0).setJsyq(bgbean.getOldContent());
				String jsyqnew=bgbean.getNewContent();
				datanew.put("jsyqnew",jsyqnew);
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
		datanew.put("checkBy",bgdataList.get(0).getCheckBy());
		datanew.put("checkTime",bgdataList.get(0).getCheckTime()!=null?(sdf.format(bgdataList.get(0).getCheckTime())):"");

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
		
   //完工汇报
	@RequestMapping("/getWghb")
	public void getWghb(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		XsddBean bean  = xsddService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		List<XsddWghbBean> dataList = xsddWghbService.queryListById(id);
		List<XsddWghbBean> result = new ArrayList<XsddWghbBean>();	
		for (Object ele : dataList)
		{
			XsddWghbBean elebean = (XsddWghbBean)ele;
			result.add(elebean);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山-XsddAction--getWghb-jsonArr"+jsonStr);
		context.put("json",jsonStr);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

		Map<String,Object>  data = new HashMap<String,Object> ();
	    data.put("ddid", bean.getId());
	    data.put("jhbh", bean.getJhbh());
	    data.put("row", bean.getRow());
		data.put("state",bean.getState());	
		data.put("jhrq",bean.getJhrq()!=null?(df.format(bean.getJhrq())):"");
	    data.put("xh", bean.getXh());
	    data.put("gg", bean.getGg());
		data.put("dy",bean.getDy());	
		data.put("gy",bean.getGy());	
	    data.put("sl", bean.getSl());
		data.put("jsyq",bean.getJsyq());	

        context.put("data", data);
		context.put(SUCCESS, true);	
		
//System.out.println("高绪山-XsddAction---getWghb-context"+context.toString());
		HtmlUtil.writerJson(response, context);

	}

   //入库汇报
	@RequestMapping("/getRkhb")
	public void getRkhb(Integer id,HttpServletResponse response) throws Exception{
		JSONObject context = new JSONObject();
		XsddBean bean  = xsddService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		List<XsddWghbBean> dataList = xsddWghbService.queryListById(id);
		List<XsddWghbBean> result = new ArrayList<XsddWghbBean>();	
		for (Object ele : dataList)
		{
			XsddWghbBean elebean = (XsddWghbBean)ele;
			result.add(elebean);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+result.size()+",\"rows\":"+jsonArr.toString()+"}";
//System.out.println("高绪山-XsddAction--getRkhb-jsonArr"+jsonStr);
		context.put("json",jsonStr);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

		Map<String,Object>  data = new HashMap<String,Object> ();
	    data.put("ddid", bean.getId());
	    data.put("jhbh", bean.getJhbh());
	    data.put("row", bean.getRow());
		data.put("state",bean.getState());	
		data.put("jhrq",bean.getJhrq()!=null?(df.format(bean.getJhrq())):"");
	    data.put("xh", bean.getXh());
	    data.put("gg", bean.getGg());
		data.put("dy",bean.getDy());	
		data.put("gy",bean.getGy());	
	    data.put("sl", bean.getSl());
		data.put("jsyq",bean.getJsyq());	

        context.put("data", data);
		context.put(SUCCESS, true);	
		
//System.out.println("高绪山-XsddAction---getRkhb-context"+context.toString());
		HtmlUtil.writerJson(response, context);
	}

  
   //不重复的产品型号
	@RequestMapping("/getUniXh")
	public void getUniXh(HttpServletResponse response) throws Exception{
	
		List<XsddBean> dataList = xsddService.queryByAll();
	
	    List<String> xh1 = new ArrayList();
		for (Object ele : dataList)
		{
			XsddBean st = (XsddBean)ele;
			xh1.add(st.getXh());
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
	
		List<XsddBean> dataList = xsddService.queryByAll();

		List<String> gg1 = new ArrayList();

		for (Object ele : dataList)
		{
			XsddBean st = (XsddBean)ele;
			gg1.add(st.getGg());
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

   //不重复的产品规格
	@RequestMapping("/getUniDdy")
	public void getUniDdy(HttpServletResponse response) throws Exception{
	
		List<XsddBean> dataList = xsddService.queryByAll();

		List<String> ddy1 = new ArrayList();

		for (Object ele : dataList)
		{
			XsddBean st = (XsddBean)ele;
			ddy1.add(st.getCreateBy());

		}
		

		//删除重复的
	
		List<String> uniddy1=new ArrayList();
		uniddy1=removeDuplicate(ddy1);
		Collections.sort(uniddy1);

		List<JsonBean> uniddy = new ArrayList<JsonBean>();
		int id=1;
		for (int i=0;i<uniddy1.size() ;i++)
		{
			JsonBean jsonbean=new JsonBean();
			jsonbean.setId(id);
			jsonbean.setText(uniddy1.get(i));
            id++;
			uniddy.add(jsonbean);
		}
        JSONArray jsonArr = new JSONArray(uniddy);
		String jsonStr=jsonArr.toString();
        response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonStr);

	}
/**
	 * 修改销售订单编号
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/updateBh")
	public void updateBh(Integer id,String jhbh,String newJhbh,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
	    XsddBean bean  = xsddService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}		
		
		if(StringUtils.isBlank(newJhbh)){
			sendFailureMessage(response, "销售订单编号未输入！");
			return;
		}
        List<XsddBean> dataList = new ArrayList<XsddBean>();	
		dataList = xsddService.queryListByJhbh(jhbh);
		int flagXdjt=0;
        for (Object ele : dataList )
		{
	        if(bean.getXdjt()  == 1 ){//|| bean.getJhrq()!=null
			    flagXdjt=1;
			    break;
		    }
	    } 
	
	    if(flagXdjt  == 1 ){
			sendFailureMessage(response, "该订单已分解下达机台,不能修改订单编号!");//确认交货期或者
			return;
		}
	    String username = SessionUtils.getUser(request).getNickName();
		String lrr=bean.getCreateBy();
		if (username.equals(lrr))
		{
	
		    //不是超级管理员，匹配旧密码
		    //if(!isAdmin && !MethodUtil.ecompareMD5(oldPwd,bean.getPwd())){
		    //	sendFailureMessage(response, "Wrong old password.");
		    //	return;
		    //}
	        
		     dataList = xsddService.queryListByJhbh(jhbh);

             for (Object ele : dataList )
		     {
			     XsddBean ddbean = (XsddBean)ele;	    
                 ddbean.setJhbh(newJhbh);  

			     xsddService.updateBean(ddbean);
	        } 
	
		    sendSuccessMessage(response, "销售订单编号修改成功~");
		}
		else 
		{
		    sendFailureMessage(response, "该订单为("+bean.getCreateBy()+")创建，不能修改订单编号!");
		    return;
		}

	}

	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletRequest request,HttpServletResponse response) throws Exception{

		if(id != null && id.length > 0){
			for (int i=0;i<id.length;i++ )
			{
				  String message;

				  XsddBean bean  = xsddService.queryById(id[i]);
	              String username = SessionUtils.getUser(request).getNickName();
		          String lrr=bean.getCreateBy();	
		          if (!username.equals(lrr))
		          {
		              sendFailureMessage(response, "该销售订单为("+lrr+")创建，不能删除!");
		              continue ;
				  }
				  if(bean.getXdjt()  == 1 || bean.getJhrq()!=null ){
					  message="ID为("+id[i]+")的订单,已确认交货期或者分解下达机台,不能删除！";
                      sendFailureMessage(response, message);
					  continue ;
					  
		           } 
				
				   else{
			           xsddService.delete(id[i]); 
					   message="ID为("+id[i]+")的订单,删除成功！";
   		               sendSuccessMessage(response,  message);	
	
				   }
			}

		}else{
			sendFailureMessage(response, "未选中记录");
			return; 
		}
	}

	/**
	 * 输入销售订单明细
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/xsdds") 
	public ModelAndView  userRole(HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("/business/xsddsManage", context);
	}

	/**
	 * 查询销售订单明细
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getXsdds") 
	public void getXsdds(Integer id,HttpServletResponse response)  throws Exception{
		//Map<String,Object>  context = getRootMap();
		//JSONObject jsonMap = new JSONObject();
		JSONObject context = new JSONObject();	
		XsddBean bean  = xsddService.queryById(id);
		if(bean  == null){
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		JSONObject data =  new JSONObject();	
		data.put("id", bean.getId());
		data.put("xdrq", bean.getXdrq());
		data.put("ywy", bean.getYwy());
		data.put("jhbh", bean.getJhbh());
		data.put("jhrq_kh", bean.getJhrq_kh());
		context.put(SUCCESS, true);
		context.put("data", data);

		List<XsddsBean> dataList = xsddsService.queryByBtid(id);
		List<XsddsBean> result = new ArrayList<XsddsBean>();	
		// 封装VO集合
		for (Object ele : dataList)
		{
			// 每个集合元素都是StockBean对象
			XsddsBean st = (XsddsBean)ele;
			result.add(st);
		}
		//设置页面数据
		context.put("total",result.size());
		context.put("rows", result);
//System.out.println("高绪山：XsddAction--getXsdds"+context.toString());	
       HtmlUtil.writerJson(response, context);

	
	}
		

	@RequestMapping("/getXsddTree")
	public void getXsddTree(Integer id,HttpServletResponse response) throws Exception{
		List<TreeNode> xsddTree = treeXsdd();
		List<TreeNode> result = new ArrayList<TreeNode>();
		// 封装VO集合
		for (Object ele : xsddTree)
		{
			// 每个集合元素都是TreeNoden对象
			TreeNode st = (TreeNode)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
//System.out.println("高绪山：XsddAction--xsdd-tree"+jsonArr.toString());
		HtmlUtil.writerJson(response, jsonArr);

		//HtmlUtil.writerJson(response, menuTree);
	}
	
	/**
	 * 构建树形菜单
	 * @return
	 */
	public List<TreeNode> treeXsdd(){
		List<XsddBean> rootItems = xsddService.queryByAll();//根节点，销售订单表头
		List<XsddsBean> childItems = xsddsService.queryByAll();//子节点。销售订单表体
		XsddTree util = new XsddTree(rootItems,childItems);
		return util.getTreeNode();
	}
	
	/**
	 * 获取请求的菜单按钮数据
	 * @param request
	 * @return
	 */
	public List<XsddsBean> getReqxsdds(HttpServletRequest request){
		List<XsddsBean> xsddsList= new ArrayList<XsddsBean>();
		String[] xhs  = request.getParameterValues("xh");
		String[] ggs  = request.getParameterValues("gg");
		String[] dys  = request.getParameterValues("dy");
		String[] gys  = request.getParameterValues("gy");
		String[] dws  = request.getParameterValues("dw");
		String[] phs  = request.getParameterValues("ph");
		String[] sls  = request.getParameterValues("sl");
		String[] jhrq_khs  = request.getParameterValues("jhrq_kh");
		String[] jsyqs  = request.getParameterValues("jsyq");
		//String jhbh  = request.getParameterValues("xh");
        int row=1 ;
		for (int i = 0; i < xhs.length; i++) {
			if(StringUtils.isNotBlank(xhs[i]) && StringUtils.isNotBlank(ggs[i])){
				//if(StringUtils.isNotBlank(btnId[i]) && NumberUtils.isNumber(btnId[i])){
				//	XsddsBean.setId(NumberUtils.toInt(btnId[i]));
				//}

				XsddsBean xsdds = new XsddsBean();
				xsdds.setXh(xhs[i]);
				xsdds.setGg(ggs[i]);
				xsdds.setDy(dys[i]);
				xsdds.setGy(gys[i]);
				xsdds.setDw(dws[i]);
				xsdds.setPh(phs[i]);
				xsdds.setSl(Double.valueOf(sls[i]));	//字符串转换为double
				 try {
 			        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(jhrq_khs[i]);
//System.out.println("高绪山date"+date);
  	                xsdds.setJhrq_kh(new java.sql.Date(date.getTime()));	       
                  } catch (ParseException e) {
                     e.printStackTrace();
                  }

                //System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date2));
                //System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date2));
				
					
				xsdds.setJsyq(jsyqs[i]);
				xsdds.setRow(row);
				row++;
				xsddsList.add(xsdds);
			}
		}
		
		return xsddsList;
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
