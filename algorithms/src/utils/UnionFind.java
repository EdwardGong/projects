package utils;

import hw3.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class UnionFind {

	private int[] arr;
	private ArrayList<Integer> leaders;
	public int mergeCount;

	public UnionFind(int n) {
		arr = new int[n];
		Arrays.fill(arr, -1);
		leaders = new ArrayList<Integer>();
		mergeCount = 0;
	}

	public int find(int x) {
		if (arr[x] < 0) {
			return x;
		} else {
			arr[x] = find(arr[x]);
			return arr[x];
		}
	}

	public void union(int a, int b) {
		int leader1 = find(a);
		int leader2 = find(b);

		if (leader1 == leader2)
			return;

		if (arr[leader2] < arr[leader1]) {
			arr[leader2] += arr[leader1];
			arr[leader1] = leader2;
			leaders.add(leader1);
			leaders.remove(leader2);
			mergeCount++;
		} else {
			arr[leader1] += arr[leader2];
			arr[leader2] = leader1;
			leaders.add(leader2);
			leaders.remove(leader1);
			mergeCount++;
		}
	}
	
}
