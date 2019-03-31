package forget;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class addbook extends  JFrame{

	private JButton yes = new JButton("确定");
	private JButton no = new JButton("取消");
	private ImageIcon picture = new ImageIcon(getClass().getResource("/pic/1.png"));
	private JLabel title = new JLabel("添加书籍");
	private JLabel book = new JLabel("书名:");
	private JLabel name = new JLabel("作者:");
	private JLabel introduce = new JLabel("简介:");
	 
	private JTextField book1 = new JTextField(10);
	private JTextField name1 = new JTextField(10);
	private JTextArea introduce1 = new JTextArea(3,10);
	
	private Box box1 = Box.createHorizontalBox();
	
	public addbook() {
		super("图书馆管理系统");
		setIconImage(picture.getImage());//下面的图片
		this.setLayout(null);//必须加，为了可以让label在特定的位置
		title.setFont(new Font("宋体",Font.BOLD,50));
		title.setBounds(270, 10, 600, 100);
		this.add(title);
		
		book.setFont(new Font("宋体",Font.BOLD,26));
		book.setBounds(20,100, 90, 90);
		this.add(book);
		book1.setBounds(120, 130, 500, 42);
		this.add(book1);
		
		name.setFont(new Font("宋体",Font.BOLD,26));
		name.setBounds(20,180, 90, 90);
		this.add(name);
		name1.setBounds(120, 210, 500, 40);
		this.add(name1);
		
		introduce.setFont(new Font("宋体",Font.BOLD,26));
		introduce.setBounds(20,260, 90, 90);
		this.add(introduce);
		introduce1.setBounds(120, 290, 500, 230);
		this.add(introduce1);
		
		yes.setFont(new Font("宋体",Font.BOLD,28));
		no.setFont(new Font("宋体",Font.BOLD,28));
		box1.add(yes);
		box1.add(Box.createHorizontalStrut(50));
		box1.add(no);
		box1.setBounds(240,530, 600, 100);
		this.add(box1);
		this.setSize(800,700);
		this.setVisible(true);
		setLocationRelativeTo(null);//中间出现
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new addbook();
	}

}
