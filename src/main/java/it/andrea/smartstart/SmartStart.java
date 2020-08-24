package it.andrea.smartstart;

import java.util.List;

public class SmartStart {
	private static final double alpha = 1.0;
	private static final int CRANE_SIZE = 2;

	public static void main(String[] args) {
		List<Request> requestList = Reader.getRequests();
		List<Request> subset = Starter.createSubset(alpha, requestList);
		Starter.kPermutate(CRANE_SIZE, subset);
		Starter.start(subset);
	}
}
