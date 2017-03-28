package com.hnzs.service;

import com.hnzs.bean.Admin;
import com.hnzs.service.bean.AdmLoginBean;

/****
 * 管理员业务层接口
 * 
 * @author Akina
 *
 */
public interface IAdminService {

	/********
	 * 管理员登陆业务
	 * 
	 * @param adm_name
	 *            管理员账号
	 * @param adm_pwd
	 *            管理员密码
	 * @return 返回一个包含管理员登陆状态和管理员实体的bean -1登陆失败 1 登陆成功
	 */
	public AdmLoginBean admlogin(String adm_name, String adm_pwd);

	/***
	 * 管理员修改密码
	 * 
	 * @return
	 */
	public Integer amdUpdatePwd(Admin adm);

	/***
	 * 
	 * 每月1号早上3点执行
	 * 
	 * @param 每月重置系统投票
	 * @param 查出本月员工得票情况，存入月记录表vote_log
	 * @param 每月员工投票记录，用记事器每月1号上午8点清除投票表记录,清空每月记录表
	 *            vote_nexus
	 * @param
	 */
	public void restartSysDate();

}
