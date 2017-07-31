package com.hnjz.app.data.xxgl.fsaq;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.manager.Manager;

public interface FsaqManager extends Manager {
	/**
	 * ��ѯ���䰲ȫ������Ϣ
	 * */
	public FsaqForm queryFsaqForm(String lawobjId,String id,String lawobjTypeId)throws Exception;
	/**
	 * ������䰲ȫ������Ϣ
	 * */
	public void saveFsaqForm(FsaqForm fsaqForm)throws Exception;
	/**
	 * ���ɷ��䰲ȫ������Ϣword���������ݿ�
	 * */
	public HashMap<String, String> createFsaqWord(HttpServletResponse response,String lawobjId,String lawobjType,String biaoshi,String appleId)throws Exception;

}