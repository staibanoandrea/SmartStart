package it.andrea.smartstart;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TourCalculator {
	public static final Request origin = new Request(null, 0, 0, null);

	/*
	 * arrange the recursive call for the real permutation method:
	 */
	public static List<Request> kPermutate(int k, List<Request> subset) {
		int size = subset.size();
		if (size <= 1) {
			return subset;
		}
		List<Request> bestSubset = new ArrayList<Request>();
		if (size <= k) {
			bestSubset = permutate(bestSubset, subset, size, null);
		} else {
			bestSubset = permutate(bestSubset, subset, k, null);
		}
		return bestSubset;
	}

	/*
	 * recursively create a permutation tree:
	 */
	private static List<Request> permutate(List<Request> permutation, List<Request> unusedItems, int k,
			List<Request> bestPermutation) {
		if (k == 0) {
			// evaluate every leaf, and keep only the "best" one:
			if (getTotalDistance(permutation) < getTotalDistance(bestPermutation)) {
				return permutation;
			} else {
				return bestPermutation;
			}
		} else {
			// run through the branches:
			for (Request i : unusedItems) {
				bestPermutation = permutate(addToList(permutation, i), removeFromList(unusedItems, i), k - 1,
						bestPermutation);
			}
		}
		return bestPermutation;
	}

	private static <T> List<T> addToList(List<T> list, T element) {
		List<T> addedList = new ArrayList<T>(list);
		addedList.add(element);
		return addedList;
	}

	private static <T> List<T> removeFromList(List<T> list, T element) {
		List<T> poppedList = new ArrayList<T>(list);
		poppedList.remove(element);
		return poppedList;
	}

	/*
	 * calculate the total distance of a sorted subset, considering the travel
	 * from/to the origin point at the start/end of the tour:
	 */
	public static Integer getTotalDistance(List<Request> subset) {
		if (subset == null || subset.isEmpty()) {
			return Integer.MAX_VALUE;
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

	/*
	 * calculate the chebyshev distance between two requests:
	 */
	private static Integer chebyshevDistance(Request source, Request destination) {
		return Math.max(Math.abs(source.getX() - destination.getX()), Math.abs(source.getY() - destination.getY()));
	}
}
