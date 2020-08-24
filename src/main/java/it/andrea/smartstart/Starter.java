package it.andrea.smartstart;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Starter {
	public static final Request origin = new Request(null, 0, 0, null);

	/*
	 * Dalla lista completa delle richieste, estrae un subset di richieste ricevute
	 * entro un timeLimit (dinamico)
	 */
	public static List<Request> createSubset(Double alpha, List<Request> requestList) {// works for alpha > 0;
		List<Request> subset = new ArrayList<Request>();
		Double timeLimit = 5.0;
		// Double timeLimit = new Double(requestList.get(0).getRequestTime());
		for (Request r : requestList) {
			if (r.getRequestTime() > timeLimit) {
				break;
			} else {
				subset.add(r);
				// timeLimit = alpha * getTotalDistance(subset);
			}
		}
		return subset;
	}

	public static List<Request> kPermutate(int k, List<Request> subset) {
		int size = subset.size();
		if (size <= 1) {
			return subset;
		}
		List<Request> bestSubset = new ArrayList<Request>();
		if (size <= k) {
			recursivePermutate(bestSubset, subset, size);
			// permutate(subset, 0, size - 1);
			return bestSubset;
		}
		recursivePermutate(bestSubset, subset, k);
		// permutate(subset, 0, size - 1);
		return bestSubset;
	}

	private static void recursivePermutate(List<Request> permutation, List<Request> unusedItems, int itemsNeeded) {
		if (itemsNeeded <= 0) {
			for (Request r : permutation) {
				System.out.print(r.getIndex());
			}
			System.out.println();
		} else {
			for (Request request : unusedItems) {
				permutation.add(request);
				// List<Request> poppedList = remove(unusedItems, request);
				// itemsNeeded--;
				recursivePermutate(permutation, remove(unusedItems, request), --itemsNeeded);
			}
		}
	}

	private static List<Request> remove(List<Request> list, Request request) {
		List<Request> newList = new ArrayList<Request>(list);
		newList.remove(request);
		return newList;
	}

	private static void permutate(List<Request> list, int left, int right) {
		if (left == right) {
			for (Request r : list) {
				System.out.print(r.getIndex());
			}
			System.out.println();
		} else {
			for (int i = left; i <= right; i++) {
				List<Request> swappedList = swap(list, left, i);
				permutate(swappedList, left + 1, right);
			}
		}
	}

	private static <T> List<T> swap(List<T> list, int i, int j) {
		T temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
		return list;
	}

	/*
	 * Dal subset di richieste ricevute entro il timelimit, elabora le [craneSize]
	 * richieste col viaggio più economico da intraprendere
	 */
	public static List<Request> bestSalesmanTour(Integer destinationsLimit, List<Request> subset) {
		List<Request> result = new ArrayList<Request>();
		if (destinationsLimit == 0) {
			return null;
		}
		if (destinationsLimit == 1) {
			result.add(origin);
			result.add(subset.get(0));
			result.add(origin);
		} else {

		}
		return result;
	}

	private static Integer getTotalDistance(List<Request> subset) {
		if (subset.size() == 0) {
			return 0;
		}
		Integer totalDistance = chebyshevDistance(origin, subset.get(0));
		ListIterator<Request> r = subset.listIterator();
		Request current = r.next();
		while (r.hasNext()) {
			Request next = r.next();
			totalDistance += chebyshevDistance(current, next);
			current = next;
		}
		totalDistance += chebyshevDistance(subset.get(subset.size() - 1), origin);
		return totalDistance;
	}

	private static Integer chebyshevDistance(Request source, Request destination) {
		return Math.max(Math.abs(source.getX() - destination.getX()), Math.abs(source.getY() - destination.getY()));
	}

	public static void start(List<Request> subset) {
	}
}
