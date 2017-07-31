package com.hnjz.app.data.xxgl.lawobjdic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
	执法对象字典manager实现层
 *
 */
@Service("lawobjDicManager")
public class LawobjDicManagerImpl extends ManagerImpl implements LawobjDicManager{

	@Override
	public List<Combobox> queryLawobjColumnList() {
		List<Combobox> listBox = new ArrayList<Combobox>();
		String sql = "select t.column_name  from user_tab_columns t  where Table_Name='T_DATA_LAWOBJ' and t.column_name not in ('ID_','LAWOBJTYPE_','UPDATEBY_','UPDATED_','VERSION_','CREATEBY_','CREATED_','AREAID_') order by t.COLUMN_ID";
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
		for(Map<String,Object> map : list){
			String column_name = (String) map.get("column_name");
			listBox.add(new Combobox(column_name,column_name));
		}
		return listBox;
	}

	@Override
	public void saveOrUpdateLawobjDic(String lawobjtypeid, String[] id, String[] orderby, String[] colengname, String[] enumname, String[] colchiname, String[] inputtype, String[] datasource,
			String[] mandatory, String[] istwoitem) {
		List<Object> listId = this.find("select id from TDataLawobjdic d where d.lawobjtypeid = ?",lawobjtypeid);
		for(Object obj : listId){
			boolean isTrue = false;
			for(String s : id){
				if(String.valueOf(obj).equals(s)){
					isTrue = true;
					break;
				}
			}
			if(!isTrue){
				this.remove(TDataLawobjdic.class, String.valueOf(obj));
			}
		}
		for(int i=0;i<id.length;i++){
			if(StringUtils.isBlank(colchiname[i])){
				continue;
			}
			TDataLawobjdic lawobjDic = null;
			TSysUser user = CtxUtil.getCurUser();
			if(StringUtils.isNotBlank(id[i])){
				lawobjDic = (TDataLawobjdic) this.get(TDataLawobjdic.class, id[i]);
			}else{
				lawobjDic = new TDataLawobjdic();
				lawobjDic.setCreateby(user);
				lawobjDic.setCreated(new Date(System.currentTimeMillis()));
			}
			lawobjDic.setLawobjtypeid(lawobjtypeid);
			lawobjDic.setColengname(colengname[i]);
			lawobjDic.setEnumname(enumname[i]);
			lawobjDic.setColchiname(colchiname[i]);
			lawobjDic.setInputtype(inputtype[i]);
			lawobjDic.setDatasource(datasource[i]);
			lawobjDic.setMandatory(mandatory[i]);
			lawobjDic.setIstwoitem(istwoitem[i]);
			lawobjDic.setOrderby(Integer.parseInt(orderby[i]));
			lawobjDic.setIsActive("Y");
			lawobjDic.setUpdateby(user);
			lawobjDic.setUpdated(new Date(System.currentTimeMillis()));
			this.save(lawobjDic);
		}
	}

	@Override
	public List<TDataLawobjdic> queryLawobjDicList(String lawobjtypeid){
		List<TDataLawobjdic> list = this.find("from TDataLawobjdic d where d.lawobjtypeid = ? order by d.orderby ",lawobjtypeid);
		return list;
	}
	
}
