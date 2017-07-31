/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.org.OrgType;
import com.hnjz.sys.po.TBizUserGroup;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserOrg;
import com.hnjz.sys.po.TSysUserRole;

/**
 * 用户管理
 * 
 * @author wumi
 * @version $Id: UserManager.java, v 0.1 Jan 17, 2013 9:32:53 AM wumi Exp $
 */
@Service("userManager")
public class UserManagerImpl extends ManagerImpl implements UserManager{
	/** 日志 */
	private static final Log log = LogFactory.getLog(UserManagerImpl.class);
	/** 用户默认的密码 */
	private static final String DEFAULT_PASS = "888888";
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	/** 用户对应部门的缓存，key为usrid,value为用户对应的部门 */
	private Map<String, TSysOrg> orgs = new ConcurrentHashMap<String, TSysOrg>();

	/** 用户的缓存，key为usrid,value为用户对应的部门 */
	private Map<String, TSysUser> users = new ConcurrentHashMap<String, TSysUser>();
	/***/
	private TSysUser sjUser;
	@Autowired
	private CommonManager commonManager;
	@Autowired
	private LawobjManager lawobjManager;
	/**
	 * 根据用户的登录名查询出符合条件的用户名称
	 * 
	 * @param name
	 *            用户登录名
	 * @return 用户名称，用","隔开
	 */
	public String getUserNames(List<String> names) {
		List<TSysUser> re = this.getUsersByLoginName(names);
		StringBuilder str = new StringBuilder();
		for (TSysUser ele : re) {
			str.append(ele.getName()).append(",");
		}
		if (str.length() > 0) {
			return str.substring(0, str.length() - 1);
		}
		return str.toString();
	}

	/**
	 * 根据用户的登录名查询出符合条件的用户信息(后续改成一次in查询的方式)
	 * 
	 * @param name
	 *            用户登录名
	 * @return 符合条件的用户信息
	 */
	public List<TSysUser> getUsersByLoginName(List<String> names) {
		List<TSysUser> re = new ArrayList<TSysUser>();
		String hsql = "from TSysUser where isActive = 'Y' and username = ?";
		for (String ele : names) {
			List<TSysUser> r = this.dao.find(hsql, ele);
			re.addAll(r);
		}
		return re;
	}

	/**
	 * 根据用户的登录名查询出符合条件的用户信息
	 * 
	 * @param name
	 *            用户登录名
	 * @return 符合条件的用户信息
	 */
	public TSysUser getUserByLoginName(String name) {
		String hsql = "from TSysUser where isActive = 'Y' and username = ?";
		List<TSysUser> r = this.dao.find(hsql, name);
		if (!r.isEmpty()) {
			return r.get(0);
		}
		return null;
	}

	/**
	 * 当任务下派到某个区域时，获取当前区域能接受任务的人员列表（目前是办公室人员）
	 * 
	 * @param areaId
	 *            区域Id
	 * @return 任务下派到某个区域时，获取当前区域能接受任务的人员列表
	 */
	public List<TSysUser> getXpUsers(String areaId) {
		String hsql = "select u from TSysUserOrg ur,TSysOrg r,TSysUser u where u.isActive = 'Y' and r.isActive = 'Y' and ur.org = r.id and ur.user = u.id and r.type = ? and u.areaId = ?";
		// String hsql =
		// "select u from SysUserOrg ur,Org r,User u where  ur.org = r.id and ur.user = u.id and r.bmlx = ? ";
		List<TSysUser> re = this.dao.find(hsql, OrgType.BGS.getCode(), areaId);
		if (log.isDebugEnabled()) {
			for (TSysUser ele : re) {
				log.debug("下派接受人员姓名：" + ele.getName());
			}
		}
		return re;
	}

	/**
	 * 可以处理上报任务的人员集合（目前是办公室人员）
	 * 
	 * @return 可以处理上报任务的人员集合
	 */
	public List<TSysUser> getSbUsers() {
		String hsql = "select u from TSysUserOrg ur,TSysOrg r,TSysUser u where u.isActive = 'Y' and r.isActive = 'Y' and ur.org = r.id and ur.user = u.id and r.type = ?";
		List<TSysUser> re = this.dao.find(hsql, OrgType.BGS.getCode());
		if (log.isDebugEnabled()) {
			for (TSysUser ele : re) {
				log.debug("上报任务接受人员姓名：" + ele.getName());
			}
		}
		return re;
	}

	/**
	 * 根据用户Id获取用户
	 * 
	 * @param id
	 *            用户ID
	 * @return 用户
	 */
	public TSysUser getUser(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		TSysUser user = users.get(id);
		if (null == user) {
			user = (TSysUser) this.get(TSysUser.class, id);
			users.put(id, user);
		}
		return user;
	}

	/**
	 * 查询用户列表
	 * 
	 * @param name
	 *            搜索条件，可以按名称，备注搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public FyWebResult queryUser(String name, String isActive, String page, String pageSize,String orgid,String areaid )
			throws Exception {

		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.id_,a.name_,a.username_,a.workmobile_,a.personmobile_,a.lawnumber_,c.name_ orgname_,a.orderby_ ");
		sql.append(" from t_sys_user a ");
		sql.append(" left join t_sys_userorg b ");
		sql.append(" on a.id_=b.userid_ left join t_sys_org c  on b.orgid_=c.id_ ");
		sql.append(" where a.id_!= 'a0000000000000000000000000000000' ");
		//过滤掉当前用户
		sql.append(" and a.id_ != :id");
		data.put("id", CtxUtil.getUserId());
		
		if (StringUtils.isNotBlank(areaid) /*&& CtxUtil.getCurUser().getIssys().equals("Y")*/) {
			sql.append(" and a.areaid_ = :areaId");
			data.put("areaId", areaid);
		}
			sql.append(" and b.orgid_ = :orgid");
			data.put("orgid", orgid);
		/*if (!CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000") && CtxUtil.getCurUser().getIssys().equals("Y")) {
			sql.append(" and a.areaid_ = :areaId");
			data.put("areaId", areaid);
		}*/
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and (a.name_ like :name  or a.username_ like :name or a.workmobile_ like :name or a.personmobile_ like :name)");
			data.putLike("name", name);
		}
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and a.isactive_ = :isActive ");
			data.put("isActive", isActive);
		} else {
			sql.append(" and a.isactive_ = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		sql.append(" order by c.orderby_ asc , a.orderby_ asc  ");
		FyResult pos = dao.query(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<Object[]> users = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] ele : users) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(ele[0]));
			dataObject.put("name", String.valueOf(ele[1]));
			dataObject.put("userName", String.valueOf(ele[2]));
			dataObject.put("workmobile", String.valueOf(ele[3]));
			dataObject.put("personmobile",ele[4]==null?"":String.valueOf(ele[4]));
			dataObject.put("lawnumber", String.valueOf(ele[5]));
			dataObject.put("org", String.valueOf(ele[6]));
			dataObject.put("orderby", String.valueOf(ele[7]));
			dataObject.put("operate", OperateUtil.getOperate(String.valueOf(ele[0])));
			rows.add(dataObject);
		}
		fy.setRows(rows);
		return fy;
	}

	@Override
	public void editUser(UserForm frm) {
		TSysUser po = (TSysUser) this.get(TSysUser.class, frm
				.getId());
		frm.setId(po.getId());
		frm.setIssys(po.getIssys());
		frm.setName(po.getName());
		frm.setUsername(po.getUsername());
		frm.setPersonmobile(po.getPersonmobile());
		frm.setWorkmobile(po.getWorkmobile());
		frm.setOrderby(po.getOrderby());
		frm.setZfzh(po.getLawnumber());
		frm.setBizType(po.getBizType());
		frm.setIsActive(po.getIsActive());
		frm.setIsRecWork(po.getIsRecWork());
		frm.setIsZaiBian(po.getIsZaiBian());
		frm.setFid(po.getFid());
		//用户多角色的取值
		List<TSysRole> r = this.getRolebyUser(frm.getId());
		String roleId = "";
		for(int i=0; i<r.size();i++){
			if (null != r) {
				if(i<r.size()-1){
					roleId += r.get(i).getId()+",";
	        	}else{
	        		roleId += r.get(i).getId();
	        	}
			}
		}
		frm.setRole(roleId);
		//用户部门的取值
		TSysOrg o = this.getOrgbyUser(frm.getId());
		if (null != o) {
			frm.setGxOrg(o.getId());
			frm.setGxOrgName(o.getName());
		}
		//用户会商接收人分组的取值
		List<TSysDic> g = this.getGroupbyUser(frm.getId());
		String groupId = "";
		if(g != null){
			for(int i=0; i<g.size();i++){
				if (null != g) {
					if(i<g.size()-1){
						groupId += g.get(i).getId()+",";
		        	}else{
		        		groupId += g.get(i).getId();
		        	}
				}
			}
		}
		frm.setGroup(groupId);
		List<TDataFile> file = this.dao.find("from TDataFile where isActive = 'Y' and pid = ?", frm.getId());
		for (int i = 0; i < file.size(); i++) {
			frm.setFilePath(file.get(i).getName());
		}
	}
	
	@Override
	public void previewImage(String id, HttpServletResponse res) {
		TDataFile filePo = (TDataFile) (this.dao.find("from TDataFile where isActive = 'Y' and pid = ?", id)).get(0);
		try {
			String path = File.separator + UploadFileType.WORK.getPath() + File.separator + filePo.getOsname();
			FileUpDownUtil.downloadFile(res, path, filePo.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存一个用户
	 * 
	 * @param frm
	 *            {@link UserForm}
	 */
	public void saveUser(UserForm frm, MultipartFile file) throws AppException {
		try {
			
//			Image image = ImageIO.read(file.getInputStream());
//			int srcwidth = image.getWidth(null);
//			int srcheight = image.getHeight(null);
			
			// 用户登录名不能重复
			StringBuilder hsq = new StringBuilder();
			hsq.append("select count(id) from TSysUser where isActive = 'Y' and username = :username ");
			QueryCondition data = new QueryCondition();
			data.put("username", frm.getUsername());
			if (StringUtils.isNotBlank(frm.getId())) {
				hsq.append("and id <> :id");
				data.put("id", frm.getId());
			}
			long count = (Long) this.dao.find(hsq.toString(), data).get(0);
			if (count > 0) {
				throw new AppException("用户登录名称不能重复！");
			}
	
			TSysUser po = null;
			TDataFile filePo = null;
			TSysUser user = CtxUtil.getCurUser();
			Date cur = new Date();
			if (StringUtils.isNotBlank(frm.getId())) {
				po = (TSysUser) this.get(TSysUser.class, frm.getId());
				// 当为编辑状态并且文件大小不为0 即上传了新的文件 执行删除  下方保存文件同理
				if (file != null && file.getSize() != 0) {
					List<TDataFile> files = this.find("from TDataFile where isActive = 'Y' and pid = '" + frm.getId() + "'" );
					if (files.size() > 0){
						filePo = files.get(0);
						// 删除文件
						FileUpDownUtil.delFile(FileUpDownUtil.path + UploadFileType.WORK.getPath() + File.separator + filePo.getOsname());
					} else {
						filePo = new TDataFile();
					}
				}
			} else {
				po = new TSysUser();
				
				po.setCreateby(user);
				po.setCreated(cur);
				
				filePo = new TDataFile();
				
			}
			if (StringUtil.isNotBlank(frm.getPassword())) {
				String password = md5PasswordEncoder.encodePassword(frm.getPassword(), null);
				po.setPassword(password);
			}
			po.setName(frm.getName());
			po.setLawnumber(frm.getZfzh());
			po.setUsername(frm.getUsername());
			po.setWorkmobile(frm.getWorkmobile());
			po.setPersonmobile(frm.getPersonmobile());
			po.setFid(frm.getFid());
			if (StringUtils.isNotBlank(frm.getIsRecWork())) {
				po.setIsRecWork(YnEnum.Y.getCode());
			} else {
				po.setIsRecWork(YnEnum.N.getCode());
			}
			
			if (StringUtils.isNotBlank(frm.getIsZaiBian())) {
				po.setIsZaiBian(YnEnum.Y.getCode());
			} else {
				po.setIsZaiBian(YnEnum.N.getCode());
			}
			
			
			if (StringUtils.isNotBlank(frm.getIssys())) {
				po.setIssys(YnEnum.Y.getCode());
			} else {
				po.setIssys(YnEnum.N.getCode());
			}
			if (StringUtils.isNotBlank(frm.getIsActive())) {
				po.setIsActive(YnEnum.Y.getCode());
			} else {
				po.setIsActive(YnEnum.N.getCode());
			}
			
			po.setUpdateby(user);
			po.setUpdated(cur);
			po.setBizType(frm.getBizType());
			po.setOrderby(frm.getOrderby());
			po.setAreaId(((TSysOrg)this.dao.get(TSysOrg.class, frm.getGxOrg())).getArea().getId());
			po = (TSysUser) this.dao.save(po);
			// 如果当前用户是要修改的用户，将系统初始化的CtxUtil中的user修改。
			if (CtxUtil.getCurUser().getId().equals(frm.getId())){
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(po, po.getPassword(), po.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			if (StringUtils.isNotBlank(po.getId())) {
				users.remove(po.getId());
			}
	
			// 删除用户的角色
			String hsql = "from TSysUserRole where user.id = ?";
			if (StringUtils.isNotBlank(po.getId())){
				this.dao.removeFindObjs(hsql, po.getId());
			}
			// 保存角色
			if (StringUtils.isNotBlank(frm.getRole())) {
				String[] roles = frm.getRole().split(",");
				//判断选择的用户角色是几个就进行几次用户角色表的保存
				for(int i=0;i<roles.length;i++){
					TSysUserRole r = new TSysUserRole();
					TSysRole tSysRole = (TSysRole) this.dao.get(TSysRole.class, roles[i]);
					r.setRole(tSysRole);
					TSysUser user1 = (TSysUser) this.dao.get(TSysUser.class, po.getId());
					r.setUser(user1);
					this.dao.save(r);
				}
			}
			
			// 删除用户所属的分组
			hsql = "from TBizUserGroup where user.id = ?";
			if(StringUtils.isNotBlank(po.getId())){
				this.dao.removeFindObjs(hsql, po.getId());	
			}
			
			// 保存会商接收人分组
			if (StringUtils.isNotBlank(frm.getGroup())) {
				String[] groupes = frm.getGroup().split(",");
				//判断选择的分组是几个就进行几次用户分组表的保存
				for(int i=0;i<groupes.length;i++){
					TBizUserGroup r = new TBizUserGroup();
					TSysDic tgroup = (TSysDic) this.dao.get(TSysDic.class, groupes[i]);
					r.setGroup(tgroup);
					TSysUser user1 = (TSysUser) this.dao.get(TSysUser.class, po.getId());
					r.setUser(user1);
					this.dao.save(r);
				}
			}
			
			// 删除用户所属的部门
			hsql = "from TSysUserOrg where user.id = ?";
			if(StringUtils.isNotBlank(po.getId())){
				this.dao.removeFindObjs(hsql, po.getId());	
			}
			
			// 设置所属部门
			if (StringUtils.isNotBlank(frm.getGxOrg())) {
				TSysOrg org = (TSysOrg) this.dao.get(TSysOrg.class, frm.getGxOrg());
				po.setAreaId(org.getArea().getId());
				TSysUserOrg s = new TSysUserOrg();
				TSysOrg tso = (TSysOrg) this.dao.get(TSysOrg.class, frm.getGxOrg());
				s.setOrg(tso);
				TSysUser user1 = (TSysUser) this.dao.get(TSysUser.class, po.getId());
				s.setUser(user1);
				this.dao.save(s);
//				if (StringUtils.isNotBlank(po.getId())) {
//					orgs.remove(po.getId());
//				}
			}
			
			if (file != null && file.getSize() != 0){
				// 保存文件
				commonManager.saveFile(filePo, file, po.getId(), FileTypeEnums.YHXXTX.getCode(), UploadFileType.WORK);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存密码
	 * 
	 * @param frm
	 *            {@link UserForm}
	 */
	public void resetPas(String id) throws AppException {
		TSysUser po = (TSysUser) this.get(TSysUser.class, id);
		// 判断当前密码是否正确
		String password = md5PasswordEncoder.encodePassword(DEFAULT_PASS, null);
		po.setPassword(password);
		this.dao.save(po);

	}

	/**
	 * 保存密码
	 * 
	 * @param frm
	 *            {@link UserForm}
	 */
	public String savePas(UserForm frm) throws AppException {
		if (!frm.getPassword().equals(frm.getConfirmPassword())) {
			throw new AppException("两次新密码不一致，请重新输入!");
		}
		TSysUser po = null;
		TSysUser user = CtxUtil.getCurUser();
		po = (TSysUser) this.get(TSysUser.class, user.getId());
		// 判断当前密码是否正确
		String old = md5PasswordEncoder.encodePassword(frm.getOldPas(), null);
		if (user.getPassword().equals(old)) {
			// 判断密码是否符合规则
			// 字母开头长度为6-16 英文数字下划线
			if (Pattern.compile("^[a-zA-Z]\\w{5,15}$").matcher(frm.getPassword()).matches()){
				String password = md5PasswordEncoder.encodePassword(frm
						.getPassword(), null);
				po.setPassword(password);
				this.dao.save(po);
//				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(po, po.getPassword(), po.getAuthorities());
//				SecurityContextHolder.getContext().setAuthentication(authentication);
//				try {
//					request.getRequestDispatcher("http://localhost:8088//app").forward(request, response);
//				} catch (ServletException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				return "修改密码成功!";
			} else {
				throw new AppException("密码不符合规则!");
			}
		} else {
			throw new AppException("当前密码不正确!");
		}
	}

	/**
	 * 根据用户id获取角色对象
	 * 
	 * @param userId
	 *            用户Id
	 * @return 角色对象
	 */
	public List<TSysDic> getGroupbyUser(String userId) {
		String hsql = "select r from TBizUserGroup ur,TSysDic r where ur.group.id = r.id and ur.user.id = ?";
		List<TSysDic> groups = this.dao.find(hsql, userId);
		log.debug("查询到分组信息：" + groups.size());
		if (groups.isEmpty()) {
			return null;
		}
		return groups;
	}

	/**
	 * 根据用户id获取会商接收人分组对象
	 * 
	 * @param userId
	 *            用户Id
	 * @return 角色对象
	 */
	public List<TSysRole> getRolebyUser(String userId) {
		String hsql = "select r from TSysUserRole ur,TSysRole r where ur.role.id = r.id and ur.user.id = ?";
		List<TSysRole> roles = this.dao.find(hsql, userId);
		log.debug("查询到角色信息：" + roles.size());
		if (roles.isEmpty()) {
			return null;
		}
		return roles;
	}
	
	/**
	 * 根据用户id获取部门
	 * 
	 * @param userId
	 *            用户Id
	 * @return 部门对象
	 */
	public TSysOrg getOrgbyUser(String userId) {
		TSysOrg org = null;
//		TSysOrg org = orgs.get(userId);
//		if (null != org) {
//			return org;
//		}
//		if (log.isDebugEnabled()) {
//			log.debug("缓存中没有部门数据，从数据库中加载");
//		}
		String hsql = "select r from TSysUserOrg ur,TSysOrg r where r.isActive = 'Y' and ur.org.id = r.id and ur.user.id = ?";
		List<TSysOrg> re = this.dao.find(hsql, userId);
		if (re.isEmpty()) {
			return null;
		} else {
			org = re.get(0);
		}
//		orgs.put(userId, org);
		return org;
	}

	/**
	 * 删除用户，用户和角色关系
	 * 
	 * @param id
	 *            用户ID
	 * @throws IOException 
	 */
	public void removeUser(String id) throws IOException {
//		// 删除用户和角色的关系
//		String hsql = "from TSysUserRole where user.id = ?";
//		this.dao.removeFindObjs(hsql, id);
//		// 删除用户和部门的关系
//		hsql = "from TSysUserOrg where user.id = ?";
//		this.dao.removeFindObjs(hsql, id);
//		this.dao.remove(TSysUser.class, id);
//		orgs.remove(id);
//		users.remove(id);
//		// 删除照片
//		List<TDataFile> filePo = this.find("from TDataFile where pid = '" + id + "'" );
//		for (int i = 0; i < filePo.size(); i++) {
//			// 删除文件
//			FileUpDownUtil.delFile(FileUpDownUtil.path + UploadFileType.CLIENT.getPath() + File.separator + filePo.get(i).getOsname());
//			this.dao.remove(TDataFile.class, filePo.get(i).getId());
//		}
		
		TSysUser po = (TSysUser) this.dao.get(TSysUser.class, id);
		po.setIsActive(YnEnum.N.getCode());
		this.dao.save(po);
	}

	public void setMd5PasswordEncoder(Md5PasswordEncoder md5PasswordEncoder) {
		this.md5PasswordEncoder = md5PasswordEncoder;
	}

	public TSysUser getSj() {
		return sjUser;
	}
	
	public TSysUser getLeaderByAreaId(String areaId){
		String hsql = "from TSysUser where id in (select leader1 from TSysOrg where type = ? and area.id = ? and isActive = 'Y')";
		List<TSysUser> re = this.dao.find(hsql, OrgType.ZD.getCode(),areaId);
		if (!re.isEmpty()) {
			return re.get(0);
		}
		return null;
	}

	public void afterPropertiesSet() throws Exception {
		saveSys();
	}

	/**
	 * 插入系统用户”上级“
	 */
	public void saveSys() {
		/*String hsql = "from TSysUser where name = ?";
        List<TSysUser> pos = this.dao.find(hsql, Constants.DEFAULTCREATER);
        if (pos.isEmpty()) {
            Date date = new Date();
            TSysUser po = new TSysUser();
            String password = md5PasswordEncoder.encodePassword(DEFAULT_PASS, null);
            po.setPassword(password);
            po.setName(Constants.DEFAULTCREATER);
            po.setUsername("syssj");
            po.setCreated(date);
            po.setUpdated(date);
           // po.setCreater(creater);
            po.setIssys(YnEnum.Y.getCode());
            po.setIsActive(YnEnum.Y.getCode());
            this.dao.save(po);
            if (log.isDebugEnabled()) {
                log.debug("插入系统用户：上级");
            }
        } else {
            sjUser = pos.get(0);
            if (log.isDebugEnabled()) {
                log.debug("不需要插入系统用户：上级");
            }
        }*/
	}
	/**
	 * 查询用户数量
	 */
	public int queryUserNumber(){
		String areaId = CtxUtil.getAreaId();
		List<Object[]> users = this.dao.findBySql("select distinct u.id_ from T_Sys_User u,T_SYS_USERORG t,T_SYS_ORG o where t.userid_ = u.id_ and t.orgid_ = o.id_ and o.BIZTYPE_='0' and u.isActive_ = 'Y' and u.issys_='N' and u.areaId_ = ? ",areaId);
		if(users!=null && users.size()>0){
			return users.size();
		}else{
			return 0;
		}
	}

	@Override
	public List<TSysUser> queryUsersByAreaid(TDataLawobjf lawobj) throws Exception{
		String regionId=lawobj.getSsxzq();
		String code = regionId.substring(0, 4)+"99";
		List<TSysArea> areas=this.dao.find("from TSysArea t where t.isActive='Y' and t.code=?",code);
		
		List<TSysUser> users=new ArrayList<TSysUser>();
		String areaid="";
		if(areas.size()>0){
			areaid=areas.get(0).getId();
		}
		
		if(areas.size()>1){
			areaid="a0000000000000000000000000000000";
		}
		 users=this.dao.find("from TSysUser t where t.areaId=? and t.isRecWork=? and t.isActive='Y'",areaid,"Y");
		
		return users;
	}

	@Override
	public TSysUser randomUser(List<TSysUser> users) throws Exception {
		 //在users里面获取随机一个用户
    	TSysUser jsuser=new TSysUser();
    	Random r=new Random();
		   int k = r.nextInt(users.size());
    		if(users.size()>0){
    			jsuser=users.get(k);
    		}
		return jsuser;
	}

	@Override
	public List<Map<String, String>> queryAllUser() {
		// TODO Auto-generated method stub
		List<Map<String,String>>  rows= new ArrayList<Map<String, String>>();
		StringBuilder sql=new StringBuilder();
		sql.append("select u.name_,o.name_,a.name_,u.id_ from t_sys_user u,t_sys_userorg uo,t_sys_org o,t_sys_area a where u.id_=uo.userid_ and uo.orgid_=o.id_ and o.areaid_=a.id_");
		List<Object[]> list = dao.findBySql(sql.toString());
		Map<String,String> dataObject=null;
		for(Object[] ele : list){
			dataObject=new HashMap<String,String>();
			dataObject.put("uname", String.valueOf(ele[0]));
			dataObject.put("oname", String.valueOf(ele[1]));
			dataObject.put("aname", String.valueOf(ele[2]));
			dataObject.put("uid", String.valueOf(ele[3]));
			rows.add(dataObject);
		}
		return rows;
	}
	
	
	
	@Override
	public List<ComboboxTree> queryUserComboTree(String areaid) {
		// TODO Auto-generated method stub
		String hsql=" from TSysUser d where d.areaId= ?";
		List<TSysUser> list=this.dao.find(hsql,areaid);
		
		
		
		return  this.UserComboTreeHelp(list,null);
	}
  
	/**
	 * 
	 * 函数介绍：执法对象类型树 执法目录树 帮助方法 获得pid的所有子项
	
	 * 输入参数：
	
	 * 返回值：
	 */

	private List<ComboboxTree> UserComboTreeHelp(List<TSysUser> list, String pid) {
		List<ComboboxTree> treeList = new ArrayList<ComboboxTree>();
		for(TSysUser ele : list){
			//System.out.println(ele.getFid());
			 if(StringUtil.isNotBlank(pid) && pid.equals(ele.getFid())){
					ComboboxTree comboboxTree = new ComboboxTree(ele.getId(),ele.getName(),pid,null);
					comboboxTree.setChildren(this.UserComboTreeHelp(list, ele.getId()));
					treeList.add(comboboxTree);
				 }else if(!StringUtil.isNotBlank(pid)&&ele.getFid()==null){//添加根节点
					ComboboxTree comboboxTree = new ComboboxTree(ele.getId(),ele.getName());
					comboboxTree.setChildren(this.UserComboTreeHelp(list, ele.getId()));
					treeList.add(comboboxTree);
				 }
			
}
		System.out.println(JSONObject.toJSONString(treeList));
		return treeList;
	}
	

}
