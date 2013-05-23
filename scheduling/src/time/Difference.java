package time;

public class Difference {

	private static int secondsInWeek = 604800;
	private static int secondsInDay = 86400;
	private static int secondsInHour = 3600;
	private static int secondsInMinute = 60;

	public int weeks;
	public int days;
	public int hours;
	public int minutes;
	public int seconds;

	public Difference(int s) {
		int seconds = s;
		weeks = seconds / secondsInWeek;
		seconds %= secondsInWeek;
		days = seconds / secondsInDay;
		seconds %= secondsInDay;
		hours = seconds / secondsInHour;
		seconds %= secondsInHour;
		minutes = seconds / secondsInMinute;
		seconds %= secondsInMinute;
		this.seconds = seconds;
	}

	public String toString() {
		String timeDiff = String.format(
				"Weeks: %d\nDays: %d\nHours: %d\nMinutes: %d\nSeconds: %d",
				weeks, days, hours, minutes, seconds);
		return timeDiff;
	}
}
