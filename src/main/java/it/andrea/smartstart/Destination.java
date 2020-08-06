package it.andrea.smartstart;

public interface Destination {

    void onMessage(String message);

    String getChannelName();
}
