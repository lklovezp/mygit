package com.hnjz.app.work.zx;

import java.util.List;
import java.util.Map;

/**
 * ���������䰲ȫ��form
 * ���ߣ�xb
 * �������ڣ�Mar 31, 2015
 * ����������

 *
 */
public class BlFsaqForm {
	/** ��������code */
	String rwlx;
	/** ����� */
	String jcr;
	String jcrName;
	/** ��¼�� */
	String jlr;
	String jlrName;
	/** ���ʱ�� */
	String jcsj;
	/** ��鿪ʼʱ�� */
	String jcsj1;
	/** ����ֹʱ�� */
	String jcsj2;
	/** ���ص� */
	private String jcdd;
	/** ���ģ��id */
	String jcmbId;
	/** ���ģ��name */
	String jcmbName;
	/** �Ƿ�����ҵ������ҵ�Ĳ�ѯ����ҵģ�壬û����ҵ��ѡ�� */
	Boolean hasIndustry;
	/** ����¼�ļ�map**/
	private List<Map<String, String>> jcjlFileMap;
	/** ��챨���ļ�map**//*
	Map<String, String> jcbgFileMap;*/
	/** ������**/
	private String jcjl;
	/** �Ƿ���м�飨���м����߼�飬�����м����ṩ���ģ���ļ������أ� */
	private String isexecchecklist;
	/** ���ģ���ļ�id */
	String jcmbFileId;
	/** ��ҵ */
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