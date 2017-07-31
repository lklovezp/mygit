package com.hnjz.app.work.jaspb;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.work.jcd.JcdManagerImpl;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.po.TBizXzcfjasp;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.zx.BlZfdxForm;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.PoiUtil;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.po.TSysUser;

@Service("jaspbManager")
public class JaspbManagerImpl extends ManagerImpl implements JaspbManager ,ServletContextAware {
	
	/** 日志 */
	private static final Log log = LogFactory
			.getLog(JcdManagerImpl.class);
	
	private ServletContext sc;
	
	@Autowired
    private CommonManager                 commonManager;
	
	@Autowired
    protected WorkManagerImpl workManager;
	
	@Autowired
    private CommWorkManager    commWorkManager;

	@Override
	public JaspbForm getSxgzsFormData(String applyId) {
		//执法对象信息
		BlZfdxForm blZfdxForm=new BlZfdxForm();
		Work work = workManager.get(applyId);	
        Map<String, String> zfdxMap = new HashMap<String, String>();
        List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
        if(zfdxlistMap!=null&&zfdxlistMap.size()==1){
        	zfdxMap=zfdxlistMap.get(0);
        	blZfdxForm.setId(zfdxMap.get("id"));
        	blZfdxForm.setLawobjid(zfdxMap.get("lawobjid"));
        	blZfdxForm.setLawobjname(zfdxMap.get("lawobjname"));
        	blZfdxForm.setAddress(zfdxMap.get("address"));
        	blZfdxForm.setManager(zfdxMap.get("manager"));
        	blZfdxForm.setManagermobile(zfdxMap.get("managermobile"));
        	blZfdxForm.setBjcr(zfdxMap.get("bjcr"));
        	blZfdxForm.setZw(zfdxMap.get("zw"));
        	blZfdxForm.setLxdh(zfdxMap.get("lxdh"));	
        	blZfdxForm.setXqyzDwmc(zfdxMap.get("xqyzDwmc"));
        }  
        JaspbForm jaspbForm=new JaspbForm();	
		QueryCondition data = new QueryCondition();
		StringBuffer sql = new StringBuffer();
		sql.append(" from TBizXzcfjasp where taskid=:taskid ");
		data.put("taskid", applyId);
		List<TBizXzcfjasp> list = this.dao.find(sql.toString(),data);
		TBizXzcfjasp po=new TBizXzcfjasp();
		if(list!=null&&list.size()>0){
			po=list.get(0);
			jaspbForm.setId(po.getId());
			jaspbForm.setTaskid(applyId);//任务ID
			jaspbForm.setAjnr(po.getAjnr());//案由
			jaspbForm.setAjly(po.getAjly());//案件来源
			jaspbForm.setDsrmc(po.getDsrmc());//当事人（名称或姓名）
			jaspbForm.setFddbr(po.getFddbr());//法定代表人
			jaspbForm.setCompany(po.getCompany());//工作单位
			jaspbForm.setDsrzw(po.getDsrzw());//职务或职业
			jaspbForm.setDsrdz(po.getDsrdz());//地址或住址
			jaspbForm.setLasj(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getLasj()?new Date():po.getLasj()));//立案时间
			jaspbForm.setAjscr(po.getAjscr());//案件审查人
			jaspbForm.setAjscrbh(po.getAjscrbh());//执法证编号
			jaspbForm.setXzcfjdswh(po.getXzcfjdswh());//行政处罚决定书文号
			jaspbForm.setJyaq(po.getJyaq());//简要案情及查处经过
			jaspbForm.setClyj(po.getClyj());//处理依据及结果
			jaspbForm.setXzssqk(po.getXzssqk());//行政复议行政诉讼情况
			jaspbForm.setCfzxqk(po.getCfzxqk());//处罚执行情况及罚没财务的处置情况
			jaspbForm.setRemark(po.getRemark());//备注
		}else{//默认带过来的
			jaspbForm.setLasj(DateUtil.getDateTime("yyyy-MM-dd HH:mm",new Date()));//立案时间默认值
			jaspbForm.setDsrmc(blZfdxForm.getLawobjname());//执法对象名称
			jaspbForm.setFddbr(blZfdxForm.getManager());//法定代表人
			jaspbForm.setDsrzw(blZfdxForm.getZw());//当事人职务
			//任务来源名称
	        String str1="";
			if(work.getSource()!=null){
				str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),work.getSource());
			}
			jaspbForm.setAjly(str1);//案件来源
			jaspbForm.setAjnr(work.getWorkNote());//任务简要内容
			jaspbForm.setDsrdz(blZfdxForm.getAddress());//住址地址
		}
		return jaspbForm;
	}
	
	@Override
	public void saveSxgzs(JaspbForm jaspbForm,String applyId,String wflx){
		TSysUser cur = CtxUtil.getCurUser();
		TBizXzcfjasp jaspb = new TBizXzcfjasp();
		if(StringUtils.isNotBlank(jaspbForm.getId())){
			jaspb = (TBizXzcfjasp)dao.get(TBizXzcfjasp.class, jaspbForm.getId());	
			jaspb.setUpdateby(cur);
			jaspb.setUpdated(new Date());
        }else{
        	jaspb.setCreateby(cur);
        	jaspb.setCreated(new Date());
        	jaspb.setUpdateby(cur);
        	jaspb.setUpdated(new Date());
        }
		jaspb.setId(jaspbForm.getId());
		jaspb.setTaskid(applyId);//任务ID
		jaspb.setAjnr(jaspbForm.getAjnr());//案由
		jaspb.setAjly(jaspbForm.getAjly());//案件来源
		jaspb.setDsrmc(jaspbForm.getDsrmc());//当事人（名称或姓名）
		jaspb.setFddbr(jaspbForm.getFddbr());//法定代表人
		jaspb.setCompany(jaspbForm.getCompany());//工作单位
		jaspb.setDsrzw(jaspbForm.getDsrzw());//职务或职业
		jaspb.setDsrdz(jaspbForm.getDsrdz());//地址或住址
		try {
			jaspb.setLasj(DateUtil.convertStringToDate("yyyy-MM-dd", jaspbForm.getLasj()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//立案时间
		jaspb.setAjscr(jaspbForm.getAjscr());//案件审查人
		jaspb.setAjscrbh(jaspbForm.getAjscrbh());//执法证编号
		jaspb.setXzcfjdswh(jaspbForm.getXzcfjdswh());//行政处罚决定书文号
		jaspb.setJyaq(jaspbForm.getJyaq());//简要案情及查处经过
		jaspb.setClyj(jaspbForm.getClyj());//处理依据及结果
		jaspb.setXzssqk(jaspbForm.getXzssqk());//行政复议行政诉讼情况
		jaspb.setCfzxqk(jaspbForm.getCfzxqk());//处罚执行情况及罚没财务的处置情况
		jaspb.setRemark(jaspbForm.getRemark());//备注
		jaspb.setAreaId(cur.getAreaId());//数据区域过滤标识
		jaspb.setIsActive(YnEnum.Y.getCode());	
		dao.save(jaspb);
	}
	
	@Override
	public void saveShengchengSxgzs(String applyId,String wflx){
		try{
			Work work = workManager.get(applyId);
			TBizXzcfjasp jaspb=new TBizXzcfjasp();
			//区域作工作单位
			//TSysUser user = CtxUtil.getCurUser();
			if (null != applyId && !"".equals(applyId.trim())) {
				String ajnr="";//案由
				String ajly = "";//案件来源
				String dsrmc = "";//当事人（名称或姓名）
				String fddbr = "";//法定代表人
				String company = "";//工作单位
				String dsrzw = "";//职务或职业
				String dsrdz = "";//地址或住址	
				String lasj = "";//立案时间		
				String ajscr = "";//案件审查人	
				String ajscrbh = "";//执法证编号
				String xzcfjdswh = "";//行政处罚决定书文号
				String jyaq = "";//简要案情及查处经过
				String clyj = "";//处理依据及结果
				String xzssqk = "";//行政复议行政诉讼情况
				String cfzxqk = "";//处罚执行情况及罚没财务的处置情况
				String remark = "";//备注
				//查看该任务的勘察笔录
				List<TBizXzcfjasp> kcbllList = dao.find(" from TBizXzcfjasp t where t.taskid=? ", applyId);
				if (kcbllList != null && kcbllList.size() > 0) {//有记录
					jaspb = kcbllList.get(0);
				} else {
					jaspb = new TBizXzcfjasp();
				}

				/********************开始赋值****************/
				
				ajnr = jaspb.getAjnr();//案由
				ajly = jaspb.getAjly();//案件来源
				dsrmc = jaspb.getDsrmc();//当事人（名称或姓名）
				fddbr = jaspb.getFddbr();//法定代表人
				company = jaspb.getCompany();//工作单位
				dsrzw = jaspb.getDsrzw();//职务或职业
				dsrdz = jaspb.getDsrdz();//地址或住址	
				lasj = DateUtil.getDateTime("yyyy年MM月dd日", jaspb.getLasj());//立案时间		
				ajscr = jaspb.getAjscr();//案件审查人	
				ajscrbh = jaspb.getAjscrbh();//执法证编号
				xzcfjdswh = jaspb.getXzcfjdswh();//行政处罚决定书文号
				jyaq = jaspb.getJyaq();//简要案情及查处经过
				clyj = jaspb.getClyj();//处理依据及结果
				xzssqk = jaspb.getXzssqk();//行政复议行政诉讼情况
				cfzxqk = jaspb.getCfzxqk();//处罚执行情况及罚没财务的处置情况
				remark = jaspb.getRemark();//备注
				/********************赋值结束****************/
				long start = System.currentTimeMillis();
				//System.out.println("用poi生成word开始时间：" + start);
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
				String tempPath=sc.getRealPath(File.separator) + "excel//xzcf/xzcf_xzcfjaspb.doc";
				//poi实现word操作
				Map<String, String> param = new HashMap<String, String>();	
				param.put("$ajnr$", ajnr);
				param.put("$ajly$", ajly);
				param.put("$dsrmc$", dsrmc);
				param.put("$fddbr$", fddbr);
				param.put("$company$", company);
				param.put("$dsrzw$", dsrzw);
				param.put("$dsrdz$", dsrdz);
				param.put("$lasj$", lasj);
				param.put("$ajscr$", ajscr);
				param.put("$ajscrbh$", ajscrbh);
				param.put("$xzcfjdswh$", xzcfjdswh);
				param.put("$jyaq$", jyaq);
				param.put("$clyj$", clyj);
				param.put("$xzssqk$", xzssqk);
				param.put("$cfzxqk$", cfzxqk);
				param.put("$remark$", remark);	
				String newfile = PoiUtil.createWord(tempPath, dirPath, param);
				//////////////////////////////根据任务类型，做相应的处理(保存生成文档)///////////////////////////////////
				File file = new File(newfile);
				if (file.exists()) {
					InputStream is = new FileInputStream(file);	
					//先删除旧的，再保存新的；
					//commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCJASPB.getCode());	
					commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCJASPB.getCode(), "work", work.getName() + "_行政处罚案件结案审批表.doc", ((Integer) is.available()).longValue());
				}
				long end = System.currentTimeMillis();
				//System.out.println("用poi生成word结束时间：" + start);
				//System.out.println("用poi生成word用时*******************：" + (end - start) + "ms");
			}
		}catch (Exception e) {
			//System.out.println("生成行政处罚案件结案审批表doc文件出错："+ e);
			log.debug("");
			
        }
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
}
