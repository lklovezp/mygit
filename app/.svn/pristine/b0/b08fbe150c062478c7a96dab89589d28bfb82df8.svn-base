package com.hnjz.app.work.sdhz;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
import com.hnjz.app.work.po.TBizSdhz;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.zx.BlZfdxForm;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.PoiUtil;
import com.hnjz.sys.po.TSysUser;

@Service("sdhzManager")
public class SdhzManagerImpl extends ManagerImpl implements SdhzManager ,ServletContextAware {
	
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
	public SdhzForm getSxgzsFormData(String applyId) {
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
        SdhzForm sdhzForm=new SdhzForm();
		QueryCondition data = new QueryCondition();
		StringBuffer sql = new StringBuffer();
		sql.append(" from TBizSdhz where taskid=:taskid ");
		data.put("taskid", applyId);
		List<TBizSdhz> list = this.dao.find(sql.toString(),data);
		TBizSdhz po=new TBizSdhz();
		if(list!=null&&list.size()>0){
			po=list.get(0);
			sdhzForm.setId(po.getId());
			sdhzForm.setTaskid(applyId);//任务ID
			sdhzForm.setSdwsmc(po.getSdwsmc());//送达文书名称及文号
			sdhzForm.setJsrmc(po.getJsrmc());//受送达人名称或姓名
			sdhzForm.setJsrdz(po.getJsrdz());//送达地点
			sdhzForm.setSdfs(po.getSdfs());//送达方式
			sdhzForm.setRemark(po.getRemark());//备注
		}
		return sdhzForm;
	}
	
	@Override
	public void saveSxgzs(SdhzForm sdhzForm,String applyId,String wflx){
		TSysUser cur = CtxUtil.getCurUser();
		TBizSdhz sdhz=new TBizSdhz();
		if(StringUtils.isNotBlank(sdhzForm.getId())){
			sdhz=(TBizSdhz)dao.get(TBizSdhz.class, sdhzForm.getId());	
			sdhz.setUpdateby(cur);
			sdhz.setUpdated(new Date());
        }else{
        	sdhz.setCreateby(cur);
        	sdhz.setCreated(new Date());
        	sdhz.setUpdateby(cur);
        	sdhz.setUpdated(new Date());
        }
		sdhz.setId(sdhzForm.getId());
		sdhz.setTaskid(applyId);//任务ID
		sdhz.setSdwsmc(sdhzForm.getSdwsmc());//送达文书名称及文号
		sdhz.setJsrmc(sdhzForm.getJsrmc());//受送达人名称或姓名
		sdhz.setJsrdz(sdhzForm.getJsrdz());//送达地点
		sdhz.setSdfs(sdhzForm.getSdfs());//送达方式
		sdhz.setRemark(sdhzForm.getRemark());//备注
		sdhz.setAreaId(cur.getAreaId());//数据区域过滤标识
		sdhz.setIsActive(YnEnum.Y.getCode());
		dao.save(sdhz);
		
	}
	
	@Override
	public void saveShengchengSxgzs(String applyId,String wflx){
		try{
			Work work = workManager.get(applyId);
			TBizSdhz sdhz=new TBizSdhz();
			//区域作工作单位
			//TSysUser user = CtxUtil.getCurUser();
			if (null != applyId && !"".equals(applyId.trim())) {
				String sdwsmc="";//送达文书名称及文号
				String jsrmc = "";//受送达人名称或姓名
				String jsrdz = "";//送达地点
				String sdfs = "";//送达方式
				String remark = "";//备注
				//查看该任务的勘察笔录
				List<TBizSdhz> kcbllList = dao.find(" from TBizSdhz t where t.taskid=? ", applyId);
				if (kcbllList != null && kcbllList.size() > 0) {//有记录
					sdhz = kcbllList.get(0);
				} else {
					sdhz = new TBizSdhz();
				}

				/********************开始赋值****************/
				sdwsmc = sdhz.getSdwsmc();//送达文书名称及文号
				jsrmc = sdhz.getJsrmc();//受送达人名称或姓名
				jsrdz = sdhz.getJsrdz();//送达地点
				sdfs = sdhz.getSdfs();//送达方式
				remark = sdhz.getRemark();//备注
				/********************赋值结束****************/
				long start = System.currentTimeMillis();
				//System.out.println("用poi生成word开始时间：" + start);
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
				String tempPath=sc.getRealPath(File.separator) + "excel/xzcf/xzcf_sdhz.doc";
				//poi实现word操作
				Map<String, String> param = new HashMap<String, String>();
				param.put("$sdwsmc$", sdwsmc);
				param.put("$jsrmc$", jsrmc);
				param.put("$jsrdz$", jsrdz);
				param.put("$sdfs$", sdfs);
				param.put("$remark$", remark);
				String newfile = PoiUtil.createWord(tempPath, dirPath, param);
				//////////////////////////////根据任务类型，做相应的处理(保存生成文档)///////////////////////////////////
				File file = new File(newfile);
				if (file.exists()) {
					InputStream is = new FileInputStream(file);	
					//先删除旧的，再保存新的；
					//commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCSDHZ.getCode());	
					commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCSDHZ.getCode(), "work", work.getName() + "_送达回证.doc", ((Integer) is.available()).longValue());
				}
				long end = System.currentTimeMillis();
				//System.out.println("用poi生成word结束时间：" + start);
				//System.out.println("用poi生成word用时*******************：" + (end - start) + "ms");
			}
		}catch (Exception e) {
			//System.out.println("生成送达回证doc文件出错："+ e);
			log.error("", e);
        }
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
}
