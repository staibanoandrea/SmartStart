package it.andrea.smartstart;

import java.util.List;

/*
 * This class contains data suitable for printing both during and at the end of an execution.
 * */
public class Progress {
	private int subsetCounter = 0; // current subset;
	private int satisfiedRequests = 0; // requests satisfied so far;
	private int[] satisfiedRequestsBySize; // contains the count of the subset served so far, divided by size;
	private Double totTravelTime = 0.0; // total of all the subsets so far;
	private Double avgTravelTime = 0.0; // avg of all the subsets so far;
	private Double maxTravelTime = 0.0; // max of all the subsets so far;
	private Double totWaitingTime = 0.0; // total time waited from every single request;
	private Double avgWaitingTime = 0.0; // avg time waited from every single request;
	private Double maxWaitingTime = 0.0; // max time waited from every single request;

	public Progress(int craneSize) {
		this.satisfiedRequestsBySize = new int[craneSize];
	}

	public void update(List<Request> subset) {
		Double travelTime = TourCalculator.getTotalDistance(subset);
		Double waitingTime = 0.0;
		Double maxWaitingTime = 0.0;

		// for now, we assume the waiting time to be the time passed between the
		// request's creation and the time when the crane is back to the origin after
		// satisfying the request:
		for (Request request : subset) {
			if (maxWaitingTime == 0) {
				maxWaitingTime = request.getRequestTime();
			}
		} // TODO wrong approach; use timeLimit from createSubset() instead.

		updateData(travelTime, waitingTime, maxWaitingTime, subset.size());
	}

	private void updateData(Double travelTime, Double waitingTime, Double maxWaitingTime, int subsetSize) {
		this.subsetCounter++;
		this.satisfiedRequests += subsetSize;
		this.satisfiedRequestsBySize[subsetSize - 1]++;

		this.totTravelTime += travelTime;
		this.totWaitingTime += waitingTime;

		this.avgTravelTime = totTravelTime / subsetCounter;
		this.maxTravelTime = Math.max(travelTime, this.maxTravelTime);

		this.avgWaitingTime = totWaitingTime / satisfiedRequests;
		this.maxWaitingTime = Math.max(maxWaitingTime, this.maxWaitingTime);
	}

	public int getSubsetCounter() {
		return subsetCounter;
	}

	public int getSatisfiedRequests() {
		return satisfiedRequests;
	}

	public int[] getSatisfiedRequestsBySize() {
		return satisfiedRequestsBySize;
	}

	public Double getTotTravelTime() {
		return totTravelTime;
	}

	public Double getAvgTravelTime() {
		return avgTravelTime;
	}

	public Double getMaxTravelTime() {
		return maxTravelTime;
	}

	public Double getTotWaitingTime() {
		return totWaitingTime;
	}

	public Double getAvgWaitingTime() {
		return avgWaitingTime;
	}

	public Double getMaxWaitingTime() {
		return maxWaitingTime;
	}
}
