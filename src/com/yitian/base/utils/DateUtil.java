﻿package com.yitian.base.utils;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 *  Description of the Class
 *
 */
public class DateUtil {

	  /**
	   * 日期转换成字符 支持格式 2005-12-12 12:12:12 20051212T121212
	   *
	   *@param  s  Description of Parameter
	   *@return    Description of the Returned Value
	   */
  @SuppressWarnings("deprecation")
public static Date StrToDate(String s) {
    int year;
    int month;
    int date;
    int hour = 0;
    int min = 0;
    int sec = 0;

    if (s != null) {
      try {
        //'2005-12-12 12:12:12'
        // 0123456789012345678
        // 20051212T121212
        try {
          char ch = s.charAt(4);
          if ( (ch >= '0') && (ch <= '9')) {
            year = parseInt(s, 0, 3) - 1900;
            month = parseInt(s, 4, 5) - 1;
            date = parseInt(s, 6, 7);
            hour = parseInt(s, 9, 10);
            min = parseInt(s, 12, 13);
            sec = parseInt(s, 15, 16);
            return new Date(year, month, date, hour, min, sec);
          }
          else {
            //'2005-12-12 12:12:12.00'
            // 0123456789012345678
            StringTokenizer st = new StringTokenizer(s,
                " \t\n\r\f,-/:.", false);
            year = Integer.parseInt(st.nextToken()) - 1900;
            month = Integer.parseInt(st.nextToken()) - 1;
            date = Integer.parseInt(st.nextToken());
            hour = 0;
            min = 0;
            sec = 0;
            if (st.hasMoreTokens()) {
              hour = Integer.parseInt(st.nextToken());
              if (st.hasMoreTokens()) {
                min = Integer.parseInt(st.nextToken());
                if (st.hasMoreTokens()) {
                  sec = Integer.parseInt(st.nextToken());
                }
              }
            }
            return new Date(year, month, date, hour, min, sec);
          }
        }
        catch (Exception e) {

        }
        return new Date(java.sql.Timestamp.valueOf(s).getTime());
      }
      catch (Exception ec) {
        throw new RuntimeException("To date error:" + s);
      }
    }
    return null;
  }

  /**
   * 日期变为字符串
   *
   *@param  aDate  Description of Parameter
   *@return        Description of the Returned Value
   */
  public static String DateTimeToStr(Date aDate) {
    return DateTimeToStr(aDate, ' ');
  }

  /**
   *  日期变成ISO 格式字符串 例如 20051212T121212
   *
   *@param  aDate  Description of Parameter
   *@return        Description of the Returned Value
   */
  public static String DateTimeToISOStr(Date aDate) {
    return DateTimeToStr(aDate, 'T');
  }

  /**
   *  日期转换成字符串
   *
   *@param  aDate         日期
   *@param  dateSpan      时间分割0 为没分割
   *@param  dateTimeSpan  日期和时间的分割字符 0 没分割符
   *@param  timeSpan      时间的分割字符
   *@return               Description of the Returned Value
   */
  public static String DateTimeToStr(Date aDate, char dateSpan,
                                     char dateTimeSpan, char timeSpan) {
    if (aDate == null) {
      return null;
    }
    StringBuffer dataBuf = new StringBuffer(20);
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(aDate);
    dataBuf.append(calendar.get(Calendar.YEAR));
    if (dateSpan != 0) {
      dataBuf.append(dateSpan);
    }
    int month = calendar.get(Calendar.MONTH) + 1;
    appendInt(dataBuf, month);
    if (dateSpan != 0) {
      dataBuf.append(dateSpan);
    }
    int date = calendar.get(Calendar.DATE);
    appendInt(dataBuf, date);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int min = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);
    if (hour + min + second > 0) {
      if (dateTimeSpan != 0) {
        dataBuf.append(dateTimeSpan);
      }
      appendInt(dataBuf, hour);
      if (timeSpan != 0) {
        dataBuf.append(timeSpan);
      }
      appendInt(dataBuf, min);
      if (timeSpan != 0) {
        dataBuf.append(timeSpan);
      }
      appendInt(dataBuf, second);
    }
    return dataBuf.toString();
  }

  /**
   *  Description of the Method
   *
   *@param  aDate  Description of Parameter
   *@param  tSign  Description of Parameter
   *@return        Description of the Returned Value
   */
  public static String DateTimeToStr(Date aDate, char tSign) {
    if (tSign == ' ') {
      return DateTimeToStr(aDate, '-', ' ', ':');
    }
    else {
      return DateTimeToStr(aDate, '\0', 'T', ':');
    }
  }

  /**
   *  Description of the Method
   *
   *@param  s          Description of Parameter
   *@param  fromIndex  Description of Parameter
   *@param  toIndex    Description of Parameter
   *@return            Description of the Returned Value
   */
  static int parseInt(String s, int fromIndex, int toIndex) {
    int result = 0;
    int length = s.length();
    if ( (fromIndex >= length) || (toIndex >= length)) {
      return 0;
    }
    for (int i = fromIndex; i <= toIndex; i++) {
      int part = (s.charAt(i) - '0');
      if ( (part >= 0) && (part <= 9)) {
        result = result * 10 + part;
      }
      else {
        //System.out.println("char not a number  at " + s + " index=" + i);
      }
    }
    return result;
  }

  /**
   *  Description of the Method
   *
   *@param  buf    Description of Parameter
   *@param  nDate  Description of Parameter
   */
  private static void appendInt(StringBuffer buf, int nDate) {
    if (nDate < 10) {
      buf.append("0");
    }
    buf.append(nDate);
  }

}
