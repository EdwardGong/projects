package utils;

import java.awt.Dimension;

public class Utils {

	public static int[][] filled2DIntArray(Dimension dim, int fillWith) {
		int[][] arr = new int[dim.height][dim.width];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = fillWith;
			}
		}
		return arr;
	}

	public static int minInArray(int[][] arr) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] < min) {
					min = arr[i][j];
				}
			}
		}
		return min;
	}

	public static void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static int factorial(int n) {
		int cumulator = n;
		for (int i = n - 1; i > 0; i--) {
			cumulator *= i;
		}
		return cumulator;
	}

	public static int choose(int n, int r) {
		int numerator = factorial(n);
		int denominator = factorial(r) * factorial(n - r);
		return numerator / denominator;
	}

}
