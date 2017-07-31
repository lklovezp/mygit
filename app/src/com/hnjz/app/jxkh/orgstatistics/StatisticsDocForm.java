package com.hnjz.app.jxkh.orgstatistics;

/**
 * 
 * ���ߣ�zhangqingfeng
 * �������ڣ�2016-08-29
 * ��������������¼����ͳ����Ϣ����
 *
 */
public class StatisticsDocForm {
	/** ����id */
	private String orgid;
	/** �������� */
	private String orgname;
	/** �û�id */
	private String userid;
	/** �û����� */
	private String username;
	/** һ�·��� */
	private Integer yyfs = 0;
	/** ���·��� */
	private Integer eyfs = 0;
	/** ���·��� */
	private Integer myfs = 0;
	/** ���·��� */
	private Integer syfs = 0;
	/** ���·��� */
	private Integer wyfs = 0;
	/** ���·��� */
	private Integer lyfs = 0;
	/** ���·��� */
	private Integer qyfs = 0;
	/** ���·��� */
	private Integer byfs = 0;
	/** ���·��� */
	private Integer jyfs = 0;
	/** ʮ�·��� */
	private Integer oyfs = 0;
	/** ʮһ�·��� */
	private Integer ayfs = 0;
	/** ʮ���·��� */
	private Integer dyfs = 0;
	/** һ����� */
	private Integer ynfs = 0;
	/** ���*/
	private String seq;
	
	public StatisticsDocForm(){
		
	}
	
	/**
	 * 
	 * �������ܣ�������ͳ��ʹ�õĹ��캯��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public StatisticsDocForm(String orgid,String orgname,Integer yyfs,Integer eyfs,Integer myfs,Integer syfs,Integer wyfs,Integer lyfs,Integer qyfs,Integer byfs,Integer jyfs,Integer oyfs,Integer ayfs,Integer dyfs,Integer ynfs){
		this.orgid = orgid;
		this.orgname = orgname;
		this.yyfs = yyfs;
		this.eyfs = eyfs;
		this.myfs = myfs;
		this.syfs = syfs;
		this.wyfs = wyfs;
		this.lyfs = lyfs;
		this.qyfs = qyfs;
		this.byfs = byfs;
		this.jyfs = jyfs;
		this.oyfs = oyfs;
		this.ayfs = ayfs;
		this.dyfs = dyfs;
		this.ynfs = ynfs;
	}
	/**
	 * 
	 * �������ܣ�����Աͳ��ʹ�õĹ��캯��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public StatisticsDocForm(String orgid,String orgname,String userid,String username,Integer yyfs,Integer eyfs,Integer myfs,Integer syfs,Integer wyfs,Integer lyfs,Integer qyfs,Integer byfs,Integer jyfs,Integer oyfs,Integer ayfs,Integer dyfs,Integer ynfs){
		this.orgid = orgid;
		this.orgname = orgname;
		this.userid = userid;
		this.username = username;
		this.yyfs = yyfs;
		this.eyfs = eyfs;
		this.myfs = myfs;
		this.syfs = syfs;
		this.wyfs = wyfs;
		this.lyfs = lyfs;
		this.qyfs = qyfs;
		this.byfs = byfs;
		this.jyfs = jyfs;
		this.oyfs = oyfs;
		this.ayfs = ayfs;
		this.dyfs = dyfs;
		this.ynfs = ynfs;
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

	public Integer getYyfs() {
		return yyfs;
	}

	public void setYyfs(Integer yyfs) {
		this.yyfs = yyfs;
	}

	public Integer getEyfs() {
		return eyfs;
	}

	public void setEyfs(Integer eyfs) {
		this.eyfs = eyfs;
	}

	public Integer getMyfs() {
		return myfs;
	}

	public void setMyfs(Integer myfs) {
		this.myfs = myfs;
	}

	public Integer getSyfs() {
		return syfs;
	}

	public void setSyfs(Integer syfs) {
		this.syfs = syfs;
	}

	public Integer getWyfs() {
		return wyfs;
	}

	public void setWyfs(Integer wyfs) {
		this.wyfs = wyfs;
	}

	public Integer getLyfs() {
		return lyfs;
	}

	public void setLyfs(Integer lyfs) {
		this.lyfs = lyfs;
	}

	public Integer getQyfs() {
		return qyfs;
	}

	public void setQyfs(Integer qyfs) {
		this.qyfs = qyfs;
	}

	public Integer getByfs() {
		return byfs;
	}

	public void setByfs(Integer byfs) {
		this.byfs = byfs;
	}

	public Integer getJyfs() {
		return jyfs;
	}

	public void setJyfs(Integer jyfs) {
		this.jyfs = jyfs;
	}

	public Integer getOyfs() {
		return oyfs;
	}

	public void setOyfs(Integer oyfs) {
		this.oyfs = oyfs;
	}

	public Integer getAyfs() {
		return ayfs;
	}

	public void setAyfs(Integer ayfs) {
		this.ayfs = ayfs;
	}

	public Integer getDyfs() {
		return dyfs;
	}

	public void setDyfs(Integer dyfs) {
		this.dyfs = dyfs;
	}

	public Integer getYnfs() {
		return ynfs;
	}

	public void setYnfs(Integer ynfs) {
		this.ynfs = ynfs;
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