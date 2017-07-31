package com.hnjz.app.data.xxgl.tslawobj;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hnjz.app.data.po.TBizConfigcheck;
import com.hnjz.app.data.po.TBizConfigpf;
import com.hnjz.app.data.po.TBizConfigpfsj;
import com.hnjz.app.data.po.TBizHistoryconfigcheck;
import com.hnjz.app.data.po.TBizYearLawobj;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataSpeciallawobj;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.configCheckProportion.QuarterEnum;
import com.hnjz.sys.po.TBizCheckedLawobj;
import com.hnjz.sys.po.TDataQuarterChecktimeSet;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

public interface TslawobjManager extends Manager {
	/**
	 * 保存特殊监管企业信息
	 * */
	public void saveSpecialLawobj(String ids,String names,String year,String quarter)throws Exception;
	/**
	 * 根据
	 * 年份，季度，执法对象名称，执法对象类型
	 * 
	 * 查询特殊监管企业
	 * */
	public FyWebResult querySpecialLawobj(String year,String quarter, String lawobjname, String lawobjtype,  String page, String pageSize)
			throws Exception ;
	/**
	 * 删除特殊监管企业
	 * @param id
	 * @throws AppException
	 */
	public void removeSpecialLawobj(String id) throws Exception;
	/**
	 * 保存随机抽查比例设定
	 * @param configCheckForm
	 * @throws Exception
	 */
	public void configCheckSave(ConfigCheckForm configCheckForm,String areaid)throws Exception;
	/**
	 * 查询随机抽查比例设定信息
	 */
    public ConfigCheckForm queryConfigCheck(String areaid)throws Exception;
    /**
	 * 根据year 和quarter 查询出该季度的设置比例
	 */
    public ConfigPfForm queryConfigPfForm(String year,String quarter,String areaid)throws Exception;
    /**
	 * 保存派发设置比例
	 */
    public void configPfSave(ConfigPfForm configPfForm,String areaid)throws Exception;
    
    /**
   	 * 保存特殊企业派发设置比例
   	 */
    public void specliallConfigPfSave(ConfigPfForm configPfForm,String areaid)throws Exception;
    
    
    // 返回第几个月份，不是几月  
    // 季度一年四季， 第一季度：2月-4月， 第二季度：5月-7月， 第三季度：8月-10月， 第四季度：11月
    public int getQuarterInMonth(int month, boolean isQuarterStart);
    //根据year 和quarter 查询出随机抽查规则
    public TBizConfigcheck queryTbizConfigCheck(String areaid)throws Exception;
    
    //查询没有选中的重点企业
    public List<TDataLawobj> queryNoCheckedKeyLawobj(String year,String quarter,String type,String areaid)
    		throws Exception;
    //查询没有选中的一般企业
    public List<TDataLawobj> queryNoCheckedLawobj(String year,String quarter,String type,String areaid)
    		throws Exception;

    /**
	 * 开始抽选(季度)
	 * 
	 * @param year
	 *            年份
	 * @param quarter
	 *            季度
	 * @return 抽选结果         
	 * @throws Exception
	 */
    public String saveStartQuarterCheck(String year, String quarter,String areaid,TDataQuarterChecktimeSet ele)throws Exception;
    public String saveStartQuarterChecknew(String year, String quarter, String areaid,TDataQuarterChecktimeSet ele)
			throws Exception;
    //查询所有特殊监管的企业
    public List<TDataLawobj> querySpecialLawobj(String year,String quarter,String areaid)
    		throws Exception;
    public List<TDataLawobjf> queryNoCheckedKeyLawobjnew(String year, String quarter,
			String type, String areaid) throws Exception;
    //按年份和季度查询出当前年份和当前季度的派发时间设置
    public List<TBizConfigpfsj> quarterConfigpfsj()throws Exception;
     
    //抽取和派发
    public String saveCheckedandPf(TBizConfigpfsj tBizConfigpfsj)throws Exception;
    /**
	 * 所有重点污染源列表
	 * @throws Exception 
	 */
    public List<TDataLawobj> queryAllKeyLawobjListByAreaId(String areaId) throws Exception;
    
    public List<TDataLawobjf> queryAllKeyLawobjListByAreaIdnew(String areaId) throws Exception;
    /**
	 * 所有非重点污染源列表
	 * @throws Exception 
	 */
    public List<TDataLawobj> queryNoKeyLawobjListByAreaId(String areaid) throws Exception;
    /**
	 * 按年和月份查询出被选中的企业，然后按照配发比例选择派发
	 * @throws Exception 
	 */
    
    public List<TBizCheckedLawobj> executePfLawobj(String year,String month,String areaid,TBizConfigpfsj tBizConfigpfsj)throws Exception;
    /**
     * 创建任务，随机任务派发到企业所属监管部门领导 
     * */
    public String saveCreateWorkToLeader(String year, String quarter,String month,String areaid,TBizConfigpfsj tBizConfigpfsj,String type)throws Exception;
    
    /**
     * 创建任务，随机任务派发到个人接受状态
     * */
    public String saveCreateWorkToUser(String year, String quarter,String month,String areaid,TBizConfigpfsj tBizConfigpfsj,String type)throws Exception;
    
    
    /**
     * 创建任务，随机任务派发到个人以及选择协办人接受状态
     * */
    public String saveCreateWorkToUserAndXbr(String year, String quarter,String areaid,TBizConfigpfsj tBizConfigpfsj)throws Exception;
    
    /**
     * 随机抽查查询统计List<CheckedCountForm>
     * */
    public List<CheckedCountForm> queryCheckedCountFormList(String year,String quarter,String month,String areaid)throws Exception;
    /**
     * 随机抽查查询统计CheckedCountForm
     * */
    public CheckedCountForm queryCheckedCountForm(String year,String quarter, String month,String areaid,String danwei) throws Exception;
    /**
     * 导出随机抽查情况统计表
     * */
    public void exportCheckedCountForm(String year, String quarter,String areaid, HttpServletResponse res)throws Exception;
    /**
     * 根据year 和quarter 查询配发设置最低比例
     * */
    public String queryLowPfbl(String year, String quarter,String areaid)throws Exception;
    /**
	 * 保存随机抽查历史比例设定
     * @return 
	 * @throws Exception
	 */
	public String saveHistoryConfigCheck(String year,String quarter,TBizConfigcheck tc)throws Exception;
	/**
	 * 根据区域和year，month去查询抽选条件
	 * @throws Exception
	 */
	public TBizHistoryconfigcheck queryTBizHistoryconfigcheck(String year,String month,String areaid)throws Exception;
	  /**
		 * 根据year 和quarter type month查询出该季度的设置比例
		 */
	//public ConfigPfForm queryConfigPfForm(String year,String quarter,String type,String month,String areaid)throws Exception;
	  /**
		* 根据year ,quarter,month, type,areaid 查询出该季度的设置比例
		*/
    public ConfigPfForm specliallQueryConfigPfForm(String year,String quarter, String month,String type,String areaid)throws Exception;
    
    /**
	* 根据year,month,type,areaid区域查询出该季度的设置比例
	*/
    public ConfigPfForm queryConfigPfFormByType(String year,String month,String type,String areaid)throws Exception;
    
    
    /**
	* 根据季度查询出本季度的月份
	*/
    
    public  List<Combobox> queryMonthByQuarter(String quarter)throws Exception; 
    /**
     * 创建特殊企业派发任务，随机任务派发到个人接受状态
     * */
    //public String saveCreateSpecialWorkToUser(String year, String quarter,String areaid,TBizConfigpfsj tBizConfigpfsj)throws Exception;
    /**
     * 通过id找到TbizConfigpf
     * */
    public TBizConfigpf queryTbizConfigpfById(String id);
    
  //查询本次要派发的所有特殊监管的企业
    public List<TBizCheckedLawobj> querySpecialLawobjByPfbl(String year,String quarter,String month,String areaid,TBizConfigpfsj tBizConfigpfsj)throws Exception;

    /**
     * 将上一年度违法的企业添加到特殊监管企业里面
     * */
    public void saveForYeartoSpecail(String year)throws Exception;
    

    /**
	 * 根据
	 * 年份
	 * 
	 * 查询上年度的特殊监管企业
	 * */
	public List<TDataSpeciallawobj> queryForSpecialLawobj(String year)throws Exception ;
	/**
	 * 根据企业areaid, quarter,year,查询出被选择的企业信息
	 * 
	 * */
	public FyWebResult queryCountLawObjList(String areaid,String year,String quarter,String type, String page, String pageSize)throws Exception;
	/**
	 * 根据企业areaid, quarter,year,查询出被选择的企业信息
	 * 
	 * */
	public FyWebResult queryCountWFLawObjList(String areaid,String year,String quarter,String type, String page, String pageSize)throws Exception;
	/**
	 * 根据企业areaid, 查询出在编在岗的所有人员数
	 * 
	 * */
	public List<TSysUser> queryUserByAreaidAndIsZaiBianZaiGang(String areaid)throws Exception;
	/**
     * 创建任务，随机任务派发到部门个人以及选择协办人接受状态
     * */
    public String saveCreateWorkToOrgUser(String year, String quarter,String month,String areaid,TBizConfigpfsj tBizConfigpfsj,String type)throws Exception;
    /**
	 * 
	 * 函数介绍：随机抽查对象综合信息列表
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param lawobjType:执法对象类型
	 * @param regionId:所属行政区编码
	 * @param orgId:所属监管部门id
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	 * @return
	 * @throws Exception
	 */
	public FyWebResult querySjccLawobjList(String name, String lawobjType, String regionId, String orgId, String curPage, String pageSize) throws Exception;
	/**
	 * 导出执法对象样本信息
	 * @param name
	 * @param lawobjType
	 * @param regionId
	 * @param orgId
	 * @param res
	 * @throws Exception 
	 */
	public void exportYbLawobjList(String name,String lawobjType,String regionId, String regionName, String orgId,String orgName,HttpServletResponse res) throws Exception;
}
