package com.hnjz.app.work.zx;

import java.util.List;
import java.util.Map;

/**
 * 办理（信访投诉）form
 * 作者：xb
 * 生成日期：Mar 31, 2015
 * 功能描述：

 *
 */
public class BlXftsForm {
	/** 任务类型code */
	String rwlx;
	/** 检查人 */
	String jcr;
	String jcrName;
	/** 记录人 */
	String jlr;
	String jlrName;
	/** 检查时间 */
	String jcsj;
	/** 检查开始时间 */
	String jcsj1;
	/** 检查截止时间 */
	String jcsj2;
	/** 检查地点 */
	private String jcdd;
	/** 监察模板id */
	String jcmbId;
	/** 监察模板name */
	String jcmbName;
	/** 是否有行业（有行业的查询出行业模板，没有行业的选择） */
	Boolean hasIndustry;
	/** 检查记录文件map**/
	private List<Map<String, String>> jcjlFileMap;
	/** 监察报告文件map**//*
	Map<String, String> jcbgFileMap;*/
	/** 监察结论**/
	private String jcjl;
	/** 是否进行检查（进行检查的走检查，不进行检查的提供监察模板文件的下载） */
	private String isexecchecklist;
	/** 监察模板文件id */
	String jcmbFileId;
	/** 信访投诉来源 */
	String xftsly;
	String xftslyName;
	/** 行业 */
	private String industry;
	public String getRwlx() {
		return rwlx;
	}
	public void setRwlx(String rwlx) {
		this.rwlx = rwlx;
	}
	public String getJcr() {
		return jcr;
	}
	public void setJcr(String jcr) {
		this.jcr = jcr;
	}
	public String getJlr() {
		return jlr;
	}
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	public String getJcsj() {
		return jcsj;
	}
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	public String getJcmbId() {
		return jcmbId;
	}
	public void setJcmbId(String jcmbId) {
		this.jcmbId = jcmbId;
	}
	public String getJcmbName() {
		return jcmbName;
	}
	public void setJcmbName(String jcmbName) {
		this.jcmbName = jcmbName;
	}
	public Boolean getHasIndustry() {
		return hasIndustry;
	}
	public void setHasIndustry(Boolean hasIndustry) {
		this.hasIndustry = hasIndustry;
	}
	public String getJcdd() {
		return jcdd;
	}
	public void setJcdd(String jcdd) {
		this.jcdd = jcdd;
	}
	public List<Map<String, String>> getJcjlFileMap() {
		return jcjlFileMap;
	}
	public void setJcjlFileMap(List<Map<String, String>> jcjlFileMap) {
		this.jcjlFileMap = jcjlFileMap;
	}
	public String getJcjl() {
		return jcjl;
	}
	public void setJcjl(String jcjl) {
		this.jcjl = jcjl;
	}
	public String getIsexecchecklist() {
		return isexecchecklist;
	}
	public void setIsexecchecklist(String isexecchecklist) {
		this.isexecchecklist = isexecchecklist;
	}
	public String getJcmbFileId() {
		return jcmbFileId;
	}
	public void setJcmbFileId(String jcmbFileId) {
		this.jcmbFileId = jcmbFileId;
	}
	public String getXftsly() {
		return xftsly;
	}
	public void setXftsly(String xftsly) {
		this.xftsly = xftsly;
	}
	public String getJcrName() {
		return jcrName;
	}
	public void setJcrName(String jcrName) {
		this.jcrName = jcrName;
	}
	public String getJlrName() {
		return jlrName;
	}
	public void setJlrName(String jlrName) {
		this.jlrName = jlrName;
	}
	public String getXftslyName() {
		return xftslyName;
	}
	public void setXftslyName(String xftslyName) {
		this.xftslyName = xftslyName;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getIndustry() {
		return industry;
	}
	public String getJcsj1() {
		return jcsj1;
	}
	public void setJcsj1(String jcsj1) {
		this.jcsj1 = jcsj1;
	}
	public String getJcsj2() {
		return jcsj2;
	}
	public void setJcsj2(String jcsj2) {
		this.jcsj2 = jcsj2;
	}
	
}
