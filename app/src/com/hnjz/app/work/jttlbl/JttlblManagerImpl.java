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
	
	/** ��־ */
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
        //�������۱�¼����
        JttlblForm jttlblForm=new JttlblForm();
		QueryCondition data = new QueryCondition();
		StringBuffer sql = new StringBuffer();
		sql.append(" from TBizAjjttlbl where taskid=:taskid ");
		data.put("taskid", applyId);
		List<TBizAjjttlbl> list = this.dao.find(sql.toString(),data);
		TBizAjjttlbl po=new TBizAjjttlbl();
		/*//��ȡ��ǰ��¼�˵�������λ
		TSysUser curUser = CtxUtil.getCurUser();
        TSysOrg org = (TSysOrg) this.find("from TSysOrg where id = (select org.id from TSysUserOrg where user.id = ? ) ", curUser.getId()).get(0);*/
		if(list!=null&&list.size()>0){
			po=list.get(0);
			jttlblForm.setId(po.getId());
			jttlblForm.setTaskid(applyId);
			jttlblForm.setTaskid(po.getTaskid());//����ID
			jttlblForm.setAjmc(po.getAjmc());//��������
			jttlblForm.setTlstartsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getTlstartsj()?new Date():po.getTlstartsj()));//���ۿ�ʼʱ��
			jttlblForm.setTlendsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm", null==po.getTlendsj()?new Date():po.getTlendsj()));//���۽���ʱ��
			jttlblForm.setTldd(po.getTldd());//���۵ص�
			jttlblForm.setZcr(po.getZcr());//������
			jttlblForm.setZcrzw(po.getZcrzw());//������ְ��
			jttlblForm.setJlr(po.getJlr());//��¼�� 
			jttlblForm.setJlrzw(po.getJlrzw());//��¼��ְ��
			jttlblForm.setCjrys(po.getCjrys());//�μ���Ա
			jttlblForm.setRy(po.getRy());//��Ա
			jttlblForm.setAjqk(po.getAjqk());//�а��˻㱨�������
			jttlblForm.setCsqk(po.getCsqk());//��������֤�����
			jttlblForm.setTlyj(po.getTlyj());//�μ�������Ա���
			jttlblForm.setTljl(po.getTljl());//�������
		}else{//Ĭ�ϴ�������
			jttlblForm.setTlstartsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm",new Date()));//���ۿ�ʼʱ��
			//Ĭ����ʾʱ��ֹʱ��ȿ�ʼʱ�������Сʱ
			Date date = new Date();     
			Calendar   dar=Calendar.getInstance();     
			dar.setTime(date);     
			dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
			Date sdf = dar.getTime();
			jttlblForm.setTlendsj(DateUtil.getDateTime("yyyy-MM-dd HH:mm",sdf));//���۽���ʱ��
			jttlblForm.setAjmc(work.getName());//��������Ĭ�ϴ�ֵ
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
		ajjttlbl.setTaskid(applyId);//����ID
		ajjttlbl.setAjmc(jttlblForm.getAjmc());//��������
		try {
			ajjttlbl.setTlstartsj(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm", jttlblForm.getTlstartsj()));//���ۿ�ʼʱ��
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ajjttlbl.setTlendsj(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm", jttlblForm.getTlendsj()));//���۽���ʱ��
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ajjttlbl.setTldd(jttlblForm.getTldd());//���۵ص�
		ajjttlbl.setZcr(jttlblForm.getZcr());//������
		ajjttlbl.setZcrzw(jttlblForm.getZcrzw());//������ְ��
		ajjttlbl.setJlr(jttlblForm.getJlr());//��¼�� 
		ajjttlbl.setJlrzw(jttlblForm.getJlrzw());//��¼��ְ��
		ajjttlbl.setCjrys(jttlblForm.getCjrys());//�μ���Ա
		ajjttlbl.setRy(jttlblForm.getRy());//��Ա
		ajjttlbl.setAjqk(jttlblForm.getAjqk());//�а��˻㱨�������
		ajjttlbl.setCsqk(jttlblForm.getCsqk());//��������֤�����
		ajjttlbl.setTlyj(jttlblForm.getTlyj());//�μ�������Ա���
		ajjttlbl.setTljl(jttlblForm.getTljl());//�������
		ajjttlbl.setAreaId(cur.getAreaId());//����������˱�ʶ
		ajjttlbl.setIsActive(YnEnum.Y.getCode());
		dao.save(ajjttlbl); 
	}
	
	@Override
	public void saveShengchengXwbl(String applyId,String wflx){
		try{
			Work work = workManager.get(applyId);
			TBizAjjttlbl jttlbl=new TBizAjjttlbl();
			if (null != applyId && !"".equals(applyId.trim())) {
				String ajmc="";//��������
				String tlStartsj = "";//�������ۿ�ʼʱ��
				String tlEndsj = "";//�������ۿ�ʼʱ��
				String tldd = "";//���۵ص�
				String zcr = "";//������
				String zcrzw = "";//������ְ��
				String jlr = "";//��¼��
				String jlrzw = "";//��¼��ְ��;
				String cjrys = "";//�μ���Ա
				String ry = "";//��Ա
				String ajqk = "";//�а��˻㱨�������
				String csqk = "";//��������֤�����
				String tlyj = "";//�μ�������Ա���
				String tljl = "";//�μ�������Ա����
				//�鿴������Ŀ����¼
				List<TBizAjjttlbl> kcbllList = dao.find(" from TBizAjjttlbl t where t.taskid=? ", applyId);
				if (kcbllList != null && kcbllList.size() > 0) {//�м�¼
					jttlbl = kcbllList.get(0);
				} else {
					jttlbl = new TBizAjjttlbl();
				}

				/********************��ʼ��ֵ****************/
				ajmc=jttlbl.getAjmc();//��������
				tlStartsj = DateUtil.getDateTime("yyyy��MM��dd��HHʱmm��", jttlbl.getTlstartsj());//�������ۿ�ʼʱ��
				tlEndsj = DateUtil.getDateTime("HHʱmm��", jttlbl.getTlendsj());//�������ۿ�ʼʱ��
				tldd = jttlbl.getTldd();//���۵ص�
				zcr = jttlbl.getZcr();//������
				zcrzw = jttlbl.getZcrzw();//������ְ��
				jlr = jttlbl.getJlr();//��¼��
				jlrzw = jttlbl.getJlrzw();//��¼��ְ��;
				cjrys = jttlbl.getCjrys();//�μ���Ա
				ry = jttlbl.getRy();//��Ա
				ajqk = jttlbl.getAjqk();//�а��˻㱨�������
				csqk = jttlbl.getCsqk();//��������֤�����
				tlyj = jttlbl.getTlyj();//�μ�������Ա���
				tljl = jttlbl.getTljl();//�μ�������Ա����
				/********************��ֵ����****************/
				long start = System.currentTimeMillis();
				//System.out.println("��poi����word��ʼʱ�䣺" + start);
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
				String tempPath=sc.getRealPath(File.separator) + "excel/xzcf/xzcf_ajtlbl.doc";
				//poiʵ��word����
				Map<String, String> param = new HashMap<String, String>();
				param.put("$ajmc$", ajmc);//��������
				param.put("$tlStartsj$", tlStartsj);//�������ۿ�ʼʱ��
				param.put("$tlEndsj$", tlEndsj);//�������ۿ�ʼʱ��
				param.put("$tldd$", tldd);//���۵ص�
				param.put("$zcr$", zcr);//������
				param.put("$zcrzw$", zcrzw);//������ְ��
				param.put("$jlr$", jlr);//��¼��
				param.put("$jlrzw$", jlrzw);//��¼��ְ��
				param.put("$cjrys$", cjrys);//�μ���Ա
				param.put("$ry$", ry);//��Ա
				param.put("$ajqk$", ajqk);//�а��˻㱨�������
				param.put("$csqk$", csqk);//��������֤�����
				param.put("$tlyj$", tlyj);//�μ�������Ա���
				param.put("$tljl$", tljl);//�μ�������Ա����
				String newfile = PoiUtil.createWord(tempPath, dirPath, param);
				//////////////////////////////�����������ͣ�����Ӧ�Ĵ���(���������ĵ�)///////////////////////////////////
				File file = new File(newfile);
				if (file.exists()) {
					InputStream is = new FileInputStream(file);	
					//��ɾ���ɵģ��ٱ����µģ�
					//commonManager.removeFileByPidAndFileType(applyId, FileTypeEnums.WFAJDCJTTLBL.getCode());	
					commonManager.saveFile(is, applyId, FileTypeEnums.WFAJDCJTTLBL.getCode(), "work", work.getName() + "_�����������۱�¼.doc", ((Integer) is.available()).longValue());
				}
				long end = System.currentTimeMillis();
				//System.out.println("��poi����word����ʱ�䣺" + start);
				//System.out.println("��poi����word��ʱ*******************��" + (end - start) + "ms");
			}
		}catch (Exception e) {
			//System.out.println("���ɰ����������۱�¼doc�ļ�������"+ e);
			log.error("", e);
        }
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
}