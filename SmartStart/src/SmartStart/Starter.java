package SmartStart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Starter {
	public List<Request> createSubset(BigDecimal alpha, List<Request> requestList) {// works for alpha > 0;
		List<Request> subset = new ArrayList<Request>();
		BigDecimal timeLimit = requestList.get(0).getRequestTime();
		for (Request r : requestList) {
			if (r.getRequestTime().compareTo(timeLimit) == 1) {
				break;
			} else {
				subset.add(r);
				timeLimit = alpha.multiply(getDistance(subset));
			}
		}
		return subset;
	}

	private BigDecimal getDistance(List<Request> subset) {
		
	}

	private void start() {

	}
}
