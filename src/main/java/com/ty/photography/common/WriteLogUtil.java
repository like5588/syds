package com.ty.photography.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

public class WriteLogUtil {
	
	private static WriteLogUtil instance = new WriteLogUtil();
	private static File logFile;
	private static FileChannel fcOut;
	
	private WriteLogUtil(){
		init();
	}
	
	private void init(){
		FileOutputStream out = null;
		logFile = new File(CommonUtils.parseProperties("image_praise_file"));
		try {
			if(!logFile.exists()){
				logFile.createNewFile();
			}
			// 获取源文件和目标文件的输入输出流
			out = new FileOutputStream(logFile);
			// 获取输出通道
			fcOut = out.getChannel();
		} catch (Exception e) {
			if (out != null) {
				try {
					out.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public static WriteLogUtil getInstance(){
		return instance;
	}

	public void writeLog(String info) {
		try {
			if(!info.endsWith("\n")){
				info = info+"\n";
			}
			if(!logFile.exists()){
				init();
			}
			StringBuilder logs = new StringBuilder();
			logs.append(CommonUtils.timeToString(new Date())).append("  ------  ").append(info);
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.put(info.getBytes());
			// flip方法让缓冲区可以将新读入的数据写入另一个通道
			buffer.flip();
			fcOut.write(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
