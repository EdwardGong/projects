package time;

import java.util.HashMap;

public class Date implements Comparable<Date> {

	private static int secondsInYear = 31536000;
	private static int secondsInDay = 86400;
	private static Month[] months = { Month.January, Month.February, Month.March,
			Month.April, Month.May, Month.June, Month.July, Month.August,
			Month.September, Month.October, Month.November, Month.December };

	public int year;
	public Month month;
	public Time time;
	public int day;

	public enum Month {
		January(1, 0), February(2, 31), March(3, 59), April(4, 90), May(5, 120), June(
				6, 151), July(7, 181), August(8, 212), September(9, 243), October(
				10, 273), November(11, 304), December(12, 334);

		public final int asNumber;
		public final int numDays;

		Month(int asNumber, int days) {
			this.asNumber = asNumber;
			numDays = days;
		}

		public String asWord() {
			return this.toString();
		}
	}

	public Date(Time time, int year, int month, int day) {
		this.time = time;
		this.month = months[month-1];
		this.year = year;
		this.day = day;
	}

	/**
	 * Calculates difference in seconds. Method is to be used to determine
	 * urgency. Makes assumption year has 365 days Makes assumption months have
	 * 30 days for non-consecutive months
	 * 
	 * Positive value means this date is ahead of compared with date
	 * 
	 * @param date
	 * @return difference in time in seconds
	 */
	public int difference(Date date) {
		int leapDays = calcLeapDays(date);
		int yearDiff = secondsInYear * (this.year - date.year);
		int dayInYear1 = month.numDays + day;
		//System.out.println(month.asWord() + " " + Integer.toString(day).toString());
		int dayInYear2 = date.month.numDays + date.day;
		//System.out.println(date.month.asWord() + " " + Integer.toString(date.day).toString());
		int dayDiff = secondsInDay * (dayInYear1 - dayInYear2 + leapDays);
		int timeDiff = time.difference(date.time);
		return yearDiff + dayDiff + timeDiff;
	}
	
	public static Difference formatSeconds(int seconds){
		Difference diff = new Difference(seconds);
		return diff;
	}

	/**
	 * Calculates the number of leap days between this date and the date given
	 * as input. A positive value corresponds to this date being ahead of the
	 * input date.
	 * 
	 * @param date
	 * @return number of leap days
	 */
	private int calcLeapDays(Date date) {
		int leapDays = 0;
		boolean isEarlier = before(date);
		if (this.year < date.year) {
			if (isLeapYear(this.year) && month.asNumber <= 2) {
				leapDays--;
			}
			for (int i = this.year + 1; i < date.year; i++) {
				if (isLeapYear(i)) {
					leapDays--;
				}
			}
			if (isLeapYear(date.year) && month.asNumber > 2) {
				leapDays--;
			}
		} else if (this.year > date.year) {
			if (isLeapYear(date.year) && month.asNumber <= 2) {
				leapDays++;
			}
			for (int i = date.year + 1; i < this.year; i++) {
				if (isLeapYear(i)) {
					leapDays++;
				}
			}
			if (isLeapYear(this.year) && month.asNumber > 2) {
				leapDays++;
			}
		} else {
			if (isLeapYear(year)) {
				if (isEarlier && month.asNumber <= 2 && date.month.asNumber > 2) {
					leapDays = -1;
				} else if (!isEarlier && date.month.asNumber <= 2
						&& month.asNumber > 2) {
					leapDays = 1;
				}
			}
		}
		return leapDays;
	}

	/**
	 * 
	 * @param date
	 * @return true if this date is before {@code date}
	 */
	public boolean before(Date date) {
		int value1 = secondsSoFar(this);
		int value2 = secondsSoFar(date);
		return value2 > value1;
	}

	/**
	 * 
	 * @param date
	 * @return true if this date is after {@code date}
	 */
	public boolean after(Date date) {
		int value1 = secondsSoFar(this);
		int value2 = secondsSoFar(date);
		return value1 > value2;
	}

	/**
	 * 
	 * @param date
	 * @return true if this date is equal to {@code date}
	 */
	public boolean equals(Date date) {
		int value1 = secondsSoFar(this);
		int value2 = secondsSoFar(date);
		return value1 == value2;
	}

	/**
	 * Counting from the very start of the 21st century, the number of seconds
	 * allotted for a date
	 * 
	 * @return
	 */
	public static int secondsSoFar(Date date) {
		int value = 0;
		value += (date.year - 2000) * secondsInYear;
		value += (date.month.numDays + date.day) * secondsInDay;
		value += date.time.secondsSoFar();
		return value;
	}

	@Override
	public int compareTo(Date date) {
		if (year == date.year) {
			if (month.asNumber == date.month.asNumber) {

			} else {
				if (month.asNumber > date.month.asNumber) {
					return 1;
				} else {
					return -1;
				}
			}
		} else {
			if (year > date.year)
				return 1;
			else
				return -1;
		}

		return 0;
	}

	/**
	 * Determines whether {@code year} is a leap year
	 * 
	 * @param year
	 */
	private static boolean isLeapYear(int year) {
		if (year % 400 == 0) {
			return true;
		} else if (year % 100 == 0) {
			return false;
		} else if (year % 4 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString(){
		return String.format("Year: %d\nMonth: %s\nDay: %d\n", year, month, day) + time.toString();
	}
}
