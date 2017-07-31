package com.hnjz.app.work.zx;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.app.work.beans.ResultBean;
import com.hnjz.app.work.po.TBizBlmbcs;
import com.hnjz.app.work.po.TBizMoreCheck;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysUser;

/**
 * 任务执行RwglManager
 * 作者：xb
 * 生成日期：Mar 9, 2015
 * 功能描述：
 *
 */
public interface CommWorkManager extends Manager{
	/**
     * 
     * 函数介绍：
    清执法对象
     * 输入参数：
    
     * 返回值：
     */
	public void saveDelZFDX(String applyId);
	/**
     * 
     * 函数介绍：
    清任务类型
     * 输入参数：
    
     * 返回值：
     */
	public void saveDelRWLX(String applyId);
	/**
     * 
     * 函数介绍：
    清执法对象类型
     * 输入参数：
    
     * 返回值：
     */
	public void saveDelZFDXLX(String applyId);
	/**
     * 
     * 函数介绍：
    清办理页面以及子任务
     * 输入参数：
    
     * 返回值：
     */
	public void saveDelBL(String applyId);
	
	/**
	 * 
	 * 函数介绍：执法对象类型保存
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveZfdxType(String applyId,String zfdxType) throws AppException;
	
	/**
	 * 
	 * 函数介绍：执法对象类型保存
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveZfdxTypeOnChange(String applyId,String zfdxType,String rwlxIds) throws AppException;
	
	/**
	 * 
	 * 函数介绍：保存任务类型
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveTaskTypeMultiple(String applyId,String checkedNodesIds,TSysUser curUser) throws AppException;
	
	/**
	 * 
	 * 函数介绍：保存任务类型
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveTaskTypeMultiple(String applyId,String checkedNodesIds,TSysUser curUser,Map<String,String> jcjlMap,Map<String,String> rcbgDescMap) throws AppException;
	
	/**
	 * 
	 * 函数介绍：根据任务id获取任务类型列表（准备）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Map<String, String>> getTaskTypeByTaskId(String applyId);
	
	/**
	 * 
	 * 函数介绍：执法对象table（准备）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Map<String, String>> zfdxTableData(String applyId);
	
	/**
	 * 
	 * 函数介绍：人员规划table（准备）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Map<String, String>> ryghTableData(String applyId);
	/**
	 * 
	 * 函数介绍：协办人员table
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Map<String, String>> xbryTableData(String applyId);
	/**
	 * 
	 * 函数介绍：人员规划-删除
	注：如果删除的人员后面用到了，则把办理过程中保存的检查人记录人也删掉。
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveDelrygh(String applyId,String id) throws AppException;
	
	/**
	 * 
	 * 函数介绍：人员规划下拉框（办理）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Combobox> ryghCombo(String applyId);
	
	/**
	 * 
	 * 函数介绍：人员保存
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveRy(String applyId,String ryid,String type) throws AppException;
	/**
	 * 
	 * 函数介绍：人员保存(多个)
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveRyMul(String applyId,String ryid,String type) throws AppException;
	/**
	 * 
	 * 函数介绍：
	协办人员保存(多个)终端。先清“协办人”再保存。
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveRyMulXbrForMobile(String applyId,String ryid,String type) throws AppException;
	
	/**
	 * 
	 * 函数介绍：任务类型下拉树（根据任务的执法对象类型+执法对象个数来决定任务类型选择）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<ComboboxTree> taskTypeTreeComboByTaskId(String applyId,String zfdxlx);
	
	/**
	 * 
	 * 函数介绍：任务类型下拉树过滤掉信访投诉（根据任务的执法对象类型+执法对象个数来决定任务类型选择）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<ComboboxTree> taskTypeTreeComboByTaskIdWithoutXf(String applyId,String zfdxlx);
	
	/**
	 * 
	 * 函数介绍：监察模板列表（如果有行业industryId，则返回该行业对应模板；如果没有行业，则返回该任务类型下所有模板列表）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<TDataChecklisttemplate> getTempList(String taskType,String industryId);
	
	
	/**
	 * 
	 * 获取办理主窗口数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public BlMainForm getBlMainFormData(String applyId);
	
	/**
	 * 获取信访办结单数据
	 * @param applyId
	 * @return
	 */
	public XfbjdForm getXfbjdform(String applyId);
	
	/**
	 * 
	 * 函数介绍：保存办理
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String saveWorkzxBL(String applyId,BlMainForm blMainForm);
	
	/**
	 * 
	 * 函数介绍：验证办理页面跳转【true、专项；false、其他】
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public ResultBean showBlPage(String applyId);
	
	/**
	 * 
	 * 函数介绍：获取勘察询问笔录
	
	 * 输入参数：applyId:任务id;bllx:笔录类型;
	
	 * 返回值：
	 */
	public Map<String, String> getKcxwblFile(String applyId,String bllx);
	
	/**
	 * 
	 * 函数介绍：通用获取单附件信息
	
	 * 输入参数：applyId:任务id;fileTypeCode:文件类型;
	
	 * 返回值：
	 */
	public Map<String, String> getCommonFile(String applyId,String fileTypeEnumName);
	
	
	/**
	 * 
	 * 函数介绍：验证"准备"【true、通过；false、不通过】
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public ResultBean checkBlZB(String applyId);
	/**
	 * 
	 * 函数介绍：验证"办理"【true、通过；false、不通过】
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public ResultBean checkBlBL(String applyId,String zfdxInfo);
	
	/**
	 * 
	 * 函数介绍：获取任务办理附件类型下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	public List<Combobox> queryBlFileTypeCombo(String rwlx,String zfdxInfo);
	
	/**
	 * 
	 * 函数介绍：获取任务办理附件类型下拉列表（专项行动子任务）
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	public List<Combobox> queryBlFileTypeComboZXXDZRW();
	
	/**
	 * 
	 * 函数介绍：保存单机执法信息
	
	 * 输入参数：applyId-任务id，file-单机压缩包
	
	 * 返回值：
	 * @throws AppException 
	 */
	public void saveDjMessage(String applyId,MultipartFile file) throws AppException;
	
	/**
	 * 
	 * 函数介绍：获取任务报告后第一个审核人
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String getFirstShrName(String applyId);
	
	/**
	 * 
	 * 函数介绍：任务上报的接口，处理上报任务类型保存+附件保存
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveSbRwlxAndFile(String sjid,String sbAreaId,String rwlxIds,List<Map<String, String>> sbFileInfo,List<Map<String, String>> bLspsInfo,Map<String,String> jcjlMap,Map<String,String> rcbgDescMap) throws AppException;
	
	public void saveJcjl(String taskid, String tasktype, String jcjl) throws Exception;
	
	public String jcjl(String taskid, String tasktype);
	
	public void saveDownJcbgmb(String jcmbId, String applyId, String taskType, HttpServletResponse res);
	/**
	 * 调出上次监察结论
	 * @param applyId 任务id
	 * @param taskType 任务类型id
	 * @return
	 * @throws AppException
	 */
	public String getJcjl(String applyId, String taskType);
	
	/**
	 * 根据文件id得到多次检查
	 * @param fileId
	 * @return
	 */
	public TBizMoreCheck getMoreCheck(String fileId);
	
	/**
	 * 根据文件id得到在线制作记录
	 * @param fileId
	 * @return
	 */
	public TBizBlmbcs getBlmbc(String fileId);
	
	/**
	 * 根据任务和任务类型查询当前第几次检查
	 * @param applyId
	 * @param taskType
	 * @return
	 */
	public int getMaxTimes(String applyId,String taskType);
	
	/**
	 * 根据id删除多次检查
	 * @param id
	 */
	public void removeMoreCheck(TBizMoreCheck mc);
	
	/**
	 * 根据id删除在线制作记录
	 * @param bc
	 */
	public void removeBlmbc(TBizBlmbcs bc);
	
	/**
	 * 查询某个任务，某种任务类型下的所有多次检查监察笔录
	 * @param applyId
	 * @param taskType
	 * @return
	 */
	public List<TBizMoreCheck> getMoreCheckList(String applyId,String taskType);
	
	/**
	 * 查询某个任务，某种任务类型下的所有多次检查监察笔录对应的文件，并按监察次数排序
	 * @param pid
	 * @param canDel
	 * @param fileType
	 * @param page
	 * @param rows
	 * @return
	 */
	public FyWebResult queryJcblFileList(String pid, String canDel,String fileType,String page, String rows);
	/**
	 * 保存日常办公备注说明
	 * */
	public void saveRcbg(String taskid, String tasktype, String desc) throws Exception;
	/**
	 * 保存日常办公备注说明
	 * */
	public BlMainForm FindRcbg(String taskid, String tasktype) throws Exception;
	/**
	 * 获得日常办公备注说明
	 * */
	public String getRcbgDesc(String applyId, String taskType);
	/**
	 * 获取除主办人外的所有人员信息
	 * @param applyId
	 * @return
	 */
	public List<Map<String, String>> ryData(String applyId);
	/**
	 * 手机端获取任务类型接口
	 */
	public List<Map<String, String>> getTaskTypeByTaskId();
}
