package com.hnjz.app.data.xxgl.tslawobj;

import java.util.Date;

import com.hnjz.sys.po.TSysUser;
/**
 * 随机抽查比例设定表form
 * likun
 * */
public class ConfigCheckForm {
	  private String id;
	  private String gzsz;
	  private String ybqybl;//一般企业一季度抽查比例
	  private String zdqybl;//重点企业一季度抽查比例
	  private String tsjgqybl;
	 
	  private String ybqyblsecond;//一般企业二季度抽查比例
	  private String zdqyblsecond;//重点企业二季度抽查比例
	  private String ybqyblthird;//一般企业三季度抽查比例
	  private String zdqyblthird;//重点企业三季度抽查比例
	  private String ybqyblfour;//一般企业四季度抽查比例
	  private String zdqyblfour;//重点企业四季度抽查比例
	  private String  ybqyjan;  //一般企业一月抽查比例 
	  private String  ybqyfeb;  
	  private String  ybqymar;   
	  private String  ybqyapr;   
	  private String  ybqymay; 
	  private String  ybqyjun; 
	  private String  ybqyjul; 
	  private String  ybqyaug; 
	  private String  ybqysep; 
	  private String  ybqyoct; 
	  private String  ybqynov; 
	  private String  ybqydec; 
	  private String  zdqyjan;//重点企业一月抽查比例  
	  private String  zdqyfeb; 
	  private String  zdqymar; 
	  private String  zdqyapr; 
	  private String  zdqymay; 
	  private String  zdqyjun; 
	  private String  zdqyjul; 
	  private String  zdqyaug; 
	  private String  zdqysep;
	  private String  zdqyoct; 
	  private String  zdqynov; 
	  private String  zdqydec; 
	  /**版本号*/
	    private Integer           version;
	    /**是否有效*/
	    private String            isActive;
	    /**创建人姓名*/
	    private TSysUser              creater;
	    /**修改人姓名*/
	    private TSysUser              updateby;
	    /**创建时间*/
	    private Date              updateTime;
	    /**修改时间*/
	    private Date              createTime;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getGzsz() {
			return gzsz;
		}
		public void setGzsz(String gzsz) {
			this.gzsz = gzsz;
		}
		public String getYbqybl() {
			return ybqybl;
		}
		public void setYbqybl(String ybqybl) {
			this.ybqybl = ybqybl;
		}
		public String getZdqybl() {
			return zdqybl;
		}
		public void setZdqybl(String zdqybl) {
			this.zdqybl = zdqybl;
		}
		
		
		 public String getYbqyblsecond() {
				return ybqyblsecond;
			}
			public void setYbqyblsecond(String ybqyblsecond) {
				this.ybqyblsecond = ybqyblsecond;
			}
			public String getZdqyblsecond() {
				return zdqyblsecond;
			}
			public void setZdqyblsecond(String zdqyblsecond) {
				this.zdqyblsecond = zdqyblsecond;
			}
			public String getYbqyblthird() {
				return ybqyblthird;
			}
			public void setYbqyblthird(String ybqyblthird) {
				this.ybqyblthird = ybqyblthird;
			}
			public String getZdqyblthird() {
				return zdqyblthird;
			}
			public void setZdqyblthird(String zdqyblthird) {
				this.zdqyblthird = zdqyblthird;
			}
			public String getYbqyblfour() {
				return ybqyblfour;
			}
			public void setYbqyblfour(String ybqyblfour) {
				this.ybqyblfour = ybqyblfour;
			}
			public String getZdqyblfour() {
				return zdqyblfour;
			}
			public void setZdqyblfour(String zdqyblfour) {
				this.zdqyblfour = zdqyblfour;
			}
		
		
		public String getTsjgqybl() {
			return tsjgqybl;
		}
		public void setTsjgqybl(String tsjgqybl) {
			this.tsjgqybl = tsjgqybl;
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
		public String getYbqyjan() {
			return ybqyjan;
		}
		public void setYbqyjan(String ybqyjan) {
			this.ybqyjan = ybqyjan;
		}
		public String getYbqyfeb() {
			return ybqyfeb;
		}
		public void setYbqyfeb(String ybqyfeb) {
			this.ybqyfeb = ybqyfeb;
		}
		public String getYbqymar() {
			return ybqymar;
		}
		public void setYbqymar(String ybqymar) {
			this.ybqymar = ybqymar;
		}
		public String getYbqyapr() {
			return ybqyapr;
		}
		public void setYbqyapr(String ybqyapr) {
			this.ybqyapr = ybqyapr;
		}
		public String getYbqymay() {
			return ybqymay;
		}
		public void setYbqymay(String ybqymay) {
			this.ybqymay = ybqymay;
		}
		public String getYbqyjun() {
			return ybqyjun;
		}
		public void setYbqyjun(String ybqyjun) {
			this.ybqyjun = ybqyjun;
		}
		public String getYbqyjul() {
			return ybqyjul;
		}
		public void setYbqyjul(String ybqyjul) {
			this.ybqyjul = ybqyjul;
		}
		public String getYbqyaug() {
			return ybqyaug;
		}
		public void setYbqyaug(String ybqyaug) {
			this.ybqyaug = ybqyaug;
		}
		public String getYbqysep() {
			return ybqysep;
		}
		public void setYbqysep(String ybqysep) {
			this.ybqysep = ybqysep;
		}
		public String getYbqyoct() {
			return ybqyoct;
		}
		public void setYbqyoct(String ybqyoct) {
			this.ybqyoct = ybqyoct;
		}
		public String getYbqynov() {
			return ybqynov;
		}
		public void setYbqynov(String ybqynov) {
			this.ybqynov = ybqynov;
		}
		public String getYbqydec() {
			return ybqydec;
		}
		public void setYbqydec(String ybqydec) {
			this.ybqydec = ybqydec;
		}
		public String getZdqyjan() {
			return zdqyjan;
		}
		public void setZdqyjan(String zdqyjan) {
			this.zdqyjan = zdqyjan;
		}
		public String getZdqyfeb() {
			return zdqyfeb;
		}
		public void setZdqyfeb(String zdqyfeb) {
			this.zdqyfeb = zdqyfeb;
		}
		public String getZdqymar() {
			return zdqymar;
		}
		public void setZdqymar(String zdqymar) {
			this.zdqymar = zdqymar;
		}
		public String getZdqyapr() {
			return zdqyapr;
		}
		public void setZdqyapr(String zdqyapr) {
			this.zdqyapr = zdqyapr;
		}
		public String getZdqymay() {
			return zdqymay;
		}
		public void setZdqymay(String zdqymay) {
			this.zdqymay = zdqymay;
		}
		public String getZdqyjun() {
			return zdqyjun;
		}
		public void setZdqyjun(String zdqyjun) {
			this.zdqyjun = zdqyjun;
		}
		public String getZdqyjul() {
			return zdqyjul;
		}
		public void setZdqyjul(String zdqyjul) {
			this.zdqyjul = zdqyjul;
		}
		public String getZdqyaug() {
			return zdqyaug;
		}
		public void setZdqyaug(String zdqyaug) {
			this.zdqyaug = zdqyaug;
		}
		public String getZdqysep() {
			return zdqysep;
		}
		public void setZdqysep(String zdqysep) {
			this.zdqysep = zdqysep;
		}
		public String getZdqyoct() {
			return zdqyoct;
		}
		public void setZdqyoct(String zdqyoct) {
			this.zdqyoct = zdqyoct;
		}
		public String getZdqynov() {
			return zdqynov;
		}
		public void setZdqynov(String zdqynov) {
			this.zdqynov = zdqynov;
		}
		public String getZdqydec() {
			return zdqydec;
		}
		public void setZdqydec(String zdqydec) {
			this.zdqydec = zdqydec;
		}
	    
		
		
}
