package it.andrea.smartstart;

import java.math.BigDecimal;

public class Request {
	private int index;
	private int xPos;
	private int yPos;
	private BigDecimal requestTime;

	public Request(int index, int xPos, int yPos, BigDecimal requestTime) {
		super();
		this.index = index;
		this.xPos = xPos;
		this.yPos = yPos;
		this.requestTime = requestTime;
	}

	public int getIndex() {
		return index;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public BigDecimal getRequestTime() {
		return requestTime;
	}
}
