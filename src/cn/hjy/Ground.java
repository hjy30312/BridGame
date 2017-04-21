package cn.hjy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年4月21日 下午6:10:58 
 */
public class Ground {
	BufferedImage image;
	int width,height;	
	Random ran = new Random();
	/*public*/ void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	int x,y;
	public Ground() throws IOException {
		image = ImageIO.read(getClass().getResourceAsStream("ground.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 0;
		y = 500;
	}
	
	public void step(){
		x--;
		if(x == -109){
			x = 0;
		}
		if(x%50==0){
			y-=ran.nextInt(5)  ;	//[-10,5]
		}
		if(y< 400){
			y+=10;
		}
	}
}
