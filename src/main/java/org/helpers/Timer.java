package org.helpers;

public class Timer {
    private long timer = 0;

    public void start() {
        timer = System.currentTimeMillis();
    }

    public long stop() {
        return System.currentTimeMillis() - timer;
    }

    public long lap() {
        long interval = System.currentTimeMillis() - timer;
        timer = System.currentTimeMillis();
        return interval;
    }

}
