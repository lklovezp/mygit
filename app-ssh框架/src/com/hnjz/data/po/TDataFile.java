package com.hnjz.data.po;

import java.util.Date;

import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.FileUtil;
import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysUser;

/**
 * T_DATA_FILE 实体类
 * 作者：时秋寒
 * 功能描述：附件（允许通过http URL访问）
 */ 

@SuppressWarnings("serial")
public class TDataFile extends BaseObject {
	/** 业务标识 */
	private String pid;
	/** 磁盘上显示的名称（32位字符） */
	private String osname;
	/** 真实文件名称（含扩展名） */
	private String name;
	/** 扩展名（包括“.",如.docx） */
	private String extension;
	/** 大小（字节） */
	private Long size;
	/** 附件类型 */
	private String type;
	/** 扩展信息存储json数组 */
	private String extinfo;
	/** 相对路径 **/
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

