package com.hnjz.app.data.xxgl.lawobj;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.app.data.po.TDataLawobjeia;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-3
 * 功能描述：
	执法对象Manager接口
 *
 */
public interface LawobjManager  extends Manager {

	/**
	 * 
	 * 函数介绍：执法对象综合信息列表
	 
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
	public FyWebResult queryLawobjList(String name, String lawobjType, String regionId, String orgId, String curPage, String pageSize) throws Exception;
	/**
	 * 
	 * 函数介绍：工业污染源列表
	
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param year:年份，年度抽查时抽查年份
	 * @param regionCode:所属行政区编码
	 * @param orgId:所属监管部门编码
	 * @param qyzt:企业状态
	 * @param kzlx:控制类型
	 * @param isActive:是否有效
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryGywryList(String year,String quarter,String name, String regionId, String orgId, String qyzt,String kzlx, String isActive, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：保存或更新工业企业污染源
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public TDataLawobj saveOrUpdateLawobj(TDataLawobj lawobj, String jsxmid) throws Exception;

	public void saveUploadFile(MultipartFile file, String fileType);

	/**
	 * 
	 * 函数介绍：获取工业污染源详情
	
	 * 输入参数：jsxmid:如果是建设项目转过来的，更新建设项目对应的执法对象id
	
	 * 返回值：
	 */
	public TDataLawobj getLawobjInfo(TDataLawobj lawobj);

	/**
	 * 
	 * 函数介绍：执法对象置为无效（通用方法）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void removeLawobj(String id);

	/**
	 * 
	 * 函数介绍：建设项目列表
	
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param year:年份，年度抽查时抽查年份
	 * @param regionCode:所属行政区编码
	 * @param orgId:所属监管部门编码
	 * @param kzlx:控制类型
	 * @param isActive:是否有效
	 * @param isChoose:是否是选择执法对象(Y/N)
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryJsxmList(String year,String quarter,String name, String regionId, String orgId, String lawobjId, String industryId, String isActive, String isChoose, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：保存环评信息
	
	 * 输入参数：hpxxForm：环评信息表单对象
	
	 * 返回值：
	 */
	public TDataLawobjeia saveOrUpdateHpxx(HpxxForm hpxxForm);

	/**
	 * 
	 * 函数介绍：环评信息列表
	
	 * 输入参数：pid：执法对象id，curPage:当前页 pageSize：每页显示条数
	
	 * 返回值：
	 */
	public FyWebResult queryHpxxList(String pid, String curPage, String pageSize);

	/**
	 * 
	 * 函数介绍：获得环评信息详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public HpxxForm getHpxxInfo(HpxxForm hpxxForm);

	/**
	 * 
	 * 函数介绍：删除环评信息
	
	 * 输入参数：环评id
	
	 * 返回值：
	 */
	public void removeHpxx(String id);

	/**
	 * 
	 * 函数介绍：医院信息列表
	
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param year:年份，年度抽查时抽查年份
	 * @param regionId:所属行政区编码
	 * @param orgId:所属监管部门编码
	 * @param qyzt:企业状态
	 * @param isActive:是否有效
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryYyxxList(String year,String quarter,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：锅炉信息列表
	
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param year:年份，年度抽查时抽查年份
	 * @param regionId:所属行政区编码
	 * @param orgId:所属监管部门编码
	 * @param qyzt:企业状态
	 * @param isActive:是否有效
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryGlxxList(String year,String quarter,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：建筑工地信息列表
	
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param year:年份，年度抽查时抽查年份
	 * @param regionId:所属行政区编码
	 * @param orgId:所属监管部门编码
	 * @param qyzt:企业状态
	 * @param isActive:是否有效
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryJzgdList(String year,String quarter,String name, String regionId,String orgId,String qyzt, String isActive, String sgdwmc, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：三产信息列表
	
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param year:年份，年度抽查时抽查年份
	 * @param regionId:所属行政区编码
	 * @param orgId:所属监管部门编码
	 * @param qyzt:企业状态
	 * @param isActive:是否有效
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryScxxList(String year,String quarter,String name, String regionId, String orgId,String qyzt,String industryId, String isActive, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：服务业信息列表
	
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param year:年份，年度抽查时抽查年份
	 * @param orgId:所属监管部门编码
	 * @param qyzt:企业状态
	 * @param isActive:是否有效
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryFwyList(String year,String quarter,String name, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception;
	
	
	/**
	 * 
	 * 函数介绍：制造业信息列表
	
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param year:年份，年度抽查时抽查年份
	 * @param orgId:所属监管部门编码
	 * @param qyzt:企业状态
	 * @param isActive:是否有效
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryZzyList(String year,String quarter,String name, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * 函数介绍：娱乐业信息列表
	
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param year:年份，年度抽查时抽查年份
	 * @param orgId:所属监管部门编码
	 * @param qyzt:企业状态
	 * @param isActive:是否有效
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryYlyList(String year,String quarter,String name,  String orgId,String qyzt, String isActive, String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * 函数介绍：饮食业信息列表
	
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param year:年份，年度抽查时抽查年份
	 * @param orgId:所属监管部门编码
	 * @param qyzt:企业状态
	 * @param isActive:是否有效
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryYsyList(String year,String quarter,String name,String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * 函数介绍：畜禽养殖信息列表
	
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param year:年份，年度抽查时抽查年份
	 * @param regionId:所属行政区编码
	 * @param orgId:所属监管部门编码
	 * @param qyzt:企业状态
	 * @param isActive:是否有效
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryXqyzList(String year,String quarter,String name, String regionId, String orgId,String qyzt,String isActive, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：生成工业污染源的编辑界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public Map<String, StringBuffer> lawobjEditInnerHtml(List<TDataLawobjdic> list, TDataLawobj lawobj);

	/**
	 * 
	 * 函数介绍：通过对外名称 获取对应的执法对象列名
	
	 * 输入参数：lawobjtypeid执法对象类型  enumname：对外名称枚举编号
	
	 * 返回值：
	 */
	public String getColumnNameByEnumname(String enumname);

	/**
	 * 
	 * 函数介绍：自动生成 污染源详情基本信息界面 表格数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String lawobjDetailInnerHtml(List<TDataLawobjdic> list, TDataLawobj lawobj);

	/**
	 * 
	 * 函数介绍：获取建设项目转其他执法对象的公共属性
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public JsxmForm getJsxmInfo(String id) throws Exception;

	/**
	 * 
	 * 函数介绍：保存建设项目对应的执法对象id
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveJsxmLawobjId(String jsxmid, TDataLawobj lawobj);

	/**
	 * 
	 * 函数介绍：根据枚举值查询对应的列名
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<String> queryLawobjColumnByEnmu(String lawobjType, String... args);

	/**
	 * 
	 * 函数介绍：通过枚举值获得执法对象某个字段值
	
	 * 输入参数：
	 * @param enumCode:枚举值编号
	 * @param lawobjid:执法对象id
	
	 * 返回值：
	 */
	public String getLawobjColumnValue(String enumCode, String lawobjid);

	/**
	 * 
	 * 函数介绍：详情界面html帮助方法 （获得有字段对用的值）数据库存的为id的获得name，显示到界面上
	
	 * 输入参数：执法对象字典po，执法对象po
	
	 * 返回值：
	 */
	public String detailInnerHtmlHelp(TDataLawobjdic tDataLawobjdic, TDataLawobj lawobj);
	
	/**
	 * 
	 * 函数介绍：判断执法对象名称是否重复(重复返回true)
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public Boolean checkLawobjname(TDataLawobj lawobj);

	/**
	 * 
	 * 函数介绍：查询执法历史记录
	
	 * 输入参数：执法对象id
	
	 * 返回值：
	 */
	public FyWebResult queryLawHistoryList(String id, String page, String pageSize);
	/**
	 * 
	 * @param lawobjtypeid 执法类型Id
	 * @param lawobjId	执法对象id
	 * @param code	创建人字段编码
	 * @param userId 创建人id
	 * @throws Exception
	 */
	public void transferCjr(String lawobjtypeid, String lawobjId,String code,String userId)  throws Exception;
	
	/**
	 * 今年没有被抽中的污染源列表
	 */
	public List<TDataLawobj> queryNoCheckedList(String year) throws Exception;
	
	/**
	 * 所有重点污染源列表
	 */
	public List<TDataLawobj> queryAllKeyLawobjList() throws Exception;
	
	/**
	 * 所有非重点污染源列表
	 */
	public List<TDataLawobj> queryNoKeyLawobjList() throws Exception;
	
	/**
	 * 根据污染源得到污染源所属监管部门id
	 * @throws Exception 
	 */
	public String getOrgidByLawobj(TDataLawobj lawobj) throws Exception;
	
	/**
	 * 根据污染源得到污染源名称
	 * @throws Exception 
	 */
	public String getNameByLawobj(TDataLawobj lawobj) throws Exception;
	
	/**
	 * 根据执法对象得到执法对象生产状态
	 * @throws Exception 
	 */
	public String getScztByLawobj(TDataLawobj lawobj) throws Exception;
	
	/**
	 * 根据污染源得到污染源的控制类型
	 * @throws Exception 
	 */
	public String getKzlxByLawobj(TDataLawobj lawobj) throws Exception;
	
	/**
	 * 查询所有违法被调查的企业列表
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryIllegalLawobjList(String year) throws ParseException, Exception;
	
	/**
	 * 查询所有一般企业列表(所有非重点工业污染源和所有其他类型企业)
	 * @return
	 * @throws Exception
	 */
	public List<TDataLawobj> queryAllNormalList() throws Exception;
	/**
	 * 根据列名获得执法对象该列的值
	 */
	public String getValueByColumnName(TDataLawobj lawobj,String columnName);
	
	/**
	 * 根据污染源得到污染源所属行政区
	 * @throws Exception 
	 */
	public String getRegionIdByLawobj(TDataLawobj lawobj) throws Exception;
	

	/**
	 * 根据企业类型获取企业对应的字典值
	 * t_data_lawobjdic
	 * @throws Exception 
	 */
	public Map<String, String> getLawobjByType(String type) throws Exception;
	
	/**
	 * 导出执法对象信息
	 * @param name
	 * @param lawobjType
	 * @param regionId
	 * @param orgId
	 * @param res
	 * @throws Exception 
	 */
	public void exportLawobjList(String name,String lawobjType,String regionId, String regionName, String orgId,String orgName,HttpServletResponse res) throws Exception;
	
}
