package test_file;

import java.io.File;

public class dome {

	public static <FileAccepct> void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("C:\\User\\李鑫\\Desktop\\java_file");
		file.getName();
		System.out.println(file.getName());
		System.out.println(file.length());
		System.out.println(file.getAbsolutePath());
		File file1=new File("C:\\User\\李鑫\\Desktop\\java_file\\demo1");
		System.out.println(file1.exists());
		file1.mkdirs();
		//System.out.println(file1.exists());
		FlieAccepct accepct = new FlieAccepct();
		String[] filename =file1.list(accepct);
		for(String str:filename) {
			System.out.println(str);
		}
	}

}
