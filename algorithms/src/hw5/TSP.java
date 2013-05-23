package hw5;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import utils.Parser;

public class TSP {

	private static String fileName = "tsp.txt";

	// public static void main(String[] args) {
	// BitSet bs = new BitSet();
	// System.out.println(bs.size());
	// }

	public static void main(String[] args) {
		String[] rawData = Parser.readData(fileName, 0);
		double[][] matrix = Parser.parseGraphMatrix(rawData);
		int numNodes = matrix.length;
		Set<BitSet> set = null;
		set = genSet(set, numNodes);
		System.out.println(set);
		HashMap<SetNum, Double> oldMap = initialize();
		HashMap<SetNum, Double> newMap = new HashMap<SetNum, Double>();
		for (int card = 2; card < numNodes; card++) {
			Set<BitSet> S = genSet(set, numNodes);
			for (Iterator<BitSet> iterator = S.iterator(); iterator
					.hasNext();) {
				BitSet b = iterator.next();
				System.out.println("BitSet: " + b);
			}
			System.out.println("Card: " + card);
			for (BitSet bs : S) {
				int j = 1;
				while (bs.nextSetBit(j) != -1) {
					j = bs.nextSetBit(j);
					int k = 0;
					double min = Integer.MAX_VALUE;
					SetNum sn = new SetNum(new BitSet(), 0);
					while (bs.nextSetBit(j) != -1) {
						k = bs.nextSetBit(k);
						if (k != j) {
							bs.clear(j);
							sn = new SetNum(bs, k);
							bs.set(j);
							if (matrix[j][k] != Integer.MAX_VALUE
									&& oldMap.get(sn) != Integer.MAX_VALUE) {
								double temp = oldMap.get(sn) + matrix[j][k];
								if (temp < min) {
									min = temp;
								}
							}
						}
					}
					newMap.put(sn, min);
				}
			}
			oldMap = newMap;
			newMap = new HashMap<SetNum, Double>();
		}
		BitSet bitSet = new BitSet();
		bitSet.set(1, numNodes);
		double m = Double.MAX_VALUE;
		for (int i = 2; i < numNodes; i++) {
			SetNum ns = new SetNum(bitSet, i);
			double lastHop = matrix[1][i];
			double pl = oldMap.get(ns);
			double pathLength = lastHop + pl;
			if (pathLength < m) {
				m = pathLength;
			}
		}
		System.out.println(m);
	}

	public static HashMap<SetNum, Double> initialize() {
		HashMap<SetNum, Double> map = new HashMap<SetNum, Double>();
		BitSet bs = new BitSet();
		bs.set(1);
		SetNum sN = new SetNum(bs, 1);
		map.put(sN, (double) 0);
		return map;
	}

	public static Set<BitSet> genSet(Set<BitSet> oldSet, int numNodes) {
		Set<BitSet> setToReturn = new HashSet<BitSet>();
		if (oldSet == null) {
			BitSet bs = new BitSet();
			bs.set(1);
			setToReturn.add(bs);
			return setToReturn;
		}
		for (BitSet bs : oldSet) {
			System.out.println("Size: " + oldSet.size());
			int setIndex = 1;
			int clearIndex = 0;
			setIndex = bs.nextClearBit(setIndex);
			while (setIndex <= numNodes) {
				System.out.println("Index: " + setIndex);
				bs.set(setIndex);
				clearIndex = setIndex;
				setToReturn.add(bs);
				setIndex = bs.nextClearBit(setIndex);
				bs.clear(clearIndex);
			}
			System.out.println("pass");
		}
		setToReturn.addAll(oldSet);
		return setToReturn;
	}
}
