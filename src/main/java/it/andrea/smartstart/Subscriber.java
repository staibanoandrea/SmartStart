package it.andrea.smartstart;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Subscriber {
	public Subscriber(String channel) {

		Jedis jedis = null;

		try {

			/* Creating Jedis object for connecting with redis server */
			jedis = new Jedis();

			/* Creating JedisPubSub object for subscribing with channels */
			JedisPubSub jedisPubSub = new JedisPubSub() {

				@Override
				public void onMessage(String channel, String message) {
					System.out.println("Channel " + channel + " has sent a message : " + message);
				}

				@Override
				public void onSubscribe(String channel, int subscribedChannels) {
					System.out.println("Client is Subscribed to channel : " + channel);
					System.out.println("Client is Subscribed to " + subscribedChannels + " no. of channels");
				}

				@Override
				public void onUnsubscribe(String channel, int subscribedChannels) {
					System.out.println("Client is Unsubscribed from channel : " + channel);
					System.out.println("Client is Subscribed to " + subscribedChannels + " no. of channels");
				}

			};

			/* Subscribing to channel C1 and C2 */
			jedis.subscribe(jedisPubSub, channel);

		} catch (Exception ex) {

			System.out.println("Exception : " + ex.getMessage());

		} finally {

			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
