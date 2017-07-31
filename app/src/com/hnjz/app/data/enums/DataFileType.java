/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.data.enums;

/**
 * ��������
 * @author xuguanghui
 * @version $Id: AccessoryType.java, v 0.1 Jun 17, 2013 10:53:19 AM Administrator Exp $
 */
public enum DataFileType {

    JIANCHAJILUSAOMIAOJIAN("001","����¼ɨ���"),//����¼ɨ���
    XIANCHANGKANCHATU("002","�ֳ�����ʾ��ͼ"),//�ֳ�����ʾ��ͼ
    DIANZIFUJIAN("003","���Ӹ���"),//���Ӹ���
    
    XUNWENBILU("010","ѯ�ʱ�¼"),//ѯ�ʱ�¼
    KANCHABILU("011","�����¼"),//�����¼
    ZHENGJUXINXI("012","֤����Ϣ"),//֤����Ϣ
    TUPIAN("013","ͼƬ����"),//ͼƬ����
    YINPIN("014","��Ƶ����"),//��Ƶ����
    SHIPIN("015","��Ƶ����"),//��Ƶ����
    QITA("016","��������"),//��������
    
    TONGYONGRENWUFUJIAN("020","���Ӹ���");//ͨ�����񸽼�
    private DataFileType(String code, String name) {
        this.code = code;
        this.name = name;
    }
    /**
     * ���ݱ�ŵõ���������
     * 
     * @param code
     * @return
     */
    public static DataFileType getType(String code){
        DataFileType type = null;
        for(DataFileType a :values()){
            if(a.code.equals(code)){
                type = a;
                break;
            }
        }
        return type;
    }
    
    /** ����*/
    private String code;
    /** ���� */
    private String name;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    
}