package com.hmmes.utils;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 图片|宽高|缩略图200的高度|计算工具类
 * @author Administrator
 *
 */
public class ImageRange {
	private int width;
	private int height;

	
	/**
	 * 计算出网络图片的宽 高
	 * @param picUrl
	 */
	public ImageRange(String picUrl) throws Exception{
		InputStream input = null;
		try {
			input = JMagickUtils.Utils.ofUrl(picUrl,picUrl).getInput();
			size(input);
		} catch (Exception e) {
//			System.out.println("出现异常："+picUrl);
			input = JMagickUtils.Utils.ofUrl(picUrl).getInput();
			size(input);
		}
		
	}
	
	/**
	 * 计算出InputStream图片的宽高
	 * @param input
	 */
	public ImageRange(InputStream input) throws Exception{
		size(input);
	}
	
	/**
	 * 
	 * @param srcPath
	 * @return
	 * @throws FileNotFoundException
	 */
	private  void size(InputStream input) throws Exception{
		BufferedImage image = null;
		try {
			image = ImageIO.read(input);
			width = image.getWidth();
			height = image.getHeight();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			input = null; //清理内存
			image = null; //清理内存
		}
	}
	
	/**
	 * 返回 计算出缩略图的高度
	 */
	public int getMinHeight(){
		int h = 0 , target_width = 200;
		h = (int) Math.round((height * target_width * 1.0 / width));
		return h; 
	}
	
	/**
	 * 获取高度
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	
	/**
	 * 获取宽度
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	
}
