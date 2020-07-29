package it.andrea.smartstart;

public class Request {
	private Integer index;
	private Integer xPos;
	private Integer yPos;
	private Double requestTime;

	public Request(Integer index, Integer xPos, Integer yPos, Double requestTime) {
		super();
		this.index = index;
		this.xPos = xPos;
		this.yPos = yPos;
		this.requestTime = requestTime;
	}

	public Integer getIndex() {
		return index;
	}

	public Integer getxPos() {
		return xPos;
	}

	public Integer getyPos() {
		return yPos;
	}

	public Double getRequestTime() {
		return requestTime;
	}
}
