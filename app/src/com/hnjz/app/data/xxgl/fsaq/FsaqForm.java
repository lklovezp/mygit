package com.hnjz.app.data.xxgl.fsaq;

import java.util.Date;

import com.hnjz.sys.po.TSysUser;

public class FsaqForm {
	 private String id;//
	   private String lawobjId;//ִ������ID
	   private String lawobjTypeId;//ִ����������ID
	   private String dwmc;
	   private String adress;
	   private String hbfzr;
	   private String hbfzrdh;
	   private String hbfzrcz;//���������˴���
	   private String hbfzremail;//����������email
	   private String yzbm;
	   private String fddbr;
	   private String fddbrdh;
	   private String xkzh;//���䰲ȫ����֤��
	   private String xkzlfw;//���������뷶Χ
	   private String aqfh;//���䰲ȫ�����
	   private String aqfhjgmc;//���䰲ȫ�����������������
	   private String fzr;//���䰲ȫ������
	   private String xl;//ѧ��
	   private String zy;//רҵ
	   private String dh;//�绰
	   private Integer gzrysl;//���乤����Ա����
	   private Integer hgzrs;//ȡ����Ӧ������ѵ�ϸ�֤����
	   private Integer yxqnrs;//����Ч��������
	   private Integer jljcrs;//���˼����������
	   private Integer fsyzs;//����Դ����
	   private Integer fsyyil;//���Т���
	   private Integer fsyerl;//����
	   private Integer fsysanl;//����
	   private Integer fsysil;//����
	   private Integer fsywul;//����
	   private Integer fjfsysanl;//�Ͼɷ���Դ���༰����
	   private String  fjfsylsqk;//�����ƻ����ʽ���ʵ���
	   private Integer fjfsysil;//���༰����
	   private Integer fjfsysilwzhd;//δ֪���
	   private String  fjfsysillsqk;//�����ƻ����ʽ���ʵ���
	   private Integer zysxzzzs;//��������װ������
	   private Integer zysxzzyil;//��������װ�â���
	   private Integer zysxzzerl;//��������װ�â���
	   private Integer zysxzzsanl;//��������װ�â���
	   private String  areaId;//
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
		public String getLawobjTypeId() {
			return lawobjTypeId;
		}
		public void setLawobjTypeId(String lawobjTypeId) {
			this.lawobjTypeId = lawobjTypeId;
		}
		public String getHbfzr() {
			return hbfzr;
		}
		public void setHbfzr(String hbfzr) {
			this.hbfzr = hbfzr;
		}
		public String getHbfzrdh() {
			return hbfzrdh;
		}
		public void setHbfzrdh(String hbfzrdh) {
			this.hbfzrdh = hbfzrdh;
		}
		public String getYzbm() {
			return yzbm;
		}
		public void setYzbm(String yzbm) {
			this.yzbm = yzbm;
		}
		public String getFddbr() {
			return fddbr;
		}
		public void setFddbr(String fddbr) {
			this.fddbr = fddbr;
		}
		public String getFddbrdh() {
			return fddbrdh;
		}
		public void setFddbrdh(String fddbrdh) {
			this.fddbrdh = fddbrdh;
		}
		public String getXkzh() {
			return xkzh;
		}
		public void setXkzh(String xkzh) {
			this.xkzh = xkzh;
		}
		public String getXkzlfw() {
			return xkzlfw;
		}
		public void setXkzlfw(String xkzlfw) {
			this.xkzlfw = xkzlfw;
		}
		public String getAqfh() {
			return aqfh;
		}
		public void setAqfh(String aqfh) {
			this.aqfh = aqfh;
		}
		public String getAqfhjgmc() {
			return aqfhjgmc;
		}
		public void setAqfhjgmc(String aqfhjgmc) {
			this.aqfhjgmc = aqfhjgmc;
		}
		public String getFzr() {
			return fzr;
		}
		public void setFzr(String fzr) {
			this.fzr = fzr;
		}
		public String getXl() {
			return xl;
		}
		public void setXl(String xl) {
			this.xl = xl;
		}
		public String getZy() {
			return zy;
		}
		public void setZy(String zy) {
			this.zy = zy;
		}
		public String getDh() {
			return dh;
		}
		public void setDh(String dh) {
			this.dh = dh;
		}
		public Integer getGzrysl() {
			return gzrysl;
		}
		public void setGzrysl(Integer gzrysl) {
			this.gzrysl = gzrysl;
		}
		public Integer getHgzrs() {
			return hgzrs;
		}
		public void setHgzrs(Integer hgzrs) {
			this.hgzrs = hgzrs;
		}
		public Integer getYxqnrs() {
			return yxqnrs;
		}
		public void setYxqnrs(Integer yxqnrs) {
			this.yxqnrs = yxqnrs;
		}
		public Integer getJljcrs() {
			return jljcrs;
		}
		public void setJljcrs(Integer jljcrs) {
			this.jljcrs = jljcrs;
		}
		public Integer getFsyzs() {
			return fsyzs;
		}
		public void setFsyzs(Integer fsyzs) {
			this.fsyzs = fsyzs;
		}
		public Integer getFsyyil() {
			return fsyyil;
		}
		public void setFsyyil(Integer fsyyil) {
			this.fsyyil = fsyyil;
		}
		public Integer getFsyerl() {
			return fsyerl;
		}
		public void setFsyerl(Integer fsyerl) {
			this.fsyerl = fsyerl;
		}
		public Integer getFsysanl() {
			return fsysanl;
		}
		public void setFsysanl(Integer fsysanl) {
			this.fsysanl = fsysanl;
		}
		public Integer getFsysil() {
			return fsysil;
		}
		public void setFsysil(Integer fsysil) {
			this.fsysil = fsysil;
		}
		public Integer getFsywul() {
			return fsywul;
		}
		public void setFsywul(Integer fsywul) {
			this.fsywul = fsywul;
		}
		public Integer getFjfsysanl() {
			return fjfsysanl;
		}
		public void setFjfsysanl(Integer fjfsysanl) {
			this.fjfsysanl = fjfsysanl;
		}
		public String getFjfsylsqk() {
			return fjfsylsqk;
		}
		public void setFjfsylsqk(String fjfsylsqk) {
			this.fjfsylsqk = fjfsylsqk;
		}
		public Integer getFjfsysil() {
			return fjfsysil;
		}
		public void setFjfsysil(Integer fjfsysil) {
			this.fjfsysil = fjfsysil;
		}
		public Integer getFjfsysilwzhd() {
			return fjfsysilwzhd;
		}
		public void setFjfsysilwzhd(Integer fjfsysilwzhd) {
			this.fjfsysilwzhd = fjfsysilwzhd;
		}
		public String getFjfsysillsqk() {
			return fjfsysillsqk;
		}
		public void setFjfsysillsqk(String fjfsysillsqk) {
			this.fjfsysillsqk = fjfsysillsqk;
		}
		public Integer getZysxzzzs() {
			return zysxzzzs;
		}
		public void setZysxzzzs(Integer zysxzzzs) {
			this.zysxzzzs = zysxzzzs;
		}
		public Integer getZysxzzyil() {
			return zysxzzyil;
		}
		public void setZysxzzyil(Integer zysxzzyil) {
			this.zysxzzyil = zysxzzyil;
		}
		public Integer getZysxzzerl() {
			return zysxzzerl;
		}
		public void setZysxzzerl(Integer zysxzzerl) {
			this.zysxzzerl = zysxzzerl;
		}
		public Integer getZysxzzsanl() {
			return zysxzzsanl;
		}
		public void setZysxzzsanl(Integer zysxzzsanl) {
			this.zysxzzsanl = zysxzzsanl;
		}
		public String getAreaId() {
			return areaId;
		}
		public void setAreaId(String areaId) {
			this.areaId = areaId;
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
		public String getDwmc() {
			return dwmc;
		}
		public void setDwmc(String dwmc) {
			this.dwmc = dwmc;
		}
		public String getAdress() {
			return adress;
		}
		public void setAdress(String adress) {
			this.adress = adress;
		}
		public String getHbfzrcz() {
			return hbfzrcz;
		}
		public void setHbfzrcz(String hbfzrcz) {
			this.hbfzrcz = hbfzrcz;
		}
		public String getHbfzremail() {
			return hbfzremail;
		}
		public void setHbfzremail(String hbfzremail) {
			this.hbfzremail = hbfzremail;
		}
	    
}