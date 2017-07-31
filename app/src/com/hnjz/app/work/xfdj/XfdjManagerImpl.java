/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.work.xfdj;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;

import com.hnjz.IndexManager;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.po.TBizXfbjd;
import com.hnjz.app.work.po.TBizXfdj;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.app.work.zx.XfbjdForm;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.PoiUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserOrg;

/**
 * 信访登记管理类
 * @author shiqiuhan
 * @created 2016-3-31,上午08:56:39
 */
@Service("xfdjManagerImpl")
public class XfdjManagerImpl extends ManagerImpl implements XfdjManager,ServletContextAware {
	
	private ServletContext sc;
	
	@Autowired
	private AreaManager areaManager;
	
	@Autowired
	private DicManager dicManager;
	
	@Autowired
	private IndexManager     indexManager;
	
	@Autowired
	private CommWorkManager commWorkManager;
	
	/**
	 * 信访登记查询
	 * @param name
	 * @param isActive
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public FyWebResult queryXfdj(String xfly, String xfsj1, String xfsj2, String zt, String page, String pageSize)
			throws Exception {
		//添加离线版的标识判断（可以查询对应的sql语句）
		String biaoshi = indexManager.sysVer;
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TBizXfdj where isActive='Y'");
		//当前用户所在的区域限制	area
		sql.append(" and area.id = :areaId");
		data.put("areaId",CtxUtil.getAreaId());
		
		//信访来源
		if (StringUtils.isNotBlank(xfly)) {
			sql.append(" and xfly = :xfly ");
			data.put("xfly", xfly);
		}
		//信访时间
        if(StringUtils.isNotBlank(xfsj1)){
        	if("0".equals(biaoshi)){
        		sql.append(" and xfsj >= '"+xfsj1+"'");
        	}else{
        		sql.append(" and xfsj >= TO_DATE(:xfsj1,'yyyy-MM-dd hh24:mi:ss')");
    			data.put("xfsj1", xfsj1+" 00:00:00");
        	}
		}
		if(StringUtils.isNotBlank(xfsj2)){
			if("0".equals(biaoshi)){
				sql.append(" and xfsj <= '"+xfsj2+"'");
			}else{
				sql.append(" and xfsj <= TO_DATE(:xfsj2,'yyyy-MM-dd hh24:mi:ss')");
				data.put("xfsj2", xfsj2+" 23:59:59");
			}
		}
		sql.append(" order by xfbh desc");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TBizXfdj> xfdjs = pos.getRe();
		Map<String, String> row = null;
		for (TBizXfdj ele : xfdjs) {
			row = new HashMap<String, String>();
			row.put("id", ele.getId());
			if(StringUtils.isNotBlank(ele.getXfly())){
				row.put("xfly",dicManager.getNameByTypeAndCode("20", ele.getXfly()));
			}
			if(ele.getXfnr() != null && ele.getXfnr().length() > 32){
				row.put("xfnr", ele.getXfnr().substring(0, 31)+"...");
			}else{
				row.put("xfnr", ele.getXfnr());
			}
			row.put("xfbh", ele.getXfbh());
			String temp="";
			if(StringUtils.isNotBlank(ele.getWrlx())){
				String [] wrlxs = ele.getWrlx().split(",");
				for(int i=0;i<wrlxs.length;i++){
					TSysDic dic = (TSysDic) dicManager.get(TSysDic.class, wrlxs[i]);
					if(dic!=null){
						if("2104".equals(dic.getCode())){
							if(StringUtils.isNotBlank(ele.getSwrqt())){
								temp=temp+"水污染其他("+ele.getSwrqt()+"),";
							}
						}
						else if("2205".equals(dic.getCode())){
							if(StringUtils.isNotBlank(ele.getDqwrqt())){
								temp=temp+"大气污染其他("+ele.getDqwrqt()+"),";
							}
						}
						else if("2304".equals(dic.getCode())){
							if(StringUtils.isNotBlank(ele.getZswrqt())){
								temp=temp+"噪声污染其他("+ele.getZswrqt()+"),";
							}
						}
						else if("2406".equals(dic.getCode())){
							if(StringUtils.isNotBlank(ele.getGfwrqt())){
								temp=temp+"固废污染其他("+ele.getGfwrqt()+"),";
							}
						}
						else if("2503".equals(dic.getCode())){
							if(StringUtils.isNotBlank(ele.getFswrqt())){
								temp=temp+"辐射污染其他("+ele.getFswrqt()+"),";
							}
						}
						else{
							temp=temp+dic.getName()+",";
						}
					}
				}
				if(StringUtils.isNotBlank(temp)){
					temp = temp.substring(0, temp.length()-1);
				}
				if(StringUtils.isNotBlank(ele.getWrlxqt())){
					row.put("wrlx",temp+",其他污染("+ele.getWrlxqt()+")");
				}else{
					row.put("wrlx",temp);
				}
			}else{
				if(StringUtils.isNotBlank(ele.getWrlxqt())){
					row.put("wrlx","其他污染("+ele.getWrlxqt()+")");
				}else{
					row.put("wrlx","");
				}
			}
			row.put("zt","已登记");
			List<Work> workList = this.dao.find(" from Work where xfdjbId = ? and state != null", ele.getId());
			if(workList!=null && workList.size()>0){
				if(StringUtils.isNotBlank(workList.get(0).getState())){
					//d.put("state", WorkState.getNote(ele.getState()));
					if(WorkState.YSH.getCode().equals(workList.get(0).getState())){
						row.put("zt","已审核");
					}else if(WorkState.YGD.getCode().equals(workList.get(0).getState())){
						row.put("zt","已归档");
					}else if(WorkState.BLZ.getCode().equals(workList.get(0).getState())){
						row.put("zt","办理中");
					}else{
						row.put("zt","已派发");
					}
				}
				List<TBizXfbjd> XfbjdList = this.dao.find(" from TBizXfbjd where rwid = ?",workList.get(0).getId());
				String num = String.valueOf(workList.size());
				if(XfbjdList!=null && XfbjdList.size()>0){
					if(StringUtils.isNotBlank(XfbjdList.get(0).getBjqk())){
						if("0".equals(XfbjdList.get(0).getBjqk())){
							row.put("zt","信访终结");
						}else if("1".equals(XfbjdList.get(0).getBjqk())){
							row.put("zt",num+"次办结");
						}else{
							row.put("zt",num+"次办理");
						}
					}
				}
			}
			row.put("operate", OperateUtil.getOperate(ele.getId()));
			row.put("xfsj", ele.getXfsj()!=null?DateUtil.getDate(ele.getXfsj()):"");
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}

	/**
	 * 保存一个信访登记表
	 */
	@Transactional(readOnly = false)
	public TBizXfdj saveXfdj(XfdjForm frm) throws AppException {
		TBizXfdj po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		if (StringUtils.isNotBlank(frm.getId())) {
			po = (TBizXfdj) this.get(TBizXfdj.class, frm.getId());
		} else {
			po = new TBizXfdj();
			po.setCreateby(user);
			po.setCreated(cur);
		}
		//保存区域
		if(StringUtils.isNotBlank(user.getId())){
			TSysArea area = areaManager.getAreaByUser(user.getId());
			po.setArea(area);
		}
		//信访人、联系电话、信访内容、信访时间
		po.setXfr(frm.getXfr());
		po.setLxdh(frm.getLxdh());
		po.setXfnr(frm.getXfnr());
		if(StringUtils.isNotBlank(frm.getXfsj())){
			po.setXfsj(DateUtil.getStartDatetime(frm.getXfsj()));
		}
		//污染类型(添加方法判断，防止页面的选择项重复提交)
		if(StringUtils.isNotBlank(frm.getWrlx())){
			String[] wrlx = frm.getWrlx().split(",");
			String aa = "";
	        for(int i=0;i<wrlx.length;i++){
	        	if(wrlx[i] != null && wrlx[i] != ""){
	        		if(!aa.contains(wrlx[i])){
						aa += wrlx[i]+",";
					}
	        	}
	         }  
			po.setWrlx(aa);
		}
		po.setWrlxqt(frm.getWrlxqt());
		
		if(StringUtil.isNotBlank(frm.getSthjwrqt())){
			po.setSthjwrqt(frm.getSthjwrqt());
		}
		
		if(StringUtil.isNotBlank(frm.getSwrqt())){
			po.setSwrqt(frm.getSwrqt());
		}
		if(StringUtil.isNotBlank(frm.getDqwrqt())){
			po.setDqwrqt(frm.getDqwrqt());
		}
		if(StringUtil.isNotBlank(frm.getZswrqt())){
			po.setZswrqt(frm.getZswrqt());
		}
		if(StringUtil.isNotBlank(frm.getGfwrqt())){
			po.setGfwrqt(frm.getGfwrqt());
		}
		if(StringUtil.isNotBlank(frm.getFswrqt())){
			po.setFswrqt(frm.getFswrqt());
		}
		//信访编号
		po.setXfbh(frm.getXfbh());
		//信访来源
		po.setXfly(frm.getXfly());
		//企业地址
		po.setQydz(frm.getQydz());
		//来文名称
		po.setLwmc(frm.getLwmc());
		//保存办结时限
		if(StringUtils.isNotBlank(frm.getBjsx())){
			po.setBjsx(DateUtil.getEndDatetime(frm.getBjsx()));
		}
		//记录人、记录时间
		po.setJlr(frm.getJlr());
		po.setJlsj(DateUtil.getEndDatetime(frm.getJlsj()));
		
		po.setIsActive(YnEnum.Y.getCode());
		po.setUpdateby(user);
		po.setUpdated(cur);
		this.dao.save(po);
		return po;
	}

	/**
	 * 删除信访登记
	 */
	public void removeXfdj(String id) throws AppException {
		TBizXfdj del = (TBizXfdj) this.dao.get(TBizXfdj.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}
	
	/**
	 * 查询本年份信访登记表数目
	 * @return
	 */
	public int queryNumByYear(){
		//添加离线版的标识判断（可以查询对应的sql语句）
		String biaoshi = indexManager.sysVer;
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TBizXfdj where isActive='Y'");
		//当前用户所在的区域限制
		sql.append(" and area.id = :areaId");
		data.put("areaId",CtxUtil.getAreaId());
		Calendar a=Calendar.getInstance();
		int year = a.get(Calendar.YEAR);//获得当前年份
		String xfsj1 = String.valueOf(year)+"-01-01 00:00:00";
		String xfsj2 =  String.valueOf(year)+"-12-31 23:59:59";
		//信访时间在本年度的信访登记单
		if("0".equals(biaoshi)){
			sql.append(" and xfsj between '"+xfsj1+"' and '"+xfsj2+"'");
		}else{
			sql.append(" and xfsj >= TO_DATE(:xfsj1,'yyyy-MM-dd hh24:mi:ss')");
			data.put("xfsj1",xfsj1);
			sql.append(" and xfsj <= TO_DATE(:xfsj2,'yyyy-MM-dd hh24:mi:ss')");
			data.put("xfsj2",xfsj2);
		}
		List<Object[]> objs= dao.find(sql.toString(),data);
		if(objs!=null){
			return objs.size();
		}
		return 0;
	}
	
	/**
	 * 查询本月份信访登记表数目
	 * @return
	 */
	public int queryNumByMonth(){
		//添加离线版的标识判断（可以查询对应的sql语句）
		String biaoshi = indexManager.sysVer;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("select id from TBizXfdj where isActive='Y'");
		Calendar a=Calendar.getInstance();
		int year = a.get(Calendar.YEAR);//获得当前年份
		int month = a.get(Calendar.MONTH)+1;//获得当前月份
		//int nextMonth =  a.get(Calendar.MONTH)+2;//获得当前月份下个月
		String mon = "";
		//String nextMon = "";
		if(month>10 || month == 10){
			mon=String.valueOf(month);
		}else{
			mon="0"+String.valueOf(month);
		}
		/*if(nextMonth>10 || nextMonth == 10){
			nextMon=String.valueOf(nextMonth);
		}else{
			nextMon="0"+String.valueOf(nextMonth);
		}*/
		String xfsj1 = String.valueOf(year)+"-"+mon+"-01 00:00:00";
	    Calendar ca = Calendar.getInstance();   
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        String last = format.format(ca.getTime());
		String xfsj2 = last+" 23:59:59";
		//信访时间在本月份的信访登记单
		if("0".equals(biaoshi)){
			sql.append(" and xfsj between '"+xfsj1+"' and '"+xfsj2+"'");
		}else{
			sql.append(" and xfsj >= TO_DATE(:xfsj1,'yyyy-MM-dd hh24:mi:ss')");
			data.put("xfsj1",xfsj1);
			sql.append(" and xfsj <= TO_DATE(:xfsj2,'yyyy-MM-dd hh24:mi:ss')");
			data.put("xfsj2",xfsj2);
		}
		//当前用户所在的区域限制
		sql.append(" and area.id = :areaId");
		data.put("areaId",CtxUtil.getAreaId());
		List<Object[]> objs= dao.find(sql.toString(),data);
		if(objs!=null){
			return objs.size();
		}
		return 0;
	}
	
	//打印登记表
	@Override
	public void exportXfdjb(HttpServletResponse response,String xfdjId){
		
		// 生成检查单所用的数据
		Map<String, String> param = new HashMap<String, String>();
		// 标题中xx环境保护局 可能是当前用户所在部门的名称
		TSysUserOrg curOrg = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", CtxUtil.getCurUser().getId()).get(0);
		param.put("$dwmc$", curOrg.getOrg().getUnitname());
		TBizXfdj xfdj = (TBizXfdj) this.get(TBizXfdj.class, xfdjId);
		//信访来源
		if(StringUtils.isNotBlank(xfdj.getXfly())){
			param.put("$xfly$",dicManager.getNameByTypeAndCode("20", xfdj.getXfly()));
		}
		//信访编号
		param.put("$xfbh$", xfdj.getXfbh());
		//污染类型
		String temp="";
		if(StringUtils.isNotBlank(xfdj.getWrlx())){
			String [] wrlxs = xfdj.getWrlx().split(",");
			for(int i=0;i<wrlxs.length;i++){
				TSysDic dic = (TSysDic) dicManager.get(TSysDic.class, wrlxs[i]);
				if(dic!=null){
					if("2104".equals(dic.getCode())){
						temp=temp+"水污染其他("+xfdj.getSwrqt()+"),";
					}
					else if("2205".equals(dic.getCode())){
						temp=temp+"大气污染其他("+xfdj.getDqwrqt()+"),";
					}
					else if("2304".equals(dic.getCode())){
						temp=temp+"噪声污染其他("+xfdj.getZswrqt()+"),";
					}
					else if("2406".equals(dic.getCode())){
						temp=temp+"固废污染其他("+xfdj.getGfwrqt()+"),";
					}
					else if("2503".equals(dic.getCode())){
						temp=temp+"辐射污染其他("+xfdj.getFswrqt()+"),";
					}
					else{
						temp=temp+dic.getName()+",";
					}
				}
			}
			temp = temp.substring(0, temp.length()-1);
			if(StringUtils.isNotBlank(xfdj.getWrlxqt())){
				param.put("$wrlx$",temp+",其他污染("+xfdj.getWrlxqt()+")");
			}else{
				param.put("$wrlx$",temp);
			}
		}else{
			if(StringUtils.isNotBlank(xfdj.getWrlxqt())){
				param.put("$wrlx$","其他污染("+xfdj.getWrlxqt()+")");
			}else{
				param.put("$wrlx$","");
			}
		}
		//信访时间
		param.put("$xfsj$", DateUtil.getDateTime("yyyy年MM月dd日",(xfdj.getXfsj())));
		//信访人
		param.put("$xfr$", xfdj.getXfr());
		//联系电话
		param.put("$lxdh$", xfdj.getLxdh());
		//来文名称
		param.put("$lwmc$", xfdj.getLwmc());
		//办结时限
		param.put("$bjsx$", DateUtil.getDateTime("yyyy年MM月dd日",xfdj.getBjsx()));
		//信访内容
		param.put("$xfnr$", xfdj.getXfnr());
		//poi实现word操作
		String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
		String tempPath=sc.getRealPath(File.separator) + "excel/xfdj/xfdjTemplate.doc";
		try {
			//生成文件路径
			String filePath = PoiUtil.createWord(tempPath, dirPath, param);
			//文件名称
			String fileName = curOrg.getOrg().getUnitname()+"环境信访登记表.doc";
			File file = new File(filePath);
			String path = UploadFileType.WORK.getPath() + File.separator + file.getName();
			//下载文件
			FileUpDownUtil.downloadFile(response,path,fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		//打印办结单
		public void exportXfbjd(HttpServletResponse response,String xfdjId, String rwid){
			// 生成检查单所用的数据
			Map<String, String> param = new HashMap<String, String>();
			// 标题中xx环境保护局 可能是当前用户所在部门的名称
			TSysUserOrg curOrg = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", CtxUtil.getCurUser().getId()).get(0);
			param.put("$dwmc$", curOrg.getOrg().getUnitname());
			List<TBizXfdj> xfdjList = this.dao.find("from TBizXfdj where id = ? ", xfdjId);
			param.put("$xfly$","");//信访来源
			param.put("$xfbh$","");//信访编号
			param.put("$wrlx$","");//污染类型
			param.put("$xfsj$","");//信访时间
			param.put("$xfr$","");//信访人
			param.put("$lxdh$","");//联系电话
			param.put("$lwmc$","");//来文名称
			param.put("$bjsx$","");//办结时限
			param.put("$xfnr$","");//信访内容
			
			//联系电话
			if(xfdjList!=null && xfdjList.size()>0){//对应的信访登记表
				TBizXfdj xfdjb = xfdjList.get(0);	
				//信访来源
				if(StringUtils.isNotBlank(xfdjb.getXfly())){
						param.put("$xfly$",dicManager.getNameByTypeAndCode("20", xfdjb.getXfly()));
				}
				//信访编号
				param.put("$xfbh$", xfdjb.getXfbh());
				//污染类型
				String temp="";
				if(StringUtils.isNotBlank(xfdjb.getWrlx())){
					String [] wrlxs = xfdjb.getWrlx().split(",");
					for(int i=0;i<wrlxs.length;i++){
						TSysDic dic = (TSysDic) dicManager.get(TSysDic.class, wrlxs[i]);
						if(dic!=null){
							if("2104".equals(dic.getCode())){
								temp=temp+"水污染其他("+xfdjb.getSwrqt()+"),";
							}
							else if("2205".equals(dic.getCode())){
								temp=temp+"大气污染其他("+xfdjb.getDqwrqt()+"),";
							}
							else if("2304".equals(dic.getCode())){
								temp=temp+"噪声污染其他("+xfdjb.getZswrqt()+"),";
							}
							else if("2406".equals(dic.getCode())){
								temp=temp+"固废污染其他("+xfdjb.getGfwrqt()+"),";
							}
							else if("2503".equals(dic.getCode())){
								temp=temp+"辐射污染其他("+xfdjb.getFswrqt()+"),";
							}
							else{
								temp=temp+dic.getName()+",";
							}
						}
					}
					temp = temp.substring(0, temp.length()-1);
					if(StringUtils.isNotBlank(xfdjb.getWrlxqt())){
						param.put("$wrlx$",temp+",其他污染("+xfdjb.getWrlxqt()+")");
					}else{
						param.put("$wrlx$",temp);
					}
				}else{
					if(StringUtils.isNotBlank(xfdjb.getWrlxqt())){
						param.put("$wrlx$","其他污染("+xfdjb.getWrlxqt()+")");
					}else{
						param.put("$wrlx$","");
					}
				}
				//信访时间
				if(xfdjb.getXfsj()!=null){
						param.put("$xfsj$",String.valueOf(DateUtil.getDateTime("yyyy年MM月dd日", xfdjb.getXfsj())));
				}else{
						param.put("$xfsj$","");
				}
				//信访人
				param.put("$xfr$", xfdjb.getXfr());
				//联系电话
				param.put("$lxdh$", xfdjb.getLxdh());
				//来文名称
				param.put("$lwmc$", xfdjb.getLwmc());
				//办结时限
				if(xfdjb.getBjsx()!=null){
						param.put("$bjsx$",String.valueOf(DateUtil.getDateTime("yyyy年MM月dd日", xfdjb.getBjsx())));
				}else{
						param.put("$bjsx$","");
				}
				//信访内容
						param.put("$xfnr$", xfdjb.getXfnr());
			}
		
			param.put("$bjqk$","");
			param.put("$hjxfblqk$","");
			param.put("$hfr$","");
			param.put("$fhr$","");
			param.put("$jsr$","");
			param.put("$bcqk$","");
			param.put("$bcr$","");
			param.put("$slsj$"," 年   月   日");
			param.put("$hfrq$"," 年   月   日");
			param.put("$fhrq$"," 年   月   日");
			param.put("$jssj$"," 年   月   日");
			param.put("$bcrq$"," 年   月   日");
			param.put("$hfxs$","");
			List<Work> workList = this.dao.find("from Work where xfdjbId = ? and state != null and state != '01'", xfdjId);
			if(workList!=null && workList.size()>0){
				List<TBizXfbjd> xfbjdList = this.dao.find("from TBizXfbjd where rwid = ? ", workList.get(0).getId());
				if(xfbjdList!=null && xfbjdList.size()>0){
					String num = String.valueOf(workList.size());
					TBizXfbjd xfbjd = xfbjdList.get(0);
					//办结情况
					if(StringUtils.equals("0", xfbjd.getBjqk())){
						param.put("$bjqk$","信访终结");
					}else if(StringUtils.equals("1", xfbjd.getBjqk())){
						param.put("$bjqk$",num+"次办结");
					}else{
						param.put("$bjqk$",num+"次办理");
					}
					//受理时间
					param.put("$slsj$"," 年   月   日");
					//环境信访办理情况
					param.put("$hjxfblqk$",xfbjd.getHjxfblqk());
					//回访人
					param.put("$hfr$","");
					if(StringUtils.isNotBlank(xfbjd.getHfr())){
						TSysUser user = (TSysUser) this.get(TSysUser.class, xfbjd.getHfr());
						if(user!=null){
							param.put("$hfr$",user.getName());
						}
					}
					
					//返回人
					param.put("$fhr$",xfbjd.getFhr());
					//接收人
					param.put("$jsr$",xfbjd.getJsr());
					//报出情况
					param.put("$bcqk$",xfbjd.getBcqk());
					//报出人
					param.put("$bcr$",xfbjd.getBcr());
					//受理时间
					if(xfbjd.getSlsj()!=null){
						param.put("$slsj$",String.valueOf(DateUtil.getDateTime("yyyy年MM月dd日", xfbjd.getSlsj())));
					}else{
						param.put("$slsj$"," 年   月   日");
					}
					//回访日期
					if(xfbjd.getHfrq()!=null){
						param.put("$hfrq$",String.valueOf(DateUtil.getDateTime("yyyy年MM月dd日",xfbjd.getHfrq())));
					}else{
						param.put("$hfrq$"," 年   月   日");
					}
					//返回日期
					if(xfbjd.getFhrq()!=null){
						param.put("$fhrq$",DateUtil.getDateTime("yyyy年MM月dd日",xfbjd.getFhrq()));
					}else{
						param.put("$fhrq$"," 年   月   日");
					}
					//接收时间
					if(xfbjd.getJssj()!=null){
						param.put("$jssj$",DateUtil.getDateTime("yyyy年MM月dd日",xfbjd.getJssj()));
					}else{
						param.put("$jssj$"," 年   月   日");
					}
					//报出日期
					if(xfbjd.getBcrq()!=null){
						param.put("$bcrq$",DateUtil.getDateTime("yyyy年MM月dd日",xfbjd.getBcrq()));
					}else{
						param.put("$bcrq$"," 年   月   日");
					}
					//回访形式
					String hfxsNr="";
					if(StringUtils.isNotBlank(xfbjd.getHfxs())){
						hfxsNr = dicManager.getNameByTypeAndCode("26", xfbjd.getHfxs());
					}
					if(StringUtils.isNotBlank(xfbjd.getHfxsxm())){
						hfxsNr = hfxsNr+"（"+xfbjd.getHfxsxm()+"）";
					}
					if(StringUtils.isNotBlank(xfbjd.getHfxsdyrn())){
						hfxsNr = hfxsNr+"（"+xfbjd.getHfxsdyrn()+"）";
					}
					param.put("$hfxs$",hfxsNr);
				}
			}
		//poi实现word操作
		String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
		String tempPath=sc.getRealPath(File.separator) + "excel/xfdj/xfbjdTemplate.doc";
		try {
			//生成文件路径
			String filePath = PoiUtil.createWord(tempPath, dirPath, param);
			//文件名称
			String fileName = curOrg.getOrg().getUnitname()+"环境信访办结单.doc";
			File file = new File(filePath);
			String path = UploadFileType.WORK.getPath() + File.separator + file.getName();
			//下载文件
			FileUpDownUtil.downloadFile(response,path,fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 	* 查询办结单(信访登记列表界面)
	 	* @param response
		* @param xfdjId
	*/
	public XfbjdForm queryXfbjd(String xfdjId){
		List<Work> workList = this.dao.find("from Work where xfdjbId = ? ", xfdjId);
		XfbjdForm xfbjdForm = new XfbjdForm();
		if(workList!=null && workList.size()>0){
			List<TBizXfbjd> xfbjdList = this.dao.find("from TBizXfbjd where rwid = ? ", workList.get(0).getId());
			if(xfbjdList!=null && xfbjdList.size()>0){
				TBizXfbjd xfbjd = xfbjdList.get(0);
				if(xfbjd!=null){
		        	xfbjdForm.setId(xfbjd.getId());
					xfbjdForm.setBjqk(xfbjd.getBjqk());
					if(StringUtils.equals("0", xfbjd.getBjqk())){
						xfbjdForm.setBjqk("信访终结");
					}else if(StringUtils.isNotBlank(xfbjd.getBjqk())){
						xfbjdForm.setBjqk(xfbjd.getBjqk()+"次办结");
					}else{
						xfbjdForm.setBjqk("");
					}
					xfbjdForm.setHjxfblqk(xfbjd.getHjxfblqk());
					String hfxsNr="";
					if(StringUtils.isNotBlank(xfbjd.getHfxs())){
						hfxsNr = dicManager.getNameByTypeAndCode("26", xfbjd.getHfxs());
					}
					if(StringUtils.isNotBlank(xfbjd.getHfxsxm())){
						hfxsNr = hfxsNr+"（"+xfbjd.getHfxsxm()+"）";
					}
					if(StringUtils.isNotBlank(xfbjd.getHfxsdyrn())){
						hfxsNr = hfxsNr+"（"+xfbjd.getHfxsdyrn()+"）";
					}
					xfbjdForm.setHfxs(hfxsNr);
					xfbjdForm.setHfr(xfbjd.getHfr());
					if(StringUtils.isNotBlank(xfbjd.getHfr())){
						TSysUser user = (TSysUser) this.get(TSysUser.class, xfbjd.getHfr());
						if(user!=null){
							xfbjdForm.setHfrName(user.getName());
						}
					}
					xfbjdForm.setSlsj(DateUtil.getDateTime(xfbjd.getSlsj()));
					xfbjdForm.setHfrq(DateUtil.getDateTime(xfbjd.getHfrq()));// 回访时间（默认）
					xfbjdForm.setFhr(xfbjd.getFhr());
					xfbjdForm.setFhrq(DateUtil.getDateTime(xfbjd.getFhrq()));
					xfbjdForm.setJsr(xfbjd.getJsr());
					xfbjdForm.setJssj(DateUtil.getDateTime(xfbjd.getJssj()));
					xfbjdForm.setBcqk(xfbjd.getBcqk());
					xfbjdForm.setBcr(xfbjd.getBcr());
					xfbjdForm.setBcrq(DateUtil.getDateTime(xfbjd.getBcrq()));
		        }
			}
		}
		return xfbjdForm;
	}
	
	@Override
	public XfbjdForm getXfbjdformByxfdjId(String xfdjId){
		// 信访办结单信息
		XfbjdForm xfbjdForm = new XfbjdForm();
		List<Work> workList = this.dao.find("from Work where xfdjbId = ? ", xfdjId);
		if(workList!=null && workList.size()>0){
			//根据任务得到信访办结单
			String sql = " from TBizXfbjd where rwid= ? ";
			List<TBizXfbjd> xfbjd = this.dao.find(sql, workList.get(0).getId());
			if(xfbjd!=null && xfbjd.size()>0){
				xfbjdForm.setId(xfbjd.get(0).getId());
				xfbjdForm.setBjqk(xfbjd.get(0).getBjqk());
				xfbjdForm.setHjxfblqk(xfbjd.get(0).getHjxfblqk());
				xfbjdForm.setHfxs(xfbjd.get(0).getHfxs());
				xfbjdForm.setHfxsxm(xfbjd.get(0).getHfxsxm());
				xfbjdForm.setHfxsdyrn(xfbjd.get(0).getHfxsdyrn());
				xfbjdForm.setHfr(xfbjd.get(0).getHfr());
				if(StringUtils.isNotBlank(xfbjd.get(0).getHfr())){
					TSysUser user = (TSysUser) this.get(TSysUser.class, xfbjd.get(0).getHfr());
					if(user!=null){
						xfbjdForm.setHfrName(user.getName());
					}
				}
				xfbjdForm.setSlsj(DateUtil.getDateTime(xfbjd.get(0).getSlsj()));
				xfbjdForm.setHfrq(DateUtil.getDateTime(xfbjd.get(0).getHfrq() == null ? new Date() : xfbjd.get(0).getHfrq()));// 回访时间（默认）
				xfbjdForm.setFhr(xfbjd.get(0).getFhr());
				xfbjdForm.setFhrq(DateUtil.getDateTime(xfbjd.get(0).getFhrq() == null ? new Date() : xfbjd.get(0).getFhrq()));
				xfbjdForm.setJsr(xfbjd.get(0).getJsr());
				xfbjdForm.setJssj(DateUtil.getDateTime(xfbjd.get(0).getJssj() == null ? new Date() : xfbjd.get(0).getJssj()));
				xfbjdForm.setBcqk(xfbjd.get(0).getBcqk());
				xfbjdForm.setBcr(xfbjd.get(0).getBcr());
				xfbjdForm.setBcrq(DateUtil.getDateTime(xfbjd.get(0).getBcrq() == null ? new Date() : xfbjd.get(0).getBcrq()));
			}else{
				xfbjdForm.setFhr(CtxUtil.getCurUser().getName());//返回人
				xfbjdForm.setHfrq(DateUtil.getDateTime(new Date()));
				xfbjdForm.setFhrq(DateUtil.getDateTime(new Date()));
				xfbjdForm.setJssj(DateUtil.getDateTime(new Date()));
				xfbjdForm.setBcrq(DateUtil.getDateTime(new Date()));
			}
		}
		return xfbjdForm;
	}
	
	@Override
	public void saveHfInfo(XfbjdForm xfbjdForm,String xfdjId){
		TBizXfbjd xfbjd = null;
		if(StringUtils.isNotBlank(xfbjdForm.getId())){
			xfbjd = (TBizXfbjd) this.get(TBizXfbjd.class, xfbjdForm.getId());
			xfbjd.setUpdateby(CtxUtil.getCurUser());
			xfbjd.setUpdated(new Date());
		}else{
			xfbjd = new TBizXfbjd();
			TSysArea area = (TSysArea) this.get(TSysArea.class, CtxUtil.getAreaId());
			xfbjd.setArea(area);
			xfbjd.setCreateby(CtxUtil.getCurUser());
			xfbjd.setCreated(new Date());
			xfbjd.setIsActive(YnEnum.Y.getCode());
			xfbjd.setUpdateby(CtxUtil.getCurUser());
			xfbjd.setUpdated(new Date());
		}
		List<Work> workList = this.dao.find("from Work where xfdjbId = ? ", xfdjId);
		if(workList!=null && workList.size()>0){
			xfbjd.setRwid(workList.get(0).getId());
			xfbjd.setSlsj(workList.get(0).getZxStartTime());//受理时间
		}
		//环境信访办理情况已经在办理页面填写保存此处不进行
		//xfbjd.setHjxfblqk(xfbjdForm.getHjxfblqk());
		xfbjd.setHfxs(xfbjdForm.getHfxs());
		if("姓名".equals(xfbjdForm.getHfxsxm())){
			xfbjd.setHfxsxm("");
		}else{
			xfbjd.setHfxsxm(xfbjdForm.getHfxsxm());
		}
		if("内容".equals(xfbjdForm.getHfxsdyrn())){
			xfbjd.setHfxsdyrn("");
		}else{
			xfbjd.setHfxsdyrn(xfbjdForm.getHfxsdyrn());
		}
		xfbjd.setHfr(xfbjdForm.getHfr());
		xfbjd.setFhr(xfbjdForm.getFhr());
		xfbjd.setJsr(xfbjdForm.getJsr());
		xfbjd.setBcqk(xfbjdForm.getBcqk());
		xfbjd.setBcr(xfbjdForm.getBcr());
		try {
			xfbjd.setHfrq(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",xfbjdForm.getHfrq()));
			xfbjd.setFhrq(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",xfbjdForm.getFhrq()));
			xfbjd.setJssj(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",xfbjdForm.getJssj()));
			xfbjd.setBcrq(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm",xfbjdForm.getBcrq()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.dao.save(xfbjd);
	}
	
	@Override
	public boolean isShowHf(String xfdjId){
		List<Work> workList = this.dao.find("from Work where xfdjbId = ? and state != null", xfdjId);
		if(workList!=null && workList.size()>0){
			return true;
		}
		return false;
	}
	
	@Override
	public Work getWorkById(String xfdjId, String biaoshi){
		//满足信访登记表关联任务派发
		List<Work> workList = null;
		if("1".equals(biaoshi)){
			workList = this.dao.find("from Work where xfdjbId = ? and state = null", xfdjId);
		}else{
			workList = this.dao.find("from Work where xfdjbId = ?", xfdjId);
		}
		if(workList!=null && workList.size()>0){
			if(StringUtils.isNotBlank(workList.get(0).getJsr())){
    			TSysUser jsrObj = (TSysUser) this.get(TSysUser.class, workList.get(0).getJsr());
    			workList.get(0).setJsrName(jsrObj.getName());
    		}
			//获取执法对象名称
    		String zfdxmcs = "";
    		List<Map<String, String>> listMap = commWorkManager
					.zfdxTableData(workList.get(0).getId());
    		for(int i = 0; i < listMap.size(); i++){
    			if(i<listMap.size()-1){
    				zfdxmcs += listMap.get(i).get("lawobjname")+',';
    			}else{
    				zfdxmcs += listMap.get(i).get("lawobjname");
    			}
    		}
    		workList.get(0).setZfdxmc(zfdxmcs);
			return workList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public String gettaskId(String processId){
		StringBuffer sql = new StringBuffer("SELECT d.dbid_ FROM JBPM4_TASK d where d.execution_id_ = '"+processId+"'");
		List<String> tasks = this.dao.findBySql(sql.toString());
		String taskId = "";
		if(tasks!=null && tasks.size()>0) {
			taskId = String.valueOf(tasks.get(0));
		}
		return taskId;
	}
	
	@Override
	public boolean isShowXf(String xfdjId){
		List<Work> workList = this.dao.find("from Work where xfdjbId = ? and state != null", xfdjId);
		if(workList!=null && workList.size()>0){
			return false;
		}
		return true;
	}
	
	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
}
