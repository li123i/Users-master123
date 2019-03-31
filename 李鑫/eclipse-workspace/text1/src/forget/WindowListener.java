package forget;

public interface WindowListener {
	//只有常量和抽象方法
	
}
static Login fr = new Login("测试Panel");  
public static void main(String[] args){  
      
    fr.setSize(500, 500);  
    fr.setLocation(500, 300);  
    fr.setBackground();  
    fr.setLayout();  
      
    Button button = new Button("点击我");  
    button.setSize(50, 25);  
    //button.setBorderPainted(false);  
    button.setLocation(50, 50);  
    button.addActionListener(new ActionListener(){  
        //单击按钮执行的方法  
        public void actionPerformed(ActionEvent e) {  
            closeThis();  
            //创建新的窗口  
            JFrame frame = new JFrame("新窗口");  
            //设置在屏幕的位置  
            frame.setLocation(100,50);  
//          窗体大小  
            frame.setSize(500,500);  
//          显示窗体  
            frame.setVisible(true);  
        }  
          
    }); 