/**
 * 
 */
package com.hnjz.quartz;

/**
 * 
 * 
 * @author xuguanghui
 * @version $Id: JobType.java, v 0.1 2013-4-2 ����11:17:22 admi Exp $
 */
public enum JobType {

    SEND_MESSAGE(0,"������"),//������
    UPLOAD_FILE(1,"���ļ�"),//���ļ�
    SEND_TASK(2,"������"),;//������
    
    private JobType(Integer code,String text){
        this.code = code;
        this.text = text;
    }
    
    /** ���� */
    private Integer code;
    /** ���� */
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