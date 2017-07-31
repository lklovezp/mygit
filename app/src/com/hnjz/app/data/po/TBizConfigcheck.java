package com.hnjz.app.data.po;

import com.hnjz.app.work.po.BaseObj;
/**
 * 随机抽查比例设定表
 * likun
 * */
@SuppressWarnings("serial")
public class TBizConfigcheck extends BaseObj{
	  private String id;
	  private String gzsz;
	  private double ybqybl;//一般企业一季度抽查比例
	  private double zdqybl;//重点企业一季度抽查比例
	  private double ybqyblsecond;//一般企业二季度抽查比例
	  private double zdqyblsecond;//重点企业二季度抽查比例
	  private double ybqyblthird;//一般企业三季度抽查比例
	  private double zdqyblthird;//重点企业三季度抽查比例
	  private double ybqyblfour;//一般企业四季度抽查比例
	  private double zdqyblfour;//重点企业四季度抽查比例
	  private double  ybqyjan;  //一般企业一月抽查比例 
	  private double  ybqyfeb;  
	  private double  ybqymar;   
	  private double  ybqyapr;   
	  private double  ybqymay; 
	  private double  ybqyjun; 
	  private double  ybqyjul; 
	  private double  ybqyaug; 
	  private double  ybqysep; 
	  private double  ybqyoct; 
	  private double  ybqynov; 
	  private double  ybqydec; 
	  private double  zdqyjan;//重点企业一月抽查比例  
	  private double  zdqyfeb; 
	  private double  zdqymar; 
	  private double  zdqyapr; 
	  private double  zdqymay; 
	  private double  zdqyjun; 
	  private double  zdqyjul; 
	  private double  zdqyaug; 
	  private double  zdqysep;
	  private double  zdqyoct; 
	  private double  zdqynov; 
	  private double  zdqydec; 
	  private String tsjgqybl;
	  private String areaId;
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
	public double getYbqybl() {
		return ybqybl;
	}
	public void setYbqybl(double ybqybl) {
		this.ybqybl = ybqybl;
	}
	public double getZdqybl() {
		return zdqybl;
	}
	public void setZdqybl(double zdqybl) {
		this.zdqybl = zdqybl;
	}
	 public double getYbqyblsecond() {
			return ybqyblsecond;
		}
		public void setYbqyblsecond(double ybqyblsecond) {
			this.ybqyblsecond = ybqyblsecond;
		}
		public double getZdqyblsecond() {
			return zdqyblsecond;
		}
		public void setZdqyblsecond(double zdqyblsecond) {
			this.zdqyblsecond = zdqyblsecond;
		}
		public double getYbqyblthird() {
			return ybqyblthird;
		}
		public void setYbqyblthird(double ybqyblthird) {
			this.ybqyblthird = ybqyblthird;
		}
		public double getZdqyblthird() {
			return zdqyblthird;
		}
		public void setZdqyblthird(double zdqyblthird) {
			this.zdqyblthird = zdqyblthird;
		}
		public double getYbqyblfour() {
			return ybqyblfour;
		}
		public void setYbqyblfour(double ybqyblfour) {
			this.ybqyblfour = ybqyblfour;
		}
		public double getZdqyblfour() {
			return zdqyblfour;
		}
		public void setZdqyblfour(double zdqyblfour) {
			this.zdqyblfour = zdqyblfour;
		}
	
	
	public String getTsjgqybl() {
		return tsjgqybl;
	}
	public void setTsjgqybl(String tsjgqybl) {
		this.tsjgqybl = tsjgqybl;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public double getYbqyjan() {
		return ybqyjan;
	}
	public void setYbqyjan(double ybqyjan) {
		this.ybqyjan = ybqyjan;
	}
	public double getYbqyfeb() {
		return ybqyfeb;
	}
	public void setYbqyfeb(double ybqyfeb) {
		this.ybqyfeb = ybqyfeb;
	}
	public double getYbqymar() {
		return ybqymar;
	}
	public void setYbqymar(double ybqymar) {
		this.ybqymar = ybqymar;
	}
	public double getYbqyapr() {
		return ybqyapr;
	}
	public void setYbqyapr(double ybqyapr) {
		this.ybqyapr = ybqyapr;
	}
	public double getYbqymay() {
		return ybqymay;
	}
	public void setYbqymay(double ybqymay) {
		this.ybqymay = ybqymay;
	}
	public double getYbqyjun() {
		return ybqyjun;
	}
	public void setYbqyjun(double ybqyjun) {
		this.ybqyjun = ybqyjun;
	}
	public double getYbqyjul() {
		return ybqyjul;
	}
	public void setYbqyjul(double ybqyjul) {
		this.ybqyjul = ybqyjul;
	}
	public double getYbqyaug() {
		return ybqyaug;
	}
	public void setYbqyaug(double ybqyaug) {
		this.ybqyaug = ybqyaug;
	}
	public double getYbqysep() {
		return ybqysep;
	}
	public void setYbqysep(double ybqysep) {
		this.ybqysep = ybqysep;
	}
	public double getYbqyoct() {
		return ybqyoct;
	}
	public void setYbqyoct(double ybqyoct) {
		this.ybqyoct = ybqyoct;
	}
	public double getYbqynov() {
		return ybqynov;
	}
	public void setYbqynov(double ybqynov) {
		this.ybqynov = ybqynov;
	}
	public double getYbqydec() {
		return ybqydec;
	}
	public void setYbqydec(double ybqydec) {
		this.ybqydec = ybqydec;
	}
	public double getZdqyjan() {
		return zdqyjan;
	}
	public void setZdqyjan(double zdqyjan) {
		this.zdqyjan = zdqyjan;
	}
	public double getZdqyfeb() {
		return zdqyfeb;
	}
	public void setZdqyfeb(double zdqyfeb) {
		this.zdqyfeb = zdqyfeb;
	}
	public double getZdqymar() {
		return zdqymar;
	}
	public void setZdqymar(double zdqymar) {
		this.zdqymar = zdqymar;
	}
	public double getZdqyapr() {
		return zdqyapr;
	}
	public void setZdqyapr(double zdqyapr) {
		this.zdqyapr = zdqyapr;
	}
	public double getZdqymay() {
		return zdqymay;
	}
	public void setZdqymay(double zdqymay) {
		this.zdqymay = zdqymay;
	}
	public double getZdqyjun() {
		return zdqyjun;
	}
	public void setZdqyjun(double zdqyjun) {
		this.zdqyjun = zdqyjun;
	}
	public double getZdqyjul() {
		return zdqyjul;
	}
	public void setZdqyjul(double zdqyjul) {
		this.zdqyjul = zdqyjul;
	}
	public double getZdqyaug() {
		return zdqyaug;
	}
	public void setZdqyaug(double zdqyaug) {
		this.zdqyaug = zdqyaug;
	}
	public double getZdqysep() {
		return zdqysep;
	}
	public void setZdqysep(double zdqysep) {
		this.zdqysep = zdqysep;
	}
	public double getZdqyoct() {
		return zdqyoct;
	}
	public void setZdqyoct(double zdqyoct) {
		this.zdqyoct = zdqyoct;
	}
	public double getZdqynov() {
		return zdqynov;
	}
	public void setZdqynov(double zdqynov) {
		this.zdqynov = zdqynov;
	}
	public double getZdqydec() {
		return zdqydec;
	}
	public void setZdqydec(double zdqydec) {
		this.zdqydec = zdqydec;
	}
	 
	
}
