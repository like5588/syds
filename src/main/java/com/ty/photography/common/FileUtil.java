package com.ty.photography.common;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class FileUtil {
	static Logger log = LoggerFactory.getLogger(FileUtil.class);

	public static boolean fileExists(String _sPathFileName) {
		File file = new File(formatPath(_sPathFileName));
		return file.exists();
	}

	public static String formatPath(String _sPath) {
		String path = _sPath.replace('/', File.separatorChar);
		path = path.replace(File.separator + File.separator, File.separator);
		return path;
	}

	/**
	 * 缩略图
	 * 
	 * @param filePath
	 * @param fileTar
	 * @throws Exception
	 */
	public void thumbnail(String filePath, String fileTar) throws Exception {
		int tarWidth = 120;
		int tarHeight = 80;
		float a;
		float b;
		// 取得原图
		File sFile = new File(filePath);
		Image src = javax.imageio.ImageIO.read(sFile);
		// 获取图片的高度宽度
		float wideth = src.getWidth(null);
		float height = src.getHeight(null);
		// 等比压缩
		a = wideth / tarWidth;
		b = height / tarHeight;
		if (a > b) {
			tarHeight = (int) (height * tarWidth / wideth);
		}
		if (a < b) {
			tarWidth = (int) (wideth * tarHeight / height);
		}

		String fileP = fileTar.substring(0, fileTar.lastIndexOf("/") + 1);
		if (!fileExists(fileP)) {
			File newPath = new File(fileP);
			newPath.mkdir();
		}
		// 进行压缩
		BufferedImage tag = new BufferedImage(tarWidth, tarHeight,
				BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(src, 0, 0, tarWidth, tarHeight, null);
		FileOutputStream out = new FileOutputStream(fileTar);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(tag);
		out.close();
	}

	/**
	 * 图片压缩
	 * 
	 * @param srcFilePath
	 * @param descFilePath
	 * @return
	 */
	public static String compressPic(String srcFilePath, String descFilePath) {
		File file = null;
		BufferedImage src = null;
		FileOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;

		// 指定写图片的方式为 jpg
		imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
		imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(
				null);
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
		// 这里指定压缩的程度，参数qality是取值0~1范围内，
		imgWriteParams.setCompressionQuality((float) 0.5);
		imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
				colorModel, colorModel.createCompatibleSampleModel(16, 16)));

		try {
			if (srcFilePath == null || srcFilePath.equalsIgnoreCase("")) {
				return descFilePath;
			} else {
				file = new File(srcFilePath);
				src = ImageIO.read(file);

				imgWrier.reset();
				String filePath = descFilePath.substring(0,
						descFilePath.lastIndexOf("/") + 1);
				if (!fileExists(filePath)) {
					File newPath = new File(filePath);
					newPath.mkdir();
				}
				out = new FileOutputStream(descFilePath);
				// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
				// OutputStream构造
				imgWrier.setOutput(ImageIO.createImageOutputStream(out));
				// 调用write方法，就可以向输入流写图片
				imgWrier.write(null, new IIOImage(src, null, null),
						imgWriteParams);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			log.error("---compressPic has error---", e);
			return descFilePath;
		}
		return descFilePath;
	}

	/**
	 * 复制文件
	 * 
	 * @param _sSrcFile
	 * @param _sDstFile
	 * @return
	 * @throws IOException
	 */
	public static boolean copyFile(String _sSrcFile, String _sDstFile)
			throws IOException {
		boolean bResult = false;
		try {
			FileUtils.copyFile(new File(formatPath(_sSrcFile)), new File(
					formatPath(_sDstFile)));
			bResult = true;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return bResult;
	}

	/**
	 * 根据尺寸图片居中裁剪
	 * @param src
	 * @param dest
	 * @param w
	 * @param h
	 * @throws IOException
	 */
	public static void cutCenterImage(String src, String dest, int w, int h)
			throws IOException {
		//获取图片类型
		String type = src.substring(src.lastIndexOf(".")+1);
		Iterator iterator = ImageIO.getImageReadersByFormatName(type);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		int iw=0;
		int ih=0;
		//判断裁剪的尺寸是否比原图大
		if(reader.getWidth(imageIndex) > w){
			iw=reader.getWidth(imageIndex) - w;
		}
		if(reader.getHeight(imageIndex) > h){
			ih=reader.getHeight(imageIndex) - h;
		}
		Rectangle rect = new Rectangle(iw / 2,ih / 2, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		String target = dest.substring(0, dest.lastIndexOf("/")+1);
		File dir = new File(target);
		if (!dir.exists())
			dir.mkdir();
		ImageIO.write(bi, "jpg", new File(dest));

	}

	/**
	 * 图片裁剪二分之一
	 * @param src
	 * @param dest
	 * @throws IOException
	 */
	public static void cutHalfImage(String src, String dest) throws IOException {
		Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		int width = reader.getWidth(imageIndex) / 2;
		int height = reader.getHeight(imageIndex) / 2;
		Rectangle rect = new Rectangle(width / 2, height / 2, width, height);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "jpg", new File(dest));
	}

	/**
	 * 图片裁剪通用接口
	 * @param src
	 * @param dest
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @throws IOException
	 */
	public static void cutImage(String src, String dest, int x, int y, int w,
			int h) throws IOException {
		Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "jpg", new File(dest));

	}

	/**
	 * 图片缩放
	 * @param src
	 * @param dest
	 * @param w
	 * @param h
	 * @throws Exception
	 */
	public static void zoomImage(String src, String dest, int w, int h)
			throws Exception {
		double wr = 0, hr = 0;
		File srcFile = new File(src);
		String target = dest.substring(0, dest.lastIndexOf("/")+1);
		File dir = new File(target);
		if (!dir.exists())
			dir.mkdir();
		File destFile = new File(dest);
		BufferedImage bufImg = ImageIO.read(srcFile);
		if(h == 0){
			if(bufImg.getHeight() >= bufImg.getWidth()){
				wr = w * 1.0 / bufImg.getWidth();
				h = w*bufImg.getHeight()/ bufImg.getWidth();
				hr = h * 1.0 / bufImg.getHeight();
			}else{
				h = w;
				hr = h * 1.0 / bufImg.getHeight();
				w = h*bufImg.getWidth()/bufImg.getHeight();
				wr = w * 1.0 / bufImg.getWidth();
			}
		}else{
			if(bufImg.getHeight()*1.0/h*w >= bufImg.getWidth()){
				wr = w * 1.0 / bufImg.getWidth();
				h = w*bufImg.getHeight()/ bufImg.getWidth();
				hr = h * 1.0 / bufImg.getHeight();
			}else{
				w = h*bufImg.getWidth()/ bufImg.getHeight();
				wr = w * 1.0 / bufImg.getWidth();
				hr = h * 1.0 / bufImg.getHeight();
			}
		}
		Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);
		AffineTransformOp ato = new AffineTransformOp(
				AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		try {
			ImageIO.write((BufferedImage) Itemp,
					dest.substring(dest.lastIndexOf(".") + 1), destFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void main(String args[]) throws Exception {
//		String a = "D:/eclipse/pro/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/syds/files/compress/5a540d263b664cbaa22649c13ce1576d.jpg";
//		String target = a.substring(0, a.lastIndexOf("/")+1);
//		System.out.println(target);
		FileUtil.zoomImage(
				"D:/2.jpg", //原图
				"D:/3.jpg", 1000, 500);	//压缩
		FileUtil.cutCenterImage(
				"D:/3.jpg",//压缩
				"D:/4.jpg", 1000, 500);
				//输出结果图
	}

}
