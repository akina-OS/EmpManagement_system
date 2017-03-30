package com.hnzs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hnzs.bean.Employee;
import com.hnzs.bean.Opinion;
import com.hnzs.bean.VoteNexus;
import com.hnzs.dao.EmployeeMapper;
import com.hnzs.dao.OpinionMapper;
import com.hnzs.dao.VoteNexusMapper;
import com.hnzs.service.IEmployeeService;
import com.hnzs.service.bean.LoginBean;
import com.hnzs.service.bean.PageBean;
import com.hnzs.util.CommonUtil;
import com.hnzs.util.StaticStr;

@Service("empService")
public class EmployeeService implements IEmployeeService {

	@Resource
	private EmployeeMapper empMapping;

	@Resource
	private VoteNexusMapper vnMapping;

	@Resource
	private OpinionMapper opinMapping;

	/***
	 * 根据姓名查询记录 如果没有密码，则存入密码 如果记录存在密码，则对比密码
	 */
	@Override
	public LoginBean loginAndreg(String emp_name, String emp_pwd) {

		Employee empBean = empMapping.selectByEmpName(emp_name);
		LoginBean logBean = new LoginBean();
		if (empBean == null) {
			// 数据库没有这个员工
			logBean.setStat(-1);
		} else {
			// 数据库有这个员工
			if (empBean.getEmpPwd() == null || "".equals(empBean.getEmpPwd())) {
				// 设置密码
				empBean.setEmpPwd(emp_pwd);
				logBean.setEmp(empBean);
				// 该员工没有密码，那么更新密码
				empBean.setEmpName(null);
				empBean.setEmpImg(null);
				empBean.setEmpVoteNum(null);
				empBean.setInitTime(null);
				// 更新数据库操作
				empMapping.updateByPrimaryKeySelective(empBean);
				// 第一次登陆成功，并且成功设置密码
				logBean.setStat(2);
			} else {
				// 该员工有密码，开始对比密码
				if (empBean.getEmpPwd().equals(emp_pwd)) {
					// 登陆成功
					logBean.setEmp(empBean);
					logBean.setStat(1);
				} else {
					// 登陆失败
					logBean.setStat(0);
				}
			}

		}
		return logBean;

	}

	/***
	 * 查询不等于传入参数的所有员工信息
	 */
	@Override
	public List<Employee> findempByNotID(Integer id) {
		return empMapping.findempByNotID(id);
	}

	/****
	 * 查询该员工投票情况(投了谁)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<Employee> findEmpVoted(Integer id) {
		return empMapping.findEmpVoted(id);
	}

	/****
	 * 投票方法
	 * 
	 * @param emp_id
	 *            投票人id
	 * @param vote_id
	 *            被投票人id
	 * @return 返回投票人持有票数 返回-1表示投票人已经用完全部的选票
	 */
	@Override
	public Integer empVote(Integer emp_id, Integer vote_id) {
		// 首先查询剩余票数
		Integer vote_num = empMapping.findVotedNum(emp_id);
		if (vote_num <= 0) {
			// 投票人不能投票
			return -1;
		} else {
			// 可以投票逻辑
			// 首先建立关联关系
			VoteNexus vnBean = new VoteNexus();
			vnBean.setVoteId(emp_id);
			vnBean.setVotedId(vote_id);
			// 插入关联关系
			vnMapping.insertSelective(vnBean);
			// 投票人票数减一
			empMapping.evnReduce(emp_id);
			// TODO : 理论上说，这样的操作没问题，但是实际操作错综复杂难保会不出差错
			// 但是再次查询数据库，显得很冗余，逻辑待考虑
			return vote_num - 1;
		}

	}

	/***
	 * 根据用户id和得票id 取消投票 ( 删除关联)
	 * 
	 * @param vote_id
	 *            投票者id
	 * @param voted_id
	 *            得票者id
	 * @return
	 */
	@Override
	public Integer cancelEmpVote(Integer vote_id, Integer voted_id) {
		// 删除关联关系
		vnMapping.deleteByVE(vote_id, voted_id);
		// 投票人票数加1
		empMapping.evnAdd(vote_id);
		// 返回投票人持有票数
		return empMapping.findVotedNum(vote_id);
	}

	/****
	 * 根据页码得到员工数据
	 * 
	 * @param pageNum
	 * @param query_str
	 *            查询条件
	 * @return
	 */
	@Override
	public PageBean getEmpByPage(Integer pageNum, String query_str) {

		if ("".equals(query_str)) {
			query_str = null;
		} else {
			query_str = "%" + query_str + "%";
		}
		PageBean page = new PageBean();
		// 查询数据
		List<Employee> pageDate = empMapping.findEmpDate((pageNum - 1) * StaticStr.PAGE_SIZE, StaticStr.PAGE_SIZE,
				query_str);
		page.setDataList(pageDate);
		// 查询一共多少人
		Integer count = empMapping.findEmpCount(query_str);
		page.setCount(count);
		// 计算页数
		if (count % StaticStr.PAGE_SIZE == 0) {
			page.setTotalPage(count / StaticStr.PAGE_SIZE);
		} else {
			page.setTotalPage(count / StaticStr.PAGE_SIZE + 1);
		}
		return page;

	}

	/****
	 * 根据id查询emp信息
	 * 
	 * @param emp_id
	 * @return
	 */
	@Override
	public Employee getEmpById(Integer emp_id) {
		return empMapping.selectByPrimaryKey(emp_id);
	}

	@Override
	public Integer updateEmpById(Employee emp_bean) {
		return empMapping.updateByPrimaryKeySelective(emp_bean);
	}

	/***
	 * 添加员工信息
	 */
	@Override
	public Integer addEmp(Employee emp) {
		return empMapping.insertSelective(emp);
	}

	/***
	 * 删除员工
	 */
	@Override
	public Integer delEmp(Integer emp_id) {
		Employee emp = empMapping.selectByPrimaryKey(emp_id);
		CommonUtil.delFile(StaticStr.HEAD_DIR + emp.getEmpImg());
		return empMapping.deleteByPrimaryKey(emp_id);
	}

	/***
	 * 计算本月员工得票数 返回得票集合 有小到大排序 *
	 * 
	 * @return
	 */
	@Override
	public List<Employee> findVotedBydesc() {
		return empMapping.findVotedBydesc();
	}

	@Override
	public List<Employee> getVotedByYear(Integer year) {
		return empMapping.findVotedByYear(year);
	}

	@Override
	public void addOpinion(Opinion opn) {
		opinMapping.insertSelective(opn);
	}

	@Override
	public void empVoteReset() {
		empMapping.empVoteReset();
	}

	@Override
	public void empVoteStatChange1(Integer emp_id) {
		empMapping.empVoteStatChange1(emp_id);

	}

	/***
	 * 查询投票状态任然为0(还未确认)的员工
	 */
	@Override
	public List<Employee> voteEmp_no() {
		return empMapping.voteEmp_no();
	}

}
