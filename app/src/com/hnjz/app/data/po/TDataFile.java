package com.hnjz.app.data.po;

import java.util.Date;

import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.FileUtil;
import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysUser;

/**
 * T_DATA_FILE ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:04 CST 2015
 * ��������������������ͨ��http URL���ʣ�
 */ 

@SuppressWarnings("serial")
public class TDataFile extends BaseObject {
	/** ҵ���ʶ */
	private String pid;
	/** ��������ʾ�����ƣ�32λ�ַ��� */
	private String osname;
	/** ��ʵ�ļ����ƣ�����չ���� */
	private String name;
	/** ��չ����������.",��.docx�� */
	private String extension;
	/** ��С���ֽڣ� */
	private Long size;
	/** �������� */
	private String type;
	/** ��չ��Ϣ�洢json���� */
	private String extinfo;
	/** ���·�� **/
	private String relativepath;
	
	public TDataFile(){
		
	}
	
	public TDataFile(String pid,String osname,String name,Long size,String type,String relativepath,TSysUser user){
		this.pid = pid;
		this.osname = osname;
		this.name = name;
		this.extension = FileUtil.getExtensionName(name);
		this.size = size;
		this.type = type;
		this.relativepath = relativepath;
		this.setIsActive("Y");
		this.setUpdateby(user);
		this.setUpdated(new Date());
		this.setCreateby(user);
		this.setCreated(new Date());
	}

	public TDataFile(String pid,String osname,String name,Long size,String type,String relativepath){
		this.pid = pid;
		this.osname = osname;
		this.name = name;
		this.extension = FileUtil.getExtensionName(name);
		this.size = size;
		this.type = type;
		this.relativepath = relativepath;
		this.setIsActive("Y");
		TSysUser user = CtxUtil.getCurUser();
		this.setUpdateby(user);
		this.setUpdated(new Date());
		this.setCreateby(user);
		this.setCreated(new Date());
	}
	
	public void setPid(String pid){
		this.pid = pid;
	}
	public String getPid(){
		return pid;
	}
	public void setOsname(String osname){
		this.osname = osname;
	}
	public String getOsname(){
		return osname;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setExtension(String extension){
		this.extension = extension;
	}
	public String getExtension(){
		return extension;
	}
	public void setSize(Long size){
		this.size = size;
	}
	public Long getSize(){
		return size;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
	public void setExtinfo(String extinfo){
		this.extinfo = extinfo;
	}
	public String getExtinfo(){
		return extinfo;
	}
	public String getRelativepath() {
		return relativepath;
	}
	public void setRelativepath(String relativepath) {
		this.relativepath = relativepath;
	}
}
