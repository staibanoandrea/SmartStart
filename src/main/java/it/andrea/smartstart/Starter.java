package it.andrea.smartstart;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Starter {
	public static final Request origin = new Request(0, 0, 0, 0.0);

	public static List<Request> createSubset(Double alpha, List<Request> requestList) {// works for alpha > 0;
		List<Request> subset = new ArrayList<Request>();
		subset.add(origin);
		Double timeLimit = requestList.get(0).getRequestTime();
		for (Request r : requestList) {
			if (r.getRequestTime() > timeLimit) {
				break;
			} else {
				subset.add(r);
				timeLimit = alpha * getTotalDistance(subset);
			}
		}
		subset.add(origin);
		return subset;
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
		return Math.max(Math.abs(source.getxPos() - destination.getxPos()),
				Math.abs(source.getyPos() - destination.getyPos()));
	}

	public static void start(List<Request> subset) {

	}
}
