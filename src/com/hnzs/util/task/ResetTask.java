package com.hnzs.util.task;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hnzs.bean.Employee;
import com.hnzs.bean.VoteLog;
import com.hnzs.dao.EmployeeMapper;
import com.hnzs.dao.VoteLogMapper;
import com.hnzs.dao.VoteNexusMapper;

@Component
public class ResetTask {

	@Resource
	private VoteLogMapper voteLogMapping;

	@Resource
	private EmployeeMapper empMapping;

	@Resource
	private VoteNexusMapper vote_n_mapping;

	/***
	 * 每月第一天的凌晨三点执行
	 */
	@Scheduled(cron = "0 0 3 1 * ?")
	public void taskJon() {
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
		// 还原员工表剩余票数
		empMapping.empVoteReset();
	}

}
