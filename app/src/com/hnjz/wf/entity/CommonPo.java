package com.hnjz.wf.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.hnjz.sys.po.TSysUser;

@MappedSuperclass
public abstract class CommonPo implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 编号 */
    public String             id;

    /** 排序 */
    public Integer            order;

    /** 创建时间 */
    public Date               createTime       = Calendar.getInstance().getTime();

    /** 创建人 */
    public TSysUser               createUser;

    /** 更新时间 */
    public Date               updateTime;

    /** 更新人 */
    public TSysUser               updateUser;

    /** 是否可用 */
    public String             isActive         = "Y";

    /** 版本 */
    public Integer            version;

    //@Id
    //@GeneratedValue(generator = "system-uuid")
    //@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    //@Column(name = "id_", nullable = false, unique = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "CREATED_")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "UPDATED_")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Version
    @Column(name = "VERSION_")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public TSysUser getCreateUser() {
        return createUser;
    }

    public void setCreateUser(TSysUser createUser) {
        this.createUser = createUser;
    }

    public TSysUser getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(TSysUser updateUser) {
        this.updateUser = updateUser;
    }

    @Column(name = "ISACTIVE_")
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

}
