package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * 多次检查结果实体类
 * @author shiqiuhan
 * @created 2016-3-29,上午10:30:24
 */

@SuppressWarnings("serial")
public class TBizMoreCheck extends BaseObject {
	/** id */
	private String id;
	/** 任务*/
	private Work work;
	/** 任务类型编号*/
	private String tasktypecode;
	/** 检查次数 */
	private int times;
	/** 内容 */
	private String content;
	/** 对应生成的文件id */
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

