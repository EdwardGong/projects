package graph;

import utils.Parser;

public class Digraph {

	public int[][] matrix;

	public Digraph(String fileName) {
		String[] rawData = Parser.readData(fileName, 1);
		matrix = Parser.parseDigraphMatrix(rawData);
	}

	public int numNodes() {
		return matrix.length;
	}

}
