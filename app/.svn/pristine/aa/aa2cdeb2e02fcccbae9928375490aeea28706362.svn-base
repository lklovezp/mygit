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
	 * 查询企业基本信息并封装成QyjbqkForm
	 * */
	public QyjbqkForm queryQyjbqkForm(TDataLawobjf lawobjf,QyjbqkForm qyjbqkForm)throws Exception;
	/**
	 * 查询危化品基本信息并封装成TBizWxpjbxx
	 * */
    public TBizWxpjbxx quserTBizWxpjbxxById(String lawobjId,QyjbqkForm qyjbqkForm)throws Exception;
    /**
	 * 根据企业所属行政区域代码获得企业所属行政区域名称
	 * */
    public TDataRegion findTDataRegionByRegionId(String regionId)throws Exception;
    /**
	 * 根据行业ID查询行业信息
	 * */
    public TDataIndustry findIndustryById(String id)throws Exception;
    /**
   	 * 根据网格ID查询行业信息
   	 * */
       public TDataWg findWgById(String wgid)throws Exception;
    /**
     * 保存危化品企业基本信息
     * */   
    public void saveQyFrom(QyjbqkForm qyjbqkForm)throws Exception;
    /**
     * 保存危化品企业基本信息
     * */   
    public void delJbxx(String id)throws Exception;
    /**
     * 查询所有的危化品基本信息
     * */    
    public FyWebResult queryQyjbqkFormList(String lawobjId,String page,String pageSize)throws Exception;
}
