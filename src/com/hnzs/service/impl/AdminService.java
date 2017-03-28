package com.hnzs.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hnzs.bean.Admin;
import com.hnzs.bean.Employee;
import com.hnzs.bean.VoteLog;
import com.hnzs.dao.AdminMapper;
import com.hnzs.dao.EmployeeMapper;
import com.hnzs.dao.VoteLogMapper;
import com.hnzs.dao.VoteNexusMapper;
import com.hnzs.service.IAdminService;
import com.hnzs.service.bean.AdmLoginBean;

@Service("admService")
public class AdminService implements IAdminService {

	@Resource
	private AdminMapper admMapping;

	@Resource
	private EmployeeMapper empMapping;

	@Resource
	private VoteLogMapper voteLogMapping;

	@Resource
	private VoteNexusMapper vote_n_mapping;

	/********
	 * 管理员登陆业务
	 * 
	 * @param adm_name
	 *            管理员账号
	 * @param adm_pwd
	 *            管理员密码
	 * @return 返回一个包含管理员登陆状态和管理员实体的bean
	 * 
	 */
	@Override
	public AdmLoginBean admlogin(String adm_name, String adm_pwd) {

		Admin admBean = admMapping.selectBynameApwd(adm_name, adm_pwd);
		AdmLoginBean adloBean = new AdmLoginBean();
		if (admBean == null) {
			adloBean.setStat(-1);
		} else {
			adloBean.setAdmBean(admBean);
			adloBean.setStat(1);
		}
		return adloBean;
	}

	/***
	 * 管理员修改密码
	 */
	@Override
	public Integer amdUpdatePwd(Admin adm) {
		return admMapping.updateByPrimaryKeySelective(adm);

	}

	/***
	 * 
	 * @param 每月重置系统投票
	 * @param 查出本月员工得票情况，存入月记录表vote_log
	 * @param 每月员工投票记录，用记事器每月1号上午8点清除投票表记录,清空每月记录表
	 *            vote_nexus
	 * 
	 *            手动执行重置程序
	 */
	@Override
	public void restartSysDate() {
		// 得到本月员工得票情况
		List<Employee> empListToMo = empMapping.findVotedBydesc();
		VoteLog voteLog = new VoteLog();

		// 获取年月
		Calendar now = Calendar.getInstance();
		// 设置年月
		voteLog.setLogYear(now.get(Calendar.YEAR));
		voteLog.setLogMonth(now.get(Calendar.MONTH) + 1);
		// 遍历月得票情况
		for (Employee employee : empListToMo) {
			// 设置得票信息
			voteLog.setEmpId(employee.getId());
			voteLog.setEmpVotenum(employee.getVotedNum());
			// 插入数据
			voteLogMapping.insertSelective(voteLog);
		}
		// 循环结束清空月记录表
		vote_n_mapping.deleteByAll();
	}

}
