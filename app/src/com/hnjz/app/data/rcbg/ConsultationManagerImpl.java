package com.hnjz.app.data.rcbg;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileForm;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataMail;
import com.hnjz.app.data.po.TDataMailyj;
import com.hnjz.app.data.po.TDataRecmail;
import com.hnjz.app.work.wszz.WszzManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.po.TBizUserGroup;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserOrg;
import com.hnjz.sys.user.UserManager;

@Service("ConsultationManagerImpl")
public class ConsultationManagerImpl extends ManagerImpl implements ConsultationManager{
    private static  final Log log=LogFactory.getLog(ConsultationManagerImpl.class);
    @Autowired
	private WszzManager wszzManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private CommonManager commonManager;
	
    @Override
	public JSONArray queryUserByArea(List<String> id) throws Exception {
		JSONArray re = new JSONArray();
		List<TSysUser> listUser = null;
		List<TSysOrg> pos = new ArrayList<TSysOrg>();
		List<TSysArea> areas=new ArrayList<TSysArea>();
		List<TSysOrg> temp = new ArrayList<TSysOrg>();
		if(CtxUtil.getCurUser().getAreaId().equals("a0000000000000000000000000000000")){
			/**查询本区域及所有下级区域 */
			areas = areaManager.getChAreas(CtxUtil.getCurUser().getAreaId());
		}else{
			TSysArea ts=new TSysArea();
			ts.setId("a0000000000000000000000000000000");
			areas.add(ts);
			TSysArea tsCur=new TSysArea();
			tsCur.setId(CtxUtil.getCurUser().getAreaId());
			areas.add(tsCur);
		}
		for(int i = 0; i < areas.size(); i++){
			temp = this.dao.find("from TSysOrg where isActive='Y' and area.id =? order by orderby",areas.get(i).getId());
			pos.addAll(temp);
		}
		// 二，用户部门关联表查询
		List<TSysUserOrg> userOrgs = new ArrayList<TSysUserOrg>();
		// 循环部门查询部门用户关系
		List<TSysUserOrg> userOrg = null;
		for (int i = 0; i < pos.size(); i++) {
							
			// 查询业务人员
			userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y'  and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
			userOrgs.addAll(userOrg);
							
		}
		JSONObject obj = null;
		for (TSysUserOrg ele : userOrgs) {
			obj = new JSONObject();
			obj.put("id", ele.getUser().getId());
			obj.put("pid", ele.getOrg().getId());
			obj.put("iconSkin", "iconuser");
			obj.put("type", "u");
			obj.put("nocheck", false);
			obj.put("isorg", false);
			obj.put("name", ele.getUser().getName());
			if (null != id) {
				for (String mx : id) {
					if (StringUtils.equals(mx, ele.getUser().getId())) {
						obj.put("selected", true);
						obj.put("checked", true);
						break;
					}
				}
			}
			re.put(obj);
		}
				
		for (TSysOrg ele : pos) {
			obj = new JSONObject();
			obj.put("id", ele.getId());
				
			if (null != ele.getOrg()) {
			    obj.put("pid", ele.getOrg().getId());
			} else {
					obj.put("pid", "");
					}
							
			obj.put("nocheck", false);
			obj.put("iconSkin", "icondept");
			obj.put("name", ele.getName());
			obj.put("isorg", true);
		    re.put(obj);
		}
					
		return re;
	}
    
    @Override
	public JSONArray queryUserByGroup(List<String> id) throws Exception {
		JSONArray re = new JSONArray();
		List<TSysUser> listUser = null;
		List<TSysDic> pos = new ArrayList<TSysDic>();
		List<TSysArea> areas=new ArrayList<TSysArea>();
		List<TSysDic> temp = new ArrayList<TSysDic>();
		if(CtxUtil.getCurUser().getAreaId().equals("a0000000000000000000000000000000")){
			/**查询本区域及所有下级区域 */
			areas = areaManager.getChAreas(CtxUtil.getCurUser().getAreaId());
		}else{
			TSysArea ts=new TSysArea();
			ts.setId("a0000000000000000000000000000000");
			areas.add(ts);
			TSysArea tsCur=new TSysArea();
			tsCur.setId(CtxUtil.getCurUser().getAreaId());
			areas.add(tsCur);
		}
		temp = this.dao.find("from TSysDic where isActive='Y' and type = '29' order by orderby");
		pos.addAll(temp);
		// 二，用户分组关联表查询
		List<TBizUserGroup> userGroups = new ArrayList<TBizUserGroup>();
		// 循环部门查询部门用户关系
		List<TBizUserGroup> userGroup = null;
		for (int i = 0; i < pos.size(); i++) {
			// 查询业务人员
			userGroup = this.dao.find("from TBizUserGroup where user.isActive='Y'  and user.issys = 'N' and group.id = ? order by user.orderby", pos.get(i).getId());
			userGroups.addAll(userGroup);
		}
		JSONObject obj = null;
		for (TBizUserGroup ele : userGroups) {
			obj = new JSONObject();
			obj.put("id", ele.getUser().getId());
			obj.put("pid", ele.getGroup().getId());
			obj.put("iconSkin", "iconuser");
			obj.put("type", "u");
			obj.put("nocheck", false);
			obj.put("isorg", false);
			obj.put("name", ele.getUser().getName());
			if (null != id) {
				for (String mx : id) {
					if (StringUtils.equals(mx, ele.getUser().getId())) {
						obj.put("selected", true);
						obj.put("checked", true);
						break;
					}
				}
			}
			re.put(obj);
		}
		for (TSysDic ele : pos) {
			obj = new JSONObject();
			obj.put("id", ele.getId());
			obj.put("pid", "");
			obj.put("nocheck", false);
			obj.put("iconSkin", "icondept");
			obj.put("name", ele.getName());
			obj.put("isorg", true);
		    re.put(obj);
		}
		return re;
	}
    
	@Override
	public TDataMail saveConsultation(MailForm mailForm) throws Exception {
		if(StringUtil.isBlank(mailForm.getId())){
			TDataMail tmail=new TDataMail();
			tmail.setAreaId(CtxUtil.getAreaId());
			tmail.setChaoSong(mailForm.getChaoSong());
			tmail.setContent(mailForm.getContent());
			tmail.setCreater(CtxUtil.getCurUser());
			tmail.setCreateTime(new Date());
			//tmail.setFileId("");
			tmail.setIsActive("Y");
			//tmail.setOpinion("");
			//tmail.setPmailId("");
			tmail.setState("0");
			tmail.setRecList(mailForm.getRecList());
			tmail.setSendDate(new Date());
			//tmail.setState("");
			tmail.setTopic(mailForm.getTopic());
			tmail.setType("1");
			tmail.setUpdateby(CtxUtil.getCurUser());
			tmail.setUpdateTime(new Date());
			tmail.setUserId(CtxUtil.getUserId());
			TDataMail tdataMail=(TDataMail) this.dao.save(tmail);
			TDataRecmail tRecMail=null;
			//收件人
			if(StringUtil.isNotBlank(tdataMail.getRecList())){
				String[] recUser=tdataMail.getRecList().split(",");
				if(recUser.length>1){
					for(int i=0;i<recUser.length;i++){
						tRecMail=new TDataRecmail();
						tRecMail.setAreaId(userManager.getUser(recUser[i]).getAreaId());
						tRecMail.setCreater(tdataMail.getCreater());
						tRecMail.setCreateTime(tdataMail.getCreateTime());
						tRecMail.setUpdateTime(new Date());
						tRecMail.setPfrId(CtxUtil.getCurUser().getId());
						tRecMail.setUpdateby(CtxUtil.getCurUser());
						tRecMail.setIsActive("Y");
						tRecMail.setIsRead("N");
						tRecMail.setIsDel("N");
						tRecMail.setMailId(tdataMail.getId());
						tRecMail.setUserId(recUser[i]);
						this.dao.save(tRecMail);
					}
					}
			}
			//抄送人
			if(StringUtil.isNotBlank(tdataMail.getChaoSong())){
				String[] chaoSong=tdataMail.getChaoSong().split(",");
				if(chaoSong.length>1){
				for(int i=0;i<chaoSong.length;i++){
					tRecMail=new TDataRecmail();
					tRecMail.setAreaId(userManager.getUser(chaoSong[i]).getAreaId());
					tRecMail.setUpdateTime(new Date());
					tRecMail.setUpdateby(CtxUtil.getCurUser());
					tRecMail.setCreater(tdataMail.getCreater());
					tRecMail.setCreateTime(tdataMail.getCreateTime());
					tRecMail.setPfrId(CtxUtil.getCurUser().getId());
					tRecMail.setIsActive("Y");
					tRecMail.setIsRead("N");
					tRecMail.setIsDel("N");
					tRecMail.setMailId(tdataMail.getId());
					tRecMail.setUserId(chaoSong[i]);
					this.dao.save(tRecMail);
				}
				}
			}
			
			return tdataMail;
		}else{
			TDataMail tmail=(TDataMail) this.get(TDataMail.class, mailForm.getId());
			tmail.setChaoSong(mailForm.getChaoSong());
			tmail.setContent(mailForm.getContent());
			tmail.setCreater(CtxUtil.getCurUser());
			tmail.setCreateTime(new Date());
			//tmail.setFileId("");
			tmail.setIsActive("Y");
			tmail.setState("0");
			//tmail.setOpinion("");
			//tmail.setPmailId("");
			tmail.setRecList(mailForm.getRecList());
			tmail.setSendDate(new Date());
			//tmail.setState("");
			tmail.setTopic(mailForm.getTopic());
			tmail.setType("1");
			tmail.setUpdateby(CtxUtil.getCurUser());
			tmail.setUpdateTime(new Date());
			tmail.setUserId(CtxUtil.getUserId());
			TDataMail tdataMail=(TDataMail) this.dao.save(tmail);
			TDataRecmail tRecMail=null;
			//收件人
			if(StringUtil.isNotBlank(tdataMail.getRecList())){
				String[] recUser=tdataMail.getRecList().split(",");
				if(recUser.length>1){
					for(int i=0;i<recUser.length;i++){
						tRecMail=new TDataRecmail();
						tRecMail.setAreaId(userManager.getUser(recUser[i]).getAreaId());
						tRecMail.setCreater(tdataMail.getCreater());
						tRecMail.setCreateTime(tdataMail.getCreateTime());
						tRecMail.setUpdateTime(new Date());
						tRecMail.setPfrId(CtxUtil.getCurUser().getId());
						tRecMail.setUpdateby(CtxUtil.getCurUser());
						tRecMail.setIsActive("Y");
						tRecMail.setIsRead("N");
						tRecMail.setIsDel("N");
						tRecMail.setMailId(tdataMail.getId());
						tRecMail.setUserId(recUser[i]);
						this.dao.save(tRecMail);
					}
					}
			}
			//抄送人
			if(StringUtil.isNotBlank(tdataMail.getChaoSong())){
				String[] chaoSong=tdataMail.getChaoSong().split(",");
				if(chaoSong.length>1){
				for(int i=0;i<chaoSong.length;i++){
					tRecMail=new TDataRecmail();
					tRecMail.setAreaId(userManager.getUser(chaoSong[i]).getAreaId());
					tRecMail.setUpdateTime(new Date());
					tRecMail.setUpdateby(CtxUtil.getCurUser());
					tRecMail.setCreater(tdataMail.getCreater());
					tRecMail.setCreateTime(tdataMail.getCreateTime());
					tRecMail.setPfrId(CtxUtil.getCurUser().getId());
					tRecMail.setIsActive("Y");
					tRecMail.setIsRead("N");
					tRecMail.setIsDel("N");
					tRecMail.setMailId(tdataMail.getId());
					tRecMail.setUserId(chaoSong[i]);
					this.dao.save(tRecMail);
				}
				}
			}
			return tdataMail;
		}
	}
	@Override
	public FyWebResult queryRecConsultation(String isActive,String startTime,String endTime,
			String pfrId,String topic, String page,String pageSize)
			throws Exception {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" select a.id_ ,a.mailid_ ,a.userid_ ,a.isread_ ,a.isactive_,a.created_ from t_data_recmail a  left join t_data_mail b on a.mailid_=b.id_  where a.isactive_='Y' and a.isdel_='N' ");
		Map<String, Object> condition = new HashMap<String, Object>();
		TSysUser cutUser=CtxUtil.getCurUser();
			sql.append(" and a.userid_=:userId");
			condition.put("userId", cutUser.getId());
			if(StringUtil.isNotBlank(startTime)){
				sql.append(" and a.created_ >= TO_DATE(:startTime,'yyyy-MM-dd hh24:mi:ss')");
				condition.put("startTime", startTime+" 00:00:00");
			}
			//派发时间
	       	if(StringUtil.isNotBlank(endTime)){
				sql.append(" and a.created_ <= TO_DATE(:endTime,'yyyy-MM-dd hh24:mi:ss')");
				condition.put("endTime", endTime+" 23:59:59");
			}
			//派发人Name
			if(StringUtil.isNotBlank(pfrId)){
				sql.append(" and a.pfrid_=:pfrId");
				condition.put("pfrId", pfrId);
			}
			//主题
			if(StringUtil.isNotBlank(topic)){
				//this.dao.findBySql(sql, canshu)
				sql.append(" and b.topic_ like :topic");
				condition.put("topic", "%"+topic+"%");
			}
		
		sql.append(" order by b.senddate_ desc ");
		//FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<Object[]> recUserList = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : recUserList) {
			dataObject = new HashMap<String, String>();
			String id=String.valueOf(obj[0]);
			TSysOrg org=wszzManager.getOrgbyUser(this.findMailByMailId(String.valueOf(obj[1])).getUserId());
			TSysUser user=userManager.getUser(this.findMailByMailId(String.valueOf(obj[1])).getUserId());
			dataObject.put("id", id);
			String topic1 = String.valueOf(this.findMailByMailId(String.valueOf(obj[1])).getTopic());
			dataObject.put("danWei", String.valueOf(org.getUnitname()));
			dataObject.put("buMen", String.valueOf(org.getName()));
			dataObject.put("userName", String.valueOf(user.getName()));
			dataObject.put("faSongTime", String.valueOf(obj[5]));
			dataObject.put("topic", topic1);
			if(StringUtils.isNotBlank(topic1) && topic1.length()>30){
				dataObject.put("homeTopic", topic1.substring(0,30)+"...");
			}else{
				dataObject.put("homeTopic", topic1);
			}
			
			dataObject.put("isRead", String.valueOf(obj[3]).equals("N")?"<img src='/app/static/app/images/b2.png'>":"已阅");
			dataObject.put("isNew", String.valueOf(obj[3]).equals("N")?"<img src='/app/static/app/images/b2.png'>":"");
			dataObject.put("isActive", "Y".equals(String.valueOf(obj[4]))?"有效":"无效");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//增删改查所有企业，通过后台权限配置
			dataObject.put("operate", "<a id='"+id+"' class='b-link' onclick='info(this)'>查看</a>  <a id='"+id+"' class='b-link' onclick='del(this)'>删除</a> ");
			rows.add(dataObject);
		}
		
		fy.setRows(rows);
		
		return fy;
	}
	
	@Override
	public int getwdhsCount() {
		String sql = "select count(a.id_) from t_data_recmail a  " +
				"left join t_data_mail b on a.mailid_=b.id_  where a.isactive_='Y' and a.isread_ ='N' and a.isdel_='N' and a.userid_= '"+CtxUtil.getUserId()+"'";
		int count = Integer.parseInt(String.valueOf(this.dao.findBySql(sql).get(0)));
		return count;
	}
	
	@Override
	public TDataMail findMailByMailId(String mailId) throws Exception {
		if(StringUtil.isNotBlank(mailId)){
			TDataMail tDataMail=(TDataMail) this.get(TDataMail.class, mailId);
			return tDataMail;
		}else{
			return null;
		}
		
	}
	@Override
	public MailForm queryMailByMailId(String id) throws Exception {
		if(StringUtil.isNotBlank(id)){
			TDataMail tm=this.findMailByMailId(this.queryRecMailById(id).getMailId());
			MailForm mf=new MailForm();
			mf.setAreaId(tm.getAreaId());
			//String chaosongName="";
			//抄送人
			mf.setId(tm.getId());
			mf.setChaoSong(this.getUserNames(tm.getChaoSong()));
			//收件人
			mf.setRecList(this.getUserNames(tm.getRecList()));
			//发件人
			mf.setUserId(userManager.getUser(tm.getUserId()).getName());
			mf.setTopic(tm.getTopic());
			mf.setContent(tm.getContent());
			return mf;
		}else{
			return null;
		}
	}
	/**
	 * 根据用户的ids查询出符合条件的用户名称
	 * 
	 * @param ids
	 *            用户登录名
	 * @return 用户名称，用";"隔开
	 */
	@Override
	public String getUserNames(String ids)throws Exception {
		if(StringUtil.isNotBlank(ids)){
			String[] rec=ids.split(",");
			StringBuilder str = new StringBuilder();
			for(int i=0;i<rec.length;i++){
				str.append(userManager.getUser(rec[i]).getName()).append(" ; ");
			}
			if (str.length() > 0) {
				return str.substring(0, str.length() - 1);
			}
			return str.toString();
		}else{
			return "";
		}
		
	}
	@Override
	public TDataRecmail queryRecMailById(String id) throws Exception {
		if(StringUtil.isNotBlank(id)){
		TDataRecmail tDataRecmail=(TDataRecmail) this.get(TDataRecmail.class, id);
		return tDataRecmail;
		}else{
			return null;
		}
	}
	@Override
	public RecMailForm queryRecMailFormById(String id) throws Exception {
		if(StringUtil.isNotBlank(id)){
			TDataRecmail tr=this.queryRecMailById(id);
			RecMailForm rf=new RecMailForm();
			rf.setId(tr.getId());
			rf.setIsActive(tr.getIsActive());
			return rf;
		}else{
			return null;	
		}
		
		
	}
	@Override
	public IsReadForm queryIsReadFormByMailId(String id,String isRead) throws Exception {
		if(StringUtil.isNotBlank(id)){
			TDataRecmail tr=this.queryRecMailById(id);
			IsReadForm isReadForm=new IsReadForm();
			List<TDataRecmail> tDataRecmailFalse=this.dao.find("from TDataRecmail t where t.isActive='Y' and t.mailId=? and t.isRead=? ",tr.getMailId(),"N");
			isReadForm.setNoReadName(this.queryNameAndNoRead(tDataRecmailFalse));
			List<TDataRecmail> tDataRecmailTrue=this.dao.find("from TDataRecmail t where t.isActive='Y' and t.mailId=? and t.isRead=? ",tr.getMailId(),"Y");
			isReadForm.setIsReadName(this.queryNameAndIsRead(tDataRecmailTrue));

			return isReadForm;
		}else{
			return null;	
		}
		
	}
	@Override
	public String queryNameAndIsRead(List<TDataRecmail> list)throws Exception{
		StringBuilder isReadTrue= new StringBuilder("");
		for(int i=0;i<list.size();i++){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   String dateString = formatter.format(list.get(i).getReadDate());

			isReadTrue.append(userManager.getUser(list.get(i).getUserId()).getName()).append(" [").append(dateString).append("]").append(" ; ");
		}
		if (isReadTrue.length() > 0) {
			return isReadTrue.substring(0, isReadTrue.length() - 1);
		}
		return isReadTrue.toString();
	}
	@Override
	public String queryNameAndNoRead(List<TDataRecmail> list) throws Exception {
		StringBuilder isReadTrue= new StringBuilder("");
		for(int i=0;i<list.size();i++){
			isReadTrue.append(userManager.getUser(list.get(i).getUserId()).getName()).append(" ; ");
		}
		if (isReadTrue.length() > 0) {
			return isReadTrue.substring(0, isReadTrue.length() - 1);
		}
		return isReadTrue.toString();
	}
	@Override
	public TDataMailyj saveYiJian(MailyjForm mailyjForm, String recMailId,String yjId)
			throws Exception {
		TDataMailyj tm=null;
		if(StringUtil.isBlank(yjId)){
			tm=new TDataMailyj();
			tm.setMailId(mailyjForm.getMailId());
			tm.setUserId(this.findMailByMailId(mailyjForm.getMailId()).getUserId());
			tm.setSetUserId(CtxUtil.getUserId());
			tm.setAreaId(CtxUtil.getAreaId());
			tm.setCreater(CtxUtil.getCurUser());
			tm.setFuJianId(mailyjForm.getFuJianId());
			tm.setCreateTime(new Date());
			tm.setIsActive("Y");
			tm.setIsRead("Y");
			tm.setReadDate(new Date());
			tm.setUpdateby(CtxUtil.getCurUser());
			tm.setUpdateTime(new Date());
			tm.setYiJianContent(mailyjForm.getYiJianContent());
			TDataMailyj tyj=(TDataMailyj) this.dao.save(tm);
//			if(StringUtil.isNotBlank(recMailId)){
//				List<TDataRecmail> trmList=this.dao.find("from TDataRecmail t where t.isActive='Y' and t.mailId=? and t.userId=? ",mailyjForm.getMailId(),CtxUtil.getCurUser().getId());
//				for(int i=0;i<trmList.size();i++){
//					TDataRecmail tr=trmList.get(i);
//					tr.setIsRead("Y");
//					tr.setUpdateTime(new Date());
//					tr.setUpdateby(CtxUtil.getCurUser());
//					//tr.setReadDate(new Date());
//					this.dao.save(tr);	
//				}
//				
//			}
			return tyj;
		}else{
			tm=(TDataMailyj) this.get(TDataMailyj.class, yjId);
			tm.setMailId(mailyjForm.getMailId());
			//tm.setUserId(this.findMailByMailId(mailyjForm.getMailId()).getUserId());
			tm.setSetUserId(CtxUtil.getUserId());
			//tm.setAreaId(CtxUtil.getAreaId());
			//tm.setCreater(CtxUtil.getCurUser());
			tm.setFuJianId(mailyjForm.getFuJianId());
			//tm.setCreateTime(new Date());
			tm.setIsActive("Y");
			tm.setIsRead("Y");
			tm.setReadDate(new Date());
			tm.setUpdateby(CtxUtil.getCurUser());
			tm.setUpdateTime(new Date());
			tm.setYiJianContent(mailyjForm.getYiJianContent());
			TDataMailyj tyj=(TDataMailyj) this.dao.save(tm);
//			if(StringUtil.isNotBlank(recMailId)){
//				List<TDataRecmail> trmList=this.dao.find("from TDataRecmail t where t.isActive='Y' and t.mailId=? and t.userId=? ",mailyjForm.getMailId(),CtxUtil.getCurUser().getId());
//				for(int i=0;i<trmList.size();i++){
//					TDataRecmail tr=trmList.get(i);
//					tr.setIsRead("Y");
//					tr.setUpdateTime(new Date());
//					tr.setUpdateby(CtxUtil.getCurUser());
//					tr.setReadDate(new Date());
//					this.dao.save(tr);	
//				}
//				
//			}
			return tyj;
		}
		
	}
	@Override
	public List<MailyjForm> queryAllYiJianByMailId(String mailId)
			throws Exception {
		List<MailyjForm> list=new ArrayList<MailyjForm>();
		if(StringUtil.isNotBlank(mailId)){
			List<TDataMailyj> listMail=this.dao.find("from TDataMailyj t where t.isActive='Y' and t.isRead='Y' and t.mailId=? order by t.updateTime desc", mailId);
			for(int i=0;i<listMail.size();i++){
				StringBuilder sber=new StringBuilder();
				MailyjForm mf=new MailyjForm();
				mf.setUserId(userManager.getUser(listMail.get(i).getSetUserId()).getName());
				String fuJianIds=listMail.get(i).getFuJianId();
				if(StringUtil.isNotBlank(fuJianIds)){
					String[] fuJianId=fuJianIds.split(",");
					for(int j=0;j<fuJianId.length;j++){
					List<TDataFile> listFile=this.dao.find("from TDataFile t where t.isActive='Y' and t.id=? ",fuJianId[j]);
					if(listFile.size()>0){sber.append("<a id='").append(listFile.get(0).getId())
						.append("' class='b-link' onclick='exportFile(this.id)'>").append(listFile.get(0).getName()).append("</a>").append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>");
					}
					}
				}
				mf.setFuJianId(sber.toString());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(listMail.get(i).getReadDate());
				//mf.setReadDate(dateString);
				mf.setReadTime(dateString);
				mf.setYiJianContent(listMail.get(i).getYiJianContent());
				list.add(mf);
			}
			
		}else{
			list=new ArrayList<MailyjForm>();
		}
		return list;
	}
	@Override
	public FyWebResult queryYiSendConsultation(String isActive,String startTime,String endTime,
			String recId,String topic, String page,String pageSize) throws Exception {
		//QueryCondition data = new QueryCondition();
	//	data.setPageSize(pageSize);
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataMail a where a.isActive='Y' and a.state='1' ");
		TSysUser cutUser=CtxUtil.getCurUser();
		sql.append(" and a.userId=:userId");
		data.put("userId", cutUser.getId());
		if(StringUtil.isNotBlank(startTime)){
			sql.append(" and a.createTime >= TO_DATE(:startTime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("startTime", startTime+" 00:00:00");
		}
		//派发时间
       	if(StringUtil.isNotBlank(endTime)){
			sql.append(" and a.createTime <= TO_DATE(:endTime,'yyyy-MM-dd hh24:mi:ss')");
			data.put("endTime", endTime+" 23:59:59");
		}
		//派发人Name
		if(StringUtil.isNotBlank(recId)){
			sql.append(" and a.recList like :recId");
			data.put("recId", "%"+recId+"%");
		}
		//主题
		if(StringUtil.isNotBlank(topic)){
			//this.dao.findBySql(sql, canshu)
			sql.append(" and a.topic like :topic");
			data.put("topic", "%"+topic+"%");
		}
		
		sql.append(" order by a.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataMail> recUserList = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		Map<String, String> dataObject = null;
		for (TDataMail tm: recUserList) {
			dataObject = new HashMap<String, String>();
			String id=String.valueOf(tm.getId());
			dataObject.put("id",id );
			dataObject.put("topic", String.valueOf(String.valueOf(tm.getTopic())));
			dataObject.put("recName", this.getUserNames(String.valueOf(tm.getRecList())));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			   String dateString = formatter.format(tm.getSendDate());
			dataObject.put("sendDate", String.valueOf(String.valueOf(dateString)));
			dataObject.put("isActive", "Y".equals(String.valueOf(tm.getIsActive()))?"有效":"无效");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//增删改查所有企业，通过后台权限配置
			dataObject.put("operate", "<a id='"+id+"' class='b-link' onclick='info(this)'>查看</a>  <a id='"+id+"' class='b-link' onclick='del(this)'>删除</a> ");
			rows.add(dataObject);
		}
		
//		
//		StringBuilder sql = new StringBuilder(" select a.id_  ,a.userid_ ,a.topic_ ,dbms_lob.substr(a.reclist_),a.senddate_,a.chaosong_,a.isactive_,a.created_ " +
//				"from t_data_mail a  where a.isactive_='Y' and a.state_='1' ");
//		Map<String, Object> condition = new HashMap<String, Object>();
//		TSysUser cutUser=CtxUtil.getCurUser();
//			sql.append(" and a.userid_=:userId");
//			condition.put("userId", cutUser.getId());
//			if(StringUtil.isNotBlank(startTime)){
//				sql.append(" and a.created_ >= TO_DATE(:startTime,'yyyy-MM-dd hh24:mi:ss')");
//				condition.put("startTime", startTime+" 00:00:00");
//			}
//			//派发时间
//	       	if(StringUtil.isNotBlank(endTime)){
//				sql.append(" and a.created_ <= TO_DATE(:endTime,'yyyy-MM-dd hh24:mi:ss')");
//				condition.put("endTime", endTime+" 23:59:59");
//			}
//			//派发人Name
//			if(StringUtil.isNotBlank(recId)){
//				sql.append(" and a.reclist_ like :recId");
//				condition.put("recId", "%"+recId+"%");
//			}
//			//主题
//			if(StringUtil.isNotBlank(topic)){
//				//this.dao.findBySql(sql, canshu)
//				sql.append(" and a.topic_ like :topic");
//				condition.put("topic", "%"+topic+"%");
//			}
//		sql.append(" order by a.updated_ desc ");
//		//FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
//		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
//		FyWebResult fy = new FyWebResult(pos);
//		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
//		
//		List<Object[]> recUserList = pos.getRe();
//		Map<String, String> dataObject = null;
//		for (Object[] obj : recUserList) {
//			dataObject = new HashMap<String, String>();
//			String id=String.valueOf(obj[0]);
//			dataObject.put("id",id );
//			dataObject.put("topic", String.valueOf(String.valueOf(obj[2])));
//			dataObject.put("recName", this.getUserNames(String.valueOf(obj[3])));
//			dataObject.put("sendDate", String.valueOf(String.valueOf(obj[4])));
//			dataObject.put("isActive", "Y".equals(String.valueOf(obj[6]))?"有效":"无效");
//			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
//			//增删改查所有企业，通过后台权限配置
//			dataObject.put("operate", "<a id='"+id+"' class='b-link' onclick='info(this)'>查看</a>  <a id='"+id+"' class='b-link' onclick='del(this)'>删除</a> ");
//			rows.add(dataObject);
//		}
		
		fy.setRows(rows);
		
		return fy;
	}
	@Override
	public MailForm queryMailById(String mailId) throws Exception {
		if(StringUtil.isNotBlank(mailId)){
			TDataMail tm=this.findMailByMailId(mailId);
			MailForm mf=new MailForm();
			mf.setAreaId(tm.getAreaId());
			//String chaosongName="";
			//抄送人
			mf.setId(tm.getId());
			mf.setChaoSong(this.getUserNames(tm.getChaoSong()));
			//收件人
			mf.setRecList(this.getUserNames(tm.getRecList()));
			//发件人
			mf.setUserId(userManager.getUser(tm.getUserId()).getName());
			mf.setTopic(tm.getTopic());
			mf.setContent(tm.getContent());
			return mf;
		}else{
			return null;
		}
	}
	@Override
	public IsReadForm queryIsReadFormById(String id, String isRead)
			throws Exception {
		if(StringUtil.isNotBlank(id)){
			IsReadForm isReadForm=new IsReadForm();
			List<TDataRecmail> tDataRecmailFalse=this.dao.find("from TDataRecmail t where t.mailId=? and t.isRead=? ",id,"N");
			isReadForm.setNoReadName(this.queryNameAndNoRead(tDataRecmailFalse));
			List<TDataRecmail> tDataRecmailTrue=this.dao.find("from TDataRecmail t where t.mailId=? and t.isRead=? ",id,"Y");
			isReadForm.setIsReadName(this.queryNameAndIsRead(tDataRecmailTrue));

			return isReadForm;
		}else{
			return null;	
		}
	}
	@Override
	public void delRecMailById(String id) throws Exception {
		TDataRecmail tr=(TDataRecmail) this.get(TDataRecmail.class, id);
		tr.setIsDel("Y");
		tr.setUpdateTime(new Date());
		tr.setUpdateby(CtxUtil.getCurUser());
		this.dao.save(tr);
	}
	@Override
	public void delYiSendMailById(String id) throws Exception {
		TDataMail tm=(TDataMail) this.get(TDataMail.class, id);
		tm.setIsActive("N");
		tm.setUpdateby(CtxUtil.getCurUser());
		tm.setUpdateTime(new Date());
		this.dao.save(tm);
		List<TDataRecmail> list=this.dao.find("from TDataRecmail t where t.isDel='N' and t.isActive='Y' and t.mailId=?", id);
	    for(TDataRecmail tr: list){
	    	tr.setUpdateby(CtxUtil.getCurUser());
	    	tr.setIsActive("N");
	    	tr.setIsDel("Y");
	    	tr.setUpdateTime(new Date());
	    	this.dao.save(tr);
	    }
	}
	@Override
	public TDataFile saveFuJian(MultipartFile multipartFile,
			HttpServletRequest request,String pid,String fileType) throws AppException {
		TDataFile tDataFile = null;
		try {
			InputStream is = multipartFile.getInputStream();
			pid=request.getParameter("pid");
			fileType = FileTypeEnums.HSFJFSHSFJ.getCode();
			String path =UploadFileType.WORK.getPath();
			String fileName = multipartFile.getOriginalFilename();
			Long size = multipartFile.getSize();
			tDataFile = commonManager.saveFile(is, pid, fileType, path, fileName, size);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("上传失败。");
		}
		return tDataFile;
	}
	@Override
	public List<Map<String, String>> queryFileList(String pid) throws Exception {
		List<TDataFile> tList=this.dao.find(" from TDataFile t where t.isActive='Y' and t.pid=? and t.type=? ",pid,FileTypeEnums.HSFJFSHSFJ.getCode());
		List<Map<String, String>> listMap=new ArrayList<Map<String,String>>();
		Map<String, String> map = null;
		for(TDataFile tf:tList){
			map=new HashMap<String, String>();
			map.put("id", tf.getId());
			map.put("name", tf.getName());
			listMap.add(map);
		}
		return listMap;
	}
	@Override
	public TDataMail saveSendConsultation(MailForm mailForm) throws Exception {
		if(StringUtil.isBlank(mailForm.getId())){
			TDataMail tmail=new TDataMail();
			tmail.setAreaId(CtxUtil.getAreaId());
			tmail.setChaoSong(mailForm.getChaoSong());
			tmail.setContent(mailForm.getContent());
			tmail.setCreater(CtxUtil.getCurUser());
			tmail.setCreateTime(new Date());
			//tmail.setFileId("");
			tmail.setIsActive("Y");
			//tmail.setOpinion("");
			//tmail.setPmailId("");
			tmail.setState("1");
			tmail.setRecList(mailForm.getRecList());
			tmail.setSendDate(new Date());
			//tmail.setState("");
			tmail.setTopic(mailForm.getTopic());
			tmail.setType("1");
			tmail.setUpdateby(CtxUtil.getCurUser());
			tmail.setUpdateTime(new Date());
			tmail.setUserId(CtxUtil.getUserId());
			TDataMail tdataMail=(TDataMail) this.dao.save(tmail);
			TDataRecmail tRecMail=null;
			//收件人
			if(StringUtil.isNotBlank(tdataMail.getRecList())){
				String[] recUser=tdataMail.getRecList().split(",");
				if(recUser.length>=1){
					for(int i=0;i<recUser.length;i++){
						tRecMail=new TDataRecmail();
						tRecMail.setAreaId(userManager.getUser(recUser[i]).getAreaId());
						tRecMail.setCreater(tdataMail.getCreater());
						tRecMail.setCreateTime(tdataMail.getCreateTime());
						tRecMail.setUpdateTime(new Date());
						tRecMail.setPfrId(CtxUtil.getCurUser().getId());
						tRecMail.setUpdateby(CtxUtil.getCurUser());
						tRecMail.setIsActive("Y");
						tRecMail.setIsRead("N");
						tRecMail.setIsDel("N");
						tRecMail.setMailId(tdataMail.getId());
						tRecMail.setUserId(recUser[i]);
						this.dao.save(tRecMail);
					}
					}
			}
			//抄送人
			if(StringUtil.isNotBlank(tdataMail.getChaoSong())){
				String[] chaoSong=tdataMail.getChaoSong().split(",");
				if(chaoSong.length>=1){
				for(int i=0;i<chaoSong.length;i++){
					tRecMail=new TDataRecmail();
					tRecMail.setAreaId(userManager.getUser(chaoSong[i]).getAreaId());
					tRecMail.setUpdateTime(new Date());
					tRecMail.setUpdateby(CtxUtil.getCurUser());
					tRecMail.setCreater(tdataMail.getCreater());
					tRecMail.setCreateTime(tdataMail.getCreateTime());
					tRecMail.setPfrId(CtxUtil.getCurUser().getId());
					tRecMail.setIsActive("Y");
					tRecMail.setIsRead("N");
					tRecMail.setIsDel("N");
					tRecMail.setMailId(tdataMail.getId());
					tRecMail.setUserId(chaoSong[i]);
					this.dao.save(tRecMail);
				}
				}
			}
			
			return tdataMail;
		}else{
			TDataMail tmail=(TDataMail) this.get(TDataMail.class, mailForm.getId());
			tmail.setChaoSong(mailForm.getChaoSong());
			tmail.setContent(mailForm.getContent());
			tmail.setCreater(CtxUtil.getCurUser());
			tmail.setCreateTime(new Date());
			//tmail.setFileId("");
			tmail.setIsActive("Y");
			//tmail.setOpinion("");
			//tmail.setPmailId("");
			tmail.setRecList(mailForm.getRecList());
			tmail.setSendDate(new Date());
			tmail.setState("1");
			tmail.setTopic(mailForm.getTopic());
			tmail.setType("1");
			tmail.setUpdateby(CtxUtil.getCurUser());
			tmail.setUpdateTime(new Date());
			tmail.setUserId(CtxUtil.getUserId());
			TDataMail tdataMail=(TDataMail) this.dao.save(tmail);
			TDataRecmail tRecMail=null;
			//收件人
			if(StringUtil.isNotBlank(tdataMail.getRecList())){
				String[] recUser=tdataMail.getRecList().split(",");
				if(recUser.length>=1){
					for(int i=0;i<recUser.length;i++){
						tRecMail=new TDataRecmail();
						tRecMail.setAreaId(userManager.getUser(recUser[i]).getAreaId());
						tRecMail.setCreater(tdataMail.getCreater());
						tRecMail.setCreateTime(tdataMail.getCreateTime());
						tRecMail.setUpdateTime(new Date());
						tRecMail.setPfrId(CtxUtil.getCurUser().getId());
						tRecMail.setUpdateby(CtxUtil.getCurUser());
						tRecMail.setIsActive("Y");
						tRecMail.setIsRead("N");
						tRecMail.setIsDel("N");
						tRecMail.setMailId(tdataMail.getId());
						tRecMail.setUserId(recUser[i]);
						this.dao.save(tRecMail);
					}
					}
			}
			//抄送人
			if(StringUtil.isNotBlank(tdataMail.getChaoSong())){
				String[] chaoSong=tdataMail.getChaoSong().split(",");
				if(chaoSong.length>=1){
				for(int i=0;i<chaoSong.length;i++){
					tRecMail=new TDataRecmail();
					tRecMail.setAreaId(userManager.getUser(chaoSong[i]).getAreaId());
					tRecMail.setUpdateTime(new Date());
					tRecMail.setUpdateby(CtxUtil.getCurUser());
					tRecMail.setCreater(tdataMail.getCreater());
					tRecMail.setCreateTime(tdataMail.getCreateTime());
					tRecMail.setPfrId(CtxUtil.getCurUser().getId());
					tRecMail.setIsActive("Y");
					tRecMail.setIsRead("N");
					tRecMail.setIsDel("N");
					tRecMail.setMailId(tdataMail.getId());
					tRecMail.setUserId(chaoSong[i]);
					this.dao.save(tRecMail);
				}
				}
			}
			return tdataMail;
		}
	}
	@Override
	public List<FileForm> queryFileFormList(String pid) throws Exception {
		List<TDataFile> tList=this.dao.find(" from TDataFile t where t.isActive='Y' and t.pid=? and t.type=? ",pid,FileTypeEnums.HSFJFSHSFJ.getCode());
		List<FileForm> listForm=new ArrayList<FileForm>();
		FileForm ff = null;
		for(TDataFile tf:tList){
			ff=new FileForm();
			ff.setPid(tf.getId());
			ff.setPath(tf.getName());
			listForm.add(ff);
			
		}
		return listForm;
	}
	@Override
	public TDataRecmail saveIsRead(String id) throws Exception {
		TDataRecmail tr=null;
		if(StringUtil.isNotBlank(id)){
			tr=(TDataRecmail) this.get(TDataRecmail.class, id);
			if(tr.getIsRead().equals("N")){
				tr.setIsRead("Y");
				tr.setUpdateTime(new Date());
				tr.setUpdateby(CtxUtil.getCurUser());
				tr.setReadDate(new Date());
				this.dao.save(tr);	
			}
		}
		return tr;
	}
	@Override
	public List<Map<String, String>> queryFileListByUserId(String pid,
			String userId) throws Exception {
		List<TDataFile> tList=this.dao.find(" from TDataFile t where t.isActive='Y' and t.pid=? and t.type=? ",pid,userId);
		List<Map<String, String>> listMap=new ArrayList<Map<String,String>>();
		Map<String, String> map = null;
		for(TDataFile tf:tList){
			map=new HashMap<String, String>();
			map.put("id", tf.getId());
			map.put("name", tf.getName());
			listMap.add(map);
		}
		return listMap;
	}
	@Override
	public TDataFile saveYJFuJian(MultipartFile multipartFile,
			HttpServletRequest request, String pid, String fileType)
			throws AppException {
		TDataFile tDataFile = null;
		try {
			InputStream is = multipartFile.getInputStream();
			pid = request.getParameter("pid");
			fileType = CtxUtil.getUserId();
			String path =UploadFileType.WORK.getPath();
			String fileName = multipartFile.getOriginalFilename();
			Long size = multipartFile.getSize();
			tDataFile = commonManager.saveFile(is, pid, fileType, path, fileName, size);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("上传失败。");
		}
		return tDataFile;
	}
	@Override
	public String ishuiFu(String recListId, String userId,String mailId) throws Exception {
		if(recListId.indexOf(userId)!=-1){
			List<TDataMailyj> list=this.dao.find("from TDataMailyj t where t.isActive='Y' and t.mailId=? and t.setUserId=?", mailId,userId);
			if(list.size()>0){
				return "Y";
			}else{
				return "N";
			}
			
		}
		return "";
	}
}
