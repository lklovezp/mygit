package com.hnjz.app.data.po;

import java.util.Date;

import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysUser;

/**
 * T_DATA_LAWDOC ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * ����������ִ���ļ�
 */ 

@SuppressWarnings("serial")
public class TDataLawdoc extends BaseObject {
	/** ִ���������� */
	private String name;
	/** ִ������Ŀ¼��ʶ */
	private String dirid;
	/** �ؼ��� */
	private String keywords;
	/** ������ʶ */
	private String fileid;
	
	
	public TDataLawdoc(){
		
	}
	/**
	 * 
	 * �������ܣ��½�ʱ�Ĺ��캯��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public TDataLawdoc(String name,String dirid,String keywords,String fileid){
		this.name = name;
		this.dirid = dirid;
		this.keywords = keywords;
		this.fileid = fileid;
		TSysUser user = CtxUtil.getCurUser();
		this.setOrderby(0);
		this.setIsActive("N");
		this.setCreateby(user);
		this.setCreated(new Date(System.currentTimeMillis()));
		this.setUpdateby(user);
		this.setUpdated(new Date(System.currentTimeMillis()));
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setDirid(String dirid){
		this.dirid = dirid;
	}
	public String getDirid(){
		return dirid;
	}
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	public String getKeywords(){
		return keywords;
	}
	public void setFileid(String fileid){
		this.fileid = fileid;
	}
	public String getFileid(){
		return fileid;
	}
}
