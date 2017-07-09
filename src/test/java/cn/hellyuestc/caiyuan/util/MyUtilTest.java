package cn.hellyuestc.caiyuan.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

public class MyUtilTest {

	@Test
	public void test() {
		File fromPic = new File("C:/caiyuan/userAvatars/myFace.jpg");
		
		try {
			BufferedImage image = ImageIO.read(fromPic);  
			Builder<BufferedImage> builder = null;  
			  
			int imageWidth = image.getWidth();  
			int imageHeitht = image.getHeight();  
			if ((float)300 / 400 != (float)imageWidth / imageHeitht) {  
			    if (imageWidth > imageHeitht) {  
			        image = Thumbnails.of(fromPic).height(300).asBufferedImage();  
			    } else {  
			        image = Thumbnails.of(fromPic).width(400).asBufferedImage();  
			    }  
			    builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, 400, 300).size(400, 300);  
			} else {  
			    builder = Thumbnails.of(image).size(400, 300);  
			}  
			builder.toFile("C:/caiyuan/userAvatars/myFace(2).jpg");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
}
