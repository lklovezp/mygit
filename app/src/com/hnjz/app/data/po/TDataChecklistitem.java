package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_CHECKLISTITEM ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:04 CST 2015
 * ������������鵥�����
 */ 

@SuppressWarnings("serial")
public class TDataChecklistitem extends BaseObject {
	/**  */
	private TDataChecklisttemplate template;
	/** ��������� */
	private String contents;
	/** ������� */
	private String code;
	/** ��������ݵ�λ���������� */
	private String contentsunit;
	/** ��������
0 ���
1 ��ѡ
2 ��ѡ */
	private String inputtype;
	/** ������һ����ʾY/N */
	private String isanswernewline;
	/** ȡִ���������Ϣ */
	private String getlawobjvalue;
	/** ��ע�Ƿ���� */
	private String isrequired;

	public void setTemplate(TDataChecklisttemplate template){
		this.template = template;
	}
	public TDataChecklisttemplate getTemplate(){
		return template;
	}
	public void setContents(String contents){
		this.contents = contents;
	}
	public String getContents(){
		return contents;
	}
	public void setContentsunit(String contentsunit){
		this.contentsunit = contentsunit;
	}
	public String getContentsunit(){
		return contentsunit;
	}
	public void setInputtype(String inputtype){
		this.inputtype = inputtype;
	}
	public String getInputtype(){
		return inputtype;
	}
	public void setIsanswernewline(String isanswernewline){
		this.isanswernewline = isanswernewline;
	}
	public String getIsanswernewline(){
		return isanswernewline;
	}
	public void setGetlawobjvalue(String getlawobjvalue){
		this.getlawobjvalue = getlawobjvalue;
	}
	public String getGetlawobjvalue(){
		return getlawobjvalue;
	}
	public void setIsrequired(String isrequired){
		this.isrequired = isrequired;
	}
	public String getIsrequired(){
		return isrequired;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
