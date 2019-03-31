package forget;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class record extends  JFrame{

	ImageIcon picture = new ImageIcon(getClass().getResource("/pic/1.png"));
	
	private JLabel book = new JLabel("借阅记录");
	private JLabel name = new JLabel("姓名：");
	
	private JTable table;
	private JScrollPane pane;
	private TableModel model;
	private JTableHeader header;
	Object[][] data= {
			{"aiqinhai","bcj","00021","201716080244","2018.6.30"},
			{"aiqinhai","bcj","00021","201716080244","2018.6.30"},
			{"aiqinhai","bcj","00021","201716080244","2018.6.30"}
	};
	Object[] clomnName= {"书名","作者",
		"编号","借书日期","应还日期"	
	};
	public record() {
		super("图书馆管理系统");
		this.setLayout(null);
		book.setFont(new Font("宋体",Font.BOLD,45));
		book.setBounds(400, 10, 300, 60);
		this.add(book);
		name.setFont(new Font("宋体",Font.BOLD,32));
		name.setBounds(30, 90, 160, 40);
		this.add(name);
		model=new DefaultTableModel(data,clomnName) {
			public boolean isCellEditable(int cow,int rol){
				return false;
			}
		};
		table = new JTable(model);
		table.setRowHeight(50);//表格的高度
		table.setFont(new Font("宋体",Font.BOLD,20));
		header=table.getTableHeader();//获得表格的表头
		header.setFont(new Font("宋体",Font.PLAIN,24));             //字体
        header.setPreferredSize(new Dimension(header.getWidth(),50));//设置表头的高度
		pane = new JScrollPane();
		pane.getViewport().add(table);
		pane.setBounds(0, 190, 1000, 580);
		this.getContentPane().add(pane);
		this.setSize(1000, 800);
		setIconImage(picture.getImage());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new record();
	}

}
