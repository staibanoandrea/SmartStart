package it.andrea.smartstart;

import java.util.List;

public class Evaluator {
	private Double time = 0.0;
	private int satisfiedRequests = 0; // requests satisfied so far;
	private int[] satisfiedRequestsBySize; // contains the count of the subset served so far, divided by size;
	private Double totalDistance = 0.0;
	private Double totTravelTime = 0.0; // total of all the subsets so far;
	private Double avgTravelTime = 0.0; // avg of all the subsets so far;
	private Double maxTravelTime = 0.0; // max of all the subsets so far;
	private Double totWaitingTime = 0.0; // total time waited from every single request;
	private Double avgWaitingTime = 0.0; // avg time waited from every single request;
	private Double maxWaitingTime = 0.0; // max time waited from every single request;

	public Evaluator(int craneSize) {
		this.satisfiedRequestsBySize = new int[craneSize];
	}

	public void run(List<Subset> craneWorkSheet) {
		for (Subset subset : craneWorkSheet) {
			Double travelDistance = TourCalculator.getTotalDistance(subset.getRequestList());
			Double startTime = time + subset.getTimeWaited();
			totalDistance += travelDistance;
			for (Request request : subset.getRequestList()) {

			}
			System.out.println("Subset " + subset.getIndex() + ": " + subset.print() + " started at " + startTime + " and traveled " + travelDistance + " meters;");
			time += subset.getTimeWaited() + travelDistance;
		}
	}
}
