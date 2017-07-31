package com.hnjz.app.data.zfdx.lawobjtype;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataGywry;
import com.hnjz.app.data.po.TDataLawdocdir;
import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.app.data.po.TDataWg;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.common.FyWebResult;
import com.hnjz.IndexManager;
import com.hnjz.common.AppException;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sal.WorkClientFactoty;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;


/**
 * Tdatalawobjtype管理的manager
 * 
 * @author xuyaxing
 * @version 
 */
 @Service("ZfdxManagerImpl")
public class ZfdxManagerImpl extends ManagerImpl implements ZfdxManager{


    /** 日志 */
	private static final Log log = LogFactory.getLog(ZfdxManagerImpl.class);

	
	@Autowired
    private IndexManager     indexManager;

/**
	 * 查询功能
	 * @param isActive 
	 * 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray queryTdatalawobjtype( String isActive) throws Exception{
		String biaoshi = indexManager.sysVer;
        JSONArray re = new JSONArray();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
        sql.append("from TDataLawobjtype where 1=1 ");
        
		sql.append(" and isactive = :isActive ");
		data.put("isActive", YnEnum.Y.getCode());
		sql.append(" order by created ");
        
        List< TDataLawobjtype > pos = dao.find(sql.toString(), data);
         
         JSONObject jsonObj = null;
         jsonObj = new JSONObject();
			
         for( TDataLawobjtype ele :pos){
            
            jsonObj = new JSONObject();
             jsonObj.put("id", ele.getId());
             if("0".equals(biaoshi)){
 				if(null != ele.getLawobjtype()){
 					if(!"".equals( ele.getLawobjtype().getId())){
 						if (!StringUtil.isNotBlank(isActive) || isActive.equals(YnEnum.Y.getCode())){
 							if (null != ele.getLawobjtype()) {
 								jsonObj.put("pid",ele.getLawobjtype().getId());
 							}
 						}
 					}
 				}
 			}else{
 				if (!StringUtil.isNotBlank(isActive) || isActive.equals(YnEnum.Y.getCode())){
 					if (null != ele.getLawobjtype()) {
 						jsonObj.put("pid",  ele.getLawobjtype().getId());
 					}
 				}
 			}
             if(null !=ele.getLawobjtype()){
            	 jsonObj.put("fid", ele.getLawobjtype().getId());
             }
             
             jsonObj.put("name", ele.getName());
             
             
             jsonObj.put("isactive", ele.getIsactive());
             
              
              re.put(jsonObj);
         
         }
    
    
    return re;
    
    }
    
    
    

/**
	 * 删除Tdatalawobjtype
	 * 
	 * @param id
	 *            Tdatalawobjtype的ID
	 */
	public void removeTdatalawobjtype(String id) throws AppException{
		TDataLawobjtype del = (TDataLawobjtype) this.dao.get(TDataLawobjtype.class, id);
		del.setIsactive(YnEnum.N.getCode());
		this.dao.save(del);
    }
    
    
    
    /**
	 * 保存Tdatalawobjtype
	 * 
	 * @param frm
	 * @throws AppException
	 */
	public void saveTdatalawobjtype(ZfdxForm frm) throws AppException{
		StringBuilder hsq = new StringBuilder();
		hsq.append("select count(id) from TDataLawobjtype where isactive = 'Y' and name = :name ");
		QueryCondition data = new QueryCondition();
		data.put("name", frm.getName());
		if (StringUtils.isNotBlank(frm.getId())) {
			hsq.append("and id <> :id");
			data.put("id", frm.getId());
		}
		long count = (Long) this.dao.find(hsq.toString(), data).get(0);
		if (count > 0) {
			throw new AppException("执法对象类型名称不能重复！");
		}
		TDataLawobjtype po = null;
	    TSysUser user = CtxUtil.getCurUser();
	    Date cur = new Date();
	    if (StringUtils.isNotBlank(frm.getId())) {
			po = (TDataLawobjtype) this.get(TDataLawobjtype.class, frm.getId());
		}else{
			po = new TDataLawobjtype();
			po.setCreateby(user);
			po.setCreated(cur);
			
		}
	    TDataLawobjtype lawobjtype=null;
	    po.setName(frm.getName());
	    po.setIsactive(YnEnum.Y.getCode());
	    po.setUpdateby(user);
		po.setUpdated(cur);
		if(StringUtils.isNotBlank(frm.getLawobjtypeid())){
			lawobjtype= (TDataLawobjtype) this.dao.get(TDataLawobjtype.class, frm.getLawobjtypeid());
		}
		po.setLawobjtype(lawobjtype);
    this.dao.save(po);
    
    
    }


/**
	 * 获取单个Tdatalawobjtype
	 * 
	 * @param id
	 *            Tdatalawobjtype的ID
	 */
    @Override
	public ZfdxForm tdatalawobjtypeInfo(String id){
    	ZfdxForm frm=new ZfdxForm();
       TDataLawobjtype po=(TDataLawobjtype)this.dao.get(TDataLawobjtype.class,id);
       
       frm.setId(po.getId());
       if (StringUtils.isNotBlank(po.getLawobjtype().getId())) {
	    	TDataLawobjtype lawobjtype = (TDataLawobjtype) this.dao.get(TDataLawobjtype.class, po
					.getLawobjtype().getId());
			frm.setLawobjtypeid(lawobjtype.getId());
			frm.setLawobjtypename(lawobjtype.getName());
		}
       
       frm.setName(po.getName());
       
       
       frm.setIsactive(po.getIsactive());
       
       return frm;
    }



       /**
        * 编辑执法对象类型
        */
		@Override
		public void editLawObjType(ZfdxForm frm) {
			// TODO Auto-generated method stub
			TDataLawobjtype po=(TDataLawobjtype) this.dao.get(TDataLawobjtype.class, frm.getId());
		    frm.setId(po.getId());
		    frm.setName(po.getName());
		    if (null !=po.getLawobjtype()) {
		    	TDataLawobjtype lawobjtype = (TDataLawobjtype) this.dao.get(TDataLawobjtype.class, po
						.getLawobjtype().getId());
				frm.setLawobjtypeid(lawobjtype.getId());
				frm.setLawobjtypename(lawobjtype.getName());
			}
		    frm.setIsactive(po.getIsactive());
		
		}




	@Override
	public TDataLawobjtype querylawobjtypeById(String id) {
		// TODO Auto-generated method stub
		TDataLawobjtype lawobj=(TDataLawobjtype) this.dao.get(TDataLawobjtype.class, id);
		
		return lawobj;
	}




	@Override
	public List<TDataLawobjtype> querylawobjtypeByFId(String fid) {
		// TODO Auto-generated method stub
		
		String hsql = " from TDataLawobjtype where lawobjtype.id=?";
		List<TDataLawobjtype> re = this.dao.find(hsql, fid);
		
		return re;
	}

	@Override
	public List<TDataLawobjtype> querylawobjtype() {
		// TODO Auto-generated method stub
		
		String hsql = " from TDataLawobjtype ";
		List<TDataLawobjtype> re = this.dao.find(hsql);
		
		return re;
	}




	@Override
	public TDataLawobjtype gettype(String id) {
		// TODO Auto-generated method stub
		TDataLawobjtype type=(TDataLawobjtype) this.dao.get(TDataLawobjtype.class, id);
		return type;
	}




	@Override
	public JSONArray getLawdocTypeListByPid(String pid) {
		// TODO Auto-generated method stub
		
		return null;
	}




	@Override
	public List<ComboboxTree> queryLawobjtypeComboTree() {
		// TODO Auto-generated method stub
		String hsql="from TDataLawobjtype d where d.isactive='Y' ";
		List<TDataLawobjtype> list=this.dao.find(hsql);
			
		return  this.LawobjtypeComboTreeHelp(list,null);
	}
   
	/**
	 * 
	 * 函数介绍：执法对象类型树 执法目录树 帮助方法 获得pid的所有子项
	
	 * 输入参数：
	
	 * 返回值：
	 */
	private List<ComboboxTree> LawobjtypeComboTreeHelp(List<TDataLawobjtype> list, String pid) {
		List<ComboboxTree> treeList = new ArrayList<ComboboxTree>();
		for(TDataLawobjtype ele : list){
			 if(null != pid && null != ele.getLawobjtype() && pid.equals(ele.getLawobjtype().getId())){
					ComboboxTree comboboxTree = new ComboboxTree(ele.getId(),ele.getName(),ele.getLawobjtype().getId(),null);
					comboboxTree.setChildren(this.LawobjtypeComboTreeHelp(list, ele.getId()));
					treeList.add(comboboxTree);
				 }else if(null == pid && null==ele.getLawobjtype()){
					ComboboxTree comboboxTree = new ComboboxTree(ele.getId(),ele.getName(),null,null);
					comboboxTree.setChildren(this.LawobjtypeComboTreeHelp(list, ele.getId()));
					treeList.add(comboboxTree);
				 }
			
}
		return treeList;
	}




	@Override
	public TDataLawobjtype getType(String lawobjid) {
		// TODO Auto-generated method stub
		TDataLawobjtype type = null;
		String hsql = " from TDataLawobjtype where id=?";
		List<TDataLawobjtype> re = this.dao.find(hsql, lawobjid);
		if (re.isEmpty()) {
			return null;
		} else {
			type = re.get(0);
		}

		return type;
	}
	
}

