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
 * �ļ�����
 * 
 * 2013-04-02 add[�ļ��и���]
 * 
 * @author zn
 * @version $Id: FileUtil.java, v 0.1 2013-4-2 ����12:39:11 zn Exp $
 */
public class FileUtil {

	/**
	 * ��ȡ�ļ���չ��
	 * 
	 * @param filename
	 *            �ļ���
	 * @return �ļ���չ��
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
	 * ��ȡ������չ�����ļ���
	 * 
	 * @param filename
	 *            �ļ���
	 * @return ������չ�����ļ���
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
	 * �����ļ�
	 * 
	 * @param sourcePath
	 *            Դ�ļ���ַ
	 * @param targetPath
	 *            Ŀ���ļ���ַ
	 * @throws Exception
	 */
	public static void copyFile(String sourcePath, String targetDirPath,
			String targetName) throws Exception {
		File sourceFile = new File(sourcePath);
		if (!sourceFile.exists()) {
			throw new RuntimeException("�ļ�������");
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
	 * �����ļ��е�ָ��Ŀ¼
	 * 
	 * @param sourceDirPath
	 * @param targetDirPath
	 * @throws Exception
	 */
	public static void copyDir(String sourceDirPath, String targetDirPath)
			throws Exception {
		File sourceDirFile = new File(sourceDirPath);
		if (!sourceDirFile.exists()) {
			throw new RuntimeException("�ļ��в�����");
		}
		if (!StringUtils.isNotBlank(targetDirPath)) {
			throw new RuntimeException("Ŀ���ļ��в���Ϊ��");
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
	 * ɾ���ļ�
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
	 * �ݹ�ɾ���ļ���
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
	 * �ļ���С��ʽ
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
		if (dValue > 0) {// �ӵ͵�λ��ߵ�λת��
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

	// �����ļ�
	private static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// �½��ļ����������������л���
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// �½��ļ���������������л���
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// ��������
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// ˢ�´˻���������
			outBuff.flush();
		} finally {
			// �ر���
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
	 * ���ݲ�������Ŀ¼
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
	 * �õ��ļ�·��
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
	 * �������ܣ��ж��ļ��Ƿ���ͼƬ
	
	 * ���������
	
	 * ����ֵ��
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