package com.dmd;


import com.xiaoleilu.hutool.date.DateField;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

/**
 * The class Date util.
 *
 * @author paascloud.net @gmail.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

	/**
	 * 获取系统前时间.
	 *
	 * @param minute the minute
	 *
	 * @return the before time[yyyy-MM-dd HH:mm:ss]
	 */
	public static String getBeforeTime(int minute) {
		Date newDate = com.xiaoleilu.hutool.date.DateUtil.offset(new Date(), DateField.MINUTE, -minute);
		return com.xiaoleilu.hutool.date.DateUtil.formatDateTime(newDate);
	}

	/**
	 * 从Date类型的时间中提取日期部分
	 */
	public static Date getDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 从Date类型的时间中提取时间部分
	 */
	public static Date getTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, 1970);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
}
