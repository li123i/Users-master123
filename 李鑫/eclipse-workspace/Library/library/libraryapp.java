package library;

import java.awt.*;

public class libraryapp extends Frame {

	private Button quit=new Button("按作者名字");
	public libraryapp(){
		super("图书馆检索图书馆");
		setSize(2500,2900);//窗口的大小
	    /*
		 * 设置按钮（GUI）
		 */
		//设置Button按钮
	    add(quit);
	    pack();
	    setVisible(true);//窗口是否可以看见
	}
	
	/*
	 * 设置按钮（GUI）
	 */
	//检索图书
	public static void main(String[] args) {
		libraryapp lib=new libraryapp();	//窗口上显示的文字
		/*
		 * 设置按钮（GUI）
		 */
		//检索图书
		
	}

}
