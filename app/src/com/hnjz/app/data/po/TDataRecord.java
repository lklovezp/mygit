package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_RECORD 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:15:03 CST 2015
 * 功能描述：勘察询问笔录问题
 */ 

@SuppressWarnings("serial")
public class TDataRecord extends BaseObject {
	/** 问题项内容 */
	private String content;
	/** 父ID */
	private String pid;
	/** 提示内容 */
	private String tsnr;
	/** 任务类型ID */
	private TDataTasktype tasktype;
	/** 违法类型ID */
	private TDataIllegaltype wflx;
	/** 勘察询问区分标记 0勘察 1询问 */
	private String kcxwbj;
	/** 是否可删除 */
	private String isdel;
	/** 是否当前版本Y/N */
	private String iscurver;
	/** 是否自动加载Y/N */
	private String iszdjz;
	/** 版本号 */
	private int vernum;

	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return content;
	}
	public void setPid(String pid){
		this.pid = pid;
	}
	public String getPid(){
		return pid;
	}
	public void setTsnr(String tsnr){
		this.tsnr = tsnr;
	}
	public String getTsnr(){
		return tsnr;
	}
	public void setTasktype(TDataTasktype tasktype){
		this.tasktype = tasktype;
	}
	public TDataTasktype getTasktype(){
		return tasktype;
	}
	public void setWflx(TDataIllegaltype wflx){
		this.wflx = wflx;
	}
	public TDataIllegaltype getWflx(){
		return wflx;
	}
	public void setKcxwbj(String kcxwbj){
		this.kcxwbj = kcxwbj;
	}
	public String getKcxwbj(){
		return kcxwbj;
	}
	public void setIsdel(String isdel){
		this.isdel = isdel;
	}
	public String getIsdel(){
		return isdel;
	}
	public void setIscurver(String iscurver){
		this.iscurver = iscurver;
	}
	public String getIscurver(){
		return iscurver;
	}
	public void setIszdjz(String iszdjz){
		this.iszdjz = iszdjz;
	}
	public String getIszdjz(){
		return iszdjz;
	}
	public void setVernum(int vernum) {
		this.vernum = vernum;
	}
	public int getVernum() {
		return vernum;
	}
}

