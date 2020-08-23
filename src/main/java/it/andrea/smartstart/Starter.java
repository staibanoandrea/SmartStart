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
		kPermutateList(new ArrayList<Request>(), k, subset);
		return null;
	}

	public static void kPermutateList(List<Request> candidate, int iterationsLeft, List<Request> unusedRequests) {
		if (iterationsLeft == 0) {
			for (Request r : candidate) {
				System.out.print(r.getIndex());
			}
			System.out.println();
			// la lista è pronta per essere valutata;
		} else {
			for (Request i : unusedRequests) {
				kPermutateList(addElement(candidate, i), iterationsLeft--,
						removeElement(new ArrayList<>(unusedRequests), i));
			}
		}
	}

	private static <T> List<T> addElement(List<T> list, T element) {
		list.add(element);
		return list;
	}

	private static <T> List<T> removeElement(List<T> list, T element) {
		list.remove(element);
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
		Integer totalDistance = 0;
		ListIterator<Request> r = subset.listIterator();
		Request current = r.next();
		while (r.hasNext()) {
			Request next = r.next();
			totalDistance += chebyshevDistance(current, next);
			current = next;
		}
		return totalDistance;
	}

	private static Integer chebyshevDistance(Request source, Request destination) {
		return Math.max(Math.abs(source.getX() - destination.getX()), Math.abs(source.getY() - destination.getY()));
	}

	public static void start(List<Request> subset) {

	}
}
