package com.ty.photography.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ty.photography.common.CommonUtils;
import com.ty.photography.common.FileUtil;

@WebServlet(urlPatterns = {"/dx/fileUploadServlet","/mh/fileUploadServlet"},asyncSupported = true)
public class FileUploadServlet extends HttpServlet {
	private Logger log = LoggerFactory.getLogger(FileUploadServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int UPLOAD_SUCCSSS=0;    // "上传文件成功！", 
	private static final int UPLOAD_FAILURE=1;    // "上传文件失败！"), 
	private static final int UPLOAD_TYPE_ERROR=2; // "上传文件类型错误！"), 
	private static final int UPLOAD_OVERSIZE=3;   // "上传文件过大！"),
	private static final int UPLOAD_ZEROSIZE=4;   // "上传文件为空！"),
	private static final int UPLOAD_NOTFOUND=5;   // "上传文件路径错误！")

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out=response.getWriter();
//		String rootPath = request.getParameter("rootPath");
		String rootPath = CommonUtils.parseProperties("file_path");  
		
		 //上传操作  
		  FileItemFactory factory = new DiskFileItemFactory();  
		  ServletFileUpload upload = new ServletFileUpload(factory);  
		  upload.setHeaderEncoding("UTF-8");  
		  try{  
		      List<FileItem> items = upload.parseRequest(request);  
		      if(null != items){  
		          Iterator<FileItem> itr = items.iterator();  
		          while(itr.hasNext()){  
		              FileItem item = (FileItem)itr.next();
		              if(item.isFormField()){  
		                 continue;  
		              }else{  
		            	  String dateStr = CommonUtils.dateToString(new Date());
		                  String path="/tmp"+dateStr;
		                  String fileName = generateFileName(item.getName());
		                  File dir = new File(rootPath+path);
		                  if(!dir.exists()){
		                	  dir.mkdir();
		                  }
		                  File savedFile = new File(rootPath+path,fileName);  
		                  item.write(savedFile); 
		                  
		                //缩略图
		      			dir = new File(rootPath+path+"/thumbnail");
		      			if(!dir.exists()){
		      				dir.mkdir();
		      			}
		      			FileUtil.zoomImage(
		      					rootPath+path+"/"+fileName,
		      					rootPath+path+"/thumbnail/"+fileName, 400, 0);
		      			FileUtil.cutCenterImage(
		      					rootPath+path+"/thumbnail/"+fileName,
		      					rootPath+path+"/thumbnail/"+fileName, 400, 400);
		                  //避免重复提交
		      			 request.getSession().setAttribute("saveImage", "OK");
		      			 String thumbnail = CommonUtils.parseProperties("thumbnail_image_url");
		      			 String tmp = thumbnail.substring(0,thumbnail.lastIndexOf("/thumbnail/")); 
		                  out.print("{status:"+UPLOAD_SUCCSSS+",message:'"+tmp+path+"/thumbnail/"+fileName+"'}");
		              }  
		          }  
		      }  
		  }catch(Exception e){  
		      e.printStackTrace();  
		  }
			
	}

	 /** 
     * new文件名= 时间 + 全球唯一编号 
     * @param fileName old文件名 
     * @return new文件名 
     */  
    private String generateFileName(String fileName) {  
        String uuid=CommonUtils.getUUID();  
        int position = fileName.lastIndexOf(".");     
        String extension = fileName.substring(position);     
        return uuid + extension;     
    }  
}
