package it.andrea.smartstart;

import java.util.ArrayList;
import java.util.List;

public class SmartStart {
	private static final double alpha = 1.0;

	public static void main(String[] args) {
		List<List<Request>> craneWorkSheet = new ArrayList<List<Request>>();
		// read the whole list:
		List<Request> requestList = Reader.getRequests();
		// iteratively get the best subset to serve, and remove it from the list:
		while (!requestList.isEmpty()) {
			List<Request> subset = TourCalculator.createSubset(alpha, requestList);
			craneWorkSheet.add(subset);
			requestList.removeAll(subset);
		}
		// TEST print the whole list of requests, divided by subsets:
		for (List<Request> subset : craneWorkSheet) {
			for (Request request : subset) {
				System.out.print(request.getIndex() + " ");
			}
			System.out.println();
		}
		// Placeholder method for crane to work:
		start(craneWorkSheet);
	}
	
	public static void start(List<List<Request>> subset) {
	}
}
