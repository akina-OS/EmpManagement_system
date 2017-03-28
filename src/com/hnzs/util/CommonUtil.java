package com.hnzs.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

/***
 * 封装一些公共的工具方法
 * 
 * @author Akina
 *
 */
public class CommonUtil {

	/****
	 * 删除单个文件
	 * 
	 * @param filePath
	 */
	public static void delFile(String filePath) {
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			// 文件路径是文件且不为空执行删除
			file.delete();
		}
	}

	/***
	 * 保存图片
	 * 
	 * @param inputStream
	 *            图片流
	 * @param fileName
	 *            文件名
	 * @param path
	 *            保存路径
	 */
	public static void savePic(InputStream inputStream, String fileName, String path) {

		OutputStream os = null;
		try {
			// 2、保存到临时文件
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流保存到本地文件

			File tempFile = new File(path);
			if (!tempFile.exists()) {
				tempFile.mkdirs();
			}
			os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
			// 开始读取
			while ((len = inputStream.read(bs)) != -1) {
				os.write(bs, 0, len);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 完毕，关闭所有链接
			try {
				os.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getCurrentTimeToFileName() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(new Date());
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date());
	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}

	/**
	 * 获取当前时间-30天
	 * 
	 * @return
	 */
	public static String getCurrentTimeLostOneMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateNow = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(dateNow);
		// cl.add(Calendar.DAY_OF_YEAR, -1); //一天
		// cl.add(Calendar.WEEK_OF_YEAR, -1); //一周
		cl.add(Calendar.MONTH, -1); // 一个月
		Date dateFrom = cl.getTime();
		return formatter.format(dateFrom);
	}

	/**
	 * 截取 时间字符串的： 【0】年 【1】月 【2】日 【3】时 【4】分 【5】秒 【6】该月多少天
	 * 
	 * @return String[]
	 */
	public static String[] getYearToSecondDays(String string) {
		if (string == null || string.equals("")) {
			string = getCurrentTime();
		} else if (string.length() == 7) {
			string += "-00 00:00:00";
		} else if (string.length() != 19) {
			string = getCurrentTime();
		}
		String year = string.substring(0, 4);
		String month = string.substring(5, 7);
		String day = string.substring(8, 10);
		String hour = string.substring(11, 13);
		String minute = string.substring(14, 16);
		String second = string.substring(17);
		int days = getDaysByYM(Integer.parseInt(year), Integer.parseInt(month));
		String[] arrayList = { year, month, day, hour, minute, second, String.valueOf(days) };
		// System.out.println("Parameter Time StringReturn :" + string);
		return arrayList;
	}

	/**
	 * 获取当月第一天
	 * 
	 * @return
	 */
	public static String getFirstDayOfOneMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = null;
		cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 1);
		String firstday = format.format(cale.getTime());
		return firstday;
	}

	/**
	 * 获取当天是星期几
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getWeekOfDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] weekOfDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		Date d;
		try {
			d = format.parse(date);

			if (d != null) {
				calendar.setTime(d);
			}
			int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;

			if (w < 0) {

				w = 0;

			}
			return weekOfDays[w];
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将long类型的毫秒是转换成时间类型
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateFromLong(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 将long类型的毫秒转换成日期类型
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateFromLong2(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 将String 的时间类型 转换成long类型的毫秒
	 * 
	 * @param date
	 * @return
	 */
	public static long getLongFromDateStr(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long rtn = 0;
		try {
			if ("".equals(date) || "0".equals(date)) {

			} else {
				rtn = sdf.parse(date).getTime() / 1000;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		return rtn;
	}

	/**
	 * 获取时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringFromDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}

	/**
	 * 获取登录的ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		System.out.println("本次登录的用户IP地址：" + ip);
		return ip;
	}

	/**
	 * 根据输入的str 返回一个带时间戳的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String getNameFromStr(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = formatter.format(new Date());
		String rtn = str + "_" + time;
		return rtn;
	}

	/**
	 * 将图片缩放 返回流
	 * 
	 * @param img
	 *            图片流
	 * @param format
	 *            输出格式
	 * @param ww
	 *            缩放的宽
	 * @return
	 */

	public static InputStream suofangImg(InputStream img, String format, int ww) {
		try {
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			int newH = (int) ((ww * 1.0 / width) * height);
			BufferedImage tag = new BufferedImage(ww, newH, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src.getScaledInstance(ww, newH, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

			/*
			 * 将小图定向生成到文件 FileOutputStream out = new
			 * FileOutputStream("D:/71.jpg"); JPEGImageEncoder encoder =
			 * JPEGCodec.createJPEGEncoder(out); //近JPEG编码 encoder.encode(tag);
			 * out.close();
			 */

			/*
			 * 将图放到字节数组 ImageIO.write(BufferedImage image,String
			 * format,OutputStream out);方法可以很好的解决问题； 参数image表示获得的BufferedImage；
			 * 参数format表示图片的格式，比如“gif”等；
			 * 参数out表示输出流，如果要转成Byte数组，则输出流为ByteArrayOutputStream即可；
			 * 执行完后，只需要toByteArray()就能得到byte[];
			 */
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(tag, format, out);
			byte[] b = out.toByteArray();
			ByteArrayInputStream in = new ByteArrayInputStream(b, 0, b.length);
			return in;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将一个用特定字符（如 ，） 拼接的字符串 转成list
	 * 
	 * @param ids
	 * @return
	 */
	public static List<String> fromStrToList(String ids, String regex) {
		String[] arr = ids.split(regex);
		/*
		 * 2种方式都可以 List<String> list = new ArrayList<String>();
		 * Collections.addAll(list,arr);
		 */
		List<String> list = Arrays.asList(arr);
		return list;
	}

	/**
	 * 根据url 下载图片流
	 * 
	 * @param image
	 * @return
	 */
	public static InputStream download(String image) {
		InputStream is = null;
		try {
			// 构造URL
			URL url = new URL(image);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			is = con.getInputStream();
			// rtnStr = IOUtils.toString(is);
			// rtnStr = IOUtils.toString(is, con.getContentEncoding());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return is;
	}

	public static int getDaysByYM(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);// 月份小1
		int maxDate = cal.getActualMaximum(Calendar.DATE);
		// System.out.println(maxDate);
		return maxDate;
	}

	/**
	 * 根据给定的内容，写入txt文档
	 */
	public static void writeToFile(List<String> content, boolean append, String fileName) {
		// fileName="D:\\App\\people"+itemId+markId+datetime; //文件命名规则
		OutputStreamWriter osw = null;
		try {
			osw = new OutputStreamWriter(new FileOutputStream(fileName, append), "utf-8");
			for (String s : content) {
				osw.write(s + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (osw != null) {
				try {
					osw.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据utime 计算某个时间段的数量
	 * 
	 * @param times
	 *            utime的list
	 * @param count
	 *            7（周），12（月），24（日），-1（间隔为小时，都展示）
	 * @return
	 * @throws ParseException
	 */
	public static Map<Object, Integer> getCounts(List<Long> times, int count) throws ParseException {
		Map<Object, Integer> map = new LinkedHashMap<Object, Integer>();
		for (int i = 0; i < times.size(); i++) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c = Calendar.getInstance();
			String dd = format.format(times.get(i));
			c.setTime(format.parse(dd));
			int mwdh = 0;
			String key = "";
			if (count == 12) {
				mwdh = c.get(Calendar.MONTH) + 1;
			} else if (count == 24) {
				mwdh = c.get(Calendar.HOUR_OF_DAY);
				// System.out.println(mwdh);
			} else if (count == 7) {
				mwdh = c.get(Calendar.DAY_OF_WEEK) - 1;
				if (mwdh == 0) {
					mwdh = 7;
				}
			} else {
				key = dd.substring(0, 10);
			}
			if (count != -1) {
				if (count == 24) {
					for (int j = 0; j < count; j++) {
						if (j == mwdh) {
							map.put(j, (map.get(j) == null ? 0 : map.get(j)) + 1);
						} else {
							if (map.get(j) != null) {
							} else {
								map.put(j, 0);
							}
						}
					}
				} else {
					for (int j = 1; j < count + 1; j++) {
						if (j == mwdh) {
							map.put(j, (map.get(j) == null ? 0 : map.get(j)) + 1);
						} else {
							if (map.get(j) != null) {
							} else {
								map.put(j, 0);
							}
						}
					}
				}
			} else {
				if (map.get(key) != null) {
					map.put(key, map.get(key) + 1);
				} else {
					map.put(key, 1);
				}
			}
		}
		return map;
	}

	/**
	 * 执行cmd命令
	 * 
	 * @param cmdStr
	 *            要执行的cmd命令串
	 * @throws Exception
	 */
	public static void execCommand(String cmdStr) {
		try {
			// System.out.println("00000000000000000");
			Runtime.getRuntime().exec(cmdStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getUrlEncode(String origin) {
		try {
			return URLEncoder.encode(origin, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
