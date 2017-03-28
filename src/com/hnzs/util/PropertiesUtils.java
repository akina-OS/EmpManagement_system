package com.hnzs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/***
 * 读取properties文件
 * 
 * @author 王维杰
 *
 */
public class PropertiesUtils {
	/***
	 * properties的键值对MAP
	 */
	public static Map<String, String> PROPERTIES_MAP = new HashMap<String, String>();

	/***
	 * 读取propertise文件并且根据key取出value
	 * 
	 * @param filePath
	 *            资源文件路径
	 * @param key
	 *            需要值得key
	 * @return
	 */
	public static String GetValueByKey(String filePath, String key) {
		Properties pps = new Properties();
		try {
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
			pps.load(in);
			String value = pps.getProperty(key);
			in.close();
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 初始化PROPERTIES_MAP
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void onLoadMap() {
		Properties pps = new Properties();
		try {
			InputStream in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("system_parameter.properties");
			pps.load(in);
			PROPERTIES_MAP = new HashMap<String, String>((Map) pps);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
