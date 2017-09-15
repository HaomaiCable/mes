package com.hmmes.utils;

import java.io.OutputStream;  
import java.util.List;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;  
import java.lang.reflect.Field; 
import java.io.File;
  
import jxl.Workbook;  
import jxl.format.Alignment;  
import jxl.format.Border;  
import jxl.format.BorderLineStyle;  
import jxl.format.VerticalAlignment;  
import jxl.write.Label;  
import jxl.write.WritableCellFormat;  
import jxl.write.WritableFont;  
import jxl.write.WritableSheet;  
import jxl.write.WritableWorkbook;  

/*** 
 * @author 
 */  
public class ExportExcel
{  
 /*************************************************************************** 
  * @param fileName EXCEL文件名称 
  * @param reporttitle 大标题
  * @param listTitle EXCEL文件第一行列标题集合 
  * @param listContent EXCEL文件正文数据集合 
  * @return 
  */  

  public   String exportExcel(HttpSession session,String fileName,String reporttitle,String[] Title, List<Object> listContent,Integer colWidth)
  {  

     // 以下开始输出到EXCEL  
     String result;    
  	  try 
	  {       
  
/**
	   //定义输出流，以便打开保存对话框______________________begin  
      //HttpServletResponse response=ServletActionContext.getResponse();  

	   //response=ServletActionContext.getResponse();
        response.setContentType("text/html;charset=gbk");
		// 获取输出流
		PrintWriter os = response.getWriter();
       //response.reset();// 清空输出流    
	   //OutputStream os = response.getOutputStream();// 取得输出流    

       //response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));  
       // 设定输出文件头        
       //response.setContentType("application/msexcel");// 定义输出类型   
	   response.setContentType("APPLICATION/DOWNLOAD");
	   //response.setContentType("application/octet-stream");
       response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));  
       //response.flushBuffer();

       //wb:HSSFWorkbook
       //wb.write(response.getOutputStream()); 
       //定义输出流，以便打开保存对话框_______________________end  
//System.out.println("高绪山：exportExcle="+fileName);  
*/
/**
//首先生成一个输入流InputStream stream = *****;
OutputStream os = null;
response.setContentType("APPLICATION/DOWNLOAD");
response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"),"ISO8859-1")); );
//***是文件名
response.setContentLength(stream.available());
os = response.getOutputStream();
int iBytesRead = 0;
byte[] buffer = new byte[8192];
while ((iBytesRead = stream.read(buffer, 0, 8192)) != -1)
	{os.write(buffer, 0, iBytesRead);}
os.close();
response.flushBuffer();
*/

       /** **********创建工作簿************ */  
	   String path = session.getServletContext().getRealPath("/uploadfiles/");
  	   String serverFilePath=path+File.separator+ fileName;
		//String  serverFilePath=request.getRealPath("/")+ "\\uploadfiles\\"+fileName;
	   	//String  serverFilePath="d:\\销售订单导出\\"+fileName;
       //WritableWorkbook workbook = Workbook.createWorkbook(new File("c:\\"+fileName));  //
        WritableWorkbook workbook = Workbook.createWorkbook(new File(serverFilePath));    
       /** **********创建工作表************ */  
  
       WritableSheet sheet = workbook.createSheet("Sheet1", 0);  
  
       /** **********设置纵横打印（默认为纵打）、打印纸***************** */  
       jxl.SheetSettings sheetset = sheet.getSettings();  
       sheetset.setProtected(false);  
  
 
       /** ************设置单元格字体************** */  
       WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  
       WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);  
  
       /** ************以下设置三种单元格样式，灵活备用************ */  
       // 用于标题居中  
       WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);  
       wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
       wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
       wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐  
       wcf_center.setWrap(false); // 文字是否换行  
     
       // 用于正文居左  
       WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);  
       wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条  
       wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
       wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐  
       wcf_left.setWrap(false); // 文字是否换行     
   
 
      /** ***************以下是EXCEL开头大标题********************* */  
      sheet.mergeCells(0, 0, colWidth-1, 0);  
      sheet.addCell(new Label(0, 0, reporttitle, wcf_center));  
      /** ***************以下是EXCEL第一行列标题********************* */  
      for (int i = 0; i < Title.length; i++)
	  {  
           sheet.addCell(new Label(i, 1,Title[i],wcf_center));  
 
      }  

      /** ***************以下是EXCEL正文数据********************* */  
      Field[] fields=null;  
      int i=2;  
      for(Object obj : listContent)
      //for(int ii=listContent.size()-1;ii>=0;ii--)
	  { 
		  //Object obj = listContent.get(ii);
          fields=obj.getClass().getDeclaredFields();  
          int j=0; 
          for(Field v:fields)
		  {  
               v.setAccessible(true);  
               Object va=v.get(obj);  
//System.out.println("高绪山：exportExcle="+va);  
              if(va==null)
			  {  
                  va="";  
              }  
              sheet.addCell(new Label(j, i,va.toString(),wcf_left));  
              j++;  
          }  
         i++;  
      }  
      /** **********将以上缓存中的内容写到EXCEL文件中******** */  
      workbook.write();  
      /** *********关闭文件************* */  
      workbook.close();   


    } 
   catch (Exception e) 
   {  
      result="系统提示：Excel文件导出失败，原因："+ e.toString();  
      System.out.println(result);   
      e.printStackTrace();  
  }  
   result="系统提示：Excel文件导出成功！";
   //result="系统提示：Excel文件导出成功！文件位置【\\\\U8\\销售订单导出\\"+fileName+"】,请从【网上邻居】复制使用。";
   System.out.println(result);   
   return result;  
  }  
 
}  
