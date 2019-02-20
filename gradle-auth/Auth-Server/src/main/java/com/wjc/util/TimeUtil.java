package com.wjc.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *********************************************** 
 * @Copyright (c) by xxx
 * @Create Date: 2014-12-01 下午2:12:52
 * @Create Author: yanghl
 * @File Name: TimeUtil
 * @Function: 对Java中的时间处理进行封装
 * @Last version: 1.0
 * @Last Update Date:
 * @Change Log:
 ************************************************* 
 */
public class TimeUtil extends org.apache.commons.lang3.time.DateFormatUtils {

	private TimeUtil() {
	}

	/**
	 * 秒的定义
	 */
	public static final long SECOND = 1000;

	/**
	 * 分的定义
	 */
	public static final long MINUTE = SECOND * 60;

	/**
	 * 小时的定义
	 */
	public static final long HOUR = MINUTE * 60;

	/**
	 * 日的定义
	 */
	public static final long DAY = HOUR * 24;

	/**
	 * 周的定义
	 */
	public static final long WEEK = DAY * 7;

	/**
	 * 时间格式：yyyy-MM-dd HH:mm:ss。
	 */
	public final static String theTimeFormat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 时间格式：yyyy-MM-dd HH:mm:ss。
	 */
	public final static String hoursFormat = "yyyy-MM-dd HH";

	/**
	 * 时间格式：yyyyMMddHHmmss。
	 */
	public final static String otherTimeFormat = "yyyyMMddHHmmss";

	/**
	 * 时间格式：yyyyMM。
	 */
	public final static String yearMonthTimeFormat = "yyyyMM";

	/**
	 * 时间格式：yyyyMMdd。
	 */
	public final static String yearMonthDayTimeFormat = "yyyyMMdd";

	/**
	 * 时间格式：yyyy-MM-dd。
	 */
	public final static String yearMonthDayFormat = "yyyy-MM-dd";

	/**
	 * 是否使用存放各种时间格式的集合自定义标志
	 */
	public static final boolean useFastDateFormatter = true;

	/**
	 * 格式化和解析日期的对象
	 */
	private final static SimpleDateFormat theTimeFormator = new SimpleDateFormat(theTimeFormat);

	/**
	 * 日期格式化对象，将当前日期格式化成ddHHmmss格式，用于生成文件名。
	 */
	public final static DateFormat nameDf = new SimpleDateFormat("ddHHmmss");

	/**
	 * 日期格式化对象，将当前日期格式化成yyyyMM格式，用于生成目录。
	 */
	public static final DateFormat pathDf = new SimpleDateFormat(yearMonthTimeFormat);

	/**
	 * 存放各种时间格式的集合自定义
	 */
	private static Map<String, Object> theFastTimeFormatterMap = new HashMap<String, Object>();

	// --start--其他工具类使用-------------------------------------------------------------------
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

	private static SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
	private static SimpleDateFormat OnlyMONTHFormat = new SimpleDateFormat("MM");
	private static SimpleDateFormat OnlyDAYFormat = new SimpleDateFormat("dd");

	private static SimpleDateFormat hourFormat = new SimpleDateFormat("yyyy-MM-dd HH:00");

	private static SimpleDateFormat minuteFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private static SimpleDateFormat shortTimeFormat = new SimpleDateFormat("HH:mm:ss");

	private static SimpleDateFormat millisecondFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
	// --end---------------------------------------------------------------------

	/**
	 * 日期/时间格式化子类对象的实现
	 * 
	 * @param format
	 *            时间格式
	 * @return 日期/时间格式化子类对象
	 */
	public static final DateFormat getTimeFormatter(String format) {
		if (useFastDateFormatter) {
			DateFormat sdf = (DateFormat) theFastTimeFormatterMap.get(format);
			if (sdf != null) {
				return sdf;
			}
		}

		return new SimpleDateFormat(format);
	}

	/**
	 * 格式化和解析日期的对象获取
	 * 
	 * @param format
	 *            时间格式
	 * @return 格式化和解析日期的对象
	 */
	public static SimpleDateFormat newTimeFormatter(String format) {
		return new SimpleDateFormat(format);
	}

	/**
	 * 判断时间是否相等。 由于系统中的时间不需要很高的精度，因此我们提供了特定的比较方法。
	 * 
	 * @param c1
	 *            判断时间对象是否相等的第1个参数
	 * @param c2
	 *            判断时间对象是否相等的第2个参数
	 * @return =true：相等 =false：不相等
	 */
	public static boolean equals(Calendar c1, Calendar c2) {
		if (c1 == c2) {
			return true;
		}
		if ((null == c1) || (null == c2)) {
			return false;
		}

		final long t1 = c1.getTime().getTime() / 1000;
		final long t2 = c2.getTime().getTime() / 1000;
		return (t1 == t2);
	}

	/**
	 * Calendar对象克隆。
	 * 
	 * @param cal
	 *            原始时间对象
	 * @return 已经克隆好的时间对象
	 */
	public static Calendar clone(Calendar cal) {
		Calendar c = Calendar.getInstance();
		c.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND));
		return c;
	}

	// /////////////////////////////////////////////////////////////////

	/**
	 * 获取系统时间Timestamp
	 * 
	 * @return
	 */
	public static Date getSysTimestamp() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 按照formator格式，把cal对象转换成字符串
	 * 
	 * @param cal
	 *            需要转换的时间对象
	 * @param formator
	 *            转换的时间格式
	 * @return 时间对象的格式字符串
	 */
	public static String toString(Calendar cal, DateFormat formator) {
		if (null == cal) {
			return "";
		}
		return formator.format(cal.getTime());
	}

	/**
	 * 按照formator格式，把cal对象转换成字符串
	 * 
	 * @param cal
	 *            需要转换的时间对象
	 * @param format
	 *            转换的时间格式
	 * @return 时间对象的格式字符串
	 */
	public static String toString(Calendar cal, String format) {
		if (null == cal) {
			return "";
		}
		return toString(cal, getTimeFormatter(format));
	}

	/**
	 * 按照formator格式，把date对象转换成字符串
	 * 
	 * @param date
	 *            需要转换的时间对象
	 * @param formator
	 *            转换的时间格式
	 * @return 时间对象的格式字符串
	 */
	public static String toString(Date date, DateFormat formator) {
		if (null == date) {
			return "";
		}
		return formator.format(date);
	}

	/**
	 * 按照format格式，把date对象转换成字符串
	 * 
	 * @param date
	 *            需要转换的时间对象
	 * @param format
	 *            转换的时间格式
	 * @return 时间对象的格式字符串
	 */
	public static String toString(Date date, String format) {
		if (null == date) {
			return "";
		}
		if (MyStringUtil.empty(format)) {
			format = "yyyy-MM-dd";
		}
		return toString(date, getTimeFormatter(format));
	}

	/**
	 * 把系统当前时间按照指定的时间格式进行转换
	 * 
	 * @param format
	 *            时间格式
	 * @return 当前时间格式字符串
	 */
	public static String toString(String format) {
		return toString(Calendar.getInstance(), format);
	}

	/**
	 * 把指定时间从一种格式转换成指定的另外一种时间格式
	 * 
	 * @param time
	 *            指定的时间
	 * @param fromFormat
	 *            元时间格式
	 * @param toFormat
	 *            目标时间格式
	 * @return 已经转换成目标时间格式的字符串
	 * @throws Exception
	 *             转换失败错误
	 */
	public static String toString(String time, String fromFormat, String toFormat) {
		try {
			return toString(toCalendar(time, fromFormat), toFormat);
		} catch (Exception e) {
			return time;
		}
	}

	/**
	 * 把时间对象转换成yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param cal
	 *            转换的时间
	 * @return 时间格式是yyyy-MM-dd HH:mm:ss的字符串
	 */
	public static String toString(Calendar cal) {
		return toString(cal, theTimeFormator);
	}

	/**
	 * 把时间对象转换成yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param date
	 *            转换的时间
	 * @return 时间格式是yyyy-MM-dd HH:mm:ss的字符串
	 */
	public static String toString(Date date) {
		return toString(date, theTimeFormator);
	}

	/**
	 * 把毫秒对象转换成yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param millSeconds
	 *            转换的毫秒
	 * @return 时间格式是yyyy-MM-dd HH:mm:ss的字符串
	 */
	public static String toString(long millSeconds) {
		Date date = new Date(millSeconds);
		return toString(date);
	}

	/**
	 * 把毫秒按照指定的格式进行时间转换
	 * 
	 * @param millSeconds
	 *            转换的毫秒
	 * @param format
	 *            指定的时间格式
	 * @return 时间格式是指定的字符串
	 */
	public static String toString(long millSeconds, String format) {
		Date date = new Date(millSeconds);
		return toString(date, format);
	}

	// /////////////////////////////////////////////////////////////////
	/**
	 * 将时间对象转化为数字
	 * 
	 * @param cal
	 *            转换的数字
	 * @param format
	 *            格式，例如"yyyyMMddHH"
	 * @return 类型是数字的时间格式
	 */
	public static long toNumber(Calendar cal, String format) {
		String time = toString(cal, format);
		return Long.parseLong(time);
	}

	/**
	 * 将时间对象转化为数字
	 * 
	 * @param strTime
	 *            转换的时间字符串
	 * @param timeFormat
	 *            格式，例如"yyyyMMddHH"
	 * @param numberFormat
	 *            要转换的数字格式
	 * @return 类型是数字的时间格式
	 * @throws Exception
	 *             转换失败错误
	 */
	public static long toNumber(String strTime, String timeFormat, String numberFormat) {
		try {
			return toNumber(toCalendar(strTime, timeFormat), numberFormat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 把Calendar对象转换成长整形数字
	 * 
	 * @param time
	 *            时间对象
	 * @return 长整型时间数字对象
	 */
	public static Long toLong(Calendar time) {
		try {
			return new Long(time.getTime().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把Date对象转换成长整形数字
	 * 
	 * @param time
	 *            时间对象
	 * @return 长整型时间数字对象
	 */
	public static Long toLong(Date time) {
		try {
			return new Long(time.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将Date型转换为指定格式的long型
	 * 
	 * @param time
	 *            Date型对象
	 * @param format
	 *            指定格式
	 * @return 转换后的时间值
	 */
	public static long toLong(Date time, String format) {
		String str = toString(time, format);
		return NumberUtil.toLong(str);
	}

	/**
	 * 获取Calendar对象的年、月、日
	 * 
	 * @param cal
	 *            时间对象
	 * @return 已经转换后的long型数字
	 */
	public static long toNumberOfyyyyMMdd(Calendar cal) {
		return (cal.get(Calendar.YEAR) * 100 + cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DAY_OF_MONTH);
	}

	// /////////////////////////////////////////////////////////////////
	/**
	 * 将字符串转化为时间对象
	 * 
	 * @param time
	 *            转换的时间
	 * @param formator
	 *            指定格式
	 * @return 带指定格式的Calendar时间对象
	 * @throws Exception
	 *             转换失败错误
	 */
	public static Calendar toRawCalendar(String time, DateFormat formator) throws Exception {
		Calendar cal = Calendar.getInstance();
		Date d = formator.parse(time);
		cal.setTime(d);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	/**
	 * 把字符串按照指定的转换格式转换为Calendar
	 * 
	 * @param time
	 *            时间字符串
	 * @param formator
	 *            指定的格式
	 * @return 带指定格式的Calendar对象
	 */
	public static Calendar toCalendar(String time, DateFormat formator) {

		try {
			return toRawCalendar(time, formator);
		} catch (Exception ex) {
			ex.printStackTrace();
			return Calendar.getInstance();
		}
	}

	/**
	 * 把字符串按照指定的转换格式转换为Calendar
	 * 
	 * @param time
	 *            时间字符串
	 * @param format
	 *            指定的格式
	 * @return 带指定格式的Calendar对象
	 * @throws Exception
	 *             转换失败错误
	 */
	public static Calendar toRawCalendar(String time, String format) throws Exception {
		return toRawCalendar(time, getTimeFormatter(format));
	}

	/**
	 * 把字符串按照指定的转换格式转换为Calendar
	 * 
	 * @param time
	 *            时间字符串
	 * @param format
	 *            指定的格式
	 * @return 带指定格式的Calendar对象
	 * @throws Exception
	 *             转换失败错误
	 */
	public static Calendar toCalendar(String time, String format) throws Exception {
		return toRawCalendar(time, getTimeFormatter(format));
	}

	/**
	 * 把long型数字按照指定格式转换为Calendar对象
	 * 
	 * @param time
	 *            时间数字
	 * @param format
	 *            指定的格式
	 * @return 带指定格式的Calendar对象
	 * @throws Exception
	 *             转换失败错误
	 */
	public static Calendar toRawCalendar(long time, String format) throws Exception {
		return toRawCalendar(String.valueOf(time), getTimeFormatter(format));
	}

	/**
	 * 把long型时间按照指定格式转换为Calendar对象
	 * 
	 * @param time
	 *            long型时间值
	 * @param format
	 *            指定的格式
	 * @return 带指定格式的Calendar对象
	 */
	public static Calendar toCalendar(long time, String format) {
		return toCalendar(String.valueOf(time), getTimeFormatter(format));
	}

	/**
	 * 把字符串转换为yyyy-MM-dd HH:mm:ss格式的Calendar对象
	 * 
	 * @param time
	 *            字符串时间
	 * @return 带指定格式的Calendar对象
	 * @throws Exception
	 *             转换错误
	 */
	public static Calendar toRawCalendar(String time) throws Exception {
		return toRawCalendar(time, theTimeFormator);
	}

	/**
	 * 将未指定格式的日期字符串转化成java.util.Date类型日期对象 <br>
	 * 
	 * @param date,待转换的日期字符串
	 * @return
	 * @throws ParseException
	 */
	public static Calendar toCalendar(String date) {
		String parse = date;
		parse = parse.replaceFirst("^[0-9]{4}([^0-9]?)", "yyyy$1");
		parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
		parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
		return toCalendar(date, new SimpleDateFormat(parse));
	}

	/**
	 * 生成位于所有参数范围之内的时间。 同时，调整参数以保证时间的语义符合参数语义，例如month为6而day为31时，将调整day为30。
	 * 
	 * @param year
	 *            2000~2010
	 * @param month
	 *            1~12
	 * @param day
	 *            1~31
	 * @param hour
	 *            0~23
	 * @param minute
	 *            0~59
	 * @param second
	 *            0~59
	 * @return
	 */
	public static Calendar toBoundaryCalendar(int year, int month, int day, int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, NumberUtil.adjustRange(year, 2000, 2116));
		cal.set(Calendar.MONTH, NumberUtil.adjustRange(month, 1, 12) - 1);
		cal.set(Calendar.DAY_OF_MONTH, NumberUtil.adjustRange(day, 1, cal.getActualMaximum(Calendar.DAY_OF_MONTH)));
		cal.set(Calendar.HOUR_OF_DAY, NumberUtil.adjustRange(hour, 0, 23));
		cal.set(Calendar.MINUTE, NumberUtil.adjustRange(minute, 0, 59));
		cal.set(Calendar.SECOND, NumberUtil.adjustRange(second, 0, 59));
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	/**
	 * 把字符串时间按照指定的分隔符分隔
	 * 
	 * @param time
	 *            字符串时间
	 * @param delim
	 *            分割符
	 * @return Date型时间对象
	 * @throws Exception
	 *             转换失败错误
	 */
	public static Date toRawStartRegularBoundaryDate(String time, String delim) throws Exception {
		return toRawRegularBoundaryDate(time, delim, 2000, 1, 1, 0, 0, 0);
	}

	/**
	 * 把字符串时间按照指定的分隔符分隔
	 * 
	 * @param time
	 *            字符串时间
	 * @param delim
	 *            分割符
	 * @return Date型时间对象
	 * @throws Exception
	 *             转换失败错误
	 */
	public static Date toRawEndRegularBoundaryDate(String time, String delim) throws Exception {
		try {
			return toRawRegularBoundaryDate(time, delim, 2010, 12, 31, 23, 59, 59);
		} catch (Exception e) {
			throw new Exception("toRawRegularStartDate, failure, exception = " + e);
		}
	}

	/**
	 * 把字符串时间按照指定的分隔符分隔
	 * 
	 * @param time
	 *            字符串时间
	 * @param delim
	 *            分割符
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param hour
	 *            小时
	 * @param minute
	 *            分钟
	 * @param second
	 *            秒
	 * @return Date型时间对象
	 * @throws Exception
	 *             转换失败错误
	 */
	public static Date toRawRegularBoundaryDate(String time, String delim, int year, int month, int day, int hour,
			int minute, int second) throws Exception {
		String[] set = MyStringUtil.split(time, delim);
		if (set.length <= 0) {
			throw new Exception("time format illegal, time = " + time + ", delim = " + delim);
		}

		if (set.length >= 1) {
			year = NumberUtil.toRawInt(set[0]);
		}
		if (set.length >= 2) {
			month = NumberUtil.toRawInt(set[1]);
		}
		if (set.length >= 3) {
			day = NumberUtil.toRawInt(set[2]);
		}

		Calendar cal = toBoundaryCalendar(year, month, day, hour, minute, second);
		return cal.getTime();
	}

	// /////////////////////////////////////////////////////////////////
	/**
	 * 比较两个Calendar时间大小，并返回较小的时间值对象
	 * 
	 * @param time1
	 *            第一个Calendar
	 * @param time2
	 *            第2个Calendar
	 * @return 较小的Calendar时间对象
	 */
	public static Calendar minTime(Calendar time1, Calendar time2) {
		if ((null == time1) || (time1.after(time2))) {
			return time2;
		}
		return time1;
	}

	/**
	 * 比较两个Date时间大小，并返回较小的时间值对象
	 * 
	 * @param time1
	 *            第一个Date
	 * @param time2
	 *            第2个Date
	 * @return 较小的Date时间对象
	 */
	public static Date minTime(Date time1, Date time2) {
		if ((null == time1) && (null == time2)) {
			return null;
		}
		if ((null == time1) && (null != time2)) {
			return time2;
		}
		if ((null != time1) && (null == time2)) {
			return time1;
		}
		if (time1.after(time2)) {
			return time2;
		}
		return time1;
	}

	/**
	 * 比较两个Calendar时间大小，并返回较大的时间值对象
	 * 
	 * @param time1
	 *            第一个Calendar
	 * @param time2
	 *            第2个Calendar
	 * @return 较大的Calendar时间对象
	 */
	public static Calendar maxTime(Calendar time1, Calendar time2) {
		if ((null == time1) || (time1.before(time2))) {
			return time2;
		}
		return time1;
	}

	/**
	 * 比较两个Date时间大小，并返回较大的时间值对象
	 * 
	 * @param time1
	 *            第一个Date
	 * @param time2
	 *            第2个Date
	 * @return 较大的Date时间对象
	 */
	public static Date maxTime(Date time1, Date time2) {
		if ((null == time1) && (null == time2)) {
			return null;
		}
		if ((null == time1) && (null != time2)) {
			return time2;
		}
		if ((null != time1) && (null == time2)) {
			return time1;
		}
		if (time1.before(time2)) {
			return time2;
		}
		return time1;
	}

	/**
	 * 判断第1个Calendar对象时间是否在第2个Calendar时间对象之后
	 * 
	 * @param time1
	 *            第一个Calendar时间对象
	 * @param time2
	 *            第2个时间对象
	 * @return =true：第1个时间对象不在第2个时间对象之后 =false：第1个时间对象在第2个时间对象之后
	 */
	public static boolean notAfter(Calendar time1, Calendar time2) {
		if ((null != time1) && (null == time2)) {
			return false;
		}
		if ((null == time1) && (null != time2)) {
			return false;
		}
		if ((null != time1) && (null != time2) && (time1.after(time2))) {
			return false;
		}
		return true;
	}

	/**
	 * 判断第1个Date对象时间是否在第2个Date间对象之后
	 * 
	 * @param time1
	 *            第一1个Date时间对象
	 * @param time2
	 *            第2个时间对象
	 * @return =true：第1个时间对象不在第2个时间对象之后 =false：第1个时间对象在第2个时间对象之后
	 */
	public static boolean notAfter(Date time1, Date time2) {
		if ((null != time1) && (null == time2)) {
			return false;
		}
		if ((null == time1) && (null != time2)) {
			return false;
		}
		if ((null != time1) && (null != time2) && (time1.after(time2))) {
			return false;
		}
		return true;
	}

	/**
	 * 判断某个时间是否在一个时间区间范围内
	 * 
	 * @param time
	 *            需要判断的时间对象
	 * @param start
	 *            区间开始时间
	 * @param end
	 *            区间结束时间
	 * @return =true 指定时间在这个区间内 =false 指定时间不再这个区间内
	 */
	public static boolean equalOrBetween(Calendar time, Calendar start, Calendar end) {
		return (notAfter(start, time) && notAfter(time, end));
	}

	/**
	 * 判断某个时间是否在一个时间区间范围内
	 * 
	 * @param time
	 *            需要判断的时间对象
	 * @param start
	 *            区间开始时间
	 * @param end
	 *            区间结束时间
	 * @return =true 指定时间在这个区间内 =false 指定时间不再这个区间内
	 */
	public static boolean equalOrBetween(Date time, Date start, Date end) {
		return (notAfter(start, time) && notAfter(time, end));
	}

	/**
	 * 实现时间之间的分钟差.<br>
	 * 工程名:cctccati<br>
	 * 包名:com.soft.sb.util.type<br>
	 * 方法名:getTwoTimeMinute方法.<br>
	 * 
	 * @author:89402437@qq.com@hotoa.com
	 * @since :1.0:2009-8-14
	 * @param stratTime：开始时间：yyyyMMddHHmmss
	 * @param endTime:结束时间：yyyyMMddHHmmss
	 * @return 返回两个时间的分钟差
	 */
	public static String getTwoTimeMinute(String stratTime, String endTime) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(otherTimeFormat);
		long minute = 0;
		long second = 0;
		try {
			Date date = myFormatter.parse(stratTime);
			Date mydate = myFormatter.parse(endTime);
			minute = (date.getTime() - mydate.getTime()) / (60 * 1000);
			second = ((date.getTime() - mydate.getTime()) / 1000) - minute * 60;
		} catch (Exception e) {
			return "";
		}
		return minute + "分钟" + second + "秒";
	}

	/**
	 * 
	 * @Title: getRandomCalendar @Description: TODO 获取随机时间 @return 设定文件 @author
	 *         89402437@qq.com @return Calendar 返回类型 @throws
	 */
	public static Calendar getRandomCalendar() {
		Random rand = new Random();
		Calendar cal = Calendar.getInstance();
		cal.set(2000, 0, 1);
		long start = cal.getTimeInMillis();
		cal.set(2008, 0, 1);
		long end = cal.getTimeInMillis();
		Date d = new Date(start + (long) (rand.nextDouble() * (end - start)));
		cal.setTime(d);
		return cal;
	}

	public static Double getTwoDay(String sj1, String sj2) {
		Double day = 0d;
		try {
			Long times = toCalendar(sj1).getTimeInMillis() - toCalendar(sj2).getTimeInMillis();
			day = times / 86400000d;
		} catch (Exception e) {
			return 0d;
		}
		return day;
	}

	/**
	 * 获取两个时间的差值秒
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Integer getSecondBetweenDate(Date d1, Date d2) {
		Long second = (d2.getTime() - d1.getTime()) / 1000;
		return second.intValue();
	}

	public static int getDaysBetweenDate(Date begin, Date end) {
		return (int) ((end.getTime() - begin.getTime()) / (1000 * 60 * 60 * 24));
	}

	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = stringToDate(sdate,null);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	//public static Date strToDate(String strDate) {
	//	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	//	ParsePosition pos = new ParsePosition(0);
	//	Date strtodate = formatter.parse(strDate, pos);
	//	return strtodate;
	//}

	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	// 计算当月最后一天,返回字符串
	public static Date getDefaultDay() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		return lastDate.getTime();
	}

	// 上月第一天
	public static Date getPreviousMonthFirst() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		return lastDate.getTime();
	}

	// 获取当月第一天
	public static Date getFirstDayOfMonth() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		return lastDate.getTime();
	}

	// 获得本周星期日的日期
	public static Date getCurrentWeekday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTime();
	}

	/**
	 * 获得当前日期与本周日相差的天数
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getMondayPlus方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:46:41
	 * @return
	 */
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 获得本周一的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getMondayOFWeek方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:46:48
	 * @return
	 */
	public static Date getMondayOFWeek() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(Calendar.DATE, mondayPlus);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTime();
	}

	/**
	 * 获得上周星期日的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getPreviousWeekSunday方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:47:04
	 * @return
	 */
	public static Date getPreviousWeekSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus - 1);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTime();
	}

	/**
	 * 获得上周星期一的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getPreviousWeekday方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:47:11
	 * @return
	 */
	public static Date getPreviousWeekday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * -1);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTime();
	}

	/**
	 * 获得下周星期一的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getNextMonday方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:47:18
	 * @return
	 */
	public static Date getNextMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTime();
	}

	/**
	 * 获得下周星期日的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getNextSunday方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:47:26
	 * @return
	 */
	public static Date getNextSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTime();
	}

	/**
	 * 获得上月最后一天的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getPreviousMonthEnd方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:47:33
	 * @return
	 */
	public static Date getPreviousMonthEnd() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		return lastDate.getTime();
	}

	/**
	 * 获得下个月第一天的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getNextMonthFirst方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:47:41
	 * @return
	 */
	public static Date getNextMonthFirst() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		return lastDate.getTime();
	}

	/**
	 * 获得下个月最后一天的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getNextMonthEnd方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:47:49
	 * @return
	 */
	public static Date getNextMonthEnd() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 加一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		return lastDate.getTime();
	}

	/**
	 * 获得明年最后一天的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getNextYearEnd方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:47:56
	 * @return
	 */
	public static Date getNextYearEnd() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// 加一个年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		lastDate.roll(Calendar.DAY_OF_YEAR, -1);
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		return lastDate.getTime();
	}

	/**
	 * 获得明年第一天的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getNextYearFirst方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:48:03
	 * @return
	 */
	public static Date getNextYearFirst() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// 加一个年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		return lastDate.getTime();
	}

	/**
	 * 获得本年有多少天
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getMaxYear方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:48:28
	 * @return
	 */
	public static int getMaxYear() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		return MaxYear;
	}

	private static int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	/**
	 * 获得本年第一天的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getCurrentYearFirst方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:48:40
	 * @return
	 */
	public static Date getCurrentYearFirst() {
		int yearPlus = getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTime();
	}

	/**
	 * 获得本年最后一天的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getCurrentYearEnd方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:48:53
	 * @return
	 */
	public static Date getCurrentYearEnd() {
		String str = toString(Calendar.getInstance(), "yyyy") + "-12-31";
		Calendar currentDate = toCalendar(str);
		return currentDate.getTime();
	}

	/**
	 * 获得上年第一天的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getPreviousYearFirst方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:49:01
	 * @return
	 */
	public static Date getPreviousYearFirst() {
		String years = toString(Calendar.getInstance(), "yyyy");
		int years_value = NumberUtil.toInt(years);
		years_value--;
		return toCalendar(years_value + "-01-01").getTime();
	}

	/**
	 * 获得上年最后一天的日期
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getPreviousYearEnd方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:49:08
	 * @return
	 */
	public static Date getPreviousYearEnd() {
		int yearPlus = getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus + 0 * 0 + (0 - 1));
		getThisSeasonTime(11);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTime();
	}

	/**
	 * 获得本季度
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method getThisSeasonTime方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:49:14
	 * @param month
	 * @return
	 */
	public static Date[] getThisSeasonTime(int month) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int start_month = array[season - 1][0];
		int end_month = array[season - 1][2];
		String years = toString(Calendar.getInstance(), "yyyy");
		int years_value = NumberUtil.toInt(years);
		int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
		int end_days = getLastDayOfMonth(years_value, end_month);
		return new Date[] { toCalendar(years_value + "-" + start_month + "-" + start_days).getTime(),
				toCalendar(years_value + "-" + end_month + "-" + end_days).getTime() };
	}

	private static int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	/**
	 * 判断是否运年
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method isLeapYear方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author 89402437@qq.com
	 * @date 2014-5-8 下午9:49:24
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	/**
	 * 
	 * @param date
	 *            指定比较日期
	 * @param compareDate
	 * @return
	 */
	public static boolean isInDate(Date date, Date compareDate) {
		if (compareDate.after(getStartDate(date)) && compareDate.before(getFinallyDate(date))) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 得到指定日期的一天的的最后时刻23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFinallyDate(Date date) {
		String temp = toString(date);
		temp += " 23:59:59";
		return toCalendar(temp).getTime();
	}

	/**
	 * 得到指定日期的一天的开始时刻00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDate(Date date) {
		String temp = toString(date);
		temp += " 00:00:00";
		return toCalendar(temp).getTime();
	}

	public static String formatDate(Date date) {
		return DateFormat.getDateInstance().format(date);
	}

	public static String formatTime(Date date) {
		return DateFormat.getTimeInstance().format(date);
	}

	public static String formatDateTime(Date date) {
		if (DateFormat.getDateTimeInstance().format(date).contains("0:00:00")) {
			return formatDate(date);
		}
		return DateFormat.getDateTimeInstance().format(date);
	}

	/**
	 * 转换为HH:mm:ss
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method parseTime方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author hjz
	 * @date 2014-12-19 上午10:25:43
	 * @param time
	 * @return
	 */
	public static Date parseTime(Date time) {
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date result = time;
		try {
			result = format.parse(formatTime(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 转换为yyyy-dd-mm
	 * 
	 * @Project SC
	 * @Package com.ysc.soasoft.util.type
	 * @Method parseDate方法.<br>
	 * @Description TODO(用一句话描述该类做什么)
	 * @author hjz
	 * @date 2014-12-19 上午10:25:23
	 * @param time
	 * @return
	 */
	public static Date parseDate(Date time) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date result = time;
		try {
			result = format.parse(formatDate(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static Date getSpecficDateStart(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, amount);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取date周后的第amount周的开始时间（这里星期一为一周的开始）
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficWeekStart(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
		cal.add(Calendar.WEEK_OF_MONTH, amount);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取date月后的amount月的第一天的开始时间
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficMonthStart(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, amount);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取当前自然月后的amount月的最后一天的终止时间
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficMonthEnd(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getSpecficMonthStart(date, amount + 1));
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return getFinallyDate(cal.getTime());
	}

	/**
	 * 获取date年后的amount年的第一天的开始时间
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficYearStart(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, amount);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取date年后的amount年的最后一天的终止时间
	 * 
	 * @param amount
	 *            可正、可负
	 * @return
	 */
	public static Date getSpecficYearEnd(Date date, int amount) {
		Date temp = getStartDate(getSpecficYearStart(date, amount + 1));
		Calendar cal = Calendar.getInstance();
		cal.setTime(temp);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return getFinallyDate(cal.getTime());
	}

	// ++与其他日期处理类合并++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public static String getCurYear() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat fmt = (SimpleDateFormat) yearFormat.clone();
		String date = fmt.format(calendar.getTime());
		return date;
	}

	public static String getCurMonth() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat fmt = (SimpleDateFormat) monthFormat.clone();
		String date = fmt.format(calendar.getTime());
		return date;
	}

	public static String getOnlyCurMonth() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat fmt = (SimpleDateFormat) OnlyMONTHFormat.clone();
		String date = fmt.format(calendar.getTime());
		return date;
	}

	public static String getCurDay() {
		Calendar calendar = Calendar.getInstance();
		String day = getDayFormat().format(calendar.getTime());
		return day;
	}

	public static String getOnlyCurDay() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat fmt = (SimpleDateFormat) OnlyDAYFormat.clone();
		String date = fmt.format(calendar.getTime());
		return date;
	}

	public static String getCurHour() {
		Calendar calendar = Calendar.getInstance();
		String day = getHourFormat().format(calendar.getTime());
		return day;
	}

	public static String getCurQuarter() {
		String year = getCurYear();
		String yearMonth = getCurMonth();
		int monthCount = yearMonth.length();

		String month = yearMonth.substring(monthCount - 2, monthCount);

		int intMonth = Integer.valueOf(month).intValue();
		int intQuarter = (intMonth % 3 == 0) ? intMonth / 3 : intMonth / 3 + 1;

		return year + "-Q-" + intQuarter;
	}

	public static String getCurTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat fmt = (SimpleDateFormat) dateFormat.clone();
		String date = fmt.format(calendar.getTime());
		return date;
	}

	public static String getCurShortTime() {
		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat fmt = (SimpleDateFormat) shortTimeFormat.clone();
		String time = fmt.format(calendar.getTime());
		return time;
	}

	public static SimpleDateFormat getDateFormat() {
		SimpleDateFormat fmt = (SimpleDateFormat) dateFormat.clone();
		return fmt;
	}

	public static SimpleDateFormat getMonthFormat() {
		SimpleDateFormat fmt = (SimpleDateFormat) monthFormat.clone();
		return fmt;
	}

	public static SimpleDateFormat getMinuteFormat() {
		SimpleDateFormat fmt = (SimpleDateFormat) minuteFormat.clone();
		return fmt;
	}

	public static SimpleDateFormat getDayFormat() {
		SimpleDateFormat fmt = (SimpleDateFormat) dayFormat.clone();
		return fmt;
	}

	public static SimpleDateFormat getYearFormat() {
		SimpleDateFormat fmt = (SimpleDateFormat) yearFormat.clone();
		return fmt;
	}

	public static SimpleDateFormat getHourFormat() {
		SimpleDateFormat fmt = (SimpleDateFormat) hourFormat.clone();
		return fmt;
	}

	public static SimpleDateFormat getMillisecondFormat() {
		SimpleDateFormat fmt = (SimpleDateFormat) millisecondFormat.clone();
		return fmt;
	}

	public static String getCurTimeWithMillisecond() {
		Calendar calendar = Calendar.getInstance();
		String date = getMillisecondFormat().format(calendar.getTime());
		return date;
	}

	public static long getCurTimeInt() {
		return System.currentTimeMillis();
	}

	public static boolean checkDate(String pDateObj) {
		boolean ret = true;
		if ((pDateObj == null) || (pDateObj.length() < 1)) {
			ret = false;
		}
		try {
			String[] arr = pDateObj.split("-");

			int year = new Integer(arr[0]).intValue();

			int month = new Integer(arr[1]).intValue();

			int day = new Integer(arr[2]).intValue();

			Calendar cal = Calendar.getInstance();

			cal.setLenient(false);
			cal.set(year, month - 1, day);

			cal.getTime();
		} catch (Exception e) {
			ret = false;
		}
		return ret;
	}

	public static long diffMillisecond(String startTime, String endTime) throws ParseException {
		SimpleDateFormat format = getMillisecondFormat();
		Date dstartTime = format.parse(startTime);
		Date dendTime = format.parse(endTime);
		return (dendTime.getTime() - dstartTime.getTime());
	}

	public static Date convertStrToDate(String srcDateStr) {
		if ((srcDateStr == null) || (srcDateStr.trim().length() == 0))
			return null;
		try {
			SimpleDateFormat format = getDayFormat();
			if (srcDateStr.indexOf("/") > 0) {
				format = new SimpleDateFormat("yyyy/MM/dd");
			}

			return format.parse(srcDateStr);
		} catch (ParseException e) {
		}
		return null;
	}

	public static String millis2Str(long time) {
		if (time < 0L) {
			return null;
		}
		return getMillisecondFormat().format(new Date(time));
	}

	public static int getDayNum(int year, int month) {
		boolean leapYear = ((year % 4 == 0) && (((year % 100 != 0) || (year % 400 == 0))));
		// boolean leapYear = UFODatePublic.isLeapYear(year);
		int dayMount = 31;
		switch (month) {
		case 2:
			dayMount = (leapYear) ? 29 : 28;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			dayMount = 30;
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		}
		return dayMount;
	}

	// 日期工具类整合
	public static Date stringToDate(String strDate) {
		return stringToDate(strDate,yearMonthDayFormat);
	}

	// 日期工具类整合
	public static Date stringToDate(String strDate, String pattern) {
		if (strDate == null || strDate.equals("")) {
			throw new RuntimeException("str date null");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = yearMonthDayFormat;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;

		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * java.util.Date ת String
	 */
	public static String date2String(Date date, String pattern) {
		if (date == null) {
			throw new IllegalArgumentException("timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = theTimeFormat;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
    /**
     * 
     * @param curTime 格式 :2016-01-05 11:21:00 或 2016-01-05 等标准格式的日期型字符串
     * @param pattern 格式:yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd 与 endTime格式匹配 
     * @param offset  格式:[秒:1000], [分:60*1000],[小时:60*60*1000],[天:24*60*60*1000]日期的long型 
     *                offset 值可以为正数，也可以为负数；假设[offset=天:24*60*60*1000]正数表示在curTime时间基础之上增加一天，负数反之
     * @return 返回与参数pattern匹配的日期字符串 如：2016-01-05 11:21:00 或 2016-01-05 等标准格式的日期型字符串
     */
	public static String stringDate2StringByCal(String curTime, String pattern, long offset) {
		if (curTime == null || curTime.equals("")) {
			throw new RuntimeException("curTime String null");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = theTimeFormat;
		}
		Date srcDate = TimeUtil.stringToDate(curTime, pattern);
		long srcLong = srcDate.getTime();
		long tarLong = srcLong + offset;
		return  toString(tarLong);
	}
	/**
	 * 
	 * @param curTime 格式 :2016-01-05 11:21:00 或 2016-01-05 等标准格式的日期型字符串
	 * @param pattern 格式:yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd 与 endTime格式匹配 
	 * @param offset  格式:[秒:1000], [分:60*1000],[小时:60*60*1000],[天:24*60*60*1000]日期的long型 
	 *                offset 值可以为正数，也可以为负数；假设[offset=天:24*60*60*1000]正数表示在curTime时间基础之上增加一天，负数反之
	 * @return 返回与参数pattern匹配的日期字符串 如：2016-01-05 11:21:00 或 2016-01-05 等标准格式的日期型字符串
	 */
	public static String Date2StringByCal(Date curTime, String pattern, long offset) {
		if (curTime == null || curTime.equals("")) {
			throw new RuntimeException("str date null");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = theTimeFormat;
		}
//		Date srcDate = TimeUtil.string2Date(curTime, pattern);
		long srcLong = curTime.getTime();
		long tarLong = srcLong + offset;
		return  toString(tarLong);
	}

	public static String timestampToString(Timestamp ts,String pattern) {
		if (ts == null) {
			throw new IllegalArgumentException("timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = theTimeFormat;
		}
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat(pattern);
		try {
			//方法一
			tsStr = sdf.format(ts);
			System.out.println(tsStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tsStr;
	}

	public static String timestampToString(Timestamp ts) {
		if (ts == null) {
			throw new IllegalArgumentException("timestamp null illegal");
		}
		String tsStr = "";
		try {
			//方法一
			tsStr = dayFormat.format(ts);
			System.out.println(tsStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tsStr;
	}

	/**
	 *
	 * @param tsStr 格式"2011-05-09 11:49:45";
	 * @return
     */
	public static Timestamp stringToTimestamp(String tsStr) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(tsStr);
			System.out.println(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}
	//---获取年月日时分秒----------------------------------------------------

	/**
	 * 获取年份
	 *
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取月份
	 *
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取日
	 *
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取星期
	 *
	 * @param date
	 * @return
	 */
	public static int getWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取时间
	 *
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取分种
	 *
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 获取秒
	 *
	 * @param date
	 * @return
	 */
	public static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**
	 * 获取当前时间的前一天时间
	 *
	 *@param currDate 2018-10-14
	 *@param beforeDay 3
	 *@param format 默认 yyyy-mm-dd
	 * @return      2018-10-11
	 */
	private static String getBeforeDay(Date currDate,int beforeDay,String format) {
		if (MyStringUtil.isBlank(format)) {
			format = yearMonthDayFormat;
		}
		if (currDate == null || currDate.equals("")) {
			throw new RuntimeException("param currDate null");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currDate);
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day - beforeDay);
		return toString(calendar, format);
	}

	/**
	 * 获取当前时间的前一天时间
	 *
	 * @param currDate  2018-10-14
	 * @param beforeDay 3
	 * @param format    默认 yyyy-mm-dd
	 * @return 2018-10-11
	 */
	public static String getBeforeDay(String currDate, int beforeDay, String format) {
		if (MyStringUtil.isBlank(format)) {
			format = yearMonthDayFormat;
		}
		if (currDate == null || currDate.equals("")) {
			throw new RuntimeException("param currDate null");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(stringToDate(currDate,format));
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day - beforeDay);
		return toString(calendar, format);
	}

	/**
	 *
	 * @param seconds 格式：1545616231
	 * @param format
	 * @return 2018-12-24 09:50:31
     */
	public static String timeStamp2Str(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";

		}
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd HH:mm:ss";

		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	public static void main(String[] args) {
		System.out.println(timeStamp2Str(String.valueOf(1545616231),null));

		//Date nowAdd1m = DateUtils.addMinutes(new Date(), -1);
		//System.out.println(date2String(nowAdd1m,theTimeFormat));
        //
		//System.out.println(getBeforeDay("2018-10-14",30,null));
        //
		//System.out.println(getDays("2018-10-14","2018-10-7"));
        //
		//System.out.println(getHour(string2Date("2018-10-11 08",hoursFormat)));
		//System.out.println(MyStringUtil.split("2018-10-14 09"," ")[1]);
		//System.out.println("获取本周一日期:" + toString(getMondayOFWeek()));
		//System.out.println("获取本周日的日期~:" + toString(getCurrentWeekday()));
		//System.out.println("获取上周一日期:" + toString(getPreviousWeekday()));
		//System.out.println("获取上周日日期:" + toString(getPreviousWeekSunday()));
		//System.out.println("获取下周一日期:" + toString(getNextMonday()));
		//System.out.println("获取下周日日期:" + toString(getNextSunday()));
		//System.out.println("获取本月第一天日期:" + toString(getFirstDayOfMonth()));
		//System.out.println("获取本月最后一天日期:" + toString(getDefaultDay()));
		//System.out.println("获取上月第一天日期:" + toString(getPreviousMonthFirst()));
		//System.out.println("获取上月最后一天的日期:" + toString(getPreviousMonthEnd()));
		//System.out.println("获取下月第一天日期:" + toString(getNextMonthFirst()));
		//System.out.println("获取下月最后一天日期:" + toString(getNextMonthEnd()));
		//System.out.println("获取本年的第一天日期:" + toString(getCurrentYearFirst()));
		//System.out.println("获取本年最后一天日期:" + toString(getCurrentYearEnd()));
		//System.out.println("获取去年的第一天日期:" + toString(getPreviousYearFirst()));
		//System.out.println("获取去年的最后一天日期:" + toString(getPreviousYearEnd()));
		//System.out.println("获取明年第一天日期:" + toString(getNextYearFirst()));
		//System.out.println("获取明年最后一天日期:" + toString(getNextYearEnd()));
		//System.out.println(
		//		"获取本季度第一天到最后一天:" + toString(getThisSeasonTime(11)[0]) + "," + toString(getThisSeasonTime(11)[1]));
		//System.out.println("获取两个日期之间间隔天数2008-12-1~2008-12.5:" + getTwoDay("2008-12-1", "2008-12-5"));
	}

}
