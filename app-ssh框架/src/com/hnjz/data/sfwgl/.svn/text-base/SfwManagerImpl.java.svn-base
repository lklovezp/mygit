/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.data.sfwgl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.data.enums.SfwlxEnum;
import com.hnjz.data.po.TDataFileInfo;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysUser;

/**
 * 收发文管理的Manager
 * @author 时秋寒
 *
 */
@Service("sfwManagerImpl")
public class SfwManagerImpl extends ManagerImpl implements SfwManager {

	/** 日志 */
	private static final Log log = LogFactory.getLog(SfwManagerImpl.class);
	
	@Autowired
	private DicManager dicManager;

	/**
	 * 查询收发文列表
	 * 
	 * @param name
	 *            搜索条件，可以按名称，备注搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 收发文列表
	 * @throws Exception
	 */
	public FyWebResult querySfw(String title, String starttime,String endtime, String type,String sourcepid,String sourceid,String number, String isActive, String page, String pageSize)
			throws Exception {
		QueryCondition data = new QueryCondition();
		if(pageSize==null || pageSize==""){
			pageSize="10";
		}
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataFileInfo where 1=1 ");
		//文件标题
		if (StringUtils.isNotBlank(title)) {
			sql.append(" and (title like :title or number like :title)");
			data.putLike("title", title);
		}
		//日期
		if (StringUtils.isNotBlank(starttime)){
			Date date1 = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",starttime+" 00:00:00");
			sql.append(" and sfwdate >= :starttime ");
			data.put("starttime", date1);
		}
		if (StringUtils.isNotBlank(endtime)){
			Date date2 = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",endtime+" 00:00:00");
			sql.append(" and sfwdate <= :endtime ");
			data.put("endtime", date2);
		}
		//收文/发文
		if (StringUtils.isNotBlank(type)) {
			sql.append(" and type = :type ");
			data.put("type", type);
		}
		//文档分类
		if (StringUtils.isNotBlank(sourcepid)) {
			sql.append(" and sourcepid = :sourcepid ");
			data.put("sourcepid", sourcepid);
		}
		//文档子类型
		if (StringUtils.isNotBlank(sourceid)) {
			sql.append(" and sourceid = :sourceid ");
			data.put("sourceid", sourceid);
		}
		//文号
		/*if (StringUtils.isNotBlank(number)) {
			sql.append(" and number like :number ");
			data.put("number", number);
		}*/
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", isActive);
		} else {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		sql.append(" order by sfwdate desc");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TDataFileInfo> files = pos.getRe();
		Map<String, String> row = null;
		for (TDataFileInfo ele : files) {
			row = new HashMap<String, String>();
			row.put("id", ele.getId());
			row.put("number", ele.getNumber()); //文号
			row.put("title", ele.getTitle());  //文件标题
			if(SfwlxEnum.getByCode(ele.getType())!=null){
				row.put("type", SfwlxEnum.getByCode(ele.getType()).getName());//收文/发文
			}else{
				row.put("type", null);//收文/发文
			}
			//文档分类
			if(StringUtils.isNotBlank(ele.getSourcepid())){
				TSysDic dic = (TSysDic) dicManager.get(TSysDic.class, ele.getSourcepid());
				if(dic!=null){
					row.put("sourcepname", dic.getName());//文档分类
				}else{
					row.put("sourcepname", null);//文档分类
				}
			}else{
				row.put("sourcepname", null);//文档分类
			}
			//文档子类型
			if(StringUtils.isNotBlank(ele.getSourceid())){
				TSysDic dic = (TSysDic) dicManager.get(TSysDic.class, ele.getSourceid());
				if(dic!=null){
					row.put("sourcename", dic.getName());//文档子类型
				}else{
					row.put("sourcename", null);//文档子类型
				}
			}else{
				row.put("sourcename", null);//文档子类型
			}
			row.put("sfwdate", DateUtil.getDate(ele.getSfwdate()));
			row.put("autograph", ele.getAutograph());
			row.put("isActive", YnEnum.getNote(ele.getIsActive()));
			//operate暂时先自己写，回头替换成OperateUtil.getOperate(ele.getId())
            StringBuilder operate = new StringBuilder();
            operate.append(" <a onclick='info(this)' style='color:#0088cc;cursor:pointer;' id='"+ele.getId()+"' >查看</a>  ");
            if("a0000000000000000000000000000000".equals(CtxUtil.getCurUser().getId()) || "Y".equals(CtxUtil.getCurUser().getIssys())){
            	operate.append(" <a onclick='edit(this)' style='color:#0088cc;cursor:pointer;' id='"+ele.getId()+"' >修改</a>  ");
                operate.append(" <a onclick='del(this)' style='color:#0088cc;cursor:pointer;' id='"+ele.getId()+"' >删除</a>  ");
            }
            row.put("operate", operate.toString());
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}

	/**
	 * 保存一个收发文信息
	 * 
	 * @param sfwForm
	 *            {@link SfwForm}
	 * @throws ParseException 
	 */
	@Transactional(readOnly = false)
	public TDataFileInfo saveSfw(SfwForm frm) throws AppException, ParseException {
		TDataFileInfo po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		if (StringUtils.isNotBlank(frm.getId())) {
			po = (TDataFileInfo) this.get(TDataFileInfo.class, frm.getId());
		} else {
			po = new TDataFileInfo();
			po.setCreateby(user);
			po.setFilecreateby(user);
			po.setCreated(cur);
		}
		po.setType(frm.getType());//收文/发文
		po.setTitle(frm.getTitle()); //文件标题
		po.setAutograph(frm.getAutograph()); //签名
		po.setNumber(frm.getNumber()); //文号
		po.setPosition(frm.getPosition()); //存放位置
		po.setSourcepid(frm.getSourcepid());//文档分类
		po.setSourceid(frm.getSourceid()); //文档子类型
		po.setCode(frm.getCode());
		po.setYear(frm.getYear());
		if(StringUtils.isNotBlank(frm.getSfwdate())){
			po.setSfwdate(DateUtil.convertStringToDate("yyyy-MM-dd",frm.getSfwdate()));  //日期
		}else{
			po.setSfwdate(null);  //日期
		}
		
		po.setIsActive(YnEnum.Y.getCode());
		po.setUpdateby(user);
		po.setUpdated(cur);
		this.dao.save(po);
		return po;
	}

	/**
	 * 删除收发文信息
	 * 
	 * @param id
	 *            收发文信息的ID
	 */
	public void removeSfw(String id) throws AppException {
		TDataFileInfo del = (TDataFileInfo) this.dao.get(TDataFileInfo.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}
	
	/**
	 * 函数介绍：收发文类型下拉列表
	 */
	public List<Combobox> querySfwlxList(){
		return SfwlxEnum.getSfwlxEnumList();
	}

}
