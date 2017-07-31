package com.hnjz.app.jxkh.orgstatistics;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-31
 * ����������
		��Ч����ͳ����Ϣ����
 *
 */
public class StatisticsForm {
	/** ����id */
	private String orgid;
	/** �������� */
	private String orgname;
	/** �û�id */
	private String userid;
	/** �û����� */
	private String username;
	/** �´���� */
	private Integer xd = 0;
	/** ��ɴ��� */
	private Integer wc = 0;
	/** ������� */
	private Integer yqwc = 0;
	/** ���ڰ��� */
	private Integer zzbl = 0;
	/** �������ڰ��� */
	private Integer yqzzbl = 0;
	/** ���*/
	private String seq;
	
	public StatisticsForm(){
		
	}
	
	/**
	 * 
	 * �������ܣ�������ͳ��ʹ�õĹ��캯��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public StatisticsForm(String orgid,String orgname,Integer xd,Integer wc,Integer yqwc,Integer zzbl,Integer yqzzbl){
		this.orgid = orgid;
		this.orgname = orgname;
		this.xd = xd;
		this.wc = wc;
		this.yqwc = yqwc;
		this.zzbl = zzbl;
		this.yqzzbl = yqzzbl;
	}
	/**
	 * 
	 * �������ܣ�����Աͳ��ʹ�õĹ��캯��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public StatisticsForm(String orgid,String orgname,String userid,String username,Integer xd,Integer wc,Integer yqwc,Integer zzbl,Integer yqzzbl){
		this.orgid = orgid;
		this.orgname = orgname;
		this.userid = userid;
		this.username = username;
		this.xd = xd;
		this.wc = wc;
		this.yqwc = yqwc;
		this.zzbl = zzbl;
		this.yqzzbl = yqzzbl;
	}
	
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public Integer getXd() {
		return xd;
	}

	public void setXd(Integer xd) {
		this.xd = xd;
	}

	public Integer getWc() {
		return wc;
	}

	public void setWc(Integer wc) {
		this.wc = wc;
	}

	public Integer getYqwc() {
		return yqwc;
	}

	public void setYqwc(Integer yqwc) {
		this.yqwc = yqwc;
	}

	public Integer getZzbl() {
		return zzbl;
	}

	public void setZzbl(Integer zzbl) {
		this.zzbl = zzbl;
	}

	public Integer getYqzzbl() {
		return yqzzbl;
	}

	public void setYqzzbl(Integer yqzzbl) {
		this.yqzzbl = yqzzbl;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

}