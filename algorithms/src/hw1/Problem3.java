package hw1;

import graph.Edge;
import graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import utils.BinaryHeap;
import utils.Parser;

/**
 * This class implements Prim's Algorithm using a heap.
 * 
 * @author Edward Gong
 */

public class Problem3 {
	public static final String FILENAME = "edges.txt";

	private HashMap<Integer, Node> vertices;
	private HashSet<Node> visited;
	private ArrayList<Edge> mst;
	private int heapSize;

	public static void main(String[] args) {
		Problem3 p = new Problem3(FILENAME);
	}

	public Problem3(String fileName) {
		mst = new ArrayList<Edge>();
		visited = new HashSet<Node>();
		String[] rawData = Parser.readData(fileName, 1);
		heapSize = rawData.length;
		vertices = Parser.parseGraphImplicit(rawData);
		Node source = vertices.get(1);
		visited.add(source);
		vertices.remove(source.id);
		int cost = buildMST(source);
		System.out.println(cost);
	}

	private int buildMST(Node source) {
		BinaryHeap frontier = new BinaryHeap(heapSize);
		System.out.println("Edges: " + source.getEdges().size());
		source.addAllEdges(frontier);
		int totalCost = 0;
		while (vertices.isEmpty() == false) {
			System.out.println(vertices.size());
			Edge e = (Edge) frontier.extractMin();
			Node vertex = visited.contains(e.tail) ? e.head : e.tail;
			if (visit(vertex)) {
				mst.add(e);
				totalCost += e.length;
			}
			// adds new edges
			for (Edge edge : vertex.getEdges()) { // edges in newly added vertex
				// add all edges that straddle cut
				Node v1 = edge.tail;
				Node v2 = edge.head;
				if (!(visited.contains(v1)) || !(visited.contains(v2))) {
					frontier.insert(edge);
				}
			}
		}
		System.out.println(mst.size());
		return totalCost;
	}

	private boolean visit(Node vertex) {
		boolean added = visited.add(vertex);
		vertices.remove(vertex.getId());
		return added;
	}

}
