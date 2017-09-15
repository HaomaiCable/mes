package com.hmmes.action;

 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;


import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*** 
 * @author 
 */  
@Controller
@RequestMapping("/downLoadManage") 
public class DownLoadAction
{  

      @RequestMapping("/downLoad")
      public ResponseEntity<byte[]>  downLoad(
			String fileName,HttpSession session) throws Exception{
		// 上传文件路径
//System.out.println("高绪山：downLoad="+fileName);
		String path = session.getServletContext().getRealPath("/uploadfiles/");
		//String path="d:\\销售订单导出\\";
		//fileName="gxs.xls";
		// 获得要下载文件的File对象
		File file = new File(path+File.separator+ fileName);
		// 创建springframework的HttpHeaders对象
		HttpHeaders headers = new HttpHeaders();  
        // 下载显示的文件名，解决中文名称乱码问题  
        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        // 通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName); 
        // application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 201 HttpStatus.CREATED
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                headers, HttpStatus.CREATED); 
	}
}  
