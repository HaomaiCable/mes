package com.hmmes.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import javax.imageio.ImageIO; 
import com.hmmes.exception.ServiceException;

/**
 * 图片处理工具类
 * @author Administrator
 *
 */
public class PicUtils  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Pic pic = new Pic();
	
	private final static String hmmes_LOGO_BG ="/static/common/images/watermark_bg.png" ;
	private final static String hmmes_LOGO_LOGO ="/static/common/images/watermark_logo.png" ;
	
	public static PicUtils  Utils= new PicUtils();
	
	/**
	 * 根据目录切小图
	 * @param path
	 * @param width
	 * @param height
	 * @throws IOException
	 * @throws Exception
	 */
	public static void crop(String path,int width,int height) throws IOException, Exception{
		File file =new File(path);
		if(!file.exists()){
			System.out.println("目录不能存在！");
			return;
		}
		if(!file.isDirectory()){
			System.out.println("不是正确文件夹");
			return;
		}
		for(File f : file.listFiles()){
			String minPath = PathUtil.minPicPath(f.getAbsolutePath(), width+"_"+height);
			PicUtils.Utils.of(f).to(minPath).createThumbnail(width,height);
			System.out.println("创建小图"+minPath);
		}
	}
	
	/**
	 * 
	 * @param url
	 * @param count
	 */
	public static void caiji(String url,int count){
		for(int i=0;i<count ;i++){
			String fileName = (i+1)+".jpg";
			String picUrl = url+fileName;
			String path = "C:\\Users\\Administrator\\Desktop\\pic\\0624\\02\\"+fileName;
			try {
				PicUtils.Utils.ofUrl(picUrl).to(path).create();
				System.out.println(picUrl);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	
	

	
	public static void main(String[] args) throws Exception {
		
		String filePath="D:\\t\\2.jpg";
    	String toPath="D:\\t\\2_magick.jpg";
    	System.out.println("完成");

	}
	
	
	/**
	 * 创建文件夹
	 * @param path
	 */
	private static void createDir(String path){
		File file = new File(path);
		createDir(file);
	}
	
	/**
	 * 创建文件夹
	 * @param path
	 */
	private static void createDir(File file){		
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
	}

	/**
	 * 
	 * @param srcPath
	 * @return
	 * @throws FileNotFoundException
	 */
	public PicUtils ofUrl(String picUrl) throws Exception{
		HttpURLConnection conn = (HttpURLConnection) new URL(picUrl).openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon;)");
		conn.setRequestProperty("Accept-Encoding", "gzip");
		conn.setRequestProperty("referer", picUrl);
		conn.setRequestProperty("cookie", "data");
		InputStream is = conn.getInputStream();
		return of(is);
	}
	
	/**
	 * 获取图片InputStream
	 * @param picUrl
	 * @return
	 * @throws IOException 
	 */
	public static InputStream toInStream(String path) throws IOException{
		InputStream stream = new FileInputStream(path);
		return stream;
	}
	
	
	/**
	 * 获取网络图片的 InputStream
	 * @param picUrl
	 * @return
	 * @throws IOException 
	 */
	public static InputStream urlToInStream(String picUrl)  throws ServiceException{
		
		try {
			URL url = new URL(picUrl);
			URLConnection urlConnection = url.openConnection();//打开url连接
			if(!checkPic(urlConnection.getContentType())){
				throw new ServiceException("URL"+picUrl + " is not (jpg|gif|png)");
			}
			InputStream stream  = urlConnection.getInputStream();
			return stream;
		} catch (IOException e) {
			throw new ServiceException("URL:"+picUrl + " is connect err"); 
		}
	}
	
	

	
	/**
	 * 
	 * @param srcPath
	 * @return
	 * @throws FileNotFoundException
	 */
	public PicUtils of(String srcPath) throws Exception{
		File imgfile = new File(srcPath);
		return of(imgfile);
	} 
	
	public PicUtils of(File srcFile) throws Exception{
		InputStream is = new FileInputStream(srcFile);
		return of(is);
	} 
	

	
	public PicUtils of(InputStream inputStream) throws Exception{
		pic.setInputStream(inputStream);
		return this;
	} 
	
	
	
	public PicUtils to(String targetPath) throws Exception{
		File file = new File(targetPath);
		return to(file);
	}
	
	
	public PicUtils to(File targetFile) throws Exception{
		createDir(targetFile);
		OutputStream os = new FileOutputStream(targetFile);
		return to(os);
	}
	
	public PicUtils to(OutputStream outputStream) throws Exception{
		pic.setOutputStream(outputStream);
		return this;
	}
	
	
	public void create() throws Exception{
		createJPG(pic.getInputStream(), pic.getOutputStream(),true);
		//BufferedImage src= ImageIO.read(pic.getInputStream());
	//	IOUtils.copy(pic.getInputStream(), pic.getOutputStream());
		System.out.println("创建成功~~");
	}
	
	public void create(boolean isAddLogo) throws Exception{
		createJPG(pic.getInputStream(), pic.getOutputStream(),isAddLogo);
//		BufferedImage src= ImageIO.read(pic.getInputStream());
	//	IOUtils.copy(pic.getInputStream(), pic.getOutputStream());
//		System.out.println("创建成功~~");
	}
	
	
	
	
	/**
	 * 根据IO流，创建文件
	 * @param is
	 * @param os
	 * @param isLogo 是否添加水印 true=添加,不添加=false
	 * @throws Exception
	 */
	public void createJPG(InputStream is,OutputStream os,boolean isAddLogo)throws Exception{
		BufferedImage src= ImageIO.read(is);
		int width = src.getWidth();
		int height = src.getHeight();
		
		Graphics2D g = src.createGraphics();
		//水印背景图片
		File fileBg =  new File(PathUtil.getRootPath()+hmmes_LOGO_BG);
		File fileLogo =  new File(PathUtil.getRootPath()+hmmes_LOGO_LOGO);
		System.out.println(fileBg.getAbsolutePath());
		System.out.println(fileBg.exists() +": "+fileLogo.exists());
		if(fileBg.exists() && fileLogo.exists() && isAddLogo){
			//水印底部半透明图片
			Image watermark_bg = ImageIO.read(fileBg);
			//水印Logo
			Image watermark_logo = ImageIO.read(fileLogo);
			if(watermark_logo.getWidth(null)< width){
				g.drawImage(watermark_bg, 0, height-30,width,30, null);
				g.drawImage(watermark_logo, 0, height-30, null);
			}
		}
        g.dispose();
		ImageIO.write(src, "JPEG" ,os);
	}

	

	/**
	 * 将图片转成InputStream  如: PicUtils.Utils.of("c://1.jpg").inputStream();
	 * @return  返回 InputStream
	 * @throws Exception
	 */
	public InputStream inputStream() throws Exception{
		return pic.getInputStream();
	}
	
	/**
	 * 将图片转成BufferedImage  如: PicUtils.Utils.of("c://1.jpg").buffereImage();
	 * @return  返回 BufferedImage
	 * @throws Exception
	 */
	public BufferedImage buffereImage() throws Exception{
		return ImageIO.read(pic.getInputStream()); 
	}
	
	/**
	 * 创建缩略图 
	 * @param width 宽度
	 * @param height 高度
	 * @return
	 * @throws IOException
	 */
	public PicUtils createThumbnail(int width,int height) throws IOException{
		int x = 0 , y = 0;
		return createThumbnail(x, y, width, height);
	}
	
	/**
	 * 创建缩略图
	 * @param x 坐标
	 * @param y 坐标
	 * @param width 宽度
	 * @param height 高度
	 * @throws IOException
	 */
	public PicUtils createThumbnail(int x ,int y ,int width,int height) throws IOException{
		BufferedImage image = get(width, height);
		ImageIO.write(image, "JPEG" ,pic.getOutputStream());
		pic.setInputStream(null);
		pic.setOutputStream(null);
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(pic.getOutputStream());  
//        encoder.encode(image);  
//        pic.getOutputStream().close();
		return this;
	}
	
	
	 
	public BufferedImage get(int width,int height)  throws IOException{
		BufferedImage srcImg  = pic.asBufferImg();
		int target_width = width;
		int target_height = height;
		int imageWidth = srcImg.getWidth();
		int imageHeight = srcImg.getHeight();
		
		int w = 0;
		int h = 0;
		int x = 0;
		int y = 0;
		// 得到合适的压缩大小，按比例。
		if (imageWidth <= imageHeight) {
			w = target_width;
			h = (int) Math.round((imageHeight * target_width * 1.0 / imageWidth));
			if(target_width > h   ){
				y = (h - height) / 2;
			}
		} else {
			h = target_height;
			w = (int) Math.round((imageWidth * target_height * 1.0 / imageHeight));
			//x = (w - width) / 2;
		}
		
		// 构建图片对象
		BufferedImage image = new BufferedImage(w,
				h, BufferedImage.TYPE_INT_RGB);
		// 绘制缩小后的图
		image.getGraphics().drawImage(srcImg, x, y, w, h, null);
		return image;
	}
	
	
	
	/** 
	  * 创建图片缩略图(等比缩放) 
	  * @param src 源图片文件完整路径 
	  * @param dist 目标图片文件完整路径 
	  * @param width 缩放的宽度 
	  * @param height 缩放的高度 
	  */  
	 public void createThumbnail(String src,String dist,float width,float height){  
	  try{  
//	   File srcfile = new File(src);  
//	   if(!srcfile.exists()){  
//	    System.out.println("文件不存在");  
//	    return;  
//	   }  
	   BufferedImage image = pic.asBufferImg();
	     
	   //获得缩放的比例  
	   double ratio = 1.0;  
	   //判断如果高、宽都不大于设定值，则不处理  
	   if(image.getHeight()>height || image.getWidth()>width){   
	    if(image.getHeight() > image.getWidth()){  
	     ratio = height / image.getHeight();  
	    } else {  
	     ratio = width / image.getWidth();  
	    }  
	   }  
	   //计算新的图面宽度和高度  
	   int newWidth =(int)(image.getWidth()*ratio);  
	   int newHeight =(int)(image.getHeight()*ratio);  
	     
	   BufferedImage bfImage= new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);  
	   bfImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH),0,0,null);  
	     


    String formatName = dist.substring(dist.lastIndexOf(".") + 1);   
	   //FileOutputStream os = new FileOutputStream(dist);  
	  // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);  
	  // encoder.encode(bfImage);  
	 //  os.close();   
   
      ImageIO.write(bfImage, /*"GIF"*/ formatName /* format desired */ , new File(dist) /* target */ );  


	   System.out.println("创建缩略图成功");  
	  } catch(Exception e) {  
		  e.printStackTrace();
	   System.out.println("创建缩略图发生异常"+e.getMessage());  
	  }  
	 }  
	 
	 
		/**
		 * 检查图片是否正确
		 * @param imgtype
		 * @return
		 */
		public static boolean  checkPic(String imgtype){
			if(imgtype.equalsIgnoreCase("image/gif")){
				return true;
			}else if(imgtype.equalsIgnoreCase("image/png")){
				return true;
			}else if(imgtype.equalsIgnoreCase("image/jpeg")){
				return true;
			}
			return false;
		}
		
	 
 	/**
	 * 根据类型返回后缀名
	 * @param imgtype
	 * @return
	 */
	public static String getPostfix(String imgtype){
		if(imgtype.equalsIgnoreCase("image/gif")){
			return ".gif";
		}else if(imgtype.equalsIgnoreCase("image/png")){
			return ".png";
		}else if(imgtype.equalsIgnoreCase("image/jpeg")){
			return ".jpg";
		}
		return ".jpg";
	}
	
	/**
	 * 根据类型返回后缀名
	 * @param imgtype
	 * @return
	 */
	public static String getPicfix(String url){
		if(url.endsWith(".gif")){
			return ".gif";
		}else if(url.endsWith(".png")){
			return ".png";
		}else if(url.endsWith(".jpg")){
			return ".jpg";
		}
		return ".jpg";
	}
	
}



class Pic{
	
	private  OutputStream outputStream;
	
	private  InputStream inputStream;
	
	

	/**
	 * 转换为BufferedImage
	 * @return
	 * @throws IOException
	 */
	public BufferedImage asBufferImg() throws IOException{
		if(inputStream == null){
			throw new IOException("file not exist!");
		}
		return ImageIO.read(inputStream);
	}
	
	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
	

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	
	
	
}
