package com.hnjz.app.jxkh.orgstatistics;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-31
 * ����������
		��Ч����ͳ����Ϣ����
 *
 */
public class StatisticsTaskInfoForm {
	/** id **/
	private String id;
	/** �������� **/
	private String workname;
	/** �������� **/
	private String tasktypename;
	/** ִ������ **/
	private String lawobjname;
	/** ������ **/
	private String regionname;
	/** �ɷ��� **/
	private String pfr;
	/** Ҫ�����ʱ�� **/
	private String yqwcsx;
	/** ������Ա **/
	private String zbry;
	/** ����״̬ **/
	private String rwzt;
	/** ��ǰ��������*/
	private String dqdclr;
	/** �鵵ʱ��*/
	private String gdsj;
	/** ���*/
	private String seq;
	
	public StatisticsTaskInfoForm(){
		
	}
	
	public StatisticsTaskInfoForm(String id,String workname,String tasktypename,String lawobjname,String regionname,String pfr,String yqwcsx,String zbry,String rwzt,String dqdclr,String gdsj,String seq){
		this.id =  id;
		this.workname = workname;
		this.tasktypename = tasktypename;
		this.lawobjname = lawobjname;
		this.regionname = regionname;
		this.pfr = pfr;
		this.yqwcsx = yqwcsx;
		this.zbry = zbry;
		this.rwzt = rwzt;
		this.dqdclr = dqdclr;
		this.gdsj = gdsj;
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkname() {
		return workname;
	}

	public void setWorkname(String workname) {
		this.workname = workname;
	}

	public String getTasktypename() {
		return tasktypename;
	}

	public void setTasktypename(String tasktypename) {
		this.tasktypename = tasktypename;
	}

	public String getLawobjname() {
		return lawobjname;
	}

	public void setLawobjname(String lawobjname) {
		this.lawobjname = lawobjname;
	}

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getPfr() {
		return pfr;
	}

	public void setPfr(String pfr) {
		this.pfr = pfr;
	}

	public String getYqwcsx() {
		return yqwcsx;
	}

	public void setYqwcsx(String yqwcsx) {
		this.yqwcsx = yqwcsx;
	}

	public String getZbry() {
		return zbry;
	}

	public void setZbry(String zbry) {
		this.zbry = zbry;
	}

	public String getRwzt() {
		return rwzt;
	}

	public void setRwzt(String rwzt) {
		this.rwzt = rwzt;
	}

	public String getDqdclr() {
		return dqdclr;
	}

	public void setDqdclr(String dqdclr) {
		this.dqdclr = dqdclr;
	}

	public String getGdsj() {
		return gdsj;
	}

	public void setGdsj(String gdsj) {
		this.gdsj = gdsj;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	

}