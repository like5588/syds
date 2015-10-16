package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipTest {
	
	public static void main(String args[]) throws IOException{
		
		File dir = new File("D:/eclipse/pro/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/"
				+ "wtpwebapps/syds/files/compress/2015-08-05");
		File f = new File("D:/eclipse/pro/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/"
				+ "wtpwebapps/syds/files/compress/aaa.zip");
		if(!f.exists()){
			f.createNewFile();
		}
		OutputStream out = new FileOutputStream(f);
		ZipOutputStream zos = new ZipOutputStream(out);
		File[] files = dir.listFiles();
		int i = 0;
		for (File file : files) {
			zos.putNextEntry(new ZipEntry(++i+file.getName().substring(file.getName().lastIndexOf("."))));     
			 FileInputStream fis = new FileInputStream(file);     
			byte[] buffer = new byte[1024];     
			int r = 0;     
			while ((r = fis.read(buffer)) != -1) {     
					zos.write(buffer, 0, r);     
				}     
				fis.close();   
			System.out.println(file.getName() + " is OK.");
		}
		zos.close();
		out.close();
	}

}
