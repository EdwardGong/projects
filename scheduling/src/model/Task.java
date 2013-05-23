package model;

import time.Date;

public class Task {

	private int length;
	private int weight;
	private Date expiration;

	public Task(int duration, int importance, Date expiration) {
		this.length = duration;
		this.weight = importance;
		this.expiration = expiration;
	}

	public int getLength() {
		return length;
	}

	public int getImportance() {
		return weight;
	}

}
