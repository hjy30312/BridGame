package cn.hjy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/** 
 * @author  作者 E-mail: 
 * @date 创建时间：2017年4月21日 下午6:10:48 
 */
public class Game extends JPanel  {
	BufferedImage bg;
	Ground ground;
	Bird bird;
	Column column1, column2;
	JPanel from;
	int score;

	BufferedImage gameoverlmg;
	boolean gameover;
	BufferedImage gamestartlmg;
	boolean gamestart;

	public Game() throws Exception { /* 构造, 异常处理 */
		bg = ImageIO.read(getClass().getResource("bg.png")); /* 调用图片 */
		ground = new Ground();
		column1 = new Column(1);
		column2 = new Column(2);
		bird = new Bird();
		score = 0;
		gameoverlmg = ImageIO.read(getClass().getResource("gameover.png"));
		gameover = false;
		gamestartlmg = ImageIO.read(getClass().getResource("start.png"));
		gamestart = true;
	}

	public void paint(Graphics g) { // 绘制画笔

		g.drawImage(bg, 0, 0, null);
		g.drawImage(column1.image, column1.x - column1.width / 2, column1.y - column1.height / 2, null);
		g.drawImage(column2.image, column2.x - column2.width / 2, column2.y - column2.height / 2, null);
		g.drawImage(ground.image, 0, 500, null);
		g.drawImage(ground.image, ground.x, ground.y, null);
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(bird.alpha/* double */, bird.x, bird.y);
		g.drawImage(bird.image, bird.x - bird.width / 2, bird.y - bird.height / 2, null);
		g2.rotate(-bird.alpha, bird.x, bird.y);

		Font f = new Font("宋体", Font.BOLD, 40);
		g.setFont(f);

		g.drawString("得分: " + score, 40, 60);
		g.setColor(Color.white);
		g.drawString("得分: " + score, 42, 62);
		if (gamestart) {
			g.drawImage(gamestartlmg, 0, 0, null);
		}
		if (gameover) {
			g.drawImage(gameoverlmg, 0, 0, null);
		}
	}

	public static void main(String[] args) throws Exception {
		// 类(类型) 引用 对象
		Game game = new Game();
		JFrame frame = new JFrame("何大洋出品");
		frame.add(game);
		frame.setSize(432, 644);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/* 关闭窗口 */
		frame.setLocationRelativeTo(null); // 将窗口置于屏幕中间
		frame.setAlwaysOnTop(true); // 不会被覆盖
		frame.setVisible(true); // 尽快调用paint方法,绘制对话框
		game.action();
	}

	public void action() throws Exception {
		MouseListener l = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (gamestart) {
					gamestart = false;
				} else {
					bird.flappy();
				}
				if (!gamestart && gameover) {	//重新开始游戏
					
					JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(Game.this));
					int val = JOptionPane
				      .showConfirmDialog(dialog, "您好,需要重新开始吗?");
				    if(val==JOptionPane.NO_OPTION){
				      System.exit(0);
				    }
					gamestart = true;
					gameover = false;
					bird.setY(285);
					bird.setAlpha(0);
					column1.setX(1);
					column1.setY();
					column2.setX(2);
					column2.setY();
					ground.setY(500);
					score = 0;
				}
			}
		};
		this.addMouseListener(l);
		while (true) { // 永真循环
			if (!gameover && !gamestart) {
				ground.step();
				column1.step();
				column2.step();
				bird.step();
				bird.fly();
				if (bird.x == column1.x || bird.x == column2.x) {
					score++;
				}
				if (bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
					gameover = true;
						    
				}
			}
			repaint();
			Thread.sleep(1000 / 60); // 1秒60下 可能中断线程
		}
	}
}
