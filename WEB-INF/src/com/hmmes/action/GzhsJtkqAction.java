
package com.hmmes.action;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

//import java.util.Date;

import net.sf.json.JSONObject;
import org.json.JSONArray;
//import net.sf.json.JsonConfig;

import com.alibaba.fastjson.JSON;  
//import com.alibaba.fastjson.JSONObject;  
import com.alibaba.fastjson.annotation.JSONField;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hmmes.bean.GzhsJtkqBean;
import com.hmmes.model.GzhsJtkqModel;
import com.hmmes.service.GzhsJtkqService;
import com.hmmes.utils.HtmlUtil;
import com.hmmes.utils.DateUtil;
import com.hmmes.utils.SessionUtils;

 
@Controller
@RequestMapping("/gzhsJtkqManage") 
public class GzhsJtkqAction extends BaseAction{
	
	private final static Logger log= Logger.getLogger(GzhsJtkqAction.class);
	
	// Servrice start
	@Autowired(required=false) 
	private GzhsJtkqService<GzhsJtkqBean> gzhsJtkqService; 
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/gzhsJtkq")
	public ModelAndView  list(GzhsJtkqModel model,HttpServletRequest request) throws Exception{
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
		 List<GzhsJtkqBean> dataList = gzhsJtkqService.queryByList(model);
		 //设置页面数据
		 context.put("dataList", dataList);
		 return forword("business/gzhsJtkqManage",context); 
	}
	
	/**
	 * ilook 首页
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/dataList") 
	public void  dataList(Integer gdid,GzhsJtkqModel model,HttpServletResponse response) throws Exception{
		String gd="";
        switch (gdid)
        {
            case 1:
                gd="拔丝工段";
                break;
            case 2:
                gd="橡缆工段";
                break;
            case 3:
                gd="低压工段";
                break;
            case 4:
                gd="高压工段";
                break;
		    default:
				gd="";
			    break;
        }

		model.setGd(gd);
		List<GzhsJtkqBean> dataList = gzhsJtkqService.queryByList(model);
		List<GzhsJtkqBean> result = new ArrayList<GzhsJtkqBean>();
		for (Object ele : dataList)
		{
			GzhsJtkqBean st = (GzhsJtkqBean)ele;
			result.add(st);
		}
		JSONArray jsonArr= new JSONArray(result);
		String jsonStr="{\"total\":"+model.getPager().getRowCount()+",\"rows\":"+jsonArr.toString()+"}";
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
	public void save(Integer id,String gd,String sbmc,String ygxm,Double dexs,String kqrq,Integer bc,Double cqgs,
		HttpServletRequest request,HttpServletResponse response) throws Exception{
		GzhsJtkqBean bean =new GzhsJtkqBean();
		GzhsJtkqBean jtkqbean  = gzhsJtkqService.queryById(id);
        
		if (jtkqbean!=null)
		{
			bean=jtkqbean;
		}


		String username = SessionUtils.getUser(request).getNickName();
        bean.setLrBy(username);
		bean.setGd(gd);
		bean.setSbmc(sbmc);
		bean.setYgxm(ygxm);
		bean.setDexs(dexs);
		bean.setBc(bc);
		bean.setCqgs(cqgs);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        String sdate;
		Date ddate=null;
	    if (StringUtils.isNotBlank(kqrq) )
		{
			sdate=kqrq;
		    	
		}
		else 
		{  
			sdate=sdf.format(new Date());
			 
		}
		try {
 			        
       		 ddate = sdf.parse(sdate);
       
        } catch (ParseException e) {
             e.printStackTrace();
        }
        bean.setKqrq(new java.sql.Date(ddate.getTime()));  //java.util.Date -->java.sql.Date 
		if(bean.getId() == null){
			gzhsJtkqService.add(bean);
		}else{
			gzhsJtkqService.update(bean);
		}
		sendSuccessMessage(response, "保存成功~");
	}
	
	@RequestMapping("/getId")
	public void getId(Integer id,HttpServletResponse response) throws Exception{
		 JSONObject context = new JSONObject();
		 Map<String,Object>  data = new HashMap<String,Object> ();
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		 Date kqdate=new Date();
		 Calendar rightNow = Calendar.getInstance();
		 rightNow.setTime(kqdate);
		 // righNow.add(Calendar.YEAR,-1);//日期减1年
		 // rightNow.add(Calendar.MONTH,3);//日期加3个月
		 rightNow.add(Calendar.DAY_OF_YEAR,-1);//日期-1天
		 Date dt1=rightNow.getTime();
		 String sdate=df.format(dt1);//考勤时间，字符串当前时间-1天作为考勤时间

		 GzhsJtkqBean bean  = gzhsJtkqService.queryById(id);
		if(bean  == null){
			data.put("gd", "");
		    data.put("sbmc","");
			data.put("ygxm","");
		    data.put("dexs",1);
            data.put("kqrq", sdate);
		    data.put("bc",1);
		    data.put("cqgs",9);
		}
		else{
			data.put("id", bean.getId());
		    data.put("gd", bean.getGd());
		    data.put("sbmc", bean.getSbmc());
			data.put("ygxm",bean.getYgxm());
		    data.put("dexs",bean.getDexs());
			data.put("kqrq", bean.getKqrq()!=null?(df.format(bean.getKqrq())):"");
		    data.put("bc",bean.getBc());
		    data.put("cqgs",bean.getCqgs());
		}

        context.put("data", data);
		context.put(SUCCESS, true);	
		HtmlUtil.writerJson(response, context);
	}

	@RequestMapping("/delete")
	public void delete(Integer[] id,HttpServletResponse response) throws Exception{
		gzhsJtkqService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}
}

