package test_file;

import java.io.File;
import java.io.FilenameFilter;

public class FlieAccepct implements FilenameFilter{

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(java);//返回以java为结尾的文件
	}

}
