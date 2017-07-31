/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.security;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hnjz.common.YnEnum;
import com.hnjz.common.security.domain.Oper;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 获取当前功能所具有的操作
 * 
 * @author wumi
 * @version $Id: OperateUtil.java, v 0.1 Jan 14, 2013 11:28:38 AM wumi Exp $
 */
public class OperateUtil {

    /**日志*/
    private static final Log   log = LogFactory.getLog(OperateUtil.class);

    static SecurityData securityData;

    /**
     * 获取当前功能所具有的操作
     * 
     * @param id 记录id
     * @return 当前功能所具有的操作
     */
    public static String getOperate(String id) {
        String fid = AppCtxStrategy.getFid();
        TSysUser u = CtxUtil.getCurUser();
        List<Oper> opers = securityData.getOper(u.getId(), fid);
        if (null == opers) {
            return "";
        }
        //不同角色的有重复的功能操作权限时候过滤掉重复的
        for(int i=0; i < opers.size();i++){
        	for(int j=opers.size()-1;j > i;j--){
				if((opers.get(i).getFunction()).equals(opers.get(j).getFunction())){
					opers.remove(j);
                 }
        	}
        }
        StringBuilder str = new StringBuilder();
        String temp = " <a onclick='function' id='poid' class='b-link'>operate</a>  ";
        for (Oper ele : opers) {
            if (StringUtils.equals(YnEnum.N.getCode(), ele.getFashion())
                || StringUtils.isBlank(ele.getFunction())) {
                continue;
            }
            String op = temp.replace("function", ele.getFunction());
            op = op.replace("poid", id);
            op = op.replace("operate", ele.getName());
            str.append(op);
            
        }
        if (log.isDebugEnabled()) {
            log.debug("getOperate:" + str);
        }
       
        return str.toString();
    }

    /**
    **
    * 获取当前功能所具有的操作
    * 
    * @param id 记录id
    * @return 当前功能所具有的操作
    */
    public static String getDloadOperate(String id) {
        StringBuilder str = new StringBuilder();
        String temp = " <a onclick='function' id='poid' class='b-link' >operate</a>  ";
        String op = temp.replace("function", "download1(this)");
        op = op.replace("poid", id);
        op = op.replace("operate", "下载");
        str.append(op);
        return str.toString();
    }
    /**
     **
     * 获取当前功能所具有的操作
     * 
     * @param id 记录id
     * @return 当前功能所具有的操作
     */
    public static String getDeleteOperate(String id) {
    	StringBuilder str = new StringBuilder();
    	String temp = " <a onclick='function' id='poid' class='b-link'>operate</a>  ";
    	String op = temp.replace("function", "deletefile1(this)");
    	op = op.replace("poid", id);
    	op = op.replace("operate", "删除");
    	str.append(op);
    	return str.toString();
    }
    /**
     **
     * 获取当前功能所具有的操作
     * 
     * @param id 记录id
     * @return 当前功能所具有的操作
     */
    public static String getDeleteOperate(String id,String tableId) {
    	StringBuilder str = new StringBuilder();
    	String temp = " <a onclick='function' id='poid' reloadtable='tableId' class='b-link'>operate</a>  ";
    	String op = temp.replace("function", "deletefile1(this)");
    	op = op.replace("poid", id);
    	if(StringUtil.isNotBlank(tableId)){
    		op = op.replace("tableId", tableId);
    	}
    	op = op.replace("operate", "删除");
    	str.append(op);
    	return str.toString();
    }
    /**
     **
     * 获取当前功能所具有的操作
     * 
     * @param id 记录id
     * @return 当前功能所具有的操作
     */
    public static String getEditOperate(String id) {
    	StringBuilder str = new StringBuilder();
    	String temp = " <a onclick='function' id='poid' class='b-link'>operate</a>  ";
    	String op = temp.replace("function", "edit(this)");
    	op = op.replace("poid", id);
    	op = op.replace("operate", "编辑");
    	str.append(op);
    	return str.toString();
    }
}
