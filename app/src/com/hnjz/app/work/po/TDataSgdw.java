package com.hnjz.app.work.po;


import com.hnjz.app.work.po.BaseObj;


/**
 * T_DATA_SGDW ʵ����
 * ���ߣ�����
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * ����������ʩ����λ
 */ 
@SuppressWarnings("serial")
public class TDataSgdw extends BaseObj{
	private static final long serialVersionUID = 1L;
	private String id;
	/**��λ����*/
	private String name;
	/**��λ��ַ*/
	private String adress;
	/**����������*/
	private String fddbr;
	/**������������ϵ�绰*/
	private String fddbrlxdh;
	/**����������*/
	private String hbfzr;
	/**������������ϵ�绰*/
	private String hbfzrlxdh;
	/**��������*/
	private String areaId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
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
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
    
    

}