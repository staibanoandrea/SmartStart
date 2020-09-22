package it.andrea.smartstart;

import java.util.List;
import java.util.stream.Collectors;

public class Subset {
	private Integer index;
	private List<Request> requestList;
	private Double timeWaited; // refers to the time passed between when the crane is back to the origin and
								// when it leaves to serve this subset;

	public Subset(Integer index, List<Request> requestList, Double timeWaited) {
		this.index = index;
		this.requestList = requestList;
		this.timeWaited = timeWaited;
	}

	public Integer getIndex() {
		return index;
	}

	public List<Request> getRequestList() {
		return requestList;
	}

	public Double getTimeWaited() {
		return timeWaited;
	}

	public String print() {
		return this.requestList.stream()
				.map(n -> String.valueOf(n.getIndex()))
				.collect(Collectors.joining(", ", "{", "}"));
	}
}
