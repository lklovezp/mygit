package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_TASKTYPE ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * �����������������ͱ�
 */ 

@SuppressWarnings("serial")
public class TDataTasktype extends BaseObject {
	/** ��� <br>
	10	 �ֳ���� <br>
	11 	��Ⱥ˲� <br>
	12	 �󶽲� <br>
	13 	�ŷ�Ͷ�� <br>
	14	 ��������֤��� <br>
	15	 ר���ж� <br>
	16 	Υ���������� <br>
	17 	�������� <br>
	18 	���ټ�� <br>
	19 	�Զ���� <br>
	20	 Σ�շ��� <br>
	21 	Σ�ջ�ѧƷ <br>
	22 	���䰲ȫ <br>
	23 	��Ⱦ�¹��ֳ����� <br>
	
	@see com.hnjz.app.work.worktype.WorkTypeCode
	 */
	private String code;
	/** ���� */
	private String name;
	/** ������ID */
	private String pid;
	/** ����ģ�� */
	private String dotemplateid;

	public void setCode(String code){
		this.code = code;
	}
	public String getCode(){
		return code;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setPid(String pid){
		this.pid = pid;
	}
	public String getPid(){
		return pid;
	}
	public void setDotemplateid(String dotemplateid){
		this.dotemplateid = dotemplateid;
	}
	public String getDotemplateid(){
		return dotemplateid;
	}
}
