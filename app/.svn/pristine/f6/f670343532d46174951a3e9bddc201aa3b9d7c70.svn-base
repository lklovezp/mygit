package com.hnjz.app.work.cfjds;

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
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.work.jcd.JcdManagerImpl;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.po.TBizXzcfjds;
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
import com.hnjz.sys.po.TSysUser;

@Service("cfjdsManager")
public class CfjdsManagerImpl extends ManagerImpl implements CfjdsManager ,ServletContextAware {
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
	
	@Autowired
	private LawobjManager lawobjManager;

	@Override
	public CfjdsForm getCfjdsFormData(String applyId) {
		//执法对象信息
		BlZfdxForm blZfdxForm=new BlZfdxForm();
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
		TDataLawobj lawobj = (TDataLawobj) lawobjManager.get(TDataLawobj.class, blZfdxForm.getId());
        CfjdsForm cfjdsForm=new CfjdsForm();
		QueryCondition data = new QueryCondition();
		StringBuffer sql = new StringBuffer();
		sql.append(" from TBizXzcfjds where taskid=:taskid ");
		data.put("taskid", applyId);
		List<TBizXzcfjds> list = this.dao.find(sql.toString(),data);
		TBizXzcfjds po=new TBizXzcfjds();
		if(list!=null&&list.size()>0){
			po=list.get(0);
			cfjdsForm.setId(po.getId());
			cfjdsForm.setTaskid(applyId);//任务ID
			cfjdsForm.setBmmc(po.getBmmc());//文书中环保部门简称
			cfjdsForm.setWsmc(po.getWsmc());//文书名称
			cfjdsForm.setBh(po.getBh());//文书编号
			cfjdsForm.setDsr(po.getDsr());//当事人,（当事人名称或者姓名，与营业执照、居民身份证一致）
			cfjdsForm.setZcbh(po.getZcbh());//营业执照注册号（公民身份证号码）
			cfjdsForm.setZcjgbm(po.getZcjgbm());//组织机构代码证
			cfjdsForm.setZcdz(po.getZcdz());//地址
			cfjdsForm.setFddbr(po.getFddbr());//法定代表人
			cfjdsForm.setJcsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getJcsj()?new Date():po.getJcsj()));//检查日期
			cfjdsForm.setWfxw(po.getWfxw());//环境违法行为
			cfjdsForm.setWfzj(po.getWfzj());//违法行为的证据（列举）
			cfjdsForm.setRules(po.getRules());//违反的法律法规
			cfjdsForm.setGzsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getGzsj()?new Date():po.getGzsj()));//行政处罚事先告知书时间
			cfjdsForm.setGzsmc(po.getGzsmc());//行政处罚事先告知书（xx[]xx号）
			cfjdsForm.setDsrsbsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getDsrsbsj()?new Date():po.getDsrsbsj()));//当事人申辩时间
			cfjdsForm.setDsrsbnr(po.getDsrsbnr());//当事人申辩内容
			cfjdsForm.setMeasure(po.getMeasure());//依据的法律法规
			cfjdsForm.setXzcfzd(po.getXzcfzd());//阐述适用行政处罚裁量基准制度
			cfjdsForm.setXzcf(po.getXzcf());//行政处罚
			cfjdsForm.setYhhm(po.getYhhm());//银行户名
			cfjdsForm.setSkyh(po.getSkyh());//收款银行
			cfjdsForm.setYhzh(po.getYhzh());//银行账号
			cfjdsForm.setFksm(po.getFksm());//罚款金额
			cfjdsForm.setZfmc(po.getZfmc());//政府名称
			cfjdsForm.setHbtmc(po.getHbtmc());//环境保护厅名称
			cfjdsForm.setFymc(po.getFymc());//法院名称
		}else{//默认带过来的
			cfjdsForm.setDsr(blZfdxForm.getLawobjname());//执法对象名称
			cfjdsForm.setZcdz(blZfdxForm.getAddress());//执法对象地址
			cfjdsForm.setFddbr(blZfdxForm.getManager());//法定代表人
			//cfjdsForm.setZcjgbm(lawobj.);//组织机构代码
			//cfjdsForm.setFddbr(blZfdxForm.getManager());//营业执照号
			cfjdsForm.setJcsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm",new Date()));//检查时间
			cfjdsForm.setGzsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm",new Date()));//行政处罚事先告知书时间
			cfjdsForm.setDsrsbsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm",new Date()));//当事人申辩时间
		}
		return cfjdsForm;	
	}
	
	@Override
	public void saveSxgzs(CfjdsForm cfjdsForm,String applyId,String wflx){
		TSysUser cur = CtxUtil.getCurUser();
		TBizXzcfjds cfjds=new TBizXzcfjds();
		if(StringUtils.isNotBlank(cfjdsForm.getId())){
			cfjds=(TBizXzcfjds)dao.get(TBizXzcfjds.class, cfjdsForm.getId());	
			cfjds.setUpdateby(cur);
			cfjds.setUpdated(new Date());
        }else{
        	cfjds.setCreateby(cur);
        	cfjds.setCreated(new Date());
        	cfjds.setUpdateby(cur);
        	cfjds.setUpdated(new Date());
        }
		cfjds.setId(cfjdsForm.getId());
		cfjds.setTaskid(applyId);//任务ID
		cfjds.setBmmc(cfjdsForm.getBmmc());//文书中环保部门简称
		cfjds.setWsmc(cfjdsForm.getWsmc());//文书名称
		cfjds.setBh(cfjdsForm.getBh());//文书编号
		cfjds.setDsr(cfjdsForm.getDsr());//当事人,（当事人名称或者姓名，与营业执照、居民身份证一致）
		cfjds.setZcbh(cfjdsForm.getZcbh());//营业执照注册号（公民身份证号码）
		cfjds.setZcjgbm(cfjdsForm.getZcjgbm());//组织机构代码证
		cfjds.setZcdz(cfjdsForm.getZcdz());//地址
		cfjds.setFddbr(cfjdsForm.getFddbr());//法定代表人
		try {
			cfjds.setJcsj(DateUtil.convertStringToDate("yyyy-MM-dd", cfjdsForm.getJcsj()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//检查日期
		cfjds.setWfxw(cfjdsForm.getWfxw());//环境违法行为
		cfjds.setWfzj(cfjdsForm.getWfzj());//违法行为的证据（列举）
		cfjds.setRules(cfjdsForm.getRules());//违反的法律法规
		try {
			cfjds.setGzsj(DateUtil.convertStringToDate("yyyy-MM-dd", cfjdsForm.getGzsj()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//行政处罚事先告知书时间
		cfjds.setGzsmc(cfjdsForm.getGzsmc());//行政处罚事先告知书（xx[]xx号）
		try {
			cfjds.setDsrsbsj(DateUtil.convertStringToDate("yyyy-MM-dd", cfjdsForm.getDsrsbsj()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//当事人申辩时间
		cfjds.setDsrsbnr(cfjdsForm.getDsrsbnr());//当事人申辩内容
		cfjds.setMeasure(cfjdsForm.getMeasure());//依据的法律法规
		cfjds.setXzcfzd(cfjdsForm.getXzcfzd());//阐述适用行政处罚裁量基准制度
		cfjds.setXzcf(cfjdsForm.getXzcf());//行政处罚
		cfjds.setYhhm(cfjdsForm.getYhhm());//银行户名
		cfjds.setSkyh(cfjdsForm.getSkyh());//收款银行
		cfjds.setYhzh(cfjdsForm.getYhzh());//银行账号
		cfjds.setFksm(cfjdsForm.getFksm());//罚款金额
		cfjds.setZfmc(cfjdsForm.getZfmc());//政府名称
		cfjds.setHbtmc(cfjdsForm.getHbtmc());//环境保护厅名称
		cfjds.setFymc(cfjdsForm.getFymc());//法院名称
		cfjds.setAreaId(cur.getAreaId());//数据区域过滤标识
		cfjds.setIsActive(YnEnum.Y.getCode());
		dao.save(cfjds);
	}
	
	@Override
	public void saveShengchengSxgzs(String applyId,String wflx){
		try{
			Work work = workManager.get(applyId);
			TBizXzcfjds cfjds=new TBizXzcfjds();
			if (null != applyId && !"".equals(applyId.trim())) {
				String bmmc="";//文书中环保部门简称
				String wsmc = "";//文书名称
				String bh = "";//文书编号
				String dsr = "";//当事人名称或者姓名，与营业执照、居民身份证一致
				String zcbh = "";//营业执照注册号（公民身份证号码）
				String zcjgbm = "";//组织机构代码证
				String zcdz = "";//注册地址
				String fddbr = "";//法定代表人
				String jcsj = "";//检查日期
				String wfxw = "";//环境违法行为
				String wfzj = "";//违法行为的证据（列举）
				String rules = "";//违反的法律法规
				String gzsj = "";//行政处罚事先告知书时间
				String gzsmc = "";//行政处罚事先告知书（xx[]xx号）
				String dsrsbsj = "";//当事人申辩时间
				String dsrsbnr = "";//当事人申辩内容
				String measure = "";//依据的法律法规
				String xzcfzd = "";//阐述适用行政处罚裁量基准制度
				String xzcf = "";//行政处罚
				String skyh = "";//收款银行
				String yhhm = "";//银行户名
				String yhzh = "";//银行账号
				String fksm = "";//罚款金额
				String zfmc = "";//政府名称
				String hbtmc = "";//环境保护厅名称
				String fymc = "";//法院名称
				//查看该任务的勘察笔录
				List<TBizXzcfjds> kcbllList = dao.find(" from TBizXzcfjds t where t.taskid=? ", applyId);
				if (kcbllList != null && kcbllList.size() > 0) {//有记录
					cfjds = kcbllList.get(0);
				} else {
					cfjds = new TBizXzcfjds();
				}

				/********************开始赋值****************/
				bmmc = cfjds.getBmmc();//文书中环保部门简称
				wsmc = cfjds.getWsmc();//文书名称
				bh = cfjds.getBh();//文书编号
				dsr = cfjds.getDsr();//当事人名称或者姓名，与营业执照、居民身份证一致
				zcbh = cfjds.getZcbh();//营业执照注册号（公民身份证号码）
				zcjgbm = cfjds.getZcjgbm();//组织机构代码证
				zcdz = cfjds.getZcdz();//注册地址
				fddbr = cfjds.getFddbr();//法定代表人
				jcsj = DateUtil.getDateTime("yyyy年MM月dd日",cfjds.getJcsj());//检查日期
				wfxw = cfjds.getWfxw();//环境违法行为
				wfzj = cfjds.getWfzj();//违法行为的证据（列举）
				rules = cfjds.getRules();//违反的法律法规
				gzsj = DateUtil.getDateTime("yyyy年MM月dd日", cfjds.getGzsj());//行政处罚事先告知书时间
				gzsmc = cfjds.getGzsmc();//行政处罚事先告知书（xx[]xx号）
				dsrsbsj = DateUtil.getDateTime("yyyy年MM月dd日", cfjds.getDsrsbsj());//当事人申辩时间
				dsrsbnr = cfjds.getDsrsbnr();//当事人申辩内容
				measure = cfjds.getMeasure();//依据的法律法规
				xzcfzd = cfjds.getXzcfzd();//阐述适用行政处罚裁量基准制度
				xzcf = cfjds.getXzcf();//行政处罚
				skyh = cfjds.getSkyh();//收款银行
				yhhm = cfjds.getYhhm();//银行户名
				yhzh = cfjds.getYhzh();//银行账号
				fksm = cfjds.getFksm();//罚款金额
				zfmc = cfjds.getZfmc();//政府名称
				hbtmc = cfjds.getHbtmc();//环境保护厅名称
				fymc = cfjds.getFymc();//法院名称
				/********************赋值结束****************/
				long start = System.currentTimeMillis();
				//System.out.println("用poi生成word开始时间：" + start);
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
				String tempPath=sc.getRealPath(File.separator) + "excel/xzcf/xzcf_xzcfjds.doc";
				//poi实现word操作
				Map<String, String> param = new HashMap<String, String>();
				param.put("$bmmc$", bmmc);//文书中环保部门简称
				param.put("$wsmc$", wsmc);//文书名称
				param.put("$bh$", bh);//文书编号
				param.put("$dsr$", dsr);//当事人名称或者姓名，与营业执照、居民身份证一致
				param.put("$zcbh$", zcbh);//营业执照注册号（公民身份证号码）
				param.put("$zzjgbm$", zcjgbm);//组织机构代码证
				param.put("$zcdz$", zcdz);//注册地址
				param.put("$fddbr$", fddbr);//法定代表人
				param.put("$jcsj$", jcsj);//检查日期
				param.put("$wfxw$", wfxw);//环境违法行为
				param.put("$wfzj$", wfzj);//违法行为的证据（列举）
				param.put("$rules$", rules);//违反的法律法规
				param.put("$gzsj$", gzsj);//行政处罚事先告知书时间
				param.put("$gzsmc$", gzsmc);//行政处罚事先告知书（xx[]xx号）
				param.put("$dsrsbsj$", dsrsbsj);//当事人申辩时间
				param.put("$dsrsbnr$", dsrsbnr);//当事人申辩内容
				param.put("$measure$", measure);//依据的法律法规
				param.put("$xzcfzd$", xzcfzd);//阐述适用行政处罚裁量基准制度
				param.put("$xzcf$", xzcf);//行政处罚
				param.put("$skyh$", skyh);//收款银行
				param.put("$yhhm$", yhhm);//银行户名
				param.put("$yhzh$", yhzh);//银行账号
				param.put("$fksm$", fksm);//罚款金额
				param.put("$zfmc$", zfmc);//政府名称
				param.put("$hbtmc$", hbtmc);//环境保护厅名称
				param.put("$fymc$", fymc);//法院名称
				String newfile = PoiUtil.createWord(tempPath, dirPath, param);
				//////////////////////////////根据任务类型，做相应的处理(保存生成文档)///////////////////////////////////
				File file = new File(newfile);
				if (file.exists()) {
					InputStream is = new FileInputStream(file);	
					//先删除旧的，再保存新的；
					//commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCXZCFJDS.getCode());	
					commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCXZCFJDS.getCode(), "work", work.getName() + "_行政处罚决定书.doc", ((Integer) is.available()).longValue());
				}
				long end = System.currentTimeMillis();
				//System.out.println("用poi生成word结束时间：" + start);
				//System.out.println("用poi生成word用时*******************：" + (end - start) + "ms");
			}
		}catch (Exception e) {
			//System.out.println("生成行政处罚决定书doc文件出错："+ e);
			log.error("", e);
        }
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
}
