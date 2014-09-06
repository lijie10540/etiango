package com.yitian.base.utils;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

/**
 * 字符串工具
 */
public class StringUtils {

	private static final char QUOTE_ENCODE[] = "&quot;".toCharArray();
	private static final char AMP_ENCODE[] = "&amp;".toCharArray();
	private static final char LT_ENCODE[] = "&lt;".toCharArray();
	private static final char GT_ENCODE[] = "&gt;".toCharArray();
	private static final int DUMP_HEX_CHAR_COUNT = 56;
	private static MessageDigest digest = null;
	private static final int fillchar = 61;
	private static Random randGen = new Random();
	private static char numbersAndLetters[] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
			.toCharArray();
	private static final char zeroArray[] = "0000000000000000".toCharArray();
	private static final char[] base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
			.toCharArray();
	private static final char[] upcaseHexChar = "0123456789ABCDEF"
			.toCharArray();
	private static final char[] lowerHexChar = "0123456789abcdef".toCharArray();

	private static int[] hexCharCodes = new int[256];

	private static int[] base64Codes = new int[256];
	private static SimpleDateFormat m_ISO8601Local = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss");

	/**
	 * 取得当前时间格式yyyyMMddHHmmss
	 * 
	 * @return The CurrTime value
	 */
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}

	public static boolean equals(String str1, String str2) {
		return str1 == null ? false : str2 == null ? true : str1.equals(str2);
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1 == null ? false : str2 == null ? true : str1
				.equalsIgnoreCase(str2);
	}

	public static boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}

	public static boolean isNotEmpty(String str) {
		return (str != null) && (str.length() > 0);
	}

	public static String upperCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase();
	}

	public static String lowerCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase();
	}

	public static int lastIndexOf(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}
		return str.lastIndexOf(searchChar);
	}

	public static int lastIndexOf(String str, char searchChar, int startPos) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}
		return str.lastIndexOf(searchChar, startPos);
	}

	public static int lastIndexOf(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}
		return str.lastIndexOf(searchStr);
	}

	public static int lastIndexOf(String str, String searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}
		return str.lastIndexOf(searchStr, startPos);
	}

	public static boolean contains(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return false;
		}
		return str.indexOf(searchChar) >= 0;
	}

	public static boolean contains(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return false;
		}
		return str.indexOf(searchStr) >= 0;
	}

	public static int indexOfAny(String str, char[] searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length == 0)) {
			return -1;
		}
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			for (int j = 0; j < searchChars.length; j++) {
				if (searchChars[j] == ch) {
					return i;
				}
			}
		}
		return -1;
	}

	public static int indexOfAny(String str, String searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length() == 0)) {
			return -1;
		}
		return indexOfAny(str, searchChars.toCharArray());
	}

	public static int indexOfAnyBut(String str, char[] searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length == 0)) {
			return -1;
		}
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			int j = 0;
			while (searchChars[j] != ch) {
				j++;
				if (j >= searchChars.length) {
					return i;
				}
			}
		}
		return -1;
	}

	public static int indexOfAnyBut(String str, String searchChars) {
		if ((str == null) || (str.length() == 0)
				|| (searchChars == null) ||
				(searchChars.length() == 0)) {
			return -1;
		}
		for (int i = 0; i < str.length(); i++) {
			if (searchChars.indexOf(str.charAt(i)) < 0) {
				return i;
			}
		}
		return -1;
	}

	public static boolean containsOnly(String str, char[] valid) {
		if ((valid == null) || (str == null)) {
			return false;
		}
		if (str.length() == 0) {
			return true;
		}
		if (valid.length == 0) {
			return false;
		}
		return indexOfAnyBut(str, valid) == -1;
	}

	public static boolean containsOnly(String str, String validChars) {
		if ((str == null) || (validChars == null)) {
			return false;
		}
		return containsOnly(str, validChars.toCharArray());
	}

	public static boolean containsNone(String str, char[] invalidChars) {
		if ((str == null) || (invalidChars == null)) {
			return true;
		}
		int strSize = str.length();
		int validSize = invalidChars.length;
		for (int i = 0; i < strSize; i++) {
			char ch = str.charAt(i);
			for (int j = 0; j < validSize; j++) {
				if (invalidChars[j] == ch) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean containsNone(String str, String invalidChars) {
		if ((str == null) || (invalidChars == null)) {
			return true;
		}
		return containsNone(str, invalidChars.toCharArray());
	}

	public static int indexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}
		int sz = searchStrs.length;

		int ret = 2147483647;

		int tmp = 0;
		for (int i = 0; i < sz; i++) {
			String search = searchStrs[i];
			if (search != null) {
				tmp = str.indexOf(search);
				if (tmp != -1) {
					if (tmp < ret)
						ret = tmp;
				}
			}
		}
		return ret == 2147483647 ? -1 : ret;
	}

	public static int lastIndexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}
		int sz = searchStrs.length;
		int ret = -1;
		int tmp = 0;
		for (int i = 0; i < sz; i++) {
			String search = searchStrs[i];
			if (search != null) {
				tmp = str.lastIndexOf(search);
				if (tmp > ret)
					ret = tmp;
			}
		}
		return ret;
	}

	public static String substring(String str, int start) {
		if (str == null) {
			return null;
		}

		if (start < 0) {
			start = str.length() + start;
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return "";
		}

		return str.substring(start);
	}

	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}

		if (end < 0) {
			end = str.length() + end;
		}
		if (start < 0) {
			start = str.length() + start;
		}

		if (end > str.length()) {
		end = str.length();
		}

		if (start > end) {
			return "";
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

	public static String left(String str, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return "";
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(0, len);
	}

	public static String right(String str, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return "";
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(str.length() - len);
	}

	public static String mid(String str, int pos, int len) {
		if (str == null) {
			return null;
		}
		if ((len < 0) || (pos > str.length())) {
			return "";
		}
		if (pos < 0) {
			pos = 0;
		}
		if (str.length() <= pos + len) {
			return str.substring(pos);
		}
		return str.substring(pos, pos + len);
	}

	public static String substringBefore(String str, String separator) {
		if ((str == null) || (separator == null) || (str.length() == 0)) {
			return str;
		}
		if (separator.length() == 0) {
			return "";
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	public static String substringAfter(String str, String separator) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		if (separator == null) {
			return "";
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return "";
		}
		return str.substring(pos + separator.length());
	}

	public static String substringBeforeLast(String str, String separator) {
		if ((str == null) || (separator == null) || (str.length() == 0)
				|| (separator.length() == 0)) {
			return str;
		}
		int pos = str.lastIndexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	public static String substringAfterLast(String str, String separator) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		if ((separator == null) || (separator.length() == 0)) {
			return "";
		}
		int pos = str.lastIndexOf(separator);
		if ((pos == -1) || (pos == str.length() - separator.length())) {
			return "";
		}
		return str.substring(pos + separator.length());
	}

	public static String substringBetween(String str, String tag) {
		return substringBetween(str, tag, tag);
	}

	public static String substringBetween(String str, String open, String close) {
		if ((str == null) || (open == null) || (close == null)) {
			return null;
		}
		int start = str.indexOf(open);
		if (start != -1) {
			int end = str.indexOf(close, start + open.length());
			if (end != -1) {
				return str.substring(start + open.length(), end);
			}
		}
		return null;
	}

	/**
	 * Gets the CurrTimeISO8601 attribute of the StringUtils class
	 * 
	 * @param date
	 *            Description of Parameter
	 * @return The CurrTimeISO8601 value
	 */
	public static String getCurrTimeISO8601(Date date) {
		if (date == null) {
			date = new Date();
		}

		// format in (almost) ISO8601 format
		String dateStr = m_ISO8601Local.format(date);

		// remap the timezone from 0000 to 00:00 (starts at char 22)
		return dateStr.substring(0, 22) + ":" + dateStr.substring(22);
	}

	/**
	 * 取得自负s在字符串数组的编号,没找到返回-1
	 * 
	 * @param s
	 *            要查的字符串
	 * @param args
	 *            字符串
	 * @return The StrIndex value
	 */
	public static int getStrIndex(String s, String[] args) {
		int length = args.length;
		for (int i = 0; i < length; i++) {
			if (args[i].equals(s)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 字符串替换,不支持正则表达式
	 * 
	 * @param line
	 *            字符串
	 * @param oldString
	 *            老字符串
	 * @param newString
	 *            新字符串
	 * @return 替换后的字符串
	 */
	public static final String replace(String line, String oldString,
			String newString) {
		return replace(line, oldString, newString, false);
	}

	/**
	 * 字符串替换,不支持正则表达式,忽略大小写
	 * 
	 * @param line
	 *            字符串
	 * @param oldString
	 *            老字符串
	 * @param newString
	 *            新字符串
	 * @return 替换后的字符串
	 */

	public static final String replaceIgnoreCase(String line, String oldString,
			String newString) {
		return replace(line, oldString, newString, true);
	}

	/**
	 * 字符串替换,不支持正则表达式
	 * 
	 * @param line
	 *            字符串
	 * @param oldString
	 *            老字符串
	 * @param newString
	 *            新字符串
	 * @param ignoreCase
	 *            是否忽略大小写
	 * @return 替换后的字符串
	 */

	public static final String replace(String line, String oldString,
			String newString, boolean ignoreCase) {
		if ((line == null) || (oldString == null) || (newString == null)) {
			return null;
		}
		String lcLine = line;
		String lcOldString = oldString;
		if (ignoreCase) {
			lcLine = line.toLowerCase();
			lcOldString = oldString.toLowerCase();
		}
		;
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char line2[] = line.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			return buf.toString();
		} else {
			return line;
		}
	}

	/**
	 * 把二进制数组变为字符串
	 * 
	 * @param bytes
	 *            输入的数组
	 * @return 返回16进制的字符串
	 */
	public static final String encodeHex(byte bytes[]) {
		return encodeHex(bytes, true);
	}

	public static final String encodeHex(byte bytes[], boolean isUpper) {
		char[] hexChar;
		if (isUpper) {
			hexChar = upcaseHexChar;
		} else {
			hexChar = lowerHexChar;
		}

		char[] buf = new char[bytes.length * 2];

		return encodeHex(bytes, buf, hexChar, 0, bytes.length);
	}

	public static final String encodeHex(byte bytes[], char[] buf, int offset,
			int length) {
		return encodeHex(bytes, buf, upcaseHexChar, offset, length);
	}

	public static final String encodeHex(byte bytes[], char[] buf,
			char[] hexChar, int offset, int length) {
		for (int i = 0; i < length; i++) {
			int code = bytes[i + offset] & 0xff;
			buf[2 * i] = hexChar[code >> 4];
			buf[2 * i + 1] = hexChar[code & 0xf];
		}
		return new String(buf, 0, length * 2);
	}

	/**
	 * 输出二进制信息 类似DEBUG 的输出
	 * 
	 * @param bytes
	 *            参数
	 * @return Description of the Returned Value
	 */
	public static final String dumpHex(byte bytes[]) {
		int bytepos;
		int bufpos;
		int linecount = (bytes.length + 15) / 16;
		char[] buf = new char[linecount * DUMP_HEX_CHAR_COUNT];
		byte[] bs = new byte[16];

		bytepos = 0;
		bufpos = 0;
		for (int i = 0; i < linecount; i++) {
			int addr = i * 16;
			buf[bufpos++] = upcaseHexChar[(addr >> 12) & 0xf];
			buf[bufpos++] = upcaseHexChar[(addr >> 8) & 0xf];
			buf[bufpos++] = upcaseHexChar[(addr >> 4) & 0xf];
			buf[bufpos++] = upcaseHexChar[(addr) & 0xf];
			buf[bufpos++] = ' ';
			buf[bufpos++] = ' ';
			for (int j = 0; j < 16; j++, bytepos++) {
				if (bytepos < bytes.length) {
					int code = bytes[bytepos] & 0xff;
					bs[j] = bytes[bytepos];
					buf[bufpos++] = upcaseHexChar[code >> 4];
					buf[bufpos++] = upcaseHexChar[code & 0xf];
					if (j == 7) {
						buf[bufpos++] = '-';
					} else {
						buf[bufpos++] = ' ';
					}
				} else {
					buf[bufpos++] = ' ';
					buf[bufpos++] = ' ';
					buf[bufpos++] = ' ';
					bs[j] = ' ';
				}
			}
			buf[bufpos++] = '\r';
			buf[bufpos++] = '\n';
		}
		return new String(buf, 0, bufpos);
	}

	/**
	 * 解码16进字符串到数组
	 * 
	 * @param hex
	 *            字符串
	 * @return 二进制数组
	 */
	public static final byte[] decodeHex(String hex) {
		byte bytes[] = new byte[hex.length() / 2];
		decodeHex(hex, bytes);
		return bytes;
	}

	public static final int decodeHex(String hex, byte bytes[]) {
		int byteCount = 0;
		int length = hex.length();
		for (int i = 0; i < length; i += 2) {
			byte newByte = 0;
			newByte |= hexCharCodes[hex.charAt(i)];
			newByte <<= 4;
			newByte |= hexCharCodes[hex.charAt(i + 1)];
			bytes[byteCount] = newByte;
			byteCount++;
		}

		return byteCount;
	}

	/**
	 * 用BASE64编码字符串
	 * 
	 * @param data
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static String encodeBase64(String data) {
		return encodeBase64(data.getBytes());
	}

	/**
	 * BASE64 编码二进制数组
	 * 
	 * @param data
	 *            数据
	 * @param lineBreak
	 *            是否加回车
	 * @return Description of the Returned Value
	 */
	public static String encodeBase64(byte data[], boolean lineBreak) {
		if (data == null)
			return null;
		int len = data.length;
		char[] buf = new char[(len / 3 + 1) * 4 + len / 57 + 1];
		int pos = 0;
		for (int i = 0; i < len; i++) {
			int c = data[i] >> 2 & 0x3f;
			buf[pos++] = (base64Chars[c]);
			c = data[i] << 4 & 0x3f;
			if (++i < len) {
				c |= data[i] >> 4 & 0xf;
			}
			buf[pos++] = (base64Chars[c]);
			if (i < len) {
				c = data[i] << 2 & 0x3f;
				if (++i < len) {
					c |= data[i] >> 6 & 3;
				}
				buf[pos++] = (base64Chars[c]);
			} else {
				i++;
				buf[pos++] = ('=');
			}
			if (i < len) {
				c = data[i] & 0x3f;
				buf[pos++] = (base64Chars[c]);
			} else {
				buf[pos++] = ('=');
			}
			;
			if (lineBreak && (i % 57 == 56)) {
				buf[pos++] = '\n';
			}
		}
		if ((pos > 0) && lineBreak && (buf[pos - 1] != '\n'))
			buf[pos] = '\n';
		return new String(buf, 0, pos);
	}

	/**
	 * 二进制编码不要换行
	 * 
	 * @param data
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static String encodeBase64(byte data[]) {
		return encodeBase64(data, false);
	}

	/**
	 * 解码BASE64 编码
	 * 
	 * @param data
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static byte[] decodeBase64(String data) {
		return decodeBase64(data, 0);
	}

	/**
	 * 解码base64信息
	 * 
	 * @param data
	 *            字符串
	 * @param offset
	 *            开始位置
	 * @return 解码后的信息
	 */
	public static byte[] decodeBase64(String data, int offset) {
		if (data == null)
			return null;
		int len = data.length();
		byte[] result = new byte[((len * 3) / 4)];
		int pos = 0;
		if (offset >= len) {
			return null;
		}
		for (int i = offset; i < len; i++) {

			int c = base64Codes[data.charAt(i)];
			if (c == -1) {
				continue;
			}
			i++;
			int c1 = base64Codes[data.charAt(i)];
			c = c << 2 | c1 >> 4 & 3;
			result[pos++] = (byte) c;
			if (++i < len) {
				c = data.charAt(i);
				if (61 == c) {
					break;
				}
				c = base64Codes[data.charAt(i)];
				c1 = c1 << 4 & 0xf0 | c >> 2 & 0xf;
				result[pos++] = (byte) c1;
			}
			if (++i >= len) {
				continue;
			}
			c1 = data.charAt(i);
			if (61 == c1) {
				break;
			}
			c1 = base64Codes[data.charAt(i)];
			c = c << 6 & 0xc0 | c1;
			result[pos++] = (byte) c;
		}
		if (result.length != pos) {
			byte[] result2 = new byte[pos];
			System.arraycopy(result, 0, result2, 0, pos);
			result = result2;
		}
		return result;
	}

	static public char randomChar() {
		return numbersAndLetters[randGen.nextInt(numbersAndLetters.length)];
	}

	/**
	 * 产生随机字符串
	 * 
	 * @param length
	 *            长度
	 * @return Description of the Returned Value
	 */
	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		char randBuffer[] = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = randomChar();
		}

		return new String(randBuffer);
	}

	/**
	 * 生成空格字符串
	 * 
	 * @param length
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static String genEmptyString(int length) {
		char[] cs = new char[length];
		for (int i = 0; i < length; i++) {
			cs[i] = ' ';
		}
		return new String(cs);
	}

	/**
	 * 字符串是否为 null或者空
	 * 
	 * @param param
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static boolean nullOrBlank(String param) {
		return param == null || param.trim().equals("");
	}

	/**
	 * 解释整数
	 * 
	 * @param param
	 *            字符串
	 * @param defValue
	 *            省却值
	 * @return Description of the Returned Value
	 */
	public static int parseInt(String param, int defValue) {
		int i = defValue;
		try {
			i = Integer.parseInt(param);
		} catch (Exception e) {
		}
		return i;
	}

	/**
	 * 解释long
	 * 
	 * @param param
	 *            字符串参数
	 * @param defValue
	 *            省却值
	 * @return Description of the Returned Value
	 */
	public static long parseLong(String param, long defValue) {
		long l = defValue;
		try {
			l = Long.parseLong(param);
		} catch (Exception e) {
		}
		return l;
	}

	/**
	 * 解释浮点字符串
	 * 
	 * @param param
	 *            输入参数
	 * @param defValue
	 *            省却值
	 * @return Description of the Returned Value
	 */
	public static float parseFloat(String param, float defValue) {
		float f = defValue;
		try {
			f = Float.parseFloat(param);
		} catch (Exception e) {
		}
		return f;
	}

	/**
	 * 解释Double
	 * 
	 * @param param
	 *            输入参数
	 * @param defValue
	 *            省却值
	 * @return Description of the Returned Value
	 */

	public static double parseDouble(String param, double defValue) {
		double d = defValue;
		try {
			d = Double.parseDouble(param);
		} catch (Exception e) {
		}
		return d;
	}

	/**
	 * 解释 boolean 省却为false
	 * 
	 * @param param
	 *            输入参数
	 * @return Description of the Returned Value
	 */
	public static boolean parseBoolean(String param) {
		return parseBoolean(param, false);
	}

	/**
	 * 解释 boolean
	 * 
	 * @param param
	 *            输入参数
	 * @param value
	 *            省却值
	 * @return Description of the Returned Value
	 */

	public static boolean parseBoolean(String param, boolean value) {
		if (nullOrBlank(param)) {
			return value;
		}
		switch (param.charAt(0)) {
		case '1':
		case 'T':
		case 'Y':
		case 't':
		case 'y':
			return true;
		case '0':
		case 'F':
		case 'N':
		case 'f':
		case 'n':
			return false;
		}
		return value;
	}

	/**
	 * URLEncoder.encode(str, "GBK"); 错误返回null
	 * 
	 * @param str
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static String encodeUrlString(String s) {
		return encodeUrlString(s, true);
	}

	public static String encodeUrlString(String s, boolean isUpper) {
		char[] hexChar;
		if (isUpper) {
			hexChar = upcaseHexChar;
		} else {
			hexChar = lowerHexChar;
		}

		StringBuffer sbuf = new StringBuffer();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			int ch = s.charAt(i);
			if (ch == ' ') { // space : map to '+'
				sbuf.append('+');
			} else if ('A' <= ch && ch <= 'Z') { // 'A'..'Z' : as it was
				sbuf.append((char) ch);
			} else if ('a' <= ch && ch <= 'z') { // 'a'..'z' : as it was
				sbuf.append((char) ch);
			} else if ('0' <= ch && ch <= '9') { // '0'..'9' : as it was
				sbuf.append((char) ch);
			} else if (ch == '-'
					|| ch == '_' // unreserved : as it was
					|| ch == '.' || ch == '!' || ch == '~' || ch == '*'
					|| ch == '\'' || ch == '(' || ch == ')') {
				sbuf.append((char) ch);
			} else if (ch <= 0x007F) { // other ASCII : map to %XX
				sbuf.append('%');
				sbuf.append(hexChar[(ch >>> 4) & 0xf]);
				sbuf.append(hexChar[ch & 0xf]);
			} else { // unicode : map to %uXXXX
				sbuf.append('%');
				sbuf.append('u');
				sbuf.append(hexChar[(ch >>> 12) & 0xf]);
				sbuf.append(hexChar[(ch >>> 8) & 0xf]);
				sbuf.append(hexChar[(ch >>> 4) & 0xf]);
				sbuf.append(hexChar[ch & 0xf]);
			}
		}
		return sbuf.toString();

	}

	/**
	 * 用gbk url解码错误返回null
	 * 
	 * @param str
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static String decodeUrlString(String s) {
		StringBuffer sbuf = new StringBuffer();
		int i = 0;
		int len = s.length();
		while (i < len) {
			int ch = s.charAt(i);
			if (ch == '+') { // + : map to ' '
				sbuf.append(' ');
			} else if ('A' <= ch && ch <= 'Z') { // 'A'..'Z' : as it was
				sbuf.append((char) ch);
			} else if ('a' <= ch && ch <= 'z') { // 'a'..'z' : as it was
				sbuf.append((char) ch);
			} else if ('0' <= ch && ch <= '9') { // '0'..'9' : as it was
				sbuf.append((char) ch);
			} else if (ch == '-'
					|| ch == '_' // unreserved : as it was
					|| ch == '.' || ch == '!' || ch == '~' || ch == '*'
					|| ch == '\'' || ch == '(' || ch == ')') {
				sbuf.append((char) ch);

			} else if ((ch == '%') && (i < s.length() - 1)) {
				int cint = 0;
				if ('%' == s.charAt(i + 1) || '\'' == s.charAt(i + 1)) {
					sbuf.append((char) ch);
				} else {
					if ('u' != s.charAt(i + 1)) { // %XX : map to ascii(XX)
						cint = (cint << 4) | hexCharCodes[s.charAt(i + 1)];
						cint = (cint << 4) | hexCharCodes[s.charAt(i + 2)];
						i += 2;
					} else { // %uXXXX : map to unicode(XXXX)
						cint = (cint << 4) | hexCharCodes[s.charAt(i + 2)];
						cint = (cint << 4) | hexCharCodes[s.charAt(i + 3)];
						cint = (cint << 4) | hexCharCodes[s.charAt(i + 4)];
						cint = (cint << 4) | hexCharCodes[s.charAt(i + 5)];
						i += 5;
					}
					sbuf.append((char) cint);
				}
			} else {
				sbuf.append((char) ch);
			}
			i++;
		}
		return sbuf.toString();

	}

	/**
	 * 把对象数组连接成字符串
	 * 
	 * @param list
	 *            Description of Parameter
	 * @param separator
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */

	public static String join(Object list[], String separator) {
		return join(java.util.Arrays.asList(list).iterator(), separator);
	}

	/**
	 * Iterator 连接成字符串
	 * 
	 * @param iterator
	 *            Description of Parameter
	 * @param separator
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static String join(Iterator iterator, String separator) {
		StringBuffer buf = new StringBuffer();
		while (iterator.hasNext()) {
			buf.append(iterator.next());
			if (iterator.hasNext()) {
				buf.append(separator);
			}
		}
		;
		return buf.toString();
	}

	/**
	 * 去掉全角的汉字数字
	 * 
	 * @param text
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static synchronized String formatChineseString(String text) {
		String ret = text;
		if (ret == null) {
			return ret;
		} else {
			ret = replace(ret, "０", "0");
			ret = replace(ret, "１", "1");
			ret = replace(ret, "２", "2");
			ret = replace(ret, "３", "3");
			ret = replace(ret, "４", "4");
			ret = replace(ret, "５", "5");
			ret = replace(ret, "６", "6");
			ret = replace(ret, "７", "7");
			ret = replace(ret, "８", "8");
			ret = replace(ret, "９", "9");
			ret = replace(ret, "＃", "#");
			return ret;
		}
	}

	/**
	 * 转变字符串的编码类型
	 * 
	 * @param strvalue
	 *            字符串
	 * @param fromEncoding
	 *            开始编码
	 * @param toEncoding
	 *            转换后的编码
	 * @return Description of the Returned Value
	 */
	public static String toEncoding(String strvalue, String fromEncoding,
			String toEncoding) {
		try {
			if (strvalue == null) {
				return null;
			} else {
				strvalue = new String(strvalue.getBytes(fromEncoding),
						toEncoding);
				return strvalue;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 把ISO-8859-1编码转换成GBK编码
	 * 
	 * @param strvalue
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static String toChinese(String strvalue) {
		return toEncoding(strvalue, "ISO-8859-1", "GBK");
	}

	/**
	 * 把GBK编码转换成ISO-8859-1编码
	 * 
	 * @param strvalue
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static String toLatin(String strvalue) {
		return toEncoding(strvalue, "GBK", "ISO-8859-1");
	}

	/**
	 * 比较字符串特定编码下比较二精制数据 如按GBK比较 compareString("中国", "美国", "GBK") 大体按拼音比较
	 * 
	 * @param s
	 *            Description of Parameter
	 * @param s2
	 *            Description of Parameter
	 * @param encoding
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static int compareString(String s, String s2, String encoding) {

		if (s2 == null) {
			if (s != null) {
				return 1;
			} else {
				return 0;
			}
		} else if (s == null) {
			return -1;
		}

		try {
			byte v1[] = s.getBytes(encoding);
			byte v2[] = s2.getBytes(encoding);
			int i = v1.length;
			int j = v2.length;
			int n = Math.min(i, j);
			int k = 0;
			int lim = n;
			while (k < lim) {
				int c1 = v1[k] & 0xff;
				int c2 = v2[k] & 0xff;
				if (c1 != c2) {
					return c1 - c2;
				}
				k++;
			}
			;
			return i - j;
		} catch (Exception e) {
			return 0;
		}
	}

	public static String escapeString(String str) {
		if (str == null) {
			return null;
		}
		int len = str.length();
		if (len == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(len * 2);
		char ch;
		for (int i = 0; i < len; i++) {
			ch = str.charAt(i);
			switch (ch) {
			case '\b':
				buf.append("\\b");
				break;
			case '\f':
				buf.append("\\f");
				break;
			case '\\':
				buf.append("\\\\");
				break;
			case '\t':
				buf.append("\\t");
				break;
			case '\n':
				buf.append("\\n");
				break;
			case '\r':
				buf.append("\\r");
				break;

			default:
				if ((ch >= ' ' && ch < 127) && (ch != '\'') && (ch != '"')) {
					buf.append(ch);
				} else {
					buf.append('\\');
					buf.append('u');
					buf.append(upcaseHexChar[((ch >> 12) & 0xF)]);
					buf.append(upcaseHexChar[((ch >> 8) & 0xF)]);
					buf.append(upcaseHexChar[((ch >> 4) & 0xF)]);
					buf.append(upcaseHexChar[((ch >> 0) & 0xF)]);
				}
			}
		}
		return buf.toString();
	}

	/**
	 * 比较两个字符串是否相同
	 * 
	 * @param s1
	 *            Description of Parameter
	 * @param s2
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static boolean strEquals(String s1, String s2) {
		if ((s1 != null) && (s2 != null)) {
			return s1.equals(s2);
		}
		return (s1 == s2);
	}

	public static String trimToByteSize(String s, String encoding, int btyeSize) {
		if (s != null) {
			int pos = 0;
			int charSize = 2;
			int strlen = s.length();
			if (encoding.charAt(0) == 'U') {
				charSize = 3;
			}
			for (int i = 0; i < strlen; i++) {

				if (pos > btyeSize) {
					return s.substring(0, i - 1);
				}

				if ((s.charAt(i) & 0xffffff00) != 0) {
					pos += charSize;
				} else {
					pos++;
				}
			}
		}
		return s;
	}

	public static boolean objectEquals(Object param0, Object pram1) {
		if ((param0 != null) && (pram1 != null)) {
			return param0.equals(pram1);
		}
		return ((param0 == null) && (pram1 == null));
	}

	static public Object parseEnum(String s, Class enumClass, Object defaultEnum) {
		if (s != null) {
			Field[] fs = enumClass.getDeclaredFields();
			try {
				for (Field f : fs) {
					if (f.getType() == enumClass) {
						if (s.equals(f.getName())) {
							return f.get(null);
						}
					}
				}
			} catch (Exception e) {
			}
			StringBuilder sb = new StringBuilder();
			sb.append("??版涓剧被??璇?" + s + "涓??[");

			for (Field f : fs) {
				if (f.getType() == enumClass) {
					sb.append(f.getName());
					sb.append(",");
				}
			}
			sb.append("]???");
			throw new RuntimeException(sb.toString());
		}

		return defaultEnum;
	}

	static {
		for (int i = 0; i < 256; i++) {
			hexCharCodes[i] = base64Codes[i] = -1;
		}
		for (int i = 0; i < base64Chars.length; i++) {
			base64Codes[base64Chars[i]] = (byte) i;
		}
		for (int i = 0; i < upcaseHexChar.length; i++) {
			hexCharCodes[upcaseHexChar[i]] = (byte) i;
		}
		for (int i = 0; i < lowerHexChar.length; i++) {
			hexCharCodes[lowerHexChar[i]] = (byte) i;
		}
	}

}
