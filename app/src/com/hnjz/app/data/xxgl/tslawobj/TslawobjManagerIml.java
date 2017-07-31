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
				lawobj = (TDataLawobjf)this.get(TDataLawobjf.class, idList[i]);//执法对象
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
		//执法对象名称
		if (StringUtil.isNotBlank(lawobjname)) {
			sql.append(" and lawobjName like :lawobjname ");
			data.putLike("lawobjname", lawobjname);
		}
		//年份
		if (StringUtil.isNotBlank(year)) {
			sql.append(" and year = :year ");
			data.put("year", year);
		}
		//季度
		if(StringUtil.isNotBlank(quarter)){
			sql.append(" and quarter = :quarter");
			data.put("quarter", quarter);
		}
		
		//执法对象类型
		if (StringUtil.isNotBlank(lawobjtype)) {
			sql.append(" and lawobjType = :lawobjtype ");
			data.put("lawobjtype", lawobjtype);
		}
		
		//所属区域
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
			row.put("year", ele.getYear()+"年");
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
			//一般企业比例
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
			//重点企业比例
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
			
			//一般企业比例
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
			//重点企业比例
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
			//一般企业比例
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
			//重点企业比例
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
					conpfsj.setSfypf(ts.getSfypf().equals("N") ? "否" : "是");
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
		//先保存TBizConfigpf 并且返回id值
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
			//保存TBizConfigpfsj
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
				// 如果操作表中的记录没有在之前保存的记录中 说明此操作已被删除
				if (!ids.contains(ele)) {
					// 删除页面中被删除的操作
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
			//保存TBizConfigpfsj
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
      * 获取本年度未被抽取的污染源信息
      * @author gaozhiyang
      * @time 2017-7-6
      * @year 抽取年份
      * @quarter 季度信息
      * @type
      * @areaid 区域ID
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
		//企业状态:运营中
		listSql.append(" and ").append(" f.qyzt_ = :qyzt ");
		condition.put("qyzt", "0");
		listSql.append(" and  f.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='0' and c.year_ = :year1 and c.source_=1 )");
		condition.put("year1", year);
		//本区域管辖的企业
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
			throw new Exception("工业污染源有字段未进行配置，请重新配置！");
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
		//所属行政区为本区域的企业
		//String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//总队
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='0' and c.year_ = :year1 and c.source_=0)");
		condition.put("year1", year);
		//本区域管辖的企业
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
			throw new Exception("工业污染源有字段未进行配置，请重新配置！");
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
		//所属行政区为本区域的企业
		//String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//总队
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		
		//所属区域
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
		//查询t_biz_configcheck表，得到企业设置的抽选比例
		//TBizConfigcheck configCheck=this.queryTbizConfigCheck(areaid);
		TBizHistoryconfigcheck thcc=this.queryTBizHistoryconfigcheck(year, quarter, areaid);

		try {
			/**
			 * 抽选重点污染源，按照设置的季度比例进行抽选，从所有抽查对象中的重点企业抽取
			 */
			List<TDataLawobj> keyLawobjList = new ArrayList<TDataLawobj>();//声明重点企业列表
			//List<TDataLawobj> normalLawobjList = new ArrayList<TDataLawobj>();//声明一般企业列表
			//List<TDataLawobj> specialLawobjList = new ArrayList<TDataLawobj>();//声明特殊企业列表
			keyLawobjList = this.queryAllKeyLawobjListByAreaId(areaid);//所有重点污染源
			
			double proportion = 0;//季度抽查比例
			double ybbl = 0;//年度抽查比例
			if(quarter.equals("1")){
			proportion=	thcc.getZdqybl();//第一季度重点抽查比例
			ybbl = thcc.getYbqybl();     //第一季度一般企业抽查比例
			}else if(quarter.equals("2")){
				proportion=thcc.getZdqyblsecond();//第二季度重点抽查比例
				ybbl = thcc.getYbqyblsecond();//第二季度一般企业抽查比例
			}else if(quarter.equals("3")){
				proportion=thcc.getZdqyblthird();//第三季度重点抽查比例
				ybbl = thcc.getYbqyblthird();//第三季度一般企业抽查比例
			}else if(quarter.equals("4")){
				proportion=thcc.getZdqyblfour();//第四季度重点抽查比例
				ybbl = thcc.getYbqyblfour();//第四季度一般企业抽查比例
			}
			if(keyLawobjList!=null && keyLawobjList.size()>0 && proportion!=0){
				int total = keyLawobjList.size();//重点企业总数
				//float d =(float) proportion/100;//抽查比例
				//要抽取的数量
				int totalNum = 0;
				float total1=(float)(total*proportion)/100;
				int total2=(int)((total*proportion)/100);
				
				if(total1==total2){//总数的25%为整数取整
					totalNum = total2;
				}else{
					totalNum = total2+1;				//总数的25%非整数取整加1
				}
				int firstNum = 0;//第一次抽查数
				int secondNum = 0;//第二次抽查数
				if(total*0.25==(int)(total*0.25)){//总数的25%为整数取整
					firstNum = (int)(total*0.25);
				}else{
					firstNum = (int)(total*0.25)+1;				//总数的25%非整数取整加1
				}
				secondNum = totalNum-firstNum;
				//本年度常规抽选时没有被抽查的污染源
				List<TDataLawobj> noCheckedList = this.queryNoCheckedKeyLawobj(year, quarter, "0", areaid);
				
				//第一次随机抽查，抽查对象：本年度没有被抽到的污染源 
				List<Integer> arr = new ArrayList<Integer>();//被抽中的数值
				List<TDataLawobj> firstCheckedList = new ArrayList<TDataLawobj>();//声明第一次抽中列表
				List<TDataLawobj> secondCheckedList = new ArrayList<TDataLawobj>();//声明第二次抽中列表
				
					if(noCheckedList!=null && noCheckedList.size()>0){
						if(firstNum>noCheckedList.size()){
							firstNum = noCheckedList.size();
							secondNum = totalNum- noCheckedList.size();
						}
						for(int i=0;i<firstNum;i++){
							int k = (int) ((Math.random())*(noCheckedList.size()));
							if(!arr.contains(k)){//k不在数组中，表示还未被抽取过
								arr.add(k);
								firstCheckedList.add(noCheckedList.get(k));
							}else{
								i--;//重新抽取
							}
					    }
				     }
				//把第一次抽查到的重点污染源插入到被抽查污染源表中
					//还没有重构此方法
				//spotCheckManager.saveCheckedList(year,quarter,"0",firstCheckedList,areaid);
				
				
				
				//第二次抽查对象（本次没有被抽中的污染源）
				List<TDataLawobj> thisTimeNocheckedList = new ArrayList<TDataLawobj>();
				//得到本次没有被抽查到的污染源列表
				for(int i=0;i<keyLawobjList.size();i++){
					int flag=0;
					for(int j=0;j<firstCheckedList.size();j++){
						TDataLawobj lawobj = keyLawobjList.get(i);
						TDataLawobj lawobj1 = firstCheckedList.get(j);
						if(lawobj!=null && lawobj1!=null && lawobj.getId().equals(lawobj1.getId())){//本次已被抽中
							flag=1;
							break;
						}
					}
					if(flag==0){//本次未被抽中的加入列表
						thisTimeNocheckedList.add(keyLawobjList.get(i));
					}else{
						flag=0;
					}
				}
								
				//第二次随机抽查，抽查对象：本次没有被抽到的污染源
				List<Integer> arr1 = new ArrayList<Integer>();//被抽中的数值
				for(int i=0;i<secondNum;i++){
					int k = (int) ((Math.random())*(thisTimeNocheckedList.size()));
					if(!arr1.contains(k)){//k不在数组中，表示还未被抽取过
						arr1.add(k);
						secondCheckedList.add(thisTimeNocheckedList.get(k));
					}else{
						i--;//重新抽取
					}
				}
				//把抽查到的污染源插入到被抽查污染源表中
				//spotCheckManager.saveCheckedList(year,quarter,"1",secondCheckedList,areaid);
			}	
				/**
				 * 抽选一般企业，从所有一般企业中抽选
				 */
				List<TDataLawobj> allNormalLawobjList = this.queryAllNormalList(areaid,year);
				List<TDataLawobj> CheckedNormalList = this.queryCheckedLawobjList(areaid,year);//把已经抽取过的企业获取出来，留作季度抽查数量备用
				
				
				//查询用户表得到在编在岗人员数量
				List<TSysUser> users=this.dao.find("from TSysUser t where t.isActive='Y' and t.isZaiBian='Y' and t.areaId=?", areaid);
				//人员数量
				int usersl=users.size();
				if(allNormalLawobjList!=null && allNormalLawobjList.size()>0){
					int number = allNormalLawobjList.size();
					List<TDataLawobj> normalCheckedList = new ArrayList<TDataLawobj>();//声明一般企业抽中列表
					int ybtotal=(int)(usersl*ybbl);//本季度抽取企业数
					//季度抽查数量
					int checkedNum=0;
					if(usersl*ybbl==ybtotal){//总数为整数取整
						checkedNum = ybtotal;
					}else{
						checkedNum = ybtotal+1;//总数的非整数取整加1
					}
					List<Integer> arr1 = new ArrayList<Integer>();//被抽中的数值
					if(number > ybtotal){
						for(int i=0;i<checkedNum && i<allNormalLawobjList.size();i++){
							int k = (int) ((Math.random())*(allNormalLawobjList.size()));
							if(!arr1.contains(k)){//k不在数组中，表示还未被抽取过
								arr1.add(k);
								normalCheckedList.add(allNormalLawobjList.get(k));
							}else{
									i--;//重新抽取
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
					List<TDataLawobj> normalCheckedList = new ArrayList<TDataLawobj>();//声明一般企业抽中列表
					int ybtotal=(int)(usersl*ybbl);//本季度抽取企业数
					//季度抽查数量
					int checkedNum=0;
					if(usersl*ybbl==ybtotal){//总数的25%为整数取整
						checkedNum = ybtotal;
					}else{
						checkedNum = ybtotal+1;//总数的25%非整数取整加1
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
				 * 抽选特殊企业，从特殊企业中抽选
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
				return "抽选成功";
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "success";
	}

	
	/** 根据随机设置
	 * 重构方法
	 * @author gaozhiyang
	 * @time 2017-7-6
	 * @year 年份
	 * @quarter 季度
	 * @areaid 区域ID 
	 * @ele TDataQuarterChecktimeSet
	 */
	@Override
	public String saveStartQuarterChecknew(String year, String quarter, String areaid,TDataQuarterChecktimeSet ele)
			throws Exception {
		//查询t_biz_configcheck表，得到企业设置的抽选比例
		//TBizConfigcheck configCheck=this.queryTbizConfigCheck(areaid);
		TBizHistoryconfigcheck thcc=this.queryTBizHistoryconfigcheck(year, quarter, areaid);

		try {
			/**
			 * 抽选重点污染源，按照设置的季度比例进行抽选，从所有抽查对象中的重点企业抽取
			 */
			List<TDataLawobjf> keyLawobjList = new ArrayList<TDataLawobjf>();//声明重点企业列表
			//List<TDataLawobj> normalLawobjList = new ArrayList<TDataLawobj>();//声明一般企业列表
			//List<TDataLawobj> specialLawobjList = new ArrayList<TDataLawobj>();//声明特殊企业列表
			keyLawobjList = this.queryAllKeyLawobjListByAreaIdnew(areaid);//所有重点污染源
			
			double proportion = 0;//季度抽查比例
			double ybbl = 0;//年度抽查比例
			if(quarter.equals("1")){
			proportion=	thcc.getZdqybl();//第一季度重点抽查比例
			ybbl = thcc.getYbqybl();     //第一季度一般企业抽查比例
			}else if(quarter.equals("2")){
				proportion=thcc.getZdqyblsecond();//第二季度重点抽查比例
				ybbl = thcc.getYbqyblsecond();//第二季度一般企业抽查比例
			}else if(quarter.equals("3")){
				proportion=thcc.getZdqyblthird();//第三季度重点抽查比例
				ybbl = thcc.getYbqyblthird();//第三季度一般企业抽查比例
			}else if(quarter.equals("4")){
				proportion=thcc.getZdqyblfour();//第四季度重点抽查比例
				ybbl = thcc.getYbqyblfour();//第四季度一般企业抽查比例
			}
			if(keyLawobjList!=null && keyLawobjList.size()>0 && proportion!=0){
				int total = keyLawobjList.size();//重点企业总数
				//float d =(float) proportion/100;//抽查比例
				//要抽取的数量
				int totalNum = 0;
				float total1=(float)(total*proportion)/100;
				int total2=(int)((total*proportion)/100);
				
				if(total1==total2){//总数的25%为整数取整
					totalNum = total2;
				}else{
					totalNum = total2+1;				//总数的25%非整数取整加1
				}
				int firstNum = 0;//第一次抽查数
				int secondNum = 0;//第二次抽查数
				if(total*0.25==(int)(total*0.25)){//总数的25%为整数取整
					firstNum = (int)(total*0.25);
				}else{
					firstNum = (int)(total*0.25)+1;				//总数的25%非整数取整加1
				}
				secondNum = totalNum-firstNum;
				//本年度常规抽选时没有被抽查的重点污染源
				List<TDataLawobjf> noCheckedList = this.queryNoCheckedKeyLawobjnew(year, quarter, "0", areaid);
				
				//第一次随机抽查，抽查对象：本年度没有被抽到的重点污染源 
				List<Integer> arr = new ArrayList<Integer>();//被抽中的数值
				List<TDataLawobjf> firstCheckedList = new ArrayList<TDataLawobjf>();//声明第一次抽中列表
				List<TDataLawobjf> secondCheckedList = new ArrayList<TDataLawobjf>();//声明第二次抽中列表
				
					if(noCheckedList!=null && noCheckedList.size()>0){
						if(firstNum>noCheckedList.size()){
							firstNum = noCheckedList.size();
							secondNum = totalNum- noCheckedList.size();
						}
						for(int i=0;i<firstNum;i++){
							int k = (int) ((Math.random())*(noCheckedList.size()));
							if(!arr.contains(k)){//k不在数组中，表示还未被抽取过
								arr.add(k);
								firstCheckedList.add(noCheckedList.get(k));
							}else{
								i--;//重新抽取
							}
					    }
				     }
				//把第一次抽查到的重点污染源插入到被抽查污染源表中
				spotCheckManager.saveCheckedListnew(year,quarter,"0",firstCheckedList,areaid);
				
				
				
				//第二次抽查对象（本次没有被抽中的污染源）
				List<TDataLawobjf> thisTimeNocheckedList = new ArrayList<TDataLawobjf>();
				//得到本次没有被抽查到的污染源列表
				for(int i=0;i<keyLawobjList.size();i++){
					int flag=0;
					for(int j=0;j<firstCheckedList.size();j++){
						TDataLawobjf lawobj = keyLawobjList.get(i);
						TDataLawobjf lawobj1 = firstCheckedList.get(j);
						if(lawobj!=null && lawobj1!=null && lawobj.getId().equals(lawobj1.getId())){//本次已被抽中
							flag=1;
							break;
						}
					}
					if(flag==0){//本次未被抽中的加入列表
						TDataLawobjf f= keyLawobjList.get(i);
						thisTimeNocheckedList.add(f);
					}else{
						flag=0;
					}
				}
								
				//第二次随机抽查，抽查对象：本次没有被抽到的重点污染源
				List<Integer> arr1 = new ArrayList<Integer>();//被抽中的数值
				for(int i=0;i<secondNum;i++){
					int k = (int) ((Math.random())*(thisTimeNocheckedList.size()));
					if(!arr1.contains(k)){//k不在数组中，表示还未被抽取过
						arr1.add(k);
						secondCheckedList.add(thisTimeNocheckedList.get(k));
					}else{
						i--;//重新抽取
					}
				}
				//把抽查到的污染源插入到被抽查污染源表中
				spotCheckManager.saveCheckedListnew(year,quarter,"1",secondCheckedList,areaid);
			}	
				/**
				 * 抽选一般企业，从所有一般企业中抽选
				 */
				List<TDataLawobjf> allNormalLawobjList = this.queryAllNormalListnew(areaid,year);
				List<TDataLawobjf> CheckedNormalList = this.queryCheckedLawobjListnew(areaid,year);//把已经抽取过的企业获取出来，留作季度抽查数量备用
				
				
				//查询用户表得到在编在岗人员数量
				List<TSysUser> users=this.dao.find("from TSysUser t where t.isActive='Y' and t.isZaiBian='Y' and t.areaId=?", areaid);
				//人员数量
				int usersl=users.size();
				if(allNormalLawobjList!=null && allNormalLawobjList.size()>0){
					int number = allNormalLawobjList.size();
					List<TDataLawobjf> normalCheckedList = new ArrayList<TDataLawobjf>();//声明一般企业抽中列表
					int ybtotal=(int)(usersl*ybbl);//本季度抽取企业数
					//季度抽查数量
					int checkedNum=0;
					if(usersl*ybbl==ybtotal){//总数为整数取整
						checkedNum = ybtotal;
					}else{
						checkedNum = ybtotal+1;//总数的非整数取整加1
					}
					List<Integer> arr1 = new ArrayList<Integer>();//被抽中的数值
					if(number > ybtotal){
						for(int i=0;i<checkedNum && i<allNormalLawobjList.size();i++){
							int k = (int) ((Math.random())*(allNormalLawobjList.size()));
							if(!arr1.contains(k)){//k不在数组中，表示还未被抽取过
								arr1.add(k);
								normalCheckedList.add(allNormalLawobjList.get(k));
							}else{
									i--;//重新抽取
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
					List<TDataLawobjf> normalCheckedList = new ArrayList<TDataLawobjf>();//声明一般企业抽中列表
					int ybtotal=(int)(usersl*ybbl);//本季度抽取企业数
					//季度抽查数量
					int checkedNum=0;
					if(usersl*ybbl==ybtotal){//总数的25%为整数取整
						checkedNum = ybtotal;
					}else{
						checkedNum = ybtotal+1;//总数的25%非整数取整加1
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
				 * 抽选特殊企业，从特殊企业中抽选
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
				return "抽选成功";
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
		//根据派发时间设置表查找派发表
		String jieguo="";
		TBizConfigpf tpf=(TBizConfigpf) this.get(TBizConfigpf.class, tsBizConfigpfsj.gettBizConfigpf().getId());
		String year=tpf.getYear();
		String quarter=tpf.getQuarter();
		String month=tpf.getMonth();
		String areaid=tpf.getAreaId();
		String type=tpf.getType();//1非特殊企业，2特殊企业
		TBizHistoryconfigcheck thcc=this.queryTBizHistoryconfigcheck(year, month, areaid);
		if(StringUtil.isNotBlank(thcc.getGzsz())){
		if(thcc.getGzsz().equals("1")){//随机抽选主办人
			jieguo=this.saveCreateWorkToUser(year, quarter,month, areaid, tsBizConfigpfsj,type);
		}else if(thcc.getGzsz().equals("2")){//随机抽选主办人协办人
			jieguo=this.saveCreateWorkToUser(year, quarter, month,areaid, tsBizConfigpfsj,type);
		}else if(thcc.getGzsz().equals("3")){//随机任务派发到企业所属监管部门领导 
			jieguo=this.saveCreateWorkToLeader(year, quarter,month,tpf.getAreaId(), tsBizConfigpfsj,type);//生成任务
		}else if(thcc.getGzsz().equals("4")){//随机派发到企业所属部门人员(主办人)
			jieguo=this.saveCreateWorkToOrgUser(year, quarter,month,areaid, tsBizConfigpfsj, type);
		}else if(thcc.getGzsz().equals("5")){//随机派发到企业所属部门人员(主办人，协办人) 
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
	 * 获取所有重点污染源列表 重构方法
	 * @areaId 区域ID
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
			
		
		
		//企业状态:运营中
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
	 * 所有重点污染源列表
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
			throw new Exception("工业污染源有字段未进行配置，请重新配置！");
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
		//所属行政区为本区域的企业
		//String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaId);
		if(area.getType().equals("0")){//总队
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//师级
			listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			listSql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//本区域管辖的企业
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
	 * 所有非重点污染源列表
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
			throw new Exception("工业污染源有字段未进行配置，请重新配置！");
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
		//所属行政区为本区域的企业
		//String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//总队
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
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
		//选出月所有的企业
		List<TBizCheckedLawobj> allList=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type in (1,2) and t.year=? and t.month=? and t.areaId=?",year,month,areaid);
		//要派发的企业
		List<TBizCheckedLawobj> pfLawobj=new ArrayList<TBizCheckedLawobj>();
		//查询出企业派发规则
		TBizHistoryconfigcheck thcc=this.queryTBizHistoryconfigcheck(year, month, areaid);
		//得到本次要派发的比例
		int intPfbl=tBizConfigpfsj.getPfbl();
		float pfbl=(float)intPfbl/100;
		//得到本次要派发的数量
		int pfNumber=(int)((allList.size() * intPfbl)/100);
		int zsNumber=0;
		float pf2=allList.size() * intPfbl;
		float pf1=pf2/100;
		if(pfNumber==pf1){//季度抽取企业数为整数,取整数
			zsNumber=pfNumber;
		}else{
			zsNumber=pfNumber+1;
		}
		if(StringUtil.isNotBlank(thcc.getId())){
			// 选出没有被派发过的企业
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
		
		// 根据年份季度查询被抽中的企业列表
				List<TBizCheckedLawobj> checkedList=new ArrayList<TBizCheckedLawobj>();
				if(type.equals("1")){
					 checkedList = this.executePfLawobj(year, month,areaid, tBizConfigpfsj);
				}else if(type.equals("2")){
					 checkedList=this.querySpecialLawobjByPfbl(year, quarter, month,areaid,tBizConfigpfsj);
				}
		
				TSysUser admin = userManager.getUser("a0000000000000000000000000000000");
				if(checkedList!=null && checkedList.size()>0){
					if(checkedList.get(0).getTask()==null){
						//将抽查到的企业生成随机任务派发给各企业的监管部门领导
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
										work.setCreateUser(user);//创建人：部门领导
										work.setDjrId(user.getId());//登记人id
										work.setDjrName(user.getName());//登记人name
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							TDataLawobjf tDataLawobjf = (TDataLawobjf)dao.get(TDataLawobjf.class,checkedList.get(i).getLawobjf().getId());
							if(StringUtil.isNotBlank(tDataLawobjf.getId())){
							work.setName("随机任务");	//任务名称
							work.setWorkNote("随机任务"); //任务内容
							work.setSource("11");//任务来源：随机抽查
							work.setCreateTime(new Date());//创建时间
							work.setEmergency("1");//紧急程度:一般
							work.setSecurity("3");//任务密级:机密
							work.setAreaid(doubleRandomManager.queryAreaIdByLawobjfId(tDataLawobjf.getId()));//随机任务加区域
							Calendar endC = Calendar.getInstance();
							//获取办结时限
							//TBizConfigpf tpf=(TBizConfigpf)this.get(TBizConfigpf,tBizConfigpfsj.gettBizConfigpf().getId());
			                endC.add(Calendar.DATE, tBizConfigpfsj.gettBizConfigpf().getBjsx()-1);//默认紧急程度一般限期时间
			                SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
			                String endTime= s.format(endC.getTime());
			               // Date date =s.parse(endTime);  
			                Date date = DateUtil.getEndDatetime(endTime);
			                work.setEndTime(date);
							work.setIsActive(YnEnum.Y.getCode());//状态
							work.setZfdxType(tDataLawobjf.getLawobjtype().getId());
							work.setRwmcgs("1");
						    //保存WORK对象
							work = (Work) workDao.save(work);
							TBizCheckedLawobj tobj=(TBizCheckedLawobj)this.get(TBizCheckedLawobj.class, checkedList.get(i).getId());
							tobj.setSfypf("Y");
							this.dao.save(tobj);
						    //保存任务和执法对象的关联
							String lawobjname = tDataLawobjf.getDwmc();//执法对象名称
						    String address = tDataLawobjf.getDwdz();//执法对象地址
						    String regionId = tDataLawobjf.getSsxzq();//所属区域
						    String fddbr = tDataLawobjf.getFddbr();//法定代表人
						    String fddbrlxdh = tDataLawobjf.getFddbrdh();//法定代表人联系电话
						    String hbfzr = tDataLawobjf.getHbfzr();//环保负责人
						    String hbfzrlxdh = tDataLawobjf.getHbfzrdh();//环保负责人联系电话
							TBizTaskandlawobj tBizTaskandlawobj = new TBizTaskandlawobj(
									work.getId(), tDataLawobjf.getLawobjtype().getId(), tDataLawobjf.getId(), lawobjname, regionId,
									address, fddbr, fddbrlxdh, hbfzr, "", hbfzrlxdh, "", "法定代表人");
							tBizTaskandlawobj.setUpdateby(admin);
							tBizTaskandlawobj.setCreateby(admin);
						    this.dao.save(tBizTaskandlawobj);
						    
						    //保存任务和任务类型关联
						    TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
						    tBizTaskandtasktype.setTaskid(work.getId());//任务id
						    tBizTaskandtasktype.setTasktypeid(TaskTypeCode.RCJC.getCode());//任务类型：现场监察
						    tBizTaskandtasktype.setIsActive(YnEnum.Y.getCode());//状态
						    tBizTaskandtasktype.setCreated(new Date());//创建时间
						    tBizTaskandtasktype.setCreateby(admin);//创建人
						    tBizTaskandtasktype.setUpdateby(admin);
						    tBizTaskandtasktype.setUpdated(new Date());
						    this.dao.save(tBizTaskandtasktype);
						    
						    //把抽中列表中任务id与生成的任务关联
						    TBizCheckedLawobj tBizCheckedLawobj = checkedList.get(i);
						    tBizCheckedLawobj.setTask(work);
						    this.dao.save(tBizCheckedLawobj);//更新抽中列表
							
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
		// 根据年份季度查询被抽中的企业列表
		List<TBizCheckedLawobj> checkedList=new ArrayList<TBizCheckedLawobj>();
		if(type.equals("1")){//非特殊企业
			 checkedList = this.executePfLawobj(year, month,areaid, tBizConfigpfsj);
		}else if(type.equals("2")){//特殊企业
			 checkedList=this.querySpecialLawobjByPfbl(year, quarter, month,areaid,tBizConfigpfsj);
		}
		
		TSysUser admin = userManager.getUser("a0000000000000000000000000000000");
		if (checkedList != null && checkedList.size() > 0) {
			if (checkedList.get(0).getTask() == null) {
				// 将抽查到的企业生成随机任务派发
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
								work.setCreateUser(user);// 创建人：部门领导
								work.setDjrId(user.getId());// 登记人id
								work.setDjrName(user.getName());// 登记人name
							}
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					}
					String id=checkedList.get(i).getLawobjf().getId();
					TDataLawobjf tDataLawobjf = (TDataLawobjf) dao.get(TDataLawobjf.class, id);
					String lawid=tDataLawobjf.getId();
					if(StringUtil.isNotBlank(lawid)){
						// 获取区域内随机用户
						// 1,首先获取区域执法用户
						List<TSysUser> users = userManager.queryUsersByAreaid(tDataLawobjf);
						if(users.size()>0){
						// 2,在users里面获取随机一个用户
						TSysUser jsuser = userManager.randomUser(users);
					
									work.setName("随机任务"); // 任务名称
									work.setWorkNote("随机任务"); // 任务内容
									work.setSource("11");// 任务来源：随机抽查
									work.setCreateTime(new Date());// 创建时间
									work.setEmergency("1");// 紧急程度:一般
									work.setSecurity("3");// 任务密级:机密
									work.setAreaid(doubleRandomManager.queryAreaIdByLawobjfId(tDataLawobjf.getId()));// 随机任务加区域
									Calendar endC = Calendar.getInstance();
									endC.add(Calendar.DATE, tBizConfigpfsj.gettBizConfigpf().getBjsx()-1);// 默认紧急程度一般20天
									SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
					                String endTime= s.format(endC.getTime());
					                Date date = DateUtil.getEndDatetime(endTime);
  
					                work.setEndTime(date);
									work.setIsActive(YnEnum.Y.getCode());// 状态
									work.setZfdxType(tDataLawobjf.getLawobjtype().getId());
									// 保存WORK对象
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
										// 在users里面获取随机一个用户 并且除去主办人
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
											//保存协办人
											commWorkManager.saveRyMul(work.getId(), xbr.getId()+",", TaskUserEnum.XBR.getCode());	
										}
										
									}
									
									
									//保存任务和执法对象的关联
								    String lawobjname = tDataLawobjf.getDwmc();//执法对象名称
								    String address = tDataLawobjf.getDwdz();//执法对象地址
								    String regionId = tDataLawobjf.getSsxzq();//所属区域
								    String fddbr = tDataLawobjf.getFddbr();//法定代表人
								    String fddbrlxdh = tDataLawobjf.getFddbrdh();//法定代表人联系电话
								    String hbfzr = tDataLawobjf.getHbfzr();//环保负责人
								    String hbfzrlxdh = tDataLawobjf.getHbfzrdh();//环保负责人联系电话
									TBizTaskandlawobj tBizTaskandlawobj = new TBizTaskandlawobj(
											work.getId(), tDataLawobjf.getLawobjtype().getId(), tDataLawobjf.getId(), lawobjname, regionId,
											address, fddbr, fddbrlxdh, hbfzr, "", hbfzrlxdh, "", "法定代表人");
									tBizTaskandlawobj.setUpdateby(admin);
									tBizTaskandlawobj.setCreateby(admin);
								    this.dao.save(tBizTaskandlawobj);
								    
								    //保存任务和任务类型关联
								    TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
								    tBizTaskandtasktype.setTaskid(work.getId());//任务id
								    tBizTaskandtasktype.setTasktypeid(TaskTypeCode.RCJC.getCode());//任务类型：现场监察
								    tBizTaskandtasktype.setIsActive(YnEnum.Y.getCode());//状态
								    tBizTaskandtasktype.setCreated(new Date());//创建时间
								    tBizTaskandtasktype.setCreateby(admin);//创建人
								    tBizTaskandtasktype.setUpdateby(admin);
								    tBizTaskandtasktype.setUpdated(new Date());
								    this.dao.save(tBizTaskandtasktype);
								    
								    //把抽中列表中任务id与生成的任务关联
								    TBizCheckedLawobj tBizCheckedLawobj = checkedList.get(i);
								    tBizCheckedLawobj.setTask(work);
								    this.dao.save(tBizCheckedLawobj);//更新抽中列表
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
	 * 所有非重点污染源列表
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
			throw new Exception("工业污染源有字段未进行配置，请重新配置！");
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
		//所属行政区为本区域的企业
		String areaid = areaId;
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//总队
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		if(StringUtil.isNotBlank(year)){
			listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
			condition.put("year1", year);
		}
		
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
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
	 * 所有建设项目列表
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
			throw new Exception("建设项目有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(1));
			jsjdjsczt_column = String.valueOf(listColumn.get(0));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '02'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//生产状态的条件
		listSql.append(" and l.").append(jsjdjsczt_column).append(" != '6' ");
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有医院列表
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
			throw new Exception("医院有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '03'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有锅炉列表
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
			throw new Exception("锅炉有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '04'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有建筑工地列表
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
			throw new Exception("建筑工地有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '05'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有三产列表
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
			throw new Exception("三产有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '06'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有畜禽养殖列表
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
			throw new Exception("畜禽养殖有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '07'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有服务业列表
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
			throw new Exception("服务业有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '08'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有饮食业列表
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
			throw new Exception("饮食业有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '09'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有三产制造业列表
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
			throw new Exception("三产制造业有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '10'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有娱乐业列表
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
			throw new Exception("娱乐业有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '11'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 *  重构查询所有一般企业列表(所有非重点工业污染源和所有其他执法对象类型企业)
	 *  @author gaozhiyang
	 *  @time 重构时间 2017-7-6
	 *  @areaId 区域ID
	 *  @year 抽查年份 
	 * @throws Exception 
	 */
	public List<TDataLawobjf> queryAllNormalListnew(String areaId,String year) throws Exception{
		List<TDataLawobjf> lawobjList = new ArrayList<TDataLawobjf>();
		//非重点的工业污染源
		lawobjList.addAll(this.queryGywryNormalList(areaId,year));//非重点工业污染源
		//除工业污染源的一般企业
		
		lawobjList.addAll(this.queryNormalList(areaId,year));
		return lawobjList;
	}
	//查询工业污染源的一般企业
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
		//企业状态:运营中
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
	
	//查询除工业污染源的一般企业
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
			//企业状态:运营中
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
	 *  查询所有一般企业列表(所有非重点工业污染源和所有其他执法对象类型企业)
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryAllNormalList(String areaId,String year) throws Exception{
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		//非重点的工业污染源
		lawobjList.addAll(this.queryNoKeyLawobjList(areaId,year));//非重点工业污染源
		lawobjList.addAll(this.queryAllJsxmList(areaId,year));//建设项目
		lawobjList.addAll(this.queryAllYyList(areaId,year));//医院
		lawobjList.addAll(this.queryAllGlList(areaId,year));//锅炉
		//lawobjList.addAll(this.queryAllJzgdList(areaId,year));//建筑工地暂时不参与随机抽查
		lawobjList.addAll(this.queryAllScList(areaId,year));//三产
		lawobjList.addAll(this.queryAllXqyzList(areaId,year));//畜禽养殖
		lawobjList.addAll(this.queryAllFwyList(areaId,year));//服务业
		lawobjList.addAll(this.queryAllYsyList(areaId,year));//饮食业
		lawobjList.addAll(this.queryAllZzyList(areaId,year));//制造业
		lawobjList.addAll(this.queryAllYlyList(areaId,year));//娱乐业
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
			 //如果是总队加上总计：
			 String danwei=""; //单位
			 int wryzs=0;//污染源总数
			 int ybwry=0;//一般污染源
			 int zdwry=0;//重点污染源
			 int tswry=0;//特殊污染源
			 int jcrysl=0;//环境监察人员数量
			 int zjc=0;//总家次
			 int ybjc=0;//一般排污单位家次
			 int zdjc=0;//重点排污单位家次
			 int tsjc=0;//特殊监管对象家次
			 int gksl=0;//随机抽查信息公开数量
			 int wfsl=0;//随机抽查发现并查处违法问题数量
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
				cfTotal.setDanwei("合计");
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
			//单位
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
			int zdqy=doubleRandomManager.queryAllKeyLawobjfListByAreaId(areaid).size();//重点企业
			cf.setZdwry(String.valueOf(zdqy));
			int ybqy=doubleRandomManager.queryNormalList(areaid, year).size();//一般企业
			cf.setYbwry(String.valueOf(ybqy));
			//污染源总数
			int wryzs=zdqy+ybqy;
			cf.setWryzs(String.valueOf(wryzs));
			//环境监察人员数量
			List<TSysUser> users=this.dao.find("from TSysUser t where t.isActive='Y' and t.isZaiBian='Y' and t.areaId=?", areaid);
			int jcrysl=users.size();
			cf.setJcrysl(String.valueOf(jcrysl));
			//一般排污单位家次
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
			//重点排污单位家次
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
            //特殊监管对象家次
            List<TBizCheckedLawobj> tsjcs=new ArrayList<TBizCheckedLawobj>();
            if(quarter.equals("0")){
            	tsjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='3' and t.sfypf='Y' and t.areaId=? and t.year=?", areaid,year);

            }else{
            	tsjcs=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y' and t.type='3' and t.sfypf='Y' and t.areaId=? and t.year=? and t.quarter=?", areaid,year,quarter);
            }
			int tsjc=tsjcs.size();
			cf.setTsjc(String.valueOf(tsjc));
			//总家次
			int zjc=ybjc+zdjc+tsjc;
			cf.setZjc(String.valueOf(zjc));
			//随机抽查信息公开数量
			List<TBizCheckedLawobj> gkzs=new ArrayList<TBizCheckedLawobj>();
			if(quarter.equals("0")){
				gkzs=this.dao.findBySql("select  distinct t.lawobjid_ from t_biz_checkedlawobj t where t.isactive_='Y' and t.sfypf_='Y' and t.year_=?  and t.areaid_=?", year,areaid);
			}else{
				gkzs=this.dao.findBySql("select  distinct t.lawobjid_ from t_biz_checkedlawobj t where t.isactive_='Y' and t.sfypf_='Y' and t.year_=? and t.quarter_=? and t.areaid_=?", year,quarter,areaid);
			}
            int gksl=gkzs.size();
            cf.setGksl(String.valueOf(gksl));
            //随机抽查发现并查处违法问题数量
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
		// 该区域内一般企业
		return cf;
	}

	@Override
	public void exportCheckedCountForm(String year, String quarter,
			String areaid, HttpServletResponse res)
			throws Exception {
				//表格数据
				Map<String, List<Object>> map = new HashMap<String, List<Object>>();
//				污染源监察情况
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
					//					ExcelUtil.copyStyle(file, "任务完成情况", 4, 0, 4 + list.size() - 1, 0 + 31);
					is = new FileInputStream(file);
					String quarterName="";
					if(quarter.equals("0")){
						quarterName="全年";
					}else if(quarter.equals("1")){
						quarterName="第一季度";
					}else if(quarter.equals("2")){
						quarterName="第二季度";
					}else if(quarter.equals("3")){
						quarterName="第三季度";
					}else if(quarter.equals("4")){
						quarterName="第四季度";
					}
					String name=year+"年"+quarterName+"随机抽查情况统计表.xls";
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
		//选出本季度所有的企业
		List<TBizCheckedLawobj> allList=this.dao.find("from TBizCheckedLawobj t where t.isActive='Y'  and t.year=? and t.quarter=? and t.areaId=?",year,quarter,areaid);
		//查询出所有要派发的特殊企业
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
		// 根据区域和year，quarter去查询抽选条件
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
					conpfsj.setSfypf(ts.getSfypf().equals("N") ? "否" : "是");
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
					conpfsj.setSfypf(ts.getSfypf().equals("N") ? "否" : "是");
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
			list.add(new Combobox("1", "一月"));
			list.add(new Combobox("2", "二月"));
			list.add(new Combobox("3", "三月"));
		}else if("2".equals(quarter)){
			list.add(new Combobox("4", "四月"));
			list.add(new Combobox("5", "五月"));
			list.add(new Combobox("6", "六月"));
		}else if("3".equals(quarter)){
			list.add(new Combobox("7", "七月"));
			list.add(new Combobox("8", "八月"));
			list.add(new Combobox("9", "九月"));
		}else if("4".equals(quarter)){
			list.add(new Combobox("10", "十月"));
			list.add(new Combobox("11", "十一月"));
			list.add(new Combobox("12", "十二月"));
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
		        
				//得到本次要派发的比例
				int intPfbl=tBizConfigpfsj.getPfbl();
				float pfbl=allList.size()*intPfbl;
				//得到本次要派发的数量
				float pfNumber=pfbl/100;
				int zsNumber=0;
				if(pfNumber==(int)pfNumber){//季度抽取企业数为整数,取整数
					zsNumber=(int)pfNumber;
				}else{
					zsNumber=(int)pfNumber+1;
				}
				//抽选的企业
				List<TDataLawobjf> tDataLawobjs=new ArrayList<TDataLawobjf>();
				List<TBizCheckedLawobj> tBizCheckedLawobjs=new ArrayList<TBizCheckedLawobj>();
				//特殊企业tf.getTsjgqybl().equals('1')按季度派发
				//if(StringUtil.isNotBlank(thcc.getId())){
					//选出本季度没有派发的特殊企业
					List<TDataSpeciallawobj> noList=this.dao.find("from TDataSpeciallawobj t where t.isActive='Y' and t.sfypf='N' and t.year=? and t.quarter=? and t.areaId=?",year,quarter,areaid);
					//float d =(float) proportion/100;//抽查比例
					//要抽取的数量
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
		// 获取上一年度的违法企业
		TSysUser user=CtxUtil.getCurUser();
		List<TDataLawobjf> lists= doubleRandomManager.queryIllegalLawobjfList(year);
		//去掉重复的特塑监管企业
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
		//去掉重复的特塑监管企业
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
		//去掉重复的特塑监管企业
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
		//去掉重复的特塑监管企业
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
		//所属行政区为本区域的企业
				if(StringUtil.isNotBlank(areaid)){
					sql.append(" and c.AREAID_ = :areaId");
					condition.put("areaId", areaid);
				}
				//年份
				if(StringUtil.isNotBlank(year)){
					sql.append(" and c.YEAR_ = :year");
					condition.put("year", year);
				}
				
				//月份
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
				//分组
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
		//所属行政区为本区域的企业
				if(StringUtil.isNotBlank(areaid)){
					sql.append(" and c.AREAID_ = :areaId");
					condition.put("areaId", areaid);
				}
				//年份
				if(StringUtil.isNotBlank(year)){
					sql.append(" and c.YEAR_ = :year");
					condition.put("year", year);
				}
				
				//月份
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
				//分组
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
		//根据year，quarter，获取特塑监管企业
		//获取第一季度的特塑监管企业
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
		// 根据年份季度查询被抽中的企业列表
				List<TBizCheckedLawobj> checkedList=new ArrayList<TBizCheckedLawobj>();
				if(type.equals("1")){
					 checkedList = this.executePfLawobj(year, month,areaid, tBizConfigpfsj);
				}else if(type.equals("2")){
					 checkedList=this.querySpecialLawobjByPfbl(year, quarter, month,areaid,tBizConfigpfsj);
				}
				TSysUser admin = userManager.getUser("a0000000000000000000000000000000");
				if (checkedList != null && checkedList.size() > 0) {
					if (checkedList.get(0).getTask() == null) {
						// 将抽查到的企业生成随机任务派发
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
										work.setCreateUser(user);// 创建人：部门领导
										work.setDjrId(user.getId());// 登记人id
										work.setDjrName(user.getName());// 登记人name
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							String id=checkedList.get(i).getLawobjf().getId();
							TDataLawobjf tDataLawobjf = (TDataLawobjf) dao.get(TDataLawobjf.class, id);
							String lawid=tDataLawobjf.getId();
							if(StringUtil.isNotBlank(lawid)){
								// 获取区域内随机用户
								// 1,首先获取区域执法用户
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
								// 2,在users里面获取随机一个用户
								TSysUser jsuser = userManager.randomUser(users);
											work.setName("随机任务"); // 任务名称
											work.setWorkNote("随机任务"); // 任务内容
											work.setSource("11");// 任务来源：随机抽查
											work.setCreateTime(new Date());// 创建时间
											work.setEmergency("1");// 紧急程度:一般
											work.setSecurity("3");// 任务密级:机密
											work.setAreaid(doubleRandomManager.queryAreaIdByLawobjfId(tDataLawobjf.getId()));// 随机任务加区域
											Calendar endC = Calendar.getInstance();
											endC.add(Calendar.DATE, tBizConfigpfsj.gettBizConfigpf().getBjsx()-1);// 默认紧急程度一般20天
											SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
							                String endTime= s.format(endC.getTime());
							                Date date = DateUtil.getEndDatetime(endTime);
  
							                work.setEndTime(date);
											work.setIsActive(YnEnum.Y.getCode());// 状态
											work.setZfdxType(tDataLawobjf.getLawobjtype().getId());
											// 保存WORK对象
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
													// 在users里面获取随机一个用户 并且出去主办人
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
														//保存协办人
														commWorkManager.saveRyMul(work.getId(), xbr.getId()+",", TaskUserEnum.XBR.getCode());	
													}
													
												}
											}
											//保存任务和执法对象的关联
											 String lawobjname = tDataLawobjf.getDwmc();//执法对象名称
											 String address = tDataLawobjf.getDwdz();//执法对象地址
											 String regionId = tDataLawobjf.getSsxzq();//所属区域
											 String fddbr = tDataLawobjf.getFddbr();//法定代表人
											 String fddbrlxdh = tDataLawobjf.getFddbrdh();//法定代表人联系电话
											 String hbfzr = tDataLawobjf.getHbfzr();//环保负责人
											 String hbfzrlxdh = tDataLawobjf.getHbfzrdh();//环保负责人联系电话
											TBizTaskandlawobj tBizTaskandlawobj = new TBizTaskandlawobj(work.getId(), tDataLawobjf.getLawobjtype().getId(), tDataLawobjf.getId(), lawobjname, regionId,address, fddbr, fddbrlxdh, hbfzr, "", hbfzrlxdh, "", "法定代表人");					
											tBizTaskandlawobj.setUpdateby(admin);
											tBizTaskandlawobj.setCreateby(admin);
										    this.dao.save(tBizTaskandlawobj);
										    
										    //保存任务和任务类型关联
										    TBizTaskandtasktype tBizTaskandtasktype = new TBizTaskandtasktype();
										    tBizTaskandtasktype.setTaskid(work.getId());//任务id
										    tBizTaskandtasktype.setTasktypeid(TaskTypeCode.RCJC.getCode());//任务类型：现场监察
										    tBizTaskandtasktype.setIsActive(YnEnum.Y.getCode());//状态
										    tBizTaskandtasktype.setCreated(new Date());//创建时间
										    tBizTaskandtasktype.setCreateby(admin);//创建人
										    tBizTaskandtasktype.setUpdateby(admin);
										    tBizTaskandtasktype.setUpdated(new Date());
										    this.dao.save(tBizTaskandtasktype);
										    
										    //把抽中列表中任务id与生成的任务关联
										    TBizCheckedLawobj tBizCheckedLawobj = checkedList.get(i);
										    tBizCheckedLawobj.setTask(work);
										    this.dao.save(tBizCheckedLawobj);//更新抽中列表
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
	 *  查询所有已被抽选的一般企业列表，为补足季度抽查数量不够的做备用
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
			
		
		//状态：有效
		listSql.append(" and ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//企业状态:运营中
		listSql.append(" and qyzt").append(" = :qyzt ");
		condition.put("qyzt", "0");
		
		//抽取的数据
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
	 *  查询所有已被抽选的一般企业列表，为补足季度抽查数量不够的做备用
	 * @throws Exception 
	 */
	public List<TDataLawobj> queryCheckedLawobjList(String areaId,String year) throws Exception{
		List<TDataLawobj> lawobjList = new ArrayList<TDataLawobj>();
		//非重点的工业污染源
		lawobjList.addAll(this.queryCheckedNoKeyLawobjList(areaId,year));//非重点工业污染源
		lawobjList.addAll(this.queryCheckedAllJsxmList(areaId,year));//建设项目
		lawobjList.addAll(this.queryCheckedAllYyList(areaId,year));//医院
		lawobjList.addAll(this.queryCheckedAllGlList(areaId,year));//锅炉
		//lawobjList.addAll(this.queryAllJzgdList(areaId,year));//建筑工地暂时不参与随机抽查
		lawobjList.addAll(this.queryCheckedAllScList(areaId,year));//三产
		lawobjList.addAll(this.queryCheckedAllXqyzList(areaId,year));//畜禽养殖
		lawobjList.addAll(this.queryCheckedAllFwyList(areaId,year));//服务业
		lawobjList.addAll(this.queryCheckedAllYsyList(areaId,year));//饮食业
		lawobjList.addAll(this.queryCheckedAllZzyList(areaId,year));//制造业
		lawobjList.addAll(this.queryCheckedAllYlyList(areaId,year));//娱乐业
		return lawobjList;
	}
	
	/**
	 * 所有非重点污染源列表
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
			throw new Exception("工业污染源有字段未进行配置，请重新配置！");
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
		//所属行政区为本区域的企业
		String areaid = areaId;
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("0")){//总队
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}if(area.getType().equals("1")){//师级
				listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
				listSql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
		}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		if(StringUtil.isNotBlank(year)){
			listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
			condition.put("year1", year);
		}
		
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//所属区域
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
	 * 所有建设项目列表
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
			throw new Exception("建设项目有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(1));
			jsjdjsczt_column = String.valueOf(listColumn.get(0));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '02'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		//生产状态的条件
		listSql.append(" and l.").append(jsjdjsczt_column).append(" != '6' ");
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有医院列表
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
			throw new Exception("医院有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '03'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有锅炉列表
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
			throw new Exception("锅炉有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '04'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有建筑工地列表
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
			throw new Exception("建筑工地有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '05'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有三产列表
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
			throw new Exception("三产有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '06'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有畜禽养殖列表
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
			throw new Exception("畜禽养殖有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '07'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有服务业列表
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
			throw new Exception("服务业有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '08'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有饮食业列表
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
			throw new Exception("饮食业有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '09'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有三产制造业列表
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
			throw new Exception("三产制造业有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '10'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
	 * 所有娱乐业列表
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
			throw new Exception("娱乐业有字段未进行配置，请重新配置！");
		}else{
			ssxzq_column = String.valueOf(listColumn.get(0));
			qyzt_column = String.valueOf(listColumn.get(1));
		}
		StringBuffer listSql = new StringBuffer(" select l.id_");
		listSql.append(" from t_Data_Lawobj l ");
		listSql.append(" left join t_data_region r on ").append(" l.").append(ssxzq_column).append(" = r.id_ ");
		listSql.append(" where l.LAWOBJTYPE_ = '11'");
		Map<String, Object> condition = new HashMap<String, Object>();
		//所属行政区为本区域的企业
				String areaid = areaId;
				TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
				if(area.getType().equals("0")){//总队
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}if(area.getType().equals("1")){//师级
						listSql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
						condition.put("regionId", area.getCode());
				}else if(area.getType().equals("2")){//团级
						listSql.append(" and r.id_ = :regionId ");
						condition.put("regionId", area.getCode());
				}
		
		//企业状态:运营中
		listSql.append(" and l.").append(qyzt_column).append(" = :qyzt ");
		condition.put("qyzt", "0");
		//状态：有效
		listSql.append(" and l.ISACTIVE_ = :isactive ");
		condition.put("isactive", YnEnum.Y.getCode());
		if(StringUtil.isNotBlank(year)){
		listSql.append(" and  l.id_ in (select distinct c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
		condition.put("year1", year);
		}
		//所属区域
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
       //单位名称
        if (StringUtils.isNotBlank(name)) {
        	sql.append(" and l.dwmc_ like :name ");
        	condition.put("name", "%"+name+"%");
        }
       //执法对象类型
        if (StringUtils.isNotBlank(lawobjType)) {
        	sql.append(" and l.lawobjtype_ = :lawobjType ");
        	condition.put("lawobjType", lawobjType);
        }
        //所属行政区
        if (StringUtils.isNotBlank(regionId)) {
        	sql.append(" and l.regionId_ = :regionId ");
        	condition.put("regionId", regionId);
        }
        //所属监管部门
        if (StringUtils.isNotBlank(orgId)) {
        	sql.append(" and  l.orgId_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgId  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgId", orgId);
        }
        String areaid = CtxUtil.getAreaId();
		TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
		if(area.getType().equals("1")){//师级
			sql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
			sql.append(" and r.id_ = :regionId ");
			condition.put("regionId", area.getCode());
		}
		//加上建设项目的类型查询条件
        sql.append(" or (l.lawobjtype_ in('02') and l.qyzt_ not in('6')");
        //单位名称
        if (StringUtils.isNotBlank(name)) {
        	sql.append(" and l.dwmc_ like :name ");
        	condition.put("name", "%"+name+"%");
        }
       //执法对象类型
        if (StringUtils.isNotBlank(lawobjType)) {
        	sql.append(" and l.lawobjtype_ = :lawobjType ");
        	condition.put("lawobjType", lawobjType);
        }
        //所属行政区
        if (StringUtils.isNotBlank(regionId)) {
        	sql.append(" and l.regionId_ = :regionId ");
        	condition.put("regionId", regionId);
        }
        //所属监管部门
        if (StringUtils.isNotBlank(orgId)) {
        	sql.append(" and  l.orgId_ in ( SELECT ID_ FROM T_SYS_ORG  WHERE ISACTIVE_='Y' START WITH ID_ = :orgId  CONNECT BY PRIOR ID_=PID_ ) ");
			condition.put("orgId", orgId);
        }
		if(area.getType().equals("1")){//师级
			sql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", area.getCode());
		}else if(area.getType().equals("2")){//团级
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
					row.put("qyzt", "运营中");
				}else if("1".equals(String.valueOf(obj[10]))){
					row.put("qyzt", "已关闭");
				}else if("2".equals(String.valueOf(obj[10]))){
					row.put("qyzt", "已停产");
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
		//表格数据
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		//表头数据
		ConditionsForm conditionsForm = new ConditionsForm();
		//单位名称
		if (StringUtils.isNotBlank(name)) {
				conditionsForm.setName("包含“"+name+"”的企业");
		}else{
			conditionsForm.setName("全部");
		}
		//执法对象类型
		if (StringUtils.isNotBlank(lawobjType)) {
			conditionsForm.setLawobjtypename(dicManager.getNameByTypeAndCode("5",lawobjType));
		}else{
			conditionsForm.setLawobjtypename("全部");
		}
		//所属行政区
		if (StringUtils.isNotBlank(regionName)) {
			conditionsForm.setRegionname(regionName);
		}else{
			conditionsForm.setRegionname("全部");
		}
		//所属监管部门
		if (StringUtils.isNotBlank(orgName)) {
			conditionsForm.setOrgname(orgName);
		}else{
			conditionsForm.setOrgname("全部");
		}
		List<Object> listConditions = new ArrayList<Object>();
		listConditions.add(conditionsForm);
		map.put("conditionsForm", listConditions);
		//列表数据
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
				ExcelUtil.copyStyle(file, "随机抽查样本统计表", 4, 0, 4 + list.size() - 1, 6);
				is = new FileInputStream(file);
				String name1="随机抽查样本统计.xls";
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
        
       //单位名称
        if (StringUtils.isNotBlank(name)) {
        	sql.append(" and l.dwmc_ like :name ");
        	condition.put("name", "%"+name+"%");
        }
       //执法对象类型
        if (StringUtils.isNotBlank(lawobjType)) {
        	sql.append(" and l.lawobjtype_ = :lawobjType ");
        	condition.put("lawobjType", lawobjType);
        }
        //所属行政区
        if (StringUtils.isNotBlank(regionId)) {
        	sql.append(" and l.regionId_ = :regionId ");
        	condition.put("regionId", regionId);
        }
        //所属监管部门
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
					zfdx.setQyzt("运营中");
				}else if("1".equals(String.valueOf(obj[10]))){
					zfdx.setQyzt("已关闭");
				}else if("2".equals(String.valueOf(obj[10]))){
					zfdx.setQyzt("已停产");
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
					conpfsj.setSfypf(ts.getSfypf().equals("N") ? "否" : "是");
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
		//先保存TBizConfigpf 并且返回id值
				TSysUser user=CtxUtil.getCurUser();
				ConfigPfForm findCon=null;
				//特殊企业季度查询
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
					//保存TBizConfigpfsj
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
						// 如果操作表中的记录没有在之前保存的记录中 说明此操作已被删除
						if (!ids.contains(ele)) {
							// 删除页面中被删除的操作
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
					//保存TBizConfigpfsj
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
