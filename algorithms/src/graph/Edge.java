package graph;

public class Edge implements Comparable<Edge> {

	public Node tail;
	public Node head;
	public int length;

	public Edge(Node tail, Node head, int length) {
		this.tail = tail;
		this.head = head;
		this.length = length;
	}

	@Override
	public int compareTo(Edge e) {
		if (length > e.length)
			return 1;
		else if (length < e.length)
			return -1;
		else if (length == e.length)
			return 0;
		else {
			throw new InternalError();
		}
	}

	public int hashCode() {
		return (int)(tail.hashCode() * Math.pow(10, 5)) * head.hashCode() + length;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Edge other = (Edge) obj;
		return hashCode() == other.hashCode();
	}

}
