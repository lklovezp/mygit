package com.hnjz.webservice;

/**
 * 
 * 
 * 作者：renzhengquan
 * 生成日期：2015-4-30
 * 功能描述：
		兵团单机同步状态接口对应的枚举
 *
 */
public enum BtdjSychEnum {
	dicDataIsSynch("01","dicDataIsSynch"),
	dataRecordIsSynch("03","dataRecordIsSynch"),
	userInfoIsSynch("05","userInfoIsSynch"),
	zfFileIsSynch("06","zfFileIsSynch"),
	jcxIsSynch("07","jcxIsSynch"),
	rwlxIsSynch("09","rwlxIsSynch"),
	hylxIsSynch("10","hylxIsSynch"),
	rwlxWflxIsSynch("11","rwlxWflxIsSynch"),
	jcjlmbIsSynch("13","jcjlmbIsSynch"),
	wflxIsSynch("16","wflxIsSynch"),
	hpInfoIsSynch("17","hpInfoIsSynch"),
	areaInfoIsSynch("19","areaInfoIsSynch"),
	xzqInfoIsSynch("21","xzqInfoIsSynch"),
	zfdxInfoIsSynch("22","zfdxInfoIsSynch"),
	zyclInfoIsSynch("23","zyclInfoIsSynch"),
	zfdxRwlxIsSynch("24","zfdxRwlxIsSynch"),
	fjxxInfoIsSynch("25","fjxxInfoIsSynch"),
	;
	private String id;
	/** 接口名称 **/
	private String methodName;
	
	private BtdjSychEnum(){
		
	}
	
	private BtdjSychEnum(String id,String methodName){
		this.id = id;
		this.methodName = methodName;
	}

	/**
	 * 
	 * 函数介绍：通过id获得方法名称
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public static String getMethodNameById(String id){
		for(BtdjSychEnum btdjSychEnum : values()){
			if(btdjSychEnum.getId().equals(id)){
				return btdjSychEnum.getMethodName();
			}
		}
		return "";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
}
