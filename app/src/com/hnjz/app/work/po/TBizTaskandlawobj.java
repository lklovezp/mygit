package com.hnjz.app.work.po;

import java.util.Date;

import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysUser;

/**
 * T_BIZ_TASKANDLAWOBJ 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:13:04 CST 2015
 * 功能描述：任务和执法对象关联
 */ 

@SuppressWarnings("serial")
public class TBizTaskandlawobj extends BaseObject {
	/** 任务标识 */
	private String taskid;
	/** 执法对象类型
01	工业污染源
02	建设项目
03	医院
04	锅炉
05	建筑工地
06	三产
07	畜禽养殖
08  服务业
09  饮食业
10  制造业
11  娱乐业
 */
	private String lawobjtype;
	/** 执法对象标识 */
	private String lawobjid;
	/** 执法对象 */
	private String lawobjname;
	/** 所属行政区 */
	private String regionid;
	/** 地址 */
	private String address;
	/** 法定代表人 */
	private String manager;
	/** 法定代表人联系电话 */
	private String managermobile;
	/** 新的任务标识 */
	private String newtaskid;
	
	/** 被检查人 */
	private String bjcr;
	/** 职务 */
	private String zw;
	/** 职务 */
	private String zwtitle;
	/** 联系电话 */
	private String lxdh;
	/** 需求养殖单位名称  */
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

