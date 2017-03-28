package com.hnzs.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzs.bean.Employee;
import com.hnzs.bean.Opinion;
import com.hnzs.service.IEmployeeService;
import com.hnzs.service.bean.LoginBean;
import com.hnzs.util.CommonMethod;
import com.hnzs.util.StaticStr;

/***
 * emp控制
 * 
 * @author Akina
 *
 */

@Controller
@RequestMapping("/emp")
public class EmpController {

	@Resource
	private IEmployeeService empService;

	/***
	 * 去登陆页面
	 * 
	 * @return
	 */
	@RequestMapping("gotoLoginview")
	public String gotoLoginview() {

		return "emp/login";
	}

	/***
	 * 去主界面
	 * 
	 * @return
	 */
	@RequestMapping("gotoEmpVote")
	public String gotoEmpVote(HttpSession session) {
		if (session.getAttribute(StaticStr.SESSION_EMP) == null) {
			return "redirect:/emp/gotoLoginview";
		}
		return "emp/showEmpVote";
	}

	/***
	 * 跳转意见收集模块
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("gotoAddopinion")
	public String gotoAddopinion(HttpSession session) {
		if (session.getAttribute(StaticStr.SESSION_EMP) == null) {
			return "redirect:/emp/gotoLoginview";
		}
		return "emp/addOpinion";
	}

	/**************** 以上为页面跳转 ******************/

	/***
	 * 登陆操作。 对比数据库员工 ,如果账户没有密码，直接用第一次的密码存储 ，如果账户有密码，对比密码
	 * 考虑到只能录入的员工才能登陆，所以就没有书写注册功能
	 * 
	 * @param emp_name
	 *            员工姓名
	 * @param emp_pwd
	 *            员工密码
	 * @return
	 */
	@RequestMapping(value = "lar", method = RequestMethod.POST)
	public String loginAndreg(String emp_name, String emp_pwd, HttpSession session, Model model) {
		LoginBean bean = empService.loginAndreg(emp_name, emp_pwd);
		switch (bean.getStat()) {
		case -1:
			// 数据库没有这个员工
			model.addAttribute("errormsg", "数据库没有您的数据，请联系管理员");
			return "emp/login";
		case 0:
			// 密码错误登陆失败
			model.addAttribute("errormsg", "密码错误");
			return "emp/login";
		case 1:
			session.setAttribute(StaticStr.SESSION_EMP, bean.getEmp());
			// System.out.println(bean.getEmp());
			// 登陆成功
			// model.addAttribute("logmsg", "登陆成功，欢迎" +
			// bean.getEmp().getEmpName());
			return "redirect:/emp/gotoEmpVote";
		case 2:
			session.setAttribute(StaticStr.SESSION_EMP, bean.getEmp());
			// System.out.println(bean.getEmp());
			// 第一次登陆成功，并且成功设置密码
			// model.addAttribute("logmsg", "密码设置成功");
			return "redirect:/emp/gotoEmpVote";
		default:
			model.addAttribute("errormsg", "系统错误");
			return "emp/login";
		}
	}

	/***
	 * 返回除了登陸用戶外的所有emp
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findAllemp", method = RequestMethod.POST)
	public List<Employee> findAllemp(HttpSession session) {
		Employee sessionEmp = (Employee) session.getAttribute(StaticStr.SESSION_EMP);
		return CommonMethod.changeEmpHeadUrl(empService.findempByNotID(sessionEmp.getId()));
	}

	/****
	 * 查询该员工投票情况(投了谁)
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getEmpVoted", method = RequestMethod.POST)
	public List<Employee> getEmpVoted(Integer id) {
		return empService.findEmpVoted(id);
	}

	/****
	 * 投票方法
	 * 
	 * @param emp_id
	 *            投票人id
	 * @param vote_id
	 *            被投票人id
	 * @return 返回投票人持有票数 返回-1表示没有选票
	 */
	@ResponseBody
	@RequestMapping(value = "empVote", method = RequestMethod.POST)
	public Integer empVote(Integer emp_id, Integer vote_id) {
		return empService.empVote(emp_id, vote_id);
	}

	/****
	 * 根据用户id和得票id 取消投票 ( 删除关联
	 * 
	 * @param emp_id
	 *            投票人id
	 * @param vote_id
	 *            被投票人id
	 * @return 返回投票人持有票数
	 */
	@ResponseBody
	@RequestMapping(value = "cEmpVote", method = RequestMethod.POST)
	public Integer cancelEmpVote(Integer emp_id, Integer vote_id) {
		return empService.cancelEmpVote(emp_id, vote_id);
	}

	/**
	 * 提交建议
	 * 
	 * @return
	 */
	@RequestMapping(value = "addOpini", method = RequestMethod.POST)
	public String addOpini(String textarea1, HttpSession session) {
		Employee emp = (Employee) session.getAttribute(StaticStr.SESSION_EMP);
		Opinion opn = new Opinion();
		opn.setEmpId(emp.getId());
		opn.setOpinionStr(textarea1);
		empService.addOpinion(opn);
		return "redirect:/emp/gotoEmpVote";
	}

}
