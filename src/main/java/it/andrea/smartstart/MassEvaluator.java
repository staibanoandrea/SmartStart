package it.andrea.smartstart;

import java.text.DecimalFormat;

public class MassEvaluator {
	private Integer craneSize;
	private int totalEvaluations = 0;
	private int totSatisfiedRequests = 0;
	private int totalTravels = 0;
	private double totalIdleTime = 0.0;
	private double totalDistance = 0.0;
	private double maxTravelTime = 0.0; // max of all the subsets so far;
	private int maxTravelAttempt;
	private Double totWaitingTime = 0.0; // total time waited from every single request;
	private double maxWaitingTime = 0.0; // max time waited from every single request;
	private int maxWaitingIndex;
	private int maxWaitingAttempt;
	private double craneUsage = 0.0;
	private int[] satisfiedRequestsBySize; // contains the count of the subset served so far, divided by size;

	private final DecimalFormat doubleDecimal = new DecimalFormat("0.00");

	public MassEvaluator(int craneSize) {
		this.craneSize = craneSize;
		this.satisfiedRequestsBySize = new int[craneSize];
	}

	public void collectData(Evaluator eval) {
		totalEvaluations++;
		totSatisfiedRequests += eval.getSatisfiedRequests();
		totalTravels += eval.getCraneWorkSheetSize();
		totalIdleTime += eval.getIdleTime();
		totalDistance += eval.getTotalDistance();
		if (eval.getMaxTravelTime() > maxTravelTime) {
			maxTravelTime = eval.getMaxTravelTime();
			maxTravelAttempt = totalEvaluations;
		}
		totWaitingTime += eval.getTotWaitingTime();
		if (eval.getMaxWaitingTime() > maxWaitingTime) {
			maxWaitingTime = eval.getMaxWaitingTime();
			maxWaitingIndex = eval.getMaxWaitingIndex();
			maxWaitingAttempt = totalEvaluations;
		}
		craneUsage += eval.getCraneUsage();
		for (int i = 0; i < satisfiedRequestsBySize.length; i++) {
			satisfiedRequestsBySize[i] += eval.getSatisfiedRequestsBySize()[i];
		}
	}

	public void recapData() {
		double avgTravel = (double) totalTravels / totalEvaluations;
		double avgIdle = (double) totalIdleTime / totalEvaluations;
		double avgDist = (double) totalDistance / totalEvaluations;
		double avgWaitPerReq = (double) totWaitingTime / totSatisfiedRequests;
		double avgCraneUsage = (double) craneUsage / totalEvaluations;

		System.out.println();
		System.out.println("Total tests carried out: " + totalEvaluations);
		System.out.println("Total travels: " + totalTravels);
		System.out.println("Average number of travels per test: " + doubleDecimal.format(avgTravel));
		System.out.println("Average time spent idle with pending requests: " + doubleDecimal.format(avgIdle));
		System.out.println("Average traveled distance per test: " + doubleDecimal.format(avgDist));
		System.out.println("Maximum travel time during a test: " + doubleDecimal.format(maxTravelTime)
				+ " at test number " + maxTravelAttempt);
		System.out.println("Average time waited by requests: " + doubleDecimal.format(avgWaitPerReq));
		System.out.println("Maximum time waited by a request: " + doubleDecimal.format(maxWaitingTime) + " (request "
				+ maxWaitingIndex + ", test " + maxWaitingAttempt + ")");
		System.out.println(
				"Average crane usage (at start of every travel): " + doubleDecimal.format(avgCraneUsage) + "%");
		System.out.println("Crane usage distribution: ");
		for (int i = 1; i <= craneSize; i++) {
			System.out.print("[" + i + "]\t");
		}
		System.out.println();
		for (int i = 0; i < craneSize; i++) {
			System.out.print("[" + satisfiedRequestsBySize[i] + "]\t");
		}
	}
}
