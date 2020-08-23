package it.andrea.smartstart;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Orchestrator extends JedisPubSub {

    private Jedis jedis;
    private Operator operator;
    private Crane crane;
    private Shuttle shuttle;

    public Orchestrator() {
        super();
        jedis = new Jedis();
        subscribe();
    }

    private void subscribe() {
        jedis.subscribe(this, "channel");
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("Channel " + channel + " has sent a message : " + message);
        switch (channel) {
        case "a":
            break;
        default:
            break;
        }
    }

    private List<Request> translate() {
        return null;
    }
}
