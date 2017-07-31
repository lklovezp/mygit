/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.util.FtpUtil;

/**
 * 对应sys.properties中的配置项
 * 
 * @author wumi
 * @version $Id: Sys.java, v 0.1 Feb 18, 2013 10:40:12 AM wumi Exp $
 */
@Component("sys")
public class Sys implements InitializingBean {

	/** 日志 */
	private static final Log log = LogFactory.getLog(Sys.class);

	@Value("#{settings['FILEPATH']}")
	public String filePath;
	@Value("#{settings['FTPUSER']}")
	public String ftpuser;
	@Value("#{settings['FTPPASS']}")
	public String ftppass;
	@Value("#{settings['FTPPORT']}")
	public int ftpport;
	@Value("#{settings['MODE']}")
	public int mode;
	@Value("#{settings['SYSVER']}")
	public String sysVER;
	@Value("#{settings['WEBURL']}")
	public String WEBURL;

	@Override
	public void afterPropertiesSet() throws Exception {
		FileUpDownUtil.path = filePath;
		FtpUtil.ftpuser = ftpuser;
		FtpUtil.ftppass = ftppass;
		FtpUtil.ftpport = ftpport;
		IndexManager.mode = mode;
		IndexManager.sysVer = sysVER;
		IndexManager.webURL = WEBURL;
		if (log.isDebugEnabled()) {
			log.debug("filePath:" + filePath);
		}
	}
}
