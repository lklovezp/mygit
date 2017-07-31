package com.hnjz.app.data.xxgl.zyclbz;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataDiscreacts;
import com.hnjz.app.data.po.TDataDiscrecaseclass;
import com.hnjz.app.data.po.TDataDiscremerit;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-9
 * ����������
		���ɲ�����׼���Ʋ�
 *
 */
@Controller
public class DiscreController {

	/**��־*/
	private static final Log log = LogFactory.getLog(DiscreController.class);

	@Autowired
	private DiscreManager discreManager;

	/**
	 * 
	 * �������ܣ����в�����׼��ʼ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/zyclbzPage.htm")
	public String zyclbzPage(ModelMap model,String title) {
		model.put("title", title);
		JSONArray array = discreManager.getZyclbzTree();
		model.put("array", array);
		return "app/data/xxgl/zyclbz/zyclbzPage";
	}
	
	/**
	 * 
	 * �������ܣ����в�����׼��ʼ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/xxcx_zyclbzPage.htm")
	public String xxcx_zyclbzPage(ModelMap model,String title) {
		model.put("title", title);
		JSONArray array = discreManager.getZyclbzTree();
		model.put("array", array);
		return "app/data/xxcx/zyclbz/zyclbzPage";
	}

	/**
	* 
	* �������ܣ�Υ����Ϊ�б�
	
	* ���������id:�ƶȷ���id
	
	* ����ֵ��
	*/
	@RequestMapping(value = "/wfxwList.htm")
	public String wfxwList(ModelMap model,String id) {
		if(id!=null){
			model.put("zdfl", discreManager.zdflInfo(id));
		}
		return "app/data/xxgl/zyclbz/wfxwList";
	}
	/**
	 * 
	 * �������ܣ�Υ����Ϊ�б�
	
	 * ���������id:�ƶȷ���id
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/xxcx_wfxwList.htm")
	public String xxcx_wfxwList(ModelMap model,String id) {
		if(id!=null){
			model.put("zdfl", discreManager.zdflInfo(id));
		}
		return "app/data/xxcx/zyclbz/wfxwList";
	}
	
	/**
	 * 
	 * �������ܣ�Υ����Ϊ�༭����
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/wfxwEdit.htm")
	public String wfxwEdit(ModelMap model,TDataDiscreacts tDataDiscreacts) {
		if(tDataDiscreacts!=null && tDataDiscreacts.getId()!=null){
			tDataDiscreacts = discreManager.getTDataDiscreactsInfo(tDataDiscreacts.getId());
		}
		model.put("tDataDiscreacts", tDataDiscreacts);
		return "app/data/xxgl/zyclbz/wfxwEdit";
	}
	/**
	 * 
	 * �������ܣ�Υ����Ϊ�༭����
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/wfxwInfo.htm")
	public String wfxwInfo(ModelMap model,TDataDiscreacts tDataDiscreacts) {
		if(tDataDiscreacts!=null && tDataDiscreacts.getId()!=null){
			tDataDiscreacts = discreManager.getTDataDiscreactsInfo(tDataDiscreacts.getId());
		}
		model.put("tDataDiscreacts", tDataDiscreacts);
		return "app/data/xxgl/zyclbz/wfxwInfo";
	}
	
	/**
	 * 
	 * �������ܣ�����Υ����Ϊ
	
	 * ���������id:�ƶȷ���id
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/saveOrUpdateWfxw.htm")
	public String saveOrUpdateWfxw(ModelMap model,TDataDiscreacts tDataDiscreacts) {
		try {
			discreManager.saveOrUpdateWfxw(tDataDiscreacts);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("����Υ����Ϊ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
		return "app/data/xxgl/zyclbz/wfxwEdit";
	}
	
	/**
	 * 
	 * �������ܣ�ɾ��Υ����Ϊ
	
	 * ���������id:Υ����Ϊid
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/deleteWfxw.json")
	public void deleteWfxw(ModelMap model,String id){
		try {
			discreManager.removeWfxw(id);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ɾ��Υ����Ϊ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	 /**
     * �����б����� Υ������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryWfxwList.json")
    public void queryWfxwList(ModelMap model,String pid, String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = discreManager.queryWflxList(pid, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    
    
    
    
    
    
    
    
    /**
	* 
	* �������ܣ����������б�
	
	* ���������id:�ƶȷ���id
	
	* ����ֵ��
	*/
	@RequestMapping(value = "/flyjList.htm")
	public String flyjList(ModelMap model,String id) {
		if(id!=null){
			model.put("wfxw", discreManager.getTDataDiscreactsInfo(id));
		}
		return "app/data/xxgl/zyclbz/flyjList";
	}
	/**
	 * 
	 * �������ܣ����������б�
	
	 * ���������id:�ƶȷ���id
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/xxcx_flyjList.htm")
	public String xxcx_flyjList(ModelMap model,String id) {
		if(id!=null){
			model.put("wfxw", discreManager.getTDataDiscreactsInfo(id));
		}
		return "app/data/xxcx/zyclbz/flyjList";
	}
	
	/**
	 * 
	 * �������ܣ��������ݱ༭����
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/flyjEdit.htm")
	public String flyjEdit(ModelMap model,TDataDiscremerit tDataDiscremerit) {
		try {
			if(tDataDiscremerit!=null && tDataDiscremerit.getId()!=null){
				tDataDiscremerit = discreManager.getTDataDiscremeritInfo(tDataDiscremerit.getId());
			}
			if(tDataDiscremerit!=null && tDataDiscremerit.gettDataDiscreacts()!=null && StringUtils.isNotBlank(tDataDiscremerit.gettDataDiscreacts().getId())){
				tDataDiscremerit.settDataDiscreacts(discreManager.getTDataDiscreactsInfo(tDataDiscremerit.gettDataDiscreacts().getId()));
			}
			model.put("tDataDiscremerit", tDataDiscremerit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/xxgl/zyclbz/flyjEdit";
	}
	
	/**
	 * 
	 * �������ܣ��������ݱ༭����
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/flyjInfo.htm")
	public String flyjInfo(ModelMap model,TDataDiscremerit tDataDiscremerit) {
		try {
			if(tDataDiscremerit!=null && tDataDiscremerit.getId()!=null){
				tDataDiscremerit = discreManager.getTDataDiscremeritInfo(tDataDiscremerit.getId());
			}
			if(tDataDiscremerit!=null && tDataDiscremerit.gettDataDiscreacts()!=null && StringUtils.isNotBlank(tDataDiscremerit.gettDataDiscreacts().getId())){
				tDataDiscremerit.settDataDiscreacts(discreManager.getTDataDiscreactsInfo(tDataDiscremerit.gettDataDiscreacts().getId()));
			}
			model.put("tDataDiscremerit", tDataDiscremerit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/xxgl/zyclbz/flyjInfo";
	}
	
	/**
	 * 
	 * �������ܣ����·�������
	
	 * ���������id:�ƶȷ���id
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/saveOrUpdateFlyj.htm")
	public String saveOrUpdateFlyj(ModelMap model,TDataDiscremerit tDataDiscremerit) {
		try {
			discreManager.saveOrUpdateFlyj(tDataDiscremerit);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("���淨�����ݴ���", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
		return "app/data/xxgl/zyclbz/flyjEdit";
	}
	
	/**
	 * 
	 * �������ܣ�ɾ����������
	
	 * ���������id:��������id
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/deleteFlyj.json")
	public void deleteFlyj(ModelMap model,String id){
		try {
			discreManager.removeFlyj(id);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ɾ���������ݴ���", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	 /**
     * �����б����� ��������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryFlyjList.json")
    public void queryFlyjList(ModelMap model,String pid, String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = discreManager.queryFlyjList(pid, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    
    
    
    
    
    
    
    
    
    /**
	* 
	* �������ܣ����η����б�
	
	* ���������id:��������id
	
	* ����ֵ��
	*/
	@RequestMapping(value = "/qxflList.htm")
	public String qxflList(ModelMap model,String id) {
		if(id!=null){
			model.put("flyj", discreManager.getTDataDiscremeritInfo(id));
		}
		return "app/data/xxgl/zyclbz/qxflList";
	}
	/**
	 * 
	 * �������ܣ����η����б�
	
	 * ���������id:��������id
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/xxcx_qxflList.htm")
	public String xxcx_qxflList(ModelMap model,String id) {
		if(id!=null){
			model.put("flyj", discreManager.getTDataDiscremeritInfo(id));
		}
		return "app/data/xxcx/zyclbz/qxflList";
	}
	
	/**
	 * 
	 * �������ܣ����η���༭����
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/qxflEdit.htm")
	public String qxflEdit(ModelMap model,TDataDiscrecaseclass tDataDiscrecaseclass) {
		if(tDataDiscrecaseclass!=null && tDataDiscrecaseclass.getId()!=null){
			tDataDiscrecaseclass = discreManager.getTDataDiscrecaseclassInfo(tDataDiscrecaseclass.getId());
		}
		if(tDataDiscrecaseclass!=null && tDataDiscrecaseclass.gettDataDiscremerit()!=null && StringUtils.isNotBlank(tDataDiscrecaseclass.gettDataDiscremerit().getId())){
			tDataDiscrecaseclass.settDataDiscremerit(discreManager.getTDataDiscremeritInfo(tDataDiscrecaseclass.gettDataDiscremerit().getId()));
		}
		model.put("tDataDiscrecaseclass", tDataDiscrecaseclass);
		return "app/data/xxgl/zyclbz/qxflEdit";
	}
	
	/**
	 * 
	 * �������ܣ��������η���
	
	 * ���������id:�ƶȷ���id
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/saveOrUpdateQxfl.htm")
	public String saveOrUpdateQxfl(ModelMap model,TDataDiscrecaseclass tDataDiscrecaseclass) {
		try {
			discreManager.saveOrUpdateQxfl(tDataDiscrecaseclass);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("�������η������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
		return "app/data/xxgl/zyclbz/qxflEdit";
	}
	
	
	/**
	 * 
	 * �������ܣ�ɾ�����η���
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/deleteQxfl.json")
	public void deleteQxfl(ModelMap model,String id){
		try {
			discreManager.removeQxfl(id);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ɾ�����η������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	 /**
     * �����б����� ���η���
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryQxflList.json")
    public void queryQxflList(ModelMap model,String pid, String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = discreManager.queryQxflList(pid, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * �����б����� ���η���
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/qxflInfo.htm")
    public String qxflInfo(ModelMap model,String id){
    	TDataDiscrecaseclass tDataDiscrecaseclass = discreManager.getTDataDiscrecaseclassInfo(id);
    	if(tDataDiscrecaseclass!=null && tDataDiscrecaseclass.gettDataDiscremerit()!=null && StringUtils.isNotBlank(tDataDiscrecaseclass.gettDataDiscremerit().getId())){
			tDataDiscrecaseclass.settDataDiscremerit(discreManager.getTDataDiscremeritInfo(tDataDiscrecaseclass.gettDataDiscremerit().getId()));
		}
    	model.put("tDataDiscrecaseclass", tDataDiscrecaseclass);
    	return "app/data/xxgl/zyclbz/qxflInfo";
    }

    
    /**
	* 
	* �������ܣ����η����б�
	
	* ���������id:��������id
	
	* ����ֵ��
	*/
	@RequestMapping(value = "/wflx_qxflList.htm")
	public String wflx_qxflList(ModelMap model,String wflxId) {
		JSONArray array = discreManager.getZyclbzTreeByWflxId(wflxId);
		model.put("array", array);
		model.put("title", "��ط�������");
		return "app/data/xxcx/zyclbz/zyclbzPage";
	}
	
	/**
     * ����Υ�����Ͳ�ѯ���η����б�
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryQxflListBywflx.json")
    public void queryQxflListByWflx(ModelMap model,String wflxId, String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = discreManager.queryQxflListByWflx(wflxId, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
}