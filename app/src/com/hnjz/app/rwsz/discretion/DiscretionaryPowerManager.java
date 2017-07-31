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
 * ���ɲ���Ȩ
 * 
 * @author xuguanghui
 * @version $Id: DiscretionaryPowerManager.java, v 0.1 Aug 6, 2013 2:01:20 PM Administrator Exp $
 */
@Service("discretionaryPowerManager")
public class DiscretionaryPowerManager extends ManagerImpl {
    
    /**��־*/
    private static final Log log = LogFactory.getLog(DiscretionaryPowerManager.class);

    /**
     * ��ѯȫ��
     * 
     * @return
     */
    public List<DiscretionaryPower> queryAllList() {
        String hsql = " from DiscretionaryPower ";
        List<DiscretionaryPower> list = dao.find(hsql);
        return list;
    }

    /**
     * ��ѯȫ��
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
     * ��ѯȫ��
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
     * ����
     * 
     * @param po
     */
    public void save(DiscretionaryPower po) {
        dao.save(po);
    }

    /**
     * ɾ��
     * 
     * @param id
     */
    public void remove(String id) {
        dao.remove(DiscretionaryPower.class, id);
    }

    /**
     * ����idȡ���ӽڵ㼯��
     * 
     * @return
     */
    public List<DiscretionaryPower> getChildNodesById(String id) {
        String hsql = " from DiscretionaryPower t where t.pid = ?";
        List<DiscretionaryPower> list = dao.find(hsql, id);
        return list;
    }

    /**
     * ����id����
     * 
     * @param id
     * @return
     */
    public DiscretionaryPower getDiscretionById(String id) {
        return (DiscretionaryPower) dao.get(DiscretionaryPower.class, id);
    }

    /**
     * �������еĸ��ڵ�
     * 
     * @param id ���ɲ�����¼Id
     * @return ���еĸ��ڵ�ļ���
     */
    public List<DiscretionaryPower> getParents(String id) {
        List<DiscretionaryPower> re = new ArrayList<DiscretionaryPower>();
        this.getPars(id, re);
        Collections.reverse(re);
        return re;
    }

    /**
     * �ݹ���ҵ�ǰ���ɲ��������и��ڵ�
     * 
     * @param pid ��id
     * @param re ����
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
    				obj.put("zdfl",dis.getName());//�ƶȷ���
    			}
    			else if(String.valueOf(DiscreCodeEnum.WFXW.getCode()).equals(dis.getCode())){
    				obj.put("wfxw",dis.getName());//Υ����Ϊ
    			}
    			else if(String.valueOf(DiscreCodeEnum.FLYJ.getCode()).equals(dis.getCode())){
    				obj.put("flyj",dis.getName());//��������
    			}
    			else if(String.valueOf(DiscreCodeEnum.QXFL.getCode()).equals(dis.getCode())){
    				obj.put("qxfl",dis.getName());//���η���
    			}
    			obj.put("wflx",dis.getIllegalTypeName());//Υ������
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
     * ����Υ������id���ҷ�������
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