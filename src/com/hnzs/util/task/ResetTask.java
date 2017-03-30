package com.hnzs.util.task;

/****
 * 任务调度模块 业务调整暂时不需要，需要的时候可放开注释
 * 
 * @author Akina
 *
 */
public class ResetTask {

	// @Resource
	// private VoteLogMapper voteLogMapping;
	//
	// @Resource
	// private EmployeeMapper empMapping;
	//
	// @Resource
	// private VoteNexusMapper vote_n_mapping;

	// /***
	// * 每月第一天的凌晨三点执行
	// */
	// @Scheduled(cron = "0 0 3 1 * ?")
	// public void taskJon() {
	// // 得到本月员工得票情况
	// List<Employee> empListToMo = empMapping.findVotedBydesc();
	// VoteLog voteLog = new VoteLog();
	//
	// // 获取年月
	// Calendar now = Calendar.getInstance();
	// // 设置年月
	// voteLog.setLogYear(now.get(Calendar.YEAR));
	// voteLog.setLogMonth(now.get(Calendar.MONTH) + 1);
	// // 遍历月得票情况
	// for (Employee employee : empListToMo) {
	// // 设置得票信息
	// voteLog.setEmpId(employee.getId());
	// voteLog.setEmpVotenum(employee.getVotedNum());
	// // 插入数据
	// voteLogMapping.insertSelective(voteLog);
	// }
	// // 循环结束清空月记录表
	// vote_n_mapping.deleteByAll();
	// // 还原员工表剩余票数
	// empMapping.empVoteReset();
	// }

}
