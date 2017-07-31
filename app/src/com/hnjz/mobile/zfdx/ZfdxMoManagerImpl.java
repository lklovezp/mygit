package com.hnjz.mobile.zfdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.app.data.po.TDataFwy;
import com.hnjz.app.data.po.TDataGl;
import com.hnjz.app.data.po.TDataGywry;
import com.hnjz.app.data.po.TDataJsxm;
import com.hnjz.app.data.po.TDataJzgd;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.po.TDataSc;
import com.hnjz.app.data.po.TDataXqyz;
import com.hnjz.app.data.po.TDataYly;
import com.hnjz.app.data.po.TDataYsy;
import com.hnjz.app.data.po.TDataYy;
import com.hnjz.app.data.po.TDataZzy;
import com.hnjz.common.jsonUtils;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.sys.po.TSysDic;

@Service("zfdxMoManagerImpl")
public class ZfdxMoManagerImpl  extends ManagerImpl implements ZfdxMoManager{

	@Override
	public List<Map<String, String>> queryLawobjf(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String,String> dataObject=null;
		List<TDataLawobjf> list=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataLawobjf c where c.isactive='Y' and c.updated > to_data(?,yyyy-MM-dd hh24:mi:ss)";
			list=this.dao.find(hsql,update);
		}else{
			String hsql="from TDataLawobjf c where c.isactive='Y'";
			list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(list.get(i).getId()));
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> queryGywry(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String,String> dataObject=null;
		List<TDataGywry> list=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataGywry c where c.updated >to_date(? , 'yyyy-MM-dd hh24:mi:ss')";
		    list=this.dao.find(hsql,update);
		}else{
			String hsql="from TDataGywry c";
			list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
			dataObject=new HashMap<String,String>();
			dataObject.put("ids", list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
			TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class, list.get(i).getLawobjf().getId());
			dataObject.put("fid", lawobjf.getId()==null?"":String.valueOf(lawobjf.getId()));
			dataObject.put("pwxkz", list.get(i).getPwxkz()==null?"":String.valueOf(list.get(i).getPwxkz()));
			dataObject.put("zzjgdm", list.get(i).getZzjgdm()==null?"":String.valueOf(list.get(i).getZzjgdm()));
			dataObject.put("qygm", list.get(i).getQygm()==null?"":String.valueOf(list.get(i).getQygm()));
			dataObject.put("tcrq", list.get(i).getTcrq()==null?"":String.valueOf(list.get(i).getTcrq()));
			dataObject.put("tyshxydm", list.get(i).getTyshxydm()==null?"":String.valueOf(list.get(i).getTyshxydm()));
			dataObject.put("kzlx", list.get(i).getKzlx()==null?"":String.valueOf(list.get(i).getKzlx()));
			dataObject.put("wrfzsssl", list.get(i).getWrfzsssl()==null?"":String.valueOf(list.get(i).getWrfzsssl()));
			dataObject.put("yb", list.get(i).getYb()==null?"":String.valueOf(list.get(i).getYb()));
			dataObject.put("fxygs", list.get(i).getFxygs()==null?"":String.valueOf(list.get(i).getFxygs()));
			dataObject.put("wfcccgs", list.get(i).getWfcccgs()==null?"":String.valueOf(list.get(i).getWfcccgs()));
			dataObject.put("mcjp", list.get(i).getMcjp()==null?"":String.valueOf(list.get(i).getMcjp()));
			dataObject.put("bm", list.get(i).getBm()==null?"":String.valueOf(list.get(i).getBm()));
			dataObject.put("cym", list.get(i).getCym()==null?"":String.valueOf(list.get(i).getCym()));
			dataObject.put("qybh", list.get(i).getQybh()==null?"":String.valueOf(list.get(i).getQybh()));
			dataObject.put("sbdm", list.get(i).getSbdm()==null?"":String.valueOf(list.get(i).getSbdm()));
			dataObject.put("zclx", list.get(i).getZclx()==null?"":String.valueOf(list.get(i).getZclx()));
			dataObject.put("gklx", list.get(i).getGklx()==null?"":String.valueOf(list.get(i).getGklx()));
			dataObject.put("lsgx",list.get(i).getLsgx()==null?"": String.valueOf(list.get(i).getLsgx()));
			dataObject.put("ly", list.get(i).getLy()==null?"":String.valueOf(list.get(i).getLy()));
			dataObject.put("zzdmj", list.get(i).getZzdmj()==null?"":String.valueOf(list.get(i).getZzdmj()));
			dataObject.put("sfsfqy", list.get(i).getSfsfqy()==null?"":String.valueOf(list.get(i).getSfsfqy()));
			dataObject.put("hyqgx", list.get(i).getHyqgx()==null?"":String.valueOf(list.get(i).getHyqgx()));
			dataObject.put("zzhbrys", list.get(i).getZzhbrys()==null?"":String.valueOf(list.get(i).getZzhbrys()));
			dataObject.put("qyhbbm", list.get(i).getQyhbbm()==null?"":String.valueOf(list.get(i).getQyhbbm()));
			dataObject.put("dzyj", list.get(i).getDzyj()==null?"":String.valueOf(list.get(i).getDzyj()));
			dataObject.put("cz", list.get(i).getCz()==null?"":String.valueOf(list.get(i).getCz()));
			dataObject.put("khyh", list.get(i).getKhyh()==null?"":String.valueOf(list.get(i).getKhyh()));
			dataObject.put("yhzh", list.get(i).getYhzh()==null?"":String.valueOf(list.get(i).getYhzh()));
			dataObject.put("qywz", list.get(i).getQywz()==null?"":String.valueOf(list.get(i).getQywz()));
			dataObject.put("updated", list.get(i).getUpdated()==null?"":String.valueOf(list.get(i).getUpdated()));
			dataObject.put("isactive", list.get(i).getIsActive()==null?"":String.valueOf(list.get(i).getIsActive()));
			rows.add(dataObject);
		}
	}
		return rows;
	}

	@Override
	public List<Map<String, String>> queryJsxm(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String,String> dataObject=null;
		List<TDataJsxm> list=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataJsxm c where c.updated >to_date(? , 'yyyy-MM-dd hh24:mi:ss')";
		    list=this.dao.find(hsql,update);
		}else{
			String hsql="from TDataJsxm c";
			list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids", list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
				TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class, list.get(i).getLawobjf().getId());
				dataObject.put("fid", lawobjf.getId()==null?"":String.valueOf(lawobjf.getId()));
				dataObject.put("jldw", list.get(i).getJldw()==null?"":String.valueOf(list.get(i).getJldw()));
				dataObject.put("jsgm", list.get(i).getJsgm()==null?"":String.valueOf(list.get(i).getJsgm()));
				dataObject.put("jsjdjsczt", list.get(i).getJsjdjsczt()==null?"":String.valueOf(list.get(i).getJsjdjsczt()));
				dataObject.put("spjg", list.get(i).getSpjg()==null?"":String.valueOf(list.get(i).getSpjg()));
				dataObject.put("jsdd", list.get(i).getJsdd()==null?"":String.valueOf(list.get(i).getJsdd()));
				dataObject.put("xmkgsj", list.get(i).getXmkgsj()==null?"":String.valueOf(list.get(i).getXmkgsj()));
				dataObject.put("hylx", list.get(i).getHylx()==null?"":String.valueOf(list.get(i).getHylx()));
				dataObject.put("jcsj", list.get(i).getJcsj()==null?"":String.valueOf(list.get(i).getJcsj()));
				dataObject.put("tcsj", list.get(i).getTcsj()==null?"":String.valueOf(list.get(i).getTcsj()));
				dataObject.put("cn", list.get(i).getCn()==null?"":String.valueOf(list.get(i).getCn()));
				dataObject.put("jsnr", list.get(i).getJsnr()==null?"":String.valueOf(list.get(i).getJsnr()));
				dataObject.put("jsxz", list.get(i).getJsxz()==null?"":String.valueOf(list.get(i).getJsxz()));
				dataObject.put("updated", list.get(i).getUpdated()==null?"":String.valueOf(list.get(i).getUpdated()));
				dataObject.put("isactive", list.get(i).getIsActive()==null?"":String.valueOf(list.get(i).getIsActive()));
				rows.add(dataObject);
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> queryYy(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String,String> dataObject=null;
		List<TDataYy> list=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataYy c where c.updated >to_date(? , 'yyyy-MM-dd hh24:mi:ss')";
		    list=this.dao.find(hsql,update);
		}else{
			String hsql="from TDataYy c";
			list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids", list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
				TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class, list.get(i).getLawobjf().getId());
				dataObject.put("fid", lawobjf.getId()==null?"":String.valueOf(lawobjf.getId()));
				dataObject.put("pwxkz", list.get(i).getPwxkz()==null?"":String.valueOf(list.get(i).getPwxkz()));
				dataObject.put("wryfzsssl", list.get(i).getWryfzsssl()==null?"":String.valueOf(list.get(i).getWryfzsssl()));
				dataObject.put("zzjgdm", list.get(i).getZzjgdm()==null?"":String.valueOf(list.get(i).getZzjgdm()));
				dataObject.put("qygm", list.get(i).getQygm()==null?"":String.valueOf(list.get(i).getQygm()));
				dataObject.put("tcrq", list.get(i).getTcrq()==null?"":String.valueOf(list.get(i).getTcrq()));
				dataObject.put("yzbm", list.get(i).getYzbm()==null?"":String.valueOf(list.get(i).getYzbm()));
				dataObject.put("sxzz", list.get(i).getSxzz()==null?"":String.valueOf(list.get(i).getSxzz()));
				dataObject.put("zyzzz", list.get(i).getZyzzz()==null?"":String.valueOf(list.get(i).getZyzzz()));
				dataObject.put("fsaqxkz",list.get(i).getFsaqxkz()==null?"":String.valueOf(list.get(i).getFsaqxkz()));
				dataObject.put("fsysl", list.get(i).getFsysl()==null?"":String.valueOf(list.get(i).getFsysl()));
				dataObject.put("cws", list.get(i).getCws()==null?"":String.valueOf(list.get(i).getCws()));
				dataObject.put("updated", list.get(i).getUpdated()==null?"":String.valueOf(list.get(i).getUpdated()));
				dataObject.put("isactive", list.get(i).getIsActive()==null?"":String.valueOf(list.get(i).getIsActive()));
				rows.add(dataObject);
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> queryGl(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		List<TDataGl> list=null;
		Map<String,String> dataObject=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataGl c where c.updated >to_date(? , 'yyyy-MM-dd hh24:mi:ss')";
		    list=this.dao.find(hsql,update);
		}else{
			String hsql="from TDataGl c";
			list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids", list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
				TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class, list.get(i).getLawobjf().getId());
				dataObject.put("fid", lawobjf.getId()==null?"":String.valueOf(lawobjf.getId()));
				dataObject.put("pwxkz", list.get(i).getPwxkz()==null?"":String.valueOf(list.get(i).getPwxkz()));
				dataObject.put("wryfzsssl", list.get(i).getWryfzsssl()==null?"":String.valueOf(list.get(i).getWryfzsssl()));
				dataObject.put("zzjgdm", list.get(i).getZzjgdm()==null?"":String.valueOf(list.get(i).getZzjgdm()));
				dataObject.put("yt", list.get(i).getYt()==null?"":String.valueOf(list.get(i).getYt()));
				dataObject.put("gls", list.get(i).getGls()==null?"":String.valueOf(list.get(i).getGls()));
				dataObject.put("zd", list.get(i).getZd()==null?"":String.valueOf(list.get(i).getZd()));
				dataObject.put("updated", list.get(i).getUpdated()==null?"":String.valueOf(list.get(i).getUpdated()));
				dataObject.put("isactive", list.get(i).getIsActive()==null?"":String.valueOf(list.get(i).getIsActive()));
			    rows.add(dataObject);
			}
		}
		
		return rows;
	}

	@Override
	public List<Map<String, String>> queryJzgd(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		List<TDataJzgd> list=null;
		Map<String,String> dataObject=null;
		if(StringUtils.isNotBlank(update)){
		  String hsql="from TDataJzgd c where c.updated > to_date(? , 'yyyy-MM-dd hh24:mi:ss')";
		  list=this.dao.find(hsql,update);
		}else{
		  String hsql="from TDataJzgd c";
		  list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids", list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
				TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class, list.get(i).getLawobjf().getId());
				dataObject.put("fid", lawobjf.getId()==null?"":String.valueOf(lawobjf.getId()));
				dataObject.put("wryfzsssl", list.get(i).getWryfzsssl()==null?"":String.valueOf(list.get(i).getWryfzsssl()));
				dataObject.put("jzgdzt", list.get(i).getJzgdzt()==null?"":String.valueOf(list.get(i).getJzgdzt()));
				dataObject.put("yjjgtrq", list.get(i).getYjjgtrq()==null?"":String.valueOf(list.get(i).getYjjgtrq()));
				dataObject.put("kgrq", list.get(i).getKgrq()==null?"":String.valueOf(list.get(i).getKgrq()));
				dataObject.put("lspwxkzbm", list.get(i).getLspwxkzbm()==null?"":String.valueOf(list.get(i).getLspwxkzbm()));
				dataObject.put("updated", list.get(i).getUpdated()==null?"":String.valueOf(list.get(i).getUpdated()));
				dataObject.put("isactive", list.get(i).getIsActive()==null?"":String.valueOf(list.get(i).getIsActive()));
				rows.add(dataObject);
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> queryXqyz(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String,String> dataObject=null;
		List<TDataXqyz> list=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataXqyz c where c.updated >to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			list=this.dao.find(hsql,update);
		}else{
			String hsql="from TDataXqyz c";
			list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids", list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
				TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class, list.get(i).getLawobjf().getId());
				dataObject.put("fid", lawobjf.getId()==null?"":String.valueOf(lawobjf.getId()));
				dataObject.put("pwxkz", list.get(i).getPwxkz()==null?"":String.valueOf(list.get(i).getPwxkz()));
				dataObject.put("wryfzsssl", list.get(i).getWryfzsssl()==null?"":String.valueOf(list.get(i).getWryfzsssl()));
				dataObject.put("zzjgdm", list.get(i).getZzjgdm()==null?"":String.valueOf(list.get(i).getZzjgdm()));
				dataObject.put("tyshxydm", list.get(i).getTyshxydm()==null?"":String.valueOf(list.get(i).getTyshxydm()));
				dataObject.put("yzmj", list.get(i).getYzmj()==null?"":String.valueOf(list.get(i).getYzmj()));
				dataObject.put("xqyzzl", list.get(i).getXqyzzl()==null?"":String.valueOf(list.get(i).getXqyzzl()));
				dataObject.put("cls", list.get(i).getCls()==null?"":String.valueOf(list.get(i).getCls()));
				dataObject.put("chuls", list.get(i).getChuls()==null?"":String.valueOf(list.get(i).getChuls()));
				dataObject.put("updated", list.get(i).getUpdated()==null?"":String.valueOf(list.get(i).getUpdated()));
				dataObject.put("isactive", list.get(i).getIsActive()==null?"":String.valueOf(list.get(i).getIsActive()));
				rows.add(dataObject);
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> querySc(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String,String> dataObject=null;
		List<TDataSc> list=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataSc c where c.updated >to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			list=this.dao.find(hsql,update);
		}else{
			String hsql="from TDataSc c";
			list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids", list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
				TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class, list.get(i).getLawobjf().getId());
				dataObject.put("fid", lawobjf.getId()==null?"":String.valueOf(lawobjf.getId()));
				dataObject.put("pwxkz", list.get(i).getPwxkz()==null?"":String.valueOf(list.get(i).getPwxkz()));
				dataObject.put("wryfzsssl", list.get(i).getWryfzsssl()==null?"":String.valueOf(list.get(i).getWryfzsssl()));
				dataObject.put("yzbm", list.get(i).getYzbm()==null?"":String.valueOf(list.get(i).getYzbm()));
				dataObject.put("wsxkz", list.get(i).getWsxkz()==null?"":String.valueOf(list.get(i).getWsxkz()));
				dataObject.put("updated", list.get(i).getUpdated()==null?"":String.valueOf(list.get(i).getUpdated()));
				dataObject.put("isactive", list.get(i).getIsActive()==null?"":String.valueOf(list.get(i).getIsActive()));
				rows.add(dataObject);
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> queryFwy(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String,String> dataObject=null;
		List<TDataFwy> list=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataFwy c where c.updated >to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			list=this.dao.find(hsql,update);
		}else{
			String hsql="from TDataFwy c";
			list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids", list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
				TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class, list.get(i).getLawobjf().getId());
				dataObject.put("fid", lawobjf.getId()==null?"":String.valueOf(lawobjf.getId()));
				dataObject.put("ry", list.get(i).getRy()==null?"":String.valueOf(list.get(i).getRy()));
				dataObject.put("sf", list.get(i).getSf()==null?"":String.valueOf(list.get(i).getSf()));
				dataObject.put("zz", list.get(i).getZz()==null?"":String.valueOf(list.get(i).getZz()));
				dataObject.put("trq", list.get(i).getTrq()==null?"":String.valueOf(list.get(i).getTrq()));
				dataObject.put("xcj", list.get(i).getXcj()==null?"":String.valueOf(list.get(i).getXcj()));
				dataObject.put("fj", list.get(i).getFj()==null?"":String.valueOf(list.get(i).getFj()));
				dataObject.put("gl", list.get(i).getGl()==null?"":String.valueOf(list.get(i).getGl()));
				dataObject.put("djgngl", list.get(i).getDjgngl()==null?"":String.valueOf(list.get(i).getDjgngl()));
				dataObject.put("gz", list.get(i).getGz()==null?"":String.valueOf(list.get(i).getGz()));
				dataObject.put("sgj", list.get(i).getSgj()==null?"":String.valueOf(list.get(i).getSgj()));
				dataObject.put("cw", list.get(i).getCw()==null?"":String.valueOf(list.get(i).getCw()));
				dataObject.put("lyt", list.get(i).getLyt()==null?"":String.valueOf(list.get(i).getLyt()));
				dataObject.put("jzgr", list.get(i).getJzgr()==null?"":String.valueOf(list.get(i).getJzgr()));
				dataObject.put("kt", list.get(i).getKt()==null?"":String.valueOf(list.get(i).getKt()));
				dataObject.put("pmj", list.get(i).getPmj()==null?"":String.valueOf(list.get(i).getPmj()));
				dataObject.put("drsq", list.get(i).getDrsq()==null?"":String.valueOf(list.get(i).getDrsq()));
				dataObject.put("zwhj", list.get(i).getZwhj()==null?"":String.valueOf(list.get(i).getZwhj()));
				dataObject.put("mj", list.get(i).getMj()==null?"":String.valueOf(list.get(i).getMj()));
				dataObject.put("ds", list.get(i).getDs()==null?"":String.valueOf(list.get(i).getDs()));
				dataObject.put("fzsj", list.get(i).getFzsj()==null?"":String.valueOf(list.get(i).getFzsj()));
				dataObject.put("wyjc", list.get(i).getWyjc()==null?"":String.valueOf(list.get(i).getWyjc()));
				dataObject.put("bz", list.get(i).getBz()==null?"":String.valueOf(list.get(i).getBz()));
				dataObject.put("updated", list.get(i).getUpdated()==null?"":String.valueOf(list.get(i).getUpdated()));
				dataObject.put("isactive", list.get(i).getIsActive()==null?"":String.valueOf(list.get(i).getIsActive()));
			    rows.add(dataObject);
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> queryYsy(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String,String> dataObject=null;
		List<TDataYsy> list=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataYsy c where c.updated >to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			list=this.dao.find(hsql,update);
		}else{
			String hsql="from TDataYsy c";
			list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids", list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
				TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class, list.get(i).getLawobjf().getId());
				dataObject.put("fid", lawobjf.getId()==null?"":String.valueOf(lawobjf.getId()));
				dataObject.put("ry", list.get(i).getRy()==null?"":String.valueOf(list.get(i).getRy()));
				dataObject.put("zwhj", list.get(i).getZwhj()==null?"":String.valueOf(list.get(i).getZwhj()));
				dataObject.put("mj", list.get(i).getMj()==null?"":String.valueOf(list.get(i).getMj()));
				dataObject.put("ds", list.get(i).getDs()==null?"":String.valueOf(list.get(i).getDs()));
				dataObject.put("fzsj", list.get(i).getFzsj()==null?"":String.valueOf(list.get(i).getFzsj()));
				dataObject.put("wyjc", list.get(i).getWyjc()==null?"":String.valueOf(list.get(i).getWyjc()));
				dataObject.put("sf", list.get(i).getSf()==null?"":String.valueOf(list.get(i).getSf()));
				dataObject.put("zz", list.get(i).getZz()==null?"":String.valueOf(list.get(i).getZz()));
				dataObject.put("trq", list.get(i).getTrq()==null?"":String.valueOf(list.get(i).getTrq()));
				dataObject.put("bz", list.get(i).getBz()==null?"":String.valueOf(list.get(i).getBz()));
				dataObject.put("pyygdsfccld", list.get(i).getPyygdsfccld()==null?"":String.valueOf(list.get(i).getPyygdsfccld()));
				dataObject.put("hmj", list.get(i).getHmj()==null?"":String.valueOf(list.get(i).getHmj()));
				dataObject.put("zbj", list.get(i).getZbj()==null?"":String.valueOf(list.get(i).getZbj()));
				dataObject.put("zzi", list.get(i).getZzi()==null?"":String.valueOf(list.get(i).getZzi()));
				dataObject.put("yz", list.get(i).getYz()==null?"":String.valueOf(list.get(i).getYz()));
				dataObject.put("wryfzssazsj", list.get(i).getWrfzssazsj()==null?"":String.valueOf(list.get(i).getWrfzssazsj()));
				dataObject.put("bx", list.get(i).getBx()==null?"":String.valueOf(list.get(i).getBx()));
				dataObject.put("fl", list.get(i).getFl()==null?"":String.valueOf(list.get(i).getFl()));
				dataObject.put("yypfqk", list.get(i).getYypfqk()==null?"":String.valueOf(list.get(i).getYypfqk()));
				dataObject.put("sfzcyx", list.get(i).getSfzcyx()==null?"":String.valueOf(list.get(i).getSfzcyx()));
				dataObject.put("pqfj", list.get(i).getPqfj()==null?"":String.valueOf(list.get(i).getPqfj()));
				dataObject.put("djj", list.get(i).getDjj()==null?"":String.valueOf(list.get(i).getDjj()));
				dataObject.put("mt", list.get(i).getMt()==null?"":String.valueOf(list.get(i).getMt()));
				dataObject.put("yhq", list.get(i).getYhq()==null?"":String.valueOf(list.get(i).getYhq()));
				dataObject.put("wxsj", list.get(i).getWxsj()==null?"":String.valueOf(list.get(i).getWxsj()));
				dataObject.put("lnjz", list.get(i).getLnjz()==null?"":String.valueOf(list.get(i).getLnjz()));
				dataObject.put("zy", list.get(i).getZy()==null?"":String.valueOf(list.get(i).getZy()));
				dataObject.put("qxsj", list.get(i).getQxsj()==null?"":String.valueOf(list.get(i).getQxsj()));
				dataObject.put("updated", list.get(i).getUpdated()==null?"":String.valueOf(list.get(i).getUpdated()));
				dataObject.put("isactive", list.get(i).getIsActive()==null?"":String.valueOf(list.get(i).getIsActive()));
			    rows.add(dataObject);
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> queryZzy(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String,String> dataObject=null;
		List<TDataZzy> list=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataZzy c where c.updated >to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			list=this.dao.find(hsql,update);
		}else{
			String hsql="from TDataZzy c";
			list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids", list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
				TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class, list.get(i).getLawobjf().getId());
				dataObject.put("fid", lawobjf.getId()==null?"":String.valueOf(lawobjf.getId()));
				dataObject.put("zwhj", list.get(i).getZwhj()==null?"":String.valueOf(list.get(i).getZwhj()));
				dataObject.put("mj", list.get(i).getMj()==null?"":String.valueOf(list.get(i).getMj()));
				dataObject.put("ds", list.get(i).getDs()==null?"":String.valueOf(list.get(i).getDs()));
				dataObject.put("fzsj", list.get(i).getFzsj()==null?"":String.valueOf(list.get(i).getFzsj()));
				dataObject.put("wyjc", list.get(i).getWyjc()==null?"":String.valueOf(list.get(i).getWyjc()));
				dataObject.put("sf", list.get(i).getSf()==null?"":String.valueOf(list.get(i).getSf()));
				dataObject.put("zz", list.get(i).getZz()==null?"":String.valueOf(list.get(i).getZz()));
				dataObject.put("bz", list.get(i).getBz()==null?"":String.valueOf(list.get(i).getBz()));
				dataObject.put("dj", list.get(i).getDj()==null?"":String.valueOf(list.get(i).getDj()));
				dataObject.put("dbj", list.get(i).getDbj()==null?"":String.valueOf(list.get(i).getDbj()));
				dataObject.put("zzdd", list.get(i).getZzdd()==null?"":String.valueOf(list.get(i).getZzdd()));
				dataObject.put("dp", list.get(i).getDp()==null?"":String.valueOf(list.get(i).getDp()));
				dataObject.put("qzj", list.get(i).getQzj()==null?"":String.valueOf(list.get(i).getQzj()));
				dataObject.put("djq", list.get(i).getDjq()==null?"":String.valueOf(list.get(i).getDjq()));
				dataObject.put("pmj", list.get(i).getPmj()==null?"":String.valueOf(list.get(i).getPmj()));
				dataObject.put("qgj", list.get(i).getQgj()==null?"":String.valueOf(list.get(i).getQgj()));
				dataObject.put("dz", list.get(i).getDz()==null?"":String.valueOf(list.get(i).getDz()));
				dataObject.put("jmj", list.get(i).getJmj()==null?"":String.valueOf(list.get(i).getJmj()));
				dataObject.put("psyq", list.get(i).getPsyq()==null?"":String.valueOf(list.get(i).getPsyq()));
				dataObject.put("pgj", list.get(i).getPgj()==null?"":String.valueOf(list.get(i).getPgj()));
				dataObject.put("zzj", list.get(i).getZzj()==null?"":String.valueOf(list.get(i).getZzj()));
				dataObject.put("gsjzcs", list.get(i).getGsjzcs()==null?"":String.valueOf(list.get(i).getGsjzcs()));
				dataObject.put("updated", list.get(i).getId()==null?"":String.valueOf(list.get(i).getUpdated()));
				dataObject.put("isactive", list.get(i).getId()==null?"":String.valueOf(list.get(i).getIsActive()));
			    rows.add(dataObject);
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> queryYly(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String,String> dataObject=null;
		List<TDataYly> list=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataYly c where c.updated >to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			list=this.dao.find(hsql,update);
		}else{
			String hsql="from TDataYly c";
			list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids", list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
				TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class, list.get(i).getLawobjf().getId());
				dataObject.put("fid", lawobjf.getId()==null?"":String.valueOf(lawobjf.getId()));
				dataObject.put("ry", list.get(i).getRy()==null?"":String.valueOf(list.get(i).getRy()));
				dataObject.put("zwhj", list.get(i).getZwhj()==null?"":String.valueOf(list.get(i).getZwhj()));
				dataObject.put("mj", list.get(i).getMj()==null?"":String.valueOf(list.get(i).getMj()));
				dataObject.put("ds", list.get(i).getDs()==null?"":String.valueOf(list.get(i).getDs()));
				dataObject.put("fzsj", list.get(i).getFzsj()==null?"":String.valueOf(list.get(i).getFzsj()));
				dataObject.put("wyjc", list.get(i).getWyjc()==null?"":String.valueOf(list.get(i).getWyjc()));
				dataObject.put("sf", list.get(i).getSf()==null?"":String.valueOf(list.get(i).getSf()));
				dataObject.put("zz", list.get(i).getZz()==null?"":String.valueOf(list.get(i).getZz()));
				dataObject.put("bz", list.get(i).getBz()==null?"":String.valueOf(list.get(i).getBz()));
				dataObject.put("gyclzl", list.get(i).getGyclzl()==null?"":String.valueOf(list.get(i).getGyclzl()));
				dataObject.put("pqs", list.get(i).getPqs()==null?"":String.valueOf(list.get(i).getPqs()));
				dataObject.put("zw", list.get(i).getZw()==null?"":String.valueOf(list.get(i).getZw()));
				dataObject.put("gyjzcs", list.get(i).getGyjzcs()==null?"":String.valueOf(list.get(i).getGyjzcs()));
				dataObject.put("yxj", list.get(i).getYxj()==null?"":String.valueOf(list.get(i).getYxj()));
				dataObject.put("dn", list.get(i).getDn()==null?"":String.valueOf(list.get(i).getDn()));
				dataObject.put("kts", list.get(i).getKts()==null?"":String.valueOf(list.get(i).getKts()));
				dataObject.put("bxs", list.get(i).getBxs()==null?"":String.valueOf(list.get(i).getBxs()));
				dataObject.put("xftdckcs", list.get(i).getXftdckcs()==null?"":String.valueOf(list.get(i).getXftdckcs()));
				dataObject.put("pfk", list.get(i).getPfk()==null?"":String.valueOf(list.get(i).getPfk()));
				dataObject.put("yx", list.get(i).getYx()==null?"":String.valueOf(list.get(i).getYx()));
				dataObject.put("updated", list.get(i).getUpdated()==null?"":String.valueOf(list.get(i).getUpdated()));
				dataObject.put("isactive", list.get(i).getIsActive()==null?"":String.valueOf(list.get(i).getIsActive()));
			    rows.add(dataObject);
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> queryDic(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		List<TSysDic> list=null;
		Map<String,String> dataObject=null;
		if(StringUtils.isNotBlank(update)){
			String hsql = " from TSysDic c where c.type not in ('5') and c.isActive='Y' and c.updated > TO_DATE(? , 'yyyy-MM-dd hh24:mi:ss') ";
			list = this.dao.find(hsql,update);
		}else{
			String hsql = " from TSysDic c where c.type not in ('5') and c.isActive='Y' ";
			list = this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids",list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
				dataObject.put("code", list.get(i).getCode()==null?"":String.valueOf(list.get(i).getCode()));
				dataObject.put("name", list.get(i).getName()==null?"":String.valueOf(list.get(i).getName()));
				dataObject.put("type", list.get(i).getType()==null?"":String.valueOf(list.get(i).getType()));
				dataObject.put("isdefaultsel", list.get(i).getIsdefaultsel()==null?"":String.valueOf(list.get(i).getIsdefaultsel()));
				dataObject.put("desc", list.get(i).getDescribe()==null?"":String.valueOf(list.get(i).getDescribe()));
				dataObject.put("orderby", list.get(i).getOrderby()==null?"":String.valueOf(list.get(i).getOrderby()));
				dataObject.put("isactive", list.get(i).getIsActive()==null?"":String.valueOf(list.get(i).getIsActive()));
				dataObject.put("version", list.get(i).getVersion()==null?"":String.valueOf(list.get(i).getVersion()));
				dataObject.put("updated", list.get(i).getUpdated()==null?"":String.valueOf(list.get(i).getUpdated()+" 23:59:59"));
				dataObject.put("updateby", list.get(i).getUpdateby()==null?"":String.valueOf(list.get(i).getUpdateby()));
				dataObject.put("created", list.get(i).getCreated()==null?"":String.valueOf(list.get(i).getCreated()+" 23:59:59"));
				dataObject.put("createdby", list.get(i).getCreateby()==null?"":String.valueOf(list.get(i).getCreateby()));
				dataObject.put("mandatory", list.get(i).getMandatory()==null?"":String.valueOf(list.get(i).getMandatory()));
				rows.add(dataObject);
			}
			
		}
		
		return rows;
	
	}

	@Override
	public List<Map<String, String>> queryLawObjtype(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		List<TDataLawobjtype> list=null;
		Map<String,String> dataObject=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataLawobjtype c where c.isactive='Y' and c.updated > TO_DATE(? , 'yyyy-MM-dd hh24:mi:ss') ";
			list = this.dao.find(hsql,update);
		}else{
			String hsql="from TDataLawobjtype c where c.isactive='Y'";
			list= this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				
				dataObject.put("ids", String.valueOf(list.get(i).getId()));
				if(list.get(i).getLawobjtype()!=null){
				TDataLawobjtype type=(TDataLawobjtype) this.dao.get(TDataLawobjtype.class, list.get(i).getLawobjtype().getId());
				dataObject.put("fid", String.valueOf(type.getId()));
				}else{
				dataObject.put("fid", "");
				}
				dataObject.put("name", list.get(i).getName()==null?"":String.valueOf(list.get(i).getName()));
				dataObject.put("isactive", list.get(i).getIsactive()==null?"":String.valueOf(list.get(i).getIsactive()));
				dataObject.put("updated",list.get(i).getUpdated()==null?"": String.valueOf(list.get(i).getUpdated()+" 23:59:59"));
				dataObject.put("updateby",list.get(i).getUpdateby()==null?"": String.valueOf(list.get(i).getUpdateby()));
				dataObject.put("created", list.get(i).getCreated()==null?"":String.valueOf(list.get(i).getCreated()+" 23:59:59"));
				dataObject.put("createby", list.get(i).getCreateby()==null?"":String.valueOf(list.get(i).getCreateby()));
			    rows.add(dataObject);
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> queryJcmb(String update) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String,String> dataObject=null;TDataChecklisttemplate dd=null; 
		List<TDataChecklisttemplate> list=null;
		if(StringUtils.isNotBlank(update)){
			String hsql="from TDataChecklisttemplate c where c.isAcitve='Y' and c.updated > TO_DATE(? , 'yyyy-MM-dd hh24:mi:ss')";
			list=this.dao.find(hsql,update);
		}else{
			String hsql="from TDataChecklisttemplate c where c.isActive='Y'";
			list=this.dao.find(hsql);
		}
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				dataObject=new HashMap<String,String>();
				dataObject.put("ids",list.get(i).getId()==null?"":String.valueOf(list.get(i).getId()));
				dataObject.put("name", list.get(i).getName()==null?"":String.valueOf(list.get(i).getName()));
				dataObject.put("pid", list.get(i).getPid()==null?"":String.valueOf(list.get(i).getPid()));
				dataObject.put("industryid", list.get(i).getIndustryid()==null?"":String.valueOf(list.get(i).getIndustryid()));
				dataObject.put("tasktypeid", list.get(i).getTasktypeid()==null?"":String.valueOf(list.get(i).getTasktypeid()));
				dataObject.put("isrequired", list.get(i).getIsrequired()==null?"":String.valueOf(list.get(i).getIsrequired()));
				dataObject.put("release", list.get(i).getRelease()==null?"":String.valueOf(list.get(i).getRelease()));
				dataObject.put("iscurver", list.get(i).getIscurver()==null?"":String.valueOf(list.get(i).getIscurver()));
				dataObject.put("childnum", list.get(i).getChildnum()==null?"":String.valueOf(list.get(i).getChildnum()));
				dataObject.put("orderby", list.get(i).getOrderby()==null?"":String.valueOf(list.get(i).getOrderby()));
				dataObject.put("desc", list.get(i).getDescribe()==null?"":String.valueOf(list.get(i).getDescribe()));
				dataObject.put("isactive", list.get(i).getIsActive()==null?"":String.valueOf(list.get(i).getIsActive()));
				dataObject.put("version", list.get(i).getVersion()==null?"":String.valueOf(list.get(i).getVersion()));
				dataObject.put("updated", list.get(i).getUpdated()==null?"":String.valueOf(list.get(i).getUpdated()));
				dataObject.put("updateby", list.get(i).getUpdateby()==null?"":String.valueOf(list.get(i).getUpdateby()));
				dataObject.put("created", list.get(i).getCreated()==null?"":String.valueOf(list.get(i).getCreated()));
				dataObject.put("createby", list.get(i).getCreateby()==null?"":String.valueOf(list.get(i).getCreateby()));
				dataObject.put("namevisiable", list.get(i).getNamevisiable()==null?"":String.valueOf(list.get(i).getNamevisiable()));
				rows.add(dataObject);
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, Object>> querytaskUser(String update,String table) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		 String sql="select * from "+table+" t  where 1=1 ";
		// map.put("tabled", table);
		 if(!table.equals("t_sys_userorg")&&!table.equals("t_sys_userrole")&&!table.equals("t_biz_taskuser")&&!table.equals("t_biz_taskillegaltype")
				 &&!table.equals("t_biz_blwtx")&&!table.equals("t_data_dirandtasktype")&&!table.equals("T_Data_Lawobjtypetasktype")&&!table.equals("t_sys_userrole")&&!table.equals("t_sys_userrole")
				){
			 sql+=" and t.isactive_='Y'  ";
		 }
		 if(table.equals("work_")){
			 sql+=" and t.state_='05'";
		 }
		 if(StringUtils.isNotBlank(update)){
			sql+=" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')";
			map.put("updated", update);
		  }
		
		System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW44 徐"+table);
		List<Map<String, Object>> rows=this.dao.queryListMapBySql(sql,null);
		return rows;
	}

	@Override
	public void getMoTbdatas(String table, String datas) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String,String>> list=jsonUtils.jsonStringToList(datas);
		
     for(int i=0;i<list.size();i++){
    	String sql="";  //主语句
 		String sql1=""; //不带id的键
 		String sql2=""; //值键对
 		String sql3="";//所有键
 		String sql4="";//处理值
 	    
    	 Map<String,String> m=list.get(i);
    	 for(String k :m.keySet()){
    		// System.out.println(k + " : " + m.get(k));
    		 if(!k.equals("baseObjId")){
    			 if(!k.equals("ID_")){
    				  sql1+="p."+k+"=np."+k+",";
    	   		 }
    			 if(k.contains("TIME")){
    				 if(k.equals("TIMES_")&&StringUtils.isNotBlank(m.get("TIMES_"))){
    					 sql2+=" "+Integer.parseInt(m.get(k))+" "+k+","; 
    				 }else{
    					 sql2+=" to_date('"+m.get(k)+"','yyyy-MM-dd hh24:mi:ss') "+k+"," ;
    				 }
    			 }else if(k.contains("ATED")){
    				 sql2+=" to_date('"+m.get(k)+"','yyyy-MM-dd hh24:mi:ss') "+k+","; 
    			 }else if(k.equals("RWMCRQ_")){
    				 sql2+=" to_date('"+m.get(k)+"','yyyy-MM-dd hh24:mi:ss') "+k+",";  
    			 }else if(k.contains("SJ")){
    				 if(k.equals("SJID_")&&StringUtils.isNotBlank(m.get("SJID_"))){
    					 sql2+="'"+m.get(k)+"' "+k+",";
    				 }else if(k.equals("SSJGBM_")&&StringUtils.isNotBlank(m.get("SSJGBM_"))){
    					 sql2+="'"+m.get(k)+"' "+k+",";
    				 }else if(k.equals("JSJDJSCZT_")&&StringUtils.isNotBlank(m.get("JSJDJSCZT_"))){
    					 sql2+="'"+m.get(k)+"' "+k+",";
    				 } else{
    					 sql2+=" to_date('"+m.get(k)+"','yyyy-MM-dd hh24:mi:ss') "+k+","; 
    				 }
    				   
    			 }else if(k.equals("VERSION_")&&StringUtils.isNotBlank(m.get("VERSION_"))){
    				 sql2+=" "+Integer.parseInt(m.get(k))+" "+k+",";  
    			 }else{
    				 sql2+="'"+m.get(k)+"' "+k+",";   
    			 } 
    			 
    			 sql3+=""+k+","; 
    			 if(k.contains("TIME")){
    				 if(k.equals("TIMES_")&&StringUtils.isNotBlank(m.get("TIMES_"))){
        				 sql4+=""+Integer.parseInt(m.get(k))+","; 
    				 }else{
    					 sql4+="to_date('"+m.get(k)+"','yyyy-MM-dd hh24:mi:ss'),"; 
    				 }
    			 }else if(k.contains("ATED")){
    				 sql4+="to_date('"+m.get(k)+"','yyyy-MM-dd hh24:mi:ss'),"; 
    			 }else if(k.equals("RWMCRQ_")){
    				 sql4+="to_date('"+m.get(k)+"','yyyy-MM-dd hh24:mi:ss'),"; 
    			 }else if(k.contains("SJ")){
    				 if(k.equals("SJID_")&&StringUtils.isNotBlank(m.get("SJID_"))&&k.equals("JSJDJSCZT_")&&StringUtils.isNotBlank(m.get("JSJDJSCZT_"))){
    					 sql4+="'"+m.get(k)+"',"; 
    				 }else if(k.equals("SSJGBM_")&&StringUtils.isNotBlank(m.get("SSJGBM_"))){
    					 sql4+="'"+m.get(k)+"',";
    				 }else if(k.equals("JSJDJSCZT_")&&StringUtils.isNotBlank(m.get("JSJDJSCZT_"))){
    					 sql4+="'"+m.get(k)+"',";
    				 }else{
    					 sql4+="to_date('"+m.get(k)+"','yyyy-MM-dd hh24:mi:ss'),";
    				 }
    			 }else if(k.equals("VERSION_")&&StringUtils.isNotBlank(m.get("VERSION_"))){
    				 sql4+=""+Integer.parseInt(m.get(k))+","; 
    			 }else{
    				 sql4+="'"+m.get(k)+"',";   
    			 } 
    			 
    		 }
    		
    	}
    	 sql1=sql1.substring(0,sql1.length()-1);
	     sql2=sql2.substring(0,sql2.length()-1);
	     sql4=sql4.substring(0,sql4.length()-1);
	     sql3=sql3.substring(0,sql3.length()-1);
	     /*System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW 皇"+sql1);
	     System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW 帝"+sql2);
	     System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW 徐"sql3);
	     System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW 睿"+sql4);*/
	     sql="merge into "+table+" p using (select";
	     sql+=" "+sql2+" from dual) np on (p.id_=np.id_)";
		 sql+=" when matched then  update set "+sql1;
		 sql+=" when not matched then insert ("+sql3+") values("+sql4+")";
		 this.dao.exec(sql);
    	 
    	// System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW 睿"+m.size());
     }
	}
	@Override
	public List querytaskByUserid(String update, String userid)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		String sql="select f.* from work_ t left join t_data_file f on t.id_=f.pid_ where  f.isactive_='Y' and t.isactive_='Y' and t.state_='05' and t.id_ in(select tu.taskid_ from t_biz_taskuser tu where tu.userid_=:userid and tu.type_='05')";
		map.put("userid", userid);
		System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW 徐"+userid);
		List<Map<String, Object>> rows=this.dao.queryListMapBySql(sql,null);
		return rows;
	}
}
