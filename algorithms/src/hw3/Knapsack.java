package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import utils.Parser;

public class Knapsack {
	public static final String FILE_NAME = "knapsack2.txt";

	public static void main(String[] args) {
		new Knapsack();
	}

	public Knapsack() {
		String[] rawData = readData(FILE_NAME, 1);
		String[] metaData = rawData[0].split(" ");
		int capacity = Integer.parseInt(metaData[0]);
		Item[] items = Parser.processItems(rawData);
		Comparator<Item> itemComparator = new WeightComparator();
		Arrays.sort(items, itemComparator);
		// System.out.println(checkRepeat(items));
		// for (Item i : items) {
		// System.out.println(i.weight);
		// }
		System.out.println(optimalKnapSack(items, capacity));
	}

	public int optimalKnapSack(Item[] items, int capacity) {
		int start = items[0].weight;
		int[] prevOptVals = new int[capacity + 1];
		int[] optVals = new int[capacity + 1];
		Arrays.fill(prevOptVals, 0);
		for (int i = 0; i < items.length; i++) {
			for (int x = start; x <= capacity; x++) {
				int itemVal = items[i].value;
				int itemWeight = items[i].weight;
				System.out.println("i: " + i);
				System.out.println("x: " + x);
				int withoutItem = prevOptVals[x];
				int withItem;
				if (x >= itemWeight) {
					withItem = itemVal + prevOptVals[x - itemWeight];
					if (withItem > withoutItem) {
						optVals[x] = withItem;
					} else
						optVals[x] = withoutItem;
				} else
					optVals[x] = withoutItem;
			}
			prevOptVals = optVals;
			optVals = new int[capacity + 1];
		}
		return prevOptVals[capacity];
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
			for (int i = 1; i < numLines + 1; i++) {
				String line = in.nextLine();
				outPut[i] = line;
			}
			return outPut;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	class WeightComparator implements Comparator<Item> {

		@Override
		public int compare(Item i1, Item i2) {
			int item1Weight = i1.weight;
			int item2Weight = i2.weight;
			if (item1Weight > item2Weight)
				return 1;
			else if (item1Weight < item2Weight)
				return -1;
			else if (item1Weight == item2Weight)
				return 0;
			else {
				throw new InternalError();
			}
		}
	}
}
