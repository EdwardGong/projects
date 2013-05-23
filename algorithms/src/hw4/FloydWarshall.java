package hw4;

import graph.Digraph;
import utils.Compare;
import utils.Utils;

public class FloydWarshall {

	private static final String fileName = "g3.txt";
	private static final int max = Integer.MAX_VALUE;

	public static void main(String[] args) {
		FloydWarshall fw = new FloydWarshall();
	}

	public FloydWarshall() {
		Digraph graph = new Digraph(fileName);
		int[][] opt = floydWarshall(graph);
		// Utils.printArr(opt);
		if (negCycle(opt) == false) {
			System.out.println("Shortest shortest path: "
					+ Utils.minInArray(opt));
		}
	}

	public static int[][] floydWarshall(Digraph graph) {
		int numNodes = graph.numNodes();
		int[][] prevOpt = initialize(graph);
		// Utils.printArr(prevOpt);
		int[][] opt = new int[numNodes][numNodes];
		for (int k = 0; k < numNodes; k++) { // serves as a counter
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					int case1 = prevOpt[i][j];
					int path1 = prevOpt[i][k];
					int path2 = prevOpt[k][j];
					int case2;
					if (path1 != max && path2 != max)
						case2 = path1 + path2;
					else
						case2 = max;
					opt[i][j] = Compare.min(case1, case2);
				}
			}
			prevOpt = opt;
			opt = new int[numNodes][numNodes];
		}
		return prevOpt;
	}

	private static int[][] initialize(Digraph graph) {
		int numNodes = graph.numNodes();
		int[][] matrix = graph.matrix.clone();
		int[][] prevOpt = new int[numNodes][numNodes];

		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				prevOpt[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				if (i == j) {
					prevOpt[i][j] = 0;
				}
				if (matrix[i][j] != Integer.MAX_VALUE) {
					prevOpt[i][j] = matrix[i][j];
				}
			}
		}
		return prevOpt;
	}

	public static boolean negCycle(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][i] != 0) {
				System.out.println("Problem cell: " + i);
				System.out.println("Negative Cycle Alert!");
				System.out.println(matrix[i][i]);
				return true;
			}
		}
		return false;
	}
}
