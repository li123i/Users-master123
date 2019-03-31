package forget;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class student extends JFrame {
	private String studentname;
	private ImageIcon picture=new ImageIcon(getClass().getResource("/pic/1.png"));//可以外包
	private JLabel people=new JLabel();
	private JLabel name=new JLabel();
	private JLabel title=new  JLabel("欢迎来到长沙理工大学图书馆");
	private JLabel name1=new JLabel("姓名：");
	
	private JButton record=new JButton("借阅记录");
	private JButton password=new JButton("修改密码");
	private JButton found=new JButton("查找书籍");
	private JButton exit=new JButton("注销");
	
	private Box box1=Box.createHorizontalBox();
	private Box box2=Box.createVerticalBox();
	private Box box3=Box.createHorizontalBox();
	private Box box4=Box.createHorizontalBox();
	private Font f=new Font("宋体",Font.BOLD,40);
	private Font f1=new Font("宋体",Font.BOLD,30);

	private Object[] columnNames = {"书籍编号","书名","作者","简介","是否被借走"};
	private Object[][] data =
		{
		{"00001","最好的我们","八月长安","","是"},
		         {"00002","魔道祖师","墨香铜臭","","是"},
		         {"00003","倾城之恋","张爱玲","","否"},
		         {"00004","斗罗大陆","唐家三少","","是"},
		};
    private JTable table = new JTable(data, columnNames);
    private JScrollPane pane=new JScrollPane(table);
	public student() {
		super("图书馆管理系统");
		setIconImage(picture.getImage());//下面的图片
		this.setLayout(null);//必须加，为了可以让label在特定的位置
		this.setSize(1200,1000);
		this.setLocation(200, 10);
		/*图片
		picture.setImage(picture.getImage().getScaledInstance(400, 350, Image.SCALE_DEFAULT));
		people.setIcon(picture);
		people.setBounds(250,80,400,350);
		this.add(people);*/
		
		title.setFont(f);
		box1.add(title);
		box1.setBounds(300, 10, 1000, 60);
		this.add(box1);	
		
		name1.setFont(f1);
		box3.add(name1);
		box3.add(Box.createHorizontalStrut(20));
		box3.setBounds(820, 90, 250, 50);
		this.add(box3);
		
		record.setFont(f1);
		record.addActionListener(new ActionListener(){  
	        //单击按钮执行的方法  
	        public void actionPerformed(ActionEvent arg0) {  
	        	//dispose();
	        	//setVisible(false);
	            //创建新的窗口  
	            record n=new record();
	            n.setVisible(true);  
	        }  
	          
	    }); 
		password.setFont(f1);
		found.setFont(f1);
		exit.setFont(f1);
		box4.add(record);
		box4.add(Box.createHorizontalStrut(10));
		box4.add(password);
		box4.add(Box.createHorizontalStrut(10));
		box4.add(found);
		box4.add(Box.createHorizontalStrut(10));
		box4.add(exit);
		box4.setBounds(20, 90, 800, 50);
		this.add(box4);
		
		
		pane.setBounds(90, 200, 1000, 700);
		table.setFont(new Font("宋体",Font.BOLD,20));
		table.setRowHeight(150);
		table.getTableHeader().setPreferredSize(new Dimension(1, 97));//设置表头大小
		table.getTableHeader().setFont(f1);
		table.setRowMargin(50);
		this.add(pane);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//关闭一个界面而不是所有界面
	}
	public static void main(String[] args) {
		new student();
	}

}
