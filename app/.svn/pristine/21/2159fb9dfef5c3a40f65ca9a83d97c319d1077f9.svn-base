package com.hnjz.app.data.xxgl.fsaq;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.manager.Manager;

public interface FsaqManager extends Manager {
	/**
	 * 查询辐射安全基本信息
	 * */
	public FsaqForm queryFsaqForm(String lawobjId,String id,String lawobjTypeId)throws Exception;
	/**
	 * 保存辐射安全基本信息
	 * */
	public void saveFsaqForm(FsaqForm fsaqForm)throws Exception;
	/**
	 * 生成辐射安全基本信息word并保存数据库
	 * */
	public HashMap<String, String> createFsaqWord(HttpServletResponse response,String lawobjId,String lawobjType,String biaoshi,String appleId)throws Exception;

}
