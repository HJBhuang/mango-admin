package com.louis.mango.admin.model;

import java.util.List;

public class SysDept extends BaseModel {

	/**
	 * 机构名称
	 */
    private String name;

	/**
	 * 上级机构ID，一级机构为0
	 */
    private Long parentId;

	/**
	 * 排序
	 */
    private Integer orderNum;

	/**
	 * 是否删除  -1：已删除  0：正常
	 */
    private Byte delFlag;
    // 非数据库字段
    private List<SysDept> children;
    // 非数据库字段
    private String parentName;
    // 非数据库字段
    private Integer level;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Byte getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}
	public List<SysDept> getChildren() {
		return children;
	}
	public void setChildren(List<SysDept> children) {
		this.children = children;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
    
}