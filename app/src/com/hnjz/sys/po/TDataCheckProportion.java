package com.hnjz.sys.po;

/**
 * 
 * ���ߣ�ʱ�ﺮ
 * �������ڣ�2015-12-16
 * ����������������ʵ����
 *
 */
public class TDataCheckProportion extends BaseObject {

	private static final long serialVersionUID = 1517474713052732108L;

	private String year;	//���

	private String quarter;		//����
	
	private Integer proportion;	//������

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public Integer getProportion() {
		return proportion;
	}

	public void setProportion(Integer proportion) {
		this.proportion = proportion;
	}

}