/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 将参数统一trim
 * 
 * @author wumi
 * @version $Id: AppFilter.java, v 0.1 Aug 1, 2013 2:55:39 PM wumi Exp $
 */
public class AppFilter implements Filter {

    /**日志*/
    private static final Log    log              = LogFactory.getLog(AppFilter.class);

    private static final String DEFAULT_ENCODING = "GBK";

    private String              encoding;

    
    public void doFilter(ServletRequest sreq, ServletResponse res, FilterChain chain)
                                                                                     throws IOException,
                                                                                     ServletException {
        HttpServletRequest req = (HttpServletRequest) sreq;
        req = new HttpServletRequestWrapperTrim(req);
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, res);
    }

    
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
        if (StringUtils.isBlank(encoding)) {
            encoding = DEFAULT_ENCODING;
        }
        if (log.isDebugEnabled()) {
            log.debug("encoding:" + encoding);
        }
    }

    public void destroy() {
    }

    /**
     * 将参数统一trim，并处理乱码问题：
     * 
     * @author wumi
     * @version $Id: MoHandlerInterceptor.java, v 0.1 Jul 29, 2013 4:35:46 PM wumi Exp $
     */
    private class HttpServletRequestWrapperTrim extends HttpServletRequestWrapper {

        @SuppressWarnings("unchecked")
        public HttpServletRequestWrapperTrim(HttpServletRequest request) {
            super(request);
            if (log.isDebugEnabled()) {
               Enumeration<String> s = request.getHeaderNames();
               while (s.hasMoreElements()) {
                   String o= s.nextElement();
                   log.debug(o+":"+request.getHeader(o));
               }
            }
        }

        /**
         * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
         */
        public String getParameter(String name) {
            return getString(this.getMethod(), super.getParameter(name));
        }

        /**
         * @see javax.servlet.ServletRequestWrapper#getParameterValues(java.lang.String)
         */
        public String[] getParameterValues(String name) {
            String[] re = super.getParameterValues(name);
            if (null == re) {
                return re;
            }
            for (int i = 0; i < re.length; i++) {
                re[i] = getString(this.getMethod(), re[i]);
            }
            return re;
        }

    }

    /**
     * 将参数统一trim
     * 
     * 
     * @param method表单提交的方式
     * @param str 参数值
     * @return
     */
    private String getString(String method, String str) {
        if (StringUtils.equalsIgnoreCase(method, "POST")) {
            return StringUtils.trim(str);
        }
        return str;
    }

}
