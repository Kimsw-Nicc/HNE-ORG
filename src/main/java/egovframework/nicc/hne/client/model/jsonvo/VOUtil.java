package egovframework.nicc.hne.client.model.jsonvo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VOUtil {

  public static String dateToString(Date date) {
    return dateToString(date, "yyyy-MM-dd HH:mm:ss");
  }

  public static String dateToString(Date date, String pattern) {
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    String result = "";
    try {
      result = format.format(date);
    } catch (Exception e) {
      result = format.format(new Date());
    }
    return result;
  }

  public static Date stringToDate(String string) {
    return stringToDate(string, "yyyyMMdd");
  }

  public static Date stringToDate(String string, String pattern) {
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    Date date = null;
    try {
      date = format.parse(string);
    } catch (Exception e) {
    }
    return date;
  }

  public static String dateToString(Date date, Boolean startToEnd) { // true면 시작, false면 끝ㄴ
    String time = startToEnd ? " 00:00:00" : " 23:59:59";
    return dateToString(date, "yyyy-MM-dd") + time;
  }
}
