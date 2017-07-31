package com.hnjz.app.data.po;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnjz.common.dao.Dao;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysUser;

/**
 * T_DATA_LAWOBJ 实体类
 * 作者：张少卫
 * 生成日期：Tue Mar 17 09:17:30 CST 2015
 * 功能描述：执法对象
 */ 

@SuppressWarnings("serial")
public class TDataLawobj extends BaseObject {
	
	@Autowired
	protected Dao dao;
	    
	/** 执法对象类型
01	工业污染源
02	建设项目
03	医院
04	锅炉
05	建筑工地
06	三产
07	畜禽养殖 
08  服务业
09  饮食业
10  三产制造业
11  娱乐业*/
	private String lawobjtype;
	private String name;
	/**  */
	private String column1;
	/**  */
	private String column2;
	/**  */
	private String column3;
	/**  */
	private String column3du;
	/**  */
	private String column3fe;
	/**  */
	private String column3mi;
	/**  */
	private String column4;
	/**  */
	private String column4du;
	/**  */
	private String column4fe;
	/**  */
	private String column4mi;
	/**  */
	private String column5;
	/**  */
	private String column5du;
	/**  */
	private String column5fe;
	/**  */
	private String column5mi;
	/**  */
	private String column6;
	/**  */
	private String column6du;
	/**  */
	private String column6fe;
	/**  */
	private String column6mi;
	/**  */
	private String column7;
	/**  */
	private String column7du;
	/**  */
	private String column7fe;
	/**  */
	private String column7mi;
	/**  */
	private String column8;
	/**  */
	private String column8du;
	/**  */
	private String column8fe;
	/**  */
	private String column8mi;
	/**  */
	private String column9;
	/**  */
	private String column10;
	/**  */
	private String column11;
	/**  */
	private String column12;
	/**  */
	private String column13;
	/**  */
	private String column14;
	/**  */
	private String column15;
	/**  */
	private String column16;
	/**  */
	private String column17;
	/**  */
	private String column18;
	/**  */
	private String column19;
	/**  */
	private String column20;
	/**  */
	private String column21;
	/**  */
	private String column22;
	/**  */
	private String column23;
	/**  */
	private String column24;
	/**  */
	private String column25;
	/**  */
	private String column26;
	/**  */
	private String column27;
	/**  */
	private String column28;
	/**  */
	private String column29;
	/**  */
	private String column30;
	/**  */
	private String column31;
	/**  */
	private String column32;
	/**  */
	private String column33;
	/**  */
	private String column34;
	/**  */
	private String column35;
	/**  */
	private String column36;
	/**  */
	private String column37;
	/**  */
	private String column38;
	/**  */
	private String column39;
	/**  */
	private String column40;
	/**  */
	private String column41;
	/**  */
	private String column42;
	/**  */
	private String column43;
	/**  */
	private String column44;
	/**  */
	private String column45;
	/**  */
	private String column46;
	/**  */
	private String column47;
	/**  */
	private String column48;
	/**  */
	private String column49;
	/**  */
	private String column50;
	/** 隶属区域 */
	private String areaid;

	public void setLawobjtype(String lawobjtype){
		this.lawobjtype = lawobjtype;
	}
	public String getLawobjtype(){
		return lawobjtype;
	}
	public void setColumn1(String column1){
		this.column1 = column1;
	}
	public String getColumn1(){
		return column1;
	}
	public void setColumn2(String column2){
		this.column2 = column2;
	}
	public String getColumn2(){
		return column2;
	}
	public void setColumn3(String column3){
		this.column3 = column3;
	}
	public String getColumn3(){
		return column3;
	}
	public void setColumn4(String column4){
		this.column4 = column4;
	}
	public String getColumn4(){
		return column4;
	}
	public void setColumn5(String column5){
		this.column5 = column5;
	}
	public String getColumn5(){
		return column5;
	}
	public void setColumn6(String column6){
		this.column6 = column6;
	}
	public String getColumn6(){
		return column6;
	}
	public void setColumn7(String column7){
		this.column7 = column7;
	}
	public String getColumn7(){
		return column7;
	}
	public void setColumn8(String column8){
		this.column8 = column8;
	}
	public String getColumn8(){
		return column8;
	}
	public void setColumn9(String column9){
		this.column9 = column9;
	}
	public String getColumn9(){
		return column9;
	}
	public void setColumn10(String column10){
		this.column10 = column10;
	}
	public String getColumn10(){
		return column10;
	}
	public void setColumn11(String column11){
		this.column11 = column11;
	}
	public String getColumn11(){
		return column11;
	}
	public void setColumn12(String column12){
		this.column12 = column12;
	}
	public String getColumn12(){
		return column12;
	}
	public void setColumn13(String column13){
		this.column13 = column13;
	}
	public String getColumn13(){
		return column13;
	}
	public void setColumn14(String column14){
		this.column14 = column14;
	}
	public String getColumn14(){
		return column14;
	}
	public void setColumn15(String column15){
		this.column15 = column15;
	}
	public String getColumn15(){
		return column15;
	}
	public void setColumn16(String column16){
		this.column16 = column16;
	}
	public String getColumn16(){
		return column16;
	}
	public void setColumn17(String column17){
		this.column17 = column17;
	}
	public String getColumn17(){
		return column17;
	}
	public void setColumn18(String column18){
		this.column18 = column18;
	}
	public String getColumn18(){
		return column18;
	}
	public void setColumn19(String column19){
		this.column19 = column19;
	}
	public String getColumn19(){
		return column19;
	}
	public void setColumn20(String column20){
		this.column20 = column20;
	}
	public String getColumn20(){
		return column20;
	}
	public void setColumn21(String column21){
		this.column21 = column21;
	}
	public String getColumn21(){
		return column21;
	}
	public void setColumn22(String column22){
		this.column22 = column22;
	}
	public String getColumn22(){
		return column22;
	}
	public void setColumn23(String column23){
		this.column23 = column23;
	}
	public String getColumn23(){
		return column23;
	}
	public void setColumn24(String column24){
		this.column24 = column24;
	}
	public String getColumn24(){
		return column24;
	}
	public void setColumn25(String column25){
		this.column25 = column25;
	}
	public String getColumn25(){
		return column25;
	}
	public void setColumn26(String column26){
		this.column26 = column26;
	}
	public String getColumn26(){
		return column26;
	}
	public void setColumn27(String column27){
		this.column27 = column27;
	}
	public String getColumn27(){
		return column27;
	}
	public void setColumn28(String column28){
		this.column28 = column28;
	}
	public String getColumn28(){
		return column28;
	}
	public void setColumn29(String column29){
		this.column29 = column29;
	}
	public String getColumn29(){
		return column29;
	}
	public void setColumn30(String column30){
		this.column30 = column30;
	}
	public String getColumn30(){
		return column30;
	}
	public void setColumn31(String column31){
		this.column31 = column31;
	}
	public String getColumn31(){
		return column31;
	}
	public void setColumn32(String column32){
		this.column32 = column32;
	}
	public String getColumn32(){
		return column32;
	}
	public void setColumn33(String column33){
		this.column33 = column33;
	}
	public String getColumn33(){
		return column33;
	}
	public void setColumn34(String column34){
		this.column34 = column34;
	}
	public String getColumn34(){
		return column34;
	}
	public void setColumn35(String column35){
		this.column35 = column35;
	}
	public String getColumn35(){
		return column35;
	}
	public void setColumn36(String column36){
		this.column36 = column36;
	}
	public String getColumn36(){
		return column36;
	}
	public void setColumn37(String column37){
		this.column37 = column37;
	}
	public String getColumn37(){
		return column37;
	}
	public void setColumn38(String column38){
		this.column38 = column38;
	}
	public String getColumn38(){
		return column38;
	}
	public void setColumn39(String column39){
		this.column39 = column39;
	}
	public String getColumn39(){
		return column39;
	}
	public void setColumn40(String column40){
		this.column40 = column40;
	}
	public String getColumn40(){
		return column40;
	}
	public void setColumn41(String column41){
		this.column41 = column41;
	}
	public String getColumn41(){
		return column41;
	}
	public void setColumn42(String column42){
		this.column42 = column42;
	}
	public String getColumn42(){
		return column42;
	}
	public void setColumn43(String column43){
		this.column43 = column43;
	}
	public String getColumn43(){
		return column43;
	}
	public void setColumn44(String column44){
		this.column44 = column44;
	}
	public String getColumn44(){
		return column44;
	}
	public void setColumn45(String column45){
		this.column45 = column45;
	}
	public String getColumn45(){
		return column45;
	}
	public void setColumn46(String column46){
		this.column46 = column46;
	}
	public String getColumn46(){
		return column46;
	}
	public void setColumn47(String column47){
		this.column47 = column47;
	}
	public String getColumn47(){
		return column47;
	}
	public void setColumn48(String column48){
		this.column48 = column48;
	}
	public String getColumn48(){
		return column48;
	}
	public void setColumn49(String column49){
		this.column49 = column49;
	}
	public String getColumn49(){
		return column49;
	}
	public void setColumn50(String column50){
		this.column50 = column50;
	}
	public String getColumn50(){
		return column50;
	}
	public void setAreaid(String areaid){
		this.areaid = areaid;
	}
	public String getAreaid(){
		return areaid;
	}
	public String getColumn5du() {
		return column5du;
	}
	public void setColumn5du(String column5du) {
		this.column5du = column5du;
	}
	public String getColumn5fe() {
		return column5fe;
	}
	public void setColumn5fe(String column5fe) {
		this.column5fe = column5fe;
	}
	public String getColumn5mi() {
		return column5mi;
	}
	public void setColumn5mi(String column5mi) {
		this.column5mi = column5mi;
	}
	public String getColumn6du() {
		return column6du;
	}
	public void setColumn6du(String column6du) {
		this.column6du = column6du;
	}
	public String getColumn6fe() {
		return column6fe;
	}
	public void setColumn6fe(String column6fe) {
		this.column6fe = column6fe;
	}
	public String getColumn6mi() {
		return column6mi;
	}
	public void setColumn6mi(String column6mi) {
		this.column6mi = column6mi;
	}
	public String getColumn3du() {
		return column3du;
	}
	public void setColumn3du(String column3du) {
		this.column3du = column3du;
	}
	public String getColumn3fe() {
		return column3fe;
	}
	public void setColumn3fe(String column3fe) {
		this.column3fe = column3fe;
	}
	public String getColumn3mi() {
		return column3mi;
	}
	public void setColumn3mi(String column3mi) {
		this.column3mi = column3mi;
	}
	public String getColumn4du() {
		return column4du;
	}
	public void setColumn4du(String column4du) {
		this.column4du = column4du;
	}
	public String getColumn4fe() {
		return column4fe;
	}
	public void setColumn4fe(String column4fe) {
		this.column4fe = column4fe;
	}
	public String getColumn4mi() {
		return column4mi;
	}
	public void setColumn4mi(String column4mi) {
		this.column4mi = column4mi;
	}
	public String getColumn7du() {
		return column7du;
	}
	public void setColumn7du(String column7du) {
		this.column7du = column7du;
	}
	public String getColumn7fe() {
		return column7fe;
	}
	public void setColumn7fe(String column7fe) {
		this.column7fe = column7fe;
	}
	public String getColumn7mi() {
		return column7mi;
	}
	public void setColumn7mi(String column7mi) {
		this.column7mi = column7mi;
	}
	public String getColumn8du() {
		return column8du;
	}
	public void setColumn8du(String column8du) {
		this.column8du = column8du;
	}
	public String getColumn8fe() {
		return column8fe;
	}
	public void setColumn8fe(String column8fe) {
		this.column8fe = column8fe;
	}
	public String getColumn8mi() {
		return column8mi;
	}
	public void setColumn8mi(String column8mi) {
		this.column8mi = column8mi;
	}
	/**
	 * 
	 * 函数介绍：对象翻转，设置属性值
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void setAttribute(TDataLawobj tTDataLawobj){
		TSysUser user = CtxUtil.getCurUser();
		if(StringUtils.isBlank(this.getId())){
			this.setCreateby(user);
			this.setCreated(new Date(System.currentTimeMillis()));
		}
		this.setColumn1(tTDataLawobj.getColumn1());
		this.setColumn2(tTDataLawobj.getColumn2());
		
		String jd = "";
		String wd = "";
		String jdenum = "";
		if(tTDataLawobj.getLawobjtype().equals("01") || tTDataLawobj.getLawobjtype().equals("02") || tTDataLawobj.getLawobjtype().equals("03") || tTDataLawobj.getLawobjtype().equals("04") || tTDataLawobj.getLawobjtype().equals("05") || tTDataLawobj.getLawobjtype().equals("06")){
			jdenum = tTDataLawobj.getLawobjtype()+"08";
		}else {
			jdenum = tTDataLawobj.getLawobjtype()+"07";
		}
		if(jdenum.equals("0208")){
			//把经度的时分秒进行进行合并取值
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn3du())){
				jd += tTDataLawobj.getColumn3du()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn3fe())){
				jd += tTDataLawobj.getColumn3fe()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn3mi())){
				jd += tTDataLawobj.getColumn3mi();
			}
			this.setColumn3(jd);
			//把纬度的时分秒进行进行合并取值
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn4du())){
				wd += tTDataLawobj.getColumn4du()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn4fe())){
				wd += tTDataLawobj.getColumn4fe()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn4mi())){
				wd += tTDataLawobj.getColumn4mi();
			}
			this.setColumn4(wd);
		}else{
			this.setColumn3(tTDataLawobj.getColumn3());
			this.setColumn4(tTDataLawobj.getColumn4());
		}
		if(jdenum.equals("0508")){
			//把经度的时分秒进行进行合并取值
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn4du())){
				jd += tTDataLawobj.getColumn4du()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn4fe())){
				jd += tTDataLawobj.getColumn4fe()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn4mi())){
				jd += tTDataLawobj.getColumn4mi();
			}
			this.setColumn4(jd);
			//把纬度的时分秒进行进行合并取值
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn5du())){
				wd += tTDataLawobj.getColumn5du()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn5fe())){
				wd += tTDataLawobj.getColumn5fe()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn5mi())){
				wd += tTDataLawobj.getColumn5mi();
			}
			this.setColumn5(wd);
		}else{
			if(StringUtils.isBlank(tTDataLawobj.getColumn4mi())){
				this.setColumn4(tTDataLawobj.getColumn4());
			}
			this.setColumn5(tTDataLawobj.getColumn5());
		}
		//1,3,6,7
		if(jdenum.equals("0108") || jdenum.equals("0308") || jdenum.equals("0408") || jdenum.equals("0608") || jdenum.equals("0707")){
			//把经度的时分秒进行进行合并取值
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn5du())){
				jd += tTDataLawobj.getColumn5du()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn5fe())){
				jd += tTDataLawobj.getColumn5fe()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn5mi())){
				jd += tTDataLawobj.getColumn5mi();
			}
			this.setColumn5(jd);
			//把纬度的时分秒进行进行合并取值
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn6du())){
				wd += tTDataLawobj.getColumn6du()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn6fe())){
				wd += tTDataLawobj.getColumn6fe()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn6mi())){
				wd += tTDataLawobj.getColumn6mi();
			}
			this.setColumn6(wd);
		}else{
			if(StringUtils.isBlank(tTDataLawobj.getColumn5mi())){
				this.setColumn5(tTDataLawobj.getColumn5());
			}
			this.setColumn6(tTDataLawobj.getColumn6());
		}
		//8 , 9 ,10 ,11
		if(jdenum.equals("0807") || jdenum.equals("0907") || jdenum.equals("1007") || jdenum.equals("1107")){
			//把经度的时分秒进行进行合并取值
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn7du())){
				jd += tTDataLawobj.getColumn7du()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn7fe())){
				jd += tTDataLawobj.getColumn7fe()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn7mi())){
				jd += tTDataLawobj.getColumn7mi();
			}
			this.setColumn7(jd);
			//把纬度的时分秒进行进行合并取值
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn8du())){
				wd += tTDataLawobj.getColumn8du()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn8fe())){
				wd += tTDataLawobj.getColumn8fe()+",";
			}
			if(StringUtils.isNotBlank(tTDataLawobj.getColumn8mi())){
				wd += tTDataLawobj.getColumn8mi();
			}
			this.setColumn8(wd);
		}else{
			this.setColumn7(tTDataLawobj.getColumn7());
			this.setColumn8(tTDataLawobj.getColumn8());
		}
		this.setColumn9(tTDataLawobj.getColumn9());
		this.setColumn10(tTDataLawobj.getColumn10());
		this.setColumn11(tTDataLawobj.getColumn11());
		this.setColumn12(tTDataLawobj.getColumn12());
		this.setColumn13(tTDataLawobj.getColumn13());
		this.setColumn14(tTDataLawobj.getColumn14());
		this.setColumn15(tTDataLawobj.getColumn15());
		this.setColumn16(tTDataLawobj.getColumn16());
		this.setColumn17(tTDataLawobj.getColumn17());
		this.setColumn18(tTDataLawobj.getColumn18());
		this.setColumn19(tTDataLawobj.getColumn19());
		this.setColumn20(tTDataLawobj.getColumn20());
		this.setColumn21(tTDataLawobj.getColumn21());
		this.setColumn22(tTDataLawobj.getColumn22());
		this.setColumn23(tTDataLawobj.getColumn23());
		this.setColumn24(tTDataLawobj.getColumn24());
		this.setColumn25(tTDataLawobj.getColumn25());
		this.setColumn26(tTDataLawobj.getColumn26());
		this.setColumn27(tTDataLawobj.getColumn27());
		this.setColumn28(tTDataLawobj.getColumn28());
		this.setColumn29(tTDataLawobj.getColumn29());
		this.setColumn30(tTDataLawobj.getColumn30());
		this.setColumn31(tTDataLawobj.getColumn31());
		this.setColumn32(tTDataLawobj.getColumn32());
		this.setColumn33(tTDataLawobj.getColumn33());
		this.setColumn34(tTDataLawobj.getColumn34());
		this.setColumn35(tTDataLawobj.getColumn35());
		this.setColumn36(tTDataLawobj.getColumn36());
		this.setColumn37(tTDataLawobj.getColumn37());
		this.setColumn38(tTDataLawobj.getColumn38());
		this.setColumn39(tTDataLawobj.getColumn39());
		this.setColumn40(tTDataLawobj.getColumn40());
		this.setColumn41(tTDataLawobj.getColumn41());
		this.setColumn42(tTDataLawobj.getColumn42());
		this.setColumn43(tTDataLawobj.getColumn43());
		this.setColumn44(tTDataLawobj.getColumn44());
		this.setColumn45(tTDataLawobj.getColumn45());
		this.setColumn46(tTDataLawobj.getColumn46());
		this.setColumn47(tTDataLawobj.getColumn47());
		this.setColumn48(tTDataLawobj.getColumn48());
		this.setColumn49(tTDataLawobj.getColumn49());
		this.setColumn50(tTDataLawobj.getColumn50());
		this.setIsActive(tTDataLawobj.getIsActive());
		this.setAreaid(user.getAreaId());
		this.setLawobjtype(tTDataLawobj.getLawobjtype());
		this.setUpdateby(user);
		this.setUpdated(new Date(System.currentTimeMillis()));
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}

