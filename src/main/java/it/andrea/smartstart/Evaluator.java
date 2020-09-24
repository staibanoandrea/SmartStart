package it.andrea.smartstart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Evaluator {
	private Integer craneSize;
	private Double time = 0.0;
	private int satisfiedRequests = 0; // requests satisfied so far;
	private int[] satisfiedRequestsBySize; // contains the count of the subset served so far, divided by size;
	private Double idleTime = 0.0;
	private Double totalDistance = 0.0;
	private Double maxTravelTime = 0.0; // max of all the subsets so far;
	private Double totWaitingTime = 0.0; // total time waited from every single request;
	private Double maxWaitingTime = 0.0; // max time waited from every single request;
	private Integer maxWaitingIndex;

	public Evaluator(int craneSize) {
		this.craneSize = craneSize;
		this.satisfiedRequestsBySize = new int[craneSize];
	}

	public void run(List<Subset> craneWorkSheet) {
		for (Subset subset : craneWorkSheet) {

			// get data about the whole subset:
			Double travelDistance = TourCalculator.getTotalDistance(subset.getRequestList());
			if (travelDistance > maxTravelTime) {
				maxTravelTime = travelDistance;
			}
			idleTime += subset.getTimeWaited();
			totalDistance += travelDistance;
			time += subset.getTimeWaited() + travelDistance;
			satisfiedRequestsBySize[subset.getRequestList().size() - 1]++;

			// get data about the single requests:
			for (Request request : subset.getRequestList()) {
				satisfiedRequests++;
				Double timeWaited = time - request.getRequestTime();
				totWaitingTime += timeWaited;
				if (timeWaited > maxWaitingTime) {
					maxWaitingTime = timeWaited;
					maxWaitingIndex = request.getIndex();
				}
			}

			// print subset-by-subset data:
			System.out.println("Subset " + subset.getIndex() + ": " + subset.print() + " started after "
					+ new BigDecimal(subset.getTimeWaited()).setScale(5, RoundingMode.HALF_UP) + " and traveled "
					+ new BigDecimal(travelDistance).setScale(5, RoundingMode.HALF_UP) + " space units, coming back at "
					+ new BigDecimal(time).setScale(5, RoundingMode.HALF_UP) + ";");
		}
		// print recap:
		System.out.println();
		System.out.println("Number of travels: " + craneWorkSheet.size());
		System.out.println("Time spent idle: " + new BigDecimal(idleTime).setScale(5, RoundingMode.HALF_UP));
		System.out.println("Time spent travelling: " + new BigDecimal(totalDistance).setScale(5, RoundingMode.HALF_UP));
		System.out.println("Average travel time: "
				+ new BigDecimal(totalDistance / craneWorkSheet.size()).setScale(5, RoundingMode.HALF_UP));
		System.out.println("Maximum travel time: " + new BigDecimal(maxTravelTime).setScale(5, RoundingMode.HALF_UP));
		System.out.println();
		System.out.println("Average time waited by all requests: "
				+ new BigDecimal(totWaitingTime / satisfiedRequests).setScale(5, RoundingMode.HALF_UP));
		System.out.println("Maximum time waited by a request: "
				+ new BigDecimal(maxWaitingTime).setScale(5, RoundingMode.HALF_UP) + " by request " + maxWaitingIndex);
		System.out.println();

		Double craneUsage = (double) satisfiedRequests / craneWorkSheet.size() / craneSize * 100;
		System.out.println("Crane usage: " + new BigDecimal(craneUsage).setScale(2, RoundingMode.HALF_UP) + "%");
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
