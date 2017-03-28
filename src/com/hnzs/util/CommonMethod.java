package com.hnzs.util;

import java.util.List;

import com.hnzs.bean.Employee;

/**
 * 通用的静态方法
 * 
 * @author Akina
 *
 */
public class CommonMethod {

	/**
	 * 被允许的文件格式
	 */
	public static String[] ALLOWED_IMAGE = { "jpg", "jpge", "png", "JPG", "JPGE", "PNG" };

	/***
	 * 为数据库取出的员工集合添加网络头像路径
	 * 
	 * @param emp_list
	 * @return
	 */
	public static List<Employee> changeEmpHeadUrl(List<Employee> emp_list) {
		for (Employee employee : emp_list) {
			employee.setEmpImg(StaticStr.HEAD_URL + employee.getEmpImg());
		}
		return emp_list;
	}

	/***
	 * 判断文件格式是否合法
	 * 
	 * @param imageDate
	 * @return
	 */
	public static boolean getImageDate(String fileName) {
		for (String imge_date : ALLOWED_IMAGE) {
			if (fileName.endsWith(imge_date)) {
				return true;
			}
		}
		return false;
	}

}
