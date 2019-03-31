package mylist;

public class App {

	public static void main(String[] args) {
		//（1）创建真实角色
		text2 pro=new text2();
	    //（2）创建代理角色  +真实角色引用
		Thread proxy=new Thread (pro);
	    // （3）调用。start（） 启动线程
		proxy.start();
		for(int i=0;i<2;i++)
			{
					System.out.println("一边敲......");
			}
		}

}
