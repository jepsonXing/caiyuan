package cn.hellyuestc.caiyuan.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	
	/*
	 * 压缩图片
	 */
	public static void compressImage(String from, String imageName, int width, int height, String localAddr) {
		System.out.println(from);
		File localImage = new File(from);
		try {
			BufferedImage image = ImageIO.read(localImage);  
			Builder<BufferedImage> builder = null;  
			  
			int imageWidth = image.getWidth();  
			int imageHeitht = image.getHeight();  
			if ((float)height / width != (float)imageWidth / imageHeitht) {  
			    if (imageWidth > imageHeitht) {  
			        image = Thumbnails.of(localImage).height(height).asBufferedImage();  
			    } else {  
			        image = Thumbnails.of(localImage).width(width).asBufferedImage();  
			    }  
			    builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, width, height).size(width, height);  
			} else {  
			    builder = Thumbnails.of(image).size(width, height);  
			}  
			builder.toFile(localAddr + "\\" + imageName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 压缩问题图片
	 */
	public static void compressQuestionImage(String from, String imageName, String localAddr) {
		compressImage(from, imageName, 100, 100, localAddr);
	}
	
}