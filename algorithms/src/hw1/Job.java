package hw1;

import hw1.Problems1_2.Algorithm;

public class Job {

	private int weight;
	private int length;
	private int difference;
	private double ratio;

	public Job(int weight, int length) {
		this.weight = weight;
		this.length = length;
		difference = weight - length;
		ratio = weight * 1.0 / length * 1.0;
	}

	public int getWeight() {
		return weight;
	}

	public int getLength() {
		return length;
	}

	public int getDiff() {
		return difference;
	}

	public double getRatio() {
		return ratio;
	}

	public double greedyScore(Algorithm alg) {
		switch (alg) {
		case DIFFERENCE:
			return difference;
		case RATIO:
			return ratio;
		default:
			throw new IllegalArgumentException();
		}
	}
}
