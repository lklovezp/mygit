/**
 * 
 */
package com.hnjz.quartz;

/**
 * 
 * 
 * @author xuguanghui
 * @version $Id: JobType.java, v 0.1 2013-4-2 上午11:17:22 admi Exp $
 */
public enum JobType {

    SEND_MESSAGE(0,"发短信"),//发短信
    UPLOAD_FILE(1,"报文件"),//报文件
    SEND_TASK(2,"派任务"),;//派任务
    
    private JobType(Integer code,String text){
        this.code = code;
        this.text = text;
    }
    
    /** 编码 */
    private Integer code;
    /** 文字 */
    private String text;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
