package collection;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.*;
import java.

public class text02 extends Frame {

	private TextField tx=new TextField("input",25);
	//文本框 25为占了多少列
	private TextField tx1=new TextField("pp",25);
	private Label
	//标签
	ColorChooser
	//下拉框
	//多选框
	Checkbox 
	//单选框
	TextArea
	public text02() {
		super("02");
		this.setSize(300, 300);
		this.setVisible(true);
		this.setLayout(new FlowLayout());
		this.add(tx);
		tx1.setEchoChar('*');
		this.add(tx1);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new text02();
	}

}
