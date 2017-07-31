package com.hnjz.app.work.danger;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

public interface HjfxffcsManager extends Manager{
	/**
     * 保存环境防范措施
     * 
     * @param 
     * */
	public void saveHjfxffcsForm(HjfxffcsForm hjfxffcsForm)throws AppException;
	
	public FyWebResult hjfxffcsList(String pid,String isActive,String page, String pageSize) throws AppException;
	
	public HjfxffcsForm infoHjfxffcsForm(HjfxffcsForm hjfxffcsForm)throws AppException;
	public void removeHjfxffcs(String id) throws AppException;
	public HjfxffcsForm editHjfxffcsForm(HjfxffcsForm hjfxffcsForm)throws AppException;


}
