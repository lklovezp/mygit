package com.hnjz.common.manager;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.QueryCondition;

public interface ManagerA {
	
	FyWebResult queryTdatalawobjtype(String strWhere, QueryCondition data, String page,String pageSize);

}
