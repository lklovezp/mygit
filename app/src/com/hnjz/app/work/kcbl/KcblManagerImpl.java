package com.hnjz.app.work.kcbl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataRecord;
import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.ZfdxLx;
import com.hnjz.app.work.jcd.JcdManagerImpl;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.TBizTaskillegaltype;
import com.hnjz.app.work.po.TBizTaskrecordans;
import com.hnjz.app.work.po.TBizTasksurveyrecord;
import com.hnjz.app.work.po.TBizTaskuser;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.xwbl.XwblManager;
import com.hnjz.app.work.zx.BlZfdxForm;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.PoiUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

@Service("kcblManagerImpl")
public class KcblManagerImpl extends ManagerImpl implements KcblManager ,ServletContextAware {
	
	/** ��־ */
	private static final Log log = LogFactory
			.getLog(JcdManagerImpl.class);
	
	private ServletContext sc;
	
	@Autowired
    private WorkDao                 workDao;
	
	@Autowired
    private CommonManager                 commonManager;
	
	@Autowired
    protected WorkManagerImpl workManager;
	
	@Autowired
    private UserManager      userManager;
	
	@Autowired
    private CommWorkManager    commWorkManager;
	
	@Autowired
    private XwblManager    xwblManager;

	@Override
	public KcblForm getKcblFormData(String applyId) {
		//ִ��������Ϣ
		BlZfdxForm blZfdxForm=new BlZfdxForm();
        Map<String, String> zfdxMap = new HashMap<String, String>();
        List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
        if(zfdxlistMap!=null&&zfdxlistMap.size()==1){
        	zfdxMap=zfdxlistMap.get(0);
        	blZfdxForm.setId(zfdxMap.get("id"));
        	blZfdxForm.setLawobjid(zfdxMap.get("lawobjid"));
        	blZfdxForm.setLawobjname(zfdxMap.get("lawobjname"));
        	blZfdxForm.setAddress(zfdxMap.get("address"));
        	blZfdxForm.setManager(zfdxMap.get("manager"));
        	blZfdxForm.setManagermobile(zfdxMap.get("managermobile"));
        	blZfdxForm.setBjcr(zfdxMap.get("bjcr"));
        	blZfdxForm.setZw(zfdxMap.get("zw"));
        	blZfdxForm.setLxdh(zfdxMap.get("lxdh"));
        	
        	blZfdxForm.setXqyzDwmc(zfdxMap.get("xqyzDwmc"));
        }
        
		KcblForm kcblForm=new KcblForm();
		
		QueryCondition data = new QueryCondition();
		StringBuffer sql = new StringBuffer();
		sql.append(" from TBizTasksurveyrecord where taskid=:taskid ");
		data.put("taskid", applyId);
		List<TBizTasksurveyrecord> list = this.dao.find(sql.toString(),data);
		TBizTasksurveyrecord po=new TBizTasksurveyrecord();
		//��ȡ��ǰ��¼�˵�������λ
		TSysUser curUser = CtxUtil.getCurUser();
        TSysOrg org = (TSysOrg) this.find("from TSysOrg where id = (select org.id from TSysUserOrg where user.id = ? ) ", curUser.getId()).get(0);
		
		//������
        String bxwdwmc="";
        Work work = workManager.get(applyId);
		if(ZfdxLx.XQYZ.getCode().equals(work.getZfdxType())){
			bxwdwmc=blZfdxForm.getXqyzDwmc();
		}else{
			bxwdwmc=blZfdxForm.getLawobjname();
		}
		
		if(list!=null&&list.size()>0){
			po=list.get(0);
			kcblForm.setId(po.getId());
			kcblForm.setTaskid(applyId);
			kcblForm.setDsr(StringUtil.isBlank(po.getDsr())?bxwdwmc:po.getDsr());
			kcblForm.setDz(StringUtil.isBlank(po.getDz())?blZfdxForm.getAddress():po.getDz());
			kcblForm.setStarttime(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getStarttime()?new Date():po.getStarttime()));
			kcblForm.setEndtime(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getEndtime()?new Date():po.getEndtime()));
			kcblForm.setDd(po.getDd());
			kcblForm.setZfrydwmc(StringUtil.isBlank(po.getZfrydwmc())?org.getUnitname():po.getZfrydwmc());
//			kcblForm.setKcr(po.getKcrid());
			kcblForm.setZfzh(po.getZfzh());
//			kcblForm.setJlr(po.getJlr());
			kcblForm.setJlrzfzh(po.getJlrzfzh());
			kcblForm.setKcjl(po.getKcjl());
		}else{//Ĭ�ϴ�������
			//1�������ˣ� Ĭ����ʾ�ܼ쵥λ����
			kcblForm.setDsr(bxwdwmc);
			//2���� ַ�� Ĭ��ȡ�ܼ쵥λ��ַ
			kcblForm.setDz(blZfdxForm.getAddress());
			//3����飨���죩ʱ�䣺��ʼʱ�䣬����ʱ��
			kcblForm.setStarttime(DateUtil.getDateTime("yyyy-MM-dd HH:mm",new Date()));
			kcblForm.setEndtime(DateUtil.getDateTime("yyyy-MM-dd HH:mm", new Date()));
			//4��ִ��֤�ţ�Ĭ��ȡ�����ִ��֤��
			
			//5��ִ����Ա��λ����
			kcblForm.setZfrydwmc(org.getUnitname());
		}
		
		String sql1=" from TBizTaskuser where taskid=? and type='"+TaskUserEnum.WFAJDC_KCBLJCKCR.getCode()+"'";
		List<TBizTaskuser> taskuserlist1=this.dao.find(sql1,applyId);
		if(taskuserlist1!=null&&taskuserlist1.size()>0){
			String kcrIds = "";
			String kcrNames = "";
			for (int k = 0; k < taskuserlist1.size(); k++) {
				if (k > 0){
					kcrIds += ",";
					kcrNames += ",";
				}
				kcrIds += taskuserlist1.get(k).getUserid();
				kcrNames += taskuserlist1.get(k).getUsername();
			}
			kcblForm.setKcr(kcrIds);//��飨���죩��
			kcblForm.setKcrName(kcrNames);
		}
		String sql2=" from TBizTaskuser where taskid=? and type='"+TaskUserEnum.WFAJDC_KCBLJLR.getCode()+"'";
		List<TBizTaskuser> taskuserlist2=this.dao.find(sql2,applyId);
		if(taskuserlist2!=null&&taskuserlist2.size()>0){
			kcblForm.setJlr(taskuserlist2.get(0).getUserid());//��¼��
			kcblForm.setJlrName(taskuserlist2.get(0).getUsername());
		}
		
		
		return kcblForm;
		
	}

	@Override
	public List getKcxwblWtList(String applyId, String rwlx, String wflx,
			String kcxwbj) {
		/**
		 * 0:����id;1:����������;2:����ѯ�����ֱ�� 0���� 1ѯ��;3:�Ƿ��ɾ��;4:����;5:������;
		 */
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.ID_,(case when C.CONTENT_ is NULL then A.CONTENT_ else C.CONTENT_ end) as CONTENT_,A.KCXWBJ_,A.ISDEL_,A.ORDER_,B.DANR_");
		buf.append(" FROM T_DATA_RECORD A  ");
		buf.append(" LEFT JOIN (SELECT * FROM T_BIZ_TASKRECORDANS WHERE TASKID_='"+applyId+"') B ");
		buf.append(" ON A.ID_=B.RECORDID_ ");
		
		buf.append(" LEFT JOIN (SELECT * FROM T_BIZ_BLWTX WHERE RWID_='"+applyId+"') C ");
		buf.append(" ON A.ID_=C.WTXID_ ");
		buf.append(" WHERE A.ISCURVER_='Y' ");
		buf.append(" AND A.ISACTIVE_='Y' ");
		buf.append(" AND A.KCXWBJ_='"+kcxwbj+"' ");
		if(StringUtils.isNotBlank(wflx)){
			buf.append(" AND A.WFLXID_ in("+StringUtil.convertSqlIn(wflx)+") ");
		}
		buf.append(" ORDER BY A.ORDER_ ASC ");
		
		return dao.findBySql(buf.toString());
	}
	
	@Override
	public List getMoreKcxwblWtList(String applyId,String kcxwbj) {
		/**
		 * 0:����id;1:����������;2:������;3:����ѯ�����ֱ�� 0���� 1ѯ��;4:����;5:����id;6:�������ͣ�0 ϵͳ 1���䣩
		 */
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.ID_,A.CONTENT_,A.DANR_,A.KCXWBJ_,A.ORDERBY_,A.TASKID_,A.WTTYPE_ ");
		buf.append(" FROM T_BIZ_TASKRECORDANS A  ");
		buf.append(" WHERE A.RECORDID_ IS NULL ");
		buf.append(" AND A.TASKID_='"+applyId+"' ");
		buf.append(" AND A.KCXWBJ_ ='"+kcxwbj+"'  ");
		buf.append(" ORDER BY A.ORDERBY_ ASC");
		
		return dao.findBySql(buf.toString());
	}
	
	@Override
	public List getAllKcxwblWtList(String applyId,String kcxwbj) {
		/**
		 * 0:����id;1:����������;2:������;3:����ѯ�����ֱ�� 0���� 1ѯ��;4:����;5:����id;6:�������ͣ�0 ϵͳ 1���䣩
		 */
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.RECORDID_,A.CONTENT_,A.KCXWBJ_,A.ISDEL_,A.ORDERBY_,A.DANR_");
		buf.append(" FROM T_BIZ_TASKRECORDANS A  ");
		buf.append("WHERE A.TASKID_='"+applyId+"' ");
		buf.append(" AND A.RECORDID_ IS NOT NULL ");
		buf.append(" AND A.KCXWBJ_ ='"+kcxwbj+"'  ");
		buf.append(" ORDER BY A.ORDERBY_ ASC");
		
		return dao.findBySql(buf.toString());
	}
	
	@Override
	public void saveKcbl(KcblForm kcblForm, String applyId, String wflx, String[] ids, String[] content, String[] danr, String[] wttype, String[] isdel){
		TSysUser cur = CtxUtil.getCurUser();
		TBizTasksurveyrecord tBizTasksurveyrecord=new TBizTasksurveyrecord();
		if(StringUtils.isNotBlank(kcblForm.getId())){
			tBizTasksurveyrecord=(TBizTasksurveyrecord)dao.get(TBizTasksurveyrecord.class, kcblForm.getId());
    		
			tBizTasksurveyrecord.setUpdateby(cur);
			tBizTasksurveyrecord.setUpdated(new Date());
        }else{
        	tBizTasksurveyrecord.setCreateby(cur);
        	tBizTasksurveyrecord.setCreated(new Date());
        	tBizTasksurveyrecord.setUpdateby(cur);
			tBizTasksurveyrecord.setUpdated(new Date());
        }
		tBizTasksurveyrecord.setId(kcblForm.getId());
		tBizTasksurveyrecord.setTaskid(applyId);
		try {
			tBizTasksurveyrecord.setStarttime(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm", kcblForm.getStarttime()));
			tBizTasksurveyrecord.setEndtime(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm", kcblForm.getEndtime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tBizTasksurveyrecord.setDsr(kcblForm.getDsr());
		tBizTasksurveyrecord.setDz(kcblForm.getDz());
		tBizTasksurveyrecord.setDd(kcblForm.getDd());
//		tBizTasksurveyrecord.setKcrid(kcblForm.getKcr());
		tBizTasksurveyrecord.setZfzh(kcblForm.getZfzh());
//		tBizTasksurveyrecord.setJlr(kcblForm.getJlrzfzh());
		tBizTasksurveyrecord.setJlrzfzh(kcblForm.getJlrzfzh());
		tBizTasksurveyrecord.setZfrydwmc(kcblForm.getZfrydwmc());
		tBizTasksurveyrecord.setIsActive(YnEnum.Y.getCode());
		
		dao.save(tBizTasksurveyrecord);
		
		//1����飨���죩��
		if(StringUtils.isNotBlank(kcblForm.getKcr())){
			//2015-7-15 �޸� ��飨���죩�˶�ѡ
			String sql=" from TBizTaskuser where taskid=? and type='"+TaskUserEnum.WFAJDC_KCBLJCKCR.getCode()+"'";
			this.dao.removeFindObjs(sql, applyId);
			for (int i = 0; i < kcblForm.getKcr().split(",").length; i++) {
				TBizTaskuser taskuser=new TBizTaskuser();
		        taskuser.setTaskid(applyId);
		        taskuser.setUserid(kcblForm.getKcr().split(",")[i]);
		        TSysUser user=(TSysUser)this.dao.get(TSysUser.class, kcblForm.getKcr().split(",")[i]);
		        taskuser.setUsername(user.getName());
		        taskuser.setType(TaskUserEnum.WFAJDC_KCBLJCKCR.getCode());
		        this.dao.save(taskuser);
			}
		}
		//2����¼��
		if(StringUtils.isNotBlank(kcblForm.getJlr())){
			String sql=" from TBizTaskuser where taskid=? and type='"+TaskUserEnum.WFAJDC_KCBLJLR.getCode()+"'";
			this.dao.removeFindObjs(sql, applyId);
			TBizTaskuser taskuser=new TBizTaskuser();
	        taskuser.setTaskid(applyId);
	        taskuser.setUserid(kcblForm.getJlr());
	        TSysUser user=(TSysUser)this.dao.get(TSysUser.class, kcblForm.getJlr());
	        taskuser.setUsername(user.getName());
	        taskuser.setType(TaskUserEnum.WFAJDC_KCBLJLR.getCode());
	        this.dao.save(taskuser);
		}
		
		
		
		//���濱���¼�����
        String sqlStr=" from TBizTaskrecordans where taskid=? and kcxwbj=? ";
        dao.removeFindObjs(sqlStr, applyId,"0");
        
        this.dao.remove(this.dao.find("from TBizTaskrecordans where taskid = ? and kcxwbj = '0' ", applyId));
        
        if (content != null){
        	for(int i=0;i<content.length && i<ids.length;i++){
            	TBizTaskrecordans kcxwblda=null;
            	
    			//�Ȳ�һ��𰸱�������Ѿ���ѯ�ʼ�¼����£��������ӡ�
        		kcxwblda=new TBizTaskrecordans();
        		kcxwblda.setCreateby(cur);
        		kcxwblda.setCreated(new Date());
        		kcxwblda.setUpdateby(cur);
        		kcxwblda.setUpdated(new Date());
            	
        		kcxwblda.setRecordid(ids[i]);//id
            	kcxwblda.setContent(String.valueOf(content[i]));//����������
            	
            	kcxwblda.setDanr(danr[i]);//������
            	
            	kcxwblda.setKcxwbj("0");//����ѯ�����ֱ��0���� 1ѯ��
            	kcxwblda.setOrderby(i + 1);//����
            	kcxwblda.setTaskid(applyId);//����id
            	
            	kcxwblda.setIsdel(isdel[i]);
            	kcxwblda.setWttype(wttype[i]);//�������ͣ�0 ϵͳ 1���䣩
            	kcxwblda.setIsActive("Y");
            	
            	dao.save(kcxwblda);
    		}
        }
        
	}
	
	@Override
	public void saveShengchengKcbl(String applyId,String wflx){
		try{
			Work work = workManager.get(applyId);
			TBizTasksurveyrecord kcbl=new TBizTasksurveyrecord();
			//������������λ
			TSysUser user = CtxUtil.getCurUser();
			if (null != applyId && !"".equals(applyId.trim())) {
				
				String szdw="";//���������ڵ�λ
				
				String dsr = "";//����������
				String dz = "";//��ַ
				String starttime = "";//���쿪ʼʱ��
				String endtime = "";//�������ʱ��
				String dd = "";//�ص�
				String kcrname = "";//����������;
				String zfzh = "";//ִ��֤��
				String jlrname = "";//��¼��name
				String jlrzfzh = "";//��¼��ִ��֤��
				String zfrydwmc = "";//ִ����Ա��λ����
				
				String kcblwtda = "";//�����¼�ʴ�

				//�����б�
				ArrayList<HashMap<String, String>> list = xwblManager.getMoreKcxwblWtList(applyId, "0");

				//�鿴������Ŀ����¼
				List<TBizTasksurveyrecord> kcbllList = dao.find(" from TBizTasksurveyrecord t where t.taskid=? ", applyId);
				if (kcbllList != null && kcbllList.size() > 0) {//�м�¼
					kcbl = kcbllList.get(0);
				} else {
					kcbl = new TBizTasksurveyrecord();
				}

				/********************��ʼ��ֵ****************/
				szdw=userManager.getOrgbyUser(work.getZxrId()).getUnitname();//���������ڵ�λ
				
				dsr = kcbl.getDsr();
				dz = kcbl.getDz();
				starttime = DateUtil.getDateTime("yyyy-MM-dd HH:mm",kcbl.getStarttime());//ѯ�ʿ�ʼʱ��
				endtime = DateUtil.getDateTime("yyyy-MM-dd HH:mm",kcbl.getEndtime());//ѯ�ʽ���ʱ��
				dd = kcbl.getDd();
//				kcrname = kcbl.getKcrid();//�����ˣ�������Ϊname�Ժ󿴿���name����id��
				zfzh = kcbl.getZfzh();
//				jlrname = kcbl.getJlr();
				jlrzfzh = kcbl.getJlrzfzh();
				zfrydwmc = kcbl.getZfrydwmc();
				
				//�����ˡ���¼��name
				String sql1=" from TBizTaskuser where taskid=? and type='"+TaskUserEnum.WFAJDC_KCBLJCKCR.getCode()+"'";
				List<TBizTaskuser> taskuserlist1=this.dao.find(sql1,applyId);
				if(taskuserlist1!=null&&taskuserlist1.size()>0){
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0){
							kcrname += ",";
						}
						kcrname += taskuserlist1.get(k).getUsername();
					}
				}
				String sql2=" from TBizTaskuser where taskid=? and type='"+TaskUserEnum.WFAJDC_KCBLJLR.getCode()+"'";
				List<TBizTaskuser> taskuserlist2=this.dao.find(sql2,applyId);
				if(taskuserlist2!=null&&taskuserlist2.size()>0){
					jlrname=taskuserlist2.get(0).getUsername();//��¼��
				}
				for (int i = 0; i < list.size(); i++) {
					String arr1 = StringUtil.isBlank(String.valueOf(list.get(i).get("content"))) ? "" : String.valueOf(list.get(i).get("content"));
					String arr5 = StringUtil.isBlank(String.valueOf(list.get(i).get("answer"))) ? "" : String.valueOf(list.get(i).get("answer"));

					kcblwtda += arr1 + "                                                                     " + "\r" + arr5 + "                                                                     " + "\r";
				}
				/********************��ֵ����****************/
				/*xwblfile=rwpPf.getXwblfileid();
				
				//����Ѿ����ļ�����ɾ��
				if(xwblfile!=null){
					File f = new File(StaticLoad.path+Constants.FILE_PATH, xwblfile.getFilename());
					if (f.exists()){
						f.delete();
					}
				}*/

				long start = System.currentTimeMillis();
				//System.out.println("��poi����word��ʼʱ�䣺" + start);

//            	if(null==xwblfile) {
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
				String tempPath=sc.getRealPath(File.separator) + "excel/wfaj_kcbl.doc";
				//poiʵ��word����
				Map<String, String> param = new HashMap<String, String>();
				
				param.put("$szdw$", szdw);
				
				param.put("$dsr$", dsr);
				param.put("$dz$", dz);
				param.put("$starttime$", starttime);
				param.put("$endtime$", endtime);
				param.put("$dd$", dd);
				param.put("$kcrname$", kcrname);
				param.put("$zfzh$", zfzh);
				param.put("$jlrname$", jlrname);
				param.put("$jlrzfzh$", jlrzfzh);
				param.put("$zfrydwmc$", zfrydwmc);
				param.put("$kcblwtda$", kcblwtda);
				
				String newfile = PoiUtil.createWord(tempPath, dirPath, param);

				//////////////////////////////�����������ͣ�����Ӧ�Ĵ���(���������ĵ�)///////////////////////////////////
				//Υ����
				if ("16".equals(TaskTypeCode.WFAJ.getCode())) {
					File file = new File(newfile);
					if (file.exists()) {
						InputStream is = new FileInputStream(file);
						
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCKCBL.getCode());
						
						commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCKCBL.getCode(), "work", work.getName() + "_���(����)��¼.doc", ((Integer) is.available()).longValue());
					}

					long end = System.currentTimeMillis();
					//System.out.println("��poi����word����ʱ�䣺" + start);
					//System.out.println("��poi����word��ʱ*******************��" + (end - start) + "ms");
				}
			}
		}catch (Exception e) {
			//System.out.println("���ɼ��(����)��¼��¼doc�ļ�������"+ e);
			log.error("", e);
        }
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}

	@Override
	public String getLwnumber(String userIds) {
		String lwnumbers="";
		if(StringUtil.isNotBlank(userIds)){
			String[] ids=userIds.split(",");
			for(int i=0;i<ids.length;i++){
				TSysUser user=userManager.getUser(ids[i]);
				if(i<ids.length-1){
            		lwnumbers+=user.getLawnumber()+",";
            	}else{
            		lwnumbers+=user.getLawnumber();
            	}
			}
		}
		return lwnumbers;
	}
	
	@Override
	public ArrayList<HashMap<String, String>> getBlwt(String applyId, String wflx) {
		TBizTaskandlawobj l = (TBizTaskandlawobj) this.dao.find("from TBizTaskandlawobj where taskid = ? ", applyId).get(0);
		
		String[] wflxs = wflx.split(",");
		String aa = "";
		for (int i = 0; i < wflxs.length; i++) {
			if (i > 0){
				aa += ",";
			}
			aa += "'" + wflxs[i] + "'";
		}
		List<TDataRecord> rs = this.dao.find("select r from TDataRecord r, TDataRecordlawobjtype l where r.id = l.recordid and r.wflx.id in ( " + aa + " ) and r.kcxwbj = ? and l.lawobjtype = ? order by r.orderby", "0", l.getLawobjtype());
		ArrayList<HashMap<String, String>> ret = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> da = new HashMap<String, String>();
		// �ҵ���ִ����������ִ�е����񣬵�ȡ���һ�������ѯ�ʿ����¼��
		StringBuffer sql = new StringBuffer("SELECT d.taskid_ FROM T_Biz_Taskandlawobj d ");
		sql.append(" left join T_BIZ_TASKANDTASKTYPE t on t.TASKID_= d.TASKID_ ");
		sql.append(" left join WORK_ w on w.id_ = d.TASKID_  where  w.isActive_='Y' and w.state_='09' and t.TASKTYPEID_='16' ");
		List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
		Map<String, String> zfdxMap = new HashMap<String, String>();
		if (zfdxlistMap != null && zfdxlistMap.size() == 1) {
			zfdxMap = zfdxlistMap.get(0);
			sql.append(" and d.lawobjid_ = '"+zfdxMap.get("lawobjid")+"'");
			sql.append(" and d.lawobjtype_ = '"+zfdxMap.get("lawobjtype")+"'");
		}
		sql.append(" order by w.archives_time_ desc");
		List<String> tasks = this.dao.findBySql(sql.toString());
		// ���һ�ι鵵����ļ���¼
		String checkedTaskId = "";
		for (int i = 0; i < rs.size(); i++) {
			da = new HashMap<String, String>();
			
			da.put("id", rs.get(i).getId());
			da.put("content", rs.get(i).getContent());
			da.put("answer", "");
			if(tasks!=null && tasks.size()>0) {
				checkedTaskId = tasks.get(0);
				List<TBizTaskrecordans> records = this.dao.find("from TBizTaskrecordans where taskid = ? and  recordid = ?", checkedTaskId, rs.get(i).getId());
				if(records!=null && records.size()>0){
					da.put("answer", records.get(0).getDanr());
				}
			}
			da.put("kcxwbj", "1");
			da.put("orderby", String.valueOf(i + 1));
			da.put("wttype", "0");
			da.put("isdel", rs.get(i).getIsdel());
			
			ret.add(da);
		}
		return ret;
	}
	
	/** ɾ����¼ */
	public void delKcbl(String applyId){
		  String sqlStr=" from TBizTaskrecordans where taskid=?";
	      dao.removeFindObjs(sqlStr, applyId);
	}
	
	/** ����������Υ������ */
	public String getWflx(String applyId){
		  String sqlStr=" from TBizTaskillegaltype where taskid=?";
	      List<TBizTaskillegaltype> wflxList = this.dao.find(sqlStr, applyId);
	      String wflxs="";
	      if(wflxList!=null && wflxList.size()>0){
	    	  for(int i=0;i<wflxList.size();i++){
	    		  wflxs=wflxs+wflxList.get(i).getIllegaltypeid()+",";
	    	  }
	    	  wflxs = wflxs.substring(0,wflxs.length()-1);
	      }
	      return wflxs;
	}
	
}