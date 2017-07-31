package com.hnjz.app.work.zx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.IndexManager;
import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.enums.PublicColumnEnum;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataIllegaltype;
import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.po.TDataRegion;
import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.work.beans.ResultBean;
import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.jcd.JcdManagerImpl;
import com.hnjz.app.work.kcbl.KcblManager;
import com.hnjz.app.work.po.TBizAutomoniter;
import com.hnjz.app.work.po.TBizBlmbcs;
import com.hnjz.app.work.po.TBizChecklist;
import com.hnjz.app.work.po.TBizMoreCheck;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.TBizTaskandtasktype;
import com.hnjz.app.work.po.TBizTaskillegaltype;
import com.hnjz.app.work.po.TBizTaskinverecord;
import com.hnjz.app.work.po.TBizTaskrecordans;
import com.hnjz.app.work.po.TBizTasksurveyrecord;
import com.hnjz.app.work.po.TBizTaskuser;
import com.hnjz.app.work.po.TBizXfbjd;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.po.WorkLog;
import com.hnjz.app.work.xfdj.XfdjManager;
import com.hnjz.app.work.zxzz.ZxzzManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.DateDifferenceUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.FileZipUtil;
import com.hnjz.common.util.PoiUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.configchecktemplate.ConfigManager;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserOrg;
import com.hnjz.sys.user.UserManager;

@Service("commWorkManagerImpl")
public class CommWorkManagerImpl extends ManagerImpl implements CommWorkManager {

	/** ���� */
	@Autowired
	private WorkDao workDao;

	@Autowired
	private UserManager userManager;

	@Autowired
	private LawobjManager lawobjManager;

	@Autowired
	private CommonManager commonManager;

	@Autowired
	private ConfigManager configManager;

	@Autowired
	private ZxWorkManager zxWorkManager;

	@Autowired
	private KcblManager kcblManager;
	
	@Autowired
	private JcdManagerImpl jcdManagerImpl;
	
	@Autowired
    private ZxzzManager    zxzzManager;
	
	@Autowired
    private IndexManager     indexManager;
	
	@Autowired
    private XfdjManager xfdjManager;

	/**
	 * 
	 * �������ܣ� ��ִ������ ���������
	 * 
	 * ����ֵ��
	 */
	@Override
	public void saveDelZFDX(String applyId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from TBizTaskandlawobj where taskid=? ");
		this.dao.removeFindObjs(sql.toString(), applyId);
	}

	/**
	 * 
	 * �������ܣ� ���������� ���������
	 * 
	 * ����ֵ��
	 */
	@Override
	public void saveDelRWLX(String applyId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from TBizTaskandtasktype where taskid=? ");
		this.dao.removeFindObjs(sql.toString(), applyId);
	}
	
	/**
	 * 
	 * �������ܣ� ��ִ���������� ���������
	 * 
	 * ����ֵ��
	 */
	public void saveDelZFDXLX(String applyId){
		Work work = workDao.get(applyId);
		work.setZfdxType("");
		this.workDao.save(work);
	}

	/**
	 * 
	 * �������ܣ� �����ҳ���Լ������� ���������
	 * 
	 * ����ֵ��
	 */
	@Override
	public void saveDelBL(String applyId) {
		// 1�����������еļ���˼�¼��
		String taskUser = "RCJC_JCR,RCJC_JLR,NDHC_JCR,NDHC_JLR,HDC_JCR,HDC_JLR,XFTS_JCR,XFTS_JLR,PWXKZJC_JCR,PWXKZJC_JLR,ZXXD_JCR,ZXXD_JLR,WFAJDC_JLR,XQZL_JCR,XQZL_JLR,GZJC_JCR,GZJC_JLR,ZDJK_JCR,ZDJK_JLR,WXFW_JCR,WXFW_JLR,WXHXP_JCR,WXHXP_JLR,FSAQ_JCR,FSAQ_JLR,WRSGXCDC_JCR,WRSGXCDC_JLR";
		String[] taskUserArr = taskUser.split(",");// Enumname
		String[] taskUserCodeArr = new String[taskUserArr.length];// code
		for (int i = 0; i < taskUserArr.length; i++) {
			taskUserCodeArr[i] = TaskUserEnum.getCodeByEnum(taskUserArr[i]);
		}
		String sql1 = " from TBizTaskuser where taskid=? and type in("
				+ StringUtil.convertSqlInArray(taskUserCodeArr) + ")";
		this.dao.removeFindObjs(sql1.toString(), applyId);
		// 2�����������и����������͵��ֶ���Ϣ
		//StringBuilder sql2 = new StringBuilder();
		//sql2.append(" from TBizTaskandtasktype where taskid=? ");
		//this.dao.removeFindObjs(sql2.toString(), applyId);
		// 3�����������еĸ�����Ϣ
		commonManager.removeAllBlFileByPid(applyId);
		
		//�h�����������еĶ�μ���¼
		String sql2 = " from TBizMoreCheck where work.id= ? ";
		this.dao.removeFindObjs(sql2.toString(), applyId);

		// 4��ɾ��������
		List<Work> subs = this.dao.find("from Work where pid = ?", applyId);
		Work sub = new Work();
		if (subs.size() > 0) {
			for (int i = 0; i < subs.size(); i++) {
				sub = subs.get(i);
				sub.setIsActive(YnEnum.N.getCode());
				this.dao.save(sub);
			}
		}

	}

	@Override
	public void saveZfdxType(String applyId, String zfdxType)
			throws AppException {
		Work work = workDao.get(applyId);
		work.setZfdxType(zfdxType);
		this.workDao.save(work);
	}

	@Override
	public void saveZfdxTypeOnChange(String applyId, String zfdxType, String rwlxIds)
			throws AppException {
		// �������
		// 1��ִ������
		this.saveDelZFDX(applyId);
		// 2����������
		if(!"13".equals(rwlxIds)){
			this.saveDelRWLX(applyId);
		}
		// 3������ҳ���Լ�������
		this.saveDelBL(applyId);

		// �����µ�
		this.saveZfdxType(applyId, zfdxType);
	}

	@Override
	public void saveTaskTypeMultiple(String applyId, String checkedNodesIds,
			TSysUser curUser,Map<String,String> jcjlMap,Map<String,String> rcbgDescMap) throws AppException {
		/**
		 * 2015-5-29 �޸ģ���������ֻ���Ӻ͸��£�Ҳ���ǲ�ʹ��ʱ��״̬Ϊ�����ã����ָ�����ʱ���������İ���һ���ɻָ���
		 */
		// TSysUser curUser = CtxUtil.getCurUser();
		Date cur = new Date();

		if (StringUtils.isNotBlank(checkedNodesIds)) {
			// 1���Ȱ����еģ���Ϊ������
			StringBuilder sql_old = new StringBuilder();
			sql_old.append(" from TBizTaskandtasktype where taskid=? ");
			List<TBizTaskandtasktype> list_old = dao.find(sql_old.toString(),
					applyId);
			for (int i = 0; i < list_old.size(); i++) {
				TBizTaskandtasktype tBizTaskandtasktype = list_old.get(i);
				tBizTaskandtasktype.setIsActive("N");
				if(jcjlMap!=null && jcjlMap.size()>0){
					tBizTaskandtasktype.setJcjl(jcjlMap.get(applyId+"-"+tBizTaskandtasktype.getTasktypeid()));
				}
				tBizTaskandtasktype.setDesc(rcbgDescMap.get(applyId+"-"+tBizTaskandtasktype.getTasktypeid()));
				dao.save(tBizTaskandtasktype);
			}
			// 2����������ѡ���ids�����ѡ�������������list_old��������¸�����¼Ϊ���ã���������һ����¼��״̬Ϊ����
			String[] ids = checkedNodesIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				String tasktypeid = ids[i];
				Boolean isHas = false;// �Ƿ��оɵ�
				for (int j = 0; j < list_old.size(); j++) {
					if (tasktypeid.equals(list_old.get(j).getTasktypeid())) {
						TBizTaskandtasktype tBizTaskandtasktype = list_old
								.get(j);
						tBizTaskandtasktype.setIsActive("Y");
						//tBizTaskandtasktype.setJcjl(jcjlMap.get(applyId+"-"+tasktypeid));
						tBizTaskandtasktype.setDesc(rcbgDescMap.get(applyId+"-"+tasktypeid));
						dao.save(tBizTaskandtasktype);
						isHas = true;
					}
				}
				if (!isHas) {// ����һ����¼��״̬Ϊ����
					TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
					tBizTaskandtasktype.setTaskid(applyId);
					tBizTaskandtasktype.setTasktypeid(tasktypeid);
					tBizTaskandtasktype.setIsActive("Y");
					tBizTaskandtasktype.setJcjl(jcjlMap.get(applyId+"-"+tasktypeid));
					tBizTaskandtasktype.setDesc(rcbgDescMap.get(applyId+"-"+tasktypeid));
					tBizTaskandtasktype.setCreateby(curUser);
					tBizTaskandtasktype.setCreated(cur);
					tBizTaskandtasktype.setUpdateby(curUser);
					tBizTaskandtasktype.setUpdated(cur);
					this.dao.save(tBizTaskandtasktype);
				}
			}
		}

		/*
		 * if(StringUtils.isNotBlank(checkedNodesIds)){ String[] ids =
		 * checkedNodesIds.split(","); //1�������֮ǰ������ StringBuilder sql1 = new
		 * StringBuilder();
		 * sql1.append(" from TBizTaskandtasktype where taskid=? ");
		 * this.dao.removeFindObjs(sql1.toString(),applyId); //2���ٱ��������� for(int
		 * i=0;i<ids.length;i++){ TBizTaskandtasktype tBizTaskandtasktype=new
		 * TBizTaskandtasktype(); tBizTaskandtasktype.setTaskid(applyId);
		 * tBizTaskandtasktype.setTasktypeid(ids[i].toString());
		 * 
		 * tBizTaskandtasktype.setIsActive("Y");
		 * tBizTaskandtasktype.setCreateby(curUser);
		 * tBizTaskandtasktype.setCreated(cur);
		 * tBizTaskandtasktype.setUpdateby(curUser);
		 * tBizTaskandtasktype.setUpdated(cur);
		 * this.dao.save(tBizTaskandtasktype); } }
		 */
	}

	@Override
	public void saveTaskTypeMultiple(String applyId, String checkedNodesIds,
			TSysUser curUser) throws AppException {
		Map<String,String> jcjlMap = new HashMap<String,String>();
		Map<String,String> rcbgDescMap = new HashMap<String,String>();
		this.saveTaskTypeMultiple(applyId, checkedNodesIds, curUser, jcjlMap,rcbgDescMap);
	}

	@Override
	public List<Map<String, String>> getTaskTypeByTaskId(String applyId) {
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		StringBuilder sql = new StringBuilder();
		sql.append("select t2.code_,t2.name_ from T_DATA_TASKTYPE t2 where t2.code_ in (select t1.tasktypeid_ from T_BIZ_TASKANDTASKTYPE t1 where t1.taskid_='" + applyId + "' and t1.isactive_='Y') ");
		List<Object[]> list = dao.findBySql(sql.toString());
		Map<String, String> dataObject = null;
		for (Object[] ele : list) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele[0].toString());//id����code
			dataObject.put("name", ele[1].toString());
			rows.add(dataObject);
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> zfdxTableData(String applyId) {
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		if(StringUtils.isBlank(applyId)){
			sql.append("from TBizTaskandlawobj");
		}else{
			sql.append("from TBizTaskandlawobj where taskid=:applyId ");
			data.put("applyId", applyId);
		}
		List<TBizTaskandlawobj> list = dao.find(sql.toString(), data);
		Map<String, String> dataObject = null;
		for (TBizTaskandlawobj ele : list) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("lawobjid", ele.getLawobjid());
			dataObject.put("lawobjname", StringUtils.isNotBlank(ele.getLawobjname())?ele.getLawobjname():"");
			dataObject.put("address", StringUtils.isNotBlank(ele.getAddress())?ele.getAddress():"");
			dataObject.put("manager", StringUtils.isNotBlank(ele.getManager())?ele.getManager():"");
			dataObject.put("managermobile", StringUtils.isNotBlank(ele.getManagermobile())?ele.getManagermobile():"");
			dataObject.put("lawobjtype", StringUtils.isNotBlank(ele.getLawobjtype())?ele.getLawobjtype():"");
			dataObject.put("bjcr", StringUtils.isNotBlank(ele.getBjcr())?ele.getBjcr():"");
			dataObject.put("zw", StringUtils.isNotBlank(ele.getZw())?ele.getZw():"");
			dataObject.put("lxdh", StringUtils.isNotBlank(ele.getLxdh())?ele.getLxdh():"");
			dataObject.put("zwtitle", StringUtils.isNotBlank(ele.getZwtitle())?ele.getZwtitle():"����������");
			dataObject.put("xqyzDwmc", StringUtils.isNotBlank(ele.getXqyzDwmc())?ele.getXqyzDwmc():"");
			if (StringUtil.isNotBlank(ele.getRegionid())) {
				TDataRegion tDataRegion = (TDataRegion) dao.get(
						TDataRegion.class, ele.getRegionid());
				dataObject.put("regionid", null == tDataRegion ? ""
						: tDataRegion.getName());
				dataObject.put("region", null == tDataRegion ? ""
						: tDataRegion.getId());
			} else {
				dataObject.put("regionid", "");
				dataObject.put("region", "");
			}
			StringBuilder operate = new StringBuilder();
			operate.append(" <a onclick='zfdxInfo(this)' id='"
					+ ele.getLawobjid() + "' class="+"b-link"+">�鿴</a>  ");
			dataObject.put("operate", operate.toString());

			rows.add(dataObject);
		}

		return rows;
	}

	@Override
	public List<Map<String, String>> ryghTableData(String applyId) {
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();

		// ˼·����Ϊ�����ֲ�ѯ��1�������ˣ�2��Э���ˣ�Э���˹���TSysUser��orderby������������˷ŵ�һ���ϲ���
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TBizTaskuser where taskid=:applyId and type='05' ");
		data.put("applyId", applyId);
		List<TBizTaskuser> list = dao.find(sql.toString(), data);
		StringBuilder sql2 = new StringBuilder();
		sql2.append("select t.id_,t.username_,t.userid_,t.type_,s.orderby_ from T_BIZ_TASKUSER t left join T_SYS_USER s on t.userid_=s.id_ where t.taskid_=? and t.type_='06'  order by s.orderby_ ");
		List<Object[]> list2 = dao.findBySql(sql2.toString(), applyId);
		// ������
		Map<String, String> zbrMap = new HashMap<String, String>();
		zbrMap.put("id", list.get(0).getId());
		zbrMap.put("name", list.get(0).getUsername());
		zbrMap.put("userid", list.get(0).getUserid());
		zbrMap.put("type", "������");
		TSysUser user = userManager.getUser(list.get(0).getUserid());
		zbrMap.put("lawnumber", user.getLawnumber());
		zbrMap.put("workmobile", user.getWorkmobile());
		zbrMap.put("personmobile", user.getPersonmobile());
		TSysOrg org = userManager.getOrgbyUser(list.get(0).getUserid());
		if (null != org) {
			zbrMap.put("org", org.getName());
		}
		StringBuilder operate = new StringBuilder();
		zbrMap.put("operate", operate.toString());
		rows.add(zbrMap);// ���������˵���һ��λ��
		// Э����
		Map<String, String> dataObject = null;
		for (Object[] ele : list2) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele[0].toString());
			dataObject.put("name", ele[1].toString());
			dataObject.put("userid", ele[2].toString());
			dataObject.put("type", "Э����");
			TSysUser userXbr = userManager.getUser(ele[2].toString());
			dataObject.put("lawnumber", userXbr.getLawnumber());
			dataObject.put("workmobile", userXbr.getWorkmobile());
			dataObject.put("personmobile", userXbr.getPersonmobile());
			TSysOrg orgXbr = userManager.getOrgbyUser(ele[2].toString());
			if (null != orgXbr) {
				dataObject.put("org", orgXbr.getName());
			}
			StringBuilder operateXbr = new StringBuilder();
			if ("06".equals(ele[3].toString())) {
				operateXbr.append(" <a onclick='del(this)' class='b-link' id='"+ ele[0].toString()+ "' title='���ɾ��Э���˽�����հ��������б���ĸ���Ա��Ϣ�Լ��������ļ���'>ɾ��</a>  ");
			}
			dataObject.put("operate", operateXbr.toString());
			rows.add(dataObject);
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> xbryTableData(String applyId) {
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TBizTaskuser where taskid=:applyId and type in ('06') ");
		data.put("applyId", applyId);
		List<TBizTaskuser> list = dao.find(sql.toString(), data);
		Map<String, String> dataObject = null;
		for (TBizTaskuser ele : list) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("name", ele.getUsername());
			dataObject.put("userid", ele.getUserid());
			TSysUser user = userManager.getUser(ele.getUserid());
			dataObject.put("lawnumber", user.getLawnumber());
			dataObject.put("workmobile", user.getWorkmobile());
			TSysOrg org = userManager.getOrgbyUser(ele.getUserid());
			if (null != org) {
				dataObject.put("org", org.getName());
			}
			rows.add(dataObject);
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> ryData(String applyId) {
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TBizTaskuser where taskid=:applyId and type not in ('05') ");
		data.put("applyId", applyId);
		List<TBizTaskuser> list = dao.find(sql.toString(), data);
		Map<String, String> dataObject = null;
		for (TBizTaskuser ele : list) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("name", ele.getUsername());
			dataObject.put("userid", ele.getUserid());
			dataObject.put("type", ele.getType());
			TSysUser user = userManager.getUser(ele.getUserid());
			dataObject.put("lawnumber", user.getLawnumber());
			dataObject.put("workmobile", user.getWorkmobile());
			TSysOrg org = userManager.getOrgbyUser(ele.getUserid());
			if (null != org) {
				dataObject.put("org", org.getName());
			}
			rows.add(dataObject);
		}
		return rows;
	}
	public List<Map<String, String>> ryghTableData_xbry(String applyId) {
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TBizTaskuser where taskid=:applyId and type in ('06') ");
		data.put("applyId", applyId);
		List<TBizTaskuser> list = dao.find(sql.toString(), data);
		Map<String, String> dataObject = null;
		for (TBizTaskuser ele : list) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("name", ele.getUsername());
			dataObject.put("userid", ele.getUserid());
			String type = "";
			if ("05".equals(ele.getType())) {
				type = "������";
			} else if ("06".equals(ele.getType())) {
				type = "Э����";
			}
			dataObject.put("type", type);
			TSysUser user = userManager.getUser(ele.getUserid());
			dataObject.put("lawnumber", user.getLawnumber());
			dataObject.put("workmobile", user.getWorkmobile());
			TSysOrg org = userManager.getOrgbyUser(ele.getUserid());
			if (null != org) {
				dataObject.put("org", org.getName());
			}
			StringBuilder operate = new StringBuilder();
			if ("06".equals(ele.getType())) {
				operate.append(" <a onclick='del(this)' id='" + ele.getId() + "' >ɾ��</a>  ");
			}
			dataObject.put("operate", operate.toString());
			rows.add(dataObject);
		}
		return rows;
	}

	@Override
	public void saveDelrygh(String applyId, String id) throws AppException {
		TBizTaskuser tBizTaskuser = (TBizTaskuser) this.dao.get(TBizTaskuser.class, id);
		String delUserId = tBizTaskuser.getUserid();
		this.dao.remove(TBizTaskuser.class, id);
		// ���ɾ������Ա�����õ��ˣ���Ѱ��������б���ļ���˼�¼��Ҳɾ����
		// ��ѯ���м���˼�¼�� TaskUserEnum

		String taskUser = "RCJC_JCR,RCJC_JLR,NDHC_JCR,NDHC_JLR,HDC_JCR,HDC_JLR,XFTS_JCR,XFTS_JLR,PWXKZJC_JCR,PWXKZJC_JLR,ZXXD_JCR,ZXXD_JLR,WFAJDC_JLR,XQZL_JCR,XQZL_JLR,GZJC_JCR,GZJC_JLR,ZDJK_JCR,ZDJK_JLR,WXFW_JCR,WXFW_JLR,WXHXP_JCR,WXHXP_JLR,FSAQ_JCR,FSAQ_JLR,WRSGXCDC_JCR,WRSGXCDC_JLR";
		String[] taskUserArr = taskUser.split(",");// Enumname
		String[] taskUserCodeArr = new String[taskUserArr.length];// code
		for (int i = 0; i < taskUserArr.length; i++) {
			taskUserCodeArr[i] = TaskUserEnum.getCodeByEnum(taskUserArr[i]);
		}
		String sql1 = " from TBizTaskuser where taskid=? and type in("
				+ StringUtil.convertSqlInArray(taskUserCodeArr) + ")";
		List<TBizTaskuser> taskuserlist = this.dao.find(sql1, applyId);
		if (taskuserlist != null && taskuserlist.size() > 0) {
			for (int i = 0; i < taskuserlist.size(); i++) {
				if (delUserId.equals(taskuserlist.get(i).getUserid())) {
					this.dao.remove(taskuserlist.get(i));
				}
			}
		}

		// ɾ������¼�ļ�����챨���ļ�
		String enumtypeNames = "RCJCJCJL,RCJCJCBG,NDHCJCJL,NDHCJCBG,HDCJCJL,HDCJCBG,XFTSJCJL,XFTSJCBG,PWXKZJCJCJL,PWXKZJCJCBG,XQZLJCJL,XQZLJCBG,GZJCJCJL,GZJCJCBG,ZDJKJCJL,ZDJKJCBG,WXFWJCJL,WXFWJCBG,WXHXPJCJL,WXHXPJCBG,FSAQJCJL,FSAQJCBG,WRSGXCDCJCJL,WRSGXCDCJCBG";
		commonManager.removeBlFileByPidAndEnumtypeNames(applyId, enumtypeNames);
	}

	@Override
	public List<Combobox> ryghCombo(String applyId) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		List<Map<String, String>> list = this.ryghTableData(applyId);
		for (Map<String, String> dic : list) {
			Combobox combobox = new Combobox(dic.get("userid"), dic.get("name"));
			listResult.add(combobox);
		}
		return listResult;
	}

	@Override
	public void saveRy(String applyId, String ryid, String type)
			throws AppException {
		// �������ʱ����������Ա��Ϣ��������Ա��Ϣ T_BIZ_TASKUSER��
		TBizTaskuser taskuser = new TBizTaskuser();
		taskuser.setTaskid(applyId);
		taskuser.setUserid(ryid);
		TSysUser user = userManager.getUser(ryid);
		taskuser.setUsername(user.getName());
		taskuser.setType(type);// ����������
		this.dao.save(taskuser);
	}

	@Override
	public void saveRyMul(String applyId, String ryid, String type)
			throws AppException {
		// ������Ա��Ϣ T_BIZ_TASKUSER��
		String[] ryids = ryid.split(",");
		if (ryids != null && ryids.length > 0) {
			for (int i = 0; i < ryids.length; i++) {
				StringBuffer sql = new StringBuffer();
				sql.append(" from TBizTaskuser t where t.taskid=? and t.userid=? and t.type=?  ");
				List<TBizTaskuser> list = this.find(sql.toString(), applyId,
						ryids[i], type);
				if (list != null && list.size() > 0) {// �Ѿ�ѡ������Ա
				} else {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(ryids[i]);
					TSysUser user = userManager.getUser(ryids[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(type);// ��Ա����
					this.dao.save(taskuser);
				}
			}
		}
	}

	@Override
	public void saveRyMulXbrForMobile(String applyId, String ryid, String type)
			throws AppException {
		/**
		 * ˼·��1���Ȳ��֮ǰ��Э����Աids�� 2����������ѡ���Э����ids�� 3������1��2�Ƚϳ���ȥ����Э����Ա->ִ��ɾ����
		 * ���ӵ���Ա->ִ�����ӣ�
		 */
		// 1���Ȳ��֮ǰ��Э����Աids��
		List<String> list_oldxbr = new ArrayList<String>();
		List<Map<String, String>> listMap = this.xbryTableData(applyId);
		for (int i = 0; i < listMap.size(); i++) {
			list_oldxbr.add(listMap.get(i).get("userid"));
		}
		// 2����������ѡ���Э����ids��
		List<String> list_newxbr = new ArrayList<String>();
		String[] xbrArr = ryid.split(",");
		for (int j = 0; j < xbrArr.length; j++) {
			list_newxbr.add(xbrArr[j]);
		}
		// 3������1��2�Ƚϳ���ȥ����Э����Ա->ִ��ɾ�������ӵ���Ա->ִ�����ӣ�
		List<String> list_xbr_del = new ArrayList<String>();
		List<String> list_xbr_add = new ArrayList<String>();
		for (int m = 0; m < list_oldxbr.size(); m++) {
			Boolean hasxbr = false;
			for (int n = 0; n < list_newxbr.size(); n++) {
				if (list_oldxbr.get(m).equals(list_newxbr.get(n))) {
					hasxbr = true;
				}
			}
			if (!hasxbr) {
				list_xbr_del.add(list_oldxbr.get(m));
			}
		}
		for (int m = 0; m < list_newxbr.size(); m++) {
			Boolean hasxbr = false;
			for (int n = 0; n < list_oldxbr.size(); n++) {
				if (list_newxbr.get(m).equals(list_oldxbr.get(n))) {
					hasxbr = true;
				}
			}
			if (!hasxbr) {
				list_xbr_add.add(list_newxbr.get(m));
			}
		}
		// 4��ִ��ɾ��������
		for (int d = 0; d < list_xbr_del.size(); d++) {
			String sql1 = " from TBizTaskuser where taskid=? and type=? and userid=? ";
			List<TBizTaskuser> taskuserlist = this.dao.find(sql1, applyId,
					type, list_xbr_del.get(d));
			String tBizTaskuserId = taskuserlist.get(0).getId();
			this.saveDelrygh(applyId, tBizTaskuserId);
		}
		String xbrAdd = "";
		for (int a = 0; a < list_xbr_add.size(); a++) {
			if (a < list_xbr_add.size() - 1) {
				xbrAdd += list_xbr_add.get(a) + ",";
			} else {
				xbrAdd += list_xbr_add.get(a);
			}
		}
		if (!"".equals(xbrAdd)) {
			this.saveRyMul(applyId, xbrAdd, type);
		}

	}

	@Override
	public List<ComboboxTree> taskTypeTreeComboByTaskId(String applyId,String zfdxlx) {
		StringBuffer sql = new StringBuffer();
		if(StringUtils.isBlank(applyId)){
			//���ɷ�����ʱ��û������id�����ݴ˱�ʶ�жϻ�ȡר�����������
			if("zxbs".equals(zfdxlx)){
				sql.append(" from TDataTasktype t ");
				sql.append(" where t.isActive = 'Y' and t.code="
						+ TaskTypeCode.ZXXD.getCode());
			}else{
				//���ɷ��������ִ�������������������������
				sql.append(" from TDataTasktype t ");
				sql.append(" where t.isActive = 'Y' and t.code in (select d.tasktypeid from TDataLawobjtypetasktype d where d.lawobjtype = '"+ zfdxlx + "' ) ");
				sql.append(" and t.code!='" + TaskTypeCode.ZXXD.getCode()+"'");// ȥ��ר��
				sql.append(" order by t.orderby ");
			}
		}else{
			Work work = workDao.get(applyId);
			// ִ����������
			String zfdxType = work.getZfdxType();
			// ִ�������б�
			List<Map<String, String>> listMap = this.zfdxTableData(applyId);

			// ���������������������������͵��б�
			if (listMap != null && listMap.size() > 1) {// ���ִ������ʱ��ר��
				sql.append(" from TDataTasktype t ");
				sql.append(" where t.isActive = 'Y' and t.code="
						+ TaskTypeCode.ZXXD.getCode());
			} else if (listMap != null && listMap.size() == 1) {// һ��ִ������ʱ
				sql.append(" from TDataTasktype t ");
				sql
						.append(" where t.isActive = 'Y' and t.code in (select d.tasktypeid from TDataLawobjtypetasktype d where d.lawobjtype = '"
								+ listMap.get(0).get("lawobjtype") + "' ) ");
				sql.append(" and t.code!='" + TaskTypeCode.ZXXD.getCode()+"'");// ȥ��ר��
			} else {
				return null;
			}
			sql.append(" order by t.orderby ");
		}
		List<TDataTasktype> list = this.find(sql.toString());
		return this.taskTypeTreeHelp(list, null);
	}
	
	@Override
	public List<ComboboxTree> taskTypeTreeComboByTaskIdWithoutXf(String applyId,String zfdxlx) {
		StringBuffer sql = new StringBuffer();
		if(StringUtils.isBlank(applyId)){
			//���ɷ�����ʱ��û������id�����ݴ˱�ʶ�жϻ�ȡר�����������
			if("zxbs".equals(zfdxlx)){
				sql.append(" from TDataTasktype t ");
				sql.append(" where t.isActive = 'Y' and t.code="
						+ TaskTypeCode.ZXXD.getCode());
			}else{
				//���ɷ��������ִ�������������������������
				sql.append(" from TDataTasktype t ");
				sql.append(" where t.isActive = 'Y' and t.code in (select d.tasktypeid from TDataLawobjtypetasktype d where d.lawobjtype = '"+ zfdxlx + "' ) ");
				sql.append(" and t.code!='" + TaskTypeCode.ZXXD.getCode()+"'and t.code!='" + TaskTypeCode.XFTS.getCode()+"'");// ȥ��ר��
				sql.append(" order by t.orderby ");
			}
		}else{
			Work work = workDao.get(applyId);
			// ִ����������
			String zfdxType = work.getZfdxType();
			// ִ�������б�
			List<Map<String, String>> listMap = this.zfdxTableData(applyId);

			// ���������������������������͵��б�
			if (listMap != null && listMap.size() > 1) {// ���ִ������ʱ��ר��
				sql.append(" from TDataTasktype t ");
				sql.append(" where t.isActive = 'Y' and t.code="
						+ TaskTypeCode.ZXXD.getCode());
			} else if (listMap != null && listMap.size() == 1) {// һ��ִ������ʱ
				sql.append(" from TDataTasktype t ");
				sql.append(" where t.isActive = 'Y' and t.code in (select d.tasktypeid from TDataLawobjtypetasktype d where d.lawobjtype = '"
								+ listMap.get(0).get("lawobjtype") + "' ) ");
				sql.append(" and t.code!='" + TaskTypeCode.ZXXD.getCode()+"' and t.code!='" + TaskTypeCode.XFTS.getCode()+"'");// ȥ��ר��
			} else {
				return null;
			}
			sql.append(" order by t.orderby ");
		}
		List<TDataTasktype> list = this.find(sql.toString());
		return this.taskTypeTreeHelp(list, null);
	}

	private List<ComboboxTree> taskTypeTreeHelp(List<TDataTasktype> list,
			String pid) {
		//���߰��ʶ���ӣ��������߰����ݱ��棬Ȼ����
		String biaoshi = indexManager.sysVer;
		List<ComboboxTree> listTree = new ArrayList<ComboboxTree>();
		//mysql���ݿ���pidȡֵ��ʱΪ ""
		if("0".equals(biaoshi)){
			for (TDataTasktype ele : list) {
				if ((StringUtils.isBlank(pid) && StringUtils.isBlank(ele.getPid()))
						|| (StringUtils.isNotBlank(pid) && StringUtils.isNotBlank(ele.getPid()) && pid.equals(ele
								.getPid()))) {
					ComboboxTree comboboxTree = new ComboboxTree(ele.getCode(), ele
							.getName().trim());
					comboboxTree.setChildren(this.taskTypeTreeHelp(list, ele
							.getId()));
					listTree.add(comboboxTree);
				}
			}
		}else{
			for (TDataTasktype ele : list) {
				if ((null == pid && null == ele.getPid())
						|| (null != pid && null != ele.getPid() && pid.equals(ele
								.getPid()))) {
					ComboboxTree comboboxTree = new ComboboxTree(ele.getCode(), ele
							.getName().trim());
					comboboxTree.setChildren(this.taskTypeTreeHelp(list, ele
							.getId()));
					listTree.add(comboboxTree);
				}
			}
		}
		return listTree;
	}

	@Override
	public List<TDataChecklisttemplate> getTempList(String taskType,
			String industryId) {
		QueryCondition data = new QueryCondition();
		StringBuffer sql = new StringBuffer();
		sql
				.append(" from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and tasktypeid=:taskType ");
		data.put("taskType", taskType);
		if (StringUtils.isNotBlank(industryId)) {
			sql.append(" and industryid=:industryId ");
			data.put("industryId", industryId);
		}
		List<TDataChecklisttemplate> checkTemplatePo = this.dao.find(sql
				.toString(), data);

		return checkTemplatePo;
	}

	@Override
	public BlMainForm getBlMainFormData(String applyId) {
		BlMainForm blMainForm = new BlMainForm();

		// /////��һ���֣���ҵ��Ϣ///////
		// ִ��������Ϣ
		BlZfdxForm blZfdxForm = new BlZfdxForm();
		Map<String, String> zfdxMap = new HashMap<String, String>();
		List<Map<String, String>> zfdxlistMap = this.zfdxTableData(applyId);
		if (zfdxlistMap != null && zfdxlistMap.size() == 1) {
			zfdxMap = zfdxlistMap.get(0);
			blZfdxForm.setId(zfdxMap.get("id"));
			blZfdxForm.setLawobjid(zfdxMap.get("lawobjid"));
			blZfdxForm.setLawobjname(zfdxMap.get("lawobjname"));
			blZfdxForm.setLawobjtype(zfdxMap.get("lawobjtype"));
			if(StringUtils.isNotBlank(zfdxMap.get("lawobjid"))){
				TDataLawobj lawobj = (TDataLawobj)lawobjManager.get(TDataLawobj.class,zfdxMap.get("lawobjid"));
				try {
					String qysczt = lawobjManager.getScztByLawobj(lawobj);//��ҵ����״̬
					if(StringUtils.contains(qysczt, "Y")){//ȫ��������
						qysczt="ȫ��������";
						blZfdxForm.setQysczt(qysczt);
					}else{//����������
						String sczt="������������";
						if(StringUtils.contains(qysczt, "01")){
							sczt=sczt+"һ�¡�";
						}
						if(StringUtils.contains(qysczt, "02")){
							sczt=sczt+"���¡�";
						}
						if(StringUtils.contains(qysczt, "03")){
							sczt=sczt+"���¡�";
						}
						if(StringUtils.contains(qysczt, "04")){
							sczt=sczt+"���¡�";
						}
						if(StringUtils.contains(qysczt, "05")){
							sczt=sczt+"���¡�";
						}
						if(StringUtils.contains(qysczt, "06")){
							sczt=sczt+"���¡�";
						}
						if(StringUtils.contains(qysczt, "07")){
							sczt=sczt+"���¡�";
						}
						if(StringUtils.contains(qysczt, "08")){
							sczt=sczt+"���¡�";
						}
						if(StringUtils.contains(qysczt, "09")){
							sczt=sczt+"���¡�";
						}
						if(StringUtils.contains(qysczt, "10")){
							sczt=sczt+"ʮ�¡�";
						}
						if(StringUtils.contains(qysczt, "11")){
							sczt=sczt+"ʮһ�¡�";
						}
						if(StringUtils.contains(qysczt, "12")){
							sczt=sczt+"ʮ���¡�";
						}
						sczt = sczt.substring(0,sczt.length()-1);//�Ƴ����һ���ٺ�
						blZfdxForm.setQysczt(sczt);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			blZfdxForm.setAddress(zfdxMap.get("address"));
			blZfdxForm.setManager(zfdxMap.get("manager"));
			blZfdxForm.setManagermobile(zfdxMap.get("managermobile").equals("null")?"":zfdxMap.get("managermobile"));
			blZfdxForm.setBjcr(zfdxMap.get("bjcr"));
			blZfdxForm.setZw(zfdxMap.get("zw"));
			blZfdxForm.setLxdh(zfdxMap.get("lxdh"));
			blZfdxForm.setZwtitle(StringUtils.isNotBlank(zfdxMap.get("zwtitle"))?zfdxMap.get("zwtitle"):"����������");
		}
		blMainForm.setBlZfdxForm(blZfdxForm);

		String lawobjid = zfdxMap.get("lawobjid");
		String lawobjtype = zfdxMap.get("lawobjtype");
		String industryId = "";
		//��ȾԴ��������Ŀ����������ҵ�ֶ�
		if(StringUtils.isNotBlank(lawobjid)){
			if("01,02,06".contains(lawobjtype)){
				industryId=lawobjManager.getLawobjColumnValue(lawobjtype+PublicColumnEnum.hylx.getCode(), lawobjid);//��ҵid��
			}else{//����ִ���������ͻ�ȡĬ����ҵ
				TDataLawobjtype List =  (TDataLawobjtype) this.dao.get(TDataLawobjtype.class,lawobjtype);
				List<TDataIndustry> list = this.dao.find("from TDataIndustry where isActive = 'Y' and lawobjtype = ?",List.getId());
				if(list.size()>0){
					industryId = list.get(0).getId();
				}
			}
		}
		
		TSysUser curu = CtxUtil.getCurUser();
		String getTemplateSql = 
			" select clt.id_" +
			" from " +
				" work_ w, t_Biz_Taskandtasktype tt, t_Biz_Taskandlawobj l, t_Data_Checklisttemplate clt" +
			" where " +
				" w.id_ = tt.taskid_" +
				" and w.id_ = l.taskid_" +
				" and tt.jcmb_ = clt.id_" +
				" and w.isactive_ = 'Y'" +
				" and tt.isactive_ = 'Y'" +
				" and l.isactive_ = 'Y'" +
				" and clt.isactive_ = 'Y'" +
				" and clt.iscurver_ = 'Y'" +
				" and w.areaid_ = '" + curu.getAreaId()+ "'" +
				" and tt.tasktypeid_ = '?'" +
				" and w.state_ = '" + WorkState.YGD.getCode() + "'" +
				" and l.lawobjid_ = '" + lawobjid + "'" +
				" order by w.archives_time_ desc"
		;
		// /////�ڶ����֣��ֿ��Ÿ����������͵�������Ϣ///////
		List<Map<String, String>> rwlxlistMap = this.getTaskTypeByTaskId(applyId);// ���������б�
		for (int i = 0; i < rwlxlistMap.size(); i++) {
			if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.RCJC.getCode())) {// 1���ֳ����
				BlRcjcForm blRcjcForm = new BlRcjcForm();

				Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.RCJC.getCode(), lawobjtype);
				if (null != jcmbInfo) {
					//blRcjcForm.setIsexecchecklist("Y".equals(jcmbInfo.get("isexecchecklist")) ? true : false);
					blRcjcForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
					blRcjcForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
				}
				// info
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.RCJC_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blRcjcForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blRcjcForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					String jcrIds = "";
					String jcrNames = "";
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcrNames += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcrNames += taskuserlist1.get(k).getUsername();
					}
					blRcjcForm.setJcr(jcrIds);// �����
					blRcjcForm.setJcrName(jcrNames);// �����name
				}
				String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.RCJC_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
				if (taskuserlist2 != null && taskuserlist2.size() > 0) {
					blRcjcForm.setJlr(taskuserlist2.get(0).getUserid());// ��¼��
					blRcjcForm.setJlrName(taskuserlist2.get(0).getUsername());// ��¼��name
				}
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.RCJC.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					blRcjcForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blRcjcForm.getJcsj1())){
						blRcjcForm.setJcsj1(blRcjcForm.getJcsj1().substring(0, blRcjcForm.getJcsj1().length()-3));
					}
					
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blRcjcForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blRcjcForm.getJcsj2())){
						blRcjcForm.setJcsj2(blRcjcForm.getJcsj2().substring(0, blRcjcForm.getJcsj2().length()-3));
					}
					if (blRcjcForm.getIsexecchecklist().equals("0")){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������鵥
							blRcjcForm.setJcmbId(ttplist.get(0).getJcmb());
							TDataChecklisttemplate tt = (TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb());
							if(null!=tt){
								blRcjcForm.setJcmbName(tt.getName());
							}
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.RCJC.getCode()));
							if (tempid.size() > 0){
								blRcjcForm.setJcmbId(tempid.get(0));
								blRcjcForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blRcjcForm.setJcmbId(clts.get(0).getId());
									blRcjcForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
					//���ص�
					blRcjcForm.setJcdd(StringUtils.isNotBlank(ttplist.get(0).getJcdd())?ttplist.get(0).getJcdd():"");
				}

				// ����¼
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
				blRcjcForm.setJcjlFileMap(jcjlFileMap);
				
				String jcjl = getJcjl(applyId, TaskTypeCode.RCJC.getCode());
				blRcjcForm.setRwlx(TaskTypeCode.RCJC.getCode());
				blRcjcForm.setJcjl(jcjl);
				blMainForm.setBlRcjcForm(blRcjcForm);
			}else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.RCBG.getCode())) {// 2����Ⱥ˲�
				BlRcbgForm blRcbgForm = new BlRcbgForm();
				blRcbgForm.setRwlx(TaskTypeCode.RCBG.getCode());
				List list=this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", applyId, "24");
				if(null!=list&&list.size()>0){
					TBizTaskandtasktype tt = (TBizTaskandtasktype)list.get(0);
					blRcbgForm.setDesc(tt.getDesc());
				}
				blMainForm.setBlRcbgForm(blRcbgForm);
			} else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.NDHC.getCode())) {// 2����Ⱥ˲�
				BlNdhcForm blNdhcForm = new BlNdhcForm();

				Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.NDHC.getCode(), lawobjtype);
				if (null != jcmbInfo) {
					//blNdhcForm.setIsexecchecklist("Y".equals(jcmbInfo.get("isexecchecklist")) ? true : false);
					blNdhcForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
					blNdhcForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
				}

				// info
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.NDHC_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blNdhcForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blNdhcForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					String jcrIds = "";
					String jcrNames = "";
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcrNames += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcrNames += taskuserlist1.get(k).getUsername();
					}
					blNdhcForm.setJcr(jcrIds);// �����
					blNdhcForm.setJcrName(jcrNames);// �����name
				}
				String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.NDHC_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
				if (taskuserlist2 != null && taskuserlist2.size() > 0) {
					blNdhcForm.setJlr(taskuserlist2.get(0).getUserid());// ��¼��
					blNdhcForm.setJlrName(taskuserlist2.get(0).getUsername());// ��¼��name
				}
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.NDHC.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					blNdhcForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blNdhcForm.getJcsj1())){
						blNdhcForm.setJcsj1(blNdhcForm.getJcsj1().substring(0, blNdhcForm.getJcsj1().length()-3));
					}
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blNdhcForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blNdhcForm.getJcsj2())){
						blNdhcForm.setJcsj2(blNdhcForm.getJcsj2().substring(0, blNdhcForm.getJcsj2().length()-3));
					}
					
					if (StringUtils.equals(blNdhcForm.getIsexecchecklist(),"0")){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������鵥
							blNdhcForm.setJcmbId(ttplist.get(0).getJcmb());
							blNdhcForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb())).getName());
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.NDHC.getCode()));
							if (tempid.size() > 0){
								blNdhcForm.setJcmbId(tempid.get(0));
								blNdhcForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blNdhcForm.setJcmbId(clts.get(0).getId());
									blNdhcForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
					//���ص�
					blNdhcForm.setJcdd(StringUtils.isNotBlank(ttplist.get(0).getJcdd())?ttplist.get(0).getJcdd():"");
				}

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
				blNdhcForm.setJcjlFileMap(jcjlFileMap);
				
				//2015-11-26 ����챨�桱�ɸ�����ʽ��Ϊ�ı���
				/*// ��챨��
				Map<String, String> jcbgFileMap = null;
				FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.NDHCJCBG.getCode(), "1", null);
				List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
				rowsList2 = re2.getRows();
				if (rowsList2.size() > 0) {
					jcbgFileMap = rowsList2.get(0);
				}
				blNdhcForm.setJcbgFileMap(jcbgFileMap);*/
				
				String jcjl = getJcjl(applyId,TaskTypeCode.NDHC.getCode());
				blNdhcForm.setJcjl(jcjl);
				blNdhcForm.setRwlx(TaskTypeCode.NDHC.getCode());
				blMainForm.setBlNdhcForm(blNdhcForm);
			} else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.HDC.getCode())) {// 3���󶽲�
				BlHdcForm blHdcForm = new BlHdcForm();

				Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.HDC.getCode(), lawobjtype);
				if (null != jcmbInfo) {
					//blHdcForm.setIsexecchecklist("Y".equals(jcmbInfo.get("isexecchecklist")) ? true : false);
					blHdcForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
					blHdcForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
				}

				// info
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.HDC_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blHdcForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blHdcForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					String jcrIds = "";
					String jcrNames = "";
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcrNames += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcrNames += taskuserlist1.get(k).getUsername();
					}
					blHdcForm.setJcr(jcrIds);// �����
					blHdcForm.setJcrName(jcrNames);// �����name
				}
				String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.HDC_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
				if (taskuserlist2 != null && taskuserlist2.size() > 0) {
					blHdcForm.setJlr(taskuserlist2.get(0).getUserid());// ��¼��
					blHdcForm.setJlrName(taskuserlist2.get(0).getUsername());// ��¼��name
				}
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.HDC.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					blHdcForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blHdcForm.getJcsj1())){
						blHdcForm.setJcsj1(blHdcForm.getJcsj1().substring(0, blHdcForm.getJcsj1().length()-3));
					}
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blHdcForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blHdcForm.getJcsj2())){
						blHdcForm.setJcsj2(blHdcForm.getJcsj2().substring(0, blHdcForm.getJcsj2().length()-3));
					}
					//blHdcForm.setJcsj(DateUtil.getDate(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ���ʱ��
					
					if (blHdcForm.getIsexecchecklist().equals("0")){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������鵥
							blHdcForm.setJcmbId(ttplist.get(0).getJcmb());
							blHdcForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb())).getName());
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.HDC.getCode()));
							if (tempid.size() > 0){
								blHdcForm.setJcmbId(tempid.get(0));
								blHdcForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blHdcForm.setJcmbId(clts.get(0).getId());
									blHdcForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
					//���ص�
					blHdcForm.setJcdd(StringUtils.isNotBlank(ttplist.get(0).getJcdd())?ttplist.get(0).getJcdd():"");
				}

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
				blHdcForm.setJcjlFileMap(jcjlFileMap);
				
				//2015-11-26 ����챨�桱�ɸ�����ʽ��Ϊ�ı���
				/*// ��챨��
				Map<String, String> jcbgFileMap = null;
				FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.HDCJCBG.getCode(), "1", null);
				List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
				rowsList2 = re2.getRows();
				if (rowsList2.size() > 0) {
					jcbgFileMap = rowsList2.get(0);
				}
				blHdcForm.setJcbgFileMap(jcbgFileMap);*/
				
				String jcjl = getJcjl(applyId,TaskTypeCode.HDC.getCode());
				blHdcForm.setJcjl(jcjl);
				blHdcForm.setRwlx(TaskTypeCode.HDC.getCode());
				blMainForm.setBlHdcForm(blHdcForm);
			} else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.XFTS.getCode())) {// 4���ŷ�Ͷ��
				BlXftsForm blXftsForm = new BlXftsForm();

				Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.XFTS.getCode(), lawobjtype);
				if (null != jcmbInfo) {
					//blXftsForm.setIsexecchecklist("Y".equals(jcmbInfo.get("isexecchecklist")) ? true : false);
					blXftsForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
					blXftsForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
				}

				// info
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XFTS_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blXftsForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blXftsForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					String jcrIds = "";
					String jcrNames = "";
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcrNames += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcrNames += taskuserlist1.get(k).getUsername();
					}
					blXftsForm.setJcr(jcrIds);// �����
					blXftsForm.setJcrName(jcrNames);// �����name
				}
				String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XFTS_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
				if (taskuserlist2 != null && taskuserlist2.size() > 0) {
					blXftsForm.setJlr(taskuserlist2.get(0).getUserid());// ��¼��
					blXftsForm.setJlrName(taskuserlist2.get(0).getUsername());// ��¼��name
				}
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.XFTS.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					blXftsForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blXftsForm.getJcsj1())){
						blXftsForm.setJcsj1(blXftsForm.getJcsj1().substring(0, blXftsForm.getJcsj1().length()-3));
					}
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blXftsForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blXftsForm.getJcsj2())){
						blXftsForm.setJcsj2(blXftsForm.getJcsj2().substring(0, blXftsForm.getJcsj2().length()-3));
					}
					//blXftsForm.setJcsj(DateUtil.getDate(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ���ʱ��
					if ("0".equals(blXftsForm.getIsexecchecklist())){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������鵥
							blXftsForm.setJcmbId(ttplist.get(0).getJcmb());
							TDataChecklisttemplate tt = (TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb());
							if(null!=tt){
								blXftsForm.setJcmbName(tt.getName());
							}
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.XFTS.getCode()));
							if (tempid.size() > 0){
								blXftsForm.setJcmbId(tempid.get(0));
								blXftsForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blXftsForm.setJcmbId(clts.get(0).getId());
									blXftsForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
					
					blXftsForm.setXftsly(ttplist.get(0).getXftsly() == null ? "" : ttplist.get(0).getXftsly());// �ŷ�Ͷ����Դ
					// �ŷ�Ͷ����Դname
					if (ttplist.get(0).getXftsly() != null) {
						String str1 = commonManager.getDicNameByTypeAndCode(DicTypeEnum.XFTSLY.getCode(), ttplist.get(0).getXftsly());
						blXftsForm.setXftslyName(str1);
					}
					//���ص�
					blXftsForm.setJcdd(StringUtils.isNotBlank(ttplist.get(0).getJcdd())?ttplist.get(0).getJcdd():"");
				}

				// ����¼
				List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
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
				blXftsForm.setJcjlFileMap(jcjlFileMap);
				//2015-11-26 ����챨�桱�ɸ�����ʽ��Ϊ�ı���
				/*// ��챨��
				Map<String, String> jcbgFileMap = null;
				FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.XFTSJCBG.getCode(), "1", null);
				List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
				rowsList2 = re2.getRows();
				if (rowsList2.size() > 0) {
					jcbgFileMap = rowsList2.get(0);
				}
				blXftsForm.setJcbgFileMap(jcbgFileMap);*/
				
				String jcjl = getJcjl(applyId,TaskTypeCode.XFTS.getCode());
				blXftsForm.setJcjl(jcjl);
				blXftsForm.setRwlx(TaskTypeCode.XFTS.getCode());
				blMainForm.setBlXftsForm(blXftsForm);
			} else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.PWXKZJC.getCode())) {// 5����������֤���
				BlPwxkzjcForm blPwxkzjcForm = new BlPwxkzjcForm();

				Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.PWXKZJC.getCode(), lawobjtype);
				if (null != jcmbInfo) {
					//blPwxkzjcForm.setIsexecchecklist("Y".equals(jcmbInfo.get("isexecchecklist")) ? true : false);
					blPwxkzjcForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
					blPwxkzjcForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
				}

				// info
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.PWXKZJC_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blPwxkzjcForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blPwxkzjcForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					String jcrIds = "";
					String jcrNames = "";
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcrNames += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcrNames += taskuserlist1.get(k).getUsername();
					}
					blPwxkzjcForm.setJcr(jcrIds);// �����
					blPwxkzjcForm.setJcrName(jcrNames);// �����name
				}
				String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.PWXKZJC_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
				if (taskuserlist2 != null && taskuserlist2.size() > 0) {
					blPwxkzjcForm.setJlr(taskuserlist2.get(0).getUserid());// ��¼��
					blPwxkzjcForm.setJlrName(taskuserlist2.get(0).getUsername());// ��¼��name
				}
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='"+ TaskTypeCode.PWXKZJC.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					blPwxkzjcForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blPwxkzjcForm.getJcsj1())){
						blPwxkzjcForm.setJcsj1(blPwxkzjcForm.getJcsj1().substring(0, blPwxkzjcForm.getJcsj1().length()-3));
					}
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blPwxkzjcForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blPwxkzjcForm.getJcsj2())){
						blPwxkzjcForm.setJcsj2(blPwxkzjcForm.getJcsj2().substring(0, blPwxkzjcForm.getJcsj2().length()-3));
					}
					//blPwxkzjcForm.setJcsj(DateUtil.getDate(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ���ʱ��
					
					if (blPwxkzjcForm.getIsexecchecklist().equals("0")){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������鵥
							blPwxkzjcForm.setJcmbId(ttplist.get(0).getJcmb());
							TDataChecklisttemplate tt = (TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb());
							if(null!=tt){
								blPwxkzjcForm.setJcmbName(tt.getName());
							}
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.PWXKZJC.getCode()));
							if (tempid.size() > 0){
								blPwxkzjcForm.setJcmbId(tempid.get(0));
								blPwxkzjcForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blPwxkzjcForm.setJcmbId(clts.get(0).getId());
									blPwxkzjcForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
					//���ص�
					blPwxkzjcForm.setJcdd(StringUtils.isNotBlank(ttplist.get(0).getJcdd())?ttplist.get(0).getJcdd():"");
				}

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
				blPwxkzjcForm.setJcjlFileMap(jcjlFileMap);
				//2015-11-26 ����챨�桱�ɸ�����ʽ��Ϊ�ı���
				/*// ��챨��
				Map<String, String> jcbgFileMap = null;
				FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.PWXKZJCJCBG.getCode(), "1", null);
				List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
				rowsList2 = re2.getRows();
				if (rowsList2.size() > 0) {
					jcbgFileMap = rowsList2.get(0);
				}
				blPwxkzjcForm.setJcbgFileMap(jcbgFileMap);*/
				
				String jcjl = getJcjl(applyId,TaskTypeCode.PWXKZJC.getCode());
				blPwxkzjcForm.setJcjl(jcjl);
				blPwxkzjcForm.setRwlx(TaskTypeCode.PWXKZJC.getCode());
				blMainForm.setBlPwxkzjcForm(blPwxkzjcForm);
			} else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.WFAJ.getCode())) {// 6��Υ����������
				BlWfajdcForm blWfajdcForm = new BlWfajdcForm();

				// Υ���������������Ϣ
				QueryCondition data = new QueryCondition();
				StringBuffer sql = new StringBuffer();
				sql.append(" from TBizTaskandtasktype where taskid=:taskid and tasktypeid=:tasktypeid ");
				data.put("taskid", applyId);
				data.put("tasktypeid", TaskTypeCode.WFAJ.getCode());
				List<TBizTaskandtasktype> wfajdcInfoList = this.dao.find(sql.toString(), data);
				TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
				if (wfajdcInfoList != null && wfajdcInfoList.size() > 0) {
					tBizTaskandtasktype = wfajdcInfoList.get(0);
					blWfajdcForm.setLadjsj(DateUtil.getDate(tBizTaskandtasktype.getLadjsj() == null ? new Date() : tBizTaskandtasktype.getLadjsj()));// �����Ǽ�ʱ��
					blWfajdcForm.setLadjh(tBizTaskandtasktype.getLadjh());
					blWfajdcForm.setWfajmc(tBizTaskandtasktype.getWfajmc());
					blWfajdcForm.setDcsj1(DateUtil.getDateTime(tBizTaskandtasktype.getDcsj() == null ? new Date() : tBizTaskandtasktype.getDcsj()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blWfajdcForm.getDcsj1())){
						blWfajdcForm.setDcsj1(blWfajdcForm.getDcsj1().substring(0, blWfajdcForm.getDcsj1().length()-3));
					}
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blWfajdcForm.setDcsj2(DateUtil.getDateTime(tBizTaskandtasktype.getJcsj2() == null ? sdf : tBizTaskandtasktype.getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blWfajdcForm.getDcsj2())){
						blWfajdcForm.setDcsj2(blWfajdcForm.getDcsj2().substring(0, blWfajdcForm.getDcsj2().length()-3));
					}
					//blWfajdcForm.setDcsj(DateUtil.getDate(tBizTaskandtasktype.getDcsj() == null ? new Date() : tBizTaskandtasktype.getDcsj()));// ����ʱ��
					blWfajdcForm.setJzzlr(tBizTaskandtasktype.getJzzlr() == null ? CtxUtil.getCurUser().getName() : tBizTaskandtasktype.getJzzlr());// ����������
				} else {
					blWfajdcForm.setJzzlr(CtxUtil.getCurUser().getName());// ����������(Ĭ��)
				}

				String cyzfry = "";
				List<Map<String, String>> cyzfryMap = this.ryghTableData(applyId);
				for (int r = 0; r < cyzfryMap.size(); r++) {
					if (r < cyzfryMap.size() - 1) {
						cyzfry += cyzfryMap.get(r).get("name") + ",";
					} else {
						cyzfry += cyzfryMap.get(r).get("name");
					}
				}
				blWfajdcForm.setCyzfry(cyzfry);// ����ִ����Ա(����+Э��)--�̶���ȡ�Ĳ��ñ���

				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.WFAJDC_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					blWfajdcForm.setJlr(taskuserlist1.get(0).getUserid());// ��¼��
					blWfajdcForm.setJlrName(taskuserlist1.get(0).getUsername());// ��¼��name
				}

				String sql2 = " from TBizTaskillegaltype where taskid=?";
				List<TBizTaskillegaltype> wflxlist = this.dao.find(sql2, applyId);
				if (wflxlist != null && wflxlist.size() > 0) {
					String wflxIds = "";
					String wflxNames = "";
					for (int j = 0; j < wflxlist.size(); j++) {
						TDataIllegaltype tDataIllegaltype = (TDataIllegaltype) this.dao.get(TDataIllegaltype.class, wflxlist.get(j).getIllegaltypeid());
						if (j < wflxlist.size() - 1) {
							wflxIds += wflxlist.get(j).getIllegaltypeid() + ",";
							wflxNames += tDataIllegaltype.getName() + ",";
						} else {
							wflxIds += wflxlist.get(j).getIllegaltypeid();
							wflxNames += tDataIllegaltype.getName();
						}
					}
					blWfajdcForm.setWflxId(wflxIds);// Υ������
					blWfajdcForm.setWflxName(wflxNames);
				}

				// ѯ�ʱ�¼
				Map<String, String> xwblFileMap = null;
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.WFAJDCXWBL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					xwblFileMap = rowsList.get(0);
				}
				blWfajdcForm.setXwblFileMap(xwblFileMap);

				// �����¼
				Map<String, String> kcblFileMap = null;
				FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WFAJDCKCBL.getCode(), "1", null);
				List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
				rowsList2 = re2.getRows();
				if (rowsList2.size() > 0) {
					kcblFileMap = rowsList2.get(0);
				}
				blWfajdcForm.setKcblFileMap(kcblFileMap);
				blMainForm.setBlWfajdcForm(blWfajdcForm);
			} else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.XQZL.getCode())) {// 7����������
				BlXqzlForm blXqzlForm = new BlXqzlForm();

				Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.XQZL.getCode(), lawobjtype);
				if (null != jcmbInfo) {
					//blXqzlForm.setIsexecchecklist("Y".equals(jcmbInfo.get("isexecchecklist")) ? true : false);
					blXqzlForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
					blXqzlForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
				}

				// info
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XQZL_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blXqzlForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blXqzlForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					String jcrIds = "";
					String jcrNames = "";
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcrNames += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcrNames += taskuserlist1.get(k).getUsername();
					}
					blXqzlForm.setJcr(jcrIds);// �����
					blXqzlForm.setJcrName(jcrNames);// �����name
				}
				String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.XQZL_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
				if (taskuserlist2 != null && taskuserlist2.size() > 0) {
					blXqzlForm.setJlr(taskuserlist2.get(0).getUserid());// ��¼��
					blXqzlForm.setJlrName(taskuserlist2.get(0).getUsername());// ��¼��name
				}
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.XQZL.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					
					blXqzlForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blXqzlForm.getJcsj1())){
						blXqzlForm.setJcsj1(blXqzlForm.getJcsj1().substring(0, blXqzlForm.getJcsj1().length()-3));
					}
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blXqzlForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blXqzlForm.getJcsj2())){
						blXqzlForm.setJcsj2(blXqzlForm.getJcsj2().substring(0, blXqzlForm.getJcsj2().length()-3));
					}
					//blXqzlForm.setJcsj(DateUtil.getDate(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ���ʱ��
					if (blXqzlForm.getIsexecchecklist().equals("0")){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������鵥
							blXqzlForm.setJcmbId(ttplist.get(0).getJcmb());
							TDataChecklisttemplate tt= (TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb());
							if(null!=tt){
								blXqzlForm.setJcmbName(tt.getName());
							}
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.XQZL.getCode()));
							if (tempid.size() > 0){
								blXqzlForm.setJcmbId(tempid.get(0));
								blXqzlForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blXqzlForm.setJcmbId(clts.get(0).getId());
									blXqzlForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
					//���ص�
					blXqzlForm.setJcdd(StringUtils.isNotBlank(ttplist.get(0).getJcdd())?ttplist.get(0).getJcdd():"");
				}

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
				blXqzlForm.setJcjlFileMap(jcjlFileMap);
				//2015-11-26 ����챨�桱�ɸ�����ʽ��Ϊ�ı���
				/*// ��챨��
				Map<String, String> jcbgFileMap = null;
				FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.XQZLJCBG.getCode(), "1", null);
				List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
				rowsList2 = re2.getRows();
				if (rowsList2.size() > 0) {
					jcbgFileMap = rowsList2.get(0);
				}
				blXqzlForm.setJcbgFileMap(jcbgFileMap);*/
				
				String jcjl = getJcjl(applyId,TaskTypeCode.XQZL.getCode());
				blXqzlForm.setJcjl(jcjl);
				blXqzlForm.setRwlx(TaskTypeCode.XQZL.getCode());
				blMainForm.setBlXqzlForm(blXqzlForm);
			} else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.GZJC.getCode())) {// 8�����ټ��
				BlGzjcForm blGzjcForm = new BlGzjcForm();

				Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.GZJC.getCode(), lawobjtype);
				if (null != jcmbInfo) {
					//blGzjcForm.setIsexecchecklist("Y".equals(jcmbInfo.get("isexecchecklist")) ? true : false);
					blGzjcForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
					blGzjcForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
				}

				// info
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.GZJC_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blGzjcForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blGzjcForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					String jcrIds = "";
					String jcrNames = "";
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcrNames += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcrNames += taskuserlist1.get(k).getUsername();
					}
					blGzjcForm.setJcr(jcrIds);// �����
					blGzjcForm.setJcrName(jcrNames);// �����name
				}
				String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.GZJC_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
				if (taskuserlist2 != null && taskuserlist2.size() > 0) {
					blGzjcForm.setJlr(taskuserlist2.get(0).getUserid());// ��¼��
					blGzjcForm.setJlrName(taskuserlist2.get(0).getUsername());// ��¼��name
				}
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.GZJC.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					blGzjcForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blGzjcForm.getJcsj1())){
						blGzjcForm.setJcsj1(blGzjcForm.getJcsj1().substring(0, blGzjcForm.getJcsj1().length()-3));
					}
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blGzjcForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blGzjcForm.getJcsj2())){
						blGzjcForm.setJcsj2(blGzjcForm.getJcsj2().substring(0, blGzjcForm.getJcsj2().length()-3));
					}
					//blGzjcForm.setJcsj(DateUtil.getDate(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ���ʱ��
					
					if (blGzjcForm.getIsexecchecklist().equals("0")){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������鵥
							blGzjcForm.setJcmbId(ttplist.get(0).getJcmb());
							blGzjcForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb())).getName());
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.GZJC.getCode()));
							if (tempid.size() > 0){
								blGzjcForm.setJcmbId(tempid.get(0));
								blGzjcForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blGzjcForm.setJcmbId(clts.get(0).getId());
									blGzjcForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
					//���ص�
					blGzjcForm.setJcdd(StringUtils.isNotBlank(ttplist.get(0).getJcdd())?ttplist.get(0).getJcdd():"");
				}

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
				blGzjcForm.setJcjlFileMap(jcjlFileMap);
				//2015-11-26 ����챨�桱�ɸ�����ʽ��Ϊ�ı���
				/*// ��챨��
				Map<String, String> jcbgFileMap = null;
				FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.GZJCJCBG.getCode(), "1", null);
				List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
				rowsList2 = re2.getRows();
				if (rowsList2.size() > 0) {
					jcbgFileMap = rowsList2.get(0);
				}
				blGzjcForm.setJcbgFileMap(jcbgFileMap);*/
				
				String jcjl = getJcjl(applyId,TaskTypeCode.GZJC.getCode());
				blGzjcForm.setJcjl(jcjl);
				blGzjcForm.setRwlx(TaskTypeCode.GZJC.getCode());
				blMainForm.setBlGzjcForm(blGzjcForm);
			} else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.ZDJK.getCode())) {// 9���Զ����
				BlZdjkForm blZdjkForm = new BlZdjkForm();
				Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.ZDJK.getCode(), lawobjtype);
				if (null != jcmbInfo) {
					blZdjkForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
					blZdjkForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
				}
				if(blZdjkForm.getIsexecchecklist().equals("2")){
					// info
					String sql4 = " from TBizAutomoniter where taskid_=? order by updated_ desc";
					List<TBizAutomoniter> tbizautomoniterList = this.dao.find(sql4, applyId);
					if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
						blZdjkForm.setZzmblx(tbizautomoniterList.get(0).getTemplateCode());
						String sql5 = " from TSysDic where code=? and type = '15'";
						List<TSysDic> zzmbList = this.dao.find(sql5, tbizautomoniterList.get(0).getTemplateCode());
						blZdjkForm.setZzmbName(zzmbList.get(0).getName());
					}
				}

				// info
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.ZDJK_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blZdjkForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blZdjkForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					String jcrIds = "";
					String jcrNames = "";
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcrNames += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcrNames += taskuserlist1.get(k).getUsername();
					}
					blZdjkForm.setJcr(jcrIds);// �����
					blZdjkForm.setJcrName(jcrNames);// �����name
				}
				String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.ZDJK_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
				if (taskuserlist2 != null && taskuserlist2.size() > 0) {
					blZdjkForm.setJlr(taskuserlist2.get(0).getUserid());// ��¼��
					blZdjkForm.setJlrName(taskuserlist2.get(0).getUsername());// ��¼��name
				}
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.ZDJK.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					blZdjkForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blZdjkForm.getJcsj1())){
						blZdjkForm.setJcsj1(blZdjkForm.getJcsj1().substring(0, blZdjkForm.getJcsj1().length()-3));
					}
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blZdjkForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blZdjkForm.getJcsj2())){
						blZdjkForm.setJcsj2(blZdjkForm.getJcsj2().substring(0, blZdjkForm.getJcsj2().length()-3));
					}
					//blZdjkForm.setJcsj(DateUtil.getDate(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ���ʱ��
					if(blZdjkForm.getIsexecchecklist().equals("0")){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������鵥
							blZdjkForm.setJcmbId(ttplist.get(0).getJcmb());
							blZdjkForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb())).getName());
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.ZDJK.getCode()));
							if (tempid.size() > 0){
								blZdjkForm.setJcmbId(tempid.get(0));
								blZdjkForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blZdjkForm.setJcmbId(clts.get(0).getId());
									blZdjkForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
					if(blZdjkForm.getIsexecchecklist().equals("2")){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������������
							blZdjkForm.setJcmbId(ttplist.get(0).getJcmb());
							blZdjkForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb())).getName());
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.ZDJK.getCode()));
							if (tempid.size() > 0){
								blZdjkForm.setJcmbId(tempid.get(0));
								blZdjkForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blZdjkForm.setJcmbId(clts.get(0).getId());
									blZdjkForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
				}
				List<Map<String, String>> zxjcjlFileMap = new ArrayList<Map<String, String>>();
				Map<String, String> jcjlFileMap = null;
				if(blZdjkForm.getIsexecchecklist().equals("2")){
					FyWebResult re1 = zxzzManager.queryZxzzFileList(applyId, "N", FileTypeEnums.ZDJKJCJL.getCode(), "1", null);
					List<Map<String, String>> rowsList1 = new ArrayList<Map<String, String>>();
					rowsList1 = re1.getRows();
					for(int k = 0; k < rowsList1.size(); k++){
						jcjlFileMap = rowsList1.get(k);
						zxjcjlFileMap.add(jcjlFileMap);
					}
					blZdjkForm.setZxjcjlFileMap(zxjcjlFileMap);//��̨������Ҫ�ĸ���list
					if(rowsList1.size() > 0){
						blZdjkForm.setJcjlFileMap(rowsList1.get(0));//�ֻ�����Ҫ�ĵ�һ��ģ����Ϣ
					}
				}
				if(blZdjkForm.getIsexecchecklist().equals("0")){
					// ����¼
					FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.ZDJKJCJL.getCode(), "1", null);
					List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
					rowsList = re.getRows();
					if (rowsList.size() > 0) {
						jcjlFileMap = rowsList.get(0);
					}
					blZdjkForm.setJcjlFileMap(jcjlFileMap);
				}
				//2015-11-26 ����챨�桱�ɸ�����ʽ��Ϊ�ı���
				/*// ��챨��
				Map<String, String> jcbgFileMap = null;
				FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.ZDJKJCBG.getCode(), "1", null);
				List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
				rowsList2 = re2.getRows();
				if (rowsList2.size() > 0) {
					jcbgFileMap = rowsList2.get(0);
				}
				blZdjkForm.setJcbgFileMap(jcbgFileMap);*/
				String jcjl = getJcjl(applyId,TaskTypeCode.ZDJK.getCode());
				blZdjkForm.setJcjl(jcjl);
				blZdjkForm.setRwlx(TaskTypeCode.ZDJK.getCode());
				blMainForm.setBlZdjkForm(blZdjkForm);
			} else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.WXFW.getCode())) {// 10��Σ�շ���
				BlWxfwForm blWxfwForm = new BlWxfwForm();
				Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.WXFW.getCode(), lawobjtype);
				if (null != jcmbInfo) {
					//blWxfwForm.setIsexecchecklist("Y".equals(jcmbInfo.get("isexecchecklist")) ? true : false);
					blWxfwForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
					blWxfwForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
				}

				// info
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.WXFW_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blWxfwForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blWxfwForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					String jcrIds = "";
					String jcrNames = "";
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcrNames += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcrNames += taskuserlist1.get(k).getUsername();
					}
					blWxfwForm.setJcr(jcrIds);// �����
					blWxfwForm.setJcrName(jcrNames);// �����name
				}
				String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.WXFW_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
				if (taskuserlist2 != null && taskuserlist2.size() > 0) {
					blWxfwForm.setJlr(taskuserlist2.get(0).getUserid());// ��¼��
					blWxfwForm.setJlrName(taskuserlist2.get(0).getUsername());// ��¼��name
				}
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.WXFW.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					blWxfwForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blWxfwForm.getJcsj1())){
						blWxfwForm.setJcsj1(blWxfwForm.getJcsj1().substring(0, blWxfwForm.getJcsj1().length()-3));
					}
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blWxfwForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blWxfwForm.getJcsj2())){
						blWxfwForm.setJcsj2(blWxfwForm.getJcsj2().substring(0, blWxfwForm.getJcsj2().length()-3));
					}
					//blWxfwForm.setJcsj(DateUtil.getDate(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ���ʱ��
					
					if (blWxfwForm.getIsexecchecklist().equals("0")){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������鵥
							blWxfwForm.setJcmbId(ttplist.get(0).getJcmb());
							blWxfwForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb())).getName());
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.WXFW.getCode()));
							if (tempid.size() > 0){
								blWxfwForm.setJcmbId(tempid.get(0));
								blWxfwForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blWxfwForm.setJcmbId(clts.get(0).getId());
									blWxfwForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
					//���ص�
					blWxfwForm.setJcdd(StringUtils.isNotBlank(ttplist.get(0).getJcdd())?ttplist.get(0).getJcdd():"");
				}

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
				blWxfwForm.setJcjlFileMap(jcjlFileMap);
				//2015-11-26 ����챨�桱�ɸ�����ʽ��Ϊ�ı���
				/*// ��챨��
				Map<String, String> jcbgFileMap = null;
				FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WXFWJCBG.getCode(), "1", null);
				List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
				rowsList2 = re2.getRows();
				if (rowsList2.size() > 0) {
					jcbgFileMap = rowsList2.get(0);
				}
				blWxfwForm.setJcbgFileMap(jcbgFileMap);*/
				
				String jcjl = getJcjl(applyId,TaskTypeCode.WXFW.getCode());
				blWxfwForm.setJcjl(jcjl);
				blWxfwForm.setRwlx(TaskTypeCode.WXFW.getCode());
				blMainForm.setBlWxfwForm(blWxfwForm);
			} else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.WXHXP.getCode())) {// 11��Σ�ջ�ѧƷ
				BlWxhxpForm blWxhxpForm = new BlWxhxpForm();

				Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.WXHXP.getCode(), lawobjtype);
				if (null != jcmbInfo) {
					//blWxhxpForm.setIsexecchecklist("Y".equals(jcmbInfo.get("isexecchecklist")) ? true : false);
					blWxhxpForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
					blWxhxpForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
				}

				// info
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.WXHXP_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blWxhxpForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blWxhxpForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					String jcrIds = "";
					String jcrNames = "";
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcrNames += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcrNames += taskuserlist1.get(k).getUsername();
					}
					blWxhxpForm.setJcr(jcrIds);// �����
					blWxhxpForm.setJcrName(jcrNames);// �����name
				}
				String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.WXHXP_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
				if (taskuserlist2 != null && taskuserlist2.size() > 0) {
					blWxhxpForm.setJlr(taskuserlist2.get(0).getUserid());// ��¼��
					blWxhxpForm.setJlrName(taskuserlist2.get(0).getUsername());// ��¼��name
				}
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.WXHXP.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					blWxhxpForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blWxhxpForm.getJcsj1())){
						blWxhxpForm.setJcsj1(blWxhxpForm.getJcsj1().substring(0, blWxhxpForm.getJcsj1().length()-3));
					}
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blWxhxpForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blWxhxpForm.getJcsj2())){
						blWxhxpForm.setJcsj2(blWxhxpForm.getJcsj2().substring(0, blWxhxpForm.getJcsj2().length()-3));
					}
					//blWxhxpForm.setJcsj(DateUtil.getDate(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ���ʱ��
					
					if (blWxhxpForm.getIsexecchecklist().equals("0")){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������鵥
							blWxhxpForm.setJcmbId(ttplist.get(0).getJcmb());
							blWxhxpForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb())).getName());
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.WXHXP.getCode()));
							if (tempid.size() > 0){
								blWxhxpForm.setJcmbId(tempid.get(0));
								blWxhxpForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blWxhxpForm.setJcmbId(clts.get(0).getId());
									blWxhxpForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
				}

				// ����¼
				Map<String, String> jcjlFileMap = null;
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.WXHXPJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					jcjlFileMap = rowsList.get(0);
				}
				blWxhxpForm.setJcjlFileMap(jcjlFileMap);
				//2015-11-26 ����챨�桱�ɸ�����ʽ��Ϊ�ı���
				/*// ��챨��
				Map<String, String> jcbgFileMap = null;
				FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WXHXPJCBG.getCode(), "1", null);
				List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
				rowsList2 = re2.getRows();
				if (rowsList2.size() > 0) {
					jcbgFileMap = rowsList2.get(0);
				}
				blWxhxpForm.setJcbgFileMap(jcbgFileMap);*/
				
				String jcjl = getJcjl(applyId,TaskTypeCode.WXHXP.getCode());
				blWxhxpForm.setJcjl(jcjl);
				blWxhxpForm.setRwlx(TaskTypeCode.WXHXP.getCode());
				blMainForm.setBlWxhxpForm(blWxhxpForm);
			} else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.FSAQ.getCode())) {// 12�����䰲ȫ
				BlFsaqForm blFsaqForm = new BlFsaqForm();

				Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.FSAQ.getCode(), lawobjtype);
				if (null != jcmbInfo) {
					//blFsaqForm.setIsexecchecklist("Y".equals(jcmbInfo.get("isexecchecklist")) ? true : false);
					blFsaqForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
					blFsaqForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
				}

				// info
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.FSAQ_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blFsaqForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blFsaqForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					String jcrIds = "";
					String jcrNames = "";
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcrNames += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcrNames += taskuserlist1.get(k).getUsername();
					}
					blFsaqForm.setJcr(jcrIds);// �����
					blFsaqForm.setJcrName(jcrNames);// �����name
				}
				String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.FSAQ_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
				if (taskuserlist2 != null && taskuserlist2.size() > 0) {
					blFsaqForm.setJlr(taskuserlist2.get(0).getUserid());// ��¼��
					blFsaqForm.setJlrName(taskuserlist2.get(0).getUsername());// ��¼��name
				}
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.FSAQ.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					blFsaqForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blFsaqForm.getJcsj1())){
						blFsaqForm.setJcsj1(blFsaqForm.getJcsj1().substring(0, blFsaqForm.getJcsj1().length()-3));
					}
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blFsaqForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blFsaqForm.getJcsj2())){
						blFsaqForm.setJcsj2(blFsaqForm.getJcsj2().substring(0, blFsaqForm.getJcsj2().length()-3));
					}
					//blFsaqForm.setJcsj(DateUtil.getDate(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ���ʱ��
					
					if (blFsaqForm.getIsexecchecklist().equals("0")){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������鵥
							blFsaqForm.setJcmbId(ttplist.get(0).getJcmb());
							blFsaqForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb())).getName());
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.FSAQ.getCode()));
							if (tempid.size() > 0){
								blFsaqForm.setJcmbId(tempid.get(0));
								blFsaqForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blFsaqForm.setJcmbId(clts.get(0).getId());
									blFsaqForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
					//���ص�
					blFsaqForm.setJcdd(StringUtils.isNotBlank(ttplist.get(0).getJcdd())?ttplist.get(0).getJcdd():"");
				}

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
				blFsaqForm.setJcjlFileMap(jcjlFileMap);
				//2015-11-26 ����챨�桱�ɸ�����ʽ��Ϊ�ı���
				/*// ��챨��
				Map<String, String> jcbgFileMap = null;
				FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.FSAQJCBG.getCode(), "1", null);
				List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
				rowsList2 = re2.getRows();
				if (rowsList2.size() > 0) {
					jcbgFileMap = rowsList2.get(0);
				}
				blFsaqForm.setJcbgFileMap(jcbgFileMap);*/
				
				String jcjl = getJcjl(applyId,TaskTypeCode.FSAQ.getCode());
				blFsaqForm.setJcjl(jcjl);
				blFsaqForm.setRwlx(TaskTypeCode.FSAQ.getCode());
				blMainForm.setBlFsaqForm(blFsaqForm);
			} else if (rwlxlistMap.get(i).get("id").equals(TaskTypeCode.WRSGXCDC.getCode())) {// 13����Ⱦ�¹��ֳ�����
				BlWrsgxcdcForm blWrsgxcdcForm = new BlWrsgxcdcForm();

				Map<String, String> jcmbInfo = configManager.queryJcmbInfo(TaskTypeCode.WRSGXCDC.getCode(), lawobjtype);
				if (null != jcmbInfo) {
					//blWrsgxcdcForm.setIsexecchecklist("Y".equals(jcmbInfo.get("isexecchecklist")) ? true : false);
					blWrsgxcdcForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
					blWrsgxcdcForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
				}

				// info
				String sql1 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.WRSGXCDC_JCR.getCode() + "'";
				List<TBizTaskuser> taskuserlist1 = this.dao.find(sql1, applyId);
				if (taskuserlist1 != null && taskuserlist1.size() > 0) {
					// blWrsgxcdcForm.setJcr(taskuserlist1.get(0).getUserid());//�����
					// blWrsgxcdcForm.setJcrName(taskuserlist1.get(0).getUsername());//�����name
					// 2015-7-15 �޸� ���ֳ���족�ļ���˸�Ϊ��ѡ��
					String jcrIds = "";
					String jcrNames = "";
					for (int k = 0; k < taskuserlist1.size(); k++) {
						if (k > 0) {
							jcrIds += ",";
							jcrNames += ",";
						}
						jcrIds += taskuserlist1.get(k).getUserid();
						jcrNames += taskuserlist1.get(k).getUsername();
					}
					blWrsgxcdcForm.setJcr(jcrIds);// �����
					blWrsgxcdcForm.setJcrName(jcrNames);// �����name
				}
				String sql2 = " from TBizTaskuser where taskid=? and type='" + TaskUserEnum.WRSGXCDC_JLR.getCode() + "'";
				List<TBizTaskuser> taskuserlist2 = this.dao.find(sql2, applyId);
				if (taskuserlist2 != null && taskuserlist2.size() > 0) {
					blWrsgxcdcForm.setJlr(taskuserlist2.get(0).getUserid());// ��¼��
					blWrsgxcdcForm.setJlrName(taskuserlist2.get(0).getUsername());// ��¼��name
				}
				String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.WRSGXCDC.getCode() + "'";
				List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, applyId);
				if (ttplist != null && ttplist.size() > 0) {
					blWrsgxcdcForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ��鿪ʼʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blWrsgxcdcForm.getJcsj1())){
						blWrsgxcdcForm.setJcsj1(blWrsgxcdcForm.getJcsj1().substring(0, blWrsgxcdcForm.getJcsj1().length()-3));
					}
					//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blWrsgxcdcForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// ����ֹʱ�䣨Ĭ�ϣ�
					//�ֻ���ʱ��ֵ�Ĵ���
					if(StringUtils.isNotBlank(blWrsgxcdcForm.getJcsj2())){
						blWrsgxcdcForm.setJcsj2(blWrsgxcdcForm.getJcsj2().substring(0, blWrsgxcdcForm.getJcsj2().length()-3));
					}
					//blWrsgxcdcForm.setJcsj(DateUtil.getDate(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// ���ʱ��
					
					if (blWrsgxcdcForm.getIsexecchecklist().equals("0")){
						if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// ���֮ǰ������鵥
							blWrsgxcdcForm.setJcmbId(ttplist.get(0).getJcmb());
							blWrsgxcdcForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb())).getName());
						} else {
							List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.WRSGXCDC.getCode()));
							if (tempid.size() > 0){
								blWrsgxcdcForm.setJcmbId(tempid.get(0));
								blWrsgxcdcForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
							} else {
								List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
								if (clts.size() > 0){
									blWrsgxcdcForm.setJcmbId(clts.get(0).getId());
									blWrsgxcdcForm.setJcmbName(clts.get(0).getName());
								}
							}
						}
					}
					//���ص�
					blWrsgxcdcForm.setJcdd(StringUtils.isNotBlank(ttplist.get(0).getJcdd())?ttplist.get(0).getJcdd():"");
				}

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
				blWrsgxcdcForm.setJcjlFileMap(jcjlFileMap);
				//2015-11-26 ����챨�桱�ɸ�����ʽ��Ϊ�ı���
				/*// ��챨��
				Map<String, String> jcbgFileMap = null;
				FyWebResult re2 = commonManager.queryFileList(applyId, "N", FileTypeEnums.WRSGXCDCJCBG.getCode(), "1", null);
				List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
				rowsList2 = re2.getRows();
				if (rowsList2.size() > 0) {
					jcbgFileMap = rowsList2.get(0);
				}
				blWrsgxcdcForm.setJcbgFileMap(jcbgFileMap);*/
				
				String jcjl = getJcjl(applyId,TaskTypeCode.WRSGXCDC.getCode());
				blWrsgxcdcForm.setJcjl(jcjl);
				blWrsgxcdcForm.setRwlx(TaskTypeCode.WRSGXCDC.getCode());
				blMainForm.setBlWrsgxcdcForm(blWrsgxcdcForm);
			}
		}
		return blMainForm;
	}
	
	@Override
	public XfbjdForm getXfbjdform(String applyId){
		// �ŷð�ᵥ��Ϣ
		XfbjdForm xfbjdForm = new XfbjdForm();
		Work work = (Work) this.get(Work.class, applyId);
		if(work!=null){
			if(StringUtils.isNotBlank(work.getXfbcjsrId())){
				TSysUser user = (TSysUser) this.get(TSysUser.class, work.getXfbcjsrId());
				xfbjdForm.setJsr(user.getName());//�ŷñ���������
				xfbjdForm.setBcr(user.getName());//������
			}
			//��������õ��ŷð�ᵥ
			String sql = " from TBizXfbjd where rwid= ? ";
			List<TBizXfbjd> xfbjd = this.dao.find(sql, applyId);
			if(xfbjd!=null && xfbjd.size()>0){
				xfbjdForm.setId(xfbjd.get(0).getId());
				if(StringUtils.isNotBlank(xfbjd.get(0).getBjqk())){
					xfbjdForm.setBjqk(xfbjd.get(0).getBjqk());
				}else{
					xfbjdForm.setBjqk("1");
				}
				xfbjdForm.setHjxfblqk(xfbjd.get(0).getHjxfblqk());
				xfbjdForm.setHfxs(xfbjd.get(0).getHfxs());
				xfbjdForm.setHfxsxm(xfbjd.get(0).getHfxsxm());
				xfbjdForm.setHfxsdyrn(xfbjd.get(0).getHfxsdyrn());
				xfbjdForm.setHfr(xfbjd.get(0).getHfr());
				if(StringUtils.isNotBlank(xfbjd.get(0).getHfr())){
					TSysUser user = (TSysUser) this.get(TSysUser.class, xfbjd.get(0).getHfr());
					if(user!=null){
						xfbjdForm.setHfrName(user.getName());
					}
				}
				xfbjdForm.setSlsj(DateUtil.getDateTime(xfbjd.get(0).getSlsj()));
				xfbjdForm.setHfrq(DateUtil.getDateTime(xfbjd.get(0).getHfrq() == null ? new Date() : xfbjd.get(0).getHfrq()));// �ط�ʱ�䣨Ĭ�ϣ�
				xfbjdForm.setFhr(xfbjd.get(0).getFhr());
				xfbjdForm.setFhrq(DateUtil.getDateTime(xfbjd.get(0).getFhrq() == null ? new Date() : xfbjd.get(0).getFhrq()));
				xfbjdForm.setJsr(xfbjd.get(0).getJsr());
				xfbjdForm.setJssj(DateUtil.getDateTime(xfbjd.get(0).getJssj() == null ? new Date() : xfbjd.get(0).getJssj()));
				xfbjdForm.setBcqk(xfbjd.get(0).getBcqk());
				xfbjdForm.setBcr(xfbjd.get(0).getBcr());
				xfbjdForm.setBcrq(DateUtil.getDateTime(xfbjd.get(0).getBcrq() == null ? new Date() : xfbjd.get(0).getBcrq()));
			}else{
				xfbjdForm.setFhr(CtxUtil.getCurUser().getName());//������
				xfbjdForm.setHfrq(DateUtil.getDateTime(new Date()));
				xfbjdForm.setFhrq(DateUtil.getDateTime(new Date()));
				xfbjdForm.setJssj(DateUtil.getDateTime(new Date()));
				xfbjdForm.setBcrq(DateUtil.getDateTime(new Date()));
			}
		}
		return xfbjdForm;
	}

	@Override
	public String saveWorkzxBL(String applyId, BlMainForm blMainForm) {
		//���߰��ʶ���ӣ��������߰����ݱ��棬Ȼ����
		String biaoshi = indexManager.sysVer;
		String xftsId = ""; //�ŷ�Ͷ�߰�����id
		// ִ������info
		if (blMainForm.getBlZfdxForm() != null) {
			BlZfdxForm blZfdxForm = blMainForm.getBlZfdxForm();
			TBizTaskandlawobj tBizTaskandlawobj = (TBizTaskandlawobj) this.dao
					.get(TBizTaskandlawobj.class, blZfdxForm.getId());
			tBizTaskandlawobj.setBjcr(blZfdxForm.getBjcr());
			tBizTaskandlawobj.setZw(blZfdxForm.getZw());
			tBizTaskandlawobj.setZwtitle(StringUtils.isNotBlank(blZfdxForm.getZwtitle())?blZfdxForm.getZwtitle():"����������");
			tBizTaskandlawobj.setLxdh(blZfdxForm.getLxdh());
			this.dao.save(tBizTaskandlawobj);
		}

		// �ֳ����
		if (blMainForm.getBlRcjcForm() != null) {
			BlRcjcForm blRcjcForm = blMainForm.getBlRcjcForm();
			// 1�������
			if (StringUtils.isNotBlank(blRcjcForm.getJcr())) {
				// 2015-7-15 �޸� ����˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.RCJC_JCR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blRcjcForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(blRcjcForm.getJcr().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							blRcjcForm.getJcr().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.RCJC_JCR.getCode());
					this.dao.save(taskuser);
				}
			}
			// 2����¼��
			if (StringUtils.isNotBlank(blRcjcForm.getJlr())) {
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.RCJC_JLR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blRcjcForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blRcjcForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.RCJC_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 3�����ʱ�䣻4�����ģ��
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blRcjcForm.getJcsj1()
						+ "', t.JCSJ2_='" + blRcjcForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blRcjcForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blRcjcForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.JCMB_='").append(blRcjcForm.getJcmbId())
					.append("'");
			sql.append(" ,t.JCDD_='").append(blRcjcForm.getJcdd())
			.append("'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.RCJC.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 5��������
			try {
				saveJcjl(applyId,blMainForm.blRcjcForm.getRwlx(), blMainForm.blRcjcForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ��Ⱥ˲�
		if (blMainForm.getBlNdhcForm() != null) {
			BlNdhcForm blNdhcForm = blMainForm.getBlNdhcForm();
			// 1�������
			if (StringUtils.isNotBlank(blNdhcForm.getJcr())) {
				// 2015-7-15 �޸� ����˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.NDHC_JCR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blNdhcForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(blNdhcForm.getJcr().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							blNdhcForm.getJcr().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.NDHC_JCR.getCode());
					this.dao.save(taskuser);
				}
			}
			// 2����¼��
			if (StringUtils.isNotBlank(blNdhcForm.getJlr())) {
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.NDHC_JLR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blNdhcForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blNdhcForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.NDHC_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 3�����ʱ�䣻4�����ģ��
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blNdhcForm.getJcsj1()
						+ "', t.JCSJ2_='" + blNdhcForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blNdhcForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blNdhcForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.JCMB_='").append(blNdhcForm.getJcmbId())
					.append("'");
			sql.append(" ,t.JCDD_='").append(blNdhcForm.getJcdd())
			.append("'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.NDHC.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 5��������
			try {
				saveJcjl(applyId,blMainForm.blNdhcForm.getRwlx(), blMainForm.blNdhcForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// �󶽲�
		if (blMainForm.getBlHdcForm() != null) {
			BlHdcForm blHdcForm = blMainForm.getBlHdcForm();
			// 1�������
			if (StringUtils.isNotBlank(blHdcForm.getJcr())) {
				// 2015-7-15 �޸� ����˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.HDC_JCR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blHdcForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(blHdcForm.getJcr().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							blHdcForm.getJcr().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.HDC_JCR.getCode());
					this.dao.save(taskuser);
				}
			}
			// 2����¼��
			if (StringUtils.isNotBlank(blHdcForm.getJlr())) {
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.HDC_JLR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blHdcForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blHdcForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.HDC_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 3�����ʱ�䣻4�����ģ��
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blHdcForm.getJcsj1()
						+ "', t.JCSJ2_='" + blHdcForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blHdcForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blHdcForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.JCMB_='").append(blHdcForm.getJcmbId()).append("'");
			sql.append(" ,t.JCDD_='").append(blHdcForm.getJcdd())
			.append("'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.HDC.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 5��������
			try {
				saveJcjl(applyId,blMainForm.blHdcForm.getRwlx(), blMainForm.blHdcForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// �ŷ�Ͷ��
		if (blMainForm.getBlXftsForm() != null) {
			BlXftsForm blXftsForm = blMainForm.getBlXftsForm();
			// 1�������
			if (StringUtils.isNotBlank(blXftsForm.getJcr())) {
				// 2015-7-15 �޸� ����˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.XFTS_JCR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blXftsForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(blXftsForm.getJcr().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							blXftsForm.getJcr().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.XFTS_JCR.getCode());
					this.dao.save(taskuser);
				}
			}
			// 2����¼��
			if (StringUtils.isNotBlank(blXftsForm.getJlr())) {
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.XFTS_JLR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blXftsForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blXftsForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.XFTS_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 3�����ʱ�䣻4�����ģ�壻5���ŷ�Ͷ����Դ
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blXftsForm.getJcsj1()
						+ "', t.JCSJ2_='" + blXftsForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blXftsForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blXftsForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.JCDD_='").append(blXftsForm.getJcdd())
			.append("'");
			sql.append(" ,t.JCMB_='").append(blXftsForm.getJcmbId())
					.append("'");
			sql.append(" ,t.XFTSLY_='").append(blXftsForm.getXftsly()).append(
					"'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.XFTS.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 6��������
			try {
				saveJcjl(applyId,blMainForm.blXftsForm.getRwlx(), blMainForm.blXftsForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//�ŷð���������������ط����
		if(blMainForm.getXfbjdForm()!= null ){
			XfbjdForm xfbjdForm = blMainForm.getXfbjdForm();
			TBizXfbjd xfbjd = null;
			if(StringUtils.isNotBlank(xfbjdForm.getId())){
				xfbjd = (TBizXfbjd) this.get(TBizXfbjd.class, xfbjdForm.getId());
				xfbjd.setUpdateby(CtxUtil.getCurUser());
				xfbjd.setUpdated(new Date());
			}else{
				xfbjd = new TBizXfbjd();
				TSysArea area = (TSysArea) this.get(TSysArea.class, CtxUtil.getAreaId());
				xfbjd.setArea(area);
				xfbjd.setCreateby(CtxUtil.getCurUser());
				xfbjd.setCreated(new Date());
				xfbjd.setIsActive(YnEnum.Y.getCode());
				xfbjd.setUpdateby(CtxUtil.getCurUser());
				xfbjd.setUpdated(new Date());
			}
			xfbjd.setRwid(applyId);
			Work work = (Work) this.get(Work.class, applyId);
			if(work!=null){
				if(StringUtils.isNotBlank(xfbjdForm.getSlsj())){
					DateFormat df=DateFormat.getDateInstance();
					Date date = null;
					try {
						date = df.parse(xfbjdForm.getSlsj());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					xfbjd.setSlsj(date);
				}else{
					xfbjd.setSlsj(work.getEndTime());//����ʱ��
				}
			}
			xfbjd.setBjqk(xfbjdForm.getBjqk());
			xfbjd.setHjxfblqk(xfbjdForm.getHjxfblqk());
			xfbjd.setHfxs(xfbjdForm.getHfxs());
			if("����".equals(xfbjdForm.getHfxsxm())){
				xfbjd.setHfxsxm("");
			}else{
				xfbjd.setHfxsxm(xfbjdForm.getHfxsxm());
			}
			if("����".equals(xfbjdForm.getHfxsdyrn())){
				xfbjd.setHfxsdyrn("");
			}else{
				xfbjd.setHfxsdyrn(xfbjdForm.getHfxsdyrn());
			}
			xfbjd.setHfr(xfbjdForm.getHfr());
			xfbjd.setFhr(xfbjdForm.getFhr());
			//��ǰ����ѡ��������˭����˭���лطúͱ���
			xfbjd.setJsr(CtxUtil.getCurUser().getName());
			xfbjd.setBcqk(xfbjdForm.getBcqk());
			xfbjd.setBcr(CtxUtil.getCurUser().getName());
			/*try {
				xfbjd.setHfrq(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",xfbjdForm.getHfrq()));
				xfbjd.setFhrq(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",xfbjdForm.getFhrq()));
				xfbjd.setJssj(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",xfbjdForm.getJssj()));
				xfbjd.setBcrq(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",xfbjdForm.getBcrq()));
			} catch (ParseException e) {
				e.printStackTrace();
			}*/
			this.dao.save(xfbjd);
			if(xfbjd!=null){
				xftsId = xfbjd.getId();
			}
		}
		// ��������֤���
		if (blMainForm.getBlPwxkzjcForm() != null) {
			BlPwxkzjcForm blPwxkzjcForm = blMainForm.getBlPwxkzjcForm();
			// 1�������
			if (StringUtils.isNotBlank(blPwxkzjcForm.getJcr())) {
				// 2015-7-15 �޸� ����˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.PWXKZJC_JCR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blPwxkzjcForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(blPwxkzjcForm.getJcr().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							blPwxkzjcForm.getJcr().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.PWXKZJC_JCR.getCode());
					this.dao.save(taskuser);
				}
			}
			// 2����¼��
			if (StringUtils.isNotBlank(blPwxkzjcForm.getJlr())) {
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.PWXKZJC_JLR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blPwxkzjcForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blPwxkzjcForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.PWXKZJC_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 3�����ʱ�䣻4�����ģ��
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blPwxkzjcForm.getJcsj1()
						+ "', t.JCSJ2_='" + blPwxkzjcForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blPwxkzjcForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blPwxkzjcForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.JCMB_='").append(blPwxkzjcForm.getJcmbId()).append(
					"'");
			sql.append(" ,t.JCDD_='").append(blPwxkzjcForm.getJcdd())
			.append("'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.PWXKZJC.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 5��������
			try {
				saveJcjl(applyId,blMainForm.blPwxkzjcForm.getRwlx(), blMainForm.blPwxkzjcForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Υ����������
		if (blMainForm.getBlWfajdcForm() != null) {
			BlWfajdcForm blWfajdcForm = blMainForm.getBlWfajdcForm();
			// 1�������Ǽ�ʱ�䣻2�������ǼǺţ�3��Υ���������ƣ�4������ʱ�䣻5�����������ˣ�
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.LADJSJ_='" + blWfajdcForm.getLadjsj() + "'");
				sql.append(" ,t.DCSJ_='" + blWfajdcForm.getDcsj1() + "'");
				sql.append(" ,t.JCSJ2_='" + blWfajdcForm.getDcsj2() + "'");
			}else{
				sql.append(" set t.LADJSJ_=to_date('" + blWfajdcForm.getLadjsj()
						+ "','yyyy-MM-dd')");
				sql.append(" ,t.DCSJ_=to_date('" + blWfajdcForm.getDcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss')");
				sql.append(" ,t.JCSJ2_=to_date('" + blWfajdcForm.getDcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.LADJH_='").append(blWfajdcForm.getLadjh()).append(
					"'");
			sql.append(" ,t.WFAJMC_='").append(blWfajdcForm.getWfajmc())
					.append("'");
			sql.append(" ,t.JZZLR_='").append(blWfajdcForm.getJzzlr()).append(
					"'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.WFAJ.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 6����¼�ˣ�
			if (StringUtils.isNotBlank(blWfajdcForm.getJlr())) {
				String sqljlr = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.WFAJDC_JLR.getCode() + "'";
				this.dao.removeFindObjs(sqljlr, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blWfajdcForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blWfajdcForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.WFAJDC_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 7��Υ������
			if (StringUtils.isNotBlank(blWfajdcForm.getWflxId())) {
				String sqlwflx = " from TBizTaskillegaltype where taskid=?";
				this.dao.removeFindObjs(sqlwflx, applyId);
				String[] wflxIds = blWfajdcForm.getWflxId().split(",");
				for (int j = 0; j < wflxIds.length; j++) {
					TBizTaskillegaltype tBizTaskillegaltype = new TBizTaskillegaltype();
					tBizTaskillegaltype.setTaskid(applyId);
					tBizTaskillegaltype.setIllegaltypeid(wflxIds[j]);
					this.dao.save(tBizTaskillegaltype);
				}
			}
			
			/*//�������ݱ������д������
			try {
				ZipOutputStream out = new ZipOutputStream(new FileOutputStream("D:\\outfile.zip"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */

		}

		// ��������
		if (blMainForm.getBlXqzlForm() != null) {
			BlXqzlForm blXqzlForm = blMainForm.getBlXqzlForm();
			// 1�������
			if (StringUtils.isNotBlank(blXqzlForm.getJcr())) {
				// 2015-7-15 �޸� ����˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.XQZL_JCR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blXqzlForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(blXqzlForm.getJcr().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							blXqzlForm.getJcr().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.XQZL_JCR.getCode());
					this.dao.save(taskuser);
				}
			}
			// 2����¼��
			if (StringUtils.isNotBlank(blXqzlForm.getJlr())) {
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.XQZL_JLR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blXqzlForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blXqzlForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.XQZL_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 3�����ʱ�䣻4�����ģ��
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blXqzlForm.getJcsj1()
						+ "', t.JCSJ2_='" + blXqzlForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blXqzlForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blXqzlForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.JCMB_='").append(blXqzlForm.getJcmbId())
					.append("'");
			sql.append(" ,t.JCDD_='").append(blXqzlForm.getJcdd())
			.append("'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.XQZL.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 5��������
			try {
				saveJcjl(applyId,blMainForm.blXqzlForm.getRwlx(), blMainForm.blXqzlForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ���ټ��
		if (blMainForm.getBlGzjcForm() != null) {
			BlGzjcForm blGzjcForm = blMainForm.getBlGzjcForm();
			// 1�������
			if (StringUtils.isNotBlank(blGzjcForm.getJcr())) {
				// 2015-7-15 �޸� ����˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.GZJC_JCR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blGzjcForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(blGzjcForm.getJcr().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							blGzjcForm.getJcr().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.GZJC_JCR.getCode());
					this.dao.save(taskuser);
				}
			}
			// 2����¼��
			if (StringUtils.isNotBlank(blGzjcForm.getJlr())) {
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.GZJC_JLR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blGzjcForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blGzjcForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.GZJC_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 3�����ʱ�䣻4�����ģ��
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blGzjcForm.getJcsj1()
						+ "', t.JCSJ2_='" + blGzjcForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blGzjcForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blGzjcForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.JCMB_='").append(blGzjcForm.getJcmbId())
					.append("'");
			sql.append(" ,t.JCDD_='").append(blGzjcForm.getJcdd())
			.append("'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.GZJC.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 5��������
			try {
				saveJcjl(applyId,blMainForm.blGzjcForm.getRwlx(), blMainForm.blGzjcForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// �Զ����
		if (blMainForm.getBlZdjkForm() != null) {
			BlZdjkForm blZdjkForm = blMainForm.getBlZdjkForm();
			// 1�������
			if (StringUtils.isNotBlank(blZdjkForm.getJcr())) {
				// 2015-7-15 �޸� ����˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.ZDJK_JCR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blZdjkForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(blZdjkForm.getJcr().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							blZdjkForm.getJcr().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.ZDJK_JCR.getCode());
					this.dao.save(taskuser);
				}
			}
			// 2����¼��
			if (StringUtils.isNotBlank(blZdjkForm.getJlr())) {
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.ZDJK_JLR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blZdjkForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blZdjkForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.ZDJK_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 3�����ʱ�䣻4�����ģ��
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blZdjkForm.getJcsj1()
						+ "', t.JCSJ2_='" + blZdjkForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blZdjkForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blZdjkForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.JCMB_='").append(blZdjkForm.getJcmbId())
					.append("'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.ZDJK.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 5��������
			try {
				saveJcjl(applyId,blMainForm.blZdjkForm.getRwlx(), blMainForm.blZdjkForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Σ�շ���
		if (blMainForm.getBlWxfwForm() != null) {
			BlWxfwForm blWxfwForm = blMainForm.getBlWxfwForm();
			// 1�������
			if (StringUtils.isNotBlank(blWxfwForm.getJcr())) {
				// 2015-7-15 �޸� ����˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.WXFW_JCR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blWxfwForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(blWxfwForm.getJcr().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							blWxfwForm.getJcr().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.WXFW_JCR.getCode());
					this.dao.save(taskuser);
				}
			}
			// 2����¼��
			if (StringUtils.isNotBlank(blWxfwForm.getJlr())) {
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.WXFW_JLR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blWxfwForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blWxfwForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.WXFW_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 3�����ʱ�䣻4�����ģ��
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blWxfwForm.getJcsj1()
						+ "', t.JCSJ2_='" + blWxfwForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blWxfwForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blWxfwForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.JCMB_='").append(blWxfwForm.getJcmbId())
					.append("'");
			sql.append(" ,t.JCDD_='").append(blWxfwForm.getJcdd())
			.append("'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.WXFW.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 5��������
			try {
				saveJcjl(applyId,blMainForm.blWxfwForm.getRwlx(), blMainForm.blWxfwForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Σ�ջ�ѧƷ
		if (blMainForm.getBlWxhxpForm() != null) {
			BlWxhxpForm blWxhxpForm = blMainForm.getBlWxhxpForm();
			// 1�������
			if (StringUtils.isNotBlank(blWxhxpForm.getJcr())) {
				// 2015-7-15 �޸� ����˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.WXHXP_JCR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blWxhxpForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(blWxhxpForm.getJcr().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							blWxhxpForm.getJcr().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.WXHXP_JCR.getCode());
					this.dao.save(taskuser);
				}
			}
			// 2����¼��
			if (StringUtils.isNotBlank(blWxhxpForm.getJlr())) {
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.WXHXP_JLR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blWxhxpForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blWxhxpForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.WXHXP_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 3�����ʱ�䣻4�����ģ��
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blWxhxpForm.getJcsj1()
						+ "', t.JCSJ2_='" + blWxhxpForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blWxhxpForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blWxhxpForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.JCMB_='").append(blWxhxpForm.getJcmbId()).append(
					"'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.WXHXP.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 5��������
			try {
				saveJcjl(applyId,blMainForm.blWxhxpForm.getRwlx(), blMainForm.blWxhxpForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ���䰲ȫ
		if (blMainForm.getBlFsaqForm() != null) {
			BlFsaqForm blFsaqForm = blMainForm.getBlFsaqForm();
			// 1�������
			if (StringUtils.isNotBlank(blFsaqForm.getJcr())) {
				// 2015-7-15 �޸� ����˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.FSAQ_JCR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blFsaqForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(blFsaqForm.getJcr().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							blFsaqForm.getJcr().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.FSAQ_JCR.getCode());
					this.dao.save(taskuser);
				}
			}
			// 2����¼��
			if (StringUtils.isNotBlank(blFsaqForm.getJlr())) {
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.FSAQ_JLR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blFsaqForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blFsaqForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.FSAQ_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 3�����ʱ�䣻4�����ģ��
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blFsaqForm.getJcsj1()
						+ "', t.JCSJ2_='" + blFsaqForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blFsaqForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blFsaqForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.JCMB_='").append(blFsaqForm.getJcmbId())
					.append("'");
			sql.append(" ,t.JCDD_='").append(blFsaqForm.getJcdd())
			.append("'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.FSAQ.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 5��������
			try {
				saveJcjl(applyId,blMainForm.blFsaqForm.getRwlx(), blMainForm.blFsaqForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ��Ⱦ�¹��ֳ�����
		if (blMainForm.getBlWrsgxcdcForm() != null) {
			BlWrsgxcdcForm blWrsgxcdcForm = blMainForm.getBlWrsgxcdcForm();
			// 1�������
			if (StringUtils.isNotBlank(blWrsgxcdcForm.getJcr())) {
				// 2015-7-15 �޸� ����˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.WRSGXCDC_JCR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blWrsgxcdcForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(applyId);
					taskuser.setUserid(blWrsgxcdcForm.getJcr().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							blWrsgxcdcForm.getJcr().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.WRSGXCDC_JCR.getCode());
					this.dao.save(taskuser);
				}
			}
			// 2����¼��
			if (StringUtils.isNotBlank(blWrsgxcdcForm.getJlr())) {
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.WRSGXCDC_JLR.getCode() + "'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser = new TBizTaskuser();
				taskuser.setTaskid(applyId);
				taskuser.setUserid(blWrsgxcdcForm.getJlr());
				TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
						blWrsgxcdcForm.getJlr());
				taskuser.setUsername(user.getName());
				taskuser.setType(TaskUserEnum.WRSGXCDC_JLR.getCode());
				this.dao.save(taskuser);
			}
			// 3�����ʱ�䣻4�����ģ��
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			//���߰�sql����ƴд
			if(("0").equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blWrsgxcdcForm.getJcsj1()
						+ "', t.JCSJ2_='" + blWrsgxcdcForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blWrsgxcdcForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blWrsgxcdcForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			sql.append(" ,t.JCMB_='").append(blWrsgxcdcForm.getJcmbId())
					.append("'");
			sql.append(" ,t.JCDD_='").append(blWrsgxcdcForm.getJcdd())
			.append("'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(
					TaskTypeCode.WRSGXCDC.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 5��������
			try {
				saveJcjl(applyId,blMainForm.blWrsgxcdcForm.getRwlx(), blMainForm.blWrsgxcdcForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// �ճ��칫
		if (blMainForm.getBlRcbgForm() != null) {
			BlRcbgForm blRcbgForm = blMainForm.getBlRcbgForm();
			// 1���ճ��칫��ע
			try {
				saveRcbg(applyId,blRcbgForm.getRwlx(), blRcbgForm.getDesc());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return xftsId;
	}

	@Override
	public ResultBean showBlPage(String applyId) {
		ResultBean rb = new ResultBean();
		// ���������б�
		List<Map<String, String>> rwlxlistMap = this
				.getTaskTypeByTaskId(applyId);
		if (rwlxlistMap != null && rwlxlistMap.size() > 0) {
			if (rwlxlistMap.size() == 1
					&& rwlxlistMap.get(0).get("id").equals(
							TaskTypeCode.ZXXD.getCode())) {// ר���ж�
				rb.setResult(true);
				rb.setMsg("ר���ж�");
			} else {
				rb.setMsg("����");
			}
		}
		return rb;
	}

	@Override
	public Map<String, String> getKcxwblFile(String applyId, String bllx) {
		Map<String, String> kcxwblFileMap = null;
		if (bllx.equals("xwbl")) {
			// ѯ�ʱ�¼
			FyWebResult re = commonManager.queryFileList(applyId, "N",
					FileTypeEnums.WFAJDCXWBL.getCode(), "1", null);
			List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
			rowsList = re.getRows();
			if (rowsList.size() > 0) {
				kcxwblFileMap = rowsList.get(0);
			}
		}
		if (bllx.equals("kcbl")) {
			// �����¼
			FyWebResult re2 = commonManager.queryFileList(applyId, "N",
					FileTypeEnums.WFAJDCKCBL.getCode(), "1", null);
			List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
			rowsList2 = re2.getRows();
			if (rowsList2.size() > 0) {
				kcxwblFileMap = rowsList2.get(0);
			}
		}
		if (bllx.equals("ZDJKJCJL")) {
			// �����¼
			FyWebResult re2 = commonManager.queryFileList(applyId, "N",
					FileTypeEnums.ZDJKJCJL.getCode(), "1", null);
			List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
			rowsList2 = re2.getRows();
			if (rowsList2.size() > 0) {
				kcxwblFileMap = rowsList2.get(0);
			}
		}
		return kcxwblFileMap;
	}

	@Override
	public Map<String, String> getCommonFile(String applyId,
			String fileTypeEnumName) {
		Map<String, String> commonFileMap = null;
		// ѯ�ʱ�¼
		FyWebResult re = commonManager.queryFileList(applyId, "N",
				FileTypeEnums.getTypeByEnumName(fileTypeEnumName), "1", null);
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		rowsList = re.getRows();
		if (rowsList.size() > 0) {
			commonFileMap = rowsList.get(0);
		}
		return commonFileMap;
	}

	@Override
	public ResultBean checkBlZB(String applyId) {
		ResultBean rb = new ResultBean();
		Work work = workDao.get(applyId);

		StringBuffer msg = new StringBuffer();
		List<Map<String, String>> rwlxMap = this.getTaskTypeByTaskId(applyId);
		if (rwlxMap != null && rwlxMap.size() == 1 && "24".equals(rwlxMap.get(0).get("id"))){//�ճ��칫
			rb.setResult(true);
			rb.setMsg("��֤ͨ��");
			return rb;
		}else if(rwlxMap != null && rwlxMap.size() == 1 && "13".equals(rwlxMap.get(0).get("id"))){//�ճ��칫
			//��Ա�滮Э����
			List<Map<String, String>> xbryMap = this.ryghTableData_xbry(applyId);
			if (xbryMap == null || xbryMap.size() <= 0) {
				msg.append("'Э����',\n");
			}
			if (StringUtils.isNotBlank(msg.toString())) {
				rb.setResult(false);
				rb.setMsg("����ȱ�ٱ����\n" + msg.toString() + "�����°�����");
				return rb;
			} else {
				rb.setResult(true);
				rb.setMsg("��֤ͨ��");
				return rb;
			}
		}else{
			// 1��ִ����������
			if (StringUtils.isBlank(work.getZfdxType())) {
				msg.append("'ִ����������',\n");
			}
			// 2��ִ������
			List<Map<String, String>> zfdxMap = this.zfdxTableData(applyId);
			if (zfdxMap == null || zfdxMap.size() <= 0) {
				msg.append("'ִ������',\n");
			}
			// 3����������
			//List<Map<String, String>> rwlxMap = this.getTaskTypeByTaskId(applyId);
			if (rwlxMap == null || rwlxMap.size() <= 0) {
				msg.append("'��������',\n");
			}
			// 4����Ա�滮Э����
			List<Map<String, String>> xbryMap = this.ryghTableData_xbry(applyId);
			if (xbryMap == null || xbryMap.size() <= 0) {
				msg.append("'Э����',\n");
			}

			if (StringUtils.isNotBlank(msg.toString())) {
				rb.setResult(false);
				rb.setMsg("����ȱ�ٱ����\n" + msg.toString() + "�����°�����");
				return rb;
			} else {
				rb.setResult(true);
				rb.setMsg("��֤ͨ��");
				return rb;
			}
		}
	}

	@Override
	public ResultBean checkBlBL(String applyId,String zfdxInfo) {
		List<Map<String, String>> rwlxMap = this.getTaskTypeByTaskId(applyId);
		ResultBean rb = new ResultBean();
		if (rwlxMap != null && rwlxMap.size() == 1 && "24".equals(rwlxMap.get(0).get("id"))){//�ճ��칫
			rb.setResult(true);
			rb.setMsg("��֤ͨ��");
			return rb;
		}else if(rwlxMap != null && rwlxMap.size() == 1 && "13".equals(rwlxMap.get(0).get("id"))){//�ŷ�Ͷ��
			if(StringUtils.equals(zfdxInfo, "Y")){
				return this.checkCommonBlBL(applyId);
			}else{
				return this.checkXftsBlqk(applyId);
			}
		}else{
			// /////a����У����ר����������������//////
			ResultBean isZx = this.showBlPage(applyId);
			if (isZx.getResult()) {// ר��
				return zxWorkManager.checkZxBlBL(applyId);
			} else {
				return this.checkCommonBlBL(applyId);
			}
		}
	}
	//�����ŷ�Ͷ�߰������
	private ResultBean checkXftsBlqk(String applyId) {
		ResultBean rb = new ResultBean();

		StringBuffer msg = new StringBuffer();
		//�ŷ�Ͷ�߰���
			String fileType = "XFTSBJDSMJ,XFTSXZWS,XFTSQTZL,XFTSCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'�ŷ�Ͷ��:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
		if (StringUtils.isNotBlank(msg.toString())) {
			rb.setResult(false);
			rb.setMsg("����ȱ�ٱ����\n" + msg.toString() + "�����°�����");
			return rb;
		} else {
			rb.setResult(true);
			rb.setMsg("��֤ͨ��");
			return rb;
		}
	}
	private ResultBean checkCommonBlBL(String applyId) {
		//���߰��ʶ���ӣ��������߰����ݱ��棬Ȼ����
		String biaoshi = indexManager.sysVer;
		ResultBean rb = new ResultBean();
		Work work = workDao.get(applyId);

		StringBuffer msg = new StringBuffer();
		BlMainForm blMainForm = this.getBlMainFormData(applyId);

		// ��ҵ��Ϣ
		if (blMainForm.getBlZfdxForm() != null) {
			// 1������������
			if (StringUtils.isBlank(blMainForm.getBlZfdxForm().getBjcr())) {
				msg.append("'��ҵ��Ϣ:����������',\n");
			}
			// 2��ְ��
			if (StringUtils.isBlank(blMainForm.getBlZfdxForm().getZw())) {
				msg.append("'��ҵ��Ϣ:ְ��',\n");
			}
			// 3����ϵ�绰
			if (StringUtils.isBlank(blMainForm.getBlZfdxForm().getLxdh())) {
				msg.append("'��ҵ��Ϣ:��ϵ�绰',\n");
			}
		}

		// �ֳ����
		if (blMainForm.getBlRcjcForm() != null) {
			// 1�������
			if (StringUtils.isBlank(blMainForm.getBlRcjcForm().getJcr())) {
				msg.append("'�ֳ����:�����',\n");
			}
			// 2����¼��
			if (StringUtils.isBlank(blMainForm.getBlRcjcForm().getJlr())) {
				msg.append("'�ֳ����:��¼��',\n");
			}
			// 3����鿪ʼʱ��
			if (StringUtils.isBlank(blMainForm.getBlRcjcForm().getJcsj1())) {
				msg.append("'�ֳ����:��鿪ʼʱ��',\n");
			}
			// 4������ֹʱ��
			if (StringUtils.isBlank(blMainForm.getBlRcjcForm().getJcsj2())) {
				msg.append("'�ֳ����:����ֹʱ��',\n");
			}
			// 5�����ģ��

			// ////////////////��������У��////////////////
			String fileType = "RCJCJCJL,RCJCJCJLSMJ,RCJCXZWS,RCJCQTZL,RCJCCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'�ֳ����:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
			// 6��������
			if (StringUtils.isBlank(blMainForm.getBlRcjcForm().getJcjl())) {
				msg.append("'�ֳ����:������',\n");
			}
		}

		// ��Ⱥ˲�
		if (blMainForm.getBlNdhcForm() != null) {
			// 1�������
			if (StringUtils.isBlank(blMainForm.getBlNdhcForm().getJcr())) {
				msg.append("'��Ⱥ˲�:�����',\n");
			}
			// 2����¼��
			if (StringUtils.isBlank(blMainForm.getBlNdhcForm().getJlr())) {
				msg.append("'��Ⱥ˲�:��¼��',\n");
			}
			// 3�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlNdhcForm().getJcsj1())) {
				msg.append("'��Ⱥ˲�:��鿪ʼʱ��',\n");
			}
			// 4�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlNdhcForm().getJcsj2())) {
				msg.append("'��Ⱥ˲�:������ʱ��',\n");
			}
			// 5�����ģ��

			// ////////////////��������У��////////////////
			String fileType = "NDHCJCJL,NDHCJCJLSMJ,NDHCXZWS,NDHCQTZL,NDHCCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'��Ⱥ˲�:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
			// 5��������
			if (StringUtils.isBlank(blMainForm.getBlNdhcForm().getJcjl())) {
				msg.append("'��Ⱥ˲�:������',\n");
			}
		}

		// �󶽲�
		if (blMainForm.getBlHdcForm() != null) {
			// 1�������
			if (StringUtils.isBlank(blMainForm.getBlHdcForm().getJcr())) {
				msg.append("'�󶽲�:�����',\n");
			}
			// 2����¼��
			if (StringUtils.isBlank(blMainForm.getBlHdcForm().getJlr())) {
				msg.append("'�󶽲�:��¼��',\n");
			}
			// 3�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlHdcForm().getJcsj1())) {
				msg.append("'�󶽲�:��鿪ʼʱ��',\n");
			}
			// 4�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlHdcForm().getJcsj2())) {
				msg.append("'�󶽲�:������ʱ��',\n");
			}
			// 5�����ģ��

			// ////////////////��������У��////////////////
			String fileType = "HDCJCJL,HDCJCJLSMJ,HDCXZWS,HDCQTZL,HDCCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'�󶽲�:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
			// 5��������
			if (StringUtils.isBlank(blMainForm.getBlHdcForm().getJcjl())) {
				msg.append("'�󶽲�:������',\n");
			}
		}

		// �ŷ�Ͷ��
		if (blMainForm.getBlXftsForm() != null) {
			// 1�������
			if (StringUtils.isBlank(blMainForm.getBlXftsForm().getJcr())) {
				msg.append("'�ŷ�Ͷ��:�����',\n");
			}
			// 2����¼��
			if (StringUtils.isBlank(blMainForm.getBlXftsForm().getJlr())) {
				msg.append("'�ŷ�Ͷ��:��¼��',\n");
			}
			// 3�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlXftsForm().getJcsj1())) {
				msg.append("'�ŷ�Ͷ��:��鿪ʼʱ��',\n");
			}
			// 4�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlXftsForm().getJcsj2())) {
				msg.append("'�ŷ�Ͷ��:������ʱ��',\n");
			}
			// 5���ŷ�Ͷ����Դ
			if (StringUtils.isBlank(blMainForm.getBlXftsForm().getXftsly())) {
				msg.append("'�ŷ�Ͷ��:�ŷ�Ͷ����Դ',\n");
			}

			// 5�����ģ��

			// ////////////////��������У��////////////////
			String fileType = "XFTSJCJL,XFTSBJDSMJ,XFTSJCJLSMJ,XFTSXZWS,XFTSQTZL,XFTSCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'�ŷ�Ͷ��:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
			// 6��������
			if (StringUtils.isBlank(blMainForm.getBlXftsForm().getJcjl())) {
				msg.append("'�ŷ�Ͷ��:������',\n");
			}
		}

		// ��������֤���
		if (blMainForm.getBlPwxkzjcForm() != null) {
			// 1�������
			if (StringUtils.isBlank(blMainForm.getBlPwxkzjcForm().getJcr())) {
				msg.append("'��������֤���:�����',\n");
			}
			// 2����¼��
			if (StringUtils.isBlank(blMainForm.getBlPwxkzjcForm().getJlr())) {
				msg.append("'��������֤���:��¼��',\n");
			}
			// 3�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlPwxkzjcForm().getJcsj1())) {
				msg.append("'��������֤���:��鿪ʼʱ��',\n");
			}
			// 4�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlPwxkzjcForm().getJcsj2())) {
				msg.append("'��������֤���:������ʱ��',\n");
			}
			// 5�����ģ��

			// ////////////////��������У��////////////////
			String fileType = "PWXKZJCJCJL,PWXKZJCJCJLSMJ,PWXKZJCXZWS,PWXKZJCQTZL,PWXKZJCCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'��������֤���:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
			// 5��������
			if (StringUtils.isBlank(blMainForm.getBlPwxkzjcForm().getJcjl())) {
				msg.append("'��������֤���:������',\n");
			}
		}

		// Υ����������
		if (blMainForm.getBlWfajdcForm() != null) {
			// 1�������Ǽ�ʱ��
			if (StringUtils.isBlank(blMainForm.getBlWfajdcForm().getLadjsj())) {
				msg.append("'Υ����������:�����Ǽ�ʱ��',\n");
			}
			// 2��Υ������
			if (StringUtils.isBlank(blMainForm.getBlWfajdcForm().getWflxId())) {
				msg.append("'Υ����������:Υ������',\n");
			}
			// 3��Υ����������
			if (StringUtils.isBlank(blMainForm.getBlWfajdcForm().getWfajmc())) {
				msg.append("'Υ����������:Υ����������',\n");
			}
			// 4����¼��
			if (StringUtils.isBlank(blMainForm.getBlWfajdcForm().getJlr())) {
				msg.append("'Υ����������:��¼��',\n");
			}

			// ////////////////��������У��////////////////
			String fileType = "WFAJDCLADJB,WFAJDCLADJSMJ,WFAJDCKCBL,WFAJDCKCBLSMJ,WFAJDCXWBL,WFAJDCXWBLSMJ,WFAJDCSZDZJZL,WFAJDCSTZLTP,WFAJDCYPZL,WFAJDCSTZLLX,WFAJDCSTZLDYFJ,WFAJDCXZWS,WFAJDCQTZL,WFAJDCDCBG";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'Υ����������:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
		}

		// ��������
		if (blMainForm.getBlXqzlForm() != null) {
			// 1�������
			if (StringUtils.isBlank(blMainForm.getBlXqzlForm().getJcr())) {
				msg.append("'��������:�����',\n");
			}
			// 2����¼��
			if (StringUtils.isBlank(blMainForm.getBlXqzlForm().getJlr())) {
				msg.append("'��������:��¼��',\n");
			}
			// 3�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlXqzlForm().getJcsj1())) {
				msg.append("'��������:��鿪ʼʱ��',\n");
			}
			// 4�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlXqzlForm().getJcsj2())) {
				msg.append("'��������:������ʱ��',\n");
			}
			// 5�����ģ��

			// ////////////////��������У��////////////////
			String fileType = "XQZLJCJL,XQZLJCJLSMJ,XQZLXZWS,XQZLQTZL,XQZLCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'��������:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
			// 5��������
			if (StringUtils.isBlank(blMainForm.getBlXqzlForm().getJcjl())) {
				msg.append("'��������:������',\n");
			}
		}

		// ���ټ��
		if (blMainForm.getBlGzjcForm() != null) {
			// 1�������
			if (StringUtils.isBlank(blMainForm.getBlGzjcForm().getJcr())) {
				msg.append("'���ټ��:�����',\n");
			}
			// 2����¼��
			if (StringUtils.isBlank(blMainForm.getBlGzjcForm().getJlr())) {
				msg.append("'���ټ��:��¼��',\n");
			}
			// 3�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlGzjcForm().getJcsj1())) {
				msg.append("'���ټ��:��鿪ʼʱ��',\n");
			}
			// 4�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlGzjcForm().getJcsj2())) {
				msg.append("'���ټ��:������ʱ��',\n");
			}
			// 5�����ģ��

			// ////////////////��������У��////////////////
			String fileType = "GZJCJCJL,GZJCJCJLSMJ,GZJCXZWS,GZJCQTZL,GZJCCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'���ټ��:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
			// 5��������
			if (StringUtils.isBlank(blMainForm.getBlGzjcForm().getJcjl())) {
				msg.append("'���ټ��:������',\n");
			}
		}

		// �Զ����
		if (blMainForm.getBlZdjkForm() != null) {
			// 1�������
			if (StringUtils.isBlank(blMainForm.getBlZdjkForm().getJcr())) {
				msg.append("'�Զ����:�����',\n");
			}
			// 2����¼��
			if (StringUtils.isBlank(blMainForm.getBlZdjkForm().getJlr())) {
				msg.append("'�Զ����:��¼��',\n");
			}
			// 3�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlZdjkForm().getJcsj1())) {
				msg.append("'�Զ����:��鿪ʼʱ��',\n");
			}
			// 4�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlZdjkForm().getJcsj2())) {
				msg.append("'�Զ����:������ʱ��',\n");
			}
			// 5�����ģ��

			// ////////////////��������У��////////////////
			String fileType = "ZDJKJCJL,ZDJKJCJLSMJ,ZDJKXZWS,ZDJKQTZL,ZDJKCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'�Զ����:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
			// 5��������
			if (StringUtils.isBlank(blMainForm.getBlZdjkForm().getJcjl())) {
				msg.append("'�Զ����:������',\n");
			}
		}

		// Σ�շ���
		if (blMainForm.getBlWxfwForm() != null) {
			// 1�������
			if (StringUtils.isBlank(blMainForm.getBlWxfwForm().getJcr())) {
				msg.append("'Σ�շ���:�����',\n");
			}
			// 2����¼��
			if (StringUtils.isBlank(blMainForm.getBlWxfwForm().getJlr())) {
				msg.append("'Σ�շ���:��¼��',\n");
			}
			// 3�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlWxfwForm().getJcsj1())) {
				msg.append("'Σ�շ���:��鿪ʼʱ��',\n");
			}
			// 4�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlWxfwForm().getJcsj2())) {
				msg.append("'Σ�շ���:������ʱ��',\n");
			}
			// 5�����ģ��

			// ////////////////��������У��////////////////
			String fileType = "WXFWJCJL,WXFWJCJLSMJ,WXFWXZWS,WXFWQTZL,WXFWCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'Σ�շ���:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
			// 5��������
			if (StringUtils.isBlank(blMainForm.getBlWxfwForm().getJcjl())) {
				msg.append("'Σ�շ���:������',\n");
			}
		}

		// Σ�ջ�ѧƷ
		if (blMainForm.getBlWxhxpForm() != null) {
			// 1�������
			if (StringUtils.isBlank(blMainForm.getBlWxhxpForm().getJcr())) {
				msg.append("'Σ�ջ�ѧƷ:�����',\n");
			}
			// 2����¼��
			if (StringUtils.isBlank(blMainForm.getBlWxhxpForm().getJlr())) {
				msg.append("'Σ�ջ�ѧƷ:��¼��',\n");
			}
			// 3�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlWxhxpForm().getJcsj1())) {
				msg.append("'Σ�ջ�ѧƷ:��鿪ʼʱ��',\n");
			}
			// 4�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlWxhxpForm().getJcsj2())) {
				msg.append("'Σ�ջ�ѧƷ:������ʱ��',\n");
			}
			// 5�����ģ��

			// ////////////////��������У��////////////////
			String fileType = "WXHXPJCJL,WXHXPJCJLSMJ,WXHXPXZWS,WXHXPQTZL,WXHXPCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'Σ�ջ�ѧƷ:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
			// 5��������
			if (StringUtils.isBlank(blMainForm.getBlWxhxpForm().getJcjl())) {
				msg.append("'Σ�ջ�ѧƷ:������',\n");
			}
		}

		// ���䰲ȫ
		if (blMainForm.getBlFsaqForm() != null) {
			// 1�������
			if (StringUtils.isBlank(blMainForm.getBlFsaqForm().getJcr())) {
				msg.append("'���䰲ȫ:�����',\n");
			}
			// 2����¼��
			if (StringUtils.isBlank(blMainForm.getBlFsaqForm().getJlr())) {
				msg.append("'���䰲ȫ:��¼��',\n");
			}
			// 3�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlFsaqForm().getJcsj1())) {
				msg.append("'���䰲ȫ:��鿪ʼʱ��',\n");
			}
			// 4�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlFsaqForm().getJcsj2())) {
				msg.append("'���䰲ȫ:������ʱ��',\n");
			}
			// 5�����ģ��

			// ////////////////��������У��////////////////
			String fileType = "FSAQJCJL,FSAQJCJLSMJ,FSAQXZWS,FSAQQTZL,FSAQCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'���䰲ȫ:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
			// 5��������
			if (StringUtils.isBlank(blMainForm.getBlFsaqForm().getJcjl())) {
				msg.append("'���䰲ȫ:������',\n");
			}
		}

		// ��Ⱦ�¹��ֳ�����
		if (blMainForm.getBlWrsgxcdcForm() != null) {
			// 1�������
			if (StringUtils.isBlank(blMainForm.getBlWrsgxcdcForm().getJcr())) {
				msg.append("'��Ⱦ�¹��ֳ�����:�����',\n");
			}
			// 2����¼��
			if (StringUtils.isBlank(blMainForm.getBlWrsgxcdcForm().getJlr())) {
				msg.append("'��Ⱦ�¹��ֳ�����:��¼��',\n");
			}
			// 3�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlWrsgxcdcForm().getJcsj1())) {
				msg.append("'��Ⱦ�¹��ֳ�����:��鿪ʼʱ��',\n");
			}
			// 3�����ʱ��
			if (StringUtils.isBlank(blMainForm.getBlWrsgxcdcForm().getJcsj2())) {
				msg.append("'��Ⱦ�¹��ֳ�����:������ʱ��',\n");
			}
			// 4�����ģ��

			// ////////////////��������У��////////////////
			String fileType = "WRSGXCDCJCJL,WRSGXCDCJCJLSMJ,WRSGXCDCXZWS,WRSGXCDCQTZL,WRSGXCDCCLYJS";
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = commonManager.getIsBtlist(fileType);
			for (int i = 0; i < list.size(); i++) {
				if ("Y".equals(list.get(i).get("isBT"))) {// ����
					FyWebResult re = commonManager.queryFileList(applyId, "N",
							list.get(i).get("fileTypeEnumName"), "", "1", null);
					List<Map<String, String>> reList = re.getRows();
					if (reList == null || reList.size() <= 0) {
						msg.append("'��Ⱦ�¹��ֳ�����:"
								+ FileTypeEnums.getNameByCode(FileTypeEnums
										.getTypeByEnumName(list.get(i).get(
												"fileTypeEnumName"))) + "',\n");
					}
				}
			}
			// 5��������
			if (StringUtils.isBlank(blMainForm.getBlWrsgxcdcForm().getJcjl())) {
				msg.append("'��Ⱦ�¹��ֳ�����:������',\n");
			}
		}

		if (StringUtils.isNotBlank(msg.toString())) {
			rb.setResult(false);
			if("0".equals(biaoshi)){
				rb.setMsg("����ȱ�ٱ����\n" + msg.toString() + "���ȵ�����棬�ٽ�����ɴ����");
			}else{
				rb.setMsg("����ȱ�ٱ����\n" + msg.toString() + "�����°�����");
			}
			return rb;
		} else {
			rb.setResult(true);
			rb.setMsg("��֤ͨ��");
			return rb;
		}
	}

	@Override
	public List<Combobox> queryBlFileTypeCombo(String rwlx,String zfdxInfo) {
		List<Combobox> re = new ArrayList<Combobox>();

		String[] code = null;
		List<TSysDic> dics = new ArrayList<TSysDic>();
		if (rwlx.equals(TaskTypeCode.RCJC.getCode())) {// �ֳ����
			code = new String[] { 
					FileTypeEnums.RCJCJCJLSMJ.getCode(),
					FileTypeEnums.RCJCXZWS.getCode(),
					FileTypeEnums.RCJCQTZL.getCode(),
					FileTypeEnums.RCJCCLYJS.getCode(),
					FileTypeEnums.RCJCSPZL.getCode(),
					FileTypeEnums.RCJCLYZL.getCode(),
					FileTypeEnums.RCJCZP.getCode(),
					FileTypeEnums.RCJCHPPFWJ.getCode(),
					FileTypeEnums.RCJCYSPFWJ.getCode()};
		} else if (rwlx.equals(TaskTypeCode.NDHC.getCode())) {// ��Ⱥ˲�
			code = new String[] {
					FileTypeEnums.NDHCJCJLSMJ.getCode(),
					FileTypeEnums.NDHCXZWS.getCode(),
					FileTypeEnums.NDHCQTZL.getCode(),
					FileTypeEnums.NDHCCLYJS.getCode(),
					FileTypeEnums.NDHCSPZL.getCode(),
					FileTypeEnums.NDHCLYZL.getCode(),
					FileTypeEnums.NDHCZP.getCode(),
					FileTypeEnums.NDHCHPPFWJ.getCode(),
					FileTypeEnums.NDHCYSPFWJ.getCode()};
		} else if (rwlx.equals(TaskTypeCode.HDC.getCode())) {// �󶽲�
			code = new String[] { 
					FileTypeEnums.HDCJCJLSMJ.getCode(),
					FileTypeEnums.HDCXZWS.getCode(),
					FileTypeEnums.HDCQTZL.getCode(),
					FileTypeEnums.HDCCLYJS.getCode(),
					FileTypeEnums.HDCSPZL.getCode(),
					FileTypeEnums.HDCLYZL.getCode(),
					FileTypeEnums.HDCZP.getCode(),
					FileTypeEnums.HDCHPPFWJ.getCode(),
					FileTypeEnums.HDCYSPFWJ.getCode()};
		} else if (rwlx.equals(TaskTypeCode.XFTS.getCode())) {// �ŷ�Ͷ��
			if(StringUtils.isNotBlank(zfdxInfo) && StringUtils.equals(zfdxInfo, "Y")){
				code = new String[] { 
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
			}else{
				code = new String[] { 
						FileTypeEnums.XFTSBJDSMJ.getCode(),
						FileTypeEnums.XFTSXZWS.getCode(),
						FileTypeEnums.XFTSQTZL.getCode(),
						FileTypeEnums.XFTSCLYJS.getCode(),
						FileTypeEnums.XFTSSPZL.getCode(),
						FileTypeEnums.XFTSLYZL.getCode(),
						FileTypeEnums.XFTSZP.getCode(),
						FileTypeEnums.XFTSHPPFWJ.getCode(),
						FileTypeEnums.XFTSYSPFWJ.getCode()};
			}
		} else if (rwlx.equals(TaskTypeCode.PWXKZJC.getCode())) {// ��������֤���
			code = new String[] { 
					FileTypeEnums.PWXKZJCJCJLSMJ.getCode(),
					FileTypeEnums.PWXKZJCXZWS.getCode(),
					FileTypeEnums.PWXKZJCQTZL.getCode(),
					FileTypeEnums.PWXKZJCCLYJS.getCode(),
					FileTypeEnums.PWXKZSPZL.getCode(),
					FileTypeEnums.PWXKZLYZL.getCode(),
					FileTypeEnums.PWXKZZP.getCode(),
					FileTypeEnums.PWXKZHPPFWJ.getCode(),
					FileTypeEnums.PWXKZYSPFWJ.getCode()};
		} else if (rwlx.equals(TaskTypeCode.ZXXD.getCode())) {// ר���ж�
			code = new String[] { FileTypeEnums.ZXXDJCBG.getCode(),
					FileTypeEnums.ZXXDQTZL.getCode() };
		} else if (rwlx.equals(TaskTypeCode.WFAJ.getCode())) {// Υ����������
			code = new String[] { 
					FileTypeEnums.WFAJDCLADJB.getCode(),
					FileTypeEnums.WFAJDCLADJSMJ.getCode(),
					FileTypeEnums.WFAJDCKCBLSMJ.getCode(),
					FileTypeEnums.WFAJDCXWBLSMJ.getCode(),
					FileTypeEnums.WFAJDCSZDZJZL.getCode(),
					FileTypeEnums.WFAJDCSTZLTP.getCode(),
					FileTypeEnums.WFAJDCYPZL.getCode(),
					FileTypeEnums.WFAJDCSTZLLX.getCode(),
					FileTypeEnums.WFAJDCXZWS.getCode(),
					FileTypeEnums.WFAJDCQTZL.getCode(),
					FileTypeEnums.WFAJDCDCBG.getCode(),
					FileTypeEnums.WFAJDCHPPFWJ.getCode(),
					FileTypeEnums.WFAJDCYSPFWJ.getCode()};
		} else if (rwlx.equals(TaskTypeCode.XQZL.getCode())) {// ��������
			code = new String[] { 
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
		} else if (rwlx.equals(TaskTypeCode.GZJC.getCode())) {// ���ټ��
			code = new String[] { 
					FileTypeEnums.GZJCJCJLSMJ.getCode(),
					FileTypeEnums.GZJCXZWS.getCode(),
					FileTypeEnums.GZJCQTZL.getCode(),
					FileTypeEnums.GZJCCLYJS.getCode(),
					FileTypeEnums.GZJCSPZL.getCode(),
					FileTypeEnums.GZJCLYZL.getCode(),
					FileTypeEnums.GZJCZP.getCode(),
					FileTypeEnums.GZJCHPPFWJ.getCode(),
					FileTypeEnums.GZJCYSPFWJ.getCode()};
		} else if (rwlx.equals(TaskTypeCode.ZDJK.getCode())) {// �Զ����
			code = new String[] { 
					FileTypeEnums.ZDJKJCJLSMJ.getCode(),
					FileTypeEnums.ZDJKXZWS.getCode(),
					FileTypeEnums.ZDJKQTZL.getCode(),
					FileTypeEnums.ZDJKCLYJS.getCode(),
					FileTypeEnums.ZDJKSPZL.getCode(),
					FileTypeEnums.ZDJKLYZL.getCode(),
					FileTypeEnums.ZDJKZP.getCode(),
					FileTypeEnums.ZDJKHPPFWJ.getCode(),
					FileTypeEnums.ZDJKYSPFWJ.getCode()};
		} else if (rwlx.equals(TaskTypeCode.WXFW.getCode())) {// Σ�շ���
			code = new String[] { 
					FileTypeEnums.WXFWJCJLSMJ.getCode(),
					FileTypeEnums.WXFWXZWS.getCode(),
					FileTypeEnums.WXFWQTZL.getCode(),
					FileTypeEnums.WXFWCLYJS.getCode(),
					FileTypeEnums.WXFWSPZL.getCode(),
					FileTypeEnums.WXFWLYZL.getCode(),
					FileTypeEnums.WXFWZP.getCode(),
					FileTypeEnums.WXFWHPPFWJ.getCode(),
					FileTypeEnums.WXFWYSPFWJ.getCode()};
		} else if (rwlx.equals(TaskTypeCode.WXHXP.getCode())) {// Σ�ջ�ѧƷ
			code = new String[] { 
					FileTypeEnums.WXHXPJCJLSMJ.getCode(),
					FileTypeEnums.WXHXPXZWS.getCode(),
					FileTypeEnums.WXHXPQTZL.getCode(),
					FileTypeEnums.WXHXPCLYJS.getCode(),
					FileTypeEnums.WXHXPSPZL.getCode(),
					FileTypeEnums.WXHXPLYZL.getCode(),
					FileTypeEnums.WXHXPZP.getCode(),
					FileTypeEnums.WXHXPHPPFWJ.getCode(),
					FileTypeEnums.WXHXPYSPFWJ.getCode()};
		} else if (rwlx.equals(TaskTypeCode.FSAQ.getCode())) {// ���䰲ȫ
			code = new String[] { 
					FileTypeEnums.FSAQJCJLSMJ.getCode(),
					FileTypeEnums.FSAQXZWS.getCode(),
					FileTypeEnums.FSAQQTZL.getCode(),
					FileTypeEnums.FSAQCLYJS.getCode(),
					FileTypeEnums.FSAQSPZL.getCode(),
					FileTypeEnums.FSAQLYZL.getCode(),
					FileTypeEnums.FSAQZP.getCode(),
					FileTypeEnums.FSAQHPPFWJ.getCode(),
					FileTypeEnums.FSAQYSPFWJ.getCode()};
		} else if (rwlx.equals(TaskTypeCode.WRSGXCDC.getCode())) {// ��Ⱦ�¹��ֳ�����
			code = new String[] { 
					FileTypeEnums.WRSGXCDCJCJLSMJ.getCode(),
					FileTypeEnums.WRSGXCDCXZWS.getCode(),
					FileTypeEnums.WRSGXCDCQTZL.getCode(),
					FileTypeEnums.WRSGXCDCCLYJS.getCode(),
					FileTypeEnums.WRSGXCDCSPZL.getCode(),
					FileTypeEnums.WRSGXCDCLYZL.getCode(),
					FileTypeEnums.WRSGXCDCZP.getCode(),
					FileTypeEnums.WRSGXCDCHPPFWJ.getCode(),
					FileTypeEnums.WRSGXCDCYSPFWJ.getCode()};
		} else if (rwlx.equals(TaskTypeCode.RCBG.getCode())){//�ճ��칫
			code = new String[] { FileTypeEnums.RCBGBLZL.getCode()};
		} else if (rwlx.equals(TaskTypeCode.ZXXD.getCode())){
			code = new String[] { FileTypeEnums.ZXXDZRWJCJLSMJ.getCode(),
					FileTypeEnums.ZXXDZRWXZWS.getCode(),
					FileTypeEnums.ZXXDZRWQTZL.getCode(),
					FileTypeEnums.ZXXDZRWCLYJS.getCode() };
		}
		
		String cs = "";
		for (int i = 0; i < code.length; i++) {
			if (i > 0) {
				cs += ", ";
			}
			cs += "'" + code[i] + "'";
		}
		dics = this.dao.find("from TSysDic where type = '4' and code in ( " + cs + " )");
		for (int i = 0; i < dics.size(); i++) {
			if (StringUtils.equalsIgnoreCase(dics.get(i).getMandatory(),
					YnEnum.Y.getCode())) {
				re.add(new Combobox(dics.get(i).getCode(), FileTypeEnums
						.getNameByCode(dics.get(i).getCode())
						+ "(����)","",commonManager.getExtension(dics.get(i).getCode())));
			} else {
				re.add(new Combobox(dics.get(i).getCode(), FileTypeEnums
						.getNameByCode(dics.get(i).getCode()),"",commonManager.getExtension(dics.get(i).getCode())));
			}
		}
		return re;
	}

	@Override
	public List<Combobox> queryBlFileTypeComboZXXDZRW() {
		
		List<Combobox> re = new ArrayList<Combobox>();

		String[] code = null;
		List<TSysDic> dics = new ArrayList<TSysDic>();
		
		code = new String[] {
				FileTypeEnums.ZXXDZRWJCJLSMJ.getCode(), 
				FileTypeEnums.ZXXDZRWXZWS.getCode(),
				FileTypeEnums.ZXXDZRWQTZL.getCode(), 
				FileTypeEnums.ZXXDZRWCLYJS.getCode(),
				FileTypeEnums.ZXXDZRWSPZL.getCode(),
				FileTypeEnums.ZXXDZRWLYZL.getCode(),
				FileTypeEnums.ZXXDZRWZP.getCode(),
				FileTypeEnums.ZXXDZRWHPPFWJ.getCode(),
				FileTypeEnums.ZXXDZRWYSPFWJ.getCode()};
 		String cs = "";
		for (int i = 0; i < code.length; i++) {
			if (i > 0) {
				cs += ", ";
			}
			cs += "'" + code[i] + "'";
		}
		dics = this.dao.find("from TSysDic where code in ( " + cs + " )");
		for (int i = 0; i < dics.size(); i++) {
			if (StringUtils.equalsIgnoreCase(dics.get(i).getMandatory(),
					YnEnum.Y.getCode())) {
				re.add(new Combobox(dics.get(i).getCode(), FileTypeEnums
						.getNameByCode(dics.get(i).getCode())
						+ "(����)"));
			} else {
				re.add(new Combobox(dics.get(i).getCode(), FileTypeEnums
						.getNameByCode(dics.get(i).getCode())));
			}
		}
		return re;
	}

	@Override
	public void saveDjMessage(String applyId, MultipartFile file)
			throws AppException {
		try {
			// ��ѹ�ļ�·��
			String unZipPath = FileUpDownUtil.path.concat(File.separator)
					.concat("temp");
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String fileInputPath = FileUpDownUtil.copyFile(file
					.getInputStream(), uuid + ".zip", UploadFileType.TEMP
					.getPath(), "");
			FileZipUtil zipUtil = new FileZipUtil();
			unZipPath = zipUtil.unZip(fileInputPath, unZipPath);
			File unzip = new File(unZipPath);
			boolean isZip = false;// �Ƿ�����ȷ��ѹ����
			if (unzip.exists() && unzip.isDirectory()) {
				File[] fileArr = unzip.listFiles();
				for (int i = 0; i < fileArr.length; i++) {
					if (fileArr[i].getName().equals("workInfo.xml")) {
						isZip = true;
						this.saveWorkInfo(applyId, fileArr[i], fileArr);
					} else if (fileArr[i].getName().endsWith(".zip")) {
						String filepath = zipUtil.unZip(unZipPath
								+ File.separator + fileArr[i].getName(),
								unZipPath);
						File fileDir = new File(filepath);
						if (fileDir.exists() && fileDir.isDirectory()) {
							File[] files = fileDir.listFiles();
							for (int j = 0; j < files.length; j++) {
								if (files[j].getName().equals("workInfo.xml")) {
									isZip = true;
									this.saveWorkInfo(applyId, files[j], files);
								}
							}
						}
					}
				}
			}
			if (!isZip) {
				throw new AppException("ѹ������û�п������ݣ����ϴ���ȷ��ѹ������");
			}
		} catch (IOException e) {
			throw new AppException("ѹ���������ã����ϴ���ȷ��ѹ������");
		}
	}

	/**
	 * 
	 * �������ܣ�����workInfo.xml�е����������Ϣ,��������ظ���
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 * 
	 * @throws AppException
	 */
	private void saveWorkInfo(String applyId, File workInfoXml, File[] fileArr)
			throws AppException {
		// ����XML
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(workInfoXml);
			Element root = doc.getRootElement();
			String rwlx = root.selectSingleNode("rwlx") == null ? "" : root
					.selectSingleNode("rwlx").getText();
			String zbrId = root.selectSingleNode("Zbr").selectSingleNode("id")
					.getText();
			Work work = (Work) this.get(Work.class, applyId);
			if (!work.getZxrId().equals(zbrId)) {
				throw new AppException("�����˲�ͬ���ܵ��룡");
			}
			// ԭ�����񸽼����ȴ�����ɹ�����ɾ����
			List<TDataFile> listFile = this
					.find(
							"from TDataFile f where f.pid = ? and f.type not in ('2500','2501') and f.type like ? ",
							applyId, StringUtil.isBlank(rwlx) ? "NNNNN"
									: (rwlx + "%"));
			if (StringUtils.isBlank(rwlx)) {
				throw new AppException("��������Ϊ�ղ��ܵ��룡");
			} else if (TaskTypeCode.WFAJ.getCode().equals(rwlx)) {
				// Υ������
				this.saveWfajWorkInfo(applyId, rwlx, root, fileArr);
			} else if (TaskTypeCode.ZXXD.getCode().equals(rwlx)) {
				if (StringUtils.isBlank(work.getPid())) {
					throw new AppException("ר������ֻ������������������е��룡");
				}
				// ר���ж�����
				this.saveZxxdWorkInfo(applyId, rwlx, root, fileArr);
			} else {
				// �ֳ������ִ����Ϣ
				this.saveRcjcWorkInfo(applyId, rwlx, root, fileArr);
			}
			// ɾ��ԭ�е��������͸���
			if (listFile.size() > 0) {
				for (TDataFile oldFile : listFile) {
					this.dao.remove(TDataFile.class, oldFile.getId());
					// ɾ���ļ�
					FileUpDownUtil.delFile(oldFile.getRelativepath()
							+ File.separator + oldFile.getOsname());
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new AppException("ɾ��ԭ�е����񸽼�������");
		}
	}

	/**
	 * 
	 * �������ܣ������ֳ�����������ִ����Ϣ
	 * 
	 * ���������applyId-����id��rwlx-�������ͱ�ţ�root-workinfo.xml���ڵ㣬unzip-��ѹ����ļ�Ŀ¼����
	 * 
	 * @throws AppException
	 *             ����ֵ��
	 */
	private void saveRcjcWorkInfo(String applyId, String rwlx, Element root,
			File[] fileArr) throws AppException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if("24".equals(rwlx)){
				String desc = root.selectSingleNode("desc") == null ? "" : root
						.selectSingleNode("desc").getText();
				List list1 = this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", applyId, "24");
				if(null!=list1&&list1.size()>0){
					TBizTaskandtasktype tt = (TBizTaskandtasktype)list1.get(0);
					tt.setDesc(desc);
					tt.setTaskid(applyId);
					this.dao.save(tt);
				}
				// Э���˲����ڣ�������
				List<String> list = this.dao
						.findBySql(
								"select listagg(u.userid_,',') within GROUP(order by u.id_)  from t_biz_taskuser u where u.taskid_ = ? and u.type_ = ? ",
								applyId, TaskUserEnum.XBR.getCode());
				String userIds = "";
				if (list.size() > 0 && StringUtil.isNotBlank(list.get(0))) {
					userIds = list.get(0);
				}
				List<Node> listXbr = root.selectSingleNode("Xbr").selectNodes("id");
				for (Node node : listXbr) {
					String xbrId = node.getText();
					if (StringUtils.isNotBlank(xbrId) && !userIds.contains(xbrId)) {
						TSysUser xbr = ((TSysUser) this.get(TSysUser.class, xbrId));
						if (xbr != null) {
							TBizTaskuser xbrUser = new TBizTaskuser(applyId, xbrId,
									xbr.getName(), TaskUserEnum.XBR.getCode());
							this.save(xbrUser);
						}
					}
				}
				// ׼������
				Node zbzlnode = root.selectSingleNode("zbzl");
				this.saveDjFile(zbzlnode, applyId, fileArr);
				// ��������
				Node qitanode = root.selectSingleNode("qita");
				this.saveDjFile(qitanode, applyId, fileArr);
			}else{
				String zfdxid = root.selectSingleNode("zfdxid") == null ? "" : root
						.selectSingleNode("zfdxid").getText();
				String zfdx = root.selectSingleNode("zfdx") == null ? "" : root
						.selectSingleNode("zfdx").getText();
				String zfdxdz = root.selectSingleNode("zfdxdz") == null ? "" : root
						.selectSingleNode("zfdxdz").getText();
				String manager = root.selectSingleNode("manager") == null ? "" : root
						.selectSingleNode("manager").getText();
				String fddbr = root.selectSingleNode("fddbr") == null ? "" : root
						.selectSingleNode("fddbr").getText();
				String lxdh = root.selectSingleNode("lxdh") == null ? "" : root
						.selectSingleNode("lxdh").getText();
				String bjcr = root.selectSingleNode("bjcr") == null ? "" : root
						.selectSingleNode("bjcr").getText();
				String bjcrzw = root.selectSingleNode("bjcrzw") == null ? "" : root
						.selectSingleNode("bjcrzw").getText();
				String bjcrlxdh = root.selectSingleNode("bjcrlxdh") == null ? ""
						: root.selectSingleNode("bjcrlxdh").getText();
				String jcsj1 = root.selectSingleNode("jcsj1") == null ? "" : root
						.selectSingleNode("jcsj1").getText()+":00";
				String jcsj2 = root.selectSingleNode("jcsj2") == null ? "" : root
						.selectSingleNode("jcsj2").getText()+":00";
				String jcmb = root.selectSingleNode("jcmb") == null ? "" : root
						.selectSingleNode("jcmb").getText();
				String jcdd = root.selectSingleNode("sjdw") == null ? "" : root
						.selectSingleNode("sjdw").getText();
				String jcjl = root.selectSingleNode("jcjl") == null ? "" : root
						.selectSingleNode("jcjl").getText();
				// �ŷ�Ͷ����Դ
				String xftsly = root.selectSingleNode("xftsly") == null ? "" : root
						.selectSingleNode("xftsly").getText();
				
				if(zfdxid == null || zfdxid == "" && TaskTypeCode.XFTS.getCode().equals(rwlx)){
					// ��֤��������һ��
					Work work = (Work) this.get(Work.class, applyId);
					this.dao.save(work);
					// ��ɾ����ͬ��������code�ļ�¼
					this.dao
							.exec(" delete from t_biz_taskandtasktype t where t.taskid_ = '"
									+ applyId + "' and t.tasktypeid_ =  '" + rwlx + "'");
					TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
					tBizTaskandtasktype.setTaskid(applyId);
					tBizTaskandtasktype.setTasktypeid(rwlx);
					
					if (TaskTypeCode.XFTS.getCode().equals(rwlx)) {
						tBizTaskandtasktype.setXftsly(xftsly);
						String xfbjdqk = root.selectSingleNode("xfbjdqk") == null ? "" : root
								.selectSingleNode("xfbjdqk").getText();
						String hjxfbjqk = root.selectSingleNode("hjxfbjqk") == null ? "" : root
								.selectSingleNode("hjxfbjqk").getText();
						String hfxs = root.selectSingleNode("hfxs") == null ? "" : root
								.selectSingleNode("hfxs").getText();
						String hfxsxm = root.selectSingleNode("hfxsxm") == null ? "" : root
								.selectSingleNode("hfxsxm").getText();
						String hfxsnr = root.selectSingleNode("hfxsnr") == null ? "" : root
								.selectSingleNode("hfxsnr").getText();
						String hfr = root.selectSingleNode("hfr") == null ? "" : root
								.selectSingleNode("hfr").getText();
						String fhr = root.selectSingleNode("fhr") == null ? "" : root
								.selectSingleNode("fhr").getText();
						String fhjsr = root.selectSingleNode("fhjsr") == null ? "" : root
								.selectSingleNode("fhjsr").getText();
						String bcqk = root.selectSingleNode("bcqk") == null ? "" : root
								.selectSingleNode("bcqk").getText();
						String hfrqsj = root.selectSingleNode("hfrqsj") == null ? "" : root
								.selectSingleNode("hfrqsj").getText();
						String bcr = root.selectSingleNode("bcr") == null ? "" : root
								.selectSingleNode("bcr").getText();
						String fhrqsj = root.selectSingleNode("fhrqsj") == null ? "" : root
								.selectSingleNode("fhrqsj").getText();
						String jssjrq = root.selectSingleNode("jssjrq") == null ? "" : root
								.selectSingleNode("jssjrq").getText();
						String bcrqsj = root.selectSingleNode("bcrqsj") == null ? "" : root
								.selectSingleNode("bcrqsj").getText();
						
						XfbjdForm xfbjdForm = this.getXfbjdform(applyId);
						//�ŷð���������������ط����
						if(xfbjdForm != null){
							TBizXfbjd xfbjd = null;
							if(StringUtils.isNotBlank(xfbjdForm.getId())){
								xfbjd = (TBizXfbjd) this.get(TBizXfbjd.class, xfbjdForm.getId());
								xfbjd.setUpdateby(CtxUtil.getCurUser());
								xfbjd.setUpdated(new Date());
							}else{
								xfbjd = new TBizXfbjd();
								TSysArea area = (TSysArea) this.get(TSysArea.class, CtxUtil.getAreaId());
								xfbjd.setArea(area);
								xfbjd.setCreateby(CtxUtil.getCurUser());
								xfbjd.setCreated(new Date());
								xfbjd.setIsActive(YnEnum.Y.getCode());
								xfbjd.setUpdateby(CtxUtil.getCurUser());
								xfbjd.setUpdated(new Date());
							}
							xfbjd.setRwid(applyId);
							if(work!=null){
								xfbjd.setSlsj(work.getZxStartTime());//����ʱ��
							}
							xfbjd.setBjqk(xfbjdqk);
							xfbjd.setHjxfblqk(hjxfbjqk);
							xfbjd.setHfxs(hfxs);
							if("����".equals(hfxsxm)){
								xfbjd.setHfxsxm("");
							}else{
								xfbjd.setHfxsxm(hfxsxm);
							}
							if("����".equals(hfxsnr)){
								xfbjd.setHfxsdyrn("");
							}else{
								xfbjd.setHfxsdyrn(hfxsnr);
							}
							xfbjd.setHfr(hfr);
							xfbjd.setFhr(fhr);
							xfbjd.setJsr(fhjsr);
							xfbjd.setBcqk(bcqk);
							xfbjd.setBcr(bcr);
							try {
								xfbjd.setHfrq(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",hfrqsj));
								xfbjd.setFhrq(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",fhrqsj));
								xfbjd.setJssj(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",jssjrq));
								xfbjd.setBcrq(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",bcrqsj));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							this.dao.save(xfbjd);
						}
					}
					tBizTaskandtasktype.setIsActive("Y");
					TSysUser user = CtxUtil.getCurUser();
					tBizTaskandtasktype.setCreateby(user);
					tBizTaskandtasktype
							.setCreated(new Date(System.currentTimeMillis()));
					tBizTaskandtasktype.setUpdateby(user);
					tBizTaskandtasktype
							.setUpdated(new Date(System.currentTimeMillis()));
					this.dao.save(tBizTaskandtasktype);
					
					// �������Ա
					// ��ɾ������ļ�¼�ˡ������
					String userType = "'" + this.getTaskUserEnumByTaskType(rwlx, 1)
							+ "'" + "," + "'" + this.getTaskUserEnumByTaskType(rwlx, 2)
							+ "'";
					this.dao.exec(" delete from t_biz_taskuser t where t.taskid_ = '"
							+ applyId + "' and t.type_ in ( " + userType + ")");
					// Э���˲����ڣ�������
					List<String> list = this.dao
							.findBySql(
									"select listagg(u.userid_,',') within GROUP(order by u.id_)  from t_biz_taskuser u where u.taskid_ = ? and u.type_ = ? ",
									applyId, TaskUserEnum.XBR.getCode());
					String userIds = "";
					if (list.size() > 0 && StringUtil.isNotBlank(list.get(0))) {
						userIds = list.get(0);
					}

					List<Node> listXbr = root.selectSingleNode("Xbr").selectNodes("id");
					for (Node node : listXbr) {
						String xbrId = node.getText();
						if (StringUtils.isNotBlank(xbrId) && !userIds.contains(xbrId)) {
							TSysUser xbr = ((TSysUser) this.get(TSysUser.class, xbrId));
							if (xbr != null) {
								TBizTaskuser xbrUser = new TBizTaskuser(applyId, xbrId,
										xbr.getName(), TaskUserEnum.XBR.getCode());
								this.save(xbrUser);
							}
						}
					}
					// ���渽����Ϣ
					Node jcjlfj = root.selectSingleNode("jcjlfj");
					this.saveDjFile(jcjlfj, applyId, fileArr);
					// ׼������
					Node zbzlnode = root.selectSingleNode("zbzl");
					this.saveDjFile(zbzlnode, applyId, fileArr);
					// ��������
					Node qitanode = root.selectSingleNode("qita");
					this.saveDjFile(qitanode, applyId, fileArr);
					// ������ģ�������¼
					Node jccs = root.selectSingleNode("jccs");
					this.saveDjJccs(jccs, applyId, fileArr);
					// �����鵥���
					for (int i = 0; i < fileArr.length; i++) {
						if (fileArr[i].getName().equals("jcjl.xml")) {
							savercjcdjg(applyId, fileArr[i]);
						}
					}
				}else{
					TDataLawobj lawobj = (TDataLawobj) this.get(TDataLawobj.class,
							zfdxid);
					if (null == lawobj) {
						throw new AppException("�����ִ�����������ݿ��в����ڣ����ܵ��룡");
					}
					// ��֤��������һ��
					Work work = (Work) this.get(Work.class, applyId);
					if (StringUtil.isNotBlank(work.getZfdxType())) {
						if (!work.getZfdxType().equals(lawobj.getLawobjtype())) {
							throw new AppException("�����ִ���������Ͳ�ͬ���ܵ��룡");
						}
					} else {
						work.setZfdxType(lawobj.getLawobjtype());
						this.dao.save(work);
					}

					// ��ִ֤������һ��
					List<TBizTaskandlawobj> listLawobj = this.dao.find(
							" from TBizTaskandlawobj o where o.taskid = ? ", applyId);
					if (listLawobj.size() > 0) {
						if (!listLawobj.get(0).getLawobjid().equals(zfdxid)) {
							throw new AppException("���߰��web�˵�ִ������һ�£����ܵ��룡");
						}
					}
					// �����ִ��������Ϣ
					// ��ɾ����ͬ��ִ������
					this.dao.exec(" delete from t_biz_taskandlawobj t where t.taskid_ = '"
									+ applyId + "' and t.LAWOBJID_ = '" + zfdxid + "' ");
					String regionId = lawobjManager.getLawobjColumnValue(lawobj
							.getLawobjtype()
							+ PublicColumnEnum.ssxzq.getCode(), lawobj.getId());
					TBizTaskandlawobj tBizTaskandlawobj = new TBizTaskandlawobj(
							applyId, lawobj.getLawobjtype(), zfdxid, zfdx, regionId,
							zfdxdz, fddbr, lxdh, bjcr, bjcrzw, bjcrlxdh, zfdx, manager);
					this.dao.save(tBizTaskandlawobj);
					// ���������������Ϣ
					// ��ɾ����ͬ��������code�ļ�¼
					this.dao
							.exec(" delete from t_biz_taskandtasktype t where t.taskid_ = '"
									+ applyId + "' and t.tasktypeid_ =  '" + rwlx + "'");
					TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
					tBizTaskandtasktype.setTaskid(applyId);
					tBizTaskandtasktype.setTasktypeid(rwlx);
					tBizTaskandtasktype.setJcsj1(sdf.parse(jcsj1));
					tBizTaskandtasktype.setJcsj2(sdf.parse(jcsj2));
					tBizTaskandtasktype.setJcdd(jcdd);//���ص�
					tBizTaskandtasktype.setJcjl(jcjl);//������
					tBizTaskandtasktype.setJcmb(jcmb);
					if (TaskTypeCode.XFTS.getCode().equals(rwlx)) {
						tBizTaskandtasktype.setXftsly(xftsly);
						String xfbjdqk = root.selectSingleNode("xfbjdqk") == null ? "" : root
								.selectSingleNode("xfbjdqk").getText();
						String hjxfbjqk = root.selectSingleNode("hjxfbjqk") == null ? "" : root
								.selectSingleNode("hjxfbjqk").getText();
						String hfxs = root.selectSingleNode("hfxs") == null ? "" : root
								.selectSingleNode("hfxs").getText();
						String hfxsxm = root.selectSingleNode("hfxsxm") == null ? "" : root
								.selectSingleNode("hfxsxm").getText();
						String hfxsnr = root.selectSingleNode("hfxsnr") == null ? "" : root
								.selectSingleNode("hfxsnr").getText();
						String hfr = root.selectSingleNode("hfr") == null ? "" : root
								.selectSingleNode("hfr").getText();
						String fhr = root.selectSingleNode("fhr") == null ? "" : root
								.selectSingleNode("fhr").getText();
						String fhjsr = root.selectSingleNode("fhjsr") == null ? "" : root
								.selectSingleNode("fhjsr").getText();
						String bcqk = root.selectSingleNode("bcqk") == null ? "" : root
								.selectSingleNode("bcqk").getText();
						String hfrqsj = root.selectSingleNode("hfrqsj") == null ? "" : root
								.selectSingleNode("hfrqsj").getText();
						String bcr = root.selectSingleNode("bcr") == null ? "" : root
								.selectSingleNode("bcr").getText();
						String fhrqsj = root.selectSingleNode("fhrqsj") == null ? "" : root
								.selectSingleNode("fhrqsj").getText();
						String jssjrq = root.selectSingleNode("jssjrq") == null ? "" : root
								.selectSingleNode("jssjrq").getText();
						String bcrqsj = root.selectSingleNode("bcrqsj") == null ? "" : root
								.selectSingleNode("bcrqsj").getText();
						
						XfbjdForm xfbjdForm = this.getXfbjdform(applyId);
						//�ŷð���������������ط����
						if(xfbjdForm != null){
							TBizXfbjd xfbjd = null;
							if(StringUtils.isNotBlank(xfbjdForm.getId())){
								xfbjd = (TBizXfbjd) this.get(TBizXfbjd.class, xfbjdForm.getId());
								xfbjd.setUpdateby(CtxUtil.getCurUser());
								xfbjd.setUpdated(new Date());
							}else{
								xfbjd = new TBizXfbjd();
								TSysArea area = (TSysArea) this.get(TSysArea.class, CtxUtil.getAreaId());
								xfbjd.setArea(area);
								xfbjd.setCreateby(CtxUtil.getCurUser());
								xfbjd.setCreated(new Date());
								xfbjd.setIsActive(YnEnum.Y.getCode());
								xfbjd.setUpdateby(CtxUtil.getCurUser());
								xfbjd.setUpdated(new Date());
							}
							xfbjd.setRwid(applyId);
							if(work!=null){
								xfbjd.setSlsj(work.getZxStartTime());//����ʱ��
							}
							xfbjd.setBjqk(xfbjdqk);
							xfbjd.setHjxfblqk(hjxfbjqk);
							xfbjd.setHfxs(hfxs);
							if("����".equals(hfxsxm)){
								xfbjd.setHfxsxm("");
							}else{
								xfbjd.setHfxsxm(hfxsxm);
							}
							if("����".equals(hfxsnr)){
								xfbjd.setHfxsdyrn("");
							}else{
								xfbjd.setHfxsdyrn(hfxsnr);
							}
							xfbjd.setHfr(hfr);
							xfbjd.setFhr(fhr);
							xfbjd.setJsr(fhjsr);
							xfbjd.setBcqk(bcqk);
							xfbjd.setBcr(bcr);
							try {
								xfbjd.setHfrq(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",hfrqsj));
								xfbjd.setFhrq(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",fhrqsj));
								xfbjd.setJssj(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",jssjrq));
								xfbjd.setBcrq(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",bcrqsj));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							this.dao.save(xfbjd);
						}
					}
					tBizTaskandtasktype.setIsActive("Y");
					TSysUser user = CtxUtil.getCurUser();
					tBizTaskandtasktype.setCreateby(user);
					tBizTaskandtasktype
							.setCreated(new Date(System.currentTimeMillis()));
					tBizTaskandtasktype.setUpdateby(user);
					tBizTaskandtasktype
							.setUpdated(new Date(System.currentTimeMillis()));
					this.dao.save(tBizTaskandtasktype);

					// �������Ա
					// ��ɾ������ļ�¼�ˡ������
					String userType = "'" + this.getTaskUserEnumByTaskType(rwlx, 1)
							+ "'" + "," + "'" + this.getTaskUserEnumByTaskType(rwlx, 2)
							+ "'";
					this.dao.exec(" delete from t_biz_taskuser t where t.taskid_ = '"
							+ applyId + "' and t.type_ in ( " + userType + ")");
					// Э���˲����ڣ�������
					List<String> list = this.dao
							.findBySql(
									"select listagg(u.userid_,',') within GROUP(order by u.id_)  from t_biz_taskuser u where u.taskid_ = ? and u.type_ = ? ",
									applyId, TaskUserEnum.XBR.getCode());
					String userIds = "";
					if (list.size() > 0 && StringUtil.isNotBlank(list.get(0))) {
						userIds = list.get(0);
					}
					List<Node> jcrs = root.selectSingleNode("Jcr").selectNodes("id");
					for (Node node : jcrs) {
						String jcrId = null == node ? "" : node.getText();
						if (StringUtil.isNotBlank(jcrId)) {
							TSysUser jcr = ((TSysUser) this.get(TSysUser.class, jcrId));
							if (jcr != null) {
								TBizTaskuser jcrUser = new TBizTaskuser(applyId, jcrId,
										jcr.getName(), this.getTaskUserEnumByTaskType(
												rwlx, 1));
								this.save(jcrUser);
							}
						}
					}

					String jlrId = null == root.selectSingleNode("Jlr")
							.selectSingleNode("id") ? "" : root.selectSingleNode("Jlr")
							.selectSingleNode("id").getText();
					if (StringUtil.isNotBlank(jlrId)) {
						TSysUser jlr = ((TSysUser) this.get(TSysUser.class, jlrId));
						if (jlr != null) {
							TBizTaskuser jlrUser = new TBizTaskuser(applyId, jlrId, jlr
									.getName(), this.getTaskUserEnumByTaskType(rwlx, 2));
							this.save(jlrUser);
						}
					}
					List<Node> listXbr = root.selectSingleNode("Xbr").selectNodes("id");
					for (Node node : listXbr) {
						String xbrId = node.getText();
						if (StringUtils.isNotBlank(xbrId) && !userIds.contains(xbrId)) {
							TSysUser xbr = ((TSysUser) this.get(TSysUser.class, xbrId));
							if (xbr != null) {
								TBizTaskuser xbrUser = new TBizTaskuser(applyId, xbrId,
										xbr.getName(), TaskUserEnum.XBR.getCode());
								this.save(xbrUser);
							}
						}
					}
					// ���渽����Ϣ
					Node jcjlfj = root.selectSingleNode("jcjlfj");
					this.saveDjFile(jcjlfj, applyId, fileArr);
					// ׼������
					Node zbzlnode = root.selectSingleNode("zbzl");
					this.saveDjFile(zbzlnode, applyId, fileArr);
					// ��������
					Node qitanode = root.selectSingleNode("qita");
					this.saveDjFile(qitanode, applyId, fileArr);
					// ������ģ�������¼
					Node jccs = root.selectSingleNode("jccs");
					this.saveDjJccs(jccs, applyId, fileArr);
					// �����鵥���
					for (int i = 0; i < fileArr.length; i++) {
						if (fileArr[i].getName().equals("jcjl.xml")) {
							savercjcdjg(applyId, fileArr[i]);
						}
					}
				}
			}
		} catch (ParseException e) {
			throw new AppException("�����鵥��Ϣʱ��������");
		} catch (FileNotFoundException e) {
			throw new AppException("���븽����Ϣ��������");
		} catch (DocumentException e) {
			throw new AppException("�����鵥��Ϣʱ��������");
		}
	}

	/**
	 * 
	 * �������ܣ�����ר�����������ִ����Ϣ
	 * 
	 * ���������applyId-����id��rwlx-�������ͱ�ţ�root-workinfo.xml���ڵ㣬unzip-��ѹ����ļ�Ŀ¼����
	 * 
	 * @throws AppException
	 *             ����ֵ��
	 */
	private void saveZxxdWorkInfo(String applyId, String rwlx, Element root,
			File[] fileArr) throws AppException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		try {
			String manager = root.selectSingleNode("manager") == null ? "" : root
					.selectSingleNode("manager").getText();
			String zfdxid = root.selectSingleNode("zfdxid") == null ? "" : root
					.selectSingleNode("zfdxid").getText();
			// String zfdx = root.selectSingleNode("zfdx") == null ? "" :
			// root.selectSingleNode("zfdx").getText();
			// String zfdxdz = root.selectSingleNode("zfdxdz") == null ? "" :
			// root.selectSingleNode("zfdxdz").getText();
			// String fddbr = root.selectSingleNode("fddbr") == null ? "" :
			// root.selectSingleNode("fddbr").getText();
			// String lxdh = root.selectSingleNode("lxdh") == null ? "" :
			// root.selectSingleNode("lxdh").getText();
			String bjcr = root.selectSingleNode("bjcr") == null ? "" : root
					.selectSingleNode("bjcr").getText();
			String bjcrzw = root.selectSingleNode("bjcrzw") == null ? "" : root
					.selectSingleNode("bjcrzw").getText();
			String bjcrlxdh = root.selectSingleNode("bjcrlxdh") == null ? ""
					: root.selectSingleNode("bjcrlxdh").getText();
			String jcsj = root.selectSingleNode("jcsj") == null ? "" : root
					.selectSingleNode("jcsj").getText();
			String jcmb = root.selectSingleNode("jcmb") == null ? "" : root
					.selectSingleNode("jcmb").getText();

			// �����ִ��������Ϣ
			List<TBizTaskandlawobj> list = this.dao.find(
					"from TBizTaskandlawobj l where l.taskid = ? ", applyId);
			TBizTaskandlawobj tBizTaskandlawobj = list.get(0);
			if (!tBizTaskandlawobj.getLawobjid().equals(zfdxid)) {
				throw new AppException("ִ������ͬ���ܵ��룡");
			}
			tBizTaskandlawobj.setZwtitle(manager);
			tBizTaskandlawobj.setBjcr(bjcr);
			tBizTaskandlawobj.setZw(bjcrzw);
			tBizTaskandlawobj.setLxdh(bjcrlxdh);
			this.dao.save(tBizTaskandlawobj);
			// ���������������Ϣ
			List<TBizTaskandtasktype> listTasktype = this.dao.find(
					"from TBizTaskandtasktype l where l.taskid = ? ", applyId);
			TBizTaskandtasktype tBizTaskandtasktype = listTasktype.get(0);
			tBizTaskandtasktype.setTaskid(applyId);
			tBizTaskandtasktype.setTasktypeid(rwlx);
			tBizTaskandtasktype.setJcsj1(sdf.parse(jcsj));
			tBizTaskandtasktype.setJcmb(jcmb);
			tBizTaskandtasktype.setIsActive("Y");
			TSysUser user = CtxUtil.getCurUser();
			tBizTaskandtasktype.setUpdateby(user);
			tBizTaskandtasktype
					.setUpdated(new Date(System.currentTimeMillis()));
			this.dao.save(tBizTaskandtasktype);

			// �������Ա
			// ��ɾ������ļ�¼�ˡ������
			String userType = "'" + this.getTaskUserEnumByTaskType(rwlx, 1)
					+ "'" + "," + "'" + this.getTaskUserEnumByTaskType(rwlx, 2)
					+ "'";
			this.dao.exec(" delete from t_biz_taskuser t where t.taskid_ = '"
					+ applyId + "' and t.type_ in ( " + userType + ")");
			// �����ڣ�������
			List<String> listUserId = this.dao
					.findBySql(
							"select listagg(u.userid_,',') within GROUP(order by u.id_)  from t_biz_taskuser u where u.taskid_ = ?  and u.type_ = ? ",
							applyId, TaskUserEnum.XBR.getCode());
			String userIds = "";
			if (listUserId.size() > 0
					&& StringUtil.isNotBlank(listUserId.get(0))) {
				userIds = listUserId.get(0);
			}
			String jcrId = null == root.selectSingleNode("Jcr")
					.selectSingleNode("id") ? "" : root.selectSingleNode("Jcr")
					.selectSingleNode("id").getText();
			if (StringUtil.isNotBlank(jcrId) && !userIds.contains(jcrId)) {
				TSysUser jcr = ((TSysUser) this.get(TSysUser.class, jcrId));
				if (jcr != null) {
					TBizTaskuser jcrUser = new TBizTaskuser(applyId, jcrId, jcr
							.getName(), this.getTaskUserEnumByTaskType(rwlx, 1));
					this.save(jcrUser);
				}
			}
			String jlrId = null == root.selectSingleNode("Jlr")
					.selectSingleNode("id") ? "" : root.selectSingleNode("Jlr")
					.selectSingleNode("id").getText();
			if (StringUtil.isNotBlank(jlrId) && !userIds.contains(jlrId)) {
				TSysUser jlr = ((TSysUser) this.get(TSysUser.class, jlrId));
				if (jlr != null) {
					TBizTaskuser jlrUser = new TBizTaskuser(applyId, jlrId, jlr
							.getName(), this.getTaskUserEnumByTaskType(rwlx, 2));
					this.save(jlrUser);
				}
			}
			List<Node> listXbr = root.selectSingleNode("Xbr").selectNodes("id");
			for (Node node : listXbr) {
				String xbrId = node.getText();
				if (StringUtils.isNotBlank(xbrId) && !userIds.contains(xbrId)) {
					TSysUser xbr = ((TSysUser) this.get(TSysUser.class, xbrId));
					if (xbr != null) {
						TBizTaskuser xbrUser = new TBizTaskuser(applyId, xbrId,
								xbr.getName(), TaskUserEnum.XBR.getCode());
						this.save(xbrUser);
					}
				}
			}
			// �����鵥���
			for (int i = 0; i < fileArr.length; i++) {
				if (fileArr[i].getName().equals("jcjl.xml")) {
					savercjcdjg(applyId, fileArr[i]);
				}
			}
			// ���渽����Ϣ
			Node jcjlfj = root.selectSingleNode("jcjlfj");
			this.saveDjFile(jcjlfj, applyId, fileArr);
			// ��������
			Node qitanode = root.selectSingleNode("qita");
			this.saveDjFile(qitanode, applyId, fileArr);
		} catch (ParseException e) {
			throw new AppException("�����鵥��Ϣʱ��������");
		} catch (FileNotFoundException e) {
			throw new AppException("���븽����Ϣ��������");
		} catch (DocumentException e) {
			throw new AppException("�����鵥��Ϣʱ��������");
		}
	}

	/**
	 * 
	 * �������ܣ���ò�ͬ�������͵ļ���ˡ���¼�˵�ö��ֵ
	 * 
	 * ���������rwlx-�������ͣ�type-1������ˣ�2����¼��
	 * 
	 * ����ֵ��
	 */
	private String getTaskUserEnumByTaskType(String rwlx, int type) {
		if (TaskTypeCode.RCJC.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.RCJC_JCR.getCode()
					: TaskUserEnum.RCJC_JLR.getCode();
		} else if (TaskTypeCode.NDHC.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.NDHC_JCR.getCode()
					: TaskUserEnum.NDHC_JLR.getCode();
		} else if (TaskTypeCode.HDC.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.HDC_JCR.getCode()
					: TaskUserEnum.HDC_JLR.getCode();
		} else if (TaskTypeCode.XFTS.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.XFTS_JCR.getCode()
					: TaskUserEnum.XFTS_JLR.getCode();
		} else if (TaskTypeCode.PWXKZJC.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.PWXKZJC_JCR.getCode()
					: TaskUserEnum.PWXKZJC_JLR.getCode();
		} else if (TaskTypeCode.ZXXD.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.ZXXD_JCR.getCode()
					: TaskUserEnum.ZXXD_JLR.getCode();
		} else if (TaskTypeCode.WFAJ.getCode().equals(rwlx)) {
			return TaskUserEnum.WFAJDC_JLR.getCode();
		} else if (TaskTypeCode.XQZL.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.XQZL_JCR.getCode()
					: TaskUserEnum.XQZL_JLR.getCode();
		} else if (TaskTypeCode.GZJC.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.GZJC_JCR.getCode()
					: TaskUserEnum.GZJC_JLR.getCode();
		} else if (TaskTypeCode.ZDJK.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.ZDJK_JCR.getCode()
					: TaskUserEnum.ZDJK_JLR.getCode();
		} else if (TaskTypeCode.WXFW.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.WXFW_JCR.getCode()
					: TaskUserEnum.WXFW_JLR.getCode();
		} else if (TaskTypeCode.WXHXP.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.WXHXP_JCR.getCode()
					: TaskUserEnum.WXHXP_JLR.getCode();
		} else if (TaskTypeCode.FSAQ.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.FSAQ_JCR.getCode()
					: TaskUserEnum.FSAQ_JLR.getCode();
		} else if (TaskTypeCode.WRSGXCDC.getCode().equals(rwlx)) {
			return type == 1 ? TaskUserEnum.WRSGXCDC_JCR.getCode()
					: TaskUserEnum.WRSGXCDC_JLR.getCode();
		}
		return "";
	}

	/***
	 * �����鵥����������Ϣ
	 * 
	 * @param xmlFile
	 * @param rwid
	 * @param user
	 * @throws DocumentException
	 * @throws ParseExceptionzhaomh2013
	 *             -5-24
	 */
	private void savercjcdjg(String rwid, File xmlFile)
			throws DocumentException, ParseException {
		// ����XML
		SAXReader reader = new SAXReader();
		Document doc = reader.read(xmlFile);
		Element root = doc.getRootElement();
		// ����
		List<Node> nodes = null;
		nodes = root.selectNodes("jljg");
		for (int i = 0; i < nodes.size(); i++) {
			String jcjlmbid = nodes.get(i).selectSingleNode("jcjlmbid") == null ? ""
					: nodes.get(i).selectSingleNode("jcjlmbid").getText();
			String jcxid = nodes.get(i).selectSingleNode("jcxid") == null ? ""
					: nodes.get(i).selectSingleNode("jcxid").getText();
			String jcxda = nodes.get(i).selectSingleNode("jcxda") == null ? ""
					: nodes.get(i).selectSingleNode("jcxda").getText();
			String jcjg = nodes.get(i).selectSingleNode("jcjg") == null ? ""
					: nodes.get(i).selectSingleNode("jcjg").getText();
			String px = nodes.get(i).selectSingleNode("px") == null ? ""
					: nodes.get(i).selectSingleNode("px").getText();
			Integer order = StringUtils.isBlank(px) ? 0 : Integer.parseInt(px);
			// ��ɾ��������
			this.dao.exec(" delete from T_BIZ_CHECKLIST t where t.taskid_ = '"
					+ rwid + "' and t.ITEMID_ = '" + jcxid + "' ");
			String[] str = jcxda.split(",");
			for (int j = 0; j < str.length; j++) {
				TBizChecklist tBizChecklist = new TBizChecklist(rwid, jcjlmbid,
						jcxid, order, str[j].equals("1") ? "" : str[j], jcjg);
				this.save(tBizChecklist);
			}
		}
	}

	/**
	 * 
	 * �������ܣ�����Υ���������������������ִ����Ϣ
	 * 
	 * ���������applyId-����id��rwlx-�������ͱ�ţ�root-workinfo.xml���ڵ㣬unzip-��ѹ����ļ�Ŀ¼����
	 * 
	 * ����ֵ��
	 * 
	 * @throws AppException
	 */
	private void saveWfajWorkInfo(String applyId, String rwlx, Element root,
			File[] fileArr) throws AppException {
		try {
			String zfdxid = root.selectSingleNode("zfdxid") == null ? "" : root
					.selectSingleNode("zfdxid").getText();
			String zfdx = root.selectSingleNode("zfdx") == null ? "" : root
					.selectSingleNode("zfdx").getText();
			String zfdxdz = root.selectSingleNode("zfdxdz") == null ? "" : root
					.selectSingleNode("zfdxdz").getText();
			String fddbr = root.selectSingleNode("fddbr") == null ? "" : root
					.selectSingleNode("fddbr").getText();
			String manager = root.selectSingleNode("manager") == null ? "" : root
					.selectSingleNode("manager").getText();
			String zw = root.selectSingleNode("bjcrzw") == null ? "" : root
					.selectSingleNode("bjcrzw").getText();
			String fddbrlxdh = root.selectSingleNode("lxdh") == null ? ""
					: root.selectSingleNode("lxdh").getText();
			String hbfzr = root.selectSingleNode("bjcr") == null ? "" : root
					.selectSingleNode("bjcr").getText();
			// String bjcrzw = root.selectSingleNode("bjcrzw") == null ? "" :
			// root.selectSingleNode("bjcrzw").getText();
			String hbfzrlxdh = root.selectSingleNode("bjcrlxdh") == null ? ""
					: root.selectSingleNode("bjcrlxdh").getText();
			TDataLawobj lawobj = (TDataLawobj) this.get(TDataLawobj.class,
					zfdxid);
			if (null == lawobj) {
				throw new AppException("�����ִ�����������ݿ��в����ڣ����ܵ��룡");
			}
			List<TBizTaskandlawobj> listLawobj = this.dao.find(
					" from TBizTaskandlawobj o where o.taskid = ? ", applyId);
			if (listLawobj.size() > 0) {
				if (!listLawobj.get(0).getLawobjid().equals(zfdxid)) {
					throw new AppException("���߰��web�˵�ִ������һ�£����ܵ��룡");
				}
			}

			Work work = (Work) this.get(Work.class, applyId);
			if (StringUtil.isNotBlank(work.getZfdxType())) {
				if (!work.getZfdxType().equals(lawobj.getLawobjtype())) {
					throw new AppException("�����ִ���������Ͳ�ͬ���ܵ��룡");
				}
			} else {
				work.setZfdxType(lawobj.getLawobjtype());
				this.dao.save(work);
			}
			// �����ִ��������Ϣ
			// ��ɾ����ִͬ������id�ļ�¼
			this.dao
					.exec(" delete from t_biz_taskandlawobj t where t.taskid_ = '"
							+ applyId + "' and t.lawobjid_ =  '" + zfdxid + "'");
			String regionId = lawobjManager.getLawobjColumnValue(lawobj
					.getLawobjtype()
					+ PublicColumnEnum.ssxzq.getCode(), lawobj.getId());
			TBizTaskandlawobj tBizTaskandlawobj = new TBizTaskandlawobj(
					applyId, lawobj.getLawobjtype(), zfdxid, zfdx, regionId,
					zfdxdz, fddbr, fddbrlxdh, hbfzr, zw, hbfzrlxdh, zfdx, manager);
			this.dao.save(tBizTaskandlawobj);
			// ���������������Ϣ
			String ladjh = root.selectSingleNode("ladjh") == null ? "" : root
					.selectSingleNode("ladjh").getText();
			String ladjsj = root.selectSingleNode("ladjsj") == null ? "" : root
					.selectSingleNode("ladjsj").getText();
			String jzmc = root.selectSingleNode("jzmc") == null ? "" : root
					.selectSingleNode("jzmc").getText();
			String dcsj1 = root.selectSingleNode("dcsj1") == null ? "" : root
					.selectSingleNode("dcsj1").getText();
			String dcsj2 = root.selectSingleNode("dcsj2") == null ? "" : root
					.selectSingleNode("dcsj2").getText();
			// ��ɾ����ͬ��������code�ļ�¼
			this.dao.exec(" delete from t_biz_taskandtasktype t where t.taskid_ = '"
							+ applyId + "' and t.tasktypeid_ =  '" + rwlx + "'");
			TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
			tBizTaskandtasktype.setTaskid(applyId);
			tBizTaskandtasktype.setTasktypeid(rwlx);
			tBizTaskandtasktype.setLadjh(ladjh);
			tBizTaskandtasktype.setWfajmc(jzmc);
			tBizTaskandtasktype.setJcsj1(StringUtil.isNotBlank(dcsj1) ? DateUtil.convertStringToDate(dcsj1.replaceAll("/", "-")) : new Date());
			tBizTaskandtasktype.setJcsj2(StringUtil.isNotBlank(dcsj2) ? DateUtil.convertStringToDate(dcsj2.replaceAll("/", "-")) : new Date());
			tBizTaskandtasktype.setLadjsj(StringUtil.isNotBlank(ladjsj) ? DateUtil.convertStringToDate(ladjsj.replaceAll("/", "-")) : new Date());
			tBizTaskandtasktype.setIsActive("Y");
			TSysUser user = CtxUtil.getCurUser();
			tBizTaskandtasktype.setJzzlr(user.getName());
			tBizTaskandtasktype.setCreateby(user);
			tBizTaskandtasktype
					.setCreated(new Date(System.currentTimeMillis()));
			tBizTaskandtasktype.setUpdateby(user);
			tBizTaskandtasktype
					.setUpdated(new Date(System.currentTimeMillis()));
			this.dao.save(tBizTaskandtasktype);

			// �������Ա
			// ��ɾ������ļ�¼�ˡ������
			String userType = "'" + this.getTaskUserEnumByTaskType(rwlx, 1)
					+ "'" + "," + "'" + this.getTaskUserEnumByTaskType(rwlx, 2)
					+ "'";
			this.dao.exec(" delete from t_biz_taskuser t where t.taskid_ = '"
					+ applyId + "' and t.type_ in ( " + userType + ")");
			// �����ڣ�������
			List<String> listUserId = this.dao
					.findBySql(
							"select listagg(u.userid_,',') within GROUP(order by u.id_)  from t_biz_taskuser u where u.taskid_ = ?  and u.type_ = ? ",
							applyId, TaskUserEnum.XBR.getCode());
			String userIds = "";
			if (listUserId.size() > 0
					&& StringUtil.isNotBlank(listUserId.get(0))) {
				userIds = listUserId.get(0);
			}
			/*String jlrId = null == root.selectSingleNode("xwbl") ? ""
					: ((null == root.selectSingleNode("xwbl").selectSingleNode(
							"Jlr").selectSingleNode("id")) ? "" : root
							.selectSingleNode("xwbl").selectSingleNode("Jlr")
							.selectSingleNode("id").getText());
			if (StringUtil.isBlank(jlrId) && !userIds.contains(jlrId)) {
				jlrId = null == root.selectSingleNode("kcbl") ? ""
						: ((null == root.selectSingleNode("kcbl")
								.selectSingleNode("Jlr").selectSingleNode("id")) ? ""
								: root.selectSingleNode("kcbl")
										.selectSingleNode("Jlr")
										.selectSingleNode("id").getText());
			}*/
			String jlrId = root.selectSingleNode("Jlr").selectSingleNode("id") == null ? "" : root
					.selectSingleNode("Jlr").selectSingleNode("id").getText();
			TSysUser tSysUser = (TSysUser) this.get(TSysUser.class, jlrId);
			if (tSysUser != null) {
				TBizTaskuser jlrUser = new TBizTaskuser(applyId, jlrId,
						tSysUser == null ? "" : tSysUser.getName(), this
								.getTaskUserEnumByTaskType(rwlx, 2));
				this.save(jlrUser);
			}
			List<Node> listXbr = root.selectSingleNode("Xbr").selectNodes("id");
			for (Node node : listXbr) {
				String xbrId = node.getText();
				if (StringUtils.isNotBlank(xbrId) && !userIds.contains(xbrId)) {
					tSysUser = (TSysUser) this.get(TSysUser.class, xbrId);
					if (tSysUser != null) {
						TBizTaskuser xbrUser = new TBizTaskuser(applyId, xbrId,
								tSysUser == null ? "" : tSysUser.getName(),
								TaskUserEnum.XBR.getCode());
						this.save(xbrUser);
					}
				}
			}

			// �����Υ������
			// ��ɾ��
			this.dao
					.exec(" delete from T_Biz_Taskillegaltype t where t.taskid_ = '"
							+ applyId + "' ");
			String wflxbh = root.selectSingleNode("wflxbh") == null ? "" : root
					.selectSingleNode("wflxbh").getText();
			String[] str = wflxbh.split(",");
			for (int i = 0; i < str.length; i++) {
				TBizTaskillegaltype tBizTaskillegaltype = new TBizTaskillegaltype(
						applyId, str[i]);
				this.save(tBizTaskillegaltype);
			}
			// ����ѯ�ʱ�¼
			//saveXwblFromDjZip(applyId, fileArr, root);
			// ���濱���¼
			//saveKcblFromDjZip(applyId, fileArr, root);
			// ���渽����Ϣ
			// �����¼
			Node kcblfj = root.selectSingleNode("kcbl");
			this.saveDjFile(kcblfj, applyId, fileArr);
			// ѯ�ʱ�¼
			Node xwblfj = root.selectSingleNode("xwbl");
			this.saveDjFile(xwblfj, applyId, fileArr);
			// ��������
			Node lianfj = root.selectSingleNode("lianfj");
			this.saveDjFile(lianfj, applyId, fileArr);
			// ����֤������
			Node stzjzl = root.selectSingleNode("stzjzl");
			this.saveDjFile(stzjzl, applyId, fileArr);
			// ������������
			Node qitanode = root.selectSingleNode("qita");
			this.saveDjFile(qitanode, applyId, fileArr);
			// ������������
			Node qita1node = root.selectSingleNode("jcjlsmj");
			this.saveDjFile(qita1node, applyId, fileArr);
			// �����սᱨ��
			Node dczj = root.selectSingleNode("dczj");
			this.saveDjFile(dczj, applyId, fileArr);
			// �����սᱨ��
			Node zbzl = root.selectSingleNode("zbzl");
			this.saveDjFile(zbzl, applyId, fileArr);
			// ��������ѯ�ʱ�¼
			// xwblManager.saveShengchengXwbl(applyId,wflxbh);
			// ��������ѯ�ʱ�¼doc�ļ�
			//kcblManager.saveShengchengXwbl(applyId, wflxbh);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new AppException("�����鵥��Ϣʱ��������");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new AppException("���븽����Ϣ��������");
		}
	}

	/**
	 * 
	 * �������ܣ����������ϴ��ĸ�����Ϣ
	 * 
	 * ��������������ڵ㣬����id����ѹ����ļ�
	 * 
	 * ����ֵ��
	 * 
	 * @throws FileNotFoundException
	 */
	private void saveDjFile(Node node, String applyId, File[] fileArr)
			throws FileNotFoundException {
		if (node == null) {
			return;
		}
		List<Node> nodes = node.selectNodes("file");
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = 0; j < fileArr.length; j++) {
				String xmlfilename = "";
				xmlfilename = nodes.get(i).selectSingleNode("url").getText();
				if (xmlfilename.equals(fileArr[j].getName())) {
					File[] commfileArr = fileArr[j].listFiles();
					for (int m = 0; m < commfileArr.length; m++) {
						String nodename = nodes.get(i).selectSingleNode("name") == null ? ""
								: nodes.get(i).selectSingleNode("name")
										.getText();
						if (commfileArr[m].getName().substring(2).equals(nodename)) {
							String filename = nodes.get(i).selectSingleNode(
									"name") == null ? "" : nodes.get(i)
									.selectSingleNode("name").getText();
							String fileEnum = nodes.get(i).selectSingleNode(
									"attacode") == null ? "" : nodes.get(i)
									.selectSingleNode("attacode").getText();
							String fileid = nodes.get(i).selectSingleNode(
									"fileid") == null ? "" : nodes.get(i)
									.selectSingleNode("fileid").getText();
							// ɾ��ͬ���͸���  ��idɾ������֧��ͬ���͵��ļ�����ϴ���ֻҪ���ֲ�ͬ����μ�飩
							if("1007".equals(fileEnum) || "1107".equals(fileEnum) || "1207".equals(fileEnum) || "1307".equals(fileEnum) || "1407".equals(fileEnum) || "1510".equals(fileEnum) || "1602".equals(fileEnum) || "1604".equals(fileEnum)
									|| "1707".equals(fileEnum) || "1807".equals(fileEnum) || "2007".equals(fileEnum) || "2207".equals(fileEnum) || "2307".equals(fileEnum)){
									commonManager.removeFile(fileid);
									//commonManager.removeFileByPidAndFileType(applyId, fileEnum);
									commonManager.saveFile(new FileInputStream(
											commfileArr[m]), applyId, fileEnum,
											UploadFileType.WORK.getPath(), filename,
											commfileArr[m].length());
							}
							else if("1006".equals(fileEnum) || "1106".equals(fileEnum) || "1206".equals(fileEnum) || "1306".equals(fileEnum) || "1406".equals(fileEnum) || "1509".equals(fileEnum) || "1614".equals(fileEnum) || "1706".equals(fileEnum)
									|| "1806".equals(fileEnum) || "1906".equals(fileEnum) || "2006".equals(fileEnum) || "2106".equals(fileEnum) || "2206".equals(fileEnum) || "2306".equals(fileEnum) || "6601".equals(fileEnum) || "6602".equals(fileEnum)){
								// ɾ��ͬ���͸���  ��idɾ������֧��ͬ���͵��ļ�����ϴ���ֻҪ���ֲ�ͬ��׼�����ϣ�
								commonManager.removeFile(fileid);
								//commonManager.removeFileByPidAndFileType(applyId, fileEnum);
								commonManager.saveFile(new FileInputStream(
										commfileArr[m]), applyId, fileEnum,
										UploadFileType.WORK.getPath(), filename,
										commfileArr[m].length());
							}
							else{
								commonManager.removeFile(fileid);
								//commonManager.removeFileByPidAndFileType(applyId, fileEnum);
								commonManager.saveFile(new FileInputStream(
										commfileArr[m]), applyId, fileEnum,
										UploadFileType.WORK.getPath(), filename,
										commfileArr[m].length());
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * �������ܣ���������ѹ�����е�ѯ�ʱ�¼��Ϣ
	 * ���������
	 * ����ֵ��
	 * 
	 * @throws AppException
	 */
	private void saveXwblFromDjZip(String rwid, File[] fileArr, Element root)
			throws ParseException, DocumentException, AppException {
		// �����
		this.dao.exec("delete from T_Biz_Taskinverecord t where t.taskid_ = '"
				+ rwid + "' ");
		this.dao.exec("delete from T_Biz_Taskrecordans t where t.taskid_ = '"
				+ rwid + "' and t.kcxwbj_ ='1' ");
		Node xwblNode = root.selectSingleNode("xwbl");
		if (null == xwblNode) {
			throw new AppException("���߰���û��ѯ�ʱ�¼��Ϣ�����ܵ��룡");
		}
		TBizTaskinverecord xwbl = new TBizTaskinverecord();
		xwbl.setTaskid(rwid);
		String startTime = xwblNode.selectSingleNode("xwsjq") == null ? ""
				: xwblNode.selectSingleNode("xwsjq").getText();
		String endTime = xwblNode.selectSingleNode("xwsjz") == null ? ""
				: xwblNode.selectSingleNode("xwsjz").getText();
		xwbl.setStarttime(StringUtils.isNotBlank(startTime) ? DateUtil
				.convertStringToDate(startTime.replaceAll("/", "-")) : new Date(
				System.currentTimeMillis()));
		xwbl.setEndtime(StringUtils.isNotBlank(endTime) ? DateUtil
				.convertStringToDate(endTime.replaceAll("/", "-")) : new Date(
				System.currentTimeMillis()));
		xwbl.setXwdd(xwblNode.selectSingleNode("xwdd") == null ? "" : xwblNode
				.selectSingleNode("xwdd").getText());
		xwbl.setBxwdwmc(xwblNode.selectSingleNode("bxwdw") == null ? ""
				: xwblNode.selectSingleNode("bxwdw").getText());
		xwbl.setFddbr(xwblNode.selectSingleNode("fddbr") == null ? ""
				: xwblNode.selectSingleNode("fddbr").getText());
		xwbl.setFddbrdh(xwblNode.selectSingleNode("fddbrdh") == null ? ""
				: xwblNode.selectSingleNode("fddbrdh").getText());
		xwbl.setBxwrxm(xwblNode.selectSingleNode("bxwrxm") == null ? ""
				: xwblNode.selectSingleNode("bxwrxm").getText());
		xwbl.setBxwrxb(xwblNode.selectSingleNode("bxwrxb") == null ? ""
				: xwblNode.selectSingleNode("bxwrxb").getText());
		xwbl.setBxwrnl(xwblNode.selectSingleNode("bxwrnl") == null ? ""
				: xwblNode.selectSingleNode("bxwrnl").getText());
		xwbl.setBxwrzw(xwblNode.selectSingleNode("bxwrzw") == null ? ""
				: xwblNode.selectSingleNode("bxwrzw").getText());
		xwbl.setBxwrdh(xwblNode.selectSingleNode("bxwrdh") == null ? ""
				: xwblNode.selectSingleNode("bxwrdh").getText());
		xwbl.setBxwzrrxm(xwblNode.selectSingleNode("bxwzrrxm") == null ? ""
				: xwblNode.selectSingleNode("bxwzrrxm").getText());
		xwbl.setBxwzrrxb(xwblNode.selectSingleNode("bxwzrrxb") == null ? ""
				: xwblNode.selectSingleNode("bxwzrrxb").getText());
		xwbl.setBxwzrrnl(xwblNode.selectSingleNode("bxwzrrnl") == null ? ""
				: xwblNode.selectSingleNode("bxwzrrnl").getText());
		xwbl.setBxwzrrdh(xwblNode.selectSingleNode("bxwzrrdh") == null ? ""
				: xwblNode.selectSingleNode("bxwzrrdh").getText());
		xwbl.setBxwzrrszdw(xwblNode.selectSingleNode("bxwzrrszdw") == null ? ""
				: xwblNode.selectSingleNode("bxwzrrszdw").getText());
		xwbl.setBxwzrrzz(xwblNode.selectSingleNode("bxwzrrzz") == null ? ""
				: xwblNode.selectSingleNode("bxwzrrzz").getText());
		xwbl
				.setBxwzrrybagx(xwblNode.selectSingleNode("bxwzrrybagx") == null ? ""
						: xwblNode.selectSingleNode("bxwzrrybagx").getText());

		xwbl.setFddbrsfzh(xwblNode.selectSingleNode("fddbrsfzh") == null ? ""
				: xwblNode.selectSingleNode("fddbrsfzh").getText());
		xwbl.setBxwrsfzh(xwblNode.selectSingleNode("bxwrsfzh") == null ? ""
				: xwblNode.selectSingleNode("bxwrsfzh").getText());
		xwbl.setBxwzrrsfzh(xwblNode.selectSingleNode("bxwzrrsfzh") == null ? ""
				: xwblNode.selectSingleNode("bxwzrrsfzh").getText());
		// xwbl.setZfrydwmc(user.getArea().getYwbm());
		List<Node> jcrnames = xwblNode.selectSingleNode("Jcr").selectNodes(
				"name");
		String zfrynames = "";
		for (int i = 0; i < jcrnames.size(); i++) {
			zfrynames += jcrnames.get(i).getText();
			if (i != jcrnames.size() - 1) {
				zfrynames += ",";
			}
		}
		xwbl.setZfrynames(zfrynames);
		xwbl.setZfzhs(xwblNode.selectSingleNode("JcrZfzh") == null ? ""
				: xwblNode.selectSingleNode("JcrZfzh").getText());
		xwbl.setIsActive("Y");
		TSysUser user = CtxUtil.getCurUser();
		xwbl.setCreateby(user);
		xwbl.setCreated(new Date(System.currentTimeMillis()));
		xwbl.setUpdateby(user);
		xwbl.setUpdated(new Date(System.currentTimeMillis()));
		this.save(xwbl);
		for (int i = 0; i < fileArr.length; i++) {
			if (fileArr[i].getName().equals("xwblwt.xml")) {
				SAXReader reader = new SAXReader();
				Document xwblDoc = reader.read(fileArr[i]);
				Element xwblRoot = xwblDoc.getRootElement();
				List<Node> kcxwbldas = xwblRoot.selectNodes("jljg");
				if (kcxwbldas != null && kcxwbldas.size() > 0) {
					for (Node node : kcxwbldas) {
						String isXzwt = node.selectSingleNode("istmpjcx")
								.getText();// �Ƿ���������
						String jcxid = node.selectSingleNode("jcxid").getText();// ����id
						String jcxdesc = node.selectSingleNode("jcxdesc")
								.getText();// ����
						String jcjg = node.selectSingleNode("jcjg").getText();// ��
						String px = node.selectSingleNode("px").getText();// ����
						TBizTaskrecordans kcxwblda = new TBizTaskrecordans();
						kcxwblda.setRecordid("Y".equals(isXzwt) ? "" : jcxid);// id
						kcxwblda.setContent(jcxdesc);// ����������
						kcxwblda.setDanr(jcjg);// ������
						kcxwblda.setKcxwbj("1");// ����ѯ�����ֱ��0���� 1ѯ��
						kcxwblda.setOrderby(Integer.valueOf(px));// ����
						kcxwblda.setTaskid(rwid);// ����id
						kcxwblda.setWttype("Y".equals(isXzwt) ? "1" : "0");// �������ͣ�0
																			// ϵͳ
																			// 1���䣩
						kcxwblda.setIsActive("Y");
						kcxwblda.setCreateby(user);
						kcxwblda
								.setCreated(new Date(System.currentTimeMillis()));
						kcxwblda.setUpdateby(user);
						kcxwblda
								.setUpdated(new Date(System.currentTimeMillis()));
						this.save(kcxwblda);
					}
				}
				break;
			}
		}
	}

	/**
	 * 
	 * �������ܣ���������ѹ�����еĿ����¼��Ϣ
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 * 
	 * @throws AppException
	 */
	private void saveKcblFromDjZip(String rwid, File[] fileArr, Element root)
			throws ParseException, DocumentException, AppException {
		// �����
		this.dao.exec("delete from T_Biz_Taskinverecord t where t.taskid_ = '"
				+ rwid + "' ");
		this.dao.exec("delete from T_Biz_Taskrecordans t where t.taskid_ = '"
				+ rwid + "' and t.kcxwbj_ ='0' ");
		Node kcblNode = root.selectSingleNode("kcbl");
		if (null == kcblNode) {
			throw new AppException("���߰���û�п����¼��Ϣ�����ܵ��룡");
		}
		TBizTasksurveyrecord kcbl = new TBizTasksurveyrecord();
		kcbl.setTaskid(rwid);
		kcbl.setDsr(kcblNode.selectSingleNode("dsr") == null ? "" : kcblNode
				.selectSingleNode("dsr").getText());
		kcbl.setDz(kcblNode.selectSingleNode("dz") == null ? "" : kcblNode
				.selectSingleNode("dz").getText());
		String startTime = kcblNode.selectSingleNode("kcsjq") == null ? ""
				: kcblNode.selectSingleNode("kcsjq").getText();
		String endTime = kcblNode.selectSingleNode("kcsjz") == null ? ""
				: kcblNode.selectSingleNode("kcsjz").getText();
		kcbl.setStarttime(StringUtils.isNotBlank(startTime) ? DateUtil
				.convertStringToDate("yyyy��MM��dd��HHʱmm��", startTime)
				: new Date(System.currentTimeMillis()));
		kcbl.setEndtime(StringUtils.isNotBlank(endTime) ? DateUtil
				.convertStringToDate("yyyy��MM��dd��HHʱmm��", endTime) : new Date(
				System.currentTimeMillis()));
		kcbl.setDd(kcblNode.selectSingleNode("kcdd") == null ? "" : kcblNode
				.selectSingleNode("kcdd").getText());

		// 1����飨���죩��
		List<Node> listJcr = root.selectSingleNode("kcbl").selectSingleNode(
				"Jcr").selectNodes("id");
		for (Node node : listJcr) {
			if (StringUtils.isNotBlank(node.getText())) {
				// 2015-7-15 �޸� ��飨���죩�˶�ѡ
				String sql = " from TBizTaskuser where taskid=? and type='"
						+ TaskUserEnum.WFAJDC_KCBLJCKCR.getCode() + "'";
				this.dao.removeFindObjs(sql, rwid);
				for (int i = 0; i < node.getText().split(",").length; i++) {
					TBizTaskuser taskuser = new TBizTaskuser();
					taskuser.setTaskid(rwid);
					taskuser.setUserid(node.getText().split(",")[i]);
					TSysUser user = (TSysUser) this.dao.get(TSysUser.class,
							node.getText().split(",")[i]);
					taskuser.setUsername(user.getName());
					taskuser.setType(TaskUserEnum.WFAJDC_KCBLJCKCR.getCode());
					this.dao.save(taskuser);
				}
			}
		}

		// 2����¼��
		Node Jlr = root.selectSingleNode("kcbl").selectSingleNode("Jlr")
				.selectSingleNode("id");
		if (StringUtils.isNotBlank(Jlr.getText())) {
			String sql = " from TBizTaskuser where taskid=? and type='"
					+ TaskUserEnum.WFAJDC_KCBLJLR.getCode() + "'";
			this.dao.removeFindObjs(sql, rwid);
			TBizTaskuser taskuser = new TBizTaskuser();
			taskuser.setTaskid(rwid);
			taskuser.setUserid(Jlr.getText());
			TSysUser user = (TSysUser) this.dao.get(TSysUser.class, Jlr
					.getText());
			taskuser.setUsername(user.getName());
			taskuser.setType(TaskUserEnum.WFAJDC_KCBLJLR.getCode());
			this.dao.save(taskuser);
		}

		kcbl.setKcrid(null == kcblNode.selectSingleNode("Jcr")
				.selectSingleNode("name") ? "" : kcblNode.selectSingleNode(
				"Jcr").selectSingleNode("name").getText());
		kcbl.setZfzh(kcblNode.selectSingleNode("JcrZfzh") == null ? ""
				: kcblNode.selectSingleNode("JcrZfzh").getText());
		kcbl.setJlr(null == kcblNode.selectSingleNode("Jlr").selectSingleNode(
				"name") ? "" : kcblNode.selectSingleNode("Jlr")
				.selectSingleNode("name").getText());
		kcbl.setJlrzfzh(kcblNode.selectSingleNode("JcrZfzh") == null ? ""
				: kcblNode.selectSingleNode("JcrZfzh").getText());
		// kcbl.setZfrydwmc(user.getArea().getYwbm());
		kcbl.setIsActive("Y");
		TSysUser user = CtxUtil.getCurUser();
		kcbl.setCreateby(user);
		kcbl.setCreated(new Date(System.currentTimeMillis()));
		kcbl.setUpdateby(user);
		kcbl.setUpdated(new Date(System.currentTimeMillis()));
		this.save(kcbl);
		for (int i = 0; i < fileArr.length; i++) {
			if (fileArr[i].getName().equals("kcblwt.xml")) {
				SAXReader reader = new SAXReader();
				Document kcblDoc = reader.read(fileArr[i]);
				Element kcblRoot = kcblDoc.getRootElement();
				List<Node> kcxwbldas = kcblRoot.selectNodes("jljg");
				if (kcxwbldas != null && kcxwbldas.size() > 0) {
					for (Node node : kcxwbldas) {
						String isXzwt = node.selectSingleNode("istmpjcx")
								.getText();// �Ƿ���������
						String jcxid = node.selectSingleNode("jcxid").getText();// ����id
						String jcxdesc = node.selectSingleNode("jcxdesc")
								.getText();// ����
						String jcjg = node.selectSingleNode("jcjg").getText();// ��
						String px = node.selectSingleNode("px").getText();// ����
						TBizTaskrecordans kcxwblda = new TBizTaskrecordans();
						kcxwblda.setRecordid("Y".equals(isXzwt) ? "" : jcxid);// id
						kcxwblda.setContent(jcxdesc);// ����������
						kcxwblda.setDanr(jcjg);// ������
						kcxwblda.setKcxwbj("0");// ����ѯ�����ֱ��0���� 1ѯ��
						kcxwblda.setOrderby(Integer.valueOf(px));// ����
						kcxwblda.setTaskid(rwid);// ����id
						kcxwblda.setWttype("Y".equals(isXzwt) ? "1" : "0");// �������ͣ�0
																			// ϵͳ
																			// 1���䣩
						kcxwblda.setIsActive("Y");
						kcxwblda.setIsActive("Y");
						kcxwblda.setCreateby(user);
						kcxwblda
								.setCreated(new Date(System.currentTimeMillis()));
						kcxwblda.setUpdateby(user);
						kcxwblda
								.setUpdated(new Date(System.currentTimeMillis()));
						this.save(kcxwblda);
					}
				}
				break;
			}
		}
	}

	@Override
	public String getFirstShrName(String applyId) {
		String name = "";
		Work work = workDao.get(applyId);
		String[] shrIds = work.getShrids().split(",");
		if (null != shrIds && shrIds.length > 0) {
			name = shrIds[shrIds.length - 1];
		}
		return name;
	}

	@Override
	public void saveSbRwlxAndFile(String sjid, String sbAreaId, String rwlxIds,
			List<Map<String, String>> sbFileInfo,
			List<Map<String, String>> bLspsInfo,Map<String,String> jcjlMap,Map<String,String> rcbgDescMap) throws AppException {
		Work work = workDao.get(sjid);
		TBizTaskuser xpr = (TBizTaskuser) this.dao.find(
				"from TBizTaskuser where taskid = ? and type = ? ", sjid,
				TaskUserEnum.XPR.getCode()).get(0);
		TSysUser cur = (TSysUser) this.dao.get(TSysUser.class, xpr.getUserid());// ��������������Ϊ���渽����user

		// 1��������������
		this.saveTaskTypeMultiple(sjid, rwlxIds, cur,jcjlMap,rcbgDescMap);
		// 2�����渽����Ϣ
		saveDownloadSBFile(sjid, sbFileInfo, cur);
		// 3�������ϱ���ʾ��Ϣ
		saveSBLspsInfo(sjid, bLspsInfo, cur);
	}

	/**
	 * 
	 * �������ܣ� �������ɸ������洢 ���������
	 * 
	 * ����ֵ��
	 */
	private void saveDownloadSBFile(String workid,
			List<Map<String, String>> pFileInfo, TSysUser giveUser) {
		if (null != pFileInfo) {
			for (int i = 0; i < pFileInfo.size(); i++) {
				Map<String, String> map = pFileInfo.get(i);
				String name = map.get("name");// �ļ���ʵ����
				String size = map.get("size");// �ļ�size
				String osname = map.get("osname");// ��������ʾ�����ƣ�32λ�ַ���
				String url = map.get("url");// ip
				String path = map.get("path");// ���·��
				String ftpuser = map.get("ftpuser");// ftpuser
				String ftppass = map.get("ftppass");// ftppass
				String ftpport = map.get("ftpport");// ftpport

				String type = map.get("type");// type

				InputStream is = commonManager.downFtpFile(url, path, osname,
						ftpuser, ftppass, Integer.valueOf(ftpport));
				try {
					commonManager.saveFile(is, workid, type, "work", name, Long
							.valueOf(size), giveUser);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * 
	 * �������ܣ� �����ϱ���ʾ��Ϣ ���������
	 * 
	 * ����ֵ��
	 */
	private void saveSBLspsInfo(String workid,
			List<Map<String, String>> bLspsInfo, TSysUser giveUser) {
		Work work = workDao.get(workid);
		if (null != bLspsInfo) {
			for (int i = 0; i < bLspsInfo.size(); i++) {
				Map<String, String> map = bLspsInfo.get(i);
				WorkLog lo = new WorkLog();
				lo.setCzrId(map.get("czrId"));
				lo.setCzrName(map.get("czrName"));
				try {
					lo.setCzsj(DateUtil.convertStringToDate(
							"yyyy-MM-dd HH:mm:ss", map.get("czsj")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				lo.setWork(work);
				lo.setOperateType(map.get("operateType"));
				lo.setWorkSate(map.get("workSate"));
				try {
					lo.setStartTime(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", map.get("startTime")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				lo.setUserTime(DateDifferenceUtil.getTimeDifferenceValue(lo
						.getStartTime(), lo.getCzsj()));
				// ��ʾ���
				lo.setNote(map.get("note"));
				this.workDao.save(lo);
			}
		}
	}

	@Override
	public void saveJcjl(String taskid, String tasktype, String jcjl) throws Exception {
		//if(StringUtils.isNotBlank(jcjl)){
		//	jcjl = java.net.URLDecoder.decode(jcjl, "UTF-8");
		//}
		List list = this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", taskid, tasktype);
		if(null!=list&&list.size()>0){
			TBizTaskandtasktype tt = (TBizTaskandtasktype)list.get(0);
			tt.setJcjl(jcjl);
			this.dao.save(tt);
		}
	}

	@Override
	public String jcjl(String taskid, String tasktype) {
		TBizTaskandtasktype tt = (TBizTaskandtasktype) this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", taskid, tasktype).get(0);
		return tt.getJcjl();
	}

	@Override
	public void saveDownJcbgmb(String jcmbId, String applyId, String taskType, HttpServletResponse res) {
		try {
			List<TDataChecklisttemplate> templatePos = new ArrayList<TDataChecklisttemplate>();
			TDataChecklisttemplate subTmp = (TDataChecklisttemplate) this.get(TDataChecklisttemplate.class, jcmbId);
			templatePos.add(subTmp);
			// ��ȡ����ģ��
			jcdManagerImpl.getSubTemplate(jcmbId, templatePos);
			List<TDataChecklisttemplate> oldSubPos = new ArrayList<TDataChecklisttemplate>();
			StringBuffer sb = new StringBuffer();
			List<String> order = new ArrayList<String>();
			jcdManagerImpl.getContents(order,"1", applyId, jcmbId, oldSubPos, sb);
			String content = sb.toString();
			if (content.startsWith("\r")){
				content = content.substring(1, content.length());
			}
			// ������������ͱ�
			TBizTaskandtasktype tattPo = (TBizTaskandtasktype) this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", applyId, taskType).get(0);
			// ����˺ͼ�¼��ö��ֵ
			String jcrCode = TaskUserEnum.getCodeByEnum(TaskTypeCode.getEnumByCode(taskType) + "_JCR");
			String jlrCode = TaskUserEnum.getCodeByEnum(TaskTypeCode.getEnumByCode(taskType) + "_JLR");
			String jcrNames = "";
			List<TBizTaskuser> jcr = this.dao.find("from TBizTaskuser where taskid = ? and type = ? ", applyId, jcrCode);
			TBizTaskuser jlr = (TBizTaskuser) this.dao.find("from TBizTaskuser where taskid = ? and type = ? ", applyId, jlrCode).get(0);
			// �����
			TSysUserOrg jcrPo = null;
			for (int i = 0; i < jcr.size(); i++) {
				jcrPo = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", jcr.get(i).getUserid()).get(0);
				if (i > 0){
					jcrNames += "��";
				}
				jcrNames += jcrPo.getUser().getName() + "(" + jcrPo.getUser().getLawnumber() + ")";
			}
			
			// ��¼��
			TSysUserOrg jlrPo = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", jlr.getUserid()).get(0);
			// �����ִ������
			TBizTaskandlawobj lawobjPo = (TBizTaskandlawobj) this.dao.find("from TBizTaskandlawobj where taskid = ?", applyId).get(0);
			// ִ����������
			String lawObjType = lawobjPo.getLawobjtype();
			// ���ɼ�鵥���õ�����
			Map<String, String> paraMap = new HashMap<String, String>();
			
			// ������xx���������� �����ǵ�ǰ�û����ڲ��ŵ�����
			TSysUserOrg curOrg = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", CtxUtil.getCurUser().getId()).get(0);
			paraMap.put("$biaoti$", curOrg.getOrg().getUnitname());
			paraMap.put("$rwlx$", "");
			// ������
			paraMap.put("$sbjc$", content);
			// ���ʱ��
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			paraMap.put("$jcsj$", sdf.format(tattPo.getJcsj1()));
			// �ܼ쵥λ
			if (lawObjType.equals("07")){
				TDataLawobjdic xqyzDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '07' and enumname = ?", LawobjOutColunmEnum.xqyz_dwmc.getCode()).get(0);
				String xqyzDwmc = xqyzDic.getColengname();
				String sql = "select " + xqyzDwmc + " as xqyzDwmc from T_DATA_LAWOBJ where ID_ = ?";
				List<Object[]> data = this.dao.findBySql(sql, lawobjPo.getLawobjid());
				paraMap.put("$sjdw$", String.valueOf(data.get(0)));
			} else {
				paraMap.put("$sjdw$", lawobjPo.getLawobjname());
			}
			
			// �ܼ쵥λ��ַ
			paraMap.put("$dwdz$", lawobjPo.getAddress());
			// ���������˱���
			paraMap.put("$fddbtit$", lawobjPo.getZwtitle());
			// ����������
			paraMap.put("$fddb$", lawobjPo.getManager());
			// ��ϵ�绰
			paraMap.put("$fddbdh$", lawobjPo.getManagermobile());
			// �������
			paraMap.put("$bjcr$", lawobjPo.getBjcr());
			// ����ְ��
			paraMap.put("$bjcrzw$", lawobjPo.getZw());
			// ��ϵ�绰 
			paraMap.put("$bjcrdh$", lawobjPo.getLxdh());
			// �����
			paraMap.put("$jcr$", jcrNames);
			// ��¼��
			paraMap.put("$jlr$", jlrPo.getUser().getName() + "(" + jlrPo.getUser().getLawnumber() + ")");
			// ������λ
			paraMap.put("$gzdw$", jcrPo.getOrg().getUnitname());
			
			// ���ݲ�ִͬ�������������ɲ�ͬ��鵥
			String classPath = this.getClass().getResource("").getPath();
			classPath = java.net.URLDecoder.decode(classPath, "utf-8");
			// ģ��·��
			String templatePath = null;
			// ������Ŀ
			if (lawObjType.equals("02")){
				TDataLawobjdic jsxmmcDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '02' and enumname = ?", LawobjOutColunmEnum.jsxm_jsxmmc.getCode()).get(0);
				String jsxmmc = jsxmmcDic.getColengname();
				TDataLawobjdic jsddDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '02' and enumname = ?", LawobjOutColunmEnum.jsxm_jsdd.getCode()).get(0);
				String jsdd = jsddDic.getColengname();
				TDataLawobjdic dwmcDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '02' and enumname = ?", LawobjOutColunmEnum.jsxm_dwmc.getCode()).get(0);
				String dwmc = dwmcDic.getColengname();
				String sql = "select " + jsxmmc + " as jsxmmc, " + jsdd + " as jsdd, " + dwmc + " as dwmc from T_DATA_LAWOBJ where ID_ = ?";
				List<Object[]> data = this.dao.findBySql(sql, lawobjPo.getLawobjid());
				// ������Ŀ����
				paraMap.put("$jsxm$", StringUtil.isBlank(String.valueOf(data.get(0)[0])) ? "" : String.valueOf(data.get(0)[0]));
				// ����ص�
				paraMap.put("$jsdd$", StringUtil.isBlank(String.valueOf(data.get(0)[1])) ? "" : String.valueOf(data.get(0)[1]));
				// ��λ����
				paraMap.remove("$sjdw");
				paraMap.put("$sjdw$", StringUtil.isBlank(String.valueOf(data.get(0)[2])) ? "" : String.valueOf(data.get(0)[2]));
				templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//jcbg//jsxmTemplatedown.doc";
			}
			// ��������
			else if (lawObjType.equals("05")){
				TDataLawobjdic jzgdmcDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '05' and enumname = ?", LawobjOutColunmEnum.jzgd_sgxmmc.getCode()).get(0);
				String jzgdmc = jzgdmcDic.getColengname();
				TDataLawobjdic gcddDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '05' and enumname = ?", LawobjOutColunmEnum.jzgd_gcdd.getCode()).get(0);
				String gcdd = gcddDic.getColengname();
				TDataLawobjdic dwmcDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '05' and enumname = ?", LawobjOutColunmEnum.jzgd_sgdwmc.getCode()).get(0);
				String dwmc = dwmcDic.getColengname();
				String sql = "select " + jzgdmc + " as jzgdmc, " + gcdd + " as gcdd, " + dwmc + " as dwmc from T_DATA_LAWOBJ where ID_ = ?";
				List<Object[]> data = this.dao.findBySql(sql, lawobjPo.getLawobjid());
				// ������������
				paraMap.put("$jzgd$", StringUtil.isBlank(String.valueOf(data.get(0)[0])) ? "" : String.valueOf(data.get(0)[0]));
				// ���̵ص�
				paraMap.put("$gcdd$", StringUtil.isBlank(String.valueOf(data.get(0)[1])) ? "" : String.valueOf(data.get(0)[1]));
				// ��λ����
				paraMap.remove("$sjdw");
				paraMap.put("$sjdw$", StringUtil.isBlank(String.valueOf(data.get(0)[2])) ? "" : String.valueOf(data.get(0)[2]));
				templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//jcbg//jzgdTemplatedown.doc";
			}
			// ����
			else {
				templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//jcbg//jcbgTemplatedown.doc";
			}
			// ���ɵ�·��
			String dirPath = FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath());
			String filePath = PoiUtil.createWord(templatePath, dirPath, paraMap);
			InputStream fis = new FileInputStream(filePath);
			res.setHeader("Content-Disposition", "attachment;filename=" + new String(("��챨��.doc").getBytes("GB2312"), "ISO-8859-1"));
			res.setContentType("application/x-msdownload");
			OutputStream os = res.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = fis.read(b)) > 0) {
				os.write(b, 0, length);
			}
			fis.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getJcjl(String applyId, String taskType){
		String jcjl = null;
		Date jcsj = null;
		try {
			List<TBizTaskandtasktype> pos = this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", applyId,taskType);
			if(pos!=null && pos.size()>=0){
				jcjl = pos.get(0).getJcjl();
			}
			// ���֮ǰû����д�����ۣ����ش�ִ��������һ�μ��ʱ�ļ�����
			if (StringUtils.isBlank(jcjl)){
				// �����ִ������
				List<TBizTaskandlawobj> lawobjPoList = this.dao.find("from TBizTaskandlawobj where taskid = ? ", applyId);
				if(lawobjPoList!=null && lawobjPoList.size()>0){
					TBizTaskandlawobj lawobjPo = lawobjPoList.get(0);
					//�������һ�ι鵵����ļ�����
					StringBuffer sql = new StringBuffer("SELECT t.JCJL_ FROM T_BIZ_TASKANDTASKTYPE t ");
					sql.append(" left join T_Biz_Taskandlawobj d  on t.TASKID_= d.TASKID_ ");
					sql.append(" left join WORK_ w on w.id_ = t.TASKID_  where w.isActive_='Y' and w.state_='09'");
					sql.append(" and d.lawobjid_ = '"+lawobjPo.getLawobjid()+"'");
					sql.append(" order by w.archives_time_ desc");
					List<String> jcjls = this.dao.findBySql(sql.toString());
					if(jcjls!=null && jcjls.size()>0) {
						jcjl = jcjls.get(0); // ���һ�ι鵵����ļ�����
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jcjl;
	}
	//��������id��taskType��ȡ�ճ��칫��ע
	@Override
	public String getRcbgDesc(String applyId, String taskType){
		String desc = null;
		try {
			List<TBizTaskandtasktype> pos = this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", applyId,taskType);
			if(pos!=null && pos.size()>0){
				desc = pos.get(0).getDesc();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return desc;
	}
	@Override
	public TBizMoreCheck getMoreCheck(String fileId){
		if(StringUtils.isNotBlank(fileId)){//�˴μ�����¼�Ѵ��ڣ��޸ļ�����
			List<TBizMoreCheck> morechecks = this.dao.find("from TBizMoreCheck where fileid = ? ", fileId);
			if(morechecks!=null && morechecks.size()>0){
				return morechecks.get(0);
			}
		}
		return null;
	}
	
	@Override
	public TBizBlmbcs getBlmbc(String fileId){
		List<TBizBlmbcs> blmbcs = this.dao.find("from TBizBlmbcs where fileId = ? ", fileId);
		if(blmbcs!=null && blmbcs.size()>0){
			return blmbcs.get(0);
		}
		return null;
	}
	
	@Override
	public int getMaxTimes(String applyId,String taskType){
		if(StringUtils.isNotBlank(applyId)){//�˴μ�����¼�Ѵ��ڣ��޸ļ�����
			List<TBizMoreCheck> morechecks = this.dao.find("from TBizMoreCheck where work.id = ? and tasktypecode = ? order by times desc", applyId,taskType);
			if(morechecks!=null && morechecks.size()>0){
				return morechecks.get(0).getTimes();
			}
		}
		return 1;
	}
	
	@Override
	public void removeMoreCheck(TBizMoreCheck mc){
		if(mc!=null){//�˴μ�����¼�Ѵ��ڣ��޸ļ�����
			this.dao.remove(mc);
		}
	}
	
	@Override
	public void removeBlmbc(TBizBlmbcs bc){
		this.dao.remove(bc);
	}
	
	@Override
	public List<TBizMoreCheck> getMoreCheckList(String applyId,String taskType){
		if(StringUtils.isNotBlank(applyId)){//�˴μ�����¼�Ѵ��ڣ��޸ļ�����
			List<TBizMoreCheck> morechecks = this.dao.find("from TBizMoreCheck where work.id = ? and tasktypecode = ? order by times asc", applyId,taskType);
			if(morechecks!=null && morechecks.size()>0){
				return morechecks;
			}
		}
		return null;
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
			dataObject.put("filesize1", String.valueOf(obj[3]));
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
			rowsList.add(dataObject);
		}
		return fy;
	}

	@Override
	public void saveRcbg(String taskid, String tasktype, String desc)
			throws Exception {
		List list = this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", taskid, tasktype);
		if(null!=list&&list.size()>0){
			TBizTaskandtasktype tt = (TBizTaskandtasktype)list.get(0);
			tt.setDesc(desc);
			this.dao.save(tt);
		}
	}

	@Override
	public BlMainForm FindRcbg(String taskid, String tasktype) throws Exception {
		// TODO Auto-generated method stub
		BlMainForm blMainForm=new BlMainForm();
		BlRcbgForm blRcbgForm=new BlRcbgForm();
		List list=this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", taskid, tasktype);
		if(null!=list&&list.size()>0){
			TBizTaskandtasktype tt = (TBizTaskandtasktype)list.get(0);
			blRcbgForm.setDesc(tt.getDesc());
		    blMainForm.setBlRcbgForm(blRcbgForm);
		}
		return blMainForm;
	}
	
	/**
	 * 
	 * �������ܣ����������ϴ��ĸ���������Ϣ
	 * ��������������ڵ㣬����id����ѹ����ļ�
	 * ����ֵ��
	 */
	private void saveDjJccs(Node node, String applyId, File[] fileArr){
		if (node == null) {
			return;
		}
		//TSysUser user = CtxUtil.getCurUser();
		//Work work  = (Work) this.get(Work.class, applyId);
		List<Node> jccs = node.selectNodes("jccsjl");
		//TBizMoreCheck tbizmc = null;
		if (jccs != null && jccs.size() > 0) {
			//ÿ�ν���ǰ�Ƚ���taskidɾ��
			for (Node jcssnode : jccs) {
				String csid = jcssnode.selectSingleNode("csid").getText();//��¼id
				String taskid = jcssnode.selectSingleNode("taskid").getText();//����id
				String tasktypecode = jcssnode.selectSingleNode("tasktypecode").getText();//ģ������
				String times = jcssnode.selectSingleNode("times").getText();//���ɴ���
				String content = jcssnode.selectSingleNode("content").getText();//��ע
				String fileid = jcssnode.selectSingleNode("fileid").getText();//���ɵĸ���id
				String isactive = jcssnode.selectSingleNode("isactive").getText();//�Ƿ���Ч
				String version = jcssnode.selectSingleNode("version").getText();//�汾��
				String updated = jcssnode.selectSingleNode("updated").getText();//����ʱ��
				String updateby = jcssnode.selectSingleNode("updateby").getText();//������
				String created = jcssnode.selectSingleNode("created").getText();//����ʱ��
				String createby = jcssnode.selectSingleNode("createby").getText();//������
				//tbizmc = new TBizMoreCheck();
				StringBuffer sql = new StringBuffer();
    			sql.append(" insert into T_BIZ_MORECHECK");
    			sql.append(" (ID_, TASKID_, TASKTYPECODE_, TIMES_, CONTENT_, FILEID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(csid+"', '")
    			.append(applyId+"', '")
    			.append(tasktypecode+"', '")
    			.append(Integer.parseInt(times)+"', '")
    			.append(content+"', '")
    			.append(fileid+"', '")
    			.append(isactive+"', '")
    			.append(Integer.parseInt(version)+"', to_date('")
    			.append(updated.substring(0, updated.length()-2)+"', 'yyyy-MM-dd hh24:mi:ss'), '")
    			.append(updateby+"', to_date('")
    			.append(created.substring(0, created.length()-2)+"', 'yyyy-MM-dd hh24:mi:ss'), '")
    			.append(createby)
    			.append("')");
    			this.dao.exec(sql.toString());
				
    			/*tbizmc.setWork(work);
				tbizmc.setTasktypecode(tasktypecode);
				tbizmc.setTimes(Integer.parseInt(times));
				tbizmc.setContent(content);
				tbizmc.setFileid(fileid);
				tbizmc.setIsActive(isactive);
				tbizmc.setVersion(Integer.parseInt(version));
				tbizmc.setUpdated(new Date(System.currentTimeMillis()));
				tbizmc.setUpdateby(user);
				tbizmc.setCreateby(user);
				tbizmc.setCreated(new Date(System.currentTimeMillis()));
				this.dao.save(tbizmc);*/
			}
		}
	}

	@Override
	public List<Map<String, String>> getTaskTypeByTaskId() {
		// TODO Auto-generated method stub
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		StringBuilder sql = new StringBuilder();
		sql.append("select t.id_ , t.code_, t.name_, t.pid_, t.dotemplateid_, t.orderby_, t.desc_, t.isactive_, t.version_, t.updated_, t.updateby_, t.created_, t.createby_ from t_data_tasktype t where 1=1 and  t.isactive_ = 'Y' ");
		List<Object[]> list = dao.findBySql(sql.toString());
		Map<String, String> dataObject = null;
		for (Object[] ele : list) {
			dataObject = new HashMap<String, String>();
			dataObject.put("ids", String.valueOf(ele[0]));
			dataObject.put("code", String.valueOf(ele[1]));
			dataObject.put("name", String.valueOf(ele[2]));
			dataObject.put("pid", String.valueOf(ele[3]));
			dataObject.put("dotemplateid", String.valueOf(ele[4]));
			dataObject.put("orderby", String.valueOf(ele[5]));
			dataObject.put("desc", String.valueOf(ele[6]));
			dataObject.put("isactive", String.valueOf(ele[7]));
			dataObject.put("version", String.valueOf(ele[8]));
			dataObject.put("updated", String.valueOf(ele[9]));
			dataObject.put("updateby", String.valueOf(ele[10]));
			dataObject.put("created", String.valueOf(ele[11]));
			dataObject.put("createby", String.valueOf(ele[12]));
			rows.add(dataObject);
		}
		return rows;
	}
}