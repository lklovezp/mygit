package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_LAWOBJDIC ʵ����
 * ���ߣ�������
 * �������ڣ�Tue Mar 17 16:10:45 CST 2015
 * ����������ִ������ṹ�ֵ�
 */ 

@SuppressWarnings("serial")
public class TDataLawobjdic extends BaseObject {
	/**  */
	private String lawobjtypeid;
	/** ������Ӣ�ģ� */
	private String colengname;
	/** ���������ģ� */
	private String colchiname;
	/** ���� Y/N */
	private String mandatory;
	/** ���ƣ�������������������ʱָ�������� */
	private String enumname;
	/** ���뷽ʽ���μ����ö�� */
	private String inputtype;
	/** ������Դ���μ����ö�� */
	private String datasource;
	/** �Ƿ�һ����ʾ��������Y/N */
	private String istwoitem;
	/** ��ע */
	private String desc;

	public void setLawobjtypeid(String lawobjtypeid){
		this.lawobjtypeid = lawobjtypeid;
	}
	public String getLawobjtypeid(){
		return lawobjtypeid;
	}
	public void setColengname(String colengname){
		this.colengname = colengname;
	}
	public String getColengname(){
		return colengname;
	}
	public void setColchiname(String colchiname){
		this.colchiname = colchiname;
	}
	public String getColchiname(){
		return colchiname;
	}
	public void setMandatory(String mandatory){
		this.mandatory = mandatory;
	}
	public String getMandatory(){
		return mandatory;
	}
	public void setEnumname(String enumname){
		this.enumname = enumname;
	}
	public String getEnumname(){
		return enumname;
	}
	public void setInputtype(String inputtype){
		this.inputtype = inputtype;
	}
	public String getInputtype(){
		return inputtype;
	}
	public void setDatasource(String datasource){
		this.datasource = datasource;
	}
	public String getDatasource(){
		return datasource;
	}
	public void setIstwoitem(String istwoitem){
		this.istwoitem = istwoitem;
	}
	public String getIstwoitem(){
		return istwoitem;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
}
