package com.hnzs.util;

import java.security.MessageDigest;

/**
 * 根据规则 和md5 加密密码
 * 
 * @author Akina
 * 
 * 
 */
public class MD5Util {
	/**
	 * 按规则 +MD5 加密 if (j == 0) { result += "upload"; result = GetMD5(result);}
	 * if (j == 1) { result = "upload" + result; result = GetMD5(result); if (j
	 * == 2) { result = GetMD5(result); }
	 * 
	 * @param inStr
	 * @return
	 */
	public static String getFinalStr(String inStr) {
		String result = "";
		for (int j = 0; j < 3; j++) {
			if (j == 0) {
				inStr += "upload";
				result = GetMD5(inStr);
			}
			if (j == 1) {
				result = "upload" + result;
				result = GetMD5(result);
			}
			if (j == 2) {
				result = GetMD5(result);
			}
		}
		return result;
	}

	/**
	 * MD5 加密
	 * 
	 * @param inStr
	 * @return
	 */
	public static String GetMD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	// public static void main(String[] args) {
	// System.out.println(getFinalStr("scorpio123"));
	// }
}
