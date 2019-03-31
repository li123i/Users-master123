package forget;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class forget extends JFrame {
	
	private Box box1=Box.createVerticalBox();
	private Box box2=Box.createHorizontalBox();
	private Box box3=Box.createHorizontalBox();
	private Box box4=Box.createHorizontalBox();
	private Box box5=Box.createHorizontalBox();
	private Box box6=Box.createHorizontalBox();

	
	private JLabel fotpass=new JLabel("忘记密码");
	private JLabel oldpass=new JLabel("原密码:");
	private JLabel newpass=new JLabel("新密码:");
	
	private TextField old=new TextField(10);
	private TextField new1=new TextField(10);
	private JButton ok=new JButton("确定");
	private JButton cancel=new JButton("取消");
	private JButton fot=new JButton("忘记原密码");
	private Font f=new Font("宋体",Font.BOLD,60);
	private Font f1=new Font("宋体",Font.BOLD,30);
	private Font f2=new Font("宋体",Font.BOLD,25);
	
	public void ziti(){
		fotpass.setFont(f);
		ok.setFont(f1);
		cancel.setFont(f1);
		fot.setFont(f1);
		oldpass.setFont(f2);
		newpass.setFont(f2);
	}
	public forget() {
		super("图书馆管理系统");
		System.out.println("忘记密码");
		this.setSize(1000, 800);
		this.setLocation(60,10);
		this.setVisible(true);
		ziti();
		box2.add(Box.createHorizontalStrut(40));
		box2.add(fotpass);
		box2.add(Box.createHorizontalStrut(40));
		box3.add(Box.createHorizontalStrut(140));
		box3.add(oldpass);
		box3.add(Box.createHorizontalStrut(20));
		box3.add(old);
		box3.add(Box.createHorizontalStrut(160));
		box4.add(Box.createHorizontalStrut(140));
		box4.add(newpass);
		box4.add(Box.createHorizontalStrut(20));
		box4.add(new1);
		box4.add(Box.createHorizontalStrut(160));
		box5.add(Box.createHorizontalStrut(40));
		box5.add(ok);
		box5.add(Box.createHorizontalStrut(40));
		box5.add(cancel);
		box5.add(Box.createHorizontalStrut(40));
		box5.add(fot);
		box5.add(Box.createHorizontalStrut(40));
		
		box1.add(Box.createVerticalStrut(110));
		box1.add(box2);
		box1.add(Box.createVerticalStrut(80));
		box1.add(box3);
		box1.add(Box.createVerticalStrut(100));
		box1.add(box4);
		box1.add(Box.createVerticalStrut(100));
		box1.add(box5);
		box1.add(Box.createVerticalStrut(110));
		this.add(box1);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
	}
	public static void main(String[] args) {
		new forget();
	}

}
