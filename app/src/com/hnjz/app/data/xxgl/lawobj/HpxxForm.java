package com.hnjz.app.data.xxgl.lawobj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hnjz.app.data.po.TDataLawobjeia;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;

public class HpxxForm {

	private String id;

	/** 执法对象标识 */
	private String pid;

	/** 环评项目名称 */
	private String name;

	/** 环评审批文号 */
	private String docnum1;

	/** 审批时间 */
	private String docnum1date;

	/** 试生产审批文号 */
	private String docnum2;

	/** 审批时间 */
	private String docnum2date;

	/** 延期试生产审批文号 */
	private String docnum3;

	/** 审批时间 */
	private String docnum3date;

	/** 建设项目竣工‘三同时’验收审批文号 */
	private String docnum4;

	/** 审批时间 */
	private String docnum4date;

	/** 清洁生产审批文号 */
	private String docnum5;

	/** 审批时间 */
	private String docnum5date;

	private String isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocnum1() {
		return docnum1;
	}

	public void setDocnum1(String docnum1) {
		this.docnum1 = docnum1;
	}

	public String getDocnum1date() {
		return docnum1date;
	}

	public void setDocnum1date(String docnum1date) {
		this.docnum1date = docnum1date;
	}

	public String getDocnum2() {
		return docnum2;
	}

	public void setDocnum2(String docnum2) {
		this.docnum2 = docnum2;
	}

	public String getDocnum2date() {
		return docnum2date;
	}

	public void setDocnum2date(String docnum2date) {
		this.docnum2date = docnum2date;
	}

	public String getDocnum3() {
		return docnum3;
	}

	public void setDocnum3(String docnum3) {
		this.docnum3 = docnum3;
	}

	public String getDocnum3date() {
		return docnum3date;
	}

	public void setDocnum3date(String docnum3date) {
		this.docnum3date = docnum3date;
	}

	public String getDocnum4() {
		return docnum4;
	}

	public void setDocnum4(String docnum4) {
		this.docnum4 = docnum4;
	}

	public String getDocnum4date() {
		return docnum4date;
	}

	public void setDocnum4date(String docnum4date) {
		this.docnum4date = docnum4date;
	}

	public String getDocnum5() {
		return docnum5;
	}

	public void setDocnum5(String docnum5) {
		this.docnum5 = docnum5;
	}

	public String getDocnum5date() {
		return docnum5date;
	}

	public void setDocnum5date(String docnum5date) {
		this.docnum5date = docnum5date;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	/**
	 * 
	 * 函数介绍：form转化为po对象
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public TDataLawobjeia transToTDataLawobjeia(TDataLawobjeia tDataLawobjeia) {
		try {
			TSysUser user = CtxUtil.getCurUser();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (tDataLawobjeia == null) {
				tDataLawobjeia = new TDataLawobjeia();
				tDataLawobjeia.setId(this.id);
				tDataLawobjeia.setCreateby(user);
				tDataLawobjeia.setCreated(new Date(System.currentTimeMillis()));
			}
			tDataLawobjeia.setPid(pid);
			tDataLawobjeia.setName(name);
			tDataLawobjeia.setDocnum1(docnum1);
			if (StringUtils.isNotBlank(docnum1date)) {
				tDataLawobjeia.setDocnum1date(sdf.parse(docnum1date));
			}
			tDataLawobjeia.setDocnum2(docnum2);
			if (StringUtils.isNotBlank(docnum2date)) {
				tDataLawobjeia.setDocnum2date(sdf.parse(docnum2date));
			}
			tDataLawobjeia.setDocnum3(docnum3);
			if (StringUtils.isNotBlank(docnum3date)) {
				tDataLawobjeia.setDocnum3date(sdf.parse(docnum3date));
			}
			tDataLawobjeia.setDocnum4(docnum4);
			if (StringUtils.isNotBlank(docnum4date)) {
				tDataLawobjeia.setDocnum4date(sdf.parse(docnum4date));
			}
			tDataLawobjeia.setDocnum5(docnum5);
			if (StringUtils.isNotBlank(docnum5date)) {
				tDataLawobjeia.setDocnum5date(sdf.parse(docnum5date));
			}
			tDataLawobjeia.setIsActive("Y");
			tDataLawobjeia.setUpdateby(user);
			tDataLawobjeia.setUpdated(new Date(System.currentTimeMillis()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tDataLawobjeia;
	}
}
