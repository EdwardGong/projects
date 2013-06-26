package stats;

import java.util.ArrayList;
import java.util.Scanner;

public class Std {

	// public static final double[] TEST_DATA = { 0.79, 0.70, 0.73, 0.66, 0.65,
	// 0.70,
	// 0.74, 0.81, 0.71, 0.70 };

	public static final double[] TEST_DATA = { 0, 2, 2 };
	private static final int MANUAL_INPUT = 0;
	private static final int READ_FROM_FILE = 1;
	private static final String PROMPT = "Please select an input method:\n"
			+ "0. Manual input via keyboard.\n"
			+ "1. Automatic input via file.";
	Scanner in;

	public static void main(String[] args) {
		int response = inputOptions();
		performAction(response);
		// analyze(TEST_DATA);
	}

	/**
	 * 1. Prints prompt to allow user to select input method. 2. Reads in and
	 * returns user's selection.
	 */
	public static int inputOptions() {
		System.out.println(PROMPT);
		int responseNum = -1;
		Scanner in = new Scanner(System.in);
		// Ensure that response is 0 or 1
		while (in.hasNext()) {
			String rawResponse = in.nextLine();
			String response = rawResponse.trim();
			responseNum = Integer.parseInt(response);
			if (responseNum == 0 || responseNum == 1) {
				return responseNum;
			} else {
				System.out
						.println("Sorry, I couldn't understand your input.\n"
								+ "Please type 0 if you would like to input manually, or 1 if you would like to select a file.");
			}
		}
		return responseNum;
	}

	/**
	 * User inputs data via keyboard. All numbers are accepted, but file will
	 * terminate in the case of a line containing anything beside space
	 * delimited numbers.
	 * 
	 * @return an Arraylist of the inputted data
	 */
	private static ArrayList<Double> manualInput() {
		ArrayList<Double> lineData = new ArrayList<Double>();
		// Cumulative data
		ArrayList<Double> cumData = new ArrayList<Double>();
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String line = in.nextLine();
			try {
				lineData = stringToArr(line);
				cumData.addAll(lineData);
			} catch (NumberFormatException e) {
				return cumData;
			}
		}
		return cumData;
	}

	/**
	 * Takes in an action. This action will specify how the code will receive
	 * user input. After user input is received, statistics of the data will be
	 * printed to console.
	 * 
	 * @param action
	 */
	public static void performAction(int action) {
		switch (action) {
		case MANUAL_INPUT:
			ArrayList<Double> rawData = manualInput();
			double[] data = new double[rawData.size()];
			for (int i = 0; i < rawData.size(); ++i)
				data[i] = rawData.get(i).doubleValue();
			analyze(data);
			break;
		case READ_FROM_FILE:

			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	// private static void readFromFile() {
	// return;
	// }

	private static ArrayList<Double> stringToArr(String str)
			throws NumberFormatException {
		String[] split = str.split(" ");
		ArrayList<Double> arrList = new ArrayList<Double>();
		for (int i = 0; i < split.length; ++i) {
			arrList.add(Double.parseDouble(split[i]));
		}
		return arrList;
	}

	public static void analyze(double[] data) {
		double mean = mean(data);
		System.out.println(String.format("Mean: %.5f", mean));
		double var = variance(data);
		System.out.println(String.format("Variance: %.5f", var));
		double std = std(data);
		System.out.println(String.format("Standard Deviation: %.5f", std));
	}

	public static double mean(double[] data) {
		double acc = 0;
		for (double i : data) {
			acc += i;
		}
		double mean = acc / data.length;
		return mean;
	}

	public static double variance(double[] data) {
		double variance = 0;
		double mean = mean(data);
		for (double i : data) {
			variance += Math.pow(i - mean, 2.0);
		}
		variance = variance / data.length;
		return variance;
	}

	public static double std(double[] data) {
		double variance = variance(data);
		double std = Math.sqrt(variance);
		return std;
	}

}