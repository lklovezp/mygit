/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.hjgl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * �ۼ�������ѯ��Controller
 * 
 * @author wumi
 * @version $Id: HjglController.java, v 0.1 Apr 7, 2013 5:52:13 PM wumi Exp $
 */
@Controller
public class HjglController {

    @Autowired
    private HjglManager      hjglManager;

    /**
     * �鿴����ĺۼ���Ϣ
     * 
     * @param model {@link ModelMap}
     * @param id ����Id
     * @return �鿴����ĺۼ���Ϣ
     */
    @RequestMapping(value = "/rwhjList.htm")
    public String rwhjList(ModelMap model, String id) {
        List<Vhjgl> re = hjglManager.getRwhj(id);
        model.addAttribute("re", re);
        return "app/work/hjgl/rwhjList";
    }
}