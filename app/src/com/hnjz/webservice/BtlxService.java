package com.hnjz.webservice;

import javax.activation.DataHandler;


/**
 * 
 * 作者：zhangqingfeng
 * 生成日期：2016-4-1
 * 功能描述：兵团离线接口
 *
 */
public interface BtlxService {

	/**
	 * 
	 * 函数介绍：数据字典是否同步
	 * 输入参数：区域id，更新日期
	 * 返回值：
	 */
	public String dicDataIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：获取字典数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getDicDataList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：区域服务器是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String areaInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：同步区域信息
	 * 输入参数：
	 * 返回值：
	 */
	public String getAreaInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：执法对象所属行政区是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String xzqInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：执法对象所属行政区同步数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getXzqInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：用户信息是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String userInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：获取用户信息
	 * 输入参数：
	 * 返回值：
	 */
	public String getUserInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍： 同步部门信息
	 * 输入参数：
	 * 返回值：
	 */
	public String getOrgInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：部门信息是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String orgInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：任务类型是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String rwlxIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：同步任务类型信息
	 * 输入参数：
	 * 返回值：
	 */
	public String getRwlxList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：行业类型
	 * 输入参数：
	 * 返回值：
	 */
	public String hylxIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：同步行业类型
	 * 输入参数：
	 * 返回值：
	 */
	public String getHylxList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：违法类型
	 * 输入参数：
	 * 返回值：
	 */
	public String wflxIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：同步违法类型
	 * 输入参数：
	 * 返回值：
	 */
	public String getWflxList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：环评信息是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String hpInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：同步环评信息数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getHpInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：附件信息是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String fjxxInfoIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：附件列表
	 * 输入参数：
	 * 返回值：
	 */
	public String getFjxxInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：通过文件id，下载文件
	 * 输入参数：fileid：文件id
	 * 返回值：
	 */
	public DataHandler downFile(String fileid);

	/**
	 * 
	 * 函数介绍：执法文件目录是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String zfFileDirIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：执法文件是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String zfFileIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：执法文件同步数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getZfFileList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：勘查询问笔录问题项是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String dataRecordIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：勘查询问笔录问题项同步数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getDataRecordList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：检查记录模版是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String jcjlmbIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：检查记录模版同步数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getJcjlmbList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：检查项是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String jcxIsSynch(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：检查项同步数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String getJcxList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：检查项答案是否同步
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String jcxdaIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：检查项答案同步数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String getJcxDaList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：获取需要更新的安装包
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String getUpdateFile();
	
	/**
	 * 
	 * 函数介绍：自由裁量权裁标准是否同步
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String zyclInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：自由裁量权裁标准同步数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String getZyclInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：获取所有接口的同步状态
	
	 * 输入参数：含有“ID、AREAID、UPDATED”列表的json字符串；
	
	 * 返回值：ID、XXZT列表组成的json字符串
	 */
	public String getJcxxTBZT(String strRequestJson);

	/**
	 * 
	 * 函数介绍：获得最新版本号
	
	 * 输入参数：
	
	 * 返回值：
	 */
    public String getMaxVersionCode();
    
    /**
	 * 
	 * 函数介绍：获取服务器数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getServDataList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：获取角色数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getRoleDataList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：角色信息是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String roleInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：系统功能是否同步
	 * 输入参数：区域id，更新日期
	 * 返回值：
	 */
	public String funcInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：获取功能数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getFuncInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：功能操作是否同步
	 * 输入参数：区域id，更新日期
	 * 返回值：
	 */
	public String funcOperInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：获取角色功能的数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getRoleFuncInfoList(String strAreaId, String strUpdated);

	/**
	 * 
	 * 函数介绍：版本号数据是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String versionInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：获取版本号的数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getVersionInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：获取版本号的数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getJcmbszInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：施工单位数据是否同步
	 * 输入参数：
	 * 返回值：
	 */
	public String sgdwInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：获取施工单位的数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getSgdwInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：获取网格化责任人的数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getWghzrrInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：获取任务类型执法文件目录的数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getDirTasktypeInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：执法对象数据是否同步
	 * 输入参数：区域id，更新日期
	 * 返回值：
	 */
	public String ZfdxDataIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：获取执法对象的数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getZfdxDataList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：获取企业危化信息的数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getQywhInfoList(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：服务器数据是否同步
	 * 输入参数：区域id，更新日期
	 * 返回值：
	 */
	public String serverIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：环评数据是否同步
	 * 输入参数：区域id，更新日期
	 * 返回值：
	 */
	public String HpInfoIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：自由裁量数据是否同步
	 * 输入参数：区域id，更新日期
	 * 返回值：
	 */
	public String zyclInfoListIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：信访登记表数据是否同步
	 * 输入参数：区域id，更新日期
	 * 返回值：
	 */
	public String xfdjbInfoListIsSynch(String strAreaId, String strUpdated);
	
	/**
	 * 
	 * 函数介绍：获取信访登记表信息的数据
	 * 输入参数：
	 * 返回值：
	 */
	public String getXfdjbInfoList(String strAreaId, String strUpdated);
}
