package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_DIRANDTASKTYPE ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:04 CST 2015
 * ����������ִ���ļ�Ŀ¼���������͹������������׼���׶�ѡ��ʱʹ�ã�
 */ 

@SuppressWarnings("serial")
public class TDataDirandtasktype extends BaseObject {
	/**  */
	private String dirid;
	/**  */
	private String tasktypeid;

	public void setDirid(String dirid){
		this.dirid = dirid;
	}
	public String getDirid(){
		return dirid;
	}
	public void setTasktypeid(String tasktypeid){
		this.tasktypeid = tasktypeid;
	}
	public String getTasktypeid(){
		return tasktypeid;
	}
}
