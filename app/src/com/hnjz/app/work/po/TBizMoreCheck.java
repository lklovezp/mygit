package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * ��μ����ʵ����
 * @author shiqiuhan
 * @created 2016-3-29,����10:30:24
 */

@SuppressWarnings("serial")
public class TBizMoreCheck extends BaseObject {
	/** id */
	private String id;
	/** ����*/
	private Work work;
	/** �������ͱ��*/
	private String tasktypecode;
	/** ������ */
	private int times;
	/** ���� */
	private String content;
	/** ��Ӧ���ɵ��ļ�id */
	private String fileid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Work getWork() {
		return work;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	public String getTasktypecode() {
		return tasktypecode;
	}
	public void setTasktypecode(String tasktypecode) {
		this.tasktypecode = tasktypecode;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

}
