package com.hnjz.sys.po;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * 作者：赵文明
 * 生成日期：2015-2-13
 * 功能描述：用户
 *
 */
public class TSysUser extends BaseObject implements UserDetails {

	private static final long serialVersionUID = -3098704168112982780L;

	private String username;

	private String name;

	private String password;

	private String issys;
	
	private String bizType;

	private String orgId;
	
	private String isZaiBian;
	private String isRecWork;

	private List<GrantedAuthority> authorities;

	private String role;//非表中的字段

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIssys() {
		return issys;
	}

	public void setIssys(String issys) {
		this.issys = issys;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getIsZaiBian() {
		return isZaiBian;
	}

	public void setIsZaiBian(String isZaiBian) {
		this.isZaiBian = isZaiBian;
	}

	public String getIsRecWork() {
		return isRecWork;
	}

	public void setIsRecWork(String isRecWork) {
		this.isRecWork = isRecWork;
	}

	public boolean isEnabled() {
		return true;
	}

}
