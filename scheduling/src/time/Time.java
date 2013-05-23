package time;

public class Time implements Comparable<Time> {

	private static final int secondsInHour = 3600;
	private static final int secondsInMinute = 60;

	public int hour;
	public int minute;
	public int second = 0;

	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	public Time(int hour, int minute, int seconds) {
		this.hour = hour;
		this.minute = minute;
		this.second = seconds;
	}

	public int secondsSoFar(){
		int seconds = hour * secondsInHour;
		seconds += minute * secondsInMinute;
		seconds += second;
		return seconds;
	}

	public int difference(Time t) {
		int hourDiff = 3600 * (hour - t.hour);
		int minuteDiff = 60 * (minute - t.minute);
		int secondDiff = second - t.second;
		return hourDiff + minuteDiff + secondDiff;
	}

	@Override
	public int compareTo(Time t) {
		int value1 = hour * 3600 + minute * 60 + second;
		int value2 = t.hour * 3600 + t.minute * 60 + t.second;
		if (value1 > value2)
			return 1;
		else if (value2 > value1)
			return -1;
		else
			return 0;
	}
	
	@Override
	public String toString(){
		return String.format("Hour: %d\nMinute: %d\nSecond: %d", hour, minute, second);
	}
}
