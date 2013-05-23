package hw2;

import graph.Edge;
import graph.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import utils.Parser;
import utils.UnionFind2;

public class ExplicitCluster {

	public static void main(String[] args) {
		ExplicitCluster e = new ExplicitCluster();
	}

	private static final String fileName = "clustering1.txt";
	private static final int k = 4;
	private HashMap<Integer, Node> graph;
	private UnionFind2<Node> unionFind;

	public ExplicitCluster() {
		String[] rawData = readData(fileName, 0);
		graph = Parser.parseGraphImplicit(rawData);
		Collection<Node> vertices = graph.values();
		unionFind = new UnionFind2<Node>(vertices.size());
		cluster(graph, k);
	}

	private void cluster(HashMap<Integer, Node> graph, int k) {
		ArrayList<Edge> edges = edges(graph);
		edges = sort(edges);
		Iterator<Edge> i = edges.iterator();
		int lastEdgeLength = Integer.MAX_VALUE;
		while (unionFind.numSets != k - 1 && i.hasNext()) {
			Edge e = i.next();
			Node n1 = e.tail;
			Node n2 = e.head;
			unionFind.union(n1, n2);
			lastEdgeLength = e.length;
		}
		System.out.println(lastEdgeLength);
	}

	private static ArrayList<Edge> edges(HashMap<Integer, Node> graph) {
		System.out.println("Number of Nodes: " + graph.size());
		int numNodes = graph.size();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		// int edgeCount = 0;
		// int addCount = 0;
		for (int i = 1; i <= numNodes; i++) {
			Node node = graph.get(i);
			// System.out.println("Node id: " + i);
			// System.out.println("Number of Edges in node: " +
			// node.edges.size());
			for (Edge e : node.edges) {
				edges.add(e);
				// System.out.println("added. addCount: " + ++addCount);
			}
			// System.out.println("Next edge. Count: " + ++edgeCount);
		}
		return edges;
	}

	private static ArrayList<Edge> sort(ArrayList<Edge> edges) {
		System.out.println("Number of Edges: " + edges.size());
		Edge[] edgeArray = edges.toArray(new Edge[edges.size()]);
		Arrays.sort(edgeArray, new Comparator<Edge>() {
			@Override
			public int compare(Edge e1, Edge e2) {
				if (e1.length > e2.length)
					return 1;
				else if (e2.length > e1.length)
					return -1;
				else {
					return 0;
				}
			}
		});
		ArrayList<Edge> edgeList = new ArrayList<Edge>(Arrays.asList(edgeArray));
		return edgeList;
	}

	public static String[] readData(String fileName, int sizeIndex) {
		File file = new File(fileName);
		try {
			Scanner in = new Scanner(file);
			String[] firstLine = in.nextLine().split(" ");
			int numVertices = Integer.parseInt(firstLine[sizeIndex]);
			int numLines = stronglyConnectedEdges(numVertices);
			String[] outPut = new String[numLines];
			for (int i = 0; i < numLines; i++) {
				String line = in.nextLine();
				outPut[i] = line;
			}
			return outPut;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int stronglyConnectedEdges(int vertices) {
		int sum = 0;
		for (int i = 0; i < vertices; i++) {
			sum += i;
		}
		return sum;
	}
}
