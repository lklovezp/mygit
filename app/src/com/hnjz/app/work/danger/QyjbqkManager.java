package com.hnjz.app.work.danger;

import com.hnjz.app.data.po.TBizWxpjbxx;
import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataRegion;
import com.hnjz.app.data.po.TDataWg;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

public interface QyjbqkManager  extends Manager{
	/**
	 * ��ѯ��ҵ������Ϣ����װ��QyjbqkForm
	 * */
	public QyjbqkForm queryQyjbqkForm(TDataLawobjf lawobjf,QyjbqkForm qyjbqkForm)throws Exception;
	/**
	 * ��ѯΣ��Ʒ������Ϣ����װ��TBizWxpjbxx
	 * */
    public TBizWxpjbxx quserTBizWxpjbxxById(String lawobjId,QyjbqkForm qyjbqkForm)throws Exception;
    /**
	 * ������ҵ�������������������ҵ����������������
	 * */
    public TDataRegion findTDataRegionByRegionId(String regionId)throws Exception;
    /**
	 * ������ҵID��ѯ��ҵ��Ϣ
	 * */
    public TDataIndustry findIndustryById(String id)throws Exception;
    /**
   	 * ��������ID��ѯ��ҵ��Ϣ
   	 * */
       public TDataWg findWgById(String wgid)throws Exception;
    /**
     * ����Σ��Ʒ��ҵ������Ϣ
     * */   
    public void saveQyFrom(QyjbqkForm qyjbqkForm)throws Exception;
    /**
     * ����Σ��Ʒ��ҵ������Ϣ
     * */   
    public void delJbxx(String id)throws Exception;
    /**
     * ��ѯ���е�Σ��Ʒ������Ϣ
     * */    
    public FyWebResult queryQyjbqkFormList(String lawobjId,String page,String pageSize)throws Exception;
}