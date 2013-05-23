package stats;

public class Std {

	//public static final double[] data = { 0.79, 0.70, 0.73, 0.66, 0.65, 0.70,
	//		0.74, 0.81, 0.71, 0.70 };
	
	public static final double[] data = {0,2,2};

	public static void main(String[] args) {
		std(data);
	}

	public static double mean(double[] data) {
		double acc = 0;
		for (double i : data) {
			acc += i;
		}
		double mean = acc / data.length;
		System.out.println(String.format("Mean: %.5f", mean));
		return mean;
	}

	public static double variance(double[] data) {
		double variance = 0;
		double mean = mean(data);
		for (double i : data) {
			variance += Math.pow(i - mean, 2.0);
		}
		variance = variance / data.length;
		System.out.println(String.format("Variance: %.5f", variance));
		return variance;
	}

	public static double std(double[] data) {
		double variance = variance(data);
		double std = Math.sqrt(variance);
		System.out.println(String.format("Standard Deviation: %.5f", std));
		return std;
	}
}
