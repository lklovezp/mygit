package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_LAWOBJTYPETASKTYPE ʵ����
 * ���ߣ�������
 * �������ڣ�Tue Mar 17 18:49:30 CST 2015
 * ����������ִ�����������������ͱ�
 */ 

@SuppressWarnings("serial")
public class TDataLawobjtypetasktype extends BaseObject {
	/** ִ����������
	01	��ҵ��ȾԴ
	02	������Ŀ
	03	ҽԺ
	04	��¯
	05	��������
	06	����
	07	������ֳ 
	08     ����ҵ
	09     ��ʳҵ
	10     ��������ҵ
	11     ����ҵ
	*/
	private String lawobjtype;
	/**  */
	private String tasktypeid;
	
	private String isexecchecklist;

	public void setLawobjtype(String lawobjtype){
		this.lawobjtype = lawobjtype;
	}
	public String getLawobjtype(){
		return lawobjtype;
	}
	public void setTasktypeid(String tasktypeid){
		this.tasktypeid = tasktypeid;
	}
	public String getTasktypeid(){
		return tasktypeid;
	}
	public void setIsexecchecklist(String isexecchecklist) {
		this.isexecchecklist = isexecchecklist;
	}
	public String getIsexecchecklist() {
		return isexecchecklist;
	}
}
