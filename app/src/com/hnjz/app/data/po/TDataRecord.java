package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_RECORD ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * ��������������ѯ�ʱ�¼����
 */ 

@SuppressWarnings("serial")
public class TDataRecord extends BaseObject {
	/** ���������� */
	private String content;
	/** ��ID */
	private String pid;
	/** ��ʾ���� */
	private String tsnr;
	/** ��������ID */
	private TDataTasktype tasktype;
	/** Υ������ID */
	private TDataIllegaltype wflx;
	/** ����ѯ�����ֱ�� 0���� 1ѯ�� */
	private String kcxwbj;
	/** �Ƿ��ɾ�� */
	private String isdel;
	/** �Ƿ�ǰ�汾Y/N */
	private String iscurver;
	/** �Ƿ��Զ�����Y/N */
	private String iszdjz;
	/** �汾�� */
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
