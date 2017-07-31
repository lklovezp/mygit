package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_TASKUSER ʵ���� ���ߣ������� �������ڣ�Fri Feb 27 15:13:03 CST 2015 ����������������Ա��Ϣ
 */

@SuppressWarnings("serial")
public class TBizTaskuser extends BaseObject {
	/**  */
	private String taskid;
	/** �û���ʶ */
	private String userid;
	/** �û���ʵ���� */
	private String username;
	/**
	 * ��Ա��������죬Э�죬�ȡ� <br>
	 * 00 �Ǽ���<br>
	 * 01 ������<br>
	 * 02 �ɷ��� <br>
	 * 03 ת���ˣ������ˣ� <br>
	 * 04 ������ <br>
	 * 05 ������ <br>
	 * 06 Э���� <br>
	 * 07 ��� <br>
	 * 08 �˻� <br>
	 * 09 �鵵
	 */
	/**
	 * @see com.hnjz.app.work.enums.TaskUserEnum
	 */
	private String type;

	
	public TBizTaskuser(){
		
	}
	
	public TBizTaskuser(String taskid,String userid,String username,String type){
		this.taskid = taskid;
		this.userid = userid;
		this.username = username;
		this.type = type;
	}
	
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}