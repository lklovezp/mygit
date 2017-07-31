/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.work.xfdj;

import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.hnjz.app.work.po.TBizXfdj;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.zx.XfbjdForm;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * �ŷõǼ���
 * @author shiqiuhan
 * @created 2016-3-31,����08:52:34
 */
public interface XfdjManager extends Manager {

	/**
	 * ��ѯ�ŷõǼ�
	 * @param xfly
	 * @param xfsj1
	 * @param xfsj2
	 * @param zt
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public FyWebResult queryXfdj(String xfly, String xfsj1, String xfsj2, String zt, String page, String pageSize)
			throws Exception;

	/**
	 * �����ŷõǼ�
	 * @param frm
	 * @throws AppException
	 */
	@Transactional(readOnly = false)
	public TBizXfdj saveXfdj(XfdjForm frm) throws AppException;

	/**
	 * ɾ���ŷõǼ�
	 * @param id
	 * @throws AppException
	 */
	public void removeXfdj(String id) throws AppException;
	
	/**
	 * ��ѯ������ŷõǼǱ���Ŀ
	 * @return
	 */
	public int queryNumByYear();
	
	/**
	 * ��ѯ���·��ŷõǼǱ���Ŀ
	 * @return
	 */
	public int queryNumByMonth();
	
	/**
	 * ��ӡ�ǼǱ�
	 * @param xfly
	 * @param xfbh
	 * @param wrlx
	 * @param wrlxqt
	 * @param xfsj
	 * @param xfr
	 * @param lxdh
	 * @param lwmc
	 * @param bjsx
	 * @param xfnr
	 * @param jlr
	 * @param jlsj
	 * @param res
	 */
	public void exportXfdjb(HttpServletResponse response,String xfdjId);
	
	/**
	 * ��ӡ��ᵥ
	 * @param response
	 * @param xfdjId
	 */
	public void exportXfbjd(HttpServletResponse response,String xfdjId, String rwid);
	
	/**
	 * ��ѯ��ᵥ
	 * @param response
	 * @param xfdjId
	 */
	public XfbjdForm queryXfbjd(String xfdjId);
	
	/**
	 * �����ŷõǼǱ�id��ȡ�ŷð�ᵥ
	 * @param xfdjId
	 * @return
	 */
	public XfbjdForm getXfbjdformByxfdjId(String xfdjId);
	
	/**
	 * �����ŷð�ᵥ
	 */
	public void saveHfInfo(XfbjdForm xfbjdForm,String xfdjId);
	
	/**
	 * �Ƿ���ʾ�ط���Ϣ
	 * @param xfdjId
	 * @return
	 */
	public boolean isShowHf(String xfdjId);
	
	/**
	 * �����ŷõǼǱ���ȡwork����Ϣ
	 * @param xfdjId
	 * @return
	 */
	public Work getWorkById(String xfdjId, String biaoshi);
	
	/**
	 * ����work����Ϣ��ȡ���ɵ�taskId
	 * @param xfdjId
	 * @return
	 */
	public String gettaskId(String processId);
	
	/**
	 * �Ƿ���Խ����ɷ������޸Ĳ���
	 * @param xfdjId
	 * @return
	 */
	public boolean isShowXf(String xfdjId);
}