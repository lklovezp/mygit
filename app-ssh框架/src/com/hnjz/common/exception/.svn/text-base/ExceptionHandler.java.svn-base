package com.hnjz.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * Spring MVC异常捕捉
 * @author mateng
 * @version $Id: ExceptionHandler.java, v 0.1 2013-5-15 下午04:59:27 lenovo Exp $
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    /**日志*/
    private static final Log log = LogFactory.getLog(ExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        log.debug("错误：", ex);
        //上传文件过大
        if (ex instanceof MaxUploadSizeExceededException) {
            //MaxUploadSizeExceededException new_name = (MaxUploadSizeExceededException) ex;
            //long size = new_name.getMaxUploadSize();
            //throw ex;
        }
        return new ModelAndView("exception");
    }

}