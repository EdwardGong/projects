package hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import utils.Parser;

public class Problems1_2 {
	public static final String DIFFERENCE = "Difference";
	public static final String RATIO = "Ratio";
	public static final String FILENAME = "jobs.txt";

	public enum Algorithm {
		DIFFERENCE, RATIO;
	}

	public static void main(String[] args) {
		Problems1_2 p1 = new Problems1_2(FILENAME, RATIO);
		System.out.println(completionTime(p1.jobList));
	}

	public Job[] jobList;

	public Problems1_2(String fileName, String algorithm) {
		Comparator<Job> comparator;
		Algorithm alg;
		if (algorithm == DIFFERENCE) {
			alg = Algorithm.DIFFERENCE;
			comparator = new DiffComparator();
		} else {
			alg = Algorithm.RATIO;
			comparator = new RatioComparator();
		}

		jobList = setUp(fileName);
		jobList = algSortAndReverse(jobList, comparator);
		jobList = weightSortAndReverse(jobList, alg);
	}

	/**
	 * Used to resolve conflicts between same greedy score jobs
	 * 
	 * @param jobList
	 * @param alg
	 */
	private Job[] weightSortAndReverse(Job[] jobList, Algorithm alg) {

		ArrayList<Job> sameScore = new ArrayList<Job>();
		ArrayList<Job> jobs = new ArrayList<Job>();
		double currentScore = jobList[0].greedyScore(alg);
		Comparator<Job> weightComp = new WeightComparator();
		for (int i = 0; i < jobList.length; i++) {
			if (jobList[i].greedyScore(alg) == currentScore) {
				sameScore.add(jobList[i]);
			} else {
				jobs = weightSort(sameScore, jobs, weightComp);
				sameScore.clear();
				sameScore.add(jobList[i]);
				currentScore = jobList[i].greedyScore(alg);
			}
		}
		weightSort(sameScore, jobs, weightComp);
		return jobs.toArray(new Job[jobs.size()]);
	}

	private static ArrayList<Job> weightSort(ArrayList<Job> sameScore,
			ArrayList<Job> jobs, Comparator<Job> weightComp) {
		Job[] sgs = sameScore.toArray(new Job[sameScore.size()]);
		Arrays.sort(sgs, weightComp);
		sgs = (Job[]) Parser.reverse(sgs, Job.class);
		List<Job> weightSorted = new ArrayList<Job>(Arrays.asList(sgs));
		jobs.addAll(weightSorted);
		return jobs;
	}

	private static Job[] setUp(String fileName) {
		String[] rawData = Parser.readData(fileName, 0);
		int[][] data = Parser.processSchedule(rawData);
		Job[] jobList = Parser.jobList(data);
		return jobList;
	}

	private static Job[] algSortAndReverse(Job[] jobList,
			final Comparator<Job> c) {
		Arrays.sort(jobList, c);
		jobList = (Job[]) Parser.reverse(jobList, Job.class);
		return jobList;
	}

	public static long completionTime(Job[] jobList) {
		long time = 0;
		long totalLength = 0;
		for (Job job : jobList) {
			totalLength += job.getLength();
			time += job.getWeight() * totalLength;
		}
		return time;
	}

	class DiffComparator implements Comparator<Job> {

		@Override
		public int compare(Job job1, Job job2) {
			int job1Diff = job1.getDiff();
			int job2Diff = job1.getDiff();
			if (job1Diff > job2Diff)
				return 1;
			else if (job1Diff < job2Diff)
				return -1;
			else if (job1Diff == job2Diff)
				return 0;
			else {
				throw new InternalError();
			}
		};
	}

	class RatioComparator implements Comparator<Job> {

		@Override
		public int compare(Job job1, Job job2) {
			double job1Ratio = job1.getRatio();
			double job2Ratio = job2.getRatio();
			if (job1Ratio > job2Ratio)
				return 1;
			else if (job1Ratio < job2Ratio)
				return -1;
			else if (job1Ratio == job2Ratio)
				return 0;
			else {
				throw new InternalError();
			}
		};
	}

	class WeightComparator implements Comparator<Job> {

		@Override
		public int compare(Job job1, Job job2) {
			int job1Weight = job1.getWeight();
			int job2Weight = job2.getWeight();
			if (job1Weight > job2Weight)
				return 1;
			else if (job1Weight < job2Weight)
				return -1;
			else if (job1Weight == job2Weight)
				return 0;
			else {
				throw new InternalError();
			}
		};
	}
}
