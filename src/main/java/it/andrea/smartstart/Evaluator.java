package it.andrea.smartstart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Evaluator {
	private Double time = 0.0;
	private int satisfiedRequests = 0; // requests satisfied so far;
	private int[] satisfiedRequestsBySize; // contains the count of the subset served so far, divided by size;
	private Double idleTime = 0.0;
	private Double totalDistance = 0.0;
	private Double maxTravelTime = 0.0; // max of all the subsets so far;
	private Double totWaitingTime = 0.0; // total time waited from every single request;
	private Double maxWaitingTime = 0.0; // max time waited from every single request;

	public Evaluator(int craneSize) {
		this.satisfiedRequestsBySize = new int[craneSize];
	}

	public void run(List<Subset> craneWorkSheet) {
		for (Subset subset : craneWorkSheet) {
			Double travelDistance = TourCalculator.getTotalDistance(subset.getRequestList());
			if (travelDistance > maxTravelTime) {
				maxTravelTime = travelDistance;
			}
			idleTime += subset.getTimeWaited();
			Double startTime = time + subset.getTimeWaited();
			totalDistance += travelDistance;
			time += subset.getTimeWaited() + travelDistance;
			for (Request request : subset.getRequestList()) {
				satisfiedRequests++;
				Double timeWaited = time - request.getRequestTime();
				totWaitingTime += timeWaited;
				if (timeWaited > maxWaitingTime) {
					maxWaitingTime = timeWaited;
				}
			}
			System.out.println("Subset " + subset.getIndex() + ": " + subset.print() + " started at "
					+ new BigDecimal(startTime).setScale(5, RoundingMode.HALF_UP) + " and traveled "
					+ new BigDecimal(travelDistance).setScale(5, RoundingMode.HALF_UP) + " space units;");
		}
		System.out.println();
		System.out.println("Number of travels: " + craneWorkSheet.size());
		System.out.println("Time spent idle: " + new BigDecimal(idleTime).setScale(5, RoundingMode.HALF_UP));
		System.out.println("Time spent travelling: " + new BigDecimal(totalDistance).setScale(5, RoundingMode.HALF_UP));
		System.out.println("Average travel time: "
				+ new BigDecimal(totalDistance / craneWorkSheet.size()).setScale(5, RoundingMode.HALF_UP));
		System.out.println("Maximum travel time: "
				+ new BigDecimal(maxTravelTime).setScale(5, RoundingMode.HALF_UP));
		System.out.println();
		System.out.println("Average time waited by all requests: "
				+ new BigDecimal(totWaitingTime / satisfiedRequests).setScale(5, RoundingMode.HALF_UP));
		System.out.println("Maximum time waited by a request: "
				+ new BigDecimal(maxWaitingTime).setScale(5, RoundingMode.HALF_UP));
	}
}
