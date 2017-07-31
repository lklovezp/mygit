package com.hnjz.app.work.danger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataQyhjyjczjjyzy;
import com.hnjz.app.data.po.TDataQyhxwzqkzycp;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;

@Service("qyhjyjczjjyzyManagerImpl")
public class QyhjyjczjjyzyManagerImpl extends ManagerImpl implements QyhjyjczjjyzyManager{
      private static final Log log=LogFactory.getLog(QyhjyjczjjyzyManagerImpl.class);

	@Override
	public List<Combobox> queryYjczTypeList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("1","���˷���װ������"));
		listResult.add(new Combobox("2","������ʩ"));
		listResult.add(new Combobox("3","��©���ռ�����/�豸"));
		listResult.add(new Combobox("4","Ӧ������豸"));
		listResult.add(new Combobox("5","Ӧ����Ԯ����"));
		listResult.add(new Combobox("6","����"));
		return listResult;
	}

	@Override
	public void saveQyhjyjczjjyzyForm(QyhjyjczjjyzyForm qyhjyjczjjyzyForm)
			throws AppException {
		if(StringUtils.isNotBlank(qyhjyjczjjyzyForm.getId())){
			TDataQyhjyjczjjyzy tq=(TDataQyhjyjczjjyzy) this.get(TDataQyhjyjczjjyzy.class, qyhjyjczjjyzyForm.getId());
			tq.setAreaId(CtxUtil.getAreaId());
			tq.setSxsl(qyhjyjczjjyzyForm.getSxsl());
			tq.setType(qyhjyjczjjyzyForm.getType());
			tq.setCreater(CtxUtil.getCurUser());
			tq.setCreateTime(new Date());
			tq.setWbdh(qyhjyjczjjyzyForm.getWbdh());
			tq.setWbxm(qyhjyjczjjyzyForm.getWbxm());
			tq.setIsActive("Y");
			tq.setPid(qyhjyjczjjyzyForm.getPid());
			tq.setWzmc(qyhjyjczjjyzyForm.getWzmc());
			tq.setXysl(qyhjyjczjjyzyForm.getXysl());
			tq.setWbName(qyhjyjczjjyzyForm.getWbName());
			tq.setUpdateby(CtxUtil.getCurUser());
			tq.setUpdateTime(new Date());
			
			this.dao.save(tq);
		}else{
			TDataQyhjyjczjjyzy tq=new TDataQyhjyjczjjyzy();
			//tDataQyhxwzqkzycp.setId(qyhxwzqkzycpForm.getId());
			tq.setAreaId(CtxUtil.getAreaId());
			tq.setSxsl(qyhjyjczjjyzyForm.getSxsl());
			tq.setType(qyhjyjczjjyzyForm.getType());
			tq.setCreater(CtxUtil.getCurUser());
			tq.setCreateTime(new Date());
			tq.setWbdh(qyhjyjczjjyzyForm.getWbdh());
			tq.setWbxm(qyhjyjczjjyzyForm.getWbxm());
			tq.setIsActive("Y");
			tq.setWbName(qyhjyjczjjyzyForm.getWbName());
			tq.setPid(qyhjyjczjjyzyForm.getPid());
			tq.setWzmc(qyhjyjczjjyzyForm.getWzmc());
			tq.setXysl(qyhjyjczjjyzyForm.getXysl());
			
			tq.setUpdateby(CtxUtil.getCurUser());
			tq.setUpdateTime(new Date());
			
			this.dao.save(tq);
		
	}
	}

	@Override
	public FyWebResult qyhjyjczjjyzyList(String pid, String isActive,String page, String pageSize) throws AppException {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataQyhjyjczjjyzy t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" t.pid=:pid");
			data.put("pid", pid);
		}
		sql.append(" order by t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataQyhjyjczjjyzy> whyLists = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//��ǰ�û�id
		Map<String, String> dataObject = null;
		for (TDataQyhjyjczjjyzy tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("sxsl", String.valueOf(tq.getSxsl()));
			String typeName="";
			if("1".equals(tq.getType())){
				typeName="���˷���װ������";
			}else if("2".equals(tq.getType())){
				typeName="������ʩ";
			}else if("3".equals(tq.getType())){
				typeName="��©���ռ�����/�豸";
			}else if("4".equals(tq.getType())){
				typeName="Ӧ������豸";
			}else if("5".equals(tq.getType())){
				typeName="Ӧ����Ԯ����";
			}else{
				typeName="����";
			}
			dataObject.put("type", String.valueOf(typeName));
			dataObject.put("wbdh", String.valueOf(tq.getWbdh()));
			dataObject.put("wbName",tq.getWbName()==null?"":String.valueOf(tq.getWbName()));
			dataObject.put("wbxm", String.valueOf(tq.getWbxm()));
			dataObject.put("wzmc", tq.getWzmc()==null?"":String.valueOf(tq.getWzmc()));
			dataObject.put("xysl", String.valueOf(tq.getXysl()));
			dataObject.put("isActive", "Y".equals(String.valueOf(tq.getIsActive()))?"��Ч":"��Ч");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//�ǹ���Ա��ɫֻ�����޸ġ��鿴�������ӵ�ִ�������Ȩ��
			
				//�ǹ���Ա��ɫ�ԷǱ���������ҵֻ���в鿴Ȩ�ޣ�����Ա������ɾ�Ĳ�������ҵ��ͨ����̨Ȩ������
				dataObject.put("operate","<a id='"+tq.getId()+"' class='b-link' onclick='info(this)'>�鿴</a> <a id='"+tq.getId()+"' class='b-link' onclick='modify(this)'>�޸�</a> <a id='"+tq.getId()+"' class='b-link' onclick='del(this)'>ɾ��</a> ");
			
			
			rows.add(dataObject);
		}
		
		fy.setRows(rows);
		
		return fy;
	}

	@Override
	public QyhjyjczjjyzyForm editQyhjyjczjjyzyForm(QyhjyjczjjyzyForm qyhjyjczjjyzyForm) throws AppException {
		TDataQyhjyjczjjyzy tq=(TDataQyhjyjczjjyzy) this.get(TDataQyhjyjczjjyzy.class, qyhjyjczjjyzyForm.getId());
		qyhjyjczjjyzyForm.setAreaId(tq.getAreaId());
		qyhjyjczjjyzyForm.setSxsl(tq.getSxsl());
		qyhjyjczjjyzyForm.setType(tq.getType());
		qyhjyjczjjyzyForm.setCreater(tq.getCreater());
		qyhjyjczjjyzyForm.setCreateTime(tq.getCreateTime());
		qyhjyjczjjyzyForm.setWbdh(tq.getWbdh());
		qyhjyjczjjyzyForm.setWbxm(tq.getWbxm());
		qyhjyjczjjyzyForm.setIsActive(tq.getIsActive());
		qyhjyjczjjyzyForm.setPid(tq.getPid());
		qyhjyjczjjyzyForm.setWzmc(tq.getWzmc());
		qyhjyjczjjyzyForm.setXysl(tq.getXysl());
		qyhjyjczjjyzyForm.setWbName(tq.getWbName());
		qyhjyjczjjyzyForm.setUpdateby(tq.getUpdateby());
		qyhjyjczjjyzyForm.setUpdateTime(tq.getUpdateTime());
		
		return qyhjyjczjjyzyForm;
	}

	@Override
	public void removeQyhjyjczjjyzy(String id) throws AppException {
		TDataQyhjyjczjjyzy tq=(TDataQyhjyjczjjyzy) this.get(TDataQyhjyjczjjyzy.class, id);
		tq.setIsActive("N");
		tq.setUpdateby(CtxUtil.getCurUser());
		tq.setUpdateTime(new Date());
		
	}

	@Override
	public QyhjyjczjjyzyForm infoQyhjyjczjjyzyForm(QyhjyjczjjyzyForm qyhjyjczjjyzyForm) throws AppException {
		TDataQyhjyjczjjyzy tq=(TDataQyhjyjczjjyzy) this.get(TDataQyhjyjczjjyzy.class, qyhjyjczjjyzyForm.getId());
		qyhjyjczjjyzyForm.setAreaId(tq.getAreaId());
		qyhjyjczjjyzyForm.setSxsl(tq.getSxsl());
		String typeName="";
		if("1".equals(tq.getType())){
			typeName="���˷���װ������";
		}else if("2".equals(tq.getType())){
			typeName="������ʩ";
		}else if("3".equals(tq.getType())){
			typeName="��©���ռ�����/�豸";
		}else if("4".equals(tq.getType())){
			typeName="Ӧ������豸";
		}else if("5".equals(tq.getType())){
			typeName="Ӧ����Ԯ����";
		}else{
			typeName="����";
		}
		qyhjyjczjjyzyForm.setType(typeName);
		qyhjyjczjjyzyForm.setCreater(tq.getCreater());
		qyhjyjczjjyzyForm.setCreateTime(tq.getCreateTime());
		qyhjyjczjjyzyForm.setWbdh(tq.getWbdh());
		qyhjyjczjjyzyForm.setWbxm(tq.getWbxm());
		qyhjyjczjjyzyForm.setIsActive(tq.getIsActive());
		qyhjyjczjjyzyForm.setPid(tq.getPid());
		qyhjyjczjjyzyForm.setWzmc(tq.getWzmc());
		qyhjyjczjjyzyForm.setXysl(tq.getXysl());
		qyhjyjczjjyzyForm.setWbName(tq.getWbName());
		qyhjyjczjjyzyForm.setUpdateby(tq.getUpdateby());
		qyhjyjczjjyzyForm.setUpdateTime(tq.getUpdateTime());
		
		return qyhjyjczjjyzyForm;
	}

	@Override
	public FyWebResult qyhjyjczjjyzyListByTypeAndTime(String pid,
			String isActive, String page, String pageSize) throws AppException {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataQyhjyjczjjyzy t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" t.pid=:pid");
			data.put("pid", pid);
		}
		sql.append(" order by t.type asc,t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataQyhjyjczjjyzy> whyLists = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//��ǰ�û�id
		Map<String, String> dataObject = null;
		for (TDataQyhjyjczjjyzy tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("sxsl", String.valueOf(tq.getSxsl()));
			String typeName="";
			if("1".equals(tq.getType())){
				typeName="���˷���װ������";
			}else if("2".equals(tq.getType())){
				typeName="������ʩ";
			}else if("3".equals(tq.getType())){
				typeName="��©���ռ�����/�豸";
			}else if("4".equals(tq.getType())){
				typeName="Ӧ������豸";
			}else if("5".equals(tq.getType())){
				typeName="Ӧ����Ԯ����";
			}else{
				typeName="����";
			}
			dataObject.put("type", String.valueOf(typeName));
			dataObject.put("wbdh", String.valueOf(tq.getWbdh()));
			dataObject.put("wbName", String.valueOf(tq.getWbName()));
			dataObject.put("wbxm", String.valueOf(tq.getWbxm()));
			dataObject.put("wzmc", String.valueOf(tq.getWzmc()));
			dataObject.put("xysl", String.valueOf(tq.getXysl()));
			dataObject.put("isActive", "Y".equals(String.valueOf(tq.getIsActive()))?"��Ч":"��Ч");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//�ǹ���Ա��ɫֻ�����޸ġ��鿴�������ӵ�ִ�������Ȩ��
			
				//�ǹ���Ա��ɫ�ԷǱ���������ҵֻ���в鿴Ȩ�ޣ�����Ա������ɾ�Ĳ�������ҵ��ͨ����̨Ȩ������
				dataObject.put("operate","<a id='"+tq.getId()+"' class='b-link' onclick='info(this)'>�鿴</a> <a id='"+tq.getId()+"' class='b-link' onclick='modify(this)'>�޸�</a> <a id='"+tq.getId()+"' class='b-link' onclick='del(this)'>ɾ��</a> ");
			
			
			rows.add(dataObject);
		}
		
		fy.setRows(rows);
		
		return fy;
	}
}