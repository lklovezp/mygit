package com.hnjz.app.data.po;

import java.util.Date;
import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysUser;

/**
 * T_DATA_TIMERTASK ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * ������������ʱ��������
 */ 

@SuppressWarnings("serial")
public class TDataTimertask extends BaseObject {
	/** �������� */
	private String name;
	/** ��Ҫ���� */
	private String content;
	/** ������Դ */
	private String source;
	/** �����ܼ� */
	private String security;
	/** �����̶� */
	private String emergency;
	/** �Ǽ��� */
	private TSysUser register;
	/** �ɷ��� */
	private TSysUser hander;
	/** ������ */
	private TSysUser accepter;
	/** ��ʾ��� */
	private String opinion;
	/** �Զ��ɷ�����:
1��ʱ��1�Σ�
2����
3���꣨1�Σ� */
	private String type;
	/** �ɷ�Ƶ�Σ�����ʱ��д�� */
	private Integer times;
	/** �����ɷ�ʱ�䣨��ʱ������ʱ��д�� */
	private Date taskstarted;
	/** Ҫ�����ʱ�ޣ���ʱ������ʱ���ݽ����̶ȼ������ */
	private Date taskended;
	/** ִ����������
01	��ҵ��ȾԴ
02	������Ŀ
03	ҽԺ
04	��¯
05	��������
06	����
07	������ֳ
08  ����ҵ
09  ��ʳҵ
10  ��������ҵ
11  ����ҵ*/
	private String lawobjtype;

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return content;
	}
	public void setSource(String source){
		this.source = source;
	}
	public String getSource(){
		return source;
	}
	public void setSecurity(String security){
		this.security = security;
	}
	public String getSecurity(){
		return security;
	}
	public void setEmergency(String emergency){
		this.emergency = emergency;
	}
	public String getEmergency(){
		return emergency;
	}
	public void setRegister(TSysUser register){
		this.register = register;
	}
	public TSysUser getRegister(){
		return register;
	}
	public void setHander(TSysUser hander){
		this.hander = hander;
	}
	public TSysUser getHander(){
		return hander;
	}
	public void setAccepter(TSysUser accepter){
		this.accepter = accepter;
	}
	public TSysUser getAccepter(){
		return accepter;
	}
	public void setOpinion(String opinion){
		this.opinion = opinion;
	}
	public String getOpinion(){
		return opinion;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
	public void setTimes(Integer times){
		this.times = times;
	}
	public Integer getTimes(){
		return times;
	}
	public void setTaskstarted(Date taskstarted){
		this.taskstarted = taskstarted;
	}
	public Date getTaskstarted(){
		return taskstarted;
	}
	public void setTaskended(Date taskended){
		this.taskended = taskended;
	}
	public Date getTaskended(){
		return taskended;
	}
	public void setLawobjtype(String lawobjtype){
		this.lawobjtype = lawobjtype;
	}
	public String getLawobjtype(){
		return lawobjtype;
	}
}
