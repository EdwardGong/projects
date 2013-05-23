package utils;

import graph.CoordNode;
import graph.Edge;
import graph.Node;
import hw1.Job;
import hw3.Item;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Scanner;

public class Parser {

	private Parser() {
	}

	public static String[] readData(String fileName, int sizeIndex) {
		File file = new File(fileName);
		try {
			Scanner in = new Scanner(file);
			String firstLine = in.nextLine();
			String[] splitFirstLine = firstLine.split(" ");
			int numLines = Integer.parseInt(splitFirstLine[sizeIndex]);
			String[] outPut = new String[numLines + 1];
			outPut[0] = firstLine;
			for (int i = 1; i <= numLines; i++) {
				String line = in.nextLine();
				outPut[i] = line;
			}
			return outPut;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Item[] processItems(String[] rawData) { // meta included
		Item[] items = new Item[rawData.length - 1];
		for (int i = 1; i < rawData.length; i++) {
			String[] splitLine = rawData[i].split(" ");
			items[i - 1] = new Item(Integer.parseInt(splitLine[0]),
					Integer.parseInt(splitLine[1]));
		}
		return items;
	}

	public static int[][] processSchedule(String[] rawData) {
		int[][] outPut = new int[rawData.length][2];
		for (int i = 0; i < rawData.length; i++) {
			String[] split = rawData[i].split(" ");
			for (int j = 0; j < split.length; j++) {
				outPut[i][j] = Integer.parseInt(split[j]);
			}
		}
		return outPut;
	}

	public static Job[] jobList(int[][] data) {
		Job[] jobList = new Job[data.length];
		for (int i = 0; i < data.length; i++) {
			int weight = data[i][0];
			int length = data[i][1];
			Job job = new Job(weight, length);
			jobList[i] = job;
		}
		return jobList;
	}

	public static int[] differences(Job[] jobList) {
		int[] differences = new int[jobList.length];
		for (int i = 0; i < jobList.length; i++) {
			int weight = jobList[i].getWeight();
			int length = jobList[i].getLength();
			differences[i] = weight - length;
		}
		return differences;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] reverse(T[] array, Class<T> c) {
		int length = array.length;
		T[] reversed = (T[]) Array.newInstance(c, length);
		for (int i = 0; i < length; i++) {
			reversed[i] = array[length - i - 1];
		}
		return reversed;
	}

	/**
	 * Read Data from rawData
	 * 
	 * @param rawData
	 */
	public static HashMap<Integer, Node> parseGraphImplicit(String[] rawData) {
		HashMap<Integer, Node> vertices = new HashMap<Integer, Node>();
		for (String line : rawData) {
			String[] split = line.split(" ");
			int v1id = Integer.parseInt(split[0]);
			int v2id = Integer.parseInt(split[1]);
			if (vertices.get(v1id) == null) {
				Node node = new Node(v1id);
				vertices.put(v1id, node);
			}
			if (vertices.get(v2id) == null) {
				Node node = new Node(v2id);
				vertices.put(v2id, node);
			}

			int length = Integer.parseInt(split[2]);
			Edge e = new Edge(vertices.get(v1id), vertices.get(v2id), length);
			vertices.get(v1id).edges.add(e);
			vertices.get(v2id).edges.add(e);
		}
		return vertices;
	}

	public static int[][] parseDigraphMatrix(String[] rawData) {
		String[] metaData = rawData[0].split(" ");
		int numNodes = Integer.parseInt(metaData[0]);
		Dimension dim = new Dimension(numNodes, numNodes);
		int[][] matrix = Utils.filled2DIntArray(dim, Integer.MAX_VALUE);
		for (int i = 1; i < rawData.length; i++) {
			String[] split = rawData[i].split(" ");
			int tail = Integer.parseInt(split[0]) - 1;
			int head = Integer.parseInt(split[1]) - 1;
			int length = Integer.parseInt(split[2]);
			matrix[tail][head] = length;
		}
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][i] = 0;
		}
		return matrix;
	}

	public static double[][] parseGraphMatrix(String[] rawData) {
		String metaData = rawData[0];
		int numNodes = Integer.parseInt(metaData);
		CoordNode[] nodes = new CoordNode[numNodes];
		for (int i = 1; i < rawData.length; i++) {
			int id = i - 1;
			String[] split = rawData[i].split(" ");
			double x = Double.parseDouble(split[0]);
			double y = Double.parseDouble(split[1]);
			nodes[id] = new CoordNode(id, x, y);
		}
		return fillDenseMatrix(nodes, numNodes);
	}

	private static double[][] fillDenseMatrix(CoordNode[] nodes, int numNodes) {
		double[][] matrix = new double[numNodes][numNodes];
		for (int i = 0; i < nodes.length; i++) {
			double curX = nodes[i].x;
			double curY = nodes[i].y;
			for (int j = 0; j < nodes.length; j++) {
				double tempX = nodes[j].x;
				double tempY = nodes[j].y;
				double dX = curX - tempX;
				double dY = curY - tempY;
				matrix[i][j] = dist(dX, dY);
			}
		}
		return matrix;
	}

	public static double dist(double a, double b) {
		double toSquare = Math.pow(a, 2.) + Math.pow(b, 2.);
		return Math.abs(Math.sqrt(toSquare));
	}
}
