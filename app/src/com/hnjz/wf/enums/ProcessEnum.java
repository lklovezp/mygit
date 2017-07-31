package com.hnjz.wf.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hnjz.app.work.po.Work;
import com.hnjz.common.domain.Combobox;
import com.hnjz.wf.ApplyCommonPo;

/**
 * ��������̣����㷢������������
 * 
 * @author zn
 * @version $Id: ProcessEnum.java, v 0.1 2013-1-14 ����02:31:44 Administrator Exp
 *          $
 */
public enum ProcessEnum {
    /**
    DEMO("����", "demo", "demo.png", "com/hnjz/wf/demo/demo.jpdl.xml", "com/hnjz/wf/demo/demo.zip",
         "wf/demo/new", "wf/demo/info", ApplyDemo.class), */
    /** һ������ */
    GENERAL_TASK("����", "general_task", "generalTask.png",
                 "com/hnjz/app/work/wf/general/generalTask.jpdl.xml",
                 "com/hnjz/app/work/wf/general/generalTask.zip", "", "", Work.class),
    /** �ϱ����� */
    REPORT_TASK("�ϱ�����", "report_task", "report_task.png",
                "com/hnjz/app/work/wf/report/jpdl/report_task.jpdl.xml",
                "com/hnjz/app/work/wf/report/jpdl/report_task.zip", "", "", Work.class),
    
    /** ר�������� */
    ZXZRW_TASK("ר��������", "zxzrw_task", "zxzrw_task.png",
                "com/hnjz/app/work/wf/zxzrw/zxzrw_task.jpdl.xml",
                "com/hnjz/app/work/wf/zxzrw/zxzrw_task.zip", "", "", Work.class);

    /** �������� */
    private String                         processName;
    /** ����KEYֵ */
    private String                         processKey;
    /** ����ͼ���� */
    private String                         processImgName;
    /** JPDL·�� */
    private String                         jpdlPath;
    /** JPDL zip�ļ�·�� */
    private String                         jpdlZipPath;
    /** �½����� */
    private String                         newMethod;
    /** �½����� */
    private String                         infoMethod;
    /** �������� */
    private Class<? extends ApplyCommonPo> applyPo;

    private ProcessEnum(String processName, String processKey, String processImgName,
                        String jpdlPath, String jpdlZipPath, String newMethod, String infoMethod,
                        Class<? extends ApplyCommonPo> applyPo) {
        this.processName = processName;
        this.processKey = processKey;
        this.processImgName = processImgName;
        this.jpdlPath = jpdlPath;
        this.jpdlZipPath = jpdlZipPath;
        this.newMethod = newMethod;
        this.infoMethod = infoMethod;
        this.applyPo = applyPo;
    }

    public Map<String, Object> toPesMap() {
        // <a href="javascript:deployProcess('${pe.processKey }')">��������</a>
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer str = new StringBuffer();
        str.append("<a href=\"#\" onclick=\"deployProcess('").append(getProcessKey()).append(
            "')\">").append("��������").append("</a>&nbsp;");
        map.put("processName", getProcessName());
        map.put("processKey", getProcessKey());
        map.put("nextActions", str.toString());
        return map;
    }

    /**
     * ����KEYֵ���Ҷ���
     * 
     * @param key
     * @return
     */
    public static ProcessEnum getByKey(String key) {
        ProcessEnum processEnum = null;
        for (ProcessEnum pe : values()) {
            if (key.equals(pe.getProcessKey())) {
                processEnum = pe;
                break;
            }
        }
        return processEnum;
    }
    public static List<Combobox> getTypes() {
        List<Combobox> re = new ArrayList<Combobox>();
        for (ProcessEnum ele : values()) {
            re.add(new Combobox(String.valueOf(ele.getProcessKey()), ele.getProcessName()));
        }
        return re;
    }
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }

    public String getJpdlPath() {
        return jpdlPath;
    }

    public void setJpdlPath(String jpdlPath) {
        this.jpdlPath = jpdlPath;
    }

    public String getNewMethod() {
        return newMethod;
    }

    public void setNewMethod(String newMethod) {
        this.newMethod = newMethod;
    }

    public Class<? extends ApplyCommonPo> getApplyPo() {
        return applyPo;
    }

    public void setApplyPo(Class<? extends ApplyCommonPo> applyPo) {
        this.applyPo = applyPo;
    }

    public String getInfoMethod() {
        return infoMethod;
    }

    public void setInfoMethod(String infoMethod) {
        this.infoMethod = infoMethod;
    }

    public String getJpdlZipPath() {
        return jpdlZipPath;
    }

    public void setJpdlZipPath(String jpdlZipPath) {
        this.jpdlZipPath = jpdlZipPath;
    }

    public String getProcessImgName() {
        return processImgName;
    }

    public void setProcessImgName(String processImgName) {
        this.processImgName = processImgName;
    }

}