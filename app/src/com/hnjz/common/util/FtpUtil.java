package com.hnjz.common.util;

import java.io.File;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

import com.hnjz.Sys;

/**
 * 
 * ���ߣ�zhangshaowei
 * �������ڣ�2014-8-27
 * ����������FTP��������
 *
 */
public class FtpUtil{
	// ������ʵ��
	static FtpServer server = null;
	public static String ftpuser;
	public static String ftppass;
	public static int ftpport;
	/**
	 * �������ܣ�����ftp������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public static void startServer() {

		FtpServerFactory serverFactory = new FtpServerFactory();
		ListenerFactory factory = new ListenerFactory();
		factory.setPort(ftpport == 0 ? 21 : ftpport);
		// �滻Ĭ�ϼ�����
		serverFactory.addListener("default", factory.createListener());

		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
		// ��ȡ�����ļ�·��
		String propPath = FtpUtil.class.getResource("").getPath();
		propPath = propPath.substring(1, propPath.indexOf("WEB-INF/classes")) + "WEB-INF/classes/";
		propPath = propPath.replaceAll("%20", " ");
		userManagerFactory.setFile(new File(propPath + "ftp.properties"));
		
		userManagerFactory.setPasswordEncryptor(new PasswordEncryptor() {
			public String encrypt(String pwd) {
				return null;
			}
			// ƥ���û����������
			public boolean matches(String passwordToCheck, String storedPassword) {
				if (passwordToCheck.equals(storedPassword)){
					return true;
				}
				return false;
			}
		});
		serverFactory.setUserManager(userManagerFactory.createUserManager());
		// ��������ʵ��
		server = serverFactory.createServer();

		try {
			// ��������
			server.start();
		} catch (FtpException e) {
			e.printStackTrace();
		}
	}
}