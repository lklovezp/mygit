package com.hnjz.app.data.xxgl.lawdocdir;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataDirandtasktype;
import com.hnjz.app.data.po.TDataLawdocdir;
import com.hnjz.common.YnEnum;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
	ִ�������ֵ�managerʵ�ֲ�
 *
 */
@Service("lawDocDirManager")
public class LawdocdirManagerImpl extends ManagerImpl implements LawdocdirManager{

	@Override
	public String queryLawdicdirTree() {
		String hql = "from TDataLawdocdir d where d.isActive = 'Y' order by d.orderby ";
		List<TDataLawdocdir> list = this.dao.find(hql);
		return this.LawdocdirTreeHelp(list, null).toString();
	}
	@Override
	public String queryLawdicdirTreeByTasktype(String tasktypeCode) {
		String hql = "from TDataLawdocdir d where d.isActive = 'Y' and d.id in (select dirid from TDataDirandtasktype where tasktypeid =?) order by d.orderby ";
		List<TDataLawdocdir> list = this.dao.find(hql,tasktypeCode);
		return this.LawdocdirTreeHelp(list, null).toString();
	}
	
	@Override
	public List<ComboboxTree> queryLawdicdirComboTree() {
		String hql = "from TDataLawdocdir d where d.isActive = 'Y' order by d.orderby ";
		List<TDataLawdocdir> list = this.dao.find(hql);
		return this.LawdocdirComboTreeHelp(list, null);
	}
	
	/**
	 * 
	 * �������ܣ�ִ��Ŀ¼�� �������� ���pid����������
	
	 * ���������
	
	 * ����ֵ��
	 */
	private JSONArray LawdocdirTreeHelp(List<TDataLawdocdir> list,String pid){
		JSONArray array = new JSONArray();
		try {
			for(TDataLawdocdir dir : list){
				if ((StringUtils.isBlank(pid) && StringUtils.isBlank(dir.getPid())) || (StringUtils.isNotBlank(pid) && pid.equals(dir.getPid()))){
					JSONObject obj = new JSONObject();
					obj.put("id", dir.getId());
					obj.put("name", dir.getName());
					//obj.put("url", "editLawdocdir.htm?id=" + dir.getId());
					//obj.put("target", "myFrame");
					if (StringUtil.isNotBlank(dir.getPid())){
						obj.put("pId", dir.getPid());
					} else {
						obj.put("pId", "");
					}
					obj.put("children", this.LawdocdirTreeHelp(list, dir.getId()));
					array.put(obj);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * 
	 * �������ܣ�ִ��Ŀ¼�� �������� ���pid����������
	
	 * ���������
	
	 * ����ֵ��
	 */
	private List<ComboboxTree> LawdocdirComboTreeHelp(List<TDataLawdocdir> list, String pid) {
		List<ComboboxTree> treeList = new ArrayList<ComboboxTree>();
		for (TDataLawdocdir dir : list) {
			if ((StringUtils.isBlank(pid) && StringUtils.isBlank(dir.getPid())) || (StringUtils.isNotBlank(pid) && pid.equals(dir.getPid()))) {
				ComboboxTree tree = new ComboboxTree(dir.getId(), dir.getName());
				tree.setChildren(this.LawdocdirComboTreeHelp(list, dir.getId()));
				treeList.add(tree);
			}
		}
		return treeList;
	}

	@Override
	public void saveOrUpdateLawdocdir(LawdocdirForm lawdocdirForm) {
		TSysUser user = CtxUtil.getCurUser();
		TDataLawdocdir dir = null;
		if(StringUtils.isNotBlank(lawdocdirForm.getId())){
			dir = (TDataLawdocdir) this.get(TDataLawdocdir.class, lawdocdirForm.getId());
		}else{
			dir = new TDataLawdocdir();
			dir.setCreateby(user);
			dir.setCreated(new Date(System.currentTimeMillis()));
		}
		dir.setName(lawdocdirForm.getName());
		dir.setOrderby(lawdocdirForm.getOrderby());
		dir.setDescribe(lawdocdirForm.getDescribe());
		dir.setPid(lawdocdirForm.getPid());
		dir.setIsActive(YnEnum.Y.getCode());
		dir.setUpdateby(user);
		dir.setUpdated(new Date(System.currentTimeMillis()));
		this.dao.save(dir);
		
		if(StringUtils.isNotBlank(lawdocdirForm.getTasktypeid())){
			TDataDirandtasktype dirtype = null;
			//��ѯ���õĸ�Ŀ¼id��һֱ����Ŀ¼
			String dirIds = "";
			if(StringUtils.isNotBlank(lawdocdirForm.getId())){
				//�޸�ʱ
				dirIds = queryAllParentDir(dir.getId());
			}else{
				//����ʱ
				dirIds = queryAllParentDir(dir.getPid());
				dirIds += ","+dir.getId();
			}
			for(String id : lawdocdirForm.getTasktypeid().split(",")){
				//��ɾ���Ѵ��ڵģ�������
			//	StringBuffer sql = new StringBuffer("delete from t_data_dirandtasktype where tasktypeid_ = '").append(id).append("' ");
			//	sql.append(" and dirid_ in (");
				StringBuffer sql = new StringBuffer("delete from t_data_dirandtasktype where ");
				sql.append(" dirid_ in (");
				String[] str = dirIds.split(",");
				for(int i=0;i<str.length;i++){
					sql.append(" '").append(str[i]).append("' ");
					if(i!=dirIds.split(",").length-1){
						sql.append(",");
					}
				}
				sql.append(")");
				this.dao.exec(sql.toString());
				for(int i=0;i<str.length;i++){
					dirtype = new TDataDirandtasktype();
					dirtype.setDirid(str[i]);
					dirtype.setTasktypeid(id);
					this.dao.save(dirtype);
				}
			}
		}else{
			TDataDirandtasktype dirtype = null;
			//��ѯ���õĸ�Ŀ¼id��һֱ����Ŀ¼
			String dirIds = "";
			if(StringUtils.isNotBlank(lawdocdirForm.getId())){
				//�޸�ʱ
				dirIds = queryAllParentDir(dir.getId());
			}else{
				//����ʱ
				dirIds = queryAllParentDir(dir.getPid());
				dirIds += ","+dir.getId();
			}
				//��ɾ���Ѵ��ڵģ�������
			//	StringBuffer sql = new StringBuffer("delete from t_data_dirandtasktype where tasktypeid_ = '").append(id).append("' ");
			//	sql.append(" and dirid_ in (");
				StringBuffer sql = new StringBuffer("delete from t_data_dirandtasktype where ");
				sql.append(" dirid_ in (");
				String[] str = dirIds.split(",");
				for(int i=0;i<str.length;i++){
					sql.append(" '").append(str[i]).append("' ");
					if(i!=dirIds.split(",").length-1){
						sql.append(",");
					}
				}
				sql.append(")");
				this.dao.exec(sql.toString());
				
		}
	}
	
	/**
	 * 
	 * �������ܣ���ѯ���еĸ�Ŀ¼id���ö��Ÿ���
	
	 * ���������
	
	 * ����ֵ��
	 */
	private String queryAllParentDir(String dirId){
		List<Object> list = this.dao.findBySql("SELECT LISTAGG(ID_, ',') WITHIN GROUP(ORDER BY ROWNUM) FROM T_DATA_lawdocdir  START WITH ID_ = ? CONNECT BY PRIOR PID_ = ID_", dirId);
		if(list.size()>0){
			return String.valueOf(list.get(0));
		}
		return "";
	}

	@Override
	public LawdocdirForm getLawdocdirInfo(LawdocdirForm lawdocdirForm) {
		TDataLawdocdir lawdocdir = (TDataLawdocdir) this.get(TDataLawdocdir.class, lawdocdirForm.getId());
		lawdocdirForm.setName(lawdocdir.getName());
		lawdocdirForm.setPid(lawdocdir.getPid());
		lawdocdirForm.setDescribe(lawdocdir.getDescribe());
		lawdocdirForm.setOrderby(lawdocdir.getOrderby());
		lawdocdirForm.setIsActive(lawdocdir.getIsActive());
		List<Object> list = this.dao.findBySql("SELECT LISTAGG(TASKTYPEID_,',') WITHIN GROUP (ORDER BY ROWNUM DESC) FROM T_DATA_DIRANDTASKTYPE WHERE DIRID_ = ?", lawdocdir.getId());
		lawdocdirForm.setTasktypeid(list.get(0)==null?"":String.valueOf(list.get(0)));
		return lawdocdirForm;
	}

	@Override
	public JSONArray getLawdocDirListByPid(String pid) {
		JSONArray array = new JSONArray();
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			StringBuffer sql = new StringBuffer("select d.id_,d.name_,d.pid_ from t_data_lawdocdir d where d.isactive_ = 'Y' ");
			if(StringUtils.isNotBlank(pid)){
				sql.append("  and d.pid_ = :pid ");
				condition.put("pid", pid);
			}else{
				sql.append("  and d.pid_ is null ");
			}
			sql.append(" order by orderby_ ");
			List<Object[]> list = this.dao.findBySql(sql.toString(), condition);
			for(Object[] obj : list){
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", obj[0]);
				jsonObj.put("name", obj[1]==null?"":String.valueOf(obj[1]));
				jsonObj.put("pid", obj[2]==null?"":String.valueOf(obj[2]));
				array.put(jsonObj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array;
	}

	@Override
	public void removeLawdocdir(String id) {
		TDataLawdocdir tDataLawdocdir = (TDataLawdocdir) this.get(TDataLawdocdir.class, id);
		tDataLawdocdir.setIsActive("N");
		this.save(tDataLawdocdir);
	}

}