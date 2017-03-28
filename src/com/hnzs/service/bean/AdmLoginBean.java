package com.hnzs.service.bean;

import com.hnzs.bean.Admin;

/***
 * 管理员登陆业务实体
 * 
 * @author Akina
 *
 */
public class AdmLoginBean {
	private Admin admBean;
	private Integer stat;

	public Admin getAdmBean() {
		return admBean;
	}

	public void setAdmBean(Admin admBean) {
		this.admBean = admBean;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}

	@Override
	public String toString() {
		return "AdmLoginBean [admBean=" + admBean + ", stat=" + stat + "]";
	}

}
