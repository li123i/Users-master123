package collection;

import java.awt.*;

import javax.swing.Box;
/**
 * 自己实现Arrylist
 * @author 李鑫
 *
 */
public class text01 extends Frame{

	private Button OK=new Button("Ok"); 
	private Button OK1=new Button("Ok1"); 
	private Button OK2=new Button("Ok2"); 
	private Button OK3=new Button("Ok3"); 
	private Button OK4=new Button("Ok4"); 

//	public text01(){
//		super("图书馆");
//		this.setSize(600, 400);
//		this.setLocation(200,200);
//		this.setVisible(true);
//		/**
//		 * 布局管理器类this.setLayout(new BorderLayout());
//		Frame默认的布局管理器 位置是东西南北
//		Flowlayout 横着布局
//		 */
//		/*
//		this.setLayout(new BorderLayout());
//		this.add(OK,BorderLayout.NORTH);//在北边
//		this.add(OK1,BorderLayout.SOUTH);
//		this.add(OK2,BorderLayout.EAST);
//		this.add(OK3,BorderLayout.WEST);
//		this.add(OK4,BorderLayout.CENTER);
//*/
//		//FlowLayout
//		//this.setLayout(new FlowLayout(FlowLayout.LEFT,20,30));
//		//左对齐  水平间距  垂直间距
//		//this.setLayout(new GridLayout(3, 2,10,20));
//		//表格布局 3行两列 水平间距  垂直间距
//		this.setLayout(null);//绝对定位
//		//OK.setSize(100,190);
//		//OK.setLocation(50,50);
//		OK.setBounds(50, 50, 100, 190);//是上面那两个的组合
//		this.add(OK);
//	}
	//盒子布局
	private Box box1=Box.createHorizontalBox();//水平摆放
	private Box box2=Box.createVerticalBox();//垂直拜访
	private Box box3=Box.createHorizontalBox();
	public text01() {
		super("图书馆");
		this.setSize(600, 400);
		this.setLocation(200, 300);
		this.setVisible(true);
		box1.add(OK);
		box1.add(Box.createHorizontalGlue());//水平的空格可以变化与struct的区别
		box1.add(OK1);
		box2.add(OK2);
		box2.add(Box.createHorizontalStrut(30));//不可以变化大小
		box2.add(OK3);
		box3.add(box1);
		box3.add(box2);
		this.add(box3);
	}
	
	public static void main(String[] args) {
		
		new text01();
	}

}
