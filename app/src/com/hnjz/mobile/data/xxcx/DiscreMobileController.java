package com.hnjz.mobile.data.xxcx;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataDiscreacts;
import com.hnjz.app.data.po.TDataDiscrecaseclass;
import com.hnjz.app.data.po.TDataDiscremerit;
import com.hnjz.app.data.xxgl.zyclbz.DiscreManager;
import com.hnjz.sys.po.TSysDic;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-4-16
 * ����������
		���ɲ�����׼���Ʋ�(�ֳ��ն˿��Ʋ�)
 *
 */
@Controller
public class DiscreMobileController {

	/**��־*/
	private static final Log log = LogFactory.getLog(DiscreMobileController.class);

	@Autowired
	private DiscreManager discreManager;
	
	@Autowired
	private DiscreMobileManager discreMobileManager;

	/**
	 * 
	 * �������ܣ����в�����׼��ʼ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/zyclbzTree.mo")
	public void zyclbzTreeMobile(ModelMap model,HttpServletResponse response) {
		try {
			JSONArray array = discreMobileManager.getZyclbzTreeMobile();
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * �������ܣ����ɲ���Ȩ������
	
	 * ������������η���id
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/zyclqcInfo.mo")
	public void zyclqcInfo(ModelMap model,String level,String id,HttpServletResponse response){
		try {
			JSONObject jsonObj = new JSONObject();
			JSONArray array = new JSONArray();
			jsonObj.put("baseInfo", array);
			JSONObject obj = null;
			switch (Integer.parseInt(level)) {
				case 1:
					TSysDic dic = (TSysDic) discreMobileManager.get(TSysDic.class, id);
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "�ƶȷ��ࣺ");
					obj.put("value", dic.getName());
					break;
				case 2:
					TDataDiscreacts tDataDiscreacts = discreManager.getTDataDiscreactsInfo(id);
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "�ƶȷ��ࣺ");
					obj.put("value", tDataDiscreacts.getType().getName());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "Υ�����ͣ�");
					obj.put("value", tDataDiscreacts.getIllegaltype().getName());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "Υ����Ϊ��");
					obj.put("value", tDataDiscreacts.getContent());
					break;
				case 3:
					TDataDiscremerit tDataDiscremerit = discreManager.getTDataDiscremeritInfo(id);
					if(tDataDiscremerit!=null && tDataDiscremerit.gettDataDiscreacts()!=null && StringUtils.isNotBlank(tDataDiscremerit.gettDataDiscreacts().getId())){
						tDataDiscremerit.settDataDiscreacts(discreManager.getTDataDiscreactsInfo(tDataDiscremerit.gettDataDiscreacts().getId()));
					}
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "�ƶȷ��ࣺ");
					obj.put("value", tDataDiscremerit.gettDataDiscreacts().getType().getName());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "Υ����Ϊ��");
					obj.put("value", tDataDiscremerit.gettDataDiscreacts().getContent());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "�������ݱ�����");
					obj.put("value", tDataDiscremerit.getAlias());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "�������ݣ�");
					obj.put("value", tDataDiscremerit.getContent());
					break;
				case 4:
					TDataDiscrecaseclass tDataDiscrecaseclass = discreManager.getTDataDiscrecaseclassInfo(id);
					if(tDataDiscrecaseclass!=null && tDataDiscrecaseclass.gettDataDiscremerit()!=null && StringUtils.isNotBlank(tDataDiscrecaseclass.gettDataDiscremerit().getId())){
						tDataDiscrecaseclass.settDataDiscremerit(discreManager.getTDataDiscremeritInfo(tDataDiscrecaseclass.gettDataDiscremerit().getId()));
					}
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "�ƶȷ��ࣺ");
					obj.put("value", tDataDiscrecaseclass.gettDataDiscremerit().gettDataDiscreacts().getType().getName());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "Υ����Ϊ��");
					obj.put("value", tDataDiscrecaseclass.gettDataDiscremerit().gettDataDiscreacts().getContent());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "�������ݣ�");
					obj.put("value", tDataDiscrecaseclass.gettDataDiscremerit().getContent());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "���η��ࣺ");
					obj.put("value", tDataDiscrecaseclass.getName());
					
					jsonObj.put("dataList", tDataDiscrecaseclass.getContent());
					break;
				default:
					break;
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(jsonObj.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}