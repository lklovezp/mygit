/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.hnjz.Constants;
import com.hnjz.common.util.FtpUtil;

/**
 * 应用启动时执行
 * 
 * @author wumi
 * @version $Id: StartListener.java, v 0.1 Dec 29, 2011 4:37:26 AM Administrator
 *          Exp $
 */
public class StartListener extends ContextLoaderListener {

	/** 日志 */
	private static final Log log = LogFactory.getLog(StartListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		log.debug("初始化。。。");
		ServletContext scx = event.getServletContext();
		scx.setAttribute("pageSize", Constants.PER_PAGE);
		scx.setAttribute("ctx", scx.getContextPath());
		scx.setAttribute("basePath", scx.getContextPath());
		scx.setAttribute("static", scx.getContextPath().concat("/static"));
		scx.setAttribute("jquery", scx.getContextPath().concat("/static/jquery"));
		scx.setAttribute("app", scx.getContextPath().concat("/static/app"));
		scx.setAttribute("common", scx.getContextPath().concat("/static/common"));
		scx.setAttribute("tabletree4j", scx.getContextPath().concat("/static/tabletree4j"));
		scx.setAttribute("colorbox", scx.getContextPath().concat("/static/colorbox"));
		scx.setAttribute("easyui", scx.getContextPath().concat("/static/easyui"));
		scx.setAttribute("ztree", scx.getContextPath().concat("/static/ztree"));
		scx.setAttribute("ztree_3_5_12", scx.getContextPath().concat("/static/zTree v3.5.12"));
		scx.setAttribute("upload", scx.getContextPath().concat("/static/upload"));
		scx.setAttribute("highcharts", scx.getContextPath().concat("/static/highcharts"));
		scx.setAttribute("jqueryFileUpload", scx.getContextPath().concat("/static/jqueryFileUpload"));
		scx.setAttribute("poshytip", scx.getContextPath().concat("/static/poshytip"));
		scx.setAttribute("easyui_jz", scx.getContextPath().concat("/static/easyui_jz"));
		scx.setAttribute("tinymce", scx.getContextPath().concat("/static/tinymce"));
		scx.setAttribute("ueditor", scx.getContextPath().concat("/static/ueditor"));
		scx.setAttribute("My97DatePicker", scx.getContextPath().concat("/static/My97DatePicker"));
		scx.setAttribute("layer", scx.getContextPath().concat("/static/layer"));
		// 启动ftp服务
		FtpUtil.startServer();
	}

}
