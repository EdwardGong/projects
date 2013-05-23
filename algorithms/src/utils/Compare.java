package utils;

public class Compare {

	private Compare() {
	}

	public static <T extends Comparable<T>> T max(T comp1, T comp2) {
		if (comp1.compareTo(comp2) > 0)
			return comp1;
		else
			return comp2;
	}

	public static <T extends Comparable<T>> T min(T comp1, T comp2) {
		if (comp1.compareTo(comp2) > 0)
			return comp2;
		else
			return comp1;
	}
}
