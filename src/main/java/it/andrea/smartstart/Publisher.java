package it.andrea.smartstart;

import redis.clients.jedis.Jedis;

public class Publisher {
	public Publisher() {
	}

	public static void publish(String channel, String message) {
		Jedis jedis = null;

		try {
			/* Creating Jedis object for connecting with redis server */
			jedis = new Jedis();

			/* Publishing message to channel */
			jedis.publish(channel, message);

		} catch (Exception ex) {

			System.out.println("Exception : " + ex.getMessage());
		} finally {

			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
