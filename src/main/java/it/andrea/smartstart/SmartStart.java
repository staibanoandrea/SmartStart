package it.andrea.smartstart;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SmartStart {
	public static Request origin;
	private static final int originIndex = 0;

	public static void main(String[] args) {
		/*
		 * expected line: java SmartStart alpha(double) craneSize(int) timeFile(file)
		 * coordFile(file) [mode(int)]
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

		int index = 0;
		double craneStartTime = Double.MAX_VALUE; // the last time the crane left the origin;
		double craneBackTime = 0.0; // the last time the crane went back to the origin;

		// iteratively get the best subset to serve, and remove it from the list:
		while (!requestList.isEmpty()) {
			index++;
			Subset subset = createSubset(index, alpha, craneSize, requestList, craneStartTime, craneBackTime, mode);
			// craneStartTime is updated to when the subset is confirmed and served:
			craneStartTime = craneBackTime + subset.getTimeWaited();
			// craneBackTime is updated accordingly:
			craneBackTime = craneStartTime + TourCalculator.getTotalDistance(subset.getRequestList());
			execute(subset); // placeholder call for crane to work
			craneWorkSheet.add(subset);
			requestList.removeAll(subset.getRequestList());
		}

		// evaluate the created craneWorkSheet:
		Evaluator evaluator = new Evaluator(craneSize);
		evaluator.run(craneWorkSheet);
	}

	/*
	 * createSubset takes the whole requestList, but uses it in an online fashion:
	 */
	public static Subset createSubset(int index, double alpha, int craneSize, List<Request> requestList,
			double craneStartTime, double craneBackTime, int mode) {
		List<Request> subsetList = new ArrayList<Request>();
		List<Request> bestRSubset = new ArrayList<Request>();
		Double timeLimit = Double.MAX_VALUE; // waits forever until it gets the first request;

		// MODE 0: always serve the best subset first, with no other priorities:
		if (mode == 0) {
			for (Request r : requestList) {
				// CASE 1: the request arrives while the crane is idling:
				if (r.getRequestTime() >= craneBackTime) {
					if (r.getRequestTime() > timeLimit + craneBackTime) {
						break;
					} else {
						subsetList.add(r);
						bestRSubset = TourCalculator.kPermutate(craneSize, subsetList);
						timeLimit = alpha * TourCalculator.getTotalDistance(bestRSubset);
					}
				}
				// CASE 2: the request arrives while the crane is still traveling:
				else {
					subsetList.add(r);
					bestRSubset = TourCalculator.kPermutate(craneSize, subsetList);
					if (craneStartTime + alpha * TourCalculator.getTotalDistance(bestRSubset) > craneBackTime) {
						timeLimit = craneStartTime + alpha * TourCalculator.getTotalDistance(bestRSubset)
								- craneBackTime;
					} else {
						timeLimit = 0.0;
					}
				}
			}
		}

		// MODE 1: first come, first served. Always fill the crane:
		if (mode == 1) {
			for (int i = 0; i < craneSize; i++) {
				bestRSubset.add(requestList.get(i));
			}
			timeLimit = Math.max(bestRSubset.get(craneSize - 1).getRequestTime() - craneBackTime, 0);
		}

		// MODE 2: first come, first served. Use timeLimit to decide whether to wait:
		if (mode == 2) {
			for (Request r : requestList) {
				// CASE 1: the request arrives while the crane is idling:
				if (r.getRequestTime() >= craneBackTime) {
					if (r.getRequestTime() > timeLimit + craneBackTime) {
						break;
					} else {
						bestRSubset.add(r);
						timeLimit = alpha * TourCalculator.getTotalDistance(bestRSubset);
					}
				}
				// CASE 2: the request arrives while the crane is still traveling:
				else {
					bestRSubset.add(r);
					if (craneStartTime + alpha * TourCalculator.getTotalDistance(bestRSubset) > craneBackTime) {
						timeLimit = craneStartTime + alpha * TourCalculator.getTotalDistance(bestRSubset)
								- craneBackTime;
					} else {
						timeLimit = 0.0;
					}
				}
				// if the crane is full, start the crane:
				if (bestRSubset.size() == craneSize) {
					timeLimit = Math.max(bestRSubset.get(craneSize - 1).getRequestTime() - craneBackTime, 0);
					break;
				}
			}
		}
		
		// TODO
		// MODE 3: same as mode 0, but prevent requests starvation:

		return new Subset(index, bestRSubset, timeLimit);
	}

	private static void execute(Subset subset) {
	}
}
