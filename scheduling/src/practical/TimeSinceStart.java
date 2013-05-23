package practical;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import time.Date;
import time.Difference;
import time.Time;

public class TimeSinceStart {

	private static final Date START_DATE;
	static {
		Time startTime = new Time(3, 24);
		START_DATE = new Date(startTime, 2013, 2, 9);
	}

	public static void main(String[] args) {
		Date curDate = currentTime();
		int secondsDiff = curDate.difference(TimeSinceStart.START_DATE);
		System.out.println("Difference in seconds: " + secondsDiff);
		Difference diff = Date.formatSeconds(secondsDiff);
		System.out.println(diff.toString());
	}

	public TimeSinceStart() {
	}

	public String formattedTime() {
		Date curDate = currentTime();
		int secondsDiff = curDate.difference(TimeSinceStart.START_DATE);
		System.out.println("Difference in seconds: " + secondsDiff);
		Difference diff = Date.formatSeconds(secondsDiff);
		return diff.toString();
	}

	public static Date currentTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
		Calendar cal = Calendar.getInstance();
		String[] f = dateFormat.format(cal.getTime()).split(" ");
		int year = Integer.parseInt(f[0]);
		int month = Integer.parseInt(f[1]);
		int day = Integer.parseInt(f[2]);
		int hour = Integer.parseInt(f[3]);
		int minute = Integer.parseInt(f[4]);
		int second = Integer.parseInt(f[5]);
		Time time = new Time(hour, minute, second);
		Date date = new Date(time, year, month, day);
		return date;
	}
}
