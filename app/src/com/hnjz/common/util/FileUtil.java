package com.hnjz.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

import com.hnjz.common.upload.FileUpDownUtil;

/**
 * 文件管理
 * 
 * 2013-04-02 add[文件夹复制]
 * 
 * @author zn
 * @version $Id: FileUtil.java, v 0.1 2013-4-2 上午12:39:11 zn Exp $
 */
public class FileUtil {

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 *            文件名
	 * @return 文件扩展名
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return "";
	}

	/**
	 * 获取不带扩展名的文件名
	 * 
	 * @param filename
	 *            文件名
	 * @return 不带扩展名的文件名
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	/**
	 * 复制文件
	 * 
	 * @param sourcePath
	 *            源文件地址
	 * @param targetPath
	 *            目标文件地址
	 * @throws Exception
	 */
	public static void copyFile(String sourcePath, String targetDirPath,
			String targetName) throws Exception {
		File sourceFile = new File(sourcePath);
		if (!sourceFile.exists()) {
			throw new RuntimeException("文件不存在");
		}
		File targetDir = new File(targetDirPath);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		File targetFile = new File(targetDirPath + File.separator + targetName);
		if (!targetFile.exists()) {
			targetFile.createNewFile();
		}
		copyFile(sourceFile, targetFile);
	}

	/**
	 * 复制文件夹到指定目录
	 * 
	 * @param sourceDirPath
	 * @param targetDirPath
	 * @throws Exception
	 */
	public static void copyDir(String sourceDirPath, String targetDirPath)
			throws Exception {
		File sourceDirFile = new File(sourceDirPath);
		if (!sourceDirFile.exists()) {
			throw new RuntimeException("文件夹不存在");
		}
		if (!StringUtils.isNotBlank(targetDirPath)) {
			throw new RuntimeException("目标文件夹不能为空");
		}
		File targetDir = new File(targetDirPath);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		File[] files = sourceDirFile.listFiles();
		File targetFile = null;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				String path = createDir(targetDirPath, files[i].getName());
				copyDir(sourceDirPath.concat(File.separator).concat(
						files[i].getName()), path);
			} else {
				targetFile = new File(targetDirPath.concat(File.separator)
						.concat(files[i].getName()));
				copyFile(files[i], targetFile);
			}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static void removeFile(String path) throws Exception {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * 递归删除文件夹
	 * @param path
	 * @throws Exception
	 */
	public static void removeFolder(String path) throws Exception {
		File file = new File(path);
		if (file.isDirectory()){
			File[] ff = file.listFiles();
			for (File f : ff) {
				removeFolder(f.getPath());
				file.delete();
			}
		} else {
			file.delete();
		}
		file.delete();
	}

	/**
	 * 文件大小格式
	 * 
	 * @param value
	 * @param source
	 * @param target
	 * @return
	 */
	public static Long sizeFormat(Long value, FileSizeEnum source,
			FileSizeEnum target) {
		if (null == value) {
			return 0l;
		}
		Double result = 0D;
		int dValue = target.getLevel() - source.getLevel();
		if (dValue > 0) {// 从低单位向高单位转换
			result = Math.ceil(value.doubleValue()
					/ (Math.abs(dValue) * target.unit));
		} else {
			result = Math.ceil(value.doubleValue() * Math.abs(dValue)
					* target.unit);
		}
		return result.longValue();
	}

	public static String sizeFormat(Long size) {
		String value = "";
		long byteLimit = 1024, kbLimit = 1048576, mbLimit = 1073741824;
		
		if (size < byteLimit) {
			value = size + "b";
		} else if (size < kbLimit) {
			value = String.valueOf(((double)Math.round(((double) size / (double) byteLimit) * 10) / 10)) + "KB";
		} else if (size < mbLimit) {
			value = String.valueOf(((double)Math.round(((double) size / (double) kbLimit) * 10) / 10)) + "MB";
		} else {
			value = String.valueOf(((double)((double) size / (double) mbLimit) * 10) / 10) + "GB";
		}
		return value;
	}

	// 复制文件
	private static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	public enum FileSizeEnum {
		B("b", 0, 1024), KB("kb", 1, 1024), M("m", 2, 1024), G("g", 3, 1024);
		private String code;
		private int level;
		private int unit;

		private FileSizeEnum(String code, int level, int unit) {
			this.code = code;
			this.level = level;
			this.unit = unit;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getUnit() {
			return unit;
		}

		public void setUnit(int unit) {
			this.unit = unit;
		}
	}

	/**
	 * 根据参数创建目录
	 * 
	 * @param path
	 */
	public static String createDir(String... path) {
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < path.length; i++) {
			strBuffer.append(path[i]).append(File.separator);
		}
		File dirFile = new File(strBuffer.toString());
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		return strBuffer.toString();
	}

	/**
	 * 得到文件路径
	 * 
	 * @param dirPath
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static String getRealPath(String... name) throws Exception {
		StringBuffer path = new StringBuffer(FileUpDownUtil.path);
		for (int i = 0; i < name.length; i++) {
			path.append(File.separator);
			path.append(name[i]);
		}
		return path.toString();
	}

	/**
	 * 
	 * 函数介绍：判断文件是否是图片
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public static boolean isImage(String filename){
		String[] imageArray = {"bmp", "gif", "jpe", "jpeg", "jpg", "png", "ico"} ;
		String extensionName = getExtensionName(filename);
		for(String str : imageArray){
			if(str.equals(extensionName.toLowerCase())){
				return true;
			}
		}
		return false;
	}
}
