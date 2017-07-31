package com.hnjz.app.data.po;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hnjz.app.data.xxgl.lawobj.HpxxForm;
import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_LAWOBJEIA ʵ����
 * ���ߣ�������
 * �������ڣ�Sat Feb 28 10:34:13 CST 2015
 * ����������������Ŀ������Ϣ
 */ 

@SuppressWarnings("serial")
public class TDataLawobjeia extends BaseObject {
	/** ִ�������ʶ */
	private String pid;
	/** ������Ŀ���� */
	private String name;
	/** ���������ĺ� */
	private String docnum1;
	/** ����ʱ�� */
	private Date docnum1date;
	/** �����������ĺ� */
	private String docnum2;
	/** ����ʱ�� */
	private Date docnum2date;
	/** ���������������ĺ� */
	private String docnum3;
	/** ����ʱ�� */
	private Date docnum3date;
	/** ������Ŀ��������ͬʱ�����������ĺ� */
	private String docnum4;
	/** ����ʱ�� */
	private Date docnum4date;
	/** ������������ĺ� */
	private String docnum5;
	/** ����ʱ�� */
	private Date docnum5date;

	public void setPid(String pid){
		this.pid = pid;
	}
	public String getPid(){
		return pid;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setDocnum1(String docnum1){
		this.docnum1 = docnum1;
	}
	public String getDocnum1(){
		return docnum1;
	}
	public void setDocnum1date(Date docnum1date){
		this.docnum1date = docnum1date;
	}
	public Date getDocnum1date(){
		return docnum1date;
	}
	public void setDocnum2(String docnum2){
		this.docnum2 = docnum2;
	}
	public String getDocnum2(){
		return docnum2;
	}
	public void setDocnum2date(Date docnum2date){
		this.docnum2date = docnum2date;
	}
	public Date getDocnum2date(){
		return docnum2date;
	}
	public void setDocnum3(String docnum3){
		this.docnum3 = docnum3;
	}
	public String getDocnum3(){
		return docnum3;
	}
	public void setDocnum3date(Date docnum3date){
		this.docnum3date = docnum3date;
	}
	public Date getDocnum3date(){
		return docnum3date;
	}
	public void setDocnum4(String docnum4){
		this.docnum4 = docnum4;
	}
	public String getDocnum4(){
		return docnum4;
	}
	public void setDocnum4date(Date docnum4date){
		this.docnum4date = docnum4date;
	}
	public Date getDocnum4date(){
		return docnum4date;
	}
	public void setDocnum5(String docnum5){
		this.docnum5 = docnum5;
	}
	public String getDocnum5(){
		return docnum5;
	}
	public void setDocnum5date(Date docnum5date){
		this.docnum5date = docnum5date;
	}
	public Date getDocnum5date(){
		return docnum5date;
	}
	
	/**
	 * 
	 * �������ܣ�ת��Ϊ��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public HpxxForm transToHpxxForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HpxxForm hpxxForm = new HpxxForm();
		hpxxForm.setId(this.getId());
		hpxxForm.setPid(this.pid);
		hpxxForm.setName(name);
		hpxxForm.setDocnum1(docnum1);
		if(docnum1date!=null){
			hpxxForm.setDocnum1date(sdf.format(docnum1date));
		}
		hpxxForm.setDocnum2(docnum2);
		if(docnum2date!=null){
			hpxxForm.setDocnum2date(sdf.format(docnum2date));
		}
		hpxxForm.setDocnum3(docnum3);
		if(docnum3date!=null){
			hpxxForm.setDocnum3date(sdf.format(docnum3date));
		}
		hpxxForm.setDocnum4(docnum4);
		if(docnum4date!=null){
			hpxxForm.setDocnum4date(sdf.format(docnum4date));
		}
		hpxxForm.setDocnum5(docnum5);
		if(docnum5date!=null){
			hpxxForm.setDocnum5date(sdf.format(docnum5date));
		}
		hpxxForm.setIsActive(this.getIsActive());
		return hpxxForm;
	}
}
