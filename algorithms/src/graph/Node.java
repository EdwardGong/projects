package graph;


import java.util.ArrayList;
import java.util.List;

import utils.BinaryHeap;

public class Node {

	public int id;
	public ArrayList<Edge> edges;
	public Node leader;
	public int rank;

	public Node(int id) {
		this.id = id;
		edges = new ArrayList<Edge>();
		leader = this;
		rank = 0;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public int getId() {
		return id;
	}

	public BinaryHeap addAllEdges(BinaryHeap frontier) {
		for (Edge e : edges) {
			frontier.insert(e);
		}
		return frontier;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Node other = (Node) obj;
		return this.id == other.id;
	}
}
