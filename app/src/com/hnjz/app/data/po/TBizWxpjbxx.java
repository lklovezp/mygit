package com.hnjz.app.data.po;

import java.util.Date;

import com.hnjz.app.work.po.BaseObj;

@SuppressWarnings("serial")
public class TBizWxpjbxx extends BaseObj{
	private String id; 
	private String lawobjId;
	//����
	private String qh;
	 //ֵ��绰
	private String zbdh;
	//�绰����
	private String dhhm;
	 //�������
	private String czhm;
	 //��������
	private String yzbm;
	 //ִ����������
	private String lawobjTypeId; 
	 //���ڹ�ҵ԰������ ��λ�ڹ��Ҽ���ʡ����ҵ԰������ҵ���
	private String yqmc; 
	 //������ʱ�䣨Сʱ��
	private Integer nscsj;
	 //��ҵ�ܲ�ֵ��Ԫ
	private Integer zcz; 
	 //�������
	private Integer cqmj; 
	 //�Ƿ����ͻ�������¼�Ӧ��Ԥ�� 0�� 1��
	private String sfbzya; 
	 //����Ӱ�������ļ��Ƿ񰴡�������Ŀ�����������ۼ�������HJ/T169����Ҫ����ƻ�����������רƪ
	private String sfpjwj; 
	 //����ͻ�������¼���� 0�� 1��
	private String sftf;
	 //����ͻ�������¼���� ��ֹ����
	private Date sftfDate; 
	 //����ͻ�������¼���� ����
	private Integer sftfcs; 
	 //����Id
	private String areaId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLawobjId() {
		return lawobjId;
	}
	public void setLawobjId(String lawobjId) {
		this.lawobjId = lawobjId;
	}
	public String getQh() {
		return qh;
	}
	public void setQh(String qh) {
		this.qh = qh;
	}
	public String getZbdh() {
		return zbdh;
	}
	public void setZbdh(String zbdh) {
		this.zbdh = zbdh;
	}
	public String getCzhm() {
		return czhm;
	}
	public void setCzhm(String czhm) {
		this.czhm = czhm;
	}
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	public String getLawobjTypeId() {
		return lawobjTypeId;
	}
	public void setLawobjTypeId(String lawobjTypeId) {
		this.lawobjTypeId = lawobjTypeId;
	}
	public String getYqmc() {
		return yqmc;
	}
	public void setYqmc(String yqmc) {
		this.yqmc = yqmc;
	}
	public Integer getNscsj() {
		return nscsj;
	}
	public void setNscsj(Integer nscsj) {
		this.nscsj = nscsj;
	}
	public Integer getZcz() {
		return zcz;
	}
	public void setZcz(Integer zcz) {
		this.zcz = zcz;
	}
	public Integer getCqmj() {
		return cqmj;
	}
	public void setCqmj(Integer cqmj) {
		this.cqmj = cqmj;
	}
	public String getSfbzya() {
		return sfbzya;
	}
	public void setSfbzya(String sfbzya) {
		this.sfbzya = sfbzya;
	}
	public String getSfpjwj() {
		return sfpjwj;
	}
	public void setSfpjwj(String sfpjwj) {
		this.sfpjwj = sfpjwj;
	}
	public String getSftf() {
		return sftf;
	}
	public void setSftf(String sftf) {
		this.sftf = sftf;
	}
	public Date getSftfDate() {
		return sftfDate;
	}
	public void setSftfDate(Date sftfDate) {
		this.sftfDate = sftfDate;
	}
	public Integer getSftfcs() {
		return sftfcs;
	}
	public void setSftfcs(Integer sftfcs) {
		this.sftfcs = sftfcs;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getDhhm() {
		return dhhm;
	}
	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
	} 
	
	

}