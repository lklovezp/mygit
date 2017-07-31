package com.hnjz.app.work.wszz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysOrg;

public interface WszzManager extends Manager{
	/**
	 * 根据用户id获得用户所属部门
	 * */
	public TSysOrg getOrgbyUser(String userId)throws Exception;
	/**
	 * 根据用户id获得用户所属部门
	 * */
	public HjwfxwtzForm findHjwfxwtzFormById(String taskId,String taskTypeId)throws Exception;
	/**
	 * 根据用户id获得用户所属部门
	 * */
	public void saveHjwfxwtzFormById(HjwfxwtzForm hjwfxwtzForm)throws Exception;
	/**
	 * 生成环境违法行为限期改正通知单
	 * */
	public HashMap<String, String> buildTzd(HjwfxwtzForm hjwfxwtzForm)throws Exception;
	/**
	 * 生成行政处罚决定书送达回执单
	 * */
	public HashMap<String, String> buildJdssdhz(JdssdhzForm jdssdhzForm)throws Exception;
	/**
	 * 添加或修改行政处罚决定书送达回执单
	 * */
	public void saveJdssdhzFormById(JdssdhzForm jdssdhzForm)throws Exception;
	/**
	 * 查询行政处罚决定书送达回执
	 * */
	public JdssdhzForm findJdssdhzFormById(String taskId,String taskTypeId)throws Exception;
	/**
	 * 生成（听证）告知书送达回证单
	 * */
	public HashMap<String, String> buildTzgzssdhz(TzgzssdhzForm tzgzssdhzForm)throws Exception;
	/**
	 * 添加或修改（听证）告知书送达回证单
	 * */
	public void saveTzgzssdhzFormById(TzgzssdhzForm tzgzssdhzForm)throws Exception;
	/**
	 * 查询（听证）告知书送达回证
	 * */
	public TzgzssdhzForm findTzgzssdhzFormById(String taskId,String taskTypeId)throws Exception;
	
}
