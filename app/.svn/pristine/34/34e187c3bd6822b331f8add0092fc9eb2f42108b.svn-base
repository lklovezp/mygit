package com.hnjz.app.clent;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.clent.po.TClient;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;

@Service(value = "clientManager")
public class TClientManagerImpl {

    @Autowired
    private TClientDaoImpl clientDao;

    /**
     * 查询对象
     * @param id
     * @return
     */
    public TClient get(String id) {
        return clientDao.get(id);
    }

    /**
     * 客户端集合
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<TClient> getList(int pageNo, int pageSize) {
        List<Criterion> cList = new ArrayList<Criterion>();
        cList.add(Restrictions.eq("isActive", "Y"));
        Map<String, Boolean> orderMap = new HashMap<String, Boolean>();
        orderMap.put("createTime", false);
        return clientDao.findByCondition(pageNo, pageSize, cList, orderMap);
    }

    /**
     * 删除
     * @param id
     * @throws Exception
     */
    public void remove(String id) throws Exception {
        if (get(id) != null) {
            clientDao.remove(TClient.class, id);
        }
    }

    /**
     * 保存
     * @param id
     * @param pzbb
     * @param bbh
     * @param name
     * @param desc
     * @param suffix
     * @param isforce
     * @param file
     * @throws Exception
     */
    public void save(String id, String pzbb, Integer bbh, String name, String desc, String suffix,
                     Boolean isforce, Boolean isActive, MultipartFile file) throws Exception {
        TClient m = null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        if (StringUtils.isNotBlank(id)) {
            m = get(id);
            m.setUpdateUser(CtxUtil.getCurUser());
            m.setUpdateTime(c.getTime());
        } else {
            m = new TClient();
            m.setCreateUser(CtxUtil.getCurUser());
            m.setCreateTime(c.getTime());
        }
        m.setPzbb(pzbb);
        m.setBbh(bbh);
        m.setDesc(desc);
        m.setIsforce(isforce);
        m.setIsActive(isActive ? "Y" : "N");

        if (file != null && !file.isEmpty()) {
            if (StringUtils.isNotBlank(m.getUrl())) {
                File oldFile = new File(m.getUrl());
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }
            m.setName(name);
            m.setSuffix(suffix);
            m.setSize(file.getSize());
            m.setContentType(file.getContentType());
            //上传至服务器的文件名使用日期生成
            String dateFileName = sdf.format(Calendar.getInstance().getTime())
                                  + (100 + new Random().nextInt(900));
            String filePath = FileUpDownUtil.copyFile(file.getInputStream(), dateFileName,
                UploadFileType.CLIENT.getPath());
            m.setUrl(filePath);
        }
        clientDao.save(m);
    }

    /**
     * 查询最新客户端（客户端更新）
     * @return
     * @throws Exception
     */
    public Map<String, Object> getNewestVersion() throws Exception {
        List<Criterion> cList = new ArrayList<Criterion>();
        cList.add(Restrictions.eq("isActive", "Y"));
        Map<String, Boolean> orderMap = new HashMap<String, Boolean>();
        orderMap.put("createTime", false);
        List<TClient> list = clientDao.findByCondition(1, 1, cList, orderMap);
        Map<String, Object> map = new HashMap<String, Object>();
        if (list.size() > 0) {
            map = list.get(0).toMobileMap();
        }
        return map;
    }
    
    /**
	 * 查询最新手机客户端（客户端更新）
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getNewestMoblieVersion() throws Exception {
	    List<Criterion> cList = new ArrayList<Criterion>();
	    cList.add(Restrictions.eq("isActive", "Y"));
	    Map<String, Boolean> orderMap = new HashMap<String, Boolean>();
	    orderMap.put("createTime", false);
	    List<TClient> list = clientDao.findByCondition(1, 1, cList, orderMap);
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (list.size() > 0) {
	        map = list.get(0).toMobileMap();
	    }
	    return map;
	}
}
