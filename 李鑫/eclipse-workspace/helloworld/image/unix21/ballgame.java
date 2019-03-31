package blog.csdn.net.unix21;

import java.awt.*;
import javax.swing.*;

public class ballgame extends JFrame{

	//加载图片
	Image ball=Toolkit.getDefaultToolkit().getImage("image/ball.jpg");
	Image desk=Toolkit.getDefaultToolkit().getImage("image/desk.jpg");
	
	double x=180;//小球的横坐标
	double y=180;//小球的纵坐标
	boolean right=true;//向右
	
	//画窗口的方法
	public void paint(Graphics g) {
		System.out.println("huageqiu");
		g.drawImage(desk, 0, 0, null);
		g.drawImage(ball, (int)x, (int)y, null);
		if(right)
		{
			x=x+10;
		}else {
			x=x-10;
		}
		if(x>480-40) {
			right=false;
		}
		if(x<40) {
			right=true;
		}
	}
	//窗台加载
	void launchFrame() {
		setSize(480,369);//窗口大小
		setLocation(50,50);//窗口位置
		setVisible(true);
		//重画窗口让小球动起来
		while(true)
		{
			repaint();
			try {
				Thread.sleep(40);//40ms 1秒=1000毫秒
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	//main
	public static void main(String[] args) {
	ballgame game=new ballgame();
	game.launchFrame();
	}

}
