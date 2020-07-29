package it.andrea.smartstart;

import java.util.List;

public class SmartStart {
	private static final double alpha = 1.0;

	public static void main(String[] args) {
		List<Request> subset = Starter.createSubset(alpha, Reader.getRequests());
		Starter.start(subset);
	}
}
