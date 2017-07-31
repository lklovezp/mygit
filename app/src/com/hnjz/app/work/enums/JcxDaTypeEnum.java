package com.hnjz.app.work.enums;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 检查单答案
 * @author zn
 * @version $Id: JcxDaTypeEnum.java, v 0.1 2013-3-13 上午04:59:15 zn Exp $
 */
public enum JcxDaTypeEnum {
    RAD("rad", "单选", 1, false), CHK("chk", "多选", 2, false), TXT("txt", "文本", 3, true);
    private String  code;
    private String  text;
    private Integer order;
    private Boolean selected;

    private JcxDaTypeEnum(String code, String text, Integer order, Boolean selected) {
        this.code = code;
        this.text = text;
        this.order = order;
        this.selected = selected;
    }

    /**
     * JSON集合
     * @return
     * @throws JSONException
     */
    public static JSONArray getJsonArr() throws JSONException {
        JSONArray jsonArr = new JSONArray();
        JSONObject jsonObj = null;
        for (JcxDaTypeEnum e : values()) {
            jsonObj = new JSONObject();
            jsonObj.put("code", e.getCode());
            jsonObj.put("text", e.getText());
            jsonObj.put("selected", e.getSelected());
            jsonArr.put(jsonObj);
        }
        return jsonArr;
    }

    /**
     * 根据编码得到类型实例
     * @param code
     * @return
     */
    public static JcxDaTypeEnum getByCode(String code) {
        JcxDaTypeEnum e = null;
        for (JcxDaTypeEnum da : values()) {
            if (da.getCode().equals(code)) {
                e = da;
                break;
            }
        }
        return e;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
