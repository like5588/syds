package com.ty.photography.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 
 * @author liyan
 *
 */
public class UrlUtil {
	
	public static String doHttpGet(String url){
		StringBuilder result = new StringBuilder();
		try {
			URL u = new URL(url);
			HttpURLConnection uc = (HttpURLConnection)u.openConnection();
			int code = uc.getResponseCode();
			if(code == 200){
				InputStream in = new BufferedInputStream(uc.getInputStream());
				Reader r = new InputStreamReader(in);
				int c;
				while((c = r.read()) != -1){
					result.append((char)c);
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result.toString());
		
		return result.toString();
	}
	
	/**
	 * 下载图片
	 * @param sourceUrl
	 * @param path
	 * @param targetFileName
	 * @return
	 * @throws Exception
	 */
	public static boolean download(String sourceUrl, String path, String targetFileName)
			throws Exception {
		try {
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdir();
			//构造URL
			URL url = new URL(sourceUrl);
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();
			byte[] bs = new byte[1024];
			int len;
			
			OutputStream os = new FileOutputStream(path+targetFileName+".jpg");
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			String compress = path+targetFileName+".jpg";
			String tmp = compress.replace("compress/", "");
			String thumbnail = compress.replace("compress", "thumbnail");
			FileUtil.zoomImage(compress,tmp, 400, 0);
			FileUtil.cutCenterImage(tmp,thumbnail, 400, 400);
			
			os.close();
			is.close();
			
			File file = new File(compress);
			if(file.exists()==false){
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}
	
	public static boolean downLoadFile(String sourceUrl,String path,String targetFileName) throws ResponseCodeException, Exception{
		try {
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdir();
			//构造URL
			URL url = new URL(sourceUrl);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			int code = con.getResponseCode();
			if(code == 200){
				if(con.getContentType().contains("image/")){
					InputStream is = con.getInputStream();
					byte[] bs = new byte[1024];
					int len;
					OutputStream os = new FileOutputStream(path+targetFileName+".jpg");
					while ((len = is.read(bs)) != -1) {
						os.write(bs, 0, len);
					}
					String compress = path+targetFileName+".jpg";
					String tmp = compress.replace("compress/", "");
					String thumbnail = compress.replace("compress", "thumbnail");
					FileUtil.zoomImage(compress,tmp, 400, 0);
					FileUtil.cutCenterImage(tmp,thumbnail, 400, 400);
					os.close();
					is.close();
					return true;
				}else if(con.getContentType().contains("text/plain")){
					StringBuilder result = new StringBuilder();
					InputStream in = new BufferedInputStream(con.getInputStream());
					Reader r = new InputStreamReader(in);
					int c;
					while((c = r.read()) != -1){
						result.append((char)c);
					}
					String res =  result.toString();
					if(res.contains("\"errcode\":40001")
							|| res.contains("\"errcode\":40014")
							|| res.contains("\"errcode\":42001")){
						return false;
					}else{
						throw new Exception("downLoadFile Exception result:"+res);
					}
				}else{
					throw new Exception("url is not image file Exception");
				}
			}else{
				throw new ResponseCodeException(String.valueOf(code));
			}
		}catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static String doHttpPostJson(String URL,String content) {

        try {
            //创建连接
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Charset","UTF-8");
            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();

            //POST请求
            
            PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(),"utf-8"));  
            out.write(content);
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            
            return sb.toString();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
	
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer sb = new StringBuffer();
	    try {
	        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
	        TrustManager[] tm = {new DefaultTrustManager()};
	        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	        sslContext.init(null, tm, new java.security.SecureRandom());
	        // 从上述SSLContext对象中得到SSLSocketFactory对象
	        SSLSocketFactory ssf = sslContext.getSocketFactory();

	        URL url = new URL(requestUrl);
	        HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
	        httpUrlConn.setSSLSocketFactory(ssf);

	        httpUrlConn.setDoOutput(true);
	        httpUrlConn.setDoInput(true);
	        httpUrlConn.setUseCaches(false);
	        // 设置请求方式（GET/POST）
	        httpUrlConn.setRequestMethod(requestMethod);

	        if ("GET".equalsIgnoreCase(requestMethod))
	          httpUrlConn.connect();

	        // 当有数据需要提交时
	        if (null != outputStr) {
	          OutputStream outputStream = httpUrlConn.getOutputStream();
	          // 注意编码格式，防止中文乱码
	          outputStream.write(outputStr.getBytes("UTF-8"));
	          outputStream.close();
	        }
            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            // 断开连接
            httpUrlConn.disconnect();
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    return sb.toString();
    }
	
    private static class DefaultTrustManager implements X509TrustManager {  
  
        @Override  
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}  
  
        @Override  
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}  
  
        @Override  
        public X509Certificate[] getAcceptedIssuers() {  
            return null;  
        }  
  
    } 

}
