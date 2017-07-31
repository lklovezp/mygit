package com.hnjz.app.common;

import com.hnjz.common.util.StringUtil;


/**
 * 
 * ���ߣ�renzhengquan
 * 
 * ���������������ִ���������form
 */ 

public class TaskandlawobjForm{
	
	private String id;
	/** �����ʶ */
	private String taskid;
	/** ִ���������� */
	private String lawobjtype;
	/** ִ�������ʶ */
	private String lawobjid;
	/** ִ������ */
	private String lawobjname;
	/** ���������� */
	private String regionid;
	/** ��ַ */
	private String address;
	/** ���������� */
	private String fddbr;
	/** ������������ϵ�绰 */
	private String fddbrlxdh;
	/** ���������� */
	private String hbfzr;
	/** ������������ϵ�绰 */
	private String hbfzrlxdh;
	/** ר��������id  **/
	private String newtaskid;
	
	
	public TaskandlawobjForm(){
		
	}

	public TaskandlawobjForm(String id,String lawobjid,String lawobjname,String regionid,String address,String fddbr,String fddbrlxdh,String hbfzr,String hbfzrlxdh,String newtaskid){
		this.id = id;
		this.lawobjid = lawobjid;
		this.lawobjname = lawobjname;
		this.regionid = StringUtil.isBlank(regionid)?"":regionid;
		this.address = StringUtil.isBlank(address)?"":address;
		this.fddbr = StringUtil.isBlank(fddbr)?"":fddbr;
		this.fddbrlxdh = StringUtil.isBlank(fddbrlxdh)?"":fddbrlxdh;
		this.hbfzr = StringUtil.isBlank(hbfzr)?"":hbfzr;
		this.hbfzrlxdh = StringUtil.isBlank(hbfzrlxdh)?"":hbfzrlxdh;
		this.newtaskid = StringUtil.isBlank(newtaskid)?"":newtaskid;
	}
	
	public void setTaskid(String taskid){
		this.taskid = taskid;
	}
	public String getTaskid(){
		return taskid;
	}
	public void setLawobjtype(String lawobjtype){
		this.lawobjtype = lawobjtype;
	}
	public String getLawobjtype(){
		return lawobjtype;
	}
	public void setLawobjid(String lawobjid){
		this.lawobjid = lawobjid;
	}
	public String getLawobjid(){
		return lawobjid;
	}
	public void setLawobjname(String lawobjname){
		this.lawobjname = lawobjname;
	}
	public String getLawobjname(){
		return lawobjname;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return address;
	}
	public String getRegionid() {
		return regionid;
	}
	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getFddbr() {
		return fddbr;
	}

	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}

	public String getFddbrlxdh() {
		return fddbrlxdh;
	}

	public void setFddbrlxdh(String fddbrlxdh) {
		this.fddbrlxdh = fddbrlxdh;
	}

	public String getHbfzr() {
		return hbfzr;
	}

	public void setHbfzr(String hbfzr) {
		this.hbfzr = hbfzr;
	}

	public String getHbfzrlxdh() {
		return hbfzrlxdh;
	}

	public void setHbfzrlxdh(String hbfzrlxdh) {
		this.hbfzrlxdh = hbfzrlxdh;
	}

	public String getNewtaskid() {
		return newtaskid;
	}

	public void setNewtaskid(String newtaskid) {
		this.newtaskid = newtaskid;
	}
}
