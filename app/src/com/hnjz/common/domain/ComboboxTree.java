/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author wumi
 * @version $Id: ComboboxTree.java, v 0.1 Mar 14, 2013 10:34:32 AM wumi Exp $
 */
public class ComboboxTree {

    private String             id;

    private String             code;
    
    private String             text;

    private List<ComboboxTree> children;
    
    private String             pid;
   

	/* ¼¶±ð **/
    private String             curLevel;

    public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public ComboboxTree() {
        super();
    }

    public ComboboxTree(String id, String text) {
        super();
        this.id = id;
        this.text = text;
    }
    
    public ComboboxTree(String id, String code, String text) {
        super();
        this.id = id;
        this.code = code;
        this.text = text;
    }
    
    public ComboboxTree(String id, String text, String pid,String curLevel) {
    	super();
    	this.id = id;
    	this.text = text;
    	this.pid = pid;
    	this.curLevel = curLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ComboboxTree> getChildren() {
        return children;
    }

    public void setChildren(List<ComboboxTree> children) {
        this.children = children;
    }

    public void addCh(ComboboxTree ele) {
        if (null == children) {
            children = new ArrayList<ComboboxTree>();
        }
        children.add(ele);
    }
    public String getCurLevel() {
		return curLevel;
	}

	public void setCurLevel(String curLevel) {
		this.curLevel = curLevel;
	}
}
