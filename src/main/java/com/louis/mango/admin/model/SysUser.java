package com.louis.mango.admin.model;

import java.util.ArrayList;
import java.util.List;

public class SysUser extends BaseModel {

	/**
	 * 用户名
	 */
    private String name;

	/**
	 * 昵称
	 */
    private String nickName;

	/**
	 * 头像
	 */
    private String avatar;

	/**
	 * 密码
	 */
    private String password;


	/**
	 * 加密盐
	 */
    private String salt;

	/**
	 * 邮箱
	 */
    private String email;

	/**
	 * 手机号
	 */
    private String mobile;

	/**
	 * 状态  0：禁用（锁定）   1：正常
	 */
    private Byte status;

	/**
	 * 机构ID
	 */
    private Long deptId;

	/**
	 * 是否删除  -1：已删除  0：正常
	 */
    private Byte delFlag;
    
    // 非数据库字段
    private String deptName;
    // 非数据库字段
    private String roleNames;
    // 非数据库字段
    private List<SysUserRole> userRoles = new ArrayList<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Byte getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public List<SysUserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<SysUserRole> userRoles) {
		this.userRoles = userRoles;
	}

}