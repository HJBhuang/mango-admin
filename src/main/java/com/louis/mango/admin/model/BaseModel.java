package com.louis.mango.admin.model;

import java.util.Date;

/**
 * 基础模型
 * @author Louis
 * @date Sep 13, 2018
 */
public class BaseModel {

	/**
	 *编号
	 */
	private Long id;

	/**
	 *创建人
	 */
    private String createBy;

	/**
	 *创建时间
	 */
    private Date createTime;

	/**
	 *最后更新人
	 */
    private String lastUpdateBy;

	/**
	 *最后更新时间
	 */
	private Date lastUpdateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
    
}
