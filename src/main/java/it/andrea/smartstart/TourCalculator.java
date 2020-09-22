package it.andrea.smartstart;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TourCalculator {
	/*
	 * arrange the recursive call for the real permutation method:
	 */
	public static List<Request> kPermutate(int k, List<Request> subset) {
		int size = subset.size();
		if (size <= 1) {
			return subset;
		}
		List<Request> bestSubset = new ArrayList<Request>();
		bestSubset = permutate(bestSubset, subset, Math.min(k, size), null);
		return bestSubset;
	}

	/*
	 * recursively create a permutation tree:
	 */
	private static List<Request> permutate(List<Request> permutation, List<Request> unusedItems, int k,
			List<Request> bestPermutation) {
		if (k == 0) {
			// evaluate the leaf, and keep it only if it's the "best" one:
			if (getTotalDistance(permutation) < getTotalDistance(bestPermutation)) {
				return permutation;
			} else {
				return bestPermutation;
			}
			
		} else if (permutation.isEmpty()){
			// initialize new branches:
			for (Request i : unusedItems) {
				bestPermutation = permutate(addToList(permutation, i), removeFromList(unusedItems, i), k - 1,
						bestPermutation);
			}
			
		} else {
			// pre-evaluate the current branch:
			if (getTotalDistance(permutation) > getTotalDistance(bestPermutation)) {
				return bestPermutation;
			} else {
				for (Request i : unusedItems) {
					bestPermutation = permutate(addToList(permutation, i), removeFromList(unusedItems, i), k - 1,
							bestPermutation);
				}
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
	public static Double getTotalDistance(List<Request> subset) {
		if (subset == null || subset.isEmpty()) {
			return Double.MAX_VALUE;
		}
		Double totalDistance = chebyshevDistance(SmartStart.origin, subset.get(0));
		ListIterator<Request> r = subset.listIterator();
		Request current = r.next();
		while (r.hasNext()) {
			Request next = r.next();
			totalDistance += chebyshevDistance(current, next);
			current = next;
		}
		totalDistance += chebyshevDistance(subset.get(subset.size() - 1), SmartStart.origin);
		return totalDistance;
	}

	/*
	 * calculate the chebyshev distance between two requests:
	 */
	private static Double chebyshevDistance(Request source, Request destination) {
		return Math.max(Math.abs(source.getX() - destination.getX()), Math.abs(source.getY() - destination.getY()));
	}
}
