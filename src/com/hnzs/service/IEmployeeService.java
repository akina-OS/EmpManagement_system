package com.hnzs.service;

import java.util.List;

import com.hnzs.bean.Employee;
import com.hnzs.bean.Opinion;
import com.hnzs.service.bean.LoginBean;
import com.hnzs.service.bean.PageBean;

/****
 * 员工业务层接口
 * 
 * @author Akina
 *
 */
public interface IEmployeeService {
	/***
	 * 根据员工姓名查询员工
	 * 
	 * @param emp_name
	 * @return
	 */
	public LoginBean loginAndreg(String emp_name, String emp_pwd);

	/***
	 * 查询不等于传入参数的所有员工信息
	 * 
	 * @param id
	 * @return 返回员工集合
	 */
	public List<Employee> findempByNotID(Integer id);

	/****
	 * 查询该员工投票情况(投了谁)
	 * 
	 * @param id
	 * @return
	 */
	public List<Employee> findEmpVoted(Integer id);

	/****
	 * 投票方法
	 * 
	 * @param emp_id
	 *            投票人id
	 * @param vote_id
	 *            被投票人id
	 * @return 返回投票人持有票数
	 */
	public Integer empVote(Integer emp_id, Integer vote_id);

	/***
	 * 根据用户id和得票id删除关联
	 * 
	 * @param vote_id
	 *            投票者id
	 * @param voted_id
	 *            得票者id
	 * @return
	 */
	public Integer cancelEmpVote(Integer vote_id, Integer voted_id);

	/****
	 * 根据页码得到员工数据
	 * 
	 * @param pageNum
	 *            页码
	 * @param query_str
	 *            查询条件
	 * @return
	 */
	public PageBean getEmpByPage(Integer pageNum, String query_str);

	/****
	 * 根据id查询emp信息
	 * 
	 * @param emp_id
	 * @return
	 */
	public Employee getEmpById(Integer emp_id);

	/***
	 * 根据id更新
	 * 
	 * @param emp_bean
	 * @return
	 */
	public Integer updateEmpById(Employee emp_bean);

	/**
	 * 添加员工信息
	 * 
	 * @param emp
	 * @return
	 */
	public Integer addEmp(Employee emp);

	/****
	 * 根据员工id删除
	 * 
	 * @param emp_id
	 * @return
	 */
	public Integer delEmp(Integer emp_id);

	/***
	 * 计算本月员工得票数 返回得票集合 有小到大排序 *
	 * 
	 * @return
	 */
	public List<Employee> findVotedBydesc();

	/***
	 * 计算全年员工得票数 返回得票集合 有小到大排序 *
	 * 
	 * @param year
	 * @return
	 */
	public List<Employee> getVotedByYear(Integer year);

	/**
	 * 添加建议
	 * 
	 * @param opn
	 */
	public void addOpinion(Opinion opn);

	/****
	 * 还原员工表剩余票数,所有員工票數改為0，任務調度執行
	 */
	public void empVoteReset();

	/***
	 * 修改投票状态，修改位1确认，确认 投票状态，0未确认，可以修改，1为确认不可修改
	 */
	public void empVoteStatChange1(Integer emp_id);

	/***
	 * 查询投票状态任然为0(还未确认)的员工
	 * 
	 * @return
	 */
	public List<Employee> voteEmp_no();
}
