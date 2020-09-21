package it.andrea.smartstart;

public class Request {
	private Integer index;
	private Double x;
	private Double y;
	private Double requestTime;

	public Request(Integer index, Double xPos, Double yPos, Double requestTime) {
		super();
		this.index = index;
		this.x = xPos;
		this.y = yPos;
		this.requestTime = requestTime;
	}

	public Integer getIndex() {
		return index;
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	public Double getRequestTime() {
		return requestTime;
	}
}
