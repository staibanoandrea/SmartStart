package it.andrea.smartstart;

public class Request {
	private Integer index;
	private Integer x;
	private Integer y;
	private Integer requestTime;

	public Request(Integer index, Integer xPos, Integer yPos, Integer requestTime) {
		super();
		this.index = index;
		this.x = xPos;
		this.y = yPos;
		this.requestTime = requestTime;
	}

	public Integer getIndex() {
		return index;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public Integer getRequestTime() {
		return requestTime;
	}
}
