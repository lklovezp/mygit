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
 * Spring MVC�쳣��׽
 * @author mateng
 * @version $Id: ExceptionHandler.java, v 0.1 2013-5-15 ����04:59:27 lenovo Exp $
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    /**��־*/
    private static final Log log = LogFactory.getLog(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        log.debug("����", ex);
        //�ϴ��ļ�����
        if (ex instanceof MaxUploadSizeExceededException) {
            //MaxUploadSizeExceededException new_name = (MaxUploadSizeExceededException) ex;
            //long size = new_name.getMaxUploadSize();
            //throw ex;
        }
        return new ModelAndView("exception");
    }

}