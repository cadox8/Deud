package me.cadox8.deud.runnable;

import java.util.Timer;
import java.util.TimerTask;

public abstract class DeudTask extends TimerTask implements Runnable {

    private final Timer timer;

    public DeudTask() {
        timer = new Timer();
    }

    /**
     * The run method to start the runnable
     */
    public abstract void run();

    public DeudTask scheduleDelayed(int delay) {
        timer.schedule(this, delay * 1000);
        stop();
        return this;
    }
    public DeudTask schedule(int initialDelay, int period) {
        timer.scheduleAtFixedRate(this, initialDelay * 1000, period * 1000);
        return this;
    }

    public void stop() {
        timer.cancel();
    }
}
