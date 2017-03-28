package com.hnzs.bean;

import java.util.Date;

/***
 * 管理员账号实体
 * 
 * @author Akina
 *
 */
public class Admin {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column admin.id
	 *
	 * @mbggenerated
	 */
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column admin.ad_name
	 *
	 * @mbggenerated
	 */
	private String adName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column admin.ad_pwd
	 *
	 * @mbggenerated
	 */
	private String adPwd;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column admin.init_time
	 *
	 * @mbggenerated
	 */
	private Date initTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column admin.id
	 *
	 * @return the value of admin.id
	 *
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column admin.id
	 *
	 * @param id
	 *            the value for admin.id
	 *
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column admin.ad_name
	 *
	 * @return the value of admin.ad_name
	 *
	 * @mbggenerated
	 */
	public String getAdName() {
		return adName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column admin.ad_name
	 *
	 * @param adName
	 *            the value for admin.ad_name
	 *
	 * @mbggenerated
	 */
	public void setAdName(String adName) {
		this.adName = adName == null ? null : adName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column admin.ad_pwd
	 *
	 * @return the value of admin.ad_pwd
	 *
	 * @mbggenerated
	 */
	public String getAdPwd() {
		return adPwd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column admin.ad_pwd
	 *
	 * @param adPwd
	 *            the value for admin.ad_pwd
	 *
	 * @mbggenerated
	 */
	public void setAdPwd(String adPwd) {
		this.adPwd = adPwd == null ? null : adPwd.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column admin.init_time
	 *
	 * @return the value of admin.init_time
	 *
	 * @mbggenerated
	 */
	public Date getInitTime() {
		return initTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column admin.init_time
	 *
	 * @param initTime
	 *            the value for admin.init_time
	 *
	 * @mbggenerated
	 */
	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", adName=" + adName + ", adPwd=" + adPwd + ", initTime=" + initTime + "]";
	}

}