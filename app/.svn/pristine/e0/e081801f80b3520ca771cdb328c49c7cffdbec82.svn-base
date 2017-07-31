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
 * 作者：zhangshaowei
 * 生成日期：2014-8-27
 * 功能描述：FTP服务器类
 *
 */
public class FtpUtil{
	// 服务器实例
	static FtpServer server = null;
	public static String ftpuser;
	public static String ftppass;
	public static int ftpport;
	/**
	 * 函数介绍：启动ftp服务器
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public static void startServer() {

		FtpServerFactory serverFactory = new FtpServerFactory();
		ListenerFactory factory = new ListenerFactory();
		factory.setPort(ftpport == 0 ? 21 : ftpport);
		// 替换默认监听器
		serverFactory.addListener("default", factory.createListener());

		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
		// 获取配置文件路径
		String propPath = FtpUtil.class.getResource("").getPath();
		propPath = propPath.substring(1, propPath.indexOf("WEB-INF/classes")) + "WEB-INF/classes/";
		propPath = propPath.replaceAll("%20", " ");
		userManagerFactory.setFile(new File(propPath + "ftp.properties"));
		
		userManagerFactory.setPasswordEncryptor(new PasswordEncryptor() {
			public String encrypt(String pwd) {
				return null;
			}
			// 匹配用户输入的密码
			public boolean matches(String passwordToCheck, String storedPassword) {
				if (passwordToCheck.equals(storedPassword)){
					return true;
				}
				return false;
			}
		});
		serverFactory.setUserManager(userManagerFactory.createUserManager());
		// 创建服务实例
		server = serverFactory.createServer();

		try {
			// 启动服务
			server.start();
		} catch (FtpException e) {
			e.printStackTrace();
		}
	}
}