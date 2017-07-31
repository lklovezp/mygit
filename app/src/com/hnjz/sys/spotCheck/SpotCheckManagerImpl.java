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
 * �����ѡManagerʵ��
 * @author shiqiuhan
 * @created 2015-12-17,����03:33:07
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
	 * ��ʼ��ѡ(����) ���ع�
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 * @return ��ѡ���         
	 * @throws Exception
	 */
	public String startSpotCheck(String year, String quarter,String areaid){
				try {
					
					/**
					 * ��ѡ�ص���ȾԴ���������õļ��ȱ������г�ѡ������ȳ������е��ص���ҵ��ȡ
					 */
					List<TDataLawobj> firstCheckedList = new ArrayList<TDataLawobj>();//������һ�γ����б�
					List<TDataLawobj> secondCheckedList = new ArrayList<TDataLawobj>();//�����ڶ��γ����б�
					List<TBizYearLawobj> allLawobjList = yearLawobjManager.queryAllYearLawobj(year,quarter, CxlxEnum.zdqy.getCode(),areaid);//��ȳ������������ص���ȾԴ
					int proportion = checkProportionManager.queryProportion(year, quarter);//���ȳ�����
					if(allLawobjList!=null && allLawobjList.size()>0 && proportion!=0){
						int total = allLawobjList.size();//��ȾԴ����
						float d =(float) proportion/100;
						int totalNum = total*d==(int)(total*d)?(int)(total*d):(int)(total*d)+1;
						int firstNum = 0;//��һ�γ����
						int secondNum = 0;//�ڶ��γ����
						if(total*0.25==(int)(total*0.25)){//������25%Ϊ����ȡ��
							firstNum = (int)(total*0.25);
						}else{
							firstNum = (int)(total*0.25)+1;				//������25%������ȡ����1
						}
						secondNum = totalNum-firstNum;
						
						//��һ�γ����󣨱���ȳ����ѡʱû�б�������ȾԴ��
						List<TBizYearLawobj> noCheckedList = yearLawobjManager.queryNoCheckedLawobj(year, quarter,CxlxEnum.zdqy.getCode(),areaid);
						//�ڶ��γ����󣨱���û�б����е���ȾԴ��
						List<TDataLawobj> thisTimeNocheckedList = new ArrayList<TDataLawobj>();
						//��һ�������飬�����󣺱����û�б��鵽����ȾԴ 
						List<Integer> arr = new ArrayList<Integer>();//�����е���ֵ
						for(int i=0;i<firstNum;i++){
							if(noCheckedList!=null && noCheckedList.size()>0){
								if(firstNum>noCheckedList.size()){
									secondNum = secondNum + firstNum - noCheckedList.size();
									firstNum = noCheckedList.size();
								}
								int k = (int) ((Math.random())*(noCheckedList.size()));
								if(!arr.contains(k)){//k���������У���ʾ��δ����ȡ��
									arr.add(k);
									firstCheckedList.add(noCheckedList.get(k).getLawobj());
								}else{
									i--;//���³�ȡ
								}
							}
						}
						//�ѵ�һ�γ�鵽����ȾԴ���뵽�������ȾԴ����
						//this.saveCheckedList(year,quarter,"","0",firstCheckedList,"");
						//�õ�����û�б���鵽����ȾԴ�б�
						for(int i=0;i<allLawobjList.size();i++){
							int flag=0;
							for(int j=0;j<firstCheckedList.size();j++){
								TDataLawobj lawobj = allLawobjList.get(i).getLawobj();
								TDataLawobj lawobj1 = firstCheckedList.get(j);
								if(lawobj!=null && lawobj1!=null && lawobj.getId().equals(lawobj1.getId())){//�����ѱ�����
									flag=1;
									break;
								}
							}
							if(flag==0){//����δ�����еļ����б�
								thisTimeNocheckedList.add(allLawobjList.get(i).getLawobj());
							}else{
								flag=0;
							}
						}
						//�ڶ��������飬�����󣺱���û�б��鵽����ȾԴ
						List<Integer> arr1 = new ArrayList<Integer>();//�����е���ֵ
						for(int i=0;i<secondNum;i++){
							int k = (int) ((Math.random())*(thisTimeNocheckedList.size()));
							if(!arr1.contains(k)){//k���������У���ʾ��δ����ȡ��
								arr1.add(k);
								secondCheckedList.add(thisTimeNocheckedList.get(k));
							}else{
								i--;//���³�ȡ
							}
						}
						//�ѳ�鵽����ȾԴ���뵽�������ȾԴ����
						//this.saveCheckedList(year,quarter,"1",secondCheckedList,"");
					}	
						/**
						 * ��ѡһ����ҵ������ȳ������һ����ҵ�г�ѡ
						 */
						List<TBizYearLawobj> allNormalLawobjList = yearLawobjManager.queryAllYearLawobj(year,quarter, CxlxEnum.ybqy.getCode(),areaid);//��ȳ�����������һ����ȾԴ
						List<TBizYearLawobj> noCheckedNormalList = yearLawobjManager.queryNoCheckedLawobj(year, quarter, CxlxEnum.ybqy.getCode(), areaid);//��ȳ�������û�鵽��һ����ȾԴ
						if(noCheckedNormalList!=null && noCheckedNormalList.size()>0){
							int number = allNormalLawobjList.size();
							List<TDataLawobj> normalCheckedList = new ArrayList<TDataLawobj>();//����һ����ҵ�����б�
							int num;
							if(number>=4){
								float n=(float)number/4;//�����ȳ�ȡ��ҵ��
								if(n==(int)n){//���ȳ�ȡ��ҵ��Ϊ����,ȡ����
									num=(int)n;
								}else{
									num=(int)n+1;
								}
								List<Integer> arr1 = new ArrayList<Integer>();//�����е���ֵ
								for(int i=0;i<num && i<noCheckedNormalList.size();i++){
									int k = (int) ((Math.random())*(noCheckedNormalList.size()));
									if(!arr1.contains(k)){//k���������У���ʾ��δ����ȡ��
										arr1.add(k);
										normalCheckedList.add(noCheckedNormalList.get(k).getLawobj());
									}else{
											i--;//���³�ȡ
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
						 * ��ѡ������ҵ������ȳ������������ҵ�г�ѡ
						 */
						List<TBizYearLawobj> allSpecialLawobjList = yearLawobjManager.queryAllYearLawobj(year,quarter, CxlxEnum.tsqy.getCode(),areaid);//��ȳ�������������������ҵ
						List<TBizYearLawobj> noCheckedSpecialList = yearLawobjManager.queryNoCheckedLawobj(year, quarter, CxlxEnum.tsqy.getCode(), areaid);//��ȳ�������û�鵽����������ҵ
						if(noCheckedSpecialList!=null && noCheckedSpecialList.size()>0){
							int number = allSpecialLawobjList.size();
							List<TDataLawobj> specialCheckedList = new ArrayList<TDataLawobj>();//����һ����ҵ�����б�
							int num;	
							if(number>=4){
									float n=(float)number/4;//�����ȳ�ȡ��ҵ��
									if(n==(int)n){//���ȳ�ȡ��ҵ��Ϊ����,ȡ����
										num=(int)n;
									}else{
										num=(int)n+1;
									}
									List<Integer> arr2 = new ArrayList<Integer>();//�����е���ֵ
									for(int i=0;i<num && i<noCheckedSpecialList.size();i++){
										int k = (int) ((Math.random())*(noCheckedSpecialList.size()));
										if(!arr2.contains(k)){//k���������У���ʾ��δ����ȡ��
											arr2.add(k);
											specialCheckedList.add(noCheckedSpecialList.get(k).getLawobj());
										}else{
												i--;//���³�ȡ
										}
									}
									//this.saveCheckedList(year,quarter,"3",specialCheckedList,"");
							}else{
								int k = (int) ((Math.random())*(noCheckedSpecialList.size()));
								specialCheckedList.add(noCheckedSpecialList.get(k).getLawobj());
								//this.saveCheckedList(year,quarter,"3",specialCheckedList,"");
							}
						}
						return "��ѡ�ɹ�";
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
	 * ��ʼ��ѡ(����)
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 * @return ��ѡ���         
	 * @throws Exception
	 **/
	public String startSpotChecknew(String year, String quarter,String areaid){
				try {
					
					/**
					 * ��ѡ�ص���ȾԴ���������õļ��ȱ������г�ѡ������ȳ������е��ص���ҵ��ȡ
					 */
					List<TDataLawobjf> firstCheckedList = new ArrayList<TDataLawobjf>();//������һ�γ����б�
					List<TDataLawobjf> secondCheckedList = new ArrayList<TDataLawobjf>();//�����ڶ��γ����б�
					List<TBizYearLawobj> allLawobjList = yearLawobjManager.queryAllYearLawobj(year,quarter, CxlxEnum.zdqy.getCode(),areaid);//��ȳ������������ص���ȾԴ
					int proportion = checkProportionManager.queryProportion(year, quarter);//���ȳ�����
					if(allLawobjList!=null && allLawobjList.size()>0 && proportion!=0){
						int total = allLawobjList.size();//��ȾԴ����
						float d =(float) proportion/100;
						int totalNum = total*d==(int)(total*d)?(int)(total*d):(int)(total*d)+1;
						int firstNum = 0;//��һ�γ����
						int secondNum = 0;//�ڶ��γ����
						if(total*0.25==(int)(total*0.25)){//������25%Ϊ����ȡ��
							firstNum = (int)(total*0.25);
						}else{
							firstNum = (int)(total*0.25)+1;				//������25%������ȡ����1
						}
						secondNum = totalNum-firstNum;
						
						//��һ�γ����󣨱���ȳ����ѡʱû�б�������ȾԴ��
						List<TBizYearLawobj> noCheckedList = yearLawobjManager.queryNoCheckedLawobj(year, quarter,CxlxEnum.zdqy.getCode(),areaid);
						//�ڶ��γ����󣨱���û�б����е���ȾԴ��
						List<TDataLawobjf> thisTimeNocheckedList = new ArrayList<TDataLawobjf>();
						//��һ�������飬�����󣺱����û�б��鵽����ȾԴ 
						List<Integer> arr = new ArrayList<Integer>();//�����е���ֵ
						for(int i=0;i<firstNum;i++){
							if(noCheckedList!=null && noCheckedList.size()>0){
								if(firstNum>noCheckedList.size()){
									secondNum = secondNum + firstNum - noCheckedList.size();
									firstNum = noCheckedList.size();
								}
								int k = (int) ((Math.random())*(noCheckedList.size()));
								if(!arr.contains(k)){//k���������У���ʾ��δ����ȡ��
									arr.add(k);
									firstCheckedList.add(noCheckedList.get(k).getLawobjf());
								}else{
									i--;//���³�ȡ
								}
							}
						}
						//�ѵ�һ�γ�鵽����ȾԴ���뵽�������ȾԴ����
						this.saveCheckedListnew(year,quarter,"0",firstCheckedList,"");
						//�õ�����û�б���鵽����ȾԴ�б�
						for(int i=0;i<allLawobjList.size();i++){
							int flag=0;
							for(int j=0;j<firstCheckedList.size();j++){
								TDataLawobjf lawobj = allLawobjList.get(i).getLawobjf();
								TDataLawobjf lawobj1 = firstCheckedList.get(j);
								if(lawobj!=null && lawobj1!=null && lawobj.getId().equals(lawobj1.getId())){//�����ѱ�����
									flag=1;
									break;
								}
							}
							if(flag==0){//����δ�����еļ����б�
								thisTimeNocheckedList.add(allLawobjList.get(i).getLawobjf());
							}else{
								flag=0;
							}
						}
						//�ڶ��������飬�����󣺱���û�б��鵽����ȾԴ
						List<Integer> arr1 = new ArrayList<Integer>();//�����е���ֵ
						for(int i=0;i<secondNum;i++){
							int k = (int) ((Math.random())*(thisTimeNocheckedList.size()));
							if(!arr1.contains(k)){//k���������У���ʾ��δ����ȡ��
								arr1.add(k);
								secondCheckedList.add(thisTimeNocheckedList.get(k));
							}else{
								i--;//���³�ȡ
							}
						}
						//�ѳ�鵽����ȾԴ���뵽�������ȾԴ����
						this.saveCheckedListnew(year,quarter,"1",secondCheckedList,"");
					}	
						/**
						 * ��ѡһ����ҵ������ȳ������һ����ҵ�г�ѡ
						 */
						List<TBizYearLawobj> allNormalLawobjList = yearLawobjManager.queryAllYearLawobj(year,quarter, CxlxEnum.ybqy.getCode(),areaid);//��ȳ�����������һ����ȾԴ
						List<TBizYearLawobj> noCheckedNormalList = yearLawobjManager.queryNoCheckedLawobj(year, quarter, CxlxEnum.ybqy.getCode(), areaid);//��ȳ�������û�鵽��һ����ȾԴ
						if(noCheckedNormalList!=null && noCheckedNormalList.size()>0){
							int number = allNormalLawobjList.size();
							List<TDataLawobj> normalCheckedList = new ArrayList<TDataLawobj>();//����һ����ҵ�����б�
							int num;
							if(number>=4){
								float n=(float)number/4;//�����ȳ�ȡ��ҵ��
								if(n==(int)n){//���ȳ�ȡ��ҵ��Ϊ����,ȡ����
									num=(int)n;
								}else{
									num=(int)n+1;
								}
								List<Integer> arr1 = new ArrayList<Integer>();//�����е���ֵ
								for(int i=0;i<num && i<noCheckedNormalList.size();i++){
									int k = (int) ((Math.random())*(noCheckedNormalList.size()));
									if(!arr1.contains(k)){//k���������У���ʾ��δ����ȡ��
										arr1.add(k);
										normalCheckedList.add(noCheckedNormalList.get(k).getLawobj());
									}else{
											i--;//���³�ȡ
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
						 * ��ѡ������ҵ������ȳ������������ҵ�г�ѡ
						 */
						List<TBizYearLawobj> allSpecialLawobjList = yearLawobjManager.queryAllYearLawobj(year,quarter, CxlxEnum.tsqy.getCode(),areaid);//��ȳ�������������������ҵ
						List<TBizYearLawobj> noCheckedSpecialList = yearLawobjManager.queryNoCheckedLawobj(year, quarter, CxlxEnum.tsqy.getCode(), areaid);//��ȳ�������û�鵽����������ҵ
						if(noCheckedSpecialList!=null && noCheckedSpecialList.size()>0){
							int number = allSpecialLawobjList.size();
							List<TDataLawobj> specialCheckedList = new ArrayList<TDataLawobj>();//����һ����ҵ�����б�
							int num;	
							if(number>=4){
									float n=(float)number/4;//�����ȳ�ȡ��ҵ��
									if(n==(int)n){//���ȳ�ȡ��ҵ��Ϊ����,ȡ����
										num=(int)n;
									}else{
										num=(int)n+1;
									}
									List<Integer> arr2 = new ArrayList<Integer>();//�����е���ֵ
									for(int i=0;i<num && i<noCheckedSpecialList.size();i++){
										int k = (int) ((Math.random())*(noCheckedSpecialList.size()));
										if(!arr2.contains(k)){//k���������У���ʾ��δ����ȡ��
											arr2.add(k);
											specialCheckedList.add(noCheckedSpecialList.get(k).getLawobj());
										}else{
												i--;//���³�ȡ
										}
									}
									//this.saveCheckedList(year,quarter,"3",specialCheckedList,"");
							}else{
								int k = (int) ((Math.random())*(noCheckedSpecialList.size()));
								specialCheckedList.add(noCheckedSpecialList.get(k).getLawobj());
								//this.saveCheckedList(year,quarter,"3",specialCheckedList,"");
							}
						}
						return "��ѡ�ɹ�";
				} catch (Exception e) {
					e.printStackTrace();
				}
		return "success";
	}
	//ɾ����ѡ���(���)
	public void deleteResult(String year){
		StringBuilder sql = new StringBuilder();
		String areaid = CtxUtil.getAreaId();
		sql.append(" from TBizYearLawobj where year=? and area.id = ?");
		this.dao.removeFindObjs(sql.toString(), year,areaid);
	}
	/**
	 * ��ʼ��ѡ(���)    
	 * 
	 * @param year
	 *            ���
	 * @return ��ѡ���         
	 * @throws Exception
	 */
	public String startYearCheck(String year){
				try {
						/**
						 * ��ѡ˵������ʡ�ء��ص���ҵ��ֻ�й�ҵ��ȾԴ�й�ʡ���ص���ҵ��ȫ��������ȱ�������
						 * һ����ҵ(���ص���ҵ�������ִ������)�������Ա1:5��������ȡ��ҵ��
						 * ������ҵ����Ͷ�ߡ�Υ�������飩ȫ��������ȱ�����嵥
						 */
						List<TDataLawobj> keyLawobjList = new ArrayList<TDataLawobj>();//�����ص���ҵ�б�
						List<TDataLawobj> normalLawobjList = new ArrayList<TDataLawobj>();//����һ����ҵ�б�
						List<TDataLawobj> specialLawobjList = new ArrayList<TDataLawobj>();//����������ҵ�б�
						
						keyLawobjList = lawobjManager.queryAllKeyLawobjList();//�����ص���ȾԴ
						//�ѳ�鵽���ص���ҵ���뵽��ȳ��������
						yearLawobjManager.saveYearLawobjList(keyLawobjList, year,CxlxEnum.zdqy.getCode());
						specialLawobjList = lawobjManager.queryIllegalLawobjList(year);//����Υ�������鼰��Ͷ����ҵ
						//��������ҵ���뵽��ȳ��������
						yearLawobjManager.saveYearLawobjList(specialLawobjList, year,CxlxEnum.tsqy.getCode());
						normalLawobjList = lawobjManager.queryAllNormalList();//����һ����ҵ
						int number = userManager.queryUserNumber();//ϵͳ�ڱ�����ע���û�
						List<TDataLawobj> checkedList = new ArrayList<TDataLawobj>();//������ѡ����һ����ҵ
						if(number>1){
							number=(number)*5;//ϵͳ�ڼ��Ա����5�õ�һ����ҵ�����
							if(normalLawobjList!=null && normalLawobjList.size()>0){
								if(normalLawobjList.size()>number){//һ����ҵ�����ڳ��������������
									List<Integer> arr = new ArrayList<Integer>();//�����е���ֵ
									for(int i=0;i<number;i++){
										int k = (int) ((Math.random())*(normalLawobjList.size()));
										if(!arr.contains(k)){//k���������У���ʾ��δ����ȡ��
											arr.add(k);
											checkedList.add(normalLawobjList.get(k));
										}else{
											i--;//���³�ȡ
										}
									}
								}else{//һ����ҵ����С�ڵ��ڳ������ȫ���������б�
									checkedList.addAll(normalLawobjList);
								}
							}
						}
						//��������ҵ���뵽��ȳ��������
						yearLawobjManager.saveYearLawobjList(checkedList, year,CxlxEnum.ybqy.getCode());
						return "��ѡ�ɹ�";
				} catch (Exception e) {
					e.printStackTrace();
				}
		return "success";
	}
	/**
	 * ��������
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 * @return ��ѡ���         
	 * @throws Exception
	 */
	public String createWork(String year, String quarter){
		//������ݼ��Ȳ�ѯ�����е���ҵ�б�
		List<TBizCheckedLawobj> checkedList= this.queryCheckedList(year,quarter);
		TSysUser admin = userManager.getUser("a0000000000000000000000000000000");
		if(checkedList!=null && checkedList.size()>0){
			if(checkedList.get(0).getTask()==null){
				//����鵽����ҵ������������ɷ�������ҵ�ļ�ܲ����쵼
				for(int i=0;i<checkedList.size();i++){
					Work work = new Work();
					String orgId;
					try {
						orgId = lawobjManager.getOrgidByLawobj(checkedList.get(i).getLawobj());
						if(StringUtils.isNotBlank(orgId)){
							TSysUser user = orgManager.getLeaderByOrg(orgId);
							if(user!=null){
								work.setCreateUser(user);//�����ˣ������쵼
								work.setDjrId(user.getId());//�Ǽ���id
								work.setDjrName(user.getName());//�Ǽ���name
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					TDataLawobj tDataLawobj = (TDataLawobj)dao.get(TDataLawobj.class,checkedList.get(i).getLawobj().getId());
					work.setName("�������");	//��������
					work.setWorkNote("�������"); //��������
					work.setSource("11");//������Դ��������
					work.setCreateTime(new Date());//����ʱ��
					work.setEmergency("1");//�����̶�:һ��
					work.setSecurity("3");//�����ܼ�:����
					work.setAreaid(tDataLawobj.getAreaid());//������������
					Calendar endC = Calendar.getInstance();
	                endC.add(Calendar.DATE, 20);//Ĭ�Ͻ����̶�һ��20��
	        		work.setEndTime(endC.getTime());
					work.setIsActive(YnEnum.Y.getCode());//״̬
					work.setZfdxType(tDataLawobj.getLawobjtype());
				    //����WORK����
					work = (Work) workDao.save(work);
					
				    //���������ִ������Ĺ���
				    String lawobjname = lawobjManager.getLawobjColumnValue(tDataLawobj.getLawobjtype()
										+PublicColumnEnum.mc.getCode(), tDataLawobj.getId());//ִ����������
				    String address = lawobjManager.getLawobjColumnValue(tDataLawobj.getLawobjtype()
							+PublicColumnEnum.dz.getCode(), tDataLawobj.getId());//ִ�������ַ
				    String regionId = lawobjManager.getLawobjColumnValue(tDataLawobj
							.getLawobjtype()
							+ PublicColumnEnum.ssxzq.getCode(), tDataLawobj.getId());//��������
				    String fddbr = lawobjManager.getLawobjColumnValue(tDataLawobj
							.getLawobjtype()
							+ PublicColumnEnum.fddbr.getCode(), tDataLawobj.getId());//����������
				    String fddbrlxdh = lawobjManager.getLawobjColumnValue(tDataLawobj
							.getLawobjtype()
							+ PublicColumnEnum.fddbrlxdh.getCode(), tDataLawobj.getId());//������������ϵ�绰
				    String hbfzr = lawobjManager.getLawobjColumnValue(tDataLawobj.getLawobjtype()
							+ PublicColumnEnum.hbfzr.getCode(), tDataLawobj.getId());//����������
				    String hbfzrlxdh = lawobjManager.getLawobjColumnValue(tDataLawobj.getLawobjtype()
							+ PublicColumnEnum.hbfzrlxdh.getCode(), tDataLawobj.getId());//������������ϵ�绰
					TBizTaskandlawobj tBizTaskandlawobj = new TBizTaskandlawobj(
							work.getId(), tDataLawobj.getLawobjtype(), tDataLawobj.getId(), lawobjname, regionId,
							address, fddbr, fddbrlxdh, hbfzr, "", hbfzrlxdh, "", "����������");
					tBizTaskandlawobj.setUpdateby(admin);
					tBizTaskandlawobj.setCreateby(admin);
				    this.dao.save(tBizTaskandlawobj);
				    
				    //����������������͹���
				    TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
				    tBizTaskandtasktype.setTaskid(work.getId());//����id
				    tBizTaskandtasktype.setTasktypeid(TaskTypeCode.RCJC.getCode());//�������ͣ��ֳ����
				    tBizTaskandtasktype.setIsActive(YnEnum.Y.getCode());//״̬
				    tBizTaskandtasktype.setCreated(new Date());//����ʱ��
				    tBizTaskandtasktype.setCreateby(admin);//������
				    tBizTaskandtasktype.setUpdateby(admin);
				    tBizTaskandtasktype.setUpdated(new Date());
				    this.dao.save(tBizTaskandtasktype);
				    
				    //�ѳ����б�������id�����ɵ��������
				    TBizCheckedLawobj tBizCheckedLawobj = checkedList.get(i);
				    tBizCheckedLawobj.setTask(work);
				    this.dao.save(tBizCheckedLawobj);//���³����б�
				}
				return "����������ɣ��������ɷ����������쵼";
			}else{
				return "�ü������������ɣ������ظ�����";
			}
		}else{
			return "�ü�����δ��ѡ��ҵ";
		}
	}

	
	/**
	 * ���汻������ȾԴ�б�
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
	 * ���汻������ȾԴ�б�
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
	 * ������ݼ��Ȳ�ѯ��������ȾԴ�б�
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 * @return ��ѡ���          
	 * @throws Exception
	 */
	public List<TBizCheckedLawobj> queryCheckedList(String year, String quarter){
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append(" from TBizCheckedLawobj where 1=1 ");
		//���
		if (StringUtils.isNotBlank(year)) {
			sql.append(" and year = :year ");
			data.put("year", year);
		}
		//����
		if (StringUtils.isNotBlank(quarter)) {
			sql.append(" and quarter = :quarter ");
			data.put("quarter", quarter);
		}
		//�Ƿ���Ч
		sql.append(" and isActive = :isActive ");
		data.put("isActive", YnEnum.Y.getCode());
		sql.append(" order by year,quarter,type");
		List<TBizCheckedLawobj> checkedLawobjList = dao.find(sql.toString(), data);
		return checkedLawobjList;
	}
	
	/**
	 * ��ѯ��������ȾԴ�б�����ҳ��
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 * @return ��ѡ���          
	 * @throws Exception 
	 * @throws Exception
	 */
	public FyWebResult queryCheckedLawobj(String year, String month,String areaid,String page, String pageSize) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct c.id_,c.year_,c.month_,c.lawobjid_,c.type_,c.taskid_,c.sfypf_,l.lawobjtypeid_ from T_Biz_CheckedLawobj c ");
		sql.append(" left join t_Data_Lawobjf l on l.id_ = c.lawobjid_ where c.isActive_='Y'");
		//sql.append(" left join T_Biz_YearLawobj y on c.lawobjid_ = y.lawobjid_  where y.isActive_='Y'");
		//���
		if (StringUtils.isNotBlank(year)) {
			sql.append(" and c.year_ = :year ");
			data.put("year", year);
		}
		//����
		if (StringUtils.isNotBlank(month)) {
			sql.append(" and c.month_ = :month ");
			data.put("month", month);
		}
		//��������
		if(StringUtil.isBlank(areaid)){
			areaid = CtxUtil.getAreaId();
		}
		//String areaid = CtxUtil.getAreaId();
		if(StringUtils.isNotBlank(areaid)){
				sql.append(" and c.areaid_ = :areaid ");
				data.put("areaid", areaid);
		}
		
		//�Ƿ���Ч
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
			row.put("year", String.valueOf(obj[1])+"��");
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
					orgId = lawobj.getSsjgbm();//������ܲ���
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
			//��ѡ����
			if("2".equals(String.valueOf(obj[4]))){
				row.put("cxlx", "һ����ҵ");
			}else if("3".equals(String.valueOf(obj[4]))){
				row.put("cxlx", "������ҵ");
			}else{
				row.put("cxlx", "�ص���ҵ");
			}
			if(String.valueOf(obj[6]).equals("Y")){
				row.put("isCreated","��");
			}else{
				row.put("isCreated","��");
			}
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}
}