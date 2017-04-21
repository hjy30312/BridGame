package cn.hjy;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年4月21日 下午6:10:31 
 */
public class Column {
	BufferedImage image;
	int width,height;
	int x,y;
	int gap;
	int distance;
	Random ran = new Random();	//随机数
	public Column(int n) throws Exception{
		image = ImageIO.read(getClass().getResourceAsStream("column.png"));
		width = image.getWidth();
		height = image.getHeight();
		gap = 144;
		distance = 280;
		y = ran.nextInt(156) + 175;	//[175,327]
		x = 500 + (n-1)*distance;
	}

	public void setX(int n) {
		this.x = 500 + (n-1)*distance;
	}

	public void setY() {
		this.y = ran.nextInt(156) + 175;
	}

	public void step() throws InterruptedException {
		x--;
		if (x == -width/2) {
			x = distance *2 - width/2 ;
			y = ran.nextInt(156) + 175;
		}
		if(x%50==0){
			y+=ran.nextInt(20) - 10;
		}
	}
}
