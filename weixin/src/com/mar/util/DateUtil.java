package com.mar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	/**
	 * 日期格式为{@value},例如:"200408";
	 */
	public static final String DATE_STYLE1 = "yyyyMM";

	/**
	 * 日期格式为{@value},例如:"20040823";
	 */
	public static final String DATE_STYLE2 = "yyyyMMdd";

	/**
	 * 日期格式�?yyyy年MM月dd�?星期X",例如:"2006�?5�?6�?星期�?;
	 */
	public static final String DATE_STYLE3 = "yyyy年MM月dd�?E";

	/**
	 * 日期格式为{@value},例如:"2006-05-26";
	 */
	public static final String DATE_STYLE4 = "yyyy-MM-dd";

	/**
	 * 日期格式为{@value},例如:"2006-05-26 17:11:01";
	 */
	public static final String DATE_STYLE5 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式为{@value},例如:"2006.05.26";
	 */
	public static final String DATE_STYLE6 = "yyyy.MM.dd";


	/**
	 * �?��期起�?
	 */
	public static final String SSSQ_Q = "qq";

	/**
	 * �?��期之�?
	 */
	public static final String SSSQ_Z = "qz";
	
	/**
	 * 日期格式为{@value},例如:"20060526171101";
	 */
	public static final String DATE_STYLE7 = "yyyyMMddHH24miss";
	
	public static final String DATE_STYLE8 = "yyyyMMdd HH:mm:ss";

	public static final String DATE_STYLE9 = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_STYLE10 = "yyyyMMdd HH:mm:ss";

	public static final String DATE_STYLE11 = "yyyy年MM月dd日";
	
	public static final String DATE_STYLE12 = "yyyy年MM月";

	public static final String DAY = "DAY";

	public static final String MONTH = "MONTH";

	public static final String YEAR = "YEAR";
	
	/**
	 * 
	 * @Title: getCurrentTimeByDb
	 * @Description: 此方法用于获得数据库的当前时间，dateStyle为时间字符串格式
	 * @param @param datePattern
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 * @author baixy
	 * @date Nov 29, 2010 2:19:38 PM
	 */
/*	public static String getCurrentTimeByDb(String dateStyle) throws Exception{
		
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		Connection conn = DriverManager.getConnection(PropertiesUtil.getProperty("TIME_DB_URL"), 
				PropertiesUtil.getProperty("TIME_DB_USERNAME"), PropertiesUtil.getProperty("TIME_DB_PWD"));
		Statement stmt = null;
		ResultSet rs = null;
		String currentDate = "";
		try {
			stmt = conn.createStatement();
			String sql = "SELECT TO_CHAR(SYSDATE, '" + dateStyle + "') as a FROM dual";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				currentDate = rs.getString("a");
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
 		
		return currentDate;
		
	}*/
	
	
	/*public static int getCurrentDay() throws Exception{
		
		return Integer.parseInt(DateUtil.getCurrentTimeByDb("dd")); 
		
	}*/
	
	
	/**
	 * 
	 * @Title: getCurrentYear
	 * @Description: 此方法用于获得当前年
	 * @param  
	 * @return int 
	 * @throws
	 * @author baixy
	 * @date Jun 3, 2010 4:02:52 PM
	 */
	/*public static int getCurrentYear() throws Exception{
		
		return Integer.parseInt(DateUtil.getCurrentTimeByDb("yyyy")); 
		
	}*/

	/**
	 * 
	 * @Title: getCurrentMonth
	 * @Description: 此方法用于获得当前月�?
	 * 	 * @param  
	 * @return int 
	 * @throws
	 * @author baixy
	 * @date Jun 3, 2010 4:43:03 PM
	 */
	/*public static int getCurrentMonth() throws Exception{
		
		return Integer.parseInt(DateUtil.getCurrentTimeByDb("mm")); 
		
	}*/
	
	
	/**
	 * 
	 * @Title: getCurrentTime
	 * @Description: 此方法用于获得当时时�?
	 * @param  
	 * @return String 
	 * @throws
	 * @author baixy
	 * @date Jun 13, 2010 11:40:45 AM
	 */
	public static String getCurrentTime() {
		
		 SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.DATE_STYLE5);
		 return dateFormat.format(new Date());
	}
	
	
	
	/**
	 * 获取当前日期字符�?<br>
	 * 
	 * @param style
	 *            日期样式;目前预定义的样式�?<br>
	 *            {@link #DATE_STYLE1}:日期格式�?yyyyMM";<br>
	 *            {@link #DATE_STYLE2}:日期格式�?yyyyMMdd";<br>
	 *            {@link #DATE_STYLE3}:日期格式�?yyyy年MM月dd�?星期X";<br>
	 *            {@link #DATE_STYLE4}:日期格式�?yyyy-MM-dd";<br>
	 *            {@link #DATE_STYLE5}:日期格式�?yyyy-MM-dd HH:mm:ss";<br>
	 * @return String
	 * @author C.Chen
	 */
	public static String getDateStr(String style) {
		return getDateStr(style, new Date());
	}

	/**
	 * 获取指定日期的字符串日期;<br>
	 * 
	 * @param style
	 *            日期样式;目前预定义的样式�?<br>
	 *            {@link #DATE_STYLE1}:日期格式�?yyyyMM";<br>
	 *            {@link #DATE_STYLE2}:日期格式�?yyyyMMdd";<br>
	 *            {@link #DATE_STYLE3}:日期格式�?yyyy年MM月dd�?星期X";<br>
	 *            {@link #DATE_STYLE4}:日期格式�?yyyy-MM-dd";<br>
	 *            {@link #DATE_STYLE5}:日期格式�?yyyy-MM-dd HH:mm:ss";<br>
	 * @param date
	 *            指定的日�?
	 * @return String
	 * @author C.Chen
	 */
	public static String getDateStr(String style, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(style, Locale.CHINA);
		return sdf.format(date);
	}

	/**
	 * 获取当前时间的上月的年�?�?<br>
	 * 例如：当前日期为�?0070313”，返回日期为�?200702�?
	 * 
	 * @return String 返回日期格式�?yyyyMM",例如:"200408";
	 * @author C.Chen
	 */
	public static String getLastMonth() {
		StringBuffer buff = new StringBuffer(10);
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		// 为某年的�?��份时
		if (month == 0) {
			year = year - 1;
			month = 12;
		}
		buff.append(year);
		buff.append((month < 10) ? "0" + month : "" + month);

		return buff.toString();
	}

	/**
	 * 解析指定日期字符�?获得日期类型的对�?
	 * 
	 * @param style
	 *            日期样式;目前预定义的样式�?<br>
	 *            {@link #DATE_STYLE1}:日期格式�?yyyyMM";<br>
	 *            {@link #DATE_STYLE2}:日期格式�?yyyyMMdd";<br>
	 *            {@link #DATE_STYLE3}:日期格式�?yyyy年MM月dd�?星期X";<br>
	 *            {@link #DATE_STYLE4}:日期格式�?yyyy-MM-dd";<br>
	 *            {@link #DATE_STYLE5}:日期格式�?yyyy-MM-dd HH:mm:ss";<br>
	 * @param dateStr
	 *            �?��转换的日期字符串;
	 * @return Date
	 * @author C.Chen
	 */
	public static Date parseDate(String style, String dateStr) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(style, Locale.CHINA);
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 计算出某年某月的天数;
	 * 
	 * @param year
	 *            指定的年份，格式�?yyyy";
	 * @param month
	 *            指定的月份，格式�?MM";
	 * @return int 指定月份的天�?
	 * @author C.Chen
	 */
	public static int getDaysOfMonth(String yyyy, String mm) {
		int year = Integer.parseInt(yyyy);
		int month = Integer.parseInt(mm);

		return getDaysOfMonth(year, month);
	}

	/**
	 * 计算出某年某月的天数;
	 * 
	 * @param year
	 *            指定的年�?
	 * @param month
	 *            指定的月�?
	 * @return int 指定月份的天�?
	 * @author C.Chen
	 */
	public static int getDaysOfMonth(int year, int month) {
		int monthCount = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			monthCount = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			monthCount = 30;
			break;
		case 2:
			if (((year % 4) == 0 && (year % 100) != 0) || (year % 400) == 0) {
				monthCount = 29;
			} else {
				monthCount = 28;
			}
			break;
		}

		return monthCount;
	}

	/**
	 * 获取指定日期之前（一定天数）的日�?
	 * 
	 * @param date
	 *            指定的日�?
	 * @param preDays
	 *            指定日期之前的天�?
	 * @return Date
	 * @author C.Chen
	 */
	public static Date getBeforeDate(Date date, int days) {
		String tempDate = getDateStr(DATE_STYLE2, date);

		int yyyy = Integer.parseInt(tempDate.substring(0, 4));
		int mm = Integer.parseInt(tempDate.substring(4, 6));
		int dd = Integer.parseInt(tempDate.substring(6, 8));

		dd = dd - days;
		while (dd <= 0) {
			if (mm == 1) {
				yyyy = yyyy - 1;
				mm = 12;
			} else {
				mm = mm - 1;
			}

			dd = dd + getDaysOfMonth(yyyy, mm);
		}

		StringBuffer buff = new StringBuffer(10);
		buff.append(yyyy);
		buff.append((String.valueOf(mm).length() == 1) ? "0" + mm : String.valueOf(mm));
		buff.append((String.valueOf(dd).length() == 1) ? "0" + dd : String.valueOf(dd));

		return parseDate(DATE_STYLE2, buff.toString());
	}

	/**
	 * 
	* @Title: getBeforeDate
	* @Description: 获取指定日期之前�?��天数或月份或年份的日期，根据type判断是年或月或日
	* @param  date 日期
	* @param  type DAY MONTH月份 YEAR年份
	* @param  size 之前的天�?月数 年数 �? 2 3�?
	* @param  dateTempalte
	* @return String
	 */
	public static String getBeforeDate(Date date, String type, int size, String dateTempalte) {
		Date temp = new Date();//当前日期
		
		if (date != null) {
			temp = date;
		}
			
		SimpleDateFormat sdf = new SimpleDateFormat(dateTempalte);//格式化对�?
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		
		switch (type) {
		case "DAY":
			calendar.add(Calendar.DAY_OF_MONTH, -size);//月份减一
			break;
		case "MONTH":
			calendar.add(Calendar.MONTH, -size);//月份
			break;
		case "YEAR":
			calendar.add(Calendar.YEAR, -size);//年份
			break;
	
		default:
			break;
		}
		
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 获取指定日期之后（一定天数）的日�?
	 * 
	 * @param date
	 *            指定的日�?
	 * @param preDays
	 *            指定日期之后的天�?
	 * @return Date
	 * @author C.Chen
	 */
	public static Date getAfterDate(String dateStyle, Date date, int days) {
		String tempDate = getDateStr(dateStyle, date);

		int yyyy = Integer.parseInt(tempDate.substring(0, 4));
		int mm = Integer.parseInt(tempDate.substring(4, 6));
		int dd = Integer.parseInt(tempDate.substring(6, 8));

		dd = dd + days;
		while (dd > getDaysOfMonth(yyyy, mm)) {
			if (mm == 12) {
				yyyy = yyyy + 1;
				mm = 1;
			} else {
				mm = mm + 1;
			}

			if (mm == 1)
				dd = dd - getDaysOfMonth(yyyy - 1, 12);
			else
				dd = dd - getDaysOfMonth(yyyy, mm - 1);
		}

		StringBuffer buff = new StringBuffer(10);
		buff.append(yyyy);
		buff.append((String.valueOf(mm).length() == 1) ? "0" + mm : String.valueOf(mm));
		buff.append((String.valueOf(dd).length() == 1) ? "0" + dd : String.valueOf(dd));

		return parseDate(dateStyle, buff.toString());
	}

	/**
	 * 
	* @Title: getAfterDate
	* @Description: 获取指定日期之后�?��天数或月份或年份的日期，根据type判断是年或月或日
	* @param @param date
	* @param @param type
	* @param @param size
	* @param @param dateTempalte
	* @param @return    设定文件
	* @return String    返回类型
	 */
	public static String getAfterDate(Date date, String type, int size, String dateTempalte) {
		Date temp = new Date();//当前日期
		
		if (date != null) {
			temp = date;
		}
			
		SimpleDateFormat sdf = new SimpleDateFormat(dateTempalte);//格式化对�?
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		
		switch (type) {
		case "DAY":
			calendar.add(Calendar.DAY_OF_MONTH, size);//月份减一
			break;
		case "MONTH":
			calendar.add(Calendar.MONTH, size);//月份
			break;
		case "YEAR":
			calendar.add(Calendar.YEAR, size);//年份
			break;
	
		default:
			break;
		}
		
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 将日期字符串�?�?"yyyyMMdd")转换�?0�?"yyyy-MM-dd");
	 * 
	 * @param rq
	 *            8位的字符串日�?
	 * @return String 10位的字符串日�?如果转换错误将返回NULL;
	 */
	public static String getRq8To10(String rq) {
		String result = null;
		if (rq.length() == 10) {
			return rq;
		}
		if (rq.length() == 8) {
			result = rq.substring(0, 4) + "-" + rq.substring(4, 6) + "-" + rq.substring(6, 8);
		}
		return result;
	}

	/**
	 * 将日期字符串�?0�?"yyyy-MM-dd")转换�?�?"yyyyMMdd");
	 * 
	 * @param rq
	 *            10位的字符串日�?
	 * @return String 8位的字符串日�?如果转换错误将返回NULL;
	 * @author C.Chen
	 */
	public static String getRq10To8(String rq) {
		String result = null;
		if (rq.length() == 8) {
			return rq;
		}
		if (rq.length() == 10) {
			result = rq.substring(0, 4) + rq.substring(5, 7) + rq.substring(8, 10);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: getDayOfMonth
	* @Description: 获得当前月的天数
	* @param @return    设定文件
	* @return int    返回类型
	* @throws
	 */
	public static int getDayOfMonth(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}
	
	/**
	 * 
	* @Title: getDayOfMonth
	* @Description: 获得指定月的天数
	* @param @param dateFormateStr 日期格式
	* @param @param dateStr	日期字符�?
	* @param @return
	* @param @throws ParseException    设定文件
	* @return int    返回类型
	 */
	public static int getDayOfMonth(String dateFormateStr, String dateStr) throws ParseException{
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		SimpleDateFormat simpleDate = new SimpleDateFormat(dateFormateStr);
		aCalendar.setTime(simpleDate.parse(dateStr));
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}
	
	/**
	 * 根据日期取得星期�?
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date){
		String[] weeks = {"星期�","星期�","星期�","星期�","星期�","星期�","星期�"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		} 
		return weeks[week_index];
	}
}
