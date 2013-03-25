package ramo.klevis.openrental.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilDate {

	private Date firstDate;
	private Date secondDate;

	public UtilDate(Date singleDate) {

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(singleDate);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		firstDate = calendar.getTime();

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		secondDate = calendar.getTime();

	}

	public UtilDate(Date singleDate, Date doubleDate) {

		GregorianCalendar calendar = new GregorianCalendar();

		calendar.setTime(singleDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		firstDate = calendar.getTime();
		singleDate = calendar.getTime();

		calendar.setTime(doubleDate);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		secondDate = calendar.getTime();
		doubleDate = calendar.getTime();
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	public Date getFirstDate() {
		return firstDate;
	}

	public void setSecondDate(Date secondDate) {
		this.secondDate = secondDate;
	}

	public Date getSecondDate() {
		return secondDate;
	}

	public static Date getInitialDate(Object date) {
		if (date == null)
			return null;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime((Date) date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getEndingDate(Object date) {
		if (date == null)
			return null;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime((Date) date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static String getStringDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hhmm");
		return sdf.format(new Date());
	}

	public static String displayDate(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		return sdf.format(date);
	}

	public static String displayDateTime(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy-hh:mm");
		return sdf.format(date);
	}

}
