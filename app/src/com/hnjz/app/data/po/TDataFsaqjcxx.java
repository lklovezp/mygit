package com.hnjz.app.data.po;

import com.hnjz.app.work.po.BaseObj;

/**
 * 
 * 辐射安全基本信息
 * 
 * */
@SuppressWarnings("serial")
public class TDataFsaqjcxx extends BaseObj{
	   private String id;//
	   private String lawobjId;//执法对象ID
	   private String lawobjTypeId;//执法对象类型ID
	   private String yzbm;//邮政编码
	   private String hbfzrcz;//环保负责人传真
	   private String hbfzremail;//环保负责人email
	   private String xkzh;//辐射安全许可证号
	   private String xkzlfw;//许可种类与范围
	   private String aqfh;//辐射安全与防护
	   private String aqfhjgmc;//辐射安全与防护管理机构名称
	   private String fzr;//辐射安全负责人
	   private String xl;//学历
	   private String zy;//专业
	   private String dh;//电话
	   private Integer gzrysl;//辐射工作人员数量
	   private Integer hgzrs;//取得相应级别培训合格证人数
	   private Integer yxqnrs;//在有效期内人数
	   private Integer jljcrs;//个人剂量监测人数
	   private Integer fsyzs;//放射源总数
	   private Integer fsyyil;//其中Ⅰ类
	   private Integer fsyerl;//Ⅱ类
	   private Integer fsysanl;//Ⅲ类
	   private Integer fsysil;//Ⅳ类
	   private Integer fsywul;//Ⅴ类
	   private Integer fjfsysanl;//废旧放射源Ⅲ类及以上
	   private String  fjfsylsqk;//处理计划及资金落实情况
	   private Integer fjfsysil;//Ⅳ类及以下
	   private Integer fjfsysilwzhd;//未知活度
	   private String  fjfsysillsqk;//处理计划及资金落实情况
	   private Integer zysxzzzs;//在用射线装置总数
	   private Integer zysxzzyil;//在用射线装置Ⅰ类
	   private Integer zysxzzerl;//在用射线装置Ⅱ类
	   private Integer zysxzzsanl;//在用射线装置Ⅲ类
	   private String  areaId;//
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
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	   
	
	
}
