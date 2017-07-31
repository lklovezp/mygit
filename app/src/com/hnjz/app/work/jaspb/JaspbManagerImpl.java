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
	
	/** ��־ */
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
		//ִ��������Ϣ
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
			jaspbForm.setTaskid(applyId);//����ID
			jaspbForm.setAjnr(po.getAjnr());//����
			jaspbForm.setAjly(po.getAjly());//������Դ
			jaspbForm.setDsrmc(po.getDsrmc());//�����ˣ����ƻ�������
			jaspbForm.setFddbr(po.getFddbr());//����������
			jaspbForm.setCompany(po.getCompany());//������λ
			jaspbForm.setDsrzw(po.getDsrzw());//ְ���ְҵ
			jaspbForm.setDsrdz(po.getDsrdz());//��ַ��סַ
			jaspbForm.setLasj(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getLasj()?new Date():po.getLasj()));//����ʱ��
			jaspbForm.setAjscr(po.getAjscr());//���������
			jaspbForm.setAjscrbh(po.getAjscrbh());//ִ��֤���
			jaspbForm.setXzcfjdswh(po.getXzcfjdswh());//���������������ĺ�
			jaspbForm.setJyaq(po.getJyaq());//��Ҫ���鼰�鴦����
			jaspbForm.setClyj(po.getClyj());//�������ݼ����
			jaspbForm.setXzssqk(po.getXzssqk());//�������������������
			jaspbForm.setCfzxqk(po.getCfzxqk());//����ִ���������û����Ĵ������
			jaspbForm.setRemark(po.getRemark());//��ע
		}else{//Ĭ�ϴ�������
			jaspbForm.setLasj(DateUtil.getDateTime("yyyy-MM-dd HH:mm",new Date()));//����ʱ��Ĭ��ֵ
			jaspbForm.setDsrmc(blZfdxForm.getLawobjname());//ִ����������
			jaspbForm.setFddbr(blZfdxForm.getManager());//����������
			jaspbForm.setDsrzw(blZfdxForm.getZw());//������ְ��
			//������Դ����
	        String str1="";
			if(work.getSource()!=null){
				str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),work.getSource());
			}
			jaspbForm.setAjly(str1);//������Դ
			jaspbForm.setAjnr(work.getWorkNote());//�����Ҫ����
			jaspbForm.setDsrdz(blZfdxForm.getAddress());//סַ��ַ
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
		jaspb.setTaskid(applyId);//����ID
		jaspb.setAjnr(jaspbForm.getAjnr());//����
		jaspb.setAjly(jaspbForm.getAjly());//������Դ
		jaspb.setDsrmc(jaspbForm.getDsrmc());//�����ˣ����ƻ�������
		jaspb.setFddbr(jaspbForm.getFddbr());//����������
		jaspb.setCompany(jaspbForm.getCompany());//������λ
		jaspb.setDsrzw(jaspbForm.getDsrzw());//ְ���ְҵ
		jaspb.setDsrdz(jaspbForm.getDsrdz());//��ַ��סַ
		try {
			jaspb.setLasj(DateUtil.convertStringToDate("yyyy-MM-dd", jaspbForm.getLasj()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//����ʱ��
		jaspb.setAjscr(jaspbForm.getAjscr());//���������
		jaspb.setAjscrbh(jaspbForm.getAjscrbh());//ִ��֤���
		jaspb.setXzcfjdswh(jaspbForm.getXzcfjdswh());//���������������ĺ�
		jaspb.setJyaq(jaspbForm.getJyaq());//��Ҫ���鼰�鴦����
		jaspb.setClyj(jaspbForm.getClyj());//�������ݼ����
		jaspb.setXzssqk(jaspbForm.getXzssqk());//�������������������
		jaspb.setCfzxqk(jaspbForm.getCfzxqk());//����ִ���������û����Ĵ������
		jaspb.setRemark(jaspbForm.getRemark());//��ע
		jaspb.setAreaId(cur.getAreaId());//����������˱�ʶ
		jaspb.setIsActive(YnEnum.Y.getCode());	
		dao.save(jaspb);
	}
	
	@Override
	public void saveShengchengSxgzs(String applyId,String wflx){
		try{
			Work work = workManager.get(applyId);
			TBizXzcfjasp jaspb=new TBizXzcfjasp();
			//������������λ
			//TSysUser user = CtxUtil.getCurUser();
			if (null != applyId && !"".equals(applyId.trim())) {
				String ajnr="";//����
				String ajly = "";//������Դ
				String dsrmc = "";//�����ˣ����ƻ�������
				String fddbr = "";//����������
				String company = "";//������λ
				String dsrzw = "";//ְ���ְҵ
				String dsrdz = "";//��ַ��סַ	
				String lasj = "";//����ʱ��		
				String ajscr = "";//���������	
				String ajscrbh = "";//ִ��֤���
				String xzcfjdswh = "";//���������������ĺ�
				String jyaq = "";//��Ҫ���鼰�鴦����
				String clyj = "";//�������ݼ����
				String xzssqk = "";//�������������������
				String cfzxqk = "";//����ִ���������û����Ĵ������
				String remark = "";//��ע
				//�鿴������Ŀ����¼
				List<TBizXzcfjasp> kcbllList = dao.find(" from TBizXzcfjasp t where t.taskid=? ", applyId);
				if (kcbllList != null && kcbllList.size() > 0) {//�м�¼
					jaspb = kcbllList.get(0);
				} else {
					jaspb = new TBizXzcfjasp();
				}

				/********************��ʼ��ֵ****************/
				
				ajnr = jaspb.getAjnr();//����
				ajly = jaspb.getAjly();//������Դ
				dsrmc = jaspb.getDsrmc();//�����ˣ����ƻ�������
				fddbr = jaspb.getFddbr();//����������
				company = jaspb.getCompany();//������λ
				dsrzw = jaspb.getDsrzw();//ְ���ְҵ
				dsrdz = jaspb.getDsrdz();//��ַ��סַ	
				lasj = DateUtil.getDateTime("yyyy��MM��dd��", jaspb.getLasj());//����ʱ��		
				ajscr = jaspb.getAjscr();//���������	
				ajscrbh = jaspb.getAjscrbh();//ִ��֤���
				xzcfjdswh = jaspb.getXzcfjdswh();//���������������ĺ�
				jyaq = jaspb.getJyaq();//��Ҫ���鼰�鴦����
				clyj = jaspb.getClyj();//�������ݼ����
				xzssqk = jaspb.getXzssqk();//�������������������
				cfzxqk = jaspb.getCfzxqk();//����ִ���������û����Ĵ������
				remark = jaspb.getRemark();//��ע
				/********************��ֵ����****************/
				long start = System.currentTimeMillis();
				//System.out.println("��poi����word��ʼʱ�䣺" + start);
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
				String tempPath=sc.getRealPath(File.separator) + "excel//xzcf/xzcf_xzcfjaspb.doc";
				//poiʵ��word����
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
				//////////////////////////////�����������ͣ�����Ӧ�Ĵ���(���������ĵ�)///////////////////////////////////
				File file = new File(newfile);
				if (file.exists()) {
					InputStream is = new FileInputStream(file);	
					//��ɾ���ɵģ��ٱ����µģ�
					//commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCJASPB.getCode());	
					commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCJASPB.getCode(), "work", work.getName() + "_�������������᰸������.doc", ((Integer) is.available()).longValue());
				}
				long end = System.currentTimeMillis();
				//System.out.println("��poi����word����ʱ�䣺" + start);
				//System.out.println("��poi����word��ʱ*******************��" + (end - start) + "ms");
			}
		}catch (Exception e) {
			//System.out.println("�����������������᰸������doc�ļ�������"+ e);
			log.debug("");
			
        }
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
}