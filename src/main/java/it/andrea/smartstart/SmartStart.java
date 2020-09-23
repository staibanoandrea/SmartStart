package it.andrea.smartstart;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SmartStart {
	public static Request origin;
	private static final int originIndex = 0;
	
	public static void main(String[] args) {
		/*
		 * expected line:
		 * java SmartStart alpha(double) craneSize(int) timeFile(file) coordFile(file) [mode(int)]
		 */
		// there are no input checks
		final double alpha = Double.parseDouble(args[0]);
		final int craneSize = Integer.parseInt(args[1]);
		final File timeFile = new File(args[2]);
		final File coordFile = new File(args[3]);
		int mode = 0;
		// mode is optional, and set to default if not inserted as argument
		if (args.length == 5) {
			mode = Integer.parseInt(args[4]);
		}
		
		List<Subset> craneWorkSheet = new ArrayList<Subset>();
		
		// create the list of requests from files:
		List<Request> requestList = Reader.translateFile(timeFile, coordFile);
		
		// pop the origin:
		origin = requestList.get(originIndex);
		requestList.remove(originIndex);
		
		// MODE 0: always serve the best subset first, with no other priorities:
		if (mode == 0) {
			// iteratively get the best subset to serve, and remove it from the list:
			int i = 0;
			double time = 0.0;
			while (!requestList.isEmpty()) {
				i++;
				Subset subset = createSubset(i, alpha, craneSize, requestList, time);
				time = execute(subset, time); // placeholder call for crane to work, actually moves time
				craneWorkSheet.add(subset);
				requestList.removeAll(subset.getRequestList());
			}
		}
		
		// evaluate the created subset:
		Evaluator evaluator = new Evaluator(craneSize);
		evaluator.run(craneWorkSheet);
	}

	/*
	 * createSubset takes the whole requestList, but uses it in an online fashion:
	 */
	public static Subset createSubset(int index, double alpha, int craneSize, List<Request> requestList, double time) {
		List<Request> subsetList = new ArrayList<Request>();
		List<Request> bestRSubset = new ArrayList<Request>();
		Double timeLimit = Double.MAX_VALUE; // waits forever until it gets the first request;
		for (Request r : requestList) {
			if (r.getRequestTime() > timeLimit + time) { // if the request arrives after the timelimit, it is excluded from the subset:
				break;
			} else { // add the request to the subset and compute the new timelimit for the next request:
				subsetList.add(r);
				bestRSubset = TourCalculator.kPermutate(craneSize, subsetList); // this is a temporary best subset; it becomes definitive if the next request is too late;
				timeLimit = alpha * TourCalculator.getTotalDistance(bestRSubset);
			}
		}
		return new Subset(index, bestRSubset, timeLimit);
	}

	private static double execute(Subset subset, double time) {
		return time + TourCalculator.getTotalDistance(subset.getRequestList());
	}
}
