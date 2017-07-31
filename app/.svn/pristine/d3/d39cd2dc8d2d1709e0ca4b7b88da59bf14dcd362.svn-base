package com.hnjz.app.work.sxgzs;

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
import com.hnjz.app.work.po.TBizXzcfsxgzs;
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

@Service("sxgzsManager")
public class SxgzsManagerImpl extends ManagerImpl implements SxgzsManager ,ServletContextAware {
	
	/** 日志 */
	private static final Log log = LogFactory
			.getLog(JcdManagerImpl.class);
	
	private ServletContext sc;
	
	@Autowired
    private CommonManager                 commonManager;
	
	@Autowired
    protected WorkManagerImpl workManager;
	
	@Autowired
    private CommWorkManager      commWorkManager;
	
	@Override
	public SxgzsForm getSxgzsFormData(String applyId) {
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
		SxgzsForm sxgzsForm=new SxgzsForm();
		QueryCondition data = new QueryCondition();
		StringBuffer sql = new StringBuffer();
		sql.append(" from TBizXzcfsxgzs where taskid=:taskid ");
		data.put("taskid", applyId);
		List<TBizXzcfsxgzs> list = this.dao.find(sql.toString(),data);
		TBizXzcfsxgzs po=new TBizXzcfsxgzs();
		if(list!=null&&list.size()>0){
			po=list.get(0);
			sxgzsForm.setId(po.getId());
			sxgzsForm.setTaskid(applyId);//任务ID
			sxgzsForm.setBmmc(po.getBmmc());//文书中环保部门简称
			sxgzsForm.setWsmc(po.getWsmc());//文书名称
			sxgzsForm.setBh(po.getBh());//文书编号
			sxgzsForm.setCw(po.getCw());//当事人名称或者姓名，与营业执照、居民身份证一致
			sxgzsForm.setJcsj(DateUtil.getDateTime("yyyy-MM-dd", null==po.getJcsj()?new Date():po.getJcsj()));//检查时间
			sxgzsForm.setJlnr(po.getJlnr());//违法行为内容
			sxgzsForm.setWfajzj(po.getWfajzj());//列举证据形式，阐述要证明的内容
			sxgzsForm.setRules(po.getRules());//违反的法律法规 
			sxgzsForm.setMeasure(po.getMeasure());//依据的法律法规
			sxgzsForm.setXzcfzd(po.getXzcfzd());//阐述适用行政处罚裁量基准制度
			sxgzsForm.setXzcf(po.getXzcf());//行政处罚
			sxgzsForm.setLxr(po.getLxr());//联系人
			sxgzsForm.setLxrdh(po.getLxrdh());//联系人电话
			sxgzsForm.setLxrdz(po.getLxrdz());//联系人地址
			sxgzsForm.setPostCode(po.getPostCode());//邮政编码
		}else{//默认带过来的
			sxgzsForm.setJcsj(DateUtil.getDateTime("yyyy-MM-dd",new Date()));//检查时间
			sxgzsForm.setCw(blZfdxForm.getLawobjname());//获取执法的对象名称（单位或个人名称）
		}
		return sxgzsForm;	
	}
	
	@Override
	public void saveSxgzs(SxgzsForm sxgzsForm,String applyId){
		TSysUser cur = CtxUtil.getCurUser();
		TBizXzcfsxgzs tBizXzcfsxgzs=new TBizXzcfsxgzs();
		if(StringUtils.isNotBlank(sxgzsForm.getId())){
			tBizXzcfsxgzs=(TBizXzcfsxgzs)dao.get(TBizXzcfsxgzs.class, sxgzsForm.getId());	
			tBizXzcfsxgzs.setUpdateby(cur);
			tBizXzcfsxgzs.setUpdated(new Date());
        }else{
        	tBizXzcfsxgzs.setCreateby(cur);
        	tBizXzcfsxgzs.setCreated(new Date());
        	tBizXzcfsxgzs.setUpdateby(cur);
        	tBizXzcfsxgzs.setUpdated(new Date());
        }
		tBizXzcfsxgzs.setId(sxgzsForm.getId());
		tBizXzcfsxgzs.setTaskid(applyId);//任务ID
		tBizXzcfsxgzs.setBmmc(sxgzsForm.getBmmc());//文书中环保部门简称
		tBizXzcfsxgzs.setWsmc(sxgzsForm.getWsmc());//文书名称
		tBizXzcfsxgzs.setBh(sxgzsForm.getBh());//文书编号
		tBizXzcfsxgzs.setCw(sxgzsForm.getCw());//当事人名称或者姓名，与营业执照、居民身份证一致
		if(StringUtils.isNotBlank(sxgzsForm.getJcsj())){
			try {
				tBizXzcfsxgzs.setJcsj(DateUtil.convertStringToDate("yyyy-MM-dd", sxgzsForm.getJcsj()));//检查时间
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		tBizXzcfsxgzs.setJlnr(sxgzsForm.getJlnr());//违法行为内容
		tBizXzcfsxgzs.setWfajzj(sxgzsForm.getWfajzj());//列举证据形式，阐述要证明的内容
		tBizXzcfsxgzs.setRules(sxgzsForm.getRules());//违反的法律法规 
		tBizXzcfsxgzs.setMeasure(sxgzsForm.getMeasure());//依据的法律法规
		tBizXzcfsxgzs.setXzcfzd(sxgzsForm.getXzcfzd());//阐述适用行政处罚裁量基准制度
		tBizXzcfsxgzs.setXzcf(sxgzsForm.getXzcf());//行政处罚
		tBizXzcfsxgzs.setLxr(sxgzsForm.getLxr());//联系人
		tBizXzcfsxgzs.setLxrdh(sxgzsForm.getLxrdh());//联系人电话
		tBizXzcfsxgzs.setLxrdz(sxgzsForm.getLxrdz());//联系人地址
		tBizXzcfsxgzs.setPostCode(sxgzsForm.getPostCode());//邮政编码
		tBizXzcfsxgzs.setAreaId(cur.getAreaId());//数据区域过滤标识
		tBizXzcfsxgzs.setIsActive(YnEnum.Y.getCode());	
		dao.save(tBizXzcfsxgzs);
	}
	
	@Override
	public void saveShengchengSxgzs(String applyId){
		try{
			Work work = workManager.get(applyId);
			TBizXzcfsxgzs sxgzs=new TBizXzcfsxgzs();
			if (null != applyId && !"".equals(applyId.trim())) {
				String bmmc="";//文书编号中的部门简称
				String wsmc = "";//文书名称（一般简写为年份2016）
				String bh = "";//文书编号
				String cw = "";//当事人名称或者姓名，与营业执照、居民身份证一致
				String jcsj = "";//检查时间
				String jlnr = "";//违法行为内容
				String wfajfj = "";//列举证据形式，阐述要证明的内容
				String rules = "";//违反的法律法规
				String measure = "";//依据的法律法规
				String xzcfzd = "";//阐述适用行政处罚裁量基准制度
				String xzcf = "";//行政处罚
				String lxr = "";//联系人
				String lxrdh = "";//联系人电话
				String lxrdz = "";//联系人地址
				String postcode = "";//邮编
				//查看该任务中的行政处罚事先告知书记录
				List<TBizXzcfsxgzs> sxgzslList = dao.find(" from TBizXzcfsxgzs t where t.taskid=? ", applyId);
				if (sxgzslList != null && sxgzslList.size() > 0) {//有记录
					sxgzs = sxgzslList.get(0);
				} else {
					sxgzs = new TBizXzcfsxgzs();
				}

				/********************开始赋值****************/
				bmmc=sxgzs.getBmmc();//文书编号中的部门简称
				wsmc = sxgzs.getWsmc();//文书名称（一般简写为年份2016）
				bh = sxgzs.getBh();//文书编号
				cw = sxgzs.getCw();//当事人名称或者姓名，与营业执照、居民身份证一致
				jcsj = DateUtil.getDateTime("yyyy年MM月dd日",sxgzs.getJcsj());//检查时间
				//String gzsj = jcsj.substring(0, 4)+"年"+jcsj.substring(5, 7)+"月"+jcsj.substring(8, 10)+"日";
				jlnr = sxgzs.getJlnr();//违法行为内容
				wfajfj = sxgzs.getWfajzj();//列举证据形式，阐述要证明的内容
				rules = sxgzs.getRules();//违反的法律法规
				measure = sxgzs.getMeasure();//依据的法律法规
				xzcfzd = sxgzs.getXzcfzd();//阐述适用行政处罚裁量基准制度
				xzcf = sxgzs.getXzcf();//行政处罚
				lxr = sxgzs.getLxr();//联系人
				lxrdh = sxgzs.getLxrdh();//联系人电话
				lxrdz = sxgzs.getLxrdz();//联系人地址
				postcode = sxgzs.getPostCode();//邮编
				/********************赋值结束****************/
				long start = System.currentTimeMillis();
				//System.out.println("用poi生成word开始时间：" + start);
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
				String tempPath=sc.getRealPath(File.separator) + "excel/xzcf/xzcf_sxgzs.doc";
				//poi实现word操作
				Map<String, String> param = new HashMap<String, String>();
				param.put("$bmmc$", bmmc);//文书编号中的部门简称
				param.put("$wsmc$", wsmc);//文书名称（一般简写为年份2016）
				param.put("$bh$", bh);//文书编号
				param.put("$cw$", cw);//当事人名称或者姓名，与营业执照、居民身份证一致
				param.put("$jcsj$", jcsj);//检查时间
				param.put("$jlnr$", jlnr);//违法行为内容
				param.put("$wfajfj$", wfajfj);//列举证据形式，阐述要证明的内容
				param.put("$rules$", rules);//违反的法律法规
				param.put("$measure$", measure);//依据的法律法规
				param.put("$xzcfzd$", xzcfzd);//阐述适用行政处罚裁量基准制度
				param.put("$xzcf$", xzcf);//行政处罚
				param.put("$lxr$", lxr);//联系人
				param.put("$lxrdh$", lxrdh);//联系人电话
				param.put("$lxrdz$", lxrdz);////联系人地址
				param.put("$postcode$", postcode);//邮编

				String newfile = PoiUtil.createWord(tempPath, dirPath, param);

				//////////////////////////////根据任务类型，做相应的处理(保存生成文档)///////////////////////////////////
				File file = new File(newfile);
				if (file.exists()) {
					InputStream is = new FileInputStream(file);	
					//先删除旧的，再保存新的；
					//commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCSXGZS.getCode());	
					commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCSXGZS.getCode(), "work", work.getName() + "_行政处罚事先告知书.doc", ((Integer) is.available()).longValue());
				}
				long end = System.currentTimeMillis();
				//System.out.println("用poi生成word结束时间：" + start);
				//System.out.println("用poi生成word用时*******************：" + (end - start) + "ms");
			}
		}catch (Exception e) {
			//System.out.println("生成行政处罚事先告知书文件出错："+ e);
			log.error("", e);
        }
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
}
