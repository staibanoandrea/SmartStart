package it.andrea.smartstart;

import java.util.ArrayList;
import java.util.List;

public class SmartStart {
	private static final double alpha = 0.14;

	public static void main(String[] args) {
		List<List<Request>> craneWorkSheet = new ArrayList<List<Request>>();
		List<Request> requestList = Reader.getRequests();
		while (!requestList.isEmpty()) {
			List<Request> subset = Starter.createSubset(alpha, requestList);
			craneWorkSheet.add(subset);
			requestList.removeAll(subset);
		}
		// print the whole list of requests, divided by subsets:
		for (List<Request> subset : craneWorkSheet) {
			for (Request request : subset) {
				System.out.print(request.getIndex() + " ");
			}
			System.out.println();
		}
		// Placeholder method for crane to work:
		Starter.start(craneWorkSheet);
	}
}
