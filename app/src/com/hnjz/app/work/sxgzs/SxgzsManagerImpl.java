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
	
	/** ��־ */
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
		//ִ��������Ϣ
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
			sxgzsForm.setTaskid(applyId);//����ID
			sxgzsForm.setBmmc(po.getBmmc());//�����л������ż��
			sxgzsForm.setWsmc(po.getWsmc());//��������
			sxgzsForm.setBh(po.getBh());//������
			sxgzsForm.setCw(po.getCw());//���������ƻ�����������Ӫҵִ�ա���������֤һ��
			sxgzsForm.setJcsj(DateUtil.getDateTime("yyyy-MM-dd", null==po.getJcsj()?new Date():po.getJcsj()));//���ʱ��
			sxgzsForm.setJlnr(po.getJlnr());//Υ����Ϊ����
			sxgzsForm.setWfajzj(po.getWfajzj());//�о�֤����ʽ������Ҫ֤��������
			sxgzsForm.setRules(po.getRules());//Υ���ķ��ɷ��� 
			sxgzsForm.setMeasure(po.getMeasure());//���ݵķ��ɷ���
			sxgzsForm.setXzcfzd(po.getXzcfzd());//����������������������׼�ƶ�
			sxgzsForm.setXzcf(po.getXzcf());//��������
			sxgzsForm.setLxr(po.getLxr());//��ϵ��
			sxgzsForm.setLxrdh(po.getLxrdh());//��ϵ�˵绰
			sxgzsForm.setLxrdz(po.getLxrdz());//��ϵ�˵�ַ
			sxgzsForm.setPostCode(po.getPostCode());//��������
		}else{//Ĭ�ϴ�������
			sxgzsForm.setJcsj(DateUtil.getDateTime("yyyy-MM-dd",new Date()));//���ʱ��
			sxgzsForm.setCw(blZfdxForm.getLawobjname());//��ȡִ���Ķ������ƣ���λ��������ƣ�
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
		tBizXzcfsxgzs.setTaskid(applyId);//����ID
		tBizXzcfsxgzs.setBmmc(sxgzsForm.getBmmc());//�����л������ż��
		tBizXzcfsxgzs.setWsmc(sxgzsForm.getWsmc());//��������
		tBizXzcfsxgzs.setBh(sxgzsForm.getBh());//������
		tBizXzcfsxgzs.setCw(sxgzsForm.getCw());//���������ƻ�����������Ӫҵִ�ա���������֤һ��
		if(StringUtils.isNotBlank(sxgzsForm.getJcsj())){
			try {
				tBizXzcfsxgzs.setJcsj(DateUtil.convertStringToDate("yyyy-MM-dd", sxgzsForm.getJcsj()));//���ʱ��
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		tBizXzcfsxgzs.setJlnr(sxgzsForm.getJlnr());//Υ����Ϊ����
		tBizXzcfsxgzs.setWfajzj(sxgzsForm.getWfajzj());//�о�֤����ʽ������Ҫ֤��������
		tBizXzcfsxgzs.setRules(sxgzsForm.getRules());//Υ���ķ��ɷ��� 
		tBizXzcfsxgzs.setMeasure(sxgzsForm.getMeasure());//���ݵķ��ɷ���
		tBizXzcfsxgzs.setXzcfzd(sxgzsForm.getXzcfzd());//����������������������׼�ƶ�
		tBizXzcfsxgzs.setXzcf(sxgzsForm.getXzcf());//��������
		tBizXzcfsxgzs.setLxr(sxgzsForm.getLxr());//��ϵ��
		tBizXzcfsxgzs.setLxrdh(sxgzsForm.getLxrdh());//��ϵ�˵绰
		tBizXzcfsxgzs.setLxrdz(sxgzsForm.getLxrdz());//��ϵ�˵�ַ
		tBizXzcfsxgzs.setPostCode(sxgzsForm.getPostCode());//��������
		tBizXzcfsxgzs.setAreaId(cur.getAreaId());//����������˱�ʶ
		tBizXzcfsxgzs.setIsActive(YnEnum.Y.getCode());	
		dao.save(tBizXzcfsxgzs);
	}
	
	@Override
	public void saveShengchengSxgzs(String applyId){
		try{
			Work work = workManager.get(applyId);
			TBizXzcfsxgzs sxgzs=new TBizXzcfsxgzs();
			if (null != applyId && !"".equals(applyId.trim())) {
				String bmmc="";//�������еĲ��ż��
				String wsmc = "";//�������ƣ�һ���дΪ���2016��
				String bh = "";//������
				String cw = "";//���������ƻ�����������Ӫҵִ�ա���������֤һ��
				String jcsj = "";//���ʱ��
				String jlnr = "";//Υ����Ϊ����
				String wfajfj = "";//�о�֤����ʽ������Ҫ֤��������
				String rules = "";//Υ���ķ��ɷ���
				String measure = "";//���ݵķ��ɷ���
				String xzcfzd = "";//����������������������׼�ƶ�
				String xzcf = "";//��������
				String lxr = "";//��ϵ��
				String lxrdh = "";//��ϵ�˵绰
				String lxrdz = "";//��ϵ�˵�ַ
				String postcode = "";//�ʱ�
				//�鿴�������е������������ȸ�֪���¼
				List<TBizXzcfsxgzs> sxgzslList = dao.find(" from TBizXzcfsxgzs t where t.taskid=? ", applyId);
				if (sxgzslList != null && sxgzslList.size() > 0) {//�м�¼
					sxgzs = sxgzslList.get(0);
				} else {
					sxgzs = new TBizXzcfsxgzs();
				}

				/********************��ʼ��ֵ****************/
				bmmc=sxgzs.getBmmc();//�������еĲ��ż��
				wsmc = sxgzs.getWsmc();//�������ƣ�һ���дΪ���2016��
				bh = sxgzs.getBh();//������
				cw = sxgzs.getCw();//���������ƻ�����������Ӫҵִ�ա���������֤һ��
				jcsj = DateUtil.getDateTime("yyyy��MM��dd��",sxgzs.getJcsj());//���ʱ��
				//String gzsj = jcsj.substring(0, 4)+"��"+jcsj.substring(5, 7)+"��"+jcsj.substring(8, 10)+"��";
				jlnr = sxgzs.getJlnr();//Υ����Ϊ����
				wfajfj = sxgzs.getWfajzj();//�о�֤����ʽ������Ҫ֤��������
				rules = sxgzs.getRules();//Υ���ķ��ɷ���
				measure = sxgzs.getMeasure();//���ݵķ��ɷ���
				xzcfzd = sxgzs.getXzcfzd();//����������������������׼�ƶ�
				xzcf = sxgzs.getXzcf();//��������
				lxr = sxgzs.getLxr();//��ϵ��
				lxrdh = sxgzs.getLxrdh();//��ϵ�˵绰
				lxrdz = sxgzs.getLxrdz();//��ϵ�˵�ַ
				postcode = sxgzs.getPostCode();//�ʱ�
				/********************��ֵ����****************/
				long start = System.currentTimeMillis();
				//System.out.println("��poi����word��ʼʱ�䣺" + start);
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
				String tempPath=sc.getRealPath(File.separator) + "excel/xzcf/xzcf_sxgzs.doc";
				//poiʵ��word����
				Map<String, String> param = new HashMap<String, String>();
				param.put("$bmmc$", bmmc);//�������еĲ��ż��
				param.put("$wsmc$", wsmc);//�������ƣ�һ���дΪ���2016��
				param.put("$bh$", bh);//������
				param.put("$cw$", cw);//���������ƻ�����������Ӫҵִ�ա���������֤һ��
				param.put("$jcsj$", jcsj);//���ʱ��
				param.put("$jlnr$", jlnr);//Υ����Ϊ����
				param.put("$wfajfj$", wfajfj);//�о�֤����ʽ������Ҫ֤��������
				param.put("$rules$", rules);//Υ���ķ��ɷ���
				param.put("$measure$", measure);//���ݵķ��ɷ���
				param.put("$xzcfzd$", xzcfzd);//����������������������׼�ƶ�
				param.put("$xzcf$", xzcf);//��������
				param.put("$lxr$", lxr);//��ϵ��
				param.put("$lxrdh$", lxrdh);//��ϵ�˵绰
				param.put("$lxrdz$", lxrdz);////��ϵ�˵�ַ
				param.put("$postcode$", postcode);//�ʱ�

				String newfile = PoiUtil.createWord(tempPath, dirPath, param);

				//////////////////////////////�����������ͣ�����Ӧ�Ĵ���(���������ĵ�)///////////////////////////////////
				File file = new File(newfile);
				if (file.exists()) {
					InputStream is = new FileInputStream(file);	
					//��ɾ���ɵģ��ٱ����µģ�
					//commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCSXGZS.getCode());	
					commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCSXGZS.getCode(), "work", work.getName() + "_�����������ȸ�֪��.doc", ((Integer) is.available()).longValue());
				}
				long end = System.currentTimeMillis();
				//System.out.println("��poi����word����ʱ�䣺" + start);
				//System.out.println("��poi����word��ʱ*******************��" + (end - start) + "ms");
			}
		}catch (Exception e) {
			//System.out.println("���������������ȸ�֪���ļ�������"+ e);
			log.error("", e);
        }
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
}