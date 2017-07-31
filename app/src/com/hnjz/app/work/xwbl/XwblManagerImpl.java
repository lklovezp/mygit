package com.hnjz.app.work.xwbl;

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
import com.hnjz.app.work.enums.ZfdxLx;
import com.hnjz.app.work.jcd.JcdManagerImpl;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.po.TBizBlwtx;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.TBizTaskinverecord;
import com.hnjz.app.work.po.TBizTaskrecordans;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.zx.BlZfdxForm;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
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

@Service("xwblManagerImpl")
public class XwblManagerImpl extends ManagerImpl implements XwblManager ,ServletContextAware {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(JcdManagerImpl.class);
	
	private ServletContext sc;

	@Autowired
    private CommonManager                 commonManager;
	
	@Autowired
    protected WorkManagerImpl workManager;
	
	@Autowired
    private UserManager      userManager;
	
	@Autowired
    private CommWorkManager    commWorkManager;

	@Override
	public XwblForm getXwblFormData(String applyId,String wflx) {
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
		
		XwblForm xwblForm=new XwblForm();
		
		QueryCondition data = new QueryCondition();
		StringBuffer sql = new StringBuffer();
		sql.append(" from TBizTaskinverecord where taskid=:taskid ");
		data.put("taskid", applyId);
		List<TBizTaskinverecord> list = this.dao.find(sql.toString(),data);
		TBizTaskinverecord po=new TBizTaskinverecord();
		
		TSysUser curUser = CtxUtil.getCurUser();
        TSysOrg org = (TSysOrg) this.find("from TSysOrg where id = (select org.id from TSysUserOrg where user.id = ? ) ", curUser.getId()).get(0);
        List<Combobox> rygh=commWorkManager.ryghCombo(applyId);
        String xbrName = "";
        String no = "";
        TSysUser xbr = new TSysUser();
        for (int i = 0; i < rygh.size(); i++) {
        	xbr = (TSysUser) this.get(TSysUser.class, rygh.get(i).getId());
        	if(i<rygh.size()-1){
        		xbrName+=xbr.getName()+"、";
        		no+=xbr.getLawnumber()+"、";
        	}else{
        		xbrName+=xbr.getName();
        		no+=xbr.getLawnumber();
        	}
        }
        
        //被询问单位名称
        String bxwdwmc="";
        Work work = workManager.get(applyId);
		if(ZfdxLx.XQYZ.getCode().equals(work.getZfdxType())){
			bxwdwmc=blZfdxForm.getXqyzDwmc();
		}else{
			bxwdwmc=blZfdxForm.getLawobjname();
		}
		
		if(list!=null&&list.size()>0){
			po=list.get(0);
			xwblForm.setId(po.getId());
			xwblForm.setTaskid(applyId);
			xwblForm.setStarttime(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getStarttime()?new Date():po.getStarttime()));
			xwblForm.setEndtime(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getEndtime()?new Date():po.getEndtime()));
			xwblForm.setXwdd(StringUtil.isBlank(po.getXwdd())?blZfdxForm.getAddress():po.getXwdd());
			xwblForm.setBxwdwmc(StringUtil.isBlank(po.getBxwdwmc())?bxwdwmc:po.getBxwdwmc());
			xwblForm.setFddbr(StringUtil.isBlank(po.getFddbr())?blZfdxForm.getManager():po.getFddbr());
			xwblForm.setFddbrdh(StringUtil.isBlank(po.getFddbrdh())?blZfdxForm.getManagermobile():po.getFddbrdh());
			xwblForm.setBxwrxm(po.getBxwrxm());
			xwblForm.setBxwrxb(po.getBxwrxb());
			xwblForm.setBxwrnl(po.getBxwrnl());
			xwblForm.setBxwrzw(po.getBxwrzw());
			xwblForm.setBxwrdh(po.getBxwrdh());
			xwblForm.setBxwzrrxm(po.getBxwzrrxm());
			xwblForm.setBxwzrrxb(po.getBxwzrrxb());
			xwblForm.setBxwzrrnl(po.getBxwzrrnl());
			xwblForm.setBxwzrrdh(po.getBxwzrrdh());
			xwblForm.setBxwzrrszdw(po.getBxwzrrszdw());
			xwblForm.setBxwzrrzz(po.getBxwzrrzz());
			xwblForm.setBxwzrrybagx(po.getBxwzrrybagx());
			xwblForm.setZfrydwmc(StringUtil.isBlank(po.getZfrydwmc())?org.getUnitname():po.getZfrydwmc());
			xwblForm.setZfrynames(StringUtil.isBlank(po.getZfrynames())?xbrName:po.getZfrynames());
			xwblForm.setZfzhs(StringUtil.isBlank(po.getZfzhs())?no:po.getZfzhs());
			
			xwblForm.setFddbrsfzh(po.getFddbrsfzh());
			xwblForm.setBxwrsfzh(po.getBxwrsfzh());
			xwblForm.setBxwzrrsfzh(po.getBxwzrrsfzh());
			
			xwblForm.setLastans(po.getLastans());
		}else{//默认带过来的
			//1、询问时间
			xwblForm.setStarttime(DateUtil.getDateTime("yyyy-MM-dd HH:mm",new Date()));
			xwblForm.setEndtime(DateUtil.getDateTime("yyyy-MM-dd HH:mm", new Date()));
			//2、询问地点：默认取执法对象的地址
			xwblForm.setXwdd(blZfdxForm.getAddress());
			//3、被询问单位名称：默认取执法对象名称（执法对象类型为畜禽养殖时取单位名称）
			xwblForm.setBxwdwmc(bxwdwmc);
			//4、法定代表人（负责人）、电话：默认取执法对象的法定代表人（负责人）、电话
			xwblForm.setFddbr(blZfdxForm.getManager());
			xwblForm.setFddbrdh(blZfdxForm.getManagermobile());
			//5、执法人员单位名称
			xwblForm.setZfrydwmc(org.getUnitname());
			//6、执法人员名称
	        xwblForm.setZfrynames(xbrName);
			//7、执法人员执法证号
	        xwblForm.setZfzhs(no);
	        //8、最后一个问题答案
	        // 找到本执法对象所有执行的任务，调取最近一次任务的询问笔录最后一个问题的答案
	        if(StringUtils.isBlank(xwblForm.getLastans())){
	        	StringBuffer sql1 = new StringBuffer("SELECT c.LASTANS_ FROM T_Biz_Taskinverecord c");
	        	sql1.append(" left join T_Biz_Taskandlawobj d  on d.taskid_ = c.taskid_ ");
	        	sql1.append(" left join T_BIZ_TASKILLEGALTYPE i on i.TASKID_= c.TASKID_ ");
	        	sql1.append(" left join T_BIZ_TASKANDTASKTYPE t on t.TASKID_= c.TASKID_ ");
				sql1.append(" left join WORK_ w on w.id_ = c.TASKID_  where w.isActive_='Y' and i.ILLEGALTYPEID_ = '"+wflx+"' and w.state_='09' and t.TASKTYPEID_='16' ");
				List<Map<String, String>> zfdxlistMap1 = commWorkManager.zfdxTableData(applyId);
				Map<String, String> zfdxMap1 = new HashMap<String, String>();
				if(zfdxlistMap1!=null&&zfdxlistMap1.size()==1){
					zfdxMap1 = zfdxlistMap1.get(0);
					sql1.append(" and d.lawobjid_ = '"+zfdxMap1.get("lawobjid")+"'");
					sql1.append(" and d.lawobjtype_ = '"+zfdxMap1.get("lawobjtype")+"'");
				}
				sql1.append(" order by w.archives_time_ desc");
				List<String> ans = this.dao.findBySql(sql1.toString());
				// 最近一次归档任务的最后一个问题答案
				if(ans!=null && ans.size()>0) {
					xwblForm.setLastans(ans.get(0));
				}
	        }
		}
		
		return xwblForm;
		
	}

	@Override
	public List<HashMap<String, String>> getKcxwblWtList(String applyId, String rwlx, String wflx,
			String kcxwbj) {
		/**
		 * 0:问题id;1:问题项内容;2:勘察询问区分标记 0勘察 1询问;3:是否可删除;4:排序;5:答案内容;
		 */
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.ID_,(case when C.CONTENT_ is NULL then A.CONTENT_ else C.CONTENT_ end) as CONTENT_,B.DANR_,A.KCXWBJ_,A.ORDER_ as ORDERBY_,'0' as WTTYPE_, A.ISDEL_");
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
		
		List<Object[]> list = dao.findBySql(buf.toString());
		
		ArrayList<HashMap<String, String>> ret = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> da = new HashMap<String, String>();
		
		for (int i = 0; i < list.size(); i++) {
			da = new HashMap<String, String>();
			
			da.put("id", list.get(i)[0].toString());
			da.put("content", String.valueOf(list.get(i)[1]));
			da.put("answer", StringUtils.isBlank(String.valueOf(list.get(i)[2])) ? "" : String.valueOf(list.get(i)[2]));
			da.put("kcxwbj", String.valueOf(list.get(i)[3]));
			da.put("orderby", String.valueOf(list.get(i)[4]));
			da.put("wttype", String.valueOf(list.get(i)[5]));
			da.put("isdel", String.valueOf(list.get(i)[5]));
			
			ret.add(da);
		}
		return ret;
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
		List<TDataRecord> rs = this.dao.find("select r from TDataRecord r, TDataRecordlawobjtype l where r.id = l.recordid and r.wflx.id in ( " + aa + " ) and r.kcxwbj = ? and l.lawobjtype = ? order by r.orderby", "1", l.getLawobjtype());
		ArrayList<HashMap<String, String>> ret = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> da = new HashMap<String, String>();
		// 找到本执法对象所有执行的任务，调取最近一次任务的询问勘察笔录答案
		StringBuffer sql = new StringBuffer("SELECT d.taskid_ FROM T_Biz_Taskandlawobj d ");
		sql.append(" left join T_BIZ_TASKANDTASKTYPE t on t.TASKID_= d.TASKID_ ");
		sql.append(" left join WORK_ w on w.id_ = d.TASKID_  where w.isActive_='Y' and w.state_='09' and t.TASKTYPEID_='16' ");
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
	
	
	@Override
	public ArrayList<HashMap<String, String>> getMoreKcxwblWtList(String applyId,String kcxwbj) {
		/**
		 * 0:问题id;1:问题项内容;2:答案内容;3:勘察询问区分标记 0勘察 1询问;4:排序;5:任务id;6:问题类型（0 系统 1补充）
		 */
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.ID_,A.CONTENT_,A.DANR_,A.KCXWBJ_,A.ORDERBY_,A.WTTYPE_, A.ISDEL_ ");
		buf.append(" FROM T_BIZ_TASKRECORDANS A ");
		buf.append(" WHERE A.TASKID_='"+applyId+"' ");
		buf.append(" AND A.KCXWBJ_ ='"+kcxwbj+"'  ");
		buf.append(" ORDER BY A.ORDERBY_ ASC");
		
		List<Object[]> list = dao.findBySql(buf.toString());
		
		ArrayList<HashMap<String, String>> ret = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> da = new HashMap<String, String>();
		
		for (int i = 0; i < list.size(); i++) {
			da = new HashMap<String, String>();
			
			da.put("id", list.get(i)[0].toString());
			da.put("content", String.valueOf(list.get(i)[1]));
			da.put("answer", StringUtil.isBlank(String.valueOf(list.get(i)[2])) ? "" : String.valueOf(list.get(i)[2]));
			da.put("kcxwbj", String.valueOf(list.get(i)[3]));
			da.put("orderby", String.valueOf(list.get(i)[4]));
			da.put("wttype", String.valueOf(list.get(i)[5]));
			da.put("isdel", String.valueOf(list.get(i)[6]));
			
			ret.add(da);
		}
		
		return ret;
	}
	
	@Override
	public void saveXwbl(XwblForm xwblForm,String applyId,String wflx,String[] ids, String[] content, String[] danr,String[] wttype, String[] isdel){
		TSysUser cur = CtxUtil.getCurUser();
		TBizTaskinverecord tBizTaskinverecord=new TBizTaskinverecord();
		if(StringUtils.isNotBlank(xwblForm.getId())){
			tBizTaskinverecord=(TBizTaskinverecord)dao.get(TBizTaskinverecord.class, xwblForm.getId());
    		
			tBizTaskinverecord.setUpdateby(cur);
			tBizTaskinverecord.setUpdated(new Date());
        }else{
        	tBizTaskinverecord.setCreateby(cur);
        	tBizTaskinverecord.setCreated(new Date());
        	tBizTaskinverecord.setUpdateby(cur);
			tBizTaskinverecord.setUpdated(new Date());
        }
		tBizTaskinverecord.setId(xwblForm.getId());
		tBizTaskinverecord.setTaskid(applyId);
		try {
			tBizTaskinverecord.setStarttime(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm", xwblForm.getStarttime()));
			tBizTaskinverecord.setEndtime(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm", xwblForm.getEndtime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tBizTaskinverecord.setXwdd(xwblForm.getXwdd());
		tBizTaskinverecord.setBxwdwmc(xwblForm.getBxwdwmc());
		tBizTaskinverecord.setFddbr(xwblForm.getFddbr());
		tBizTaskinverecord.setFddbrdh(xwblForm.getFddbrdh());
		tBizTaskinverecord.setBxwrxm(xwblForm.getBxwrxm());
		tBizTaskinverecord.setBxwrxb(xwblForm.getBxwrxb());
		tBizTaskinverecord.setBxwrnl(xwblForm.getBxwrnl());
		tBizTaskinverecord.setBxwrzw(xwblForm.getBxwrzw());
		tBizTaskinverecord.setBxwrdh(xwblForm.getBxwrdh());
		tBizTaskinverecord.setBxwzrrxm(xwblForm.getBxwzrrxm());
		tBizTaskinverecord.setBxwzrrxb(xwblForm.getBxwzrrxb());
		tBizTaskinverecord.setBxwzrrnl(xwblForm.getBxwzrrnl());
		tBizTaskinverecord.setBxwzrrdh(xwblForm.getBxwzrrdh());
		tBizTaskinverecord.setBxwzrrszdw(xwblForm.getBxwzrrszdw());
		tBizTaskinverecord.setBxwzrrzz(xwblForm.getBxwzrrzz());
		tBizTaskinverecord.setBxwzrrybagx(xwblForm.getBxwzrrybagx());
		tBizTaskinverecord.setZfrydwmc(xwblForm.getZfrydwmc());
		tBizTaskinverecord.setZfrynames(xwblForm.getZfrynames());
		tBizTaskinverecord.setZfzhs(xwblForm.getZfzhs());
		
		tBizTaskinverecord.setFddbrsfzh(xwblForm.getFddbrsfzh());
		tBizTaskinverecord.setBxwrsfzh(xwblForm.getBxwrsfzh());
		tBizTaskinverecord.setBxwzrrsfzh(xwblForm.getBxwzrrsfzh());
		
		tBizTaskinverecord.setLastans(xwblForm.getLastans());
		
		tBizTaskinverecord.setIsActive(YnEnum.Y.getCode());
		
		dao.save(tBizTaskinverecord);
		
		//保存询问笔录问题答案
        //2013-3-22修改 保存问题答案的时候，先清空之前的答案记录，以问题项改变的时候免产生垃圾数据。也就是下面的保存没有update更新只有添加。下面的判断先保留。
        String sqlStr=" from TBizTaskrecordans where taskid=? and kcxwbj=? ";
        dao.removeFindObjs(sqlStr, applyId,"1");
        
        this.dao.remove(this.dao.find("from TBizTaskrecordans where taskid = ? and kcxwbj = '1' ", applyId));
        
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
            	
            	kcxwblda.setKcxwbj("1");//勘察询问区分标记0勘察 1询问
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
	public void saveShengchengXwbl(String applyId,String wflx){
		try{
			Work work = workManager.get(applyId);
			TBizTaskinverecord xwbl=new TBizTaskinverecord();
			//区域作工作单位
			if (null != applyId && !"".equals(applyId.trim())) {
				
				String szdw="";//主办人所在单位
				
				String starttime = "";//询问开始时间
				String endtime = "";//询问结束时间
				String xwdd = "";//询问地点
				String bxwdwmc = "";//被询问单位名称
				String fddbr = "";//法定代表人
				String fddbrdh = "";//法定代表人电话

				String bxwrxm = "";//被询问人姓名
				String bxwrxb = "";//被询问人性别
				String bxwrnl = "";//被询问人年龄
				String bxwrzw = "";//被询问人职务
				String bxwrdh = "";//被询问人电话

				String bxwzrrxm = "";//被询问自然人姓名
				String bxwzrrxb = "";//被询问自然人性别
				String bxwzrrnl = "";//被询问自然人年龄
				String bxwzrrdh = "";//被询问自然人电话
				String bxwzrrszdw = "";//被询问自然人所在单位
				String bxwzrrzz = "";//被询问自然人住址
				String bxwzrrybagx = "";//被询问自然人与本案关系

				String zfrydwmc = "";//执法人员单位名称
				String zfrynames = "";//执法人员姓名
				String zfzhs = "";//执法证号
				
				String fddbrsfzh = "";//法定代表人身份证号
				String bxwrsfzh = "";//被询问人身份证号
				String bxwzrrsfzh = "";//被询问自然人身份证号
				
				String lastans = "";//最后询问的答案

				String xwblwtda = "";//询问笔录问答案

				//问题列表
				ArrayList<HashMap<String, String>> list = this.getMoreKcxwblWtList(applyId, "1");

				//查看该任务的询问笔录
				List<TBizTaskinverecord> xwbllList = dao.find(" from TBizTaskinverecord t where t.taskid=? ", applyId);
				if (xwbllList != null && xwbllList.size() > 0) {//有记录
					xwbl = xwbllList.get(0);
				} else {
					xwbl = new TBizTaskinverecord();
				}

				/********************开始赋值****************/
				szdw=userManager.getOrgbyUser(work.getZxrId()).getUnitname();//主办人所在单位
				
				starttime = DateUtil.getDateTime("yyyy-MM-dd HH:mm",xwbl.getStarttime());//询问开始时间
				endtime = DateUtil.getDateTime("yyyy-MM-dd HH:mm",xwbl.getEndtime());//询问结束时间
				xwdd = xwbl.getXwdd();//询问地点
				bxwdwmc = xwbl.getBxwdwmc();//被询问单位名称
				fddbr = xwbl.getFddbr();//法定代表人
				fddbrdh = xwbl.getFddbrdh();//法定代表人电话

				bxwrxm = xwbl.getBxwrxm();//被询问人姓名
				bxwrxb = xwbl.getBxwrxb();//被询问人性别
				bxwrnl = xwbl.getBxwrnl();//被询问人年龄
				bxwrzw = xwbl.getBxwrzw();//被询问人职务
				bxwrdh = xwbl.getBxwrdh();//被询问人电话

				bxwzrrxm = xwbl.getBxwzrrxm();//被询问自然人姓名
				bxwzrrxb = xwbl.getBxwzrrxb();//被询问自然人性别
				bxwzrrnl = xwbl.getBxwzrrnl();//被询问自然人年龄
				bxwzrrdh = xwbl.getBxwzrrdh();//被询问自然人电话
				bxwzrrszdw = xwbl.getBxwzrrszdw();//被询问自然人所在单位
				bxwzrrzz = xwbl.getBxwzrrzz();//被询问自然人住址
				bxwzrrybagx = xwbl.getBxwzrrybagx();//被询问自然人与本案关系

				zfrydwmc = xwbl.getZfrydwmc();//执法人员单位名称
				zfrynames = xwbl.getZfrynames();//执法人员姓名
				zfzhs = xwbl.getZfzhs();//执法证号
				
				fddbrsfzh = xwbl.getFddbrsfzh();//法定代表人身份证号
				bxwrsfzh = xwbl.getBxwrsfzh();//被询问人身份证号
				bxwzrrsfzh = xwbl.getBxwzrrsfzh();//被询问自然人身份证号
				
				lastans = xwbl.getLastans();//最后询问的答案

				/**
				 * xb 2013-3-28 根据需求变更修改：第一个问题不需要“问”字，并且第一个问题在导出word中没有下划线。
				 * 处理方式：在模板中第一个问题已经配上，这里只需要从第一个答案遍历就行。
				 */
				for (int i = 0; i < list.size(); i++) {
					String arr1 = StringUtil.isBlank(String.valueOf(list.get(i).get("content"))) ? "" : String.valueOf(list.get(i).get("content"));
					String arr5 = StringUtil.isBlank(String.valueOf(list.get(i).get("answer"))) ? "" : String.valueOf(list.get(i).get("answer"));

					if (i == 0) {
						xwblwtda += "答：" + arr5 + "                                                                    " + "\r";
					} else {
						xwblwtda += "问：" + arr1 + "                                                                     " + "\r" + "答：" + arr5 + "                                                                     " + "\r";
					}
				}
				
				// 最后一题
				xwblwtda += "问：以上这些是我们对你的询问笔录，请查看一下内容是否一致，如无异议，请签名并注明对笔录内容真实性的意见" + "                                                                    ";
				xwblwtda += "答：" + lastans + "                                                                     ";
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
				String tempPath=sc.getRealPath(File.separator) + "excel/wfaj_xwbl.doc";
				//poi实现word操作
				Map<String, String> param = new HashMap<String, String>();
//				param.put("$area_dept$", user.getArea().getDeptName());
				
				param.put("$szdw$", szdw);
				
				param.put("$area_dept$", "");
				param.put("$starttime$", starttime);
				param.put("$endtime$", endtime);
				param.put("$xwdd$", xwdd);
				param.put("$bxwdwmc$", bxwdwmc);
				param.put("$fddbr$", fddbr);
				param.put("$fddbrdh$", fddbrdh);
				param.put("$bxwrxm$", bxwrxm);
				param.put("$bxwrxb$", bxwrxb);
				param.put("$bxwrnl$", bxwrnl);
				param.put("$bxwrzw$", bxwrzw);
				param.put("$bxwrdh$", bxwrdh);
				param.put("$bxwzrrxm$", bxwzrrxm);
				param.put("$bxwzrrxb$", bxwzrrxb);
				param.put("$bxwzrrnl$", bxwzrrnl);
				param.put("$bxwzrrdh$", bxwzrrdh);
				param.put("$bxwzrrszdw$", bxwzrrszdw);
				param.put("$bxwzrrzz$", bxwzrrzz);
				param.put("$bxwzrrybagx$", bxwzrrybagx);
				param.put("$zfrydwmc$", zfrydwmc);
				param.put("$zfrynames$", zfrynames);
				param.put("$zfzhs$", zfzhs);
				
				param.put("$fddbrsfzh$", fddbrsfzh);
				param.put("$bxwrsfzh$", bxwrsfzh);
				param.put("$bxwzrrsfzh$", bxwzrrsfzh);
				
				param.put("$xwblwtda$", xwblwtda);
				
				String newfile = PoiUtil.createWord(tempPath, dirPath, param);

				//////////////////////////////根据任务类型，做相应的处理(保存生成文档)///////////////////////////////////
				//违法的
				if ("16".equals(TaskTypeCode.WFAJ.getCode())) {
					File file = new File(newfile);
					if (file.exists()) {
						InputStream is = new FileInputStream(file);
						
						//先删除旧的，再保存新的；
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCXWBL.getCode());
						
						commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCXWBL.getCode(), "work", work.getName() + "_调查询问笔录.doc", ((Integer) is.available()).longValue());
					}

					long end = System.currentTimeMillis();
					//System.out.println("用poi生成word结束时间：" + start);
					//System.out.println("用poi生成word用时*******************：" + (end - start) + "ms");
				}
			}
		}catch (Exception e) {
			//System.out.println("生成调查询问笔录doc文件出错："+ e);
			log.error("", e);
        }
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
	@Override
	public String saveWT(String applyId,String wtid,String wtcontent) {
		String result="";
		//先查询是否已有记录，有的话update，没的话add
		List<TBizBlwtx> list = new ArrayList<TBizBlwtx>();
		
		list = dao.find(" from TBizBlwtx p where p.rwid =? and p.wtxid=? ",applyId,wtid);
		
		if(list.size()>0){
			TBizBlwtx tBizBlwtx_old = list.get(0);
			tBizBlwtx_old.setContent(wtcontent);
			dao.save(tBizBlwtx_old);
			result=tBizBlwtx_old.getId();
		}else{
			TBizBlwtx tBizBlwtx_new = new TBizBlwtx();
			tBizBlwtx_new.setRwid(applyId);
			tBizBlwtx_new.setWtxid(wtid);
			tBizBlwtx_new.setContent(wtcontent);
			dao.save(tBizBlwtx_new);
			result=tBizBlwtx_new.getId();
		}
		
		return result;
	}
}
