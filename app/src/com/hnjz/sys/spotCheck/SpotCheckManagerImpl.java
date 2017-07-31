/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.spotCheck;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.enums.CxlxEnum;
import com.hnjz.app.data.enums.MonthEnum;
import com.hnjz.app.data.enums.PublicColumnEnum;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TBizYearLawobj;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.data.xxgl.yearlawobj.YearLawobjManager;
import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.ZfdxLx;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.TBizTaskandtasktype;
import com.hnjz.app.work.po.Work;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.configCheckProportion.CheckProportionManager;
import com.hnjz.sys.configCheckProportion.QuarterEnum;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TBizCheckedLawobj;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 随机抽选Manager实现
 * @author shiqiuhan
 * @created 2015-12-17,下午03:33:07
 */
@Service("spotCheckManagerImpl")
public class SpotCheckManagerImpl extends ManagerImpl implements SpotCheckManager {
	
	@Autowired
    private LawobjManager lawobjManager;
	
	@Autowired
    private WorkDao                 workDao;
	
	@Autowired
    private OrgManager orgManager;
	
	@Autowired
	private CheckProportionManager checkProportionManager;
	

	@Autowired
	private YearLawobjManager yearLawobjManager;
	
	@Autowired
	private UserManager userManager;
	/**
	 * 开始抽选(季度) 已重构
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 * @return 抽选结果         
	 * @throws Exception
	 */
	public String startSpotCheck(String year, String quarter,String areaid){
				try {
					
					/**
					 * 抽选重点污染源，按照设置的季度比例进行抽选，从年度抽查对象中的重点企业抽取
					 */
					List<TDataLawobj> firstCheckedList = new ArrayList<TDataLawobj>();//声明第一次抽中列表
					List<TDataLawobj> secondCheckedList = new ArrayList<TDataLawobj>();//声明第二次抽中列表
					List<TBizYearLawobj> allLawobjList = yearLawobjManager.queryAllYearLawobj(year,quarter, CxlxEnum.zdqy.getCode(),areaid);//年度抽查对象中所有重点污染源
					int proportion = checkProportionManager.queryProportion(year, quarter);//季度抽查比例
					if(allLawobjList!=null && allLawobjList.size()>0 && proportion!=0){
						int total = allLawobjList.size();//污染源总数
						float d =(float) proportion/100;
						int totalNum = total*d==(int)(total*d)?(int)(total*d):(int)(total*d)+1;
						int firstNum = 0;//第一次抽查数
						int secondNum = 0;//第二次抽查数
						if(total*0.25==(int)(total*0.25)){//总数的25%为整数取整
							firstNum = (int)(total*0.25);
						}else{
							firstNum = (int)(total*0.25)+1;				//总数的25%非整数取整加1
						}
						secondNum = totalNum-firstNum;
						
						//第一次抽查对象（本年度常规抽选时没有被抽查的污染源）
						List<TBizYearLawobj> noCheckedList = yearLawobjManager.queryNoCheckedLawobj(year, quarter,CxlxEnum.zdqy.getCode(),areaid);
						//第二次抽查对象（本次没有被抽中的污染源）
						List<TDataLawobj> thisTimeNocheckedList = new ArrayList<TDataLawobj>();
						//第一次随机抽查，抽查对象：本年度没有被抽到的污染源 
						List<Integer> arr = new ArrayList<Integer>();//被抽中的数值
						for(int i=0;i<firstNum;i++){
							if(noCheckedList!=null && noCheckedList.size()>0){
								if(firstNum>noCheckedList.size()){
									secondNum = secondNum + firstNum - noCheckedList.size();
									firstNum = noCheckedList.size();
								}
								int k = (int) ((Math.random())*(noCheckedList.size()));
								if(!arr.contains(k)){//k不在数组中，表示还未被抽取过
									arr.add(k);
									firstCheckedList.add(noCheckedList.get(k).getLawobj());
								}else{
									i--;//重新抽取
								}
							}
						}
						//把第一次抽查到的污染源插入到被抽查污染源表中
						//this.saveCheckedList(year,quarter,"","0",firstCheckedList,"");
						//得到本次没有被抽查到的污染源列表
						for(int i=0;i<allLawobjList.size();i++){
							int flag=0;
							for(int j=0;j<firstCheckedList.size();j++){
								TDataLawobj lawobj = allLawobjList.get(i).getLawobj();
								TDataLawobj lawobj1 = firstCheckedList.get(j);
								if(lawobj!=null && lawobj1!=null && lawobj.getId().equals(lawobj1.getId())){//本次已被抽中
									flag=1;
									break;
								}
							}
							if(flag==0){//本次未被抽中的加入列表
								thisTimeNocheckedList.add(allLawobjList.get(i).getLawobj());
							}else{
								flag=0;
							}
						}
						//第二次随机抽查，抽查对象：本次没有被抽到的污染源
						List<Integer> arr1 = new ArrayList<Integer>();//被抽中的数值
						for(int i=0;i<secondNum;i++){
							int k = (int) ((Math.random())*(thisTimeNocheckedList.size()));
							if(!arr1.contains(k)){//k不在数组中，表示还未被抽取过
								arr1.add(k);
								secondCheckedList.add(thisTimeNocheckedList.get(k));
							}else{
								i--;//重新抽取
							}
						}
						//把抽查到的污染源插入到被抽查污染源表中
						//this.saveCheckedList(year,quarter,"1",secondCheckedList,"");
					}	
						/**
						 * 抽选一般企业，从年度抽查对象的一般企业中抽选
						 */
						List<TBizYearLawobj> allNormalLawobjList = yearLawobjManager.queryAllYearLawobj(year,quarter, CxlxEnum.ybqy.getCode(),areaid);//年度抽查对象中所有一般污染源
						List<TBizYearLawobj> noCheckedNormalList = yearLawobjManager.queryNoCheckedLawobj(year, quarter, CxlxEnum.ybqy.getCode(), areaid);//年度抽查对象中没抽到的一般污染源
						if(noCheckedNormalList!=null && noCheckedNormalList.size()>0){
							int number = allNormalLawobjList.size();
							List<TDataLawobj> normalCheckedList = new ArrayList<TDataLawobj>();//声明一般企业抽中列表
							int num;
							if(number>=4){
								float n=(float)number/4;//本季度抽取企业数
								if(n==(int)n){//季度抽取企业数为整数,取整数
									num=(int)n;
								}else{
									num=(int)n+1;
								}
								List<Integer> arr1 = new ArrayList<Integer>();//被抽中的数值
								for(int i=0;i<num && i<noCheckedNormalList.size();i++){
									int k = (int) ((Math.random())*(noCheckedNormalList.size()));
									if(!arr1.contains(k)){//k不在数组中，表示还未被抽取过
										arr1.add(k);
										normalCheckedList.add(noCheckedNormalList.get(k).getLawobj());
									}else{
											i--;//重新抽取
									}
								}
								//this.saveCheckedList(year,quarter,"2",normalCheckedList,"");
							}else{
								int k = (int) ((Math.random())*(noCheckedNormalList.size()));
								normalCheckedList.add(noCheckedNormalList.get(k).getLawobj());
								//this.saveCheckedList(year,quarter,"2",normalCheckedList,"");
							}
						}
						/**
						 * 抽选特殊企业，从年度抽查对象的特殊企业中抽选
						 */
						List<TBizYearLawobj> allSpecialLawobjList = yearLawobjManager.queryAllYearLawobj(year,quarter, CxlxEnum.tsqy.getCode(),areaid);//年度抽查对象中所有特殊监管企业
						List<TBizYearLawobj> noCheckedSpecialList = yearLawobjManager.queryNoCheckedLawobj(year, quarter, CxlxEnum.tsqy.getCode(), areaid);//年度抽查对象中没抽到的特殊监管企业
						if(noCheckedSpecialList!=null && noCheckedSpecialList.size()>0){
							int number = allSpecialLawobjList.size();
							List<TDataLawobj> specialCheckedList = new ArrayList<TDataLawobj>();//声明一般企业抽中列表
							int num;	
							if(number>=4){
									float n=(float)number/4;//本季度抽取企业数
									if(n==(int)n){//季度抽取企业数为整数,取整数
										num=(int)n;
									}else{
										num=(int)n+1;
									}
									List<Integer> arr2 = new ArrayList<Integer>();//被抽中的数值
									for(int i=0;i<num && i<noCheckedSpecialList.size();i++){
										int k = (int) ((Math.random())*(noCheckedSpecialList.size()));
										if(!arr2.contains(k)){//k不在数组中，表示还未被抽取过
											arr2.add(k);
											specialCheckedList.add(noCheckedSpecialList.get(k).getLawobj());
										}else{
												i--;//重新抽取
										}
									}
									//this.saveCheckedList(year,quarter,"3",specialCheckedList,"");
							}else{
								int k = (int) ((Math.random())*(noCheckedSpecialList.size()));
								specialCheckedList.add(noCheckedSpecialList.get(k).getLawobj());
								//this.saveCheckedList(year,quarter,"3",specialCheckedList,"");
							}
						}
						return "抽选成功";
				} catch (Exception e) {
					e.printStackTrace();
				}
		return "success";
	}


	/**
	 * @author gaozhiyang
	 * @time 2017-7-6
	 * 
	 * 
	 * 开始抽选(季度)
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 * @return 抽选结果         
	 * @throws Exception
	 **/
	public String startSpotChecknew(String year, String quarter,String areaid){
				try {
					
					/**
					 * 抽选重点污染源，按照设置的季度比例进行抽选，从年度抽查对象中的重点企业抽取
					 */
					List<TDataLawobjf> firstCheckedList = new ArrayList<TDataLawobjf>();//声明第一次抽中列表
					List<TDataLawobjf> secondCheckedList = new ArrayList<TDataLawobjf>();//声明第二次抽中列表
					List<TBizYearLawobj> allLawobjList = yearLawobjManager.queryAllYearLawobj(year,quarter, CxlxEnum.zdqy.getCode(),areaid);//年度抽查对象中所有重点污染源
					int proportion = checkProportionManager.queryProportion(year, quarter);//季度抽查比例
					if(allLawobjList!=null && allLawobjList.size()>0 && proportion!=0){
						int total = allLawobjList.size();//污染源总数
						float d =(float) proportion/100;
						int totalNum = total*d==(int)(total*d)?(int)(total*d):(int)(total*d)+1;
						int firstNum = 0;//第一次抽查数
						int secondNum = 0;//第二次抽查数
						if(total*0.25==(int)(total*0.25)){//总数的25%为整数取整
							firstNum = (int)(total*0.25);
						}else{
							firstNum = (int)(total*0.25)+1;				//总数的25%非整数取整加1
						}
						secondNum = totalNum-firstNum;
						
						//第一次抽查对象（本年度常规抽选时没有被抽查的污染源）
						List<TBizYearLawobj> noCheckedList = yearLawobjManager.queryNoCheckedLawobj(year, quarter,CxlxEnum.zdqy.getCode(),areaid);
						//第二次抽查对象（本次没有被抽中的污染源）
						List<TDataLawobjf> thisTimeNocheckedList = new ArrayList<TDataLawobjf>();
						//第一次随机抽查，抽查对象：本年度没有被抽到的污染源 
						List<Integer> arr = new ArrayList<Integer>();//被抽中的数值
						for(int i=0;i<firstNum;i++){
							if(noCheckedList!=null && noCheckedList.size()>0){
								if(firstNum>noCheckedList.size()){
									secondNum = secondNum + firstNum - noCheckedList.size();
									firstNum = noCheckedList.size();
								}
								int k = (int) ((Math.random())*(noCheckedList.size()));
								if(!arr.contains(k)){//k不在数组中，表示还未被抽取过
									arr.add(k);
									firstCheckedList.add(noCheckedList.get(k).getLawobjf());
								}else{
									i--;//重新抽取
								}
							}
						}
						//把第一次抽查到的污染源插入到被抽查污染源表中
						this.saveCheckedListnew(year,quarter,"0",firstCheckedList,"");
						//得到本次没有被抽查到的污染源列表
						for(int i=0;i<allLawobjList.size();i++){
							int flag=0;
							for(int j=0;j<firstCheckedList.size();j++){
								TDataLawobjf lawobj = allLawobjList.get(i).getLawobjf();
								TDataLawobjf lawobj1 = firstCheckedList.get(j);
								if(lawobj!=null && lawobj1!=null && lawobj.getId().equals(lawobj1.getId())){//本次已被抽中
									flag=1;
									break;
								}
							}
							if(flag==0){//本次未被抽中的加入列表
								thisTimeNocheckedList.add(allLawobjList.get(i).getLawobjf());
							}else{
								flag=0;
							}
						}
						//第二次随机抽查，抽查对象：本次没有被抽到的污染源
						List<Integer> arr1 = new ArrayList<Integer>();//被抽中的数值
						for(int i=0;i<secondNum;i++){
							int k = (int) ((Math.random())*(thisTimeNocheckedList.size()));
							if(!arr1.contains(k)){//k不在数组中，表示还未被抽取过
								arr1.add(k);
								secondCheckedList.add(thisTimeNocheckedList.get(k));
							}else{
								i--;//重新抽取
							}
						}
						//把抽查到的污染源插入到被抽查污染源表中
						this.saveCheckedListnew(year,quarter,"1",secondCheckedList,"");
					}	
						/**
						 * 抽选一般企业，从年度抽查对象的一般企业中抽选
						 */
						List<TBizYearLawobj> allNormalLawobjList = yearLawobjManager.queryAllYearLawobj(year,quarter, CxlxEnum.ybqy.getCode(),areaid);//年度抽查对象中所有一般污染源
						List<TBizYearLawobj> noCheckedNormalList = yearLawobjManager.queryNoCheckedLawobj(year, quarter, CxlxEnum.ybqy.getCode(), areaid);//年度抽查对象中没抽到的一般污染源
						if(noCheckedNormalList!=null && noCheckedNormalList.size()>0){
							int number = allNormalLawobjList.size();
							List<TDataLawobj> normalCheckedList = new ArrayList<TDataLawobj>();//声明一般企业抽中列表
							int num;
							if(number>=4){
								float n=(float)number/4;//本季度抽取企业数
								if(n==(int)n){//季度抽取企业数为整数,取整数
									num=(int)n;
								}else{
									num=(int)n+1;
								}
								List<Integer> arr1 = new ArrayList<Integer>();//被抽中的数值
								for(int i=0;i<num && i<noCheckedNormalList.size();i++){
									int k = (int) ((Math.random())*(noCheckedNormalList.size()));
									if(!arr1.contains(k)){//k不在数组中，表示还未被抽取过
										arr1.add(k);
										normalCheckedList.add(noCheckedNormalList.get(k).getLawobj());
									}else{
											i--;//重新抽取
									}
								}
								//this.saveCheckedList(year,quarter,"2",normalCheckedList,"");
							}else{
								int k = (int) ((Math.random())*(noCheckedNormalList.size()));
								normalCheckedList.add(noCheckedNormalList.get(k).getLawobj());
								//this.saveCheckedList(year,quarter,"2",normalCheckedList,"");
							}
						}
						/**
						 * 抽选特殊企业，从年度抽查对象的特殊企业中抽选
						 */
						List<TBizYearLawobj> allSpecialLawobjList = yearLawobjManager.queryAllYearLawobj(year,quarter, CxlxEnum.tsqy.getCode(),areaid);//年度抽查对象中所有特殊监管企业
						List<TBizYearLawobj> noCheckedSpecialList = yearLawobjManager.queryNoCheckedLawobj(year, quarter, CxlxEnum.tsqy.getCode(), areaid);//年度抽查对象中没抽到的特殊监管企业
						if(noCheckedSpecialList!=null && noCheckedSpecialList.size()>0){
							int number = allSpecialLawobjList.size();
							List<TDataLawobj> specialCheckedList = new ArrayList<TDataLawobj>();//声明一般企业抽中列表
							int num;	
							if(number>=4){
									float n=(float)number/4;//本季度抽取企业数
									if(n==(int)n){//季度抽取企业数为整数,取整数
										num=(int)n;
									}else{
										num=(int)n+1;
									}
									List<Integer> arr2 = new ArrayList<Integer>();//被抽中的数值
									for(int i=0;i<num && i<noCheckedSpecialList.size();i++){
										int k = (int) ((Math.random())*(noCheckedSpecialList.size()));
										if(!arr2.contains(k)){//k不在数组中，表示还未被抽取过
											arr2.add(k);
											specialCheckedList.add(noCheckedSpecialList.get(k).getLawobj());
										}else{
												i--;//重新抽取
										}
									}
									//this.saveCheckedList(year,quarter,"3",specialCheckedList,"");
							}else{
								int k = (int) ((Math.random())*(noCheckedSpecialList.size()));
								specialCheckedList.add(noCheckedSpecialList.get(k).getLawobj());
								//this.saveCheckedList(year,quarter,"3",specialCheckedList,"");
							}
						}
						return "抽选成功";
				} catch (Exception e) {
					e.printStackTrace();
				}
		return "success";
	}
	//删除抽选结果(年度)
	public void deleteResult(String year){
		StringBuilder sql = new StringBuilder();
		String areaid = CtxUtil.getAreaId();
		sql.append(" from TBizYearLawobj where year=? and area.id = ?");
		this.dao.removeFindObjs(sql.toString(), year,areaid);
	}
	/**
	 * 开始抽选(年度)    
	 * 
	 * @param year
	 *            年份
	 * @return 抽选结果         
	 * @throws Exception
	 */
	public String startYearCheck(String year){
				try {
						/**
						 * 抽选说明：国省控、重点企业（只有工业污染源有国省控重点企业）全部列入年度被抽查对象；
						 * 一般企业(除重点企业外的所有执法对象)按监察人员1:5的数量抽取企业；
						 * 特殊企业（被投诉、违法被调查）全部列入年度被抽查清单
						 */
						List<TDataLawobj> keyLawobjList = new ArrayList<TDataLawobj>();//声明重点企业列表
						List<TDataLawobj> normalLawobjList = new ArrayList<TDataLawobj>();//声明一般企业列表
						List<TDataLawobj> specialLawobjList = new ArrayList<TDataLawobj>();//声明特殊企业列表
						
						keyLawobjList = lawobjManager.queryAllKeyLawobjList();//所有重点污染源
						//把抽查到的重点企业插入到年度抽查对象表中
						yearLawobjManager.saveYearLawobjList(keyLawobjList, year,CxlxEnum.zdqy.getCode());
						specialLawobjList = lawobjManager.queryIllegalLawobjList(year);//所有违法被调查及被投诉企业
						//把特殊企业插入到年度抽查对象表中
						yearLawobjManager.saveYearLawobjList(specialLawobjList, year,CxlxEnum.tsqy.getCode());
						normalLawobjList = lawobjManager.queryAllNormalList();//所有一般企业
						int number = userManager.queryUserNumber();//系统内本区域注册用户
						List<TDataLawobj> checkedList = new ArrayList<TDataLawobj>();//声明抽选到的一般企业
						if(number>1){
							number=(number)*5;//系统内监察员数乘5得到一般企业抽查数
							if(normalLawobjList!=null && normalLawobjList.size()>0){
								if(normalLawobjList.size()>number){//一般企业数大于抽查数，做随机抽查
									List<Integer> arr = new ArrayList<Integer>();//被抽中的数值
									for(int i=0;i<number;i++){
										int k = (int) ((Math.random())*(normalLawobjList.size()));
										if(!arr.contains(k)){//k不在数组中，表示还未被抽取过
											arr.add(k);
											checkedList.add(normalLawobjList.get(k));
										}else{
											i--;//重新抽取
										}
									}
								}else{//一般企业总数小于等于抽查数，全部加入抽查列表
									checkedList.addAll(normalLawobjList);
								}
							}
						}
						//把特殊企业插入到年度抽查对象表中
						yearLawobjManager.saveYearLawobjList(checkedList, year,CxlxEnum.ybqy.getCode());
						return "抽选成功";
				} catch (Exception e) {
					e.printStackTrace();
				}
		return "success";
	}
	/**
	 * 生成任务
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 * @return 抽选结果         
	 * @throws Exception
	 */
	public String createWork(String year, String quarter){
		//根据年份季度查询被抽中的企业列表
		List<TBizCheckedLawobj> checkedList= this.queryCheckedList(year,quarter);
		TSysUser admin = userManager.getUser("a0000000000000000000000000000000");
		if(checkedList!=null && checkedList.size()>0){
			if(checkedList.get(0).getTask()==null){
				//将抽查到的企业生成随机任务派发给各企业的监管部门领导
				for(int i=0;i<checkedList.size();i++){
					Work work = new Work();
					String orgId;
					try {
						orgId = lawobjManager.getOrgidByLawobj(checkedList.get(i).getLawobj());
						if(StringUtils.isNotBlank(orgId)){
							TSysUser user = orgManager.getLeaderByOrg(orgId);
							if(user!=null){
								work.setCreateUser(user);//创建人：部门领导
								work.setDjrId(user.getId());//登记人id
								work.setDjrName(user.getName());//登记人name
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					TDataLawobj tDataLawobj = (TDataLawobj)dao.get(TDataLawobj.class,checkedList.get(i).getLawobj().getId());
					work.setName("随机任务");	//任务名称
					work.setWorkNote("随机任务"); //任务内容
					work.setSource("11");//任务来源：随机抽查
					work.setCreateTime(new Date());//创建时间
					work.setEmergency("1");//紧急程度:一般
					work.setSecurity("3");//任务密级:机密
					work.setAreaid(tDataLawobj.getAreaid());//随机任务加区域
					Calendar endC = Calendar.getInstance();
	                endC.add(Calendar.DATE, 20);//默认紧急程度一般20天
	        		work.setEndTime(endC.getTime());
					work.setIsActive(YnEnum.Y.getCode());//状态
					work.setZfdxType(tDataLawobj.getLawobjtype());
				    //保存WORK对象
					work = (Work) workDao.save(work);
					
				    //保存任务和执法对象的关联
				    String lawobjname = lawobjManager.getLawobjColumnValue(tDataLawobj.getLawobjtype()
										+PublicColumnEnum.mc.getCode(), tDataLawobj.getId());//执法对象名称
				    String address = lawobjManager.getLawobjColumnValue(tDataLawobj.getLawobjtype()
							+PublicColumnEnum.dz.getCode(), tDataLawobj.getId());//执法对象地址
				    String regionId = lawobjManager.getLawobjColumnValue(tDataLawobj
							.getLawobjtype()
							+ PublicColumnEnum.ssxzq.getCode(), tDataLawobj.getId());//所属区域
				    String fddbr = lawobjManager.getLawobjColumnValue(tDataLawobj
							.getLawobjtype()
							+ PublicColumnEnum.fddbr.getCode(), tDataLawobj.getId());//法定代表人
				    String fddbrlxdh = lawobjManager.getLawobjColumnValue(tDataLawobj
							.getLawobjtype()
							+ PublicColumnEnum.fddbrlxdh.getCode(), tDataLawobj.getId());//法定代表人联系电话
				    String hbfzr = lawobjManager.getLawobjColumnValue(tDataLawobj.getLawobjtype()
							+ PublicColumnEnum.hbfzr.getCode(), tDataLawobj.getId());//环保负责人
				    String hbfzrlxdh = lawobjManager.getLawobjColumnValue(tDataLawobj.getLawobjtype()
							+ PublicColumnEnum.hbfzrlxdh.getCode(), tDataLawobj.getId());//环保负责人联系电话
					TBizTaskandlawobj tBizTaskandlawobj = new TBizTaskandlawobj(
							work.getId(), tDataLawobj.getLawobjtype(), tDataLawobj.getId(), lawobjname, regionId,
							address, fddbr, fddbrlxdh, hbfzr, "", hbfzrlxdh, "", "法定代表人");
					tBizTaskandlawobj.setUpdateby(admin);
					tBizTaskandlawobj.setCreateby(admin);
				    this.dao.save(tBizTaskandlawobj);
				    
				    //保存任务和任务类型关联
				    TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
				    tBizTaskandtasktype.setTaskid(work.getId());//任务id
				    tBizTaskandtasktype.setTasktypeid(TaskTypeCode.RCJC.getCode());//任务类型：现场监察
				    tBizTaskandtasktype.setIsActive(YnEnum.Y.getCode());//状态
				    tBizTaskandtasktype.setCreated(new Date());//创建时间
				    tBizTaskandtasktype.setCreateby(admin);//创建人
				    tBizTaskandtasktype.setUpdateby(admin);
				    tBizTaskandtasktype.setUpdated(new Date());
				    this.dao.save(tBizTaskandtasktype);
				    
				    //把抽中列表中任务id与生成的任务关联
				    TBizCheckedLawobj tBizCheckedLawobj = checkedList.get(i);
				    tBizCheckedLawobj.setTask(work);
				    this.dao.save(tBizCheckedLawobj);//更新抽中列表
				}
				return "任务生成完成，任务已派发到各部门领导";
			}else{
				return "该季度任务已生成，请勿重复生成";
			}
		}else{
			return "该季度尚未抽选企业";
		}
	}

	
	/**
	 * 保存被抽中污染源列表
	 * @throws Exception 
	 */
	@Override
	public List<TBizCheckedLawobj> saveCheckedListnew(String year, String quarter, String type, List<TDataLawobjf> checkedList,String areaId) throws Exception{
		//TSysUser user = CtxUtil.getCurUser();
		List<TBizCheckedLawobj> lists=new ArrayList<TBizCheckedLawobj>();
		TSysUser user = userManager.getUser("a0000000000000000000000000000000");
		Date cur = new Date();
		if(checkedList!=null &&checkedList.size()>0){
			for(int i=0;i<checkedList.size();i++){
				TBizCheckedLawobj checkedLawobj = new TBizCheckedLawobj();
				checkedLawobj.setYear(year);
				checkedLawobj.setQuarter(quarter);
				checkedLawobj.setIsActive("Y");
				checkedLawobj.setLawobjf(checkedList.get(i));
				if(checkedList.get(i)!=null){
					checkedLawobj.setLawobjType(checkedList.get(i).getLawobjtype().getName());
					//checkedLawobj.setLawobjName(checkedList.get(i).getName());
					checkedLawobj.setLawobjName(checkedList.get(i).getDwmc());
				}
				checkedLawobj.setCreated(new Date());
				checkedLawobj.setCreateby(user);
				checkedLawobj.setCreated(cur);
				checkedLawobj.setUpdateby(user);
				checkedLawobj.setUpdated(cur);
				checkedLawobj.setType(type);
				checkedLawobj.setAreaId(areaId);
				checkedLawobj.setSfypf("N");
				checkedLawobj=(TBizCheckedLawobj)this.save(checkedLawobj);
				lists.add(checkedLawobj);
			}
		}
		return lists;
	}


	
	
	
	
	/**
	 * 保存被抽中污染源列表
	 * @throws Exception 
	 */
	@Override
	public List<TBizCheckedLawobj> saveCheckedList(String year, String quarter, String month,String type, List<TDataLawobjf> checkedList,String areaId) throws Exception{
		//TSysUser user = CtxUtil.getCurUser();
		List<TBizCheckedLawobj> lists=new ArrayList<TBizCheckedLawobj>();
		TSysUser user = userManager.getUser("a0000000000000000000000000000000");
		Date cur = new Date();
		if(checkedList!=null &&checkedList.size()>0){
			for(int i=0;i<checkedList.size();i++){
				TBizCheckedLawobj checkedLawobj = new TBizCheckedLawobj();
				checkedLawobj.setYear(year);
				checkedLawobj.setQuarter(quarter);
				checkedLawobj.setIsActive("Y");
				checkedLawobj.setLawobjf(checkedList.get(i));
				if(checkedList.get(i)!=null){
					TDataLawobjtype tlt=(TDataLawobjtype)this.get(TDataLawobjtype.class, checkedList.get(i).getLawobjtype().getId());
					checkedLawobj.setLawobjType(tlt.getName());
					//checkedLawobj.setLawobjName(checkedList.get(i).getName());
					checkedLawobj.setLawobjName(checkedList.get(i).getDwmc());
				}
				checkedLawobj.setCreated(new Date());
				checkedLawobj.setCreateby(user);
				checkedLawobj.setCreated(cur);
				checkedLawobj.setUpdateby(user);
				checkedLawobj.setUpdated(cur);
				checkedLawobj.setType(type);
				checkedLawobj.setAreaId(areaId);
				checkedLawobj.setMonth(month);
				checkedLawobj.setSfypf("N");
				checkedLawobj=(TBizCheckedLawobj)this.save(checkedLawobj);
				lists.add(checkedLawobj);
			}
		}
		return lists;
	}

	/**
	 * 根据年份季度查询被抽中污染源列表
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 * @return 抽选结果          
	 * @throws Exception
	 */
	public List<TBizCheckedLawobj> queryCheckedList(String year, String quarter){
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append(" from TBizCheckedLawobj where 1=1 ");
		//年份
		if (StringUtils.isNotBlank(year)) {
			sql.append(" and year = :year ");
			data.put("year", year);
		}
		//季度
		if (StringUtils.isNotBlank(quarter)) {
			sql.append(" and quarter = :quarter ");
			data.put("quarter", quarter);
		}
		//是否有效
		sql.append(" and isActive = :isActive ");
		data.put("isActive", YnEnum.Y.getCode());
		sql.append(" order by year,quarter,type");
		List<TBizCheckedLawobj> checkedLawobjList = dao.find(sql.toString(), data);
		return checkedLawobjList;
	}
	
	/**
	 * 查询被抽中污染源列表（分页）
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 * @return 抽选结果          
	 * @throws Exception 
	 * @throws Exception
	 */
	public FyWebResult queryCheckedLawobj(String year, String month,String areaid,String page, String pageSize) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct c.id_,c.year_,c.month_,c.lawobjid_,c.type_,c.taskid_,c.sfypf_,l.lawobjtypeid_ from T_Biz_CheckedLawobj c ");
		sql.append(" left join t_Data_Lawobjf l on l.id_ = c.lawobjid_ where c.isActive_='Y'");
		//sql.append(" left join T_Biz_YearLawobj y on c.lawobjid_ = y.lawobjid_  where y.isActive_='Y'");
		//年份
		if (StringUtils.isNotBlank(year)) {
			sql.append(" and c.year_ = :year ");
			data.put("year", year);
		}
		//季度
		if (StringUtils.isNotBlank(month)) {
			sql.append(" and c.month_ = :month ");
			data.put("month", month);
		}
		//所属区域
		if(StringUtil.isBlank(areaid)){
			areaid = CtxUtil.getAreaId();
		}
		//String areaid = CtxUtil.getAreaId();
		if(StringUtils.isNotBlank(areaid)){
				sql.append(" and c.areaid_ = :areaid ");
				data.put("areaid", areaid);
		}
		
		//是否有效
		//sql.append(" and c.isActive_ = :isActive ");
		//data.put("isActive", YnEnum.Y.getCode());
		sql.append(" order by c.year_,c.month_,c.type_,l.lawobjtypeid_");
		FyResult pos = dao.find(sql.toString(),Integer.parseInt(page),pageSize==null?0:Integer.parseInt(pageSize),data);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<Object[]> checkedLawobjs = pos.getRe();
		Map<String, String> row = null;
		for (Object[] obj : checkedLawobjs) {
			row = new HashMap<String, String>();
			row.put("id", String.valueOf(obj[0]));
			row.put("year", String.valueOf(obj[1])+"年");
			if(StringUtils.isNotBlank(String.valueOf(obj[2]))){
				row.put("month", MonthEnum.getNameByCode(String.valueOf(obj[2])));
			}else{
				row.put("month","");
			}
			row.put("ssjgbm", "");
			if(StringUtils.isNotBlank(String.valueOf(obj[3]))){
				TDataLawobjf lawobj = (TDataLawobjf) this.get(TDataLawobjf.class, String.valueOf(obj[3]));
				if(lawobj!=null){
					row.put("qymc", lawobj.getDwmc());
					TDataLawobjtype tlt=(TDataLawobjtype)this.get(TDataLawobjtype.class, lawobj.getLawobjtype().getId());
					row.put("zfdxType", tlt.getName());
					String orgId;
					orgId = lawobj.getSsjgbm();//所属监管部门
					if(StringUtils.isNotBlank(orgId)){
						TSysOrg org = (TSysOrg) this.get(TSysOrg.class, orgId);
						if(org!=null){
							row.put("ssjgbm", org.getName());
						}
					}
				}
				else{
					row.put("qymc", "");
					row.put("zfdxType", "");
				}
			}
			//抽选类型
			if("2".equals(String.valueOf(obj[4]))){
				row.put("cxlx", "一般企业");
			}else if("3".equals(String.valueOf(obj[4]))){
				row.put("cxlx", "特殊企业");
			}else{
				row.put("cxlx", "重点企业");
			}
			if(String.valueOf(obj[6]).equals("Y")){
				row.put("isCreated","是");
			}else{
				row.put("isCreated","否");
			}
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}
}
