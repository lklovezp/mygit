package com.hnjz.app.data.doublerandom;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TBizConfigcheck;
import com.hnjz.app.data.po.TBizHistoryconfigcheck;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TBizCheckedLawobj;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysUser;

@Service("doubleRandomManager")
public class DoubleRandomManagerImpl extends ManagerImpl implements DoubleRandomManager,ServletContextAware{
	private ServletContext servletContext;
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext=servletContext;
		
	}
	/**
	 * ����ʷ�������ѯ year��month�µ����ñ���
	 * */
	@Override
	public TBizHistoryconfigcheck queryTBizHistoryconfigcheck(String year,
			String month, String areaid) {
		// ���������year��monthȥ��ѯ��ѡ����
		List<TBizHistoryconfigcheck> thcc=this.dao.find("from TBizHistoryconfigcheck t where t.isActive='Y' and t.year=? and t.month=? and t.areaId=?", year,month,areaid);
		TBizHistoryconfigcheck tc=new TBizHistoryconfigcheck();
		if(thcc.size()>0){
			tc=thcc.get(0);
		}
		return tc;
	}
	@Override
	public List<TDataLawobjf> queryAllKeyLawobjfListByAreaId(String areaId)
			throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer listSql = new StringBuffer(" select f.id_ from t_data_lawobjf f  ");
		listSql.append(" left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_  ");
		listSql.append(" left join t_data_region  r on r.id_=f.ssxzq_  where 1=1 and f.isactive_='Y'  and ssxzq_ in ( ");
		listSql.append(" SELECT id_ from t_data_region where PID_  = ( SELECT PID_ from t_data_region  where id_ = ( SELECT code_ from t_sys_area  ");
		if(StringUtils.isNotBlank(areaId)){
		listSql.append(" where id_=:AreaId   ");
		condition.put("AreaId", areaId);
		}
		listSql.append(" )) )");
		listSql.append(" and f.id_ in ( select fid_ from T_data_gywry  where kzlx_ in ('1','2','3')) ");
		//��ҵ״̬:��Ӫ��
		listSql.append(" and f.qyzt_").append(" = :qyzt ");
		condition.put("qyzt", "0");
		
		List<TDataLawobjf> lawobjList=new ArrayList<TDataLawobjf>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobjf lawobj = new TDataLawobjf();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobjf) this.get(TDataLawobjf.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	@Override
	public List<TDataLawobjf> queryKeyLawobjByAreaIdAndMonth(String year,String month,
			String areaId) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer listSql = new StringBuffer(" select f.id_ from t_data_lawobjf f  ");
		listSql.append(" left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_  ");
		listSql.append(" left join t_data_region  r on r.id_=f.ssxzq_  where 1=1 and f.isactive_='Y'  and f.ssxzq_ in ( ");
		listSql.append(" SELECT id_ from t_data_region where PID_  = ( SELECT PID_ from t_data_region  where id_ = ( SELECT code_ from t_sys_area  ");
		if(StringUtils.isNotBlank(areaId)){
		listSql.append(" where id_=:AreaId ");
		condition.put("AreaId", areaId);
		}
		listSql.append(" )) )");
		listSql.append(" and f.id_ in ( select fid_ from T_data_gywry  where kzlx_ in ('1','2','3')) ");
		//instr('','')��������
		//listSql.append(" and instr(f.qysczt_,").append(month+")>0");
		listSql.append(" and instr(f.qysczt_,:month)>0 ");
		condition.put("month", month);
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and f.qyzt_").append(" = :qyzt ");
		condition.put("qyzt", "0");
		
		listSql.append(" and  f.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='1' and c.year_ = :year1 and c.sfypf_= 'Y' )");
		condition.put("year1", year);
		
		listSql.append(" order by nvl(length(trim(f.qysczt_)),0) ");
		
		List<TDataLawobjf> lawobjList=new ArrayList<TDataLawobjf>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobjf lawobj = new TDataLawobjf();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobjf) this.get(TDataLawobjf.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	@Override
	public List<TDataLawobjf> queryNoMonthKeyLawobjfListByAreaId(String year,String areaId)
			throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer listSql = new StringBuffer(" select f.id_ from t_data_lawobjf f  ");
		listSql.append(" left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_  ");
		listSql.append(" left join t_data_region  r on r.id_=f.ssxzq_  where 1=1 and f.isactive_='Y'  and f.ssxzq_ in ( ");
		listSql.append(" SELECT id_ from t_data_region where PID_  = ( SELECT PID_ from t_data_region  where id_ = ( SELECT code_ from t_sys_area  ");
		if(StringUtils.isNotBlank(areaId)){
		listSql.append(" where id_=:AreaId   ");
		condition.put("AreaId", areaId);
		}
		listSql.append(" )) )");
		listSql.append(" and f.id_ in ( select fid_ from T_data_gywry  where kzlx_ in ('1','2','3')) ");
		listSql.append("and f.qysczt_='Y' ");
		//��ҵ״̬:��Ӫ��
		listSql.append(" and f.qyzt_").append(" = :qyzt ");
		condition.put("qyzt", "0");
		
		listSql.append(" and  f.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='1' and c.year_ = :year1 and c.sfypf_= 'Y' )");
		condition.put("year1", year);
		
		List<TDataLawobjf> lawobjList=new ArrayList<TDataLawobjf>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobjf lawobj = new TDataLawobjf();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobjf) this.get(TDataLawobjf.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	@Override
	public String saveCheckKeyLawobjf(String year, String month, String areaid)
			throws Exception {
		// ��ȡ�����ص���ҵ
		List<TDataLawobjf> keyAll=this.queryAllKeyLawobjfListByAreaId(areaid);
		//������ݺ��·�ȷ�����ɷ����������·�
		String maxMonth=this.checkMaxMonth(year, month, areaid);
		if(StringUtil.isBlank(maxMonth)){
			maxMonth="1";
		}
		
		//��ѯ��û�б��ɷ����·ݲ���װ��map
		Map<String,List<TDataLawobjf>> inMap=new HashMap<String,List<TDataLawobjf>>();
		//�����·ݲ���װ��map
		Map<String,List<TDataLawobjf>> checkMap=new HashMap<String,List<TDataLawobjf>>();
		//�����������·��ǡ������·ݼ��Ժ��·ݡ�����ҵ�����
		List<TDataLawobjf> listMonth=new ArrayList<TDataLawobjf>();//�����Ե�
		List<TDataLawobjf> listNoMonth=new ArrayList<TDataLawobjf>();//�Ǽ����Ե�
		for(int i = Integer.valueOf(maxMonth);i<13;i++)
		{
			String a=String.valueOf(i).length()>1?i+"":"0"+i;
			//��ѯmaxMonth֮����·��ܳ�����ҵ //�����Ե�
			listMonth=this.queryKeyLawobjByAreaIdAndMonth(year,a, areaid);//�˷����Ѿ�ȥ�������ɷ�����ҵ
			//�Ǽ����Ե�
			listNoMonth=this.queryNoMonthKeyLawobjfListByAreaId(year, areaid);
			listMonth.addAll(listNoMonth);
				inMap.put(a, listMonth);
			
			
		}
		//��ȡ��ҵ��ÿ���µ����ñ�����
		TBizHistoryconfigcheck tbh=this.queryTBizHistoryconfigcheckScale(year, areaid);
		Map<String,Double> mapMonthSale=new HashMap<String, Double>();
		mapMonthSale.put("01", tbh.getZdqyjan());
		mapMonthSale.put("02", tbh.getZdqyfeb());
		mapMonthSale.put("03", tbh.getZdqymar());
		mapMonthSale.put("04", tbh.getZdqyapr());
		mapMonthSale.put("05", tbh.getZdqymay());
		mapMonthSale.put("06", tbh.getZdqyjun());
		mapMonthSale.put("07", tbh.getZdqyjul());
		mapMonthSale.put("08", tbh.getZdqyaug());
		mapMonthSale.put("09", tbh.getZdqysep());
		mapMonthSale.put("10", tbh.getZdqyoct());
		mapMonthSale.put("11", tbh.getZdqynov());
		mapMonthSale.put("12", tbh.getZdqydec());
		
		
		while(inMap.size()>0){
			// ��ȡ������ҵ���·�
			String minMonth=getMinMonth(inMap);
			
			if(inMap.get(minMonth)==null)
			{
				break;
			}
			if(inMap.get(minMonth).size()>0){
			
			//����minMonth�ĳ�����
			int total=0;
			//total1 ��total2��Ϊ�˼�����С�����������С��ʱ������1
			float total1=(float)(keyAll.size()*mapMonthSale.get(minMonth))/100;
			int total2=(int)((keyAll.size()*mapMonthSale.get(minMonth))/100);
			if(total1==total2){
				total=total2;
			}else{
				total=total2+1;
			}
			//��ѡ�е���ҵ
			List<TDataLawobjf> checkList=new ArrayList<TDataLawobjf>();
			for(int i=0;i<total;i++){
				if(total<=inMap.get(minMonth).size()){
					checkList.add(inMap.get(minMonth).get(i));
					//����ѡ�е���ҵ�������·��Ƴ�
					for(String a:inMap.keySet())
					{
						//�����ų���ǰ��С��
						if(!a.equals(minMonth)){
							for(int j=0;j<inMap.get(a).size();j++){
								if(inMap.get(a).get(j).getId().equals(inMap.get(minMonth).get(i).getId())){
									//�Ƴ���ѡ�е���ҵ
									inMap.get(a).remove(j);
								}
							}
						}
						
					}
				}else{
					//�ȳ�ȡ����ȫ����
					checkList.add(inMap.get(minMonth).get(i));
					//����ѡ�е���ҵ�������·��Ƴ�
					for(String a:inMap.keySet())
					{
						//�����ų���ǰ��С��
						if(!a.equals(minMonth)){
							for(int j=0;j<inMap.get(a).size();j++){
								if(inMap.get(a).get(j).getId().equals(inMap.get(minMonth).get(i).getId())){
									//�Ƴ���ѡ�е���ҵ
									inMap.get(a).remove(j);
								}
							}
						}
						
					}
					//ȥ���Ѿ���ѡ����ȫ������ҵ
					for(int k=0;k<inMap.get(minMonth).size();k++){
						for(int f=0;f<listNoMonth.size();f++){
							if(inMap.get(minMonth).get(k).getId().equals(listNoMonth.get(f).getId())){
								listNoMonth.remove(f);
							}	
						}
						
					}
					
					
					
					//�����Ĵ�ȫ������ҵ��ȡ
					List<Integer> arr = new ArrayList<Integer>();//�����е���ֵ
					int num=total-inMap.get(minMonth).size();
					if(num<listNoMonth.size()){
						for(int j=0;j<num;j++){
							
							//ÿ�����ɵ����������һ��
							Random random = new Random();
					        int k = random.nextInt(listNoMonth.size());
							//int k = (int) ((Math.random())*(listNoMonth.size()));
							
							if(!arr.contains(k)){//k���������У���ʾ��δ����ȡ��
								arr.add(k);
								checkList.add(listNoMonth.get(k));
							}else{
								j--;//���³�ȡ
							}
								
						}
					}else{
						checkList.addAll(listNoMonth);
						for(int j=0;j<num-listNoMonth.size();j++){
							//ÿ�����ɵ����������һ��
							Random random = new Random();
					        int k = random.nextInt(listNoMonth.size());
							//int k = (int) ((Math.random())*(listNoMonth.size()));
							checkList.add(listNoMonth.get(k));
								
						}
					}
					
					//����ѡ�е���ҵ�������·��Ƴ�
					
				}
			}
			
			//����ѡ�е���ҵ���·���map���Ա��ڱ������ݿ⡣
			checkMap.put(minMonth, checkList);
			}
			inMap.remove(minMonth);
		}
		//�������ݿ�
		this.saveCheckLawobjf(year, "1", areaid, checkMap);
		return "success";
	}
	//�������ݿⷽ��
	public void saveCheckLawobjf(String year,String type,String areaid,Map<String,List<TDataLawobjf>> checkMap)throws Exception{
		//TSysUser user = CtxUtil.getCurUser();
				List<TBizCheckedLawobj> lists=new ArrayList<TBizCheckedLawobj>();
				//TSysUser user = userManager.getUser("a0000000000000000000000000000000");
				TSysUser user =  CtxUtil.getCurUser();;
				Date cur = new Date();
				for(String k:checkMap.keySet()){
					//����֮ǰɾ��֮ǰ��ѡ����ҵ
					this.dao.removeFindObjs("from TBizCheckedLawobj t where t.year=? and t.month=? and t.areaId=? and t.type=? ", year,k,areaid,type);
					
					if(checkMap.get(k)!=null &&checkMap.get(k).size()>0){
						for(int i=0;i<checkMap.get(k).size();i++){
							TBizCheckedLawobj checkedLawobj = new TBizCheckedLawobj();
							checkedLawobj.setYear(year);
							checkedLawobj.setQuarter("");
							checkedLawobj.setIsActive("Y");
							checkedLawobj.setMonth(k);
							checkedLawobj.setLawobjf(checkMap.get(k).get(i));
							if(checkMap.get(k).get(i)!=null){
								TDataLawobjtype tp=(TDataLawobjtype)this.get(TDataLawobjtype.class, checkMap.get(k).get(i).getLawobjtype().getId());
								checkedLawobj.setLawobjType(tp.getName());
								//checkedLawobj.setLawobjName(checkedList.get(i).getName());
								checkedLawobj.setLawobjName(checkMap.get(k).get(i).getDwmc());
							}
							checkedLawobj.setCreated(new Date());
							checkedLawobj.setCreateby(user);
							checkedLawobj.setCreated(cur);
							checkedLawobj.setUpdateby(user);
							checkedLawobj.setUpdated(cur);
							checkedLawobj.setType(type);
							checkedLawobj.setAreaId(areaid);
							checkedLawobj.setSfypf("N");
							checkedLawobj=(TBizCheckedLawobj)this.save(checkedLawobj);
						}
					}
				}
	}
	
	
	
	//��ȡ������ҵ���·�
	public String getMinMonth(Map<String,List<TDataLawobjf>> inMap){
		String minMonth="";
		int maxsize=Integer.MAX_VALUE;
		for(String k:inMap.keySet()){
			int size=inMap.get(k).size();
			if(size<maxsize){
				minMonth=k;
				maxsize=size;
			}
		}
		return minMonth;
	}
	
	
	//������ݺ��·�ȷ���ѳ����������·�
	public String checkMaxMonth(String year,String month,String areaid)throws Exception{
		String maxMonth="";
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer listSql = new StringBuffer(" select max(c.month_) from T_Biz_Checkedlawobj c where c.year_= :year1 ");
		condition.put("year1", year);
		listSql.append(" and c.areaid_= :areaid");
		condition.put("areaid", areaid);
		listSql.append(" and c.sfypf_= 'Y' ");
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				maxMonth=String.valueOf(list.get(i));
			}
		}
		return maxMonth;
	}
	//��ȡ��ҵ��ÿ���µ����ñ���
	public TBizConfigcheck queryScale(String year,String areaid)throws Exception{
		TBizConfigcheck tc=new TBizConfigcheck();
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer listSql = new StringBuffer(" select c.id_ from T_Biz_ConfigCheck c where c.year_= :year1 ");
		condition.put("year1", year);
		listSql.append(" and c.areaid_= :areaid");
		condition.put("areaid", areaid);
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		//for(int i =0; i<list.size(); i++){
			if(StringUtils.isNotBlank(String.valueOf(list.get(0)))){
				tc=(TBizConfigcheck)this.get(TBizConfigcheck.class, String.valueOf(list.get(0)));
			}
	//	}
		return tc;
	}
	    //��ȡ��ҵ��ÿ���µ����ñ���
		public TBizHistoryconfigcheck queryTBizHistoryconfigcheckScale(String year,String areaid)throws Exception{
			TBizHistoryconfigcheck tc=new TBizHistoryconfigcheck();
			Map<String, Object> condition = new HashMap<String, Object>();
			StringBuffer listSql = new StringBuffer(" select c.id_ from T_Biz_Historyconfigcheck c where c.year_= :year1 ");
			condition.put("year1", year);
			listSql.append(" and c.areaid_= :areaid");
			condition.put("areaid", areaid);
			List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
			//for(int i =0; i<list.size(); i++){
				if(StringUtils.isNotBlank(String.valueOf(list.get(0)))){
					tc=(TBizHistoryconfigcheck)this.get(TBizHistoryconfigcheck.class, String.valueOf(list.get(0)));
				}
		//	}
			return tc;
		}
	
	@Override
	public String saveCheckNormalLawobjf(String year, String month,
			String areaid) throws Exception {
		//��ȡ����һ����ҵ
		//List<String> lawobjList =this.queryAllNormalLawobjf(year, areaid);
		//������ݺ��·�ȷ�����ɷ����������·�
		String maxMonth=this.checkMaxMonth(year, month, areaid);
		if(StringUtil.isBlank(maxMonth)){
			maxMonth="1";
		}
		//��ȡ��ҵ��ÿ���µ����ñ�����
		TBizHistoryconfigcheck thc=this.queryTBizHistoryconfigcheckScale(year, areaid);
		Map<String,Double> mapMonthSale=new HashMap<String, Double>();
		mapMonthSale.put("01", thc.getYbqyjan());
		mapMonthSale.put("02", thc.getYbqyfeb());
		mapMonthSale.put("03", thc.getYbqymar());
		mapMonthSale.put("04", thc.getYbqyapr());
		mapMonthSale.put("05", thc.getYbqymay());
		mapMonthSale.put("06", thc.getYbqyjun());
		mapMonthSale.put("07", thc.getYbqyjul());
		mapMonthSale.put("08", thc.getYbqyaug());
		mapMonthSale.put("09", thc.getYbqysep());
		mapMonthSale.put("10", thc.getYbqyoct());
		mapMonthSale.put("11", thc.getYbqynov());
		mapMonthSale.put("12", thc.getYbqydec());
		//��ѯ�û����õ��ڱ��ڸ���Ա����
		List<TSysUser> usersList=this.dao.find("from TSysUser t where t.isActive='Y' and t.isZaiBian='Y' and t.areaId=?", areaid);
		//��Ա����
		int users=usersList.size();
		for(int i = Integer.valueOf(maxMonth);i<13;i++)
		{
			String a=String.valueOf(i).length()>1?i+"":"0"+i;
			//��ѯ a���ɷ���һ����ҵ��������ҵ
			List<String> monthList=this.queryNomalLawobjfByMonth(year,a,areaid);
			//�����a���ɷ�������
			int numCheck=(int)(mapMonthSale.get(a)*users);
			if(numCheck==mapMonthSale.get(a)*users){//����Ϊ����ȡ��
				//numCheck = numCheck;
			}else{
				numCheck = numCheck+1;//�����ķ�����ȡ����1
			}
			//��ʼ���
			//�ѳ��Ľ���ŵ�list����
			List<String> checkList=new ArrayList<String>();
			
			if(numCheck<=monthList.size()){
				List<Integer> arr = new ArrayList<Integer>();//�����е���ֵ
				for(int j=0;j<numCheck;j++){
					Random r=new Random();
					int rnum=r.nextInt(monthList.size());
					if(!arr.contains(rnum)){//k���������У���ʾ��δ����ȡ��
						arr.add(rnum);
						checkList.add(monthList.get(rnum));
					}else{
						j--;//���³�ȡ
					}
					
				}
			}else{
				checkList.addAll(monthList);
				//��ѯ��һ����ҵ�Ǽ����Ե�
				List<String> noMonth=this.queryNomalLawobjfNoMonth(year, areaid);
				List<Integer> arr = new ArrayList<Integer>();//�����е���ֵ
				int cn=numCheck-monthList.size();
				if(noMonth.size()>cn){
					for(int j=0;j<cn;j++){
						Random r=new Random();
						int rnum=r.nextInt(noMonth.size());
						if(!arr.contains(rnum)){//k���������У���ʾ��δ����ȡ��
							arr.add(rnum);
							checkList.add(noMonth.get(rnum));
						}else{
							j--;//���³�ȡ
						}
					}
				}else{
					checkList.addAll(noMonth);
					for(int j=0;j<cn-noMonth.size();j++){
						Random r=new Random();
						int rnum=r.nextInt(noMonth.size());
						checkList.add(noMonth.get(rnum));
					}
				}
				
			}
			//���浽���ݿ�
			this.saveCheckedNomalList(year, a, "2", checkList, areaid);
		}
		return "success";
	}
	//����һ����ҵ�����ݿ�
	public List<TBizCheckedLawobj> saveCheckedNomalList(String year, String month, String type, List<String> checkedList,String areaId) throws Exception{
		//����֮ǰɾ��֮ǰ��ѡ����ҵ
		this.dao.removeFindObjs("from TBizCheckedLawobj t where t.year=? and t.month=? and t.areaId=? and t.type=? ", year,month,areaId,type);
		  		TSysUser user = CtxUtil.getCurUser();
				List<TBizCheckedLawobj> lists=new ArrayList<TBizCheckedLawobj>();
				//TSysUser user = userManager.getUser("a0000000000000000000000000000000");
				Date cur = new Date();
				if(checkedList!=null &&checkedList.size()>0){
					for(int i=0;i<checkedList.size();i++){
						TBizCheckedLawobj checkedLawobj = new TBizCheckedLawobj();
						TDataLawobjf tf=(TDataLawobjf)this.get(TDataLawobjf.class, checkedList.get(i));
						checkedLawobj.setYear(year);
						checkedLawobj.setQuarter("");
						checkedLawobj.setMonth(month);
						checkedLawobj.setIsActive("Y");
						checkedLawobj.setLawobjf(tf);
						if(checkedList.get(i)!=null){
							TDataLawobjtype tp=(TDataLawobjtype)this.get(TDataLawobjtype.class, tf.getLawobjtype().getId());
							checkedLawobj.setLawobjType(tp.getName());
							//checkedLawobj.setLawobjName(checkedList.get(i).getName());
							checkedLawobj.setLawobjName(tf.getDwmc());
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
	//���²�ѯ�������ܹ���ѡ�ļ�������ҵ
	public List<String> queryNomalLawobjfByMonth(String year,String month,String areaid)throws Exception{
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer listSql = new StringBuffer(" select f.id_ from t_data_lawobjf f  ");
		listSql.append(" left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_  ");
		listSql.append(" left join t_data_region  r on r.id_=f.ssxzq_  where 1=1 and f.isactive_='Y'  and f.ssxzq_ in ( ");
		listSql.append(" SELECT id_ from t_data_region where PID_  = ( SELECT PID_ from t_data_region  where id_ = ( SELECT code_ from t_sys_area  ");
		if(StringUtils.isNotBlank(areaid)){
			listSql.append(" where id_=:AreaId   ");
			condition.put("AreaId", areaid);
		}
		
		listSql.append(" )) )");
		//instr('','')��������
		//listSql.append(" and instr(f.qysczt_,").append(month+")>0");
		listSql.append(" and instr(f.qysczt_,:month)>0 ");
		condition.put("month", month);
		listSql.append(" and f.id_ not in ( select fid_ from T_data_gywry  where  kzlx_ in ('1','2','3')) ");
		//��ҵ״̬:��Ӫ��
		listSql.append(" and f.qyzt_").append(" = :qyzt ");
		listSql.append(" and f.lawobjtypeid_ !='5' ");
		condition.put("qyzt", "0");
		List<String> lawobjList = new ArrayList<String>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobjList.add(String.valueOf(list.get(i)));
			}
					
		}
				
		return lawobjList;		
	}
	//���²�ѯ�������ܹ���ѡ�ļ�������ҵ
	public List<String> queryNomalLawobjfNoMonth(String year,String areaid)throws Exception{
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer listSql = new StringBuffer(" select f.id_ from t_data_lawobjf f  ");
		listSql.append(" left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_  ");
		listSql.append(" left join t_data_region  r on r.id_=f.ssxzq_  where 1=1 and f.isactive_='Y'  and f.ssxzq_ in ( ");
		listSql.append(" SELECT id_ from t_data_region where PID_  = ( SELECT PID_ from t_data_region  where id_ = ( SELECT code_ from t_sys_area  ");
		if(StringUtils.isNotBlank(areaid)){
			listSql.append(" where id_=:AreaId   ");
			condition.put("AreaId", areaid);
		}
		
		listSql.append(" )) )");
		//instr('','')��������
		//listSql.append(" and instr(f.qysczt_,").append(month+")>0");
		listSql.append(" and f.qysczt_ ='Y' ");
		listSql.append(" and f.id_ not in ( select fid_ from T_data_gywry  where kzlx_ in ('1','2','3')) ");
		//��ҵ״̬:��Ӫ��
		listSql.append(" and f.qyzt_").append(" = :qyzt ");
		listSql.append(" and f.lawobjtypeid_ !='5' ");
		condition.put("qyzt", "0");
		List<String> lawobjList = new ArrayList<String>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobjList.add(String.valueOf(list.get(i)));
			}
					
		}
				
		return lawobjList;
	}
	
	
	/**
	 * ��ѯ����һ����ҵ
	 * */
	public List<String> queryAllNormalLawobjf(String year,String areaid)throws Exception{
		
		List<String> lawobjList = new ArrayList<String>();
			
		lawobjList.addAll(this.queryNormalList(areaid,year));
		return lawobjList;
		
		
	}
	
	
		//��ѯ����һ����ҵ
	public List<String> queryNormalList(String areaId,String year) throws Exception{
			Map<String, Object> condition = new HashMap<String, Object>();
			StringBuffer listSql = new StringBuffer(" select f.id_ from t_data_lawobjf f  ");
			listSql.append(" left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_  ");
			listSql.append(" left join t_data_region  r on r.id_=f.ssxzq_  where 1=1 and f.isactive_='Y'  and f.ssxzq_ in ( ");
			listSql.append(" SELECT id_ from t_data_region where PID_  = ( SELECT PID_ from t_data_region  where id_ = ( SELECT code_ from t_sys_area  ");
			if(StringUtils.isNotBlank(areaId)){
				listSql.append(" where id_=:AreaId   ");
				condition.put("AreaId", areaId);
			}
			listSql.append(" )) )");
			listSql.append(" and f.id_ not in ( select fid_ from T_data_gywry  where  kzlx_ in ('1','2','3')) ");
			//��ҵ״̬:��Ӫ��
			listSql.append(" and f.qyzt_").append(" = :qyzt ");
			listSql.append(" and f.lawobjtypeid_ !='5' ");
			condition.put("qyzt", "0");
			List<String> lawobjList = new ArrayList<String>();
			List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
			for(int i =0; i<list.size(); i++){
				if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
					lawobjList.add(String.valueOf(list.get(i)));
				}
						
			}
					
			return lawobjList;
		}
	@Override
	public String saveCheckAllLawobjf(String year, String areaid)
			throws Exception {
		//month ��ʱû���á������ȴ����ַ���
		this.saveCheckKeyLawobjf(year, "", areaid);
		this.saveCheckNormalLawobjf(year, "", areaid);
		return "seccess";
	}
	@Override
	public List<TBizConfigcheck> queryAllConfigCheck() throws Exception {
		List<TBizConfigcheck> list=new ArrayList<TBizConfigcheck>();
		//TBizConfigcheck tf=new TBizConfigcheck();
		list=this.dao.find("from TBizConfigcheck t where t.isActive='Y' ");
	    return list;
	}
	@Override
	public String queryAreaIdByLawobjfId(String id) throws Exception {
		TDataLawobjf tlf=(TDataLawobjf)this.get(TDataLawobjf.class, id);
		String code=tlf.getSsxzq().substring(0, 4)+"99";
		
		List<TSysArea> t=this.dao.find("from TSysArea t where t.code=?", code);
		String areaid="";
		if(t.size()>0){
			areaid=t.get(0).getId();
		}
		
		return areaid;
	}
			
	/**
	 * ���������Υ������Ͷ�ߵĹ�ҵ��ȾԴ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobjf> queryIllegalLawobjfList(String year) throws Exception{
		int year1 = Integer.parseInt(year)-1;
		Date time1 = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", String.valueOf(year1).concat("-01-01 00:00:00"));
		Date time2 = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", String.valueOf(year).concat("-01-01 00:00:00"));
		StringBuffer listSql = new StringBuffer(" select distinct l.id_ id");
		listSql.append(" from t_Data_Lawobjf l , t_Biz_TaskAndLawobj t, T_BIZ_TASKANDTASKTYPE p,T_DATA_TASKTYPE k,WORK_ w,T_DATA_REGION r");
		listSql.append(" where l.isActive_='Y'  and l.id_ = t.LAWOBJID_ and t.TASKID_ = p.TASKID_ and p.TASKTYPEID_=k.CODE_ and w.id_=p.taskid_ and l.ssxzq_= r.id_");
		listSql.append(" and (k.CODE_ = ? or k.CODE_ = ?) ");	//Υ��+�ŷ�Ͷ��
		listSql.append(" and w.ARCHIVES_TIME_ >= ? ");	//�鵵ʱ��
		listSql.append(" and w.ARCHIVES_TIME_ < ? ");	//�鵵ʱ��
		
		//����������Ϊ���������ҵ
		String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//�ܶ�
			listSql.append(" and r.id_ = ? ");
		}if(area.getType().equals("1")){//ʦ��
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = ?) ");
		}else if(area.getType().equals("2")){//�ż�
			listSql.append(" and r.id_ = ? ");
		}
		List<Object[]> list = this.dao.findBySql(listSql.toString(),TaskTypeCode.WFAJ.getCode(),TaskTypeCode.XFTS.getCode(),time1,time2,area.getCode());
			
		List<TDataLawobjf> lawobjList = new ArrayList<TDataLawobjf>();
		for(int i =0; i<list.size(); i++){
			TDataLawobjf lawobj = new TDataLawobjf();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobjf) this.get(TDataLawobjf.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	@Override
	public int queryCheckedLawobjByType(String year, String quarter, String type,String areaid)
			throws Exception {
		int num=0;
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer listSql = new StringBuffer(" select * from t_biz_checkedlawobj  t left join t_data_lawobjf l on  l.id_=t.lawobjid_ where  l.isactive_='Y' ");
		if(StringUtils.isNotBlank(year)){
			listSql.append(" and t.year_=:year  ");
			condition.put("year", year);
		}
		if(StringUtils.isNotBlank(quarter)){
			if("0".equals(quarter)){
				
			}else if("1".equals(quarter)){
				listSql.append(" and t.month_ in ('01','02','03') ");
			}else if("2".equals(quarter)){
				listSql.append(" and t.month_ in ('04','05','06') ");
			}else if("3".equals(quarter)){
				listSql.append(" and t.month_ in ('07','08','09') ");
			}else if("4".equals(quarter)){
				listSql.append(" and t.month_ in ('10','11','12') ");
			}
			
		}
		if(StringUtils.isNotBlank(type)){
			listSql.append(" and l.lawobjtypeid_=:type   ");
			condition.put("type", type);
		}
		if(StringUtils.isNotBlank(areaid)){
			listSql.append(" and t.areaid_=:areaid   ");
			condition.put("areaid", areaid);
		}
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		num=list.size();
				
		return num;
	}		

}