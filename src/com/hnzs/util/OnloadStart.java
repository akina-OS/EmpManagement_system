package com.hnzs.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * tomcat启动运行
 * 
 * @author Akina
 *
 */
public class OnloadStart extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -289299507912929481L;

	/**
	 * tomcat服务启动执行此方法 读取配置文件，将配置文件里面的信息初始化到PROPERTIES_MAP里面
	 */
	public void init() throws ServletException {
		PropertiesUtils.onLoadMap();
	}

}
