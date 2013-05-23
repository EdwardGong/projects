package utils;

import graph.Node;

public class UnionFind2<T extends Node> {
	public int numSets;

	public UnionFind2(int numVertices) {
		numSets = numVertices;
	}

	@SuppressWarnings("unchecked")
	public T find(T n1) {
		if (n1.equals(n1.leader)) {
			return n1;
		} else {
			return n1 = find((T) n1.leader);
		}
	}

	public boolean union(T n1, T n2) {
		T rootn1 = find(n1);
		T rootn2 = find(n2);
		if (rootn1.equals(rootn2))
			return false;
		else {
			if (rootn1.rank > rootn2.rank)
				rootn2.leader = rootn1;
			else if (rootn2.rank > rootn1.rank)
				rootn1.leader = rootn2;
			else {
				rootn1.leader = rootn2;
				rootn2.rank++;
			}
			numSets--;
			return true;
		}
	}
}
