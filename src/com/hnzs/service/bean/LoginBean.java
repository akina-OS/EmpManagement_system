package com.hnzs.service.bean;

import com.hnzs.bean.Employee;

/**
 * 登陆用的实体
 * 
 * @author Akina
 *
 */
public class LoginBean {

	private Employee emp;
	private Integer stat;

	public LoginBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginBean(Employee emp, Integer stat) {
		super();
		this.emp = emp;
		this.stat = stat;
	}

	@Override
	public String toString() {
		return "LoginBean [emp=" + emp + ", stat=" + stat + "]";
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}

}
