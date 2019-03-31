package learning;

import java.awt.Color;

import javax.swing.CellRendererPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class learning extends JFrame{

	private JTable table;
	private TableModel model;//表模型
	private Object[][] data;//存放表格数据
	private Object[] columnNames;//定义表格中列名字
	private JScrollPane pane;//带滚动条的面板
	public learning() {
		super("第一个JTable程序应用");
		init();
	}
	private void init() {
		data=new Object[][] {
			{
				"1001","zhangzhu","yanfa","nan",new Double(3000)
			},
			{
				"1002","lisi","yanfa","nan",new Double(3000)
			},
			{
				"1003","wanger","guanli","nan",new Double(3000)
			},
			{
				"1004","sunwu","xiaoshou","nan",new Double(3000)
			}
		};//初始化表格数据
		columnNames = new Object[] {
			"num","name","major","sex","salary"	
		};
		//初始化表模型，利用DefaultTableModel来生成对象
		model = new DefaultTableModel(data,columnNames) {
			//把单元格不可编辑 重写这个函数
			public boolean isCellEditable(int row,int col) {
				if(row==0&&col==0) { 
					return true;//指定行可以编辑
				}
				return false;
			}
		};
		//初始化JTable
		table= new JTable(model);
		/*
		 * //改变字体 颜色
		TableColumn tablecolumn=table.getColumn("name");
		//初始化table渲染器
		DefaultTableCellRenderer cellrenderer = new DefaultTableCellRenderer();
		//设置单元格的前景色
		cellrenderer.setForeground(Color.red);
		//将渲染器对象设置到指定的表格
		tablecolumn.setCellRenderer(cellrenderer);
		*	
		* 设置 背景颜色
		TableColumn department = table.getColumn("sex");
		DefaultTableCellRenderer cellrenderer = new DefaultTableCellRenderer();
		cellrenderer.setBackground(Color.darkGray);
		department.setCellRenderer(cellrenderer);
		*/
		//初始化带滚动条的面板
		pane= new JScrollPane();
		//把table放进去
		pane.getViewport().add(table);
		//把面板放到里面
		this.getContentPane().add(pane);
		this.setSize(500,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);//是否能最大化
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new learning();
	}

}
