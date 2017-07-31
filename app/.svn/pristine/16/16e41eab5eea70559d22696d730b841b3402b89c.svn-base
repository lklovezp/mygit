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
	
	/** 日志 */
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
		//执法对象信息
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
		//获取当前登录人的所属单位
		TSysUser curUser = CtxUtil.getCurUser();
        TSysOrg org = (TSysOrg) this.find("from TSysOrg where id = (select org.id from TSysUserOrg where user.id = ? ) ", curUser.getId()).get(0);
		
		//当事人
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
		}else{//默认带过来的
			//1、当事人： 默认显示受检单位名称
			kcblForm.setDsr(bxwdwmc);
			//2、地 址： 默认取受检单位地址
			kcblForm.setDz(blZfdxForm.getAddress());
			//3、检查（勘察）时间：开始时间，结束时间
			kcblForm.setStarttime(DateUtil.getDateTime("yyyy-MM-dd HH:mm",new Date()));
			kcblForm.setEndtime(DateUtil.getDateTime("yyyy-MM-dd HH:mm", new Date()));
			//4、执法证号：默认取检查人执法证号
			
			//5、执法人员单位名称
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
			kcblForm.setKcr(kcrIds);//检查（勘察）人
			kcblForm.setKcrName(kcrNames);
		}
		String sql2=" from TBizTaskuser where taskid=? and type='"+TaskUserEnum.WFAJDC_KCBLJLR.getCode()+"'";
		List<TBizTaskuser> taskuserlist2=this.dao.find(sql2,applyId);
		if(taskuserlist2!=null&&taskuserlist2.size()>0){
			kcblForm.setJlr(taskuserlist2.get(0).getUserid());//记录人
			kcblForm.setJlrName(taskuserlist2.get(0).getUsername());
		}
		
		
		return kcblForm;
		
	}

	@Override
	public List getKcxwblWtList(String applyId, String rwlx, String wflx,
			String kcxwbj) {
		/**
		 * 0:问题id;1:问题项内容;2:勘察询问区分标记 0勘察 1询问;3:是否可删除;4:排序;5:答案内容;
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
		 * 0:问题id;1:问题项内容;2:答案内容;3:勘察询问区分标记 0勘察 1询问;4:排序;5:任务id;6:问题类型（0 系统 1补充）
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
		 * 0:问题id;1:问题项内容;2:答案内容;3:勘察询问区分标记 0勘察 1询问;4:排序;5:任务id;6:问题类型（0 系统 1补充）
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
		
		//1、检查（勘察）人
		if(StringUtils.isNotBlank(kcblForm.getKcr())){
			//2015-7-15 修改 检查（勘察）人多选
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
		//2、记录人
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
		
		
		
		//保存勘察笔录问题答案
        String sqlStr=" from TBizTaskrecordans where taskid=? and kcxwbj=? ";
        dao.removeFindObjs(sqlStr, applyId,"0");
        
        this.dao.remove(this.dao.find("from TBizTaskrecordans where taskid = ? and kcxwbj = '0' ", applyId));
        
        if (content != null){
        	for(int i=0;i<content.length && i<ids.length;i++){
            	TBizTaskrecordans kcxwblda=null;
            	
    			//先查一遍答案表，如果已经有询问记录则更新，否则添加。
        		kcxwblda=new TBizTaskrecordans();
        		kcxwblda.setCreateby(cur);
        		kcxwblda.setCreated(new Date());
        		kcxwblda.setUpdateby(cur);
        		kcxwblda.setUpdated(new Date());
            	
        		kcxwblda.setRecordid(ids[i]);//id
            	kcxwblda.setContent(String.valueOf(content[i]));//问题项内容
            	
            	kcxwblda.setDanr(danr[i]);//答案内容
            	
            	kcxwblda.setKcxwbj("0");//勘察询问区分标记0勘察 1询问
            	kcxwblda.setOrderby(i + 1);//排序
            	kcxwblda.setTaskid(applyId);//任务id
            	
            	kcxwblda.setIsdel(isdel[i]);
            	kcxwblda.setWttype(wttype[i]);//问题类型（0 系统 1补充）
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
			//区域作工作单位
			TSysUser user = CtxUtil.getCurUser();
			if (null != applyId && !"".equals(applyId.trim())) {
				
				String szdw="";//主办人所在单位
				
				String dsr = "";//当事人名称
				String dz = "";//地址
				String starttime = "";//勘察开始时间
				String endtime = "";//勘察结束时间
				String dd = "";//地点
				String kcrname = "";//勘察人姓名;
				String zfzh = "";//执法证号
				String jlrname = "";//记录人name
				String jlrzfzh = "";//记录人执法证号
				String zfrydwmc = "";//执法人员单位名称
				
				String kcblwtda = "";//勘察笔录问答案

				//问题列表
				ArrayList<HashMap<String, String>> list = xwblManager.getMoreKcxwblWtList(applyId, "0");

				//查看该任务的勘察笔录
				List<TBizTasksurveyrecord> kcbllList = dao.find(" from TBizTasksurveyrecord t where t.taskid=? ", applyId);
				if (kcbllList != null && kcbllList.size() > 0) {//有记录
					kcbl = kcbllList.get(0);
				} else {
					kcbl = new TBizTasksurveyrecord();
				}

				/********************开始赋值****************/
				szdw=userManager.getOrgbyUser(work.getZxrId()).getUnitname();//主办人所在单位
				
				dsr = kcbl.getDsr();
				dz = kcbl.getDz();
				starttime = DateUtil.getDateTime("yyyy-MM-dd HH:mm",kcbl.getStarttime());//询问开始时间
				endtime = DateUtil.getDateTime("yyyy-MM-dd HH:mm",kcbl.getEndtime());//询问结束时间
				dd = kcbl.getDd();
//				kcrname = kcbl.getKcrid();//勘察人（先理解为name以后看看是name还是id）
				zfzh = kcbl.getZfzh();
//				jlrname = kcbl.getJlr();
				jlrzfzh = kcbl.getJlrzfzh();
				zfrydwmc = kcbl.getZfrydwmc();
				
				//勘察人、记录人name
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
					jlrname=taskuserlist2.get(0).getUsername();//记录人
				}
				for (int i = 0; i < list.size(); i++) {
					String arr1 = StringUtil.isBlank(String.valueOf(list.get(i).get("content"))) ? "" : String.valueOf(list.get(i).get("content"));
					String arr5 = StringUtil.isBlank(String.valueOf(list.get(i).get("answer"))) ? "" : String.valueOf(list.get(i).get("answer"));

					kcblwtda += arr1 + "                                                                     " + "\r" + arr5 + "                                                                     " + "\r";
				}
				/********************赋值结束****************/
				/*xwblfile=rwpPf.getXwblfileid();
				
				//如果已经有文件则先删除
				if(xwblfile!=null){
					File f = new File(StaticLoad.path+Constants.FILE_PATH, xwblfile.getFilename());
					if (f.exists()){
						f.delete();
					}
				}*/

				long start = System.currentTimeMillis();
				//System.out.println("用poi生成word开始时间：" + start);

//            	if(null==xwblfile) {
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
				String tempPath=sc.getRealPath(File.separator) + "excel/wfaj_kcbl.doc";
				//poi实现word操作
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

				//////////////////////////////根据任务类型，做相应的处理(保存生成文档)///////////////////////////////////
				//违法的
				if ("16".equals(TaskTypeCode.WFAJ.getCode())) {
					File file = new File(newfile);
					if (file.exists()) {
						InputStream is = new FileInputStream(file);
						
						//先删除旧的，再保存新的；
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCKCBL.getCode());
						
						commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCKCBL.getCode(), "work", work.getName() + "_检查(勘察)笔录.doc", ((Integer) is.available()).longValue());
					}

					long end = System.currentTimeMillis();
					//System.out.println("用poi生成word结束时间：" + start);
					//System.out.println("用poi生成word用时*******************：" + (end - start) + "ms");
				}
			}
		}catch (Exception e) {
			//System.out.println("生成检查(勘察)笔录笔录doc文件出错："+ e);
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
		// 找到本执法对象所有执行的任务，调取最近一次任务的询问勘察笔录答案
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
		// 最近一次归档任务的检查记录
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
	
	/** 删除笔录 */
	public void delKcbl(String applyId){
		  String sqlStr=" from TBizTaskrecordans where taskid=?";
	      dao.removeFindObjs(sqlStr, applyId);
	}
	
	/** 根据任务获得违法类型 */
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
