package forget;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class teacher extends JFrame {

	private JButton poassword=new JButton("修改密码");
	private JButton books = new JButton("添加书籍");
	private JButton borrow = new JButton("添加书籍");
	private JButton exit = new JButton("注销");
	
	private JLabel title = new JLabel("欢迎来到长沙理工大学图书馆");
	 
	private Font f1 = new Font("宋体",Font.BOLD,40);
	
	private DefaultTableModel model=null;
	private JTable table = null;
	private JScrollPane pane = new JScrollPane(table);
	
	private Box box1 = Box.createHorizontalBox();
	private Box box2 = Box.createHorizontalBox();
	String[][] data= {{"1","1","1","1","1","1"},{"2","2","2","2","2","2"}};		
	String [] titles= {"书名","作者",
				"书籍编号","借书学号","借书日期","应还日期"
		};
	public teacher() {
		super("图书馆管理系统");
		this.setLayout(null);//必须加，为了可以让label在特定的位置
		
		title.setFont(f1);
		box1.add(title);
		box1.setBounds(170, 10, 600, 40);
		this.add(box1);
		
		borrow.setFont(new Font("宋体",Font.BOLD,22));
		books.setFont(new Font("宋体",Font.BOLD,22));
		poassword.setFont(new Font("宋体",Font.BOLD,22));
		exit.setFont(new Font("宋体",Font.BOLD,22));
		
		box2.add(borrow);
		box2.add(Box.createHorizontalStrut(30));
		box2.add(poassword);
		box2.add(Box.createHorizontalStrut(30));
		box2.add(books);
		box2.add(Box.createHorizontalStrut(30));
		box2.add(exit);
		box2.setBounds(20, 70, 600, 70);
		this.add(box2);
		
		model = new DefaultTableModel(data, titles);
		table = new JTable(model);
		getContentPane().add(pane,BorderLayout.CENTER);
		this.add(pane);
		this.setSize(900, 800);
		this.setVisible(true);
		setLocationRelativeTo(null);//中间出现
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//关闭一个界面而不是所有界面
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new teacher();
	}

}
