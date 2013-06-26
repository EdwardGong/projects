package stats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Std {

	// public static final double[] TEST_DATA = { 0.79, 0.70, 0.73, 0.66, 0.65,
	// 0.70,
	// 0.74, 0.81, 0.71, 0.70 };

	public static final double[] TEST_DATA = { 0, 2, 2 };
	private static final int MANUAL_INPUT = 0;
	private static final int AUTO_INPUT = 1;
	private static final String PROMPT = "Please select an input method:\n"
			+ "0. Manual input via keyboard.\n"
			+ "1. Automatic input via file.";
	private static final String MANUAL_MSG = "Please enter data that you would like to analyze in the following format:\n"
			+ "<number> <number> <number> ...\n"
			+ "Numbers should be separated by only a space. Decimals are permitted.\n"
			+ "When you are done, simply hit enter or type anything that does not conform to acceptable format.";
	private static final String AUTO_MSG = "Please enter the name of the file that you would like to have analyzed.\n"
			+ "Please note that:\n"
			+ "\t 1. The selected file should be in the same directory as this file.\n"
			+ "\t 2. The data from the selected file should adhere to the format:\n"
			+ "<number> <number> <number> ...\n"
			+ "<number> <number> <number> ...\n"
			+ "<number> <number> <number> ...\n"
			+ "\t    .\n"
			+ "\t    .\n"
			+ "\t    .\n"
			+ "Data on the same line should only be separated by one space.\n";
	private static final String FILE_FORMAT_ERR_MSG = "File is in the wrong format. Please try a different file.";
	private static final String FILE_NOT_FOUND_MSG = "File is not found in working directory. Please a different file.";
	private static final String INVALID_ENTRY_MSG = "Sorry, I couldn't understand your input.\n"
			+ "Please type 0 if you would like to input manually, or 1 if you would like to select a file.";

	private static BufferedReader reader;
	private static Scanner in;

	public static void main(String[] args) {
		int response = inputOptions();
		performAction(response);
	}

	/**
	 * 1. Prints prompt to allow user to select input method. 2. Reads in and
	 * returns user's selection.
	 */
	public static int inputOptions() {
		System.out.println(PROMPT);
		int responseNum = -1;
		in = new Scanner(System.in);
		// Ensure that response is 0 or 1
		while (in.hasNext()) {
			String rawResponse = in.nextLine();
			String response = rawResponse.trim();
			responseNum = Integer.parseInt(response);
			if (responseNum == 0 || responseNum == 1) {
				return responseNum;
			} else {
				System.out.println(INVALID_ENTRY_MSG);
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
		System.out.println(MANUAL_MSG);
		in = new Scanner(System.in);
		while (true) {
			String line = in.nextLine();
			try {
				lineData = strToArrList(line);
				cumData.addAll(lineData);
			} catch (NumberFormatException e) {
				return cumData;
			}
		}
	}

	/**
	 * User inputs file name as a string. File must be in the same directory as
	 * this Java file. Method will read the input and return an Arraylist of
	 * doubles of the input
	 */
	private static ArrayList<Double> autoInput() {
		ArrayList<Double> cumData = new ArrayList<Double>();
		String workingDir = System.getProperty("user.dir");

		System.out.println(AUTO_MSG);
		in = new Scanner(System.in);

		while (true) {
			String filename = in.nextLine();
			String path = workingDir + File.separator + filename;
			File file = new File(path);
			if (!file.canRead()) {
				System.out.println("Can\'t read file! Please try again.");
				continue;
			}
			// Good until this point
			try {
				reader = new BufferedReader(new FileReader(file));
				String line = null;
				while ((line = reader.readLine()) != null) {
					System.out.println("Line: " + line);
					cumData.addAll(strToArrList(line));
				}
				// if (line == null)
				return cumData;
				// cumData.addAll(strToArrList(line));
			} catch (NumberFormatException e) {
				System.out.println(FILE_FORMAT_ERR_MSG);
				continue;
			} catch (FileNotFoundException e) {
				System.out.println(FILE_NOT_FOUND_MSG);
				continue;
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}

		}
	}

	/**
	 * Takes in an action. This action will specify how the code will receive
	 * user input. After user input is received, statistics of the data will be
	 * printed to console.
	 * 
	 * @param action
	 */
	public static void performAction(int action) {
		ArrayList<Double> data;
		switch (action) {
		case MANUAL_INPUT:
			data = manualInput();
			break;
		case AUTO_INPUT:
			data = autoInput();
			break;
		default:
			throw new IllegalArgumentException();
		}
		analyze(data);
	}

	private static ArrayList<Double> strToArrList(String str)
			throws NumberFormatException {
		String[] split = str.split(" ");
		ArrayList<Double> arrList = new ArrayList<Double>();
		for (int i = 0; i < split.length; ++i) {
			arrList.add(Double.parseDouble(split[i]));
		}
		return arrList;
	}

	public static void analyze(ArrayList<Double> data) {
		double mean = mean(data);
		System.out.println(String.format("Mean: %.5f", mean));
		double var = variance(data);
		System.out.println(String.format("Variance: %.5f", var));
		double std = std(data);
		System.out.println(String.format("Standard Deviation: %.5f", std));

	}

	public static double mean(ArrayList<Double> data) {
		double acc = 0;
		for (double i : data) {
			acc += i;
		}
		return acc / data.size();
	}

	public static double variance(ArrayList<Double> data) {
		double cum = 0;
		double mean = mean(data);
		for (double i : data) {
			cum += Math.pow(i - mean, 2.0);
		}
		return cum / data.size();
	}

	public static double std(ArrayList<Double> data) {
		double variance = variance(data);
		return Math.sqrt(variance);
	}

}