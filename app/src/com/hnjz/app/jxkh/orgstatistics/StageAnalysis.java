package com.hnjz.app.jxkh.orgstatistics;

import com.hnjz.common.util.DateUtil;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-4-20
 * 功能描述：
		任务阶段分析
 *
 */
public class StageAnalysis {

	/** 阶段类型 ***/
	private String stageType;
	
	/** 操作人  ***/
	private String oprateUser;
	
	/** 开始时间  ***/
	private String starttime;
	
	/** 结束时间 ***/
	private String endtime;
	
	/** 用时 ***/
	private String usetime;
	
	/** 是否是用时最多阶段 **/
	private String isMaxUsertime;
	
	public StageAnalysis(){
		
	}
	
	public StageAnalysis(String stageType,String oprateUser,String starttime,String endtime){
		this.stageType = stageType;
		this.oprateUser = oprateUser;
		this.starttime = starttime;
		this.endtime = endtime;
		this.usetime = DateUtil.getTwoDateMinue(starttime, endtime);
	}
	
	public StageAnalysis(String stageType,String oprateUser,String starttime,String endtime,String isMaxUsertime){
		this.stageType = stageType;
		this.oprateUser = oprateUser;
		this.starttime = starttime;
		this.endtime = endtime;
		this.usetime = DateUtil.getTwoDateMinue(starttime, endtime);
		this.isMaxUsertime = isMaxUsertime;
	}

	public String getStageType() {
		return stageType;
	}

	public void setStageType(String stageType) {
		this.stageType = stageType;
	}

	public String getOprateUser() {
		return oprateUser;
	}

	public void setOprateUser(String oprateUser) {
		this.oprateUser = oprateUser;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getIsMaxUsertime() {
		return isMaxUsertime;
	}

	public void setIsMaxUsertime(String isMaxUsertime) {
		this.isMaxUsertime = isMaxUsertime;
	}

	public String getUsetime() {
		return usetime;
	}

	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}
	
	
	
	
}
