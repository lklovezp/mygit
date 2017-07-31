package com.hnjz.app.clent;

import org.springframework.stereotype.Repository;

import com.hnjz.app.clent.po.TClient;
import com.hnjz.wf.dao.MyDao;

@Repository(value = "clientDao")
public class TClientDaoImpl extends MyDao<TClient, String> {

}
