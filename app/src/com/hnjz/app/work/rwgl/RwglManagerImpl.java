package com.hnjz.app.work.rwgl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.Constants;
import com.hnjz.IndexManager;
import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.zfdx.lawobjtype.ZfdxManager;
import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkProcessEnum;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.enums.ZfdxLx;
import com.hnjz.app.work.hjgl.HjglManager;
import com.hnjz.app.work.hjgl.Vhjgl;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.manager.WorkProxy;
import com.hnjz.app.work.manager.nodes.WorkPf;
import com.hnjz.app.work.manager.nodes.ZxNode;
import com.hnjz.app.work.po.TBizMoreCheck;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.TBizTaskandtasktype;
import com.hnjz.app.work.po.TBizTaskuser;
import com.hnjz.app.work.po.TBizXfbjd;
import com.hnjz.app.work.po.VdbWork;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.po.WorkLog;
import com.hnjz.app.work.zx.BlMainForm;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.app.work.zx.ZxWorkManager;
import com.hnjz.app.work.zxzz.ZxzzManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.NullControllHashMap;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.facade.domain.WorkDto;
import com.hnjz.sys.configchecktemplate.ConfigManager;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;
import com.hnjz.sys.user.UserPosition;
import com.hnjz.wf.bean.NextActionBean;
import com.hnjz.wf.business.INextActions;
import com.hnjz.wf.business.NextActionsFactory;
import com.hnjz.wf.enums.ProcessEnum;

@Service("rwglManagerImpl")
public class RwglManagerImpl extends ManagerImpl implements RwglManager {
	
	@Autowired
    protected Dao dao;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	protected WorkPf workPf;
	
	@Autowired
    private UserManager userManager;
	
	/**��־*/
    private static final Log log = LogFactory.getLog(RwglManagerImpl.class);
    
    /**WorkNode�Ĵ���**/
    @Autowired
    private WorkProxy               workProxy;
    
    @Autowired
	private CommonManager commonManager;
    
    @Autowired
    protected WorkManagerImpl workManager;
    
    @Autowired
    private CommWorkManager    commWorkManager;
    
    @Autowired
    private HjglManager      hjglManager;
    
    @Autowired
    protected ZxNode zxNode;
    
    private ServletContext sc;
    
    /** ���Ź��� */
    @Autowired
    private OrgManager              orgManager;
    
    @Autowired
    private WorkDao                 workDao;

    @Autowired
    private ZxWorkManager    zxWorkManager;
    
    @Autowired
    private ZxzzManager    zxzzManager;
    
    @Autowired
	private ConfigManager configManager;
    
    @Autowired
    private IndexManager     indexManager;
    
    @Autowired
	private DicManager dicManager;
    
    @Autowired
	private ZfdxManager zfdxManager;
    
	@Override
	public FyWebResult getRwpfList(String rwmc, String rwly, String page,
			String pageSize) throws Exception {
		QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        sql.append("from Work where 1=1");
        if (StringUtils.isNotBlank(rwmc)) {
            sql.append(" and (name like :rwmc)");
            data.putLike("rwmc", rwmc);
        }
        if (StringUtils.isNotBlank(rwly)) {
            sql.append(" and source=:rwly");
            data.put("rwly", rwly);
        }
        sql.append(" and createUser=:createUser");
        data.put("createUser", CtxUtil.getCurUser());
        sql.append(" and startTime is null ");//����ʼʱ��Ϊ�գ���Ϊ�����ɵ�����
//        sql.append(" and taskstarted is null");
        sql.append(" order by createTime DESC ");
        
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<Work> s = pos.getRe();
        Map<String, String> row = null;
        for (Work ele : s) {
            row = new HashMap<String, String>();
    		
    		//������Դ���ƣ���Ϊ�ǲ鿴ҳ�棬ֱ����ԭ�ֶ��ˣ�
    		String str1="";
    		if(ele.getSource()!=null){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
    		}
    		//�����ܼ�����
    		String str2="";
    		if(ele.getSecurity()!=null){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWMJ.getCode(),ele.getSecurity());
    		}
    		//�����̶�����
    		String str3="";
    		if(ele.getEmergency()!=null){
    			str3=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
    		}
    		row.put("rwly", str1);//������Դ
    		row.put("rwmj", str2);//�����ܼ�
    		row.put("jjcd", str3);//�����̶�
            
            row.put("id", ele.getId());
            //row.put("rwmc", ele.getName());
            if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 35){
            	row.put("rwmc", ele.getName().substring(0, 34)+"...");
            	row.put("rwmc1", ele.getName());
	        }else{
	        	row.put("rwmc", ele.getName());//��������
	        	row.put("rwmc1", ele.getName());
	        }
            if(StringUtils.isNotBlank(ele.getDjrId())){
    			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, ele.getDjrId());
    			row.put("djr", djrObj==null?"":djrObj.getName());//�Ǽ���
    		}else{
    			row.put("djr", "");
    		}
            row.put("scsj", DateUtil.getDate(ele.getCreateTime()));
            
            //operate��ʱ���Լ�д����ͷ�滻��OperateUtil.getOperate(ele.getId())
            StringBuilder operate = new StringBuilder();
            operate.append(" <a onclick='pf(this)' style='color:#0088cc;cursor:pointer;' id='"+ele.getId()+"' >�ɷ�</a>  ");
            operate.append(" <a onclick='info(this)' style='color:#0088cc;cursor:pointer;' id='"+ele.getId()+"' >�鿴</a>  ");
            operate.append(" <a onclick='del(this)' style='color:#0088cc;cursor:pointer;' id='"+ele.getId()+"' >ɾ��</a>  ");
            
            row.put("operate", operate.toString());
            rows.add(row);
        }
        fy.setRows(rows);
        return fy;
	}
	
	@Override
	public int getDbrwCount(TSysUser u) {
		String sql = "select count(id) from VdbWork where userId = ?";
		int count = Integer.parseInt(String.valueOf((Long) this.dao.find(sql, u.getUsername()).get(0)));
		return count;
	}
	
	@Override
	public int getYqrwCount(TSysUser u) {
		Date curDate=new Date();
		String sql = "select count(id) from VdbWork where userId = ? and endTime < TO_DATE(?,'yyyy-MM-dd hh24:mi:ss')";
		int count = Integer.parseInt(String.valueOf((Long) this.dao.find(sql, u.getUsername(), curDate).get(0)));
		return count;
	}
	
	@Override
	public FyWebResult getDbrwList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String lx, String page,
			String pageSize) throws Exception {
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		TSysUser cur = CtxUtil.getCurUser();
        TSysUser u = null;
        //���ɷ�������,˫������������ɺ���Ҫ
        QueryCondition data2 = new QueryCondition();
        data2.setPageSize(pageSize);
        StringBuilder sql2 = new StringBuilder();
        sql2.append("from Work where 1=1");
        if (StringUtils.isNotBlank(rwmc)) {
            sql2.append(" and (name like :rwmc)");
            data2.putLike("rwmc", rwmc);
        }
        if (StringUtils.isNotBlank(rwly)) {
            sql2.append(" and source=:rwly");
            data2.put("rwly", rwly);
        }
        //�ŷõǼǱ����������ѯ
        if("13".equals(tasktype)){
        	sql2.append(" and xfdjbId is not null");
        }else{
        	sql2.append(" and xfdjbId is null");
        }
        sql2.append(" and createUser=:createUser");
        data2.put("createUser", CtxUtil.getCurUser());
        sql2.append(" and startTime is null");//����ʼʱ��Ϊ�գ���Ϊ�����ɵ�����
        //sql.append(" and taskstarted is null");
        sql2.append(" order by createTime DESC ");
        
        FyResult pos2 = dao.find(sql2.toString(), data2, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos2);
        List<Work> vd = pos2.getRe();
        NullControllHashMap row = null;
        for (Work ele : vd) {
        	row = new NullControllHashMap();
    		//������Դ���ƣ���Ϊ�ǲ鿴ҳ�棬ֱ����ԭ�ֶ��ˣ�
    		String str1="";
    		if(ele.getSource()!=null){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
    		}
    		//�����ܼ�����
    		String str2="";
    		if(ele.getSecurity()!=null){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWMJ.getCode(),ele.getSecurity());
    		}
    		//�����̶�����
    		String str3="";
    		if(ele.getEmergency()!=null){
    			str3=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
    		}
    		row.put("rwly", str1);//������Դ
    		row.put("rwmj", str2);//�����ܼ�
    		row.put("jjcd", str3);//�����̶�
            
            row.put("ids", ele.getId());
            if(StringUtils.isNotBlank(ele.getState())){
            	if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 13){
                	row.put("dbworkName", ele.getName().substring(0, 12)+"...");
                	row.put("dbworkName1", "<a id='' title='' class='mTtasks taskState' href='#'></a><span class='ydban'>�����졿</span>"+ele.getName());
                }else{
                	row.put("dbworkName", "<a id='' title='' class='mTtasks taskState' href='#'></a><span class='ydban'>�����졿</span>"+ele.getName());
                	row.put("dbworkName1", "<a id='' title='' class='mTtasks taskState' href='#'></a><span class='ydban'>�����졿</span>"+ele.getName());
                }
            	row.put("stateCode", ele.getState());
                row.put("state", WorkState.getNote(ele.getState()));
            }else{
            	if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 13){
                	row.put("dbworkName", ele.getName().substring(0, 12)+"...");
                	row.put("dbworkName1", "<a id='' title='' class='mTtasks taskState' href='#'></a><span class='ydban'>�����ɷ���</span>"+ele.getName());
                }else{
                	row.put("dbworkName", "<a id='' title='' class='mTtasks taskState' href='#'></a><span class='ydban'>�����ɷ���</span>"+ele.getName());
                	row.put("dbworkName1", "<a id='' title='' class='mTtasks taskState' href='#'></a><span class='ydban'>�����ɷ���</span>"+ele.getName());
                }
            	row.put("stateCode", "");
            	row.put("state", "���ɷ�");
            }
            row.put("workNote1", ele.getWorkNote());
            if (null != ele.getCreateUser()) {
                u = this.userManager.getUser(ele.getCreateUser().getId());
                row.put("createby", u.getName());
            } else {
            	row.put("createby", Constants.DEFAULTCREATER);
            }
            row.put("end", ele.getEndTime());
            row.put("dbcreated", ele.getCreateTime());
            rows.add(row);
        }
        
        //���������ѯ(�ڴ��������У�����״̬����Ϊ��)
  		QueryCondition data1 = new QueryCondition();
          data1.setPageSize(pageSize);
          StringBuilder sql1 = new StringBuilder();
          StringBuilder countSql1 = new StringBuilder();
          //��������
          if (StringUtil.isNotBlank(tasktype)) {
          	sql1.append("select distinct v from VdbWork v, TBizTaskandtasktype tt where v.userId = :userId and v.id = tt.taskid ");
          	countSql1.append("select distinct v.* from v_dbwork v, T_Biz_Taskandtasktype tt where v.userId_ = :userId and v.id_ = tt.taskid_ ");
          } else {
          	sql1.append("select v from VdbWork v, TBizTaskandtasktype tt where v.userId = :userId and v.id = tt.taskid ");
          }
          
          data1.put("userId", cur.getUsername());
          //��������
          if (StringUtil.isNotBlank(rwmc)) {
              sql1.append(" and (v.name like :dbworkName )");
              countSql1.append(" and (v.WORK_NAME_ like :dbworkName )");
              data1.putLike("dbworkName", rwmc);
          }
          //������Դ
          if (StringUtil.isNotBlank(rwly)) {
              sql1.append(" and v.source = :rwly");
              countSql1.append(" and v.SOURCE_ = :rwly");
              data1.put("rwly", rwly);
          }
          //�ɷ���
          if (StringUtil.isNotBlank(pfrId)) {
              sql1.append(" and v.createUser.id = :pfr");
              countSql1.append(" and v.CREATEBY_ = :pfr");
              data1.put("pfr", pfrId);
          }
          //����״̬
          if (StringUtil.isNotBlank(rwzt)) {
              sql1.append(" and v.state = :state");
              countSql1.append(" and v.STATE_ = :state");
              data1.put("state", rwzt);
          }
          
          //ִ����������
          if (StringUtil.isNotBlank(zfdxType)) {
              sql1.append(" and v.zfdxType = :zfdxlx");
              countSql1.append(" and v.ZFDX_TYPE_ = :zfdxlx");
              data1.put("zfdxlx", zfdxType);
          }
          
          //�ɷ�ʱ��
          if(StringUtils.isNotBlank(pfStarttime)){
      		sql1.append(" and v.startTime >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
  			countSql1.append(" and v.START_TIME_ >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
  			data1.put("pfStarttime", pfStarttime+" 00:00:00");
  		}
  		if(StringUtils.isNotBlank(pfEndtime)){
  			sql1.append(" and v.startTime <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
  			countSql1.append(" and v.START_TIME_ <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
  			data1.put("pfEndtime", pfEndtime+" 23:59:59");
  		}
  		
  		//Ҫ�����ʱ��
          if(StringUtils.isNotBlank(gdStarttime)){
  			sql1.append(" and v.endTime >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
  			countSql1.append(" and v.END_TIME_ >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
  			data1.put("gdStarttime", gdStarttime+" 00:00:00");
  		}
  		if(StringUtils.isNotBlank(gdEndtime)){
  			sql1.append(" and v.endTime <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
  			countSql1.append(" and v.END_TIME_ <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
  			data1.put("gdEndtime", gdEndtime+" 23:59:59");
  		}
          if("zfjc".equals(lx)){
          	if(StringUtils.isNotBlank(tasktype)){
      			String[] taskType = new String[tasktype.split(",").length];
                  for (int i = 0; i < tasktype.split(",").length; i++) {
                  	taskType[i] = tasktype.split(",")[i];
          		}
                  sql1.append(" and tt.tasktypeid in (:taskType)");
                  countSql1.append(" and tt.TASKTYPEID_ in (:taskType)");
                  data1.put("taskType", taskType);
      		}else{
                  sql1.append(" and tt.tasktypeid not in ('13', '24')");
                  countSql1.append(" and tt.TASKTYPEID_ not in ('13', '24')");
      		}
          }else{
          	if(StringUtils.isNotBlank(tasktype)){
          		//��������
          		String taskTypeinit = tasktype;
              	String[] taskType = new String[taskTypeinit.split(",").length];
                  for (int i = 0; i < taskTypeinit.split(",").length; i++) {
                  	taskType[i] = taskTypeinit.split(",")[i];
          		}
                  sql1.append(" and tt.tasktypeid in (:taskType) and tt.isActive = 'Y' ");
                  countSql1.append(" and tt.tasktypeid_ in (:taskType) and tt.isActive_ = 'Y' ");
                  data1.put("taskType", taskType);
      		}
          }
          //�ŷõǼǱ����������ѯ
          if(StringUtils.isNotBlank(xfdjId)){
          	sql1.append(" and v.xfdjbId = :xfdjId");
              countSql1.append(" and v.xfdjbId_ = :xfdjId");
              data1.put("xfdjId", xfdjId);
          }
          
          sql1.append(" and v.id not in (select work.id from WorkLog l where 1=1 ");
          countSql1.append(" and v.id_ not in (select WORK_ID_ from T_BIZ_TASKOPERLOG l where 1=1 ");
          //������
          sql1.append(" and l.czrId = :czrId ");
          countSql1.append(" and l.CZR_ID_ = :czrId ");
          data1.put("czrId", cur.getId());
          //��������,Ϊ�ַ�������
          sql1.append(" and l.operateType in (:operateType) ");
          countSql1.append(" and l.OPERATE_TYPE_ in (:operateType) ");
          String[] operateType1 = {WorkLogType.PF.getCode(),WorkLogType.ZF.getCode(),WorkLogType.XP.getCode(),WorkLogType.ZX.getCode(),WorkLogType.SH.getCode(),WorkLogType.TH.getCode()};
          data1.put("operateType", operateType1);
          sql1.append(") ");
          countSql1.append(") ");
          
          sql1.append(" order by v.orderby desc, v.endTime asc, v.updateTime desc");
          countSql1.append(" order by v.orderby_ desc, v.END_TIME_ asc, v.UPDATED_ desc");
          
          FyResult pos1 = dao.find(sql1.toString(), data1, Integer.parseInt(page));
          List<VdbWork> vs1 = pos1.getRe();
          NullControllHashMap db = null;
          //WorkType worktype = null;
          for (VdbWork ele : vs1) {
  			db = new NullControllHashMap();
              //��������
  			List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(ele.getId());
              db.put("ids", ele.getId());
              String rwlxIds = "";
  			for (int i = 0; i < rwlxlistMap.size(); i++) {
  				if (i < rwlxlistMap.size() - 1) {
  					rwlxIds += TaskTypeCode.getNote(rwlxlistMap.get(i).get("id")) + ",";
  				} else {
  					rwlxIds += TaskTypeCode.getNote(rwlxlistMap.get(i).get("id"));
  				}
  			}
  			if(StringUtils.isNotBlank(rwlxIds) && rwlxIds.length() > 10){
  	            db.put("rwlx", rwlxIds.substring(0, 9)+"...");
  	            db.put("rwlx1", rwlxIds);
  	        }else{
  	        	db.put("rwlx", rwlxIds);//��������
  	        	db.put("rwlx1", rwlxIds);
  	        }
              //������Դ����
              String str1="";
      		if(ele.getSource()!=null){
      			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
      		}
              //�����̶�����
              String str2="";
      		if(ele.getEmergency()!=null){
      			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
      		}
      		db.put("rwly", str1);//������Դ
      		//�������ڵ���ʱ��ʾ
              Date curDate=new Date();
              if(-1 == DateUtil.compareDate(curDate, ele.getEndTime())){
              	SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
                  String endTime= s.format(ele.getEndTime().getTime());
              	long to =DateUtil.getEndDatetime(endTime).getTime();
                  long from = curDate.getTime();
                  long lastday = (to - from) / (1000 * 60 * 60 * 24);
                  long syts = lastday+1;
                  if(lastday < 2 || lastday == 2){
                  	db.put("syts", syts);//ʣ������
                  	db.put("jjcd", "<font color='red'>"+str2+"(ʣ��"+syts+"��)</font>");//�����̶�
                  }else{
                  	db.put("syts", syts);//ʣ������
                  	db.put("jjcd", str2+"(ʣ��"+syts+"��)");//�����̶�
                  }
              }else{
              	db.put("syts", 0);//ʣ������
              	db.put("jjcd", str2);//�����̶�
              }
              db.put("yqwcsx", ele.getEndTime());//Ҫ�����ʱ��
              if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 13){
              	db.put("dbworkName", ele.getName().substring(0, 12)+"...");
              	db.put("dbworkName1", "<a id='' title='' class='mTtasks taskState' href='#'></a><span class='ydban'>�����졿</span>"+ele.getName());
              }else{
              	db.put("dbworkName", "<a id='' title='' class='mTtasks taskState' href='#'></a><span class='ydban'>�����졿</span>"+ele.getName());
              	db.put("dbworkName1", "<a id='' title='' class='mTtasks taskState' href='#'></a><span class='ydban'>�����졿</span>"+ele.getName());
              }
              db.put("workNote1", ele.getWorkNote());
              db.put("dbcreated", ele.getCreateTime());
              if (null != ele.getCreateUser()) {
                  u = this.userManager.getUser(ele.getCreateUser().getId());
                  db.put("createby", u.getName());
              } else {
                  db.put("createby", Constants.DEFAULTCREATER);
              }
              db.put("nextOper", ele.getNextOper());
              ///////�ն���Ҫ��һ��������//////
              String lastOper="";
              StringBuffer sql_step = new StringBuffer();
              sql_step.append(" from WorkLog where work.id =? order by czsj asc ");
      		List<WorkLog> list_step = this.dao.find(sql_step.toString(),ele.getId());
      		if(list_step!=null&&list_step.size()>0){
      			lastOper=list_step.get(list_step.size()-1).getCzrName();
      		}
              db.put("lastOper", lastOper);
              db.put("zxrName", ele.getZxrName());
              db.put("taskId", ele.getTaskId());
              if (log.isDebugEnabled()) {
                  log.debug("getProcessId:" + ele.getProcessId());
              }
              db.put("taskType", ele.getCode());
              db.put("zxtime", ele.getZxtime());
              db.put("stateCode", ele.getState());
              if(StringUtils.isNotBlank(ele.getState())){
              	db.put("state", WorkState.getNote(ele.getState()));
              }else{
              	db.put("state", "���ɷ�");
              }
              db.put("start", ele.getStartTime());
              db.put("end", ele.getEndTime());
              //�Ƿ�����
              db.put("isYQ", DateUtil.compareDate(curDate, ele.getEndTime()));
              //�ֻ��˶�ӵ��ֶ�
              db.put("areaid", ele.getAreaid());//��������������
              db.put("shrids", ele.getShrids());//��Ҫ�������id
              db.put("jlrid", ele.getJlr());//��¼�ˣ���������������е���Ҫ���ֶ�
              db.put("isXp", ele.getIsxp());//�Ƿ������ɵ�����
              db.put("zfdxtype", ele.getZfdxType());//�����й�����ִ����������
              db.put("thrids", ele.getThrids());//������˹����п����˻ص���id
              db.put("sjid", ele.getSjid());//������˹����п����˻ص���id
              rows.add(db);
  		}
        
        //�����б��Ĳ�ѯ���Ѱ�����
        QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	sql.append("select distinct w from Work w, TBizTaskandtasktype tt where w.isActive = 'Y' and tt.isActive = 'Y' and w.id = tt.taskid ");
        	countSql.append("select distinct w.* from Work_ w, T_Biz_Taskandtasktype tt where w.isActive_ = 'Y' and tt.isActive_ = 'Y' and w.id_ = tt.taskid_");
        } else {
        	sql.append("select distinct w from Work w, TBizTaskandtasktype tt where w.isActive = 'Y' and tt.isActive = 'Y' and w.id = tt.taskid");
        }
        //���Ȳ�����
        sql.append(" and w.areaid=:areaid ");
        countSql.append(" and w.AREAID_ = :areaid ");
        data.put("areaid", CtxUtil.getAreaId());
        /*//���ߵĹ�ϵ���Ͻ�����
        sql.append(" or w.jsr=:jsrid ");
        countSql.append(" or w.JSR_ = :jsrid ");
        data.put("jsrid", CtxUtil.getCurUser().getId());*/
        
        //��������
        if (StringUtils.isNotBlank(rwmc)) {
            sql.append(" and (w.name like :dbworkName or w.workNote like :dbworkName ) ");
            countSql.append(" and (w.WORK_NAME_ like :dbworkName or w.WORK_NOTE_ like :dbworkName ) ");
            data.putLike("dbworkName", rwmc);
        }
       //������Դ
        if (StringUtil.isNotBlank(rwly)) {
            sql.append(" and w.source = :rwly");
            countSql.append(" and w.SOURCE_ = :rwly");
            data.put("rwly", rwly);
        }
        //����״̬
        if (StringUtils.isNotBlank(rwzt)) {
            sql.append(" and w.state = :state ");
            countSql.append(" and w.state_ = :state ");
            data.put("state", rwzt);
        }
        //ִ����������
        if (StringUtils.isNotBlank(zfdxType)) {
            sql.append(" and w.zfdxType = :zfdxType ");
            countSql.append(" and w.ZFDX_TYPE_ = :zfdxType ");
            data.put("zfdxType", zfdxType);
        }
        //�ɷ���
        if (StringUtil.isNotBlank(pfrId)) {
            sql.append(" and w.createUser.id = :pfr");
            countSql.append(" and w.CREATEBY_ = :pfr");
            data.put("pfr", pfrId);
        }
        //�ɷ�ʱ��
        if(StringUtils.isNotBlank(pfStarttime)){
    		sql.append(" and w.startTime >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and w.START_TIME_ >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("pfStarttime", pfStarttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(pfEndtime)){
    		sql.append(" and w.startTime <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and w.START_TIME_ <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("pfEndtime", pfEndtime+" 23:59:59");
		}
		//�鵵ʱ��
        if(StringUtils.isNotBlank(gdStarttime)){
    		sql.append(" and w.gdsj >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and w.ARCHIVES_TIME_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("starttime", gdStarttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(gdEndtime)){
    		sql.append(" and w.gdsj <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and w.ARCHIVES_TIME_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("endtime", gdEndtime+" 23:59:59");
		}
		//���˵�ִ������е��ŷ�Ͷ�߸��ճ��칫
		if("zfjc".equals(lx)){
        	if(StringUtils.isNotBlank(tasktype)){
    			String[] taskType = new String[tasktype.split(",").length];
                for (int i = 0; i < tasktype.split(",").length; i++) {
                	taskType[i] = tasktype.split(",")[i];
        		}
                sql.append(" and tt.tasktypeid in (:taskType)");
                countSql.append(" and tt.TASKTYPEID_ in (:taskType)");
                data.put("taskType", taskType);
    		}else{
                sql.append(" and tt.tasktypeid not in ('13', '24')");
                countSql.append(" and tt.TASKTYPEID_ not in ('13', '24')");
    		}
        }else{
        	if(StringUtils.isNotBlank(tasktype)){
        		//��������
        		String taskTypeinit = tasktype;
            	String[] taskType = new String[taskTypeinit.split(",").length];
                for (int i = 0; i < taskTypeinit.split(",").length; i++) {
                	taskType[i] = taskTypeinit.split(",")[i];
        		}
                sql.append(" and tt.tasktypeid in (:taskType) and tt.isActive = 'Y' ");
                countSql.append(" and tt.tasktypeid_ in (:taskType) and tt.isActive_ = 'Y' ");
                data.put("taskType", taskType);
    		}
        }
        ///////��������:�ɷ���ת�ɡ����������/////
        sql.append(" and w.id in (select work.id from WorkLog l where 1=1 ");
        countSql.append(" and w.id_ in (select WORK_ID_ from T_BIZ_TASKOPERLOG l where 1=1 ");
        //������
        sql.append(" and l.czrId = :czrId ");
        countSql.append(" and l.CZR_ID_ = :czrId ");
        data.put("czrId", cur.getId());
        //��������,Ϊ�ַ�������
        sql.append(" and l.operateType in (:operateType) ");
        countSql.append(" and l.OPERATE_TYPE_ in (:operateType) ");
        String[] operateType = {WorkLogType.PF.getCode(),WorkLogType.ZF.getCode(),WorkLogType.XP.getCode(),WorkLogType.ZX.getCode(),WorkLogType.SH.getCode(),WorkLogType.TH.getCode(),WorkLogType.GD.getCode()};
        data.put("operateType", operateType);
        sql.append(") ");
        countSql.append(") ");
        
        sql.append(" order by w.updateTime desc, w.startTime desc");
        countSql.append(" order by w.UPDATED_ desc, w.START_TIME_ desc");
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        List<Work> vs = pos.getRe();
        NullControllHashMap d = null;
        for (Work ele : vs) {
        	d = new NullControllHashMap();
        	d.put("ids", ele.getId());
            //������Դ����
            String str1="";
    		if(ele.getSource()!=null){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
    		}
            //�����̶�����
            String str2="";
    		if(ele.getEmergency()!=null){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
    		}
    		d.put("rwly", str1);//������Դ
    		d.put("pfsj", ele.getStartTime());//�ɷ�ʱ��
            d.put("yqwcsx", ele.getEndTime());//Ҫ�����ʱ��
    		if(cur.getId().equals(ele.getJsr())){
    			if("05".equals(ele.getState()) || "07".equals(ele.getState())){
    				if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 15){
                    	d.put("dbworkName", ele.getName().substring(0, 14)+"...");
                    	d.put("dbworkName1", "<a id='' title='' class='mT3tasks taskState' href='#'></a><span class='ydban'>�����졿</span>"+ele.getName());
                    }else{
                    	d.put("dbworkName", "<a id='' title='' class='mT3tasks taskState' href='#'></a><span class='ydban'>�����졿</span>"+ele.getName());
                    	d.put("dbworkName1", "<a id='' title='' class='mT3tasks taskState' href='#'></a><span class='ydban'>�����졿</span>"+ele.getName());
                    }
    				//�������ڵ���ʱ��ʾ
    	            Date curDate=new Date();
    	            if(-1 == DateUtil.compareDate(curDate, ele.getEndTime())){
    	            	SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
    	                String endTime= s.format(ele.getEndTime().getTime());
    	            	long to =DateUtil.getEndDatetime(endTime).getTime();
    	                long from = curDate.getTime();
    	                long lastday = (to - from) / (1000 * 60 * 60 * 24);
    	                long syts = lastday+1;
    	                if(lastday < 2 || lastday == 2){
    	                	d.put("syts", syts);//ʣ������
    	                	d.put("jjcd", "<font color='red'>"+str2+"(ʣ��"+syts+"��)</font>");//�����̶�
    	                }else{
    	                	d.put("syts", syts);//ʣ������
    	                	d.put("jjcd", str2+"(ʣ��"+syts+"��)");//�����̶�
    	                }
    	            }
    			}else{
    				if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 15){
                    	d.put("dbworkName", ele.getName().substring(0, 14)+"...");
                    	d.put("dbworkName1", "<a id='' title='' class='mT3tasks taskState' href='#'></a><span class='ydban blue'>���Ѱ졿</span>"+ele.getName());
                    }else{
                    	d.put("dbworkName", "<a id='' title='' class='mT3tasks taskState' href='#'></a><span class='ydban blue'>���Ѱ졿</span>"+ele.getName());
                    	d.put("dbworkName1", "<a id='' title='' class='mT3tasks taskState' href='#'></a><span class='ydban blue'>���Ѱ졿</span>"+ele.getName());
                    }
    				d.put("jjcd", str2);//�����̶�
    			}
    		}else{
    			if("05".equals(ele.getState()) || "004".equals(ele.getState())){
    				if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 15){
                    	d.put("dbworkName", ele.getName().substring(0, 14)+"...");
                    	d.put("dbworkName1", "<a id='' title='' class='mT3tasks taskState' href='#'></a><span class='ydban'>�����졿</span>"+ele.getName());
                    }else{
                    	d.put("dbworkName", "<a id='' title='' class='mT3tasks taskState' href='#'></a><span class='ydban'>�����졿</span>"+ele.getName());
                    	d.put("dbworkName1", "<a id='' title='' class='mT3tasks taskState' href='#'></a><span class='ydban'>�����졿</span>"+ele.getName());
                    }
    				//�������ڵ���ʱ��ʾ
    	            Date curDate=new Date();
    	            if(-1 == DateUtil.compareDate(curDate, ele.getEndTime())){
    	            	SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
    	                String endTime= s.format(ele.getEndTime().getTime());
    	            	long to =DateUtil.getEndDatetime(endTime).getTime();
    	                long from = curDate.getTime();
    	                long lastday = (to - from) / (1000 * 60 * 60 * 24);
    	                long syts = lastday+1;
    	                if(lastday < 2 || lastday == 2){
    	                	d.put("syts", syts);//ʣ������
    	                	d.put("jjcd", "<font color='red'>"+str2+"(ʣ��"+syts+"��)</font>");//�����̶�
    	                }else{
    	                	d.put("syts", syts);//ʣ������
    	                	d.put("jjcd", str2+"(ʣ��"+syts+"��)");//�����̶�
    	                }
    	            }
    			}else{
    				if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 15){
                    	d.put("dbworkName", ele.getName().substring(0, 14)+"...");
                    	d.put("dbworkName1", "<a id='' title='' class='mT3tasks taskState' href='#'></a><span class='ydban blue'>���Ѱ졿</span>"+ele.getName());
                    }else{
                    	d.put("dbworkName", "<a id='' title='' class='mT3tasks taskState' href='#'></a><span class='ydban blue'>���Ѱ졿</span>"+ele.getName());
                    	d.put("dbworkName1", "<a id='' title='' class='mT3tasks taskState' href='#'></a><span class='ydban blue'>���Ѱ졿</span>"+ele.getName());
                    }
    				d.put("jjcd", str2);//�����̶�
    			}
    		}
        	d.put("stateCode", ele.getState());
            d.put("state", WorkState.getNote(ele.getState()));
            if(StringUtils.isNotBlank(ele.getWorkNote()) && ele.getWorkNote().length() > 13){
            	d.put("workNote", ele.getWorkNote().substring(0, 12)+"...");
            	d.put("workNote1", ele.getWorkNote());
            }else{
            	d.put("workNote", ele.getWorkNote());
            	d.put("workNote1", ele.getWorkNote());
            }
            d.put("dbcreated", ele.getCreateTime());
            if (null != ele.getCreateUser()) {
                u = this.userManager.getUser(ele.getCreateUser().getId());
                d.put("createby", u.getName());
            } else {
                d.put("createby", Constants.DEFAULTCREATER);
            }
            d.put("nextOper", ele.getNextOper());
            d.put("zxrName", ele.getZxrName());
            d.put("taskId", ele.getTaskId());

            if (log.isDebugEnabled()) {
                log.debug("getProcessId:" + ele.getProcessId());
            }
            d.put("taskType", ele.getCode());
            //���������б�
            List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(ele.getId());
            String rwlx="";
            for(int i=0;i<rwlxlistMap.size();i++){
            	if(i<rwlxlistMap.size()-1){
            		rwlx+=rwlxlistMap.get(i).get("name")+",";
            	}else{
            		rwlx+=rwlxlistMap.get(i).get("name");
            	}
            }
            if(StringUtils.isNotBlank(rwlx) && rwlx.length() > 10){
            	d.put("rwlx", rwlx.substring(0, 9)+"...");
            	d.put("rwlx1", rwlx);//��������
            }else{
            	d.put("rwlx", rwlx);//��������
            	d.put("rwlx1", rwlx);//��������
            }
            d.put("zxtime", ele.getZxtime());
            d.put("start", ele.getStartTime());
            d.put("end", ele.getEndTime());
            //���������õ���taskId
            String taskId=getTaskIdByWorkId(ele.getId());
            //��������
            String dczrName=zxNode.getNextOperName(ele.getId(), taskId);
            d.put("dczrName", dczrName);
            rows.add(d);
        }
        
        int a = vd.size();
    	int b = vs.size();
    	int c = vs1.size();
    	fy.setTotal(Long.parseLong(String.valueOf(a+b+c)));
        fy.setRows(rows);
        return fy;
    }
	
	private String getJbpmAction(String workId, String taskId, String processId, String actions) {
        INextActions nextActions = NextActionsFactory.getInstance(ProcessEnum.GENERAL_TASK
            .getProcessKey());
        StringBuilder str = new StringBuilder();
        try {
            List<NextActionBean> acs = nextActions.getActions(actions == null ? "" : actions);
            for (NextActionBean ele : acs) {
            		str.append("<a href=\"#\" class=\"b-link\" onclick=\"actionOper('").append(ele.getCode())
                    .append("','").append(ele.getAction()).append("','")
                    .append(ele.getActionType()).append("','").append(workId).append("','")
                    .append(taskId).append("','").append(ele.getText()).append("')\">")
                    .append(ele.getText()).append("</a>&nbsp;");
            }
            str.append("<a href=\"#\" class=\"b-link\" onclick=\"flowChart('").append(processId).append("')\">")
                .append("����ͼ").append("</a>&nbsp;");
            
            str.append(" <a class=\"b-link\" onclick='info(this)' id='"+workId+"' >�鿴</a>  ");
            
        } catch (Exception e) {
            log.error("", e);
        }
        return str.toString();
    }
	
	private String getNextAction(String workId, String taskId, String processId, String actions) {
        INextActions nextActions = NextActionsFactory.getInstance(ProcessEnum.GENERAL_TASK
            .getProcessKey());
        StringBuilder str = new StringBuilder();
        try {
            List<NextActionBean> acs = nextActions.getActions(actions == null ? "" : actions);
            if (acs!=null && acs.size()>0) {
            		str.append("<a href=\"#\" onclick=\"actionOper('").append(acs.get(0).getCode())
                    .append("','").append(acs.get(0).getAction()).append("','")
                    .append(acs.get(0).getActionType()).append("','").append(workId).append("','")
                    .append(taskId).append("','").append(acs.get(0).getText()).append("')\">")
                    .append(acs.get(0).getText()).append("</a>&nbsp;");
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return str.toString();
    }
	
	/**
     * ��ȡ��������Ĳ���,�ֻ���ʹ��
     * 
     * @param workId ����Id
     * @param taskId 
     * @param processId ����Id
     * @param actions ��ǰ���������еĲ�������,������;{@link WorkProcessEnum}
     * @return
     */
    private String getJbpmActionJson(String workId, String taskId, String processId, String actions) {

        JSONArray re = new JSONArray();
        try {
            INextActions nextActions = NextActionsFactory.getInstance(ProcessEnum.GENERAL_TASK
                .getProcessKey());
            List<NextActionBean> acs = nextActions.getActions(actions == null ? "" : actions);
            JSONObject o = null;
            for (NextActionBean ele : acs) {
                o = new JSONObject();
                o.put("code", ele.getCode());
                o.put("text", ele.getText());
                o.put("action", ele.getAction());
                o.put("actionType", ele.getActionType());
                re.put(o);
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return re.toString();
    }

    @Override
	public void saveDelwork(String id) throws AppException {
		this.dao.remove(Work.class, id);
		
	}
	
	@Override
	public void savework(Work work) throws AppException {
		this.dao.save(work);
		
	}
	
	
	@Override
	public FyWebResult getYbrwList(String zfdxType, String rwmc,String rwly, String rwzt, String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String tasktype, String pfrId, String zbrId, String page,
			String pageSize) throws Exception {
		//���߰��ʶ���ӣ��������߰����ݱ��棬Ȼ����
		String biaoshi = indexManager.sysVer;
		QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	sql.append("select distinct w from Work w, TBizTaskandtasktype tt where w.isActive = 'Y' and tt.isActive = 'Y' and w.id = tt.taskid ");
        	countSql.append("select distinct w.* from Work_ w, T_Biz_Taskandtasktype tt where w.isActive_ = 'Y' and tt.isActive_ = 'Y' and w.id_ = tt.taskid_");
        } else {
        	sql.append("select w from Work w where w.isActive = 'Y' ");
        }
        
        sql.append(" and w.areaid=:areaid ");
        countSql.append(" and w.AREAID_ = :areaid ");
        data.put("areaid", CtxUtil.getAreaId());
        
        //��������
        if (StringUtils.isNotBlank(rwmc)) {
            sql.append(" and (w.name like :dbworkName or w.workNote like :dbworkName ) ");
            countSql.append(" and (w.WORK_NAME_ like :dbworkName or w.WORK_NOTE_ like :dbworkName ) ");
            data.putLike("dbworkName", rwmc);
        }
       //������Դ
        if (StringUtil.isNotBlank(rwly)) {
            sql.append(" and w.source = :rwly");
            countSql.append(" and w.SOURCE_ = :rwly");
            data.put("rwly", rwly);
        }
        //����״̬
        if (StringUtils.isNotBlank(rwzt)) {
            sql.append(" and w.state = :state ");
            countSql.append(" and w.state_ = :state ");
            data.put("state", rwzt);
        }
        //ִ����������
        if (StringUtils.isNotBlank(zfdxType)) {
            sql.append(" and w.zfdxType = :zfdxType ");
            countSql.append(" and w.ZFDX_TYPE_ = :zfdxType ");
            data.put("zfdxType", zfdxType);
        }
        //�ɷ���
        if (StringUtil.isNotBlank(pfrId)) {
            sql.append(" and w.createUser.id = :pfr");
            countSql.append(" and w.CREATEBY_ = :pfr");
            data.put("pfr", pfrId);
        }
        //������
        if (StringUtil.isNotBlank(zbrId)) {
            sql.append(" and w.zxrId = :zbr");
            countSql.append(" and w.ZXR_ID_ = :zbr");
            data.put("zbr", zbrId);
        }
        //�ɷ�ʱ��
        if(StringUtils.isNotBlank(pfStarttime)){
        	if("0".equals(biaoshi)){
        		sql.append(" and w.startTime >= '"+pfStarttime+"'");
    			countSql.append(" and w.START_TIME_ >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
        	}else{
        		sql.append(" and w.startTime >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
    			countSql.append(" and w.START_TIME_ >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
    			data.put("pfStarttime", pfStarttime+" 00:00:00");
        	}
		}
		if(StringUtils.isNotBlank(pfEndtime)){
			if("0".equals(biaoshi)){
				sql.append(" and w.startTime <= '"+pfEndtime+"'");
    			countSql.append(" and w.START_TIME_ <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
        	}else{
        		sql.append(" and w.startTime <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
    			countSql.append(" and w.START_TIME_ <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
    			data.put("pfEndtime", pfEndtime+" 23:59:59");
        	}
		}
		//�鵵ʱ��
        if(StringUtils.isNotBlank(gdStarttime)){
        	if("0".equals(biaoshi)){
        		sql.append(" and w.gdsj >= '"+gdStarttime+"'");
    			countSql.append(" and w.ARCHIVES_TIME_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
        	}else{
        		sql.append(" and w.gdsj >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
    			countSql.append(" and w.ARCHIVES_TIME_ >= TO_DATE(:starttime,'yyyy-MM-dd hh24:mi:ss')");
    			data.put("starttime", gdStarttime+" 00:00:00");
        	}
		}
		if(StringUtils.isNotBlank(gdEndtime)){
			if("0".equals(biaoshi)){
				sql.append(" and w.gdsj <= '"+gdEndtime+"'");
    			countSql.append(" and w.ARCHIVES_TIME_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
        	}else{
        		sql.append(" and w.gdsj <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
    			countSql.append(" and w.ARCHIVES_TIME_ <= TO_DATE(:endtime,'yyyy-MM-dd hh24:mi:ss')");
    			data.put("endtime", gdEndtime+" 23:59:59");
        	}
		}
		if(StringUtils.isNotBlank(tasktype)){
			String[] taskType = new String[tasktype.split(",").length];
            for (int i = 0; i < tasktype.split(",").length; i++) {
            	taskType[i] = tasktype.split(",")[i];
    		}
            sql.append(" and tt.tasktypeid in (:taskType)");
            countSql.append(" and tt.TASKTYPEID_ in (:taskType)");
            data.put("taskType", taskType);
		}
        ///////��������:�ɷ���ת�ɡ����������/////
        sql.append(" and w.id in (select work.id from WorkLog l where 1=1 ");
        countSql.append(" and w.id_ in (select WORK_ID_ from T_BIZ_TASKOPERLOG l where 1=1 ");
        //������
        TSysUser cur = CtxUtil.getCurUser();
        sql.append(" and l.czrId = :czrId ");
        countSql.append(" and l.CZR_ID_ = :czrId ");
        data.put("czrId", cur.getId());
        //��������,Ϊ�ַ�������
        sql.append(" and l.operateType in (:operateType) ");
        countSql.append(" and l.OPERATE_TYPE_ in (:operateType) ");
        String[] operateType = {WorkLogType.PF.getCode(),WorkLogType.ZF.getCode(),WorkLogType.XP.getCode(),WorkLogType.ZX.getCode(),WorkLogType.SH.getCode(),WorkLogType.TH.getCode(),WorkLogType.GD.getCode()};
        data.put("operateType", operateType);
        
        sql.append(") ");
        countSql.append(") ");
        
        sql.append(" order by w.startTime desc");
        countSql.append(" order by w.START_TIME_ desc");
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<Work> vs = pos.getRe();
        NullControllHashMap d = null;
        TSysUser u = null;
        for (Work ele : vs) {
            d = new NullControllHashMap();
            d.put("id", ele.getId());
            //������Դ����
            String str1="";
    		if(ele.getSource()!=null){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
    		}
            //�����̶�����
            String str2="";
    		if(ele.getEmergency()!=null){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
    		}
            d.put("rwly", str1);//������Դ
            d.put("jjcd", str2);//�����̶�
            d.put("pfsj", ele.getStartTime());//�ɷ�ʱ��
            d.put("yqwcsx", ele.getEndTime());//Ҫ�����ʱ��
            if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 15){
            	d.put("dbworkName", ele.getName().substring(0, 14)+"...");
            	d.put("dbworkName1", ele.getName());
            }else{
            	d.put("dbworkName", ele.getName());
            	d.put("dbworkName1", ele.getName());
            }
            if(StringUtils.isNotBlank(ele.getWorkNote()) && ele.getWorkNote().length() > 13){
            	d.put("workNote", ele.getWorkNote().substring(0, 12)+"...");
            	d.put("workNote1", ele.getWorkNote());
            }else{
            	d.put("workNote", ele.getWorkNote());
            	d.put("workNote1", ele.getWorkNote());
            }
            d.put("dbcreated", ele.getCreateTime());
            if (null != ele.getCreateUser()) {
                u = this.userManager.getUser(ele.getCreateUser().getId());
                d.put("createby", u.getName());
            } else {
                d.put("createby", Constants.DEFAULTCREATER);
            }
            d.put("nextOper", ele.getNextOper());
            d.put("zxrName", ele.getZxrName());
            d.put("taskId", ele.getTaskId());

            if (log.isDebugEnabled()) {
                log.debug("getProcessId:" + ele.getProcessId());
            }
            d.put("taskType", ele.getCode());
            //���������б�
            List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(ele.getId());
            String rwlx="";
            for(int i=0;i<rwlxlistMap.size();i++){
            	if(i<rwlxlistMap.size()-1){
            		rwlx+=rwlxlistMap.get(i).get("name")+",";
            	}else{
            		rwlx+=rwlxlistMap.get(i).get("name");
            	}
            }
            if(StringUtils.isNotBlank(rwlx) && rwlx.length() > 10){
            	d.put("rwlx", rwlx.substring(0, 9)+"...");
            	d.put("rwlx1", rwlx);//��������
            }else{
            	d.put("rwlx", rwlx);//��������
            	d.put("rwlx1", rwlx);//��������
            }
            if(StringUtils.isNotBlank(ele.getZfdxType())){
            	d.put("zfdxlx", ZfdxLx.getByCode(ele.getZfdxType()).getText());
            }
            d.put("zxtime", ele.getZxtime());
            d.put("stateCode", ele.getState());
            d.put("state", WorkState.getNote(ele.getState()));
            d.put("start", ele.getStartTime());
            d.put("end", ele.getEndTime());
            
            /**
             * ��ǰ�û��Ѿ��ɷ���δ�����������ڲ�������ʾ�����������ɣ���ǰ�û��Ѿ�ת����δ�����������ڲ�������ʾ���ɣ������񴴽��˲��ܽ��г������ɣ�
             */
            //���������õ���taskId
            String taskId=getTaskIdByWorkId(ele.getId());
            
            //��������
            String dczrName=zxNode.getNextOperName(ele.getId(), taskId);
            d.put("dczrName", dczrName);
            
            StringBuilder operate = new StringBuilder();
            JSONArray operateMobileRe = new JSONArray();//�ն���
            if(ele.getCreateUser().getId().equals(cur.getId()) && (WorkState.YPF.getCode().equals(ele.getState()) || WorkState.YZP.getCode().equals(ele.getState()) ||
            		WorkState.JS.getCode().equals(ele.getState()) || WorkState.BLZ.getCode().equals(ele.getState()) || 
            		WorkState.YBL.getCode().equals(ele.getState()) || WorkState.YSH.getCode().equals(ele.getState()) || WorkState.YTH.getCode().equals(ele.getState()))){
            	//�ϼ����������ܳ���
            	if(StringUtil.isBlank(ele.getSjid())){
            		operate.append(" <a class='b-link' onclick='cx(this)' id='"+ele.getId()+"' >����</a>  ");
            		JSONObject cx=new JSONObject();
                    cx.put("code", "cx");
                    cx.put("text", "����");
                    operateMobileRe.put(cx);
            	}
            	if(WorkState.YPF.getCode().equals(ele.getState()) || WorkState.JS.getCode().equals(ele.getState()) || WorkState.BLZ.getCode().equals(ele.getState()) || WorkState.YZP.getCode().equals(ele.getState())){
            		operate.append(" <a class='b-link' onclick='cp(this,"+taskId+")' id='"+ele.getId()+"' >����</a>  ");
                    JSONObject cp=new JSONObject();
                    cp.put("code", "cp");
                    cp.put("text", "����");
                    operateMobileRe.put(cp);
            	}
            }
            
            //����鵵ǰ�ɷ��˾��ɽ������ڲ���
            if(ele.getCreateUser().getId().equals(cur.getId()) && !WorkState.YGD.getCode().equals(ele.getState()) && StringUtils.isBlank(ele.getSjid())){
                operate.append(" <a class='b-link' onclick='rwyq(this)' id='"+ele.getId()+"' >��������</a>  ");
                JSONObject rwyq=new JSONObject();
                rwyq.put("code", "rwyq");
                rwyq.put("text", "��������");
                operateMobileRe.put(rwyq);
        	}
            
            d.put("nextActions", operateMobileRe.toString());
            
            //���������õ���taskId
            String processId=ele.getProcessId();
            operate.append("<a class='b-link' href=\"#\" onclick=\"flowChart('").append(processId).append("')\">").append("����ͼ").append("</a>&nbsp;");
            
            operate.append(" <a class='b-link' onclick='info(this)' id='"+ele.getId()+"' >�鿴</a>  ");
            d.put("operate", operate.toString());
            
            rows.add(d);
        }
        List<Object> count = null;
        if (StringUtil.isNotBlank(tasktype)) {
        	if("0".equals(biaoshi)){
        		count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ") total", data.getCanshu());
        	}else{
        		count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ")", data.getCanshu());
        	}
        	if (count != null){
        		fy.setTotal(Long.parseLong(count.get(0).toString()));
        	}
        }
        fy.setRows(rows);
        return fy;
    }
	
	@Override
	public String getTaskIdByWorkId(String workId){
		String taskId="";
		StringBuilder sql = new StringBuilder();
		sql.append("select DISTINCT jt.DBID_ AS task_id from work_ it " +
				"left join T_BIZ_WFTODOTASK db on DB.WORK_ID_ = it.id_ " +
				"left join jbpm4_task jt ON db.TASK_ID_ = jt.DBID_ " +
				"LEFT JOIN jbpm4_participation jp ON jt.DBID_=jp.TASK_ " +
				"where it.id_=? " +
				"and jp.TYPE_='candidate' and it.isactive_ = 'Y' AND jt.ASSIGNEE_ IS NULL");
		List<Object> list = dao.findBySql(sql.toString(),workId);
		if(list!=null&&list.size()>0){
			taskId=String.valueOf(list.get(0)==null?"":list.get(0));
		}
		return taskId;
	}

	@Override 
	public Map<String, Object> getShInfo(String applyId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Work work = workManager.get(applyId);
		TSysUser zxr=(TSysUser)this.dao.get(TSysUser.class, work.getZxrId());
		map.put("zxrName", zxr.getName());//������
		map.put("lawnumber", zxr.getLawnumber());//ִ��֤��
		map.put("workmobile", zxr.getWorkmobile());//�绰
		TSysOrg org = userManager.getOrgbyUser(work.getZxrId());
		map.put("org", null != org?org.getName():"");//����
		//ִ������
		StringBuffer sql = new StringBuffer();
		sql.append(" from TBizTaskandlawobj where taskid =? ");
		List<TBizTaskandlawobj> zfdxList = this.dao.find(sql.toString(),applyId);
		map.put("zfdxList", zfdxList);
		map.put("zxStartTime", work.getZxStartTime());//ִ�п�ʼʱ��
		map.put("zxtime", work.getZxtime());//ִ��ʱ��
		
		return map;
	}
	
	
	@Override
	public Map<String, Object> taskDetailJBXX(String applyId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Work work = workManager.get(applyId);
		map.put("id", work.getId());//����id
		//String lawobjtypeName = commonManager.getDicNameByTypeAndCode(DicTypeEnum.LAWTYPE.getCode(), work.getZfdxType());
		List<TDataLawobjtype> listtype= this.dao.find("from TDataLawobjtype where id="+work.getZfdxType()+"");
		if(listtype.size()>0){
			for(TDataLawobjtype type:listtype){
				map.put("lawobjtypeName", type.getName());//ִ����������name
			}
		}
		map.put("lawobjtypeCode", work.getZfdxType());//ִ����������code
		
		map.put("pid", work.getPid());//����pid
		
		map.put("name", work.getName());//��������
		map.put("workNote", work.getWorkNote());//��Ҫ����
		map.put("state", work.getState());//��Ҫ����
		//���������б�
        List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);
        String rwlxNames="";
        String rwlxIds="";
        for(int i=0;i<rwlxlistMap.size();i++){
        	if(i<rwlxlistMap.size()-1){
        		rwlxIds+=rwlxlistMap.get(i).get("id")+",";
        		rwlxNames+=rwlxlistMap.get(i).get("name")+",";
        	}else{
        		rwlxIds+=rwlxlistMap.get(i).get("id");
        		rwlxNames+=rwlxlistMap.get(i).get("name");
        	}
        }
        map.put("rwlxIds", rwlxIds);//��������id
        map.put("rwlxNames", rwlxNames);//��������Name
        //������Դ����
        String str1="";
		if(work.getSource()!=null){
			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),work.getSource());
		}
		map.put("source", str1);
		//�����ܼ�����
		String str2="";
		if(work.getSecurity()!=null){
			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWMJ.getCode(),work.getSecurity());
		}
		map.put("security", str2);
		//�����̶�����
		String str3="";
		if(work.getEmergency()!=null){
			str3=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),work.getEmergency());
		}
		map.put("emergency", str3);
		//ִ������
		StringBuffer sql = new StringBuffer();
		Work w = this.getXpWork(applyId);
		sql.append(" from TBizTaskandlawobj where taskid =? ");
		List<TBizTaskandlawobj> zfdxList = this.dao.find(sql.toString(),applyId);
		if(w!=null && StringUtils.isNotBlank(w.getId())){
			zfdxList = this.dao.find(sql.toString(),w.getId());
		}
		String zfdxNames="";
        for(int i=0;i<zfdxList.size();i++){
        	if(i<zfdxList.size()-1){
        		zfdxNames+=zfdxList.get(i).getLawobjname()+",";
        	}else{
        		zfdxNames+=zfdxList.get(i).getLawobjname();
        	}
        }
        if("11".equals(work.getSource()) && (WorkState.YPF.getCode().equals(work.getState()) || WorkState.YZP.getCode().equals(work.getState()) || WorkState.YXP.getCode().equals(work.getState()))){//������
        	map.put("zfdxNames", "������������ҵ");
        }else{
        	map.put("zfdxNames", zfdxNames);
        }
        
        //����Ǽ���
        String djrName="";
        if(StringUtils.isNotBlank(work.getDjrId())){
			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
			djrName=djrObj.getName();
		}
        map.put("djrName", djrName);
        map.put("pfrName", work.getCreateUser().getName());//�ɷ���
        map.put("endTime", DateUtil.getDate(work.getEndTime()));//Ҫ�����ʱ��
        map.put("createTime", DateUtil.getDate(work.getCreateTime()));//�����ɷ�ʱ��
        
        map.put("startTime", work.getStartTime()!=null?DateUtil.getDate(work.getStartTime()):"");//�ɷ�ʱ��
        
        ////////���������¼����ʷ��ʾ��///////
        List<Vhjgl> lsps = hjglManager.getRwhj_pfzp(applyId);
        map.put("lsps", lsps);
		
		return map;
	}
	
	@Override
	public Map<String, Object> taskDetailRWLZJL(String applyId) {
		Map<String, Object> map = new HashMap<String, Object>();
        ////////������ת��¼///////
		StringBuffer sql = new StringBuffer();
		sql.append(" from WorkLog where work.id =? or (work.sjid = ? and czrId not in (select id from TSysUser where areaId='a0000000000000000000000000000000')) order by czsj asc, operateType ");
		List<WorkLog> list = this.dao.find(sql.toString(),applyId,applyId);
		
		List<Map<String, String>> workLogList = new ArrayList<Map<String,String>>();
		Map<String, String> mapItem = null;
		
		for(int i=0;i<list.size();i++){
			mapItem = new HashMap<String, String>();
			mapItem.put("id", list.get(i).getId());//id
			mapItem.put("operateTypeNote", list.get(i).getOperateTypeNote());//����
			mapItem.put("czrName", list.get(i).getCzrName());//������
			mapItem.put("czsj", DateUtil.getDateTime("yyyy-MM-dd HH:mm", list.get(i).getCzsj()));//����ʱ��
			mapItem.put("note", list.get(i).getNote());//��ע
			workLogList.add(mapItem);
		}
		
        map.put("workLogList", workLogList);
		
		return map;
	}
	
	@Override
	public Map<String, Object> taskDetailRWLZJL_THYJ(String applyId) {
		Map<String, Object> map = new HashMap<String, Object>();
        ////////������ת��¼///////
		StringBuffer sql = new StringBuffer();
		sql.append(" from WorkLog where work.id =? and workSate=? order by czsj asc ");
		List<WorkLog> list = this.dao.find(sql.toString(),applyId,WorkLogType.TH.getCode());
		
		List<Map<String, String>> workLogList = new ArrayList<Map<String,String>>();
		Map<String, String> mapItem = null;
		for(int i=0;i<list.size();i++){
			mapItem = new HashMap<String, String>();
			mapItem.put("operateTypeNote", list.get(i).getOperateTypeNote());//����
			mapItem.put("czrName", list.get(i).getCzrName());//������
			mapItem.put("czsj", DateUtil.getDateTime("yyyy-MM-dd HH:mm", list.get(i).getCzsj()));//����ʱ��
			mapItem.put("note", list.get(i).getNote());//��ע
			workLogList.add(mapItem);
		}
		
        map.put("workLogList", workLogList);
		
		return map;
	}
	
	@Override
	public Map<String, Object> taskDetailBGXQ(String applyId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Work work = workManager.get(applyId);
		if(StringUtils.isNotBlank(work.getZxrId())){
	        TSysUser zxr=(TSysUser)this.dao.get(TSysUser.class, work.getZxrId());
			map.put("zxrName", null!=zxr?zxr.getName():"");//������
			map.put("lawnumber", null!=zxr?zxr.getLawnumber():"");//ִ��֤��
			map.put("workmobile", null!=zxr?zxr.getWorkmobile():"");//�绰
			TSysOrg org = userManager.getOrgbyUser(work.getZxrId());
			map.put("org", null!=org?org.getName():"");//����
		}else{
			map.put("zxrName", "");//������
			map.put("lawnumber", "");//ִ��֤��
			map.put("workmobile", "");//�绰
			map.put("org", "");//����
		}
		//��������
		List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);
		String rwlxIds = "";
		for (int i = 0; i < rwlxlistMap.size(); i++) {
			if (i < rwlxlistMap.size() - 1) {
				rwlxIds += rwlxlistMap.get(i).get("id") + ",";
			} else {
				rwlxIds += rwlxlistMap.get(i).get("id");
			}
		}
		map.put("rwlxIds", rwlxIds);
		List<Map<String, String>> listMap = commWorkManager
				.xbryTableData(applyId);
		if(listMap!=null && listMap.size()>0){
			map.put("xbr", "Y");//Э�����Ƿ����
		}else{
			map.put("xbr", "N");//Э�����Ƿ����
		}
		if("11".equals(work.getSource()) && (WorkState.YPF.getCode().equals(work.getState()) || WorkState.YZP.getCode().equals(work.getState()) || WorkState.YXP.getCode().equals(work.getState()))){//������
			map.put("isshow", "N");
		}else{
			map.put("isshow", "Y");
		}
		map.put("zxStartTime", DateUtil.getDateTime("yyyy-MM-dd HH:mm", work.getZxStartTime()));//����ʱ�俪ʼ
		map.put("zxtime", DateUtil.getDateTime("yyyy-MM-dd HH:mm", work.getZxtime()));//����ʱ�����
		map.put("xpCity", null!=work.getXpCity()?work.getXpCity():"");//����city
		
		return map;
	}

	@Override
	public void saveHdcTask(String applyId) {
		//��ǰ�û���Ϣ
		TSysUser cur = CtxUtil.getCurUser();
		//ִ��������Ϣ
		Map<String, String> zfdxMap = new HashMap<String, String>();
        List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
        if(zfdxlistMap!=null&&zfdxlistMap.size()==1){
        	zfdxMap=zfdxlistMap.get(0);
        }
		//���������б�
        List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);
        for(int i=0;i<rwlxlistMap.size();i++){
        	String rwlx=rwlxlistMap.get(i).get("id");
        	
        	//Υ���������Ͳ����ɶ�������
        	if(TaskTypeCode.WFAJ.getCode().equals(rwlx)){
        		continue;
        	}
        	
        	String fileTypeEnumName="";
        	if(TaskTypeCode.ZXXD.getCode().equals(rwlx)){//ר��������
        		fileTypeEnumName=FileTypeEnums.getEnumByCode(rwlx+"08");
        	}else{
        		fileTypeEnumName=FileTypeEnums.getEnumByCode(rwlx+"05");
        	}
        	
        	//У���Ƿ��д��������
    		FyWebResult re = commonManager.queryFileList(applyId, "N",fileTypeEnumName,"", "1", null);
			List<Map<String, String>> reList=re.getRows();
			if(reList!=null && reList.size()>0){
				//���ɺ󶽲�����
				WorkDto frm=new WorkDto();
				frm.setWorkName(zfdxMap.get("lawobjname")+"�󶽲�");
				frm.setWorkNote(zfdxMap.get("lawobjname")+"�󶽲�");
				frm.setPsyj(zfdxMap.get("lawobjname")+"�󶽲�");
				frm.setDjrId(cur.getId());
				frm.setJsrId(cur.getId());
				frm.setEmergency("1");
				Calendar endC = Calendar.getInstance();
                endC.add(Calendar.DATE, 20);//Ĭ�Ͻ����̶�һ��20��
				frm.setEndTime(DateUtil.getDate(endC.getTime()));
				frm.setSecurity("1");
				frm.setSource("8");
				try {
					Work newWork = workPf.savePf(frm, frm.getJsrId(), CtxUtil.getCurUser(), "");//��Ҫ���ݾ������жϴ��Ǹ���ʾ
					//��work�б���������ġ�ִ���������͡�����ִ�����󡱡����������͡�
					saveDefaultHdc(applyId,newWork.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        }
        
	}
	/**
	 * 
	 * �������ܣ�
	��work�б���������ġ�ִ���������͡�����ִ�����󡱡����������͡�
	 * ���������
	
	 * ����ֵ��
	 */
	private void saveDefaultHdc(String applyId,String newWorkId){
		TSysUser cur = CtxUtil.getCurUser();
		//1��ִ����������
		Work oldWork = workDao.get(applyId);
		Work newWork = workDao.get(newWorkId);
		newWork.setZfdxType(oldWork.getZfdxType());
		this.workDao.save(newWork);
		//2��ִ������
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TBizTaskandlawobj where taskid=:applyId ");
		data.put("applyId", applyId);
		List<TBizTaskandlawobj> list = dao.find(sql.toString(), data);
		for(int i=0;i<list.size();i++){
			TBizTaskandlawobj tBizTaskandlawobj=list.get(i);
			
			TBizTaskandlawobj newTBizTaskandlawobj=new TBizTaskandlawobj();
			newTBizTaskandlawobj.setTaskid(newWorkId);
			
			newTBizTaskandlawobj.setLawobjtype(tBizTaskandlawobj.getLawobjtype());
			newTBizTaskandlawobj.setLawobjid(tBizTaskandlawobj.getLawobjid());
			newTBizTaskandlawobj.setLawobjname(tBizTaskandlawobj.getLawobjname());
			newTBizTaskandlawobj.setRegionid(tBizTaskandlawobj.getRegionid());
			newTBizTaskandlawobj.setAddress(tBizTaskandlawobj.getAddress());
			newTBizTaskandlawobj.setManager(tBizTaskandlawobj.getManager());
			newTBizTaskandlawobj.setManagermobile(tBizTaskandlawobj.getManagermobile());
			newTBizTaskandlawobj.setIsActive(tBizTaskandlawobj.getIsActive());
			newTBizTaskandlawobj.setCreateby(cur);
			newTBizTaskandlawobj.setCreated(new Date());
			newTBizTaskandlawobj.setUpdateby(cur);
			newTBizTaskandlawobj.setUpdated(new Date());
			this.dao.save(newTBizTaskandlawobj);
		}
		//3����������(�󶽲�)
		try {
			commWorkManager.saveTaskTypeMultiple(newWorkId,TaskTypeCode.HDC.getCode(),cur);
		} catch (AppException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Boolean isSB(String applyId) {
		Boolean isSB=false;//�Ƿ��ϱ��ϼ�
		Work work = workManager.get(applyId);
		String userId = CtxUtil.getCurUser().getId();
		UserPosition p = orgManager.getPosition(userId);
        if (StringUtils.isNotBlank(work.getSjid()) && p == UserPosition.ZD) {
        	isSB=true;
        }
		return isSB;
	}
	
	@Override
	public TSysUser getXpr(String applyId) {
		TBizTaskuser xpr = (TBizTaskuser) this.dao.find("from TBizTaskuser where taskid = ? and type = ? ", applyId, TaskUserEnum.XPR.getCode()).get(0);
		TSysUser cur=(TSysUser)this.dao.get(TSysUser.class, xpr.getUserid());//��������������Ϊ���渽����user
		return cur;
	}
	
	@Override
	public Work getXpWork(String applyId) {
		List <Work> workList = this.dao.find("from Work where sjid = ? and isActive = ? ", applyId, "Y");
		if(workList!=null && workList.size()>0){
			return workList.get(0);
		}
		return null;
	}
	
	@Override
	public void saveRwzxStart(String applyId) {
		Work work = workManager.get(applyId);
		if(null == work.getZxStartTime()){
            work.setZxStartTime(new Date());
        }
		//���桰��������ת��¼
        Date date = new Date();
        if(!WorkState.BLZ.getCode().equals(work.getState())){
        	zxWorkManager.saveLog(CtxUtil.getCurUser(), date, WorkLogType.ZX, WorkState.BLZ, work, date);//����	
        }
		work.setState(WorkState.BLZ.getCode());//��ʼ����״̬��Ϊ�������С�
        this.dao.save(work);
       
        
	}
	
	//��׷�ӵ��½����ڹ���ģ��
	@Override
	public FyWebResult getJzList(String rwmc, String pfStarttime, String pfEndtime, String page, String pageSize) throws Exception {
		QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        sql.append("from Work where 1=1");
        if (StringUtils.isNotBlank(rwmc)) {
            sql.append(" and (name like :rwmc)");
            data.putLike("rwmc", rwmc);
        }
        //���ڴ���ʱ��
        if(StringUtils.isNotBlank(pfStarttime)){
			sql.append(" and createTime between STR_TO_DATE(:pfStarttime, '%Y-%m-%d %H:%i:%s')");
			data.put("pfStarttime", pfStarttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(pfEndtime)){
			sql.append(" and STR_TO_DATE(:pfEndtime, '%Y-%m-%d %H:%i:%s')");
			data.put("pfEndtime", pfEndtime+" 23:59:59");
		}
        sql.append(" and createUser=:createUser");
        data.put("createUser", CtxUtil.getCurUser());
        sql.append(" and startTime is null ");//����ʼʱ��Ϊ�գ���Ϊ�����ɵ�����
        sql.append(" order by createTime DESC ");
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<Work> s = pos.getRe();
        Map<String, String> row = null;
        for (Work ele : s) {
            row = new HashMap<String, String>();
            row.put("id", ele.getId());
            row.put("rwmc", ele.getName());
            row.put("scsj", DateUtil.getDate(ele.getEndTime())); 
            
            if(StringUtils.isNotBlank(ele.getDjrId())){
    			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, ele.getDjrId());
    			row.put("djr", djrObj==null?"":djrObj.getName());//�Ǽ���
    		}else{
    			row.put("djr", "");
    		}
                
            //operate��ʱ���Լ�д����ͷ�滻��OperateUtil.getOperate(ele.getId())
            StringBuilder operate = new StringBuilder();
            operate.append(" <a onclick='pf(this)' id='"+ele.getId()+"' >�༭</a>  ");
            operate.append(" <a onclick='info(this)' id='"+ele.getId()+"' >�鿴</a>  ");
            operate.append(" <a onclick='del(this)' id='"+ele.getId()+"' >ɾ��</a>  ");           
            row.put("operate", operate.toString());
            rows.add(row);
        }
        fy.setRows(rows);
        return fy;
	}
	
	@Override
	public void saveXml(String applyId){
		try{
    		Work work = null;
   		 	//1.��һ�� �����ĵ������ø�Ԫ�ؽڵ�ķ�ʽ
   		 	//�����ĵ��ĸ��ڵ�
   		 	//Document document = DocumentHelper.createDocument();
   		 	//�����ĵ��� ��Ԫ�ؽڵ�
   		 	//Element root = DocumentHelper.createElement("Person");
   		 	//document.setRootElement(root);
   		 	//2.�ڶ��� �����ĵ������ø�Ԫ�ؽڵ�ķ�ʽ
   		 	if(StringUtils.isNotBlank(applyId)){
   		 		work = workManager.get(applyId);
   		 	}
   		 	//��ȡ��������
   		 	String rwlxIds = "";
   		 	if(StringUtils.isNotBlank(work.getId())){
   				List<Map<String, String>> rwlxlistMap = commWorkManager
   						.getTaskTypeByTaskId(applyId);
   				for (int i = 0; i < rwlxlistMap.size(); i++) {
   					if (i < rwlxlistMap.size() - 1) {
   						rwlxIds += rwlxlistMap.get(i).get("id") + ",";
   					} else {
   						rwlxIds += rwlxlistMap.get(i).get("id");
   					}
   				}
   		 	}
   		 	
	   		//���ݲ�ͬ���������ͽ��в�ͬ���ݸ�ֵ
			List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);// ���������б�
			for (int i = 0; i < rwlxlistMap.size(); i++) {
				if(rwlxlistMap.get(i).get("id").equals(TaskTypeCode.RCBG.getCode())){
					//��ȡ����ҳ��Ļ�����Ϣ
		   		 	BlMainForm blMainForm = new BlMainForm();
					blMainForm = commWorkManager.getBlMainFormData(applyId);
					//�޸Ķ�Ӧ�����������ĵ�·��
					String path = FileUpDownUtil.path;
					File xmlfile=new File(path + "workInfo.xml");
						if(xmlfile.exists()){
							xmlfile.createNewFile();
						}
					FileOutputStream fos = new FileOutputStream(xmlfile);
					OutputFormat format = OutputFormat.createPrettyPrint();
					format.setEncoding("GB2312");
					XMLWriter writer =  new XMLWriter(fos,format);
					Document doc = DocumentHelper.createDocument();
					Element rootElement = DocumentHelper.createElement("Work");
					//rootElement.addAttribute("version", "2.0");
					doc.setRootElement(rootElement);
					//����xml��ǩ�ڵ�
					Element jzidElement = rootElement.addElement("jzid");
					Element descElement = rootElement.addElement("desc");
					Element rwlxElement = rootElement.addElement("rwlx");
					Element ZbrElement = rootElement.addElement("Zbr");
					//�ڽڵ������ӽڵ�
					Element idElement = ZbrElement.addElement("id");
					Element nameElement = ZbrElement.addElement("name");
					//���ӽڵ�Ľڵ�ֿ�д��Э���ˣ�
					Element XbrElement = rootElement.addElement("Xbr");
					jzidElement.addText(work.getId());//����id
					rwlxElement.addText(rwlxIds);//���ڵ����ͣ��������ͣ�
					if(StringUtils.isNotBlank(blMainForm.getBlRcbgForm().getDesc())){
						descElement.addText(blMainForm.getBlRcbgForm().getDesc());
					}
					//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
					idElement.addText(work.getZxrId());
					nameElement.addText(work.getZxrName());
					String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
					List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
					//Э���˵��ӽڵ�����
					if (taskuserlist2 != null && taskuserlist2.size() > 0) {
						//�ڽڵ������ӽڵ�
						for (int k = 0; k < taskuserlist2.size(); k++) {
							//�ڽڵ������ӽڵ�
							Element XbridElement = XbrElement.addElement("id");
							Element XbrnameElement = XbrElement.addElement("name");
							XbridElement.addText(taskuserlist2.get(k).getUserid());
							XbrnameElement.addText(taskuserlist2.get(k).getUsername());
						}
					}
						
					List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
					List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
					FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.RCBGBLZL.getCode(), "1", null);
					FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.RCBGZBZL.getCode(), "1", null);
					for(int n = 0; n < re1.getRows().size(); n++) {
						jcblsmFileMap.add(re1.getRows().get(n));
					}
					for(int m = 0; m < re2.getRows().size(); m++){
						zbzlFileMap.add(re2.getRows().get(m));
					}
					//���ӽڵ�Ľڵ�ֿ�д��������
					Element qitaElement = rootElement.addElement("qita");
					for(int m = 0; m < jcblsmFileMap.size(); m++){
						//���ӽڵ��������ٽڵ�
						Element fileElement = qitaElement.addElement("file");
						Element fileidElement = fileElement.addElement("fileid");
						Element islastedElement = fileElement.addElement("islasted");
						Element attacodeElement = fileElement.addElement("attacode");
						Element filenameElement = fileElement.addElement("name");
						Element filesizeElement = fileElement.addElement("size");
						Element fileurlElement = fileElement.addElement("url");
						Element filedescElement = fileElement.addElement("desc");
						Element contenttypeElement = fileElement.addElement("contenttype");
						//���и�ֵ
						fileidElement.addText(jcblsmFileMap.get(m).get("id"));
						islastedElement.addText("Y");
						attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
						filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
						filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
						fileurlElement.addText("qita");
						filedescElement.addText("");
						contenttypeElement.addText("");
					}
					//���ӽڵ�Ľڵ�ֿ�д��׼��������
					Element zbzlElement = rootElement.addElement("zbzl");
					for(int m = 0; m < zbzlFileMap.size(); m++){
						//���ӽڵ��������ٽڵ�
						Element fileElement = zbzlElement.addElement("file");
						Element fileidElement = fileElement.addElement("fileid");
						Element islastedElement = fileElement.addElement("islasted");
						Element attacodeElement = fileElement.addElement("attacode");
						Element filenameElement = fileElement.addElement("name");
						Element filesizeElement = fileElement.addElement("size");
						Element fileurlElement = fileElement.addElement("url");
						Element filedescElement = fileElement.addElement("desc");
						Element contenttypeElement = fileElement.addElement("contenttype");
						//���и�ֵ
						fileidElement.addText(zbzlFileMap.get(m).get("id"));
						islastedElement.addText("Y");
						attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
						filenameElement.addText(zbzlFileMap.get(m).get("filename"));
						filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
						fileurlElement.addText("zbzl");
						filedescElement.addText("");
						contenttypeElement.addText("");
					}
					writer.write(doc);
					InputStream is = new FileInputStream(xmlfile);
					//��ɾ���ɵģ��ٱ����µģ�
					commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.RCBGDBFJ.getCode());
					commonManager.saveFile(is, applyId, FileTypeEnums.RCBGDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
				}else{
		   		 	//��ȡ����ҳ��Ļ�����Ϣ
		   		 	BlMainForm blMainForm = new BlMainForm();
		   		 	List<Map<String, String>> rows = commWorkManager.zfdxTableData(applyId);
					if(rows!=null && rows.size()>0){
						blMainForm = commWorkManager.getBlMainFormData(applyId);
					}
	   		 	
					//�޸Ķ�Ӧ�����������ĵ�·��
					String path = FileUpDownUtil.path;
					File xmlfile=new File(path + "workInfo.xml");
						if(xmlfile.exists()){
							xmlfile.createNewFile();
						}
					FileOutputStream fos = new FileOutputStream(xmlfile);
					OutputFormat format = OutputFormat.createPrettyPrint();
					format.setEncoding("GB2312");
					XMLWriter writer =  new XMLWriter(fos,format);
					Document doc = DocumentHelper.createDocument();
					Element rootElement = DocumentHelper.createElement("Work");
					//rootElement.addAttribute("version", "2.0");
					doc.setRootElement(rootElement);
					//����xml��ǩ�ڵ�
					//Element sysidElement = rootElement.addElement("sysid");
					Element jzidElement = rootElement.addElement("jzid");
					Element rwlxElement = rootElement.addElement("rwlx");
					Element managerElement = rootElement.addElement("manager");
					Element fddbrElement = rootElement.addElement("fddbr");
					Element lxdhElement = rootElement.addElement("lxdh");
					Element bjcrElement = rootElement.addElement("bjcr");
					Element bjcrzwElement = rootElement.addElement("bjcrzw");
					Element bjcrlxdhElement = rootElement.addElement("bjcrlxdh");
					Element jcsjElement = rootElement.addElement("jcsj");
					Element jcsj1Element = rootElement.addElement("jcsj1");
					Element jcsj2Element = rootElement.addElement("jcsj2");
					Element zfdxidElement = rootElement.addElement("zfdxid");
					Element zfdxElement = rootElement.addElement("zfdx");
					Element zfdxdzElement = rootElement.addElement("zfdxdz");
					Element wflxElement = rootElement.addElement("wflxbh");
					Element jclxElement = rootElement.addElement("jclx");
					Element jcjlElement = rootElement.addElement("jcjl");
					Element jcmbElement = rootElement.addElement("jcmb");
					Element jzmcElement = rootElement.addElement("jzmc");
					Element sjdwElement = rootElement.addElement("sjdw");
					Element xftslyElement = rootElement.addElement("xftsly");
					Element ZbrElement = rootElement.addElement("Zbr");
					//�ڽڵ������ӽڵ�
					Element idElement = ZbrElement.addElement("id");
					Element nameElement = ZbrElement.addElement("name");
					//���ӽڵ�Ľڵ�ֿ�д��Э���ˣ�
					Element XbrElement = rootElement.addElement("Xbr");
					//�����
					Element JcrElement = rootElement.addElement("Jcr");
					
					//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
					Element JlrElement = rootElement.addElement("Jlr");
					//�ڽڵ������ӽڵ�
					Element JlridElement = JlrElement.addElement("id");
					Element JlrnameElement = JlrElement.addElement("name");
					
					//���ڵ㸳ֵ��Mac������ַ���ڶ�����������޷�ʹ�ã�
					/*NetworkInterface netInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
					byte[] macAddr = netInterface.getHardwareAddress();
					String str = "";
					for (byte b : macAddr) {
						String a = Integer.toHexString((int) (b & 0xff));
						if (a.length() == 1) {
							a = "0" + a;
						}
						str += a+":";
					}
					sysidElement.addText(str.substring(0, str.length()-1));*/
					jzidElement.addText(work.getId());//����id
					rwlxElement.addText(rwlxIds);//���ڵ����ͣ��������ͣ�
					if(null != blMainForm.getBlZfdxForm()){
						if(StringUtils.isNotBlank(blMainForm.getBlZfdxForm().getZwtitle())){
							managerElement.addText(blMainForm.getBlZfdxForm().getZwtitle());//����������
						}
						if(StringUtils.isNotBlank(blMainForm.getBlZfdxForm().getManager())){
							fddbrElement.addText(blMainForm.getBlZfdxForm().getManager());//��������������
						}
						if(StringUtils.isNotBlank(blMainForm.getBlZfdxForm().getManagermobile())){
							lxdhElement.addText(blMainForm.getBlZfdxForm().getManagermobile());//������������ϵ�绰
						}
						if(StringUtils.isNotBlank(blMainForm.getBlZfdxForm().getBjcr())){
							bjcrElement.addText(blMainForm.getBlZfdxForm().getBjcr());//����������
						}
						if(StringUtils.isNotBlank(blMainForm.getBlZfdxForm().getZw())){
							bjcrzwElement.addText(blMainForm.getBlZfdxForm().getZw());//����������ְ��
						}
						if(StringUtils.isNotBlank(blMainForm.getBlZfdxForm().getLxdh())){
							bjcrlxdhElement.addText(blMainForm.getBlZfdxForm().getLxdh());//���������˵绰
						}
						if(StringUtils.isNotBlank(blMainForm.getBlZfdxForm().getLawobjid())){
							zfdxidElement.addText(blMainForm.getBlZfdxForm().getLawobjid());//ִ������id
						}
						if(StringUtils.isNotBlank(blMainForm.getBlZfdxForm().getLawobjname())){
							zfdxElement.addText(blMainForm.getBlZfdxForm().getLawobjname());//ִ����������
						}
						if(StringUtils.isNotBlank(blMainForm.getBlZfdxForm().getAddress())){
							zfdxdzElement.addText(blMainForm.getBlZfdxForm().getAddress());//ִ�������ַ
						}
					}
					if(rwlxlistMap.get(i).get("id").equals(TaskTypeCode.RCJC.getCode())){
						//�����ִ����������Form�ֶ�����
						if(StringUtils.isNotBlank(blMainForm.getBlRcjcForm().getJcsj1())){
							jcsjElement.addText(blMainForm.getBlRcjcForm().getJcsj1());//���ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlRcjcForm().getJcsj1())){
							jcsj1Element.addText(blMainForm.getBlRcjcForm().getJcsj1());//��鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlRcjcForm().getJcsj1())){
							jcsj2Element.addText(blMainForm.getBlRcjcForm().getJcsj2());//������ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						
						Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.RCJC.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
						if (null != jcmbInfo) {
							if("3".equals(jcmbInfo.get("isexecchecklist"))){
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
							}else{
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
								if(StringUtils.isNotBlank(blMainForm.getBlRcjcForm().getJcmbId())){
									jcmbElement.addText(blMainForm.getBlRcjcForm().getJcmbId());//���ģ��
								}
							}
						}
						jzmcElement.addText(work.getName());//��������
						jcjlElement.addText(blMainForm.getBlRcjcForm().getJcjl());//��ȡ������
						sjdwElement.addText(blMainForm.getBlRcjcForm().getJcdd());//���������еļ��ص�
						xftslyElement.addText(work.getSource());//������Դ
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.RCJC_JCR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
						JlridElement.addText(blMainForm.getBlRcjcForm().getJlr());
						JlrnameElement.addText(blMainForm.getBlRcjcForm().getJlrName());
						List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
						FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.RCJCJCJL.getCode(), "1", null);
						List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
						rowsList = re.getRows();
						if (rowsList.size() > 0) {
							rowsList.get(0).put("fileTypeEnumName", "RCJCJCJL");
							rowsList.get(0).put("moreCheckFiletype", "RCJCMOREJCBL");
							rowsList.get(0).put("type","0");
							jcjlFileMap.add(rowsList.get(0));
						}
						re = this.queryJcblFileList(applyId, "N", FileTypeEnums.RCJCMOREJCBL.getCode(), "1", null);
						rowsList = re.getRows();
						if(rowsList!=null && rowsList.size()>0)
						for(int k=0;k<rowsList.size();k++){
							rowsList.get(k).put("fileTypeEnumName", "RCJCJCJL");
							rowsList.get(k).put("moreCheckFiletype", "RCJCMOREJCBL");
							rowsList.get(k).put("type","1");
							jcjlFileMap.add(rowsList.get(k));
						}
						
						List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						//FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.RCJCJCJLSMJ.getCode(), "1", null);
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] { 
								FileTypeEnums.RCJCJCJLSMJ.getCode(),
								FileTypeEnums.RCJCXZWS.getCode(),
								FileTypeEnums.RCJCQTZL.getCode(),
								FileTypeEnums.RCJCCLYJS.getCode(),
								FileTypeEnums.RCJCSPZL.getCode(),
								FileTypeEnums.RCJCLYZL.getCode(),
								FileTypeEnums.RCJCZP.getCode(),
								FileTypeEnums.RCJCHPPFWJ.getCode(),
								FileTypeEnums.RCJCYSPFWJ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re1 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.RCJCZBZL.getCode(), "1", null);
						for(int n = 0; n < re1.getRows().size(); n++) {
							jcblsmFileMap.add(re1.getRows().get(n));
						}
						for(int m = 0; m < re2.getRows().size(); m++){
							zbzlFileMap.add(re2.getRows().get(m));
						}
						//���ӽڵ�Ľڵ�ֿ�д��������
						Element qitaElement = rootElement.addElement("qita");
						for(int m = 0; m < jcblsmFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(jcblsmFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
							filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						for(int m = 0; m < jcjlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlfjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(jcjlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(jcjlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcjlFileMap.get(m).get("filename"));
							filesizeElement.addText(jcjlFileMap.get(m).get("filesize"));
							fileurlElement.addText("jcjlfj");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//�����ϰ�����ģ���������Ϣ
						List<TBizMoreCheck> tbizmc = dao.find("from TBizMoreCheck where taskid_ = ?", applyId);
						Element jccsElement = rootElement.addElement("jccs");
						for(int a = 0; a < tbizmc.size();a++){
							Element jccsjlElement = jccsElement.addElement("jccsjl");
							Element csidElement = jccsjlElement.addElement("csid");
							Element taskidElement = jccsjlElement.addElement("taskid");
							Element tasktypecodeElement = jccsjlElement.addElement("tasktypecode");
							Element timesElement = jccsjlElement.addElement("times");
							Element contentElement = jccsjlElement.addElement("content");
							Element fileidElement = jccsjlElement.addElement("fileid");
							Element isactiveElement = jccsjlElement.addElement("isactive");
							Element versionElement = jccsjlElement.addElement("version");
							Element updatedElement = jccsjlElement.addElement("updated");
							Element updatebyElement = jccsjlElement.addElement("updateby");
							Element createdElement = jccsjlElement.addElement("created");
							Element createbyElement = jccsjlElement.addElement("createby");
							//��ʼ��ֵ
							csidElement.addText(tbizmc.get(a).getId());
							taskidElement.addText(tbizmc.get(a).getWork().getId());
							tasktypecodeElement.addText(tbizmc.get(a).getTasktypecode());
							timesElement.addText(String.valueOf(tbizmc.get(a).getTimes()));
							contentElement.addText(tbizmc.get(a).getContent());
							fileidElement.addText(tbizmc.get(a).getFileid());
							isactiveElement.addText(tbizmc.get(a).getIsActive());
							versionElement.addText(String.valueOf(tbizmc.get(a).getVersion()));
							updatedElement.addText(tbizmc.get(a).getUpdated().toString());
							updatebyElement.addText(tbizmc.get(a).getUpdateby().getId());
							createdElement.addText(tbizmc.get(a).getCreated().toString());
							createbyElement.addText(tbizmc.get(a).getCreateby().getId());
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.RCJCDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.RCJCDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
					else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.NDHC.getCode())) {// 2����Ⱥ˲�
						//�����ִ����������Form�ֶ�����
						if(StringUtils.isNotBlank(blMainForm.getBlNdhcForm().getJcsj1())){
							jcsjElement.addText(blMainForm.getBlNdhcForm().getJcsj1());//���ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlNdhcForm().getJcsj1())){
							jcsj1Element.addText(blMainForm.getBlNdhcForm().getJcsj1());//��鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlNdhcForm().getJcsj1())){
							jcsj2Element.addText(blMainForm.getBlNdhcForm().getJcsj2());//������ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.NDHC.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
						if (null != jcmbInfo) {
							if("3".equals(jcmbInfo.get("isexecchecklist"))){
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
							}else{
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
								jcmbElement.addText(blMainForm.getBlNdhcForm().getJcmbId());//���ģ��
							}
						}
						jzmcElement.addText(work.getName());//��������
						jcjlElement.addText(blMainForm.getBlNdhcForm().getJcjl());//��ȡ������
						sjdwElement.addText(blMainForm.getBlNdhcForm().getJcdd());//���������еļ��ص�
						xftslyElement.addText(work.getSource());//������Դ
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.NDHC_JCR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
						JlridElement.addText(blMainForm.getBlNdhcForm().getJlr());
						JlrnameElement.addText(blMainForm.getBlNdhcForm().getJlrName());
						
						// ����¼
						List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
						FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.NDHCJCJL.getCode(), "1", null);
						List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
						rowsList = re.getRows();
						if (rowsList.size() > 0) {
							rowsList.get(0).put("fileTypeEnumName", "NDHCJCJL");
							rowsList.get(0).put("moreCheckFiletype", "NDHCMOREJCBL");
							rowsList.get(0).put("type","0");
							jcjlFileMap.add(rowsList.get(0));
						}
						re = this.queryJcblFileList(applyId, "N", FileTypeEnums.NDHCMOREJCBL.getCode(), "1", null);
						rowsList = re.getRows();
						if(rowsList!=null && rowsList.size()>0)
						for(int k=0;k<rowsList.size();k++){
							rowsList.get(k).put("fileTypeEnumName", "NDHCJCJL");
							rowsList.get(k).put("moreCheckFiletype", "NDHCMOREJCBL");
							rowsList.get(k).put("type","1");
							jcjlFileMap.add(rowsList.get(k));
						}
						//���ӽڵ�Ľڵ�ֿ�д��������
						List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] { 
								FileTypeEnums.NDHCJCJLSMJ.getCode(),
								FileTypeEnums.NDHCXZWS.getCode(),
								FileTypeEnums.NDHCQTZL.getCode(),
								FileTypeEnums.NDHCCLYJS.getCode(),
								FileTypeEnums.NDHCSPZL.getCode(),
								FileTypeEnums.NDHCLYZL.getCode(),
								FileTypeEnums.NDHCZP.getCode(),
								FileTypeEnums.NDHCHPPFWJ.getCode(),
								FileTypeEnums.NDHCYSPFWJ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re1 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						//FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.NDHCJCJLSMJ.getCode(), "1", null);
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.NDHCZBZL.getCode(), "1", null);
						for(int n = 0; n < re1.getRows().size(); n++) {
							jcblsmFileMap.add(re1.getRows().get(n));
						}
						for(int m = 0; m < re2.getRows().size(); m++){
							zbzlFileMap.add(re2.getRows().get(m));
						}
						Element qitaElement = rootElement.addElement("qita");
						for(int m = 0; m < jcblsmFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcblsmFileMap.get(m).get("id"));
							attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
							filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						for(int m = 0; m < jcjlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlfjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcjlFileMap.get(m).get("id"));
							attacodeElement.addText(jcjlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcjlFileMap.get(m).get("filename"));
							filesizeElement.addText(jcjlFileMap.get(m).get("filesize"));
							fileurlElement.addText("jcjlfj");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//�����ϰ�����ģ���������Ϣ
						List<TBizMoreCheck> tbizmc = dao.find("from TBizMoreCheck where taskid_ = ?", applyId);
						Element jccsElement = rootElement.addElement("jccs");
						for(int a = 0; a < tbizmc.size();a++){
							Element jccsjlElement = jccsElement.addElement("jccsjl");
							Element csidElement = jccsjlElement.addElement("csid");
							Element taskidElement = jccsjlElement.addElement("taskid");
							Element tasktypecodeElement = jccsjlElement.addElement("tasktypecode");
							Element timesElement = jccsjlElement.addElement("times");
							Element contentElement = jccsjlElement.addElement("content");
							Element fileidElement = jccsjlElement.addElement("fileid");
							Element isactiveElement = jccsjlElement.addElement("isactive");
							Element versionElement = jccsjlElement.addElement("version");
							Element updatedElement = jccsjlElement.addElement("updated");
							Element updatebyElement = jccsjlElement.addElement("updateby");
							Element createdElement = jccsjlElement.addElement("created");
							Element createbyElement = jccsjlElement.addElement("createby");
							//��ʼ��ֵ
							csidElement.addText(tbizmc.get(a).getId());
							taskidElement.addText(tbizmc.get(a).getWork().getId());
							tasktypecodeElement.addText(tbizmc.get(a).getTasktypecode());
							timesElement.addText(String.valueOf(tbizmc.get(a).getTimes()));
							contentElement.addText(tbizmc.get(a).getContent());
							fileidElement.addText(tbizmc.get(a).getFileid());
							isactiveElement.addText(tbizmc.get(a).getIsActive());
							versionElement.addText(String.valueOf(tbizmc.get(a).getVersion()));
							updatedElement.addText(tbizmc.get(a).getUpdated().toString());
							updatebyElement.addText(tbizmc.get(a).getUpdateby().getId());
							createdElement.addText(tbizmc.get(a).getCreated().toString());
							createbyElement.addText(tbizmc.get(a).getCreateby().getId());
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.NDHCDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.NDHCDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
					else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.HDC.getCode())) {// 2����Ⱥ˲�
						//�����ִ����������Form�ֶ�����
						if(StringUtils.isNotBlank(blMainForm.getBlHdcForm().getJcsj1())){
							jcsjElement.addText(blMainForm.getBlHdcForm().getJcsj1());//���ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlHdcForm().getJcsj1())){
							jcsj1Element.addText(blMainForm.getBlHdcForm().getJcsj1());//��鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlHdcForm().getJcsj1())){
							jcsj2Element.addText(blMainForm.getBlHdcForm().getJcsj2());//������ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						
						Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.HDC.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
						if (null != jcmbInfo) {
							if("3".equals(jcmbInfo.get("isexecchecklist"))){
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
							}else{
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
								jcmbElement.addText(blMainForm.getBlHdcForm().getJcmbId());//���ģ��
							}
						}
						jzmcElement.addText(work.getName());//��������
						sjdwElement.addText(blMainForm.getBlHdcForm().getJcdd());//���������еļ��ص�
						xftslyElement.addText(work.getSource());//������Դ
						jcjlElement.addText(blMainForm.getBlHdcForm().getJcjl());//��ȡ������
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.HDC_JCR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
						JlridElement.addText(blMainForm.getBlHdcForm().getJlr());
						JlrnameElement.addText(blMainForm.getBlHdcForm().getJlrName());
						// ����¼
						List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
						FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.HDCJCJL.getCode(), "1", null);
						List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
						rowsList = re.getRows();
						if (rowsList.size() > 0) {
							rowsList.get(0).put("fileTypeEnumName", "HDCJCJL");
							rowsList.get(0).put("moreCheckFiletype", "HDCMOREJCBL");
							rowsList.get(0).put("type","0");
							jcjlFileMap.add(rowsList.get(0));
						}
						re = this.queryJcblFileList(applyId, "N", FileTypeEnums.HDCMOREJCBL.getCode(), "1", null);
						rowsList = re.getRows();
						if(rowsList!=null && rowsList.size()>0)
						for(int k=0;k<rowsList.size();k++){
							rowsList.get(k).put("fileTypeEnumName", "HDCJCJL");
							rowsList.get(k).put("moreCheckFiletype", "HDCMOREJCBL");
							rowsList.get(k).put("type","1");
							jcjlFileMap.add(rowsList.get(k));
						}
						//���ӽڵ�Ľڵ�ֿ�д��������
						List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						//FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.HDCJCJLSMJ.getCode(), "1", null);
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] { 
								FileTypeEnums.HDCJCJLSMJ.getCode(),
								FileTypeEnums.HDCXZWS.getCode(),
								FileTypeEnums.HDCQTZL.getCode(),
								FileTypeEnums.HDCCLYJS.getCode(),
								FileTypeEnums.HDCSPZL.getCode(),
								FileTypeEnums.HDCLYZL.getCode(),
								FileTypeEnums.HDCZP.getCode(),
								FileTypeEnums.HDCHPPFWJ.getCode(),
								FileTypeEnums.HDCYSPFWJ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re1 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.HDCZBZL.getCode(), "1", null);
						for(int n = 0; n < re1.getRows().size(); n++) {
							jcblsmFileMap.add(re1.getRows().get(n));
						}
						for(int m = 0; m < re2.getRows().size(); m++){
							zbzlFileMap.add(re2.getRows().get(m));
						}
						Element qitaElement = rootElement.addElement("qita");
						for(int m = 0; m < jcblsmFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcblsmFileMap.get(m).get("id"));
							attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
							filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						for(int m = 0; m < jcjlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlfjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcjlFileMap.get(m).get("id"));
							attacodeElement.addText(jcjlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcjlFileMap.get(m).get("filename"));
							filesizeElement.addText(jcjlFileMap.get(m).get("filesize"));
							fileurlElement.addText("jcjlfj");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//�����ϰ�����ģ���������Ϣ
						List<TBizMoreCheck> tbizmc = dao.find("from TBizMoreCheck where taskid_ = ?", applyId);
						Element jccsElement = rootElement.addElement("jccs");
						for(int a = 0; a < tbizmc.size();a++){
							Element jccsjlElement = jccsElement.addElement("jccsjl");
							Element csidElement = jccsjlElement.addElement("csid");
							Element taskidElement = jccsjlElement.addElement("taskid");
							Element tasktypecodeElement = jccsjlElement.addElement("tasktypecode");
							Element timesElement = jccsjlElement.addElement("times");
							Element contentElement = jccsjlElement.addElement("content");
							Element fileidElement = jccsjlElement.addElement("fileid");
							Element isactiveElement = jccsjlElement.addElement("isactive");
							Element versionElement = jccsjlElement.addElement("version");
							Element updatedElement = jccsjlElement.addElement("updated");
							Element updatebyElement = jccsjlElement.addElement("updateby");
							Element createdElement = jccsjlElement.addElement("created");
							Element createbyElement = jccsjlElement.addElement("createby");
							//��ʼ��ֵ
							csidElement.addText(tbizmc.get(a).getId());
							taskidElement.addText(tbizmc.get(a).getWork().getId());
							tasktypecodeElement.addText(tbizmc.get(a).getTasktypecode());
							timesElement.addText(String.valueOf(tbizmc.get(a).getTimes()));
							contentElement.addText(tbizmc.get(a).getContent());
							fileidElement.addText(tbizmc.get(a).getFileid());
							isactiveElement.addText(tbizmc.get(a).getIsActive());
							versionElement.addText(String.valueOf(tbizmc.get(a).getVersion()));
							updatedElement.addText(tbizmc.get(a).getUpdated().toString());
							updatebyElement.addText(tbizmc.get(a).getUpdateby().getId());
							createdElement.addText(tbizmc.get(a).getCreated().toString());
							createbyElement.addText(tbizmc.get(a).getCreateby().getId());
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.HDCDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.HDCDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
					else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.XFTS.getCode())) {// 2����Ⱥ˲�
						//�����ִ����������Form�ֶ�����
						List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
						if(null != blMainForm.getBlXftsForm()){
							if(StringUtils.isNotBlank(blMainForm.getBlXftsForm().getJcsj1())){
								jcsjElement.addText(blMainForm.getBlXftsForm().getJcsj1());//���ʱ�䣨���ڸ�Ϊʱ����ˣ�
							}
							if(StringUtils.isNotBlank(blMainForm.getBlXftsForm().getJcsj1())){
								jcsj1Element.addText(blMainForm.getBlXftsForm().getJcsj1());//��鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
							}
							if(StringUtils.isNotBlank(blMainForm.getBlXftsForm().getJcsj1())){
								jcsj2Element.addText(blMainForm.getBlXftsForm().getJcsj2());//������ʱ�䣨���ڸ�Ϊʱ����ˣ�
							}
							//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
							JlridElement.addText(blMainForm.getBlXftsForm().getJlr());
							JlrnameElement.addText(blMainForm.getBlXftsForm().getJlrName());
							
							Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.XFTS.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
							if (null != jcmbInfo) {
								if("3".equals(jcmbInfo.get("isexecchecklist"))){
									jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
								}else{
									jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
									jcmbElement.addText(blMainForm.getBlXftsForm().getJcmbId());//���ģ��
								}
							}
							jzmcElement.addText(work.getName());//��������
							jcjlElement.addText(blMainForm.getBlXftsForm().getJcjl());//��ȡ������
							sjdwElement.addText(blMainForm.getBlXftsForm().getJcdd());//���������еļ��ص�
							xftslyElement.addText(blMainForm.getBlXftsForm().getXftsly());//�ŷ�Ͷ����Դ
							// ����¼
							FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.XFTSJCJL.getCode(), "1", null);
							List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
							rowsList = re.getRows();
							if (rowsList.size() > 0) {
								rowsList.get(0).put("fileTypeEnumName", "XFTSJCJL");
								rowsList.get(0).put("moreCheckFiletype", "XFTSMOREJCBL");
								rowsList.get(0).put("type","0");
								jcjlFileMap.add(rowsList.get(0));
							}
							re = this.queryJcblFileList(applyId, "N", FileTypeEnums.XFTSMOREJCBL.getCode(), "1", null);
							rowsList = re.getRows();
							if(rowsList!=null && rowsList.size()>0)
							for(int k=0;k<rowsList.size();k++){
								rowsList.get(k).put("fileTypeEnumName", "XFTSJCJL");
								rowsList.get(k).put("moreCheckFiletype", "XFTSMOREJCBL");
								rowsList.get(k).put("type","1");
								jcjlFileMap.add(rowsList.get(k));
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XFTS_JCR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//��������õ��ŷð�ᵥ
						String sql = " from TBizXfbjd where rwid= ? ";
						List<TBizXfbjd> xfbjd = this.dao.find(sql, applyId);
						Element xfbjdqkElement = rootElement.addElement("xfbjdqk");
						Element hjxfbjqkElement = rootElement.addElement("hjxfbjqk");
						Element hfxsElement = rootElement.addElement("hfxs");//�ظ���ʽ
						Element hfxsxmElement = rootElement.addElement("hfxsxm");//�ظ���ʽ������
						Element hfxsnrElement = rootElement.addElement("hfxsnr");//�ظ�����
						Element hfrElement = rootElement.addElement("hfr");//�ظ���
						Element fhrElement = rootElement.addElement("fhr");//������
						Element fhjsrElement = rootElement.addElement("fhjsr");//�ظ�������
						Element bcqkElement = rootElement.addElement("bcqk");//�ظ��������
						Element hfrqsjElement = rootElement.addElement("hfrqsj");//�ظ�����
						Element bcrElement = rootElement.addElement("bcr");//������
						Element fhrqsjElement = rootElement.addElement("fhrqsj");//��������ʱ��
						Element jssjrqElement = rootElement.addElement("jssjrq");//����ʱ������
						Element bcrqsjElement = rootElement.addElement("bcrqsj");//������
						if(xfbjd!=null && xfbjd.size()>0){
							//��ֵ��ʼ
							if(StringUtils.isNotBlank(xfbjd.get(0).getBjqk())){
								xfbjdqkElement.addText(xfbjd.get(0).getBjqk());
							}
							if(StringUtils.isNotBlank(xfbjd.get(0).getHjxfblqk())){
								hjxfbjqkElement.addText(xfbjd.get(0).getHjxfblqk());						
							}
							if(StringUtils.isNotBlank(xfbjd.get(0).getHfxs())){
								hfxsElement.addText(xfbjd.get(0).getHfxs());
							}
							if(StringUtils.isNotBlank(xfbjd.get(0).getHfxsxm())){
								hfxsxmElement.addText(xfbjd.get(0).getHfxsxm());
							}
							if(StringUtils.isNotBlank(xfbjd.get(0).getHfxsdyrn())){
								hfxsnrElement.addText(xfbjd.get(0).getHfxsdyrn());
							}
							if(StringUtils.isNotBlank(xfbjd.get(0).getHfr())){
								hfrElement.addText(xfbjd.get(0).getHfr());
							}
							if(StringUtils.isNotBlank(xfbjd.get(0).getFhr())){
								fhrElement.addText(xfbjd.get(0).getFhr());
							}
							if(StringUtils.isNotBlank(xfbjd.get(0).getJsr())){
								fhjsrElement.addText(xfbjd.get(0).getJsr());
							}
							if(StringUtils.isNotBlank(xfbjd.get(0).getBcqk())){
								bcqkElement.addText(xfbjd.get(0).getBcqk());
							}
							if(StringUtils.isNotBlank(xfbjd.get(0).getBcr())){
								bcrElement.addText(xfbjd.get(0).getBcr());
							}
							hfrqsjElement.addText(DateUtil.getDateTime(xfbjd.get(0).getHfrq() == null ? new Date() : xfbjd.get(0).getHfrq()));
							fhrqsjElement.addText(DateUtil.getDateTime(xfbjd.get(0).getFhrq() == null ? new Date() : xfbjd.get(0).getFhrq()));
							jssjrqElement.addText(DateUtil.getDateTime(xfbjd.get(0).getJssj() == null ? new Date() : xfbjd.get(0).getJssj()));
							bcrqsjElement.addText(DateUtil.getDateTime(xfbjd.get(0).getBcrq() == null ? new Date() : xfbjd.get(0).getBcrq()));
						}else{
							fhrElement.addText(CtxUtil.getCurUser().getName());//������
							hfrqsjElement.addText(DateUtil.getDateTime(new Date()));
							fhrqsjElement.addText(DateUtil.getDateTime(new Date()));
							jssjrqElement.addText(DateUtil.getDateTime(new Date()));
							bcrqsjElement.addText(DateUtil.getDateTime(new Date()));
						}
						//���ӽڵ�Ľڵ�ֿ�д��������
						List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						//FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.XFTSJCJLSMJ.getCode(), "1", null);
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] { 
								FileTypeEnums.XFTSJCJLSMJ.getCode(),
								FileTypeEnums.XFTSBJDSMJ.getCode(),
								FileTypeEnums.XFTSXZWS.getCode(),
								FileTypeEnums.XFTSQTZL.getCode(),
								FileTypeEnums.XFTSCLYJS.getCode(),
								FileTypeEnums.XFTSSPZL.getCode(),
								FileTypeEnums.XFTSLYZL.getCode(),
								FileTypeEnums.XFTSZP.getCode(),
								FileTypeEnums.XFTSHPPFWJ.getCode(),
								FileTypeEnums.XFTSYSPFWJ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re1 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.XFTSZBZL.getCode(), "1", null);
						for(int n = 0; n < re1.getRows().size(); n++) {
							jcblsmFileMap.add(re1.getRows().get(n));
						}
						for(int m = 0; m < re2.getRows().size(); m++){
							zbzlFileMap.add(re2.getRows().get(m));
						}
						Element qitaElement = rootElement.addElement("qita");
						for(int m = 0; m < jcblsmFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcblsmFileMap.get(m).get("id"));
							attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
							filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						for(int m = 0; m < jcjlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlfjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcjlFileMap.get(m).get("id"));
							attacodeElement.addText(jcjlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcjlFileMap.get(m).get("filename"));
							filesizeElement.addText(jcjlFileMap.get(m).get("filesize"));
							fileurlElement.addText("jcjlfj");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//�����ϰ�����ģ���������Ϣ
						List<TBizMoreCheck> tbizmc = dao.find("from TBizMoreCheck where taskid_ = ?", applyId);
						Element jccsElement = rootElement.addElement("jccs");
						for(int a = 0; a < tbizmc.size();a++){
							Element jccsjlElement = jccsElement.addElement("jccsjl");
							Element csidElement = jccsjlElement.addElement("csid");
							Element taskidElement = jccsjlElement.addElement("taskid");
							Element tasktypecodeElement = jccsjlElement.addElement("tasktypecode");
							Element timesElement = jccsjlElement.addElement("times");
							Element contentElement = jccsjlElement.addElement("content");
							Element fileidElement = jccsjlElement.addElement("fileid");
							Element isactiveElement = jccsjlElement.addElement("isactive");
							Element versionElement = jccsjlElement.addElement("version");
							Element updatedElement = jccsjlElement.addElement("updated");
							Element updatebyElement = jccsjlElement.addElement("updateby");
							Element createdElement = jccsjlElement.addElement("created");
							Element createbyElement = jccsjlElement.addElement("createby");
							//��ʼ��ֵ
							csidElement.addText(tbizmc.get(a).getId());
							taskidElement.addText(tbizmc.get(a).getWork().getId());
							tasktypecodeElement.addText(tbizmc.get(a).getTasktypecode());
							timesElement.addText(String.valueOf(tbizmc.get(a).getTimes()));
							contentElement.addText(tbizmc.get(a).getContent());
							fileidElement.addText(tbizmc.get(a).getFileid());
							isactiveElement.addText(tbizmc.get(a).getIsActive());
							versionElement.addText(String.valueOf(tbizmc.get(a).getVersion()));
							updatedElement.addText(tbizmc.get(a).getUpdated().toString());
							updatebyElement.addText(tbizmc.get(a).getUpdateby().getId());
							createdElement.addText(tbizmc.get(a).getCreated().toString());
							createbyElement.addText(tbizmc.get(a).getCreateby().getId());
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.XFTSDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.XFTSDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
					else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.PWXKZJC.getCode())) {// 2����Ⱥ˲�
						//�����ִ����������Form�ֶ�����
						if(StringUtils.isNotBlank(blMainForm.getBlPwxkzjcForm().getJcsj1())){
							jcsjElement.addText(blMainForm.getBlPwxkzjcForm().getJcsj1());//���ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlPwxkzjcForm().getJcsj1())){
							jcsj1Element.addText(blMainForm.getBlPwxkzjcForm().getJcsj1());//��鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlPwxkzjcForm().getJcsj1())){
							jcsj2Element.addText(blMainForm.getBlPwxkzjcForm().getJcsj2());//������ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						
						Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.PWXKZJC.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
						if (null != jcmbInfo) {
							if("3".equals(jcmbInfo.get("isexecchecklist"))){
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
							}else{
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
								jcmbElement.addText(blMainForm.getBlPwxkzjcForm().getJcmbId());//���ģ��
							}
						}
						jzmcElement.addText(work.getName());//��������
						jcjlElement.addText(blMainForm.getBlPwxkzjcForm().getJcjl());//��ȡ������
						sjdwElement.addText(blMainForm.getBlPwxkzjcForm().getJcdd());//���������еļ��ص�
						xftslyElement.addText(work.getSource());//������Դ
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.PWXKZJC_JCR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
						JlridElement.addText(blMainForm.getBlPwxkzjcForm().getJlr());
						JlrnameElement.addText(blMainForm.getBlPwxkzjcForm().getJlrName());
						// ����¼
						List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
						FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.PWXKZJCJCJL.getCode(), "1", null);
						List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
						rowsList = re.getRows();
						if (rowsList.size() > 0) {
							rowsList.get(0).put("fileTypeEnumName", "PWXKZJCJCJL");
							rowsList.get(0).put("moreCheckFiletype", "PWXKZMOREJCBL");
							rowsList.get(0).put("type","0");
							jcjlFileMap.add(rowsList.get(0));
						}
						re = this.queryJcblFileList(applyId, "N", FileTypeEnums.PWXKZJCMOREJCBL.getCode(), "1", null);
						rowsList = re.getRows();
						if(rowsList!=null && rowsList.size()>0)
						for(int k=0;k<rowsList.size();k++){
							rowsList.get(k).put("fileTypeEnumName", "PWXKZJCJCJL");
							rowsList.get(k).put("moreCheckFiletype", "PWXKZMOREJCBL");
							rowsList.get(k).put("type","1");
							jcjlFileMap.add(rowsList.get(k));
						}
						//���ӽڵ�Ľڵ�ֿ�д��������
						List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						//FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.PWXKZJCJCJLSMJ.getCode(), "1", null);
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] { 
								FileTypeEnums.PWXKZJCJCJLSMJ.getCode(),
								FileTypeEnums.PWXKZJCXZWS.getCode(),
								FileTypeEnums.PWXKZJCQTZL.getCode(),
								FileTypeEnums.PWXKZJCCLYJS.getCode(),
								FileTypeEnums.PWXKZSPZL.getCode(),
								FileTypeEnums.PWXKZLYZL.getCode(),
								FileTypeEnums.PWXKZZP.getCode(),
								FileTypeEnums.PWXKZHPPFWJ.getCode(),
								FileTypeEnums.PWXKZYSPFWJ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re1 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.PWXKZJCZBZL.getCode(), "1", null);
						for(int n = 0; n < re1.getRows().size(); n++) {
							jcblsmFileMap.add(re1.getRows().get(n));
						}
						for(int m = 0; m < re2.getRows().size(); m++){
							zbzlFileMap.add(re2.getRows().get(m));
						}
						Element qitaElement = rootElement.addElement("qita");
						for(int m = 0; m < jcblsmFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcblsmFileMap.get(m).get("id"));
							attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
							filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						for(int m = 0; m < jcjlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlfjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcjlFileMap.get(m).get("id"));
							attacodeElement.addText(jcjlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcjlFileMap.get(m).get("filename"));
							filesizeElement.addText(jcjlFileMap.get(m).get("filesize"));
							fileurlElement.addText("jcjlfj");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//�����ϰ�����ģ���������Ϣ
						List<TBizMoreCheck> tbizmc = dao.find("from TBizMoreCheck where taskid_ = ?", applyId);
						Element jccsElement = rootElement.addElement("jccs");
						for(int a = 0; a < tbizmc.size();a++){
							Element jccsjlElement = jccsElement.addElement("jccsjl");
							Element csidElement = jccsjlElement.addElement("csid");
							Element taskidElement = jccsjlElement.addElement("taskid");
							Element tasktypecodeElement = jccsjlElement.addElement("tasktypecode");
							Element timesElement = jccsjlElement.addElement("times");
							Element contentElement = jccsjlElement.addElement("content");
							Element fileidElement = jccsjlElement.addElement("fileid");
							Element isactiveElement = jccsjlElement.addElement("isactive");
							Element versionElement = jccsjlElement.addElement("version");
							Element updatedElement = jccsjlElement.addElement("updated");
							Element updatebyElement = jccsjlElement.addElement("updateby");
							Element createdElement = jccsjlElement.addElement("created");
							Element createbyElement = jccsjlElement.addElement("createby");
							//��ʼ��ֵ
							csidElement.addText(tbizmc.get(a).getId());
							taskidElement.addText(tbizmc.get(a).getWork().getId());
							tasktypecodeElement.addText(tbizmc.get(a).getTasktypecode());
							timesElement.addText(String.valueOf(tbizmc.get(a).getTimes()));
							contentElement.addText(tbizmc.get(a).getContent());
							fileidElement.addText(tbizmc.get(a).getFileid());
							isactiveElement.addText(tbizmc.get(a).getIsActive());
							versionElement.addText(String.valueOf(tbizmc.get(a).getVersion()));
							updatedElement.addText(tbizmc.get(a).getUpdated().toString());
							updatebyElement.addText(tbizmc.get(a).getUpdateby().getId());
							createdElement.addText(tbizmc.get(a).getCreated().toString());
							createbyElement.addText(tbizmc.get(a).getCreateby().getId());
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.PWXKZDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.PWXKZDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
					else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.WFAJ.getCode())) {// 2����Ⱥ˲�
						Element ladjhElement = rootElement.addElement("ladjh");//�����ǼǺ�
						Element ladjsjElement = rootElement.addElement("ladjsj");//�����Ǽ�ʱ��
						Element dcsj1Element = rootElement.addElement("dcsj1");//��������ʱ��
						Element dcsj2Element = rootElement.addElement("dcsj2");//��������ʱ��
						Element jzzlrElement = rootElement.addElement("jzzlr");//����������
						//�����ִ����������Form�ֶ�����
						if(StringUtils.isNotBlank(blMainForm.getBlWfajdcForm().getLadjh())){
							ladjhElement.addText(blMainForm.getBlWfajdcForm().getLadjh());//�����ǼǺ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlWfajdcForm().getDcsj1())){
							ladjsjElement.addText(blMainForm.getBlWfajdcForm().getLadjsj());//�����Ǽ�ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlWfajdcForm().getDcsj1())){
							dcsj1Element.addText(blMainForm.getBlWfajdcForm().getDcsj1());//���鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlWfajdcForm().getDcsj2())){
							dcsj2Element.addText(blMainForm.getBlWfajdcForm().getDcsj2());//�������ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlWfajdcForm().getJzzlr())){
							jzzlrElement.addText(blMainForm.getBlWfajdcForm().getJzzlr());//����������
						}
						
						Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.WFAJ.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
						if (null != jcmbInfo) {
							if("3".equals(jcmbInfo.get("isexecchecklist"))){
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
							}else{
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
								jcmbElement.addText("jcmb");//���ģ��
							}
						}
						wflxElement.addText(blMainForm.getBlWfajdcForm().getWflxId());//Υ�����͵�Ids
						jzmcElement.addText(work.getName());//��������
						sjdwElement.addText(blMainForm.getBlWfajdcForm().getLadjh());//���������еļ��ص�
						xftslyElement.addText(work.getSource());//������Դ
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.WFAJDC_JLR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
						JlridElement.addText(blMainForm.getBlWfajdcForm().getJlr());
						JlrnameElement.addText(blMainForm.getBlWfajdcForm().getJlrName());
						// ѯ�ʱ�¼
						FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.WFAJDCXWBL.getCode(), "1", null);
						List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
						rowsList = re.getRows();
	
						// �����¼
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WFAJDCKCBL.getCode(), "1", null);
						List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
						rowsList2 = re2.getRows();
						
						FyWebResult re3 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WFAJDCZBZL.getCode(), "1", null);
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						for(int m = 0; m < re3.getRows().size(); m++){
							zbzlFileMap.add(re3.getRows().get(m));
						}
						//���ӽڵ�Ľڵ�ֿ�д��������
						//FyWebResult re4 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WFAJDCKCBLSMJ.getCode(), "1", null);
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] { 
								FileTypeEnums.WFAJDCLADJB.getCode(),
								FileTypeEnums.WFAJDCLADJSMJ.getCode(),
								FileTypeEnums.WFAJDCKCBLSMJ.getCode(),
								FileTypeEnums.WFAJDCSZDZJZL.getCode(),
								FileTypeEnums.WFAJDCSTZLTP.getCode(),
								FileTypeEnums.WFAJDCYPZL.getCode(),
								FileTypeEnums.WFAJDCSTZLLX.getCode(),
								FileTypeEnums.WFAJDCXZWS.getCode(),
								FileTypeEnums.WFAJDCQTZL.getCode(),
								FileTypeEnums.WFAJDCDCBG.getCode(),
								FileTypeEnums.WFAJDCHPPFWJ.getCode(),
								FileTypeEnums.WFAJDCYSPFWJ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re4 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						FyWebResult re5 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WFAJDCXWBLSMJ.getCode(), "1", null);
						Element qitaElement = rootElement.addElement("qita");
						Element jcjlsmjElement = rootElement.addElement("jcjlsmj");
						for(int m = 0; m < re4.getRows().size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(re4.getRows().get(m).get("id"));
							attacodeElement.addText(re4.getRows().get(m).get("filetypecode"));
							filenameElement.addText(re4.getRows().get(m).get("filename"));
							filesizeElement.addText(re4.getRows().get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						for(int m = 0; m < re5.getRows().size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlsmjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(re5.getRows().get(m).get("id"));
							attacodeElement.addText(re5.getRows().get(m).get("filetypecode"));
							filenameElement.addText(re5.getRows().get(m).get("filename"));
							filesizeElement.addText(re5.getRows().get(m).get("filesize"));
							fileurlElement.addText("jcjlsmj");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						//ѯ�ʱ�¼
						Element xwblElement = rootElement.addElement("xwbl");
						for(int m = 0; m < rowsList.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = xwblElement.addElement("file");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							attacodeElement.addText(rowsList.get(m).get("filetypecode"));
							filenameElement.addText(rowsList.get(m).get("filename"));
							filesizeElement.addText(rowsList.get(m).get("filesize"));
							fileurlElement.addText("xwbl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//�����¼
						Element kcblElement = rootElement.addElement("kcbl");
						for(int m = 0; m < rowsList2.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = kcblElement.addElement("file");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							attacodeElement.addText(rowsList2.get(m).get("filetypecode"));
							filenameElement.addText(rowsList2.get(m).get("filename"));
							filesizeElement.addText(rowsList2.get(m).get("filesize"));
							fileurlElement.addText("kcbl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
					else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.XQZL.getCode())) {// 2����Ⱥ˲�
						//�����ִ����������Form�ֶ�����
						if(StringUtils.isNotBlank(blMainForm.getBlXqzlForm().getJcsj1())){
							jcsjElement.addText(blMainForm.getBlXqzlForm().getJcsj1());//���ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlXqzlForm().getJcsj1())){
							jcsj1Element.addText(blMainForm.getBlXqzlForm().getJcsj1());//��鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlXqzlForm().getJcsj1())){
							jcsj2Element.addText(blMainForm.getBlXqzlForm().getJcsj2());//������ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						
						Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.XQZL.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
						if (null != jcmbInfo) {
							if("3".equals(jcmbInfo.get("isexecchecklist"))){
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
							}else{
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
								jcmbElement.addText(blMainForm.getBlXqzlForm().getJcmbId());//���ģ��
							}
						}
						jzmcElement.addText(work.getName());//��������
						jcjlElement.addText(blMainForm.getBlXqzlForm().getJcjl());//��ȡ������
						sjdwElement.addText(blMainForm.getBlXqzlForm().getJcdd());//���������еļ��ص�
						xftslyElement.addText(work.getSource());//������Դ
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XQZL_JCR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
						JlridElement.addText(blMainForm.getBlXqzlForm().getJlr());
						JlrnameElement.addText(blMainForm.getBlXqzlForm().getJlrName());
						// ����¼
						List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
						FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.XQZLJCJL.getCode(), "1", null);
						List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
						rowsList = re.getRows();
						if (rowsList.size() > 0) {
							rowsList.get(0).put("fileTypeEnumName", "XQZLJCJL");
							rowsList.get(0).put("moreCheckFiletype", "XQZLMOREJCBL");
							rowsList.get(0).put("type","0");
							jcjlFileMap.add(rowsList.get(0));
						}
						re = this.queryJcblFileList(applyId, "N", FileTypeEnums.XQZLMOREJCBL.getCode(), "1", null);
						rowsList = re.getRows();
						if(rowsList!=null && rowsList.size()>0)
						for(int k=0;k<rowsList.size();k++){
							rowsList.get(k).put("fileTypeEnumName", "XQZLJCJL");
							rowsList.get(k).put("moreCheckFiletype", "XQZLMOREJCBL");
							rowsList.get(k).put("type","1");
							jcjlFileMap.add(rowsList.get(k));
						}
						//���ӽڵ�Ľڵ�ֿ�д��������
						List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						//FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.XQZLJCJLSMJ.getCode(), "1", null);
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] { 
								FileTypeEnums.XQZLJCJLSMJ.getCode(),
								FileTypeEnums.XQZLXZWS.getCode(),
								FileTypeEnums.XQZLQTZL.getCode(),
								FileTypeEnums.XQZLCLYJS.getCode(),
								FileTypeEnums.XQZLSPZL.getCode(),
								FileTypeEnums.XQZLLYZL.getCode(),
								FileTypeEnums.XQZLZP.getCode(),
								FileTypeEnums.XQZLHPPFWJ.getCode(),
								FileTypeEnums.XQZLYSPFWJ.getCode(),
								FileTypeEnums.XQZLHJWFXWXQGZTZ.getCode(),
								FileTypeEnums.XQZLXZCFJDHSDHZ.getCode(),
								FileTypeEnums.XQZLTZGZSSDHZ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re1 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.XQZLZBZL.getCode(), "1", null);
						for(int n = 0; n < re1.getRows().size(); n++) {
							jcblsmFileMap.add(re1.getRows().get(n));
						}
						for(int m = 0; m < re2.getRows().size(); m++){
							zbzlFileMap.add(re2.getRows().get(m));
						}
						Element qitaElement = rootElement.addElement("qita");
						for(int m = 0; m < jcblsmFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcblsmFileMap.get(m).get("id"));
							attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
							filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						for(int m = 0; m < jcjlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlfjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcjlFileMap.get(m).get("id"));
							attacodeElement.addText(jcjlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcjlFileMap.get(m).get("filename"));
							filesizeElement.addText(jcjlFileMap.get(m).get("filesize"));
							fileurlElement.addText("jcjlfj");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//�����ϰ�����ģ���������Ϣ
						List<TBizMoreCheck> tbizmc = dao.find("from TBizMoreCheck where taskid_ = ?", applyId);
						Element jccsElement = rootElement.addElement("jccs");
						for(int a = 0; a < tbizmc.size();a++){
							Element jccsjlElement = jccsElement.addElement("jccsjl");
							Element csidElement = jccsjlElement.addElement("csid");
							Element taskidElement = jccsjlElement.addElement("taskid");
							Element tasktypecodeElement = jccsjlElement.addElement("tasktypecode");
							Element timesElement = jccsjlElement.addElement("times");
							Element contentElement = jccsjlElement.addElement("content");
							Element fileidElement = jccsjlElement.addElement("fileid");
							Element isactiveElement = jccsjlElement.addElement("isactive");
							Element versionElement = jccsjlElement.addElement("version");
							Element updatedElement = jccsjlElement.addElement("updated");
							Element updatebyElement = jccsjlElement.addElement("updateby");
							Element createdElement = jccsjlElement.addElement("created");
							Element createbyElement = jccsjlElement.addElement("createby");
							//��ʼ��ֵ
							csidElement.addText(tbizmc.get(a).getId());
							taskidElement.addText(tbizmc.get(a).getWork().getId());
							tasktypecodeElement.addText(tbizmc.get(a).getTasktypecode());
							timesElement.addText(String.valueOf(tbizmc.get(a).getTimes()));
							contentElement.addText(tbizmc.get(a).getContent());
							fileidElement.addText(tbizmc.get(a).getFileid());
							isactiveElement.addText(tbizmc.get(a).getIsActive());
							versionElement.addText(String.valueOf(tbizmc.get(a).getVersion()));
							updatedElement.addText(tbizmc.get(a).getUpdated().toString());
							updatebyElement.addText(tbizmc.get(a).getUpdateby().getId());
							createdElement.addText(tbizmc.get(a).getCreated().toString());
							createbyElement.addText(tbizmc.get(a).getCreateby().getId());
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.XQZLDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.XQZLDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
					else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.GZJC.getCode())) {// 2����Ⱥ˲�
						//�����ִ����������Form�ֶ�����
						if(StringUtils.isNotBlank(blMainForm.getBlGzjcForm().getJcsj1())){
							jcsjElement.addText(blMainForm.getBlGzjcForm().getJcsj1());//���ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlGzjcForm().getJcsj1())){
							jcsj1Element.addText(blMainForm.getBlGzjcForm().getJcsj1());//��鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlGzjcForm().getJcsj1())){
							jcsj2Element.addText(blMainForm.getBlGzjcForm().getJcsj2());//������ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						
						Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.GZJC.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
						if (null != jcmbInfo) {
							if("3".equals(jcmbInfo.get("isexecchecklist"))){
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
							}else{
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
								jcmbElement.addText(blMainForm.getBlGzjcForm().getJcmbId());//���ģ��
							}
						}
						jzmcElement.addText(work.getName());//��������
						jcjlElement.addText(blMainForm.getBlGzjcForm().getJcjl());//��ȡ������
						sjdwElement.addText(blMainForm.getBlGzjcForm().getJcdd());//���������еļ��ص�
						xftslyElement.addText(work.getSource());//������Դ
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.GZJC_JCR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
						JlridElement.addText(blMainForm.getBlGzjcForm().getJlr());
						JlrnameElement.addText(blMainForm.getBlGzjcForm().getJlrName());
						// ����¼
						List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
						FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.GZJCJCJL.getCode(), "1", null);
						List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
						rowsList = re.getRows();
						if (rowsList.size() > 0) {
							rowsList.get(0).put("fileTypeEnumName", "GZJCJCJL");
							rowsList.get(0).put("moreCheckFiletype", "GZJCMOREJCBL");
							rowsList.get(0).put("type","0");
							jcjlFileMap.add(rowsList.get(0));
						}
						re = this.queryJcblFileList(applyId, "N", FileTypeEnums.GZJCMOREJCBL.getCode(), "1", null);
						rowsList = re.getRows();
						if(rowsList!=null && rowsList.size()>0)
						for(int k=0;k<rowsList.size();k++){
							rowsList.get(k).put("fileTypeEnumName", "GZJCJCJL");
							rowsList.get(k).put("moreCheckFiletype", "GZJCMOREJCBL");
							rowsList.get(k).put("type","1");
							jcjlFileMap.add(rowsList.get(k));
						}
						//���ӽڵ�Ľڵ�ֿ�д��������
						List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						//FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.GZJCJCJLSMJ.getCode(), "1", null);
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] { 
								FileTypeEnums.GZJCJCJLSMJ.getCode(),
								FileTypeEnums.GZJCXZWS.getCode(),
								FileTypeEnums.GZJCQTZL.getCode(),
								FileTypeEnums.GZJCCLYJS.getCode(),
								FileTypeEnums.GZJCSPZL.getCode(),
								FileTypeEnums.GZJCLYZL.getCode(),
								FileTypeEnums.GZJCZP.getCode(),
								FileTypeEnums.GZJCHPPFWJ.getCode(),
								FileTypeEnums.GZJCYSPFWJ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re1 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.GZJCZBZL.getCode(), "1", null);
						for(int n = 0; n < re1.getRows().size(); n++) {
							jcblsmFileMap.add(re1.getRows().get(n));
						}
						for(int m = 0; m < re2.getRows().size(); m++){
							zbzlFileMap.add(re2.getRows().get(m));
						}
						Element qitaElement = rootElement.addElement("qita");
						for(int m = 0; m < jcblsmFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcblsmFileMap.get(m).get("id"));
							attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
							filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						for(int m = 0; m < jcjlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlfjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcjlFileMap.get(m).get("id"));
							attacodeElement.addText(jcjlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcjlFileMap.get(m).get("filename"));
							filesizeElement.addText(jcjlFileMap.get(m).get("filesize"));
							fileurlElement.addText("jcjlfj");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//�����ϰ�����ģ���������Ϣ
						List<TBizMoreCheck> tbizmc = dao.find("from TBizMoreCheck where taskid_ = ?", applyId);
						Element jccsElement = rootElement.addElement("jccs");
						for(int a = 0; a < tbizmc.size();a++){
							Element jccsjlElement = jccsElement.addElement("jccsjl");
							Element csidElement = jccsjlElement.addElement("csid");
							Element taskidElement = jccsjlElement.addElement("taskid");
							Element tasktypecodeElement = jccsjlElement.addElement("tasktypecode");
							Element timesElement = jccsjlElement.addElement("times");
							Element contentElement = jccsjlElement.addElement("content");
							Element fileidElement = jccsjlElement.addElement("fileid");
							Element isactiveElement = jccsjlElement.addElement("isactive");
							Element versionElement = jccsjlElement.addElement("version");
							Element updatedElement = jccsjlElement.addElement("updated");
							Element updatebyElement = jccsjlElement.addElement("updateby");
							Element createdElement = jccsjlElement.addElement("created");
							Element createbyElement = jccsjlElement.addElement("createby");
							//��ʼ��ֵ
							csidElement.addText(tbizmc.get(a).getId());
							taskidElement.addText(tbizmc.get(a).getWork().getId());
							tasktypecodeElement.addText(tbizmc.get(a).getTasktypecode());
							timesElement.addText(String.valueOf(tbizmc.get(a).getTimes()));
							contentElement.addText(tbizmc.get(a).getContent());
							fileidElement.addText(tbizmc.get(a).getFileid());
							isactiveElement.addText(tbizmc.get(a).getIsActive());
							versionElement.addText(String.valueOf(tbizmc.get(a).getVersion()));
							updatedElement.addText(tbizmc.get(a).getUpdated().toString());
							updatebyElement.addText(tbizmc.get(a).getUpdateby().getId());
							createdElement.addText(tbizmc.get(a).getCreated().toString());
							createbyElement.addText(tbizmc.get(a).getCreateby().getId());
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.GZJCDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.GZJCDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
					else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.ZDJK.getCode())) {// 2����Ⱥ˲�
						Element zxmblxElement = rootElement.addElement("zxmblx");
						//�����ִ����������Form�ֶ�����
						if(StringUtils.isNotBlank(blMainForm.getBlZdjkForm().getJcsj1())){
							jcsjElement.addText(blMainForm.getBlZdjkForm().getJcsj1());//���ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlZdjkForm().getJcsj1())){
							jcsj1Element.addText(blMainForm.getBlZdjkForm().getJcsj1());//��鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlZdjkForm().getJcsj1())){
							jcsj2Element.addText(blMainForm.getBlZdjkForm().getJcsj2());//������ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						
						Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.ZDJK.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
						if (null != jcmbInfo) {
							if("3".equals(jcmbInfo.get("isexecchecklist"))){
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
							}else{
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
								if(StringUtils.isNotBlank(blMainForm.getBlZdjkForm().getJcmbId())){
									jcmbElement.addText(blMainForm.getBlZdjkForm().getJcmbId());//���ģ��
								}
								if(StringUtils.isNotBlank(blMainForm.getBlZdjkForm().getZzmblx())){
									zxmblxElement.addText(blMainForm.getBlZdjkForm().getZzmblx());//���ģ��
								}
							}
						}
						jzmcElement.addText(work.getName());//��������
						jcjlElement.addText(blMainForm.getBlZdjkForm().getJcjl());//��ȡ������
						sjdwElement.addText(blMainForm.getBlZdjkForm().getZzmblx());//�Զ������ѡ����������������
						xftslyElement.addText(work.getSource());//������Դ
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.ZDJK_JCR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
						JlridElement.addText(blMainForm.getBlZdjkForm().getJlr());
						JlrnameElement.addText(blMainForm.getBlZdjkForm().getJlrName());
						
						List<Map<String, String>> zxjcjlFileMap = new ArrayList<Map<String, String>>();
						Map<String, String> jcjlFileMap = null;
						FyWebResult re4 = null;
						if(blMainForm.getBlZdjkForm().getIsexecchecklist().equals("2")){
							re4 = zxzzManager.queryZxzzFileList(applyId, "N", FileTypeEnums.ZDJKJCJL.getCode(), "1", null);
							List<Map<String, String>> rowsList1 = new ArrayList<Map<String, String>>();
							rowsList1 = re4.getRows();
							for(int k = 0; k < rowsList1.size(); k++){
								jcjlFileMap = rowsList1.get(k);
								zxjcjlFileMap.add(jcjlFileMap);
							}
							blMainForm.getBlZdjkForm().setZxjcjlFileMap(zxjcjlFileMap);//��̨������Ҫ�ĸ���list
							if(rowsList1.size() > 0){
								blMainForm.getBlZdjkForm().setJcjlFileMap(rowsList1.get(0));//�ֻ�����Ҫ�ĵ�һ��ģ����Ϣ
							}
						}
						if(blMainForm.getBlZdjkForm().getIsexecchecklist().equals("0")){
							// ����¼
							re4 = commonManager.queryFileList(applyId, "N", FileTypeEnums.ZDJKJCJL.getCode(), "1", null);
							List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
							rowsList = re4.getRows();
							if (rowsList.size() > 0) {
								jcjlFileMap = rowsList.get(0);
							}
						}
						
						//���ӽڵ�Ľڵ�ֿ�д��������
						List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						//FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.ZDJKJCJLSMJ.getCode(), "1", null);
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] { 
								FileTypeEnums.ZDJKJCJLSMJ.getCode(),
								FileTypeEnums.ZDJKXZWS.getCode(),
								FileTypeEnums.ZDJKQTZL.getCode(),
								FileTypeEnums.ZDJKCLYJS.getCode(),
								FileTypeEnums.ZDJKSPZL.getCode(),
								FileTypeEnums.ZDJKLYZL.getCode(),
								FileTypeEnums.ZDJKZP.getCode(),
								FileTypeEnums.ZDJKHPPFWJ.getCode(),
								FileTypeEnums.ZDJKYSPFWJ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re1 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.ZDJKZBZL.getCode(), "1", null);
						for(int n = 0; n < re1.getRows().size(); n++) {
							jcblsmFileMap.add(re1.getRows().get(n));
						}
						for(int m = 0; m < re2.getRows().size(); m++){
							zbzlFileMap.add(re2.getRows().get(m));
						}
						Element qitaElement = rootElement.addElement("qita");
						for(int m = 0; m < jcblsmFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcblsmFileMap.get(m).get("id"));
							attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
							filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						for(int m = 0; m < re4.getRows().size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlfjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							if("2".equals(jcmbInfo.get("isexecchecklist"))){
								//���и�ֵ
								islastedElement.addText("Y");
								fileidElement.addText(re4.getRows().get(m).get("id"));
								attacodeElement.addText(re4.getRows().get(m).get("filetypecode"));
								filenameElement.addText(re4.getRows().get(m).get("filename"));
								filesizeElement.addText(re4.getRows().get(m).get("filesize"));
								fileurlElement.addText("jcjlfj");
								filedescElement.addText("");
								contenttypeElement.addText("");
							}else{
								//���и�ֵ
								islastedElement.addText("Y");
								fileidElement.addText(re4.getRows().get(m).get("id"));
								attacodeElement.addText(re4.getRows().get(m).get("filetypecode"));
								filenameElement.addText(re4.getRows().get(m).get("filename"));
								filesizeElement.addText(re4.getRows().get(m).get("filesize"));
								fileurlElement.addText("jcjlfj");
								filedescElement.addText("");
								contenttypeElement.addText("");
							}
						}
						//�����ϰ�����ģ���������Ϣ
						List<TBizMoreCheck> tbizmc = dao.find("from TBizMoreCheck where taskid_ = ?", applyId);
						Element jccsElement = rootElement.addElement("jccs");
						for(int a = 0; a < tbizmc.size();a++){
							Element jccsjlElement = jccsElement.addElement("jccsjl");
							Element csidElement = jccsjlElement.addElement("csid");
							Element taskidElement = jccsjlElement.addElement("taskid");
							Element tasktypecodeElement = jccsjlElement.addElement("tasktypecode");
							Element timesElement = jccsjlElement.addElement("times");
							Element contentElement = jccsjlElement.addElement("content");
							Element fileidElement = jccsjlElement.addElement("fileid");
							Element isactiveElement = jccsjlElement.addElement("isactive");
							Element versionElement = jccsjlElement.addElement("version");
							Element updatedElement = jccsjlElement.addElement("updated");
							Element updatebyElement = jccsjlElement.addElement("updateby");
							Element createdElement = jccsjlElement.addElement("created");
							Element createbyElement = jccsjlElement.addElement("createby");
							//��ʼ��ֵ
							csidElement.addText(tbizmc.get(a).getId());
							taskidElement.addText(tbizmc.get(a).getWork().getId());
							tasktypecodeElement.addText(tbizmc.get(a).getTasktypecode());
							timesElement.addText(String.valueOf(tbizmc.get(a).getTimes()));
							contentElement.addText(tbizmc.get(a).getContent());
							fileidElement.addText(tbizmc.get(a).getFileid());
							isactiveElement.addText(tbizmc.get(a).getIsActive());
							versionElement.addText(String.valueOf(tbizmc.get(a).getVersion()));
							updatedElement.addText(tbizmc.get(a).getUpdated().toString());
							updatebyElement.addText(tbizmc.get(a).getUpdateby().getId());
							createdElement.addText(tbizmc.get(a).getCreated().toString());
							createbyElement.addText(tbizmc.get(a).getCreateby().getId());
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.ZDJKDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.ZDJKDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
					else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.WXFW.getCode())) {// 2����Ⱥ˲�
						//�����ִ����������Form�ֶ�����
						if(StringUtils.isNotBlank(blMainForm.getBlWxfwForm().getJcsj1())){
							jcsjElement.addText(blMainForm.getBlWxfwForm().getJcsj1());//���ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlWxfwForm().getJcsj1())){
							jcsj1Element.addText(blMainForm.getBlWxfwForm().getJcsj1());//��鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlWxfwForm().getJcsj1())){
							jcsj2Element.addText(blMainForm.getBlWxfwForm().getJcsj2());//������ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						
						Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.WXFW.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
						if (null != jcmbInfo) {
							if("3".equals(jcmbInfo.get("isexecchecklist"))){
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
							}else{
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
								jcmbElement.addText(blMainForm.getBlWxfwForm().getJcmbId());//���ģ��
							}
						}
						jzmcElement.addText(work.getName());//��������
						jcjlElement.addText(blMainForm.getBlWxfwForm().getJcjl());//��ȡ������
						sjdwElement.addText(blMainForm.getBlWxfwForm().getJcdd());//���������еļ��ص�
						xftslyElement.addText(work.getSource());//������Դ
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.WXFW_JCR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
						JlridElement.addText(blMainForm.getBlWxfwForm().getJlr());
						JlrnameElement.addText(blMainForm.getBlWxfwForm().getJlrName());
						// ����¼
						List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
						FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.WXFWJCJL.getCode(), "1", null);
						List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
						rowsList = re.getRows();
						if (rowsList.size() > 0) {
							rowsList.get(0).put("fileTypeEnumName", "WXFWJCJL");
							rowsList.get(0).put("moreCheckFiletype", "WXFWMOREJCBL");
							rowsList.get(0).put("type","0");
							jcjlFileMap.add(rowsList.get(0));
						}
						re = this.queryJcblFileList(applyId, "N", FileTypeEnums.WXFWMOREJCBL.getCode(), "1", null);
						rowsList = re.getRows();
						if(rowsList!=null && rowsList.size()>0)
						for(int k=0;k<rowsList.size();k++){
							rowsList.get(k).put("fileTypeEnumName", "WXFWJCJL");
							rowsList.get(k).put("moreCheckFiletype", "WXFWMOREJCBL");
							rowsList.get(k).put("type","1");
							jcjlFileMap.add(rowsList.get(k));
						}
						//���ӽڵ�Ľڵ�ֿ�д��������
						List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						//FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WXFWJCJLSMJ.getCode(), "1", null);
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] { 
								FileTypeEnums.WXFWJCJLSMJ.getCode(),
								FileTypeEnums.WXFWXZWS.getCode(),
								FileTypeEnums.WXFWQTZL.getCode(),
								FileTypeEnums.WXFWCLYJS.getCode(),
								FileTypeEnums.WXFWSPZL.getCode(),
								FileTypeEnums.WXFWLYZL.getCode(),
								FileTypeEnums.WXFWZP.getCode(),
								FileTypeEnums.WXFWHPPFWJ.getCode(),
								FileTypeEnums.WXFWYSPFWJ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re1 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WXFWZBZL.getCode(), "1", null);
						for(int n = 0; n < re1.getRows().size(); n++) {
							jcblsmFileMap.add(re1.getRows().get(n));
						}
						for(int m = 0; m < re2.getRows().size(); m++){
							zbzlFileMap.add(re2.getRows().get(m));
						}
						Element qitaElement = rootElement.addElement("qita");
						for(int m = 0; m < jcblsmFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcblsmFileMap.get(m).get("id"));
							attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
							filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						for(int m = 0; m < jcjlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlfjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcjlFileMap.get(m).get("id"));
							attacodeElement.addText(jcjlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcjlFileMap.get(m).get("filename"));
							filesizeElement.addText(jcjlFileMap.get(m).get("filesize"));
							fileurlElement.addText("jcjlfj");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//�����ϰ�����ģ���������Ϣ
						List<TBizMoreCheck> tbizmc = dao.find("from TBizMoreCheck where taskid_ = ?", applyId);
						Element jccsElement = rootElement.addElement("jccs");
						for(int a = 0; a < tbizmc.size();a++){
							Element jccsjlElement = jccsElement.addElement("jccsjl");
							Element csidElement = jccsjlElement.addElement("csid");
							Element taskidElement = jccsjlElement.addElement("taskid");
							Element tasktypecodeElement = jccsjlElement.addElement("tasktypecode");
							Element timesElement = jccsjlElement.addElement("times");
							Element contentElement = jccsjlElement.addElement("content");
							Element fileidElement = jccsjlElement.addElement("fileid");
							Element isactiveElement = jccsjlElement.addElement("isactive");
							Element versionElement = jccsjlElement.addElement("version");
							Element updatedElement = jccsjlElement.addElement("updated");
							Element updatebyElement = jccsjlElement.addElement("updateby");
							Element createdElement = jccsjlElement.addElement("created");
							Element createbyElement = jccsjlElement.addElement("createby");
							//��ʼ��ֵ
							csidElement.addText(tbizmc.get(a).getId());
							taskidElement.addText(tbizmc.get(a).getWork().getId());
							tasktypecodeElement.addText(tbizmc.get(a).getTasktypecode());
							timesElement.addText(String.valueOf(tbizmc.get(a).getTimes()));
							contentElement.addText(tbizmc.get(a).getContent());
							fileidElement.addText(tbizmc.get(a).getFileid());
							isactiveElement.addText(tbizmc.get(a).getIsActive());
							versionElement.addText(String.valueOf(tbizmc.get(a).getVersion()));
							updatedElement.addText(tbizmc.get(a).getUpdated().toString());
							updatebyElement.addText(tbizmc.get(a).getUpdateby().getId());
							createdElement.addText(tbizmc.get(a).getCreated().toString());
							createbyElement.addText(tbizmc.get(a).getCreateby().getId());
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WXFWDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.WXFWDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
					else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.WXHXP.getCode())) {// 2����Ⱥ˲�
						//�����ִ����������Form�ֶ�����
						if(StringUtils.isNotBlank(blMainForm.getBlWxhxpForm().getJcsj1())){
							jcsjElement.addText(blMainForm.getBlWxhxpForm().getJcsj1());//���ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlWxhxpForm().getJcsj1())){
							jcsj1Element.addText(blMainForm.getBlWxhxpForm().getJcsj1());//��鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlWxhxpForm().getJcsj1())){
							jcsj2Element.addText(blMainForm.getBlWxhxpForm().getJcsj2());//������ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						
						Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.WXHXP.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
						if (null != jcmbInfo) {
							jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
							if("1".equals(jcmbInfo.get("isexecchecklist"))){
								jcmbElement.addText("");
							}else{
								jcmbElement.addText(blMainForm.getBlWxhxpForm().getJcmbId());//���ģ��
							}
						}
						jzmcElement.addText(work.getName());//��������
						//sjdwElement.addText(blMainForm.getBlWxhxpForm().getJcmbFileId());//���������еļ��ص�
						jcjlElement.addText(blMainForm.getBlWxhxpForm().getJcjl());//��ȡ������
						xftslyElement.addText(work.getSource());//������Դ
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.WXHXP_JCR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
						JlridElement.addText(blMainForm.getBlWxhxpForm().getJlr());
						JlrnameElement.addText(blMainForm.getBlWxhxpForm().getJlrName());
						// ����¼
						List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
						FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.WXHXPJCJL.getCode(), "1", null);
						List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
						rowsList = re.getRows();
						if (rowsList.size() > 0) {
							rowsList.get(0).put("fileTypeEnumName", "WXHXPJCJL");
							rowsList.get(0).put("type","0");
							jcjlFileMap.add(rowsList.get(0));
						}
						
						//���ӽڵ�Ľڵ�ֿ�д��������
						List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						//FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WXHXPJCJLSMJ.getCode(), "1", null);
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] { 
								FileTypeEnums.WXHXPJCJLSMJ.getCode(),
								FileTypeEnums.WXHXPXZWS.getCode(),
								FileTypeEnums.WXHXPQTZL.getCode(),
								FileTypeEnums.WXHXPCLYJS.getCode(),
								FileTypeEnums.WXHXPSPZL.getCode(),
								FileTypeEnums.WXHXPLYZL.getCode(),
								FileTypeEnums.WXHXPZP.getCode(),
								FileTypeEnums.WXHXPHPPFWJ.getCode(),
								FileTypeEnums.WXHXPYSPFWJ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re1 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WXHXPZBZL.getCode(), "1", null);
						for(int n = 0; n < re1.getRows().size(); n++) {
							jcblsmFileMap.add(re1.getRows().get(n));
						}
						for(int m = 0; m < re2.getRows().size(); m++){
							zbzlFileMap.add(re2.getRows().get(m));
						}
						Element qitaElement = rootElement.addElement("qita");
						for(int m = 0; m < jcblsmFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcblsmFileMap.get(m).get("id"));
							attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
							filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						for(int m = 0; m < jcjlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlfjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcjlFileMap.get(m).get("id"));
							attacodeElement.addText(jcjlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcjlFileMap.get(m).get("filename"));
							filesizeElement.addText(jcjlFileMap.get(m).get("filesize"));
							fileurlElement.addText("jcjlfj");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WXHXPDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.WXHXPDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
					else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.FSAQ.getCode())) {// 2�����䰲ȫ
						//�����ִ����������Form�ֶ�����
						if(StringUtils.isNotBlank(blMainForm.getBlFsaqForm().getJcsj1())){
							jcsjElement.addText(blMainForm.getBlFsaqForm().getJcsj1());//���ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlFsaqForm().getJcsj1())){
							jcsj1Element.addText(blMainForm.getBlFsaqForm().getJcsj1());//��鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlFsaqForm().getJcsj1())){
							jcsj2Element.addText(blMainForm.getBlFsaqForm().getJcsj2());//������ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						
						Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.FSAQ.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
						if (null != jcmbInfo) {
							if("3".equals(jcmbInfo.get("isexecchecklist"))){
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
							}else{
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
								jcmbElement.addText(blMainForm.getBlFsaqForm().getJcmbId());//���ģ��
							}
						}
						jzmcElement.addText(work.getName());//��������
						jcjlElement.addText(blMainForm.getBlFsaqForm().getJcjl());//��ȡ������
						sjdwElement.addText(blMainForm.getBlFsaqForm().getJcdd());//���������еļ��ص�
						xftslyElement.addText(work.getSource());//������Դ
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.FSAQ_JCR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
						JlridElement.addText(blMainForm.getBlFsaqForm().getJlr());
						JlrnameElement.addText(blMainForm.getBlFsaqForm().getJlrName());
						// ����¼
						List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
						FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.FSAQJCJL.getCode(), "1", null);
						List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
						rowsList = re.getRows();
						if (rowsList.size() > 0) {
							rowsList.get(0).put("fileTypeEnumName", "FSAQJCJL");
							rowsList.get(0).put("moreCheckFiletype", "FSAQMOREJCBL");
							rowsList.get(0).put("type","0");
							jcjlFileMap.add(rowsList.get(0));
						}
						re = this.queryJcblFileList(applyId, "N", FileTypeEnums.FSAQMOREJCBL.getCode(), "1", null);
						rowsList = re.getRows();
						if(rowsList!=null && rowsList.size()>0)
						for(int k=0;k<rowsList.size();k++){
							rowsList.get(k).put("fileTypeEnumName", "FSAQJCJL");
							rowsList.get(k).put("moreCheckFiletype", "FSAQMOREJCBL");
							rowsList.get(k).put("type","1");
							jcjlFileMap.add(rowsList.get(k));
						}
						//���ӽڵ�Ľڵ�ֿ�д��������
						List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						//FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.FSAQJCJLSMJ.getCode(), "1", null);
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] { 
								FileTypeEnums.FSAQJCJLSMJ.getCode(),
								FileTypeEnums.FSAQXZWS.getCode(),
								FileTypeEnums.FSAQQTZL.getCode(),
								FileTypeEnums.FSAQCLYJS.getCode(),
								FileTypeEnums.FSAQSPZL.getCode(),
								FileTypeEnums.FSAQLYZL.getCode(),
								FileTypeEnums.FSAQZP.getCode(),
								FileTypeEnums.FSAQHPPFWJ.getCode(),
								FileTypeEnums.FSAQYSPFWJ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re1 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.FSAQZBZL.getCode(), "1", null);
						for(int n = 0; n < re1.getRows().size(); n++) {
							jcblsmFileMap.add(re1.getRows().get(n));
						}
						for(int m = 0; m < re2.getRows().size(); m++){
							zbzlFileMap.add(re2.getRows().get(m));
						}
						Element qitaElement = rootElement.addElement("qita");
						for(int m = 0; m < jcblsmFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcblsmFileMap.get(m).get("id"));
							attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
							filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						for(int m = 0; m < jcjlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlfjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcjlFileMap.get(m).get("id"));
							attacodeElement.addText(jcjlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcjlFileMap.get(m).get("filename"));
							filesizeElement.addText(jcjlFileMap.get(m).get("filesize"));
							fileurlElement.addText("jcjlfj");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//�����ϰ�����ģ���������Ϣ
						List<TBizMoreCheck> tbizmc = dao.find("from TBizMoreCheck where taskid_ = ?", applyId);
						Element jccsElement = rootElement.addElement("jccs");
						for(int a = 0; a < tbizmc.size();a++){
							Element jccsjlElement = jccsElement.addElement("jccsjl");
							Element csidElement = jccsjlElement.addElement("csid");
							Element taskidElement = jccsjlElement.addElement("taskid");
							Element tasktypecodeElement = jccsjlElement.addElement("tasktypecode");
							Element timesElement = jccsjlElement.addElement("times");
							Element contentElement = jccsjlElement.addElement("content");
							Element fileidElement = jccsjlElement.addElement("fileid");
							Element isactiveElement = jccsjlElement.addElement("isactive");
							Element versionElement = jccsjlElement.addElement("version");
							Element updatedElement = jccsjlElement.addElement("updated");
							Element updatebyElement = jccsjlElement.addElement("updateby");
							Element createdElement = jccsjlElement.addElement("created");
							Element createbyElement = jccsjlElement.addElement("createby");
							//��ʼ��ֵ
							csidElement.addText(tbizmc.get(a).getId());
							taskidElement.addText(tbizmc.get(a).getWork().getId());
							tasktypecodeElement.addText(tbizmc.get(a).getTasktypecode());
							timesElement.addText(String.valueOf(tbizmc.get(a).getTimes()));
							contentElement.addText(tbizmc.get(a).getContent());
							fileidElement.addText(tbizmc.get(a).getFileid());
							isactiveElement.addText(tbizmc.get(a).getIsActive());
							versionElement.addText(String.valueOf(tbizmc.get(a).getVersion()));
							updatedElement.addText(tbizmc.get(a).getUpdated().toString());
							updatebyElement.addText(tbizmc.get(a).getUpdateby().getId());
							createdElement.addText(tbizmc.get(a).getCreated().toString());
							createbyElement.addText(tbizmc.get(a).getCreateby().getId());
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.FSAQDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.FSAQDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
					else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.WRSGXCDC.getCode())) {// 2����ȾԴ�¹ʵ���
						//�����ִ����������Form�ֶ�����
						if(StringUtils.isNotBlank(blMainForm.getBlWrsgxcdcForm().getJcsj1())){
							jcsjElement.addText(blMainForm.getBlWrsgxcdcForm().getJcsj1());//���ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlWrsgxcdcForm().getJcsj1())){
							jcsj1Element.addText(blMainForm.getBlWrsgxcdcForm().getJcsj1());//��鿪ʼʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						if(StringUtils.isNotBlank(blMainForm.getBlWrsgxcdcForm().getJcsj1())){
							jcsj2Element.addText(blMainForm.getBlWrsgxcdcForm().getJcsj2());//������ʱ�䣨���ڸ�Ϊʱ����ˣ�
						}
						
						Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.WRSGXCDC.getCode(), blMainForm.getBlZfdxForm().getLawobjtype());
						if (null != jcmbInfo) {
							if("3".equals(jcmbInfo.get("isexecchecklist"))){
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
							}else{
								jclxElement.addText(jcmbInfo.get("isexecchecklist"));//�������
								jcmbElement.addText(blMainForm.getBlWrsgxcdcForm().getJcmbId());//���ģ��
							}
						}
						jzmcElement.addText(work.getName());//��������
						jcjlElement.addText(blMainForm.getBlWrsgxcdcForm().getJcjl());//��ȡ������
						sjdwElement.addText(blMainForm.getBlWrsgxcdcForm().getJcdd());//���������еļ��ص�
						xftslyElement.addText(work.getSource());//������Դ
						//���ӽڵ�Ľڵ�ֿ�д�������ˣ�
						idElement.addText(work.getZxrId());
						nameElement.addText(work.getZxrName());
						
						//��ȡ����˵ķ���
						String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.WRSGXCDC_JCR.getCode() + "'";
						List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
						String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XBR.getCode() + "'";
						List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
						//Э���˵��ӽڵ�����
						if (taskuserlist2 != null && taskuserlist2.size() > 0) {
							//�ڽڵ������ӽڵ�
							for (int k = 0; k < taskuserlist2.size(); k++) {
								//�ڽڵ������ӽڵ�
								Element XbridElement = XbrElement.addElement("id");
								Element XbrnameElement = XbrElement.addElement("name");
								XbridElement.addText(taskuserlist2.get(k).getUserid());
								XbrnameElement.addText(taskuserlist2.get(k).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д������ˣ�
						if (taskuserlist1 != null && taskuserlist1.size() > 0) {
							for(int j = 0; j < taskuserlist1.size(); j++){
								Element JcridElement = JcrElement.addElement("id");
								Element JcrnameElement = JcrElement.addElement("name");
								JcridElement.addText(taskuserlist1.get(j).getUserid());
								JcrnameElement.addText(taskuserlist1.get(j).getUsername());
							}
						}
						//���ӽڵ�Ľڵ�ֿ�д����¼�ˣ�
						JlridElement.addText(blMainForm.getBlWrsgxcdcForm().getJlr());
						JlrnameElement.addText(blMainForm.getBlWrsgxcdcForm().getJlrName());
						// ����¼
						List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
						FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.WRSGXCDCJCJL.getCode(), "1", null);
						List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
						rowsList = re.getRows();
						if (rowsList.size() > 0) {
							rowsList.get(0).put("fileTypeEnumName", "WRSGXCDCJCJL");
							rowsList.get(0).put("moreCheckFiletype", "WRSGXCDCMOREJCBL");
							rowsList.get(0).put("type","0");
							jcjlFileMap.add(rowsList.get(0));
						}
						re = this.queryJcblFileList(applyId, "N", FileTypeEnums.WRSGXCDCMOREJCBL.getCode(), "1", null);
						rowsList = re.getRows();
						if(rowsList!=null && rowsList.size()>0)
						for(int k=0;k<rowsList.size();k++){
							rowsList.get(k).put("fileTypeEnumName", "WRSGXCDCJCJL");
							rowsList.get(k).put("moreCheckFiletype", "WRSGXCDCJCJL");
							rowsList.get(k).put("type","1");
							jcjlFileMap.add(rowsList.get(k));
						}
						//���ӽڵ�Ľڵ�ֿ�д��������
						List<Map<String, String>> jcblsmFileMap = new ArrayList<Map<String, String>> ();
						List<Map<String, String>> zbzlFileMap = new ArrayList<Map<String, String>> ();
						//FyWebResult re1 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WRSGXCDCJCJLSMJ.getCode(), "1", null);
						//��ȡ�ճ����ĸ�������
						String[] code = new String[] {
								FileTypeEnums.WRSGXCDCJCJLSMJ.getCode(),
								FileTypeEnums.WRSGXCDCXZWS.getCode(),
								FileTypeEnums.WRSGXCDCQTZL.getCode(),
								FileTypeEnums.WRSGXCDCCLYJS.getCode(),
								FileTypeEnums.WRSGXCDCSPZL.getCode(),
								FileTypeEnums.WRSGXCDCLYZL.getCode(),
								FileTypeEnums.WRSGXCDCZP.getCode(),
								FileTypeEnums.WRSGXCDCHPPFWJ.getCode(),
								FileTypeEnums.WRSGXCDCYSPFWJ.getCode()};
						String fileType = "";
						for (int b = 0; b < code.length; b++) {
							if (b > 0) {
								fileType += ",";
							}
							fileType += code[b];
						}
						String canDel = "N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N"+","+"N";
						FyWebResult re1 = commonManager.queryFileListMulfileType(applyId, canDel, fileType, "1", null);
						FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WRSGXCDCZBZL.getCode(), "1", null);
						for(int n = 0; n < re1.getRows().size(); n++) {
							jcblsmFileMap.add(re1.getRows().get(n));
						}
						for(int m = 0; m < re2.getRows().size(); m++){
							zbzlFileMap.add(re2.getRows().get(m));
						}
						Element qitaElement = rootElement.addElement("qita");
						for(int m = 0; m < jcblsmFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = qitaElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcblsmFileMap.get(m).get("id"));
							attacodeElement.addText(jcblsmFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcblsmFileMap.get(m).get("filename"));
							filesizeElement.addText(jcblsmFileMap.get(m).get("filesize"));
							fileurlElement.addText("qita");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//���ӽڵ�Ľڵ�ֿ�д��׼��������
						Element zbzlElement = rootElement.addElement("zbzl");
						for(int m = 0; m < zbzlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = zbzlElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							fileidElement.addText(zbzlFileMap.get(m).get("id"));
							islastedElement.addText("Y");
							attacodeElement.addText(zbzlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(zbzlFileMap.get(m).get("filename"));
							filesizeElement.addText(zbzlFileMap.get(m).get("filesize"));
							fileurlElement.addText("zbzl");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						Element jcjlfjElement = rootElement.addElement("jcjlfj");
						for(int m = 0; m < jcjlFileMap.size(); m++){
							//���ӽڵ��������ٽڵ�
							Element fileElement = jcjlfjElement.addElement("file");
							Element fileidElement = fileElement.addElement("fileid");
							Element islastedElement = fileElement.addElement("islasted");
							Element attacodeElement = fileElement.addElement("attacode");
							Element filenameElement = fileElement.addElement("name");
							Element filesizeElement = fileElement.addElement("size");
							Element fileurlElement = fileElement.addElement("url");
							Element filedescElement = fileElement.addElement("desc");
							Element contenttypeElement = fileElement.addElement("contenttype");
							//���и�ֵ
							islastedElement.addText("Y");
							fileidElement.addText(jcjlFileMap.get(m).get("id"));
							attacodeElement.addText(jcjlFileMap.get(m).get("filetypecode"));
							filenameElement.addText(jcjlFileMap.get(m).get("filename"));
							filesizeElement.addText(jcjlFileMap.get(m).get("filesize"));
							fileurlElement.addText("jcjlfj");
							filedescElement.addText("");
							contenttypeElement.addText("");
						}
						//�����ϰ�����ģ���������Ϣ
						List<TBizMoreCheck> tbizmc = dao.find("from TBizMoreCheck where taskid_ = ?", applyId);
						Element jccsElement = rootElement.addElement("jccs");
						for(int a = 0; a < tbizmc.size();a++){
							Element jccsjlElement = jccsElement.addElement("jccsjl");
							Element csidElement = jccsjlElement.addElement("csid");
							Element taskidElement = jccsjlElement.addElement("taskid");
							Element tasktypecodeElement = jccsjlElement.addElement("tasktypecode");
							Element timesElement = jccsjlElement.addElement("times");
							Element contentElement = jccsjlElement.addElement("content");
							Element fileidElement = jccsjlElement.addElement("fileid");
							Element isactiveElement = jccsjlElement.addElement("isactive");
							Element versionElement = jccsjlElement.addElement("version");
							Element updatedElement = jccsjlElement.addElement("updated");
							Element updatebyElement = jccsjlElement.addElement("updateby");
							Element createdElement = jccsjlElement.addElement("created");
							Element createbyElement = jccsjlElement.addElement("createby");
							//��ʼ��ֵ
							csidElement.addText(tbizmc.get(a).getId());
							taskidElement.addText(tbizmc.get(a).getWork().getId());
							tasktypecodeElement.addText(tbizmc.get(a).getTasktypecode());
							timesElement.addText(String.valueOf(tbizmc.get(a).getTimes()));
							contentElement.addText(tbizmc.get(a).getContent());
							fileidElement.addText(tbizmc.get(a).getFileid());
							isactiveElement.addText(tbizmc.get(a).getIsActive());
							versionElement.addText(String.valueOf(tbizmc.get(a).getVersion()));
							updatedElement.addText(tbizmc.get(a).getUpdated().toString());
							updatebyElement.addText(tbizmc.get(a).getUpdateby().getId());
							createdElement.addText(tbizmc.get(a).getCreated().toString());
							createbyElement.addText(tbizmc.get(a).getCreateby().getId());
						}
						writer.write(doc);
						InputStream is = new FileInputStream(xmlfile);
						//��ɾ���ɵģ��ٱ����µģ�
						commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WRSGXCDCDBFJ.getCode());
						commonManager.saveFile(is, applyId, FileTypeEnums.WRSGXCDCDBFJ.getCode(), "work", "workInfo.xml", ((Integer) is.available()).longValue());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
	@Override
	public FyWebResult queryJcblFileList(String pid, String canDel,String fileType,String page, String rows) {
		List<String> ext = new ArrayList<String>();
		ext.add(".jpg");
		ext.add(".png");
		ext.add(".bmp");
		ext.add(".jpeg");
		ext.add(".doc");
		ext.add(".docx");
		ext.add(".txt");
		Map<String,Object> condition = new HashMap<String,Object>();
		String sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ "
			   		+"left join t_biz_morecheck m on f.id_ = m.fileid_ where f.pid_ = :pid";
		condition.put("pid", pid);
		//��������
		if(StringUtils.isNotBlank(fileType)){
			sql+=" and d.code_=:fileTypeCode ";
			fileType = FileTypeEnums.getTypeByEnumName(fileType);
			condition.put("fileTypeCode", fileType);
		}
		sql+=" order by m.times_ asc";
		FyResult pos = this.dao.find(sql, Integer.parseInt(page), rows==null?0:Integer.parseInt(rows), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		fy.setRows(rowsList);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String name = "";
		for (Object[] obj : listLawobj) {
			if (String.valueOf(obj[1]).contains(".")){
				name = String.valueOf(obj[1]).substring((String.valueOf(obj[1]).lastIndexOf(".") + 1), String.valueOf(obj[1]).length());
			}
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("url", "/download.mo?id="+String.valueOf(obj[0]));
			if(obj[2] != null && FileUtil.isImage(String.valueOf(obj[2]))){
				dataObject.put("imageUrl", obj[0] == null ? "" : "/downThumbnailImage.mo?id="+String.valueOf(obj[0]));
			}
			dataObject.put("filetype", name);
			dataObject.put("filename", String.valueOf(obj[2]));
			long filesize = Long.valueOf(String.valueOf(obj[3]));
			dataObject.put("filesize", FileUtil.sizeFormat(filesize));
			String operate = "";
			if (String.valueOf(obj[2]).lastIndexOf(".")!=-1 && ext.contains(String.valueOf(obj[2]).substring(String.valueOf(obj[2]).lastIndexOf("."), String.valueOf(obj[2]).length()).toLowerCase())) {
				operate = "<a class='b-link' onclick='review(this)' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[3])+"' >Ԥ��</a>";
			}
			if (canDel==null || canDel.equals("Y")) {
				operate += OperateUtil.getDloadOperate(String.valueOf(obj[0]))+OperateUtil.getDeleteOperate(String.valueOf(obj[0]));
			} else {
				operate += OperateUtil.getDloadOperate(String.valueOf(obj[0]));
			}
			dataObject.put("operate", operate);
			dataObject.put("filetypecode", String.valueOf(obj[5]));
			rowsList.add(dataObject);
		}
		return fy;
	}
	
	public String getPsyj(String applyId){
		////////�����ɷ���¼///////
		StringBuffer sql = new StringBuffer();
		sql.append(" from WorkLog where work.id =? and operateType =? order by czsj asc, operateType ");
		List<WorkLog> list = this.dao.find(sql.toString(),applyId,WorkLogType.PF.getCode());
		if(list!=null && list.size()>0){
			return list.get(0).getNote();
		}
		return null;
	}
	
	@Override
	public FyWebResult getXcjcrwList(String rwmc, String rwly, String pfrId, String rwzt, String tasktype, String zfdxType, String pfStarttime, String pfEndtime, String gdStarttime, String gdEndtime,
			String page, String pageSize) throws Exception {
		//���߰��ʶ���ӣ��������߰����ݱ��棬Ȼ����
		String biaoshi = indexManager.sysVer;
		QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        
        StringBuilder countSql = new StringBuilder();
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	sql.append("select distinct v from VdbWork v, TBizTaskandtasktype tt where v.userId = :userId and v.id = tt.taskid ");
        	countSql.append("select distinct v.* from v_dbwork v, T_Biz_Taskandtasktype tt where v.userId_ = :userId and v.id_ = tt.taskid_ ");
        } else {
        	sql.append("select v from VdbWork v , TBizTaskandtasktype tt where v.userId = :userId and v.id = tt.taskid");
        }
        
        TSysUser cur = CtxUtil.getCurUser();
        data.put("userId", cur.getUsername());
        //��������
        if (StringUtil.isNotBlank(rwmc)) {
            sql.append(" and (v.name like :dbworkName )");
            countSql.append(" and (v.WORK_NAME_ like :dbworkName )");
            data.putLike("dbworkName", rwmc);
        }
        //������Դ
        if (StringUtil.isNotBlank(rwly)) {
            sql.append(" and v.source = :rwly");
            countSql.append(" and v.SOURCE_ = :rwly");
            data.put("rwly", rwly);
        }
        //�ɷ���
        if (StringUtil.isNotBlank(pfrId)) {
            sql.append(" and v.createUser.id = :pfr");
            countSql.append(" and v.CREATEBY_ = :pfr");
            data.put("pfr", pfrId);
        }
        //����״̬
        if (StringUtil.isNotBlank(rwzt)) {
            sql.append(" and v.state = :state");
            countSql.append(" and v.STATE_ = :state");
            data.put("state", rwzt);
        }
        //ִ����������
        if (StringUtil.isNotBlank(zfdxType)) {
            sql.append(" and v.zfdxType = :zfdxlx");
            countSql.append(" and v.ZFDX_TYPE_ = :zfdxlx");
            data.put("zfdxlx", zfdxType);
        }
        
      //�ɷ�ʱ��
        if(StringUtils.isNotBlank(pfStarttime)){
        	if("0".equals(biaoshi)){
        		sql.append(" and v.startTime >= '"+pfStarttime+"'");
    			countSql.append(" and v.START_TIME_ >= (:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
        	}else{
        		sql.append(" and v.startTime >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
    			countSql.append(" and v.START_TIME_ >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
    			data.put("pfStarttime", pfStarttime+" 00:00:00");
        	}
		}
		if(StringUtils.isNotBlank(pfEndtime)){
			if("0".equals(biaoshi)){
				sql.append(" and v.startTime <= '"+pfEndtime+"'");
				countSql.append(" and v.START_TIME_ <= (:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			}else{
				sql.append(" and v.startTime <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
				countSql.append(" and v.START_TIME_ <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
				data.put("pfEndtime", pfEndtime+" 23:59:59");
			}
		}
		
		//Ҫ�����ʱ��
        if(StringUtils.isNotBlank(gdStarttime)){
        	if("0".equals(biaoshi)){
        		sql.append(" and v.endTime >='"+gdStarttime+"'");
    			countSql.append(" and v.END_TIME_ >= (:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
			}else{
				sql.append(" and v.endTime >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
				countSql.append(" and v.END_TIME_ >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
				data.put("gdStarttime", gdStarttime+" 00:00:00");
			}
		}
		if(StringUtils.isNotBlank(gdEndtime)){
			if("0".equals(biaoshi)){
				sql.append(" and v.endTime <= '"+gdEndtime+"'");
				countSql.append(" and v.END_TIME_ <= (:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
			}else{
				sql.append(" and v.endTime <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
				countSql.append(" and v.END_TIME_ <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
				data.put("gdEndtime", gdEndtime+" 23:59:59");
			}
		}
        
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	String[] taskType = new String[tasktype.split(",").length];
            for (int i = 0; i < tasktype.split(",").length; i++) {
            	taskType[i] = tasktype.split(",")[i];
    		}
            sql.append(" and tt.tasktypeid in (:taskType) and tt.isActive = 'Y' ");
            countSql.append(" and tt.tasktypeid_ in (:taskType) and tt.isActive_ = 'Y' ");
            data.put("taskType", taskType);
        }else{
        	String taskTypeinit = "10,12,15";
        	String[] taskType = new String[taskTypeinit.split(",").length];
            for (int i = 0; i < taskTypeinit.split(",").length; i++) {
            	taskType[i] = taskTypeinit.split(",")[i];
    		}
            sql.append(" and tt.tasktypeid in (:taskType) and tt.isActive = 'Y' ");
            countSql.append(" and tt.tasktypeid_ in (:taskType) and tt.isActive_ = 'Y' ");
            data.put("taskType", taskType);
        }
        
        sql.append(" order by v.orderby desc, v.endTime asc, v.updateTime desc");
        countSql.append(" order by v.orderby_ desc, v.END_TIME_ asc, v.UPDATED_ desc");
        
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<VdbWork> vs = pos.getRe();
        NullControllHashMap d = null;
        String type = null;
        TSysUser u = null;
//        WorkType worktype = null;
        for (VdbWork ele : vs) {
            d = new NullControllHashMap();
            d.put("id", ele.getId());
            
            //������Դ����
            String str1="";
    		if(ele.getSource()!=null){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
    		}
            //�����̶�����
            String str2="";
    		if(ele.getEmergency()!=null){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
    		}
    		d.put("rwly", str1);//������Դ
    		//�������ڵ���ʱ��ʾ
            Date curDate=new Date();
            if(-1 == DateUtil.compareDate(curDate, ele.getEndTime())){
            	SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
                String endTime= s.format(ele.getEndTime().getTime());
            	long to =DateUtil.getEndDatetime(endTime).getTime();
                long from = curDate.getTime();
                long lastday = (to - from) / (1000 * 60 * 60 * 24);
                long syts = lastday+1;
                if(lastday < 2 || lastday == 2){
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", "<font color='red'>"+str2+"(ʣ��"+syts+"��)</font>");//�����̶�
                }else{
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", str2+"(ʣ��"+syts+"��)");//�����̶�
                }
            }else{
            	d.put("syts", 0);//ʣ������
            	d.put("jjcd", str2);//�����̶�
            }
            d.put("yqwcsx", ele.getEndTime());//Ҫ�����ʱ��
            if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 24){
            	d.put("dbworkName", ele.getName().substring(0, 23)+"...");
            	d.put("dbworkName1", ele.getName());
            }else{
            	d.put("dbworkName", ele.getName());
            	d.put("dbworkName1", ele.getName());
            }
            if(StringUtils.isNotBlank(ele.getWorkNote()) && ele.getWorkNote().length() > 24){
            	d.put("workNote", ele.getWorkNote().substring(0, 23)+"...");
            	d.put("workNote1", ele.getWorkNote());
            }else{
            	d.put("workNote", ele.getWorkNote());
            	d.put("workNote1", ele.getWorkNote());
            }
            d.put("dbcreated", ele.getCreateTime());
            if (null != ele.getCreateUser()) {
                u = this.userManager.getUser(ele.getCreateUser().getId());
                d.put("createby", u.getName());
            } else {
                d.put("createby", Constants.DEFAULTCREATER);
            }
            d.put("nextOper", ele.getNextOper());
            
            ///////�ն���Ҫ��һ��������//////
            String lastOper="";
            StringBuffer sql_step = new StringBuffer();
            sql_step.append(" from WorkLog where work.id =? order by czsj asc ");
    		List<WorkLog> list_step = this.dao.find(sql_step.toString(),ele.getId());
    		if(list_step!=null&&list_step.size()>0){
    			lastOper=list_step.get(list_step.size()-1).getCzrName();
    		}
            d.put("lastOper", lastOper);
            
            d.put("zxrName", ele.getZxrName());
            d.put("taskId", ele.getTaskId());

            if (log.isDebugEnabled()) {
                log.debug("getProcessId:" + ele.getProcessId());
            }
            d.put("taskType", ele.getCode());
            d.put("zxtime", ele.getZxtime());
            d.put("stateCode", ele.getState());
            d.put("state", WorkState.getNote(ele.getState()));
            d.put("start", ele.getStartTime());
            d.put("end", ele.getEndTime());
            
            //�Ƿ�����
            d.put("isYQ", DateUtil.compareDate(curDate, ele.getEndTime()));
            
            String nextActions = getJbpmActionJson(ele.getId(), ele.getTaskId(),
                ele.getProcessId(), ele.getNextActions());
            d.put("nextActions", nextActions);
            String operate = getJbpmAction(ele.getId(), ele.getTaskId(), ele.getProcessId(),
                ele.getNextActions());
            d.put("operate", operate);
            rows.add(d);
        }
        
        List<Object> count = null;
        if (StringUtil.isNotBlank(tasktype)) {
        	if("0".equals(biaoshi)){
        		count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ") total", data.getCanshu());
        	}else{
        		count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ")", data.getCanshu());
        	}
        	if (count != null){
        		fy.setTotal(Long.parseLong(count.get(0).toString()));
        	}
        }
        
        fy.setRows(rows);
        return fy;
    }
	
	@Override
	public FyWebResult getXftsrwList(String rwmc, String rwly, String pfrId, String rwzt, String tasktype, String zfdxType, String pfStarttime, String pfEndtime, String gdStarttime, String gdEndtime,
			String xfdjbId, String page, String pageSize) throws Exception {
		//���߰��ʶ���ӣ��������߰����ݱ��棬Ȼ����
		String biaoshi = indexManager.sysVer;
		QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        
        StringBuilder countSql = new StringBuilder();
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	sql.append("select distinct v from VdbWork v, TBizTaskandtasktype tt where v.userId = :userId and v.id = tt.taskid ");
        	countSql.append("select distinct v.* from v_dbwork v, T_Biz_Taskandtasktype tt where v.userId_ = :userId and v.id_ = tt.taskid_ ");
        } else {
        	sql.append("select v from VdbWork v , TBizTaskandtasktype tt where v.userId = :userId and v.id = tt.taskid");
        }
        
        TSysUser cur = CtxUtil.getCurUser();
        data.put("userId", cur.getUsername());
        //��������
        if (StringUtil.isNotBlank(rwmc)) {
            sql.append(" and (v.name like :dbworkName )");
            countSql.append(" and (v.WORK_NAME_ like :dbworkName )");
            data.putLike("dbworkName", rwmc);
        }
        //������Դ
        if (StringUtil.isNotBlank(rwly)) {
            sql.append(" and v.source = :rwly");
            countSql.append(" and v.SOURCE_ = :rwly");
            data.put("rwly", rwly);
        }
        //�ɷ���
        if (StringUtil.isNotBlank(pfrId)) {
            sql.append(" and v.createUser.id = :pfr");
            countSql.append(" and v.CREATEBY_ = :pfr");
            data.put("pfr", pfrId);
        }
        //����״̬
        if (StringUtil.isNotBlank(rwzt)) {
            sql.append(" and v.state = :state");
            countSql.append(" and v.STATE_ = :state");
            data.put("state", rwzt);
        }
        //ִ����������
        if (StringUtil.isNotBlank(zfdxType)) {
            sql.append(" and v.zfdxType = :zfdxlx");
            countSql.append(" and v.ZFDX_TYPE_ = :zfdxlx");
            data.put("zfdxlx", zfdxType);
        }
        
        //ִ����������
        if (StringUtil.isNotBlank(xfdjbId)) {
            sql.append(" and v.xfdjbId = :xfdjbId");
            countSql.append(" and v.XFDJBID_ = :xfdjbId");
            data.put("xfdjbId", xfdjbId);
        }
        
        //�ɷ�ʱ��
        if(StringUtils.isNotBlank(pfStarttime)){
        	if("0".equals(biaoshi)){
        		sql.append(" and v.startTime >= '"+pfStarttime+"'");
    			countSql.append(" and v.START_TIME_ >= (:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
        	}else{
        		sql.append(" and v.startTime >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
    			countSql.append(" and v.START_TIME_ >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
    			data.put("pfStarttime", pfStarttime+" 00:00:00");
        	}
		}
		if(StringUtils.isNotBlank(pfEndtime)){
			if("0".equals(biaoshi)){
				sql.append(" and v.startTime <= '"+pfEndtime+"'");
				countSql.append(" and v.START_TIME_ <= (:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			}else{
				sql.append(" and v.startTime <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
				countSql.append(" and v.START_TIME_ <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
				data.put("pfEndtime", pfEndtime+" 23:59:59");
			}
		}
		
		//Ҫ�����ʱ��
        if(StringUtils.isNotBlank(gdStarttime)){
        	if("0".equals(biaoshi)){
        		sql.append(" and v.endTime >='"+gdStarttime+"'");
    			countSql.append(" and v.END_TIME_ >= (:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
			}else{
				sql.append(" and v.endTime >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
				countSql.append(" and v.END_TIME_ >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
				data.put("gdStarttime", gdStarttime+" 00:00:00");
			}
		}
		if(StringUtils.isNotBlank(gdEndtime)){
			if("0".equals(biaoshi)){
				sql.append(" and v.endTime <= '"+gdEndtime+"'");
				countSql.append(" and v.END_TIME_ <= (:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
			}else{
				sql.append(" and v.endTime <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
				countSql.append(" and v.END_TIME_ <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
				data.put("gdEndtime", gdEndtime+" 23:59:59");
			}
		}
        
		//��������
		String taskTypeinit = "13";
    	String[] taskType = new String[taskTypeinit.split(",").length];
        for (int i = 0; i < taskTypeinit.split(",").length; i++) {
        	taskType[i] = taskTypeinit.split(",")[i];
		}
        sql.append(" and tt.tasktypeid in (:taskType) and tt.isActive = 'Y' ");
        countSql.append(" and tt.tasktypeid_ in (:taskType) and tt.isActive_ = 'Y' ");
        data.put("taskType", taskType);
        
        sql.append(" order by v.orderby desc, v.endTime asc, v.updateTime desc");
        countSql.append(" order by v.orderby_ desc, v.END_TIME_ asc, v.UPDATED_ desc");
        
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<VdbWork> vs = pos.getRe();
        NullControllHashMap d = null;
        String type = null;
        TSysUser u = null;
//        WorkType worktype = null;
        for (VdbWork ele : vs) {
            d = new NullControllHashMap();
            d.put("ids", ele.getId());
            
            //������Դ����
            String str1="";
    		if(ele.getSource()!=null){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
    		}
            //�����̶�����
            String str2="";
    		if(ele.getEmergency()!=null){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
    		}
    		d.put("rwly", str1);//������Դ
    		
    		//�������ڵ���ʱ��ʾ
            Date curDate=new Date();
            if(-1 == DateUtil.compareDate(curDate, ele.getEndTime())){
            	SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
                String endTime= s.format(ele.getEndTime().getTime());
            	long to =DateUtil.getEndDatetime(endTime).getTime();
            	//long to = ele.getEndTime().getTime();
                long from = curDate.getTime();
                long lastday = (to - from) / (1000 * 60 * 60 * 24);
                long syts = lastday+1;
                if(lastday < 2 || lastday == 2){
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", "<font color='red'>"+str2+"(ʣ��"+syts+"��)</font>");//�����̶�
                }else{
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", str2+"(ʣ��"+syts+"��)");//�����̶�
                }
            }else{
            	d.put("syts", 0);//ʣ������
            	d.put("jjcd", str2);//�����̶�
            }
            d.put("yqwcsx", ele.getEndTime());//Ҫ�����ʱ��
            if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 24){
            	d.put("dbworkName", ele.getName().substring(0, 23)+"...");
            	d.put("dbworkName1", ele.getName());
            }else{
            	d.put("dbworkName", ele.getName());
            	d.put("dbworkName1", ele.getName());
            }
            if(StringUtils.isNotBlank(ele.getWorkNote()) && ele.getWorkNote().length() > 24){
            	d.put("workNote", ele.getWorkNote().substring(0, 23)+"...");
            	d.put("workNote1", ele.getWorkNote());
            }else{
            	d.put("workNote", ele.getWorkNote());
            	d.put("workNote1", ele.getWorkNote());
            }
            d.put("dbcreated", ele.getCreateTime());
            if (null != ele.getCreateUser()) {
                u = this.userManager.getUser(ele.getCreateUser().getId());
                d.put("createby", u.getName());
            } else {
                d.put("createby", Constants.DEFAULTCREATER);
            }
            d.put("nextOper", ele.getNextOper());
            
            ///////�ն���Ҫ��һ��������//////
            String lastOper="";
            StringBuffer sql_step = new StringBuffer();
            sql_step.append(" from WorkLog where work.id =? order by czsj asc ");
    		List<WorkLog> list_step = this.dao.find(sql_step.toString(),ele.getId());
    		if(list_step!=null&&list_step.size()>0){
    			lastOper=list_step.get(list_step.size()-1).getCzrName();
    		}
            d.put("lastOper", lastOper);
            
            d.put("zxrName", ele.getZxrName());
            d.put("taskId", ele.getTaskId());

            if (log.isDebugEnabled()) {
                log.debug("getProcessId:" + ele.getProcessId());
            }
            d.put("taskType", ele.getCode());
            d.put("zxtime", ele.getZxtime());
            d.put("stateCode", ele.getState());
            d.put("state", WorkState.getNote(ele.getState()));
            d.put("start", ele.getStartTime());
            d.put("end", ele.getEndTime());
            
            //�Ƿ�����
            d.put("isYQ", DateUtil.compareDate(curDate, ele.getEndTime()));
            
            String nextActions = getJbpmActionJson(ele.getId(), ele.getTaskId(),
                ele.getProcessId(), ele.getNextActions());
            d.put("nextActions", nextActions);
            String operate = getJbpmAction(ele.getId(), ele.getTaskId(), ele.getProcessId(),
                ele.getNextActions());
            d.put("operate", operate);
            rows.add(d);
        }
        
        List<Object> count = null;
        if (StringUtil.isNotBlank(tasktype)) {
        	if("0".equals(biaoshi)){
        		count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ") total", data.getCanshu());
        	}else{
        		count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ")", data.getCanshu());
        	}
        	if (count != null){
        		fy.setTotal(Long.parseLong(count.get(0).toString()));
        	}
        }
        
        fy.setRows(rows);
        return fy;
    }
	public String dbQuery(){
		JSONArray array = new JSONArray();
		JSONObject object = null;
		Map<String, Object> condition = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        TSysUser cur = CtxUtil.getCurUser();
        sql.append("select v.id_,v.work_name_,v.END_TIME_,v.TASK_ID,v.PROCESS_ID_,v.NEXT_ACTIONS_ from v_dbWork v  where v.userId_ = :userId ");
        condition.put("userId", cur.getUsername());
        sql.append(" order by v.orderby_ desc, v.END_TIME_ asc, v.UPDATED_ desc");
        
        List <Object[]> list = dao.findBySql(sql.toString(), condition);
        if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				try {
					object = new JSONObject();
					object.put("id", obj[0]);
					object.put("dbworkName", obj[1]);//��������
					if(StringUtil.isNotBlank(String.valueOf(obj[2]))){
						object.put("yqwcsx", obj[2]);//Ҫ�����ʱ��
					}
					List<TBizTaskandtasktype> ttaskType= this.dao.find("from TBizTaskandtasktype t where t.isActive='Y' and t.taskid=? ",obj[0]);
					String type="";
					for(TBizTaskandtasktype tsk:ttaskType){
						if(ttaskType.size()>1){
							type += TaskTypeCode.getNote(tsk.getTasktypeid())+",";
						}else{
							type = TaskTypeCode.getNote(tsk.getTasktypeid());
						}
						
					}
					if(type.length()>7){
						type=type.substring(0, 7)+"...";
					}
					if(StringUtil.isBlank(type)){
						type="������������";
					}
					object.put("type", type);
					//�������ڵ���ʱ��ʾ
		            Date curDate=new Date();
		            if(StringUtil.isNotBlank(String.valueOf(obj[2]))){
		            if(-1 == DateUtil.compareDate(curDate, DateUtil.getEndDatetime(obj[2].toString()))){
		            	long to = DateUtil.getEndDatetime(obj[2].toString()).getTime();
		                long from = curDate.getTime();
		                long lastday = (to - from) / (1000 * 60 * 60 * 24);
		                long syts = lastday+1;
		                if(syts < 2 || syts == 2 ){
		                	object.put("syts", "<span style='color:#ff7200;'>(ʣ��&nbsp;&nbsp;"+syts+"&nbsp;&nbsp;��)</span>");//ʣ������
		                }else{
		                	object.put("syts", "(ʣ��&nbsp;&nbsp;"+syts+"&nbsp;&nbsp;��)");//ʣ������
		                }
		            }else{
		            	object.put("syts", "<span style='color:#ff7200;'>&nbsp;&nbsp;(&nbsp;������&nbsp;)&nbsp;&nbsp;</span>");//ʣ������
		            }
		            }
					//�²�����
				    String operate = getNextAction(obj[0].toString(), obj[3].toString(),
									obj[4].toString(), obj[5].toString());
				    object.put("operate", operate);
					
					array.put(object);
				} catch (JSONException e) {
					e.printStackTrace();
				} 
			}
		}
        return array.toString();
	}
	
	public String ybQuery(){
		JSONArray array = new JSONArray();
		JSONObject object = null;
		Map<String, Object> condition = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        TSysUser cur = CtxUtil.getCurUser();
        //��������
        sql.append("select w.id_,w.work_name_,w.state_ from Work_ w where w.isActive_ = 'Y' ");
        
        sql.append(" and w.AREAID_ = :areaid ");
        condition.put("areaid", CtxUtil.getAreaId());
        
        sql.append(" and w.id_ in (select WORK_ID_ from T_BIZ_TASKOPERLOG l where 1=1 ");
        //������
        sql.append(" and l.CZR_ID_ = :czrId )");
        condition.put("czrId", cur.getId());
        
        sql.append(" order by w.START_TIME_ desc");
        
        List <Object[]> list = dao.findBySql(sql.toString(), condition);
        if (list != null && list.size() > 0) {
			for (Object[] obj : list) {
				try {
					object = new JSONObject();
					object.put("id", obj[0]);
					object.put("ybworkName", obj[1]);//��������
					object.put("state", obj[2]==null?"":WorkState.getNameByCode(obj[2].toString()));//Ҫ�����ʱ��
					array.put(object);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
        return array.toString();
	}
	
	@Override
	public FyWebResult getRcbgrwList(String rwmc, String rwly, String pfrId, String rwzt, String tasktype, String zfdxType, String pfStarttime, String pfEndtime, String gdStarttime, String gdEndtime,
			String xfdjbId, String page, String pageSize) throws Exception {
		//���߰��ʶ���ӣ��������߰����ݱ��棬Ȼ����
		String biaoshi = indexManager.sysVer;
		QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        
        StringBuilder countSql = new StringBuilder();
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	sql.append("select distinct v from VdbWork v, TBizTaskandtasktype tt where v.userId = :userId and v.id = tt.taskid ");
        	countSql.append("select distinct v.* from v_dbwork v, T_Biz_Taskandtasktype tt where v.userId_ = :userId and v.id_ = tt.taskid_ ");
        } else {
        	sql.append("select v from VdbWork v , TBizTaskandtasktype tt where v.userId = :userId and v.id = tt.taskid");
        }
        
        TSysUser cur = CtxUtil.getCurUser();
        data.put("userId", cur.getUsername());
        //��������
        if (StringUtil.isNotBlank(rwmc)) {
            sql.append(" and (v.name like :dbworkName )");
            countSql.append(" and (v.WORK_NAME_ like :dbworkName )");
            data.putLike("dbworkName", rwmc);
        }
        //������Դ
        if (StringUtil.isNotBlank(rwly)) {
            sql.append(" and v.source = :rwly");
            countSql.append(" and v.SOURCE_ = :rwly");
            data.put("rwly", rwly);
        }
        //�ɷ���
        if (StringUtil.isNotBlank(pfrId)) {
            sql.append(" and v.createUser.id = :pfr");
            countSql.append(" and v.CREATEBY_ = :pfr");
            data.put("pfr", pfrId);
        }
        //����״̬
        if (StringUtil.isNotBlank(rwzt)) {
            sql.append(" and v.state = :state");
            countSql.append(" and v.STATE_ = :state");
            data.put("state", rwzt);
        }
        //ִ����������
        if (StringUtil.isNotBlank(zfdxType)) {
            sql.append(" and v.zfdxType = :zfdxlx");
            countSql.append(" and v.ZFDX_TYPE_ = :zfdxlx");
            data.put("zfdxlx", zfdxType);
        }
        
        //ִ����������
        if (StringUtil.isNotBlank(xfdjbId)) {
            sql.append(" and v.xfdjbId = :xfdjbId");
            countSql.append(" and v.XFDJBID_ = :xfdjbId");
            data.put("xfdjbId", xfdjbId);
        }
        
        //�ɷ�ʱ��
        if(StringUtils.isNotBlank(pfStarttime)){
        	if("0".equals(biaoshi)){
        		sql.append(" and v.startTime >= '"+pfStarttime+"'");
    			countSql.append(" and v.START_TIME_ >= (:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
        	}else{
        		sql.append(" and v.startTime >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
    			countSql.append(" and v.START_TIME_ >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
    			data.put("pfStarttime", pfStarttime+" 00:00:00");
        	}
		}
		if(StringUtils.isNotBlank(pfEndtime)){
			if("0".equals(biaoshi)){
				sql.append(" and v.startTime <= '"+pfEndtime+"'");
				countSql.append(" and v.START_TIME_ <= (:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			}else{
				sql.append(" and v.startTime <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
				countSql.append(" and v.START_TIME_ <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
				data.put("pfEndtime", pfEndtime+" 23:59:59");
			}
		}
		
		//Ҫ�����ʱ��
        if(StringUtils.isNotBlank(gdStarttime)){
        	if("0".equals(biaoshi)){
        		sql.append(" and v.endTime >='"+gdStarttime+"'");
    			countSql.append(" and v.END_TIME_ >= (:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
			}else{
				sql.append(" and v.endTime >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
				countSql.append(" and v.END_TIME_ >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
				data.put("gdStarttime", gdStarttime+" 00:00:00");
			}
		}
		if(StringUtils.isNotBlank(gdEndtime)){
			if("0".equals(biaoshi)){
				sql.append(" and v.endTime <= '"+gdEndtime+"'");
				countSql.append(" and v.END_TIME_ <= (:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
			}else{
				sql.append(" and v.endTime <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
				countSql.append(" and v.END_TIME_ <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
				data.put("gdEndtime", gdEndtime+" 23:59:59");
			}
		}
        
        //��������
		String taskTypeinit = "24";
    	String[] taskType = new String[taskTypeinit.split(",").length];
        for (int i = 0; i < taskTypeinit.split(",").length; i++) {
        	taskType[i] = taskTypeinit.split(",")[i];
		}
        sql.append(" and tt.tasktypeid in (:taskType) and tt.isActive = 'Y' ");
        countSql.append(" and tt.tasktypeid_ in (:taskType) and tt.isActive_ = 'Y' ");
        data.put("taskType", taskType);
        
        sql.append(" order by v.orderby desc, v.endTime asc, v.updateTime desc");
        countSql.append(" order by v.orderby_ desc, v.END_TIME_ asc, v.UPDATED_ desc");
        
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<VdbWork> vs = pos.getRe();
        NullControllHashMap d = null;
        String type = null;
        TSysUser u = null;
//        WorkType worktype = null;
        for (VdbWork ele : vs) {
            d = new NullControllHashMap();
            d.put("ids", ele.getId());
            
            //������Դ����
            String str1="";
    		if(ele.getSource()!=null){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
    		}
            //�����̶�����
            String str2="";
    		if(ele.getEmergency()!=null){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
    		}
    		d.put("rwly", str1);//������Դ
    		
    		//�������ڵ���ʱ��ʾ
            Date curDate=new Date();
            if(-1 == DateUtil.compareDate(curDate, ele.getEndTime())){
            	SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
                String endTime= s.format(ele.getEndTime().getTime());
            	long to =DateUtil.getEndDatetime(endTime).getTime();
            	//long to = ele.getEndTime().getTime();
                long from = curDate.getTime();
                long lastday = (to - from) / (1000 * 60 * 60 * 24);
                long syts = lastday+1;
                if(lastday < 2 || lastday == 2){
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", "<font color='red'>"+str2+"(ʣ��"+syts+"��)</font>");//�����̶�
                }else{
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", str2+"(ʣ��"+syts+"��)");//�����̶�
                }
            }else{
            	d.put("syts", 0);//ʣ������
            	d.put("jjcd", str2);//�����̶�
            }
            d.put("yqwcsx", ele.getEndTime());//Ҫ�����ʱ��
            if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 24){
            	d.put("dbworkName", ele.getName().substring(0, 23)+"...");
            	d.put("dbworkName1", ele.getName());
            }else{
            	d.put("dbworkName", ele.getName());
            	d.put("dbworkName1", ele.getName());
            }
            if(StringUtils.isNotBlank(ele.getWorkNote()) && ele.getWorkNote().length() > 24){
            	d.put("workNote", ele.getWorkNote().substring(0, 23)+"...");
            	d.put("workNote1", ele.getWorkNote());
            }else{
            	d.put("workNote", ele.getWorkNote());
            	d.put("workNote1", ele.getWorkNote());
            }
            d.put("dbcreated", ele.getCreateTime());
            if (null != ele.getCreateUser()) {
                u = this.userManager.getUser(ele.getCreateUser().getId());
                d.put("createby", u.getName());
            } else {
                d.put("createby", Constants.DEFAULTCREATER);
            }
            d.put("nextOper", ele.getNextOper());
            
            ///////�ն���Ҫ��һ��������//////
            String lastOper="";
            StringBuffer sql_step = new StringBuffer();
            sql_step.append(" from WorkLog where work.id =? order by czsj asc ");
    		List<WorkLog> list_step = this.dao.find(sql_step.toString(),ele.getId());
    		if(list_step!=null&&list_step.size()>0){
    			lastOper=list_step.get(list_step.size()-1).getCzrName();
    		}
            d.put("lastOper", lastOper);
            
            d.put("zxrName", ele.getZxrName());
            d.put("taskId", ele.getTaskId());

            if (log.isDebugEnabled()) {
                log.debug("getProcessId:" + ele.getProcessId());
            }
            d.put("taskType", ele.getCode());
            d.put("zxtime", ele.getZxtime());
            d.put("stateCode", ele.getState());
            d.put("state", WorkState.getNote(ele.getState()));
            d.put("start", ele.getStartTime());
            d.put("end", ele.getEndTime());
            
            //�Ƿ�����
            d.put("isYQ", DateUtil.compareDate(curDate, ele.getEndTime()));
            
            String nextActions = getJbpmActionJson(ele.getId(), ele.getTaskId(),
                ele.getProcessId(), ele.getNextActions());
            d.put("nextActions", nextActions);
            String operate = getJbpmAction(ele.getId(), ele.getTaskId(), ele.getProcessId(),
                ele.getNextActions());
            d.put("operate", operate);
            rows.add(d);
        }
        
        List<Object> count = null;
        if (StringUtil.isNotBlank(tasktype)) {
        	if("0".equals(biaoshi)){
        		count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ") total", data.getCanshu());
        	}else{
        		count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ")", data.getCanshu());
        	}
        	if (count != null){
        		fy.setTotal(Long.parseLong(count.get(0).toString()));
        	}
        }
        
        fy.setRows(rows);
        return fy;
    }

	@Override
	public FyWebResult getRcbgRwpfList(String rwmc, String rwly, String rwlx,
			String page, String pageSize) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
        //data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        sql.append("select w.id_,w.work_name_,w.source_, w.security_,w.emergency_,w.djrid_,w.created_  from work_ w, t_biz_taskandtasktype t where w.id_ = t.taskid_   and t.tasktypeid_ = '24' ");
        if (StringUtils.isNotBlank(rwmc)) {
            sql.append(" and (w.work_name_ like :rwmc)");
            condition.put("rwmc", rwmc);
        }
        if (StringUtils.isNotBlank(rwly)) {
            sql.append(" and w.source_=:rwly");
            condition.put("rwly", rwly);
        }
        sql.append(" and w.createby_=:createUser");
        condition.put("createUser", CtxUtil.getCurUser());
        sql.append(" and w.start_time_ is null ");//����ʼʱ��Ϊ�գ���Ϊ�����ɵ�����
//        sql.append(" and taskstarted is null");
        sql.append(" order by w.created_ DESC ");
        
        //FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);

		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> row = null;
		//String userId = CtxUtil.getCurUser().getId();//��ǰ�û�id
		for (Object[] obj : listLawobj) {
			row = new HashMap<String, String>();
    		
    		//������Դ���ƣ���Ϊ�ǲ鿴ҳ�棬ֱ����ԭ�ֶ��ˣ�
    		String str1="";
    		if(StringUtil.isNotBlank(String.valueOf(obj[2]))){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),String.valueOf(obj[2]));
    		}
    		//�����ܼ�����
    		String str2="";
    		if(StringUtil.isNotBlank(String.valueOf(obj[3]))){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWMJ.getCode(),String.valueOf(obj[3]));
    		}
    		//�����̶�����
    		String str3="";
    		if(StringUtil.isNotBlank(String.valueOf(obj[4]))){
    			str3=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),String.valueOf(obj[4]));
    		}
    		row.put("rwly", str1);//������Դ
    		row.put("rwmj", str2);//�����ܼ�
    		row.put("jjcd", str3);//�����̶�
            
            row.put("id", String.valueOf(obj[0]));
            //row.put("rwmc", ele.getName());
            if(StringUtils.isNotBlank(String.valueOf(obj[1])) && String.valueOf(obj[1]).length() > 35){
            	row.put("rwmc", String.valueOf(obj[1]).substring(0, 34)+"...");
            	row.put("rwmc1", String.valueOf(obj[1]));
	        }else{
	        	row.put("rwmc", String.valueOf(obj[1]));//��������
	        	row.put("rwmc1", String.valueOf(obj[1]));
	        }
            if(StringUtils.isNotBlank(String.valueOf(obj[5]))){
    			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, String.valueOf(obj[5]));
    			row.put("djr", djrObj==null?"":djrObj.getName());//�Ǽ���
    		}else{
    			row.put("djr", "");
    		}
            row.put("scsj", String.valueOf(obj[6]));
            
            //operate��ʱ���Լ�д����ͷ�滻��OperateUtil.getOperate(ele.getId())
            StringBuilder operate = new StringBuilder();
            operate.append(" <a onclick='pf(this)' style='color:#0088cc;cursor:pointer;' id='"+String.valueOf(obj[0])+"' >�ɷ�</a>  ");
            operate.append(" <a onclick='info(this)' style='color:#0088cc;cursor:pointer;' id='"+String.valueOf(obj[0])+"' >�鿴</a>  ");
            operate.append(" <a onclick='del(this)' style='color:#0088cc;cursor:pointer;' id='"+String.valueOf(obj[0])+"' >ɾ��</a>  ");
            
            row.put("operate", operate.toString());
            rows.add(row);
        }
        fy.setRows(rows);
        return fy;
	}
	
	@Override
	public TSysUser getPfr(String applyId) {
		TBizTaskuser xpr = (TBizTaskuser) this.dao.find("from TBizTaskuser where taskid = ? and type = ? ", applyId, TaskUserEnum.PFR.getCode()).get(0);
		TSysUser cur=(TSysUser)this.dao.get(TSysUser.class, xpr.getUserid());//��������������Ϊ���渽����user
		return cur;
	}
	
	@Override
	public FyWebResult getZfjcDbrwList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId,  String page,
			String pageSize) throws Exception {
        QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        
        StringBuilder countSql = new StringBuilder();
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	sql.append("select distinct v from Work v, TBizTaskandtasktype tt where v.userId = :userId and v.id = tt.taskid ");
        	countSql.append("select distinct v.* from work_zf v, T_Biz_Taskandtasktype tt where v.userId_ = :userId and v.id_ = tt.taskid_ ");
        } else {
        	sql.append("select v from Work v where v.userId = :userId");
        }
        
        TSysUser cur = CtxUtil.getCurUser();
        data.put("userId", cur.getUsername());
        //��������
        if (StringUtil.isNotBlank(rwmc)) {
            sql.append(" and (v.name like :dbworkName )");
            countSql.append(" and (v.WORK_NAME_ like :dbworkName )");
            data.putLike("dbworkName", rwmc);
        }
        //������Դ
        if (StringUtil.isNotBlank(rwly)) {
            sql.append(" and v.source = :rwly");
            countSql.append(" and v.SOURCE_ = :rwly");
            data.put("rwly", rwly);
        }
        //�ɷ���
        if (StringUtil.isNotBlank(pfrId)) {
            sql.append(" and v.createUser.id = :pfr");
            countSql.append(" and v.CREATEBY_ = :pfr");
            data.put("pfr", pfrId);
        }
        //����״̬
        if (StringUtil.isNotBlank(rwzt)) {
            sql.append(" and v.state = :state");
            countSql.append(" and v.STATE_ = :state");
            data.put("state", rwzt);
        }
        
        //ִ����������
        if (StringUtil.isNotBlank(zfdxType)) {
            sql.append(" and v.zfdxType = :zfdxlx");
            countSql.append(" and v.ZFDX_TYPE_ = :zfdxlx");
            data.put("zfdxlx", zfdxType);
        }
        
        //�ɷ�ʱ��
        if(StringUtils.isNotBlank(pfStarttime)){
    		sql.append(" and v.startTime >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.START_TIME_ >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("pfStarttime", pfStarttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(pfEndtime)){
			sql.append(" and v.startTime <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.START_TIME_ <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("pfEndtime", pfEndtime+" 23:59:59");
		}
		
		//Ҫ�����ʱ��
        if(StringUtils.isNotBlank(gdStarttime)){
			sql.append(" and v.endTime >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.END_TIME_ >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("gdStarttime", gdStarttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(gdEndtime)){
			sql.append(" and v.endTime <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.END_TIME_ <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("gdEndtime", gdEndtime+" 23:59:59");
		}
        
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	String[] taskType = new String[tasktype.split(",").length];
            for (int i = 0; i < tasktype.split(",").length; i++) {
            	taskType[i] = tasktype.split(",")[i];
    		}
            sql.append(" and tt.tasktypeid in (:taskType) and tt.isActive = 'Y' ");
            countSql.append(" and tt.tasktypeid_ in (:taskType) and tt.isActive_ = 'Y' ");
            data.put("taskType", taskType);
        }
        //�ŷõǼǱ����������ѯ
        if(StringUtils.isNotBlank(xfdjId)){
        	sql.append(" and v.xfdjbId = :xfdjId");
            countSql.append(" and v.xfdjbId_ = :xfdjId");
            data.put("xfdjId", xfdjId);
        }
        
        sql.append(" order by v.orderby desc, v.endTime asc, v.updateTime desc");
        countSql.append(" order by v.orderby_ desc, v.END_TIME_ asc, v.UPDATED_ desc");
        
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<VdbWork> vs = pos.getRe();
        NullControllHashMap d = null;
        String type = null;
        TSysUser u = null;
        //WorkType worktype = null;
        for (VdbWork ele : vs) {
            d = new NullControllHashMap();
            //��������
			List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(ele.getId());
            d.put("id", ele.getId());
            String rwlxIds = "";
			for (int i = 0; i < rwlxlistMap.size(); i++) {
				if (i < rwlxlistMap.size() - 1) {
					rwlxIds += TaskTypeCode.getNote(rwlxlistMap.get(i).get("id")) + ",";
				} else {
					rwlxIds += TaskTypeCode.getNote(rwlxlistMap.get(i).get("id"));
				}
			}
			if(StringUtils.isNotBlank(rwlxIds) && rwlxIds.length() > 10){
	            d.put("rwlx", rwlxIds.substring(0, 9)+"...");
	            d.put("rwlx1", rwlxIds);
	        }else{
	        	d.put("rwlx", rwlxIds);//��������
	        	d.put("rwlx1", rwlxIds);
	        }
            //������Դ����
            String str1="";
    		if(ele.getSource()!=null){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
    		}
            //�����̶�����
            String str2="";
    		if(ele.getEmergency()!=null){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
    		}
    		d.put("rwly", str1);//������Դ
    		//�������ڵ���ʱ��ʾ
            Date curDate=new Date();
            if(-1 == DateUtil.compareDate(curDate, ele.getEndTime())){
            	SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
                String endTime= s.format(ele.getEndTime().getTime());
            	long to =DateUtil.getEndDatetime(endTime).getTime();
                long from = curDate.getTime();
                long lastday = (to - from) / (1000 * 60 * 60 * 24);
                long syts = lastday+1;
                if(lastday < 2 || lastday == 2){
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", "<font color='red'>"+str2+"(ʣ��"+syts+"��)</font>");//�����̶�
                }else{
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", str2+"(ʣ��"+syts+"��)");//�����̶�
                }
            }else{
            	d.put("syts", 0);//ʣ������
            	d.put("jjcd", str2);//�����̶�
            }
            d.put("yqwcsx", ele.getEndTime());//Ҫ�����ʱ��
            if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 13){
            	d.put("dbworkName", ele.getName().substring(0, 12)+"...");
            	d.put("dbworkName1", ele.getName());
            }else{
            	d.put("dbworkName", ele.getName());
            	d.put("dbworkName1", ele.getName());
            }
            d.put("workNote", ele.getWorkNote());
            d.put("dbcreated", ele.getCreateTime());
            if (null != ele.getCreateUser()) {
                u = this.userManager.getUser(ele.getCreateUser().getId());
                d.put("createby", u.getName());
            } else {
                d.put("createby", Constants.DEFAULTCREATER);
            }
            d.put("nextOper", ele.getNextOper());
            ///////�ն���Ҫ��һ��������//////
            String lastOper="";
            StringBuffer sql_step = new StringBuffer();
            sql_step.append(" from WorkLog where work.id =? order by czsj asc ");
    		List<WorkLog> list_step = this.dao.find(sql_step.toString(),ele.getId());
    		if(list_step!=null&&list_step.size()>0){
    			lastOper=list_step.get(list_step.size()-1).getCzrName();
    		}
            d.put("lastOper", lastOper);
            d.put("zxrName", ele.getZxrName());
            d.put("taskId", ele.getTaskId());
            if (log.isDebugEnabled()) {
                log.debug("getProcessId:" + ele.getProcessId());
            }
            d.put("taskType", ele.getCode());
            d.put("zxtime", ele.getZxtime());
            d.put("stateCode", ele.getState());
            d.put("state", WorkState.getNote(ele.getState()));
            d.put("start", ele.getStartTime());
            d.put("end", ele.getEndTime());
            //�Ƿ�����
            d.put("isYQ", DateUtil.compareDate(curDate, ele.getEndTime()));
            String nextActions = getJbpmActionJson(ele.getId(), ele.getTaskId(),
                ele.getProcessId(), ele.getNextActions());
            d.put("nextActions", nextActions);
            String operate = getJbpmAction(ele.getId(), ele.getTaskId(), ele.getProcessId(),
                ele.getNextActions());
            if(StringUtils.isNotBlank(xfdjId)){
            	String info = "<a onclick='info(this)' id='"+ele.getId()+"' class='b-link'>�鿴</a>";
				String flowchart = "<a onclick='flowChart(this)' id='"+ele.getProcessId()+"' class='b-link'>����ͼ</a>";
            	d.put("operate", info+flowchart);
            }else{
            	d.put("operate", operate);
            }
            rows.add(d);
        }
        
        List<Object> count = null;
        if (StringUtil.isNotBlank(tasktype)) {
        	count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ")", data.getCanshu());
        	if (count != null){
        		fy.setTotal(Long.parseLong(count.get(0).toString()));
        	}
        }
        fy.setRows(rows);
        return fy;
    }
	
	@Override
	public FyWebResult getXftsDbrwList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId,  String page,
			String pageSize) throws Exception {
		QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        
        StringBuilder countSql = new StringBuilder();
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	sql.append("select distinct v from Work v, TBizTaskandtasktype tt where v.userId = :userId and v.id = tt.taskid ");
        	countSql.append("select distinct v.* from work_zf v, T_Biz_Taskandtasktype tt where v.userId_ = :userId and v.id_ = tt.taskid_ ");
        } else {
        	sql.append("select v from Work v where v.userId = :userId");
        }
        
        TSysUser cur = CtxUtil.getCurUser();
        data.put("userId", cur.getUsername());
        //��������
        if (StringUtil.isNotBlank(rwmc)) {
            sql.append(" and (v.name like :dbworkName )");
            countSql.append(" and (v.WORK_NAME_ like :dbworkName )");
            data.putLike("dbworkName", rwmc);
        }
        //������Դ
        if (StringUtil.isNotBlank(rwly)) {
            sql.append(" and v.source = :rwly");
            countSql.append(" and v.SOURCE_ = :rwly");
            data.put("rwly", rwly);
        }
        //�ɷ���
        if (StringUtil.isNotBlank(pfrId)) {
            sql.append(" and v.createUser.id = :pfr");
            countSql.append(" and v.CREATEBY_ = :pfr");
            data.put("pfr", pfrId);
        }
        //����״̬
        if (StringUtil.isNotBlank(rwzt)) {
            sql.append(" and v.state = :state");
            countSql.append(" and v.STATE_ = :state");
            data.put("state", rwzt);
        }
        
        //ִ����������
        if (StringUtil.isNotBlank(zfdxType)) {
            sql.append(" and v.zfdxType = :zfdxlx");
            countSql.append(" and v.ZFDX_TYPE_ = :zfdxlx");
            data.put("zfdxlx", zfdxType);
        }
        
        //�ɷ�ʱ��
        if(StringUtils.isNotBlank(pfStarttime)){
    		sql.append(" and v.startTime >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.START_TIME_ >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("pfStarttime", pfStarttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(pfEndtime)){
			sql.append(" and v.startTime <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.START_TIME_ <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("pfEndtime", pfEndtime+" 23:59:59");
		}
		
		//Ҫ�����ʱ��
        if(StringUtils.isNotBlank(gdStarttime)){
			sql.append(" and v.endTime >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.END_TIME_ >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("gdStarttime", gdStarttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(gdEndtime)){
			sql.append(" and v.endTime <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.END_TIME_ <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("gdEndtime", gdEndtime+" 23:59:59");
		}
        
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	String[] taskType = new String[tasktype.split(",").length];
            for (int i = 0; i < tasktype.split(",").length; i++) {
            	taskType[i] = tasktype.split(",")[i];
    		}
            sql.append(" and tt.tasktypeid in (:taskType) and tt.isActive = 'Y' ");
            countSql.append(" and tt.tasktypeid_ in (:taskType) and tt.isActive_ = 'Y' ");
            data.put("taskType", taskType);
        }
        //�ŷõǼǱ����������ѯ
        if(StringUtils.isNotBlank(xfdjId)){
        	sql.append(" and v.xfdjbId = :xfdjId");
            countSql.append(" and v.xfdjbId_ = :xfdjId");
            data.put("xfdjId", xfdjId);
        }
        
        sql.append(" order by v.orderby desc, v.endTime asc, v.updateTime desc");
        countSql.append(" order by v.orderby_ desc, v.END_TIME_ asc, v.UPDATED_ desc");
        
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<VdbWork> vs = pos.getRe();
        NullControllHashMap d = null;
        String type = null;
        TSysUser u = null;
        //WorkType worktype = null;
        for (VdbWork ele : vs) {
            d = new NullControllHashMap();
            //��������
			List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(ele.getId());
            d.put("id", ele.getId());
            String rwlxIds = "";
			for (int i = 0; i < rwlxlistMap.size(); i++) {
				if (i < rwlxlistMap.size() - 1) {
					rwlxIds += TaskTypeCode.getNote(rwlxlistMap.get(i).get("id")) + ",";
				} else {
					rwlxIds += TaskTypeCode.getNote(rwlxlistMap.get(i).get("id"));
				}
			}
			if(StringUtils.isNotBlank(rwlxIds) && rwlxIds.length() > 10){
	            d.put("rwlx", rwlxIds.substring(0, 9)+"...");
	            d.put("rwlx1", rwlxIds);
	        }else{
	        	d.put("rwlx", rwlxIds);//��������
	        	d.put("rwlx1", rwlxIds);
	        }
            //������Դ����
            String str1="";
    		if(ele.getSource()!=null){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
    		}
            //�����̶�����
            String str2="";
    		if(ele.getEmergency()!=null){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
    		}
    		d.put("rwly", str1);//������Դ
    		//�������ڵ���ʱ��ʾ
            Date curDate=new Date();
            if(-1 == DateUtil.compareDate(curDate, ele.getEndTime())){
            	SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
                String endTime= s.format(ele.getEndTime().getTime());
            	long to =DateUtil.getEndDatetime(endTime).getTime();
                long from = curDate.getTime();
                long lastday = (to - from) / (1000 * 60 * 60 * 24);
                long syts = lastday+1;
                if(lastday < 2 || lastday == 2){
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", "<font color='red'>"+str2+"(ʣ��"+syts+"��)</font>");//�����̶�
                }else{
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", str2+"(ʣ��"+syts+"��)");//�����̶�
                }
            }else{
            	d.put("syts", 0);//ʣ������
            	d.put("jjcd", str2);//�����̶�
            }
            d.put("yqwcsx", ele.getEndTime());//Ҫ�����ʱ��
            if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 13){
            	d.put("dbworkName", ele.getName().substring(0, 12)+"...");
            	d.put("dbworkName1", ele.getName());
            }else{
            	d.put("dbworkName", ele.getName());
            	d.put("dbworkName1", ele.getName());
            }
            d.put("workNote", ele.getWorkNote());
            d.put("dbcreated", ele.getCreateTime());
            if (null != ele.getCreateUser()) {
                u = this.userManager.getUser(ele.getCreateUser().getId());
                d.put("createby", u.getName());
            } else {
                d.put("createby", Constants.DEFAULTCREATER);
            }
            d.put("nextOper", ele.getNextOper());
            ///////�ն���Ҫ��һ��������//////
            String lastOper="";
            StringBuffer sql_step = new StringBuffer();
            sql_step.append(" from WorkLog where work.id =? order by czsj asc ");
    		List<WorkLog> list_step = this.dao.find(sql_step.toString(),ele.getId());
    		if(list_step!=null&&list_step.size()>0){
    			lastOper=list_step.get(list_step.size()-1).getCzrName();
    		}
            d.put("lastOper", lastOper);
            d.put("zxrName", ele.getZxrName());
            d.put("taskId", ele.getTaskId());
            if (log.isDebugEnabled()) {
                log.debug("getProcessId:" + ele.getProcessId());
            }
            d.put("taskType", ele.getCode());
            d.put("zxtime", ele.getZxtime());
            d.put("stateCode", ele.getState());
            d.put("state", WorkState.getNote(ele.getState()));
            d.put("start", ele.getStartTime());
            d.put("end", ele.getEndTime());
            //�Ƿ�����
            d.put("isYQ", DateUtil.compareDate(curDate, ele.getEndTime()));
            String nextActions = getJbpmActionJson(ele.getId(), ele.getTaskId(),
                ele.getProcessId(), ele.getNextActions());
            d.put("nextActions", nextActions);
            String operate = getJbpmAction(ele.getId(), ele.getTaskId(), ele.getProcessId(),
                ele.getNextActions());
            if(StringUtils.isNotBlank(xfdjId)){
            	String info = "<a onclick='info(this)' id='"+ele.getId()+"' class='b-link'>�鿴</a>";
				String flowchart = "<a onclick='flowChart(this)' id='"+ele.getProcessId()+"' class='b-link'>����ͼ</a>";
            	d.put("operate", info+flowchart);
            }else{
            	d.put("operate", operate);
            }
            rows.add(d);
        }
        
        List<Object> count = null;
        if (StringUtil.isNotBlank(tasktype)) {
        	count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ")", data.getCanshu());
        	if (count != null){
        		fy.setTotal(Long.parseLong(count.get(0).toString()));
        	}
        }
        fy.setRows(rows);
        return fy;
    }
	
	@Override
	public FyWebResult getRcbgDbrwList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId,  String page,
			String pageSize) throws Exception {
		QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        
        StringBuilder countSql = new StringBuilder();
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	sql.append("select distinct v from Work v, TBizTaskandtasktype tt where v.userId = :userId and v.id = tt.taskid ");
        	countSql.append("select distinct v.* from work_zf v, T_Biz_Taskandtasktype tt where v.userId_ = :userId and v.id_ = tt.taskid_ ");
        } else {
        	sql.append("select v from Work v where v.userId = :userId");
        }
        
        TSysUser cur = CtxUtil.getCurUser();
        data.put("userId", cur.getUsername());
        //��������
        if (StringUtil.isNotBlank(rwmc)) {
            sql.append(" and (v.name like :dbworkName )");
            countSql.append(" and (v.WORK_NAME_ like :dbworkName )");
            data.putLike("dbworkName", rwmc);
        }
        //������Դ
        if (StringUtil.isNotBlank(rwly)) {
            sql.append(" and v.source = :rwly");
            countSql.append(" and v.SOURCE_ = :rwly");
            data.put("rwly", rwly);
        }
        //�ɷ���
        if (StringUtil.isNotBlank(pfrId)) {
            sql.append(" and v.createUser.id = :pfr");
            countSql.append(" and v.CREATEBY_ = :pfr");
            data.put("pfr", pfrId);
        }
        //����״̬
        if (StringUtil.isNotBlank(rwzt)) {
            sql.append(" and v.state = :state");
            countSql.append(" and v.STATE_ = :state");
            data.put("state", rwzt);
        }
        
        //ִ����������
        if (StringUtil.isNotBlank(zfdxType)) {
            sql.append(" and v.zfdxType = :zfdxlx");
            countSql.append(" and v.ZFDX_TYPE_ = :zfdxlx");
            data.put("zfdxlx", zfdxType);
        }
        
        //�ɷ�ʱ��
        if(StringUtils.isNotBlank(pfStarttime)){
    		sql.append(" and v.startTime >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.START_TIME_ >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("pfStarttime", pfStarttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(pfEndtime)){
			sql.append(" and v.startTime <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.START_TIME_ <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("pfEndtime", pfEndtime+" 23:59:59");
		}
		
		//Ҫ�����ʱ��
        if(StringUtils.isNotBlank(gdStarttime)){
			sql.append(" and v.endTime >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.END_TIME_ >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("gdStarttime", gdStarttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(gdEndtime)){
			sql.append(" and v.endTime <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.END_TIME_ <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("gdEndtime", gdEndtime+" 23:59:59");
		}
        
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	String[] taskType = new String[tasktype.split(",").length];
            for (int i = 0; i < tasktype.split(",").length; i++) {
            	taskType[i] = tasktype.split(",")[i];
    		}
            sql.append(" and tt.tasktypeid in (:taskType) and tt.isActive = 'Y' ");
            countSql.append(" and tt.tasktypeid_ in (:taskType) and tt.isActive_ = 'Y' ");
            data.put("taskType", taskType);
        }
        //�ŷõǼǱ����������ѯ
        if(StringUtils.isNotBlank(xfdjId)){
        	sql.append(" and v.xfdjbId = :xfdjId");
            countSql.append(" and v.xfdjbId_ = :xfdjId");
            data.put("xfdjId", xfdjId);
        }
        
        sql.append(" order by v.orderby desc, v.endTime asc, v.updateTime desc");
        countSql.append(" order by v.orderby_ desc, v.END_TIME_ asc, v.UPDATED_ desc");
        
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<VdbWork> vs = pos.getRe();
        NullControllHashMap d = null;
        String type = null;
        TSysUser u = null;
        //WorkType worktype = null;
        for (VdbWork ele : vs) {
            d = new NullControllHashMap();
            //��������
			List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(ele.getId());
            d.put("id", ele.getId());
            String rwlxIds = "";
			for (int i = 0; i < rwlxlistMap.size(); i++) {
				if (i < rwlxlistMap.size() - 1) {
					rwlxIds += TaskTypeCode.getNote(rwlxlistMap.get(i).get("id")) + ",";
				} else {
					rwlxIds += TaskTypeCode.getNote(rwlxlistMap.get(i).get("id"));
				}
			}
			if(StringUtils.isNotBlank(rwlxIds) && rwlxIds.length() > 10){
	            d.put("rwlx", rwlxIds.substring(0, 9)+"...");
	            d.put("rwlx1", rwlxIds);
	        }else{
	        	d.put("rwlx", rwlxIds);//��������
	        	d.put("rwlx1", rwlxIds);
	        }
            //������Դ����
            String str1="";
    		if(ele.getSource()!=null){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
    		}
            //�����̶�����
            String str2="";
    		if(ele.getEmergency()!=null){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
    		}
    		d.put("rwly", str1);//������Դ
    		//�������ڵ���ʱ��ʾ
            Date curDate=new Date();
            if(-1 == DateUtil.compareDate(curDate, ele.getEndTime())){
            	SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
                String endTime= s.format(ele.getEndTime().getTime());
            	long to =DateUtil.getEndDatetime(endTime).getTime();
                long from = curDate.getTime();
                long lastday = (to - from) / (1000 * 60 * 60 * 24);
                long syts = lastday+1;
                if(lastday < 2 || lastday == 2){
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", "<font color='red'>"+str2+"(ʣ��"+syts+"��)</font>");//�����̶�
                }else{
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", str2+"(ʣ��"+syts+"��)");//�����̶�
                }
            }else{
            	d.put("syts", 0);//ʣ������
            	d.put("jjcd", str2);//�����̶�
            }
            d.put("yqwcsx", ele.getEndTime());//Ҫ�����ʱ��
            if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 13){
            	d.put("dbworkName", ele.getName().substring(0, 12)+"...");
            	d.put("dbworkName1", ele.getName());
            }else{
            	d.put("dbworkName", ele.getName());
            	d.put("dbworkName1", ele.getName());
            }
            d.put("workNote", ele.getWorkNote());
            d.put("dbcreated", ele.getCreateTime());
            if (null != ele.getCreateUser()) {
                u = this.userManager.getUser(ele.getCreateUser().getId());
                d.put("createby", u.getName());
            } else {
                d.put("createby", Constants.DEFAULTCREATER);
            }
            d.put("nextOper", ele.getNextOper());
            ///////�ն���Ҫ��һ��������//////
            String lastOper="";
            StringBuffer sql_step = new StringBuffer();
            sql_step.append(" from WorkLog where work.id =? order by czsj asc ");
    		List<WorkLog> list_step = this.dao.find(sql_step.toString(),ele.getId());
    		if(list_step!=null&&list_step.size()>0){
    			lastOper=list_step.get(list_step.size()-1).getCzrName();
    		}
            d.put("lastOper", lastOper);
            d.put("zxrName", ele.getZxrName());
            d.put("taskId", ele.getTaskId());
            if (log.isDebugEnabled()) {
                log.debug("getProcessId:" + ele.getProcessId());
            }
            d.put("taskType", ele.getCode());
            d.put("zxtime", ele.getZxtime());
            d.put("stateCode", ele.getState());
            d.put("state", WorkState.getNote(ele.getState()));
            d.put("start", ele.getStartTime());
            d.put("end", ele.getEndTime());
            //�Ƿ�����
            d.put("isYQ", DateUtil.compareDate(curDate, ele.getEndTime()));
            String nextActions = getJbpmActionJson(ele.getId(), ele.getTaskId(),
                ele.getProcessId(), ele.getNextActions());
            d.put("nextActions", nextActions);
            String operate = getJbpmAction(ele.getId(), ele.getTaskId(), ele.getProcessId(),
                ele.getNextActions());
            if(StringUtils.isNotBlank(xfdjId)){
            	String info = "<a onclick='info(this)' id='"+ele.getId()+"' class='b-link'>�鿴</a>";
				String flowchart = "<a onclick='flowChart(this)' id='"+ele.getProcessId()+"' class='b-link'>����ͼ</a>";
            	d.put("operate", info+flowchart);
            }else{
            	d.put("operate", operate);
            }
            rows.add(d);
        }
        
        List<Object> count = null;
        if (StringUtil.isNotBlank(tasktype)) {
        	count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ")", data.getCanshu());
        	if (count != null){
        		fy.setTotal(Long.parseLong(count.get(0).toString()));
        	}
        }
        fy.setRows(rows);
        return fy;
    }
	
	@Override
	public FyWebResult getRwslList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String page, String pageSize) throws Exception {
		QueryCondition data = new QueryCondition();
        data.setPageSize(pageSize);
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	sql.append("select distinct from Work v, TBizTaskandtasktype tt where v.id = tt.taskid ");
        	countSql.append("select distinct from Work v , TBizTaskandtasktype tt where v.id = tt.taskid ");
        } else {
        	sql.append("select from Work v where 1=1");
        }
        //��������
        if (StringUtil.isNotBlank(rwmc)) {
            sql.append(" and (v.name like :dbworkName )");
            countSql.append(" and (v.name like :dbworkName )");
            data.putLike("dbworkName", rwmc);
        }
        //������Դ
        if (StringUtil.isNotBlank(rwly)) {
            sql.append(" and v.source = :rwly");
            countSql.append(" and v.source = :rwly");
            data.put("rwly", rwly);
        }
        //����״̬
        if (StringUtil.isNotBlank(rwzt)) {
            sql.append(" and v.state = :state");
            countSql.append(" and v.state = :state");
            data.put("state", rwzt);
        }
        //�ɷ�ʱ��
        if(StringUtils.isNotBlank(pfStarttime)){
    		sql.append(" and v.startTime >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.startTime >= TO_DATE(:pfStarttime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("pfStarttime", pfStarttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(pfEndtime)){
			sql.append(" and v.startTime <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.startTime <= TO_DATE(:pfEndtime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("pfEndtime", pfEndtime+" 23:59:59");
		}
		
		//Ҫ�����ʱ��
        if(StringUtils.isNotBlank(gdStarttime)){
			sql.append(" and v.endTime >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.endTime >= TO_DATE(:gdStarttime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("gdStarttime", gdStarttime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(gdEndtime)){
			sql.append(" and v.endTime <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
			countSql.append(" and v.endTime <= TO_DATE(:gdEndtime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("gdEndtime", gdEndtime+" 23:59:59");
		}
        
        //��������
        if (StringUtil.isNotBlank(tasktype)) {
        	String[] taskType = new String[tasktype.split(",").length];
            for (int i = 0; i < tasktype.split(",").length; i++) {
            	taskType[i] = tasktype.split(",")[i];
    		}
            sql.append(" and tt.tasktypeid in (:taskType) and tt.isActive = 'Y' ");
            countSql.append(" and tt.tasktypeid_ in (:taskType) and tt.isActive_ = 'Y' ");
            data.put("taskType", taskType);
        }
        sql.append(" order by v.orderby desc, v.endTime asc, v.updateTime desc");
        countSql.append(" order by v.orderby desc, v.endTime asc, v.updateTime desc");
        
        sql.append(" and createUser=:createUser");
        data.put("createUser", CtxUtil.getCurUser());
        sql.append(" and startTime is null ");//����ʼʱ��Ϊ�գ���Ϊ�����ɵ�����
//        sql.append(" and taskstarted is null");
        sql.append(" order by createTime DESC ");
        
        FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
        FyWebResult fy = new FyWebResult(pos);
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        List<Work> vs = pos.getRe();
        NullControllHashMap d = null;
        String type = null;
        TSysUser u = null;
        //WorkType worktype = null;
        for (Work ele : vs) {
            d = new NullControllHashMap();
            //��������
			List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(ele.getId());
            d.put("id", ele.getId());
            String rwlxIds = "";
			for (int i = 0; i < rwlxlistMap.size(); i++) {
				if (i < rwlxlistMap.size() - 1) {
					rwlxIds += TaskTypeCode.getNote(rwlxlistMap.get(i).get("id")) + ",";
				} else {
					rwlxIds += TaskTypeCode.getNote(rwlxlistMap.get(i).get("id"));
				}
			}
			if(StringUtils.isNotBlank(rwlxIds) && rwlxIds.length() > 10){
	            d.put("rwlx", rwlxIds.substring(0, 9)+"...");
	            d.put("rwlx1", rwlxIds);
	        }else{
	        	d.put("rwlx", rwlxIds);//��������
	        	d.put("rwlx1", rwlxIds);
	        }
            //������Դ����
            String str1="";
    		if(ele.getSource()!=null){
    			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),ele.getSource());
    		}
            //�����̶�����
            String str2="";
    		if(ele.getEmergency()!=null){
    			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),ele.getEmergency());
    		}
    		d.put("rwly", str1);//������Դ
    		//�������ڵ���ʱ��ʾ
            Date curDate=new Date();
            if(-1 == DateUtil.compareDate(curDate, ele.getEndTime())){
            	SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
                String endTime= s.format(ele.getEndTime().getTime());
            	long to =DateUtil.getEndDatetime(endTime).getTime();
                long from = curDate.getTime();
                long lastday = (to - from) / (1000 * 60 * 60 * 24);
                long syts = lastday+1;
                if(lastday < 2 || lastday == 2){
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", "<font color='red'>"+str2+"(ʣ��"+syts+"��)</font>");//�����̶�
                }else{
                	d.put("syts", syts);//ʣ������
                	d.put("jjcd", str2+"(ʣ��"+syts+"��)");//�����̶�
                }
            }else{
            	d.put("syts", 0);//ʣ������
            	d.put("jjcd", str2);//�����̶�
            }
            d.put("yqwcsx", ele.getEndTime());//Ҫ�����ʱ��
            if(StringUtils.isNotBlank(ele.getName()) && ele.getName().length() > 13){
            	d.put("dbworkName", ele.getName().substring(0, 12)+"...");
            	d.put("dbworkName1", ele.getName());
            }else{
            	d.put("dbworkName", ele.getName());
            	d.put("dbworkName1", ele.getName());
            }
            d.put("workNote", ele.getWorkNote());
            d.put("dbcreated", ele.getCreateTime());
            if (null != ele.getCreateUser()) {
                u = this.userManager.getUser(ele.getCreateUser().getId());
                d.put("createby", u.getName());
            } else {
                d.put("createby", Constants.DEFAULTCREATER);
            }
            d.put("nextOper", ele.getNextOper());
            ///////�ն���Ҫ��һ��������//////
            String lastOper="";
            StringBuffer sql_step = new StringBuffer();
            sql_step.append(" from WorkLog where work.id =? order by czsj asc ");
    		List<WorkLog> list_step = this.dao.find(sql_step.toString(),ele.getId());
    		if(list_step!=null&&list_step.size()>0){
    			lastOper=list_step.get(list_step.size()-1).getCzrName();
    		}
            d.put("lastOper", lastOper);
            d.put("zxrName", ele.getZxrName());
            d.put("taskId", ele.getTaskId());
            if (log.isDebugEnabled()) {
                log.debug("getProcessId:" + ele.getProcessId());
            }
            d.put("taskType", ele.getCode());
            d.put("zxtime", ele.getZxtime());
            d.put("stateCode", ele.getState());
            d.put("state", WorkState.getNote(ele.getState()));
            d.put("start", ele.getStartTime());
            d.put("end", ele.getEndTime());
            //�Ƿ�����
            d.put("isYQ", DateUtil.compareDate(curDate, ele.getEndTime()));
            String nextActions = getJbpmActionJson(ele.getId(), ele.getTaskId(),
                ele.getProcessId(), ele.getNextActions());
            d.put("nextActions", nextActions);
            String operate = getJbpmAction(ele.getId(), ele.getTaskId(), ele.getProcessId(),
                ele.getNextActions());
            if(StringUtils.isNotBlank(xfdjId)){
            	String info = "<a onclick='info(this)' id='"+ele.getId()+"' class='b-link'>�鿴</a>";
				String flowchart = "<a onclick='flowChart(this)' id='"+ele.getProcessId()+"' class='b-link'>����ͼ</a>";
            	d.put("operate", info+flowchart);
            }else{
            	d.put("operate", operate);
            }
            rows.add(d);
        }
        List<Object> count = null;
        if (StringUtil.isNotBlank(tasktype)) {
        	count = this.dao.findBySql("select count(1) from (" + countSql.toString() + ")", data.getCanshu());
        	if (count != null){
        		fy.setTotal(Long.parseLong(count.get(0).toString()));
        	}
        }
        fy.setRows(rows);
        return fy;
	}
	
	/**
	 * ��ѯ����������Ԥ�ύ���صĽڵ㷽��
	 * 
	 * @return �ڵ��б�
	 */
	public JSONArray preSubmitNodePubQuery() {
		JSONArray re = new JSONArray();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysOrg where 1=1 ");
		String areaId = CtxUtil.getAreaId();
		if (!CtxUtil.getCurUser().getIssys().equals("Y") && StringUtils.isNotBlank(areaId)) {
			sql.append(" and area.id = :area");
			data.put("area", areaId);
		}
		// ����ǹ���Ա�����ǳ�������Ա ����������
		if (CtxUtil.getCurUser().getIssys().equals("Y") && !CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")) {
			sql.append(" and area.id = :area");
			data.put("area", areaId);
		}
		sql.append(" order by orderby ");
		List<TSysOrg> pos = dao.find(sql.toString(), data);
		JSONObject jsonObj = null;
		for (TSysOrg ele : pos) {
			jsonObj = new JSONObject();
			re.put(jsonObj);
		}
		return re;
	}

}