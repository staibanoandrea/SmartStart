package it.andrea.smartstart;

import java.util.List;

import redis.clients.jedis.Jedis;

public class Orchestrator {
	private void subscribe(String channel) {
		Subscriber subscriber = new Subscriber(channel);
	}
	
	private List<Request> translate() {
		return null;
	}
}
