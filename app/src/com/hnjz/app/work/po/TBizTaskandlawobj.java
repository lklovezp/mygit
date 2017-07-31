package com.hnjz.app.work.po;

import java.util.Date;

import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysUser;

/**
 * T_BIZ_TASKANDLAWOBJ ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:13:04 CST 2015
 * ���������������ִ���������
 */ 

@SuppressWarnings("serial")
public class TBizTaskandlawobj extends BaseObject {
	/** �����ʶ */
	private String taskid;
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
10  ����ҵ
11  ����ҵ
 */
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
	private String manager;
	/** ������������ϵ�绰 */
	private String managermobile;
	/** �µ������ʶ */
	private String newtaskid;
	
	/** ������� */
	private String bjcr;
	/** ְ�� */
	private String zw;
	/** ְ�� */
	private String zwtitle;
	/** ��ϵ�绰 */
	private String lxdh;
	/** ������ֳ��λ����  */
	private String xqyzDwmc;
	
	public TBizTaskandlawobj(){
		
	}
	
	public TBizTaskandlawobj(String taskid,String lawobjtype,
			String lawobjid,String lawobjname,
			String regionid,String address,String manager,
			String managermobile,String bjcr,String zw,String lxdh,String xqyzDwmc, String zwtitle){
		this.taskid = taskid;
		this.lawobjtype = lawobjtype;
		this.lawobjid = lawobjid;
		this.lawobjname = lawobjname;
		this.regionid = regionid;
		this.address = address;
		this.manager = manager;
		this.managermobile = managermobile;
		this.bjcr = bjcr;
		this.zw = zw;
		this.lxdh = lxdh;
		this.xqyzDwmc = xqyzDwmc;
		this.zwtitle = zwtitle;
		this.setIsActive("Y");
		TSysUser user = CtxUtil.getCurUser();
		this.setCreateby(user);
		this.setCreated(new Date(System.currentTimeMillis()));
		this.setUpdateby(user);
		this.setUpdated(new Date(System.currentTimeMillis()));
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
	public void setManager(String manager){
		this.manager = manager;
	}
	public String getManager(){
		return manager;
	}
	public void setManagermobile(String managermobile){
		this.managermobile = managermobile;
	}
	public String getManagermobile(){
		return managermobile;
	}
	public void setNewtaskid(String newtaskid){
		this.newtaskid = newtaskid;
	}
	public String getNewtaskid(){
		return newtaskid;
	}
	public String getRegionid() {
		return regionid;
	}
	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}
	public String getBjcr() {
		return bjcr;
	}
	public void setBjcr(String bjcr) {
		this.bjcr = bjcr;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getXqyzDwmc() {
		return xqyzDwmc;
	}
	public void setXqyzDwmc(String xqyzDwmc) {
		this.xqyzDwmc = xqyzDwmc;
	}

	public void setZwtitle(String zwtitle) {
		this.zwtitle = zwtitle;
	}

	public String getZwtitle() {
		return zwtitle;
	}
	
}
