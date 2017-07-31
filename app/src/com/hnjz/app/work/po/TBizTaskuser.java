package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_TASKUSER 实体类 作者：张少卫 生成日期：Fri Feb 27 15:13:03 CST 2015 功能描述：任务人员信息
 */

@SuppressWarnings("serial")
public class TBizTaskuser extends BaseObject {
	/**  */
	private String taskid;
	/** 用户标识 */
	private String userid;
	/** 用户真实姓名 */
	private String username;
	/**
	 * 人员类别，如主办，协办，等。 <br>
	 * 00 登记人<br>
	 * 01 生成人<br>
	 * 02 派发人 <br>
	 * 03 转派人（下派人） <br>
	 * 04 接受人 <br>
	 * 05 主办人 <br>
	 * 06 协办人 <br>
	 * 07 审核 <br>
	 * 08 退回 <br>
	 * 09 归档
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
