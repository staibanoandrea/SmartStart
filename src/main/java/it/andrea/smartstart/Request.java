package it.andrea.smartstart;

public class Request {
	private Integer index;
	private Double x;
	private Double y;
	private Double requestTime;

	public Request(Integer index, Double requestTime, Double xPos, Double yPos) {
		super();
		this.index = index;
		this.requestTime = requestTime;
		this.x = xPos;
		this.y = yPos;
	}

	public Integer getIndex() {
		return index;
	}
	
	public Double getRequestTime() {
		return requestTime;
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}
}
