package it.andrea.smartstart;

public class Crane implements Destination {
    private static final String CHANNEL_NAME = "crane";

    @Override
    public void onMessage(String message) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getChannelName() {
        return CHANNEL_NAME;
    }
}
