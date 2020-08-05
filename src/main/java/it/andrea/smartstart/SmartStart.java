package it.andrea.smartstart;

import java.util.List;

import redis.clients.jedis.Jedis;

public class SmartStart {
	private static final double alpha = 1.0;

	public static void main(String[] args) {
		// Connecting to Redis server on localhost
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		// check whether server is running or not
		System.out.println("Server is running: " + jedis.ping());
		
		List<Request> subset = Starter.createSubset(alpha, Reader.getRequests());
		Starter.start(subset);
	}
}
