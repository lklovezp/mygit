package com.hnjz.app.work.danger;

import java.util.Date;

import com.hnjz.sys.po.TSysUser;

public class QyjbqkForm {
	private String id; 
	private String lawobjId;
	//����
	private String qh;
	//�绰����
	private String dhhm;
	 //ֵ��绰
	private String zbdh;
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
	private String sftfDate; 
	 //����ͻ�������¼���� ����
	private Integer sftfcs; 
	 //����Id
	private String areaId;
	//��λ����
	private String dwmc;
	//��λ����
	private String code;
	//����������
	private String fddbr;
	//��������
	private String xzqh;
	//�������������룩
	private String xzqhCode;
	//��λ���ڵ�
	private String dwszd;
	//��������  ���ľ���
	private String zxjd;
	//����γ��
	private String zxwd;
	//��ҵ����������Ա
	private String qyhjglrName;
	//��ҵ����������Ա��ϵ�绰
	private String qyhjglrPhone;
	//��ҵ���
	private String hylb;
	//��ҵ����
	private String hydm;
	//������������
	private String sswgqy;
	//�����������
	private String ssqgdm;
	//��������
	private String wgms;
	
	/**�汾��*/
    private Integer           version;
	/**�Ƿ���Ч*/
	private String            isActive;
	/**����������*/
    private TSysUser              creater;
	/**�޸�������*/
    private TSysUser              updateby;
	/**����ʱ��*/
	private Date              updateTime;
	/**�޸�ʱ��*/
	private Date              createTime;
	
	public String getHylb() {
		return hylb;
	}
	public void setHylb(String hylb) {
		this.hylb = hylb;
	}
	public String getHydm() {
		return hydm;
	}
	public void setHydm(String hydm) {
		this.hydm = hydm;
	}
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
	public String getSftfDate() {
		return sftfDate;
	}
	public void setSftfDate(String sftfDate) {
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
	public String getDwmc() {
		return dwmc;
	}
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFddbr() {
		return fddbr;
	}
	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}
	public String getXzqh() {
		return xzqh;
	}
	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}
	public String getXzqhCode() {
		return xzqhCode;
	}
	public void setXzqhCode(String xzqhCode) {
		this.xzqhCode = xzqhCode;
	}
	public String getDwszd() {
		return dwszd;
	}
	public void setDwszd(String dwszd) {
		this.dwszd = dwszd;
	}
	public String getZxjd() {
		return zxjd;
	}
	public void setZxjd(String zxjd) {
		this.zxjd = zxjd;
	}
	public String getZxwd() {
		return zxwd;
	}
	public void setZxwd(String zxwd) {
		this.zxwd = zxwd;
	}
	public String getQyhjglrName() {
		return qyhjglrName;
	}
	public void setQyhjglrName(String qyhjglrName) {
		this.qyhjglrName = qyhjglrName;
	}
	public String getQyhjglrPhone() {
		return qyhjglrPhone;
	}
	public void setQyhjglrPhone(String qyhjglrPhone) {
		this.qyhjglrPhone = qyhjglrPhone;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public TSysUser getCreater() {
		return creater;
	}
	public void setCreater(TSysUser creater) {
		this.creater = creater;
	}
	public TSysUser getUpdateby() {
		return updateby;
	}
	public void setUpdateby(TSysUser updateby) {
		this.updateby = updateby;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDhhm() {
		return dhhm;
	}
	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
	}
	public String getSswgqy() {
		return sswgqy;
	}
	public void setSswgqy(String sswgqy) {
		this.sswgqy = sswgqy;
	}
	public String getSsqgdm() {
		return ssqgdm;
	}
	public void setSsqgdm(String ssqgdm) {
		this.ssqgdm = ssqgdm;
	}
	public String getWgms() {
		return wgms;
	}
	public void setWgms(String wgms) {
		this.wgms = wgms;
	}
	
	
	
}