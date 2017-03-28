package com.hnzs.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzs.bean.Employee;
import com.hnzs.service.IAdminService;
import com.hnzs.service.IEmployeeService;
import com.hnzs.util.StaticStr;

/***
 * 管理员投票管理界面
 * 
 * @author Akina
 *
 */

@Controller
@RequestMapping("/admVote")
public class AdmVoteController {

	@Resource
	private IEmployeeService empService;

	@Resource
	private IAdminService admservice;

	/***
	 * 计算本月员工得票数 返回得票集合 有小到大排序 *
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getVoteEmp", method = RequestMethod.POST)
	public List<Employee> getVoteEmp() {
		return empService.findVotedBydesc();
	}

	/**
	 * 计算全年员工得票数
	 * 
	 * @param year
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getVoteByYear", method = RequestMethod.POST)
	public List<Employee> getVoteByYear(Integer year) {
		return empService.getVotedByYear(year);
	}

	/***
	 * 手动重置投票关系
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "restartSysDate")
	public Integer restartSysDate(HttpSession session) {
		// 检查是否登录
		if (session.getAttribute(StaticStr.SESSION_ADMIN) == null) {
			return -1;
		}
		admservice.restartSysDate();
		return 1;
	}

}
