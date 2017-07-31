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

	/** ��־ */
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
        		xbrName+=xbr.getName()+"��";
        		no+=xbr.getLawnumber()+"��";
        	}else{
        		xbrName+=xbr.getName();
        		no+=xbr.getLawnumber();
        	}
        }
        
        //��ѯ�ʵ�λ����
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
		}else{//Ĭ�ϴ�������
			//1��ѯ��ʱ��
			xwblForm.setStarttime(DateUtil.getDateTime("yyyy-MM-dd HH:mm",new Date()));
			xwblForm.setEndtime(DateUtil.getDateTime("yyyy-MM-dd HH:mm", new Date()));
			//2��ѯ�ʵص㣺Ĭ��ȡִ������ĵ�ַ
			xwblForm.setXwdd(blZfdxForm.getAddress());
			//3����ѯ�ʵ�λ���ƣ�Ĭ��ȡִ���������ƣ�ִ����������Ϊ������ֳʱȡ��λ���ƣ�
			xwblForm.setBxwdwmc(bxwdwmc);
			//4�����������ˣ������ˣ����绰��Ĭ��ȡִ������ķ��������ˣ������ˣ����绰
			xwblForm.setFddbr(blZfdxForm.getManager());
			xwblForm.setFddbrdh(blZfdxForm.getManagermobile());
			//5��ִ����Ա��λ����
			xwblForm.setZfrydwmc(org.getUnitname());
			//6��ִ����Ա����
	        xwblForm.setZfrynames(xbrName);
			//7��ִ����Աִ��֤��
	        xwblForm.setZfzhs(no);
	        //8�����һ�������
	        // �ҵ���ִ����������ִ�е����񣬵�ȡ���һ�������ѯ�ʱ�¼���һ������Ĵ�
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
				// ���һ�ι鵵��������һ�������
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
		 * 0:����id;1:����������;2:����ѯ�����ֱ�� 0���� 1ѯ��;3:�Ƿ��ɾ��;4:����;5:������;
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
		// �ҵ���ִ����������ִ�е����񣬵�ȡ���һ�������ѯ�ʿ����¼��
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
	
	
	@Override
	public ArrayList<HashMap<String, String>> getMoreKcxwblWtList(String applyId,String kcxwbj) {
		/**
		 * 0:����id;1:����������;2:������;3:����ѯ�����ֱ�� 0���� 1ѯ��;4:����;5:����id;6:�������ͣ�0 ϵͳ 1���䣩
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
		
		//����ѯ�ʱ�¼�����
        //2013-3-22�޸� ��������𰸵�ʱ�������֮ǰ�Ĵ𰸼�¼����������ı��ʱ��������������ݡ�Ҳ��������ı���û��update����ֻ�����ӡ�������ж��ȱ�����
        String sqlStr=" from TBizTaskrecordans where taskid=? and kcxwbj=? ";
        dao.removeFindObjs(sqlStr, applyId,"1");
        
        this.dao.remove(this.dao.find("from TBizTaskrecordans where taskid = ? and kcxwbj = '1' ", applyId));
        
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
            	
            	kcxwblda.setKcxwbj("1");//����ѯ�����ֱ��0���� 1ѯ��
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
	public void saveShengchengXwbl(String applyId,String wflx){
		try{
			Work work = workManager.get(applyId);
			TBizTaskinverecord xwbl=new TBizTaskinverecord();
			//������������λ
			if (null != applyId && !"".equals(applyId.trim())) {
				
				String szdw="";//���������ڵ�λ
				
				String starttime = "";//ѯ�ʿ�ʼʱ��
				String endtime = "";//ѯ�ʽ���ʱ��
				String xwdd = "";//ѯ�ʵص�
				String bxwdwmc = "";//��ѯ�ʵ�λ����
				String fddbr = "";//����������
				String fddbrdh = "";//���������˵绰

				String bxwrxm = "";//��ѯ��������
				String bxwrxb = "";//��ѯ�����Ա�
				String bxwrnl = "";//��ѯ��������
				String bxwrzw = "";//��ѯ����ְ��
				String bxwrdh = "";//��ѯ���˵绰

				String bxwzrrxm = "";//��ѯ����Ȼ������
				String bxwzrrxb = "";//��ѯ����Ȼ���Ա�
				String bxwzrrnl = "";//��ѯ����Ȼ������
				String bxwzrrdh = "";//��ѯ����Ȼ�˵绰
				String bxwzrrszdw = "";//��ѯ����Ȼ�����ڵ�λ
				String bxwzrrzz = "";//��ѯ����Ȼ��סַ
				String bxwzrrybagx = "";//��ѯ����Ȼ���뱾����ϵ

				String zfrydwmc = "";//ִ����Ա��λ����
				String zfrynames = "";//ִ����Ա����
				String zfzhs = "";//ִ��֤��
				
				String fddbrsfzh = "";//��������������֤��
				String bxwrsfzh = "";//��ѯ��������֤��
				String bxwzrrsfzh = "";//��ѯ����Ȼ������֤��
				
				String lastans = "";//���ѯ�ʵĴ�

				String xwblwtda = "";//ѯ�ʱ�¼�ʴ�

				//�����б�
				ArrayList<HashMap<String, String>> list = this.getMoreKcxwblWtList(applyId, "1");

				//�鿴�������ѯ�ʱ�¼
				List<TBizTaskinverecord> xwbllList = dao.find(" from TBizTaskinverecord t where t.taskid=? ", applyId);
				if (xwbllList != null && xwbllList.size() > 0) {//�м�¼
					xwbl = xwbllList.get(0);
				} else {
					xwbl = new TBizTaskinverecord();
				}

				/********************��ʼ��ֵ****************/
				szdw=userManager.getOrgbyUser(work.getZxrId()).getUnitname();//���������ڵ�λ
				
				starttime = DateUtil.getDateTime("yyyy-MM-dd HH:mm",xwbl.getStarttime());//ѯ�ʿ�ʼʱ��
				endtime = DateUtil.getDateTime("yyyy-MM-dd HH:mm",xwbl.getEndtime());//ѯ�ʽ���ʱ��
				xwdd = xwbl.getXwdd();//ѯ�ʵص�
				bxwdwmc = xwbl.getBxwdwmc();//��ѯ�ʵ�λ����
				fddbr = xwbl.getFddbr();//����������
				fddbrdh = xwbl.getFddbrdh();//���������˵绰

				bxwrxm = xwbl.getBxwrxm();//��ѯ��������
				bxwrxb = xwbl.getBxwrxb();//��ѯ�����Ա�
				bxwrnl = xwbl.getBxwrnl();//��ѯ��������
				bxwrzw = xwbl.getBxwrzw();//��ѯ����ְ��
				bxwrdh = xwbl.getBxwrdh();//��ѯ���˵绰

				bxwzrrxm = xwbl.getBxwzrrxm();//��ѯ����Ȼ������
				bxwzrrxb = xwbl.getBxwzrrxb();//��ѯ����Ȼ���Ա�
				bxwzrrnl = xwbl.getBxwzrrnl();//��ѯ����Ȼ������
				bxwzrrdh = xwbl.getBxwzrrdh();//��ѯ����Ȼ�˵绰
				bxwzrrszdw = xwbl.getBxwzrrszdw();//��ѯ����Ȼ�����ڵ�λ
				bxwzrrzz = xwbl.getBxwzrrzz();//��ѯ����Ȼ��סַ
				bxwzrrybagx = xwbl.getBxwzrrybagx();//��ѯ����Ȼ���뱾����ϵ

				zfrydwmc = xwbl.getZfrydwmc();//ִ����Ա��λ����
				zfrynames = xwbl.getZfrynames();//ִ����Ա����
				zfzhs = xwbl.getZfzhs();//ִ��֤��
				
				fddbrsfzh = xwbl.getFddbrsfzh();//��������������֤��
				bxwrsfzh = xwbl.getBxwrsfzh();//��ѯ��������֤��
				bxwzrrsfzh = xwbl.getBxwzrrsfzh();//��ѯ����Ȼ������֤��
				
				lastans = xwbl.getLastans();//���ѯ�ʵĴ�

				/**
				 * xb 2013-3-28 �����������޸ģ���һ�����ⲻ��Ҫ���ʡ��֣����ҵ�һ�������ڵ���word��û���»��ߡ�
				 * ������ʽ����ģ���е�һ�������Ѿ����ϣ�����ֻ��Ҫ�ӵ�һ���𰸱������С�
				 */
				for (int i = 0; i < list.size(); i++) {
					String arr1 = StringUtil.isBlank(String.valueOf(list.get(i).get("content"))) ? "" : String.valueOf(list.get(i).get("content"));
					String arr5 = StringUtil.isBlank(String.valueOf(list.get(i).get("answer"))) ? "" : String.valueOf(list.get(i).get("answer"));

					if (i == 0) {
						xwblwtda += "��" + arr5 + "                                                                    " + "\r";
					} else {
						xwblwtda += "�ʣ�" + arr1 + "                                                                     " + "\r" + "��" + arr5 + "                                                                     " + "\r";
					}
				}
				
				// ���һ��
				xwblwtda += "�ʣ�������Щ�����Ƕ����ѯ�ʱ�¼����鿴һ�������Ƿ�һ�£��������飬��ǩ����ע���Ա�¼������ʵ�Ե����" + "                                                                    ";
				xwblwtda += "��" + lastans + "                                                                     ";
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
				String tempPath=sc.getRealPath(File.separator) + "excel/wfaj_xwbl.doc";
				//poiʵ��word����
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

				//////////////////////////////�����������ͣ�����Ӧ�Ĵ���(���������ĵ�)///////////////////////////////////
				//Υ����
				if ("16".equals(TaskTypeCode.WFAJ.getCode())) {
					File file = new File(newfile);
					if (file.exists()) {
						InputStream is = new FileInputStream(file);
						
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCXWBL.getCode());
						
						commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCXWBL.getCode(), "work", work.getName() + "_����ѯ�ʱ�¼.doc", ((Integer) is.available()).longValue());
					}

					long end = System.currentTimeMillis();
					//System.out.println("��poi����word����ʱ�䣺" + start);
					//System.out.println("��poi����word��ʱ*******************��" + (end - start) + "ms");
				}
			}
		}catch (Exception e) {
			//System.out.println("���ɵ���ѯ�ʱ�¼doc�ļ�������"+ e);
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
		//�Ȳ�ѯ�Ƿ����м�¼���еĻ�update��û�Ļ�add
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