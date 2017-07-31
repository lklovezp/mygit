/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.po;


/**
 * 自定义查询的字典配置
 * 
 * @author wumi
 * @version $Id: Dbdic.java, v 0.1 Mar 14, 2013 11:02:19 AM wumi Exp $
 */
public class Dbdic extends BaseObject {

    /**  */
    private static final long serialVersionUID = -4432523123135483410L;

    /** id */
    private String            id;

    /**表名*/
    private String            tableName;

    /**名称*/
    private String            coloumnName;

    /** 备注 */
    private String            tableNote;

    /**名称*/
    private String            coloumnNote;

    private String            colType;

    private String            colScript;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColoumnName() {
        return coloumnName;
    }

    public void setColoumnName(String coloumnName) {
        this.coloumnName = coloumnName;
    }

    public String getTableNote() {
        return tableNote;
    }

    public void setTableNote(String tableNote) {
        this.tableNote = tableNote;
    }

    public String getColoumnNote() {
        return coloumnNote;
    }

    public void setColoumnNote(String coloumnNote) {
        this.coloumnNote = coloumnNote;
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    public String getColScript() {
        return colScript;
    }

    public void setColScript(String colScript) {
        this.colScript = colScript;
    }

}
