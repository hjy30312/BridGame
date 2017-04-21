package cn.hjy;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


/** 
 * @author  作者 E-mail: 543363559@qq.com
 * @date 创建时间：2017年4月21日 下午6:07:58 
 */
public class Bird  {
	BufferedImage image;
	int width,height;
	int x,y;
	int size;	//圆的半径,方便碰撞检验
	BufferedImage[] images;
	int index;
	//增加属性 描述运动
	
	double v0;
	double s;
	double t;
	double g;
	int i = 1;	//控制旋转变量
	double alpha;
	
	public Bird() throws Exception{
		image = ImageIO.read(getClass().getResource("0.png"));	//异常  可能找不到
		width = image.getWidth();
		height = image.getHeight();
		size = 40;
		x = 132;
		y = 285;
		s = 0;
		v0 = -20;
		g = 4;
		t = 0.25;
		alpha = 0;
		images = new BufferedImage[8];
		index = 0;
		for (int i = 0; i < images.length ; i++) {
			images[i] = ImageIO.read(getClass().getResource(i + ".png"));
		}
	}
	
	public void fly(){	/*切换图片形成动态*/
		image = images[ (index++ /10) % 8];
	}
	public void flappy(){
		v0 = -20;
	}
	public boolean hit(Ground g){
		if(y > g.y - size/2){
			y = g.y - size/2;
			alpha = Math.PI/2;
			return true;
		}
		return false;
	}
	
	public boolean hit(Column c){
		if(x>c.x-c.width/2-size/2 && x<c.x-c.width/2){	//y轴的 两面
			if(y < c.y-c.gap/2 +size/2){	//上面
				return true;
				}
			if(y> c.y+c.gap/2-size/2){	//下面
				return true;
			}
		}
		if(x>c.x-c.width/2 && x<c.x+c.width/2){ 	//x轴的两面
			if(y < c.y-c.gap/2 +size/2){	//上面
				y = c.y - c.gap/2 + size/2;
				return true;
				}
			if(y> c.y+c.gap/2-size/2){	//下面
				y = c.y + c.gap/2 - size/2;
				return true;
			}
		}
		return false;
	}
 	public void step(){
 		s = v0 * t + g*t*t/2;
 		y+=s;
 		v0 = v0 + g * t;
 		alpha = Math.atan(s/6);
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}


	public void setY(int y) {
		this.y = y;
	}
}




