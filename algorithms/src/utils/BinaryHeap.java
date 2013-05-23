package utils;

//BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// boolean isFull( )      --> Return true if full; else false
// void makeEmpty( )      --> Remove all items
// ************************ERRORS**************************
// Throws Overflow if capacity exceeded

public class BinaryHeap {
	/**
	 * Construct the binary heap.
	 */
	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Construct the binary heap.
	 * 
	 * @param capacity
	 *            the capacity of the binary heap.
	 */
	public BinaryHeap(int capacity) {
		currentSize = 0;
		array = new Comparable[capacity + 1];
	}

	/**
	 * Insert into the priority queue, maintaining heap order. Duplicates are
	 * allowed.
	 * 
	 * @param x
	 *            the item to insert.
	 * @exception Overflow
	 *                if container is full.
	 */
	public void insert(Comparable x) throws StackOverflowError {
		if (isFull())
			throw new StackOverflowError();

		// Percolate up
		int hole = ++currentSize;
		for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2)
			array[hole] = array[hole / 2];
		array[hole] = x;
	}

	/**
	 * Find the smallest item in the priority queue.
	 * 
	 * @return the smallest item, or null, if empty.
	 */
	public Comparable findMin() {
		if (isEmpty())
			return null;
		return array[1];
	}

	/**
	 * Remove the smallest item from the priority queue.
	 * 
	 * @return the smallest item, or null, if empty.
	 */
	public Comparable extractMin() {
		if (isEmpty())
			return null;

		Comparable minItem = findMin();
		array[1] = array[currentSize--];
		percolateDown(1);

		return minItem;
	}

	/**
	 * Establish heap order property from an arbitrary arrangement of items.
	 * Runs in linear time.
	 */
	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--)
			percolateDown(i);
	}

	/**
	 * Test if the priority queue is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return currentSize == 0;
	}

	/**
	 * Test if the priority queue is logically full.
	 * 
	 * @return true if full, false otherwise.
	 */
	public boolean isFull() {
		return currentSize == array.length - 1;
	}

	/**
	 * Make the priority queue logically empty.
	 */
	public void makeEmpty() {
		currentSize = 0;
	}

	private static final int DEFAULT_CAPACITY = 100;

	private int currentSize; // Number of elements in heap
	private Comparable[] array; // The heap array

	/**
	 * Internal method to percolate down in the heap.
	 * 
	 * @param hole
	 *            the index at which the percolate begins.
	 */
	private void percolateDown(int hole) {
		/* 1 */int child;
		/* 2 */Comparable tmp = array[hole];

		/* 3 */for (; hole * 2 <= currentSize; hole = child) {
			/* 4 */child = hole * 2;
			/* 5 */if (child != currentSize &&
			/* 6 */array[child + 1].compareTo(array[child]) < 0)
				/* 7 */child++;
			/* 8 */if (array[child].compareTo(tmp) < 0)
				/* 9 */array[hole] = array[child];
			else
				/* 10 */break;
		}
		/* 11 */array[hole] = tmp;
	}
}