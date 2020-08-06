package it.andrea.smartstart;

import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Orchestrator extends JedisPubSub {

    private Jedis jedis;
    private Map<String, Destination> destinations;

    public Orchestrator() {
        super();
        jedis = new Jedis();
    }

    public void addSubscriber(Destination destination) {
        jedis.subscribe(this, destination.getChannelName());
        destinations.put(destination.getChannelName(), destination);
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("Channel " + channel + " has sent a message : " + message);
        if (destinations.containsKey(channel)) {
            destinations.get(channel).onMessage(message);
        }
    }

    private List<Request> translate() {
        return null;
    }
}
