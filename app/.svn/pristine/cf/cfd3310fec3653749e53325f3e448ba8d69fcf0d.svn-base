package com.hnjz.mobile.client;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.clent.TClientManagerImpl;
import com.hnjz.app.clent.po.TClient;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.wf.AbsJbpmController;

/**
 * 客户端更新
 * @author zn
 * @version $Id: MobileClientController.java, v 0.1 2013-5-13 上午06:43:28 zn Exp $
 */
@Controller
@RequestMapping(value = "mobile/client")
public class MobileClientController extends AbsJbpmController {

    @Autowired
    private TClientManagerImpl clientManager;

    /**
     * 得到最新版本
     * @return
     */
    @RequestMapping(value = "/get_newest.mo", produces = "application/json")
    @ResponseBody
    public Map<String, Object> getNewest() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = clientManager.getNewestVersion();
        } catch (Exception e) {
            log.error("", e);
        }
        return map;
    }
    
    /**
     * 得到手机端最新版本
     * @return
     */
    @RequestMapping(value = "/get_moblie_newest.mo", produces = "application/json")
    @ResponseBody
    public Map<String, Object> getMoblieNewest() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = clientManager.getNewestMoblieVersion();
        } catch (Exception e) {
            log.error("", e);
        }
        return map;
    }

    /**
     * 下载客户端
     * @param id
     * @param res
     * @param model
     */
    @RequestMapping(value = "/download.mo")
    public void download(String id, HttpServletResponse res, ModelMap model) {
        try {
            TClient m = clientManager.get(id);
            FileUpDownUtil.downloadFile(res, m.getUrl(), m.getName());
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
