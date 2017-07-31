package com.hnjz.app.rwsz.po;

import org.apache.commons.lang.StringUtils;

import com.hnjz.app.rwsz.discretion.DiscreCodeEnum;
import com.hnjz.sys.po.BaseObject;

/**
 * 自由裁量权
 * 
 * @author xuguanghui
 * @version $Id: DiscretionaryPower.java, v 0.1 Aug 6, 2013 11:29:12 AM Administrator Exp $
 */
public class DiscretionaryPower extends BaseObject {

    private static final long serialVersionUID = 1L;
    private String            id;
    private String            name;                 //名称
    private String            content;              //内容
    private String            gjc;                  //关键词
    private String            illegalTypeId;        //违法类型id
    private String            illegalTypeName;      //违法类型名称
    private String            pid;                  //父id
    private String            code;                 //所属分类
    private String            codenote;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIllegalTypeId() {
        return illegalTypeId;
    }

    public void setIllegalTypeId(String illegalTypeId) {
        this.illegalTypeId = illegalTypeId;
    }

    public String getIllegalTypeName() {
        return illegalTypeName;
    }

    public void setIllegalTypeName(String illegalTypeName) {
        this.illegalTypeName = illegalTypeName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGjc() {
        return gjc;
    }

    public void setGjc(String gjc) {
        this.gjc = gjc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getDesc(){
        if(StringUtils.isNotBlank(content)){
            return content;
        }
        return name;
    }
    
    public String getCodenote(){
        if(StringUtils.isNotBlank(code)){
            DiscreCodeEnum dis = DiscreCodeEnum.getByCode(code);
            if(null != dis){
                return dis.getName();
            }
        }
        return code;
    }
	public void setCodenote(String codenote) {
		this.codenote = codenote;
	}

}
