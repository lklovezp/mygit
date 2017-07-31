package com.hnjz.app.data.xxgl.dataCrud;

import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataChecklistitem;
import com.hnjz.app.data.po.TDataDiscreacts;
import com.hnjz.app.data.po.TDataIllegaltype;
import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataLawdoc;
import com.hnjz.app.data.po.TDataLawdocdir;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjeia;
import com.hnjz.app.data.po.TDataRecord;
import com.hnjz.app.data.po.TDataRegion;
import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.app.data.po.TDataVersion;
import com.hnjz.app.work.po.TBizXfdj;
import com.hnjz.app.work.po.TDataSgdw;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.permission.PermissionManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysFunc;
import com.hnjz.sys.po.TSysFuncOper;
import com.hnjz.sys.po.TSysMobileFunc;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.po.TSysServer;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：zhangqingfeng
 * 生成日期：2016-3-07
 * 功能描述：数据库数据同步的功能manager实现层
 *
 */
@Service("dataCrudManager")
public class DataCrudManagerImpl extends ManagerImpl implements DataCrudManager{
	
	/**日志*/
    private static final Log log = LogFactory.getLog(DataCrudManagerImpl.class);
    
    @Autowired
	private PermissionManager permissionManager;
    
	@Override
	public void saveDataNameList(String type, String webUrl) {
		TSysUser cur = CtxUtil.getCurUser();
		String strAreaId = "";
		if(StringUtils.isNotBlank(cur.getAreaId())){
			strAreaId = cur.getAreaId();
		}else{
			strAreaId = "1";
		}
		String strUpdated = "2";
		String link = webUrl+"services/btlxService";
		org.apache.axis.client.Service service=new  org.apache.axis.client.Service();
		Call call;
	    try {
	    	call = (Call) service.createCall();
	    	//获取服务器的数据
	    	if("01".equals(type)){
	    		call.setOperationName(new QName(link, "getServDataList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
		    	// 设置参数名:
		        call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		        call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		        // 设置返回值类型：
		        call.setReturnType(XMLType.XSD_STRING);
		        String params[] = new String[]{strAreaId, strUpdated};
		        String data = (String) call.invoke(params);
		    	List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
		    	//系统服务器表
		    	StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_SYS_SERVER where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
    				StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_SYS_SERVER");
	    			sql.append(" (ID_, NAME_, URL_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("NAME_")+"', '")
	    			.append(json.get(i).get("URL_")+"', '")
	    			.append(json.get(i).get("DESC_")+"', '")
	    			.append(json.get(i).get("ORDERBY_")+"', '")
	    			.append(json.get(i).get("ISACTIVE_")+"', '")
	    			.append(json.get(i).get("VERSION_")+"', '")
	    			.append(json.get(i).get("UPDATED_")+"', '")
	    			.append(json.get(i).get("UPDATEBY_")+"', '")
	    			.append(json.get(i).get("CREATED_")+"', '")
	    			.append(json.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    	 }
	    	//获取区域的数据
	    	else if("02".equals(type)){
	    	   call.setOperationName(new QName(link, "getAreaInfoList"));//调用的方法名
	    	   call.setTargetEndpointAddress(new URL(link));
	    	   // 设置参数名:
		       call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		       call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		       // 设置返回值类型：
		       call.setReturnType(XMLType.XSD_STRING);
		       String params[] = new String[]{strAreaId, strUpdated};
		       String data = (String) call.invoke(params);
		       List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
		       //系统区域表
		       StringBuffer sql1 = new StringBuffer();
		       sql1.append("delete from T_SYS_AREA where 1=1");
		       this.dao.exec(sql1.toString());
		       for(int i = 0; i < json.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_SYS_AREA");
	    			sql.append(" (ID_, NAME_, PID_, SERVERID_, TYPE_, UNITNAME_, SHORTUNITNAME_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_, CODE_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("NAME_")+"', '")
	    			.append(json.get(i).get("PID_")+"', '")
	    			.append(json.get(i).get("SERVERID_")+"', '")
	    			.append(json.get(i).get("TYPE_")+"', '")
	    			.append(json.get(i).get("UNITNAME_")+"', '")
	    			.append(json.get(i).get("SHORTUNITNAME_")+"', '")
	    			.append(json.get(i).get("DESC_")+"', '")
	    			.append(json.get(i).get("ORDERBY_")+"', '")
	    			.append(json.get(i).get("ISACTIVE_")+"', '")
	    			.append(json.get(i).get("VERSION_")+"', '")
	    			.append(json.get(i).get("UPDATED_")+"', '")
	    			.append(json.get(i).get("UPDATEBY_")+"', '")
	    			.append(json.get(i).get("CREATED_")+"', '")
	    			.append(json.get(i).get("CREATEBY_")+"', '")
	    			.append(json.get(i).get("CODE_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    	 }
		    //获取部门的数据
	    	else if("03".equals(type)){
		    	call.setOperationName(new QName(link, "getOrgInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		//系统部门表
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_SYS_ORG where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_SYS_ORG");
	    			sql.append(" (ID_, NAME_, PID_, UNITNAME_, TYPE_, LEADER1_, LEADER2_, AREAID_, BIZTYPE_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("NAME_")+"', '")
	    			.append(json.get(i).get("PID_")+"', '")
	    			.append(json.get(i).get("UNITNAME_")+"', '")
	    			.append(json.get(i).get("TYPE_")+"', '")
	    			.append(json.get(i).get("LEADER1_")+"', '")
	    			.append(json.get(i).get("LEADER2_")+"', '")
	    			.append(json.get(i).get("AREAID_")+"', '")
	    			.append(json.get(i).get("BIZTYPE_")+"', '")
	    			.append(json.get(i).get("DESC_")+"', '")
	    			.append(json.get(i).get("ORDERBY_")+"', '")
	    			.append(json.get(i).get("ISACTIVE_")+"', '")
	    			.append(json.get(i).get("VERSION_")+"', '")
	    			.append(json.get(i).get("UPDATED_")+"', '")
	    			.append(json.get(i).get("UPDATEBY_")+"', '")
	    			.append(json.get(i).get("CREATED_")+"', '")
	    			.append(json.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取角色的数据
	    	else if("04".equals(type)){
		    	call.setOperationName(new QName(link, "getRoleDataList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		//系统角色表
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_SYS_ROLE where issys_ != 'Y'");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
	    			if(!"Y".equals(json.get(i).get("ISSYS_"))){
	    				StringBuffer sql = new StringBuffer();
		    			sql.append(" insert into T_SYS_ROLE");
		    			sql.append(" (ID_, NAME_, ISSYS_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json.get(i).get("ID_")+"', '")
		    			.append(json.get(i).get("NAME_")+"', '")
		    			.append(json.get(i).get("ISSYS_")+"', '")
		    			.append(json.get(i).get("DESC_")+"', '")
		    			.append(json.get(i).get("ORDERBY_")+"', '")
		    			.append(json.get(i).get("ISACTIVE_")+"', '")
		    			.append(json.get(i).get("VERSION_")+"', '")
		    			.append(json.get(i).get("UPDATED_")+"', '")
		    			.append(json.get(i).get("UPDATEBY_")+"', '")
		    			.append(json.get(i).get("CREATED_")+"', '")
		    			.append(json.get(i).get("CREATEBY_"))
		    			.append("')");
		    			this.dao.exec(sql.toString());
	    			}
		    	}
		    }
		    //获取用户的数据
	    	else if("05".equals(type)){
		    	call.setOperationName(new QName(link, "getUserInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		String array1 = json.get(0).get("T_SYS_USER");
	    		List<Map<String, String>> json1 = JsonResultUtil.jsonToList(array1);
	    		//系统用户表
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_SYS_USER where issys_ != 'Y'");
	    		this.dao.exec(sql1.toString());
	    		String userid = "";
	    		for(int i = 0; i < json1.size() ; i++){
	    			if(!"Y".equals(json1.get(i).get("ISSYS_"))){
	    				StringBuffer sql = new StringBuffer();
		    			sql.append(" insert into T_SYS_USER");
		    			sql.append(" (ID_, USERNAME_, NAME_, PASSWORD_, WORKMOBILE_, PERSONMOBILE_, LAWNUMBER_, PHONEIMEI_, ISSYS_, BIZTYPE_, AREAID_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json1.get(i).get("ID_")+"', '")
		    			.append(json1.get(i).get("USERNAME_")+"', '")
		    			.append(json1.get(i).get("NAME_")+"', '")
		    			.append(json1.get(i).get("PASSWORD_")+"', '")
		    			.append(json1.get(i).get("WORKMOBILE_")+"', '")
		    			.append(json1.get(i).get("PERSONMOBILE_")+"', '")
		    			.append(json1.get(i).get("LAWNUMBER_")+"', '")
		    			.append(json1.get(i).get("PHONEIMEI_")+"', '")
		    			.append(json1.get(i).get("ISSYS_")+"', '")
		    			.append(json1.get(i).get("BIZTYPE_")+"', '")
		    			.append(json1.get(i).get("AREAID_")+"', '")
		    			.append(json1.get(i).get("DESC_")+"', '")
		    			.append(json1.get(i).get("ORDERBY_")+"', '")
		    			.append(json1.get(i).get("ISACTIVE_")+"', '")
		    			.append(json1.get(i).get("VERSION_")+"', '")
		    			.append(json1.get(i).get("UPDATED_")+"', '")
		    			.append(json1.get(i).get("UPDATEBY_")+"', '")
		    			.append(json1.get(i).get("CREATED_")+"', '")
		    			.append(json1.get(i).get("CREATEBY_"))
		    			.append("')");
		    			this.dao.exec(sql.toString());
	    			}else{
	    				userid = json1.get(i).get("ID_");
	    			}
		    	}
	    		String array2 = json.get(0).get("T_SYS_USERORG");
	    		List<Map<String, String>> json2 = JsonResultUtil.jsonToList(array2);
	    		//系统用户表
	    		StringBuffer sql2 = new StringBuffer();
	    		sql2.append("delete from T_SYS_USERORG where USERID_ != '").append(userid).append("' ");
	    		this.dao.exec(sql2.toString());
	    		for(int i = 0; i < json2.size() ; i++){
	    			if(!userid.equals(json2.get(i).get("USERID_"))){
	    				StringBuffer sql = new StringBuffer();
		    			sql.append(" insert into T_SYS_USERORG");
		    			sql.append(" (ID_, USERID_, ORGID_) VALUES ('").append(json2.get(i).get("ID_")+"', '")
		    			.append(json2.get(i).get("USERID_")+"', '")
		    			.append(json2.get(i).get("ORGID_"))
		    			.append("')");
		    			this.dao.exec(sql.toString());
	    			}
		    	}
	    		String array3 = json.get(0).get("T_SYS_USERROLE");
	    		List<Map<String, String>> json3 = JsonResultUtil.jsonToList(array3);
	    		//关联的系统用户角色表
	    		StringBuffer sql3 = new StringBuffer();
	    		sql3.append("delete from T_SYS_USERROLE where USERID_ != '").append(userid).append("' ");
	    		this.dao.exec(sql3.toString());
	    		for(int i = 0; i < json3.size() ; i++){
	    			if(!userid.equals(json3.get(i).get("USERID_"))){
	    				StringBuffer sql = new StringBuffer();
		    			sql.append(" insert into T_SYS_USERROLE");
		    			sql.append(" (ID_, USERID_, ROLEID_) VALUES ('").append(json3.get(i).get("ID_")+"', '")
		    			.append(json3.get(i).get("USERID_")+"', '")
		    			.append(json3.get(i).get("ROLEID_"))
		    			.append("')");
		    			this.dao.exec(sql.toString());
	    			}
		    	}
		    }
		    //获取功能的数据
	    	else if("07".equals(type)){
		    	call.setOperationName(new QName(link, "getFuncInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		//功能表数据
	    		String array1 = json.get(0).get("T_SYS_FUNC");
	    		List<Map<String, String>> json1 = JsonResultUtil.jsonToList(array1);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_SYS_FUNC where 1=1 and PID_ != 'NULL'");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json1.size() ; i++){
	    			if(!"40288abd53bfb5c20153bfe35ff90050".equals(json1.get(i).get("ID_")) && !"40288ac4559065de015590742d00000c".equals(json1.get(i).get("ID_"))){
	    				StringBuffer sql = new StringBuffer();
	    				sql.append(" insert into T_SYS_FUNC");
		    			sql.append(" (ID_, NAME_, PID_, TYPE_, LINKADDR_, STYLE_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json1.get(i).get("ID_")+"', '")
		    			.append(json1.get(i).get("NAME_")+"', '")
		    			.append(json1.get(i).get("PID_")+"', '")
		    			.append(json1.get(i).get("TYPE_")+"', '")
		    			.append(json1.get(i).get("LINKADDR_")+"', '")
		    			.append(json1.get(i).get("STYLE_")+"', '")
		    			.append(json1.get(i).get("DESC_")+"', '")
		    			.append(json1.get(i).get("ORDERBY_")+"', '")
		    			.append(json1.get(i).get("ISACTIVE_")+"', '")
		    			.append(json1.get(i).get("VERSION_")+"', '")
		    			.append(json1.get(i).get("UPDATED_")+"', '")
		    			.append(json1.get(i).get("UPDATEBY_")+"', '")
		    			.append(json1.get(i).get("CREATED_")+"', '")
		    			.append(json1.get(i).get("CREATEBY_"))
		    			.append("')");
		    			this.dao.exec(sql.toString());
	    			}
		    	}
	    		//功能操作表数据
	    		String array2 = json.get(0).get("T_SYS_FUNCOPER");
	    		List<Map<String, String>> json2 = JsonResultUtil.jsonToList(array2);
	    		StringBuffer sql2 = new StringBuffer();
	    		sql2.append("delete from T_SYS_FUNCOPER where 1=1");
	    		this.dao.exec(sql2.toString());
	    		for(int i = 0; i < json2.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_SYS_FUNCOPER");
	    			sql.append(" (ID_, FUNCID_, ONCLICKEVENT_, LINKADDR_, OPERNAME_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json2.get(i).get("ID_")+"', '")
	    			.append(json2.get(i).get("FUNCID_")+"', '")
	    			.append(json2.get(i).get("ONCLICKEVENT_")+"', '")
	    			.append(json2.get(i).get("LINKADDR_")+"', '")
	    			.append(json2.get(i).get("OPERNAME_")+"', '")
	    			.append(json2.get(i).get("DESC_")+"', '")
	    			.append(json2.get(i).get("ORDERBY_")+"', '")
	    			.append(json2.get(i).get("ISACTIVE_")+"', '")
	    			.append(json2.get(i).get("VERSION_")+"', '")
	    			.append(json2.get(i).get("UPDATED_")+"', '")
	    			.append(json2.get(i).get("UPDATEBY_")+"', '")
	    			.append(json2.get(i).get("CREATED_")+"', '")
	    			.append(json2.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取字典的数据
	    	else if("10".equals(type)){
		    	call.setOperationName(new QName(link, "getDicDataList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_SYS_DIC where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_SYS_DIC");
	    			sql.append(" (ID_, CODE_, NAME_, TYPE_, ISDEFAULTSEL_, MANDATORY_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("CODE_")+"', '")
	    			.append(json.get(i).get("NAME_")+"', '")
	    			.append(json.get(i).get("TYPE_")+"', '")
	    			.append(json.get(i).get("ISDEFAULTSEL_")+"', '")
	    			.append(json.get(i).get("MANDATORY_")+"', '")
	    			.append(json.get(i).get("DESC_")+"', '")
	    			.append(json.get(i).get("ORDERBY_")+"', '")
	    			.append(json.get(i).get("ISACTIVE_")+"', '")
	    			.append(json.get(i).get("VERSION_")+"', '")
	    			.append(json.get(i).get("UPDATED_")+"', '")
	    			.append(json.get(i).get("UPDATEBY_")+"', '")
	    			.append(json.get(i).get("CREATED_")+"', '")
	    			.append(json.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取角色功能的数据
	    	else if("11".equals(type)){
		    	call.setOperationName(new QName(link, "getRoleFuncInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		String array1  = json.get(0).get("T_SYS_ROLEFUNC");
	    		List<Map<String, String>> json1 = JsonResultUtil.jsonToList(array1);
	    			StringBuffer sql1 = new StringBuffer();
		    		sql1.append("delete from T_SYS_ROLEFUNC where roleid_ != 'a0000000000000000000000000000000'");
		    		this.dao.exec(sql1.toString());
		    		for(int j = 0; j < json1.size() ; j++){
		    			if(!("a0000000000000000000000000000000").equals(json1.get(j).get("ROLEID_"))){
		    				StringBuffer sql = new StringBuffer();
			    			sql.append(" insert into T_SYS_ROLEFUNC");
			    			sql.append(" (ID_, ROLEID_, FUNCID_) VALUES ('").append(json1.get(j).get("ID_")+"', '")
			    			.append(json1.get(j).get("ROLEID_")+"', '")
			    			.append(json1.get(j).get("FUNCID_"))
			    			.append("')");
			    			this.dao.exec(sql.toString());
		    			}
			    	}
		    		String array2  = json.get(0).get("T_SYS_ROLEFUNCOPER");
		    		List<Map<String, String>> json2 = JsonResultUtil.jsonToList(array2);
		    		StringBuffer sql2 = new StringBuffer();
		    		sql2.append("delete from T_SYS_ROLEFUNCOPER where roleid_ != 'a0000000000000000000000000000000'");
		    		this.dao.exec(sql2.toString());
		    		for(int n = 0; n < json2.size() ; n++){
		    			if(!("a0000000000000000000000000000000").equals(json2.get(n).get("ROLEID_"))){
		    				StringBuffer sql = new StringBuffer();
			    			sql.append(" insert into T_SYS_ROLEFUNCOPER");
			    			sql.append(" (ID_, ROLEID_, FUNCOPERID_) VALUES ('").append(json2.get(n).get("ID_")+"', '")
			    			.append(json2.get(n).get("ROLEID_")+"', '")
			    			.append(json2.get(n).get("FUNCOPERID_"))
			    			.append("')");
			    			this.dao.exec(sql.toString());
		    			}
			    	}
		    		permissionManager.getSecurityData().loadAllResource();
		    }
		    //获取违法类型的数据
	    	else if("14".equals(type)){
		    	call.setOperationName(new QName(link, "getWflxList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_ILLEGALTYPE where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
    				sql.append(" insert into T_DATA_ILLEGALTYPE");
	    			sql.append(" (ID_, NAME_, PID_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATEBY_, CREATEBY_, UPDATED_, CREATED_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("NAME_")+"', '")
	    			.append(json.get(i).get("PID_")+"', '")
	    			.append(json.get(i).get("DESC_")+"', '")
	    			.append(json.get(i).get("ORDERBY_")+"', '")
	    			.append(json.get(i).get("ISACTIVE_")+"', '")
	    			.append(json.get(i).get("VERSION_")+"', '")
	    			.append(json.get(i).get("UPDATEBY_")+"', '")
	    			.append(json.get(i).get("CREATEBY_")+"', '")
	    			.append(json.get(i).get("UPDATED_")+"', '")
	    			.append(json.get(i).get("CREATED_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
	    		}
		    }
		    //获取任务类型的数据
	    	else if("15".equals(type)){
		    	call.setOperationName(new QName(link, "getRwlxList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		String array1 = json.get(0).get("T_DATA_TASKTYPE");
	    		List<Map<String, String>> json1 = JsonResultUtil.jsonToList(array1);
	    		//任务类型表
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_TASKTYPE where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json1.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_TASKTYPE");
	    			sql.append(" (ID_, CODE_, NAME_, PID_, DOTEMPLATEID_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json1.get(i).get("ID_")+"', '")
	    			.append(json1.get(i).get("CODE_")+"', '")
	    			.append(json1.get(i).get("NAME_")+"', '")
	    			.append(json1.get(i).get("PID_")+"', '")
	    			.append(json1.get(i).get("DOTEMPLATEID_")+"', '")
	    			.append(json1.get(i).get("DESC_")+"', '")
	    			.append(json1.get(i).get("ORDERBY_")+"', '")
	    			.append(json1.get(i).get("ISACTIVE_")+"', '")
	    			.append(json1.get(i).get("VERSION_")+"', '")
	    			.append(json1.get(i).get("UPDATED_")+"', '")
	    			.append(json1.get(i).get("UPDATEBY_")+"', '")
	    			.append(json1.get(i).get("CREATED_")+"', '")
	    			.append(json1.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//任务违法类型表
	    		String array2 = json.get(0).get("T_DATA_TASKTYPEANDILLEGALTYPE");
	    		List<Map<String, String>> json2 = JsonResultUtil.jsonToList(array2);
	    		StringBuffer sql2 = new StringBuffer();
	    		sql2.append("delete from T_DATA_TASKTYPEANDILLEGALTYPE where 1=1");
	    		this.dao.exec(sql2.toString());
	    		for(int i = 0; i < json2.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_TASKTYPEANDILLEGALTYPE");
	    			sql.append(" (ID_, TASKTYPEID_, ILLEGALTYPEID_) VALUES ('").append(json2.get(i).get("ID_")+"', '")
	    			.append(json2.get(i).get("TASKTYPEID_")+"', '")
	    			.append(json2.get(i).get("ILLEGALTYPEID_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//任务类型表
	    		String array3 = json.get(0).get("T_DATA_TASKTYPEATTACHMENTTYPE");
	    		List<Map<String, String>> json3 = JsonResultUtil.jsonToList(array3);
	    		StringBuffer sql3 = new StringBuffer();
	    		sql3.append("delete from T_DATA_TASKTYPEATTACHMENTTYPE where 1=1");
	    		this.dao.exec(sql3.toString());
	    		for(int i = 0; i < json3.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_TASKTYPEATTACHMENTTYPE");
	    			sql.append(" (ID_, TASKTYPEID_, ATTACHMENTTYPE_) VALUES ('").append(json3.get(i).get("ID_")+"', '")
	    			.append(json3.get(i).get("TASKTYPEID_")+"', '")
	    			.append(json3.get(i).get("ATTACHMENTTYPE_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//任务类型表
	    		String array4 = json.get(0).get("T_DATA_TASKTYPEPRIMARYFILE");
	    		List<Map<String, String>> json4 = JsonResultUtil.jsonToList(array4);
	    		StringBuffer sql4 = new StringBuffer();
	    		sql4.append("delete from T_DATA_TASKTYPEPRIMARYFILE where 1=1");
	    		this.dao.exec(sql4.toString());
	    		for(int i = 0; i < json4.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_TASKTYPEPRIMARYFILE");
	    			sql.append(" (ID_, TASKTYPEID_, ATTACHMENTTYPE_, ISPARIMAY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json4.get(i).get("ID_")+"', '")
	    			.append(json4.get(i).get("TASKTYPEID_")+"', '")
	    			.append(json4.get(i).get("ATTACHMENTTYPE_")+"', '")
	    			.append(json4.get(i).get("ISPARIMAY_")+"', '")
	    			.append(json4.get(i).get("ISACTIVE_")+"', '")
	    			.append(json4.get(i).get("VERSION_")+"', '")
	    			.append(json4.get(i).get("UPDATED_")+"', '")
	    			.append(json4.get(i).get("UPDATEBY_")+"', '")
	    			.append(json4.get(i).get("CREATED_")+"', '")
	    			.append(json4.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取行业的数据
	    	else if("16".equals(type)){
		    	call.setOperationName(new QName(link, "getHylxList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_INDUSTRY where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_INDUSTRY");
	    			sql.append(" (ID_, NAME_, PID_, LAWOBJTYPE_, TOLAWOBJTYPE_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("NAME_")+"', '")
	    			.append(json.get(i).get("PID_")+"', '")
	    			.append(json.get(i).get("LAWOBJTYPE_")+"', '")
	    			.append(json.get(i).get("TOLAWOBJTYPE_")+"', '")
	    			.append(json.get(i).get("ORDERBY_")+"', '")
	    			.append(json.get(i).get("ISACTIVE_")+"', '")
	    			.append(json.get(i).get("VERSION_")+"', '")
	    			.append(json.get(i).get("UPDATED_")+"', '")
	    			.append(json.get(i).get("UPDATEBY_")+"', '")
	    			.append(json.get(i).get("CREATED_")+"', '")
	    			.append(json.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取检查单模板的数据
	    	else if("17".equals(type)){
		    	call.setOperationName(new QName(link, "getJcjlmbList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		String array1 = json.get(0).get("T_DATA_CHECKLISTITEM");
	    		List<Map<String, String>> json1 = JsonResultUtil.jsonToList(array1);
	    		//检查单模板问题表
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_CHECKLISTITEM where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json1.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_CHECKLISTITEM");
	    			sql.append(" (ID_, TEMPID_, CONTENTS_, CONTENTSUNIT_, INPUTTYPE_, ISANSWERNEWLINE_, GETLAWOBJVALUE_, ISREQUIRED_, CODE_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json1.get(i).get("ID_")+"', '")
	    			.append(json1.get(i).get("TEMPID_")+"', '")
	    			.append(json1.get(i).get("CONTENTS_")+"', '")
	    			.append(json1.get(i).get("CONTENTSUNIT_")+"', '")
	    			.append(json1.get(i).get("INPUTTYPE_")+"', '")
	    			.append(json1.get(i).get("ISANSWERNEWLINE_")+"', '")
	    			.append(json1.get(i).get("GETLAWOBJVALUE_")+"', '")
	    			.append(json1.get(i).get("ISREQUIRED_")+"', '")
	    			.append(json1.get(i).get("CODE_")+"', '")
	    			.append(json1.get(i).get("DESC_")+"', '")
	    			.append(json1.get(i).get("ORDERBY_")+"', '")
	    			.append(json1.get(i).get("ISACTIVE_")+"', '")
	    			.append(json1.get(i).get("VERSION_")+"', '")
	    			.append(json1.get(i).get("UPDATED_")+"', '")
	    			.append(json1.get(i).get("UPDATEBY_")+"', '")
	    			.append(json1.get(i).get("CREATED_")+"', '")
	    			.append(json1.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		String array2 = json.get(0).get("T_DATA_CHECKLISTITEMANS");
	    		List<Map<String, String>> json2 = JsonResultUtil.jsonToList(array2);
	    		//检查单模板答案表
	    		StringBuffer sql2 = new StringBuffer();
	    		sql2.append("delete from T_DATA_CHECKLISTITEMANS where 1=1");
	    		this.dao.exec(sql2.toString());
	    		for(int i = 0; i < json2.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_CHECKLISTITEMANS");
	    			sql.append(" (ID_, ITEMID_, ANSWER_, ISNORMAL_, ANSWERDESC_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json2.get(i).get("ID_")+"', '")
	    			.append(json2.get(i).get("ITEMID_")+"', '")
	    			.append(json2.get(i).get("ANSWER_")+"', '")
	    			.append(json2.get(i).get("ISNORMAL_")+"', '")
	    			.append(json2.get(i).get("ANSWERDESC_")+"', '")
	    			.append(json2.get(i).get("DESC_")+"', '")
	    			.append(json2.get(i).get("ORDERBY_")+"', '")
	    			.append(json2.get(i).get("ISACTIVE_")+"', '")
	    			.append(json2.get(i).get("VERSION_")+"', '")
	    			.append(json2.get(i).get("UPDATED_")+"', '")
	    			.append(json2.get(i).get("UPDATEBY_")+"', '")
	    			.append(json2.get(i).get("CREATED_")+"', '")
	    			.append(json2.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		String array3 = json.get(0).get("T_DATA_CHECKLISTTEMPLATE");
	    		List<Map<String, String>> json3 = JsonResultUtil.jsonToList(array3);
	    		//检查单模板表
	    		StringBuffer sql3 = new StringBuffer();
	    		sql3.append("delete from T_DATA_CHECKLISTTEMPLATE where 1=1");
	    		this.dao.exec(sql3.toString());
	    		String temp = "";
	    		for(int i = 0; i < json3.size() ; i++){
	    			if(StringUtils.isNotBlank(json3.get(i).get("RELEASE_"))){
	    				temp = json3.get(i).get("RELEASE_");
	    			}else{
	    				temp = "99999";
	    			}
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_CHECKLISTTEMPLATE");
	    			sql.append(" (ID_, NAME_, PID_, INDUSTRYID_, TASKTYPEID_, ISREQUIRED_, RELEASE_, ISCURVER_, CHILDNUM_, NAME_VISIABLE_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json3.get(i).get("ID_")+"', '")
	    			.append(json3.get(i).get("NAME_")+"', '")
	    			.append(json3.get(i).get("PID_")+"', '")
	    			.append(json3.get(i).get("INDUSTRYID_")+"', '")
	    			.append(json3.get(i).get("TASKTYPEID_")+"', '")
	    			.append(json3.get(i).get("ISREQUIRED_")+"', '")
	    			.append(temp+"', '")
	    			.append(json3.get(i).get("ISCURVER_")+"', '")
	    			.append(json3.get(i).get("CHILDNUM_")+"', '")
	    			.append(json3.get(i).get("NAME_VISIABLE_")+"', '")
	    			.append(json3.get(i).get("DESC_")+"', '")
	    			.append(json3.get(i).get("ORDERBY_")+"', '")
	    			.append(json3.get(i).get("ISACTIVE_")+"', '")
	    			.append(json3.get(i).get("VERSION_")+"', '")
	    			.append(json3.get(i).get("UPDATED_")+"', '")
	    			.append(json3.get(i).get("UPDATEBY_")+"', '")
	    			.append(json3.get(i).get("CREATED_")+"', '")
	    			.append(json3.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取勘察询问笔录的数据
	    	else if("18".equals(type)){
		    	call.setOperationName(new QName(link, "getDataRecordList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		String array1 = json.get(0).get("T_DATA_RECORD");
	    		String array2 = json.get(0).get("T_DATA_RECORDLAWOBJTYPE");
	    		List<Map<String, String>> json1 = JsonResultUtil.jsonToList(array1);
	    		List<Map<String, String>> json2 = JsonResultUtil.jsonToList(array2);
	    		//勘察询问笔录表
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_RECORD where 1=1");
	    		this.dao.exec(sql1.toString());
	    		//勘察询问笔录对应的执法对象类型表
	    		StringBuffer sql2 = new StringBuffer();
	    		sql2.append("delete from T_DATA_RECORDLAWOBJTYPE where 1=1");
	    		this.dao.exec(sql2.toString());
	    		for(int i = 0; i < json1.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_RECORD");
	    			sql.append(" (ID_, CONTENT_, PID_, TSNR_, TASKTYPEID_, WFLXID_, KCXWBJ_, ISDEL_, VERNUM_, ISCURVER_, ISZDJZ_, DESC_, ORDER_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json1.get(i).get("ID_")+"', '")
	    			.append(json1.get(i).get("CONTENT_")+"', '")
	    			.append(json1.get(i).get("PID_")+"', '")
	    			.append(json1.get(i).get("TSNR_")+"', '")
	    			.append(json1.get(i).get("TASKTYPEID_")+"', '")
	    			.append(json1.get(i).get("WFLXID_")+"', '")
	    			.append(json1.get(i).get("KCXWBJ_")+"', '")
	    			.append(json1.get(i).get("ISDEL_")+"', '")
	    			.append(json1.get(i).get("VERNUM_")+"', '")
	    			.append(json1.get(i).get("ISCURVER_")+"', '")
	    			.append(json1.get(i).get("ISZDJZ_")+"', '")
	    			.append(json1.get(i).get("DESC_")+"', '")
	    			.append(json1.get(i).get("ORDER_")+"', '")
	    			.append(json1.get(i).get("ISACTIVE_")+"', '")
	    			.append(json1.get(i).get("VERSION_")+"', '")
	    			.append(json1.get(i).get("UPDATED_")+"', '")
	    			.append(json1.get(i).get("UPDATEBY_")+"', '")
	    			.append(json1.get(i).get("CREATED_")+"', '")
	    			.append(json1.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		for(int i = 0; i < json2.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_RECORDLAWOBJTYPE");
	    			sql.append(" (ID_, RECORDID_, LAWOBJTYPE_) VALUES ('").append(json2.get(i).get("ID_")+"', '")
	    			.append(json2.get(i).get("RECORDID_")+"', '")
	    			.append(json2.get(i).get("LAWOBJTYPE_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		 	//获取版本管理的数据
	    	else if("19".equals(type)){
		    	call.setOperationName(new QName(link, "getVersionInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_VERSION where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_VERSION");
	    			sql.append(" (ID_, CODE_, NAME_, ISFORCE_, TYPE_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("CODE_")+"', '")
	    			.append(json.get(i).get("NAME_")+"', '")
	    			.append(json.get(i).get("ISFORCE_")+"', '")
	    			.append(json.get(i).get("TYPE_")+"', '")
	    			.append(json.get(i).get("DESC_")+"', '")
	    			.append(json.get(i).get("ORDERBY_")+"', '")
	    			.append(json.get(i).get("ISACTIVE_")+"', '")
	    			.append(json.get(i).get("VERSION_")+"', '")
	    			.append(json.get(i).get("UPDATED_")+"', '")
	    			.append(json.get(i).get("UPDATEBY_")+"', '")
	    			.append(json.get(i).get("CREATED_")+"', '")
	    			.append(json.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取设置检查模板的数据
	    	else if("20".equals(type)){
		    	call.setOperationName(new QName(link, "getJcmbszInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_LAWOBJTYPETASKTYPE where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_LAWOBJTYPETASKTYPE");
	    			sql.append(" (ID_, LAWOBJTYPE_, TASKTYPEID_, IS_EXEC_CHECKLIST) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("LAWOBJTYPE_")+"', '")
	    			.append(json.get(i).get("TASKTYPEID_")+"', '")
	    			.append(json.get(i).get("IS_EXEC_CHECKLIST"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取所属行政区的数据
	    	else if("21".equals(type)){
		    	call.setOperationName(new QName(link, "getXzqInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_REGION where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_REGION");
	    			sql.append(" (ID_, NAME_, PID_, TYPE_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("NAME_")+"', '")
	    			.append(json.get(i).get("PID_")+"', '")
	    			.append(json.get(i).get("TYPE_")+"', '")
	    			.append(json.get(i).get("DESC_")+"', '")
	    			.append(json.get(i).get("ORDERBY_")+"', '")
	    			.append(json.get(i).get("ISACTIVE_")+"', '")
	    			.append(json.get(i).get("VERSION_")+"', '")
	    			.append(json.get(i).get("UPDATED_")+"', '")
	    			.append(json.get(i).get("UPDATEBY_")+"', '")
	    			.append(json.get(i).get("CREATED_")+"', '")
	    			.append(json.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取施工单位的数据
	    	else if("22".equals(type)){
		    	call.setOperationName(new QName(link, "getSgdwInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_SGDW where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_SGDW");
	    			sql.append(" (ID_, NAME_, ADRESS_, FDDBR_, FDDBRLXDH_, HBFZR_, HBFZRLXDH_, AREAID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("NAME_")+"', '")
	    			.append(json.get(i).get("ADRESS_")+"', '")
	    			.append(json.get(i).get("FDDBR_")+"', '")
	    			.append(json.get(i).get("FDDBRLXDH_")+"', '")
	    			.append(json.get(i).get("HBFZR_")+"', '")
	    			.append(json.get(i).get("HBFZRLXDH_")+"', '")
	    			.append(json.get(i).get("AREAID_")+"', '")
	    			.append(json.get(i).get("ISACTIVE_")+"', '")
	    			.append(json.get(i).get("VERSION_")+"', '")
	    			.append(json.get(i).get("UPDATED_")+"', '")
	    			.append(json.get(i).get("UPDATEBY_")+"', '")
	    			.append(json.get(i).get("CREATED_")+"', '")
	    			.append(json.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取执法对象的数据
	    	else if("23".equals(type)){
		    	call.setOperationName(new QName(link, "getZfdxDataList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		String array1 = json.get(0).get("T_DATA_LAWOBJ");
	    		List<Map<String, String>> json1 = JsonResultUtil.jsonToList(array1);
	    		//执法对象的数据表
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_LAWOBJ where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json1.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_LAWOBJ");
	    			sql.append(" (ID_, LAWOBJTYPE_, COLUMN_1, COLUMN_2, COLUMN_3, COLUMN_4, COLUMN_5, COLUMN_6, COLUMN_7, COLUMN_8, COLUMN_9, COLUMN_10, COLUMN_11, COLUMN_12, COLUMN_13, COLUMN_14, COLUMN_15, " +
	    					"COLUMN_16, COLUMN_17, COLUMN_18, COLUMN_19, COLUMN_20, COLUMN_21, COLUMN_22, COLUMN_23, COLUMN_24, COLUMN_25, COLUMN_26, COLUMN_27, COLUMN_28, COLUMN_29, COLUMN_30, " +
	    					"COLUMN_31, COLUMN_32, COLUMN_33, COLUMN_34, COLUMN_35, COLUMN_36, COLUMN_37, COLUMN_38, COLUMN_39, COLUMN_40, COLUMN_41, COLUMN_42, COLUMN_43, COLUMN_44, COLUMN_45, " +
	    					"COLUMN_46, COLUMN_47, COLUMN_48, COLUMN_49, COLUMN_50, AREAID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json1.get(i).get("ID_")+"', '")
	    			.append(json1.get(i).get("LAWOBJTYPE_")+"', '")
	    			.append(json1.get(i).get("COLUMN_1")+"', '")
	    			.append(json1.get(i).get("COLUMN_2")+"', '")
	    			.append(json1.get(i).get("COLUMN_3")+"', '")
	    			.append(json1.get(i).get("COLUMN_4")+"', '")
	    			.append(json1.get(i).get("COLUMN_5")+"', '")
	    			.append(json1.get(i).get("COLUMN_6")+"', '")
	    			.append(json1.get(i).get("COLUMN_7")+"', '")
	    			.append(json1.get(i).get("COLUMN_8")+"', '")
	    			.append(json1.get(i).get("COLUMN_9")+"', '")
	    			.append(json1.get(i).get("COLUMN_10")+"', '")
	    			.append(json1.get(i).get("COLUMN_11")+"', '")
	    			.append(json1.get(i).get("COLUMN_12")+"', '")
	    			.append(json1.get(i).get("COLUMN_13")+"', '")
	    			.append(json1.get(i).get("COLUMN_14")+"', '")
	    			.append(json1.get(i).get("COLUMN_15")+"', '")
	    			.append(json1.get(i).get("COLUMN_16")+"', '")
	    			.append(json1.get(i).get("COLUMN_17")+"', '")
	    			.append(json1.get(i).get("COLUMN_18")+"', '")
	    			.append(json1.get(i).get("COLUMN_19")+"', '")
	    			.append(json1.get(i).get("COLUMN_20")+"', '")
	    			.append(json1.get(i).get("COLUMN_21")+"', '")
	    			.append(json1.get(i).get("COLUMN_22")+"', '")
	    			.append(json1.get(i).get("COLUMN_23")+"', '")
	    			.append(json1.get(i).get("COLUMN_24")+"', '")
	    			.append(json1.get(i).get("COLUMN_25")+"', '")
	    			.append(json1.get(i).get("COLUMN_26")+"', '")
	    			.append(json1.get(i).get("COLUMN_27")+"', '")
	    			.append(json1.get(i).get("COLUMN_28")+"', '")
	    			.append(json1.get(i).get("COLUMN_29")+"', '")
	    			.append(json1.get(i).get("COLUMN_30")+"', '")
	    			.append(json1.get(i).get("COLUMN_31")+"', '")
	    			.append(json1.get(i).get("COLUMN_32")+"', '")
	    			.append(json1.get(i).get("COLUMN_33")+"', '")
	    			.append(json1.get(i).get("COLUMN_34")+"', '")
	    			.append(json1.get(i).get("COLUMN_35")+"', '")
	    			.append(json1.get(i).get("COLUMN_36")+"', '")
	    			.append(json1.get(i).get("COLUMN_37")+"', '")
	    			.append(json1.get(i).get("COLUMN_38")+"', '")
	    			.append(json1.get(i).get("COLUMN_39")+"', '")
	    			.append(json1.get(i).get("COLUMN_40")+"', '")
	    			.append(json1.get(i).get("COLUMN_41")+"', '")
	    			.append(json1.get(i).get("COLUMN_42")+"', '")
	    			.append(json1.get(i).get("COLUMN_43")+"', '")
	    			.append(json1.get(i).get("COLUMN_44")+"', '")
	    			.append(json1.get(i).get("COLUMN_45")+"', '")
	    			.append(json1.get(i).get("COLUMN_46")+"', '")
	    			.append(json1.get(i).get("COLUMN_47")+"', '")
	    			.append(json1.get(i).get("COLUMN_48")+"', '")
	    			.append(json1.get(i).get("COLUMN_49")+"', '")
	    			.append(json1.get(i).get("COLUMN_50")+"', '")
	    			.append(json1.get(i).get("AREAID_")+"', '")
	    			.append(json1.get(i).get("ISACTIVE_")+"', '")
	    			.append(json1.get(i).get("VERSION_")+"', '")
	    			.append(json1.get(i).get("UPDATED_")+"', '")
	    			.append(json1.get(i).get("UPDATEBY_")+"', '")
	    			.append(json1.get(i).get("CREATED_")+"', '")
	    			.append(json1.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		String array2 = json.get(0).get("T_DATA_LAWOBJDIC");
	    		List<Map<String, String>> json2 = JsonResultUtil.jsonToList(array2);
	    		//执法对象的字典表
	    		StringBuffer sql2 = new StringBuffer();
	    		sql2.append("delete from T_DATA_LAWOBJDIC where 1=1");
	    		this.dao.exec(sql2.toString());
	    		for(int i = 0; i < json2.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_LAWOBJDIC");
	    			sql.append(" (ID_, LAWOBJTYPEID_, COLENGNAME_, COLCHINAME_, MANDATORY_, ENUMNAME_, INPUTTYPE_, DATASOURCE_, ISTWOITEM_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json2.get(i).get("ID_")+"', '")
	    			.append(json2.get(i).get("LAWOBJTYPEID_")+"', '")
	    			.append(json2.get(i).get("COLENGNAME_")+"', '")
	    			.append(json2.get(i).get("COLCHINAME_")+"', '")
	    			.append(json2.get(i).get("MANDATORY_")+"', '")
	    			.append(json2.get(i).get("ENUMNAME_")+"', '")
	    			.append(json2.get(i).get("INPUTTYPE_")+"', '")
	    			.append(json2.get(i).get("DATASOURCE_")+"', '")
	    			.append(json2.get(i).get("ISTWOITEM_")+"', '")
	    			.append(json2.get(i).get("DESC_")+"', '")
	    			.append(json2.get(i).get("ORDERBY_")+"', '")
	    			.append(json2.get(i).get("ISACTIVE_")+"', '")
	    			.append(json2.get(i).get("VERSION_")+"', '")
	    			.append(json2.get(i).get("UPDATED_")+"', '")
	    			.append(json2.get(i).get("UPDATEBY_")+"', '")
	    			.append(json2.get(i).get("CREATED_")+"', '")
	    			.append(json2.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		String array3 = json.get(0).get("T_DATA_FSAQJCXX");
	    		List<Map<String, String>> json3 = JsonResultUtil.jsonToList(array3);
	    		//执法对象关联的辐射安全信息表
	    		StringBuffer sql3 = new StringBuffer();
	    		sql3.append("delete from T_DATA_FSAQJCXX where 1=1");
	    		this.dao.exec(sql3.toString());
	    		String str1 = "";
	    		String str2 = "";
	    		String str3 = "";
	    		String str4 = "";
	    		String str5 = "";
	    		String str6 = "";
	    		String str7 = "";
	    		String str8 = "";
	    		String str9 = "";
	    		String str10 = "";
	    		String str11 = "";
	    		String str12 = "";
	    		String str13 = "";
	    		String str14 = "";
	    		String str15 = "";
	    		String str16 = "";
	    		String str17 = "";
	    		for(int i = 0; i < json3.size() ; i++){
	    			if(StringUtils.isNotBlank(json3.get(i).get("GZRYSL_"))){
	    				str1 = "'"+json3.get(i).get("GZRYSL_")+"'";
	    			}else{
	    				str1 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("HGZRS_"))){
	    				str2 = "'"+json3.get(i).get("HGZRS_")+"'";
	    			}else{
	    				str2 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("YXQNRS_"))){
	    				str3 = "'"+json3.get(i).get("YXQNRS_")+"'";
	    			}else{
	    				str3 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("JLJCRS_"))){
	    				str4 = "'"+json3.get(i).get("JLJCRS_")+"'";
	    			}else{
	    				str4 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("FSYZS_"))){
	    				str5 = "'"+json3.get(i).get("FSYZS_")+"'";
	    			}else{
	    				str5 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("FSYYIL_"))){
	    				str6 = "'"+json3.get(i).get("FSYYIL_")+"'";
	    			}else{
	    				str6 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("FSYERL_"))){
	    				str7 = "'"+json3.get(i).get("FSYERL_")+"'";
	    			}else{
	    				str7 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("FSYSANL_"))){
	    				str8 = "'"+json3.get(i).get("FSYSANL_")+"'";
	    			}else{
	    				str8 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("FSYSIL_"))){
	    				str9 = "'"+json3.get(i).get("FSYSIL_")+"'";
	    			}else{
	    				str9 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("FSYWUL_"))){
	    				str10 = "'"+json3.get(i).get("FSYWUL_")+"'";
	    			}else{
	    				str10 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("FJFSYSANL_"))){
	    				str11 = "'"+json3.get(i).get("FJFSYSANL_")+"'";
	    			}else{
	    				str11 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("FJFSYSIL_"))){
	    				str12 = "'"+json3.get(i).get("FJFSYSIL_")+"'";
	    			}else{
	    				str12 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("FJFSYSILWZHD_"))){
	    				str13 = "'"+json3.get(i).get("FJFSYSILWZHD_")+"'";
	    			}else{
	    				str13 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("ZYSXZZZS_"))){
	    				str14 = "'"+json3.get(i).get("ZYSXZZZS_")+"'";
	    			}else{
	    				str14 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("ZYSXZZYIL_"))){
	    				str15 = "'"+json3.get(i).get("ZYSXZZYIL_")+"'";
	    			}else{
	    				str15 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("ZYSXZZERL_"))){
	    				str16 = "'"+json3.get(i).get("ZYSXZZERL_")+"'";
	    			}else{
	    				str16 = "0";
	    			}
	    			if(StringUtils.isNotBlank(json3.get(i).get("ZYSXZZSANL_"))){
	    				str17 = "'"+json3.get(i).get("ZYSXZZSANL_")+"'";
	    			}else{
	    				str17 = "0";
	    			}
	    			
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_FSAQJCXX");
	    			sql.append(" (ID_, LAWOBJID_, LAWOBJTYPEID_, YZBM_, HBFZRCZ_, HBFZREMAIL_, XKZH_, XKZLFW_, AQFH_, AQFHJGMC_, FZR_, XL_, ZY_, DH_, " +
	    					"GZRYSL_, HGZRS_, YXQNRS_, JLJCRS_, FSYZS_, FSYYIL_, FSYERL_, FSYSANL_, FSYSIL_, FSYWUL_, FJFSYSANL_, FJFSYLSQK_, " +
	    					"FJFSYSIL_, FJFSYSILWZHD_, FJFSYSILLSQK_, ZYSXZZZS_, ZYSXZZYIL_, ZYSXZZERL_, ZYSXZZSANL_, AREAID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json3.get(i).get("ID_")+"', '")
	    			.append(json3.get(i).get("LAWOBJID_")+"', '")
	    			.append(json3.get(i).get("LAWOBJTYPEID_")+"', '")
	    			.append(json3.get(i).get("YZBM_")+"', '")
	    			.append(json3.get(i).get("HBFZRCZ_")+"', '")
	    			.append(json3.get(i).get("HBFZREMAIL_")+"', '")
	    			.append(json3.get(i).get("XKZH_")+"', '")
	    			.append(json3.get(i).get("XKZLFW_")+"', '")
	    			.append(json3.get(i).get("AQFH_")+"', '")
	    			.append(json3.get(i).get("AQFHJGMC_")+"', '")
	    			.append(json3.get(i).get("FZR_")+"', '")
	    			.append(json3.get(i).get("XL_")+"', '")
	    			.append(json3.get(i).get("ZY_")+"', '")
	    			.append(json3.get(i).get("DH_")+"', ")
	    			.append(str1+", ")
	    			.append(str2+", ")
	    			.append(str3+", ")
	    			.append(str4+", ")
	    			.append(str5+", ")
	    			.append(str6+", ")
	    			.append(str7+", ")
	    			.append(str8+", ")
	    			.append(str9+", ")
	    			.append(str10+", ")
	    			.append(str11+", '")
	    			.append(json3.get(i).get("FJFSYLSQK_")+"', ")
	    			.append(str12+", ")
	    			.append(str13+", '")
	    			.append(json3.get(i).get("FJFSYSILLSQK_")+"', ")
	    			.append(str14+", ")
	    			.append(str15+", ")
	    			.append(str16+", ")
	    			.append(str17+", '")
	    			.append(json3.get(i).get("AREAID_")+"', '")
	    			.append(json3.get(i).get("ISACTIVE_")+"', '")
	    			.append(json3.get(i).get("VERSION_")+"', '")
	    			.append(json3.get(i).get("UPDATED_")+"', '")
	    			.append(json3.get(i).get("UPDATEBY_")+"', '")
	    			.append(json3.get(i).get("CREATED_")+"', '")
	    			.append(json3.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取建设项目环评的数据
	    	else if("24".equals(type)){
		    	call.setOperationName(new QName(link, "getHpInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_LAWOBJEIA where 1=1");
	    		this.dao.exec(sql1.toString());
	    		String str1 = "";
	    		String str2 = "";
	    		String str3 = "";
	    		String str4 = "";
	    		String str5 = "";
	    		for(int i = 0; i < json.size() ; i++){
	    			if(StringUtils.isNotBlank(json.get(i).get("DOCNUM1DATE_"))){
	    				str1 = "'"+json.get(i).get("DOCNUM1DATE_")+"'";
	    			}else{
	    				str1 = null;
	    			}
	    			if(StringUtils.isNotBlank(json.get(i).get("DOCNUM2DATE_"))){
	    				str2 = "'"+json.get(i).get("DOCNUM2DATE_")+"'";
	    			}else{
	    				str2 = null;
	    			}
	    			if(StringUtils.isNotBlank(json.get(i).get("DOCNUM3DATE_"))){
	    				str3 = "'"+json.get(i).get("DOCNUM3DATE_")+"'";
	    			}else{
	    				str3 = null;
	    			}
	    			if(StringUtils.isNotBlank(json.get(i).get("DOCNUM4DATE_"))){
	    				str4 = "'"+json.get(i).get("DOCNUM4DATE_")+"'";
	    			}else{
	    				str4 = null;
	    			}
	    			if(StringUtils.isNotBlank(json.get(i).get("DOCNUM5DATE_"))){
	    				str5 = "'"+json.get(i).get("DOCNUM5DATE_")+"'";
	    			}else{
	    				str5 = null;
	    			}
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_LAWOBJEIA");
	    			sql.append(" (ID_, PID_, NAME_, DOCNUM1_, DOCNUM1DATE_, DOCNUM2_, DOCNUM2DATE_, DOCNUM3_, DOCNUM3DATE_, DOCNUM4_, DOCNUM4DATE_, DOCNUM5_, DOCNUM5DATE_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("PID_")+"', '")
	    			.append(json.get(i).get("NAME_")+"', '")
	    			.append(json.get(i).get("DOCNUM1_")+"', ")
	    			.append(str1+", '")
	    			.append(json.get(i).get("DOCNUM2_")+"', ")
	    			.append(str2+", '")
	    			.append(json.get(i).get("DOCNUM3_")+"', ")
	    			.append(str3+", '")
	    			.append(json.get(i).get("DOCNUM4_")+"', ")
	    			.append(str4+", '")
	    			.append(json.get(i).get("DOCNUM5_")+"', ")
	    			.append(str5+", '")
	    			.append(json.get(i).get("ISACTIVE_")+"', '")
	    			.append(json.get(i).get("VERSION_")+"', '")
	    			.append(json.get(i).get("UPDATED_")+"', '")
	    			.append(json.get(i).get("UPDATEBY_")+"', '")
	    			.append(json.get(i).get("CREATED_")+"', '")
	    			.append(json.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取网格化责任人的数据
	    	else if("25".equals(type)){
		    	call.setOperationName(new QName(link, "getWghzrrInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_GRIDLEADER where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_GRIDLEADER");
	    			sql.append(" (ID_, LAWOBJID_, USERID_, USERNAME_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("LAWOBJID_")+"', '")
	    			.append(json.get(i).get("USERID_")+"', '")
	    			.append(json.get(i).get("USERNAME_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取企业危化信息的数据
	    	else if("26".equals(type)){
		    	call.setOperationName(new QName(link, "getQywhInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		String array1 = json.get(0).get("T_DATA_SHJJBZK");
	    		String array2 = json.get(0).get("T_DATA_SHJMBFB");
	    		String array3 = json.get(0).get("T_DATA_QYHJFXFFCS");
	    		String array4 = json.get(0).get("T_DATA_QYHJYJCZJJYZY");
	    		String array5 = json.get(0).get("T_DATA_QYHXWZQKFCP");
	    		String array6 = json.get(0).get("T_DATA_QYHXWZQKYL");
	    		String array7 = json.get(0).get("T_DATA_QYHXWZQKZYCP");
	    		String array8 = json.get(0).get("T_DATA_DQHJBHMBFB");
	    		String array9 = json.get(0).get("T_DATA_DQHJJBZK");
	    		List<Map<String, String>> json1 = JsonResultUtil.jsonToList(array1);
	    		List<Map<String, String>> json2 = JsonResultUtil.jsonToList(array2);
	    		List<Map<String, String>> json3 = JsonResultUtil.jsonToList(array3);
	    		List<Map<String, String>> json4 = JsonResultUtil.jsonToList(array4);
	    		List<Map<String, String>> json5 = JsonResultUtil.jsonToList(array5);
	    		List<Map<String, String>> json6 = JsonResultUtil.jsonToList(array6);
	    		List<Map<String, String>> json7 = JsonResultUtil.jsonToList(array7);
	    		List<Map<String, String>> json8 = JsonResultUtil.jsonToList(array8);
	    		List<Map<String, String>> json9 = JsonResultUtil.jsonToList(array9);
	    		//危化表T_DATA_SHJJBZK
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_SHJJBZK where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json1.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_SHJJBZK");
	    			sql.append(" (ID_, PID_, QXLXDM_, STMC_, STDM_, DBS_, HS_, QJSTMC_, QJSTDM_, QJDBS_, AREAID_, QJHS_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json1.get(i).get("ID_")+"', '")
	    			.append(json1.get(i).get("PID_")+"', '")
	    			.append(json1.get(i).get("QXLXDM_")+"', '")
	    			.append(json1.get(i).get("STMC_")+"', '")
	    			.append(json1.get(i).get("STDM_")+"', '")
	    			.append(json1.get(i).get("DBS_")+"', '")
	    			.append(json1.get(i).get("HS_")+"', '")
	    			.append(json1.get(i).get("QJSTMC_")+"', '")
	    			.append(json1.get(i).get("QJSTDM_")+"', '")
	    			.append(json1.get(i).get("QJDBS_")+"', '")
	    			.append(json1.get(i).get("AREAID_")+"', '")
	    			.append(json1.get(i).get("QJHS_")+"', '")
	    			.append(json1.get(i).get("ISACTIVE_")+"', '")
	    			.append(json1.get(i).get("VERSION_")+"', '")
	    			.append(json1.get(i).get("UPDATED_")+"', '")
	    			.append(json1.get(i).get("UPDATEBY_")+"', '")
	    			.append(json1.get(i).get("CREATED_")+"', '")
	    			.append(json1.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//危化表T_DATA_SHJMBFB
	    		StringBuffer sql2 = new StringBuffer();
	    		sql2.append("delete from T_DATA_SHJMBFB where 1=1");
	    		this.dao.exec(sql2.toString());
	    		String sl = null;
	    		String jl = null;
	    		for(int i = 0; i < json2.size() ; i++){
	    			if(StringUtils.isBlank(json2.get(i).get("SL_"))){
	    				sl = null;
	    			}else{
	    				sl = json2.get(i).get("SL_");
	    			}
	    			if(StringUtils.isBlank(json2.get(i).get("JL_"))){
	    				jl = null;
	    			}else{
	    				jl = json2.get(i).get("JL_");
	    			}
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_SHJMBFB");
	    			sql.append(" (ID_, PID_, BHMBMC_, LX_, SL_, JD_, WD_, JL_, SXHJGN_, AREAID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json2.get(i).get("ID_")+"', '")
	    			.append(json2.get(i).get("PID_")+"', '")
	    			.append(json2.get(i).get("BHMBMC_")+"', '")
	    			.append(json2.get(i).get("LX_")+"', ")
	    			.append(sl+", '")
	    			.append(json2.get(i).get("JD_")+"', '")
	    			.append(json2.get(i).get("WD_")+"', ")
	    			.append(jl+", '")
	    			.append(json2.get(i).get("SXHJGN_")+"', '")
	    			.append(json2.get(i).get("AREAID_")+"', '")
	    			.append(json2.get(i).get("ISACTIVE_")+"', '")
	    			.append(json2.get(i).get("VERSION_")+"', '")
	    			.append(json2.get(i).get("UPDATED_")+"', '")
	    			.append(json2.get(i).get("UPDATEBY_")+"', '")
	    			.append(json2.get(i).get("CREATED_")+"', '")
	    			.append(json2.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//危化表T_DATA_QYHJFXFFCS
	    		StringBuffer sql3 = new StringBuffer();
	    		sql3.append("delete from T_DATA_QYHJFXFFCS where 1=1");
	    		this.dao.exec(sql3.toString());
	    		String xcl = null;
	    		String zdccl = null;
	    		String yxrj = null;
	    		String sgyjcrj = null;
	    		for(int i = 0; i < json3.size() ; i++){
	    			if(StringUtils.isBlank(json3.get(i).get("XCL_"))){
	    				xcl = null;
	    			}else{
	    				xcl = json3.get(i).get("XCL_");
	    			}
	    			if(StringUtils.isBlank(json3.get(i).get("ZDCCL_"))){
	    				zdccl = null;
	    			}else{
	    				zdccl = json3.get(i).get("ZDCCL_");
	    			}
	    			if(StringUtils.isBlank(json3.get(i).get("YXRJ_"))){
	    				yxrj = null;
	    			}else{
	    				yxrj = json3.get(i).get("YXRJ_");
	    			}
	    			if(StringUtils.isBlank(json3.get(i).get("SGYJCRJ_"))){
	    				sgyjcrj = null;
	    			}else{
	    				sgyjcrj = json3.get(i).get("SGYJCRJ_");
	    			}
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_QYHJFXFFCS");
	    			sql.append(" (ID_, PID_, FXDYMC_, ZYHXWZMC_, XCL_, ZDCCL_, GWGY_, YRYB_, HXWZYXL_, QT_, WY_, YXRJ_, ZYPXGG_, DMFS_, " +
	    					"DMFSCL_, BJXT_, WCJKW_, PFQX_, SGYJC_, SGYJCRJ_, QHFM_, HCC_, XSZZ_, AREAID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json3.get(i).get("ID_")+"', '")
	    			.append(json3.get(i).get("PID_")+"', '")
	    			.append(json3.get(i).get("FXDYMC_")+"', '")
	    			.append(json3.get(i).get("ZYHXWZMC_")+"', ")
	    			.append(xcl+", ")
	    			.append(zdccl+", '")
	    			.append(json3.get(i).get("GWGY_")+"', '")
	    			.append(json3.get(i).get("YRYB_")+"', '")
	    			.append(json3.get(i).get("HXWZYXL_")+"', '")
	    			.append(json3.get(i).get("QT_")+"', '")
	    			.append(json3.get(i).get("WY_")+"', ")
	    			.append(yxrj+", '")
	    			.append(json3.get(i).get("ZYPXGG_")+"', '")
	    			.append(json3.get(i).get("DMFS_")+"', '")
	    			.append(json3.get(i).get("DMFSCL_")+"', '")
	    			.append(json3.get(i).get("BJXT_")+"', '")
	    			.append(json3.get(i).get("WCJKW_")+"', '")
	    			.append(json3.get(i).get("PFQX_")+"', '")
	    			.append(json3.get(i).get("SGYJC_")+"', ")
	    			.append(sgyjcrj+", '")
	    			.append(json3.get(i).get("QHFM_")+"', '")
	    			.append(json3.get(i).get("HCC_")+"', '")
	    			.append(json3.get(i).get("XSZZ_")+"', '")
	    			.append(json3.get(i).get("AREAID_")+"', '")
	    			.append(json3.get(i).get("ISACTIVE_")+"', '")
	    			.append(json3.get(i).get("VERSION_")+"', '")
	    			.append(json3.get(i).get("UPDATED_")+"', '")
	    			.append(json3.get(i).get("UPDATEBY_")+"', '")
	    			.append(json3.get(i).get("CREATED_")+"', '")
	    			.append(json3.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//危化表T_DATA_QYHJYJCZJJYZY
	    		StringBuffer sql4 = new StringBuffer();
	    		sql4.append("delete from T_DATA_QYHJYJCZJJYZY where 1=1");
	    		this.dao.exec(sql4.toString());
	    		String xysl = null;
	    		String sxsl = null;
	    		for(int i = 0; i < json4.size() ; i++){
	    			if(StringUtils.isBlank(json4.get(i).get("XYSL_"))){
	    				xysl = null;
	    			}else{
	    				xysl = json4.get(i).get("XYSL_");
	    			}
	    			if(StringUtils.isBlank(json4.get(i).get("SXSL_"))){
	    				sxsl = null;
	    			}else{
	    				sxsl = json4.get(i).get("SXSL_");
	    			}
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_QYHJYJCZJJYZY");
	    			sql.append(" (ID_, PID_, TYPE_, WZMC_, XYSL_, SXSL_, WBNAME_, WBXM_, WBDH_, AREAID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json4.get(i).get("ID_")+"', '")
	    			.append(json4.get(i).get("PID_")+"', '")
	    			.append(json4.get(i).get("TYPE_")+"', '")
	    			.append(json4.get(i).get("WZMC_")+"', ")
	    			.append(xysl+", ")
	    			.append(sxsl+", '")
	    			.append(json4.get(i).get("WBNAME_")+"', '")
	    			.append(json4.get(i).get("WBXM_")+"', '")
	    			.append(json4.get(i).get("WBDH_")+"', '")
	    			.append(json4.get(i).get("AREAID_")+"', '")
	    			.append(json4.get(i).get("ISACTIVE_")+"', '")
	    			.append(json4.get(i).get("VERSION_")+"', '")
	    			.append(json4.get(i).get("UPDATED_")+"', '")
	    			.append(json4.get(i).get("UPDATEBY_")+"', '")
	    			.append(json4.get(i).get("CREATED_")+"', '")
	    			.append(json4.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//危化表T_DATA_QYHXWZQKFCP
	    		StringBuffer sql5 = new StringBuffer();
	    		sql5.append("delete from T_DATA_QYHXWZQKFCP where 1=1");
	    		this.dao.exec(sql5.toString());
	    		String wzfl = null;
	    		for(int i = 0; i < json5.size() ; i++){
	    			if(StringUtils.isBlank(json5.get(i).get("WZFL_"))){
	    				wzfl = null;
	    			}else{
	    				wzfl = json5.get(i).get("WZFL_");
	    			}
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_QYHXWZQKFCP");
	    			sql.append(" (ID_, PID_, SPM_, HXM_, CAS_, WLZT_, WZFL_, YT_, AREAID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json5.get(i).get("ID_")+"', '")
	    			.append(json5.get(i).get("PID_")+"', '")
	    			.append(json5.get(i).get("SPM_")+"', '")
	    			.append(json5.get(i).get("HXM_")+"', '")
	    			.append(json5.get(i).get("CAS_")+"', '")
	    			.append(json5.get(i).get("WLZT_")+"', ")
	    			.append(wzfl+", '")
	    			.append(json5.get(i).get("YT_")+"', '")
	    			.append(json5.get(i).get("AREAID_")+"', '")
	    			.append(json5.get(i).get("ISACTIVE_")+"', '")
	    			.append(json5.get(i).get("VERSION_")+"', '")
	    			.append(json5.get(i).get("UPDATED_")+"', '")
	    			.append(json5.get(i).get("UPDATEBY_")+"', '")
	    			.append(json5.get(i).get("CREATED_")+"', '")
	    			.append(json5.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//危化表T_DATA_QYHXWZQKYL
	    		StringBuffer sql6 = new StringBuffer();
	    		sql6.append("delete from T_DATA_QYHXWZQKYL where 1=1");
	    		this.dao.exec(sql6.toString());
	    		String str1 = "";
	    		String str2 = "";
	    		String str3 = "";
	    		String str4 = "";
	    		for(int i = 0; i < json6.size() ; i++){
	    			if(StringUtils.isBlank(json6.get(i).get("SYL_"))){
	    				str1 = null;
	    			}else{
	    				str1 = json6.get(i).get("SYL_");
	    			}
	    			if(StringUtils.isBlank(json6.get(i).get("CPDH_"))){
	    				str2 = null;
	    			}else{
	    				str2 = json6.get(i).get("CPDH_");
	    			}
	    			if(StringUtils.isBlank(json6.get(i).get("SCQZCL_"))){
	    				str3 = null;
	    			}else{
	    				str3 = json6.get(i).get("SCQZCL_");
	    			}
	    			if(StringUtils.isBlank(json6.get(i).get("ZCQZCL_"))){
	    				str4 = null;
	    			}else{
	    				str4 = json6.get(i).get("ZCQZCL_");
	    			}
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_QYHXWZQKYL");
	    			sql.append(" (ID_, PID_, ZYCP_, YLMC_, HXM_, CAS_, WLZT_, SYL_, CPDH_, SCQZCL_, ZCQZCL_, SCQZCFS_, ZCQZCFS_, AREAID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json6.get(i).get("ID_")+"', '")
	    			.append(json6.get(i).get("PID_")+"', '")
	    			.append(json6.get(i).get("ZYCP_")+"', '")
	    			.append(json6.get(i).get("YLMC_")+"', '")
	    			.append(json6.get(i).get("HXM_")+"', '")
	    			.append(json6.get(i).get("CAS_")+"', '")
	    			.append(json6.get(i).get("WLZT_")+"', ")
	    			.append(str1+", ")
	    			.append(str2+", ")
	    			.append(str3+", ")
	    			.append(str4+", '")
	    			.append(json6.get(i).get("SCQZCFS_")+"', '")
	    			.append(json6.get(i).get("ZCQZCFS_")+"', '")
	    			.append(json6.get(i).get("AREAID_")+"', '")
	    			.append(json6.get(i).get("ISACTIVE_")+"', '")
	    			.append(json6.get(i).get("VERSION_")+"', '")
	    			.append(json6.get(i).get("UPDATED_")+"', '")
	    			.append(json6.get(i).get("UPDATEBY_")+"', '")
	    			.append(json6.get(i).get("CREATED_")+"', '")
	    			.append(json6.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//危化表T_DATA_QYHXWZQKZYCP
	    		StringBuffer sql7 = new StringBuffer();
	    		sql7.append("delete from T_DATA_QYHXWZQKZYCP where 1=1");
	    		this.dao.exec(sql7.toString());
	    		String sjscnl = null;
	    		String sjcl = null;
	    		String gnxsl = null;
	    		String ckxsl = null;
	    		String scqzcl = null;
	    		String zcqzcl = null;
	    		for(int i = 0; i < json7.size() ; i++){
	    			if(StringUtils.isBlank(json7.get(i).get("SJSCNL_"))){
	    				sjscnl = null;
	    			}else{
	    				sjscnl = json7.get(i).get("SJSCNL_");
	    			}
	    			if(StringUtils.isBlank(json7.get(i).get("SJCL_"))){
	    				sjcl = null;
	    			}else{
	    				sjcl = json7.get(i).get("SJCL_");
	    			}
	    			if(StringUtils.isBlank(json7.get(i).get("GNXSL_"))){
	    				gnxsl = null;
	    			}else{
	    				gnxsl = json7.get(i).get("GNXSL_");
	    			}
	    			if(StringUtils.isBlank(json7.get(i).get("CKXSL_"))){
	    				ckxsl = null;
	    			}else{
	    				ckxsl = json7.get(i).get("CKXSL_");
	    			}
	    			if(StringUtils.isBlank(json7.get(i).get("SCQZCL_"))){
	    				scqzcl = null;
	    			}else{
	    				scqzcl = json7.get(i).get("SCQZCL_");
	    			}
	    			if(StringUtils.isBlank(json7.get(i).get("ZCQZCL_"))){
	    				zcqzcl = null;
	    			}else{
	    				zcqzcl = json7.get(i).get("ZCQZCL_");
	    			}
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_QYHXWZQKZYCP");
	    			sql.append(" (ID_, PID_, SPM_, HXM_, CAS_, WLZT_, WZFL_, SJSCNL_, SJCL_, YT_, GNXSL_, CKXSL_, YSFS_, " +
	    					"SCQZCL_, ZCQZCL_, SCQZCFS_, ZCQZCFS_, SBZT_, SCFS_, AREAID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json7.get(i).get("ID_")+"', '")
	    			.append(json7.get(i).get("PID_")+"', '")
	    			.append(json7.get(i).get("SPM_")+"', '")
	    			.append(json7.get(i).get("HXM_")+"', '")
	    			.append(json7.get(i).get("CAS_")+"', '")
	    			.append(json7.get(i).get("WLZT_")+"', '")
	    			.append(json7.get(i).get("WZFL_")+"', ")
	    			.append(sjscnl+", ")
	    			.append(sjcl+", '")
	    			.append(json7.get(i).get("YT_")+"', ")
	    			.append(gnxsl+", ")
	    			.append(ckxsl+", '")
	    			.append(json7.get(i).get("YSFS_")+"', ")
	    			.append(scqzcl+", ")
	    			.append(zcqzcl+", '")
	    			.append(json7.get(i).get("SCQZCFS_")+"', '")
	    			.append(json7.get(i).get("ZCQZCFS_")+"', '")
	    			.append(json7.get(i).get("SBZT_")+"', '")
	    			.append(json7.get(i).get("SCFS_")+"', '")
	    			.append(json7.get(i).get("AREAID_")+"', '")
	    			.append(json7.get(i).get("ISACTIVE_")+"', '")
	    			.append(json7.get(i).get("VERSION_")+"', '")
	    			.append(json7.get(i).get("UPDATED_")+"', '")
	    			.append(json7.get(i).get("UPDATEBY_")+"', '")
	    			.append(json7.get(i).get("CREATED_")+"', '")
	    			.append(json7.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//危化表T_DATA_DQHJBHMBFB
	    		StringBuffer sql8 = new StringBuffer();
	    		sql8.append("delete from T_DATA_DQHJBHMBFB where 1=1");
	    		this.dao.exec(sql8.toString());
	    		String jl1 = null;
	    		for(int i = 0; i < json8.size() ; i++){
	    			if(StringUtils.isBlank(json8.get(i).get("JL_"))){
	    				jl1 = null;
	    			}else{
	    				jl1 = json8.get(i).get("JL_");
	    			}
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_DQHJBHMBFB");
	    			sql.append(" (ID_, PID_, BHMBMC_, LX_, SLJB_, JD_, WD_, WZFW_, JL_, SSWJGN_, AREAID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json8.get(i).get("ID_")+"', '")
	    			.append(json8.get(i).get("PID_")+"', '")
	    			.append(json8.get(i).get("BHMBMC_")+"', '")
	    			.append(json8.get(i).get("LX_")+"', '")
	    			.append(json8.get(i).get("SLJB_")+"', '")
	    			.append(json8.get(i).get("JD_")+"', '")
	    			.append(json8.get(i).get("WD_")+"', '")
	    			.append(json8.get(i).get("WZFW_")+"', ")
	    			.append(jl1+", '")
	    			.append(json8.get(i).get("SSWJGN_")+"', '")
	    			.append(json8.get(i).get("AREAID_")+"', '")
	    			.append(json8.get(i).get("ISACTIVE_")+"', '")
	    			.append(json8.get(i).get("VERSION_")+"', '")
	    			.append(json8.get(i).get("UPDATED_")+"', '")
	    			.append(json8.get(i).get("UPDATEBY_")+"', '")
	    			.append(json8.get(i).get("CREATED_")+"', '")
	    			.append(json8.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//危化表T_DATA_DQHJJBZK
	    		StringBuffer sql9 = new StringBuffer();
	    		sql9.append("delete from T_DATA_DQHJJBZK where 1=1");
	    		this.dao.exec(sql9.toString());
	    		for(int i = 0; i < json9.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_DQHJJBZK");
	    			sql.append(" (ID_, PID_, TYPE_, AREAID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json9.get(i).get("ID_")+"', '")
	    			.append(json9.get(i).get("PID_")+"', '")
	    			.append(json9.get(i).get("TYPE_")+"', '")
	    			.append(json9.get(i).get("AREAID_")+"', '")
	    			.append(json9.get(i).get("ISACTIVE_")+"', '")
	    			.append(json9.get(i).get("VERSION_")+"', '")
	    			.append(json9.get(i).get("UPDATED_")+"', '")
	    			.append(json9.get(i).get("UPDATEBY_")+"', '")
	    			.append(json9.get(i).get("CREATED_")+"', '")
	    			.append(json9.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取执法文件的数据
	    	else if("27".equals(type)){
		    	call.setOperationName(new QName(link, "getZfFileList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		String array1 = json.get(0).get("T_DATA_LAWDOC");
	    		String array2 = json.get(0).get("T_DATA_LAWDOCDIR");
	    		String array3 = json.get(0).get("T_DATA_FILE");
	    		List<Map<String, String>> json1 = JsonResultUtil.jsonToList(array1);
	    		List<Map<String, String>> json2 = JsonResultUtil.jsonToList(array2);
	    		List<Map<String, String>> json3 = JsonResultUtil.jsonToList(array3);
	    		//执法文件表
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_LAWDOC where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json1.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_LAWDOC");
	    			sql.append(" (ID_, NAME_, DIRID_, KEYWORDS_, FILEID_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json1.get(i).get("ID_")+"', '")
	    			.append(json1.get(i).get("NAME_")+"', '")
	    			.append(json1.get(i).get("DIRID_")+"', '")
	    			.append(json1.get(i).get("KEYWORDS_")+"', '")
	    			.append(json1.get(i).get("FILEID_")+"', '")
	    			.append(json1.get(i).get("DESC_")+"', '")
	    			.append(json1.get(i).get("ORDERBY_")+"', '")
	    			.append(json1.get(i).get("ISACTIVE_")+"', '")
	    			.append(json1.get(i).get("VERSION_")+"', '")
	    			.append(json1.get(i).get("UPDATED_")+"', '")
	    			.append(json1.get(i).get("UPDATEBY_")+"', '")
	    			.append(json1.get(i).get("CREATED_")+"', '")
	    			.append(json1.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//执法文件目录表
	    		StringBuffer sql2 = new StringBuffer();
	    		sql2.append("delete from T_DATA_LAWDOCDIR where 1=1");
	    		this.dao.exec(sql2.toString());
	    		for(int i = 0; i < json2.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_LAWDOCDIR");
	    			sql.append(" (ID_, NAME_, PID_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json2.get(i).get("ID_")+"', '")
	    			.append(json2.get(i).get("NAME_")+"', '")
	    			.append(json2.get(i).get("PID_")+"', '")
	    			.append(json2.get(i).get("DESC_")+"', '")
	    			.append(json2.get(i).get("ORDERBY_")+"', '")
	    			.append(json2.get(i).get("ISACTIVE_")+"', '")
	    			.append(json2.get(i).get("VERSION_")+"', '")
	    			.append(json2.get(i).get("UPDATED_")+"', '")
	    			.append(json2.get(i).get("UPDATEBY_")+"', '")
	    			.append(json2.get(i).get("CREATED_")+"', '")
	    			.append(json2.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//保存文件表
	    		StringBuffer sql3 = new StringBuffer();
	    		sql3.append("delete from T_DATA_FILE where 1=1 and type_ = '2400'");
	    		this.dao.exec(sql3.toString());
	    		for(int i = 0; i < json3.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_FILE");
	    			sql.append(" (ID_, PID_, OSNAME_, NAME_, EXTENSION_, SIZE_, TYPE_, EXTINFO_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_, RELATIVEPATH_) VALUES ('").append(json3.get(i).get("ID_")+"', '")
	    			.append(json3.get(i).get("PID_")+"', '")
	    			.append(json3.get(i).get("OSNAME_")+"', '")
	    			.append(json3.get(i).get("NAME_")+"', '")
	    			.append(json3.get(i).get("EXTENSION_")+"', '")
	    			.append(json3.get(i).get("SIZE_")+"', '")
	    			.append(json3.get(i).get("TYPE_")+"', '")
	    			.append(json3.get(i).get("EXTINFO_")+"', '")
	    			.append(json3.get(i).get("ISACTIVE_")+"', '")
	    			.append(json3.get(i).get("VERSION_")+"', '")
	    			.append(json3.get(i).get("UPDATED_")+"', '")
	    			.append(json3.get(i).get("UPDATEBY_")+"', '")
	    			.append(json3.get(i).get("CREATED_")+"', '")
	    			.append(json3.get(i).get("CREATEBY_")+"', '")
	    			.append(json3.get(i).get("RELATIVEPATH_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取任务类型执法文件目录的数据
	    	else if("28".equals(type)){
		    	call.setOperationName(new QName(link, "getDirTasktypeInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_DIRANDTASKTYPE where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_DIRANDTASKTYPE");
	    			sql.append(" (ID_, DIRID_, TASKTYPEID_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("DIRID_")+"', '")
	    			.append(json.get(i).get("TASKTYPEID_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
		    //获取自由裁量的数据
	    	else if("29".equals(type)){
		    	call.setOperationName(new QName(link, "getZyclInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		//自由裁量表
	    		String array1 = json.get(0).get("T_DATA_DISCREACTS");
	    		List<Map<String, String>> json1 = JsonResultUtil.jsonToList(array1);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_DATA_DISCREACTS where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json1.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_DISCREACTS");
	    			sql.append(" (ID_, CONTENT_, TYPE_, ILLEGALTYPEID_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json1.get(i).get("ID_")+"', '")
	    			.append(json1.get(i).get("CONTENT_")+"', '")
	    			.append(json1.get(i).get("TYPE_")+"', '")
	    			.append(json1.get(i).get("ILLEGALTYPEID_")+"', '")
	    			.append(json1.get(i).get("DESC_")+"', '")
	    			.append(json1.get(i).get("ORDERBY_")+"', '")
	    			.append(json1.get(i).get("ISACTIVE_")+"', '")
	    			.append(json1.get(i).get("VERSION_")+"', '")
	    			.append(json1.get(i).get("UPDATED_")+"', '")
	    			.append(json1.get(i).get("UPDATEBY_")+"', '")
	    			.append(json1.get(i).get("CREATED_")+"', '")
	    			.append(json1.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//自由裁量表的法律根据
	    		String array2 = json.get(0).get("T_DATA_DISCREMERIT");
	    		List<Map<String, String>> json2 = JsonResultUtil.jsonToList(array2);
	    		StringBuffer sql2 = new StringBuffer();
	    		sql2.append("delete from T_DATA_DISCREMERIT where 1=1");
	    		this.dao.exec(sql2.toString());
	    		for(int i = 0; i < json2.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_DISCREMERIT");
	    			sql.append(" (ID_, CISCREACTID_, ALIAS_, CONTENT_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json2.get(i).get("ID_")+"', '")
	    			.append(json2.get(i).get("CISCREACTID_")+"', '")
	    			.append(json2.get(i).get("ALIAS_")+"', '")
	    			.append(json2.get(i).get("CONTENT_")+"', '")
	    			.append(json2.get(i).get("DESC_")+"', '")
	    			.append(json2.get(i).get("ORDERBY_")+"', '")
	    			.append(json2.get(i).get("ISACTIVE_")+"', '")
	    			.append(json2.get(i).get("VERSION_")+"', '")
	    			.append(json2.get(i).get("UPDATED_")+"', '")
	    			.append(json2.get(i).get("UPDATEBY_")+"', '")
	    			.append(json2.get(i).get("CREATED_")+"', '")
	    			.append(json2.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
	    		//自由裁量表关联表
	    		String array3 = json.get(0).get("T_DATA_DISCRECASECLASS");
	    		List<Map<String, String>> json3 = JsonResultUtil.jsonToList(array3);
	    		StringBuffer sql3 = new StringBuffer();
	    		sql3.append("delete from T_DATA_DISCRECASECLASS where 1=1");
	    		this.dao.exec(sql3.toString());
	    		for(int i = 0; i < json3.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_DATA_DISCRECASECLASS");
	    			sql.append(" (ID_, MERITID_, NAME_, CONTENT_, DESC_, ORDERBY_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_) VALUES ('").append(json3.get(i).get("ID_")+"', '")
	    			.append(json3.get(i).get("MERITID_")+"', '")
	    			.append(json3.get(i).get("NAME_")+"', '")
	    			.append(json3.get(i).get("CONTENT_")+"', '")
	    			.append(json3.get(i).get("DESC_")+"', '")
	    			.append(json3.get(i).get("ORDERBY_")+"', '")
	    			.append(json3.get(i).get("ISACTIVE_")+"', '")
	    			.append(json3.get(i).get("VERSION_")+"', '")
	    			.append(json3.get(i).get("UPDATED_")+"', '")
	    			.append(json3.get(i).get("UPDATEBY_")+"', '")
	    			.append(json3.get(i).get("CREATED_")+"', '")
	    			.append(json3.get(i).get("CREATEBY_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
	    	//获取信访登记表的数据
	    	else if("30".equals(type)){
		    	call.setOperationName(new QName(link, "getXfdjbInfoList"));//调用的方法名
	    		call.setTargetEndpointAddress(new URL(link));
	    		// 设置参数名:
	            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
	            // 设置返回值类型：
	            call.setReturnType(XMLType.XSD_STRING);
	            String params[] = new String[]{strAreaId, strUpdated};
	    		String data = (String) call.invoke(params);
	    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
	    		StringBuffer sql1 = new StringBuffer();
	    		sql1.append("delete from T_BIZ_XFDJB where 1=1");
	    		this.dao.exec(sql1.toString());
	    		for(int i = 0; i < json.size() ; i++){
	    			StringBuffer sql = new StringBuffer();
	    			sql.append(" insert into T_BIZ_XFDJB");
	    			sql.append(" (ID_, XFLY_, XFBH_, WRLX_, BJSX_, XFSJ_, XFR_, LXDH_, LWMC_, XFNR_, JLR_, JLSJ_, AREAID_, ISACTIVE_, VERSION_, UPDATED_, UPDATEBY_, CREATED_, CREATEBY_, WRLXQT_) VALUES ('").append(json.get(i).get("ID_")+"', '")
	    			.append(json.get(i).get("XFLY_")+"', '")
	    			.append(json.get(i).get("XFBH_")+"', '")
	    			.append(json.get(i).get("WRLX_")+"', '")
	    			.append(json.get(i).get("BJSX_")+"', '")
	    			.append(json.get(i).get("XFSJ_")+"', '")
	    			.append(json.get(i).get("XFR_")+"', '")
	    			.append(json.get(i).get("LXDH_")+"', '")
	    			.append(json.get(i).get("LWMC_")+"', '")
	    			.append(json.get(i).get("XFNR_")+"', '")
	    			.append(json.get(i).get("JLR_")+"', '")
	    			.append(json.get(i).get("JLSJ_")+"', '")
	    			.append(json.get(i).get("AREAID_")+"', '")
	    			.append(json.get(i).get("ISACTIVE_")+"', '")
	    			.append(json.get(i).get("VERSION_")+"', '")
	    			.append(json.get(i).get("UPDATED_")+"', '")
	    			.append(json.get(i).get("UPDATEBY_")+"', '")
	    			.append(json.get(i).get("CREATED_")+"', '")
	    			.append(json.get(i).get("CREATEBY_")+"', '")
	    			.append(json.get(i).get("WRLXQT_"))
	    			.append("')");
	    			this.dao.exec(sql.toString());
		    	}
		    }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	@Override
	public DataCrudForm getUpdateTimeValue(String webUrl) {
		DataCrudForm date = new DataCrudForm();
		TSysUser cur = CtxUtil.getCurUser();
		String strAreaId = "";
		String strUpdated = "";
		if(StringUtils.isNotBlank(cur.getAreaId())){
			strAreaId = cur.getAreaId();
		}else{
			strAreaId = "1";
		}
		String link = webUrl+"services/btlxService";
		org.apache.axis.client.Service service=new  org.apache.axis.client.Service();
		Call call;
		if("a0000000000000000000000000000000".equals(cur.getId())){
			try {
				//服务器最近更新时间
				String hsql = "from TSysServer where isActive = 'Y' order by updated_ desc";
				List<TSysServer> da = this.dao.find(hsql);
				if(da.size() > 0){
					date.setFwqsj(String.valueOf(da.get(0).getUpdated()).substring(0, String.valueOf(da.get(0).getUpdated()).length()-2));
				    date.setFwqtb("Y");
				}else{
					date.setFwqsj("");
					date.setFwqtb("Y");
				}
				
				//区域最新更新时间
				String hsql1 = "from TSysArea where isActive = 'Y' order by updated_ desc";
				List<TSysArea> da1 = this.dao.find(hsql1);
				if(da1.size() > 0){
					date.setQysj(String.valueOf(da1.get(0).getUpdated()).substring(0, String.valueOf(da1.get(0).getUpdated()).length()-2));
				    date.setQytb("Y");
				}else{
					date.setQysj("");
					date.setQytb("Y");
				}
				
				//部门最新更新时间
				String hsql2 = "from TSysOrg where isActive = 'Y' order by updated_ desc";
				List<TSysOrg> da2 = this.dao.find(hsql2);
				if(da2.size() > 0){
					date.setOrgsj(String.valueOf(da2.get(0).getUpdated()).substring(0, String.valueOf(da2.get(0).getUpdated()).length()-2));
				    date.setOrgtb("Y");
				}else{
					date.setOrgsj("");
					date.setOrgtb("Y");
				}
				
				//角色最新更新时间
				String hsql3 = "from TSysRole where isActive = 'Y' order by updated_ desc";
				List<TSysRole> da3 = this.dao.find(hsql3);
				if(da3.size() > 0){
					date.setRolesj(String.valueOf(da3.get(0).getUpdated()).substring(0, String.valueOf(da3.get(0).getUpdated()).length()-2));
				    date.setRoletb("Y");
				}else{
					date.setRolesj("");
					date.setRoletb("Y");
				}
				
				//用户最新更新时间
				String hsql4 = "from TSysUser where isActive = 'Y' order by updated_ desc";
				List<TSysUser> da4 = this.dao.find(hsql4);
				if(da4.size() > 0){
					date.setUsersj(String.valueOf(da4.get(0).getUpdated()).substring(0, String.valueOf(da4.get(0).getUpdated()).length()-2));
					//关联表的时间显示为主表时间
					date.setUserrolesj(String.valueOf(da4.get(0).getUpdated()).substring(0, String.valueOf(da4.get(0).getUpdated()).length()-2));
				    date.setUsertb("Y");
				}else{
					date.setUsersj("");
					date.setUserrolesj("");//关联表的时间显示为主表时间
					date.setUsertb("Y");
				}
				
				//系统功能最新更新时间
				String hsql6 = "from TSysFunc where isActive = 'Y' order by updated_ desc";
				List<TSysFunc> da6 = this.dao.find(hsql6);
				if(da6.size() > 0){
					date.setFuncsj(String.valueOf(da6.get(0).getUpdated()).substring(0, String.valueOf(da6.get(0).getUpdated()).length()-2));
				    date.setFunctb("Y");
				}else{
					date.setFuncsj("");
					date.setFunctb("Y");
				}
				
				//系统功能对应操作最新更新时间
				String hsql7 = "from TSysFuncOper where isActive = 'Y' order by updated_ desc";
				List<TSysFuncOper> da7 = this.dao.find(hsql7);
				if(da7.size() > 0){
					date.setFuncopersj(String.valueOf(da7.get(0).getUpdated()).substring(0, String.valueOf(da7.get(0).getUpdated()).length()-2));
				    date.setFuncopertb("Y");
				}else{
					date.setFuncopersj("");
					date.setFuncopertb("Y");
				}
				
				//终端功能最新更新时间
				String hsql8 = "from TSysMobileFunc where isActive = 'Y' order by updated_ desc";
				List<TSysMobileFunc> da8 = this.dao.find(hsql8);
				if(da8.size() > 0){
					date.setMofuncsj(String.valueOf(da8.get(0).getUpdated()).substring(0, String.valueOf(da8.get(0).getUpdated()).length()-2));
				    date.setMofunctb("Y");
				}else{
					date.setMofuncsj("");
					date.setMofunctb("Y");
				}
				
				//字典最新更新时间
				String hsql9 = "from TSysDic where isActive = 'Y' order by updated_ desc";
				List<TSysDic> da9 = this.dao.find(hsql9);
				if(da9.size() > 0){
					date.setDicsj(String.valueOf(da9.get(0).getUpdated()).substring(0, String.valueOf(da9.get(0).getUpdated()).length()-2));
				    date.setDictb("Y");
				}else{
					date.setDicsj("");
					date.setDictb("Y");
				}
				//角色功能最新更新时间
				/*String hsql10 = "from TSysRoleFunc where isActive = 'Y' order by updated_";
				List<TSysRoleFunc> da10 = this.dao.find(hsql10);
				if(da10.size() > 0){
					date.put("rolefuncsj", String.valueOf(da10.get(0).getUpdated()).substring(0, String.valueOf(da10.get(0).getUpdated()).length()-2));
				}else{
					date.put("rolefuncsj", "");
				}*/
				//角色功能操作最新更新时间
				/*String hsql11 = "from TSysRoleFuncOper where isActive = 'Y' order by updated_";
				List<TSysRoleFuncOper> da11 = this.dao.find(hsql11);
				if(da11.size() > 0){
					date.put("rolefuncopersj", String.valueOf(da11.get(0).getUpdated()).substring(0, String.valueOf(da11.get(0).getUpdated()).length()-2));
				}else{
					date.put("rolefuncopersj", "");
				}*/
				//角色终端功能最新更新时间
				/*String hsql12 = "from TSysRoleMobileFunc where isActive = 'Y' order by updated_";
				List<TSysRoleMobileFunc> da12 = this.dao.find(hsql12);
				if(da12.size() > 0){
					date.put("rolemofuncsj", String.valueOf(da12.get(0).getUpdated()).substring(0, String.valueOf(da12.get(0).getUpdated()).length()-2));
				}else{
					date.put("rolemofuncsj", "");
				}*/
				//违法类型最新更新时间
				String hsql13 = "from TDataIllegaltype where isActive = 'Y' order by updated_ desc";
				List<TDataIllegaltype> da13 = this.dao.find(hsql13);
				if(da13.size() > 0){
					date.setWflxsj(String.valueOf(da13.get(0).getUpdated()).substring(0, String.valueOf(da13.get(0).getUpdated()).length()-2));
				    date.setWflxtb("Y");
				}else{
					date.setWflxsj("");
					date.setWflxtb("Y");
				}
				
				//任务类型最新更新时间
				String hsql14 = "from TDataTasktype where isActive = 'Y' order by updated_ desc";
				List<TDataTasktype> da14 = this.dao.find(hsql14);
				if(da14.size() > 0){
					date.setRwlxsj(String.valueOf(da14.get(0).getUpdated()).substring(0, String.valueOf(da14.get(0).getUpdated()).length()-2));
				    date.setRwlxtb("Y");
				}else{
					date.setRwlxsj("");
					date.setRwlxtb("Y");
				}
				
				//行业最新更新时间
				String hsql15 = "from TDataIndustry where isActive = 'Y' order by updated_ desc";
				List<TDataIndustry> da15 = this.dao.find(hsql15);
				if(da15.size() > 0){
					date.setHylxsj(String.valueOf(da15.get(0).getUpdated()).substring(0, String.valueOf(da15.get(0).getUpdated()).length()-2));
				    date.setHylxtb("Y");
				}else{
					date.setHylxsj("");
					date.setHylxtb("Y");
				}
				//检查单模板最新更新时间
				String hsql16 = "from TDataChecklistitem where isActive = 'Y' order by updated_ desc";
				List<TDataChecklistitem> da16 = this.dao.find(hsql16);
				if(da16.size() > 0){
					date.setJcdmbsj(String.valueOf(da16.get(0).getUpdated()).substring(0, String.valueOf(da16.get(0).getUpdated()).length()-2));
				    date.setJcdmbtb("Y");
				}else{
					date.setJcdmbsj("");
					date.setJcdmbtb("Y");
				}
				
				//勘察询问笔录最新更新时间
				String hsql17 = "from TDataRecord where isActive = 'Y' order by updated_ desc";
				List<TDataRecord> da17 = this.dao.find(hsql17);
				if(da17.size() > 0){
					date.setKcxwblsj(String.valueOf(da17.get(0).getUpdated()).substring(0, String.valueOf(da17.get(0).getUpdated()).length()-2));
				    date.setKcxwbltb("Y");
				}else{
					date.setKcxwblsj("");
					date.setKcxwbltb("Y");
				}
				
				//版本管理最新更新时间
				String hsql18 = "from TDataVersion where isActive = 'Y' order by updated_ desc";
				List<TDataVersion> da18 = this.dao.find(hsql18);
				if(da18.size() > 0){
					date.setBbglsj(String.valueOf(da18.get(0).getUpdated()).substring(0, String.valueOf(da18.get(0).getUpdated()).length()-2));
				    date.setBbgltb("Y");
				}else{
					date.setBbglsj("");
					date.setBbgltb("Y");
				}
				//设置检查模板最新更新时间
				/*String hsql19 = "from TDataLawobjtypetasktype where isActive = 'Y' order by updated_";
				List<TDataLawobjtypetasktype> da19 = this.dao.find(hsql19);
				if(da19.size() > 0){
					date.put("szjcmbsj", String.valueOf(da19.get(0).getUpdated()).substring(0, String.valueOf(da19.get(0).getUpdated()).length()-2));
				}else{
					date.put("szjcmbsj", "");
				}*/
				//所属行政区最新更新时间
				String hsql20 = "from TDataRegion where isActive = 'Y' order by updated_ desc";
				List<TDataRegion> da20 = this.dao.find(hsql20);
				if(da20.size() > 0){
					date.setSsxzqsj(String.valueOf(da20.get(0).getUpdated()).substring(0, String.valueOf(da20.get(0).getUpdated()).length()-2));
				    date.setSsxzqtb("Y");
				}else{
					date.setSsxzqsj("");
					date.setSsxzqtb("Y");
				}
				
				//施工单位最新更新时间  address(mysql 和 oracle字段有不同)
				String hsql21 = "from TDataSgdw where isActive = 'Y' order by updated_ desc";
				List<TDataSgdw> da21 = this.dao.find(hsql21);
				if(da21.size() > 0){
					date.setSgdwsj(String.valueOf(da21.get(0).getUpdateTime()).substring(0, String.valueOf(da21.get(0).getUpdateTime()).length()-2));
				    date.setSgdwtb("Y");
				}else{
					date.setSgdwsj("");
					date.setSgdwtb("Y");
				}
				//执法对象最新更新时间
				String hsql22 = "from TDataLawobj where isActive = 'Y' order by updated_ desc";
				List<TDataLawobj> da22 = this.dao.find(hsql22);
				if(da22.size() > 0){
					date.setZfdxsj(String.valueOf(da22.get(0).getUpdated()).substring(0, String.valueOf(da22.get(0).getUpdated()).length()-2));
				    date.setZfdxtb("Y");
				}else{
					date.setZfdxsj("");
					date.setZfdxtb("Y");
				}
				//建设项目环评最新更新时间
				String hsql23 = "from TDataLawobjeia where isActive = 'Y' order by updated_ desc";
				List<TDataLawobjeia> da23 = this.dao.find(hsql23);
				if(da23.size() > 0){
					date.setJsxmhpsj(String.valueOf(da23.get(0).getUpdated()).substring(0, String.valueOf(da23.get(0).getUpdated()).length()-2));
				    date.setJsxmhptb("Y");
				}else{
					date.setJsxmhpsj("");
					date.setJsxmhptb("Y");
				}
				//网格化责任人最新更新时间
				/*String hsql24 = "from TDataGridleader where isActive = 'Y' order by updated_";
				List<TDataGridleader> da24 = this.dao.find(hsql24);
				if(da24.size() > 0){
					date.put("wgrsj", String.valueOf(da24.get(0).getUpdated()).substring(0, String.valueOf(da24.get(0).getUpdated()).length()-2));
				}else{
					date.put("wgrsj", "");
				}*/
				//企业危化信息最新更新时间（pid mysql和oracle字段有区别）
				/*String hsql25 = "from TDataShjjbzk where isActive = 'Y' order by updatetime_";
				List<TDataShjjbzk> da25 = this.dao.find(hsql25);
				if(da25.size() > 0){
					date.put("qywhxxsj", String.valueOf(da25.get(0).getUpdateTime()).substring(0, String.valueOf(da25.get(0).getUpdateTime()).length()-2));
				}else{
					date.put("qywhxxsj", "");
				}*/
				//执法文件最新更新时间
				String hsql26 = "from TDataLawdoc where isActive = 'Y' order by updated_ desc";
				List<TDataLawdoc> da26 = this.dao.find(hsql26);
				if(da26.size() > 0){
					date.setZfwjsj(String.valueOf(da26.get(0).getUpdated()).substring(0, String.valueOf(da26.get(0).getUpdated()).length()-2));
					date.setZfwjtb("Y");
				}else{
					date.setZfwjsj("");
					date.setZfwjtb("Y");
				}
				//任务类型执法文件目录最新更新时间
				String hsql27 = "from TDataLawdocdir where isActive = 'Y' order by updated_ desc";
				List<TDataLawdocdir> da27 = this.dao.find(hsql27);
				if(da27.size() > 0){
					date.setZfwjmlsj(String.valueOf(da27.get(0).getUpdated()).substring(0, String.valueOf(da27.get(0).getUpdated()).length()-2));
				    date.setZfwjmltb("Y");
				}else{
					date.setZfwjmlsj("");
					date.setZfwjmltb("Y");
				}
				//自由裁量最新更新时间
				String hsql28 = "from TDataDiscreacts where isActive = 'Y' order by updated_ desc";
				List<TDataDiscreacts> da28 = this.dao.find(hsql28);
				if(da28.size() > 0){
					date.setZyclsj(String.valueOf(da28.get(0).getUpdated()).substring(0, String.valueOf(da28.get(0).getUpdated()).length()-2));
				    date.setZycltb("Y");
				}else{
					date.setZyclsj("");
					date.setZycltb("Y");
				}
				//信访登记表最新更新时间
				String hsql29 = "from TBizXfdj where isActive = 'Y' order by updated_ desc";
				List<TBizXfdj> da29 = this.dao.find(hsql29);
				if(da29.size() > 0){
					date.setXfdjbsj(String.valueOf(da29.get(0).getUpdated()).substring(0, String.valueOf(da29.get(0).getUpdated()).length()-2));
				    date.setXfdjbtb("Y");
				}else{
					date.setXfdjbsj("");
					date.setXfdjbtb("Y");
				}
			}catch (Exception e) {
		    	e.printStackTrace();
		    }
		}else{
			try {
				//服务器最近更新时间
				String hsql = "from TSysServer where isActive = 'Y' order by updated_ desc";
				List<TSysServer> da = this.dao.find(hsql);
				if(da.size() > 0){
					call = (Call) service.createCall();
					date.setFwqsj(String.valueOf(da.get(0).getUpdated()).substring(0, String.valueOf(da.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da.get(0).getUpdated()).substring(0, String.valueOf(da.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "serverIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setFwqtb("N");
				    }else{
				    	date.setFwqtb("Y");
				    }
				}else{
					date.setFwqsj("");
					date.setFwqtb("Y");
				}
				
				//区域最新更新时间
				String hsql1 = "from TSysArea where isActive = 'Y' order by updated_ desc";
				List<TSysArea> da1 = this.dao.find(hsql1);
				if(da1.size() > 0){
					call = (Call) service.createCall();
					date.setQysj(String.valueOf(da1.get(0).getUpdated()).substring(0, String.valueOf(da1.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da1.get(0).getUpdated()).substring(0, String.valueOf(da1.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "areaInfoIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setQytb("N");
				    }else{
				    	date.setQytb("Y");
				    }
				}else{
					date.setQysj("");
					date.setQytb("Y");
				}
				
				//部门最新更新时间
				String hsql2 = "from TSysOrg where isActive = 'Y' order by updated_ desc";
				List<TSysOrg> da2 = this.dao.find(hsql2);
				if(da2.size() > 0){
					call = (Call) service.createCall();
					date.setOrgsj(String.valueOf(da2.get(0).getUpdated()).substring(0, String.valueOf(da2.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da2.get(0).getUpdated()).substring(0, String.valueOf(da2.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "orgInfoIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setOrgtb("N");
				    }else{
				    	date.setOrgtb("Y");
				    }
				}else{
					date.setOrgsj("");
					date.setOrgtb("Y");
				}
				
				//角色最新更新时间
				String hsql3 = "from TSysRole where isActive = 'Y' order by updated_ desc";
				List<TSysRole> da3 = this.dao.find(hsql3);
				if(da3.size() > 0){
					call = (Call) service.createCall();
					date.setRolesj(String.valueOf(da3.get(0).getUpdated()).substring(0, String.valueOf(da3.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da3.get(0).getUpdated()).substring(0, String.valueOf(da3.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "roleInfoIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setRoletb("N");
				    }else{
				    	date.setRoletb("Y");
				    }
				}else{
					date.setRolesj("");
					date.setRoletb("Y");
				}
				
				//用户最新更新时间
				String hsql4 = "from TSysUser where isActive = 'Y' order by updated_ desc";
				List<TSysUser> da4 = this.dao.find(hsql4);
				if(da4.size() > 0){
					call = (Call) service.createCall();
					date.setUsersj(String.valueOf(da4.get(0).getUpdated()).substring(0, String.valueOf(da4.get(0).getUpdated()).length()-2));
					//关联表的时间显示为主表时间
					date.setUserrolesj(String.valueOf(da4.get(0).getUpdated()).substring(0, String.valueOf(da4.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da4.get(0).getUpdated()).substring(0, String.valueOf(da4.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "userInfoIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setUsertb("N");
				    }else{
				    	date.setUsertb("Y");
				    }
				}else{
					date.setUsersj("");
					date.setUserrolesj("");//关联表的时间显示为主表时间
					date.setUsertb("Y");
				}
				
				//系统功能最新更新时间
				String hsql6 = "from TSysFunc where isActive = 'Y' order by updated_ desc";
				List<TSysFunc> da6 = this.dao.find(hsql6);
				if(da6.size() > 0){
					call = (Call) service.createCall();
					date.setFuncsj(String.valueOf(da6.get(0).getUpdated()).substring(0, String.valueOf(da6.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da6.get(0).getUpdated()).substring(0, String.valueOf(da6.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "funcInfoIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setFunctb("N");
				    }else{
				    	date.setFunctb("Y");
				    }
				}else{
					date.setFuncsj("");
					date.setFunctb("Y");
				}
				
				//系统功能对应操作最新更新时间
				String hsql7 = "from TSysFuncOper where isActive = 'Y' order by updated_ desc";
				List<TSysFuncOper> da7 = this.dao.find(hsql7);
				if(da7.size() > 0){
					call = (Call) service.createCall();
					date.setFuncopersj(String.valueOf(da7.get(0).getUpdated()).substring(0, String.valueOf(da7.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da7.get(0).getUpdated()).substring(0, String.valueOf(da7.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "funcOperInfoIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setFuncopertb("N");
				    }else{
				    	date.setFuncopertb("Y");
				    }
				}else{
					date.setFuncopersj("");
					date.setFuncopertb("Y");
				}
				
				//字典最新更新时间
				String hsql9 = "from TSysDic where isActive = 'Y' order by updated_ desc";
				List<TSysDic> da9 = this.dao.find(hsql9);
				if(da9.size() > 0){
					call = (Call) service.createCall();
					date.setDicsj(String.valueOf(da9.get(0).getUpdated()).substring(0, String.valueOf(da9.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da9.get(0).getUpdated()).substring(0, String.valueOf(da9.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "dicDataIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setDictb("N");
				    }else{
				    	date.setDictb("Y");
				    }
				}else{
					date.setDicsj("");
					date.setDictb("Y");
				}
				//角色功能最新更新时间
				/*String hsql10 = "from TSysRoleFunc where isActive = 'Y' order by updated_";
				List<TSysRoleFunc> da10 = this.dao.find(hsql10);
				if(da10.size() > 0){
					date.put("rolefuncsj", String.valueOf(da10.get(0).getUpdated()).substring(0, String.valueOf(da10.get(0).getUpdated()).length()-2));
				}else{
					date.put("rolefuncsj", "");
				}*/
				//角色功能操作最新更新时间
				/*String hsql11 = "from TSysRoleFuncOper where isActive = 'Y' order by updated_";
				List<TSysRoleFuncOper> da11 = this.dao.find(hsql11);
				if(da11.size() > 0){
					date.put("rolefuncopersj", String.valueOf(da11.get(0).getUpdated()).substring(0, String.valueOf(da11.get(0).getUpdated()).length()-2));
				}else{
					date.put("rolefuncopersj", "");
				}*/
				//角色终端功能最新更新时间
				/*String hsql12 = "from TSysRoleMobileFunc where isActive = 'Y' order by updated_";
				List<TSysRoleMobileFunc> da12 = this.dao.find(hsql12);
				if(da12.size() > 0){
					date.put("rolemofuncsj", String.valueOf(da12.get(0).getUpdated()).substring(0, String.valueOf(da12.get(0).getUpdated()).length()-2));
				}else{
					date.put("rolemofuncsj", "");
				}*/
				//违法类型最新更新时间
				String hsql13 = "from TDataIllegaltype where isActive = 'Y' order by updated_ desc";
				List<TDataIllegaltype> da13 = this.dao.find(hsql13);
				if(da13.size() > 0){
					call = (Call) service.createCall();
					date.setWflxsj(String.valueOf(da13.get(0).getUpdated()).substring(0, String.valueOf(da13.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da13.get(0).getUpdated()).substring(0, String.valueOf(da13.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "wflxIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setWflxtb("N");
				    }else{
				    	date.setWflxtb("Y");
				    }
				}else{
					date.setWflxsj("");
					date.setWflxtb("Y");
				}
				
				//任务类型最新更新时间
				String hsql14 = "from TDataTasktype where isActive = 'Y' order by updated_ desc";
				List<TDataTasktype> da14 = this.dao.find(hsql14);
				if(da14.size() > 0){
					call = (Call) service.createCall();
					date.setRwlxsj(String.valueOf(da14.get(0).getUpdated()).substring(0, String.valueOf(da14.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da14.get(0).getUpdated()).substring(0, String.valueOf(da14.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "rwlxIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setRwlxtb("N");
				    }else{
				    	date.setRwlxtb("Y");
				    }
				}else{
					date.setRwlxsj("");
					date.setRwlxtb("Y");
				}
				
				//行业最新更新时间
				String hsql15 = "from TDataIndustry where isActive = 'Y' order by updated_ desc";
				List<TDataIndustry> da15 = this.dao.find(hsql15);
				if(da15.size() > 0){
					call = (Call) service.createCall();
					date.setHylxsj(String.valueOf(da15.get(0).getUpdated()).substring(0, String.valueOf(da15.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da15.get(0).getUpdated()).substring(0, String.valueOf(da15.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "hylxIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setHylxtb("N");
				    }else{
				    	date.setHylxtb("Y");
				    }
				}else{
					date.setHylxsj("");
					date.setHylxtb("Y");
				}
				//检查单模板最新更新时间
				String hsql16 = "from TDataChecklistitem where isActive = 'Y' order by updated_ desc";
				List<TDataChecklistitem> da16 = this.dao.find(hsql16);
				if(da16.size() > 0){
					call = (Call) service.createCall();
					date.setJcdmbsj(String.valueOf(da16.get(0).getUpdated()).substring(0, String.valueOf(da16.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da16.get(0).getUpdated()).substring(0, String.valueOf(da16.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "jcxIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setJcdmbtb("N");
				    }else{
				    	date.setJcdmbtb("Y");
				    }
				}else{
					date.setJcdmbsj("");
					date.setJcdmbtb("Y");
				}
				
				//勘察询问笔录最新更新时间
				String hsql17 = "from TDataRecord where isActive = 'Y' order by updated_ desc";
				List<TDataRecord> da17 = this.dao.find(hsql17);
				if(da17.size() > 0){
					call = (Call) service.createCall();
					date.setKcxwblsj(String.valueOf(da17.get(0).getUpdated()).substring(0, String.valueOf(da17.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da17.get(0).getUpdated()).substring(0, String.valueOf(da17.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "dataRecordIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setKcxwbltb("N");
				    }else{
				    	date.setKcxwbltb("Y");
				    }
				}else{
					date.setKcxwblsj("");
					date.setKcxwbltb("Y");
				}
				
				//版本管理最新更新时间
				String hsql18 = "from TDataVersion where isActive = 'Y' order by updated_ desc";
				List<TDataVersion> da18 = this.dao.find(hsql18);
				if(da18.size() > 0){
					call = (Call) service.createCall();
					date.setBbglsj(String.valueOf(da18.get(0).getUpdated()).substring(0, String.valueOf(da18.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da18.get(0).getUpdated()).substring(0, String.valueOf(da18.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "versionInfoIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setBbgltb("N");
				    }else{
				    	date.setBbgltb("Y");
				    }
				}else{
					date.setBbglsj("");
					date.setBbgltb("Y");
				}
				//设置检查模板最新更新时间
				/*String hsql19 = "from TDataLawobjtypetasktype where isActive = 'Y' order by updated_";
				List<TDataLawobjtypetasktype> da19 = this.dao.find(hsql19);
				if(da19.size() > 0){
					date.put("szjcmbsj", String.valueOf(da19.get(0).getUpdated()).substring(0, String.valueOf(da19.get(0).getUpdated()).length()-2));
				}else{
					date.put("szjcmbsj", "");
				}*/
				//所属行政区最新更新时间
				String hsql20 = "from TDataRegion where isActive = 'Y' order by updated_ desc";
				List<TDataRegion> da20 = this.dao.find(hsql20);
				if(da20.size() > 0){
					call = (Call) service.createCall();
					date.setSsxzqsj(String.valueOf(da20.get(0).getUpdated()).substring(0, String.valueOf(da20.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da20.get(0).getUpdated()).substring(0, String.valueOf(da20.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "xzqInfoIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setSsxzqtb("N");
				    }else{
				    	date.setSsxzqtb("Y");
				    }
				}else{
					date.setSsxzqsj("");
					date.setSsxzqtb("Y");
				}
				
				//施工单位最新更新时间  address(mysql 和 oracle字段有不同)
				String hsql21 = "from TDataSgdw where isActive = 'Y' order by updated_ desc";
				List<TDataSgdw> da21 = this.dao.find(hsql21);
				if(da21.size() > 0){
					call = (Call) service.createCall();
					date.setSgdwsj(String.valueOf(da21.get(0).getUpdateTime()).substring(0, String.valueOf(da21.get(0).getUpdateTime()).length()-2));
					strUpdated = String.valueOf(da21.get(0).getUpdateTime()).substring(0, String.valueOf(da21.get(0).getUpdateTime()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "sgdwInfoIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setSgdwtb("N");
				    }else{
				    	date.setSgdwtb("Y");
				    }
				}else{
					date.setSgdwsj("");
					date.setSgdwtb("Y");
				}
				//执法对象最新更新时间
				String hsql22 = "from TDataLawobj where isActive = 'Y' order by updated_ desc";
				List<TDataLawobj> da22 = this.dao.find(hsql22);
				if(da22.size() > 0){
					call = (Call) service.createCall();
					date.setZfdxsj(String.valueOf(da22.get(0).getUpdated()).substring(0, String.valueOf(da22.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da22.get(0).getUpdated()).substring(0, String.valueOf(da22.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "ZfdxDataIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setZfdxtb("N");
				    }else{
				    	date.setZfdxtb("Y");
				    }
				}else{
					date.setZfdxsj("");
					date.setZfdxtb("Y");
				}
				//建设项目环评最新更新时间
				String hsql23 = "from TDataLawobjeia where isActive = 'Y' order by updated_ desc";
				List<TDataLawobjeia> da23 = this.dao.find(hsql23);
				if(da23.size() > 0){
					call = (Call) service.createCall();
					date.setJsxmhpsj(String.valueOf(da23.get(0).getUpdated()).substring(0, String.valueOf(da23.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da23.get(0).getUpdated()).substring(0, String.valueOf(da23.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "HpInfoIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setJsxmhptb("N");
				    }else{
				    	date.setJsxmhptb("Y");
				    }
				}else{
					date.setJsxmhpsj("");
					date.setJsxmhptb("Y");
				}
				//网格化责任人最新更新时间
				/*String hsql24 = "from TDataGridleader where isActive = 'Y' order by updated_";
				List<TDataGridleader> da24 = this.dao.find(hsql24);
				if(da24.size() > 0){
					date.put("wgrsj", String.valueOf(da24.get(0).getUpdated()).substring(0, String.valueOf(da24.get(0).getUpdated()).length()-2));
				}else{
					date.put("wgrsj", "");
				}*/
				//企业危化信息最新更新时间（pid mysql和oracle字段有区别）
				/*String hsql25 = "from TDataQyhxwzqkyl where isActive = 'Y' order by updated_ desc";
				List<TDataQyhxwzqkyl> da25 = this.dao.find(hsql25);
				if(da25.size() > 0){
					call = (Call) service.createCall();
					date.setQywhxxsj(String.valueOf(da25.get(0).getUpdateTime()).substring(0, String.valueOf(da23.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da25.get(0).getUpdateTime()).substring(0, String.valueOf(da23.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "QywhInfoIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setQywhxxtb("N");
				    }else{
				    	date.setQywhxxtb("Y");
				    }
				}else{
					date.setQywhxxsj("");
					date.setQywhxxtb("Y");
				}*/
				//执法文件最新更新时间
				String hsql26 = "from TDataLawdoc where isActive = 'Y' order by updated_ desc";
				List<TDataLawdoc> da26 = this.dao.find(hsql26);
				if(da26.size() > 0){
					call = (Call) service.createCall();
					date.setZfwjsj(String.valueOf(da26.get(0).getUpdated()).substring(0, String.valueOf(da26.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da26.get(0).getUpdated()).substring(0, String.valueOf(da26.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "zfFileIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setZfwjtb("N");
				    }else{
				    	date.setZfwjtb("Y");
				    }
				}else{
					date.setZfwjsj("");
					date.setZfwjtb("Y");
				}
				//任务类型执法文件目录最新更新时间
				String hsql27 = "from TDataLawdocdir where isActive = 'Y' order by updated_ desc";
				List<TDataLawdocdir> da27 = this.dao.find(hsql27);
				if(da27.size() > 0){
					call = (Call) service.createCall();
					date.setZfwjmlsj(String.valueOf(da27.get(0).getUpdated()).substring(0, String.valueOf(da27.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da27.get(0).getUpdated()).substring(0, String.valueOf(da27.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "zfFileDirIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setZfwjmltb("N");
				    }else{
				    	date.setZfwjmltb("Y");
				    }
				}else{
					date.setZfwjmlsj("");
					date.setZfwjmltb("Y");
				}
				//自由裁量最新更新时间
				String hsql28 = "from TDataDiscreacts where isActive = 'Y' order by updated_ desc";
				List<TDataDiscreacts> da28 = this.dao.find(hsql28);
				if(da28.size() > 0){
					call = (Call) service.createCall();
					date.setZyclsj(String.valueOf(da28.get(0).getUpdated()).substring(0, String.valueOf(da28.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da28.get(0).getUpdated()).substring(0, String.valueOf(da28.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "zyclInfoListIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setZycltb("N");
				    }else{
				    	date.setZycltb("Y");
				    }
				}else{
					date.setZyclsj("");
					date.setZycltb("Y");
				}
				//信访登记表最新更新时间
				String hsql29 = "from TBizXfdj where isActive = 'Y' order by updated_ desc";
				List<TBizXfdj> da29 = this.dao.find(hsql29);
				if(da29.size() > 0){
					call = (Call) service.createCall();
					date.setXfdjbsj(String.valueOf(da29.get(0).getUpdated()).substring(0, String.valueOf(da29.get(0).getUpdated()).length()-2));
					strUpdated = String.valueOf(da29.get(0).getUpdated()).substring(0, String.valueOf(da29.get(0).getUpdated()).length()-2);
			    	//调用webservice服务的方法开始
					call.setOperationName(new QName(link, "xfdjbInfoListIsSynch"));//调用的方法名
		    		call.setTargetEndpointAddress(new URL(link));
		    		// 设置参数名:
		            call.addParameter("in0", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            call.addParameter("in1", XMLType.XSD_STRING, ParameterMode.IN);//参数类型:String 参数模式：'IN' or 'OUT'
		            // 设置返回值类型：
		            call.setReturnType(XMLType.XSD_STRING);
		            String params[] = new String[]{strAreaId, strUpdated};
		    		String data = (String) call.invoke(params);
		    		List<Map<String, String>> json = JsonResultUtil.jsonToList(data);
				    if(json.get(0).get("result").equals("N")){
				    	date.setXfdjbtb("N");
				    }else{
				    	date.setXfdjbtb("Y");
				    }
				}else{
					date.setXfdjbsj("");
					date.setXfdjbtb("Y");
				}
			}catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
		return date;
	}
	
	@Override
	public String queryUpdateddata(String type) {
		String strUpdated = "";
		try {
			if("01".equals(type)){
				//服务器最近更新时间
				String hsql = "from TSysServer where isActive = 'Y' order by updated_ desc";
				List<TSysServer> da = this.dao.find(hsql);
				if(da.size() > 0){
					strUpdated = String.valueOf(da.get(0).getUpdated()).substring(0, String.valueOf(da.get(0).getUpdated()).length()-2);
				}
			}
			else if("02".equals(type)){
				//区域最新更新时间
				String hsql = "from TSysArea where isActive = 'Y' order by updated_ desc";
				List<TSysArea> da = this.dao.find(hsql);
				if(da.size() > 0){
					strUpdated = String.valueOf(da.get(0).getUpdated()).substring(0, String.valueOf(da.get(0).getUpdated()).length()-2);
				}
			}
			else if("03".equals(type)){
				//部门最新更新时间
				String hsql2 = "from TSysOrg where isActive = 'Y' order by updated_ desc";
				List<TSysOrg> da2 = this.dao.find(hsql2);
				if(da2.size() > 0){
					strUpdated = String.valueOf(da2.get(0).getUpdated()).substring(0, String.valueOf(da2.get(0).getUpdated()).length()-2);
				}
			}
			else if("04".equals(type)){
				//角色最新更新时间
				String hsql3 = "from TSysRole where isActive = 'Y' order by updated_ desc";
				List<TSysRole> da3 = this.dao.find(hsql3);
				if(da3.size() > 0){
					strUpdated = String.valueOf(da3.get(0).getUpdated()).substring(0, String.valueOf(da3.get(0).getUpdated()).length()-2);
				}
			}
			else if("05".equals(type)){
				//用户最新更新时间
				String hsql4 = "from TSysUser where isActive = 'Y' order by updated_ desc";
				List<TSysUser> da4 = this.dao.find(hsql4);
				if(da4.size() > 0){
					strUpdated = String.valueOf(da4.get(0).getUpdated()).substring(0, String.valueOf(da4.get(0).getUpdated()).length()-2);
				}
			}
			else if("07".equals(type)){
				//系统功能最新更新时间
				String hsql6 = "from TSysFunc where isActive = 'Y' order by updated_ desc";
				List<TSysFunc> da6 = this.dao.find(hsql6);
				if(da6.size() > 0){
					strUpdated = String.valueOf(da6.get(0).getUpdated()).substring(0, String.valueOf(da6.get(0).getUpdated()).length()-2);
				}
			}
			/*else if("08".equals(type)){
				//系统功能对应操作最新更新时间
				String hsql7 = "from TSysFuncOper where isActive = 'Y' order by updated_ desc";
				List<TSysFuncOper> da7 = this.dao.find(hsql7);
				if(da7.size() > 0){
					strUpdated = String.valueOf(da7.get(0).getUpdated()).substring(0, String.valueOf(da7.get(0).getUpdated()).length()-2);
				}
			}*/
			else if("10".equals(type)){
				//字典最新更新时间
				String hsql9 = "from TSysDic where isActive = 'Y' order by updated_ desc";
				List<TSysDic> da9 = this.dao.find(hsql9);
				if(da9.size() > 0){
					strUpdated = String.valueOf(da9.get(0).getUpdated()).substring(0, String.valueOf(da9.get(0).getUpdated()).length()-2);
				}
			}
			//缺少11对应额角色功能，表里没有时间字段值
			else if("14".equals(type)){
				//违法类型最新更新时间
				String hsql13 = "from TDataIllegaltype where isActive = 'Y' order by updated_ desc";
				List<TDataIllegaltype> da13 = this.dao.find(hsql13);
				if(da13.size() > 0){
					strUpdated = String.valueOf(da13.get(0).getUpdated()).substring(0, String.valueOf(da13.get(0).getUpdated()).length()-2);
				}
			}
			else if("15".equals(type)){
				//任务类型最新更新时间
				String hsql14 = "from TDataTasktype where isActive = 'Y' order by updated_ desc";
				List<TDataTasktype> da14 = this.dao.find(hsql14);
				if(da14.size() > 0){
					strUpdated = String.valueOf(da14.get(0).getUpdated()).substring(0, String.valueOf(da14.get(0).getUpdated()).length()-2);
				}
			}
			else if("16".equals(type)){
				//行业最新更新时间
				String hsql15 = "from TDataIndustry where isActive = 'Y' order by updated_ desc";
				List<TDataIndustry> da15 = this.dao.find(hsql15);
				if(da15.size() > 0){
					strUpdated = String.valueOf(da15.get(0).getUpdated()).substring(0, String.valueOf(da15.get(0).getUpdated()).length()-2);
				}
			}
			else if("17".equals(type)){
				//检查单模板最新更新时间
				String hsql16 = "from TDataChecklistitem where isActive = 'Y' order by updated_ desc";
				List<TDataChecklistitem> da16 = this.dao.find(hsql16);
				if(da16.size() > 0){
					strUpdated = String.valueOf(da16.get(0).getUpdated()).substring(0, String.valueOf(da16.get(0).getUpdated()).length()-2);
				}
			}
			else if("18".equals(type)){
				//勘察询问笔录最新更新时间
				String hsql17 = "from TDataRecord where isActive = 'Y' order by updated_ desc";
				List<TDataRecord> da17 = this.dao.find(hsql17);
				if(da17.size() > 0){
					strUpdated = String.valueOf(da17.get(0).getUpdated()).substring(0, String.valueOf(da17.get(0).getUpdated()).length()-2);
				}
			}
			else if("19".equals(type)){
				//版本管理最新更新时间
				String hsql18 = "from TDataVersion where isActive = 'Y' order by updated_ desc";
				List<TDataVersion> da18 = this.dao.find(hsql18);
				if(da18.size() > 0){
					strUpdated = String.valueOf(da18.get(0).getUpdated()).substring(0, String.valueOf(da18.get(0).getUpdated()).length()-2);
				}
			}
			//缺少的20对应设置检查模板，表里没有时间字段值
			else if("21".equals(type)){
				//所属行政区最新更新时间
				String hsql20 = "from TDataRegion where isActive = 'Y' order by updated_ desc";
				List<TDataRegion> da20 = this.dao.find(hsql20);
				if(da20.size() > 0){
					strUpdated = String.valueOf(da20.get(0).getUpdated()).substring(0, String.valueOf(da20.get(0).getUpdated()).length()-2);
				}
			}
			else if("22".equals(type)){
				//施工单位最新更新时间  address(mysql 和 oracle字段有不同)
				String hsql21 = "from TDataSgdw where isActive = 'Y' order by updated_ desc";
				List<TDataSgdw> da21 = this.dao.find(hsql21);
				if(da21.size() > 0){
					strUpdated = String.valueOf(da21.get(0).getUpdateTime()).substring(0, String.valueOf(da21.get(0).getUpdateTime()).length()-2);
				}
			}
			else if("23".equals(type)){
				//执法对象最新更新时间
				String hsql22 = "from TDataLawobj where isActive = 'Y' order by updated_ desc";
				List<TDataLawobj> da22 = this.dao.find(hsql22);
				if(da22.size() > 0){
					strUpdated = String.valueOf(da22.get(0).getUpdated()).substring(0, String.valueOf(da22.get(0).getUpdated()).length()-2);
				}
			}
			else if("24".equals(type)){
				//建设项目环评最新更新时间
				String hsql23 = "from TDataLawobjeia where isActive = 'Y' order by updated_ desc";
				List<TDataLawobjeia> da23 = this.dao.find(hsql23);
				if(da23.size() > 0){
					strUpdated = String.valueOf(da23.get(0).getUpdated()).substring(0, String.valueOf(da23.get(0).getUpdated()).length()-2);
				}
			}
			else if("26".equals(type)){
				//企业危化表最新更新时间
				String hsql23 = "from TDataLawobjeia where isActive = 'Y' order by updated_ desc";
				List<TDataLawobjeia> da23 = this.dao.find(hsql23);
				if(da23.size() > 0){
					strUpdated = String.valueOf(da23.get(0).getUpdated()).substring(0, String.valueOf(da23.get(0).getUpdated()).length()-2);
				}
			}
			else if("27".equals(type)){
				//执法文件最新更新时间
				String hsql26 = "from TDataLawdoc where isActive = 'Y' order by updated_ desc";
				List<TDataLawdoc> da26 = this.dao.find(hsql26);
				if(da26.size() > 0){
					strUpdated = String.valueOf(da26.get(0).getUpdated()).substring(0, String.valueOf(da26.get(0).getUpdated()).length()-2);
				}
			}
			else if("28".equals(type)){
				//任务类型执法文件目录最新更新时间
				String hsql27 = "from TDataLawdocdir where isActive = 'Y' order by updated_ desc";
				List<TDataLawdocdir> da27 = this.dao.find(hsql27);
				if(da27.size() > 0){
					strUpdated = String.valueOf(da27.get(0).getUpdated()).substring(0, String.valueOf(da27.get(0).getUpdated()).length()-2);
				}
			}
			else if("29".equals(type)){
				//自由裁量最新更新时间
				String hsql28 = "from TDataDiscreacts where isActive = 'Y' order by updated_ desc";
				List<TDataDiscreacts> da28 = this.dao.find(hsql28);
				if(da28.size() > 0){
					strUpdated = String.valueOf(da28.get(0).getUpdated()).substring(0, String.valueOf(da28.get(0).getUpdated()).length()-2);
				}
			}
			else if("30".equals(type)){
				//信访登记表最新更新时间
				String hsql29 = "from TBizXfdj where isActive = 'Y' order by updated_ desc";
				List<TBizXfdj> da29 = this.dao.find(hsql29);
				if(da29.size() > 0){
					strUpdated = String.valueOf(da29.get(0).getUpdated()).substring(0, String.valueOf(da29.get(0).getUpdated()).length()-2);
				}
			}
			}catch (Exception e) {
		    	e.printStackTrace();
		    }
		return strUpdated;
	}
}
