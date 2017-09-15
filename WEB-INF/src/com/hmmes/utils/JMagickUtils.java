package com.hmmes.utils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import magick.CompositeOperator;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 图片工具类
 * @author Vowo
 *
 */
public class JMagickUtils {
	
	

	private String toPath; 
	
	private InputStream input;

//	private final static String hmmes_LOGO_BG ="d:\\t2\\watermark_bg.png";
//	private final static String hmmes_LOGO_LOGO = "d:\\t2\\watermark_logo.png";
	private final static String hmmes_LOGO_BG = PathUtil.getRootPath()+"/static/common/images/watermark_bg.png" ; //水印背景图片 白色半透明
	private final static String hmmes_LOGO_LOGO = PathUtil.getRootPath()+"/static/common/images/watermark_logo.png" ;//水印图片 名称+网址

	
	public JMagickUtils(){
	    //不能漏掉这个，不然jmagick.jar的路径找不到   
		System.setProperty("jmagick.systemclassloader","no"); 
	}
	
	public static JMagickUtils  Utils= new JMagickUtils();

	/**
	 * 获取网络图片
	 * @param url
	 * @return
	 */
	public JMagickUtils ofUrl(String picUrl) throws IOException{
		URL url = new URL(picUrl);
		URLConnection urlConnection = url.openConnection();//打开url连接
		InputStream is = urlConnection.getInputStream();
		urlConnection.getContentType();
		return of(is);
	}

	/**
	 * 获取盗链的图片
	 * @param url
	 * @param refererURL
	 * @return
	 * @throws IOException
	 */
	public JMagickUtils ofUrl(String url,String refererURL) throws IOException{
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon;)");
		conn.setRequestProperty("Accept-Encoding", "gzip");
		conn.setRequestProperty("referer", refererURL);
		conn.setRequestProperty("cookie", "data");
		InputStream is = conn.getInputStream();
		return of(is);
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public JMagickUtils of(String path) throws IOException{
		File file =  new File(path);
		return of(file);
	}
	
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public JMagickUtils of(File file) throws IOException{
		InputStream input = new FileInputStream(file);
		return of(input);
	}
	
	
	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException 
	 */
	public JMagickUtils of(InputStream input ) throws IOException{
		this.input = input;
		return this;
	} 
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public JMagickUtils to(String path){
		this.toPath = path;
		return this;
	}
	
	/**
	 * 创建图片，默认增加图片logo水印
	 * @throws Exception
	 */
	public void create() throws Exception{
		create(true);
	}
	
	/**
	 *  创建图片
	 * @param isAddLogo  是否增加水印 
	 * @throws Exception
	 */
	public void create(boolean isAddLogo) throws Exception{
		createDir();// 创建文件夹
		ImageInfo info = null;   
        MagickImage image = null;
        MagickImage mask = null;//水印 
        int width,height;
        try{   
        	 info = new ImageInfo();   
             image = new MagickImage(info, getBytes());   
	         image.setFileName(toPath);   
	         width = (int)image.getDimension().getWidth();
	         height = (int)image.getDimension().getHeight();
	        //添加水印 start
	 		File fileBg =  new File(hmmes_LOGO_BG);
	 		File fileLogo =  new File(hmmes_LOGO_LOGO);
	 		if(fileBg.exists() && fileLogo.exists() && isAddLogo){
	 			mask = new MagickImage(new ImageInfo(hmmes_LOGO_BG));  
	 			image.compositeImage(CompositeOperator.AtopCompositeOp, mask,0, height-30);  
	 			mask = new MagickImage(new ImageInfo(hmmes_LOGO_LOGO));  
	 			image.compositeImage(CompositeOperator.AtopCompositeOp, mask, 0,  height-30);  
	 		}
	 		//添加水印 end
	        image.writeImage(info);   
	        System.out.println("创建图片："+toPath);
        }finally{   
            if(image != null){   
            	image.destroyImages();   
            }   
            info = null;   
            image = null;
            mask = null;
            destroy();
        }     
	}
	
	/**
	 * 创建图片，指定图片宽度，高度自动按比例缩放
	 * @param width  宽度
	 * @throws MagickException
	 */
	public void create(int width) throws Exception{
		createDir();// 创建文件夹
		ImageInfo info = null;   
        MagickImage image = null;   
        Dimension imageDim = null;   
        MagickImage scaled = null;   
        try{  
             info = new ImageInfo();   
             image = new MagickImage(info, getBytes());   
	         imageDim = image.getDimension();  
	         int imageWidth = (int)imageDim.getWidth();
	         int imageHeight = (int)imageDim.getHeight();
	         int target_width = width;
	    	 int w = target_width;
	    	 int h = (int) Math.round((imageHeight * target_width * 1.0 / imageWidth));
	         scaled = image.scaleImage(w, h);// minImage.cropImage(rect);
             scaled.setFileName(toPath);
             scaled.writeImage(info);   
             System.out.println("创建图片："+toPath);
        }finally{   
            if(scaled != null){   
                scaled.destroyImages();   
            }   
            info = null;   
            image = null;   
            imageDim = null;   
            scaled = null;   
            destroy();
        }     
	}
	
	/**
	 * 创建图片
	 * @param width  指定图片宽度
	 * @param height 指定图片高度
	 * @throws MagickException
	 */
	public void create(int width,int height) throws Exception{
		createDir();// 创建文件夹
		ImageInfo info = null;   
        MagickImage image = null;   
        Dimension imageDim = null;   
        MagickImage scaled = null;
        MagickImage minImage = null; 
        Rectangle rect = null;
        int  x = 0 ,y = 0,w = 0,h = 0,imageWidth = 0,imageHeight = 0;
        try{  
             info = new ImageInfo();   
             image = new MagickImage(info, getBytes());   
	         imageDim = image.getDimension();  
	         imageWidth = (int)imageDim.getWidth();
	         imageHeight = (int)imageDim.getHeight();
	         
	         // 得到合适的压缩大小，按比例。
    		 if (imageWidth <= imageHeight) {
    			w = width;
    			h = (int) Math.round((imageHeight * width * 1.0 / imageWidth));
    			if(width > h   ){
    				y = (h - height) / 2;
    			}
    		 } else {
    			h = height;
    			w = (int) Math.round((imageWidth * height * 1.0 / imageHeight));
    			x = (w - width) / 2;
    		 }
    		 minImage  = image.scaleImage(w, h);
    		 rect = new Rectangle(x,y,width,height);
	         scaled = minImage.cropImage(rect);
             scaled.setFileName(toPath);   
             scaled.writeImage(info);   
             System.out.println("创建图片："+toPath);
        }finally{   
            if(scaled != null){   
            	image.destroyImages();
                scaled.destroyImages();
                destroy();
            }
            info = null;   
            image = null;   
            imageDim = null;   
            scaled = null;
            minImage = null;
        }     
	}
	
	
    public static void main(String[] args) throws MagickException {
    	String filePath="D:\\t\\3.jpg";
    	String toPath="D:\\t2\\001_magick.jpg";
    	try {
			String url = "http://www.eale.cc/UploadFiles/20127910293960.jpg";
			url="http://www.9441.com/uploads/allimg/c120703/13412UYTZ10-13YK.jpg";
			//获取网络图片
			JMagickUtils.Utils.ofUrl(url).to("D:\\t2\\1.jpg").create();
			//获取盗链图片
			JMagickUtils.Utils.ofUrl(url,"http://www.9441.com/").to("D:\\t2\\1.jpg").create();
			
			JMagickUtils.Utils.of("d:\\src.jpg").to("D:\\t2\\1.jpg").create();
			//切缩略图片
			JMagickUtils.Utils.of("d:\\src.jpg").to("D:\\t2\\1.jpg").create(200, 200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public byte[] getBytes() throws IOException {
		byte[] bytes  = IOUtils.toByteArray(input);
		return bytes;
	}
	

	/**
	 * 返回InputStream
	 * @return InputStream
	 */
	public InputStream getInput(){
		return input;
	}
	
	
	public BufferedImage getBuffer() throws IOException{
		BufferedImage buffer = ImageIO.read(input);
		return buffer;
	}


	/**
	 * 创建文件夹
	 * @param path
	 */
	private void createDir() throws Exception{
		if(StringUtils.isBlank(toPath)){
			throw new Exception("未指定文件路径");
		}
		File file = new File(toPath);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
	}
	
	public void destroy(){
		this.input = null;
	}

	  
}
