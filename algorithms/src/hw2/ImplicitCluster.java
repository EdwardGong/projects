package hw2;

import utils.Parser;
import utils.UnionFind;

public class ImplicitCluster {
	private static final String fileName = "clustering2.txt";
	private static final int minDist = 3;
	private static final int sizeIndex = 0;
	private static final int rows = 20000;
	private static final int cols = 24;

	public static void main(String[] args) {
		ImplicitCluster ic = new ImplicitCluster();
	}

	public ImplicitCluster() {
		String[] rawData = Parser.readData(fileName, sizeIndex);
		String[] parsedData = parseData(rawData); // length 20k
		int i = findK(parsedData, minDist);
		System.out.println(i);
	}

	/**
	 * Takes in rawData and outputs an array of strings
	 * 
	 * @param rawData
	 * @return
	 */
	private static String[] parseData(String[] rawData) {
		String[] data = new String[rows];
		for (int i = 0; i < rows; i++) {
			String[] split = rawData[i].split(" ");
			data[i] = stringify(split);
		}
		return data;
	}

	/**
	 * Given m, minimum distance between clusters, computes the Hamming
	 * distances, d, between all nodes. Count how many times one must cluster in
	 * order to eliminate all d < m in implicit graph. Subtracts the number of
	 * times one must cluster from the total number of nodes.
	 * 
	 * Checks if each hamming distance < m creates a cycle.
	 * 
	 * @param <UF>
	 * 
	 * @return
	 */
	private static int findK(String[] data, int m) {
		int numNodes = rows;
		UnionFind unionFind = new UnionFind(numNodes);
		for (int i = 0; i < numNodes; i++) {
			for (int j = i + 1; j < numNodes; j++) {
				int hammingDist = hammingDistance(data[i], data[j]);
				if (hammingDist < m)
					unionFind.union(i, j);
			}
		}
		return numNodes - unionFind.mergeCount;
	}

	/**
	 * Outputs a String for efficient manipulation
	 * 
	 * @param split
	 * @return
	 */
	private static String stringify(String[] split) {
		StringBuilder sb = new StringBuilder(cols);
		for (int i = 0; i < split.length; i++) {
			sb.append(split[i]);
		}
		return sb.toString();
	}

	/**
	 * Takes in two Strings and finds the number of differing bits
	 * 
	 * @return
	 */
	private static int hammingDistance(String a, String b) {
		int dist = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i))
				dist++;
		}
		return dist;
	}
}
