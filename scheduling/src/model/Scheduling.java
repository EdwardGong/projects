package model;

import java.util.ArrayList;
import java.util.List;

public class Scheduling {

	List tasks;

	public Scheduling() {
		tasks = new ArrayList<Task>();
		
	}

	/**
	 * Returns the name of the task(s) that the algorithm deems most important
	 * 
	 * @param freeTime
	 */
	public String getTask(int freeTime) {
		return "";
	}

	/**
	 * Adds a task to the pool of to-do tasks
	 * 
	 * @param name
	 * @param length
	 * @param weight
	 */

	public void addTask(String name, int length, int weight) {
		return;
	}

	public void completeTask(Task t) {

	}

	/**
	 * Refreshes list of tasks
	 */
	private void refresh() {
		return;
	}

}
