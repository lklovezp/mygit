package com.hnjz.app.rwsz.discretion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hnjz.app.rwsz.po.DiscretionaryPower;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.sys.po.TSysDic;

/**
 * 自由裁量权
 * 
 * @author xuguanghui
 * @version $Id: DiscretionaryPowerManager.java, v 0.1 Aug 6, 2013 2:01:20 PM Administrator Exp $
 */
@Service("discretionaryPowerManager")
public class DiscretionaryPowerManager extends ManagerImpl {
    
    /**日志*/
    private static final Log log = LogFactory.getLog(DiscretionaryPowerManager.class);

    /**
     * 查询全部
     * 
     * @return
     */
    public List<DiscretionaryPower> queryAllList() {
        String hsql = " from DiscretionaryPower ";
        List<DiscretionaryPower> list = dao.find(hsql);
        return list;
    }

    /**
     * 查询全部
     * 
     * @return
     * @throws JSONException
     */
    public JSONArray queryAllArray(String id) throws JSONException {
        JSONArray arr = new JSONArray();
        String hsql = " from DiscretionaryPower ";
        List<DiscretionaryPower> list = dao.find(hsql);
        for (DiscretionaryPower po : list) {
            JSONObject obj = new JSONObject();
            obj.put("id", null == po.getId() ? "" : po.getId());
            obj.put("name", null == po.getName() ? "" : po.getName());
            obj.put("pid", null == po.getPid() ? "" : po.getPid());
            obj.put("type", null == po.getCode() ? "" : po.getCode());
            if(StringUtils.isNotBlank(id)){
                obj.put("selected", StringUtils.equals(id, po.getId()));
            }
            arr.put(obj);
        }
        return arr;
    }

    /**
     * 查询全部
     * 
     * @return
     * @throws JSONException
     */
    public JSONArray queryAllArrayByWflx(String wflx) throws JSONException {
        JSONArray arr = new JSONArray();
        String hsql = " from DiscretionaryPower ";
        List<DiscretionaryPower> list = dao.find(hsql);
        for (DiscretionaryPower po : list) {
            JSONObject obj = new JSONObject();
            obj.put("id", null == po.getId() ? "" : po.getId());
            obj.put("name", null == po.getName() ? "" : po.getName());
            obj.put("checked", StringUtils.equals(wflx, po.getIllegalTypeId()));
            obj.put("pid", null == po.getPid() ? "" : po.getPid());
            obj.put("type", null == po.getCode() ? "" : po.getCode());
            arr.put(obj);
        }
        return arr;
    }

    /**
     * 保存
     * 
     * @param po
     */
    public void save(DiscretionaryPower po) {
        dao.save(po);
    }

    /**
     * 删除
     * 
     * @param id
     */
    public void remove(String id) {
        dao.remove(DiscretionaryPower.class, id);
    }

    /**
     * 根据id取得子节点集合
     * 
     * @return
     */
    public List<DiscretionaryPower> getChildNodesById(String id) {
        String hsql = " from DiscretionaryPower t where t.pid = ?";
        List<DiscretionaryPower> list = dao.find(hsql, id);
        return list;
    }

    /**
     * 根据id查找
     * 
     * @param id
     * @return
     */
    public DiscretionaryPower getDiscretionById(String id) {
        return (DiscretionaryPower) dao.get(DiscretionaryPower.class, id);
    }

    /**
     * 查找所有的父节点
     * 
     * @param id 自由裁量记录Id
     * @return 所有的父节点的集合
     */
    public List<DiscretionaryPower> getParents(String id) {
        List<DiscretionaryPower> re = new ArrayList<DiscretionaryPower>();
        this.getPars(id, re);
        Collections.reverse(re);
        return re;
    }

    /**
     * 递归查找当前自由裁量的所有父节点
     * 
     * @param pid 父id
     * @param re 集合
     */
    private void getPars(String pid, List<DiscretionaryPower> re) {
        if (StringUtils.isNotBlank(pid)) {
            DiscretionaryPower po = this.getDiscretionById(pid);
            if (null != po) {
                if (log.isDebugEnabled()) {
                    log.debug("desc:"+po.getDesc());
                }
                re.add(po);
                this.getPars(po.getPid(), re);
            }
        }
    }
    public String getdisInfoArr(String pid){
    	List<DiscretionaryPower> dislist=getParents(pid);
    	JSONArray array = new JSONArray();
    	JSONObject obj = new JSONObject();
    	for(DiscretionaryPower dis:dislist){
    		try {
    			obj.put("id", dis.getId());
    			if(String.valueOf(DiscreCodeEnum.ZDFL.getCode()).equals(dis.getCode())){
    				obj.put("zdfl",dis.getName());//制度分类
    			}
    			else if(String.valueOf(DiscreCodeEnum.WFXW.getCode()).equals(dis.getCode())){
    				obj.put("wfxw",dis.getName());//违法行为
    			}
    			else if(String.valueOf(DiscreCodeEnum.FLYJ.getCode()).equals(dis.getCode())){
    				obj.put("flyj",dis.getName());//法律依据
    			}
    			else if(String.valueOf(DiscreCodeEnum.QXFL.getCode()).equals(dis.getCode())){
    				obj.put("qxfl",dis.getName());//情形分类
    			}
    			obj.put("wflx",dis.getIllegalTypeName());//违法类型
				obj.put("neirong", dis.getContent());
				array.put(obj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return array.toString();
    }
    /**
     * 根据违法类型id查找法律依据
     * 
     * @param illegalTypeId
     * @return
     */
    public List<DiscretionaryPower> getNodesByIllegalTypeId(String illegalTypeId) {
        String hsql = " from DiscretionaryPower t where t.illegalTypeId = ? and t.code=? ";
        String code =  String.valueOf(DiscreCodeEnum.FLYJ.getCode());
        return dao.find(hsql, illegalTypeId,code);
    }

    public void saveWflx(String[] ids, String wflxid) {
        for (String ele : ids) {
            if (StringUtils.isBlank(ele)) {
                continue;
            }
            DiscretionaryPower m = getDiscretionById(ele);
            if (null != m) {
                m.setIllegalTypeId(wflxid);
                TSysDic dict = (TSysDic) dao.get(TSysDic.class, wflxid);
                if (null != dict) {
                    m.setIllegalTypeName(dict.getName());
                }
                this.dao.save(m);
            }
        }
    }
    public String getDisWfxw(String id, String childstyle) {
		JSONArray array = new JSONArray();
		JSONObject obj = null;
		int child=0;
		if(""!=childstyle){
			child=Integer.valueOf(childstyle)+1;
		}
		if(id!=null && !"".equals(id.trim())) {
			String hql = "from DiscretionaryPower t where t.pid=? ";
	        List<DiscretionaryPower> list = dao.find(hql, id);
	        try{
                for (DiscretionaryPower d : list) {
                	String title = d.getName();
                	String content = d.getContent();
                    obj = new JSONObject();
                    obj.put("ID", d.getId());
                    obj.put("LOGONAME", "");
                    obj.put("TITLE", title);
                    obj.put("DESC", content);
                    obj.put("SEQ","1");
                    obj.put("CHILDSTYLE",child);
                    array.put(obj);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
		} 
		else{
			String hql = "from DiscretionaryPower t where t.pid is null";
	        List<DiscretionaryPower> list = dao.find(hql);
	        try{
                for (DiscretionaryPower d : list) {
                	String title = d.getName();
                	String content = d.getContent();
                    obj = new JSONObject();
                    obj.put("ID", d.getId());
                    obj.put("LOGONAME", "");
                    obj.put("TITLE", title);
                    obj.put("DESC", content);
                    obj.put("SEQ","1");
                    obj.put("CHILDSTYLE",child);
                    array.put(obj);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
		}
		return array.toString();
	}

}
