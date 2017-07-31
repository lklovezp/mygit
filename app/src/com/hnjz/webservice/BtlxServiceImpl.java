package com.hnjz.webservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.xfire.util.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.util.FtpUtil;

/**
 * 
 * 作者：zhangqingfeng
 * 生成日期：2016-4-1
 * 功能描述：离线同步数据接口实现类
 *
 */
@Service("btlxServiceImpl")
public class BtlxServiceImpl implements BtlxService {

	@Autowired
	private Dao dao;

	/**
	 * 
	 * 函数介绍：判断是否同步公共方法
	 * 输入参数：
	 * 返回值：
	 */
	private String isSynchHelp(String strAreaId, String strUpdated, String tablename) {
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer sql = new StringBuffer("select id_ from ").append(tablename).append(" d where 1=1 ");
			if (StringUtils.isNotBlank(strUpdated)) {
				sql.append(" and d.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
				map.put("updated", strUpdated);
			}
			if (StringUtils.isNotBlank(strAreaId)) {
				List<Object> listId = this.dao.findBySql("select id_ from t_sys_area a where a.isactive_ = 'Y' and a.code_ = ? ", strAreaId);
				if(listId.size()>0){
					sql.append(" and d.areaid_ = :areaid ");
					map.put("areaid", String.valueOf(listId.get(0)));
				}else{
					sql.append(" and 1=2 ");
				}
			}
			//sql.append(" and d.isactive_ = 'Y' ");//去掉了isactive为Y的，体现删除的数据
			List<String> list = dao.findBySql(sql.toString(), map);
			if (list.size() > 0) {
				object.put("result", "Y");
			} else {
				object.put("result", "N");
			}
			array.put(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array.toString();
	}

	/**
	 * 
	 * 函数介绍：获取同步数据公共方法
	 * 输入参数：
	 * 返回值：
	 */
	private String getTableDataHelp(String strAreaId, String strUpdated, String tablename) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sql = null;
		if("T_DATA_FILE".equals(tablename)){
			sql = new StringBuffer("select * from ").append(tablename).append(" d where 1=1 and d.type_ = '2400'");
		}else if("T_SYS_FUNC".equals(tablename)){
			sql = new StringBuffer("select * from ").append(tablename).append(" d where 1=1 and d.pid_ in('a0000000000000000000000000000000' , '40288ab24bc88934014bc89218c700ad', '40288ace4b80bdc0014b80c3daf80011', '40288ac4559065de01559068f7ed0003', '40288ac4559065de015590700d780009')");
		}else if("T_SYS_FUNCOPER".equals(tablename)){
			sql = new StringBuffer("select * from ").append(tablename).append(" d where 1=1 and d.funcid_ in(select id_ from t_sys_func where pid_ in('a0000000000000000000000000000000' , '40288ab24bc88934014bc89218c700ad', '40288ace4b80bdc0014b80c3daf80011', '40288ac4559065de01559068f7ed0003', '40288ac4559065de015590700d780009') and id_ != '40288abd53bfb5c20153bfe35ff90050' and id_ != '40288ac4559065de015590742d00000c')");
		}else if("T_SYS_ROLEFUNC".equals(tablename) || "T_SYS_ROLEFUNCOPER".equals(tablename)){
			sql = new StringBuffer("select * from ").append(tablename).append(" d where 1=1 and d.ROLEID_ != 'a0000000000000000000000000000000'");
		}else{
			sql = new StringBuffer("select * from ").append(tablename).append(" d where 1=1 ");
		}
		/*if (StringUtils.isNotBlank(strUpdated) && (!"2".equals(strUpdated))) {
			sql.append(" and d.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdated);
		}
		if (StringUtils.isNotBlank(strAreaId) && (!"1".equals(strAreaId))) {
			List<Object> listId = this.dao.findBySql("select id_ from t_sys_area a where a.isactive_ = 'Y' and a.code_ = ? ", strAreaId);
			if(listId.size()>0){
				sql.append(" and d.areaid_ = :areaid ");
				map.put("areaid", String.valueOf(listId.get(0)));
			}else{
				sql.append(" and 1=2 ");
			}
		}*/
		List<Map<String, Object>> listMap = dao.queryListMapBySqlForHbdj(sql.toString(), tablename, map);
		return JsonResultUtil.listMapToJSONArray(listMap).toString();
	}

	@Override
	public String dicDataIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_SYS_DIC");
	}

	@Override
	public String getDicDataList(String strAreaId, String strUpdated) {
		return this.getTableDataHelp(null, strUpdated, "T_SYS_DIC");
	}

	@Override
	public String areaInfoIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_SYS_AREA");
	}

	@Override
	public String getAreaInfoList(String strAreaId, String strUpdated) {
		return this.getTableDataHelp(null, strUpdated, "T_SYS_AREA");
	}

	@Override
	public String xzqInfoIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_REGION");
	}

	@Override
	public String getXzqInfoList(String strAreaId, String strUpdated) {
		return this.getTableDataHelp(null, strUpdated, "T_DATA_REGION");
	}

	@Override
	public String userInfoIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp("", strUpdated, "T_SYS_USER");
	}

	@Override
	public String getUserInfoList(String strAreaId, String strUpdated) {
		JSONArray array = new JSONArray();
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("T_SYS_USER", new JSONArray(getTableDataHelp(null, strUpdated, "T_SYS_USER")));
			jsonobj.put("T_SYS_USERORG", new JSONArray(getTableDataHelp(null, strUpdated, "T_SYS_USERORG")));
			jsonobj.put("T_SYS_USERROLE", new JSONArray(getTableDataHelp(null, strUpdated, "T_SYS_USERROLE")));
			array.put(jsonobj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array.toString();
	}

	@Override
	public String orgInfoIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_SYS_ORG");
	}

	@Override
	public String getOrgInfoList(String strAreaId, String strUpdated) {
		return getTableDataHelp(strAreaId, strUpdated, "T_SYS_ORG");
	}

	@Override
	public String rwlxIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_TASKTYPE");
	}

	@Override
	public String getRwlxList(String strAreaId, String strUpdated) {
		JSONArray array = new JSONArray();
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("T_DATA_TASKTYPE", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_TASKTYPE")));
			jsonobj.put("T_DATA_TASKTYPEANDILLEGALTYPE", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_TASKTYPEANDILLEGALTYPE")));
			jsonobj.put("T_DATA_TASKTYPEATTACHMENTTYPE", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_TASKTYPEATTACHMENTTYPE")));
			jsonobj.put("T_DATA_TASKTYPEPRIMARYFILE", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_TASKTYPEPRIMARYFILE")));
			array.put(jsonobj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array.toString();
	}

	@Override
	public String hylxIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_INDUSTRY");
	}

	@Override
	public String getHylxList(String strAreaId, String strUpdated) {
		return getTableDataHelp(null, strUpdated, "T_DATA_INDUSTRY");
	}

	@Override
	public String wflxIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_ILLEGALTYPE");
	}

	@Override
	public String getWflxList(String strAreaId, String strUpdated) {
		return getTableDataHelp(null, strUpdated, "T_DATA_ILLEGALTYPE");
	}
	
	@Override
	public String hpInfoIsSynch(String strAreaId, String strUpdated) {
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer sql = new StringBuffer("select d.id_ from T_DATA_LAWOBJEIA d left join t_data_lawobj o on d.pid_ = o.id_ where 1=1 ");
			if (StringUtils.isNotBlank(strUpdated)) {
				sql.append(" and d.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
				map.put("updated", strUpdated);
			}
			if (StringUtils.isNotBlank(strAreaId)) {
				sql.append(" and o.areaid_ = :areaid ");
				map.put("areaid", strAreaId);
			}
			List<Map<String, Object>> list = dao.findBySql(sql.toString(), map);
			if (list.size() > 0) {
				object.put("result", "Y");
			} else {
				object.put("result", "N");
			}
			array.put(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array.toString();
	}
	
	@Override
	public String getHpInfoList(String strAreaId, String strUpdated) {
		return getTableDataHelp(null, strUpdated, "T_DATA_LAWOBJEIA");
	}
	
	@Override
	public String fjxxInfoIsSynch(String strAreaId, String strUpdated) {
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer sql = new StringBuffer("select id_ from T_DATA_FILE d where 1=1 ");
			List<String> fileType = this.getFileTypeForDj();
			String innerType = "";
			for(int i=0;i<fileType.size();i++){
				innerType += "'"+fileType.get(i)+"'";
				if(i!=fileType.size()-1){
					innerType += ",";
				}
			}
			sql.append(" and d.type_ in (").append(innerType).append(") ");
			if (StringUtils.isNotBlank(strUpdated)) {
				sql.append(" and d.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
				map.put("updated", strUpdated);
			}
			List<String> list = dao.findBySql(sql.toString(), map);
			if (list.size() > 0) {
				object.put("result", "Y");
			} else {
				object.put("result", "N");
			}
			array.put(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array.toString();
	}
	
	/**
	 * 
	 * 函数介绍：获得单机可同步的附件类型
	 * 输入参数：
	 * 返回值：
	 */
	private List<String> getFileTypeForDj(){
		List<String> fileType = new ArrayList<String>();
		fileType.add(FileTypeEnums.ZFWJGLZFWJ.getCode());
		
		fileType.add(FileTypeEnums.GYWRYZZJGDMSMJ.getCode());
		fileType.add(FileTypeEnums.GYWRYXCKCSYT.getCode());
		fileType.add(FileTypeEnums.GYWRYYYZZSMJ.getCode());
		fileType.add(FileTypeEnums.GYWRYPWXKZSMJ.getCode());
		fileType.add(FileTypeEnums.GYWRYQT.getCode());
		
//		YY("32", "医院"),
		fileType.add(FileTypeEnums.YYZZJGDMSMJ.getCode());
		fileType.add(FileTypeEnums.YYXCKCSYT.getCode());
		fileType.add(FileTypeEnums.YYFSAQXKZSMJ.getCode());
		fileType.add(FileTypeEnums.YYPWXKZSMJ.getCode());
		fileType.add(FileTypeEnums.YYQT.getCode());
		
//		GL("33", "锅炉"),
		fileType.add(FileTypeEnums.GLZZJGDMSMJ.getCode());
		fileType.add(FileTypeEnums.GLXCKCSYT.getCode());
		fileType.add(FileTypeEnums.GLYYZZSMJ.getCode());
		fileType.add(FileTypeEnums.GLPWXKZSMJ.getCode());
		fileType.add(FileTypeEnums.GLQT.getCode());
		
//		JZGD("34", "建筑工地"),
		fileType.add(FileTypeEnums.JZGDZZJGDMSMJ.getCode());
		fileType.add(FileTypeEnums.JZGDXCKCSYT.getCode());
		fileType.add(FileTypeEnums.JZGDQT.getCode());
		
//		XQYZ("35", "畜禽养殖"),
		fileType.add(FileTypeEnums.XQYZZZJGDMSMJ.getCode());
		fileType.add(FileTypeEnums.XQYZXCKCSYT.getCode());
		fileType.add(FileTypeEnums.XQYYZZSMJ.getCode());
		fileType.add(FileTypeEnums.XQPWXKZSMJ.getCode());
		fileType.add(FileTypeEnums.XQYZQT.getCode());
		
//		SC("36", "三产"),
		fileType.add(FileTypeEnums.SCXCKCSYT.getCode());
		fileType.add(FileTypeEnums.SCYYZZSMJ.getCode());
		fileType.add(FileTypeEnums.SCPWXKZSMJ.getCode());
		fileType.add(FileTypeEnums.SCWSXKZSMJ.getCode());
		fileType.add(FileTypeEnums.SCQT.getCode());
		
//		JSXM("37", "建设项目"),
		fileType.add(FileTypeEnums.JSXMXCKCSYT.getCode());
		fileType.add(FileTypeEnums.JSXMQT.getCode());
		
//		HPXX("38", "环评信息"),
		fileType.add(FileTypeEnums.HPXXHPSPWH.getCode());
		fileType.add(FileTypeEnums.HPXXSSCSPWH.getCode());
		fileType.add(FileTypeEnums.HPXXYQSSCSPWH.getCode());
		fileType.add(FileTypeEnums.HPXXSTSYSSPWH.getCode());
		fileType.add(FileTypeEnums.HPXXQJSCSPWH.getCode());
//		JCMB("6000", "监察模板"),
		fileType.add(FileTypeEnums.JCMB.getCode());
		return fileType;
	}
	
	@Override
	public String getFjxxInfoList(String strAreaId, String strUpdated) {
		JSONArray array = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer sql = new StringBuffer("select * from T_DATA_FILE d where 1=1 ");
			List<String> fileType = this.getFileTypeForDj();
			String innerType = "";
			for(int i=0;i<fileType.size();i++){
				innerType += "'"+fileType.get(i)+"'";
				if(i!=fileType.size()-1){
					innerType += ",";
				}
			}
			sql.append(" and d.type_ in (").append(innerType).append(") ");
			if (StringUtils.isNotBlank(strUpdated)) {
				sql.append(" and d.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
				map.put("updated", strUpdated);
			}
			List<Map<String, Object>> listMap = dao.queryListMapBySqlForHbdj(sql.toString(), "T_DATA_FILE", map);
			array = JsonResultUtil.listMapToJSONArray(listMap);
			array = new JSONArray(getTableDataHelp("", strUpdated, "T_DATA_FILE"));
			for(int i=0;i<array.length();i++){
				JSONObject obj = array.getJSONObject(i);
				obj.put("strRemoteUser", FtpUtil.ftpuser);
				obj.put("strRemotePass", FtpUtil.ftppass);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array.toString();
	}
	
	@Override
    public DataHandler downFile(String fileid) {
		TDataFile tdataFile = (TDataFile) this.dao.get(TDataFile.class, fileid);
		if(tdataFile == null){
			return null;
		}
    	DataHandler dh = null;
    	InputStream is = null;
    	FileOutputStream fos = null;
    	try {
    		String path = FileUpDownUtil.path + File.separator + tdataFile.getRelativepath() + File.separator + tdataFile.getOsname();
    		is = new FileInputStream(new File(path));
			File file = File.createTempFile("file", ".tmp");
			fos = new FileOutputStream(file);
			byte[] b = new byte[512];
			int len;
			while ((len = is.read(b)) > 0) {
				fos.write(b, 0, len);
			}
			dh = new DataHandler(new FileDataSource(file));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dh;
	}
	
	@Override
	public String zfFileDirIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_LAWDOCDIR");
	}
	
	@Override
	public String zfFileIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_LAWDOC");
	}
	
	@Override
	public String getZfFileList(String strAreaId, String strUpdated) {
		JSONArray array = new JSONArray();
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("T_DATA_LAWDOCDIR", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_LAWDOCDIR")));
			jsonobj.put("T_DATA_LAWDOC", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_LAWDOC")));
			jsonobj.put("T_DATA_FILE", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_FILE")));
			array.put(jsonobj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array.toString();
	}
	
	@Override
	public String dataRecordIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_RECORD");
	}
	
	@Override
	public String getDataRecordList(String strAreaId, String strUpdated) {
		JSONArray array = new JSONArray();
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("T_DATA_RECORD", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_RECORD")));
			jsonobj.put("T_DATA_RECORDLAWOBJTYPE", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_RECORDLAWOBJTYPE")));
			array.put(jsonobj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array.toString();
	}
	
	@Override
	public String jcjlmbIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_CHECKLISTTEMPLATE");
	}
	
	@Override
	public String getJcjlmbList(String strAreaId, String strUpdated) {
		JSONArray array = new JSONArray();
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("T_DATA_CHECKLISTITEM", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_CHECKLISTITEM")));
			jsonobj.put("T_DATA_CHECKLISTITEMANS", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_CHECKLISTITEMANS")));
			jsonobj.put("T_DATA_CHECKLISTTEMPLATE", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_CHECKLISTTEMPLATE")));
			array.put(jsonobj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array.toString();
	}
	
	@Override
	public String jcxIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_CHECKLISTITEM");
	}
	
	@Override
	public String getJcxList(String strAreaId, String strUpdated) {
		return getTableDataHelp(null, strUpdated, "T_DATA_CHECKLISTITEM");
	}
	
	@Override
	public String jcxdaIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_CHECKLISTITEMANS");
	}
	
	@Override
	public String getJcxDaList(String strAreaId, String strUpdated) {
		return getTableDataHelp(null, strUpdated, "T_DATA_CHECKLISTITEMANS");
	}
	
	

	@Override
	public String zyclInfoIsSynch(String strAreaId, String strUpdated) {
		try {
			JSONArray array = new JSONArray(this.isSynchHelp(null, strUpdated, "T_DATA_DISCREACTS"));
			if(array.getJSONObject(0).getString("result").equals("N")){
				array = new JSONArray(this.isSynchHelp(null, strUpdated, "T_DATA_DISCREMERIT"));
				if(array.getJSONObject(0).getString("result").equals("N")){
					array = new JSONArray(this.isSynchHelp(null, strUpdated, "T_DATA_DISCRECASECLASS"));
				}
			}
			return array.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getZyclInfoList(String strAreaId, String strUpdated) {
		JSONArray array = new JSONArray();
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("T_DATA_DISCREACTS", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_DISCREACTS")));
			jsonobj.put("T_DATA_DISCREMERIT", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_DISCREMERIT")));
			jsonobj.put("T_DATA_DISCRECASECLASS", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_DISCRECASECLASS")));
			array.put(jsonobj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array.toString();
	}

	@Override
	public String getJcxxTBZT(String strRequestJson) {
		JSONArray resultJson = new JSONArray();
		try {
			JSONArray array = new JSONArray(strRequestJson);
			for(int i=0;i<array.length();i++){
				JSONObject obj = array.getJSONObject(i);
				String id = obj.getString("ID");
				String areaId = obj.getString("AREAID");
				String updated = obj.getString("UPDATED");
				String methodName = BtdjSychEnum.getMethodNameById(id);
				if(StringUtils.isNotBlank(methodName)){
					String result = (String) this.getClass().getMethod(methodName, String.class,String.class).invoke(this, areaId, updated);
					JSONArray jsonArray = new JSONArray(result);
					if(jsonArray!=null && jsonArray.length()>0){
						JSONObject isSychObj = new JSONObject();
						isSychObj.put("ID", id);
						isSychObj.put("XXZT", jsonArray.getJSONObject(0).getString("result"));
						resultJson.put(isSychObj);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return resultJson.toString();
	}
	
	
	@Override
	public String getMaxVersionCode() {
		JSONArray array = new JSONArray();
		JSONObject jsonObject = null;
		try {
			String sql = "SELECT * FROM(select t.code_,t.desc_,t.isforce_,t.updated_ from T_DATA_VERSION  t where t.isactive_ = 'Y' and t.type_ in ('1','2') order by updated_ desc) WHERE ROWNUM <=1";
			List<Object[]> list = this.dao.findBySql(sql);
			if (list != null && list.size() > 0) {
				Object[] obj = list.get(0);
				jsonObject = new JSONObject();
				jsonObject.put("bbh", String.valueOf(obj[0]));
				jsonObject.put("desc", String.valueOf(obj[1]));
				jsonObject.put("isForce", String.valueOf(obj[2]));
				jsonObject.put("bbhsj", String.valueOf(obj[3]));
				array.put(jsonObject);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array.toString();
	}

	@Override
	public String getUpdateFile() {
		JSONArray array = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		try {
			String sql = "SELECT * FROM(select f.name_,f.relativepath_,t.type_,f.osname_ from T_DATA_VERSION  t left join t_data_file f on t.id_ = f.pid_ where t.isactive_ = 'Y' and t.type_ in ('1','2') ORDER BY t.updated_ DESC) WHERE ROWNUM<=1";
			List<Object[]> list = this.dao.findBySql(sql);
			if (list != null && list.size() > 0) {
				Object[] obj = list.get(0);
				jsonObject.put("isSucc",  "Y");
				jsonObject.put("errDesc",  "");
				jsonObject.put("filename", String.valueOf(obj[0]));
				jsonObject.put("osname", String.valueOf(obj[3]));
				jsonObject.put("filepath", String.valueOf(obj[1]));
				jsonObject.put("packtype", String.valueOf(obj[2]));
				jsonObject.put("strRemoteUser", FtpUtil.ftpuser);
				jsonObject.put("strRemotePass", FtpUtil.ftppass);
				File file = new File(FileUpDownUtil.path.concat(String.valueOf(obj[1]) + File.separator + String.valueOf(obj[3])));
				jsonObject.put("updatefile", Base64.encode(FileUtils.readFileToByteArray(file)));
			}else{
				jsonObject.put("isSucc",  "N");
				jsonObject.put("filename", "");
				jsonObject.put("osname", "");
				jsonObject.put("filepath", "");
				jsonObject.put("packtype", "");
				jsonObject.put("errDesc", "没有可更新安装包！");
				jsonObject.put("strRemoteUser", FtpUtil.ftpuser);
				jsonObject.put("strRemotePass", FtpUtil.ftppass);
			}
			array.put(jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return array.toString();
	}
	
	@Override
	public String getServDataList(String strAreaId, String strUpdated) {
		return this.getTableDataHelp(null, strUpdated, "T_SYS_SERVER");
	}
	
	@Override
	public String getRoleDataList(String strAreaId, String strUpdated) {
		return this.getTableDataHelp(null, strUpdated, "T_SYS_ROLE");
	}
	
	@Override
	public String roleInfoIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_SYS_ROLE");
	}
	
	@Override
	public String getFuncInfoList(String strAreaId, String strUpdated) {
		JSONArray array = new JSONArray();
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("T_SYS_FUNC", new JSONArray(getTableDataHelp(null, strUpdated, "T_SYS_FUNC")));
			jsonobj.put("T_SYS_FUNCOPER", new JSONArray(getTableDataHelp(null, strUpdated, "T_SYS_FUNCOPER")));
			array.put(jsonobj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array.toString();
	}
	
	@Override
	public String funcInfoIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_SYS_FUNC");
	}
	
	@Override
	public String funcOperInfoIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_SYS_FUNCOPER");
	}
	
	@Override
	public String getRoleFuncInfoList(String strAreaId, String strUpdated) {
		JSONArray array = new JSONArray();
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("T_SYS_ROLEFUNC", new JSONArray(getTableDataHelp(null, strUpdated, "T_SYS_ROLEFUNC")));
			jsonobj.put("T_SYS_ROLEFUNCOPER", new JSONArray(getTableDataHelp(null, strUpdated, "T_SYS_ROLEFUNCOPER")));
			array.put(jsonobj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array.toString();
	}
	
	@Override
	public String versionInfoIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_VERSION");
	}
	
	@Override
	public String getVersionInfoList(String strAreaId, String strUpdated) {
		return this.getTableDataHelp(null, strUpdated, "T_DATA_VERSION");
	}
	
	@Override
	public String getJcmbszInfoList(String strAreaId, String strUpdated) {
		return this.getTableDataHelp(null, strUpdated, "T_DATA_LAWOBJTYPETASKTYPE");
	}
	
	@Override
	public String getSgdwInfoList(String strAreaId, String strUpdated) {
		return this.getTableDataHelp(null, strUpdated, "T_DATA_SGDW");
	}
	
	@Override
	public String sgdwInfoIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_SGDW");
	}
	
	@Override
	public String getWghzrrInfoList(String strAreaId, String strUpdated) {
		return this.getTableDataHelp(null, strUpdated, "T_DATA_GRIDLEADER");
	}
	
	@Override
	public String getDirTasktypeInfoList(String strAreaId, String strUpdated) {
		return this.getTableDataHelp(null, strUpdated, "T_DATA_DIRANDTASKTYPE");
	}
	
	@Override
	public String ZfdxDataIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_LAWOBJ");
	}
	
	@Override
	public String getZfdxDataList(String strAreaId, String strUpdated) {
		JSONArray array = new JSONArray();
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("T_DATA_LAWOBJ", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_LAWOBJ")));
			jsonobj.put("T_DATA_FSAQJCXX", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_FSAQJCXX")));
			jsonobj.put("T_DATA_LAWOBJDIC", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_LAWOBJDIC")));
			array.put(jsonobj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array.toString();
	}
	
	@Override
	public String getQywhInfoList(String strAreaId, String strUpdated) {
		JSONArray array = new JSONArray();
		try {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("T_DATA_SHJJBZK", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_SHJJBZK")));
			jsonobj.put("T_DATA_SHJMBFB", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_SHJMBFB")));
			jsonobj.put("T_DATA_QYHJFXFFCS", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_QYHJFXFFCS")));
			jsonobj.put("T_DATA_QYHJYJCZJJYZY", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_QYHJYJCZJJYZY")));
			jsonobj.put("T_DATA_QYHXWZQKFCP", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_QYHXWZQKFCP")));
			jsonobj.put("T_DATA_QYHXWZQKYL", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_QYHXWZQKYL")));
			jsonobj.put("T_DATA_QYHXWZQKZYCP", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_QYHXWZQKZYCP")));
			jsonobj.put("T_DATA_DQHJBHMBFB", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_DQHJBHMBFB")));
			jsonobj.put("T_DATA_DQHJJBZK", new JSONArray(getTableDataHelp(null, strUpdated, "T_DATA_DQHJJBZK")));
			array.put(jsonobj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array.toString();
	}
	
	@Override
	public String serverIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_SYS_SERVER");
	}
	
	@Override
	public String HpInfoIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_LAWOBJEIA");
	}
	
	@Override
	public String zyclInfoListIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_DATA_LAWOBJEIA");
	}
	
	@Override
	public String xfdjbInfoListIsSynch(String strAreaId, String strUpdated) {
		return this.isSynchHelp(null, strUpdated, "T_BIZ_XFDJB");
	}
	
	@Override
	public String getXfdjbInfoList(String strAreaId, String strUpdated) {
		return this.getTableDataHelp(null, strUpdated, "T_BIZ_XFDJB");
	}
}
