package it.andrea.smartstart;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SmartStart {
	public static void main(String[] args) {
		/*
		 * expected line:
		 * java SmartStart alpha(double) craneSize(int) txtFile(file) [mode(int)]
		 */
		// there are no input checks
		final double alpha = Double.parseDouble(args[0]);
		final int craneSize = Integer.parseInt(args[1]);
		final File requestsFile = new File(args[2]);
		int mode = 0;
		// mode is optional, and set to default if not inserted as argument
		if (args.length == 4) {
			mode = Integer.parseInt(args[3]);
		}

		List<List<Request>> craneWorkSheet = new ArrayList<List<Request>>();
		// read the whole list:
		List<Request> requestList = Reader.translateFile(requestsFile);
		
		// MODE 0: always serve the best subset first, with no other priorities:
		if (mode == 0) {
			// iteratively get the best subset to serve, and remove it from the list:
			while (!requestList.isEmpty()) {
				List<Request> subset = createSubset(alpha, craneSize, requestList);
				craneWorkSheet.add(subset);
				requestList.removeAll(subset);
			}
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

	/*
	 * createSubset takes the whole requestList, but uses it in an online fashion:
	 */
	public static List<Request> createSubset(double alpha, int craneSize, List<Request> requestList) {
		List<Request> subset = new ArrayList<Request>();
		List<Request> bestRSubset = new ArrayList<Request>();
		Double timeLimit = Double.MAX_VALUE; // waits forever until it gets the first request;
		for (Request r : requestList) {
			if (r.getRequestTime() > timeLimit) { // if the request arrives after the timelimit, it is excluded from the subset:
				break;
			} else { // add the request to the subset and compute the new timelimit for the next request:
				subset.add(r);
				bestRSubset = TourCalculator.kPermutate(craneSize, subset); // this is a temporary best subset; it becomes definitive if the next request is too late;
				timeLimit = alpha * TourCalculator.getTotalDistance(bestRSubset);
			}
		}
		return bestRSubset;
	}

	public static void start(List<List<Request>> subset) {
	}
}
