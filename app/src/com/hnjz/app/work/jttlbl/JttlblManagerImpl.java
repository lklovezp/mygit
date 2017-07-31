package com.hnjz.app.work.jttlbl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Calendar;
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
import com.hnjz.app.work.po.TBizAjjttlbl;
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

@Service("jttlblManager")
public class JttlblManagerImpl extends ManagerImpl implements JttlblManager ,ServletContextAware {
	
	/** 日志 */
	private static final Log log = LogFactory
			.getLog(JcdManagerImpl.class);
	
	private ServletContext sc;
	
	@Autowired
    private CommonManager commonManager;
	
	@Autowired
    protected WorkManagerImpl workManager;
	
	@Autowired
    private CommWorkManager commWorkManager;

	@Override
	public JttlblForm getJttlblForm(String applyId) {
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
        //集体讨论笔录数据
        JttlblForm jttlblForm=new JttlblForm();
		QueryCondition data = new QueryCondition();
		StringBuffer sql = new StringBuffer();
		sql.append(" from TBizAjjttlbl where taskid=:taskid ");
		data.put("taskid", applyId);
		List<TBizAjjttlbl> list = this.dao.find(sql.toString(),data);
		TBizAjjttlbl po=new TBizAjjttlbl();
		/*//获取当前登录人的所属单位
		TSysUser curUser = CtxUtil.getCurUser();
        TSysOrg org = (TSysOrg) this.find("from TSysOrg where id = (select org.id from TSysUserOrg where user.id = ? ) ", curUser.getId()).get(0);*/
		if(list!=null&&list.size()>0){
			po=list.get(0);
			jttlblForm.setId(po.getId());
			jttlblForm.setTaskid(applyId);
			jttlblForm.setTaskid(po.getTaskid());//任务ID
			jttlblForm.setAjmc(po.getAjmc());//案件名称
			jttlblForm.setTlstartsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getTlstartsj()?new Date():po.getTlstartsj()));//讨论开始时间
			jttlblForm.setTlendsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getTlendsj()?new Date():po.getTlendsj()));//讨论结束时间
			jttlblForm.setTldd(po.getTldd());//讨论地点
			jttlblForm.setZcr(po.getZcr());//主持人
			jttlblForm.setZcrzw(po.getZcrzw());//主持人职务
			jttlblForm.setJlr(po.getJlr());//记录人 
			jttlblForm.setJlrzw(po.getJlrzw());//记录人职务
			jttlblForm.setCjrys(po.getCjrys());//参加人员
			jttlblForm.setRy(po.getRy());//人员
			jttlblForm.setAjqk(po.getAjqk());//承办人汇报案件情况
			jttlblForm.setCsqk(po.getCsqk());//陈述（听证）情况
			jttlblForm.setTlyj(po.getTlyj());//参加讨论人员意见
			jttlblForm.setTljl(po.getTljl());//结论意见
		}else{//默认带过来的
			jttlblForm.setTlstartsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm",new Date()));//讨论开始时间
			//默认显示时截止时间比开始时间大两个小时
			Date date = new Date();     
			Calendar   dar=Calendar.getInstance();     
			dar.setTime(date);     
			dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
			Date sdf = dar.getTime();
			jttlblForm.setTlendsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm",sdf));//讨论结束时间
			jttlblForm.setAjmc(work.getName());//案件名称默认带值
		}
		return jttlblForm;	
	}
	
	@Override
	public void saveJttlbl(JttlblForm jttlblForm,String applyId,String wflx){
		TSysUser cur = CtxUtil.getCurUser();
		TBizAjjttlbl ajjttlbl=new TBizAjjttlbl();
		if(StringUtils.isNotBlank(jttlblForm.getId())){
			ajjttlbl=(TBizAjjttlbl)dao.get(TBizAjjttlbl.class, jttlblForm.getId());	
			ajjttlbl.setUpdateby(cur);
			ajjttlbl.setUpdated(new Date());
        }else{
        	ajjttlbl.setCreateby(cur);
        	ajjttlbl.setCreated(new Date());
        	ajjttlbl.setUpdateby(cur);
        	ajjttlbl.setUpdated(new Date());
        }
		ajjttlbl.setId(jttlblForm.getId());
		ajjttlbl.setTaskid(applyId);//任务ID
		ajjttlbl.setAjmc(jttlblForm.getAjmc());//案件名称
		try {
			ajjttlbl.setTlstartsj(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm", jttlblForm.getTlstartsj()));//讨论开始时间
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ajjttlbl.setTlendsj(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm", jttlblForm.getTlendsj()));//讨论结束时间
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ajjttlbl.setTldd(jttlblForm.getTldd());//讨论地点
		ajjttlbl.setZcr(jttlblForm.getZcr());//主持人
		ajjttlbl.setZcrzw(jttlblForm.getZcrzw());//主持人职务
		ajjttlbl.setJlr(jttlblForm.getJlr());//记录人 
		ajjttlbl.setJlrzw(jttlblForm.getJlrzw());//记录人职务
		ajjttlbl.setCjrys(jttlblForm.getCjrys());//参加人员
		ajjttlbl.setRy(jttlblForm.getRy());//人员
		ajjttlbl.setAjqk(jttlblForm.getAjqk());//承办人汇报案件情况
		ajjttlbl.setCsqk(jttlblForm.getCsqk());//陈述（听证）情况
		ajjttlbl.setTlyj(jttlblForm.getTlyj());//参加讨论人员意见
		ajjttlbl.setTljl(jttlblForm.getTljl());//结论意见
		ajjttlbl.setAreaId(cur.getAreaId());//数据区域过滤标识
		ajjttlbl.setIsActive(YnEnum.Y.getCode());
		dao.save(ajjttlbl); 
	}
	
	@Override
	public void saveShengchengXwbl(String applyId,String wflx){
		try{
			Work work = workManager.get(applyId);
			TBizAjjttlbl jttlbl=new TBizAjjttlbl();
			if (null != applyId && !"".equals(applyId.trim())) {
				String ajmc="";//案件名称
				String tlStartsj = "";//案件讨论开始时间
				String tlEndsj = "";//案件讨论开始时间
				String tldd = "";//讨论地点
				String zcr = "";//主持人
				String zcrzw = "";//主持人职务
				String jlr = "";//记录人
				String jlrzw = "";//记录人职务;
				String cjrys = "";//参加人员
				String ry = "";//人员
				String ajqk = "";//承办人汇报案件情况
				String csqk = "";//陈述（听证）情况
				String tlyj = "";//参加讨论人员意见
				String tljl = "";//参加讨论人员结论
				//查看该任务的勘察笔录
				List<TBizAjjttlbl> kcbllList = dao.find(" from TBizAjjttlbl t where t.taskid=? ", applyId);
				if (kcbllList != null && kcbllList.size() > 0) {//有记录
					jttlbl = kcbllList.get(0);
				} else {
					jttlbl = new TBizAjjttlbl();
				}

				/********************开始赋值****************/
				ajmc=jttlbl.getAjmc();//案件名称
				tlStartsj = DateUtil.getDateTime("yyyy年MM月dd日HH时mm分", jttlbl.getTlstartsj());//案件讨论开始时间
				tlEndsj = DateUtil.getDateTime("HH时mm分", jttlbl.getTlendsj());//案件讨论开始时间
				tldd = jttlbl.getTldd();//讨论地点
				zcr = jttlbl.getZcr();//主持人
				zcrzw = jttlbl.getZcrzw();//主持人职务
				jlr = jttlbl.getJlr();//记录人
				jlrzw = jttlbl.getJlrzw();//记录人职务;
				cjrys = jttlbl.getCjrys();//参加人员
				ry = jttlbl.getRy();//人员
				ajqk = jttlbl.getAjqk();//承办人汇报案件情况
				csqk = jttlbl.getCsqk();//陈述（听证）情况
				tlyj = jttlbl.getTlyj();//参加讨论人员意见
				tljl = jttlbl.getTljl();//参加讨论人员结论
				/********************赋值结束****************/
				long start = System.currentTimeMillis();
				//System.out.println("用poi生成word开始时间：" + start);
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
				String tempPath=sc.getRealPath(File.separator) + "excel/xzcf/xzcf_ajtlbl.doc";
				//poi实现word操作
				Map<String, String> param = new HashMap<String, String>();
				param.put("$ajmc$", ajmc);//案件名称
				param.put("$tlStartsj$", tlStartsj);//案件讨论开始时间
				param.put("$tlEndsj$", tlEndsj);//案件讨论开始时间
				param.put("$tldd$", tldd);//讨论地点
				param.put("$zcr$", zcr);//主持人
				param.put("$zcrzw$", zcrzw);//主持人职务
				param.put("$jlr$", jlr);//记录人
				param.put("$jlrzw$", jlrzw);//记录人职务
				param.put("$cjrys$", cjrys);//参加人员
				param.put("$ry$", ry);//人员
				param.put("$ajqk$", ajqk);//承办人汇报案件情况
				param.put("$csqk$", csqk);//陈述（听证）情况
				param.put("$tlyj$", tlyj);//参加讨论人员意见
				param.put("$tljl$", tljl);//参加讨论人员结论
				String newfile = PoiUtil.createWord(tempPath, dirPath, param);
				//////////////////////////////根据任务类型，做相应的处理(保存生成文档)///////////////////////////////////
				File file = new File(newfile);
				if (file.exists()) {
					InputStream is = new FileInputStream(file);	
					//先删除旧的，再保存新的；
					//commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCJTTLBL.getCode());	
					commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCJTTLBL.getCode(), "work", work.getName() + "_案件集体讨论笔录.doc", ((Integer) is.available()).longValue());
				}
				long end = System.currentTimeMillis();
				//System.out.println("用poi生成word结束时间：" + start);
				//System.out.println("用poi生成word用时*******************：" + (end - start) + "ms");
			}
		}catch (Exception e) {
			//System.out.println("生成案件集体讨论笔录doc文件出错："+ e);
			log.error("", e);
        }
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
}
