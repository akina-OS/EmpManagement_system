package com.hnzs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzs.bean.Employee;

public interface EmployeeMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table employee
	 *
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table employee
	 *
	 * @mbggenerated
	 */
	int insert(Employee record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table employee
	 *
	 * @mbggenerated
	 */
	int insertSelective(Employee record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table employee
	 *
	 * @mbggenerated
	 */
	Employee selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table employee
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(Employee record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table employee
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKey(Employee record);

	/** ==================================== **/
	/***
	 * 根据员工姓名查询员工
	 * 
	 * @param emp_name
	 * @return Employee
	 */
	Employee selectByEmpName(String emp_name);

	/***
	 * 查询不等于传入参数的所有员工信息
	 * 
	 * @param id
	 * @return 返回员工集合
	 */
	List<Employee> findempByNotID(@Param("id") Integer id);

	/****
	 * 查询该员工投票情况(投了谁)
	 * 
	 * @param id
	 * @return List<Employee>
	 */
	List<Employee> findEmpVoted(@Param("id") Integer id);

	/***
	 * 员工持有票数减一
	 * 
	 * @param id
	 * @return
	 */
	int evnReduce(@Param("id") Integer id);

	/***
	 * 查询员工持有票数
	 * 
	 * @param id
	 * @return
	 */
	Integer findVotedNum(@Param("id") Integer id);

	/***
	 * 员工持有票数加一
	 * 
	 * @param id
	 * @return int
	 */
	int evnAdd(@Param("id") Integer id);

	/****
	 * 分页查询数据
	 * 
	 * @param start
	 *            起始条数
	 * @param size
	 *            每页显示条数
	 * 
	 * @param query_str
	 *            查询条件
	 * @return List<Employee>
	 */
	List<Employee> findEmpDate(@Param("start") Integer start, @Param("size") Integer size,
			@Param("query_str") String query_str);

	/***
	 * 查询已供货有多少员工
	 * 
	 * @param query_str
	 *            查 询条件
	 * @return Integer
	 */
	Integer findEmpCount(@Param("query_str") String query_str);

	/***
	 * 按得票数由大到小排序返回员工集合
	 * 
	 * @return List<Employee>
	 */
	List<Employee> findVotedBydesc();

	/**
	 * 按年得票数由大到小排序返回员工集合
	 * 
	 * @param year
	 * @return List<Employee>
	 */
	List<Employee> findVotedByYear(@Param("year") Integer year);
}