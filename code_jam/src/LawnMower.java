import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class LawnMower {

	public static final String fileName = "B-large.in";
	public static final String col = "Column";
	public static final String row = "Row";

	public static void main(String[] str) throws IOException {
		File file = new File(fileName);
		PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
		try {
			Scanner in = new Scanner(file);
			int numGardens = Integer.parseInt(in.nextLine());
			// Go through each of these gardens
			for (int i = 0; i < numGardens; i++) {
				String[] dims = in.nextLine().split(" ");
				int numRows = Integer.parseInt(dims[0]);
				int numCols = Integer.parseInt(dims[1]);
				int[][] garden = new int[numRows][numCols];
				// Go through each row
				for (int j = 0; j < numRows; j++) {
					String[] line = in.nextLine().split(" ");
					// Fill row j of array with this line
					for (int k = 0; k < numCols; k++) {
						garden[j][k] = Integer.parseInt(line[k]);
					}
				}
				// Operate on your garden. Write implementation here!
				String statement = checkGarden(garden, i + 1);
				out.println(statement);
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String checkGarden(int[][] garden, int caseNum) {
		String yesNo;
		int[][] processed = roundOneTrim(garden);
		int[][] trimmed = roundTwoTrim(processed, garden);
		if (Arrays.deepEquals(trimmed, garden))
			yesNo = "YES";
		else
			yesNo = "NO";
		return String.format("Case #%s: %s", caseNum, yesNo);
	}

	public static int[][] roundTwoTrim(int[][] processed, int[][] garden) {
		int r = garden.length;
		int c = garden[0].length;
		for (int i = 0; i < c; i++) {
			int maxInCol = maxInDimension(garden, i, col);
			for (int j = 0; j < r; j++) {
				if (processed[j][i] > maxInCol) {
					processed[j][i] = maxInCol;
				}
			}
		}
		return processed;
	}

	public static int[][] roundOneTrim(int[][] garden) {
		int r = garden.length;
		int c = garden[0].length;
		int[][] newMatrix = new int[r][c];
		for (int i = 0; i < r; i++) {
			int fillWith = maxInDimension(garden, i, row);
			// fill
			for (int j = 0; j < c; j++) {
				newMatrix[i][j] = fillWith;
			}
		}
		return newMatrix;
	}

	public static int maxInDimension(int[][] matrix, int index, String colOrRow) {
		int max = 0;
		if (colOrRow.equals(row)) {
			int c = matrix[0].length;
			for (int i = 0; i < c; i++) {
				if (matrix[index][i] > max)
					max = matrix[index][i];
			}
		}
		if (colOrRow.equals(col)) {
			int r = matrix.length;
			for (int i = 0; i < r; i++) {
				if (matrix[i][index] > max)
					max = matrix[i][index];
			}
		}
		return max;
	}

	public static void printArray(int[][] arr) {
		int rows = arr.length;
		int cols = arr[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(" " + arr[i][j]);
			}
			System.out.println();
		}
	}

}
