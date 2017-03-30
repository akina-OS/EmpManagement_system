package com.hnzs.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hnzs.bean.Admin;
import com.hnzs.bean.Employee;
import com.hnzs.service.IAdminService;
import com.hnzs.service.IEmployeeService;
import com.hnzs.service.IOpinionSerive;
import com.hnzs.service.bean.AdmLoginBean;
import com.hnzs.service.bean.PageBean;
import com.hnzs.util.CommonMethod;
import com.hnzs.util.CommonUtil;
import com.hnzs.util.StaticStr;

/***
 * 管理员控制器
 * 
 * @author Akina
 *
 */
@Controller
@RequestMapping("/adm")
public class AdmController {

	@Resource
	private IAdminService admService;

	@Resource
	private IEmployeeService empService;

	@Resource
	private IOpinionSerive opinService;

	/***
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("gotoAdm")
	public String gotoAdmLogin() {

		return "adm/login";
	}

	/***
	 * 跳转至投票统计界面
	 * 
	 * @return
	 */
	@RequestMapping("gotoVStat")
	public String gotoVStat(HttpSession session) {
		if (session.getAttribute(StaticStr.SESSION_ADMIN) == null) {
			return "redirect:/adm/gotoAdm";
		}
		return "adm/voteStatistics";
	}

	/***
	 * 跳转至员工管理界面
	 * 
	 * @return
	 */
	@RequestMapping("gotoEmpistr")
	public String gotoEmpistr(HttpSession session) {
		if (session.getAttribute(StaticStr.SESSION_ADMIN) == null) {
			return "redirect:/adm/gotoAdm";
		}
		return "adm/empistration";
	}

	/***
	 * 跳转至意见管理
	 * 
	 * @return
	 */
	@RequestMapping("gotoOpinion")
	public String gotoOpinion(HttpSession session) {
		if (session.getAttribute(StaticStr.SESSION_ADMIN) == null) {
			return "redirect:/adm/gotoAdm";
		}
		return "adm/opinionManag";
	}

	/***
	 * 跳转至密码管理
	 * 
	 * @return
	 */
	@RequestMapping("gotoUpdatePwd")
	public String gotoUpdatePwd(HttpSession session) {
		if (session.getAttribute(StaticStr.SESSION_ADMIN) == null) {
			return "redirect:/adm/gotoAdm";
		}
		return "adm/updatePwd";
	}

	// /***
	// * 跳转至员工修改界面
	// *
	// * @return
	// */
	// public String gotoUpdateEmp() {
	// return "adm/empUpdate";
	// }

	/*********************************************************/

	/****
	 * 登陆控制器
	 * 
	 * @param adm_name
	 * @param adm_pwd
	 * @return
	 */
	@RequestMapping(value = "admLog", method = RequestMethod.POST)
	public String admLog(String adm_name, String adm_pwd, HttpSession session, Model model) {
		AdmLoginBean bean = admService.admlogin(adm_name, adm_pwd);
		// -1登陆失败 1 登陆成功
		switch (bean.getStat()) {
		case -1:
			model.addAttribute("errormsg", "登陆失败，请检查您的账号和密码");
			return "adm/login";
		case 1:
			session.setAttribute(StaticStr.SESSION_ADMIN, bean.getAdmBean());
			return "adm/empistration";
		default:
			break;
		}
		return null;
	}

	/***
	 * 按照页码返回当页的emp数据
	 * 
	 * @param pageNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getEmpByPpage", method = RequestMethod.POST)
	public PageBean getEmpByPpage(Integer pageNum, String query_str) {
		return empService.getEmpByPage(pageNum, query_str);
	}

	/***
	 * 获取需要修改的员工信息
	 * 
	 * @param emp_id
	 *            员工id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getUpdateEmp", method = RequestMethod.GET)
	public String getUpdateEmp(Integer emp_id, Model model, HttpSession session) {
		if (session.getAttribute(StaticStr.SESSION_ADMIN) == null) {
			return "redirect:/adm/gotoAdm";
		}
		Employee empBean = empService.getEmpById(emp_id);
		empBean.setEmpImg(StaticStr.HEAD_URL + empBean.getEmpImg());
		model.addAttribute("updateEmp", empBean);
		return "adm/empUpdate";
	}

	/***
	 * 修改员工信息
	 * 
	 * @param emp_name
	 * @return
	 */
	@RequestMapping(value = "updateEmp", method = RequestMethod.POST)
	public String updateEmp(Integer emp_id, String emp_name, @RequestParam MultipartFile img_file, Model model) {
		Employee empBean = empService.getEmpById(emp_id);
		String fileName = img_file.getOriginalFilename();
		if (!"".equals(fileName)) {
			// 如果不为空字符串 那么开始保存逻辑
			// 如果返回不合法
			if (!CommonMethod.getImageDate(fileName)) {
				// 文件有，且不合法
				empBean.setEmpImg(StaticStr.HEAD_URL + empBean.getEmpImg());
				model.addAttribute("updateEmp", empBean);
				model.addAttribute("upLoadImagemsg", "请选择jpg，或者png格式的文件");
				// 返回修改页面
				return "adm/empUpdate";
			} else {
				// 文件有，并且合法
				// 获取当前时间字符串
				// 判断文件类型
				if (img_file.getContentType().equals("image/jpeg")) {
					// 文件为jpg格式
					fileName = CommonUtil.getCurrentTimeToFileName() + ".jpg";
				} else {
					// 反之为png（只有png和jpg能通过
					fileName = CommonUtil.getCurrentTimeToFileName() + ".png";
				}
				try {
					CommonUtil.delFile(StaticStr.HEAD_DIR + empBean.getEmpImg());
					// 保存新文件
					CommonUtil.savePic(img_file.getInputStream(), fileName, StaticStr.HEAD_DIR);
				} catch (IOException e) {
					model.addAttribute("updateEmp", empBean);
					model.addAttribute("upLoadImagemsg", "文件保存错误");
					e.printStackTrace();
					return "adm/empUpdate";
				}
			}
		} else {
			// 如果接受到的文件为空字符串 证明用户没有更改头像 将文件置空
			fileName = null;
		}

		empBean.setEmpName(emp_name);
		empBean.setEmpImg(fileName);
		empBean.setInitTime(null);
		empBean.setEmpPwd(null);
		empBean.setVotedNum(null);
		// 写入数据库
		empService.updateEmpById(empBean);
		// 合法的文件格式，清除警告信息
		model.addAttribute("upLoadImagemsg", null);
		// return "adm/empistration";
		// 重定向至员工管理界面
		return "redirect:/adm/gotoEmpistr";
	}

	/**
	 * 添加员工信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "addEmp", method = RequestMethod.POST)
	public String addEmp(String emp_name, @RequestParam MultipartFile img_file, Model model) {
		String fileName = img_file.getOriginalFilename();
		String adderrormsg = "";
		// 如果不为空字符串 那么开始保存逻辑
		// 如果返回不合法
		if (!CommonMethod.getImageDate(fileName)) {
			// 文件有，且不合法
			adderrormsg = "请选择jpg，或者png格式的文件";
		} else {
			// 文件有，并且合法
			// 获取当前时间字符串
			// 判断文件类型
			if (img_file.getContentType().equals("image/jpeg")) {
				// 文件为jpg格式
				fileName = CommonUtil.getCurrentTimeToFileName() + ".jpg";
			} else {
				// 反之为png（只有png和jpg能通过
				fileName = CommonUtil.getCurrentTimeToFileName() + ".png";
			}
			try {
				// 保存新文件
				CommonUtil.savePic(img_file.getInputStream(), fileName, StaticStr.HEAD_DIR);
				empService.addEmp(new Employee(emp_name, fileName));
				emp_name = null;
				adderrormsg = null;
			} catch (IOException e) {
				adderrormsg = "文件保存错误";
				e.printStackTrace();
			}
		}
		// 添加成功 清除提示消息
		model.addAttribute("emp_name", emp_name);
		model.addAttribute("adderrormsg", adderrormsg);
		// 返回员工管理
		// return "adm/empistration";
		// 重定向至员工管理界面
		return "redirect:/adm/gotoEmpistr";
	}

	/**
	 * 删除员工
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delEmp", method = RequestMethod.POST)
	public Integer delEmp(Integer emp_id) {
		return empService.delEmp(emp_id);
	}

	/***
	 * 管理员修改密码
	 * 
	 * @param new_pwd
	 * @return
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	public String updatePwd(String new_pwd, HttpSession session) {
		Admin old_adm = (Admin) session.getAttribute(StaticStr.SESSION_ADMIN);
		Admin new_adm = new Admin();
		new_adm.setId(old_adm.getId());
		new_adm.setAdPwd(new_pwd);
		admService.amdUpdatePwd(new_adm);
		return "redirect:/adm/gotoEmpistr";
	}

	/**
	 * 分页查询意见数据
	 * 
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getOpinDateByPage", method = RequestMethod.POST)
	public PageBean getOpinDateByPage(Integer pageNum) {
		return opinService.findOpinByPage(pageNum);

	}

	/***
	 * 删除意见
	 * 
	 * @param opin_id
	 * @return
	 */
	@RequestMapping(value = "delOpin", method = RequestMethod.GET)
	public String delOpin(Integer opin_id, HttpSession session) {
		if (session.getAttribute(StaticStr.SESSION_ADMIN) == null) {
			return "redirect:/adm/gotoAdm";
		}
		opinService.delOpin(opin_id);
		return "redirect:/adm/gotoOpinion";
	}

	/***
	 * 查询投票状态任然为0(还未确认)的员工
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getVoteEmp_no", method = RequestMethod.POST)
	public List<Employee> getVoteEmp_no() {
		return CommonMethod.changeEmpHeadUrl(empService.voteEmp_no());
	}
}
