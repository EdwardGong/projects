package hw1;

public class Difference extends Job {

	/**
	 * Note: this class has a natural ordering that is inconsistent with equals.
	 */

	public int difference;

	public Difference(int weight, int length) {
		super(weight, length);
		difference = weight - length;
	}

}
