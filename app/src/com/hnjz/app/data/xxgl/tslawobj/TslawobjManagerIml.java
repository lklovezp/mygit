package com.hnjz.app.data.xxgl.tslawobj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.data.doublerandom.DoubleRandomManager;
import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TBizConfigcheck;
import com.hnjz.app.data.po.TBizConfigpf;
import com.hnjz.app.data.po.TBizConfigpfsj;
import com.hnjz.app.data.po.TBizHistoryconfigcheck;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.po.TDataSpeciallawobj;
import com.hnjz.app.data.po.Vzfdx;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.data.xxgl.yearlawobj.YearLawobjManager;
import com.hnjz.app.jxkh.orgstatistics.ConditionsForm;
import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.ZfdxLx;
import com.hnjz.app.work.manager.nodes.WorkPf;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.TBizTaskandtasktype;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.common.util.excel.ExcelUtil;
import com.hnjz.facade.domain.WorkDto;
import com.hnjz.sys.configCheckProportion.QuarterEnum;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TBizCheckedLawobj;
import com.hnjz.sys.po.TDataQuarterChecktimeSet;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.spotCheck.SpotCheckManager;
import com.hnjz.sys.user.UserManager;

@Service("tslawobjManager")
public class TslawobjManagerIml extends ManagerImpl implements TslawobjManager,ServletContextAware{
	private ServletContext servletContext;
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	@Autowired
	private LawobjManager lawobjManager;
    @Autowired
    private YearLawobjManager yearLawobjManager;
    @Autowired
    private CommWorkManager commWorkManager;
    @Autowired
    private WorkPf workPf;
    @Autowired
    private CommonManager commonManager;
    @Autowired
	private UserManager userManager;
    @Autowired
    private WorkDao                 workDao;
    @Autowired
    private OrgManager orgManager;
    @Autowired
    private SpotCheckManager spotCheckManager;
    @Autowired
    private DicManager     dicManager;
    @Autowired
    private DoubleRandomManager doubleRandomManager;
    
	@Override
	public void saveSpecialLawobj(String ids, String names, String year,
			String quarter) throws Exception {
		TSysUser user = CtxUtil.getCurUser();
		if(StringUtil.isNotBlank(ids)){
			
			String [] idList = ids.split(",");
			String [] nameList = names.split(",");
			
			TDataSpeciallawobj po = new TDataSpeciallawobj();
			TDataLawobjf lawobj = new TDataLawobjf();
			Date cur = new Date();
			for(int i=0;i<idList.length;i++){
				po = new TDataSpeciallawobj();
				lawobj = (TDataLawobjf)this.get(TDataLawobjf.class, idList[i]);//ִ������
				po.setAreaId(user.getAreaId());
				po.setCreater(user);
				po.setCreateTime(cur);
				po.setIsActive("Y");
				po.setType("0");
				po.setLawobjId(lawobj.getId());
				po.setLawobjName(nameList[i]);
				po.setLawobjType(lawobj.getLawobjtype().getId());
				po.setQuarter(quarter);
				po.setUpdateby(user);
				po.setUpdateTime(cur);
				po.setYear(year);
				po.setSfypf("N");
				this.dao.save(po);
			}
		}
	}

	@Override
	public FyWebResult querySpecialLawobj(String year, String quarter,
			String lawobjname, String lawobjtype,  String page,
			String pageSize) throws Exception {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataSpeciallawobj where 1=1 and isActive = 'Y'");
		//ִ����������
		if (StringUtil.isNotBlank(lawobjname)) {
			sql.append(" and lawobjName like :lawobjname ");
			data.putLike("lawobjname", lawobjname);
		}
		//���
		if (StringUtil.isNotBlank(year)) {
			sql.append(" and year = :year ");
			data.put("year", year);
		}
		//����
		if(StringUtil.isNotBlank(quarter)){
			sql.append(" and quarter = :quarter");
			data.put("quarter", quarter);
		}
		
		//ִ����������
		if (StringUtil.isNotBlank(lawobjtype)) {
			sql.append(" and lawobjType = :lawobjtype ");
			data.put("lawobjtype", lawobjtype);
		}
		
		//��������
		String areaid = CtxUtil.getAreaId();
		if(StringUtil.isNotBlank(areaid)){
			sql.append(" and areaId = :areaid ");
			data.put("areaid", areaid);
		}
		sql.append(" order by year,quarter,lawobjType,lawobjName ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TDataSpeciallawobj> specialLawobjs = pos.getRe();
		Map<String, String> row = null;
		for (TDataSpeciallawobj ele : specialLawobjs) {
			row = new HashMap<String, String>();
			row.put("id", ele.getId());
			row.put("year", ele.getYear()+"��");
			row.put("lawobjname", ele.getLawobjName());
			TDataLawobjtype tlt=(TDataLawobjtype)this.get(TDataLawobjtype.class, ele.getLawobjType());
			row.put("lawobjtype", tlt.getName());
			row.put("quarter", QuarterEnum.getNameByCode(ele.getQuarter()));
			row.put("isActive", YnEnum.getNote(ele.getIsActive()));
			row.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}

	@Override
	public void removeSpecialLawobj(String id) throws Exception {
		TDataSpeciallawobj del = (TDataSpeciallawobj) this.dao.get(TDataSpeciallawobj.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
		
	}

	@Override
	public void configCheckSave(ConfigCheckForm configCheckForm,String areaid)
			throws Exception {
		TBizConfigcheck tc=null;
		TSysUser user=CtxUtil.getCurUser();
		if(StringUtil.isNotBlank(configCheckForm.getId())){
			tc=(TBizConfigcheck)this.get(TBizConfigcheck.class, configCheckForm.getId());
			tc.setGzsz(configCheckForm.getGzsz());
			tc.setIsActive("Y");
			tc.setTsjgqybl(configCheckForm.getTsjgqybl());
			tc.setAreaId(areaid);
			tc.setUpdateby(user);
			tc.setUpdateTime(new Date());
			/*tc.setYbqybl(Double.valueOf(configCheckForm.getYbqybl()));
            tc.setYbqyblfour(Double.valueOf(configCheckForm.getYbqyblfour()));
            tc.setYbqyblthird(Double.valueOf(configCheckForm.getYbqyblthird()));
            tc.setYbqyblsecond(Double.valueOf(configCheckForm.getYbqyblsecond()));
            tc.setZdqybl(Double.valueOf(configCheckForm.getZdqybl()));
			tc.setZdqyblfour(Double.valueOf(configCheckForm.getZdqyblfour()));
			tc.setZdqyblthird(Double.valueOf(configCheckForm.getZdqyblthird()));
			tc.setZdqyblsecond(Double.valueOf(configCheckForm.getZdqyblsecond()));*/
			//һ����ҵ����
			tc.setYbqyapr(Double.valueOf(configCheckForm.getYbqyapr()));
			tc.setYbqyaug(Double.valueOf(configCheckForm.getYbqyaug()));
			tc.setYbqydec(Double.valueOf(configCheckForm.getYbqydec()));
			tc.setYbqyfeb(Double.valueOf(configCheckForm.getYbqyfeb()));
			tc.setYbqyjan(Double.valueOf(configCheckForm.getYbqyjan()));
			tc.setYbqyjul(Double.valueOf(configCheckForm.getYbqyjul()));
			tc.setYbqyjun(Double.valueOf(configCheckForm.getYbqyjun()));
			tc.setYbqymar(Double.valueOf(configCheckForm.getYbqymar()));
			tc.setYbqymay(Double.valueOf(configCheckForm.getYbqymay()));
			tc.setYbqynov(Double.valueOf(configCheckForm.getYbqynov()));
			tc.setYbqyoct(Double.valueOf(configCheckForm.getYbqyoct()));
			tc.setYbqysep(Double.valueOf(configCheckForm.getYbqysep()));
			//�ص���ҵ����
			tc.setZdqyapr(Double.valueOf(configCheckForm.getZdqyapr()));
			tc.setZdqyaug(Double.valueOf(configCheckForm.getZdqyaug()));
			tc.setZdqydec(Double.valueOf(configCheckForm.getZdqydec()));
			tc.setZdqyfeb(Double.valueOf(configCheckForm.getZdqyfeb()));
			tc.setZdqyjan(Double.valueOf(configCheckForm.getZdqyjan()));
			tc.setZdqyjul(Double.valueOf(configCheckForm.getZdqyjul()));
			tc.setZdqyjun(Double.valueOf(configCheckForm.getZdqyjun()));
			tc.setZdqymar(Double.valueOf(configCheckForm.getZdqymar()));
			tc.setZdqymay(Double.valueOf(configCheckForm.getZdqymay()));
			tc.setZdqynov(Double.valueOf(configCheckForm.getZdqynov()));
			tc.setZdqyoct(Double.valueOf(configCheckForm.getZdqyoct()));
			tc.setZdqysep(Double.valueOf(configCheckForm.getZdqysep()));
			
			this.dao.save(tc);
		}else{
			tc=new TBizConfigcheck();
			tc.setCreater(user);
			tc.setAreaId(areaid);
			tc.setCreateTime(new Date());
			tc.setGzsz(configCheckForm.getGzsz());
			tc.setIsActive("Y");
			tc.setTsjgqybl(configCheckForm.getTsjgqybl());
			tc.setUpdateby(user);
			tc.setUpdateTime(new Date());
			
			/*tc.setYbqybl(Double.valueOf(configCheckForm.getYbqybl()));
            tc.setYbqyblfour(Double.valueOf(configCheckForm.getYbqyblfour()));
            tc.setYbqyblthird(Double.valueOf(configCheckForm.getYbqyblthird()));
            tc.setYbqyblsecond(Double.valueOf(configCheckForm.getYbqyblsecond()));


             tc.setZdqybl(Double.valueOf(configCheckForm.getZdqybl()));
			
			tc.setZdqyblfour(Double.valueOf(configCheckForm.getZdqyblfour()));
			
			tc.setZdqyblthird(Double.valueOf(configCheckForm.getZdqyblthird()));
			
			tc.setZdqyblsecond(Double.valueOf(configCheckForm.getZdqyblsecond()));*/
			
			//һ����ҵ����
			tc.setYbqyapr(Double.valueOf(configCheckForm.getYbqyapr()));
			tc.setYbqyaug(Double.valueOf(configCheckForm.getYbqyaug()));
			tc.setYbqydec(Double.valueOf(configCheckForm.getYbqydec()));
			tc.setYbqyfeb(Double.valueOf(configCheckForm.getYbqyfeb()));
			tc.setYbqyjan(Double.valueOf(configCheckForm.getYbqyjan()));
			tc.setYbqyjul(Double.valueOf(configCheckForm.getYbqyjul()));
			tc.setYbqyjun(Double.valueOf(configCheckForm.getYbqyjun()));
			tc.setYbqymar(Double.valueOf(configCheckForm.getYbqymar()));
			tc.setYbqymay(Double.valueOf(configCheckForm.getYbqymay()));
			tc.setYbqynov(Double.valueOf(configCheckForm.getYbqynov()));
			tc.setYbqyoct(Double.valueOf(configCheckForm.getYbqyoct()));
			tc.setYbqysep(Double.valueOf(configCheckForm.getYbqysep()));
			//�ص���ҵ����
			tc.setZdqyapr(Double.valueOf(configCheckForm.getZdqyapr()));
			tc.setZdqyaug(Double.valueOf(configCheckForm.getZdqyaug()));
			tc.setZdqydec(Double.valueOf(configCheckForm.getZdqydec()));
			tc.setZdqyfeb(Double.valueOf(configCheckForm.getZdqyfeb()));
			tc.setZdqyjan(Double.valueOf(configCheckForm.getZdqyjan()));
			tc.setZdqyjul(Double.valueOf(configCheckForm.getZdqyjul()));
			tc.setZdqyjun(Double.valueOf(configCheckForm.getZdqyjun()));
			tc.setZdqymar(Double.valueOf(configCheckForm.getZdqymar()));
			tc.setZdqymay(Double.valueOf(configCheckForm.getZdqymay()));
			tc.setZdqynov(Double.valueOf(configCheckForm.getZdqynov()));
			tc.setZdqyoct(Double.valueOf(configCheckForm.getZdqyoct()));
			tc.setZdqysep(Double.valueOf(configCheckForm.getZdqysep()));
			
			
			
			this.dao.save(tc);
		}
	}

	@Override
	public ConfigCheckForm queryConfigCheck(String areaid) throws Exception {
		List<TBizConfigcheck> tcs=this.dao.find("from TBizConfigcheck t where t.isActive='Y' and t.areaId=? order by t.createTime asc",areaid);
		ConfigCheckForm cc=new ConfigCheckForm();
		if(tcs.size()>0){
			TBizConfigcheck tc=new TBizConfigcheck();
			tc=tcs.get(0);
			cc.setGzsz(tc.getGzsz());
			cc.setId(tc.getId());
			cc.setIsActive(tc.getIsActive());
			cc.setTsjgqybl(tc.getTsjgqybl());
			/*cc.setYbqybl(String.valueOf(tc.getYbqybl()));
			cc.setYbqyblfour(String.valueOf(tc.getYbqyblfour()));
			cc.setYbqyblthird(String.valueOf(tc.getYbqyblthird()));
			cc.setYbqyblsecond(String.valueOf(tc.getYbqyblsecond()));
			cc.setZdqybl(String.valueOf(tc.getZdqybl()));
			cc.setZdqyblfour(String.valueOf(tc.getZdqyblfour()));
			cc.setZdqyblthird(String.valueOf(tc.getZdqyblthird()));
			cc.setZdqyblsecond(String.valueOf(tc.getZdqyblsecond()));*/
			//һ����ҵ����
			cc.setYbqyapr(String.valueOf(tc.getYbqyapr()));
			cc.setYbqyaug(String.valueOf(tc.getYbqyaug()));
			cc.setYbqydec(String.valueOf(tc.getYbqydec()));
			cc.setYbqyfeb(String.valueOf(tc.getYbqyfeb()));
			cc.setYbqyjan(String.valueOf(tc.getYbqyjan()));
			cc.setYbqyjul(String.valueOf(tc.getYbqyjul()));
			cc.setYbqyjun(String.valueOf(tc.getYbqyjun()));
			cc.setYbqymar(String.valueOf(tc.getYbqymar()));
			cc.setYbqymay(String.valueOf(tc.getYbqymay()));
			cc.setYbqynov(String.valueOf(tc.getYbqynov()));
			cc.setYbqyoct(String.valueOf(tc.getYbqyoct()));
			cc.setYbqysep(String.valueOf(tc.getYbqysep()));
			//�ص���ҵ����
			cc.setZdqyapr(String.valueOf(tc.getZdqyapr()));
			cc.setZdqyaug(String.valueOf(tc.getZdqyaug()));
			cc.setZdqydec(String.valueOf(tc.getZdqydec()));
			cc.setZdqyfeb(String.valueOf(tc.getZdqyfeb()));
			cc.setZdqyjan(String.valueOf(tc.getZdqyjan()));
			cc.setZdqyjul(String.valueOf(tc.getZdqyjul()));
			cc.setZdqyjun(String.valueOf(tc.getZdqyjun()));
			cc.setZdqymar(String.valueOf(tc.getZdqymar()));
			cc.setZdqymay(String.valueOf(tc.getZdqymay()));
			cc.setZdqynov(String.valueOf(tc.getZdqynov()));
			cc.setZdqyoct(String.valueOf(tc.getZdqyoct()));
			cc.setZdqysep(String.valueOf(tc.getZdqysep()));
		}			
		return cc;
	}

	@Override
	public ConfigPfForm queryConfigPfForm(String year, String quarter,String areaid)
			throws Exception {
		ConfigPfForm con = new ConfigPfForm();
		if(StringUtil.isBlank(areaid)){
			areaid=CtxUtil.getAreaId();
		}
		if (StringUtil.isNotBlank(year) && StringUtil.isNotBlank(quarter)) {
			List<TBizConfigpf> tpf = this.dao
					.find("from TBizConfigpf t where t.isActive='Y' and t.areaId=? and t.year=? and t.quarter=? and type='1'",
							areaid, year, quarter);
			List<ConfigPfSjForm> consjList = new ArrayList<ConfigPfSjForm>();
			if (tpf.size() > 0) {
				List<TBizConfigpfsj> tsj = this.dao
						.find("from TBizConfigpfsj t where t.isActive='Y' and t.areaId=? and t.tBizConfigpf.id=? order by pfsj asc",
								areaid, tpf.get(0).getId());
				ConfigPfSjForm conpfsj = null;

				for (TBizConfigpfsj ts : tsj) {
					conpfsj = new ConfigPfSjForm();
					// conpfsj.setAreaId(ts.getAreaId());
					// conpfsj.setCreater(ts.getCreater());
					// conpfsj.setCreateTime(ts.getCreateTime());
					conpfsj.setId(ts.getId());
					conpfsj.setIsActive(ts.getIsActive());
					conpfsj.setPfbl(String.valueOf(ts.getPfbl()));
					String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(ts.getPfsj());
					conpfsj.setPfsj(dateStr);
					conpfsj.setSfypf(ts.getSfypf().equals("N") ? "��" : "��");
					conpfsj.setBjsx(String.valueOf(tpf.get(0).getBjsx()));
					// conpfsj.settBizConfigpf(ts.gettBizConfigpf());
					// conpfsj.setUpdateby(ts.getUpdateby());
					// conpfsj.setUpdateTime(ts.getUpdateTime());
					consjList.add(conpfsj);
				}
				con.setAreaId(tpf.get(0).getAreaId());
				con.setBjsx(String.valueOf(tpf.get(0).getBjsx()));
				con.setCreater(tpf.get(0).getCreater());
				con.setCreateTime(tpf.get(0).getCreateTime());
				// con.setData(data);
				con.setId(tpf.get(0).getId());
				con.setIsActive(tpf.get(0).getIsActive());
				
				con.setYear(year);
				con.setQuarter(quarter);
				con.setUpdateby(tpf.get(0).getUpdateby());
				con.setUpdateTime(tpf.get(0).getUpdateTime());
			} else {
				con.setYear(year);
				con.setQuarter(quarter);
			}
			con.setList(consjList);
		} else {
			ConfigPfSjForm conpfsj = new ConfigPfSjForm();
			List<ConfigPfSjForm> consjList = new ArrayList<ConfigPfSjForm>();
			consjList.add(conpfsj);
			con.setList(consjList);
		}
		return con;
	}

	@Override
	public void configPfSave(ConfigPfForm configPfForm,String areaid) throws Exception {
		//�ȱ���TBizConfigpf ���ҷ���idֵ
		TSysUser user=CtxUtil.getCurUser();
		ConfigPfForm findCon=null;
		if(StringUtil.isNotBlank(configPfForm.getMonth())){
	        findCon= this.queryConfigPfFormByType(configPfForm.getYear(), configPfForm.getMonth(), configPfForm.getType(), areaid);
		}

		if(StringUtil.isNotBlank(findCon.getId())){
			TBizConfigpf tf=(TBizConfigpf)this.get(TBizConfigpf.class, findCon.getId());
			tf.setBjsx(Integer.valueOf(configPfForm.getBjsx()));
			tf.setMonth(configPfForm.getMonth());
			tf.setType(configPfForm.getType());
			tf.setQuarter(configPfForm.getQuarter());
			tf.setUpdateby(user);
			tf.setUpdateTime(new Date());
			tf.setYear(configPfForm.getYear());
			TBizConfigpf tfId=(TBizConfigpf) this.dao.save(tf);
			//����TBizConfigpfsj
			JSONObject obj = new JSONObject(configPfForm.getData());
			JSONArray arr = obj.getJSONArray("rows");
			List<String> ids=new ArrayList<String>();
			for(int i=0;i<arr.length();i++){
				obj=arr.getJSONObject(i);
				String id=obj.getString("id");
				TBizConfigpfsj tfs=new TBizConfigpfsj();
				if(StringUtil.isNotBlank(id)){
					tfs=(TBizConfigpfsj)this.get(TBizConfigpfsj.class, id);
				}else{
					tfs.setCreater(user);
					tfs.setCreateTime(new Date());
					tfs.setIsActive("Y");
				}
				tfs.setAreaId(areaid);
				tfs.setPfbl(obj.getInt("pfbl"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				Date date = sdf.parse(obj.getString("pfsj")); 
				tfs.setPfsj(date);
				if("Y".equals(tfs.getSfypf())){
					tfs.setSfypf("Y");
				}else{
					tfs.setSfypf("N");
				}
				tfs.settBizConfigpf(tfId);
				tfs.setUpdateby(user);
				tfs.setUpdateTime(new Date());
				TBizConfigpfsj saveid=(TBizConfigpfsj)this.dao.save(tfs);
				ids.add(saveid.getId());
			}
			
			List<String> list=this.dao.find("select t.id from TBizConfigpfsj t where t.isActive='Y' and t.areaId=? and t.tBizConfigpf.id=?" ,user.getAreaId(),findCon.getId() );
			for (String ele : list) {
				// ����������еļ�¼û����֮ǰ����ļ�¼�� ˵���˲����ѱ�ɾ��
				if (!ids.contains(ele)) {
					// ɾ��ҳ���б�ɾ���Ĳ���
					this.dao.remove(TBizConfigpfsj.class, ele);
				}
			}
		}else{
			TBizConfigpf tf=new TBizConfigpf();
			tf.setAreaId(areaid);
			tf.setBjsx(Integer.valueOf(configPfForm.getBjsx()));
			tf.setCreateTime(new Date());
			tf.setIsActive("Y");
			tf.setMonth(configPfForm.getMonth());
			tf.setType(configPfForm.getType());
			tf.setQuarter(configPfForm.getQuarter());
			tf.setUpdateby(user);
			tf.setUpdateTime(new Date());
			tf.setYear(configPfForm.getYear());
			TBizConfigpf tfId=(TBizConfigpf) this.dao.save(tf);
			//����TBizConfigpfsj
			JSONObject obj = new JSONObject(configPfForm.getData());
			JSONArray arr = obj.getJSONArray("rows");
			for(int i=0;i<arr.length();i++){
				obj=arr.getJSONObject(i);
				String id=obj.getString("id");
				TBizConfigpfsj tfs=new TBizConfigpfsj();
				if(StringUtil.isNotBlank(id)){
					tfs=(TBizConfigpfsj)this.get(TBizConfigpfsj.class, id);
				}else{
					tfs.setCreater(user);
					tfs.setCreateTime(new Date());
					tfs.setIsActive("Y");
				}
				tfs.setAreaId(areaid);
				tfs.setPfbl(obj.getInt("pfbl"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				Date date = sdf.parse(obj.getString("pfsj")); 
				tfs.setPfsj(date);
				tfs.setSfypf("N");
				tfs.settBizConfigpf(tfId);
				tfs.setUpdateby(user);
				tfs.setUpdateTime(new Date());
				this.dao.save(tfs);
			}
		}
		
	}

	@Override
	public int getQuarterInMonth(int month, boolean isQuarterStart) {
		int months[] = { 1, 2, 3, 4 };  
        if (!isQuarterStart) {  
            months = new int[] { 3, 6, 9, 12 };  
        }  
        if (month >= 2 && month <= 4)  
            return months[0];  
        else if (month >= 5 && month <= 7)  
            return months[1];  
        else if (month >= 8 && month <= 10)  
            return months[2];  
        else  
            return months[3];  
	}

	@Override
	public TBizConfigcheck queryTbizConfigCheck(String areaid)
			throws Exception {
		TBizConfigcheck tf=new TBizConfigcheck();
			List<TBizConfigcheck> configChecks=this.dao.find("from TBizConfigcheck t where t.isActive='Y' and t.areaId=?",areaid);
			if(configChecks.size()>0){
				tf=configChecks.get(0);
			}
		
		return tf;
	}
	
     /**
      * ��ȡ�����δ����ȡ����ȾԴ��Ϣ
      * @author gaozhiyang
      * @time 2017-7-6
      * @year ��ȡ���
      * @quarter ������Ϣ
      * @type
      * @areaid ����ID
      * 
     */
	@Override
	public List<TDataLawobjf> queryNoCheckedKeyLawobjnew(String year, String quarter,
			String type, String areaid) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer listSql = new StringBuffer(" select f.id_ from t_data_lawobjf f  ");
		listSql.append(" left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_  ");
		listSql.append(" left join t_data_region  r on r.id_=f.ssxzq_  where 1=1 and f.isactive_='Y'  and ssxzq_ in ( ");
		listSql.append(" SELECT id_ from t_data_region where PID_  = ( SELECT PID_ from t_data_region  where id_ = ( SELECT code_ from t_sys_area  ");
		if(StringUtils.isNotBlank(areaid)){
		listSql.append(" where id_=:AreaId   ");
		condition.put("AreaId", areaid);
		}
		listSql.append(" )) )");
		listSql.append(" and f.id_ in ( select fid_ from T_data_gywry  where kzlx_ in (1,2,3))  ");
		//��ҵ״̬:��Ӫ��
		listSql.append(" and ").append(" f.qyzt_ = :qyzt ");
		condition.put("qyzt", "0");
		listSql.append(" and  f.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='0' and c.year_ = :year1 and c.source_=1 )");
		condition.put("year1", year);
		//�������Ͻ����ҵ
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobjf> lawobjList = new ArrayList<TDataLawobjf>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobjf lawobj = new TDataLawobjf();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobjf) this.get(TDataLawobjf.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	
	
	
	@Override
	public List<TDataLawobj> queryNoCheckedKeyLawobj(String year, String quarter,
			String type, String areaid) throws Exception {
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gywry_ssxzq.getCode(),
				LawobjOutColunmEnum.gywry_kzlx.getCode(),
				LawobjOutColunmEnum.gywry_qyzt.getCode());
		String ssxzq_column = null;
		String kzlx_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=3){
			throw new Exception("��ҵ��ȾԴ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			kzlx_column = String.valueOf(listColumn.get(1));
			qyzt_column = String.valueOf(listColumn.get(2));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append("left join t_sys_dic d on l.").append(kzlx_column).append(" = d.code_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '01' and (d.code_='1' or d.code_='2'or d.code_='3') and d.type_='6'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
		//String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//�ܶ�
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//ʦ��
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//�ż�
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='0' and c.year_ = :year1 and c.source_=0)");
		condition.put("year1", year);
		//�������Ͻ����ҵ
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	@Override
	public List<TDataLawobj> queryNoCheckedLawobj(String year, String quarter,
			String type, String areaid) throws Exception {
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gywry_ssxzq.getCode(),
				LawobjOutColunmEnum.gywry_kzlx.getCode(),
				LawobjOutColunmEnum.gywry_qyzt.getCode());
		String ssxzq_column = null;
		String kzlx_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=3){
			throw new Exception("��ҵ��ȾԴ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			kzlx_column = String.valueOf(listColumn.get(1));
			qyzt_column = String.valueOf(listColumn.get(2));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append("left join t_sys_dic d on l.").append(kzlx_column).append(" = d.code_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '01' and (d.code_='4') and d.type_='6'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
		//String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//�ܶ�
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//ʦ��
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//�ż�
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	@Override
	public String saveStartQuarterCheck(String year, String quarter, String areaid,TDataQuarterChecktimeSet ele)
			throws Exception {
		//��ѯt_biz_configcheck�����õ���ҵ���õĳ�ѡ����
		//TBizConfigcheck configCheck=this.queryTbizConfigCheck(areaid);
		TBizHistoryconfigcheck thcc=this.queryTBizHistoryconfigcheck(year, quarter, areaid);

		try {
			/**
			 * ��ѡ�ص���ȾԴ���������õļ��ȱ������г�ѡ�������г������е��ص���ҵ��ȡ
			 */
			List<TDataLawobj> keyLawobjList = new ArrayList<TDataLawobj>();//�����ص���ҵ�б�
			//List<TDataLawobj> normalLawobjList = new ArrayList<TDataLawobj>();//����һ����ҵ�б�
			//List<TDataLawobj> specialLawobjList = new ArrayList<TDataLawobj>();//����������ҵ�б�
			keyLawobjList = this.queryAllKeyLawobjListByAreaId(areaid);//�����ص���ȾԴ
			
			double proportion = 0;//���ȳ�����
			double ybbl = 0;//��ȳ�����
			if(quarter.equals("1")){
			proportion=	thcc.getZdqybl();//��һ�����ص������
			ybbl = thcc.getYbqybl();     //��һ����һ����ҵ������
			}else if(quarter.equals("2")){
				proportion=thcc.getZdqyblsecond();//�ڶ������ص������
				ybbl = thcc.getYbqyblsecond();//�ڶ�����һ����ҵ������
			}else if(quarter.equals("3")){
				proportion=thcc.getZdqyblthird();//���������ص������
				ybbl = thcc.getYbqyblthird();//��������һ����ҵ������
			}else if(quarter.equals("4")){
				proportion=thcc.getZdqyblfour();//���ļ����ص������
				ybbl = thcc.getYbqyblfour();//���ļ���һ����ҵ������
			}
			if(keyLawobjList!=null && keyLawobjList.size()>0 && proportion!=0){
				int total = keyLawobjList.size();//�ص���ҵ����
				//float d =(float) proportion/100;//������
				//Ҫ��ȡ������
				int totalNum = 0;
				float total1=(float)(total*proportion)/100;
				int total2=(int)((total*proportion)/100);
				
				if(total1==total2){//������25%Ϊ����ȡ��
					totalNum = total2;
				}else{
					totalNum = total2+1;				//������25%������ȡ����1
				}
				int firstNum = 0;//��һ�γ����
				int secondNum = 0;//�ڶ��γ����
				if(total*0.25==(int)(total*0.25)){//������25%Ϊ����ȡ��
					firstNum = (int)(total*0.25);
				}else{
					firstNum = (int)(total*0.25)+1;				//������25%������ȡ����1
				}
				secondNum = totalNum-firstNum;
				//����ȳ����ѡʱû�б�������ȾԴ
				List<TDataLawobj> noCheckedList = this.queryNoCheckedKeyLawobj(year, quarter, "0", areaid);
				
				//��һ�������飬�����󣺱����û�б��鵽����ȾԴ 
				List<Integer> arr = new ArrayList<Integer>();//�����е���ֵ
				List<TDataLawobj> firstCheckedList = new ArrayList<TDataLawobj>();//������һ�γ����б�
				List<TDataLawobj> secondCheckedList = new ArrayList<TDataLawobj>();//�����ڶ��γ����б�
				
					if(noCheckedList!=null && noCheckedList.size()>0){
						if(firstNum>noCheckedList.size()){
							firstNum = noCheckedList.size();
							secondNum = totalNum- noCheckedList.size();
						}
						for(int i=0;i<firstNum;i++){
							int k = (int) ((Math.random())*(noCheckedList.size()));
							if(!arr.contains(k)){//k���������У���ʾ��δ����ȡ��
								arr.add(k);
								firstCheckedList.add(noCheckedList.get(k));
							}else{
								i--;//���³�ȡ
							}
					    }
				     }
				//�ѵ�һ�γ�鵽���ص���ȾԴ���뵽�������ȾԴ����
					//��û���ع��˷���
				//spotCheckManager.saveCheckedList(year,quarter,"0",firstCheckedList,areaid);
				
				
				
				//�ڶ��γ����󣨱���û�б����е���ȾԴ��
				List<TDataLawobj> thisTimeNocheckedList = new ArrayList<TDataLawobj>();
				//�õ�����û�б���鵽����ȾԴ�б�
				for(int i=0;i<keyLawobjList.size();i++){
					int flag=0;
					for(int j=0;j<firstCheckedList.size();j++){
						TDataLawobj lawobj = keyLawobjList.get(i);
						TDataLawobj lawobj1 = firstCheckedList.get(j);
						if(lawobj!=null && lawobj1!=null && lawobj.getId().equals(lawobj1.getId())){//�����ѱ�����
							flag=1;
							break;
						}
					}
					if(flag==0){//����δ�����еļ����б�
						thisTimeNocheckedList.add(keyLawobjList.get(i));
					}else{
						flag=0;
					}
				}
								
				//�ڶ��������飬�����󣺱���û�б��鵽����ȾԴ
				List<Integer> arr1 = new ArrayList<Integer>();//�����е���ֵ
				for(int i=0;i<secondNum;i++){
					int k = (int) ((Math.random())*(thisTimeNocheckedList.size()));
					if(!arr1.contains(k)){//k���������У���ʾ��δ����ȡ��
						arr1.add(k);
						secondCheckedList.add(thisTimeNocheckedList.get(k));
					}else{
						i--;//���³�ȡ
					}
				}
				//�ѳ�鵽����ȾԴ���뵽�������ȾԴ����
				//spotCheckManager.saveCheckedList(year,quarter,"1",secondCheckedList,areaid);
			}	
				/**
				 * ��ѡһ����ҵ��������һ����ҵ�г�ѡ
				 */
				List<TDataLawobj> allNormalLawobjList = this.queryAllNormalList(areaid,year);
				List<TDataLawobj> CheckedNormalList = this.queryCheckedLawobjList(areaid,year);//���Ѿ���ȡ������ҵ��ȡ�������������ȳ����������
				
				
				//��ѯ�û����õ��ڱ��ڸ���Ա����
				List<TSysUser> users=this.dao.find("from TSysUser t where t.isActive='Y' and t.isZaiBian='Y' and t.areaId=?", areaid);
				//��Ա����
				int usersl=users.size();
				if(allNormalLawobjList!=null && allNormalLawobjList.size()>0){
					int number = allNormalLawobjList.size();
					List<TDataLawobj> normalCheckedList = new ArrayList<TDataLawobj>();//����һ����ҵ�����б�
					int ybtotal=(int)(usersl*ybbl);//�����ȳ�ȡ��ҵ��
					//���ȳ������
					int checkedNum=0;
					if(usersl*ybbl==ybtotal){//����Ϊ����ȡ��
						checkedNum = ybtotal;
					}else{
						checkedNum = ybtotal+1;//�����ķ�����ȡ����1
					}
					List<Integer> arr1 = new ArrayList<Integer>();//�����е���ֵ
					if(number > ybtotal){
						for(int i=0;i<checkedNum && i<allNormalLawobjList.size();i++){
							int k = (int) ((Math.random())*(allNormalLawobjList.size()));
							if(!arr1.contains(k)){//k���������У���ʾ��δ����ȡ��
								arr1.add(k);
								normalCheckedList.add(allNormalLawobjList.get(k));
							}else{
									i--;//���³�ȡ
							}
						}
					}else if(number < ybtotal && "1".equals(quarter)){
						int yushu;
						yushu = ybtotal - number;
						if(allNormalLawobjList != null && allNormalLawobjList.size() > 0){
							for(int i=0;i<checkedNum && i<allNormalLawobjList.size();i++){
								normalCheckedList.add(allNormalLawobjList.get(i));
							}
						}
						if(CheckedNormalList != null && CheckedNormalList.size() > 0){
							for(int i=0;i<checkedNum && i<yushu;i++){
								int k = (int) ((Math.random())*(CheckedNormalList.size()));
								normalCheckedList.add(CheckedNormalList.get(k));
							}
						}else{
							if(yushu < number){
								for(int i=0;i<checkedNum && i<yushu;i++){
									int k = (int) ((Math.random())*(allNormalLawobjList.size()));
									normalCheckedList.add(allNormalLawobjList.get(k));
								}
							}else{
								for(int i=0;i<number;i++){
									normalCheckedList.add(allNormalLawobjList.get(i));
								}
							}
						}
					}else{
						int yushu;
						yushu = ybtotal - number;
						for(int i=0;i<checkedNum && i<allNormalLawobjList.size();i++){
							normalCheckedList.add(allNormalLawobjList.get(i));
						}
						if(CheckedNormalList != null && CheckedNormalList.size() > 0){
							for(int i=0;i<checkedNum && i<yushu;i++){
								int k = (int) ((Math.random())*(CheckedNormalList.size()));
								normalCheckedList.add(CheckedNormalList.get(k));
							}
						}
					}
					//spotCheckManager.saveCheckedList(year,quarter,"2",normalCheckedList,areaid);
				}else{
					List<TDataLawobj> normalCheckedList = new ArrayList<TDataLawobj>();//����һ����ҵ�����б�
					int ybtotal=(int)(usersl*ybbl);//�����ȳ�ȡ��ҵ��
					//���ȳ������
					int checkedNum=0;
					if(usersl*ybbl==ybtotal){//������25%Ϊ����ȡ��
						checkedNum = ybtotal;
					}else{
						checkedNum = ybtotal+1;//������25%������ȡ����1
					}
					if(CheckedNormalList != null && CheckedNormalList.size() > 0){
						for(int i=0;i<checkedNum && i<ybtotal;i++){
							int k = (int) ((Math.random())*(CheckedNormalList.size()));
							normalCheckedList.add(CheckedNormalList.get(k));
						}
					}
					//spotCheckManager.saveCheckedList(year,quarter,"2",normalCheckedList,areaid);
				}
				/**
				 * ��ѡ������ҵ����������ҵ�г�ѡ
				 */
				/**
				 * 
				
				List<TDataLawobj> allSpecialLawobj=this.querySpecialLawobj(year, quarter, areaid);
				if(thcc.getTsjgqybl().equals("1")){
					spotCheckManager.saveCheckedList(year,quarter,"3",allSpecialLawobj,areaid);
					
				}else{
					spotCheckManager.saveCheckedList(year,quarter,"3",allSpecialLawobj,areaid);
					spotCheckManager.saveCheckedList(year,quarter,"3",allSpecialLawobj,areaid);
					spotCheckManager.saveCheckedList(year,quarter,"3",allSpecialLawobj,areaid);
				}
				*/
					ele.setExecuted("Y");
                	this.dao.save(ele);
				return "��ѡ�ɹ�";
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "success";
	}

	
	/** �����������
	 * �ع�����
	 * @author gaozhiyang
	 * @time 2017-7-6
	 * @year ���
	 * @quarter ����
	 * @areaid ����ID 
	 * @ele TDataQuarterChecktimeSet
	 */
	@Override
	public String saveStartQuarterChecknew(String year, String quarter, String areaid,TDataQuarterChecktimeSet ele)
			throws Exception {
		//��ѯt_biz_configcheck�����õ���ҵ���õĳ�ѡ����
		//TBizConfigcheck configCheck=this.queryTbizConfigCheck(areaid);
		TBizHistoryconfigcheck thcc=this.queryTBizHistoryconfigcheck(year, quarter, areaid);

		try {
			/**
			 * ��ѡ�ص���ȾԴ���������õļ��ȱ������г�ѡ�������г������е��ص���ҵ��ȡ
			 */
			List<TDataLawobjf> keyLawobjList = new ArrayList<TDataLawobjf>();//�����ص���ҵ�б�
			//List<TDataLawobj> normalLawobjList = new ArrayList<TDataLawobj>();//����һ����ҵ�б�
			//List<TDataLawobj> specialLawobjList = new ArrayList<TDataLawobj>();//����������ҵ�б�
			keyLawobjList = this.queryAllKeyLawobjListByAreaIdnew(areaid);//�����ص���ȾԴ
			
			double proportion = 0;//���ȳ�����
			double ybbl = 0;//��ȳ�����
			if(quarter.equals("1")){
			proportion=	thcc.getZdqybl();//��һ�����ص������
			ybbl = thcc.getYbqybl();     //��һ����һ����ҵ������
			}else if(quarter.equals("2")){
				proportion=thcc.getZdqyblsecond();//�ڶ������ص������
				ybbl = thcc.getYbqyblsecond();//�ڶ�����һ����ҵ������
			}else if(quarter.equals("3")){
				proportion=thcc.getZdqyblthird();//���������ص������
				ybbl = thcc.getYbqyblthird();//��������һ����ҵ������
			}else if(quarter.equals("4")){
				proportion=thcc.getZdqyblfour();//���ļ����ص������
				ybbl = thcc.getYbqyblfour();//���ļ���һ����ҵ������
			}
			if(keyLawobjList!=null && keyLawobjList.size()>0 && proportion!=0){
				int total = keyLawobjList.size();//�ص���ҵ����
				//float d =(float) proportion/100;//������
				//Ҫ��ȡ������
				int totalNum = 0;
				float total1=(float)(total*proportion)/100;
				int total2=(int)((total*proportion)/100);
				
				if(total1==total2){//������25%Ϊ����ȡ��
					totalNum = total2;
				}else{
					totalNum = total2+1;				//������25%������ȡ����1
				}
				int firstNum = 0;//��һ�γ����
				int secondNum = 0;//�ڶ��γ����
				if(total*0.25==(int)(total*0.25)){//������25%Ϊ����ȡ��
					firstNum = (int)(total*0.25);
				}else{
					firstNum = (int)(total*0.25)+1;				//������25%������ȡ����1
				}
				secondNum = totalNum-firstNum;
				//����ȳ����ѡʱû�б������ص���ȾԴ
				List<TDataLawobjf> noCheckedList = this.queryNoCheckedKeyLawobjnew(year, quarter, "0", areaid);
				
				//��һ�������飬�����󣺱����û�б��鵽���ص���ȾԴ 
				List<Integer> arr = new ArrayList<Integer>();//�����е���ֵ
				List<TDataLawobjf> firstCheckedList = new ArrayList<TDataLawobjf>();//������һ�γ����б�
				List<TDataLawobjf> secondCheckedList = new ArrayList<TDataLawobjf>();//�����ڶ��γ����б�
				
					if(noCheckedList!=null && noCheckedList.size()>0){
						if(firstNum>noCheckedList.size()){
							firstNum = noCheckedList.size();
							secondNum = totalNum- noCheckedList.size();
						}
						for(int i=0;i<firstNum;i++){
							int k = (int) ((Math.random())*(noCheckedList.size()));
							if(!arr.contains(k)){//k���������У���ʾ��δ����ȡ��
								arr.add(k);
								firstCheckedList.add(noCheckedList.get(k));
							}else{
								i--;//���³�ȡ
							}
					    }
				     }
				//�ѵ�һ�γ�鵽���ص���ȾԴ���뵽�������ȾԴ����
				spotCheckManager.saveCheckedListnew(year,quarter,"0",firstCheckedList,areaid);
				
				
				
				//�ڶ��γ����󣨱���û�б����е���ȾԴ��
				List<TDataLawobjf> thisTimeNocheckedList = new ArrayList<TDataLawobjf>();
				//�õ�����û�б���鵽����ȾԴ�б�
				for(int i=0;i<keyLawobjList.size();i++){
					int flag=0;
					for(int j=0;j<firstCheckedList.size();j++){
						TDataLawobjf lawobj = keyLawobjList.get(i);
						TDataLawobjf lawobj1 = firstCheckedList.get(j);
						if(lawobj!=null && lawobj1!=null && lawobj.getId().equals(lawobj1.getId())){//�����ѱ�����
							flag=1;
							break;
						}
					}
					if(flag==0){//����δ�����еļ����б�
						TDataLawobjf f= keyLawobjList.get(i);
						thisTimeNocheckedList.add(f);
					}else{
						flag=0;
					}
				}
								
				//�ڶ��������飬�����󣺱���û�б��鵽���ص���ȾԴ
				List<Integer> arr1 = new ArrayList<Integer>();//�����е���ֵ
				for(int i=0;i<secondNum;i++){
					int k = (int) ((Math.random())*(thisTimeNocheckedList.size()));
					if(!arr1.contains(k)){//k���������У���ʾ��δ����ȡ��
						arr1.add(k);
						secondCheckedList.add(thisTimeNocheckedList.get(k));
					}else{
						i--;//���³�ȡ
					}
				}
				//�ѳ�鵽����ȾԴ���뵽�������ȾԴ����
				spotCheckManager.saveCheckedListnew(year,quarter,"1",secondCheckedList,areaid);
			}	
				/**
				 * ��ѡһ����ҵ��������һ����ҵ�г�ѡ
				 */
				List<TDataLawobjf> allNormalLawobjList = this.queryAllNormalListnew(areaid,year);
				List<TDataLawobjf> CheckedNormalList = this.queryCheckedLawobjListnew(areaid,year);//���Ѿ���ȡ������ҵ��ȡ�������������ȳ����������
				
				
				//��ѯ�û����õ��ڱ��ڸ���Ա����
				List<TSysUser> users=this.dao.find("from TSysUser t where t.isActive='Y' and t.isZaiBian='Y' and t.areaId=?", areaid);
				//��Ա����
				int usersl=users.size();
				if(allNormalLawobjList!=null && allNormalLawobjList.size()>0){
					int number = allNormalLawobjList.size();
					List<TDataLawobjf> normalCheckedList = new ArrayList<TDataLawobjf>();//����һ����ҵ�����б�
					int ybtotal=(int)(usersl*ybbl);//�����ȳ�ȡ��ҵ��
					//���ȳ������
					int checkedNum=0;
					if(usersl*ybbl==ybtotal){//����Ϊ����ȡ��
						checkedNum = ybtotal;
					}else{
						checkedNum = ybtotal+1;//�����ķ�����ȡ����1
					}
					List<Integer> arr1 = new ArrayList<Integer>();//�����е���ֵ
					if(number > ybtotal){
						for(int i=0;i<checkedNum && i<allNormalLawobjList.size();i++){
							int k = (int) ((Math.random())*(allNormalLawobjList.size()));
							if(!arr1.contains(k)){//k���������У���ʾ��δ����ȡ��
								arr1.add(k);
								normalCheckedList.add(allNormalLawobjList.get(k));
							}else{
									i--;//���³�ȡ
							}
						}
					}else if(number < ybtotal && "1".equals(quarter)){
						int yushu;
						yushu = ybtotal - number;
						if(allNormalLawobjList != null && allNormalLawobjList.size() > 0){
							for(int i=0;i<checkedNum && i<allNormalLawobjList.size();i++){
								normalCheckedList.add(allNormalLawobjList.get(i));
							}
						}
						if(CheckedNormalList != null && CheckedNormalList.size() > 0){
							for(int i=0;i<checkedNum && i<yushu;i++){
								int k = (int) ((Math.random())*(CheckedNormalList.size()));
								normalCheckedList.add(CheckedNormalList.get(k));
							}
						}else{
							if(yushu < number){
								for(int i=0;i<checkedNum && i<yushu;i++){
									int k = (int) ((Math.random())*(allNormalLawobjList.size()));
									normalCheckedList.add(allNormalLawobjList.get(k));
								}
							}else{
								for(int i=0;i<number;i++){
									normalCheckedList.add(allNormalLawobjList.get(i));
								}
							}
						}
					}else{
						int yushu;
						yushu = ybtotal - number;
						for(int i=0;i<checkedNum && i<allNormalLawobjList.size();i++){
							normalCheckedList.add(allNormalLawobjList.get(i));
						}
						if(CheckedNormalList != null && CheckedNormalList.size() > 0){
							for(int i=0;i<checkedNum && i<yushu;i++){
								int k = (int) ((Math.random())*(CheckedNormalList.size()));
								normalCheckedList.add(CheckedNormalList.get(k));
							}
						}
					}
					spotCheckManager.saveCheckedListnew(year,quarter,"2",normalCheckedList,areaid);
				}else{
					List<TDataLawobjf> normalCheckedList = new ArrayList<TDataLawobjf>();//����һ����ҵ�����б�
					int ybtotal=(int)(usersl*ybbl);//�����ȳ�ȡ��ҵ��
					//���ȳ������
					int checkedNum=0;
					if(usersl*ybbl==ybtotal){//������25%Ϊ����ȡ��
						checkedNum = ybtotal;
					}else{
						checkedNum = ybtotal+1;//������25%������ȡ����1
					}
					if(CheckedNormalList != null && CheckedNormalList.size() > 0){
						for(int i=0;i<checkedNum && i<ybtotal;i++){
							int k = (int) ((Math.random())*(CheckedNormalList.size()));
							normalCheckedList.add(CheckedNormalList.get(k));
						}
					}
					spotCheckManager.saveCheckedListnew(year,quarter,"2",normalCheckedList,areaid);
				}
				/**
				 * ��ѡ������ҵ����������ҵ�г�ѡ
				 */
				/**
				 * 
				
				List<TDataLawobj> allSpecialLawobj=this.querySpecialLawobj(year, quarter, areaid);
				if(thcc.getTsjgqybl().equals("1")){
					spotCheckManager.saveCheckedList(year,quarter,"3",allSpecialLawobj,areaid);
					
				}else{
					spotCheckManager.saveCheckedList(year,quarter,"3",allSpecialLawobj,areaid);
					spotCheckManager.saveCheckedList(year,quarter,"3",allSpecialLawobj,areaid);
					spotCheckManager.saveCheckedList(year,quarter,"3",allSpecialLawobj,areaid);
				}
				*/
					ele.setExecuted("Y");
                	this.dao.save(ele);
				return "��ѡ�ɹ�";
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "success";
	}

	
	
	@Override
	public List<TDataLawobj> querySpecialLawobj(String year, String quarter,
			String areaid) throws Exception {
		List<TDataSpeciallawobj> list=new ArrayList<TDataSpeciallawobj>();
		List<TDataLawobj> lawobjs=new ArrayList<TDataLawobj>();
		if(StringUtil.isNotBlank(year)&&StringUtil.isNotBlank(quarter)&&StringUtil.isNotBlank(areaid)){
		    list=this.dao.find("from TDataSpeciallawobj t where t.isActive='Y' and t.type='0' and t.year=?  and t.areaId=?", year,areaid);
		    for(int i=0;i<list.size();i++){
		    	TDataLawobj obj=(TDataLawobj)this.get(TDataLawobj.class, list.get(i).getLawobjId());
		    	lawobjs.add(obj);
		    }	
		}
		return lawobjs;
	}

	@Override
	public List<TBizConfigpfsj> quarterConfigpfsj()
			throws Exception {
		List<TBizConfigpfsj> list=this.dao.find("from TBizConfigpfsj t where t.isActive='Y' and t.sfypf='N' ");
		return list;
	}

	@Override
	public String saveCheckedandPf(TBizConfigpfsj tsBizConfigpfsj) throws Exception {
		//�����ɷ�ʱ�����ñ������ɷ���
		String jieguo="";
		TBizConfigpf tpf=(TBizConfigpf) this.get(TBizConfigpf.class, tsBizConfigpfsj.gettBizConfigpf().getId());
		String year=tpf.getYear();
		String quarter=tpf.getQuarter();
		String month=tpf.getMonth();
		String areaid=tpf.getAreaId();
		String type=tpf.getType();//1��������ҵ��2������ҵ
		TBizHistoryconfigcheck thcc=this.queryTBizHistoryconfigcheck(year, month, areaid);
		if(StringUtil.isNotBlank(thcc.getGzsz())){
		if(thcc.getGzsz().equals("1")){//�����ѡ������
			jieguo=this.saveCreateWorkToUser(year, quarter,month, areaid, tsBizConfigpfsj,type);
		}else if(thcc.getGzsz().equals("2")){//�����ѡ������Э����
			jieguo=this.saveCreateWorkToUser(year, quarter, month,areaid, tsBizConfigpfsj,type);
		}else if(thcc.getGzsz().equals("3")){//��������ɷ�����ҵ������ܲ����쵼 
			jieguo=this.saveCreateWorkToLeader(year, quarter,month,tpf.getAreaId(), tsBizConfigpfsj,type);//��������
		}else if(thcc.getGzsz().equals("4")){//����ɷ�����ҵ����������Ա(������)
			jieguo=this.saveCreateWorkToOrgUser(year, quarter,month,areaid, tsBizConfigpfsj, type);
		}else if(thcc.getGzsz().equals("5")){//����ɷ�����ҵ����������Ա(�����ˣ�Э����) 
			jieguo=this.saveCreateWorkToOrgUser(year, quarter,month ,areaid, tsBizConfigpfsj, type);
		}
		}
		if(StringUtil.isNotBlank(jieguo)&&"success".equals(jieguo)){
			tsBizConfigpfsj.setSfypf("Y");
	        this.dao.save(tsBizConfigpfsj);
		}
		
		return "success";
	}

	
	/**
	 * ��ȡ�����ص���ȾԴ�б� �ع�����
	 * @areaId ����ID
	 * @author gaozhiyang
	 * @time 2017-7-6
	 * @throws Exception 
	 */
	public List<TDataLawobjf> queryAllKeyLawobjListByAreaIdnew(String areaId) throws Exception{
		Map<String, Object> condition = new HashMap<String, Object>();
		/*select f.*,o.name_ orgmc,r.name_ regionmc,a.name_ typemc from t_data_lawobjf f  
		left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_ 
		left join t_data_region  r on r.id_=f.ssxzq_  where 1=1 and f.isactive_='Y'  and ssxzq_ in (
		SELECT id_ from t_data_region where PID_  = ( SELECT PID_ from t_data_region  where id_ = ( SELECT code_ from t_sys_area where id_='4028e4484d656b9e014d6579282e0008' )) )
		and f.id_ in ( select fid_ from T_data_gywry  where kzlx_ in (1,2,3))*/
		StringBuffer listSql = new StringBuffer(" select f.id_ from t_data_lawobjf f  ");
		listSql.append(" left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_  ");
		listSql.append(" left join t_data_region  r on r.id_=f.ssxzq_  where 1=1 and f.isactive_='Y'  and ssxzq_ in ( ");
		listSql.append(" SELECT id_ from t_data_region where PID_  = ( SELECT PID_ from t_data_region  where id_ = ( SELECT code_ from t_sys_area  ");
		if(StringUtils.isNotBlank(areaId)){
		listSql.append(" where id_=:AreaId   ");
		condition.put("AreaId", areaId);
		}
		listSql.append(" )) )");
		listSql.append(" and f.id_ in ( select fid_ from T_data_gywry  where kzlx_ in ('1','2','3')) ");
			
		
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and f.qyzt_").append(" = :qyzt ");
		condition.put("qyzt", "0");
		
		List<TDataLawobjf> lawobjList=new ArrayList<TDataLawobjf>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobjf lawobj = new TDataLawobjf();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobjf) this.get(TDataLawobjf.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}

	
	
	
	/**
	 * �����ص���ȾԴ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllKeyLawobjListByAreaId(String areaId) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gywry_ssxzq.getCode(),
				LawobjOutColunmEnum.gywry_kzlx.getCode(),
				LawobjOutColunmEnum.gywry_qyzt.getCode());
		String ssxzq_column = null;
		String kzlx_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=3){
			throw new Exception("��ҵ��ȾԴ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			kzlx_column = String.valueOf(listColumn.get(1));
			qyzt_column = String.valueOf(listColumn.get(2));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append("left join t_sys_dic d on l.").append(kzlx_column).append(" = d.code_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '01' and (d.code_='1' or d.code_='2'or d.code_='3') and d.type_='6'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
		//String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaId);
		if(area.getType().equals("0")){//�ܶ�
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//ʦ��
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//�ż�
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//�������Ͻ����ҵ
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}

	/**
	 * ���з��ص���ȾԴ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryNoKeyLawobjListByAreaId(String areaid) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gywry_ssxzq.getCode(),
				LawobjOutColunmEnum.gywry_kzlx.getCode(),
				LawobjOutColunmEnum.gywry_qyzt.getCode());
		String ssxzq_column = null;
		String kzlx_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=3){
			throw new Exception("��ҵ��ȾԴ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			kzlx_column = String.valueOf(listColumn.get(1));
			qyzt_column = String.valueOf(listColumn.get(2));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append("left join t_sys_dic d on l.").append(kzlx_column).append(" = d.code_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '01' and (d.code_='4') and d.type_='6'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
		//String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//�ܶ�
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//ʦ��
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//�ż�
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}

	@Override
	public List<TBizCheckedLawobj> executePfLawobj(String year, String month,
			String areaid,TBizConfigpfsj tBizConfigpfsj) throws Exception {
		//ѡ�������е���ҵ
		List<TBizCheckedLawobj> allList=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type in (1,2) and t.year=? and t.month=? and t.areaId=?",year,month,areaid);
		//Ҫ�ɷ�����ҵ
		List<TBizCheckedLawobj> pfLawobj=new ArrayList<TBizCheckedLawobj>();
		//��ѯ����ҵ�ɷ�����
		TBizHistoryconfigcheck thcc=this.queryTBizHistoryconfigcheck(year, month, areaid);
		//�õ�����Ҫ�ɷ��ı���
		int intPfbl=tBizConfigpfsj.getPfbl();
		float pfbl=(float)intPfbl/100;
		//�õ�����Ҫ�ɷ�������
		int pfNumber=(int)((allList.size() * intPfbl)/100);
		int zsNumber=0;
		float pf2=allList.size() * intPfbl;
		float pf1=pf2/100;
		if(pfNumber==pf1){//���ȳ�ȡ��ҵ��Ϊ����,ȡ����
			zsNumber=pfNumber;
		}else{
			zsNumber=pfNumber+1;
		}
		if(StringUtil.isNotBlank(thcc.getId())){
			// ѡ��û�б��ɷ�������ҵ
			List<TBizCheckedLawobj> list=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.sfypf ='N' and t.year=? and t.month=? and t.areaId=?",year,month,areaid);
			
			if(zsNumber>list.size()){
				pfLawobj.addAll(list);
			}else{
				for(int i=0;i<zsNumber;i++){
					if(list!=null && list.size()>0){
						//TDataLawobj lawobj=(TDataLawobj)this.get(TDataLawobj.class, list.get(i).getLawobj().getId());
							pfLawobj.add(list.get(i));
					}
				}
			}	
		}
		return pfLawobj;
	}

	@Override
	public String saveCreateWorkToLeader(String year, String quarter,String month,String areaid, TBizConfigpfsj tBizConfigpfsj,String type) throws Exception{
		
		// ������ݼ��Ȳ�ѯ�����е���ҵ�б�
				List<TBizCheckedLawobj> checkedList=new ArrayList<TBizCheckedLawobj>();
				if(type.equals("1")){
					 checkedList = this.executePfLawobj(year, month,areaid, tBizConfigpfsj);
				}else if(type.equals("2")){
					 checkedList=this.querySpecialLawobjByPfbl(year, quarter, month,areaid,tBizConfigpfsj);
				}
		
				TSysUser admin = userManager.getUser("a0000000000000000000000000000000");
				if(checkedList!=null && checkedList.size()>0){
					if(checkedList.get(0).getTask()==null){
						//����鵽����ҵ������������ɷ�������ҵ�ļ�ܲ����쵼
						for(int i=0;i<checkedList.size();i++){
							Work work = new Work();
							String orgId;
							try {
								TDataLawobjf tf=(TDataLawobjf)this.get(TDataLawobjf.class,checkedList.get(i).getLawobjf().getId());
								orgId = tf.getSsjgbm();
								if(StringUtils.isNotBlank(orgId)){
									TSysUser user = orgManager.getLeaderByAreaId(areaid);
									if(user!=null){
									//TSysUser user=new TSysUser();
										work.setCreateUser(user);//�����ˣ������쵼
										work.setDjrId(user.getId());//�Ǽ���id
										work.setDjrName(user.getName());//�Ǽ���name
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							TDataLawobjf tDataLawobjf = (TDataLawobjf)dao.get(TDataLawobjf.class,checkedList.get(i).getLawobjf().getId());
							if(StringUtil.isNotBlank(tDataLawobjf.getId())){
							work.setName("�������");	//��������
							work.setWorkNote("�������"); //��������
							work.setSource("11");//������Դ��������
							work.setCreateTime(new Date());//����ʱ��
							work.setEmergency("1");//�����̶�:һ��
							work.setSecurity("3");//�����ܼ�:����
							work.setAreaid(doubleRandomManager.queryAreaIdByLawobjfId(tDataLawobjf.getId()));//������������
							Calendar endC = Calendar.getInstance();
							//��ȡ���ʱ��
							//TBizConfigpf tpf=(TBizConfigpf)this.get(TBizConfigpf,tBizConfigpfsj.gettBizConfigpf().getId());
			                endC.add(Calendar.DATE, tBizConfigpfsj.gettBizConfigpf().getBjsx()-1);//Ĭ�Ͻ����̶�һ������ʱ��
			                SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
			                String endTime= s.format(endC.getTime());
			               // Date date =s.parse(endTime);  
			                Date date = DateUtil.getEndDatetime(endTime);
			                work.setEndTime(date);
							work.setIsActive(YnEnum.Y.getCode());//״̬
							work.setZfdxType(tDataLawobjf.getLawobjtype().getId());
							work.setRwmcgs("1");
						    //����WORK����
							work = (Work) workDao.save(work);
							TBizCheckedLawobj tobj=(TBizCheckedLawobj)this.get(TBizCheckedLawobj.class, checkedList.get(i).getId());
							tobj.setSfypf("Y");
							this.dao.save(tobj);
						    //���������ִ������Ĺ���
							String lawobjname = tDataLawobjf.getDwmc();//ִ����������
						    String address = tDataLawobjf.getDwdz();//ִ�������ַ
						    String regionId = tDataLawobjf.getSsxzq();//��������
						    String fddbr = tDataLawobjf.getFddbr();//����������
						    String fddbrlxdh = tDataLawobjf.getFddbrdh();//������������ϵ�绰
						    String hbfzr = tDataLawobjf.getHbfzr();//����������
						    String hbfzrlxdh = tDataLawobjf.getHbfzrdh();//������������ϵ�绰
							TBizTaskandlawobj tBizTaskandlawobj = new TBizTaskandlawobj(
									work.getId(), tDataLawobjf.getLawobjtype().getId(), tDataLawobjf.getId(), lawobjname, regionId,
									address, fddbr, fddbrlxdh, hbfzr, "", hbfzrlxdh, "", "����������");
							tBizTaskandlawobj.setUpdateby(admin);
							tBizTaskandlawobj.setCreateby(admin);
						    this.dao.save(tBizTaskandlawobj);
						    
						    //����������������͹���
						    TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
						    tBizTaskandtasktype.setTaskid(work.getId());//����id
						    tBizTaskandtasktype.setTasktypeid(TaskTypeCode.RCJC.getCode());//�������ͣ��ֳ����
						    tBizTaskandtasktype.setIsActive(YnEnum.Y.getCode());//״̬
						    tBizTaskandtasktype.setCreated(new Date());//����ʱ��
						    tBizTaskandtasktype.setCreateby(admin);//������
						    tBizTaskandtasktype.setUpdateby(admin);
						    tBizTaskandtasktype.setUpdated(new Date());
						    this.dao.save(tBizTaskandtasktype);
						    
						    //�ѳ����б�������id�����ɵ��������
						    TBizCheckedLawobj tBizCheckedLawobj = checkedList.get(i);
						    tBizCheckedLawobj.setTask(work);
						    this.dao.save(tBizCheckedLawobj);//���³����б�
							
							}
						}
						return "success";
					}else{
						return "false";
					}
				}else{
					return "false";
				}
	}

	@Override
	public String saveCreateWorkToUser(String year, String quarter,String month, String areaid,
			TBizConfigpfsj tBizConfigpfsj,String type) throws Exception {
		// ������ݼ��Ȳ�ѯ�����е���ҵ�б�
		List<TBizCheckedLawobj> checkedList=new ArrayList<TBizCheckedLawobj>();
		if(type.equals("1")){//��������ҵ
			 checkedList = this.executePfLawobj(year, month,areaid, tBizConfigpfsj);
		}else if(type.equals("2")){//������ҵ
			 checkedList=this.querySpecialLawobjByPfbl(year, quarter, month,areaid,tBizConfigpfsj);
		}
		
		TSysUser admin = userManager.getUser("a0000000000000000000000000000000");
		if (checkedList != null && checkedList.size() > 0) {
			if (checkedList.get(0).getTask() == null) {
				// ����鵽����ҵ������������ɷ�
				String orgId="";
				for (int i = 0; i < checkedList.size(); i++) {
					Work work = new Work();
					try {
						TDataLawobjf tf=(TDataLawobjf)this.get(TDataLawobjf.class,checkedList.get(i).getLawobjf().getId());
						orgId = tf.getSsxzq();
						if(StringUtil.isNotBlank(orgId)){
							TSysUser user = orgManager.getLeaderByAreaId(areaid);
							if (user != null) {
								//TSysUser user=new TSysUser();
								work.setCreateUser(user);// �����ˣ������쵼
								work.setDjrId(user.getId());// �Ǽ���id
								work.setDjrName(user.getName());// �Ǽ���name
							}
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					}
					String id=checkedList.get(i).getLawobjf().getId();
					TDataLawobjf tDataLawobjf = (TDataLawobjf) dao.get(TDataLawobjf.class, id);
					String lawid=tDataLawobjf.getId();
					if(StringUtil.isNotBlank(lawid)){
						// ��ȡ����������û�
						// 1,���Ȼ�ȡ����ִ���û�
						List<TSysUser> users = userManager.queryUsersByAreaid(tDataLawobjf);
						if(users.size()>0){
						// 2,��users�����ȡ���һ���û�
						TSysUser jsuser = userManager.randomUser(users);
					
									work.setName("�������"); // ��������
									work.setWorkNote("�������"); // ��������
									work.setSource("11");// ������Դ��������
									work.setCreateTime(new Date());// ����ʱ��
									work.setEmergency("1");// �����̶�:һ��
									work.setSecurity("3");// �����ܼ�:����
									work.setAreaid(doubleRandomManager.queryAreaIdByLawobjfId(tDataLawobjf.getId()));// ������������
									Calendar endC = Calendar.getInstance();
									endC.add(Calendar.DATE, tBizConfigpfsj.gettBizConfigpf().getBjsx()-1);// Ĭ�Ͻ����̶�һ��20��
									SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
					                String endTime= s.format(endC.getTime());
					                Date date = DateUtil.getEndDatetime(endTime);
  
					                work.setEndTime(date);
									work.setIsActive(YnEnum.Y.getCode());// ״̬
									work.setZfdxType(tDataLawobjf.getLawobjtype().getId());
									// ����WORK����
									work = (Work) workDao.save(work);
									WorkDto frm = new WorkDto();
									// frm.setCreateTime(new Date());
									frm.setDjrId(work.getDjrId());
									frm.setBlrsfbc(work.getBlrsfbc());
									frm.setDjrName(work.getDjrName());
									frm.setEmergency(work.getEmergency());
									String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(work.getEndTime());
									frm.setEndTime(dateStr);
									frm.setId(work.getId());
									
				
									frm.setJsr(jsuser.getId());
									frm.setJsrId(jsuser.getId());
									frm.setJsrIds(jsuser.getId());
									frm.setJsrName(jsuser.getName());
									// frm.setJsrNames(jsrNames);
									// frm.setPFileInfo(fileInfo);
									// frm.setPLspsInfo(lspsInfo);
									// frm.setPsyj(psyj);
									// frm.setRcbg(rcbg);
									frm.setRwmcgs("1");
									String rwmcrq = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
									frm.setRwmcrq(rwmcrq);
									frm.setSecurity(work.getSecurity());
									frm.setSfdb(work.getSfdb());
									frm.setSource(work.getSource());
									// frm.setStartTime(startTime);
									frm.setWorkName(work.getName());
									frm.setWorkNote(work.getWorkNote());
									frm.setXfbcjsrId(work.getXfbcjsrId());
									frm.setXfbcjsrName(work.getXfbcjsrName());
									frm.setXfdjbId(work.getXfdjbId());
									// frm.setXfts(work.getx);
									// frm.setXptype(work.getx);
									frm.setZfdxmc(work.getZfdxmc());
									frm.setZfdxType(work.getZfdxType());
									workPf.savePf(frm, jsuser.getId(), jsuser, "zfjc");
									TBizCheckedLawobj tobj=(TBizCheckedLawobj)this.get(TBizCheckedLawobj.class, checkedList.get(i).getId());
									tobj.setSfypf("Y");
									this.dao.save(tobj);
									
									
									//TBizConfigcheck configCheck=this.queryTbizConfigCheck(areaid);
									TBizHistoryconfigcheck thcc=this.queryTBizHistoryconfigcheck(year, month, areaid);
									if(StringUtil.isBlank(thcc.getGzsz())){
										List<TBizConfigcheck> list=this.dao.find("from TBizConfigcheck t where t.isActive='Y' and t.areaId=?", areaid);
										if(list.size()>0){
											thcc.setGzsz(list.get(0).getGzsz());
										}
									}
									
									if(thcc.getGzsz().equals("2")){
										// ��users�����ȡ���һ���û� ���ҳ�ȥ������
										for(int m=0;m<users.size();m++){
											if(StringUtil.isNotBlank(users.get(m).getId())){
												if(users.get(m).getId().equals(jsuser.getId())){
													users.remove(m);
												}	
											}
											
									    		//}
										}
										TSysUser xbr = userManager.randomUser(users);
										if(StringUtil.isNotBlank(xbr.getId())){
											//����Э����
											commWorkManager.saveRyMul(work.getId(), xbr.getId()+",", TaskUserEnum.XBR.getCode());	
										}
										
									}
									
									
									//���������ִ������Ĺ���
								    String lawobjname = tDataLawobjf.getDwmc();//ִ����������
								    String address = tDataLawobjf.getDwdz();//ִ�������ַ
								    String regionId = tDataLawobjf.getSsxzq();//��������
								    String fddbr = tDataLawobjf.getFddbr();//����������
								    String fddbrlxdh = tDataLawobjf.getFddbrdh();//������������ϵ�绰
								    String hbfzr = tDataLawobjf.getHbfzr();//����������
								    String hbfzrlxdh = tDataLawobjf.getHbfzrdh();//������������ϵ�绰
									TBizTaskandlawobj tBizTaskandlawobj = new TBizTaskandlawobj(
											work.getId(), tDataLawobjf.getLawobjtype().getId(), tDataLawobjf.getId(), lawobjname, regionId,
											address, fddbr, fddbrlxdh, hbfzr, "", hbfzrlxdh, "", "����������");
									tBizTaskandlawobj.setUpdateby(admin);
									tBizTaskandlawobj.setCreateby(admin);
								    this.dao.save(tBizTaskandlawobj);
								    
								    //����������������͹���
								    TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
								    tBizTaskandtasktype.setTaskid(work.getId());//����id
								    tBizTaskandtasktype.setTasktypeid(TaskTypeCode.RCJC.getCode());//�������ͣ��ֳ����
								    tBizTaskandtasktype.setIsActive(YnEnum.Y.getCode());//״̬
								    tBizTaskandtasktype.setCreated(new Date());//����ʱ��
								    tBizTaskandtasktype.setCreateby(admin);//������
								    tBizTaskandtasktype.setUpdateby(admin);
								    tBizTaskandtasktype.setUpdated(new Date());
								    this.dao.save(tBizTaskandtasktype);
								    
								    //�ѳ����б�������id�����ɵ��������
								    TBizCheckedLawobj tBizCheckedLawobj = checkedList.get(i);
								    tBizCheckedLawobj.setTask(work);
								    this.dao.save(tBizCheckedLawobj);//���³����б�
								}else{
									return "flase";	
								}
						
					}else{
						return "flase";
					}	
				}
			}else{
				return "flase";
			}
		}
		return "success";
	}

	@Override
	public String saveCreateWorkToUserAndXbr(String year, String quarter,
			String areaid, TBizConfigpfsj tBizConfigpfsj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * ���з��ص���ȾԴ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryNoKeyLawobjList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gywry_ssxzq.getCode(),
				LawobjOutColunmEnum.gywry_kzlx.getCode(),
				LawobjOutColunmEnum.gywry_qyzt.getCode());
		String ssxzq_column = null;
		String kzlx_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=3){
			throw new Exception("��ҵ��ȾԴ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			kzlx_column = String.valueOf(listColumn.get(1));
			qyzt_column = String.valueOf(listColumn.get(2));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append("left join t_sys_dic d on l.").append(kzlx_column).append(" = d.code_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '01' and (d.code_='4') and d.type_='6'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
		String areaid = areaId;
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//�ܶ�
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//ʦ��
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//�ż�
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		if(StringUtil.isNotBlank(year)){
			listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
			condition.put("year1", year);
		}
		
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	/**
	 * ���н�����Ŀ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllJsxmList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ? ");
	    sql.append(" union all");
	    sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.jsxm_jsjdjsczt.getCode(),
				LawobjOutColunmEnum.jsxm_ssxzq.getCode());
		String ssxzq_column = null;
		String jsjdjsczt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("������Ŀ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(1));
			jsjdjsczt_column = String.valueOf(listColumn.get(0));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '02'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//����״̬������
		listSql.append(" and l.").append(jsjdjsczt_column).append(" != '6' ");
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ����ҽԺ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllYyList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.yy_ssxzq.getCode(),
				LawobjOutColunmEnum.yy_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("ҽԺ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '03'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ���й�¯�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllGlList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gl_ssxzq.getCode(),
				LawobjOutColunmEnum.gl_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("��¯���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '04'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ���н��������б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllJzgdList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.jzgd_ssxzq.getCode(),
				LawobjOutColunmEnum.jzgd_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("�����������ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '05'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ���������б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllScList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.sc_ssxzq.getCode(),
				LawobjOutColunmEnum.sc_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("�������ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '06'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ����������ֳ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllXqyzList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.xqyz_ssxzq.getCode(),
				LawobjOutColunmEnum.xqyz_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("������ֳ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '07'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ���з���ҵ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllFwyList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.fwy_ssxzq.getCode(),
				LawobjOutColunmEnum.fwy_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("����ҵ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '08'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ������ʳҵ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllYsyList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.ysy_ssxzq.getCode(),
				LawobjOutColunmEnum.ysy_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("��ʳҵ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '09'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ������������ҵ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllZzyList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.zzy_ssxzq.getCode(),
				LawobjOutColunmEnum.zzy_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("��������ҵ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '10'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	
	/**
	 * ��������ҵ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllYlyList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.yly_ssxzq.getCode(),
				LawobjOutColunmEnum.yly_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("����ҵ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '11'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 *  �ع���ѯ����һ����ҵ�б�(���з��ص㹤ҵ��ȾԴ����������ִ������������ҵ)
	 *  @author gaozhiyang
	 *  @time �ع�ʱ�� 2017-7-6
	 *  @areaId ����ID
	 *  @year ������ 
	 * @throws Exception 
	 */
	public List<TDataLawobjf> queryAllNormalListnew(String areaId,String year) throws Exception{
		List<TDataLawobjf> lawobjList = new ArrayList<TDataLawobjf>();
		//���ص�Ĺ�ҵ��ȾԴ
		lawobjList.addAll(this.queryGywryNormalList(areaId,year));//���ص㹤ҵ��ȾԴ
		//����ҵ��ȾԴ��һ����ҵ
		
		lawobjList.addAll(this.queryNormalList(areaId,year));
		return lawobjList;
	}
	//��ѯ��ҵ��ȾԴ��һ����ҵ
	public List<TDataLawobjf> queryGywryNormalList(String areaId,String year) throws Exception{
        Map<String, Object> condition = new HashMap<String, Object>();
		
		StringBuffer listSql = new StringBuffer(" select f.id_ from t_data_lawobjf f  ");
		listSql.append(" left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_  ");
		listSql.append(" left join t_data_region  r on r.id_=f.ssxzq_  where 1=1 and f.isactive_='Y'  and ssxzq_ in ( ");
		listSql.append(" SELECT id_ from t_data_region where PID_  = ( SELECT PID_ from t_data_region  where id_ = ( SELECT code_ from t_sys_area  ");
		if(StringUtils.isNotBlank(areaId)){
		listSql.append(" where id_=:AreaId");
		condition.put("AreaId", areaId);
		}
		listSql.append(" )) )");
		listSql.append(" and f.id_ not in ( select fid_ from T_data_gywry  where kzlx_='4') ");
		//��ҵ״̬:��Ӫ��
		listSql.append(" and qyzt").append(" = :qyzt ");
		condition.put("qyzt", "0");
		
			
		List<TDataLawobjf> lawobjList = new ArrayList<TDataLawobjf>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobjf lawobj = new TDataLawobjf();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobjf) this.get(TDataLawobjf.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		
		return lawobjList;
	}
	
	//��ѯ����ҵ��ȾԴ��һ����ҵ
		public List<TDataLawobjf> queryNormalList(String areaId,String year) throws Exception{
	        Map<String, Object> condition = new HashMap<String, Object>();
			
			StringBuffer listSql = new StringBuffer(" select f.id_ from t_data_lawobjf f  ");
			listSql.append(" left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_  ");
			listSql.append(" left join t_data_region  r on r.id_=f.ssxzq_  where 1=1 and f.isactive_='Y'  and ssxzq_ in ( ");
			listSql.append(" SELECT id_ from t_data_region where PID_  = ( SELECT PID_ from t_data_region  where id_ = ( SELECT code_ from t_sys_area  ");
			if(StringUtils.isNotBlank(areaId)){
			listSql.append(" where id_=:AreaId   ");
			condition.put("AreaId", areaId);
			}
			listSql.append(" )) )");
			listSql.append(" and f.id_ not in ( select fid_ from T_data_gywry  where kzlx_='4') ");
			//��ҵ״̬:��Ӫ��
			listSql.append(" and qyzt").append(" = :qyzt ");
			condition.put("qyzt", "0");
			
				
			List<TDataLawobjf> lawobjList = new ArrayList<TDataLawobjf>();
			List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
			for(int i =0; i<list.size(); i++){
				TDataLawobjf lawobj = new TDataLawobjf();
				if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
					lawobj = (TDataLawobjf) this.get(TDataLawobjf.class, String.valueOf(list.get(i)));
				}
				lawobjList.add(lawobj);
			}
			
			return lawobjList;
		}
	
	
	
	
	
	/**
	 *  ��ѯ����һ����ҵ�б�(���з��ص㹤ҵ��ȾԴ����������ִ������������ҵ)
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllNormalList(String areaId,String year) throws Exception{
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		//���ص�Ĺ�ҵ��ȾԴ
		lawobjList.addAll(this.queryNoKeyLawobjList(areaId,year));//���ص㹤ҵ��ȾԴ
		lawobjList.addAll(this.queryAllJsxmList(areaId,year));//������Ŀ
		lawobjList.addAll(this.queryAllYyList(areaId,year));//ҽԺ
		lawobjList.addAll(this.queryAllGlList(areaId,year));//��¯
		//lawobjList.addAll(this.queryAllJzgdList(areaId,year));//����������ʱ������������
		lawobjList.addAll(this.queryAllScList(areaId,year));//����
		lawobjList.addAll(this.queryAllXqyzList(areaId,year));//������ֳ
		lawobjList.addAll(this.queryAllFwyList(areaId,year));//����ҵ
		lawobjList.addAll(this.queryAllYsyList(areaId,year));//��ʳҵ
		lawobjList.addAll(this.queryAllZzyList(areaId,year));//����ҵ
		lawobjList.addAll(this.queryAllYlyList(areaId,year));//����ҵ
		return lawobjList;
	}

	@Override
	public List<CheckedCountForm> queryCheckedCountFormList(String year,String quarter,String month, String areaid) throws Exception {
		List<CheckedCountForm> list=new ArrayList<CheckedCountForm>();
			List<Combobox> areaids=commonManager.queryAreaComboWithCur(areaid);
			CheckedCountForm cf=null;
			String btjsjid = "4028e4484fa18632014fedf40563147f";
			for(int i=0;i<areaids.size();i++){
				if(!btjsjid.equals(areaids.get(i).getId())){
					cf=new CheckedCountForm();
					cf=this.queryCheckedCountForm(year,quarter,month,areaids.get(i).getId(),areaids.get(i).getName());
					list.add(cf);
				}
			}
			 //������ܶӼ����ܼƣ�
			 String danwei=""; //��λ
			 int wryzs=0;//��ȾԴ����
			 int ybwry=0;//һ����ȾԴ
			 int zdwry=0;//�ص���ȾԴ
			 int tswry=0;//������ȾԴ
			 int jcrysl=0;//���������Ա����
			 int zjc=0;//�ܼҴ�
			 int ybjc=0;//һ�����۵�λ�Ҵ�
			 int zdjc=0;//�ص����۵�λ�Ҵ�
			 int tsjc=0;//�����ܶ���Ҵ�
			 int gksl=0;//��������Ϣ��������
			 int wfsl=0;//�����鷢�ֲ��鴦Υ����������
			if(list.size()>2){
				for(CheckedCountForm ccf:list){
					wryzs=wryzs+Integer.valueOf(ccf.getWryzs());
					ybwry=ybwry+Integer.valueOf(ccf.getYbwry());
					zdwry=zdwry+Integer.valueOf(ccf.getZdwry());
					tswry=tswry+Integer.valueOf(ccf.getTswry());
					jcrysl=jcrysl+Integer.valueOf(ccf.getJcrysl());
					zjc=zjc+Integer.valueOf(ccf.getZjc());
					ybjc=ybjc+Integer.valueOf(ccf.getYbjc());
					zdjc=zdjc+Integer.valueOf(ccf.getZdjc());
					tsjc=tsjc+Integer.valueOf(ccf.getTsjc());
					gksl=gksl+Integer.valueOf(ccf.getGksl());
					wfsl=wfsl+Integer.valueOf(ccf.getWfsl());
				}
				CheckedCountForm cfTotal=new CheckedCountForm();
				cfTotal.setDanwei("�ϼ�");
				cfTotal.setGksl(String.valueOf(gksl));
				cfTotal.setJcrysl(String.valueOf(jcrysl));
				cfTotal.setTsjc(String.valueOf(tsjc));
				cfTotal.setTswry(String.valueOf(tswry));
				cfTotal.setWfsl(String.valueOf(wfsl));
				cfTotal.setWryzs(String.valueOf(wryzs));
				cfTotal.setYbjc(String.valueOf(ybjc));
				cfTotal.setYbwry(String.valueOf(ybwry));
				cfTotal.setZdjc(String.valueOf(zdjc));
				cfTotal.setZdwry(String.valueOf(zdwry));
				cfTotal.setZjc(String.valueOf(zjc));
				cfTotal.setCurYear(list.get(0).getCurYear());
				cfTotal.setCurQuarter(list.get(0).getCurQuarter());
				cfTotal.setCurAreaId("");
				list.add(0, cfTotal);
			}
		return list;
	}

	@Override
	public CheckedCountForm queryCheckedCountForm(String year, String quarter,String month,
			String areaid,String danwei) throws Exception {
			CheckedCountForm cf=new CheckedCountForm();
			//��λ
			//String danwei=areaManager.getAreaByUser(CtxUtil.getCurUser().getId()).getName();
			cf.setDanwei(danwei);
			int tsqy=0;
			List<TDataSpeciallawobj>  list=new ArrayList<TDataSpeciallawobj>();
			if(quarter.equals("0")){
				list=this.dao.find("from TDataSpeciallawobj t where t.isActive='Y'  and t.year=?  and t.areaId=? ", year,areaid);
			}else{
				list=this.dao.find("from TDataSpeciallawobj t where t.isActive='Y'  and t.year=?  and t.areaId=? and t.quarter=? ", year,areaid,quarter);
			}
			tsqy=list.size();
			
			//List<TDataLawobjf> tDataLawobjs=doubleRandomManager.queryIllegalLawobjfList(String.valueOf(Integer.valueOf(year)-1));
			//tsqy=tsqy+tDataLawobjs.size();
			cf.setTswry(String.valueOf(tsqy));
			int zdqy=doubleRandomManager.queryAllKeyLawobjfListByAreaId(areaid).size();//�ص���ҵ
			cf.setZdwry(String.valueOf(zdqy));
			int ybqy=doubleRandomManager.queryNormalList(areaid, year).size();//һ����ҵ
			cf.setYbwry(String.valueOf(ybqy));
			//��ȾԴ����
			int wryzs=zdqy+ybqy;
			cf.setWryzs(String.valueOf(wryzs));
			//���������Ա����
			List<TSysUser> users=this.dao.find("from TSysUser t where t.isActive='Y' and t.isZaiBian='Y' and t.areaId=?", areaid);
			int jcrysl=users.size();
			cf.setJcrysl(String.valueOf(jcrysl));
			//һ�����۵�λ�Ҵ�
			List<TBizCheckedLawobj> ybjcs=new ArrayList<TBizCheckedLawobj>();
			if(quarter.equals("0")){
				ybjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='2' and t.sfypf='Y' and t.areaId=? and t.year=? ", areaid,year);
			}else if(quarter.equals("1")){
				ybjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='2' and t.sfypf='Y' and t.areaId=? and t.year=? and t.month in('01','02','03')", areaid,year);
			}else if(quarter.equals("2")){
				ybjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='2' and t.sfypf='Y' and t.areaId=? and t.year=? and t.month in('04','05','06')", areaid,year);
			}else if(quarter.equals("3")){
				ybjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='2' and t.sfypf='Y' and t.areaId=? and t.year=? and t.month in('07','08','09')", areaid,year);
			}else if(quarter.equals("4")){
				ybjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='2' and t.sfypf='Y' and t.areaId=? and t.year=? and t.month in('10','11','12')", areaid,year);
			}
			int ybjc=ybjcs.size();
			cf.setYbjc(String.valueOf(ybjc));
			//�ص����۵�λ�Ҵ�
			List<TBizCheckedLawobj> zdjcs=new ArrayList<TBizCheckedLawobj>();
			if(quarter.equals("0")){
				zdjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='1' and t.sfypf='Y' and  t.areaId=? and t.year=?  ", areaid,year);
			}else if(quarter.equals("1")){
				zdjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='1' and t.sfypf='Y' and  t.areaId=? and t.year=? and t.month in('01','02','03') ", areaid,year);
			}else if(quarter.equals("2")){
				zdjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='1' and t.sfypf='Y' and  t.areaId=? and t.year=? and t.month in('04','05','06') ", areaid,year);
			}else if(quarter.equals("3")){
				zdjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='1' and t.sfypf='Y' and  t.areaId=? and t.year=? and t.month in('07','08','09') ", areaid,year);
			}else if(quarter.equals("4")){
				zdjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='1' and t.sfypf='Y' and  t.areaId=? and t.year=? and t.month in('10','11','12') ", areaid,year);
			}		
            int zdjc=zdjcs.size();
            cf.setZdjc(String.valueOf(zdjc));
            //�����ܶ���Ҵ�
            List<TBizCheckedLawobj> tsjcs=new ArrayList<TBizCheckedLawobj>();
            if(quarter.equals("0")){
            	tsjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='3' and t.sfypf='Y' and t.areaId=? and t.year=?", areaid,year);

            }else{
            	tsjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='3' and t.sfypf='Y' and t.areaId=? and t.year=? and t.quarter=?", areaid,year,quarter);
            }
			int tsjc=tsjcs.size();
			cf.setTsjc(String.valueOf(tsjc));
			//�ܼҴ�
			int zjc=ybjc+zdjc+tsjc;
			cf.setZjc(String.valueOf(zjc));
			//��������Ϣ��������
			List<TBizCheckedLawobj> gkzs=new ArrayList<TBizCheckedLawobj>();
			if(quarter.equals("0")){
				gkzs=this.dao.findBySql("select  distinct t.lawobjid_ from t_biz_checkedlawobj t where t.isactive_='Y' and t.sfypf_='Y' and t.year_=?  and t.areaid_=?", year,areaid);
			}else{
				gkzs=this.dao.findBySql("select  distinct t.lawobjid_ from t_biz_checkedlawobj t where t.isactive_='Y' and t.sfypf_='Y' and t.year_=? and t.quarter_=? and t.areaid_=?", year,quarter,areaid);
			}
            int gksl=gkzs.size();
            cf.setGksl(String.valueOf(gksl));
            //�����鷢�ֲ��鴦Υ����������
            List<String> wfsls=new ArrayList<String>();
            if(quarter.equals("0")){
                wfsls=this.dao.findBySql("select t.id_ from work_ t left join t_biz_taskandtasktype a on t.id_=a.taskid_ left join t_biz_checkedlawobj c on c.taskid_=t.id_ where  a.tasktypeid_='16' and t.areaid_=? and c.year_=? ", areaid,year);
            }else{
                wfsls=this.dao.findBySql("select t.id_ from work_ t left join t_biz_taskandtasktype a on t.id_=a.taskid_ left join t_biz_checkedlawobj c on c.taskid_=t.id_ where  a.tasktypeid_='16' and t.areaid_=? and c.year_=? and c.quarter_=?", areaid,year,quarter);
            }
            int wfsl=wfsls.size();
            cf.setWfsl(String.valueOf(wfsl));
            cf.setCurAreaId(areaid);
            cf.setCurYear(year);
            cf.setCurQuarter(quarter);
		// ��������һ����ҵ
		return cf;
	}

	@Override
	public void exportCheckedCountForm(String year, String quarter,
			String areaid, HttpServletResponse res)
			throws Exception {
				//��������
				Map<String, List<Object>> map = new HashMap<String, List<Object>>();
//				��ȾԴ������
				List list = this.queryCheckedCountFormList(year, quarter, "",areaid);
				map.put("checkedCountForm", list);
				String realPath = servletContext.getRealPath(File.separator);
				//String realPath = servletContext.getRealPath(File.separator);
				InputStream is = null;
				OutputStream os = null;
				try {
					File file = ExcelUtil.setValue(
							new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", ""))), 
							new File(realPath + "excel/sjcc/checkedCount.xls"), 
							new File(realPath + "excel/sjcc/checkedCount.xml"), 
							map, false);
					//					ExcelUtil.copyStyle(file, "����������", 4, 0, 4 + list.size() - 1, 0 + 31);
					is = new FileInputStream(file);
					String quarterName="";
					if(quarter.equals("0")){
						quarterName="ȫ��";
					}else if(quarter.equals("1")){
						quarterName="��һ����";
					}else if(quarter.equals("2")){
						quarterName="�ڶ�����";
					}else if(quarter.equals("3")){
						quarterName="��������";
					}else if(quarter.equals("4")){
						quarterName="���ļ���";
					}
					String name=year+"��"+quarterName+"���������ͳ�Ʊ�.xls";
					String de = "attachment;filename=".concat(new String(name.getBytes("GB2312"), "ISO-8859-1"));
					res.setHeader("Content-Disposition", de);
					res.setContentType("application/x-msdownload");
					os = res.getOutputStream();
					byte[] b = new byte[1024];
					int length;
					while ((length = is.read(b)) > 0) {
						os.write(b, 0, length);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (is != null)
							is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						if (os != null){
							os.flush();
							os.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		
	}

	@Override
	public String queryLowPfbl(String year, String quarter,String areaid) throws Exception {
		String num="0";
		ConfigCheckForm tf=this.queryConfigCheck(areaid);
		//ѡ�����������е���ҵ
		List<TBizCheckedLawobj> allList=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y'  and t.year=? and t.quarter=? and t.areaId=?",year,quarter,areaid);
		//��ѯ������Ҫ�ɷ���������ҵ
		List<TBizCheckedLawobj> specilList=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='3' and t.year=? and t.quarter=?  and t.areaId=?",
				year,quarter,areaid);
		int zs=0;
		if(StringUtil.isBlank(tf.getTsjgqybl())){
			if(tf.getTsjgqybl().equals("2")){
				float pfbl=(float) specilList.size()/(allList.size()+specilList.size()*2);
				zs=(int)(pfbl*100);
			}
			num=String.valueOf(zs+2);
		}
		
		return num;
	}

	@Override
	public String saveHistoryConfigCheck(String year, String quarter,
			TBizConfigcheck tcc) throws Exception {
		String err = "";
		TBizHistoryconfigcheck thc=this.queryTBizHistoryconfigcheck(year, quarter, tcc.getAreaId());
		//TBizConfigcheck tcc = this.queryTbizConfigCheck(areaid);
		if(StringUtil.isNotBlank(thc.getId())){
			if(StringUtil.isNotBlank(year)){
				if(tcc.getId() != null){
					TBizHistoryconfigcheck thcc=(TBizHistoryconfigcheck)this.get(TBizHistoryconfigcheck.class,thc.getId());
					thcc.setAreaId(tcc.getAreaId());
					thcc.setCreater(tcc.getCreater());
					thcc.setCreateTime(tcc.getCreateTime());
					thcc.setGzsz(tcc.getGzsz());
					thcc.setIsActive("Y");
					thcc.setQuarter(quarter);
					thcc.setTsjgqybl(tcc.getTsjgqybl());
					thcc.setUpdateby(tcc.getUpdateby());
					thcc.setUpdateTime(tcc.getUpdateTime());
					thcc.setYbqybl(tcc.getYbqybl());
					thcc.setYbqyblfour(tcc.getYbqyblfour());
					thcc.setYbqyblsecond(tcc.getYbqyblsecond());
					thcc.setYbqyblthird(tcc.getYbqyblthird());
					thcc.setYear(year);
					thcc.setZdqybl(tcc.getZdqybl());
					thcc.setZdqyblsecond(tcc.getZdqyblsecond());
					thcc.setZdqyblthird(tcc.getZdqyblthird());
					thcc.setZdqyblfour(tcc.getZdqyblfour());
					thcc.setYbqyapr(tcc.getYbqyapr());
					thcc.setYbqyaug(tcc.getYbqyaug());
					thcc.setYbqydec(tcc.getYbqydec());
					thcc.setYbqyfeb(tcc.getYbqyfeb());
					thcc.setYbqyjan(tcc.getYbqyjan());
					thcc.setYbqyjul(tcc.getYbqyjul());
					thcc.setYbqyjun(tcc.getYbqyjun());
					thcc.setYbqymar(tcc.getYbqymar());
					thcc.setYbqymay(tcc.getYbqymay());
					thcc.setYbqynov(tcc.getYbqynov());
					thcc.setYbqyoct(tcc.getYbqyoct());
					thcc.setYbqysep(tcc.getYbqysep());
					thcc.setZdqyapr(tcc.getZdqyapr());
					thcc.setZdqyaug(tcc.getZdqyaug());
					thcc.setZdqydec(tcc.getZdqydec());
					thcc.setZdqyfeb(tcc.getZdqyfeb());
					thcc.setZdqyjan(tcc.getZdqyjan());
					thcc.setZdqyjul(tcc.getZdqyjul());
					thcc.setZdqyjun(tcc.getZdqyjun());
					thcc.setZdqymar(tcc.getZdqymar());
					thcc.setZdqymay(tcc.getZdqymay());
					thcc.setZdqynov(tcc.getZdqynov());
					thcc.setZdqyoct(tcc.getZdqyoct());
					thcc.setZdqysep(tcc.getZdqysep());
					this.dao.save(thcc);
					err = tcc.getId();
				}
			}
		}else{
			if(StringUtil.isNotBlank(year)){
				if(tcc.getId() != null){
					TBizHistoryconfigcheck thcc=new TBizHistoryconfigcheck();
					thcc.setAreaId(tcc.getAreaId());
					thcc.setCreater(tcc.getCreater());
					thcc.setCreateTime(tcc.getCreateTime());
					thcc.setGzsz(tcc.getGzsz());
					thcc.setIsActive("Y");
					thcc.setQuarter(quarter);
					thcc.setTsjgqybl(tcc.getTsjgqybl());
					thcc.setUpdateby(tcc.getUpdateby());
					thcc.setUpdateTime(tcc.getUpdateTime());
					thcc.setYbqybl(tcc.getYbqybl());
					thcc.setYbqyblfour(tcc.getYbqyblfour());
					thcc.setYbqyblsecond(tcc.getYbqyblsecond());
					thcc.setYbqyblthird(tcc.getYbqyblthird());
					thcc.setYear(year);
					thcc.setZdqybl(tcc.getZdqybl());
					thcc.setZdqyblsecond(tcc.getZdqyblsecond());
					thcc.setZdqyblthird(tcc.getZdqyblthird());
					thcc.setZdqyblfour(tcc.getZdqyblfour());
					thcc.setYbqyapr(tcc.getYbqyapr());
					thcc.setYbqyaug(tcc.getYbqyaug());
					thcc.setYbqydec(tcc.getYbqydec());
					thcc.setYbqyfeb(tcc.getYbqyfeb());
					thcc.setYbqyjan(tcc.getYbqyjan());
					thcc.setYbqyjul(tcc.getYbqyjul());
					thcc.setYbqyjun(tcc.getYbqyjun());
					thcc.setYbqymar(tcc.getYbqymar());
					thcc.setYbqymay(tcc.getYbqymay());
					thcc.setYbqynov(tcc.getYbqynov());
					thcc.setYbqyoct(tcc.getYbqyoct());
					thcc.setYbqysep(tcc.getYbqysep());
					thcc.setZdqyapr(tcc.getZdqyapr());
					thcc.setZdqyaug(tcc.getZdqyaug());
					thcc.setZdqydec(tcc.getZdqydec());
					thcc.setZdqyfeb(tcc.getZdqyfeb());
					thcc.setZdqyjan(tcc.getZdqyjan());
					thcc.setZdqyjul(tcc.getZdqyjul());
					thcc.setZdqyjun(tcc.getZdqyjun());
					thcc.setZdqymar(tcc.getZdqymar());
					thcc.setZdqymay(tcc.getZdqymay());
					thcc.setZdqynov(tcc.getZdqynov());
					thcc.setZdqyoct(tcc.getZdqyoct());
					thcc.setZdqysep(tcc.getZdqysep());
					this.dao.save(thcc);
					err = tcc.getId();
				}
			}
		}
		
		return err;
	}

	@Override
	public TBizHistoryconfigcheck queryTBizHistoryconfigcheck(String year,
			String month, String areaid) throws Exception {
		// ���������year��quarterȥ��ѯ��ѡ����
		List<TBizHistoryconfigcheck> thcc=this.dao.find("from TBizHistoryconfigcheck t where t.isActive='Y' and t.year=?  and t.areaId=? ", year,areaid);
		TBizHistoryconfigcheck tc=new TBizHistoryconfigcheck();
		if(thcc.size()>0){
			tc=thcc.get(0);
		}
		return tc;
	}

	/*@Override
	public ConfigPfForm queryConfigPfForm(String year, String quarter,
			String type, String month, String areaid) throws Exception {
		ConfigPfForm con = new ConfigPfForm();
		if(StringUtil.isBlank(areaid)){
			areaid=CtxUtil.getAreaId();
		}
		if (StringUtil.isNotBlank(year) &&StringUtil.isNotBlank(type)&&StringUtil.isNotBlank(month)) {
			List<TBizConfigpf> tpf = this.dao.find("from TBizConfigpf t where t.isActive='Y' and t.type=? and t.areaId=? and t.year=? and t.month=?",type,areaid, year, month);
			List<ConfigPfSjForm> consjList = new ArrayList<ConfigPfSjForm>();
			if (tpf.size() > 0) {
				List<TBizConfigpfsj> tsj = this.dao.find("from TBizConfigpfsj t where t.isActive='Y' and t.areaId=? and t.tBizConfigpf.id=? order by pfsj asc",areaid, tpf.get(0).getId());
				ConfigPfSjForm conpfsj = null;

				for (TBizConfigpfsj ts : tsj) {
					conpfsj = new ConfigPfSjForm();
					// conpfsj.setAreaId(ts.getAreaId());
					// conpfsj.setCreater(ts.getCreater());
					// conpfsj.setCreateTime(ts.getCreateTime());
					conpfsj.setId(ts.getId());
					conpfsj.setIsActive(ts.getIsActive());
					conpfsj.setPfbl(String.valueOf(ts.getPfbl()));
					String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(ts.getPfsj());
					conpfsj.setPfsj(dateStr);
					conpfsj.setSfypf(ts.getSfypf().equals("N") ? "��" : "��");
					conpfsj.setBjsx(String.valueOf(tpf.get(0).getBjsx()));
					// conpfsj.settBizConfigpf(ts.gettBizConfigpf());
					// conpfsj.setUpdateby(ts.getUpdateby());
					// conpfsj.setUpdateTime(ts.getUpdateTime());
					consjList.add(conpfsj);
				}
				con.setAreaId(tpf.get(0).getAreaId());
				con.setBjsx(String.valueOf(tpf.get(0).getBjsx()));
				con.setCreater(tpf.get(0).getCreater());
				con.setCreateTime(tpf.get(0).getCreateTime());
				// con.setData(data);
				con.setType(tpf.get(0).getType());
				con.setMonth(tpf.get(0).getMonth());
				con.setId(tpf.get(0).getId());
				con.setIsActive(tpf.get(0).getIsActive());
				
				con.setYear(year);
				con.setQuarter(quarter);
				con.setUpdateby(tpf.get(0).getUpdateby());
				con.setUpdateTime(tpf.get(0).getUpdateTime());
			} else {
				con.setYear(year);
				con.setQuarter(quarter);
				con.setType(type);
				con.setMonth(month);
			}
			con.setList(consjList);
		} else {
			ConfigPfSjForm conpfsj = new ConfigPfSjForm();
			List<ConfigPfSjForm> consjList = new ArrayList<ConfigPfSjForm>();
			consjList.add(conpfsj);
			con.setList(consjList);
		}
		return con;
	}*/

	@Override
	public ConfigPfForm specliallQueryConfigPfForm(String year, String quarter,
			String month,String type, String areaid) throws Exception {
		ConfigPfForm con = new ConfigPfForm();
		if(StringUtil.isBlank(areaid)){
			areaid=CtxUtil.getAreaId();
		}
		if (StringUtil.isNotBlank(year) &&StringUtil.isNotBlank(type)) {
			List<TBizConfigpf> tpf =new ArrayList<TBizConfigpf>();
			if(StringUtil.isNotBlank(month)&& StringUtil.isNotBlank(quarter)){
				tpf = this.dao.find("from TBizConfigpf t where t.isActive='Y' and t.month =? and t.type=? and t.areaId=? and t.year=? and t.quarter=?",month,type,areaid, year, quarter);
			}else{
			    tpf = this.dao.find("from TBizConfigpf t where t.isActive='Y' and t.month is null and t.type=? and t.areaId=? and t.year=? and t.quarter=?",type,areaid, year, quarter);
			}
			
			
			//List<TBizConfigpf> tpf = this.dao.findBySql("select * from t_biz_configpf t where t.isactive_='Y' and t.month_ is null and t.year_=? and t.quarter_=? and t.type_=? and t.areaid_=? ", year,quarter, type, areaid);
			List<ConfigPfSjForm> consjList = new ArrayList<ConfigPfSjForm>();
			if (tpf.size() > 0) {
				List<TBizConfigpfsj> tsj = this.dao
						.find("from TBizConfigpfsj t where t.isActive='Y' and t.areaId=? and t.tBizConfigpf.id=? order by pfsj asc",
								areaid, tpf.get(0).getId());
				ConfigPfSjForm conpfsj = null;

				for (TBizConfigpfsj ts : tsj) {
					conpfsj = new ConfigPfSjForm();
					// conpfsj.setAreaId(ts.getAreaId());
					// conpfsj.setCreater(ts.getCreater());
					// conpfsj.setCreateTime(ts.getCreateTime());
					conpfsj.setId(ts.getId());
					conpfsj.setIsActive(ts.getIsActive());
					conpfsj.setPfbl(String.valueOf(ts.getPfbl()));
					String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(ts.getPfsj());
					conpfsj.setPfsj(dateStr);
					conpfsj.setSfypf(ts.getSfypf().equals("N") ? "��" : "��");
					conpfsj.setBjsx(String.valueOf(tpf.get(0).getBjsx()));
					// conpfsj.settBizConfigpf(ts.gettBizConfigpf());
					// conpfsj.setUpdateby(ts.getUpdateby());
					// conpfsj.setUpdateTime(ts.getUpdateTime());
					consjList.add(conpfsj);
				}
				con.setAreaId(tpf.get(0).getAreaId());
				con.setBjsx(String.valueOf(tpf.get(0).getBjsx()));
				con.setCreater(tpf.get(0).getCreater());
				con.setCreateTime(tpf.get(0).getCreateTime());
				// con.setData(data);
				con.setType(tpf.get(0).getType());
				con.setId(tpf.get(0).getId());
				con.setIsActive(tpf.get(0).getIsActive());
				con.setMonth(tpf.get(0).getMonth());
				con.setYear(year);
				con.setQuarter(quarter);
				con.setUpdateby(tpf.get(0).getUpdateby());
				con.setUpdateTime(tpf.get(0).getUpdateTime());
			} else {
				con.setYear(year);
				con.setQuarter(quarter);
				con.setType("2");
			}
			con.setList(consjList);
		} else {
			ConfigPfSjForm conpfsj = new ConfigPfSjForm();
			List<ConfigPfSjForm> consjList = new ArrayList<ConfigPfSjForm>();
			consjList.add(conpfsj);
			con.setList(consjList);
		}
		return con;
	}

	@Override
	public List<Combobox> queryMonthByQuarter(String quarter) throws Exception {
		List<Combobox> list = new ArrayList<Combobox>();
		if("1".equals(quarter)){
			list.add(new Combobox("1", "һ��"));
			list.add(new Combobox("2", "����"));
			list.add(new Combobox("3", "����"));
		}else if("2".equals(quarter)){
			list.add(new Combobox("4", "����"));
			list.add(new Combobox("5", "����"));
			list.add(new Combobox("6", "����"));
		}else if("3".equals(quarter)){
			list.add(new Combobox("7", "����"));
			list.add(new Combobox("8", "����"));
			list.add(new Combobox("9", "����"));
		}else if("4".equals(quarter)){
			list.add(new Combobox("10", "ʮ��"));
			list.add(new Combobox("11", "ʮһ��"));
			list.add(new Combobox("12", "ʮ����"));
		}
		
		return list;
	}

	@Override
	public TBizConfigpf queryTbizConfigpfById(String id) {
		TBizConfigpf tpf=new TBizConfigpf();
		if(StringUtil.isNotBlank(id)){
			tpf=(TBizConfigpf)this.get(TBizConfigpf.class, id);
		}
		return tpf;
	}



	@Override
	public List<TBizCheckedLawobj> querySpecialLawobjByPfbl(String year,
			String quarter,String month, String areaid, TBizConfigpfsj tBizConfigpfsj)
			throws Exception {
				List<TDataSpeciallawobj> allList=new ArrayList<TDataSpeciallawobj>();
			    allList=this.dao.find("from TDataSpeciallawobj t where t.isActive='Y'  and t.year=? and t.quarter=? and t.areaId=?",year,quarter,areaid);
		        
				//�õ�����Ҫ�ɷ��ı���
				int intPfbl=tBizConfigpfsj.getPfbl();
				float pfbl=allList.size()*intPfbl;
				//�õ�����Ҫ�ɷ�������
				float pfNumber=pfbl/100;
				int zsNumber=0;
				if(pfNumber==(int)pfNumber){//���ȳ�ȡ��ҵ��Ϊ����,ȡ����
					zsNumber=(int)pfNumber;
				}else{
					zsNumber=(int)pfNumber+1;
				}
				//��ѡ����ҵ
				List<TDataLawobjf> tDataLawobjs=new ArrayList<TDataLawobjf>();
				List<TBizCheckedLawobj> tBizCheckedLawobjs=new ArrayList<TBizCheckedLawobj>();
				//������ҵtf.getTsjgqybl().equals('1')�������ɷ�
				//if(StringUtil.isNotBlank(thcc.getId())){
					//ѡ��������û���ɷ���������ҵ
					List<TDataSpeciallawobj> noList=this.dao.find("from TDataSpeciallawobj t where t.isActive='Y' and t.sfypf='N' and t.year=? and t.quarter=? and t.areaId=?",year,quarter,areaid);
					//float d =(float) proportion/100;//������
					//Ҫ��ȡ������
					//int totalNum = total*d==(int)(total*d)?(int)(total*d):(int)(total*d)+1;
					if(noList.size()==0){
						noList=this.dao.find("from TDataSpeciallawobj t where t.isActive='Y' and t.sfypf='Y' and t.year=? and t.quarter=? and t.areaId=?",year,quarter,areaid);
						for(int i=0;i<noList.size();i++){
							TDataSpeciallawobj ts=noList.get(i);
							ts.setSfypf("N");
							this.dao.save(ts);
						}
						noList=this.dao.find("from TDataSpeciallawobj t where t.isActive='Y' and t.sfypf='N' and t.year=? and t.quarter=? and t.areaId=?",year,quarter,areaid);

					}
					
					if(zsNumber>noList.size()){
						for(int i=0;i<noList.size();i++){
							if(noList!=null && noList.size()>0){
								TDataLawobjf lawobj=(TDataLawobjf)this.get(TDataLawobjf.class, noList.get(i).getLawobjId());
								tDataLawobjs.add(lawobj);
							}
							
							TDataSpeciallawobj ts=noList.get(i);
							ts.setSfypf("Y");
							this.dao.save(ts);
							
						}
					}else{
						for(int i=0;i<zsNumber;i++){
							if(noList!=null && noList.size()>0){
								TDataLawobjf lawobj=(TDataLawobjf)this.get(TDataLawobjf.class, noList.get(i).getLawobjId());
								tDataLawobjs.add(lawobj);
							}
							TDataSpeciallawobj ts=noList.get(i);
							ts.setSfypf("Y");
							this.dao.save(ts);
						}
					}	
					tBizCheckedLawobjs=spotCheckManager.saveCheckedList(year,quarter,month,"3", tDataLawobjs,areaid);

			//	}
				return tBizCheckedLawobjs;
	}

	@Override
	public void saveForYeartoSpecail(String year) throws Exception {
		// ��ȡ��һ��ȵ�Υ����ҵ
		TSysUser user=CtxUtil.getCurUser();
		List<TDataLawobjf> lists= doubleRandomManager.queryIllegalLawobjfList(year);
		//ȥ���ظ������ܼ����ҵ
		List<TDataLawobjf> list1= this.removeSameSpecail(lists,year, "1", user.getAreaId());
		for(TDataLawobjf lawobj:list1){
			TDataSpeciallawobj tsl=new TDataSpeciallawobj();
			tsl.setAreaId(user.getAreaId());
			tsl.setCreater(user.getCreateby());
			tsl.setCreateTime(new Date());
			tsl.setIsActive("Y");
			tsl.setLawobjId(lawobj.getId());
			tsl.setLawobjName(lawobj.getDwmc());
			
			tsl.setLawobjType(lawobj.getLawobjtype().getId());
			tsl.setQuarter("1");
			tsl.setSfypf("N");
			tsl.setType("1");
			tsl.setUpdateby(user);
			tsl.setUpdateTime(new Date());
			tsl.setYear(year);
			this.dao.save(tsl);
		}
		//ȥ���ظ������ܼ����ҵ
		List<TDataLawobjf> list2= this.removeSameSpecail(lists,year, "2", user.getAreaId());
		for(TDataLawobjf lawobj:list2){
			TDataSpeciallawobj tsl=new TDataSpeciallawobj();
			tsl.setAreaId(user.getAreaId());
			tsl.setCreater(user.getCreateby());
			tsl.setCreateTime(new Date());
			tsl.setIsActive("Y");
			tsl.setLawobjId(lawobj.getId());
			tsl.setLawobjName(lawobj.getDwmc());
			tsl.setLawobjType(lawobj.getLawobjtype().getId());
			tsl.setQuarter("2");
			tsl.setSfypf("N");
			tsl.setType("1");
			tsl.setUpdateby(user);
			tsl.setUpdateTime(new Date());
			tsl.setYear(year);
			this.dao.save(tsl);
		}
		//ȥ���ظ������ܼ����ҵ
		List<TDataLawobjf> list3= this.removeSameSpecail(lists,year, "3", user.getAreaId());
		for(TDataLawobjf lawobj:list3){
			TDataSpeciallawobj tsl=new TDataSpeciallawobj();
			tsl.setAreaId(user.getAreaId());
			tsl.setCreater(user.getCreateby());
			tsl.setCreateTime(new Date());
			tsl.setIsActive("Y");
			tsl.setLawobjId(lawobj.getId());
			tsl.setLawobjName(lawobj.getDwmc());
			tsl.setLawobjType(lawobj.getLawobjtype().getId());
			tsl.setQuarter("3");
			tsl.setSfypf("N");
			tsl.setType("1");
			tsl.setUpdateby(user);
			tsl.setUpdateTime(new Date());
			tsl.setYear(year);
			this.dao.save(tsl);
		}
		//ȥ���ظ������ܼ����ҵ
		List<TDataLawobjf> list4= this.removeSameSpecail(lists,year, "4", user.getAreaId());
		for(TDataLawobjf lawobj:list4){
			TDataSpeciallawobj tsl=new TDataSpeciallawobj();
			tsl.setAreaId(user.getAreaId());
			tsl.setCreater(user.getCreateby());
			tsl.setCreateTime(new Date());
			tsl.setIsActive("Y");
			tsl.setLawobjId(lawobj.getId());
			tsl.setLawobjName(lawobj.getDwmc());
			tsl.setLawobjType(lawobj.getLawobjtype().getId());
			tsl.setQuarter("4");
			tsl.setSfypf("N");
			tsl.setType("1");
			tsl.setUpdateby(user);
			tsl.setUpdateTime(new Date());
			tsl.setYear(year);
			this.dao.save(tsl);
		}
		
	}

	@Override
	public List<TDataSpeciallawobj> queryForSpecialLawobj(String year)
			throws Exception {
		List<TDataSpeciallawobj> list=this.dao.find("from TDataSpeciallawobj t where t.isActive='Y' and t.type='1' and t.year=?", year);
		return list;
	}

	@Override
	public FyWebResult queryCountLawObjList(String areaid, String year,
			String quarter,String type ,String page, String pageSize) throws Exception {
				Map<String,Object> condition = new HashMap<String,Object>();
		StringBuilder sql = new StringBuilder("SELECT c.lawobjid_   AS id,c.LAWOBJNAME_ AS name,c.lawobjtype_ AS type,COUNT(c.lawobjid_) AS times FROM t_biz_checkedlawobj c WHERE c.SFYPF_='Y' and c.ISACTIVE_='Y' ");
		//����������Ϊ���������ҵ
				if(StringUtil.isNotBlank(areaid)){
					sql.append(" and c.AREAID_ = :areaId");
					condition.put("areaId", areaid);
				}
				//���
				if(StringUtil.isNotBlank(year)){
					sql.append(" and c.YEAR_ = :year");
					condition.put("year", year);
				}
				
				//�·�
				if(StringUtil.isNotBlank(quarter)){
					if("0".equals(quarter)){
						
					}else if("1".equals(quarter)){
						sql.append(" and c.month_ in('01','02','03') ");
					}else if("2".equals(quarter)){
						sql.append(" and c.month_ in('04','05','06') ");
					}else if("3".equals(quarter)){
						sql.append(" and c.month_ in('07','08','09') ");
					}else if("4".equals(quarter)){
						sql.append(" and c.month_ in('10','11','12') ");
					}
					
				}
				if(StringUtil.isNotBlank(type)){
					if("1".equals(type)){
						sql.append(" and c.type_ ='2' ");
					}else if("2".equals(type)){
						sql.append(" and c.type_ ='1' ");
					}else if("3".equals(type)){
						sql.append(" and c.type_ ='3' ");
					}
				}
				//����
				sql.append(" GROUP BY c.lawobjid_,c.lawobjname_,c.lawobjtype_  order by c.LAWOBJTYPE_");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", String.valueOf(obj[1]));
			dataObject.put("type", String.valueOf(obj[2]));
			dataObject.put("times", String.valueOf(obj[3]));
			rows.add(dataObject);
		}
		
		return fy;
	}

	@Override
	public FyWebResult queryCountWFLawObjList(String areaid, String year,
			String quarter, String type, String page, String pageSize)
			throws Exception {
		Map<String,Object> condition = new HashMap<String,Object>();
		StringBuilder sql = new StringBuilder("select c.id_ as whid, c.LAWOBJNAME_ as whnane,p.JCSJ2_  as whtime," +
				"c.lawobjtype_ as lawobjtype,i.NAME_ as whType,u.NAME_ as whuser FROM t_biz_checkedlawobj c " +
				"left join Work_ w on w.id_=c.taskid_ left join T_BIZ_TASKILLEGALTYPE t on t.taskid_=w.id_ " +
				"left join T_DATA_ILLEGALTYPE i on i.id_=t.ILLEGALTYPEID_ " +
				"left join T_BIZ_TASKANDTASKTYPE p on p.TASKID_=w.id_ " +
				"left join T_SYS_USER u on w.ZXR_ID_ = u.ID_ " +
				"WHERE c.SFYPF_='Y' and c.ISACTIVE_='Y' and p.TASKTYPEID_ = '16' ");
		//����������Ϊ���������ҵ
				if(StringUtil.isNotBlank(areaid)){
					sql.append(" and c.AREAID_ = :areaId");
					condition.put("areaId", areaid);
				}
				//���
				if(StringUtil.isNotBlank(year)){
					sql.append(" and c.YEAR_ = :year");
					condition.put("year", year);
				}
				
				//�·�
				if(StringUtil.isNotBlank(quarter)){
					if("0".equals(quarter)){
						
					}else if("1".equals(quarter)){
						sql.append(" and c.month_ in('01','02','03') ");
					}else if("2".equals(quarter)){
						sql.append(" and c.month_ in('04','05','06') ");
					}else if("3".equals(quarter)){
						sql.append(" and c.month_ in('07','08','09') ");
					}else if("4".equals(quarter)){
						sql.append(" and c.month_ in('10','11','12') ");
					}
					
				}
				if(StringUtil.isNotBlank(type)){
					if("1".equals(type)){
						sql.append(" and c.type_ ='2' ");
					}else if("2".equals(type)){
						sql.append(" and c.type_ ='1' ");
					}else if("3".equals(type)){
						sql.append(" and c.type_ ='3' ");
					}
				}
				//����
				sql.append(" order by c.LAWOBJTYPE_");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", String.valueOf(obj[1]));
			dataObject.put("time", String.valueOf(obj[2]));
			dataObject.put("type", ZfdxLx.getByCode(String.valueOf(obj[3])).getText());
			dataObject.put("whType", String.valueOf(obj[4]));
			dataObject.put("user", String.valueOf(obj[5]));
			rows.add(dataObject);
		}
		
		return fy;
	}
	
	public List<TDataLawobjf> removeSameSpecail(List<TDataLawobjf> lists,String year,String quarter,String areaid) throws Exception {
		//����year��quarter����ȡ���ܼ����ҵ
		//��ȡ��һ���ȵ����ܼ����ҵ
		List<TDataSpeciallawobj> firstQuarter=this.dao.find("from TDataSpeciallawobj t where t.isActive='Y' and t.year=? and t.quarter=? and t.areaId=?", year,quarter,areaid);
		//List<TDataLawobj> list1= lists;
		for(int i=0;i<lists.size();i++){
			for(int j=0;j<firstQuarter.size();j++){
				if(lists.size()>0){
					if(lists.get(i).getId().equals(firstQuarter.get(j).getLawobjId())){
						lists.remove(i);
					}
				}
			}
		}
		return lists;
	}

	@Override
	public List<TSysUser> queryUserByAreaidAndIsZaiBianZaiGang(String areaid)
			throws Exception {
		List<TSysUser> users=new ArrayList<TSysUser>();
		if(StringUtil.isNotBlank(areaid)){
			users=this.dao.find("from TSysUser t where t.isActive='Y' and t.isZaiBian='Y' and t.areaId=?", areaid);
		}
		return users;
	}

	@Override
	public String saveCreateWorkToOrgUser(String year, String quarter,String month,String areaid, TBizConfigpfsj tBizConfigpfsj,String type) throws Exception {
		// ������ݼ��Ȳ�ѯ�����е���ҵ�б�
				List<TBizCheckedLawobj> checkedList=new ArrayList<TBizCheckedLawobj>();
				if(type.equals("1")){
					 checkedList = this.executePfLawobj(year, month,areaid, tBizConfigpfsj);
				}else if(type.equals("2")){
					 checkedList=this.querySpecialLawobjByPfbl(year, quarter, month,areaid,tBizConfigpfsj);
				}
				TSysUser admin = userManager.getUser("a0000000000000000000000000000000");
				if (checkedList != null && checkedList.size() > 0) {
					if (checkedList.get(0).getTask() == null) {
						// ����鵽����ҵ������������ɷ�
						for (int i = 0; i < checkedList.size(); i++) {
							Work work = new Work();
							String orgId;
							try {
								TDataLawobjf tf=(TDataLawobjf)this.get(TDataLawobjf.class,checkedList.get(i).getLawobjf().getId());
								orgId = tf.getSsjgbm();
								if (StringUtils.isNotBlank(orgId)) {
									//String areaid=CtxUtil.getCurUser().getAreaId();
									TSysUser user = orgManager.getLeaderByAreaId(areaid);
									if (user != null) {
										work.setCreateUser(user);// �����ˣ������쵼
										work.setDjrId(user.getId());// �Ǽ���id
										work.setDjrName(user.getName());// �Ǽ���name
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							String id=checkedList.get(i).getLawobjf().getId();
							TDataLawobjf tDataLawobjf = (TDataLawobjf) dao.get(TDataLawobjf.class, id);
							String lawid=tDataLawobjf.getId();
							if(StringUtil.isNotBlank(lawid)){
								// ��ȡ����������û�
								// 1,���Ȼ�ȡ����ִ���û�
								//List<TSysUser> users = userManager.queryUsersByAreaid(tDataLawobj);
								String orgId1 = tDataLawobjf.getSsjgbm();
								List<TSysUser> users=null;
								if(StringUtil.isNotBlank(orgId1)){
									users= orgManager.queryUsersByOrgId(orgId1);
									if(users.size()>0){
										
									}else{
										users = userManager.queryUsersByAreaid(tDataLawobjf);
									}
								}else{
									users = userManager.queryUsersByAreaid(tDataLawobjf);
								}
								if(users.size()>0){
								// 2,��users�����ȡ���һ���û�
								TSysUser jsuser = userManager.randomUser(users);
											work.setName("�������"); // ��������
											work.setWorkNote("�������"); // ��������
											work.setSource("11");// ������Դ��������
											work.setCreateTime(new Date());// ����ʱ��
											work.setEmergency("1");// �����̶�:һ��
											work.setSecurity("3");// �����ܼ�:����
											work.setAreaid(doubleRandomManager.queryAreaIdByLawobjfId(tDataLawobjf.getId()));// ������������
											Calendar endC = Calendar.getInstance();
											endC.add(Calendar.DATE, tBizConfigpfsj.gettBizConfigpf().getBjsx()-1);// Ĭ�Ͻ����̶�һ��20��
											SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
							                String endTime= s.format(endC.getTime());
							                Date date = DateUtil.getEndDatetime(endTime);
  
							                work.setEndTime(date);
											work.setIsActive(YnEnum.Y.getCode());// ״̬
											work.setZfdxType(tDataLawobjf.getLawobjtype().getId());
											// ����WORK����
											work = (Work) workDao.save(work);
											WorkDto frm = new WorkDto();
											// frm.setCreateTime(new Date());
											frm.setDjrId(work.getDjrId());
											frm.setBlrsfbc(work.getBlrsfbc());
											frm.setDjrName(work.getDjrName());
											frm.setEmergency(work.getEmergency());
											String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(work.getEndTime());
											frm.setEndTime(dateStr);
											frm.setId(work.getId());
											
											frm.setJsr(jsuser.getId());
											frm.setJsrId(jsuser.getId());
											frm.setJsrIds(jsuser.getId());
											
											frm.setJsrName(jsuser.getName());
											// frm.setJsrNames(jsrNames);
											// frm.setPFileInfo(fileInfo);
											// frm.setPLspsInfo(lspsInfo);
											// frm.setPsyj(psyj);
											// frm.setRcbg(rcbg);
											frm.setRwmcgs("1");
											String rwmcrq = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
											frm.setRwmcrq(rwmcrq);
											frm.setSecurity(work.getSecurity());
											frm.setSfdb(work.getSfdb());
											frm.setSource(work.getSource());
											// frm.setStartTime(startTime);
											frm.setWorkName(work.getName());
											frm.setWorkNote(work.getWorkNote());
											frm.setXfbcjsrId(work.getXfbcjsrId());
											frm.setXfbcjsrName(work.getXfbcjsrName());
											frm.setXfdjbId(work.getXfdjbId());
											// frm.setXfts(work.getx);
											// frm.setXptype(work.getx);
											frm.setZfdxmc(work.getZfdxmc());
											frm.setZfdxType(work.getZfdxType());
											workPf.savePf(frm, jsuser.getId(), jsuser, "zfjc");
											TBizCheckedLawobj tobj=(TBizCheckedLawobj)this.get(TBizCheckedLawobj.class, checkedList.get(i).getId());
											tobj.setSfypf("Y");
											this.dao.save(tobj);
											
											
											//TBizConfigcheck configCheck=this.queryTbizConfigCheck(areaid);
											TBizHistoryconfigcheck thcc=this.queryTBizHistoryconfigcheck(year, month, areaid);
											if(StringUtil.isBlank(thcc.getGzsz())){
												List<TBizConfigcheck> list=this.dao.find("from TBizConfigcheck t where t.isActive='Y' and t.areaId=?", areaid);
												if(list.size()>0){
													thcc.setGzsz(list.get(0).getGzsz());
												}
											}
											if(users.size()>1){
												if(thcc.getGzsz().equals("5")){
													// ��users�����ȡ���һ���û� ���ҳ�ȥ������
													for(int m=0;m<users.size();m++){
														if(StringUtil.isNotBlank(users.get(m).getId())){
															if(users.get(m).getId().equals(jsuser.getId())){
																users.remove(m);
															}	
														}
														
												    		//}
													}
													TSysUser xbr = userManager.randomUser(users);
													if(StringUtil.isNotBlank(xbr.getId())){
														//����Э����
														commWorkManager.saveRyMul(work.getId(), xbr.getId()+",", TaskUserEnum.XBR.getCode());	
													}
													
												}
											}
											//���������ִ������Ĺ���
											 String lawobjname = tDataLawobjf.getDwmc();//ִ����������
											 String address = tDataLawobjf.getDwdz();//ִ�������ַ
											 String regionId = tDataLawobjf.getSsxzq();//��������
											 String fddbr = tDataLawobjf.getFddbr();//����������
											 String fddbrlxdh = tDataLawobjf.getFddbrdh();//������������ϵ�绰
											 String hbfzr = tDataLawobjf.getHbfzr();//����������
											 String hbfzrlxdh = tDataLawobjf.getHbfzrdh();//������������ϵ�绰
											TBizTaskandlawobj tBizTaskandlawobj = new TBizTaskandlawobj(work.getId(), tDataLawobjf.getLawobjtype().getId(), tDataLawobjf.getId(), lawobjname, regionId,address, fddbr, fddbrlxdh, hbfzr, "", hbfzrlxdh, "", "����������");					
											tBizTaskandlawobj.setUpdateby(admin);
											tBizTaskandlawobj.setCreateby(admin);
										    this.dao.save(tBizTaskandlawobj);
										    
										    //����������������͹���
										    TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
										    tBizTaskandtasktype.setTaskid(work.getId());//����id
										    tBizTaskandtasktype.setTasktypeid(TaskTypeCode.RCJC.getCode());//�������ͣ��ֳ����
										    tBizTaskandtasktype.setIsActive(YnEnum.Y.getCode());//״̬
										    tBizTaskandtasktype.setCreated(new Date());//����ʱ��
										    tBizTaskandtasktype.setCreateby(admin);//������
										    tBizTaskandtasktype.setUpdateby(admin);
										    tBizTaskandtasktype.setUpdated(new Date());
										    this.dao.save(tBizTaskandtasktype);
										    
										    //�ѳ����б�������id�����ɵ��������
										    TBizCheckedLawobj tBizCheckedLawobj = checkedList.get(i);
										    tBizCheckedLawobj.setTask(work);
										    this.dao.save(tBizCheckedLawobj);//���³����б�
										}else{
											return "flase";	
										}
								
							}else{
								return "flase";
							}	
						}
					}else{
						return "flase";
					}
				}
				return "success";
	}
	
	
	/**
	 *  ��ѯ�����ѱ���ѡ��һ����ҵ�б���Ϊ���㼾�ȳ������������������
	 *  
	 *  
	 *  
	 * @throws Exception 
	 */
	public List<TDataLawobjf> queryCheckedLawobjListnew(String areaId,String year) throws Exception{
	

        Map<String, Object> condition = new HashMap<String, Object>();
		
		StringBuffer listSql = new StringBuffer(" select f.*,o.name_ orgmc,r.name_ regionmc,a.name_ typemc from t_data_lawobjf f  ");
		listSql.append(" left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_  ");
		listSql.append(" left join t_data_region  r on r.id_=f.ssxzq_  where 1=1 and f.isactive_='Y'  and ssxzq_ in ( ");
		listSql.append(" SELECT id_ from t_data_region where PID_  = ( SELECT PID_ from t_data_region  where id_ = ( SELECT code_ from t_sys_area  ");
		if(StringUtils.isNotBlank(areaId)){
		listSql.append(" where id_=:AreaId   ");
		condition.put("AreaId", areaId);
		}
		listSql.append(" )) ");
		listSql.append(" and f.id_ not in ( select fid_ from T_data_gywry  where kzlx_ in ('1','2','3')) ");
			
		
		//״̬����Ч
		listSql.append(" and ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//��ҵ״̬:��Ӫ��
		listSql.append(" and qyzt").append(" = :qyzt ");
		condition.put("qyzt", "0");
		
		//��ȡ������
		listSql.append(" and f.id_  in  (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='0' and c.year_ = :year1 and c.source_=1 )");
		condition.put("year1", year);	
		List<TDataLawobjf> lawobjList = new ArrayList<TDataLawobjf>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobjf lawobj = new TDataLawobjf();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobjf) this.get(TDataLawobjf.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 *  ��ѯ�����ѱ���ѡ��һ����ҵ�б���Ϊ���㼾�ȳ������������������
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedLawobjList(String areaId,String year) throws Exception{
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		//���ص�Ĺ�ҵ��ȾԴ
		lawobjList.addAll(this.queryCheckedNoKeyLawobjList(areaId,year));//���ص㹤ҵ��ȾԴ
		lawobjList.addAll(this.queryCheckedAllJsxmList(areaId,year));//������Ŀ
		lawobjList.addAll(this.queryCheckedAllYyList(areaId,year));//ҽԺ
		lawobjList.addAll(this.queryCheckedAllGlList(areaId,year));//��¯
		//lawobjList.addAll(this.queryAllJzgdList(areaId,year));//����������ʱ������������
		lawobjList.addAll(this.queryCheckedAllScList(areaId,year));//����
		lawobjList.addAll(this.queryCheckedAllXqyzList(areaId,year));//������ֳ
		lawobjList.addAll(this.queryCheckedAllFwyList(areaId,year));//����ҵ
		lawobjList.addAll(this.queryCheckedAllYsyList(areaId,year));//��ʳҵ
		lawobjList.addAll(this.queryCheckedAllZzyList(areaId,year));//����ҵ
		lawobjList.addAll(this.queryCheckedAllYlyList(areaId,year));//����ҵ
		return lawobjList;
	}
	
	/**
	 * ���з��ص���ȾԴ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedNoKeyLawobjList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '01' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gywry_ssxzq.getCode(),
				LawobjOutColunmEnum.gywry_kzlx.getCode(),
				LawobjOutColunmEnum.gywry_qyzt.getCode());
		String ssxzq_column = null;
		String kzlx_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=3){
			throw new Exception("��ҵ��ȾԴ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			kzlx_column = String.valueOf(listColumn.get(1));
			qyzt_column = String.valueOf(listColumn.get(2));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append("left join t_sys_dic d on l.").append(kzlx_column).append(" = d.code_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '01' and (d.code_='4') and d.type_='6'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
		String areaid = areaId;
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//�ܶ�
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//ʦ��
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//�ż�
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		if(StringUtil.isNotBlank(year)){
			listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
			condition.put("year1", year);
		}
		
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ���н�����Ŀ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedAllJsxmList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ? ");
		sql.append(" union all");
	    sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '02' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.jsxm_jsjdjsczt.getCode(),
				LawobjOutColunmEnum.jsxm_ssxzq.getCode());
		String ssxzq_column = null;
		String jsjdjsczt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("������Ŀ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(1));
			jsjdjsczt_column = String.valueOf(listColumn.get(0));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '02'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//����״̬������
		listSql.append(" and l.").append(jsjdjsczt_column).append(" != '6' ");
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ����ҽԺ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedAllYyList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '03' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.yy_ssxzq.getCode(),
				LawobjOutColunmEnum.yy_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("ҽԺ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '03'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ���й�¯�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedAllGlList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '04' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.gl_ssxzq.getCode(),
				LawobjOutColunmEnum.gl_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("��¯���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '04'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ���н��������б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedAllJzgdList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '05' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.jzgd_ssxzq.getCode(),
				LawobjOutColunmEnum.jzgd_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("�����������ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '05'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ���������б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedAllScList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '06' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.sc_ssxzq.getCode(),
				LawobjOutColunmEnum.sc_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("�������ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '06'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ����������ֳ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedAllXqyzList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '07' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.xqyz_ssxzq.getCode(),
				LawobjOutColunmEnum.xqyz_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("������ֳ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '07'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ���з���ҵ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedAllFwyList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '08' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.fwy_ssxzq.getCode(),
				LawobjOutColunmEnum.fwy_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("����ҵ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '08'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ������ʳҵ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedAllYsyList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '09' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.ysy_ssxzq.getCode(),
				LawobjOutColunmEnum.ysy_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("��ʳҵ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '09'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	/**
	 * ������������ҵ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedAllZzyList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '10' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.zzy_ssxzq.getCode(),
				LawobjOutColunmEnum.zzy_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("��������ҵ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '10'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	
	/**
	 * ��������ҵ�б�
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedAllYlyList(String areaId,String year) throws Exception{
		StringBuffer sql = new StringBuffer("select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ? ");
		sql.append(" union all");
		sql.append(" select d.colengname_ from t_Data_Lawobjdic d where d.lawobjtypeid_ = '11' and d.enumname_ = ?  ");
		List<Object> listColumn = this.dao.findBySql(sql.toString(), 
				LawobjOutColunmEnum.yly_ssxzq.getCode(),
				LawobjOutColunmEnum.yly_qyzt.getCode());
		String ssxzq_column = null;
		String qyzt_column = null;
		if(listColumn.size()!=2){
			throw new Exception("����ҵ���ֶ�δ�������ã����������ã�");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '11'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//����������Ϊ���������ҵ
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//�ܶ�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//ʦ��
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//�ż�
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//��ҵ״̬:��Ӫ��
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//״̬����Ч
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//��������
		/*listSql.append(" and l.AREAID_").append(" = :areaid ");
		condition.put("areaid", areaid);*/
			
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		List<Object[]> list = this.dao.findBySql(listSql.toString(),condition);
		for(int i =0; i<list.size(); i++){
			TDataLawobj lawobj = new TDataLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TDataLawobj) this.get(TDataLawobj.class, String.valueOf(list.get(i)));
			}
			lawobjList.add(lawobj);
		}
		return lawobjList;
	}
	
	@Override
	public FyWebResult querySjccLawobjList(String name, String lawobjType, String regionId, String orgId, String curPage, String pageSize)
			throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
        sql.append("select l.* from V_ZFDX_ZHXX l ");
        sql.append(" left join t_data_region r on l.REGIONID_ = r.id_ ");
        sql.append(" where l.qyzt_ not in('1','2') ");
       //��λ����
        if (StringUtils.isNotBlank(name)) {
        	sql.append(" and l.dwmc_ like :name ");
        	condition.put("name", "%"+name+"%");
        }
       //ִ����������
        if (StringUtils.isNotBlank(lawobjType)) {
        	sql.append(" and l.lawobjtype_ = :lawobjType ");
        	condition.put("lawobjType", lawobjType);
        }
        //����������
        if (StringUtils.isNotBlank(regionId)) {
        	sql.append(" and l.regionId_ = :regionId ");
        	condition.put("regionId", regionId);
        }
        //������ܲ���
        if (StringUtils.isNotBlank(orgId)) {
        	sql.append(" and  l.orgId_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgId  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgId", orgId);
        }
        String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//ʦ��
			sql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//�ż�
			sql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		//���Ͻ�����Ŀ�����Ͳ�ѯ����
        sql.append(" or (l.lawobjtype_ in('02') and l.qyzt_ not in('6')");
        //��λ����
        if (StringUtils.isNotBlank(name)) {
        	sql.append(" and l.dwmc_ like :name ");
        	condition.put("name", "%"+name+"%");
        }
       //ִ����������
        if (StringUtils.isNotBlank(lawobjType)) {
        	sql.append(" and l.lawobjtype_ = :lawobjType ");
        	condition.put("lawobjType", lawobjType);
        }
        //����������
        if (StringUtils.isNotBlank(regionId)) {
        	sql.append(" and l.regionId_ = :regionId ");
        	condition.put("regionId", regionId);
        }
        //������ܲ���
        if (StringUtils.isNotBlank(orgId)) {
        	sql.append(" and  l.orgId_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgId  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgId", orgId);
        }
		if(area.getType().equals("1")){//ʦ��
			sql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//�ż�
			sql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
        sql.append(" ) order by l.lawobjType_ asc");
        FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(curPage), pageSize==null?0:Integer.parseInt(pageSize), condition);
        FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> row = null;
		for (Object[] obj : listLawobj) {
			row = new HashMap<String, String>();
			row.put("id", String.valueOf(obj[0]));
			TDataLawobjtype tlt=(TDataLawobjtype) this.get(TDataLawobjtype.class, String.valueOf(obj[1]));
			row.put("lawobjType", obj[1]==null?"":tlt.getName());
			row.put("lawobjTypeid", obj[1]==null?"":String.valueOf(obj[1]));
			row.put("name", obj[2]==null?"":String.valueOf(obj[2]));
			row.put("address", obj[3]==null?"":String.valueOf(obj[3]));
			row.put("regionName", obj[5]==null?"":String.valueOf(obj[5]));
			row.put("orgName", obj[7]==null?"":String.valueOf(obj[7]));
			row.put("fddbr", obj[8]==null?"":String.valueOf(obj[8]));
			row.put("fddbrlxdh", obj[9]==null?"":String.valueOf(obj[9]));
			if(!"02".equals(String.valueOf(obj[1]))){
				if("0".equals(String.valueOf(obj[10]))){
					row.put("qyzt", "��Ӫ��");
				}else if("1".equals(String.valueOf(obj[10]))){
					row.put("qyzt", "�ѹر�");
				}else if("2".equals(String.valueOf(obj[10]))){
					row.put("qyzt", "��ͣ��");
				}
			}else{
				row.put("qyzt", obj[10]==null?"":dicManager.getNameByTypeAndCode("8", String.valueOf(obj[10])));
			}
			row.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}
	
	@Override
	public void exportYbLawobjList(String name,String lawobjType,String regionId, String regionName, String orgId,String orgName,HttpServletResponse res) 
			throws Exception{
		//��������
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		//��ͷ����
		ConditionsForm conditionsForm = new ConditionsForm();
		//��λ����
		if (StringUtils.isNotBlank(name)) {
				conditionsForm.setName("������"+name+"������ҵ");
		}else{
			conditionsForm.setName("ȫ��");
		}
		//ִ����������
		if (StringUtils.isNotBlank(lawobjType)) {
			conditionsForm.setLawobjtypename(dicManager.getNameByTypeAndCode("5",lawobjType));
		}else{
			conditionsForm.setLawobjtypename("ȫ��");
		}
		//����������
		if (StringUtils.isNotBlank(regionName)) {
			conditionsForm.setRegionname(regionName);
		}else{
			conditionsForm.setRegionname("ȫ��");
		}
		//������ܲ���
		if (StringUtils.isNotBlank(orgName)) {
			conditionsForm.setOrgname(orgName);
		}else{
			conditionsForm.setOrgname("ȫ��");
		}
		List<Object> listConditions = new ArrayList<Object>();
		listConditions.add(conditionsForm);
		map.put("conditionsForm", listConditions);
		//�б�����
		List list = this.queryLawobjList(name,lawobjType, regionId, orgId);
		map.put("Vzfdx", list);
		String realPath = servletContext.getRealPath(File.separator);
		InputStream is = null;
		OutputStream os = null;
		try {
				File file = ExcelUtil.setValue(
						new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", ""))), 
						new File(realPath + "excel/sjcc/YbLawobj.xls"), 
						new File(realPath + "excel/sjcc/YbLawobj.xml"), 
						map, false);
				ExcelUtil.copyStyle(file, "����������ͳ�Ʊ�", 4, 0, 4 + list.size() - 1, 6);
				is = new FileInputStream(file);
				String name1="����������ͳ��.xls";
				String de = "attachment;filename=".concat(new String(name1.getBytes("GB2312"), "ISO-8859-1"));
				res.setHeader("Content-Disposition", de);
				res.setContentType("application/x-msdownload");
				os = res.getOutputStream();
				byte[] b = new byte[1024];
				int length;
				while ((length = is.read(b)) > 0) {
					os.write(b, 0, length);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(is!=null)
						is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			try {
					if( os != null )
						os.flush();
						os.close();
				} catch (IOException e) {
						e.printStackTrace();
				}
		}
	}
	
	public List<Vzfdx> queryLawobjList(String name, String lawobjType, String regionId, String orgId)
			throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
        sql.append("select l.* from V_ZFDX_ZHXX l where (l.lawobjtype_ not in('02','05') and l.qyzt_ not in('1','2'))");
        
       //��λ����
        if (StringUtils.isNotBlank(name)) {
        	sql.append(" and l.dwmc_ like :name ");
        	condition.put("name", "%"+name+"%");
        }
       //ִ����������
        if (StringUtils.isNotBlank(lawobjType)) {
        	sql.append(" and l.lawobjtype_ = :lawobjType ");
        	condition.put("lawobjType", lawobjType);
        }
        //����������
        if (StringUtils.isNotBlank(regionId)) {
        	sql.append(" and l.regionId_ = :regionId ");
        	condition.put("regionId", regionId);
        }
        //������ܲ���
        if (StringUtils.isNotBlank(orgId)) {
			sql.append(" and  l.orgId_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgId  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgId", orgId);
        }
        sql.append(" or (l.lawobjtype_ in('02') and l.qyzt_ not in('6')) order by l.lawobjType_ asc");
        List<Object[]> list = this.dao.findBySql(sql.toString(),condition);
		List<Vzfdx> zfdxList = new ArrayList();
		for (Object[] obj : list) {
			Vzfdx zfdx = new Vzfdx();
			zfdx.setLawobjType(obj[1]==null?"":dicManager.getNameByTypeAndCode("5", String.valueOf(obj[1])));
			zfdx.setDwmc(obj[2]==null?"":String.valueOf(obj[2]));
			zfdx.setAddress(obj[3]==null?"":String.valueOf(obj[3]));
			zfdx.setRegionName(obj[5]==null?"":String.valueOf(obj[5]));
			zfdx.setOrgName(obj[7]==null?"":String.valueOf(obj[7]));
			zfdx.setFddbr(obj[8]==null?"":String.valueOf(obj[8]));
			zfdx.setFddbrlxdh(obj[9]==null?"":String.valueOf(obj[9]));
			if(!"02".equals(String.valueOf(obj[1]))){
				if("0".equals(String.valueOf(obj[10]))){
					zfdx.setQyzt("��Ӫ��");
				}else if("1".equals(String.valueOf(obj[10]))){
					zfdx.setQyzt("�ѹر�");
				}else if("2".equals(String.valueOf(obj[10]))){
					zfdx.setQyzt("��ͣ��");
				}
			}else{
				zfdx.setQyzt(obj[10]==null?"":dicManager.getNameByTypeAndCode("8", String.valueOf(obj[10])));
			}
			zfdxList.add(zfdx);
		}
		return zfdxList;
	}

	@Override
	public ConfigPfForm queryConfigPfFormByType(String year,String month,String type, String areaid)
			throws Exception {
		ConfigPfForm con = new ConfigPfForm();
		if(StringUtil.isBlank(areaid)){
			areaid=CtxUtil.getAreaId();
		}
		if (StringUtil.isNotBlank(year)&&StringUtil.isNotBlank(type)&&StringUtil.isNotBlank(month)) {
			List<TBizConfigpf> tpf = this.dao
					.find("from TBizConfigpf t where t.isActive='Y' and t.month=? and t.type=? and t.areaId=? and t.year=? ",month,type,areaid, year);
			
			//List<TBizConfigpf> tpf = this.dao.findBySql("select * from t_biz_configpf t where t.isactive_='Y' and t.month_ is null and t.year_=? and t.quarter_=? and t.type_=? and t.areaid_=? ", year,quarter, type, areaid);
			List<ConfigPfSjForm> consjList = new ArrayList<ConfigPfSjForm>();
			if (tpf.size() > 0) {
				List<TBizConfigpfsj> tsj = this.dao
						.find("from TBizConfigpfsj t where t.isActive='Y' and t.areaId=? and t.tBizConfigpf.id=? order by pfsj asc",
								areaid, tpf.get(0).getId());
				ConfigPfSjForm conpfsj = null;

				for (TBizConfigpfsj ts : tsj) {
					conpfsj = new ConfigPfSjForm();
					// conpfsj.setAreaId(ts.getAreaId());
					// conpfsj.setCreater(ts.getCreater());
					// conpfsj.setCreateTime(ts.getCreateTime());
					conpfsj.setId(ts.getId());
					conpfsj.setIsActive(ts.getIsActive());
					conpfsj.setPfbl(String.valueOf(ts.getPfbl()));
					String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
							.format(ts.getPfsj());
					conpfsj.setPfsj(dateStr);
					conpfsj.setSfypf(ts.getSfypf().equals("N") ? "��" : "��");
					conpfsj.setBjsx(String.valueOf(tpf.get(0).getBjsx()));
					// conpfsj.settBizConfigpf(ts.gettBizConfigpf());
					// conpfsj.setUpdateby(ts.getUpdateby());
					// conpfsj.setUpdateTime(ts.getUpdateTime());
					consjList.add(conpfsj);
				}
				con.setAreaId(tpf.get(0).getAreaId());
				con.setBjsx(String.valueOf(tpf.get(0).getBjsx()));
				con.setCreater(tpf.get(0).getCreater());
				con.setCreateTime(tpf.get(0).getCreateTime());
				// con.setData(data);
				con.setType(tpf.get(0).getType());
				con.setId(tpf.get(0).getId());
				con.setIsActive(tpf.get(0).getIsActive());
				con.setMonth(tpf.get(0).getMonth());
				con.setYear(year);
				con.setQuarter("");
				con.setUpdateby(tpf.get(0).getUpdateby());
				con.setUpdateTime(tpf.get(0).getUpdateTime());
			} else {
				con.setYear(year);
				con.setQuarter("");
				con.setMonth(month);
				con.setType("1");
			}
			con.setList(consjList);
		} else {
			ConfigPfSjForm conpfsj = new ConfigPfSjForm();
			List<ConfigPfSjForm> consjList = new ArrayList<ConfigPfSjForm>();
			consjList.add(conpfsj);
			con.setList(consjList);
		}
		return con;
	}

	@Override
	public void specliallConfigPfSave(ConfigPfForm configPfForm, String areaid)
			throws Exception {
		//�ȱ���TBizConfigpf ���ҷ���idֵ
				TSysUser user=CtxUtil.getCurUser();
				ConfigPfForm findCon=null;
				//������ҵ���Ȳ�ѯ
				findCon= this.specliallQueryConfigPfForm(configPfForm.getYear(), configPfForm.getQuarter(),configPfForm.getMonth(),configPfForm.getType(), areaid);
				

				if(StringUtil.isNotBlank(findCon.getId())){
					TBizConfigpf tf=(TBizConfigpf)this.get(TBizConfigpf.class, findCon.getId());
					tf.setBjsx(Integer.valueOf(configPfForm.getBjsx()));
					tf.setMonth(configPfForm.getMonth());
					tf.setType(configPfForm.getType());
					tf.setQuarter(configPfForm.getQuarter());
					tf.setUpdateby(user);
					tf.setUpdateTime(new Date());
					tf.setYear(configPfForm.getYear());
					TBizConfigpf tfId=(TBizConfigpf) this.dao.save(tf);
					//����TBizConfigpfsj
					JSONObject obj = new JSONObject(configPfForm.getData());
					JSONArray arr = obj.getJSONArray("rows");
					List<String> ids=new ArrayList<String>();
					for(int i=0;i<arr.length();i++){
						obj=arr.getJSONObject(i);
						String id=obj.getString("id");
						TBizConfigpfsj tfs=new TBizConfigpfsj();
						if(StringUtil.isNotBlank(id)){
							tfs=(TBizConfigpfsj)this.get(TBizConfigpfsj.class, id);
						}else{
							tfs.setCreater(user);
							tfs.setCreateTime(new Date());
							tfs.setIsActive("Y");
						}
						tfs.setAreaId(areaid);
						tfs.setPfbl(obj.getInt("pfbl"));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
						Date date = sdf.parse(obj.getString("pfsj")); 
						tfs.setPfsj(date);
						if("Y".equals(tfs.getSfypf())){
							tfs.setSfypf("Y");
						}else{
							tfs.setSfypf("N");
						}
						tfs.settBizConfigpf(tfId);
						tfs.setUpdateby(user);
						tfs.setUpdateTime(new Date());
						TBizConfigpfsj saveid=(TBizConfigpfsj)this.dao.save(tfs);
						ids.add(saveid.getId());
					}
					
					List<String> list=this.dao.find("select t.id from TBizConfigpfsj t where t.isActive='Y' and t.areaId=? and t.tBizConfigpf.id=?" ,user.getAreaId(),findCon.getId() );
					for (String ele : list) {
						// ����������еļ�¼û����֮ǰ����ļ�¼�� ˵���˲����ѱ�ɾ��
						if (!ids.contains(ele)) {
							// ɾ��ҳ���б�ɾ���Ĳ���
							this.dao.remove(TBizConfigpfsj.class, ele);
						}
					}
				}else{
					TBizConfigpf tf=new TBizConfigpf();
					tf.setAreaId(areaid);
					tf.setBjsx(Integer.valueOf(configPfForm.getBjsx()));
					tf.setCreateTime(new Date());
					tf.setIsActive("Y");
					tf.setMonth(configPfForm.getMonth());
					tf.setType(configPfForm.getType());
					tf.setQuarter(configPfForm.getQuarter());
					tf.setUpdateby(user);
					tf.setUpdateTime(new Date());
					tf.setYear(configPfForm.getYear());
					TBizConfigpf tfId=(TBizConfigpf) this.dao.save(tf);
					//����TBizConfigpfsj
					JSONObject obj = new JSONObject(configPfForm.getData());
					JSONArray arr = obj.getJSONArray("rows");
					for(int i=0;i<arr.length();i++){
						obj=arr.getJSONObject(i);
						String id=obj.getString("id");
						TBizConfigpfsj tfs=new TBizConfigpfsj();
						if(StringUtil.isNotBlank(id)){
							tfs=(TBizConfigpfsj)this.get(TBizConfigpfsj.class, id);
						}else{
							tfs.setCreater(user);
							tfs.setCreateTime(new Date());
							tfs.setIsActive("Y");
						}
						tfs.setAreaId(areaid);
						tfs.setPfbl(obj.getInt("pfbl"));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
						Date date = sdf.parse(obj.getString("pfsj")); 
						tfs.setPfsj(date);
						tfs.setSfypf("N");
						tfs.settBizConfigpf(tfId);
						tfs.setUpdateby(user);
						tfs.setUpdateTime(new Date());
						this.dao.save(tfs);
					}
				}
		
	}
}