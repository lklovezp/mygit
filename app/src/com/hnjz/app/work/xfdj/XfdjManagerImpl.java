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
 * �ŷõǼǹ�����
 * @author shiqiuhan
 * @created 2016-3-31,����08:56:39
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
	 * �ŷõǼǲ�ѯ
	 * @param name
	 * @param isActive
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public FyWebResult queryXfdj(String xfly, String xfsj1, String xfsj2, String zt, String page, String pageSize)
			throws Exception {
		//�������߰�ı�ʶ�жϣ����Բ�ѯ��Ӧ��sql��䣩
		String biaoshi = indexManager.sysVer;
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TBizXfdj where isActive='Y'");
		//��ǰ�û����ڵ���������	area
		sql.append(" and area.id = :areaId");
		data.put("areaId",CtxUtil.getAreaId());
		
		//�ŷ���Դ
		if (StringUtils.isNotBlank(xfly)) {
			sql.append(" and xfly = :xfly ");
			data.put("xfly", xfly);
		}
		//�ŷ�ʱ��
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
								temp=temp+"ˮ��Ⱦ����("+ele.getSwrqt()+"),";
							}
						}
						else if("2205".equals(dic.getCode())){
							if(StringUtils.isNotBlank(ele.getDqwrqt())){
								temp=temp+"������Ⱦ����("+ele.getDqwrqt()+"),";
							}
						}
						else if("2304".equals(dic.getCode())){
							if(StringUtils.isNotBlank(ele.getZswrqt())){
								temp=temp+"������Ⱦ����("+ele.getZswrqt()+"),";
							}
						}
						else if("2406".equals(dic.getCode())){
							if(StringUtils.isNotBlank(ele.getGfwrqt())){
								temp=temp+"�̷���Ⱦ����("+ele.getGfwrqt()+"),";
							}
						}
						else if("2503".equals(dic.getCode())){
							if(StringUtils.isNotBlank(ele.getFswrqt())){
								temp=temp+"������Ⱦ����("+ele.getFswrqt()+"),";
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
					row.put("wrlx",temp+",������Ⱦ("+ele.getWrlxqt()+")");
				}else{
					row.put("wrlx",temp);
				}
			}else{
				if(StringUtils.isNotBlank(ele.getWrlxqt())){
					row.put("wrlx","������Ⱦ("+ele.getWrlxqt()+")");
				}else{
					row.put("wrlx","");
				}
			}
			row.put("zt","�ѵǼ�");
			List<Work> workList = this.dao.find(" from Work where xfdjbId = ? and state != null", ele.getId());
			if(workList!=null && workList.size()>0){
				if(StringUtils.isNotBlank(workList.get(0).getState())){
					//d.put("state", WorkState.getNote(ele.getState()));
					if(WorkState.YSH.getCode().equals(workList.get(0).getState())){
						row.put("zt","�����");
					}else if(WorkState.YGD.getCode().equals(workList.get(0).getState())){
						row.put("zt","�ѹ鵵");
					}else if(WorkState.BLZ.getCode().equals(workList.get(0).getState())){
						row.put("zt","������");
					}else{
						row.put("zt","���ɷ�");
					}
				}
				List<TBizXfbjd> XfbjdList = this.dao.find(" from TBizXfbjd where rwid = ?",workList.get(0).getId());
				String num = String.valueOf(workList.size());
				if(XfbjdList!=null && XfbjdList.size()>0){
					if(StringUtils.isNotBlank(XfbjdList.get(0).getBjqk())){
						if("0".equals(XfbjdList.get(0).getBjqk())){
							row.put("zt","�ŷ��ս�");
						}else if("1".equals(XfbjdList.get(0).getBjqk())){
							row.put("zt",num+"�ΰ��");
						}else{
							row.put("zt",num+"�ΰ���");
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
	 * ����һ���ŷõǼǱ�
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
		//��������
		if(StringUtils.isNotBlank(user.getId())){
			TSysArea area = areaManager.getAreaByUser(user.getId());
			po.setArea(area);
		}
		//�ŷ��ˡ���ϵ�绰���ŷ����ݡ��ŷ�ʱ��
		po.setXfr(frm.getXfr());
		po.setLxdh(frm.getLxdh());
		po.setXfnr(frm.getXfnr());
		if(StringUtils.isNotBlank(frm.getXfsj())){
			po.setXfsj(DateUtil.getStartDatetime(frm.getXfsj()));
		}
		//��Ⱦ����(���ӷ����жϣ���ֹҳ���ѡ�����ظ��ύ)
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
		//�ŷñ��
		po.setXfbh(frm.getXfbh());
		//�ŷ���Դ
		po.setXfly(frm.getXfly());
		//��ҵ��ַ
		po.setQydz(frm.getQydz());
		//��������
		po.setLwmc(frm.getLwmc());
		//������ʱ��
		if(StringUtils.isNotBlank(frm.getBjsx())){
			po.setBjsx(DateUtil.getEndDatetime(frm.getBjsx()));
		}
		//��¼�ˡ���¼ʱ��
		po.setJlr(frm.getJlr());
		po.setJlsj(DateUtil.getEndDatetime(frm.getJlsj()));
		
		po.setIsActive(YnEnum.Y.getCode());
		po.setUpdateby(user);
		po.setUpdated(cur);
		this.dao.save(po);
		return po;
	}

	/**
	 * ɾ���ŷõǼ�
	 */
	public void removeXfdj(String id) throws AppException {
		TBizXfdj del = (TBizXfdj) this.dao.get(TBizXfdj.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}
	
	/**
	 * ��ѯ������ŷõǼǱ���Ŀ
	 * @return
	 */
	public int queryNumByYear(){
		//�������߰�ı�ʶ�жϣ����Բ�ѯ��Ӧ��sql��䣩
		String biaoshi = indexManager.sysVer;
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TBizXfdj where isActive='Y'");
		//��ǰ�û����ڵ���������
		sql.append(" and area.id = :areaId");
		data.put("areaId",CtxUtil.getAreaId());
		Calendar a=Calendar.getInstance();
		int year = a.get(Calendar.YEAR);//��õ�ǰ���
		String xfsj1 = String.valueOf(year)+"-01-01 00:00:00";
		String xfsj2 =  String.valueOf(year)+"-12-31 23:59:59";
		//�ŷ�ʱ���ڱ���ȵ��ŷõǼǵ�
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
	 * ��ѯ���·��ŷõǼǱ���Ŀ
	 * @return
	 */
	public int queryNumByMonth(){
		//�������߰�ı�ʶ�жϣ����Բ�ѯ��Ӧ��sql��䣩
		String biaoshi = indexManager.sysVer;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("select id from TBizXfdj where isActive='Y'");
		Calendar a=Calendar.getInstance();
		int year = a.get(Calendar.YEAR);//��õ�ǰ���
		int month = a.get(Calendar.MONTH)+1;//��õ�ǰ�·�
		//int nextMonth =  a.get(Calendar.MONTH)+2;//��õ�ǰ�·��¸���
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
		//�ŷ�ʱ���ڱ��·ݵ��ŷõǼǵ�
		if("0".equals(biaoshi)){
			sql.append(" and xfsj between '"+xfsj1+"' and '"+xfsj2+"'");
		}else{
			sql.append(" and xfsj >= TO_DATE(:xfsj1,'yyyy-MM-dd hh24:mi:ss')");
			data.put("xfsj1",xfsj1);
			sql.append(" and xfsj <= TO_DATE(:xfsj2,'yyyy-MM-dd hh24:mi:ss')");
			data.put("xfsj2",xfsj2);
		}
		//��ǰ�û����ڵ���������
		sql.append(" and area.id = :areaId");
		data.put("areaId",CtxUtil.getAreaId());
		List<Object[]> objs= dao.find(sql.toString(),data);
		if(objs!=null){
			return objs.size();
		}
		return 0;
	}
	
	//��ӡ�ǼǱ�
	@Override
	public void exportXfdjb(HttpServletResponse response,String xfdjId){
		
		// ���ɼ�鵥���õ�����
		Map<String, String> param = new HashMap<String, String>();
		// ������xx���������� �����ǵ�ǰ�û����ڲ��ŵ�����
		TSysUserOrg curOrg = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", CtxUtil.getCurUser().getId()).get(0);
		param.put("$dwmc$", curOrg.getOrg().getUnitname());
		TBizXfdj xfdj = (TBizXfdj) this.get(TBizXfdj.class, xfdjId);
		//�ŷ���Դ
		if(StringUtils.isNotBlank(xfdj.getXfly())){
			param.put("$xfly$",dicManager.getNameByTypeAndCode("20", xfdj.getXfly()));
		}
		//�ŷñ��
		param.put("$xfbh$", xfdj.getXfbh());
		//��Ⱦ����
		String temp="";
		if(StringUtils.isNotBlank(xfdj.getWrlx())){
			String [] wrlxs = xfdj.getWrlx().split(",");
			for(int i=0;i<wrlxs.length;i++){
				TSysDic dic = (TSysDic) dicManager.get(TSysDic.class, wrlxs[i]);
				if(dic!=null){
					if("2104".equals(dic.getCode())){
						temp=temp+"ˮ��Ⱦ����("+xfdj.getSwrqt()+"),";
					}
					else if("2205".equals(dic.getCode())){
						temp=temp+"������Ⱦ����("+xfdj.getDqwrqt()+"),";
					}
					else if("2304".equals(dic.getCode())){
						temp=temp+"������Ⱦ����("+xfdj.getZswrqt()+"),";
					}
					else if("2406".equals(dic.getCode())){
						temp=temp+"�̷���Ⱦ����("+xfdj.getGfwrqt()+"),";
					}
					else if("2503".equals(dic.getCode())){
						temp=temp+"������Ⱦ����("+xfdj.getFswrqt()+"),";
					}
					else{
						temp=temp+dic.getName()+",";
					}
				}
			}
			temp = temp.substring(0, temp.length()-1);
			if(StringUtils.isNotBlank(xfdj.getWrlxqt())){
				param.put("$wrlx$",temp+",������Ⱦ("+xfdj.getWrlxqt()+")");
			}else{
				param.put("$wrlx$",temp);
			}
		}else{
			if(StringUtils.isNotBlank(xfdj.getWrlxqt())){
				param.put("$wrlx$","������Ⱦ("+xfdj.getWrlxqt()+")");
			}else{
				param.put("$wrlx$","");
			}
		}
		//�ŷ�ʱ��
		param.put("$xfsj$", DateUtil.getDateTime("yyyy��MM��dd��",(xfdj.getXfsj())));
		//�ŷ���
		param.put("$xfr$", xfdj.getXfr());
		//��ϵ�绰
		param.put("$lxdh$", xfdj.getLxdh());
		//��������
		param.put("$lwmc$", xfdj.getLwmc());
		//���ʱ��
		param.put("$bjsx$", DateUtil.getDateTime("yyyy��MM��dd��",xfdj.getBjsx()));
		//�ŷ�����
		param.put("$xfnr$", xfdj.getXfnr());
		//poiʵ��word����
		String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
		String tempPath=sc.getRealPath(File.separator) + "excel/xfdj/xfdjTemplate.doc";
		try {
			//�����ļ�·��
			String filePath = PoiUtil.createWord(tempPath, dirPath, param);
			//�ļ�����
			String fileName = curOrg.getOrg().getUnitname()+"�����ŷõǼǱ�.doc";
			File file = new File(filePath);
			String path = UploadFileType.WORK.getPath() + File.separator + file.getName();
			//�����ļ�
			FileUpDownUtil.downloadFile(response,path,fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		//��ӡ��ᵥ
		public void exportXfbjd(HttpServletResponse response,String xfdjId, String rwid){
			// ���ɼ�鵥���õ�����
			Map<String, String> param = new HashMap<String, String>();
			// ������xx���������� �����ǵ�ǰ�û����ڲ��ŵ�����
			TSysUserOrg curOrg = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", CtxUtil.getCurUser().getId()).get(0);
			param.put("$dwmc$", curOrg.getOrg().getUnitname());
			List<TBizXfdj> xfdjList = this.dao.find("from TBizXfdj where id = ? ", xfdjId);
			param.put("$xfly$","");//�ŷ���Դ
			param.put("$xfbh$","");//�ŷñ��
			param.put("$wrlx$","");//��Ⱦ����
			param.put("$xfsj$","");//�ŷ�ʱ��
			param.put("$xfr$","");//�ŷ���
			param.put("$lxdh$","");//��ϵ�绰
			param.put("$lwmc$","");//��������
			param.put("$bjsx$","");//���ʱ��
			param.put("$xfnr$","");//�ŷ�����
			
			//��ϵ�绰
			if(xfdjList!=null && xfdjList.size()>0){//��Ӧ���ŷõǼǱ�
				TBizXfdj xfdjb = xfdjList.get(0);	
				//�ŷ���Դ
				if(StringUtils.isNotBlank(xfdjb.getXfly())){
						param.put("$xfly$",dicManager.getNameByTypeAndCode("20", xfdjb.getXfly()));
				}
				//�ŷñ��
				param.put("$xfbh$", xfdjb.getXfbh());
				//��Ⱦ����
				String temp="";
				if(StringUtils.isNotBlank(xfdjb.getWrlx())){
					String [] wrlxs = xfdjb.getWrlx().split(",");
					for(int i=0;i<wrlxs.length;i++){
						TSysDic dic = (TSysDic) dicManager.get(TSysDic.class, wrlxs[i]);
						if(dic!=null){
							if("2104".equals(dic.getCode())){
								temp=temp+"ˮ��Ⱦ����("+xfdjb.getSwrqt()+"),";
							}
							else if("2205".equals(dic.getCode())){
								temp=temp+"������Ⱦ����("+xfdjb.getDqwrqt()+"),";
							}
							else if("2304".equals(dic.getCode())){
								temp=temp+"������Ⱦ����("+xfdjb.getZswrqt()+"),";
							}
							else if("2406".equals(dic.getCode())){
								temp=temp+"�̷���Ⱦ����("+xfdjb.getGfwrqt()+"),";
							}
							else if("2503".equals(dic.getCode())){
								temp=temp+"������Ⱦ����("+xfdjb.getFswrqt()+"),";
							}
							else{
								temp=temp+dic.getName()+",";
							}
						}
					}
					temp = temp.substring(0, temp.length()-1);
					if(StringUtils.isNotBlank(xfdjb.getWrlxqt())){
						param.put("$wrlx$",temp+",������Ⱦ("+xfdjb.getWrlxqt()+")");
					}else{
						param.put("$wrlx$",temp);
					}
				}else{
					if(StringUtils.isNotBlank(xfdjb.getWrlxqt())){
						param.put("$wrlx$","������Ⱦ("+xfdjb.getWrlxqt()+")");
					}else{
						param.put("$wrlx$","");
					}
				}
				//�ŷ�ʱ��
				if(xfdjb.getXfsj()!=null){
						param.put("$xfsj$",String.valueOf(DateUtil.getDateTime("yyyy��MM��dd��", xfdjb.getXfsj())));
				}else{
						param.put("$xfsj$","");
				}
				//�ŷ���
				param.put("$xfr$", xfdjb.getXfr());
				//��ϵ�绰
				param.put("$lxdh$", xfdjb.getLxdh());
				//��������
				param.put("$lwmc$", xfdjb.getLwmc());
				//���ʱ��
				if(xfdjb.getBjsx()!=null){
						param.put("$bjsx$",String.valueOf(DateUtil.getDateTime("yyyy��MM��dd��", xfdjb.getBjsx())));
				}else{
						param.put("$bjsx$","");
				}
				//�ŷ�����
						param.put("$xfnr$", xfdjb.getXfnr());
			}
		
			param.put("$bjqk$","");
			param.put("$hjxfblqk$","");
			param.put("$hfr$","");
			param.put("$fhr$","");
			param.put("$jsr$","");
			param.put("$bcqk$","");
			param.put("$bcr$","");
			param.put("$slsj$"," ��   ��   ��");
			param.put("$hfrq$"," ��   ��   ��");
			param.put("$fhrq$"," ��   ��   ��");
			param.put("$jssj$"," ��   ��   ��");
			param.put("$bcrq$"," ��   ��   ��");
			param.put("$hfxs$","");
			List<Work> workList = this.dao.find("from Work where xfdjbId = ? and state != null and state != '01'", xfdjId);
			if(workList!=null && workList.size()>0){
				List<TBizXfbjd> xfbjdList = this.dao.find("from TBizXfbjd where rwid = ? ", workList.get(0).getId());
				if(xfbjdList!=null && xfbjdList.size()>0){
					String num = String.valueOf(workList.size());
					TBizXfbjd xfbjd = xfbjdList.get(0);
					//������
					if(StringUtils.equals("0", xfbjd.getBjqk())){
						param.put("$bjqk$","�ŷ��ս�");
					}else if(StringUtils.equals("1", xfbjd.getBjqk())){
						param.put("$bjqk$",num+"�ΰ��");
					}else{
						param.put("$bjqk$",num+"�ΰ���");
					}
					//����ʱ��
					param.put("$slsj$"," ��   ��   ��");
					//�����ŷð������
					param.put("$hjxfblqk$",xfbjd.getHjxfblqk());
					//�ط���
					param.put("$hfr$","");
					if(StringUtils.isNotBlank(xfbjd.getHfr())){
						TSysUser user = (TSysUser) this.get(TSysUser.class, xfbjd.getHfr());
						if(user!=null){
							param.put("$hfr$",user.getName());
						}
					}
					
					//������
					param.put("$fhr$",xfbjd.getFhr());
					//������
					param.put("$jsr$",xfbjd.getJsr());
					//�������
					param.put("$bcqk$",xfbjd.getBcqk());
					//������
					param.put("$bcr$",xfbjd.getBcr());
					//����ʱ��
					if(xfbjd.getSlsj()!=null){
						param.put("$slsj$",String.valueOf(DateUtil.getDateTime("yyyy��MM��dd��", xfbjd.getSlsj())));
					}else{
						param.put("$slsj$"," ��   ��   ��");
					}
					//�ط�����
					if(xfbjd.getHfrq()!=null){
						param.put("$hfrq$",String.valueOf(DateUtil.getDateTime("yyyy��MM��dd��",xfbjd.getHfrq())));
					}else{
						param.put("$hfrq$"," ��   ��   ��");
					}
					//��������
					if(xfbjd.getFhrq()!=null){
						param.put("$fhrq$",DateUtil.getDateTime("yyyy��MM��dd��",xfbjd.getFhrq()));
					}else{
						param.put("$fhrq$"," ��   ��   ��");
					}
					//����ʱ��
					if(xfbjd.getJssj()!=null){
						param.put("$jssj$",DateUtil.getDateTime("yyyy��MM��dd��",xfbjd.getJssj()));
					}else{
						param.put("$jssj$"," ��   ��   ��");
					}
					//��������
					if(xfbjd.getBcrq()!=null){
						param.put("$bcrq$",DateUtil.getDateTime("yyyy��MM��dd��",xfbjd.getBcrq()));
					}else{
						param.put("$bcrq$"," ��   ��   ��");
					}
					//�ط���ʽ
					String hfxsNr="";
					if(StringUtils.isNotBlank(xfbjd.getHfxs())){
						hfxsNr = dicManager.getNameByTypeAndCode("26", xfbjd.getHfxs());
					}
					if(StringUtils.isNotBlank(xfbjd.getHfxsxm())){
						hfxsNr = hfxsNr+"��"+xfbjd.getHfxsxm()+"��";
					}
					if(StringUtils.isNotBlank(xfbjd.getHfxsdyrn())){
						hfxsNr = hfxsNr+"��"+xfbjd.getHfxsdyrn()+"��";
					}
					param.put("$hfxs$",hfxsNr);
				}
			}
		//poiʵ��word����
		String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
		String tempPath=sc.getRealPath(File.separator) + "excel/xfdj/xfbjdTemplate.doc";
		try {
			//�����ļ�·��
			String filePath = PoiUtil.createWord(tempPath, dirPath, param);
			//�ļ�����
			String fileName = curOrg.getOrg().getUnitname()+"�����ŷð�ᵥ.doc";
			File file = new File(filePath);
			String path = UploadFileType.WORK.getPath() + File.separator + file.getName();
			//�����ļ�
			FileUpDownUtil.downloadFile(response,path,fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 	* ��ѯ��ᵥ(�ŷõǼ��б�����)
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
						xfbjdForm.setBjqk("�ŷ��ս�");
					}else if(StringUtils.isNotBlank(xfbjd.getBjqk())){
						xfbjdForm.setBjqk(xfbjd.getBjqk()+"�ΰ��");
					}else{
						xfbjdForm.setBjqk("");
					}
					xfbjdForm.setHjxfblqk(xfbjd.getHjxfblqk());
					String hfxsNr="";
					if(StringUtils.isNotBlank(xfbjd.getHfxs())){
						hfxsNr = dicManager.getNameByTypeAndCode("26", xfbjd.getHfxs());
					}
					if(StringUtils.isNotBlank(xfbjd.getHfxsxm())){
						hfxsNr = hfxsNr+"��"+xfbjd.getHfxsxm()+"��";
					}
					if(StringUtils.isNotBlank(xfbjd.getHfxsdyrn())){
						hfxsNr = hfxsNr+"��"+xfbjd.getHfxsdyrn()+"��";
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
					xfbjdForm.setHfrq(DateUtil.getDateTime(xfbjd.getHfrq()));// �ط�ʱ�䣨Ĭ�ϣ�
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
		// �ŷð�ᵥ��Ϣ
		XfbjdForm xfbjdForm = new XfbjdForm();
		List<Work> workList = this.dao.find("from Work where xfdjbId = ? ", xfdjId);
		if(workList!=null && workList.size()>0){
			//��������õ��ŷð�ᵥ
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
				xfbjdForm.setHfrq(DateUtil.getDateTime(xfbjd.get(0).getHfrq() == null ? new Date() : xfbjd.get(0).getHfrq()));// �ط�ʱ�䣨Ĭ�ϣ�
				xfbjdForm.setFhr(xfbjd.get(0).getFhr());
				xfbjdForm.setFhrq(DateUtil.getDateTime(xfbjd.get(0).getFhrq() == null ? new Date() : xfbjd.get(0).getFhrq()));
				xfbjdForm.setJsr(xfbjd.get(0).getJsr());
				xfbjdForm.setJssj(DateUtil.getDateTime(xfbjd.get(0).getJssj() == null ? new Date() : xfbjd.get(0).getJssj()));
				xfbjdForm.setBcqk(xfbjd.get(0).getBcqk());
				xfbjdForm.setBcr(xfbjd.get(0).getBcr());
				xfbjdForm.setBcrq(DateUtil.getDateTime(xfbjd.get(0).getBcrq() == null ? new Date() : xfbjd.get(0).getBcrq()));
			}else{
				xfbjdForm.setFhr(CtxUtil.getCurUser().getName());//������
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
			xfbjd.setSlsj(workList.get(0).getZxStartTime());//����ʱ��
		}
		//�����ŷð�������Ѿ��ڰ���ҳ����д����˴�������
		//xfbjd.setHjxfblqk(xfbjdForm.getHjxfblqk());
		xfbjd.setHfxs(xfbjdForm.getHfxs());
		if("����".equals(xfbjdForm.getHfxsxm())){
			xfbjd.setHfxsxm("");
		}else{
			xfbjd.setHfxsxm(xfbjdForm.getHfxsxm());
		}
		if("����".equals(xfbjdForm.getHfxsdyrn())){
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
		//�����ŷõǼǱ����������ɷ�
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
			//��ȡִ����������
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