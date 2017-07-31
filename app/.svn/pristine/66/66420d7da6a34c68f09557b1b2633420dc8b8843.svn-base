/**
 * 
 */
package com.hnjz.app.rwsz.discretion;

import java.util.ArrayList;
import java.util.List;

import com.hnjz.common.domain.Combobox;

/**
 * 自由裁量权枚举类
 * 
 * @author zhaomh
 * @version $Id: DiscreCodeEnum.java, v 0.1 2013-9-12 下午05:28:00 zhaomh Exp $
 */
public enum DiscreCodeEnum {
    //
    ZDFL(1, "制度分类", "制度分类名称", "制度分类内容"),
    //
    WFXW(2, "违法行为", "违法行为名称", "违法行为内容"),
    //
    FLYJ(3, "法律依据", "法律依据名称", "法律依据内容"),
    //
    QXFL(4, "情形分类", "情形分类名称", "情形分类内容"), ;

    /** 编码 */
    private Integer code;
    /** 文字 */
    private String  text;
    /**名字*/
    private String  name;
    /**内容*/
    private String  note;
    
    
    private DiscreCodeEnum(Integer code, String text, String name, String note) {
        this.code = code;
        this.text = text;
        this.name = name;
        this.note = note;
    }

    /**
     * 
     * 
     * @param code
     * @return
     */
    public static List<Combobox> getTypes(int code) {
        List<Combobox> re = new ArrayList<Combobox>();
        for (DiscreCodeEnum ele : values()) {
            if (ele.getCode() > code) {
                re.add(new Combobox(String.valueOf(ele.getCode()), ele.getText()));
            }
        }
        return re;
    }

    /**
     * 根据code得到对象
     * @param code
     * @return
     */
    public static String getTextByCode(String code) {
        for (DiscreCodeEnum ele : values()) {
            if (String.valueOf(ele.getCode()).equals(code)) {
                return ele.getText();
            }
        }
        return "";
    }

    /**
     * 根据code得到对象
     * @param code
     * @return
     */
    public static DiscreCodeEnum getByCode(String code) {
        DiscreCodeEnum isEnum = null;
        for (DiscreCodeEnum ele : values()) {
            if (String.valueOf(ele.getCode()).equals(code)) {
                isEnum = ele;
                break;
            }
        }
        return isEnum;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
