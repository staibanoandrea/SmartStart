package it.andrea.smartstart;

import java.util.List;

public class Operator {
	private String name;
	private List<Integer> workingStations;

	private void sendRequestList() {
		String operatorChannel = "OperatorChannel";
		Publisher.publish(operatorChannel, null);
	}

	public void sendNotification() {

	}
}
