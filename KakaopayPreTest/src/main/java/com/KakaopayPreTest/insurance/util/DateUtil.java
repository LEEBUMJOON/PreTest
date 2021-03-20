package com.KakaopayPreTest.insurance.util;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public  class DateUtil {
	/**

	 * <p>yyyyMMdd Ȥ�� yyyy-MM-dd ������ ��¥ ���ڿ��� �Է� �޾� ��, ��, ����

	 * �����Ѵ�. ��, ��, ���� ������ ���� �ǹ��ϸ�, ������ �Է��� ��� ���Ѵ�.</p>

	 *

	 * <pre>

	 * DateUtil.addYearMonthDay("19810828", 0, 0, 19)  = "19810916"

	 * DateUtil.addYearMonthDay("20060228", 0, 0, -10) = "20060218"

	 * DateUtil.addYearMonthDay("20060228", 0, 0, 10)  = "20060310"

	 * DateUtil.addYearMonthDay("20060228", 0, 0, 32)  = "20060401"

	 * DateUtil.addYearMonthDay("20050331", 0, -1, 0)  = "20050228"

	 * DateUtil.addYearMonthDay("20050301", 0, 2, 30)  = "20050531"

	 * DateUtil.addYearMonthDay("20050301", 1, 2, 30)  = "20060531"

	 * DateUtil.addYearMonthDay("20040301", 2, 0, 0)   = "20060301"

	 * DateUtil.addYearMonthDay("20040229", 2, 0, 0)   = "20060228"

	 * DateUtil.addYearMonthDay("20040229", 2, 0, 1)   = "20060301"

	 * </pre>

	 *

	 * @param  dateStr ��¥ ���ڿ�(yyyyMMdd, yyyy-MM-dd�� ����)

	 * @param  year ������ ��. 0�� �Էµ� ��� ������ ����

	 * @param  month ������ ��. 0�� �Էµ� ��� ������ ����

	 * @param  day ������ ��. 0�� �Էµ� ��� ������ ����

	 * @return  yyyyMMdd ������ ��¥ ���ڿ�

	 * @throws IllegalArgumentException ��¥ ������ ������ �ٿ� �ٸ� ���.

	 *         �Է� ���� <code>null</code>�� ���.

	 */

	public static String addYearMonthDay(String sDate, int year, int month, int day) {



		String dateStr = sDate;


		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

		try {

			cal.setTime(sdf.parse(dateStr));

		} catch (ParseException e) {

			throw new IllegalArgumentException("Invalid date format: " + dateStr);

		}



		if (year != 0)

			cal.add(Calendar.YEAR, year);

		if (month != 0)

			cal.add(Calendar.MONTH, month);

		if (day != 0)

			cal.add(Calendar.DATE, day);

		return sdf.format(cal.getTime());

	}



	/**

	 * <p>yyyyMMdd Ȥ�� yyyy-MM-dd ������ ��¥ ���ڿ��� �Է� �޾� ����

	 * �����Ѵ�. <code>year</code>�� ������ ���� �ǹ��ϸ�, ������ �Է��� ��� ���Ѵ�.</p>

	 *

	 * <pre>

	 * DateUtil.addYear("20000201", 62)  = "20620201"

	 * DateUtil.addYear("20620201", -62) = "20000201"

	 * DateUtil.addYear("20040229", 2)   = "20060228"

	 * DateUtil.addYear("20060228", -2)  = "20040228"

	 * DateUtil.addYear("19000101", 200) = "21000101"

	 * </pre>

	 *

	 * @param  dateStr ��¥ ���ڿ�(yyyyMMdd, yyyy-MM-dd�� ����)

	 * @param  year ������ ��. 0�� �Էµ� ��� ������ ����

	 * @return  yyyyMMdd ������ ��¥ ���ڿ�

	 * @throws IllegalArgumentException ��¥ ������ ������ �ٿ� �ٸ� ���.

	 *         �Է� ���� <code>null</code>�� ���.

	 */

	public static String addYear(String dateStr, int year) {

		return addYearMonthDay(dateStr, year, 0, 0);

	}



	/**

	 * <p>yyyyMMdd Ȥ�� yyyy-MM-dd ������ ��¥ ���ڿ��� �Է� �޾� ����

	 * �����Ѵ�. <code>month</code>�� ������ ���� �ǹ��ϸ�, ������ �Է��� ��� ���Ѵ�.</p>

	 *

	 * <pre>

	 * DateUtil.addMonth("20010201", 12)  = "20020201"

	 * DateUtil.addMonth("19800229", 12)  = "19810228"

	 * DateUtil.addMonth("20040229", 12)  = "20050228"

	 * DateUtil.addMonth("20050228", -12) = "20040228"

	 * DateUtil.addMonth("20060131", 1)   = "20060228"

	 * DateUtil.addMonth("20060228", -1)  = "20060128"

	 * </pre>

	 *

	 * @param  dateStr ��¥ ���ڿ�(yyyyMMdd, yyyy-MM-dd�� ����)

	 * @param  month ������ ��. 0�� �Էµ� ��� ������ ����

	 * @return  yyyyMMdd ������ ��¥ ���ڿ�

	 * @throws IllegalArgumentException ��¥ ������ ������ �ٿ� �ٸ� ���.

	 *         �Է� ���� <code>null</code>�� ���.

	 */

	public static String addMonth(String dateStr, int month) {

		return addYearMonthDay(dateStr, 0, month, 0);

	}



	/**

	 * <p>yyyyMMdd Ȥ�� yyyy-MM-dd ������ ��¥ ���ڿ��� �Է� �޾� ��(day)��

	 * �����Ѵ�. <code>day</code>�� ������ ���� �ǹ��ϸ�, ������ �Է��� ��� ���Ѵ�.

	 * <br/><br/>

	 * ���� ���ǵ� addDays �޼���� ����ڰ� ParseException�� �ݵ�� ó���ؾ� �ϴ� ��������

	 * �ֱ� ������ �߰��� �޼����̴�.</p>

	 *

	 * <pre>

	 * DateUtil.addDay("19991201", 62) = "20000201"

	 * DateUtil.addDay("20000201", -62) = "19991201"

	 * DateUtil.addDay("20050831", 3) = "20050903"

	 * DateUtil.addDay("20050831", 3) = "20050903"

	 * // 2006�� 6�� 31���� ������ �������� �ʴ� ��¥�̴� -> 20060701�� ���ֵȴ�

	 * DateUtil.addDay("20060631", 1) = "20060702"

	 * </pre>

	 *

	 * @param  dateStr ��¥ ���ڿ�(yyyyMMdd, yyyy-MM-dd�� ����)

	 * @param  day ������ ��. 0�� �Էµ� ��� ������ ����

	 * @return  yyyyMMdd ������ ��¥ ���ڿ�

	 * @throws IllegalArgumentException ��¥ ������ ������ �ٿ� �ٸ� ���.

	 *         �Է� ���� <code>null</code>�� ���.

	 */

	public static String addDay(String dateStr, int day) {

		return addYearMonthDay(dateStr, 0, 0, day);

	}



	/**

	 * <p>yyyyMMdd Ȥ�� yyyy-MM-dd ������ ��¥ ���ڿ� <code>dateStr1</code>�� <code>

	 * dateStr2</code> ������ �� ���� ���Ѵ�.<br>

	 * <code>dateStr2</code>�� <code>dateStr1</code> ���� ���� ��¥�� ��쿡��

	 * ������ ��ȯ�Ѵ�. ������ ��쿡�� 0�� ��ȯ�Ѵ�.</p>

	 *

	 * <pre>

	 * DateUtil.getDaysDiff("20060228","20060310") = 10

	 * DateUtil.getDaysDiff("20060101","20070101") = 365

	 * DateUtil.getDaysDiff("19990228","19990131") = -28

	 * DateUtil.getDaysDiff("20060801","20060802") = 1

	 * DateUtil.getDaysDiff("20060801","20060801") = 0

	 * </pre>

	 *

	 * @param  dateStr1 ��¥ ���ڿ�(yyyyMMdd, yyyy-MM-dd�� ����)

	 * @param  dateStr2 ��¥ ���ڿ�(yyyyMMdd, yyyy-MM-dd�� ����)

	 * @return  �� �� ����.

	 * @throws IllegalArgumentException ��¥ ������ ������ �ٿ� �ٸ� ���.

	 *         �Է� ���� <code>null</code>�� ���.

	 */

	public static int getDaysDiff(String sDate1, String sDate2) {

		String dateStr1 = sDate1;

		String dateStr2 = sDate2;



		if (!checkDate(sDate1) || !checkDate(sDate2)) {

			throw new IllegalArgumentException("Invalid date format: args[0]=" + sDate1 + " args[1]=" + sDate2);

		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());



		Date date1 = null;

		Date date2 = null;

		try {

			date1 = sdf.parse(dateStr1);

			date2 = sdf.parse(dateStr2);

		} catch (ParseException e) {

			throw new IllegalArgumentException("Invalid date format: args[0]=" + dateStr1 + " args[1]=" + dateStr2);

		}

		int days1 = (int) ((date1.getTime() / 3600000) / 24);

		int days2 = (int) ((date2.getTime() / 3600000) / 24);



		return days2 - days1;

	}



	/**

	 * <p>yyyyMMdd Ȥ�� yyyy-MM-dd ������ ��¥ ���ڿ��� �Է� �޾� ��ȿ�� ��¥���� �˻�.</p>

	 *

	 * <pre>

	 * DateUtil.checkDate("1999-02-35") = false

	 * DateUtil.checkDate("2000-13-31") = false

	 * DateUtil.checkDate("2006-11-31") = false

	 * DateUtil.checkDate("2006-2-28")  = false

	 * DateUtil.checkDate("2006-2-8")   = false

	 * DateUtil.checkDate("20060228")   = true

	 * DateUtil.checkDate("2006-02-28") = true

	 * </pre>

	 *

	 * @param  dateStr ��¥ ���ڿ�(yyyyMMdd, yyyy-MM-dd�� ����)

	 * @return  ��ȿ�� ��¥���� ����

	 */

	public static boolean checkDate(String sDate) {

		String dateStr = sDate;



		String year = dateStr.substring(0, 4);

		String month = dateStr.substring(4, 6);

		String day = dateStr.substring(6);



		return checkDate(year, month, day);

	}



	/**

	 * <p>�Է��� ��, ��, ���� ��ȿ���� �˻�.</p>

	 *

	 * @param  year ����

	 * @param  month ��

	 * @param  day ��

	 * @return  ��ȿ�� ��¥���� ����

	 */

	public static boolean checkDate(String year, String month, String day) {

		try {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());



			Date result = formatter.parse(year + "." + month + "." + day);

			String resultStr = formatter.format(result);

			if (resultStr.equalsIgnoreCase(year + "." + month + "." + day))

				return true;

			else

				return false;

		} catch (ParseException e) {

			return false;

		}

	}



	/**

	 * ��¥������ String�� ��¥ ���� �� TimeZone�� ������ �ִ� �޼���

	 *

	 * @param  strSource       �ٲ� ��¥ String

	 * @param  fromDateFormat  ������ ��¥ ����

	 * @param  toDateFormat    ���ϴ� ��¥ ����

	 * @param  strTimeZone     ������ TimeZone(""�̸� ���� ����)

	 * @return  �ҽ� String�� ��¥ ������ ������ String

	 */

	


	/**

	 * yyyyMMdd ������ ��¥���ڿ��� ���ϴ� ĳ����(ch)�� �ɰ� �����ش�<br/>

	* <pre>

	* ex) 20030405, ch(.) -> 2003.04.05

	* ex) 200304, ch(.) -> 2003.04

	* ex) 20040101,ch(/) --> 2004/01/01 �� ����

	* </pre>

	*

	* @param date yyyyMMdd ������ ��¥���ڿ�

	* @param ch ������

	* @return ��ȯ�� ���ڿ�

	 */

	public static String formatDate(String sDate, String ch) {

		String dateStr = sDate;



		String str = dateStr.trim();

		String yyyy = "";

		String mm = "";

		String dd = "";



		if (str.length() == 8) {

			yyyy = str.substring(0, 4);

			if (yyyy.equals("0000"))

				return "";



			mm = str.substring(4, 6);

			if (mm.equals("00"))

				return yyyy;



			dd = str.substring(6, 8);

			if (dd.equals("00"))

				return yyyy + ch + mm;



			return yyyy + ch + mm + ch + dd;

		} else if (str.length() == 6) {

			yyyy = str.substring(0, 4);

			if (yyyy.equals("0000"))

				return "";



			mm = str.substring(4, 6);

			if (mm.equals("00"))

				return yyyy;



			return yyyy + ch + mm;

		} else if (str.length() == 4) {

			yyyy = str.substring(0, 4);

			if (yyyy.equals("0000"))

				return "";

			else

				return yyyy;

		} else

			return "";

	}



	/**

	 * HH24MISS ������ �ð����ڿ��� ���ϴ� ĳ����(ch)�� �ɰ� �����ش� <br>

	 * <pre>

	 *     ex) 151241, ch(/) -> 15/12/31

	 * </pre>

	 *

	 * @param str HH24MISS ������ �ð����ڿ�

	 * @param ch ������

	 * @return ��ȯ�� ���ڿ�

	 */

	public static String formatTime(String sTime, String ch) {

		String timeStr = sTime;

		return timeStr.substring(0, 2) + ch + timeStr.substring(2, 4) + ch + timeStr.substring(4, 6);

	}



	/**

	 * ������ �Է� �޾� �ش� ���� 2���� ����(�ϼ�)�� ���ڿ��� ��ȯ�Ѵ�.

	 *

	 * @param year

	 * @return �ش� ���� 2���� ����(�ϼ�)

	 */

	public String leapYear(int year) {

		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {

			return "29";

		}



		return "28";

	}



	/**

	 * <p>�Է¹��� ������ �������� �ƴ��� �˻��Ѵ�.</p>

	 *

	 * <pre>

	 * DateUtil.isLeapYear(2004) = false

	 * DateUtil.isLeapYear(2005) = true

	 * DateUtil.isLeapYear(2006) = true

	 * </pre>

	 *

	 * @param  year ����

	 * @return  ���� ����

	 */

	public static boolean isLeapYear(int year) {

		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {

			return false;

		}

		return true;

	}



	/**

	 * ����(�ѱ�����) ��¥������ ��´�.                     <BR>

	 * ǥ����� yyyy-mm-dd                                  <BR>

	 * @return  String      yyyymmdd������ ���� �ѱ��ð�.   <BR>

	 */

	public static String getToday() {

		return getCurrentDate("");

	}



	/**

	 * ����(�ѱ�����) ��¥������ ��´�.                     <BR>

	 * ǥ����� yyyy-mm-dd                                  <BR>

	 * @return  String      yyyymmdd������ ���� �ѱ��ð�.   <BR>

	 */

	public static String getCurrentDate(String dateType) {

		Calendar aCalendar = Calendar.getInstance();



		int year = aCalendar.get(Calendar.YEAR);

		int month = aCalendar.get(Calendar.MONTH) + 1;

		int date = aCalendar.get(Calendar.DATE);

		String strDate = Integer.toString(year) + ((month < 10) ? "0" + Integer.toString(month) : Integer.toString(month))

				+ ((date < 10) ? "0" + Integer.toString(date) : Integer.toString(date));



		if (!"".equals(dateType))

			strDate = convertDate(strDate, "yyyyMMdd", dateType);



		return strDate;

	}



	/**

	 * ��¥������ String�� ��¥ ���˸��� ������ �ִ� �޼���

	 * @param sDate ��¥

	 * @param sTime �ð�

	 * @param sFormatStr ���� ��Ʈ�� ���ڿ�

	 * @return ������ ��¥/�ð��� ������ �������� ���

	 * @See Letter  Date or Time Component  Presentation  Examples

	           G  Era designator  Text  AD

	           y  Year  Year  1996; 96

	           M  Month in year  Month  July; Jul; 07

	           w  Week in year  Number  27

	           W  Week in month  Number  2

	           D  Day in year  Number  189

	           d  Day in month  Number  10

	           F  Day of week in month  Number  2

	           E  Day in week  Text  Tuesday; Tue

	           a  Am/pm marker  Text  PM

	           H  Hour in day (0-23)  Number  0

	           k  Hour in day (1-24)  Number  24

	           K  Hour in am/pm (0-11)  Number  0

	           h  Hour in am/pm (1-12)  Number  12

	           m  Minute in hour  Number  30

	           s  Second in minute  Number  55

	           S  Millisecond  Number  978

	           z  Time zone  General time zone  Pacific Standard Time; PST; GMT-08:00

	           Z  Time zone  RFC 822 time zone  -0800







	           Date and Time Pattern  Result

	           "yyyy.MM.dd G 'at' HH:mm:ss z"  2001.07.04 AD at 12:08:56 PDT

	           "EEE, MMM d, ''yy"  Wed, Jul 4, '01

	           "h:mm a"  12:08 PM

	           "hh 'o''clock' a, zzzz"  12 o'clock PM, Pacific Daylight Time

	           "K:mm a, z"  0:08 PM, PDT

	           "yyyyy.MMMMM.dd GGG hh:mm aaa"  02001.July.04 AD 12:08 PM

	           "EEE, d MMM yyyy HH:mm:ss Z"  Wed, 4 Jul 2001 12:08:56 -0700

	           "yyMMddHHmmssZ"  010704120856-0700



	 */

	public static String convertDate(String sDate, String sTime, String sFormatStr) {

		String dateStr = sDate;

		String timeStr = sTime;



		Calendar cal = null;

		cal = Calendar.getInstance();



		cal.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0, 4)));

		cal.set(Calendar.MONTH, Integer.parseInt(dateStr.substring(4, 6)) - 1);

		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6, 8)));

		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeStr.substring(0, 2)));

		cal.set(Calendar.MINUTE, Integer.parseInt(timeStr.substring(2, 4)));



		SimpleDateFormat sdf = new SimpleDateFormat(sFormatStr, Locale.ENGLISH);



		return sdf.format(cal.getTime());

	}



	/**

	 * �Է¹��� ���� ������ ������ ���ڸ� ��ȯ

	 * @param sDate1 ��������

	 * @param sDate2 ��������

	 * @return ��������

	 */

	public static String getRandomDate(String sDate1, String sDate2) {

		String dateStr1 = sDate1;

		String dateStr2 = sDate2;



		String randomDate = null;



		int sYear, sMonth, sDay;

		int eYear, eMonth, eDay;



		sYear = Integer.parseInt(dateStr1.substring(0, 4));

		sMonth = Integer.parseInt(dateStr1.substring(4, 6));

		sDay = Integer.parseInt(dateStr1.substring(6, 8));



		eYear = Integer.parseInt(dateStr2.substring(0, 4));

		eMonth = Integer.parseInt(dateStr2.substring(4, 6));

		eDay = Integer.parseInt(dateStr2.substring(6, 8));



		GregorianCalendar beginDate = new GregorianCalendar(sYear, sMonth - 1, sDay, 0, 0);

		GregorianCalendar endDate = new GregorianCalendar(eYear, eMonth - 1, eDay, 23, 59);



		if (endDate.getTimeInMillis() < beginDate.getTimeInMillis()) {

			throw new IllegalArgumentException("Invalid input date : " + sDate1 + "~" + sDate2);

		}



		SecureRandom r = new SecureRandom();



		long rand = ((r.nextLong() >>> 1) % (endDate.getTimeInMillis() - beginDate.getTimeInMillis() + 1)) + beginDate.getTimeInMillis();



		GregorianCalendar cal = new GregorianCalendar();

		//SimpleDateFormat calformat = new SimpleDateFormat("yyyy-MM-dd");

		SimpleDateFormat calformat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);

		cal.setTimeInMillis(rand);

		randomDate = calformat.format(cal.getTime());



		// �������ڿ��� ����

		return randomDate;

	}



	

	


	/**

	 * �Է¹��� ������ �������� �������� ���Ϸ� ��ȯ

	 * @param sWeek ���� ���ϸ�

	 * @return ���� ���ϸ�

	 */

	public static String convertWeek(String sWeek) {

		String retStr = null;



		if (sWeek.equals("SUN")) {

			retStr = "�Ͽ���";

		} else if (sWeek.equals("MON")) {

			retStr = "������";

		} else if (sWeek.equals("TUE")) {

			retStr = "ȭ����";

		} else if (sWeek.equals("WED")) {

			retStr = "������";

		} else if (sWeek.equals("THR")) {

			retStr = "�����";

		} else if (sWeek.equals("FRI")) {

			retStr = "�ݿ���";

		} else if (sWeek.equals("SAT")) {

			retStr = "�����";

		}



		return retStr;

	}



	/**

	 * �Է������� ��ȿ ���θ� Ȯ��

	 * @param sDate ����

	 * @return ��ȿ ����

	 */

	public static boolean validDate(String sDate) {

		String dateStr = sDate;



		Calendar cal;

		boolean ret = false;



		cal = Calendar.getInstance();



		cal.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0, 4)));

		cal.set(Calendar.MONTH, Integer.parseInt(dateStr.substring(4, 6)) - 1);

		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6, 8)));



		String year = String.valueOf(cal.get(Calendar.YEAR));

		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);

		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));



		String pad4Str = "0000";

		String pad2Str = "00";



		String retYear = (pad4Str + year).substring(year.length());

		String retMonth = (pad2Str + month).substring(month.length());

		String retDay = (pad2Str + day).substring(day.length());



		String retYMD = retYear + retMonth + retDay;



		if (sDate.equals(retYMD)) {

			ret = true;

		}



		return ret;

	}



	/**

	 * �Է�����, ������ ��ȿ ���θ� Ȯ��

	 * @param     sDate ����

	 * @param     sWeek ���� (DAY_OF_WEEK)

	 * @return    ��ȿ ����

	 */

	public static boolean validDate(String sDate, int sWeek) {

		String dateStr = sDate;



		Calendar cal;

		boolean ret = false;



		cal = Calendar.getInstance();



		cal.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0, 4)));

		cal.set(Calendar.MONTH, Integer.parseInt(dateStr.substring(4, 6)) - 1);

		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6, 8)));



		int Week = cal.get(Calendar.DAY_OF_WEEK);



		if (validDate(sDate)) {

			if (sWeek == Week) {

				ret = true;

			}

		}



		return ret;

	}



	/**

	 * �Է½ð��� ��ȿ ���θ� Ȯ��

	 * @param     sTime �Է½ð�

	 * @return    ��ȿ ����

	 */

	public static boolean validTime(String sTime) {

		String timeStr = sTime;



		Calendar cal;

		boolean ret = false;



		cal = Calendar.getInstance();



		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeStr.substring(0, 2)));

		cal.set(Calendar.MINUTE, Integer.parseInt(timeStr.substring(2, 4)));



		String HH = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));

		String MM = String.valueOf(cal.get(Calendar.MINUTE));



		String pad2Str = "00";



		String retHH = (pad2Str + HH).substring(HH.length());

		String retMM = (pad2Str + MM).substring(MM.length());



		String retTime = retHH + retMM;



		if (sTime.equals(retTime)) {

			ret = true;

		}



		return ret;

	}



	/**

	 * �Էµ� ���ڿ� ��, ��, ���� ������ ��¥�� ������ ��ȯ

	 * @param sDate ��¥

	 * @param year ��

	 * @param month ��

	 * @param day ��

	 * @return ���� ������ ����(DAY_OF_WEEK)

	 */

	public static String addYMDtoWeek(String sDate, int year, int month, int day) {

		String dateStr = sDate;



		dateStr = addYearMonthDay(dateStr, year, month, day);



		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);

		try {

			cal.setTime(sdf.parse(dateStr));

		} catch (ParseException e) {

			throw new IllegalArgumentException("Invalid date format: " + dateStr);

		}



		SimpleDateFormat rsdf = new SimpleDateFormat("E", Locale.ENGLISH);



		return rsdf.format(cal.getTime());

	}



	/**

	 * �Էµ� ���ڿ� ��, ��, ��, �ð�, ���� ������ ��¥, �ð��� ���佺Ʈ�� �������� ��ȯ

	 * @param sDate ��¥

	 * @param sTime �ð�

	 * @param year ��

	 * @param month ��

	 * @param day ��

	 * @param hour �ð�

	 * @param minute ��

	 * @param formatStr ���佺Ʈ��

	 * @return

	 */

	public static String addYMDtoDayTime(String sDate, String sTime, int year, int month, int day, int hour, int minute, String formatStr) {

		String dateStr = sDate;

		String timeStr = sTime;



		dateStr = addYearMonthDay(dateStr, year, month, day);



		dateStr = convertDate(dateStr, timeStr, "yyyyMMddHHmm");



		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm", Locale.ENGLISH);



		try {

			cal.setTime(sdf.parse(dateStr));

		} catch (ParseException e) {

			throw new IllegalArgumentException("Invalid date format: " + dateStr);

		}



		if (hour != 0) {

			cal.add(Calendar.HOUR, hour);

		}



		if (minute != 0) {

			cal.add(Calendar.MINUTE, minute);

		}



		SimpleDateFormat rsdf = new SimpleDateFormat(formatStr, Locale.ENGLISH);



		return rsdf.format(cal.getTime());

	}



	/**

	 * �Էµ� ���ڸ� int ������ ��ȯ

	 * @param sDate ����

	 * @return int(����)

	 */

	public static int datetoInt(String sDate) {

		return Integer.parseInt(convertDate(sDate, "0000", "yyyyMMdd"));

	}



	/**

	 * �Էµ� �ð��� int ������ ��ȯ

	 * @param sTime �ð�

	 * @return int(�ð�)

	 */

	public static int timetoInt(String sTime) {

		return Integer.parseInt(convertDate("00000101", sTime, "HHmm"));

	}



}
