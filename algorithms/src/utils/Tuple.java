package utils;

public class Tuple {
	public Tuple leader;
	public int first;
	public int second;
	
	public Tuple(int f, int s){
		first = f;
		second = s;
		leader = this;
	}
}
