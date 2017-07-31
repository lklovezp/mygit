package com.hnjz.mobile.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.FileUtil;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserOrg;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-9
 * 功能描述：
		公共功能Manager实现层
 *
 */
@Service("commonMobileManager")
public class CommonMobileManagerImpl extends ManagerImpl implements CommonMobileManager {
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	@Autowired
	private CommWorkManager commWorkManager;
	
	@Override
	public JSONArray queryTasktypeTree(String lawobjtype, String markId) {
		List<TDataTasktype> list = null;
		String hql = " from TDataTasktype t where isActive = 'Y' ";
		if(StringUtils.isNotBlank(lawobjtype)){
			hql += " and t.code in (select tasktypeid from TDataLawobjtypetasktype p where p.lawobjtype = ?) ";
			hql += " order by orderby ";
			list = this.find(hql,new Object[]{lawobjtype});
		}else{
			hql += " order by orderby ";
			list = this.find(hql);
		}
		return this.taskTypeTreeHelp(list, null, markId);
	}
	
	private JSONArray taskTypeTreeHelp(List<TDataTasktype> list,String pid ,String markId){
		JSONArray array = new JSONArray();
			try {
				if("1".equals(markId)){
					for(TDataTasktype ele : list){
						if((StringUtils.isBlank(pid) && StringUtils.isBlank(ele.getPid())) || (StringUtils.isNotBlank(pid) && StringUtils.isNotBlank(ele.getPid()) && pid.equals(ele.getPid())) ){
							if("10".equals(ele.getCode()) || "12".equals(ele.getCode()) || "15".equals(ele.getCode())){
								JSONObject obj = new JSONObject();
								obj.put("id",ele.getCode());
								obj.put("pId",ele.getPid()==null?"":ele.getPid());
								obj.put("name",ele.getName());
								array.put(obj);
							}
						}
					}
				}else{
					for(TDataTasktype ele : list){
						if((StringUtils.isBlank(pid) && StringUtils.isBlank(ele.getPid())) || (StringUtils.isNotBlank(pid) && StringUtils.isNotBlank(ele.getPid()) && pid.equals(ele.getPid())) ){
							JSONObject obj = new JSONObject();
							obj.put("id",ele.getCode());
							obj.put("pId",ele.getPid()==null?"":ele.getPid());
							obj.put("name",ele.getName());
							array.put(obj);
						}
					}
				}
				for(int i=0;i<array.length();i++){
					JSONObject obj = array.getJSONObject(i);
					String parentId = obj.getString("pId");
					if(StringUtils.isNotBlank(parentId)){
						Integer count = 2;
						obj.put("level", this.jsonHelp(array, parentId, count));
					}else{
						obj.put("level", 1);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
        return array;
	}
	
	private Integer jsonHelp(JSONArray array,String pid,Integer count){
		try {
			for(int i=0;i<array.length();i++){
				JSONObject obj = array.getJSONObject(i);
				if(obj.getString("id").equals(pid) && StringUtils.isNotBlank(obj.getString("pId"))){
					count++;
					count = this.jsonHelp(array, obj.getString("pId"), count);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public FyWebResult queryFileList(String pid, String fileType,String page, String rows) {
		Map<String,Object> condition = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer("select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ and d.type_ = '4' where pid_ = :pid");
		condition.put("pid", pid);
		//附件类型
		if(StringUtils.isNotBlank(fileType)){
			String[] arg = fileType.split(",");
			String filetypeCode = "";
			for (int i = 0; i < arg.length; i++) {
				filetypeCode += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					filetypeCode += ",";
				}
			}
			sql.append(" and d.code_ in ("+filetypeCode+")");;
		}
		sql.append(" order by d.code_, f.CREATED_ desc");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), rows==null?0:Integer.parseInt(rows), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		fy.setRows(rowsList);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String name = "";
		for (Object[] obj : listLawobj) {
			if (String.valueOf(obj[1]).contains(".")){
				name = String.valueOf(obj[1]).substring((String.valueOf(obj[1]).lastIndexOf(".") + 1), String.valueOf(obj[1]).length());
			}else{
				name = String.valueOf(obj[1]);
			}
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("url", "/download.mo?id="+String.valueOf(obj[0]));
			if(obj[2] != null && FileUtil.isImage(String.valueOf(obj[2]))){
				dataObject.put("imageUrl", obj[0] == null ? "" : "/downThumbnailImage.mo?id="+String.valueOf(obj[0]));
			}
			dataObject.put("filetype", name);
			dataObject.put("filetypeCode", obj[5] == null ? "0" : String.valueOf(obj[5]));
			dataObject.put("filename", obj[2] == null ? "" : String.valueOf(obj[2]));
			Long filesize = Long.valueOf(obj[3] == null ? "0" : String.valueOf(obj[3]));
			dataObject.put("filesize", FileUtil.sizeFormat(filesize));
			rowsList.add(dataObject);
		}
		return fy;
	}

	@Override
	public JSONArray queryWorkBlFileList(String rwid) {
		JSONArray array = new JSONArray();
		try {
			List<Object[]> tasktypeList = this.dao.findBySql("select p.code_,p.name_ from t_biz_taskandtasktype t left join t_data_tasktype p on t.tasktypeid_ = p.code_ where t.ISACTIVE_ = 'Y' and t.taskid_ = ? order by p.code_ ", rwid);
			for(Object[] obj : tasktypeList){
				JSONObject tasktypeObj = new JSONObject();
				tasktypeObj.put("tasktypeCode", String.valueOf(obj[0]));
				tasktypeObj.put("tasktypeName", String.valueOf(obj[1]));
				String fileType = this.queryBlFileType(String.valueOf(obj[0]));
				tasktypeObj.put("fileList", this.queryFileList(rwid, fileType));
				array.put(tasktypeObj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	@Override
	public JSONArray queryWorkBglxFileList(String rwid) {
		JSONArray array = new JSONArray();
		String fileType = "";
		try {
			List<Object[]> tasktypeList = this.dao.findBySql("select p.code_,p.name_ from t_biz_taskandtasktype t left join t_data_tasktype p on t.tasktypeid_ = p.code_ where t.isActive_ = 'Y' and t.taskid_ = ? order by p.code_ ", rwid);
			for(Object[] obj : tasktypeList){
				JSONObject tasktypeObj = new JSONObject();
				tasktypeObj.put("tasktypeCode", String.valueOf(obj[0]));
				tasktypeObj.put("tasktypeName", String.valueOf(obj[1]));
				tasktypeObj.put("jcjl", commWorkManager.getJcjl(rwid, String.valueOf(obj[0])));
				//获取报告附件类型
				fileType = this.queryBgFileType(String.valueOf(obj[0]));
				tasktypeObj.put("fileList", this.queryFileList(rwid, fileType));
				array.put(tasktypeObj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	
	@Override
	public String queryBlFileType(String rwlx) {
		String filetype = "";
		if(rwlx.equals(TaskTypeCode.RCJC.getCode())){//现场监察
			filetype += FileTypeEnums.RCJCJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.RCJCXZWS.getCode()+",";
			filetype += FileTypeEnums.RCJCQTZL.getCode()+",";
			filetype += FileTypeEnums.RCJCCLYJS.getCode()+",";
			filetype +=	FileTypeEnums.RCJCSPZL.getCode()+",";
			filetype +=	FileTypeEnums.RCJCLYZL.getCode()+",";
			filetype +=	FileTypeEnums.RCJCZP.getCode()+",";
			filetype +=	FileTypeEnums.RCJCHPPFWJ.getCode()+",";
			filetype +=	FileTypeEnums.RCJCYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.NDHC.getCode())){//年度核查
			filetype += FileTypeEnums.NDHCJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.NDHCXZWS.getCode()+",";
			filetype += FileTypeEnums.NDHCQTZL.getCode()+",";
			filetype += FileTypeEnums.NDHCCLYJS.getCode()+",";
			filetype += FileTypeEnums.NDHCSPZL.getCode()+",";
			filetype += FileTypeEnums.NDHCLYZL.getCode()+",";
			filetype += FileTypeEnums.NDHCZP.getCode()+",";
			filetype += FileTypeEnums.NDHCHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.NDHCYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.HDC.getCode())){//后督察
			filetype += FileTypeEnums.HDCJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.HDCXZWS.getCode()+",";
			filetype += FileTypeEnums.HDCQTZL.getCode()+",";
			filetype += FileTypeEnums.HDCCLYJS.getCode()+",";
			filetype += FileTypeEnums.HDCSPZL.getCode()+",";
			filetype += FileTypeEnums.HDCLYZL.getCode()+",";
			filetype += FileTypeEnums.HDCZP.getCode()+",";
			filetype += FileTypeEnums.HDCHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.HDCYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.XFTS.getCode())){//信访投诉
			filetype += FileTypeEnums.XFTSJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.XFTSXZWS.getCode()+",";
			filetype += FileTypeEnums.XFTSQTZL.getCode()+",";
			filetype += FileTypeEnums.XFTSCLYJS.getCode()+",";
			filetype += FileTypeEnums.XFTSSPZL.getCode()+",";
			filetype += FileTypeEnums.XFTSLYZL.getCode()+",";
			filetype += FileTypeEnums.XFTSZP.getCode()+",";
			filetype += FileTypeEnums.XFTSHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.XFTSYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.PWXKZJC.getCode())){//排污许可证检查
			filetype += FileTypeEnums.PWXKZJCJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.PWXKZJCXZWS.getCode()+",";
			filetype += FileTypeEnums.PWXKZJCQTZL.getCode()+",";
			filetype += FileTypeEnums.PWXKZJCCLYJS.getCode()+",";
			filetype += FileTypeEnums.PWXKZSPZL.getCode()+",";
			filetype += FileTypeEnums.PWXKZLYZL.getCode()+",";
			filetype += FileTypeEnums.PWXKZZP.getCode()+",";
			filetype += FileTypeEnums.PWXKZHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.PWXKZYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.ZXXD.getCode())){//专项行动
			filetype += FileTypeEnums.ZXXDJCBG.getCode()+",";
			filetype += FileTypeEnums.ZXXDQTZL.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.WFAJ.getCode())){//违法案件调查
			filetype += FileTypeEnums.WFAJDCLADJB.getCode()+",";
			filetype += FileTypeEnums.WFAJDCLADJSMJ.getCode()+",";
			filetype += FileTypeEnums.WFAJDCKCBLSMJ.getCode()+",";
			filetype += FileTypeEnums.WFAJDCXWBLSMJ.getCode()+",";
			filetype += FileTypeEnums.WFAJDCSZDZJZL.getCode()+",";
			filetype += FileTypeEnums.WFAJDCSTZLTP.getCode()+",";
			filetype += FileTypeEnums.WFAJDCYPZL.getCode()+",";
			filetype += FileTypeEnums.WFAJDCSTZLLX.getCode()+",";
//			filetype += FileTypeEnums.WFAJDCSTZLDYFJ.getCode()+",";
			filetype += FileTypeEnums.WFAJDCXZWS.getCode()+",";
			filetype += FileTypeEnums.WFAJDCQTZL.getCode()+",";
			filetype += FileTypeEnums.WFAJDCDCBG.getCode()+",";
			filetype += FileTypeEnums.WFAJDCHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.WFAJDCYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.XQZL.getCode())){//限期治理
			filetype += FileTypeEnums.XQZLJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.XQZLXZWS.getCode()+",";
			filetype += FileTypeEnums.XQZLQTZL.getCode()+",";
			filetype += FileTypeEnums.XQZLSPZL.getCode()+",";
			filetype += FileTypeEnums.XQZLLYZL.getCode()+",";
			filetype += FileTypeEnums.XQZLZP.getCode()+",";
			filetype += FileTypeEnums.XQZLHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.XQZLYSPFWJ.getCode()+",";
			filetype += FileTypeEnums.XQZLHJWFXWXQGZTZ.getCode()+",";
			filetype += FileTypeEnums.XQZLXZCFJDHSDHZ.getCode()+",";
			filetype += FileTypeEnums.XQZLTZGZSSDHZ.getCode()+",";
			filetype += FileTypeEnums.XQZLCLYJS.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.GZJC.getCode())){//跟踪检查
			filetype += FileTypeEnums.GZJCJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.GZJCXZWS.getCode()+",";
			filetype += FileTypeEnums.GZJCQTZL.getCode()+",";
			filetype += FileTypeEnums.GZJCCLYJS.getCode()+",";
			filetype += FileTypeEnums.GZJCSPZL.getCode()+",";
			filetype += FileTypeEnums.GZJCLYZL.getCode()+",";
			filetype += FileTypeEnums.GZJCZP.getCode()+",";
			filetype += FileTypeEnums.GZJCHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.GZJCYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.ZDJK.getCode())){//自动监控
			filetype += FileTypeEnums.ZDJKJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.ZDJKXZWS.getCode()+",";
			filetype += FileTypeEnums.ZDJKQTZL.getCode()+",";
			filetype += FileTypeEnums.ZDJKCLYJS.getCode()+",";
			filetype += FileTypeEnums.ZDJKSPZL.getCode()+",";
			filetype += FileTypeEnums.ZDJKLYZL.getCode()+",";
			filetype += FileTypeEnums.ZDJKZP.getCode()+",";
			filetype += FileTypeEnums.ZDJKHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.ZDJKYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.WXFW.getCode())){//危险废物
			filetype += FileTypeEnums.WXFWJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.WXFWXZWS.getCode()+",";
			filetype += FileTypeEnums.WXFWQTZL.getCode()+",";
			filetype += FileTypeEnums.WXFWCLYJS.getCode()+",";
			filetype += FileTypeEnums.WXFWSPZL.getCode()+",";
			filetype += FileTypeEnums.WXFWLYZL.getCode()+",";
			filetype += FileTypeEnums.WXFWZP.getCode()+",";
			filetype += FileTypeEnums.WXFWHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.WXFWYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.WXHXP.getCode())){//危险化学品
			filetype += FileTypeEnums.WXHXPJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.WXHXPXZWS.getCode()+",";
			filetype += FileTypeEnums.WXHXPQTZL.getCode()+",";
			filetype += FileTypeEnums.WXHXPCLYJS.getCode()+",";
			filetype += FileTypeEnums.WXHXPSPZL.getCode()+",";
			filetype += FileTypeEnums.WXHXPLYZL.getCode()+",";
			filetype += FileTypeEnums.WXHXPZP.getCode()+",";
			filetype += FileTypeEnums.WXHXPHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.WXHXPYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.FSAQ.getCode())){//辐射安全
			filetype += FileTypeEnums.FSAQJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.FSAQXZWS.getCode()+",";
			filetype += FileTypeEnums.FSAQQTZL.getCode()+",";
			filetype += FileTypeEnums.FSAQCLYJS.getCode()+",";
			filetype += FileTypeEnums.FSAQSPZL.getCode()+",";
			filetype += FileTypeEnums.FSAQLYZL.getCode()+",";
			filetype += FileTypeEnums.FSAQZP.getCode()+",";
			filetype += FileTypeEnums.FSAQHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.FSAQYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.WRSGXCDC.getCode())){//污染事故现场调查
			filetype += FileTypeEnums.WRSGXCDCJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCXZWS.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCQTZL.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCCLYJS.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCSPZL.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCLYZL.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCZP.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.RCBG.getCode())){//日常办公
			filetype += FileTypeEnums.RCBGBLZL.getCode()+",";//办理附件
		}
		return filetype;
	}
	
	public String queryBgFileType(String rwlx) {
		String filetype = "";
		if(rwlx.equals(TaskTypeCode.RCJC.getCode())){//现场监察
			filetype += FileTypeEnums.RCJCJCJL.getCode()+",";
			filetype += FileTypeEnums.RCJCMOREJCBL.getCode()+",";
			filetype += FileTypeEnums.RCJCJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.RCJCXZWS.getCode()+",";
			filetype += FileTypeEnums.RCJCQTZL.getCode()+",";
			filetype += FileTypeEnums.RCJCCLYJS.getCode()+",";
			filetype += FileTypeEnums.RCJCZBZL.getCode()+",";
			filetype += FileTypeEnums.RCJCDBFJ.getCode()+",";
			filetype +=	FileTypeEnums.RCJCSPZL.getCode()+",";
			filetype +=	FileTypeEnums.RCJCLYZL.getCode()+",";
			filetype +=	FileTypeEnums.RCJCZP.getCode()+",";
			filetype +=	FileTypeEnums.RCJCHPPFWJ.getCode()+",";
			filetype +=	FileTypeEnums.RCJCYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.NDHC.getCode())){//年度核查
			filetype += FileTypeEnums.NDHCJCJL.getCode()+",";
			filetype += FileTypeEnums.NDHCMOREJCBL.getCode()+",";
			filetype += FileTypeEnums.NDHCJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.NDHCXZWS.getCode()+",";
			filetype += FileTypeEnums.NDHCQTZL.getCode()+",";
			filetype += FileTypeEnums.NDHCCLYJS.getCode()+",";
			filetype += FileTypeEnums.NDHCZBZL.getCode()+",";
			filetype += FileTypeEnums.NDHCDBFJ.getCode()+",";
			filetype += FileTypeEnums.NDHCSPZL.getCode()+",";
			filetype += FileTypeEnums.NDHCLYZL.getCode()+",";
			filetype += FileTypeEnums.NDHCZP.getCode()+",";
			filetype += FileTypeEnums.NDHCHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.NDHCYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.HDC.getCode())){//后督察
			filetype += FileTypeEnums.HDCJCJL.getCode()+",";
			filetype += FileTypeEnums.HDCMOREJCBL.getCode()+",";
			filetype += FileTypeEnums.HDCJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.HDCXZWS.getCode()+",";
			filetype += FileTypeEnums.HDCQTZL.getCode()+",";
			filetype += FileTypeEnums.HDCCLYJS.getCode()+",";
			filetype += FileTypeEnums.HDCZBZL.getCode()+",";
			filetype += FileTypeEnums.HDCDBFJ.getCode()+",";
			filetype += FileTypeEnums.HDCSPZL.getCode()+",";
			filetype += FileTypeEnums.HDCLYZL.getCode()+",";
			filetype += FileTypeEnums.HDCZP.getCode()+",";
			filetype += FileTypeEnums.HDCHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.HDCYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.XFTS.getCode())){//信访投诉
			filetype += FileTypeEnums.XFTSJCJL.getCode()+",";
			filetype += FileTypeEnums.XFTSJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.XFTSXZWS.getCode()+",";
			filetype += FileTypeEnums.XFTSQTZL.getCode()+",";
			filetype += FileTypeEnums.XFTSCLYJS.getCode()+",";
			filetype += FileTypeEnums.XFTSZBZL.getCode()+",";
			filetype += FileTypeEnums.XFTSDBFJ.getCode()+",";
			filetype += FileTypeEnums.XFTSSPZL.getCode()+",";
			filetype += FileTypeEnums.XFTSLYZL.getCode()+",";
			filetype += FileTypeEnums.XFTSZP.getCode()+",";
			filetype += FileTypeEnums.XFTSHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.XFTSMOREJCBL.getCode()+",";
			filetype += FileTypeEnums.XFTSBJDSMJ.getCode()+",";
			filetype += FileTypeEnums.XFTSYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.PWXKZJC.getCode())){//排污许可证检查
			filetype += FileTypeEnums.PWXKZJCJCJL.getCode()+",";
			filetype += FileTypeEnums.PWXKZJCMOREJCBL.getCode()+",";
			filetype += FileTypeEnums.PWXKZJCJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.PWXKZJCXZWS.getCode()+",";
			filetype += FileTypeEnums.PWXKZJCQTZL.getCode()+",";
			filetype += FileTypeEnums.PWXKZJCCLYJS.getCode()+",";
			filetype += FileTypeEnums.PWXKZJCZBZL.getCode()+",";
			filetype += FileTypeEnums.PWXKZDBFJ.getCode()+",";
			filetype += FileTypeEnums.PWXKZSPZL.getCode()+",";
			filetype += FileTypeEnums.PWXKZLYZL.getCode()+",";
			filetype += FileTypeEnums.PWXKZZP.getCode()+",";
			filetype += FileTypeEnums.PWXKZHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.PWXKZYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.ZXXD.getCode())){//专项行动
			filetype += FileTypeEnums.ZXXDJCBG.getCode()+",";
			filetype += FileTypeEnums.ZXXDZRWYSB.getCode()+",";
			filetype += FileTypeEnums.ZXXDQTZL.getCode()+",";
			filetype += FileTypeEnums.ZXXDZBZL.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.WFAJ.getCode())){//违法案件调查
			filetype += FileTypeEnums.WFAJDCLADJB.getCode()+",";
			filetype += FileTypeEnums.WFAJDCLADJSMJ.getCode()+",";
			filetype += FileTypeEnums.WFAJDCKCBL.getCode()+",";
			filetype += FileTypeEnums.WFAJDCKCBLSMJ.getCode()+",";
			filetype += FileTypeEnums.WFAJDCXWBL.getCode()+",";
			filetype += FileTypeEnums.WFAJDCXWBLSMJ.getCode()+",";
			filetype += FileTypeEnums.WFAJDCSZDZJZL.getCode()+",";
			filetype += FileTypeEnums.WFAJDCSTZLTP.getCode()+",";
			filetype += FileTypeEnums.WFAJDCYPZL.getCode()+",";
			filetype += FileTypeEnums.WFAJDCSTZLLX.getCode()+",";
//			filetype += FileTypeEnums.WFAJDCSTZLDYFJ.getCode()+",";
			filetype += FileTypeEnums.WFAJDCXZWS.getCode()+",";
			filetype += FileTypeEnums.WFAJDCQTZL.getCode()+",";
			filetype += FileTypeEnums.WFAJDCDCBG.getCode()+",";
			filetype += FileTypeEnums.WFAJDCZBZL.getCode()+",";
			filetype += FileTypeEnums.WFAJDCDBFJ.getCode()+",";
			filetype += FileTypeEnums.WFAJDCHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.WFAJDCYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.XQZL.getCode())){//限期治理
			filetype += FileTypeEnums.XQZLJCJL.getCode()+",";
			filetype += FileTypeEnums.XQZLMOREJCBL.getCode()+",";
			filetype += FileTypeEnums.XQZLJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.XQZLXZWS.getCode()+",";
			filetype += FileTypeEnums.XQZLQTZL.getCode()+",";
			filetype += FileTypeEnums.XQZLCLYJS.getCode()+",";
			filetype += FileTypeEnums.XQZLZBZL.getCode()+",";
			filetype += FileTypeEnums.XQZLSPZL.getCode()+",";
			filetype += FileTypeEnums.XQZLLYZL.getCode()+",";
			filetype += FileTypeEnums.XQZLZP.getCode()+",";
			filetype += FileTypeEnums.XQZLHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.XQZLYSPFWJ.getCode()+",";
			filetype += FileTypeEnums.XQZLHJWFXWXQGZTZ.getCode()+",";
			filetype += FileTypeEnums.XQZLXZCFJDHSDHZ.getCode()+",";
			filetype += FileTypeEnums.XQZLTZGZSSDHZ.getCode()+",";
			filetype += FileTypeEnums.XQZLDBFJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.GZJC.getCode())){//跟踪检查
			filetype += FileTypeEnums.GZJCJCJL.getCode()+",";
			filetype += FileTypeEnums.GZJCMOREJCBL.getCode()+",";
			filetype += FileTypeEnums.GZJCJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.GZJCXZWS.getCode()+",";
			filetype += FileTypeEnums.GZJCQTZL.getCode()+",";
			filetype += FileTypeEnums.GZJCCLYJS.getCode()+",";
			filetype += FileTypeEnums.GZJCZBZL.getCode()+",";
			filetype += FileTypeEnums.GZJCDBFJ.getCode()+",";
			filetype += FileTypeEnums.GZJCSPZL.getCode()+",";
			filetype += FileTypeEnums.GZJCLYZL.getCode()+",";
			filetype += FileTypeEnums.GZJCZP.getCode()+",";
			filetype += FileTypeEnums.GZJCHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.GZJCYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.ZDJK.getCode())){//自动监控
			filetype += FileTypeEnums.ZDJKJCJL.getCode()+",";
			filetype += FileTypeEnums.ZDJKJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.ZDJKXZWS.getCode()+",";
			filetype += FileTypeEnums.ZDJKQTZL.getCode()+",";
			filetype += FileTypeEnums.ZDJKCLYJS.getCode()+",";
			filetype += FileTypeEnums.ZDJKZBZL.getCode()+",";
			filetype += FileTypeEnums.ZDJKDBFJ.getCode()+",";
			filetype += FileTypeEnums.ZDJKSPZL.getCode()+",";
			filetype += FileTypeEnums.ZDJKLYZL.getCode()+",";
			filetype += FileTypeEnums.ZDJKZP.getCode()+",";
			filetype += FileTypeEnums.ZDJKHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.ZDJKHBWD.getCode()+",";
			filetype += FileTypeEnums.ZDJKYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.WXFW.getCode())){//危险废物
			filetype += FileTypeEnums.WXFWJCJL.getCode()+",";
			filetype += FileTypeEnums.WXFWMOREJCBL.getCode()+",";
			filetype += FileTypeEnums.WXFWJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.WXFWXZWS.getCode()+",";
			filetype += FileTypeEnums.WXFWQTZL.getCode()+",";
			filetype += FileTypeEnums.WXFWCLYJS.getCode()+",";
			filetype += FileTypeEnums.WXFWZBZL.getCode()+",";
			filetype += FileTypeEnums.WXFWDBFJ.getCode()+",";
			filetype += FileTypeEnums.WXFWSPZL.getCode()+",";
			filetype += FileTypeEnums.WXFWLYZL.getCode()+",";
			filetype += FileTypeEnums.WXFWZP.getCode()+",";
			filetype += FileTypeEnums.WXFWHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.WXFWYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.WXHXP.getCode())){//危险化学品
			filetype += FileTypeEnums.WXHXPJCJL.getCode()+",";
			filetype += FileTypeEnums.WXHXPJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.WXHXPXZWS.getCode()+",";
			filetype += FileTypeEnums.WXHXPQTZL.getCode()+",";
			filetype += FileTypeEnums.WXHXPCLYJS.getCode()+",";
			filetype += FileTypeEnums.WXHXPZBZL.getCode()+",";
			filetype += FileTypeEnums.WXHXPDBFJ.getCode()+",";
			filetype += FileTypeEnums.WXHXPSPZL.getCode()+",";
			filetype += FileTypeEnums.WXHXPLYZL.getCode()+",";
			filetype += FileTypeEnums.WXHXPZP.getCode()+",";
			filetype += FileTypeEnums.WXHXPHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.WXHXPYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.FSAQ.getCode())){//辐射安全
			filetype += FileTypeEnums.FSAQJCJL.getCode()+",";
			filetype += FileTypeEnums.FSAQMOREJCBL.getCode()+",";
			filetype += FileTypeEnums.FSAQJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.FSAQXZWS.getCode()+",";
			filetype += FileTypeEnums.FSAQQTZL.getCode()+",";
			filetype += FileTypeEnums.FSAQCLYJS.getCode()+",";
			filetype += FileTypeEnums.FSAQZBZL.getCode()+",";
			filetype += FileTypeEnums.FSAQDBFJ.getCode()+",";
			filetype += FileTypeEnums.FSAQSPZL.getCode()+",";
			filetype += FileTypeEnums.FSAQLYZL.getCode()+",";
			filetype += FileTypeEnums.FSAQZP.getCode()+",";
			filetype += FileTypeEnums.FSAQHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.FSAQYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.WRSGXCDC.getCode())){//污染事故现场调查
			filetype += FileTypeEnums.WRSGXCDCJCJL.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCMOREJCBL.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCJCJLSMJ.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCXZWS.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCQTZL.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCCLYJS.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCZBZL.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCDBFJ.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCSPZL.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCLYZL.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCZP.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCHPPFWJ.getCode()+",";
			filetype += FileTypeEnums.WRSGXCDCYSPFWJ.getCode();
		}
		else if(rwlx.equals(TaskTypeCode.RCBG.getCode())){//日常办公
			filetype += FileTypeEnums.RCBGBLZL.getCode()+",";//办理附件
			filetype += FileTypeEnums.RCBGZBZL.getCode();//准备附件
		}
		return filetype;
	}
	
	@Override
	public JSONArray queryFileList(String pid, String fileType) {
		JSONArray rowsList = new JSONArray();
		Map<String,Object> condition = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer("select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ where f.isActive_ = 'Y' and d.type_ = '4' and pid_ = :pid");
		condition.put("pid", pid);
		//附件类型
		if(StringUtils.isNotBlank(fileType)){
			String[] arg = fileType.split(",");
			String filetypeCode = "";
			for (int i = 0; i < arg.length; i++) {
				filetypeCode += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					filetypeCode += ",";
				}
			}
			sql.append(" and d.code_ in ("+filetypeCode+")");;
		}
		sql.append(" order by d.code_, f.CREATED_ desc");
		List<Object[]>  pos = this.dao.findBySql(sql.toString(), condition);
		JSONObject dataObject = null;
		String name = "";
		try {
			for (Object[] obj : pos) {
				if (String.valueOf(obj[1]).contains(".")){
					name = String.valueOf(obj[1]).substring((String.valueOf(obj[1]).lastIndexOf(".") + 1), String.valueOf(obj[1]).length());
				}
				dataObject = new JSONObject();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("url", "/download.mo?id="+String.valueOf(obj[0]));
				if(obj[2] != null && FileUtil.isImage(String.valueOf(obj[2]))){
					dataObject.put("imageUrl", obj[0] == null ? "" : "/downThumbnailImage.mo?id="+String.valueOf(obj[0]));
				}
				dataObject.put("filetype", name);
				dataObject.put("filetypeCode", obj[5] == null ? "0" : String.valueOf(obj[5]));
				dataObject.put("filename", obj[2] == null ? "" : String.valueOf(obj[2]));
				Long filesize = Long.valueOf(obj[3] == null ? "0" : String.valueOf(obj[3]));
				dataObject.put("filesize", FileUtil.sizeFormat(filesize));
				rowsList.put(dataObject);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rowsList;
	}

	@Override
	public HashMap<String, Object> getUserInfo(String id) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		TSysUser u = (TSysUser) this.dao.get(TSysUser.class, id);
		if (u.getId().equals("a0000000000000000000000000000000")) {
			data.put("id", u.getId());
			data.put("name", u.getName());
			data.put("username", u.getUsername());
			data.put("personmobile", u.getPersonmobile());
			data.put("workmobile", u.getWorkmobile());
			data.put("orgid", "");
			data.put("orgname", "");
		} else {
			TSysUserOrg uo = (TSysUserOrg)this.dao.find("from TSysUserOrg where user.id = ? ", id).get(0);
			
			data.put("id", uo.getUser().getId());
			data.put("name", uo.getUser().getName());
			data.put("username", uo.getUser().getUsername());
			data.put("personmobile", uo.getUser().getPersonmobile());
			data.put("workmobile", uo.getUser().getWorkmobile());
			data.put("orgid", uo.getOrg().getId());
			data.put("orgname", uo.getOrg().getName());
		}
		List<TDataFile> files = this.dao.find("from TDataFile where pid = ?", id);
		if (files.size() > 0){
			TDataFile file = files.get(0);
			data.put("fileId", file.getId());
		} else {
			data.put("fileId", "");
		}
		
		return data;
	}

	@Override
	public String savePas(String pass, String newPass, String confirmPass) throws AppException{

		if (!newPass.equals(confirmPass)) {
			throw new AppException("两次新密码不一致，请重新输入!");
		}
		TSysUser po = null;
		TSysUser user = CtxUtil.getCurUser();
		po = (TSysUser) this.get(TSysUser.class, user.getId());
		// 判断当前密码是否正确
		String old = md5PasswordEncoder.encodePassword(pass, null);
		if (user.getPassword().equals(old)) {
			// 判断密码是否符合规则
			// 字母开头长度为6-16 英文数字下划线
			if (Pattern.compile("^[a-zA-Z]\\w{5,15}$").matcher(newPass).matches()){
				String password = md5PasswordEncoder.encodePassword(newPass, null);
				po.setPassword(password);
				this.dao.save(po);
				return "修改密码成功!";
			} else {
				throw new AppException("密码不符合规则!");
			}
		} else {
			throw new AppException("当前密码不正确!");
		}
	}
}
